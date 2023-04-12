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

package com.weprode.nero.document.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.document.model.LoolToken;
import com.weprode.nero.document.service.base.LoolTokenLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=com.weprode.nero.document.model.LoolToken",
	service = AopService.class
)
public class LoolTokenLocalServiceImpl extends LoolTokenLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(LoolTokenLocalServiceImpl.class);

	/**
	 * Creates a LoolToken with gisven userID and token
	 */
	public LoolToken createLoolToken (long userId, String token) {
		try {
			long loolTokenId = counterLocalService.increment();
			LoolToken loolToken = loolTokenPersistence.create(loolTokenId);
			loolToken.setToken(token);
			loolToken.setUserId(userId);
			logger.info("Created LoolToken with userId "+userId+" and token "+token);

			return loolTokenPersistence.update(loolToken);
		} catch (Exception e) {
			logger.error("Error when creating a lool token for userId "+userId+" and token "+token, e);
		}

		return null;
	}

	/**
	 * Returns a LoolToken with given token
	 */
	public LoolToken getLoolToken (String token) {
		try {
			return loolTokenPersistence.findBytoken(token);
		} catch (Exception e) {
			logger.error("Error when getting lool token for token "+token);
		}

		return null;
	}

}