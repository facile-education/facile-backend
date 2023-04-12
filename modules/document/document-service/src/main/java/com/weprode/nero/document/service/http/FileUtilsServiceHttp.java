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

package com.weprode.nero.document.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.document.service.FileUtilsServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>FileUtilsServiceUtil</code> service
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
 * @see FileUtilsServiceSoap
 * @generated
 */
public class FileUtilsServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject uploadFile(
		HttpPrincipal httpPrincipal, long folderId, String fileName,
		java.io.File file, int mode) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "uploadFile",
				_uploadFileParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, fileName, file, mode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject renameFile(
		HttpPrincipal httpPrincipal, long fileId, String fileName) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "renameFile",
				_renameFileParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileId, fileName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createAudioFile(
		HttpPrincipal httpPrincipal, long folderId, String name,
		java.io.File file) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createAudioFile",
				_createAudioFileParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, name, file);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createGeogebraFile(
		HttpPrincipal httpPrincipal, long folderId, String name) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createGeogebraFile",
				_createGeogebraFileParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, name);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createMindmapFile(
		HttpPrincipal httpPrincipal, long folderId, String name) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createMindmapFile",
				_createMindmapFileParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, name);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createScratchFile(
		HttpPrincipal httpPrincipal, long folderId, String name) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createScratchFile",
				_createScratchFileParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, name);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createLoolFile(
		HttpPrincipal httpPrincipal, long folderId, String name, String type) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createLoolFile",
				_createLoolFileParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, name, type);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createHTMLFile(
		HttpPrincipal httpPrincipal, long folderId, String name) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createHTMLFile",
				_createHTMLFileParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, name);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject addLock(
		HttpPrincipal httpPrincipal, long fileId) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "addLock", _addLockParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, fileId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject removeLock(
		HttpPrincipal httpPrincipal, long fileId) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "removeLock",
				_removeLockParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, fileId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getResource(
		HttpPrincipal httpPrincipal, long fileId, long versionId,
		boolean readOnly) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "getResource",
				_getResourceParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileId, versionId, readOnly);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject removeLoolToken(
		HttpPrincipal httpPrincipal, String token) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "removeLoolToken",
				_removeLoolTokenParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, token);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(FileUtilsServiceHttp.class);

	private static final Class<?>[] _uploadFileParameterTypes0 = new Class[] {
		long.class, String.class, java.io.File.class, int.class
	};
	private static final Class<?>[] _renameFileParameterTypes1 = new Class[] {
		long.class, String.class
	};
	private static final Class<?>[] _createAudioFileParameterTypes2 =
		new Class[] {long.class, String.class, java.io.File.class};
	private static final Class<?>[] _createGeogebraFileParameterTypes3 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _createMindmapFileParameterTypes4 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _createScratchFileParameterTypes5 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _createLoolFileParameterTypes6 =
		new Class[] {long.class, String.class, String.class};
	private static final Class<?>[] _createHTMLFileParameterTypes7 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _addLockParameterTypes8 = new Class[] {
		long.class
	};
	private static final Class<?>[] _removeLockParameterTypes9 = new Class[] {
		long.class
	};
	private static final Class<?>[] _getResourceParameterTypes10 = new Class[] {
		long.class, long.class, boolean.class
	};
	private static final Class<?>[] _removeLoolTokenParameterTypes11 =
		new Class[] {String.class};

}