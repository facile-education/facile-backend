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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for OrgUtils. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Marc Salvat
 * @see OrgUtilsLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface OrgUtilsLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.organization.service.impl.OrgUtilsLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the org utils local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link OrgUtilsLocalServiceUtil} if injection and service tracking are not available.
	 */
	public String formatOrgName(String name, boolean withSchoolName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getAllSchools();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Organization getOrCreateOrganization(
			long companyId, String orgName, long schoolId, int type)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Organization getOrCreateRootOrg(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Organization getOrCreateSchool(long companyId, String name)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Organization getOrCreateSchool(
			long companyId, String schoolName, String entStructureUAI)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getOrgColor(User user, Organization org);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getSchoolClasses(
		long schoolId, Boolean withArchives);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Organization> getSchoolOrganizations(
		long schoolId, List<Integer> types, Boolean withArchives);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isVoleeAuthorized(String volee);

}