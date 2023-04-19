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

package com.weprode.nero.schedule.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface HomeworkFinder {

	public java.util.List<com.weprode.nero.schedule.model.Homework>
			getTeacherHomeworks(
				com.liferay.portal.kernel.model.User teacher,
				java.util.Date minDate, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.weprode.nero.schedule.model.Homework>
		getStudentHomeworksFromGroupIds(
			java.util.List<Long> studentGroupIdList, java.util.Date minDate,
			java.util.Date maxDate);

}