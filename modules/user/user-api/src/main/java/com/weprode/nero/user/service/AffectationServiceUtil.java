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

/**
 * Provides the remote service utility for Affectation. This utility wraps
 * <code>com.weprode.nero.user.service.impl.AffectationServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AffectationService
 * @generated
 */
public class AffectationServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.user.service.impl.AffectationServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject addUserAffectation(
		long userId, long orgId, java.lang.String expirationDate) {

		return getService().addUserAffectation(userId, orgId, expirationDate);
	}

	public static com.liferay.portal.kernel.json.JSONObject getAffectedUsers(
		long schoolId, java.lang.String filter) {

		return getService().getAffectedUsers(schoolId, filter);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject
		removeUserAffectation(long userId, long orgId) {

		return getService().removeUserAffectation(userId, orgId);
	}

	public static AffectationService getService() {
		return _service;
	}

	private static volatile AffectationService _service;

}