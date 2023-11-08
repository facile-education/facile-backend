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

package com.weprode.facile.authentication.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.authentication.service.base.AuthenticationServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=authentication",
		"json.web.service.context.path=Authentication"
	},
	service = AopService.class
)
public class AuthenticationServiceImpl extends AuthenticationServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(AuthenticationServiceImpl.class);

	// Uncomment for local testing only
//	@JSONWebService(value = "auth-log", method = "GET")
//	public JSONObject authLog(String str) {
//		JSONObject result = new JSONObject();
//
//		logger.info("authLog : " + str);
//		result.put("success", true);
//
//		return result;
//	}

}