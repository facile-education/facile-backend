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

package com.weprode.nero.schedule.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HomeworkService}.
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkService
 * @generated
 */
public class HomeworkServiceWrapper
	implements HomeworkService, ServiceWrapper<HomeworkService> {

	public HomeworkServiceWrapper(HomeworkService homeworkService) {
		_homeworkService = homeworkService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getHomeworks(
			long studentId, String minDateStr)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.getHomeworks(studentId, minDateStr);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _homeworkService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject setHomeworkDone(
		long homeworkId, boolean isDone) {

		return _homeworkService.setHomeworkDone(homeworkId, isDone);
	}

	@Override
	public HomeworkService getWrappedService() {
		return _homeworkService;
	}

	@Override
	public void setWrappedService(HomeworkService homeworkService) {
		_homeworkService = homeworkService;
	}

	private HomeworkService _homeworkService;

}