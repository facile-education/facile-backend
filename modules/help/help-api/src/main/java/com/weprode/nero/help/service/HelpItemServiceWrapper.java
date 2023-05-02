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

package com.weprode.nero.help.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HelpItemService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpItemService
 * @generated
 */
public class HelpItemServiceWrapper
	implements HelpItemService, ServiceWrapper<HelpItemService> {

	public HelpItemServiceWrapper(HelpItemService helpItemService) {
		_helpItemService = helpItemService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject deleteItem(long itemId) {
		return _helpItemService.deleteItem(itemId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getHelpItemDetails(
		long itemId) {

		return _helpItemService.getHelpItemDetails(itemId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpItemService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject saveHelpItem(
		long categoryId, String item) {

		return _helpItemService.saveHelpItem(categoryId, item);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject saveHelpItemPosition(
		long categoryId, String item) {

		return _helpItemService.saveHelpItemPosition(categoryId, item);
	}

	@Override
	public HelpItemService getWrappedService() {
		return _helpItemService;
	}

	@Override
	public void setWrappedService(HelpItemService helpItemService) {
		_helpItemService = helpItemService;
	}

	private HelpItemService _helpItemService;

}