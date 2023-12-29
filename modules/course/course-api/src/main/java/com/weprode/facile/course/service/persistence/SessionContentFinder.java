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

package com.weprode.facile.course.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface SessionContentFinder {

	public java.util.List<com.weprode.facile.course.model.SessionContent>
			getCourseContents(
				long courseId, java.util.Date minDate, java.util.Date maxDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countSchoolSessionContents(
		long schoolId, java.util.Date minDate, java.util.Date maxDate);

}