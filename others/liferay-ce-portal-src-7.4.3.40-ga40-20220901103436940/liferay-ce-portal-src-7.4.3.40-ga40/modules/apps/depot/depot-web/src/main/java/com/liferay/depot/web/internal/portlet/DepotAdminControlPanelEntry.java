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

package com.liferay.depot.web.internal.portlet;

import com.liferay.depot.web.internal.constants.DepotPortletKeys;
import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Tardín
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + DepotPortletKeys.DEPOT_ADMIN,
	service = ControlPanelEntry.class
)
public class DepotAdminControlPanelEntry extends BaseControlPanelEntry {
}