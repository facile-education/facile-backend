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

package com.weprode.facile.user.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface UserUtilsFinder {

	public java.util.List<com.liferay.portal.kernel.model.User>
		findUsersFromIdList(java.util.List<Long> userIds);

	public Long getUserCountFromContactSearch(
			String searchQuery, java.util.List<Long> organizationIds,
			java.util.List<Long> groupIds, java.util.List<Long> roleIds,
			java.util.List<Long> subjectIds, boolean localUsersOnly)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.kernel.model.User>
			getUserFromContactSearch(
				String searchQuery, java.util.List<Long> organizationIds,
				java.util.List<Long> groupIds, java.util.List<Long> roleIds,
				java.util.List<Long> subjectIds, boolean localUsersOnly,
				int start, int stop,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

}