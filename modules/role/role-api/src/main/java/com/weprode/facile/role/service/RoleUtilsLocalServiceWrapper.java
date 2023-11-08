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

package com.weprode.facile.role.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RoleUtilsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RoleUtilsLocalService
 * @generated
 */
public class RoleUtilsLocalServiceWrapper
	implements RoleUtilsLocalService, ServiceWrapper<RoleUtilsLocalService> {

	public RoleUtilsLocalServiceWrapper() {
		this(null);
	}

	public RoleUtilsLocalServiceWrapper(
		RoleUtilsLocalService roleUtilsLocalService) {

		_roleUtilsLocalService = roleUtilsLocalService;
	}

	@Override
	public String displayUserRoles(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.displayUserRoles(user);
	}

	@Override
	public com.liferay.portal.kernel.model.Role getAdministratorRole() {
		return _roleUtilsLocalService.getAdministratorRole();
	}

	@Override
	public java.util.List<Long> getAgentsRoleIds() {
		return _roleUtilsLocalService.getAgentsRoleIds();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getAssistantTechniqueRole() {
		return _roleUtilsLocalService.getAssistantTechniqueRole();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role>
		getAvailableRolesForLocalUser() {

		return _roleUtilsLocalService.getAvailableRolesForLocalUser();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getBibliothecaireRole() {
		return _roleUtilsLocalService.getBibliothecaireRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getCaissierComptableRole() {
		return _roleUtilsLocalService.getCaissierComptableRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role
		getCommunityAdministratorRole() {

		return _roleUtilsLocalService.getCommunityAdministratorRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getCommunityOwnerRole() {
		return _roleUtilsLocalService.getCommunityOwnerRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getConseillerOrientationRole() {
		return _roleUtilsLocalService.getConseillerOrientationRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getConseillerSocialRole() {
		return _roleUtilsLocalService.getConseillerSocialRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getDirectionRole() {
		return _roleUtilsLocalService.getDirectionRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getDoyenRole() {
		return _roleUtilsLocalService.getDoyenRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getEntAdminRole() {
		return _roleUtilsLocalService.getEntAdminRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getInfirmiereRole() {
		return _roleUtilsLocalService.getInfirmiereRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getMainTeacherRole() {
		return _roleUtilsLocalService.getMainTeacherRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getOrganizationUserRole() {
		return _roleUtilsLocalService.getOrganizationUserRole();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _roleUtilsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getParentRole() {
		return _roleUtilsLocalService.getParentRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getPersonalRole() {
		return _roleUtilsLocalService.getPersonalRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getPsychologueRole() {
		return _roleUtilsLocalService.getPsychologueRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getRole(String roleName) {
		return _roleUtilsLocalService.getRole(roleName);
	}

	@Override
	public com.liferay.portal.kernel.model.Role getSchoolAdminRole() {
		return _roleUtilsLocalService.getSchoolAdminRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getSecretariatRole() {
		return _roleUtilsLocalService.getSecretariatRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getStudentRole() {
		return _roleUtilsLocalService.getStudentRole();
	}

	@Override
	public com.liferay.portal.kernel.model.Role getTeacherRole() {
		return _roleUtilsLocalService.getTeacherRole();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role> getUserEntRoles(
		com.liferay.portal.kernel.model.User user) {

		return _roleUtilsLocalService.getUserEntRoles(user);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role>
		getUserSearchableRoles(com.liferay.portal.kernel.model.User user) {

		return _roleUtilsLocalService.getUserSearchableRoles(user);
	}

	@Override
	public boolean isAdministrator(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isAdministrator(user);
	}

	@Override
	public boolean isAssistantTechnique(
		com.liferay.portal.kernel.model.User user) {

		return _roleUtilsLocalService.isAssistantTechnique(user);
	}

	@Override
	public boolean isBibliothecaire(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isBibliothecaire(user);
	}

	@Override
	public boolean isCaissierComptable(
		com.liferay.portal.kernel.model.User user) {

		return _roleUtilsLocalService.isCaissierComptable(user);
	}

	@Override
	public boolean isCommunityAdmin(long userId, long groupId) {
		return _roleUtilsLocalService.isCommunityAdmin(userId, groupId);
	}

	@Override
	public boolean isCommunityAdministrator(
		com.liferay.portal.kernel.model.User user) {

		return _roleUtilsLocalService.isCommunityAdministrator(user);
	}

	@Override
	public boolean isConseillerOrientation(
		com.liferay.portal.kernel.model.User user) {

		return _roleUtilsLocalService.isConseillerOrientation(user);
	}

	@Override
	public boolean isConseillerSocial(
		com.liferay.portal.kernel.model.User user) {

		return _roleUtilsLocalService.isConseillerSocial(user);
	}

	@Override
	public boolean isConseillerSocial(
		com.liferay.portal.kernel.model.User user, long orgId) {

		return _roleUtilsLocalService.isConseillerSocial(user, orgId);
	}

	@Override
	public boolean isDirectionMember(
		com.liferay.portal.kernel.model.User user) {

		return _roleUtilsLocalService.isDirectionMember(user);
	}

	@Override
	public boolean isDoyen(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isDoyen(user);
	}

	@Override
	public boolean isDoyen(
		com.liferay.portal.kernel.model.User user, long orgId) {

		return _roleUtilsLocalService.isDoyen(user, orgId);
	}

	@Override
	public boolean isENTAdmin(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isENTAdmin(user);
	}

	@Override
	public boolean isForClass(com.liferay.portal.kernel.model.Role role) {
		return _roleUtilsLocalService.isForClass(role);
	}

	@Override
	public boolean isInfirmiere(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isInfirmiere(user);
	}

	@Override
	public boolean isMainTeacher(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isMainTeacher(user);
	}

	@Override
	public boolean isMainTeacher(
		com.liferay.portal.kernel.model.User user, long orgId) {

		return _roleUtilsLocalService.isMainTeacher(user, orgId);
	}

	@Override
	public boolean isParent(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isParent(user);
	}

	@Override
	public boolean isPersonal(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isPersonal(user);
	}

	@Override
	public boolean isPsychologue(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isPsychologue(user);
	}

	@Override
	public boolean isPsychologue(
		com.liferay.portal.kernel.model.User user, long orgId) {

		return _roleUtilsLocalService.isPsychologue(user, orgId);
	}

	@Override
	public boolean isSchoolAdmin(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isSchoolAdmin(user);
	}

	@Override
	public boolean isSchoolAdmin(
		com.liferay.portal.kernel.model.User user, long schoolId) {

		return _roleUtilsLocalService.isSchoolAdmin(user, schoolId);
	}

	@Override
	public boolean isSecretariat(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isSecretariat(user);
	}

	@Override
	public boolean isStudent(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isStudent(user);
	}

	@Override
	public boolean isStudentOrParent(
		com.liferay.portal.kernel.model.User user) {

		return _roleUtilsLocalService.isStudentOrParent(user);
	}

	@Override
	public boolean isTeacher(com.liferay.portal.kernel.model.User user) {
		return _roleUtilsLocalService.isTeacher(user);
	}

	@Override
	public boolean isUserGroupAdmin(
		com.liferay.portal.kernel.model.User user, long groupId) {

		return _roleUtilsLocalService.isUserGroupAdmin(user, groupId);
	}

	@Override
	public RoleUtilsLocalService getWrappedService() {
		return _roleUtilsLocalService;
	}

	@Override
	public void setWrappedService(RoleUtilsLocalService roleUtilsLocalService) {
		_roleUtilsLocalService = roleUtilsLocalService;
	}

	private RoleUtilsLocalService _roleUtilsLocalService;

}