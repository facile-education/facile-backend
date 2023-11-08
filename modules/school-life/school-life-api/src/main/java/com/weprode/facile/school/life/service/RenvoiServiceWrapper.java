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

package com.weprode.facile.school.life.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RenvoiService}.
 *
 * @author Brian Wing Shun Chan
 * @see RenvoiService
 * @generated
 */
public class RenvoiServiceWrapper
	implements RenvoiService, ServiceWrapper<RenvoiService> {

	public RenvoiServiceWrapper() {
		this(null);
	}

	public RenvoiServiceWrapper(RenvoiService renvoiService) {
		_renvoiService = renvoiService;
	}

	@Override
	public org.json.JSONObject getCandidateSessions(
		long schoollifeSessionId, long studentId) {

		return _renvoiService.getCandidateSessions(
			schoollifeSessionId, studentId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _renvoiService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getPendingRenvois() {
		return _renvoiService.getPendingRenvois();
	}

	@Override
	public org.json.JSONObject registerStudentRenvoi(
		long schoollifeSessionId, long sourceTeacherId, long studentId,
		long sourceSessionId, long sourceSchoollifeSessionId,
		String registrationDate) {

		return _renvoiService.registerStudentRenvoi(
			schoollifeSessionId, sourceTeacherId, studentId, sourceSessionId,
			sourceSchoollifeSessionId, registrationDate);
	}

	@Override
	public org.json.JSONObject setRenvoiReason(
		long schoollifeSessionId, long studentId, String reason) {

		return _renvoiService.setRenvoiReason(
			schoollifeSessionId, studentId, reason);
	}

	@Override
	public org.json.JSONObject unregisterStudentRenvoi(
		long schoollifeSessionId, long studentId) {

		return _renvoiService.unregisterStudentRenvoi(
			schoollifeSessionId, studentId);
	}

	@Override
	public RenvoiService getWrappedService() {
		return _renvoiService;
	}

	@Override
	public void setWrappedService(RenvoiService renvoiService) {
		_renvoiService = renvoiService;
	}

	private RenvoiService _renvoiService;

}