/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.contact.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.contact.constants.ContactConstants;
import com.weprode.facile.contact.service.ContactLocalServiceUtil;
import com.weprode.facile.contact.service.base.ContactCompletionLocalServiceBaseImpl;
import com.weprode.facile.contact.utils.CacheUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.facile.contact.model.ContactCompletion",
	service = AopService.class
)
public class ContactCompletionLocalServiceImpl
	extends ContactCompletionLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ContactCompletionLocalServiceImpl.class);

	private static final String COMPLETION_CONTACTS_CACHE_KEY = "UserContactsCompletion";
	private static final String COMPLETION_LISTS_CACHE_KEY = "UserListsCompletion";

	public JSONObject getCompletionResultAsJSON(String query, User user, boolean includeLists) {

		JSONObject result = new JSONObject();
		result.put(JSONConstants.SUCCESS, false);

		JSONArray completionResults = getUserContactsCompletion(user, query);

		if (includeLists) {
			JSONArray completionLists = getUserListsCompletion(user, query);
			for (int i = 0 ; i < completionLists.length() ; i++) {
				completionResults.put(completionLists.getJSONObject(i));
			}
		}

		result.put(JSONConstants.RESULTS, completionResults);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	private JSONArray getUserContactsCompletion(User user, String query) {

		JSONArray results = new JSONArray();

		// Create cache key
		String contactsCacheKey = COMPLETION_CONTACTS_CACHE_KEY + user.getUserId();

		JSONArray allContacts;
		String cache = CacheUtil.getObjectFromCache(contactsCacheKey);
		if (cache == null) {
			allContacts = generateUserContactsCache(user);
			CacheUtil.storeObjectIntoCache(contactsCacheKey, allContacts.toString());
		} else {
			allContacts = new JSONArray(cache);
		}

		String normalizedQuery = Normalizer.normalize(query, Normalizer.Form.NFKD).toLowerCase();

		for (int i=0 ; i < allContacts.length() ; ++i) {
			JSONObject contact = allContacts.getJSONObject(i);
			String lastName = Normalizer.normalize(contact.getString(JSONConstants.LAST_NAME), Normalizer.Form.NFKD).toLowerCase();
			String firstName = Normalizer.normalize(contact.getString(JSONConstants.FIRST_NAME), Normalizer.Form.NFKD).toLowerCase();

			// Match lastName, firstName and aggregations of both
			if (stringMatches(firstName, normalizedQuery) || stringMatches(lastName, normalizedQuery) || stringMatches(firstName + " " + lastName, normalizedQuery) || stringMatches(lastName + " " + firstName, normalizedQuery)) {

				try {
					User completedUser = UserLocalServiceUtil.getUser(contact.getLong(JSONConstants.USER_ID));
					contact = ContactLocalServiceUtil.convertUserToJson(completedUser);
					contact.put(JSONConstants.ID, completedUser.getUserId());
					contact.put(JSONConstants.NAME, completedUser.getFullName());
				} catch (Exception e) {
					logger.info("Error adding user " + contact.getLong(JSONConstants.USER_ID) + " to completion list for user " + user.getFullName() +" and query " + query);
				}
				results.put(contact);
			}
		}
		return results;
	}


	// User's contacts are the one from his schools plus the user's groups members
	private JSONArray generateUserContactsCache(User user) {

		JSONArray allContacts = new JSONArray();
		List<Long> addedUserId = new ArrayList<>();

		List<Organization> schools = UserOrgsLocalServiceUtil.getUserSchools(user);

		for (Organization school : schools) {
			List<User> contactList = getSchoolContacts("", user, school, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			for (User contact : contactList) {
				if (!addedUserId.contains(contact.getUserId())) {
					JSONObject jsonContact = convertUserToCompletionJson(contact);
					allContacts.put(jsonContact);
					addedUserId.add(contact.getUserId());
				}
			}
		}

		try {
			final List<User> groupMembers = ContactLocalServiceUtil.getAllGroupsContacts(user, "", QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (User member: groupMembers) {
				if (!addedUserId.contains(member.getUserId())) {
					JSONObject contact = convertUserToCompletionJson(member);
					allContacts.put(contact);
					addedUserId.add(member.getUserId());
				}
			}

		} catch (Exception e) {
			logger.error("Error when fetching contacts for all user's groups", e);
		}
		return allContacts;
	}

	private JSONObject convertUserToCompletionJson (User user) {
		JSONObject jsonUser = new JSONObject();
		jsonUser.put(JSONConstants.USER_ID, user.getUserId());
		jsonUser.put(JSONConstants.FIRST_NAME, user.getFirstName());
		jsonUser.put(JSONConstants.LAST_NAME, user.getLastName());
		return jsonUser;
	}


	private JSONArray getUserListsCompletion(User user, String query) {

		JSONArray results = new JSONArray();

		// Create cache key
		String listsCacheKey = COMPLETION_LISTS_CACHE_KEY + user.getUserId();

		JSONArray userContactTree;
		String cache = CacheUtil.getObjectFromCache(listsCacheKey);
		if (cache == null) {
			userContactTree = ContactLocalServiceUtil.getContactTree(user);
			CacheUtil.storeObjectIntoCache(listsCacheKey, userContactTree.toString());
		} else {
			userContactTree = new JSONArray(cache);
		}

		// Normalize the query
		String normalizedQuery = Normalizer.normalize(query, Normalizer.Form.NFD).toLowerCase();
		normalizedQuery = normalizedQuery.replaceAll("[^\\p{ASCII}]", "");

		for (int schoolIdx = 0 ; schoolIdx < userContactTree.length() ; schoolIdx++) {
			JSONObject jsonSchool = userContactTree.getJSONObject(schoolIdx);

			// School
			if (jsonSchool.has(JSONConstants.SCHOOL_ORG_ID) && jsonSchool.getLong(JSONConstants.SCHOOL_ORG_ID) != 0) {
				// Loop over school's lists
				JSONArray schoolLists = jsonSchool.getJSONArray(JSONConstants.PERSONALS);
				for (int i = 0 ; i < schoolLists.length() ; i++) {
					JSONObject schoolList = schoolLists.getJSONObject(i);
					if (schoolList.has(JSONConstants.POPULATION_NAME) && stringMatches(schoolList.getString(JSONConstants.POPULATION_NAME), normalizedQuery)) {
						// id is schoolId + roleId
						schoolList.put(JSONConstants.ID, Long.parseLong(
								String.valueOf(jsonSchool.getLong(JSONConstants.SCHOOL_ORG_ID)) + schoolList.getLong(JSONConstants.ROLE_ID)));
						schoolList.put(JSONConstants.NAME, schoolList.getString(JSONConstants.POPULATION_NAME));
						results.put(schoolList);
					}
				}

				if (jsonSchool.has(JSONConstants.CLASSES)) {
					JSONObject rootClasses = jsonSchool.getJSONObject(JSONConstants.CLASSES);

					JSONArray volees = rootClasses.getJSONArray(JSONConstants.VOLEES);
					for (int voleeIdx = 0; voleeIdx < volees.length(); voleeIdx++) {
						JSONObject volee = volees.getJSONObject(voleeIdx);

						// Matching volee name ?
						if (volee.has(JSONConstants.POPULATION_NAME) && stringMatches(volee.getString(JSONConstants.POPULATION_NAME), normalizedQuery)) {
							// id is voleeIdx
							volee.put(JSONConstants.ID, voleeIdx);
							// Population name is suffixed by '(Volee)'
							volee.put(JSONConstants.NAME, volee.getString(JSONConstants.GROUP_NAME) + " (Volee)");
							volee.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_ORG);
							results.put(volee);
						}

						// Loop over classes
						JSONArray classes = volee.getJSONArray(JSONConstants.CLASSES);
						for (int i = 0; i < classes.length(); i++) {
							JSONArray populations = classes.getJSONObject(i).getJSONArray(JSONConstants.POPULATIONS);
							for (int j = 0; j < populations.length(); j++) {
								JSONObject population = populations.getJSONObject(j);
								if (population.has(JSONConstants.POPULATION_NAME) && stringMatches(population.getString(JSONConstants.POPULATION_NAME), normalizedQuery)) {
									// id is schoolId + roleId
									population.put(JSONConstants.ID, Long.parseLong(
											String.valueOf(population.getLong(JSONConstants.ORG_ID)) + population.getLong(JSONConstants.ROLE_ID)));
									// Population name is suffixed by '(Classe)'
									population.put(JSONConstants.NAME, population.getString(JSONConstants.POPULATION_NAME) + " (Classe)");
									population.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_ORG);
									results.put(population);
								}
							}
						}
					}
				}

				if (jsonSchool.has(JSONConstants.COURS)) {
					JSONObject cours = jsonSchool.getJSONObject(JSONConstants.COURS);
					JSONArray coursList = cours.getJSONArray(JSONConstants.COURS);
					for (int i = 0 ; i < coursList.length() ; i++) {
						JSONArray populations = coursList.getJSONObject(i).getJSONArray(JSONConstants.POPULATIONS);
						for (int j = 0 ; j < populations.length() ; j++) {
							JSONObject population = populations.getJSONObject(j);
							if (population.has(JSONConstants.POPULATION_NAME) && stringMatches(population.getString(JSONConstants.POPULATION_NAME), normalizedQuery)) {
								// id is schoolId + roleId
								population.put(JSONConstants.ID, Long.parseLong(
										String.valueOf(population.getLong(JSONConstants.ORG_ID)) + population.getLong(JSONConstants.ROLE_ID)));
								// Population name is suffixed by '(Cours)'
								population.put(JSONConstants.NAME, population.getString(JSONConstants.POPULATION_NAME) + " (Cours)");
								population.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_ORG);
								results.put(population);
							}
						}
					}
				}
				if (jsonSchool.has(JSONConstants.SUBJECTS)) {
					JSONArray subjects = jsonSchool.getJSONArray(JSONConstants.SUBJECTS);
					for (int i = 0; i < subjects.length(); i++) {
						JSONObject subject = subjects.getJSONObject(i);
						if (subject.has(JSONConstants.POPULATION_NAME) && stringMatches(subject.getString(JSONConstants.POPULATION_NAME), normalizedQuery)) {
							// id is schoolId + roleId
							subject.put(JSONConstants.ID, Long.parseLong(
									String.valueOf(subject.getLong(JSONConstants.ORG_ID)) + subject.getLong(JSONConstants.ROLE_ID)));
							// Population name is suffixed by '(Discipline)'
							subject.put(JSONConstants.NAME, subject.getString(JSONConstants.POPULATION_NAME) + " (Discipline)");
							subject.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_ORG);
							results.put(subject);
						}
					}
				}

			} else if (jsonSchool.has(JSONConstants.GROUPS)) {
				// Communities
				JSONArray groups = jsonSchool.getJSONArray(JSONConstants.GROUPS);
				for (int i = 0 ; i < groups.length() ; i++) {
					JSONObject group = groups.getJSONObject(i);
					if (group.has(JSONConstants.GROUP_NAME) && stringMatches(group.getString(JSONConstants.GROUP_NAME), normalizedQuery)) {
						group.put(JSONConstants.ID, group.getLong(JSONConstants.GROUP_ID));
						// Population name is suffixed by '(Groupe)'
						group.put(JSONConstants.POPULATION_NAME, group.getString(JSONConstants.GROUP_NAME) + " (Groupe)");
						group.put(JSONConstants.NAME, group.getString(JSONConstants.POPULATION_NAME));
						group.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_GROUP);
						results.put(group);
					}
				}
			}
		}
		return results;
	}

	public List<User> getSchoolContacts(String search, User user, Organization school, int start, int limit, OrderByComparator comparator) {

		List<User> sortedUserList = new ArrayList<>();

		logger.info("Searching contacts in school "+school.getName()+" for user "+user.getFullName()+" with search '"+search+"' and limited between "+start+" and "+limit);

		// Process start search index
		int startIndex = 0;
		if (start == QueryUtil.ALL_POS) {
			startIndex = start;
		}

		try {
			List<User> resultUsers = new ArrayList<>();

			// For direction members: fetch all school users
			if (RoleUtilsLocalServiceUtil.isDirectionMember(user)
					|| RoleUtilsLocalServiceUtil.isInfirmiere(user)
					|| RoleUtilsLocalServiceUtil.isConseillerOrientation(user)
					|| RoleUtilsLocalServiceUtil.isSecretariat(user)) {

				// Scope is the whole school
				List<Long> orgIds = new ArrayList<>();
				orgIds.add(school.getOrganizationId());
				List<Long> roleIds = new ArrayList<>();

				List<User> schoolUsers = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, startIndex, limit, comparator);
				resultUsers.addAll(schoolUsers);

			} else if (RoleUtilsLocalServiceUtil.isStudent(user)) {

				// Students see:
				// 1. Students and teachers of their classes and cours
				List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserClasses(user, false, school.getOrganizationId());
				List<Organization> userCourses = UserOrgsLocalServiceUtil.getUserCours(user, false, school.getOrganizationId());
				List<Long> orgIds = new ArrayList<>();
				for (Organization userClass : userClasses) {
					orgIds.add(userClass.getOrganizationId());
				}
				for (Organization userCours : userCourses) {
					orgIds.add(userCours.getOrganizationId());
				}
				List<Long> roleIds = new ArrayList<>();
				roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
				roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
				List<User> schoolUsers = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, startIndex, limit, comparator);
				resultUsers.addAll(schoolUsers);

				// 2. All personals (except teachers)
				List<Long> schoolRoleIds = new ArrayList<>();
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getConseillerSocialRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getPsychologueRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getInfirmiereRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getConseillerOrientationRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getDoyenRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getSecretariatRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getCaissierComptableRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getAssistantTechniqueRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getBibliothecaireRole().getRoleId());
				resultUsers.addAll(getSchoolRolesMembers(search, school.getOrganizationId(), schoolRoleIds, startIndex, limit, comparator));

				// 3. His doyens, psychologues and conseillers sociaux
				resultUsers.addAll(UserUtilsLocalServiceUtil.getUserDoyens(user));
				resultUsers.addAll(UserUtilsLocalServiceUtil.getUserPsychologues(user));
				resultUsers.addAll(UserUtilsLocalServiceUtil.getUserConseillersSociaux(user));

				// 4. Parents
				resultUsers.addAll(UserRelationshipLocalServiceUtil.getParents(user.getUserId()));

			} else if (RoleUtilsLocalServiceUtil.isParent(user)) {

				// Parents see:
				// 1. Teachers of their classes
				List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserClasses(user, false, school.getOrganizationId());
				List<Long> orgIds = new ArrayList<>();
				for (Organization userClass : userClasses) {
					orgIds.add(userClass.getOrganizationId());
				}
				List<Long> roleIds = new ArrayList<>();
				roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
				List<User> schoolUsers = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, startIndex, limit, comparator);
				resultUsers.addAll(schoolUsers);

				// 2. All personals (except teachers)
				List<Long> schoolRoleIds = new ArrayList<>();
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getConseillerSocialRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getPsychologueRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getInfirmiereRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getConseillerOrientationRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getDoyenRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getSecretariatRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getCaissierComptableRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getAssistantTechniqueRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getBibliothecaireRole().getRoleId());
				resultUsers.addAll(getSchoolRolesMembers(search, school.getOrganizationId(), schoolRoleIds, startIndex, limit, comparator));

				// 3. His doyens, psychologue and conseiller social
				resultUsers.addAll(UserUtilsLocalServiceUtil.getUserDoyens(user));
				resultUsers.addAll(UserUtilsLocalServiceUtil.getUserPsychologues(user));
				resultUsers.addAll(UserUtilsLocalServiceUtil.getUserConseillersSociaux(user));

				// 4. Children
				resultUsers.addAll(UserRelationshipLocalServiceUtil.getChildren(user.getUserId()));

			} else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {

				// Teachers see:
				// 1. Students and parents of their cours
				List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserCours(user, false, school.getOrganizationId());
				List<Long> orgIds = new ArrayList<>();
				for (Organization userClass : userClasses) {
					orgIds.add(userClass.getOrganizationId());
				}
				List<Long> roleIds = new ArrayList<>();
				roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
				roleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());
				if (!orgIds.isEmpty()) {
					List<User> schoolUsers = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, startIndex, limit, comparator);
					resultUsers.addAll(schoolUsers);
				}

				// 2. All school teachers + personals
				List<Long> schoolRoleIds = new ArrayList<>();
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getPersonalRole().getRoleId());
				resultUsers.addAll(getSchoolRolesMembers(search, school.getOrganizationId(), schoolRoleIds, startIndex, limit, comparator));

			} else if (RoleUtilsLocalServiceUtil.isPersonal(user)) {

				// All school personals
				List<Long> schoolRoleIds = new ArrayList<>();
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getPersonalRole().getRoleId());
				resultUsers.addAll(getSchoolRolesMembers(search, school.getOrganizationId(), schoolRoleIds, startIndex, limit, comparator));
			}

			// Additional roles
			if (RoleUtilsLocalServiceUtil.isDoyen(user)) {

				// Students, parents and teachers of their affected classes
				List<Organization> doyenAffectedClasses = UserOrgsLocalServiceUtil.getAffectedClasses(user, RoleUtilsLocalServiceUtil.getDoyenRole().getRoleId());
				List<Long> orgIds = new ArrayList<>();
				for (Organization doyenAffectedClass : doyenAffectedClasses) {
					orgIds.add(doyenAffectedClass.getOrganizationId());
				}
				List<Long> roleIds = new ArrayList<>();
				roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
				roleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());
				roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
				List<User> doyenAffectedUsers = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, startIndex, limit, comparator);
				resultUsers.addAll(doyenAffectedUsers);


			} else if (RoleUtilsLocalServiceUtil.isPsychologue(user)) {

				// Students, parents and teachers of their affected classes
				List<Organization> psyAffectedClasses = UserOrgsLocalServiceUtil.getAffectedClasses(user, RoleUtilsLocalServiceUtil.getPsychologueRole().getRoleId());
				List<Long> orgIds = new ArrayList<>();
				for (Organization psyAffectedClass : psyAffectedClasses) {
					orgIds.add(psyAffectedClass.getOrganizationId());
				}
				List<Long> roleIds = new ArrayList<>();
				roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
				roleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());
				roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
				List<User> psyAffectedUsers = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, startIndex, limit, comparator);
				resultUsers.addAll(psyAffectedUsers);

			} else if (RoleUtilsLocalServiceUtil.isConseillerSocial(user)) {

				// Students, parents and teachers of their affected classes
				List<Organization> conseillerSocialAffectedClasses = UserOrgsLocalServiceUtil.getAffectedClasses(user, RoleUtilsLocalServiceUtil.getConseillerSocialRole().getRoleId());
				List<Long> orgIds = new ArrayList<>();
				for (Organization conseillerSocialAffectedClass : conseillerSocialAffectedClasses) {
					orgIds.add(conseillerSocialAffectedClass.getOrganizationId());
				}
				List<Long> roleIds = new ArrayList<>();
				roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
				roleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());
				roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
				List<User> conseillerSocialAffectedUsers = UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, startIndex, limit, comparator);
				resultUsers.addAll(conseillerSocialAffectedUsers);
			}

			// Unify the list
			List<User> uniqueList = new ArrayList<>();
			List<Long> uniqueIdsList = new ArrayList<>();
			for (User _user : resultUsers) {
				if (!uniqueIdsList.contains(_user.getUserId())) {
					uniqueList.add(_user);
					uniqueIdsList.add(_user.getUserId());
				}
			}

			// Sort with the given comparator
			if (comparator != null) {
				uniqueList.sort(comparator);
			}

			if (start != QueryUtil.ALL_POS && limit != QueryUtil.ALL_POS) {
				// Start and limit are provided
				int max = (Math.min(uniqueList.size(), limit));
				// To prevent infinite loops error
				if (start <= max) {
					sortedUserList = uniqueList.subList(start, max);
				}
			} else {
				sortedUserList = uniqueList;
			}

		} catch (Exception e) {
			logger.error("Error when fetching all school's classes for "+school.getName(), e);
		}

		return sortedUserList;
	}

	private List<User> getSchoolRolesMembers(String search, long schoolId, List<Long> roleIds, int startIndex, int limit, OrderByComparator comparator) {

		List<Long> orgIds = new ArrayList<>();
		orgIds.add(schoolId);
		try {
			return UserSearchLocalServiceUtil.searchUsers(search, orgIds, null, roleIds, null, startIndex, limit, comparator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public void removeCompletionResultsFromCache(long userId) {
		String cacheKey = COMPLETION_LISTS_CACHE_KEY + userId;
		CacheUtil.removeObjectFromCache(cacheKey);
		cacheKey = COMPLETION_CONTACTS_CACHE_KEY + userId;
		CacheUtil.removeObjectFromCache(cacheKey);
	}

	private boolean stringMatches(String source, String query) {
		String name = Normalizer.normalize(source, Normalizer.Form.NFKD).toLowerCase();
		name = name.replaceAll("[^\\p{ASCII}]", "");
		String newQuery = query.replaceAll("[^\\p{ASCII}]", "");
		return name.length() >= newQuery.length() && name.contains(newQuery);
	}

}