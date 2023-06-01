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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * Provides the local service utility for Matomo. This utility wraps
 * <code>com.weprode.nero.statistic.service.impl.MatomoLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MatomoLocalService
 * @generated
 */
public class MatomoLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.statistic.service.impl.MatomoLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject fetchStatistics(
			com.liferay.portal.kernel.model.User user, String compareOn,
			String period, java.util.Date startDate, java.util.Date endDate,
			List<Long> profileIds, List<Long> schoolIds, List<Long> serviceIds,
			boolean actions)
		throws java.io.IOException, PortalException, SystemException {

		return getService().fetchStatistics(
			user, compareOn, period, startDate, endDate, profileIds, schoolIds,
			serviceIds, actions);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static String getServiceSegment(
		Long serviceId, List<Long> schoolIds, List<Long> profileIds) {

		return getService().getServiceSegment(serviceId, schoolIds, profileIds);
	}

	public static long getUserProfileId(
		com.liferay.portal.kernel.model.User user) {

		return getService().getUserProfileId(user);
	}

	public static MatomoLocalService getService() {
		return _service;
	}

	private static volatile MatomoLocalService _service;

}