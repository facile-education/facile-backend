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
 * Provides a wrapper for {@link HelpCategoryService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpCategoryService
 * @generated
 */
public class HelpCategoryServiceWrapper
	implements HelpCategoryService, ServiceWrapper<HelpCategoryService> {

	public HelpCategoryServiceWrapper() {
		this(null);
	}

	public HelpCategoryServiceWrapper(HelpCategoryService helpCategoryService) {
		_helpCategoryService = helpCategoryService;
	}

	@Override
	public org.json.JSONObject deleteCategory(long categoryId) {
		return _helpCategoryService.deleteCategory(categoryId);
	}

	@Override
	public org.json.JSONObject getHelpMenu(String search) {
		return _helpCategoryService.getHelpMenu(search);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpCategoryService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject saveHelpCategory(
		String categoryName, long serviceId) {

		return _helpCategoryService.saveHelpCategory(categoryName, serviceId);
	}

	@Override
	public HelpCategoryService getWrappedService() {
		return _helpCategoryService;
	}

	@Override
	public void setWrappedService(HelpCategoryService helpCategoryService) {
		_helpCategoryService = helpCategoryService;
	}

	private HelpCategoryService _helpCategoryService;

}