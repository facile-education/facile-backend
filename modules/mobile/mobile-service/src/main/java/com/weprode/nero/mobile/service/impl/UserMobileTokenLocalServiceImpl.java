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
import com.weprode.nero.mobile.model.UserMobileToken;
import com.weprode.nero.mobile.service.base.UserMobileTokenLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.UUID;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.mobile.model.UserMobileToken",
	service = AopService.class
)
public class UserMobileTokenLocalServiceImpl extends UserMobileTokenLocalServiceBaseImpl {

	public String addMobileToken(long userId) throws SystemException {

		UserMobileToken userMobileToken = userMobileTokenPersistence.create(counterLocalService.increment());

		userMobileToken.setUserId(userId);
		String newToken = UUID.randomUUID().toString();
		userMobileToken.setMobileToken(newToken);
		userMobileTokenPersistence.update(userMobileToken);
		return newToken;
	}

	public String refreshMobileToken(long userId, String mobileToken) throws SystemException {
		UserMobileToken userMobileToken = userMobileTokenPersistence.fetchByuserId_mobileToken(userId, mobileToken);
		if (userMobileToken != null) {
			String newToken = UUID.randomUUID().toString();
			userMobileToken.setMobileToken(newToken);
			userMobileTokenPersistence.update(userMobileToken);
			return newToken;
		}
		return "";
	}

	public boolean hasUserMobileToken(long userId, String mobileToken) throws SystemException {
		return userMobileTokenPersistence.fetchByuserId_mobileToken(userId, mobileToken) != null;
	}

}