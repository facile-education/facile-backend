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

package com.weprode.nero.mobile.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.mobile.model.MobileDevice;
import com.weprode.nero.mobile.service.base.MobileDeviceLocalServiceBaseImpl;
import com.weprode.nero.mobile.utils.ThreadMobileNotification;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.mobile.model.MobileDevice",
	service = AopService.class
)
public class MobileDeviceLocalServiceImpl extends MobileDeviceLocalServiceBaseImpl {

	/**
	 * Add new mobile device for user
	 */
	public MobileDevice addMobileDevice(String manufacturerDeviceId, long userId, String model, String manufacturer, String os, String osVersion, String browserUA) throws SystemException {

		final MobileDevice mobileDevice = mobileDevicePersistence.create(counterLocalService.increment());

		mobileDevice.setManufacturerDeviceId(manufacturerDeviceId);
		mobileDevice.setUserId(userId);
		mobileDevice.setDeviceModel(model);
		mobileDevice.setManufacturer(manufacturer);
		mobileDevice.setOperatingSystem(os);
		mobileDevice.setOperatingSystemVersion(osVersion);
		mobileDevice.setBrowserUA(browserUA);

		return mobileDevicePersistence.update(mobileDevice);
	}

	public List<MobileDevice> getUserMobileDevices(long userId) throws SystemException {
		return mobileDevicePersistence.findByuserId(userId);
	}

	public MobileDevice getMobileDeviceByManufacturerDeviceId(String manufacturerDeviceId) throws SystemException {
		return mobileDevicePersistence.fetchBymanufacturerDeviceId(manufacturerDeviceId);
	}

	public void pushNotificationToUser(long userId, String title, String subtitle, String message, String service, long paramId) {

		boolean isMobileNotificationEnabled = Boolean.parseBoolean(PropsUtil.get(NeroSystemProperties.MOBILE_NOTIFICATIONS_ENABLED));
		if (isMobileNotificationEnabled) {
			ThreadMobileNotification notifThread = new ThreadMobileNotification(userId, title, subtitle, message, service, paramId);
			notifThread.start();
		}
	}

	public void pushNotificationToGroup(List<Long> groupIds, long senderId, String title, String subtitle, String message, String service, long paramId) {

		boolean isMobileNotificationEnabled = Boolean.parseBoolean(PropsUtil.get(NeroSystemProperties.MOBILE_NOTIFICATIONS_ENABLED));
		if (isMobileNotificationEnabled) {
			ThreadMobileNotification notifThread = new ThreadMobileNotification(groupIds, senderId, title, subtitle, message, service, paramId);
			notifThread.start();
		}
	}

}