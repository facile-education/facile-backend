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

package com.weprode.facile.role.service.impl;

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
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.constants.NeroRoleConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.role.service.base.RoleUtilsLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marc Salvat
 */
@Component(
	property = "model.class.name=com.weprode.facile.role.model.RoleUtils",
	service = AopService.class
)
public class RoleUtilsLocalServiceImpl extends RoleUtilsLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(RoleUtilsLocalServiceImpl.class);

	public Role getStudentRole() {
		return getRole(NeroRoleConstants.STUDENT);
	}
	
	public Role getParentRole() {
		return getRole(NeroRoleConstants.RELATIVE);
	}

	public Role getTeacherRole() {
		return getRole(NeroRoleConstants.TEACHER);
	}

	public Role getPersonalRole() {
		return getRole(NeroRoleConstants.PERSONAL);
	}

	public Role getDirectionRole() {
		return getRole(NeroRoleConstants.DIRECTION);
	}

	public Role getSchoolAdminRole() {
		return getRole(NeroRoleConstants.SCHOOL_ADMIN);
	}

	public Role getCollectivityAdminRole() {
		return getRole(NeroRoleConstants.COLLECTIVITY_ADMIN);
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
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getTeacherRole().getRoleId());
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
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getDirectionRole().getRoleId());
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

	public boolean isCollectivityAdmin(User user) {
		try {
			return RoleLocalServiceUtil.hasUserRole(user.getUserId(), getCollectivityAdminRole().getRoleId());
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

	public boolean isConseillerSocial(User user, long orgId) {
		try {
			Organization org = OrganizationLocalServiceUtil.getOrganization(orgId);
			return UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), org.getGroupId(), getConseillerSocialRole().getRoleId());
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

	public boolean isPsychologue(User user, long orgId) {
		try {
			Organization org = OrganizationLocalServiceUtil.getOrganization(orgId);
			return UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), org.getGroupId(), getPsychologueRole().getRoleId());
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
				if (!isCollectivityAdmin(user)) {
					visibleRoles.add(getStudentRole());
					visibleRoles.add(getParentRole());
				}
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

	public boolean isForClass(Role role) {
		return role.getRoleId() == RoleUtilsLocalServiceUtil.getStudentRole().getRoleId() ||
				role.getRoleId() == RoleUtilsLocalServiceUtil.getParentRole().getRoleId() ||
				role.getRoleId() == RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId() ||
				role.getRoleId() == RoleUtilsLocalServiceUtil.getMainTeacherRole().getRoleId();
	}


	 public List<Long> getAgentsRoleIds() {
	 	List<Long> agentsRoleIds = new ArrayList<>();
	 	try {
	 		for (String personalRole : NeroRoleConstants.PERSONAL_ROLES) {
	 			agentsRoleIds.add(getRole(personalRole).getRoleId());
	 		}
	 		agentsRoleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());
	 	} catch (Exception e) {
			 logger.info("Error fetching agent roleIds", e);
	 	}
	 	return agentsRoleIds;
	 }

	 public String displayUserRoles(User user) {
		
	 	String roleStr;
		
	 	if (isStudent(user)) {
	 		roleStr = getStudentRole().getTitle(user.getLocale());
	 	} else if (isParent(user)) {
	 		roleStr = getParentRole().getTitle(user.getLocale());
	 	} else if (isTeacher(user)) {
	 		roleStr = getTeacherRole().getTitle(user.getLocale());
	 	} else if (isInfirmiere(user)) {
	 		roleStr = getInfirmiereRole().getTitle(user.getLocale());
	 	} else if (isAssistantTechnique(user)) {
	 		roleStr = getAssistantTechniqueRole().getTitle(user.getLocale());
	 	} else if (isCaissierComptable(user)) {
	 		roleStr = getCaissierComptableRole().getTitle(user.getLocale());
	 	} else if (isConseillerOrientation(user)) {
	 		roleStr = getConseillerOrientationRole().getTitle(user.getLocale());
	 	} else if (isConseillerSocial(user)) {
	 		roleStr = getConseillerSocialRole().getTitle(user.getLocale());
	 	} else if (isBibliothecaire(user)) {
	 		roleStr = getBibliothecaireRole().getTitle(user.getLocale());
	 	} else if (isPsychologue(user)) {
	 		roleStr = getPsychologueRole().getTitle(user.getLocale());
	 	} else if (isSecretariat(user)) {
	 		roleStr = getSecretariatRole().getTitle(user.getLocale());
	 	} else {
	 		roleStr = "Personnel";
	 	}

	 	if (isDoyen(user)) {
	 		roleStr += ", " + getDoyenRole().getTitle(user.getLocale());
	 	}
	 	if (isSchoolAdmin(user)) {
	 		roleStr += ", " + getSchoolAdminRole().getTitle(user.getLocale());
	 	}
	 	return roleStr;
	 }

	 public List<Role> getUserEntRoles(User user) {
	 	List<Role> userEntRoles = new ArrayList<>();

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
	 }

}