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

package com.weprode.facile.statistic.service;

/**
 * Provides the remote service utility for GeneralStat. This utility wraps
 * <code>com.weprode.facile.statistic.service.impl.GeneralStatServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see GeneralStatService
 * @generated
 */
public class GeneralStatServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.statistic.service.impl.GeneralStatServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getActionsCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId,
		long serviceId, java.lang.String comparator) {

		return getService().getActionsCount(
			startDate, endDate, schoolId, serviceId, comparator);
	}

	public static org.json.JSONObject getActiveUsersCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().getActiveUsersCount(startDate, endDate, schoolId);
	}

	public static org.json.JSONObject getDashboardStatistics() {
		return getService().getDashboardStatistics();
	}

	public static org.json.JSONObject getFilesCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().getFilesCount(startDate, endDate, schoolId);
	}

	public static org.json.JSONObject getHomeworksCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().getHomeworksCount(startDate, endDate, schoolId);
	}

	public static org.json.JSONObject getMessagesCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().getMessagesCount(startDate, endDate, schoolId);
	}

	public static org.json.JSONObject getNewsCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().getNewsCount(startDate, endDate, schoolId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getSchoolLifeStudentsCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().getSchoolLifeStudentsCount(
			startDate, endDate, schoolId);
	}

	public static org.json.JSONObject getSessionsCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId,
		java.lang.String comparator) {

		return getService().getSessionsCount(
			startDate, endDate, schoolId, comparator);
	}

	public static GeneralStatService getService() {
		return _service;
	}

	private static volatile GeneralStatService _service;

}