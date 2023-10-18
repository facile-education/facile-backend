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

package com.weprode.nero.statistic.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GeneralStatService}.
 *
 * @author Brian Wing Shun Chan
 * @see GeneralStatService
 * @generated
 */
public class GeneralStatServiceWrapper
	implements GeneralStatService, ServiceWrapper<GeneralStatService> {

	public GeneralStatServiceWrapper() {
		this(null);
	}

	public GeneralStatServiceWrapper(GeneralStatService generalStatService) {
		_generalStatService = generalStatService;
	}

	@Override
	public org.json.JSONObject getActionsCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId,
		long serviceId, String comparator) {

		return _generalStatService.getActionsCount(
			startDate, endDate, schoolId, serviceId, comparator);
	}

	@Override
	public org.json.JSONObject getActiveUsersCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatService.getActiveUsersCount(
			startDate, endDate, schoolId);
	}

	@Override
	public org.json.JSONObject getDashboardStatistics() {
		return _generalStatService.getDashboardStatistics();
	}

	@Override
	public org.json.JSONObject getFilesCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatService.getFilesCount(startDate, endDate, schoolId);
	}

	@Override
	public org.json.JSONObject getHomeworksCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatService.getHomeworksCount(
			startDate, endDate, schoolId);
	}

	@Override
	public org.json.JSONObject getMessagesCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatService.getMessagesCount(
			startDate, endDate, schoolId);
	}

	@Override
	public org.json.JSONObject getNewsCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return _generalStatService.getNewsCount(startDate, endDate, schoolId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _generalStatService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSessionsCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId,
		String comparator) {

		return _generalStatService.getSessionsCount(
			startDate, endDate, schoolId, comparator);
	}

	@Override
	public GeneralStatService getWrappedService() {
		return _generalStatService;
	}

	@Override
	public void setWrappedService(GeneralStatService generalStatService) {
		_generalStatService = generalStatService;
	}

	private GeneralStatService _generalStatService;

}