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
 * Provides a wrapper for {@link MatomoLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MatomoLocalService
 * @generated
 */
public class MatomoLocalServiceWrapper
	implements MatomoLocalService, ServiceWrapper<MatomoLocalService> {

	public MatomoLocalServiceWrapper(MatomoLocalService matomoLocalService) {
		_matomoLocalService = matomoLocalService;
	}

	@Override
	public org.json.JSONObject fetchStatistics(
			com.liferay.portal.kernel.model.User user, String compareOn,
			String period, java.util.Date startDate, java.util.Date endDate,
			java.util.List<Long> profileIds, java.util.List<Long> schoolIds,
			java.util.List<Long> serviceIds, boolean actions)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException,
			   java.io.IOException {

		return _matomoLocalService.fetchStatistics(
			user, compareOn, period, startDate, endDate, profileIds, schoolIds,
			serviceIds, actions);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _matomoLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public String getServiceSegment(
		Long serviceId, java.util.List<Long> schoolIds,
		java.util.List<Long> profileIds) {

		return _matomoLocalService.getServiceSegment(
			serviceId, schoolIds, profileIds);
	}

	@Override
	public long getUserProfileId(com.liferay.portal.kernel.model.User user) {
		return _matomoLocalService.getUserProfileId(user);
	}

	@Override
	public MatomoLocalService getWrappedService() {
		return _matomoLocalService;
	}

	@Override
	public void setWrappedService(MatomoLocalService matomoLocalService) {
		_matomoLocalService = matomoLocalService;
	}

	private MatomoLocalService _matomoLocalService;

}