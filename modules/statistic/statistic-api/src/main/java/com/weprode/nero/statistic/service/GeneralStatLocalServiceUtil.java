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

import java.util.Map;

/**
 * Provides the local service utility for GeneralStat. This utility wraps
 * <code>com.weprode.nero.statistic.service.impl.GeneralStatLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see GeneralStatLocalService
 * @generated
 */
public class GeneralStatLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.statistic.service.impl.GeneralStatLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static int countActiveUsers(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().countActiveUsers(startDate, endDate, schoolId);
	}

	public static Map<String, Integer> countFiles(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().countFiles(startDate, endDate, schoolId);
	}

	public static Map<Integer, Integer> countHomeworks(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().countHomeworks(startDate, endDate, schoolId);
	}

	public static int countMessages(
		java.util.Date startDate, java.util.Date endDate, long schoolId) {

		return getService().countMessages(startDate, endDate, schoolId);
	}

	public static int countNews(
		java.util.Date startDate, java.util.Date endDate, long schoolId,
		boolean isSchoolNewsType) {

		return getService().countNews(
			startDate, endDate, schoolId, isSchoolNewsType);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static GeneralStatLocalService getService() {
		return _service;
	}

	private static volatile GeneralStatLocalService _service;

}