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
 * Provides a wrapper for {@link GroupsService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupsService
 * @generated
 */
public class GroupsServiceWrapper
	implements GroupsService, ServiceWrapper<GroupsService> {

	public GroupsServiceWrapper() {
		this(null);
	}

	public GroupsServiceWrapper(GroupsService groupsService) {
		_groupsService = groupsService;
	}

	@Override
	public org.json.JSONObject getGroupBreadcrumb(String nodePath) {
		return _groupsService.getGroupBreadcrumb(nodePath);
	}

	@Override
	public org.json.JSONObject getGroupEntities(String nodePath) {
		return _groupsService.getGroupEntities(nodePath);
	}

	@Override
	public org.json.JSONObject getGroupImages(String nodePath) {
		return _groupsService.getGroupImages(nodePath);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _groupsService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject recordDownloadActivity(
		long fileEntryId, long versionId) {

		return _groupsService.recordDownloadActivity(fileEntryId, versionId);
	}

	@Override
	public org.json.JSONObject recordViewActivity(
		long fileEntryId, long versionId) {

		return _groupsService.recordViewActivity(fileEntryId, versionId);
	}

	@Override
	public GroupsService getWrappedService() {
		return _groupsService;
	}

	@Override
	public void setWrappedService(GroupsService groupsService) {
		_groupsService = groupsService;
	}

	private GroupsService _groupsService;

}