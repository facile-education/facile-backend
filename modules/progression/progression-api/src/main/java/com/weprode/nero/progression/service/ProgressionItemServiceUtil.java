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

/**
 * Provides the remote service utility for ProgressionItem. This utility wraps
 * <code>com.weprode.nero.progression.service.impl.ProgressionItemServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItemService
 * @generated
 */
public class ProgressionItemServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.progression.service.impl.ProgressionItemServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject addItem(
		long progressionId, long folderId, boolean isHomework) {

		return getService().addItem(progressionId, folderId, isHomework);
	}

	public static com.liferay.portal.kernel.json.JSONObject addItemContent(
		long itemId, int contentType, java.lang.String contentName,
		java.lang.String fileName, java.io.File file) {

		return getService().addItemContent(
			itemId, contentType, contentName, fileName, file);
	}

	public static com.liferay.portal.kernel.json.JSONObject addItemContent(
		long itemId, int contentType, java.lang.String contentName,
		java.lang.String contentValue, long fileEntryId,
		boolean isToBeCompleted) {

		return getService().addItemContent(
			itemId, contentType, contentName, contentValue, fileEntryId,
			isToBeCompleted);
	}

	public static com.liferay.portal.kernel.json.JSONObject deleteItem(
		long itemId) {

		return getService().deleteItem(itemId);
	}

	public static com.liferay.portal.kernel.json.JSONObject deleteItemContent(
		long contentId) {

		return getService().deleteItemContent(contentId);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		getHomeworkSpecificContents(long homeworkId) {

		return getService().getHomeworkSpecificContents(homeworkId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getItemContents(
		long itemId) {

		return getService().getItemContents(itemId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getItemPreview(
		long itemId) {

		return getService().getItemPreview(itemId);
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
		getSessionSpecificContents(long sessionId) {

		return getService().getSessionSpecificContents(sessionId);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		saveHomeworkSpecificItem(long homeworkId) {

		return getService().saveHomeworkSpecificItem(homeworkId);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		saveSessionSpecificItem(long sessionId) {

		return getService().saveSessionSpecificItem(sessionId);
	}

	public static com.liferay.portal.kernel.json.JSONObject updateItem(
		long itemId, long folderId, java.lang.String name, int type,
		java.lang.String duration, int order) {

		return getService().updateItem(
			itemId, folderId, name, type, duration, order);
	}

	public static com.liferay.portal.kernel.json.JSONObject updateItemContent(
		long contentId, java.lang.String contentName,
		java.lang.String contentValue, int order) {

		return getService().updateItemContent(
			contentId, contentName, contentValue, order);
	}

	public static ProgressionItemService getService() {
		return _service;
	}

	private static volatile ProgressionItemService _service;

}