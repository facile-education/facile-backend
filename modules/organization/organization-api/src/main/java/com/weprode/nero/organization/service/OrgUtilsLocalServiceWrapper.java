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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OrgUtilsLocalService}.
 *
 * @author Marc Salvat
 * @see OrgUtilsLocalService
 * @generated
 */
public class OrgUtilsLocalServiceWrapper
	implements OrgUtilsLocalService, ServiceWrapper<OrgUtilsLocalService> {

	public OrgUtilsLocalServiceWrapper(
		OrgUtilsLocalService orgUtilsLocalService) {

		_orgUtilsLocalService = orgUtilsLocalService;
	}

	@Override
	public String formatOrgName(String name, boolean withSchoolName) {
		return _orgUtilsLocalService.formatOrgName(name, withSchoolName);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getAllSchools() {

		return _orgUtilsLocalService.getAllSchools();
	}

	@Override
	public com.liferay.portal.kernel.model.Organization getOrCreateOrganization(
			long companyId, String orgName, long schoolId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _orgUtilsLocalService.getOrCreateOrganization(
			companyId, orgName, schoolId, type);
	}

	@Override
	public com.liferay.portal.kernel.model.Organization getOrCreateRootOrg(
			long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _orgUtilsLocalService.getOrCreateRootOrg(companyId);
	}

	@Override
	public com.liferay.portal.kernel.model.Organization getOrCreateSchool(
			long companyId, String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _orgUtilsLocalService.getOrCreateSchool(companyId, name);
	}

	@Override
	public com.liferay.portal.kernel.model.Organization getOrCreateSchool(
			long companyId, String schoolName, String entStructureUAI)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _orgUtilsLocalService.getOrCreateSchool(
			companyId, schoolName, entStructureUAI);
	}

	@Override
	public String getOrgColor(
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.model.Organization org) {

		return _orgUtilsLocalService.getOrgColor(user, org);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _orgUtilsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getSchoolClasses(long schoolId, Boolean withArchives) {

		return _orgUtilsLocalService.getSchoolClasses(schoolId, withArchives);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getSchoolOrganizations(
			long schoolId, java.util.List<Integer> types,
			Boolean withArchives) {

		return _orgUtilsLocalService.getSchoolOrganizations(
			schoolId, types, withArchives);
	}

	@Override
	public boolean isVoleeAuthorized(String volee) {
		return _orgUtilsLocalService.isVoleeAuthorized(volee);
	}

	@Override
	public OrgUtilsLocalService getWrappedService() {
		return _orgUtilsLocalService;
	}

	@Override
	public void setWrappedService(OrgUtilsLocalService orgUtilsLocalService) {
		_orgUtilsLocalService = orgUtilsLocalService;
	}

	private OrgUtilsLocalService _orgUtilsLocalService;

}