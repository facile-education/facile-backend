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
 * Provides a wrapper for {@link ItemAssignmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see ItemAssignmentService
 * @generated
 */
public class ItemAssignmentServiceWrapper
	implements ItemAssignmentService, ServiceWrapper<ItemAssignmentService> {

	public ItemAssignmentServiceWrapper(
		ItemAssignmentService itemAssignmentService) {

		_itemAssignmentService = itemAssignmentService;
	}

	@Override
	public org.json.JSONObject addHomeworkAssignment(
		long itemId, String homeworks) {

		return _itemAssignmentService.addHomeworkAssignment(itemId, homeworks);
	}

	@Override
	public org.json.JSONObject addSessionAssignment(
		long itemId, long sessionId) {

		return _itemAssignmentService.addSessionAssignment(itemId, sessionId);
	}

	@Override
	public org.json.JSONObject deleteAssignment(long itemId, long sessionId) {
		return _itemAssignmentService.deleteAssignment(itemId, sessionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _itemAssignmentService.getOSGiServiceIdentifier();
	}

	@Override
	public ItemAssignmentService getWrappedService() {
		return _itemAssignmentService;
	}

	@Override
	public void setWrappedService(ItemAssignmentService itemAssignmentService) {
		_itemAssignmentService = itemAssignmentService;
	}

	private ItemAssignmentService _itemAssignmentService;

}