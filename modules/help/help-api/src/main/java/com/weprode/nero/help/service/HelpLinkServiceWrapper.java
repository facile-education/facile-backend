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
 * Provides a wrapper for {@link HelpLinkService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpLinkService
 * @generated
 */
public class HelpLinkServiceWrapper
	implements HelpLinkService, ServiceWrapper<HelpLinkService> {

	public HelpLinkServiceWrapper() {
		this(null);
	}

	public HelpLinkServiceWrapper(HelpLinkService helpLinkService) {
		_helpLinkService = helpLinkService;
	}

	@Override
	public org.json.JSONObject deleteLink(long linkId) {
		return _helpLinkService.deleteLink(linkId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpLinkService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject saveLink(String link) {
		return _helpLinkService.saveLink(link);
	}

	@Override
	public HelpLinkService getWrappedService() {
		return _helpLinkService;
	}

	@Override
	public void setWrappedService(HelpLinkService helpLinkService) {
		_helpLinkService = helpLinkService;
	}

	private HelpLinkService _helpLinkService;

}