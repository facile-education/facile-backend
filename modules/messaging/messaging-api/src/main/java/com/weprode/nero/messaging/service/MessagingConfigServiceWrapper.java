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

package com.weprode.nero.messaging.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingConfigService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingConfigService
 * @generated
 */
public class MessagingConfigServiceWrapper
	implements MessagingConfigService, ServiceWrapper<MessagingConfigService> {

	public MessagingConfigServiceWrapper() {
		this(null);
	}

	public MessagingConfigServiceWrapper(
		MessagingConfigService messagingConfigService) {

		_messagingConfigService = messagingConfigService;
	}

	@Override
	public org.json.JSONObject getMessagingConfiguration() {
		return _messagingConfigService.getMessagingConfiguration();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messagingConfigService.getOSGiServiceIdentifier();
	}

	/**
	 * Update messaging configuration
	 */
	@Override
	public org.json.JSONObject updateMessagingConfiguration(
		String configuration) {

		return _messagingConfigService.updateMessagingConfiguration(
			configuration);
	}

	@Override
	public MessagingConfigService getWrappedService() {
		return _messagingConfigService;
	}

	@Override
	public void setWrappedService(
		MessagingConfigService messagingConfigService) {

		_messagingConfigService = messagingConfigService;
	}

	private MessagingConfigService _messagingConfigService;

}