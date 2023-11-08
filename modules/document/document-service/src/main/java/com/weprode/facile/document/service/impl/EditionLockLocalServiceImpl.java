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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.document.model.EditionLock;
import com.weprode.facile.document.service.base.EditionLockLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.Date;

@Component(
	property = "model.class.name=com.weprode.facile.document.model.EditionLock",
	service = AopService.class
)
public class EditionLockLocalServiceImpl
	extends EditionLockLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(EditionLockLocalServiceImpl.class);

	public EditionLock addEditionLock(long fileId, long userId) throws SystemException {
		EditionLock editionLock = editionLockPersistence.create(fileId);

		editionLock.setUserId(userId);
		editionLock.setEditionDate(new Date());
		editionLockPersistence.update(editionLock);

		return editionLock;
	}

	public EditionLock isFileEdited(Long fileId) throws SystemException {
		return editionLockPersistence.fetchByPrimaryKey(fileId);
	}

	public boolean removeEditionLock(long fileId) {
		try {
			editionLockPersistence.remove(fileId);

			return true;
		} catch (Exception e) {
			logger.error("Error when removing EditionLock "+fileId);
		}

		return false;
	}

}