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

package com.weprode.facile.course.service;

/**
 * Provides the remote service utility for ContentBlock. This utility wraps
 * <code>com.weprode.facile.course.service.impl.ContentBlockServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlockService
 * @generated
 */
public class ContentBlockServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.course.service.impl.ContentBlockServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject addBlock(
		long itemId, int blockType, java.lang.String blockName,
		java.lang.String blockValue, long fileEntryId) {

		return getService().addBlock(
			itemId, blockType, blockName, blockValue, fileEntryId);
	}

	public static org.json.JSONObject addFileBlock(
		long itemId, int blockType, java.lang.String blockName,
		java.lang.String fileName, java.io.File file) {

		return getService().addFileBlock(
			itemId, blockType, blockName, fileName, file);
	}

	public static org.json.JSONObject deleteBlock(long blockId) {
		return getService().deleteBlock(blockId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject isEmbedUrlWhitelisted(
		java.lang.String url) {

		return getService().isEmbedUrlWhitelisted(url);
	}

	public static org.json.JSONObject isValidUrl(java.lang.String url) {
		return getService().isValidUrl(url);
	}

	public static org.json.JSONObject updateBlock(
		long blockId, java.lang.String blockName, java.lang.String blockValue,
		int order) {

		return getService().updateBlock(blockId, blockName, blockValue, order);
	}

	public static ContentBlockService getService() {
		return _service;
	}

	private static volatile ContentBlockService _service;

}