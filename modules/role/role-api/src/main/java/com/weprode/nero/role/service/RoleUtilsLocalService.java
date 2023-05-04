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

package com.weprode.nero.role.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for RoleUtils. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see RoleUtilsLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface RoleUtilsLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.role.service.impl.RoleUtilsLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the role utils local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link RoleUtilsLocalServiceUtil} if injection and service tracking are not available.
	 */
	public String displayUserRoles(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getAcademicRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getAdministrativeRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getAdministratorRole();

	/**
	 * Get the User Profile
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getAgentsRoleIds();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getAssistantTechniqueRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getAvailableRolesForLocalUser();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getBetaTesterRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getBibliothecaireRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getCaissierComptableRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getCommunityAdministratorRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getCommunityOwnerRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getConseillerOrientationRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getConseillerSocialRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getDirectionRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getDocumentalistRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getDoyenRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getEntAdminRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getGarAdminRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getInfirmiereRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getInspectorRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getMainTeacherRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getMedicalRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getOrganizationUserRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getOrientationRole();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getParentRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getPsychologueRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getRole(String roleName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getSchoolAdminRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getSchoolLifeRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getSecretariatRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getStudentRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getTeacherRole();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getUserEntRoles(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getUserSearchableRoles(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isAcademic(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isAdministrative(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isAdministrator(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isAssistantTechnique(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isBibliothecaire(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isCaissierComptable(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isCommunityAdmin(long userId, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isCommunityAdministrator(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isConseillerOrientation(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isConseillerSocial(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isDirectionMember(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isDocumentaliste(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isDoyen(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isDoyen(User user, long orgId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isEmps(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isENTAdmin(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isGARAdmin(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isGARAdmin(User user, long orgId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isInfirmiere(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isInspector(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isMainTeacher(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isMainTeacher(User user, long orgId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isMedical(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isOrientation(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isParent(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isPat(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isPersonal(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isPsychologue(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isSchoolAdmin(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isSchoolAdmin(User user, long schoolId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isSchoolLife(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isSecretariat(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isStudent(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isStudentOrParent(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isTeacher(User user);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isUserGroupAdmin(User user, long groupId);

}