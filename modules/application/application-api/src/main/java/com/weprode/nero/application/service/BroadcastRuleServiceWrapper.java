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
 * Provides a wrapper for {@link BroadcastRuleService}.
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastRuleService
 * @generated
 */
public class BroadcastRuleServiceWrapper
	implements BroadcastRuleService, ServiceWrapper<BroadcastRuleService> {

	public BroadcastRuleServiceWrapper() {
		this(null);
	}

	public BroadcastRuleServiceWrapper(
		BroadcastRuleService broadcastRuleService) {

		_broadcastRuleService = broadcastRuleService;
	}

	@Override
	public org.json.JSONObject getApplicationRules(
		long applicationId, long schoolId) {

		return _broadcastRuleService.getApplicationRules(
			applicationId, schoolId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _broadcastRuleService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject updateBroadcastRules(
		long applicationId, long schoolId, String rules) {

		return _broadcastRuleService.updateBroadcastRules(
			applicationId, schoolId, rules);
	}

	@Override
	public BroadcastRuleService getWrappedService() {
		return _broadcastRuleService;
	}

	@Override
	public void setWrappedService(BroadcastRuleService broadcastRuleService) {
		_broadcastRuleService = broadcastRuleService;
	}

	private BroadcastRuleService _broadcastRuleService;

}