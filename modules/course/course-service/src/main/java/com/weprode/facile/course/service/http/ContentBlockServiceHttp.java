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

package com.weprode.facile.course.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.facile.course.service.ContentBlockServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>ContentBlockServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContentBlockServiceHttp {

	public static org.json.JSONObject addBlock(
		HttpPrincipal httpPrincipal, long itemId, int blockType,
		String blockName, String blockValue, long fileEntryId) {

		try {
			MethodKey methodKey = new MethodKey(
				ContentBlockServiceUtil.class, "addBlock",
				_addBlockParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, itemId, blockType, blockName, blockValue,
				fileEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject addFileBlock(
		HttpPrincipal httpPrincipal, long itemId, int blockType,
		String blockName, String fileName, java.io.File file) {

		try {
			MethodKey methodKey = new MethodKey(
				ContentBlockServiceUtil.class, "addFileBlock",
				_addFileBlockParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, itemId, blockType, blockName, fileName, file);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject updateBlock(
		HttpPrincipal httpPrincipal, long blockId, String blockName,
		String blockValue, int order) {

		try {
			MethodKey methodKey = new MethodKey(
				ContentBlockServiceUtil.class, "updateBlock",
				_updateBlockParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, blockId, blockName, blockValue, order);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject deleteBlock(
		HttpPrincipal httpPrincipal, long blockId) {

		try {
			MethodKey methodKey = new MethodKey(
				ContentBlockServiceUtil.class, "deleteBlock",
				_deleteBlockParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, blockId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject isEmbedUrlWhitelisted(
		HttpPrincipal httpPrincipal, String url) {

		try {
			MethodKey methodKey = new MethodKey(
				ContentBlockServiceUtil.class, "isEmbedUrlWhitelisted",
				_isEmbedUrlWhitelistedParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, url);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ContentBlockServiceHttp.class);

	private static final Class<?>[] _addBlockParameterTypes0 = new Class[] {
		long.class, int.class, String.class, String.class, long.class
	};
	private static final Class<?>[] _addFileBlockParameterTypes1 = new Class[] {
		long.class, int.class, String.class, String.class, java.io.File.class
	};
	private static final Class<?>[] _updateBlockParameterTypes2 = new Class[] {
		long.class, String.class, String.class, int.class
	};
	private static final Class<?>[] _deleteBlockParameterTypes3 = new Class[] {
		long.class
	};
	private static final Class<?>[] _isEmbedUrlWhitelistedParameterTypes4 =
		new Class[] {String.class};

}