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

package com.weprode.facile.course.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContentBlockService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlockService
 * @generated
 */
public class ContentBlockServiceWrapper
	implements ContentBlockService, ServiceWrapper<ContentBlockService> {

	public ContentBlockServiceWrapper() {
		this(null);
	}

	public ContentBlockServiceWrapper(ContentBlockService contentBlockService) {
		_contentBlockService = contentBlockService;
	}

	@Override
	public org.json.JSONObject addBlock(
		long itemId, int blockType, String blockName, String blockValue,
		long fileEntryId) {

		return _contentBlockService.addBlock(
			itemId, blockType, blockName, blockValue, fileEntryId);
	}

	@Override
	public org.json.JSONObject addFileBlock(
		long itemId, int blockType, String blockName, String fileName,
		java.io.File file) {

		return _contentBlockService.addFileBlock(
			itemId, blockType, blockName, fileName, file);
	}

	@Override
	public org.json.JSONObject deleteBlock(long blockId) {
		return _contentBlockService.deleteBlock(blockId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contentBlockService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject updateBlock(
		long blockId, String blockName, String blockValue, int order) {

		return _contentBlockService.updateBlock(
			blockId, blockName, blockValue, order);
	}

	@Override
	public ContentBlockService getWrappedService() {
		return _contentBlockService;
	}

	@Override
	public void setWrappedService(ContentBlockService contentBlockService) {
		_contentBlockService = contentBlockService;
	}

	private ContentBlockService _contentBlockService;

}