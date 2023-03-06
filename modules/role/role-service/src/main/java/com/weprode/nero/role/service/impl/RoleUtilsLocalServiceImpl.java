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

package com.weprode.nero.role.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.constants.NeroRoleConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.role.service.base.RoleUtilsLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marc Salvat
 */
@Component(
	property = "model.class.name=com.weprode.nero.role.model.RoleUtils",
	service = AopService.class
)
public class RoleUtilsLocalServiceImpl extends RoleUtilsLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(RoleUtilsLocalServiceImpl.class);

	public Role getStudentRole() {
		return getRole(NeroRoleConstants.NATIONAL_1);
	}
	
	public Role getParentRole() {
		return getRole(NeroRoleConstants.NATIONAL_2);
	}

	public Role getTeacherRole() {
		return getRole(NeroRoleConstants.NATIONAL_3);
	}

	public Role getSchoolLifeRole() {
		return getRole(NeroRoleConstants.NATIONAL_5);
	}

	public Role getAdministrativeRole() {
		return getRole(NeroRoleConstants.NATIONAL_6);
	}

	public Role getInspectorRole() {
		return getRole(NeroRoleConstants.INSPECTOR);
	}

	public Role getDocumentalistRole() {
		return getRole(NeroRoleConstants.NATIONAL_24);
	}

	public Role getMedicalRole() {
		return getRole(NeroRoleConstants.NATIONAL_28);
	}

	public Role getOrientationRole() {
		return getRole(NeroRoleConstants.NATIONAL_29);
	}

	public Role getAcademicRole() {
		return getRole(NeroRoleConstants.NATIONAL_7);
	}

	public Role getDirectionRole() {
		return getRole(NeroRoleConstants.NATIONAL_4);
	}

	public Role getSchoolAdminRole() {
		return getRole(NeroRoleConstants.GROUP_ADMIN);
	}

	public Role getEntAdminRole() {
		return getRole(NeroRoleConstants.ENT_ADMIN);
	}

	public Role getGarAdminRole() {
		return getRole(NeroRoleConstants.GAR_ADMIN);
	}

	public Role getAdministratorRole() {
		return getRole(RoleConstants.ADMINISTRATOR);
	}

	public Role getMainTeacherRole() {
		return getRole(NeroRoleConstants.MAIN_TEACHER);
	}

	public Role getCommunityAdministratorRole() {
		return getRole(RoleConstants.SITE_ADMINISTRATOR);
	}

	public Role getCommunityOwnerRole() {
		return getRole(RoleConstants.SITE_OWNER);
	}

	public Role getDoyenRole() {
		return getRole(NeroRoleConstants.DOYEN);
	}

	public Role getConseillerOrientationRole() {
		return getRole(NeroRoleConstants.CONSEILLER_ORIENTATION);
	}

	public Role getConseillerSocialRole() {
		return getRole(NeroRoleConstants.CONSEILLER_SOCIAL);
	}

	public Role getInfirmiereRole() {
		return getRole(NeroRoleConstants.INFIRMIERE);
	}

	public Role getPsychologueRole() {
		return getRole(NeroRoleConstants.PSYCHOLOGUE);
	}

	public Role getAssistantTechniqueRole() {
		return getRole(NeroRoleConstants.ASSISTANT_TECHNIQUE);
	}

	public Role getCaissierComptableRole() {
		return getRole(NeroRoleConstants.CAISSIER_COMPTABLE);
	}

	public Role getBibliothecaireRole() {
		return getRole(NeroRoleConstants.BIBLIOTHECAIRE);
	}

	public Role getSecretariatRole() {
		return getRole(NeroRoleConstants.SECRETAIRE);
	}

	public Role getOrganizationUserRole() {
		return getRole(NeroRoleConstants.ORGANIZATION_USER);
	}

	public Role getBetaTesterRole() {
		return getRole(NeroRoleConstants.BETA_TESTER);
	}

	public Role getRole(String roleName) {
		Role desiredRole = null;
		try {
			desiredRole = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), roleName);
		} catch (Exception e) {
			logger.debug("Could not find role name = " + roleName, e);
		}
		return desiredRole;
	}

	public boolean isStudent(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getStudentRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}
	
	public boolean isParent(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getParentRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isStudentOrParent(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getStudentRole().getRoleId()) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), getParentRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isTeacher(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getTeacherRole().getRoleId()) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), getDocumentalistRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isMainTeacher(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getMainTeacherRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isMainTeacher(User user, long orgId) {
		try {
			Organization org = OrganizationLocalServiceUtil.getOrganization(orgId);
			return UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), org.getGroupId(), getMainTeacherRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isDirectionMember(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getDirectionRole().getRoleId()) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), getRole(NeroRoleConstants.NATIONAL_23).getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isSchoolLife(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getSchoolLifeRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isAdministrative(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getAdministrativeRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isInspector(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getInspectorRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isAcademic(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getAcademicRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isDocumentaliste(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getDocumentalistRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isMedical(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getMedicalRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isOrientation(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getOrientationRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isCommunityAdministrator(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getCommunityAdministratorRole().getRoleId()) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), getCommunityOwnerRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isPersonal(User user) {
		for (String roleName : NeroRoleConstants.PERSONAL_ROLES) {
			if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), getRole(roleName).getRoleId())) {
				return true;
			}
		}

		return false;
	}

	public boolean isPat(User user) {
		for (String roleName : NeroRoleConstants.PAT_ROLES) {
			if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), getRole(roleName).getRoleId())) {
				return true;
			}
		}

		return false;
	}

	public boolean isEmps(User user) {
		for (String roleName : NeroRoleConstants.EMPS_ROLES) {
			if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), getRole(roleName).getRoleId())) {
				return true;
			}
		}

		return false;
	}

	public boolean isSchoolAdmin(User user) {
		try {
			 for (Organization school : UserOrgsLocalServiceUtil.getUserSchools(user)) {
			 	if (isSchoolAdmin(user, school.getOrganizationId())) {
			 		return true;
			 	}
			 }
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public boolean isSchoolAdmin(User user, long schoolId) {
		try {
			 Organization school = OrganizationLocalServiceUtil.getOrganization(schoolId);
			 return UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), school.getGroupId(), getSchoolAdminRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	public boolean isENTAdmin(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getEntAdminRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}
	
	public boolean isGARAdmin(User user) {
		try {
			 for (Organization school : UserOrgsLocalServiceUtil.getUserSchools(user)) {
			 	if(UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), school.getGroupId(), getGarAdminRole().getRoleId())) {
			 		return true;
			 	}
			 }
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	public boolean isGARAdmin(User user, long orgId) {
		try {
			 Organization org = OrganizationLocalServiceUtil.getOrganization(orgId);
			 return UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), org.getGroupId(), getGarAdminRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	public boolean isAdministrator(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getAdministratorRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	// Swiss codes
	public boolean isDoyen(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getDoyenRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}
	
	public boolean isDoyen(User user, long orgId) {
		try {
			 Organization org = OrganizationLocalServiceUtil.getOrganization(orgId);
			 return UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), org.getGroupId(), getDoyenRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	public boolean isConseillerOrientation(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getConseillerOrientationRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}
	
	public boolean isConseillerSocial(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getConseillerSocialRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}
	
	public boolean isInfirmiere(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getInfirmiereRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}
	
	public boolean isPsychologue(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getPsychologueRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}
	
	public boolean isAssistantTechnique(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getAssistantTechniqueRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

		
	public boolean isCaissierComptable(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getCaissierComptableRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}
	
	public boolean isBibliothecaire(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getBibliothecaireRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}
	
	public boolean isSecretariat(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getSecretariatRole().getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	public boolean isCommunityAdmin(long userId, long groupId) {
		try {
			Role managerRole = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), RoleConstants.SITE_ADMINISTRATOR);
		 	return UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, groupId, managerRole.getRoleId());
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	public boolean isUserGroupAdmin(User user, long groupId) {
		try {
			Role communityAdminRole = RoleUtilsLocalServiceUtil.getCommunityAdministratorRole();
			Role communityOwnerRole = RoleUtilsLocalServiceUtil.getCommunityOwnerRole();

			return UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), groupId, communityAdminRole.getRoleId()) ||
					UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), groupId, communityOwnerRole.getRoleId());
		} catch (Exception e) {
			logger.error("Error determining if user " + user.getFullName() + " is admin for group " + groupId, e);
		}

		return false;
	}

	public List<Role> getUserSearchableRoles(User user) {

		List<Role> visibleRoles = new ArrayList<>();

		// Student can only search for other students, if it's an other user all roles are allowed in search
		try {
			if (isStudent(user)) {
				visibleRoles.add(getStudentRole());
			} else if (isParent(user)) {
				visibleRoles.add(getParentRole());
			} else {
				visibleRoles.add(getStudentRole());
				visibleRoles.add(getParentRole());
				visibleRoles.add(getTeacherRole());
				visibleRoles.add(getDirectionRole());
				visibleRoles.add(getDoyenRole());
				visibleRoles.add(getConseillerOrientationRole());
				visibleRoles.add(getConseillerSocialRole());
				visibleRoles.add(getInfirmiereRole());
				visibleRoles.add(getPsychologueRole());
				visibleRoles.add(getAssistantTechniqueRole());
				visibleRoles.add(getCaissierComptableRole());
				visibleRoles.add(getBibliothecaireRole());
				visibleRoles.add(getSecretariatRole());
			}
		} catch (Exception e) {
			logger.error("Error getting all visible roles for user "+user.getFullName(), e);
		}

		return visibleRoles;
	}

	public List<Role> getAvailableRolesForLocalUser() {
		List<Role> roles = new ArrayList<>();

		roles.add(getStudentRole());
		roles.add(getTeacherRole());
		roles.add(getDirectionRole());
		roles.add(getDoyenRole());
		roles.add(getConseillerOrientationRole());
		roles.add(getConseillerSocialRole());
		roles.add(getInfirmiereRole());
		roles.add(getPsychologueRole());
		roles.add(getAssistantTechniqueRole());
		roles.add(getCaissierComptableRole());
		roles.add(getBibliothecaireRole());
		roles.add(getSecretariatRole());

		return roles;
	}

	/**
	 * Get the User Profile 
	 */
/*	 public int getUserProfile(User user) throws SystemException,NoSuchRoleException {
		
	 	int userProfile = 0;

	 	if (isStudent(user)) {
	 		userProfile = UserProfile.STUDENT.getId();

	 	} else if (isParent(user)) {
	 		userProfile = UserProfile.PARENT.getId();

	 	} else if (isTeacher(user)) {
	 		userProfile = UserProfile.TEACHER.getId();
			
	 	} else if (isInspector(user)) {
	 		userProfile = UserProfile.INSPECTOR.getId();

	 	} else if (isDirectionMember(user)) {
	 		userProfile = UserProfile.DIRECTOR.getId();

	 	} else if (isPersonal(user)) {
	 		userProfile = UserProfile.PERSONNAL.getId();

	 	} else {
	 		userProfile = UserProfile.OTHER.getId();
	 	}
		
	 	if (userProfile == 0) {
	 		throw new NoSuchRoleException("User "+user.getFullName()+" with id "+user.getUserId()+" doesn't have a valid role");
	 	} else {
	 		return userProfile;
	 	}
	 }*/

/*	 public List<Long> getAgentsRoleIds() {
	 	List<Long> agentsRoleIds = new ArrayList<Long>();
	 	try {
	 		for (String personalRole : NeroRoleConstants.PERSONAL_ROLES_LIST) {
	 			agentsRoleIds.add(getRole(personalRole).getRoleId());
	 		}
	 		agentsRoleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
	 	} catch (Exception e) {
	 	}
	 	return agentsRoleIds;
	 }*/

/*	 public String displayUserRoles(User user) {
		
	 	String roleStr = "";
		
	 	if (isStudent(user)) {
	 		roleStr = "El\u00E8ve";
	 	} else if (isParent(user)) {
	 		roleStr = "Responsable l\u00E9gal";
	 	} else if (isTeacher(user)) {
	 		roleStr = "Enseignant\u00B7te";
	 	} else if (isInfirmiere(user)) {
	 		roleStr = "Infirmier\u00B7\u00E8re";
	 	} else if (isAssistantTechnique(user)) {
	 		roleStr = "Assistant\u00B7te technique";
	 	} else if (isCaissierComptable(user)) {
	 		roleStr = "Caissier\u00B7\u00E8re comptable ";
	 	} else if (isConseillerOrientation(user)) {
	 		roleStr = "Conseiller\u00B7\u00E8re d\u0027orientation";
	 	} else if (isConseillerSocial(user)) {
	 		roleStr = "Conseiller\u00B7\u00E8re social\u00B7e";
	 	} else if (isBibliothecaire(user)) {
	 		roleStr = "Biblioth\u00E9caire";
	 	} else if (isPsychologue(user)) {
	 		roleStr = "Psychologue";
	 	} else if (isSecretariat(user)) {
	 		roleStr = "Secr\u00E9taire";
	 	} else {
	 		roleStr = "Personnel";
	 	}
	 	if (isDoyen(user)) {
	 		roleStr += ", Doyen\u00B7enne";
	 	}
	 	if (isSchoolAdmin(user)) {
	 		roleStr += ", Admin. ENTA";
	 	}
	 	return roleStr;
	 }*/

/*	 public List<Role> getUserEntRoles (User user) {

	 	List<Role> userEntRoles = new ArrayList<Role>();

	 	if (isStudent(user)) {
	 		userEntRoles.add(getStudentRole());
	 	}
	 	if (isParent(user)) {
	 		userEntRoles.add(getParentRole());
	 	}
	 	if (isTeacher(user)) {
	 		userEntRoles.add(getTeacherRole());
	 	}
	 	if (isDirectionMember(user)) {
	 		userEntRoles.add(getDirectionRole());
	 	}
	 	if (isDoyen(user)) {
	 		userEntRoles.add(getDoyenRole());
	 	}
	 	if (isConseillerOrientation(user)) {
	 		userEntRoles.add(getConseillerOrientationRole());
	 	}
	 	if (isConseillerSocial(user)) {
	 		userEntRoles.add(getConseillerSocialRole());
	 	}
	 	if (isInfirmiere(user)) {
	 		userEntRoles.add(getInfirmiereRole());
	 	}
	 	if (isPsychologue(user)) {
	 		userEntRoles.add(getPsychologueRole());
	 	}
	 	if (isAssistantTechnique(user)) {
	 		userEntRoles.add(getAssistantTechniqueRole());
	 	}
	 	if (isCaissierComptable(user)) {
	 		userEntRoles.add(getCaissierComptableRole());
	 	}
	 	if (isBibliothecaire(user)) {
	 		userEntRoles.add(getBibliothecaireRole());
	 	}
	 	if (isSecretariat(user)) {
	 		userEntRoles.add(getSecretariatRole());
	 	}

	 	return userEntRoles;
	 }*/

/*	public JSONObject getAllEntRolesAsJson(User user, boolean addEmptyRole) {

		JSONObject ret = JSONFactoryUtil.createJSONObject();
		JSONArray roles = JSONFactoryUtil.createJSONArray();
		
		// Empty role to search them all (only for agents)
		if (addEmptyRole && !RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
			JSONObject emptyRole = JSONFactoryUtil.createJSONObject();
			emptyRole.put("roleId", "0");
			emptyRole.put("roleCode", "0");
			emptyRole.put("label", "Tous");
			roles.put(emptyRole);
		}
		
		List<Role> entRoles = getUserSearchableRoles(user);
		
		// Create JSON object for visible roles
		for (Role role : entRoles) {
			JSONObject currRole = JSONFactoryUtil.createJSONObject();
			currRole.put("roleId", role.getRoleId());
			currRole.put("roleCode", role.getName());
			currRole.put("label", role.getTitle(user.getLocale()));
			roles.put(currRole);
		}
		
		ret.put("roles", roles);
		ret.put("success", true);
		
		return ret;
	}*/
}