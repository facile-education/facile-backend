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

import java.util.List;

/**
 * Provides the local service utility for RoleUtils. This utility wraps
 * <code>com.weprode.nero.role.service.impl.RoleUtilsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RoleUtilsLocalService
 * @generated
 */
public class RoleUtilsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.role.service.impl.RoleUtilsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Get the User Profile
	 */
	public static String displayUserRoles(
		com.liferay.portal.kernel.model.User user) {

		return getService().displayUserRoles(user);
	}

	public static com.liferay.portal.kernel.model.Role getAcademicRole() {
		return getService().getAcademicRole();
	}

	public static com.liferay.portal.kernel.model.Role getAdministrativeRole() {
		return getService().getAdministrativeRole();
	}

	public static com.liferay.portal.kernel.model.Role getAdministratorRole() {
		return getService().getAdministratorRole();
	}

	public static com.liferay.portal.kernel.model.Role
		getAssistantTechniqueRole() {

		return getService().getAssistantTechniqueRole();
	}

	public static List<com.liferay.portal.kernel.model.Role>
		getAvailableRolesForLocalUser() {

		return getService().getAvailableRolesForLocalUser();
	}

	public static com.liferay.portal.kernel.model.Role getBetaTesterRole() {
		return getService().getBetaTesterRole();
	}

	public static com.liferay.portal.kernel.model.Role getBibliothecaireRole() {
		return getService().getBibliothecaireRole();
	}

	public static com.liferay.portal.kernel.model.Role
		getCaissierComptableRole() {

		return getService().getCaissierComptableRole();
	}

	public static com.liferay.portal.kernel.model.Role
		getCommunityAdministratorRole() {

		return getService().getCommunityAdministratorRole();
	}

	public static com.liferay.portal.kernel.model.Role getCommunityOwnerRole() {
		return getService().getCommunityOwnerRole();
	}

	public static com.liferay.portal.kernel.model.Role
		getConseillerOrientationRole() {

		return getService().getConseillerOrientationRole();
	}

	public static com.liferay.portal.kernel.model.Role
		getConseillerSocialRole() {

		return getService().getConseillerSocialRole();
	}

	public static com.liferay.portal.kernel.model.Role getDirectionRole() {
		return getService().getDirectionRole();
	}

	public static com.liferay.portal.kernel.model.Role getDocumentalistRole() {
		return getService().getDocumentalistRole();
	}

	public static com.liferay.portal.kernel.model.Role getDoyenRole() {
		return getService().getDoyenRole();
	}

	public static com.liferay.portal.kernel.model.Role getEntAdminRole() {
		return getService().getEntAdminRole();
	}

	public static com.liferay.portal.kernel.model.Role getGarAdminRole() {
		return getService().getGarAdminRole();
	}

	public static com.liferay.portal.kernel.model.Role getInfirmiereRole() {
		return getService().getInfirmiereRole();
	}

	public static com.liferay.portal.kernel.model.Role getInspectorRole() {
		return getService().getInspectorRole();
	}

	public static com.liferay.portal.kernel.model.Role getMainTeacherRole() {
		return getService().getMainTeacherRole();
	}

	public static com.liferay.portal.kernel.model.Role getMedicalRole() {
		return getService().getMedicalRole();
	}

	public static com.liferay.portal.kernel.model.Role
		getOrganizationUserRole() {

		return getService().getOrganizationUserRole();
	}

	public static com.liferay.portal.kernel.model.Role getOrientationRole() {
		return getService().getOrientationRole();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.Role getParentRole() {
		return getService().getParentRole();
	}

	public static com.liferay.portal.kernel.model.Role getPsychologueRole() {
		return getService().getPsychologueRole();
	}

	public static com.liferay.portal.kernel.model.Role getRole(
		String roleName) {

		return getService().getRole(roleName);
	}

	public static com.liferay.portal.kernel.model.Role getSchoolAdminRole() {
		return getService().getSchoolAdminRole();
	}

	public static com.liferay.portal.kernel.model.Role getSchoolLifeRole() {
		return getService().getSchoolLifeRole();
	}

	public static com.liferay.portal.kernel.model.Role getSecretariatRole() {
		return getService().getSecretariatRole();
	}

	public static com.liferay.portal.kernel.model.Role getStudentRole() {
		return getService().getStudentRole();
	}

	public static com.liferay.portal.kernel.model.Role getTeacherRole() {
		return getService().getTeacherRole();
	}

	public static List<com.liferay.portal.kernel.model.Role>
		getUserSearchableRoles(com.liferay.portal.kernel.model.User user) {

		return getService().getUserSearchableRoles(user);
	}

	public static boolean isAcademic(
		com.liferay.portal.kernel.model.User user) {

		return getService().isAcademic(user);
	}

	public static boolean isAdministrative(
		com.liferay.portal.kernel.model.User user) {

		return getService().isAdministrative(user);
	}

	public static boolean isAdministrator(
		com.liferay.portal.kernel.model.User user) {

		return getService().isAdministrator(user);
	}

	public static boolean isAssistantTechnique(
		com.liferay.portal.kernel.model.User user) {

		return getService().isAssistantTechnique(user);
	}

	public static boolean isBibliothecaire(
		com.liferay.portal.kernel.model.User user) {

		return getService().isBibliothecaire(user);
	}

	public static boolean isCaissierComptable(
		com.liferay.portal.kernel.model.User user) {

		return getService().isCaissierComptable(user);
	}

	public static boolean isCommunityAdmin(long userId, long groupId) {
		return getService().isCommunityAdmin(userId, groupId);
	}

	public static boolean isCommunityAdministrator(
		com.liferay.portal.kernel.model.User user) {

		return getService().isCommunityAdministrator(user);
	}

	public static boolean isConseillerOrientation(
		com.liferay.portal.kernel.model.User user) {

		return getService().isConseillerOrientation(user);
	}

	public static boolean isConseillerSocial(
		com.liferay.portal.kernel.model.User user) {

		return getService().isConseillerSocial(user);
	}

	public static boolean isDirectionMember(
		com.liferay.portal.kernel.model.User user) {

		return getService().isDirectionMember(user);
	}

	public static boolean isDocumentaliste(
		com.liferay.portal.kernel.model.User user) {

		return getService().isDocumentaliste(user);
	}

	public static boolean isDoyen(com.liferay.portal.kernel.model.User user) {
		return getService().isDoyen(user);
	}

	public static boolean isDoyen(
		com.liferay.portal.kernel.model.User user, long orgId) {

		return getService().isDoyen(user, orgId);
	}

	public static boolean isEmps(com.liferay.portal.kernel.model.User user) {
		return getService().isEmps(user);
	}

	public static boolean isENTAdmin(
		com.liferay.portal.kernel.model.User user) {

		return getService().isENTAdmin(user);
	}

	public static boolean isGARAdmin(
		com.liferay.portal.kernel.model.User user) {

		return getService().isGARAdmin(user);
	}

	public static boolean isGARAdmin(
		com.liferay.portal.kernel.model.User user, long orgId) {

		return getService().isGARAdmin(user, orgId);
	}

	public static boolean isInfirmiere(
		com.liferay.portal.kernel.model.User user) {

		return getService().isInfirmiere(user);
	}

	public static boolean isInspector(
		com.liferay.portal.kernel.model.User user) {

		return getService().isInspector(user);
	}

	public static boolean isMainTeacher(
		com.liferay.portal.kernel.model.User user) {

		return getService().isMainTeacher(user);
	}

	public static boolean isMainTeacher(
		com.liferay.portal.kernel.model.User user, long orgId) {

		return getService().isMainTeacher(user, orgId);
	}

	public static boolean isMedical(com.liferay.portal.kernel.model.User user) {
		return getService().isMedical(user);
	}

	public static boolean isOrientation(
		com.liferay.portal.kernel.model.User user) {

		return getService().isOrientation(user);
	}

	public static boolean isParent(com.liferay.portal.kernel.model.User user) {
		return getService().isParent(user);
	}

	public static boolean isPat(com.liferay.portal.kernel.model.User user) {
		return getService().isPat(user);
	}

	public static boolean isPersonal(
		com.liferay.portal.kernel.model.User user) {

		return getService().isPersonal(user);
	}

	public static boolean isPsychologue(
		com.liferay.portal.kernel.model.User user) {

		return getService().isPsychologue(user);
	}

	public static boolean isSchoolAdmin(
		com.liferay.portal.kernel.model.User user) {

		return getService().isSchoolAdmin(user);
	}

	public static boolean isSchoolAdmin(
		com.liferay.portal.kernel.model.User user, long schoolId) {

		return getService().isSchoolAdmin(user, schoolId);
	}

	public static boolean isSchoolLife(
		com.liferay.portal.kernel.model.User user) {

		return getService().isSchoolLife(user);
	}

	public static boolean isSecretariat(
		com.liferay.portal.kernel.model.User user) {

		return getService().isSecretariat(user);
	}

	public static boolean isStudent(com.liferay.portal.kernel.model.User user) {
		return getService().isStudent(user);
	}

	public static boolean isStudentOrParent(
		com.liferay.portal.kernel.model.User user) {

		return getService().isStudentOrParent(user);
	}

	public static boolean isTeacher(com.liferay.portal.kernel.model.User user) {
		return getService().isTeacher(user);
	}

	public static boolean isUserGroupAdmin(
		com.liferay.portal.kernel.model.User user, long groupId) {

		return getService().isUserGroupAdmin(user, groupId);
	}

	public static RoleUtilsLocalService getService() {
		return _service;
	}

	private static volatile RoleUtilsLocalService _service;

}