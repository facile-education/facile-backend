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
import com.weprode.facile.mobile.model.UserMobileToken;
import com.weprode.facile.mobile.service.base.UserMobileTokenLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.UUID;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.facile.mobile.model.UserMobileToken",
	service = AopService.class
)
public class UserMobileTokenLocalServiceImpl extends UserMobileTokenLocalServiceBaseImpl {

	public String addMobileToken(long userId) throws SystemException {

		UserMobileToken userMobileToken = null;
		try {
			userMobileToken = userMobileTokenPersistence.findByPrimaryKey(userId);
		} catch (Exception e) {
			// Nothing
		}
		if (userMobileToken == null) {
			userMobileToken = userMobileTokenPersistence.create(userId);
		}
		String newToken = UUID.randomUUID().toString();
		userMobileToken.setMobileToken(newToken);
		userMobileTokenPersistence.update(userMobileToken);
		return newToken;
	}

	public UserMobileToken addMobileToken(long userId, String token) throws SystemException {

		UserMobileToken userMobileToken = null;
		try {
			userMobileToken = userMobileTokenPersistence.create(userId);
			userMobileToken.setMobileToken(token);
			userMobileToken = userMobileTokenPersistence.update(userMobileToken);
			return userMobileToken;
		} catch (Exception e) {
			// Nothing
		}
		return null;
	}

	public String refreshMobileToken(long userId, String mobileToken) throws SystemException {

		UserMobileToken userMobileToken = null;
		try {
			userMobileToken = userMobileTokenPersistence.findByPrimaryKey(userId);
		} catch (Exception e) {
			// Nothing
		}
		if (userMobileToken != null && userMobileToken.getMobileToken().equals(mobileToken)) {
			String newToken = UUID.randomUUID().toString();
			userMobileToken.setMobileToken(newToken);
			userMobileTokenPersistence.update(userMobileToken);
			return newToken;
		}
		return "";
	}

	public boolean hasUserMobileToken(long userId, String mobileToken) throws SystemException {
		UserMobileToken userMobileToken = null;
		try {
			userMobileToken = userMobileTokenPersistence.findByPrimaryKey(userId);
		} catch (Exception e) {
			// Nothing
		}
		return userMobileToken != null && userMobileToken.getMobileToken().equals(mobileToken);
	}

	public UserMobileToken getTokenUser(String mobileToken) throws SystemException {
		UserMobileToken userMobileToken = null;
		try {
			userMobileToken = userMobileTokenPersistence.findBymobileToken(mobileToken);
		} catch (Exception e) {
			// Nothing
		}
		return userMobileToken;
	}

}