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

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.document.service.base.DocumentUtilsLocalServiceBaseImpl;

import com.weprode.nero.document.utils.ENTWebDAVUtil;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.document.model.DocumentUtils",
	service = AopService.class
)
public class DocumentUtilsLocalServiceImpl
	extends DocumentUtilsLocalServiceBaseImpl {

	public boolean isEmbedUrlWhitelisted (String url) {
		List<String> domainWhitelist = List.of(
				PropsUtil.get(NeroSystemProperties.XSS_IFRAME_WHITELIST).split(","));

		for (String domain : domainWhitelist) {
			if (url.matches("^"+domain+".*$")) {
				return true;
			}
		}
		return false;
	}

	public String getWebDavUrl (User user) {
		return ENTWebDAVUtil.getWebDavUrl(user);
	}
}