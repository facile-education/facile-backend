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

package com.weprode.facile.document.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TrashService}.
 *
 * @author Brian Wing Shun Chan
 * @see TrashService
 * @generated
 */
public class TrashServiceWrapper
	implements ServiceWrapper<TrashService>, TrashService {

	public TrashServiceWrapper() {
		this(null);
	}

	public TrashServiceWrapper(TrashService trashService) {
		_trashService = trashService;
	}

	@Override
	public org.json.JSONObject deleteDocuments(
		String folderIdArray, String fileIdArray) {

		return _trashService.deleteDocuments(folderIdArray, fileIdArray);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _trashService.getOSGiServiceIdentifier();
	}

	@Override
	public TrashService getWrappedService() {
		return _trashService;
	}

	@Override
	public void setWrappedService(TrashService trashService) {
		_trashService = trashService;
	}

	private TrashService _trashService;

}