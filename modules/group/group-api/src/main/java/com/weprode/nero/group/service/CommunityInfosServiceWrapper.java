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

package com.weprode.nero.group.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommunityInfosService}.
 *
 * @author Brian Wing Shun Chan
 * @see CommunityInfosService
 * @generated
 */
public class CommunityInfosServiceWrapper
	implements CommunityInfosService, ServiceWrapper<CommunityInfosService> {

	public CommunityInfosServiceWrapper() {
		this(null);
	}

	public CommunityInfosServiceWrapper(
		CommunityInfosService communityInfosService) {

		_communityInfosService = communityInfosService;
	}

	@Override
	public org.json.JSONObject checkCommunityName(String communityName) {
		return _communityInfosService.checkCommunityName(communityName);
	}

	@Override
	public org.json.JSONObject createCommunity(
		String groupName, String description, boolean isPedagogical,
		String members, String color) {

		return _communityInfosService.createCommunity(
			groupName, description, isPedagogical, members, color);
	}

	@Override
	public org.json.JSONObject editCommunity(
		long groupId, String groupName, String description,
		boolean isPedagogical, String members, String color) {

		return _communityInfosService.editCommunity(
			groupId, groupName, description, isPedagogical, members, color);
	}

	@Override
	public org.json.JSONObject extendCommunity(long groupId) {
		return _communityInfosService.extendCommunity(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _communityInfosService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject removeCommunity(long groupId) {
		return _communityInfosService.removeCommunity(groupId);
	}

	@Override
	public CommunityInfosService getWrappedService() {
		return _communityInfosService;
	}

	@Override
	public void setWrappedService(CommunityInfosService communityInfosService) {
		_communityInfosService = communityInfosService;
	}

	private CommunityInfosService _communityInfosService;

}