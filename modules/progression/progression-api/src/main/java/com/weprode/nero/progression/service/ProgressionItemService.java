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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.File;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for ProgressionItem. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItemServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ProgressionItemService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.progression.service.impl.ProgressionItemServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the progression item remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ProgressionItemServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(method = "POST", value = "add-item")
	public JSONObject addItem(
		long progressionId, long folderId, boolean isHomework);

	@JSONWebService(method = "POST", value = "add-item-content")
	public JSONObject addItemContent(
		long itemId, int contentType, String contentName, String fileName,
		File file);

	@JSONWebService(method = "POST", value = "add-item-content")
	public JSONObject addItemContent(
		long itemId, int contentType, String contentName, String contentValue,
		long fileEntryId, boolean isToBeCompleted);

	@JSONWebService(method = "DELETE", value = "delete-item")
	public JSONObject deleteItem(long itemId);

	@JSONWebService(method = "DELETE", value = "delete-item-content")
	public JSONObject deleteItemContent(long contentId);

	@JSONWebService(method = "GET", value = "get-homework-specific-contents")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getHomeworkSpecificContents(long homeworkId);

	@JSONWebService(method = "GET", value = "get-item-contents")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getItemContents(long itemId);

	@JSONWebService(method = "GET", value = "get-item-preview")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getItemPreview(long itemId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "GET", value = "get-session-specific-contents")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getSessionSpecificContents(long sessionId);

	@JSONWebService(method = "POST", value = "save-homework-specific-item")
	public JSONObject saveHomeworkSpecificItem(long homeworkId);

	@JSONWebService(method = "POST", value = "save-session-specific-item")
	public JSONObject saveSessionSpecificItem(long sessionId);

	@JSONWebService(method = "POST", value = "update-item")
	public JSONObject updateItem(
		long itemId, long folderId, String name, int type, String duration,
		int order);

	@JSONWebService(method = "POST", value = "update-item-content")
	public JSONObject updateItemContent(
		long contentId, String contentName, String contentValue, int order);

}