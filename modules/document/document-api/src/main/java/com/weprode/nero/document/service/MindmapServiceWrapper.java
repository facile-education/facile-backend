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

package com.weprode.nero.document.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MindmapService}.
 *
 * @author Brian Wing Shun Chan
 * @see MindmapService
 * @generated
 */
public class MindmapServiceWrapper
	implements MindmapService, ServiceWrapper<MindmapService> {

	public MindmapServiceWrapper() {
		this(null);
	}

	public MindmapServiceWrapper(MindmapService mindmapService) {
		_mindmapService = mindmapService;
	}

	/**
	 * Returns the content of the given mindmap file
	 *
	 * @return JSONObject - the mindmap file name and content
	 */
	@Override
	public org.json.JSONObject getMindFile(long fileVersionId) {
		return _mindmapService.getMindFile(fileVersionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _mindmapService.getOSGiServiceIdentifier();
	}

	/**
	 * This method saves a mindmap file
	 *
	 * @param params - The map containing fileVersionId, fileName and content
	 * @return JSONObject success or not
	 */
	@Override
	public org.json.JSONObject saveMindFile(String params)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _mindmapService.saveMindFile(params);
	}

	@Override
	public MindmapService getWrappedService() {
		return _mindmapService;
	}

	@Override
	public void setWrappedService(MindmapService mindmapService) {
		_mindmapService = mindmapService;
	}

	private MindmapService _mindmapService;

}