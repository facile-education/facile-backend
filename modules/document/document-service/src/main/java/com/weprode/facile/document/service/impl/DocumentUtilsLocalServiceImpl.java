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

package com.weprode.facile.document.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.document.service.base.DocumentUtilsLocalServiceBaseImpl;

import com.weprode.facile.document.utils.DocumentUtil;
import com.weprode.facile.document.utils.ENTWebDAVUtil;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
	property = "model.class.name=com.weprode.facile.document.model.DocumentUtils",
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

	// Only here to export to others modules (like News module for example)
	public boolean belongToTmpFolder (FileEntry fileEntry, long userId) throws PortalException {
		return DocumentUtil.belongToTmpFolder(fileEntry, userId);
	}
}