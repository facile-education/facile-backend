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

package com.weprode.nero.user.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AffectationService}.
 *
 * @author Brian Wing Shun Chan
 * @see AffectationService
 * @generated
 */
public class AffectationServiceWrapper
	implements AffectationService, ServiceWrapper<AffectationService> {

	public AffectationServiceWrapper(AffectationService affectationService) {
		_affectationService = affectationService;
	}

	@Override
	public org.json.JSONObject addUserAffectation(
		long userId, long orgId, String expirationDate) {

		return _affectationService.addUserAffectation(
			userId, orgId, expirationDate);
	}

	@Override
	public org.json.JSONObject getAffectedUsers(long schoolId, String filter) {
		return _affectationService.getAffectedUsers(schoolId, filter);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _affectationService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject removeUserAffectation(long userId, long orgId) {
		return _affectationService.removeUserAffectation(userId, orgId);
	}

	@Override
	public AffectationService getWrappedService() {
		return _affectationService;
	}

	@Override
	public void setWrappedService(AffectationService affectationService) {
		_affectationService = affectationService;
	}

	private AffectationService _affectationService;

}