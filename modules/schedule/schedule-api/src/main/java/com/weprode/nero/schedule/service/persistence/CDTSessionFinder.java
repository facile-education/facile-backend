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
public interface CDTSessionFinder {

	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getTeacherSessions(
			long teacherId, java.util.Date minDate, java.util.Date maxDate);

	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getStudentSpecificSessions(
			long studentId, java.util.Date minDate, java.util.Date maxDate);

	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getSessionActivity(
			long userId, java.util.List<Long> groupIds, java.util.Date minDate,
			java.util.Date maxDate);

	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
			getSchoolSessions(
				Long schoolId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
			getGroupsSessions(
				java.util.List<Long> groupIds, java.util.Date startDate,
				java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

}