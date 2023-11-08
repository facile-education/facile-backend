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

package com.weprode.facile.organization.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserOrgsLocalService}.
 *
 * @author Marc Salvat
 * @see UserOrgsLocalService
 * @generated
 */
public class UserOrgsLocalServiceWrapper
	implements ServiceWrapper<UserOrgsLocalService>, UserOrgsLocalService {

	public UserOrgsLocalServiceWrapper() {
		this(null);
	}

	public UserOrgsLocalServiceWrapper(
		UserOrgsLocalService userOrgsLocalService) {

		_userOrgsLocalService = userOrgsLocalService;
	}

	@Override
	public boolean affectManuallyUserToOrg(
		long userId, com.liferay.portal.kernel.model.Organization org) {

		return _userOrgsLocalService.affectManuallyUserToOrg(userId, org);
	}

	@Override
	public Long countOrgMembers(long orgId) {
		return _userOrgsLocalService.countOrgMembers(orgId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getAffectedClasses(
			com.liferay.portal.kernel.model.User user, long roleId) {

		return _userOrgsLocalService.getAffectedClasses(user, roleId);
	}

	/**
	 * Returns the list of user's schools including school complex
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getAllUserSchoolsIncludingSchoolComplex(
			com.liferay.portal.kernel.model.User user) {

		return _userOrgsLocalService.getAllUserSchoolsIncludingSchoolComplex(
			user);
	}

	@Override
	public com.liferay.portal.kernel.model.Organization getEtabRatachement(
		com.liferay.portal.kernel.model.User user) {

		return _userOrgsLocalService.getEtabRatachement(user);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userOrgsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getRoleAffectedClasses(com.liferay.portal.kernel.model.User user) {

		return _userOrgsLocalService.getRoleAffectedClasses(user);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getSchoolTeachers(long schoolId) {

		return _userOrgsLocalService.getSchoolTeachers(schoolId);
	}

	@Override
	public String getStudentClassName(
		com.liferay.portal.kernel.model.User student) {

		return _userOrgsLocalService.getStudentClassName(student);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserClasses(
			com.liferay.portal.kernel.model.User user, boolean withArchive) {

		return _userOrgsLocalService.getUserClasses(user, withArchive);
	}

	/**
	 * Returns all classes and groups the user is member of
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserClasses(
			com.liferay.portal.kernel.model.User user, boolean withArchive,
			long schoolId) {

		return _userOrgsLocalService.getUserClasses(
			user, withArchive, schoolId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserClassesAndCours(
			com.liferay.portal.kernel.model.User user, boolean withArchive) {

		return _userOrgsLocalService.getUserClassesAndCours(user, withArchive);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserClassesAndCours(
			com.liferay.portal.kernel.model.User user, boolean withArchive,
			long schoolId) {

		return _userOrgsLocalService.getUserClassesAndCours(
			user, withArchive, schoolId);
	}

	/**
	 * Returns all classes and groups the user is member of, for a given school
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserCours(
			com.liferay.portal.kernel.model.User user, boolean withArchive,
			long schoolId) {

		return _userOrgsLocalService.getUserCours(user, withArchive, schoolId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserOrganizations(
			long userId, java.util.List<Integer> types, Boolean withArchives,
			long schoolId) {

		return _userOrgsLocalService.getUserOrganizations(
			userId, types, withArchives, schoolId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserSchools(com.liferay.portal.kernel.model.User user) {

		return _userOrgsLocalService.getUserSchools(user);
	}

	/**
	 * Returns all subject organizations the user is member of
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserSubjectOrganizations(
			com.liferay.portal.kernel.model.User user, long schoolId,
			boolean withArchive) {

		return _userOrgsLocalService.getUserSubjectOrganizations(
			user, schoolId, withArchive);
	}

	/**
	 * Return all schools that can see a user (In admin services for example)
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserVisibilitySchools(com.liferay.portal.kernel.model.User user) {

		return _userOrgsLocalService.getUserVisibilitySchools(user);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getUserVolees(
			com.liferay.portal.kernel.model.User user, boolean withArchive,
			long schoolId) {

		return _userOrgsLocalService.getUserVolees(user, withArchive, schoolId);
	}

	@Override
	public UserOrgsLocalService getWrappedService() {
		return _userOrgsLocalService;
	}

	@Override
	public void setWrappedService(UserOrgsLocalService userOrgsLocalService) {
		_userOrgsLocalService = userOrgsLocalService;
	}

	private UserOrgsLocalService _userOrgsLocalService;

}