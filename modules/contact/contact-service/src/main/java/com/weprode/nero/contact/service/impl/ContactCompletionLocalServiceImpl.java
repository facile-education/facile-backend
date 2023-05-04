/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.weprode.nero.contact.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.weprode.nero.contact.constants.ContactConstants;
import com.weprode.nero.contact.service.ContactLocalServiceUtil;
import com.weprode.nero.contact.service.base.ContactCompletionLocalServiceBaseImpl;
import com.weprode.nero.contact.utils.CacheUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.contact.model.ContactCompletion",
	service = AopService.class
)
public class ContactCompletionLocalServiceImpl
	extends ContactCompletionLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ContactCompletionLocalServiceImpl.class);

	private static final String COMPLETION_CONTACTS_CACHE_KEY = "UserContactsCompletion";
	private static final String COMPLETION_LISTS_CACHE_KEY = "UserListsCompletion";

	public JSONObject getCompletionResultAsJSON(String query, User user, boolean includeLists) {

		JSONObject result = new JSONObject();
		result.put("success", false);

		JSONArray completionResults = getUserContactsCompletion(user, query);

		if (includeLists) {
			JSONArray completionLists = getUserListsCompletion(user, query);
			for (int i = 0 ; i < completionLists.length() ; i++) {
				completionResults.put(completionLists.getJSONObject(i));
			}
		}

		result.put("results", completionResults);
		result.put("success", true);

		return result;
	}

	private JSONArray getUserContactsCompletion(User user, String query) {

		JSONArray results = new JSONArray();

		// Create cache key
		String contactsCacheKey = COMPLETION_CONTACTS_CACHE_KEY + user.getUserId();
		// TODO Cache
		// JSONArray allContacts = (JSONArray) CacheUtil.getObjectFromCache(contactsCacheKey);
		JSONArray allContacts = null;

		if (allContacts == null) {
			allContacts = generateUserContactsCache(user);
			// We store in cache for half an hour (30 minutes)
			// TODO Cache
			// CacheUtil.storeObjectIntoCache(contactsCacheKey, allContacts, 1800000);
		}

		for (int i=0 ; i < allContacts.length() ; ++i) {
			JSONObject contact = allContacts.getJSONObject(i);
			String lastName = Normalizer.normalize(contact.getString(ContactConstants.LASTNAME), Normalizer.Form.NFC).toLowerCase();
			String firstName = Normalizer.normalize(contact.getString(ContactConstants.FIRSTNAME), Normalizer.Form.NFC).toLowerCase();
			String normalizedQuery = Normalizer.normalize(query, Normalizer.Form.NFC).toLowerCase();

			if (stringMatches(firstName, normalizedQuery) || stringMatches(lastName, normalizedQuery)) {

				try {
					User completedUser = UserLocalServiceUtil.getUser(contact.getLong(ContactConstants.USER_ID));
					contact = ContactLocalServiceUtil.convertUserToJson(completedUser);
					contact.put(COMPLETION_ID, completedUser.getUserId());
					contact.put(COMPLETION_NAME, completedUser.getFullName());
				} catch (Exception e) {
					logger.info("Error adding user " + contact.getLong(ContactConstants.USER_ID) + " to completion list for user " + user.getFullName() +" and query " + query);
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
		jsonUser.put(ContactConstants.USER_ID, user.getUserId());
		jsonUser.put(ContactConstants.FIRSTNAME, user.getFirstName());
		jsonUser.put(ContactConstants.LASTNAME, user.getLastName());
		return jsonUser;
	}


	private JSONArray getUserListsCompletion(User user, String query) {

		JSONArray results = new JSONArray();

		// Create cache key
		String listsCacheKey = COMPLETION_LISTS_CACHE_KEY + user.getUserId();
		// TODO Cache
		// JSONArray userContactTree = (JSONArray) CacheUtil.getObjectFromCache(listsCacheKey);
		JSONArray userContactTree = null;

		if (userContactTree == null) {
			userContactTree = ContactLocalServiceUtil.getContactTree(user);
			// We store in cache for half an hour (30 minutes)
			// TODO Cache
			// CacheUtil.storeObjectIntoCache(listsCacheKey, userContactTree, 1800000);
		}

		// Normalize the query
		String normalizedQuery = Normalizer.normalize(query, Normalizer.Form.NFD).toLowerCase();
		normalizedQuery = normalizedQuery.replaceAll("[^\\p{ASCII}]", "");

		for (int schoolIdx = 0 ; schoolIdx < userContactTree.length() ; schoolIdx++) {
			JSONObject jsonSchool = userContactTree.getJSONObject(schoolIdx);

			// School
			if (jsonSchool.getLong(ContactConstants.SCHOOL_ORG_ID) != 0) {
				// Loop over school's lists
				JSONArray schoolLists = jsonSchool.getJSONArray(ContactConstants.PERSONALS);
				for (int i = 0 ; i < schoolLists.length() ; i++) {
					JSONObject schoolList = schoolLists.getJSONObject(i);
					if (stringMatches(schoolList.getString(ContactConstants.POPULATION_NAME), normalizedQuery)) {
						// id is schoolId + roleId
						schoolList.put(COMPLETION_ID, Long.parseLong(
								String.valueOf(jsonSchool.getLong(ContactConstants.SCHOOL_ORG_ID)) + schoolList.getLong(ContactConstants.ROLE_ID)));
						schoolList.put(COMPLETION_NAME, schoolList.getString(ContactConstants.POPULATION_NAME));
						results.put(schoolList);
					}
				}

				if (jsonSchool.has(ContactConstants.CLASSES)) {
					JSONObject rootClasses = jsonSchool.getJSONObject(ContactConstants.CLASSES);

					JSONArray volees = rootClasses.getJSONArray(ContactConstants.VOLEES);
					for (int voleeIdx = 0; voleeIdx < volees.length(); voleeIdx++) {
						JSONObject volee = volees.getJSONObject(voleeIdx);

						// Matching volee name ?
						if (stringMatches(volee.getString(ContactConstants.POPULATION_NAME), normalizedQuery)) {
							// id is voleeIdx
							volee.put(COMPLETION_ID, voleeIdx);
							// Population name is suffixed by '(Volee)'
							volee.put(COMPLETION_NAME, volee.getString(ContactConstants.GROUP_NAME) + " (Volee)");
							volee.put(ContactConstants.RECIPIENT_TYPE, ContactConstants.RECIPIENT_TYPE_ORG);
							results.put(volee);
						}

						// Loop over classes
						JSONArray classes = volee.getJSONArray(ContactConstants.CLASSES);
						for (int i = 0; i < classes.length(); i++) {
							JSONArray populations = classes.getJSONObject(i).getJSONArray("populations");
							for (int j = 0; j < populations.length(); j++) {
								JSONObject population = populations.getJSONObject(j);
								if (stringMatches(population.getString(ContactConstants.POPULATION_NAME), normalizedQuery)) {
									// id is schoolId + roleId
									population.put(COMPLETION_ID, Long.parseLong(
											String.valueOf(population.getLong(ContactConstants.ORG_ID)) + population.getLong(ContactConstants.ROLE_ID)));
									// Population name is suffixed by '(Classe)'
									population.put(COMPLETION_NAME, population.getString(ContactConstants.POPULATION_NAME) + " (Classe)");
									population.put(ContactConstants.RECIPIENT_TYPE, ContactConstants.RECIPIENT_TYPE_ORG);
									results.put(population);
								}
							}
						}
					}
				}
				JSONObject cours = jsonSchool.getJSONObject(ContactConstants.COURS);
				if (cours != null) {
					JSONArray coursList = cours.getJSONArray(ContactConstants.COURS);
					for (int i = 0 ; i < coursList.length() ; i++) {
						JSONArray populations = coursList.getJSONObject(i).getJSONArray("populations");
						for (int j = 0 ; j < populations.length() ; j++) {
							JSONObject population = populations.getJSONObject(j);
							if (stringMatches(population.getString(ContactConstants.POPULATION_NAME), normalizedQuery)) {
								// id is schoolId + roleId
								population.put(COMPLETION_ID, Long.parseLong(
										String.valueOf(population.getLong(ContactConstants.ORG_ID)) + population.getLong(ContactConstants.ROLE_ID)));
								// Population name is suffixed by '(Cours)'
								population.put(COMPLETION_NAME, population.getString(ContactConstants.POPULATION_NAME) + " (Cours)");
								population.put(ContactConstants.RECIPIENT_TYPE, ContactConstants.RECIPIENT_TYPE_ORG);
								results.put(population);
							}
						}
					}
				}
				JSONArray subjects = jsonSchool.getJSONArray(ContactConstants.SUBJECTS);
				if (subjects != null) {
					for (int i = 0; i < subjects.length(); i++) {
						JSONObject subject = subjects.getJSONObject(i);
						if (stringMatches(subject.getString(ContactConstants.POPULATION_NAME), normalizedQuery)) {
							// id is schoolId + roleId
							subject.put(COMPLETION_ID, Long.parseLong(
									String.valueOf(subject.getLong(ContactConstants.ORG_ID)) + subject.getLong(ContactConstants.ROLE_ID)));
							// Population name is suffixed by '(Discipline)'
							subject.put(COMPLETION_NAME, subject.getString(ContactConstants.POPULATION_NAME) + " (Discipline)");
							subject.put(ContactConstants.RECIPIENT_TYPE, ContactConstants.RECIPIENT_TYPE_ORG);
							results.put(subject);
						}
					}
				}

			} else {
				// Communities
				JSONArray groups = jsonSchool.getJSONArray(ContactConstants.GROUPS);
				for (int i = 0 ; i < groups.length() ; i++) {
					JSONObject group = groups.getJSONObject(i);
					if (stringMatches(group.getString(ContactConstants.GROUP_NAME), normalizedQuery)) {
						group.put(COMPLETION_ID, group.getLong(ContactConstants.GROUP_ID));
						// Population name is suffixed by '(Groupe)'
						group.put(ContactConstants.POPULATION_NAME, group.getString(ContactConstants.GROUP_NAME) + " (Groupe)");
						group.put(COMPLETION_NAME, group.getString(ContactConstants.POPULATION_NAME));
						group.put(ContactConstants.RECIPIENT_TYPE, ContactConstants.RECIPIENT_TYPE_GROUP);
						results.put(group);
					}
				}
			}
		}
		return results;
	}

	public List<User> getSchoolContacts(String search, User user, Organization school, int start, int limit, OrderByComparator comparator) {

		List<User> sortedUserList = new ArrayList<User>();

		logger.info("Searching contacts in school "+school.getName()+" for user "+user.getFullName()+" with search '"+search+"' and limited between "+start+" and "+limit);

		// Process start search index
		int startIndex = 0;
		if (start == QueryUtil.ALL_POS) {
			startIndex = start;
		} else {
			startIndex = 0;
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

				// 2. Direction members and infirmieres
				List<Long> schoolRoleIds = new ArrayList<>();
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getInfirmiereRole().getRoleId());
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

				// 2. Direction members and infirmiers
				List<Long> schoolRoleIds = new ArrayList<>();
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getInfirmiereRole().getRoleId());
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
				schoolRoleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
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

			} else if (RoleUtilsLocalServiceUtil.isPersonal(user)) {
				// All school personals
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
				Collections.sort(uniqueList, comparator);
			}

			if (start != QueryUtil.ALL_POS && limit != QueryUtil.ALL_POS) {
				// Start and limit are provided
				int max = ((uniqueList.size() < limit) ? uniqueList.size() : limit);
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
		String name = Normalizer.normalize(source, Normalizer.Form.NFD).toLowerCase();
		name = name.replaceAll("[^\\p{ASCII}]", "");
		return name.length() >= query.length() && name.contains(query);
	}


	private static final String COMPLETION_ID = "id";
	private static final String COMPLETION_NAME = "name";

}