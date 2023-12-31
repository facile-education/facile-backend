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

package com.weprode.facile.organization.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Marc Salvat
 * @generated
 */
@ProviderType
public interface OrgUtilsFinder {

	public java.util.List<com.liferay.portal.kernel.model.Organization>
		findUserOrganizations(
			long userId, java.util.List<Integer> types, Boolean withArchives,
			long schoolId, int begin, int end);

	public java.util.List<com.liferay.portal.kernel.model.Organization>
		findSchoolOrganizations(
			long schoolId, java.util.List<Integer> types, Boolean withArchives,
			int begin, int end);

	public java.util.List<com.liferay.portal.kernel.model.Organization>
		findOrganizationByType(int type, int begin, int end);

}