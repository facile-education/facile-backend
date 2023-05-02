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
 * Provides a wrapper for {@link ProgressionItemService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItemService
 * @generated
 */
public class ProgressionItemServiceWrapper
	implements ProgressionItemService, ServiceWrapper<ProgressionItemService> {

	public ProgressionItemServiceWrapper(
		ProgressionItemService progressionItemService) {

		_progressionItemService = progressionItemService;
	}

	@Override
	public org.json.JSONObject addItem(
		long progressionId, long folderId, boolean isHomework) {

		return _progressionItemService.addItem(
			progressionId, folderId, isHomework);
	}

	@Override
	public org.json.JSONObject addItemContent(
		long itemId, int contentType, String contentName, String fileName,
		java.io.File file) {

		return _progressionItemService.addItemContent(
			itemId, contentType, contentName, fileName, file);
	}

	@Override
	public org.json.JSONObject addItemContent(
		long itemId, int contentType, String contentName, String contentValue,
		long fileEntryId, boolean isToBeCompleted) {

		return _progressionItemService.addItemContent(
			itemId, contentType, contentName, contentValue, fileEntryId,
			isToBeCompleted);
	}

	@Override
	public org.json.JSONObject deleteItem(long itemId) {
		return _progressionItemService.deleteItem(itemId);
	}

	@Override
	public org.json.JSONObject deleteItemContent(long contentId) {
		return _progressionItemService.deleteItemContent(contentId);
	}

	@Override
	public org.json.JSONObject getHomeworkSpecificContents(long homeworkId) {
		return _progressionItemService.getHomeworkSpecificContents(homeworkId);
	}

	@Override
	public org.json.JSONObject getItemContents(long itemId) {
		return _progressionItemService.getItemContents(itemId);
	}

	@Override
	public org.json.JSONObject getItemPreview(long itemId) {
		return _progressionItemService.getItemPreview(itemId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _progressionItemService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSessionSpecificContents(long sessionId) {
		return _progressionItemService.getSessionSpecificContents(sessionId);
	}

	@Override
	public org.json.JSONObject saveHomeworkSpecificItem(long homeworkId) {
		return _progressionItemService.saveHomeworkSpecificItem(homeworkId);
	}

	@Override
	public org.json.JSONObject saveSessionSpecificItem(long sessionId) {
		return _progressionItemService.saveSessionSpecificItem(sessionId);
	}

	@Override
	public org.json.JSONObject updateItem(
		long itemId, long folderId, String name, int type, String duration,
		int order) {

		return _progressionItemService.updateItem(
			itemId, folderId, name, type, duration, order);
	}

	@Override
	public org.json.JSONObject updateItemContent(
		long contentId, String contentName, String contentValue, int order) {

		return _progressionItemService.updateItemContent(
			contentId, contentName, contentValue, order);
	}

	@Override
	public ProgressionItemService getWrappedService() {
		return _progressionItemService;
	}

	@Override
	public void setWrappedService(
		ProgressionItemService progressionItemService) {

		_progressionItemService = progressionItemService;
	}

	private ProgressionItemService _progressionItemService;

}