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

package com.weprode.facile.mobile.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.mobile.model.MobileDevice;
import com.weprode.facile.mobile.service.base.MobileDeviceLocalServiceBaseImpl;
import com.weprode.facile.mobile.utils.ThreadMobileNotification;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.facile.mobile.model.MobileDevice",
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