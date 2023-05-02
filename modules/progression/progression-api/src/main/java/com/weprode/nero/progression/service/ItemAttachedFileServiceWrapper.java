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
 * Provides a wrapper for {@link ItemAttachedFileService}.
 *
 * @author Brian Wing Shun Chan
 * @see ItemAttachedFileService
 * @generated
 */
public class ItemAttachedFileServiceWrapper
	implements ItemAttachedFileService,
			   ServiceWrapper<ItemAttachedFileService> {

	public ItemAttachedFileServiceWrapper(
		ItemAttachedFileService itemAttachedFileService) {

		_itemAttachedFileService = itemAttachedFileService;
	}

	@Override
	public org.json.JSONObject addAttachment(
		long itemId, String fileName, java.io.File file,
		boolean isToBeCompleted) {

		return _itemAttachedFileService.addAttachment(
			itemId, fileName, file, isToBeCompleted);
	}

	@Override
	public org.json.JSONObject deleteAttachment(long attachmentId) {
		return _itemAttachedFileService.deleteAttachment(attachmentId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _itemAttachedFileService.getOSGiServiceIdentifier();
	}

	@Override
	public ItemAttachedFileService getWrappedService() {
		return _itemAttachedFileService;
	}

	@Override
	public void setWrappedService(
		ItemAttachedFileService itemAttachedFileService) {

		_itemAttachedFileService = itemAttachedFileService;
	}

	private ItemAttachedFileService _itemAttachedFileService;

}