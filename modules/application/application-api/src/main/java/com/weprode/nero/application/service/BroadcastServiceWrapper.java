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

package com.weprode.nero.application.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BroadcastService}.
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastService
 * @generated
 */
public class BroadcastServiceWrapper
	implements BroadcastService, ServiceWrapper<BroadcastService> {

	public BroadcastServiceWrapper(BroadcastService broadcastService) {
		_broadcastService = broadcastService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _broadcastService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateBroadcast(
		long applicationId, long schoolId, boolean isBroadcasted,
		String applicationUrl) {

		return _broadcastService.updateBroadcast(
			applicationId, schoolId, isBroadcasted, applicationUrl);
	}

	@Override
	public BroadcastService getWrappedService() {
		return _broadcastService;
	}

	@Override
	public void setWrappedService(BroadcastService broadcastService) {
		_broadcastService = broadcastService;
	}

	private BroadcastService _broadcastService;

}