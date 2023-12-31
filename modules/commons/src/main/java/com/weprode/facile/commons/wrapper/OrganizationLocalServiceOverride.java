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

package com.weprode.facile.commons.wrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.organization.service.ClassCoursMappingLocalServiceUtil;
import com.weprode.facile.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgMappingLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
		immediate = true,
		service = ServiceWrapper.class
)
public class OrganizationLocalServiceOverride extends OrganizationLocalServiceWrapper {

	private static final Log logger = LogFactoryUtil.getLog(OrganizationLocalServiceOverride.class);

	public OrganizationLocalServiceOverride() {
		super(null);
	}

	@Override
	public Organization deleteOrganization(long organizationId) throws PortalException {

		// Remove organization related data
		deleteOrganizationData(organizationId);

		return super.deleteOrganization(organizationId);
	}

	@Override
	public Organization deleteOrganization(Organization organization) throws PortalException {

		// Remove organization related content
		deleteOrganizationData(organization.getOrganizationId());

		return super.deleteOrganization(organization);
	}

	private void deleteOrganizationData (long organizationId) throws PortalException {

		logger.info("Deleting organizationId "+ organizationId);

		boolean isSchool = OrgDetailsLocalServiceUtil.isSchool(organizationId);

		// Organization details
		try {
			OrgDetailsLocalServiceUtil.deleteOrgDetails(organizationId);
		} catch (Exception e) {
			logger.error("Could not remove orgDetails");
		}

		// ClassCours mapping
		ClassCoursMappingLocalServiceUtil.deleteByClassOrgId(organizationId);
		ClassCoursMappingLocalServiceUtil.deleteByCoursOrgId(organizationId);

		if (isSchool) {
			try {
				OrgMappingLocalServiceUtil.deleteOrgMapping(organizationId);
			} catch (Exception e) {
				logger.error("Could not remove orgMapping");
			}
		}

		// Remove organization members
		long[] userIds = UserLocalServiceUtil.getOrganizationUserIds(organizationId);
		if (userIds != null && userIds.length > 0) {
			logger.info("Removing "+userIds.length+" members from organizationId "+organizationId+"...");
			UserLocalServiceUtil.unsetOrganizationUsers(organizationId, userIds);
		}
	}

	// Add the following method to the bottom of your service wrapper
	// so it can find the appropriate service it’s overriding on deployment.
	@Reference(unbind = "-")
	private void serviceSetter(OrganizationLocalService organizationLocalService) {
		setWrappedService(organizationLocalService);
	}
}
