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

package com.weprode.nero.agenda.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface EventFinder {

	public java.util.List<com.weprode.nero.agenda.model.Event> getUserEvents(
		long userId, int startIndex, int nbEvents,
		java.util.List<Long> groupIds, java.util.List<Long> roleIds,
		boolean unreadOnly);

	public int countEvents(
		long userId, java.util.Date minDate, java.util.List<Long> groupIds,
		java.util.List<Long> roleIds, boolean unreadOnly);

}