/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.dashboard;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;

import java.util.Date;

public class DashboardAccessUpdater extends Thread{

	private static final Log logger = LogFactoryUtil.getLog(DashboardAccessUpdater.class);
	private final long userId;

	public DashboardAccessUpdater(long userId) {
		this.userId = userId;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(this.userId);
			userProperties.setLastDashboardAccessDate(new Date());
			UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);
		} catch (Exception e) {
			logger.error("Error updating last dashboard access date", e);
		}
	}
	
}
