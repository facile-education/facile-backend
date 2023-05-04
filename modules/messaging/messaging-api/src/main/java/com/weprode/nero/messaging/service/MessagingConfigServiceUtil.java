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

/**
 * Provides the remote service utility for MessagingConfig. This utility wraps
 * <code>com.weprode.nero.messaging.service.impl.MessagingConfigServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingConfigService
 * @generated
 */
public class MessagingConfigServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.messaging.service.impl.MessagingConfigServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getMessagingConfiguration()
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().getMessagingConfiguration();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Update messaging configuration
	 */
	public static org.json.JSONObject updateMessagingConfiguration(
			java.lang.String configuration)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().updateMessagingConfiguration(configuration);
	}

	public static MessagingConfigService getService() {
		return _service;
	}

	private static volatile MessagingConfigService _service;

}