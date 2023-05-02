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

package com.weprode.nero.progression.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProgressionService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionService
 * @generated
 */
public class ProgressionServiceWrapper
	implements ProgressionService, ServiceWrapper<ProgressionService> {

	public ProgressionServiceWrapper(ProgressionService progressionService) {
		_progressionService = progressionService;
	}

	@Override
	public org.json.JSONObject addProgression(
		String name, String description, long subjectId, String volee,
		String color) {

		return _progressionService.addProgression(
			name, description, subjectId, volee, color);
	}

	@Override
	public org.json.JSONObject deleteProgression(long progressionId) {
		return _progressionService.deleteProgression(progressionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _progressionService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getProgressionList() {
		return _progressionService.getProgressionList();
	}

	@Override
	public org.json.JSONObject getProgressionTree(long progressionId) {
		return _progressionService.getProgressionTree(progressionId);
	}

	@Override
	public org.json.JSONObject updateProgression(
		long progressionId, String name, String description, long subjectId,
		String volee, String color) {

		return _progressionService.updateProgression(
			progressionId, name, description, subjectId, volee, color);
	}

	@Override
	public ProgressionService getWrappedService() {
		return _progressionService;
	}

	@Override
	public void setWrappedService(ProgressionService progressionService) {
		_progressionService = progressionService;
	}

	private ProgressionService _progressionService;

}