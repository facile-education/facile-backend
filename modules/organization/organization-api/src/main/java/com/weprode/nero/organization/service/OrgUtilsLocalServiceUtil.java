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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;

import java.util.List;

/**
 * Provides the local service utility for OrgUtils. This utility wraps
 * <code>com.weprode.nero.organization.service.impl.OrgUtilsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marc Salvat
 * @see OrgUtilsLocalService
 * @generated
 */
public class OrgUtilsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.organization.service.impl.OrgUtilsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static String formatOrgName(String name, boolean withSchoolName) {
		return getService().formatOrgName(name, withSchoolName);
	}

	public static List<Organization> getAllSchools() {
		return getService().getAllSchools();
	}

	public static Organization getOrCreateOrganization(
			long companyId, String orgName, long schoolId, int type)
		throws PortalException, SystemException {

		return getService().getOrCreateOrganization(
			companyId, orgName, schoolId, type);
	}

	public static Organization getOrCreateRootOrg(long companyId)
		throws PortalException, SystemException {

		return getService().getOrCreateRootOrg(companyId);
	}

	public static Organization getOrCreateSchool(long companyId, String name)
		throws PortalException, SystemException {

		return getService().getOrCreateSchool(companyId, name);
	}

	public static Organization getOrCreateSchool(
			long companyId, String schoolName, String entStructureUAI)
		throws PortalException, SystemException {

		return getService().getOrCreateSchool(
			companyId, schoolName, entStructureUAI);
	}

	public static String getOrgColor(User user, Organization org) {
		return getService().getOrgColor(user, org);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<Organization> getSchoolClasses(
		long schoolId, Boolean withArchives) {

		return getService().getSchoolClasses(schoolId, withArchives);
	}

	public static List<Organization> getSchoolOrganizations(
		long schoolId, List<Integer> types, List<Integer> roles,
		Boolean withArchives) {

		return getService().getSchoolOrganizations(
			schoolId, types, roles, withArchives);
	}

	public static Organization getSchoolPATsOrganization(long schoolId) {
		return getService().getSchoolPATsOrganization(schoolId);
	}

	public static Organization getSchoolPersonalsOrganization(long schoolId) {
		return getService().getSchoolPersonalsOrganization(schoolId);
	}

	public static Organization getSchoolTeachersOrganization(long schoolId) {
		return getService().getSchoolTeachersOrganization(schoolId);
	}

	public static boolean isVoleeAuthorized(String volee) {
		return getService().isVoleeAuthorized(volee);
	}

	public static OrgUtilsLocalService getService() {
		return _service;
	}

	private static volatile OrgUtilsLocalService _service;

}