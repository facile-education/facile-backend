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

package com.weprode.facile.help.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HelpRelationService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpRelationService
 * @generated
 */
public class HelpRelationServiceWrapper
	implements HelpRelationService, ServiceWrapper<HelpRelationService> {

	public HelpRelationServiceWrapper() {
		this(null);
	}

	public HelpRelationServiceWrapper(HelpRelationService helpRelationService) {
		_helpRelationService = helpRelationService;
	}

	@Override
	public org.json.JSONObject deleteRelation(long relationId) {
		return _helpRelationService.deleteRelation(relationId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpRelationService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject saveRelation(String relation) {
		return _helpRelationService.saveRelation(relation);
	}

	@Override
	public HelpRelationService getWrappedService() {
		return _helpRelationService;
	}

	@Override
	public void setWrappedService(HelpRelationService helpRelationService) {
		_helpRelationService = helpRelationService;
	}

	private HelpRelationService _helpRelationService;

}