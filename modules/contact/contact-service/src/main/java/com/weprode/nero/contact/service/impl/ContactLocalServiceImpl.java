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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.contact.constants.ContactConstants;
import com.weprode.nero.contact.service.base.ContactLocalServiceBaseImpl;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.constants.NeroRoleConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.service.TeacherSubjectLocalServiceUtil;
import com.weprode.nero.user.model.UserContact;
import com.weprode.nero.user.service.UserContactLocalServiceUtil;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.contact.model.Contact",
	service = AopService.class
)
public class ContactLocalServiceImpl extends ContactLocalServiceBaseImpl {


	private static final Log logger = LogFactoryUtil.getLog(ContactLocalServiceImpl.class);

	public JSONArray getContactTree (User user) {

		JSONArray jsonTree = new JSONArray();
		try {

			// First, loop over user's schools
			List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserSchools(user);
			for (Organization userSchool : userSchools) {

				JSONObject jsonSchool = new JSONObject();
				jsonSchool.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userSchool.getName(), true));
				jsonSchool.put(JSONConstants.SCHOOL_ORG_ID, userSchool.getOrganizationId());
				jsonSchool.put(JSONConstants.IS_EXPANDED, true);

				// Global personal lists
				JSONArray jsonSchoolGlobalLists = new JSONArray();
				jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId(), NeroRoleConstants.DIRECTION_INCLUSIVE, user.getUserId()));
				jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getInfirmiereRole().getRoleId(), NeroRoleConstants.INFIRMIERE_INCLUSIVE, user.getUserId()));
				jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getConseillerOrientationRole().getRoleId(), NeroRoleConstants.CONSEILLER_ORIENTATION_INCLUSIVE, user.getUserId()));
				jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getSecretariatRole().getRoleId(), NeroRoleConstants.SECRETAIRE_INCLUSIVE, user.getUserId()));
				jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getCaissierComptableRole().getRoleId(), NeroRoleConstants.CAISSIER_COMPTABLE_INCLUSIVE, user.getUserId()));
				jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getAssistantTechniqueRole().getRoleId(), NeroRoleConstants.ASSISTANT_TECHNIQUE_INCLUSIVE, user.getUserId()));
				jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getBibliothecaireRole().getRoleId(), NeroRoleConstants.BIBLIOTHECAIRE_INCLUSIVE, user.getUserId()));

				if (RoleUtilsLocalServiceUtil.isStudent(user)
						|| RoleUtilsLocalServiceUtil.isParent(user)) {
					jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), ContactConstants.MY_TEACHER, NeroRoleConstants.TEACHER_INCLUSIVE, user.getUserId()));
					jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), ContactConstants.MY_DOYEN, NeroRoleConstants.DOYEN_INCLUSIVE, user.getUserId()));
					jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), ContactConstants.MY_PSY, NeroRoleConstants.PSYCHOLOGUE_INCLUSIVE, user.getUserId()));
					jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), ContactConstants.MY_SOCIAL, NeroRoleConstants.CONSEILLER_SOCIAL_INCLUSIVE, user.getUserId()));
				} else {
					jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId(), NeroRoleConstants.TEACHER_INCLUSIVE, user.getUserId()));
					jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getDoyenRole().getRoleId(), NeroRoleConstants.DOYEN_INCLUSIVE, user.getUserId()));
					jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getPsychologueRole().getRoleId(), NeroRoleConstants.PSYCHOLOGUE_INCLUSIVE, user.getUserId()));
					jsonSchoolGlobalLists.put(getJsonPopulation(userSchool.getGroupId(), userSchool.getOrganizationId(), RoleUtilsLocalServiceUtil.getConseillerSocialRole().getRoleId(), NeroRoleConstants.CONSEILLER_SOCIAL_INCLUSIVE, user.getUserId()));
				}

				jsonSchool.put(JSONConstants.PERSONALS, jsonSchoolGlobalLists);

				// Classes
				List<Organization> schoolClasses = getUserClasses(user, userSchool);
				if (!schoolClasses.isEmpty()) {
					jsonSchool.put(JSONConstants.CLASSES, getJsonClasses(user, schoolClasses));
				}

				// Cours
				if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
					List<Organization> schoolCours = getUserCours(user, userSchool);
					if (!schoolCours.isEmpty()) {
						JSONObject jsonCours = new JSONObject();
						jsonCours.put(JSONConstants.IS_EXPANDED, true);
						jsonCours.put(JSONConstants.IS_SELECTABLE, false);
						jsonCours.put(JSONConstants.COURS, getOrgTypePopulations(user, schoolCours));
						jsonSchool.put(JSONConstants.COURS, jsonCours);
					}
				}

				// Subjects
				List<Organization> schoolSubjects = getUserSubjects(user, userSchool);
				if (!schoolSubjects.isEmpty()) {
					jsonSchool.put(JSONConstants.SUBJECTS, getOrgTypePopulations(user, schoolSubjects));
				}

				jsonTree.put(jsonSchool);
			}

			// Communities
			JSONObject jsonCommunities = new JSONObject();
			jsonCommunities.put(JSONConstants.SCHOOL_NAME, ContactConstants.COMMUNITIES);
			jsonCommunities.put(JSONConstants.SCHOOL_ORG_ID, 0);
			JSONArray jsonPersGroups = new JSONArray();
			List<Group> userGroups = CommunityInfosLocalServiceUtil.getUserCommunities(user.getUserId(), false, true);
			for (Group userGroup : userGroups) {
				JSONObject jsonGroup = new JSONObject();
				jsonGroup.put(JSONConstants.GROUP_NAME, userGroup.getName(user.getLocale()));
				jsonGroup.put(JSONConstants.GROUP_ID, userGroup.getGroupId());
				jsonGroup.put(JSONConstants.ROLE_ID, 0);
				jsonGroup.put(JSONConstants.IS_COMMUNITY, true);
				jsonGroup.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_GROUP);
				jsonPersGroups.put(jsonGroup);
			}
			jsonCommunities.put(JSONConstants.GROUPS, jsonPersGroups);
			jsonTree.put(jsonCommunities);

		} catch (Exception e) {
			logger.error("Error when fetching groups to broadcast a group news", e);
		}
		return jsonTree;
	}


	private List<Organization> getUserClasses(User user, Organization school) {
		List<Organization> schoolClasses = new ArrayList<>();
		if (RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
			// No classes for students and parents
		} else {
			// Affected roles: doyen, psychologue, conseiller social
			if (RoleUtilsLocalServiceUtil.isDoyen(user)
					|| RoleUtilsLocalServiceUtil.isConseillerSocial(user)
					|| RoleUtilsLocalServiceUtil.isPsychologue(user)) {
				schoolClasses = UserOrgsLocalServiceUtil.getRoleAffectedClasses(user);
			} else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
				schoolClasses = UserOrgsLocalServiceUtil.getUserClasses(user, false, school.getOrganizationId());
			} else {
				// All school's classes
				List<Integer> types = new ArrayList<>();
				types.add(OrgConstants.CLASS_TYPE);
				schoolClasses = OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, false);
			}
		}
		return schoolClasses;
	}

	// Only teachers see their cours
	private List<Organization> getUserCours(User user, Organization school) {
		if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return UserOrgsLocalServiceUtil.getUserCours(user, false, school.getOrganizationId());
		} else {
			return new ArrayList<>();
		}
	}

	private List<Organization> getUserSubjects(User user, Organization school) {
		//  - Direction members see all subject orgs
		//  - Teachers see only theirs
		if (RoleUtilsLocalServiceUtil.isDirectionMember(user)) {
			List<Integer> types = new ArrayList<>();
			types.add(OrgConstants.SUBJECT_TYPE);
			return OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, false);
		} else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return UserOrgsLocalServiceUtil.getUserSubjectOrganizations(user, school.getOrganizationId(), false);
		} else {
			return new ArrayList<>();
		}
	}


	private JSONObject getJsonClasses(User user, List<Organization> userClasses) {

		JSONObject result = new JSONObject();

		// First group them in volees
		Map<String, List<Organization>> voleeMap = new HashMap<>();
		for (Organization userClass : userClasses) {
			String volee = OrgUtilsLocalServiceUtil.formatOrgName(userClass.getName(), false).substring(0, 2);
			if (OrgUtilsLocalServiceUtil.isVoleeAuthorized(volee)) {
				if (!voleeMap.containsKey(volee)) {
					voleeMap.put(volee, new ArrayList<>());
				}
				voleeMap.get(volee).add(userClass);
			}
		}

		// Loop over volees
		JSONArray jsonVolees = new JSONArray();
		for (String volee : voleeMap.keySet()) {
			JSONObject jsonVolee = new JSONObject();
			jsonVolee.put(JSONConstants.GROUP_NAME, volee);
			jsonVolee.put(JSONConstants.IS_SELECTABLE, false);
			jsonVolee.put(JSONConstants.IS_EXPANDED, false);

			// Loop over classes
			JSONArray jsonClasses = new JSONArray();
			for (Organization userClass : voleeMap.get(volee)) {
				JSONObject jsonClass = new JSONObject();
				jsonClass.put(JSONConstants.GROUP_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userClass.getName(), false));
				jsonClass.put(JSONConstants.ORG_ID, userClass.getOrganizationId());
				jsonClass.put(JSONConstants.ROLE_ID, 0);
				jsonClass.put(JSONConstants.IS_SELECTABLE, false);
				jsonClass.put(JSONConstants.IS_EXPANDED, false);
				jsonClass.put(JSONConstants.POPULATIONS, getOrgPopulations(user, userClass));
				jsonClasses.put(jsonClass);
			}

			jsonVolee.put(JSONConstants.CLASSES, jsonClasses);
			jsonVolees.put(jsonVolee);
		}

		result.put(JSONConstants.IS_SELECTABLE, false);
		result.put(JSONConstants.IS_EXPANDED, true);
		result.put(JSONConstants.VOLEES, jsonVolees);
		return result;
	}


	private JSONArray getOrgTypePopulations(User user, List<Organization> orgs) {
		JSONArray jsonGroups = new JSONArray();
		try {
			for (Organization org : orgs) {
				JSONObject jsonGroup = new JSONObject();
				jsonGroup.put(JSONConstants.GROUP_NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false));
				jsonGroup.put(JSONConstants.ORG_ID, org.getOrganizationId());

				boolean isSubject = OrgDetailsLocalServiceUtil.isSubject(org.getOrganizationId());
				long teacherRoleId = RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId();
				jsonGroup.put(JSONConstants.ROLE_ID, isSubject ? teacherRoleId : 0);
				jsonGroup.put(JSONConstants.IS_SELECTABLE, isSubject);
				// No sub-population for subjects
				if (!isSubject) {
					jsonGroup.put(JSONConstants.POPULATIONS, getOrgPopulations(user, org));
				} else {
					String populationName = OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false);
					if (!UserOrgsLocalServiceUtil.getUserSchools(user).isEmpty() && org.getParentOrganizationId() != 0) {
						populationName += ContactConstants.OF + OrgUtilsLocalServiceUtil.formatOrgName(org.getParentOrganization().getName(), true);
					}
					jsonGroup.put(JSONConstants.POPULATION_NAME, populationName);
				}
				jsonGroups.put(jsonGroup);
			}
		} catch (Exception e) {
			logger.error("Error building populations for user " + user.getUserId());
		}
		return jsonGroups;
	}

	// For classes, cours and volees: students, parents, teachers
	// For volees : main teachers
	private JSONArray getOrgPopulations(User user, Organization org) {

		JSONArray rolePopulations = new JSONArray();

		try {
			int type = OrgDetailsLocalServiceUtil.getOrgDetails(org.getOrganizationId()).getType();
			if (type == OrgConstants.CLASS_TYPE || type == OrgConstants.COURS_TYPE) {

				if (!RoleUtilsLocalServiceUtil.isParent(user)) {
					rolePopulations.put(getJsonPopulation(org.getGroupId(), org.getOrganizationId(), RoleUtilsLocalServiceUtil.getStudentRole().getRoleId(), NeroRoleConstants.STUDENT_INCLUSIVE, user.getUserId()));
				}
				rolePopulations.put(getJsonPopulation(org.getGroupId(), org.getOrganizationId(), RoleUtilsLocalServiceUtil.getParentRole().getRoleId(), NeroRoleConstants.PARENT_INCLUSIVE, user.getUserId()));
				rolePopulations.put(getJsonPopulation(org.getGroupId(), org.getOrganizationId(), RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId(), NeroRoleConstants.TEACHER_INCLUSIVE, user.getUserId()));
			}
		} catch (Exception e) {
			logger.error("Error building populations for org " + org.getOrganizationId(), e);
		}
		return rolePopulations;
	}

	private JSONObject getJsonPopulation(long groupId, long orgId, long roleId, String groupName, long userId) {

		JSONObject population = new JSONObject();
		population.put(JSONConstants.GROUP_ID, groupId);
		population.put(JSONConstants.ORG_ID, orgId);
		population.put(JSONConstants.GROUP_NAME, groupName);
		population.put(JSONConstants.ROLE_ID, roleId);
		population.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_ORG);
		population.put(JSONConstants.POPULATION_NAME, getPopulationName(orgId, roleId, userId));

		return population;
	}

	public String getPopulationName(long orgId, long roleId, long userId) {
		String populationName = "";
		try {
			Organization org = OrganizationLocalServiceUtil.getOrganization(orgId);
			String orgName = OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false);
			// Subject orgs already have the role inside
			if (OrgDetailsLocalServiceUtil.isSubject(orgId)) {
				populationName = orgName;
			} else {
				if (roleId == RoleUtilsLocalServiceUtil.getStudentRole().getRoleId()) {
					if (OrgDetailsLocalServiceUtil.isSchool(orgId)) {
						populationName = "Tous les élèves";
					} else if (OrgDetailsLocalServiceUtil.isVolee(orgId)) {
						populationName = "Elèves de la volée " + orgName;
					} else {
						populationName = NeroRoleConstants.STUDENT_INCLUSIVE + ContactConstants.OF + orgName;
					}
				} else if (roleId == RoleUtilsLocalServiceUtil.getParentRole().getRoleId()) {
					if (OrgDetailsLocalServiceUtil.isSchool(orgId)) {
						populationName = "Tous les responsables légaux";
					} else if (OrgDetailsLocalServiceUtil.isVolee(orgId)) {
						populationName = "Responsables légaux  de la volée " + orgName;
					} else {
						populationName = NeroRoleConstants.PARENT_INCLUSIVE + ContactConstants.OF + orgName;
					}
				} else if (roleId == RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId()) {
					if (OrgDetailsLocalServiceUtil.isSchool(orgId)) {
						populationName = "Tous les enseignants";
					} else if (OrgDetailsLocalServiceUtil.isVolee(orgId)) {
						populationName = "Enseignants de la volée " + orgName;
					} else {
						populationName = NeroRoleConstants.TEACHER_INCLUSIVE + ContactConstants.OF + orgName;
					}
				} else if (roleId == RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId()) {
					populationName = NeroRoleConstants.DIRECTION_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getDoyenRole().getRoleId()) {
					populationName = NeroRoleConstants.DOYEN_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getMainTeacherRole().getRoleId()) {
					populationName = NeroRoleConstants.MAIN_TEACHER_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getConseillerOrientationRole().getRoleId()) {
					populationName = NeroRoleConstants.CONSEILLER_ORIENTATION_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getConseillerSocialRole().getRoleId()) {
					populationName = NeroRoleConstants.CONSEILLER_SOCIAL_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getInfirmiereRole().getRoleId()) {
					populationName = NeroRoleConstants.INFIRMIERE_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getPsychologueRole().getRoleId()) {
					populationName = NeroRoleConstants.PSYCHOLOGUE_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getAssistantTechniqueRole().getRoleId()) {
					populationName = NeroRoleConstants.ASSISTANT_TECHNIQUE_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getCaissierComptableRole().getRoleId()) {
					populationName = NeroRoleConstants.CAISSIER_COMPTABLE_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getBibliothecaireRole().getRoleId()) {
					populationName = NeroRoleConstants.BIBLIOTHECAIRE_INCLUSIVE + ContactConstants.OF + orgName;
				} else if (roleId == RoleUtilsLocalServiceUtil.getSecretariatRole().getRoleId()) {
					populationName = NeroRoleConstants.SECRETAIRE_INCLUSIVE + ContactConstants.OF + orgName;
				}
			}
			// If user is multi-school, and if the org is not a school, suffix each population by the school's name
			User user = UserLocalServiceUtil.getUser(userId);
			if (UserOrgsLocalServiceUtil.getUserSchools(user).size() > 1 && !OrgDetailsLocalServiceUtil.isSchool(org.getOrganizationId())) {
				populationName += ContactConstants.OF + OrgUtilsLocalServiceUtil.formatOrgName(org.getParentOrganization().getName(), true);
			}
		} catch (Exception e) {
			logger.error("Error formatting population for roleId " + roleId + " and orgId " + orgId, e);
		}
		return populationName;
	}


	public List<User> getListMembers(User currentUser, long roleId, long orgId) {

		logger.info("Search list members for roleId="+roleId+", orgId="+orgId);
		List<User> uniqueList = new ArrayList<>();

		try {
			List<User> members = new ArrayList<>();
			if (roleId == ContactConstants.MY_SOCIAL) {

				members = UserUtilsLocalServiceUtil.getUserConseillersSociaux(currentUser);

			} else if (roleId == ContactConstants.MY_PSY) {

				members = UserUtilsLocalServiceUtil.getUserPsychologues(currentUser);

			} else if (roleId == ContactConstants.MY_DOYEN) {

				members = UserUtilsLocalServiceUtil.getUserDoyens(currentUser);

			} else if (roleId == ContactConstants.MY_TEACHER) {

				members = UserUtilsLocalServiceUtil.getUserTeachers(currentUser);

			} else if (roleId == RoleUtilsLocalServiceUtil.getMainTeacherRole().getRoleId()) {

				List<UserGroupRole> userGroupRoles = new ArrayList<>();
				// Main teachers of school
				if (OrgDetailsLocalServiceUtil.isSchool(orgId)) {
					List<Organization> schoolClasses = OrgUtilsLocalServiceUtil.getSchoolClasses(orgId, false);
					for (Organization schoolClass : schoolClasses) {
						userGroupRoles.addAll(UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(schoolClass.getGroupId(), roleId));
					}
					for (UserGroupRole userGroupRole : userGroupRoles) {
						User user = UserLocalServiceUtil.getUser(userGroupRole.getUserId());
						members.add(user);
					}
				}

			} else {
				// Classic search in school, org, cours or subject organizations
				List<Long> orgIds = new ArrayList<>();
				orgIds.add(orgId);
				List<Long> roleIds = new ArrayList<>();
				roleIds.add(roleId);

				members = UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			}

			for (User member : members) {
				if (!uniqueList.contains(member)) {
					uniqueList.add(member);
				}
			}

		} catch (Exception e) {
			logger.error("Error while retrieving the members of institutional org "+orgId+" having roleId "+roleId, e);
		}
		return uniqueList;
	}


	public List<User> directorySearch(User user, String query, List<Long> organizationIds, List<Long> schoolIds,
									  List<Long> roleIds, List<Long> subjectIds, int start, int limit, OrderByComparator obc) {

		List<Long> studentAndParentOrgIds = getStudentAndParentUsersOrgIds(schoolIds, organizationIds, user);
		List<Long> agentOrgIds = getAgentUsersOrgIds(schoolIds, organizationIds);
		List<Long> studentAndParentRoleIds = getStudentAndParentRoles(roleIds);
		List<Long> agentRoleIds = getAgentRoles(roleIds);

		List<User> studentAndParentContacts = new ArrayList<>();
		List<User> agentContacts = new ArrayList<>();
		try {
			if (!studentAndParentOrgIds.isEmpty() && !studentAndParentRoleIds.isEmpty()) {
				studentAndParentContacts = UserSearchLocalServiceUtil.searchUsers(query, studentAndParentOrgIds, null, studentAndParentRoleIds, null, start, limit, obc);
				studentAndParentContacts = userListFilter(studentAndParentContacts);
			}

			if (!agentRoleIds.isEmpty()) {
				agentContacts = UserSearchLocalServiceUtil.searchUsers(query, agentOrgIds, null, agentRoleIds, subjectIds, start, limit, obc);
				agentContacts = userListFilter(agentContacts);
			}
		} catch (Exception e) {
			logger.error("Error searching directory", e);
		}

		List<User> aggregatedList = new ArrayList<>();
		aggregatedList.addAll(studentAndParentContacts);
		aggregatedList.addAll(agentContacts);

		Collections.sort(aggregatedList, obc);
		if (start > -1 && limit > -1) {
			aggregatedList = aggregatedList.subList(start, Math.min(aggregatedList.size(), limit));
		}

		return aggregatedList;
	}

	public List<User> getMyStudents(User user) {
		List<User> students = new ArrayList<>();
		if (RoleUtilsLocalServiceUtil.isStudent(user)) {
			// Return all students of the student's classes
			List<Organization> studentClasses = UserOrgsLocalServiceUtil.getUserClasses(user, false);
			for (Organization studentClass : studentClasses) {
				try {
					List<Long> orgIds = new ArrayList<>();
					orgIds.add(studentClass.getOrganizationId());
					List<Long> roleIds = new ArrayList<>();
					roleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
					students.addAll(UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null));
				} catch (Exception e) {
					logger.error("Error fetching students for user " + user.getUserId(), e);
				}
			}
		} else if (RoleUtilsLocalServiceUtil.isParent(user)) {
			// Return all parent's children
			students = UserRelationshipLocalServiceUtil.getChildren(user.getUserId());
		}
		return students;
	}

	public List<User> getMyRelatives(User user) {
		List<User> relatives = new ArrayList<>();
		if (RoleUtilsLocalServiceUtil.isStudent(user)) {
			// Return all student's relatives
			relatives = UserRelationshipLocalServiceUtil.getParents(user.getUserId());
		} else if (RoleUtilsLocalServiceUtil.isParent(user)) {
			// Return all parent's children's relatives
			relatives.addAll(UserRelationshipLocalServiceUtil.getAllRelatives(user.getUserId()));
		}
		return relatives;
	}

	public JSONObject convertUserToJson (User user) {

		JSONObject jsonUser = new JSONObject();

		jsonUser.put(JSONConstants.USER_ID, user.getUserId());
		jsonUser.put(JSONConstants.FIRST_NAME, formatName(user.getFirstName()));
		jsonUser.put(JSONConstants.LAST_NAME, formatName(user.getLastName()));
		jsonUser.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_USER);
		List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserSchools(user);
		if (userSchools != null && userSchools.size() > 0) {
			jsonUser.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userSchools.get(0).getName(), true));
		}

		try {
			jsonUser.put(JSONConstants.ROLES, RoleUtilsLocalServiceUtil.displayUserRoles(user));

			if (RoleUtilsLocalServiceUtil.isStudent(user)) {
				jsonUser.put(JSONConstants.CLASSES, UserOrgsLocalServiceUtil.getStudentClassName(user));

			} else if (RoleUtilsLocalServiceUtil.isParent(user)) {
				StringBuilder parentClasses = new StringBuilder();
				List<User> children = UserRelationshipLocalServiceUtil.getChildren(user.getUserId());
				for (int i = 0 ; i < children.size() ; i++ ) {
					User child = children.get(i);
					parentClasses.append(UserOrgsLocalServiceUtil.getStudentClassName(child));
					if (i != children.size() - 1) {
						parentClasses.append(", ");
					}
				}
				jsonUser.put(JSONConstants.CLASSES, parentClasses.toString());

			} else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
				jsonUser.put(JSONConstants.SUBJECTS, TeacherSubjectLocalServiceUtil.getTeacherSubjectList(user));
			}

		} catch (Exception e) {
			logger.error("Error when getting informations on user "+user.getUserId(), e);
		}

		return jsonUser;
	}

	// Uppercase the first letter and lowercase the others, for each space-separated part of the given name
	private String formatName(String name) {
		StringBuilder formattedName = new StringBuilder();
		String[] nameTab = name.split(" ");
		for (int i = 0 ; i < nameTab.length ; i++) {
			String namePart = nameTab[i];
			// Skip double spaces
			if (namePart.equals("")) {
				continue;
			}
			formattedName.append(namePart.substring(0, 1).toUpperCase()).append(namePart.substring(1).toLowerCase());
			if (i != nameTab.length - 1) {
				formattedName.append(" ");
			}
		}
		return formattedName.toString();
	}

	private List<Long> getStudentAndParentRoles(List<Long> roleIds) {

		List<Long> longRoleIds = new ArrayList<>();

		try {
			Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
			Role parentRole = RoleUtilsLocalServiceUtil.getParentRole();

			for (Long roleId : roleIds) {
				if (roleId == studentRole.getRoleId() || roleId == parentRole.getRoleId()) {
					longRoleIds.add(roleId);
				}
			}

			// If roleId 0 is given, search on both students and parents
			if (roleIds.isEmpty()) {
				longRoleIds.add(studentRole.getRoleId());
				longRoleIds.add(parentRole.getRoleId());
			}

		} catch (Exception e) {
			logger.error("Error while getting the list of students and parents roles for directory search", e);
		}

		return longRoleIds;
	}

	private List<Long> getStudentAndParentUsersOrgIds(List<Long> schoolIds, List<Long> organizationIds, User user) {

		// If classes or groups are given, return them
		List<Long> longOrgIds = new ArrayList<>();
		if (organizationIds != null) {
			for (Long organizationId : organizationIds) {
				if (organizationId != 0) {
					longOrgIds.add(organizationId);
				}
			}
		}
		if (!longOrgIds.isEmpty())  {
			return longOrgIds;
		}

		// Else, process schools

		// Build the authorized school list
		List<Long> authorizedSchoolIds = new ArrayList<>();
		try {
			List<Organization> userSchools;
			if (RoleUtilsLocalServiceUtil.isAdministrator(user)) {
				userSchools = OrgUtilsLocalServiceUtil.getAllSchools();
			} else {
				userSchools = UserOrgsLocalServiceUtil.getAllUserSchoolsIncludingSchoolComplex(user);
			}
			for (Organization userSchool : userSchools) {
				authorizedSchoolIds.add(userSchool.getOrganizationId());
			}

		} catch (Exception e) {
			logger.error("Error while building the authorized school list for students and parents directory search", e);
		}

		// Filter schools with authorized ones
		List<Long> longSchoolIds = new ArrayList<>();
		for (Long schoolId : schoolIds) {
			if (authorizedSchoolIds.contains(schoolId)) {
				longSchoolIds.add(schoolId);
			}
		}

		// Check organizations : We search in the one selected, or in the restricted one,
		// or in all schools of the list (after removing first empty value)
		if (longSchoolIds.isEmpty() && !schoolIds.isEmpty()) {
			// We have selected one or more school filters but none is authorized
			// for student and parent : return null to cancel search on this profiles
			return Collections.emptyList();
		} else if (!longSchoolIds.isEmpty()) {
			return longSchoolIds;
		} else {
			return authorizedSchoolIds;
		}
	}

	public List<Long> getRecipients(JSONArray recipients, User user) throws SystemException {

		logger.info("User "+user.getFullName()+" targets the following recipients: "+recipients.toString());

		List<Long> recipientUserIds = new ArrayList<>();

		for (int i=0; i < recipients.length(); i++) {

			JSONObject recipient = recipients.getJSONObject(i);
			int recipientType = recipient.getInt(JSONConstants.TYPE);

			// 1. Recipient is a user
			if (recipientType == ContactConstants.RECIPIENT_TYPE_USER) {
				recipientUserIds.add(recipient.getLong(JSONConstants.USER_ID));
			}
			// 2. Recipients are members of an organization
			else if (recipientType == ContactConstants.RECIPIENT_TYPE_ORG) {

				long orgId = JSONConstants.getLongValue(recipient, JSONConstants.ORG_ID, 0);
				long roleId = JSONConstants.getLongValue(recipient, JSONConstants.ROLE_ID, 0);
				List<User> recipientUsers = getListMembers(user, roleId, orgId);
				for (User recipientUser : recipientUsers) {
					recipientUserIds.add(recipientUser.getUserId());
				}

			}
			// 3. Recipients are group members
			else if (recipientType == ContactConstants.RECIPIENT_TYPE_GROUP) {

				long groupId = recipient.getLong(JSONConstants.GROUP_ID);
				List<User> groupMembers = UserLocalServiceUtil.getGroupUsers(groupId);

				for (User groupMember : groupMembers) {
					recipientUserIds.add(groupMember.getUserId());
				}
			}
		}

		// Sort unique
		List<Long> singleUserIds = new ArrayList<>();
		for (Long recipientUserId : recipientUserIds) {
			if (!singleUserIds.contains(recipientUserId)) {
				singleUserIds.add(recipientUserId);
			}
		}

		logger.info("Total of "+singleUserIds.size()+" users targeted");

		return singleUserIds;
	}

	private List<Long> getAgentUsersOrgIds(List<Long> schoolIds, List<Long> organizationIds) {

		// If classes or groups are given, return them
		List<Long> longOrgIds = new ArrayList<>();
		if (organizationIds != null) {
			longOrgIds.addAll(organizationIds);
		}
		if (!longOrgIds.isEmpty())  {
			return longOrgIds;
		}

		// Else, process schools

		// The authorized schoolId list is all schools because only agents can come here
		List<Long> authorizedSchoolIds = new ArrayList<>();
		try {
			List<Organization> userSchools = OrgUtilsLocalServiceUtil.getAllSchools();
			for (Organization userSchool : userSchools) {
				authorizedSchoolIds.add(userSchool.getOrganizationId());
			}

		} catch (Exception e) {
			logger.error("Error while building the authorized school list for agents directory search", e);
		}

		// Filter schools with authorized ones
		List<Long> longSchoolIds = new ArrayList<>();
		if (schoolIds != null) {
			for (Long schoolId : schoolIds) {
				if (authorizedSchoolIds.contains(schoolId)) {
					longSchoolIds.add(schoolId);
				}
			}
		}

		// Check organizations : We search in the one selected, or in the restricted one,
		// or in all schools of the list (after removing first empty value)
		if (!longSchoolIds.isEmpty()) {
			return longSchoolIds;
		} else {
			return authorizedSchoolIds;
		}
	}

	/**
	 * Remove double from a users list and return the filtered list
	 * @param usersList The users list which possibly contains double
	 * @return a User's List without double left
	 */
	private List<User> userListFilter(List<User> usersList) {
		// Avoid duplicate user
		List<Long> listExistingUserId = new ArrayList<>();
		List<User> filteredUsers = new ArrayList<>();
		for(User u : usersList){
			if(!listExistingUserId.contains(u.getUserId())){
				filteredUsers.add(u);
				listExistingUserId.add(u.getUserId());
			}
		}

		return filteredUsers;
	}

	private List<Long> getAgentRoles(List<Long> roleIds) {

		List<Long> longRoleIds = new ArrayList<>();

		try {
			Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
			Role parentRole = RoleUtilsLocalServiceUtil.getParentRole();

			for (Long roleId : roleIds) {
				if (roleId != 0 && roleId != studentRole.getRoleId() && roleId != parentRole.getRoleId()) {
					longRoleIds.add(roleId);
				}
			}

			// If roleId 0 is given, search on all agents
			if (roleIds.isEmpty()) {
				longRoleIds.addAll(RoleUtilsLocalServiceUtil.getAgentsRoleIds());
			}

		} catch (Exception e) {
			logger.error("Error while getting the list of agents roles for directory search", e);
		}

		return longRoleIds;
	}

	public List<User> getAllGroupsContacts(User user, String search, int start, int limit, OrderByComparator comparator) {

		try {
			List<Long> groupIds = CommunityInfosLocalServiceUtil.getUserCommunitiesIds(user.getUserId(), false, true);
			List<Long> roleIds = new ArrayList<Long>();
			if (!groupIds.isEmpty()) {
				return UserSearchLocalServiceUtil.searchUsers(search, null, groupIds, roleIds, null, start, limit, comparator);
			}

		} catch (Exception e) {
			logger.error("Error when fetching contacts for all user's groups", e);
		}
		return new ArrayList<>();
	}


	// Not used yet but kept for future contact panel
	public JSONObject getContactDetails(User currentUser, long contactUserId) {

		// The contact details json structure
		JSONObject jsonContactDetails = new JSONObject();

		try {
			User contactUser = UserLocalServiceUtil.getUser(contactUserId);

			// Get roles for current user
			boolean isCurrentUserStudent = false;
			boolean isCurrentUserTeacher = false;
			boolean isCurrentUserPersonal = false;

			if (RoleUtilsLocalServiceUtil.isStudent(currentUser)) {
				isCurrentUserStudent = true;
			} else if (RoleUtilsLocalServiceUtil.isTeacher(currentUser)) {
				isCurrentUserTeacher = true;
			} else {
				isCurrentUserPersonal = true;
			}

			// Get roles for watched contact
			boolean isContactUserStudent = false;
			boolean isContactUserParent = false;
			boolean isContactUserTeacher = false;
			boolean isContactUserPersonal = false;
			boolean isContactUserSchoolAdmin = RoleUtilsLocalServiceUtil.isSchoolAdmin(contactUser);;

			if (RoleUtilsLocalServiceUtil.isStudent(contactUser)) {
				isContactUserStudent = true;
			} else if (RoleUtilsLocalServiceUtil.isParent(contactUser)) {
				isContactUserParent = true;
			} else if (RoleUtilsLocalServiceUtil.isTeacher(contactUser)) {
				isContactUserTeacher = true;
			} else {
				isContactUserPersonal = true;
			}
			String roles = RoleUtilsLocalServiceUtil.displayUserRoles(contactUser);

			jsonContactDetails.put(JSONConstants.LAST_NAME, contactUser.getLastName());
			jsonContactDetails.put(JSONConstants.FIRST_NAME, contactUser.getFirstName());

			// Role
			jsonContactDetails.put(JSONConstants.ROLES, roles);
			jsonContactDetails.put(JSONConstants.IS_STUDENT, isContactUserStudent);
			jsonContactDetails.put(JSONConstants.IS_PARENT, isContactUserParent);
			jsonContactDetails.put(JSONConstants.IS_TEACHER, isContactUserTeacher);
			jsonContactDetails.put(JSONConstants.IS_PERSONAL, isContactUserPersonal);
			jsonContactDetails.put(JSONConstants.IS_SCHOOL_ADMIN, isContactUserSchoolAdmin);

			// Subjects for teachers
			if (isContactUserTeacher) {
				jsonContactDetails.put(JSONConstants.SUBJECTS, TeacherSubjectLocalServiceUtil.getTeacherSubjectList(contactUser));
			}

			// User classes and cours
			JSONArray jsonUserSchools = new JSONArray();
			List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserSchools(contactUser);
			List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserClasses(contactUser, false);
			List<Organization> userCours  = UserOrgsLocalServiceUtil.getUserCours(contactUser, false, OrgConstants.ALL_SCHOOLS_ID);

			// Set main class for student only
			if (isContactUserStudent) {
				if (userClasses != null && userClasses.size() == 1) {
					JSONObject jsonUserClass = new JSONObject();
					jsonUserClass.put(JSONConstants.ORG_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userClasses.get(0).getName(), false));
					jsonUserClass.put(JSONConstants.ORG_ID, userClasses.get(0).getOrganizationId());
					jsonContactDetails.put(JSONConstants.CLASSES, jsonUserClass);
				}
			}

			for (Organization school : userSchools) {
				JSONObject jsonUserSchool = new JSONObject();
				jsonUserSchool.put(JSONConstants.SCHOOL_ID, school.getOrganizationId());
				jsonUserSchool.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(school.getName(), false));

				// Loop over classes
				JSONArray jsonUserClasses = new JSONArray();
				for (Organization userClass : userClasses) {
					if (userClass.getParentOrganizationId() == school.getOrganizationId()) {
						JSONObject jsonUserClass = new JSONObject();
						jsonUserClass.put(JSONConstants.ORG_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userClass.getName(), false));
						jsonUserClass.put(JSONConstants.ORG_ID, userClass.getOrganizationId());
						jsonUserClasses.put(jsonUserClass);
					}
				}
				jsonUserSchool.put(JSONConstants.CLASSES, jsonUserClasses);

				// Loop over groups
				JSONArray jsonUserCours = new JSONArray();
				for (Organization _userCours : userCours) {
					if (_userCours.getParentOrganizationId() == school.getOrganizationId()) {
						JSONObject jsonUserGroup = new JSONObject();
						jsonUserGroup.put(JSONConstants.ORG_NAME, OrgUtilsLocalServiceUtil.formatOrgName(_userCours.getName(), false));
						jsonUserGroup.put(JSONConstants.ORG_ID, _userCours.getOrganizationId());
						jsonUserCours.put(jsonUserGroup);
					}
				}
				jsonUserSchool.put(JSONConstants.COURS, jsonUserCours);

				jsonUserSchools.put(jsonUserSchool);
			}
			// Groups for personal people are not seen by others than personal people
			if (!isContactUserPersonal || (!isCurrentUserStudent && !isContactUserParent && !isCurrentUserTeacher) ) {
				jsonContactDetails.put(JSONConstants.SCHOOLS, jsonUserSchools);
			}

			// Are there classes or groups in common between current user and contact user ?
			boolean areGroupsInCommon = false;
			if (isCurrentUserTeacher && (isContactUserStudent || isContactUserParent)) {
				List<Organization> currentUserClassesAndCours = UserOrgsLocalServiceUtil.getUserClassesAndCours(currentUser, false);

				List<Organization> contactClassesAndCours = new ArrayList<Organization>();
				if (isContactUserStudent) {
					contactClassesAndCours.addAll(userClasses);
					contactClassesAndCours.addAll(userCours);
				}
				for (Organization currentUserClass : currentUserClassesAndCours) {
					for (Organization contactClassOrCours : contactClassesAndCours) {
						if (currentUserClass.getOrganizationId() == contactClassOrCours.getOrganizationId()) {
							areGroupsInCommon = true;
						}
					}
				}
			}

			// Coordinates may be seen by
			//  - all personals
			//  - teachers for students and parents only (of their classes)
			UserContact userContact = UserContactLocalServiceUtil.getUserContactByUserId(contactUserId);
			if (isCurrentUserPersonal || (isCurrentUserTeacher && (isContactUserStudent || isContactUserParent) && areGroupsInCommon)) {

				jsonContactDetails.put(JSONConstants.HOME_PHONE, userContact.getHomePhone());
				jsonContactDetails.put(JSONConstants.MOBILE_PHONE, userContact.getMobilePhone());
				jsonContactDetails.put(JSONConstants.PRO_PHONE, userContact.getProPhone());
				if (userContact.isIsAddressAuthorized()) {
					jsonContactDetails.put(JSONConstants.ADDRESS, userContact.getAddress());
				}
			}

			// Show email if it is not an internal one, to agents only
			if (isCurrentUserTeacher || isCurrentUserPersonal) {
				if (isContactUserTeacher || isContactUserPersonal) {
					jsonContactDetails.put(JSONConstants.EMAIL, contactUser.getEmailAddress());
				} else {
					if (!contactUser.getEmailAddress().startsWith(contactUser.getScreenName()+"@")) {
						jsonContactDetails.put(JSONConstants.EMAIL, contactUser.getEmailAddress());
					} else if (userContact.isIsMailAuthorized()) {
						jsonContactDetails.put(JSONConstants.EMAIL, userContact.getMail());
					}
				}
			}

			// Parents or children
			if (isContactUserStudent) {
				try {
					List<User> parents = UserRelationshipLocalServiceUtil.getParents(contactUserId);
					if (parents != null && !parents.isEmpty()) {
						JSONArray jsonParents = new JSONArray();
						for (User parent : parents) {
							JSONObject jsonParent = new JSONObject();
							jsonParent.put(JSONConstants.FIRST_NAME, parent.getFirstName());
							jsonParent.put(JSONConstants.LAST_NAME, parent.getLastName());
							jsonParent.put(JSONConstants.USER_ID, parent.getUserId());
							jsonParents.put(jsonParent);
						}
						jsonContactDetails.put(JSONConstants.PARENTS, jsonParents);
					}

				} catch (Exception e) {
					logger.error("Error while processing student's parents", e);
				}
			}
			if (isContactUserParent) {
				try {
					List<User> children = UserRelationshipLocalServiceUtil.getChildren(contactUserId);
					if (!children.isEmpty()) {
						JSONArray jsonChildren = new JSONArray();
						for (User child : children) {
							JSONObject jsonChild = new JSONObject();
							jsonChild.put(JSONConstants.FIRST_NAME, child.getFirstName());
							jsonChild.put(JSONConstants.LAST_NAME, child.getLastName());
							jsonChild.put(JSONConstants.USER_ID, child.getUserId());
							jsonChildren.put(jsonChild);
						}
						jsonContactDetails.put(JSONConstants.CHILDREN, jsonChildren);
					}
				} catch (Exception e) {
					logger.error("Error while processing parent's children", e);
				}
			}

		} catch (Exception e) {
			logger.error("Error when fetching contact details for user", e);
		}

		return jsonContactDetails;
	}


}