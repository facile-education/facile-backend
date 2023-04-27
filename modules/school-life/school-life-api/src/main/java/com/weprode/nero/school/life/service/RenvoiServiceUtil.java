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

package com.weprode.nero.school.life.service;

/**
 * Provides the remote service utility for Renvoi. This utility wraps
 * <code>com.weprode.nero.school.life.service.impl.RenvoiServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see RenvoiService
 * @generated
 */
public class RenvoiServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.school.life.service.impl.RenvoiServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject
		getCandidateSessions(long schoollifeSessionId, long studentId) {

		return getService().getCandidateSessions(
			schoollifeSessionId, studentId);
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
		getPendingRenvois() {

		return getService().getPendingRenvois();
	}

	public static com.liferay.portal.kernel.json.JSONObject
		registerStudentRenvoi(
			long schoollifeSessionId, long sourceTeacherId, long studentId,
			long sourceSessionId, long sourceSchoollifeSessionId,
			java.lang.String registrationDate) {

		return getService().registerStudentRenvoi(
			schoollifeSessionId, sourceTeacherId, studentId, sourceSessionId,
			sourceSchoollifeSessionId, registrationDate);
	}

	public static com.liferay.portal.kernel.json.JSONObject setRenvoiReason(
		long schoollifeSessionId, long studentId, java.lang.String reason) {

		return getService().setRenvoiReason(
			schoollifeSessionId, studentId, reason);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		unregisterStudentRenvoi(long schoollifeSessionId, long studentId) {

		return getService().unregisterStudentRenvoi(
			schoollifeSessionId, studentId);
	}

	public static RenvoiService getService() {
		return _service;
	}

	private static volatile RenvoiService _service;

}