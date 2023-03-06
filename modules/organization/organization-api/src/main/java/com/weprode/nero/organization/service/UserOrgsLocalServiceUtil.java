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

package com.weprode.nero.organization.service;

import java.util.List;

/**
 * Provides the local service utility for UserOrgs. This utility wraps
 * <code>com.weprode.nero.organization.service.impl.UserOrgsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marc Salvat
 * @see UserOrgsLocalService
 * @generated
 */
public class UserOrgsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.organization.service.impl.UserOrgsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean affectManuallyUserToOrg(
		long userId, com.liferay.portal.kernel.model.Organization org) {

		return getService().affectManuallyUserToOrg(userId, org);
	}

	public static Long countOrgMembers(long orgId) {
		return getService().countOrgMembers(orgId);
	}

	public static List<com.liferay.portal.kernel.model.Organization>
		getAffectedClasses(
			com.liferay.portal.kernel.model.User user, long roleId) {

		return getService().getAffectedClasses(user, roleId);
	}

	/**
	 * Returns the list of user's schools including school complex
	 */
	public static List<com.liferay.portal.kernel.model.Organization>
		getAllUserSchoolsIncludingSchoolComplex(
			com.liferay.portal.kernel.model.User user) {

		return getService().getAllUserSchoolsIncludingSchoolComplex(user);
	}

	public static com.liferay.portal.kernel.model.Organization
		getEtabRatachement(com.liferay.portal.kernel.model.User user) {

		return getService().getEtabRatachement(user);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<com.liferay.portal.kernel.model.Organization>
		getRoleAffectedClasses(com.liferay.portal.kernel.model.User user) {

		return getService().getRoleAffectedClasses(user);
	}

	public static List<com.liferay.portal.kernel.model.User> getSchoolTeachers(
		long schoolId) {

		return getService().getSchoolTeachers(schoolId);
	}

	public static String getStudentClassName(
		com.liferay.portal.kernel.model.User student) {

		return getService().getStudentClassName(student);
	}

	public static List<com.liferay.portal.kernel.model.Organization>
		getUserClasses(
			com.liferay.portal.kernel.model.User user, boolean withArchive) {

		return getService().getUserClasses(user, withArchive);
	}

	/**
	 * Returns all classes and groups the user is member of
	 */
	public static List<com.liferay.portal.kernel.model.Organization>
		getUserClasses(
			com.liferay.portal.kernel.model.User user, boolean withArchive,
			long schoolId) {

		return getService().getUserClasses(user, withArchive, schoolId);
	}

	public static List<com.liferay.portal.kernel.model.Organization>
		getUserClassesAndCours(
			com.liferay.portal.kernel.model.User user, boolean withArchive) {

		return getService().getUserClassesAndCours(user, withArchive);
	}

	public static List<com.liferay.portal.kernel.model.Organization>
		getUserClassesAndCours(
			com.liferay.portal.kernel.model.User user, boolean withArchive,
			long schoolId) {

		return getService().getUserClassesAndCours(user, withArchive, schoolId);
	}

	/**
	 * Returns all classes and groups the user is member of, for a given school
	 */
	public static List<com.liferay.portal.kernel.model.Organization>
		getUserCours(
			com.liferay.portal.kernel.model.User user, boolean withArchive,
			long schoolId) {

		return getService().getUserCours(user, withArchive, schoolId);
	}

	public static List<com.liferay.portal.kernel.model.Organization>
		getUserOrganizations(
			long userId, List<Integer> types, List<Integer> roles,
			Boolean withArchives, long schoolId) {

		return getService().getUserOrganizations(
			userId, types, roles, withArchives, schoolId);
	}

	public static List<com.liferay.portal.kernel.model.Organization>
		getUserSchools(com.liferay.portal.kernel.model.User user) {

		return getService().getUserSchools(user);
	}

	/**
	 * Returns all subject organizations the user is member of
	 */
	public static List<com.liferay.portal.kernel.model.Organization>
		getUserSubjectOrganizations(
			com.liferay.portal.kernel.model.User user, long schoolId,
			boolean withArchive) {

		return getService().getUserSubjectOrganizations(
			user, schoolId, withArchive);
	}

	/**
	 * Return all schools that can see a user (In admin services for example)
	 */
	public static List<com.liferay.portal.kernel.model.Organization>
		getUserVisibilitySchools(com.liferay.portal.kernel.model.User user) {

		return getService().getUserVisibilitySchools(user);
	}

	public static List<com.liferay.portal.kernel.model.Organization>
		getUserVolees(
			com.liferay.portal.kernel.model.User user, boolean withArchive,
			long schoolId) {

		return getService().getUserVolees(user, withArchive, schoolId);
	}

	public static UserOrgsLocalService getService() {
		return _service;
	}

	private static volatile UserOrgsLocalService _service;

}