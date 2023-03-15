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

package com.weprode.nero.eel.synchronization.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SubjectService}.
 *
 * @author Brian Wing Shun Chan
 * @see SubjectService
 * @generated
 */
public class SubjectServiceWrapper
	implements ServiceWrapper<SubjectService>, SubjectService {

	public SubjectServiceWrapper(SubjectService subjectService) {
		_subjectService = subjectService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _subjectService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getSubjects() {
		return _subjectService.getSubjects();
	}

	@Override
	public SubjectService getWrappedService() {
		return _subjectService;
	}

	@Override
	public void setWrappedService(SubjectService subjectService) {
		_subjectService = subjectService;
	}

	private SubjectService _subjectService;

}