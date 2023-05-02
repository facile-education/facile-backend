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

	public static org.json.JSONObject uploadFile(
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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject uploadTmpFile(
		HttpPrincipal httpPrincipal, String fileName, java.io.File file) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "uploadTmpFile",
				_uploadTmpFileParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileName, file);

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

	public static org.json.JSONObject renameFile(
		HttpPrincipal httpPrincipal, long fileId, String fileName) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "renameFile",
				_renameFileParameterTypes2);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject createAudioFile(
		HttpPrincipal httpPrincipal, long folderId, String name,
		java.io.File file) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createAudioFile",
				_createAudioFileParameterTypes3);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject createGeogebraFile(
		HttpPrincipal httpPrincipal, long folderId, String name) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createGeogebraFile",
				_createGeogebraFileParameterTypes4);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject createMindmapFile(
		HttpPrincipal httpPrincipal, long folderId, String name) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createMindmapFile",
				_createMindmapFileParameterTypes5);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject createScratchFile(
		HttpPrincipal httpPrincipal, long folderId, String name) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createScratchFile",
				_createScratchFileParameterTypes6);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject createLoolFile(
		HttpPrincipal httpPrincipal, long folderId, String name, String type) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createLoolFile",
				_createLoolFileParameterTypes7);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject createHTMLFile(
		HttpPrincipal httpPrincipal, long folderId, String name) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "createHTMLFile",
				_createHTMLFileParameterTypes8);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject addLock(
		HttpPrincipal httpPrincipal, long fileId) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "addLock", _addLockParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, fileId);

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

	public static org.json.JSONObject removeLock(
		HttpPrincipal httpPrincipal, long fileId) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "removeLock",
				_removeLockParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, fileId);

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

	public static org.json.JSONObject getResource(
		HttpPrincipal httpPrincipal, long fileId, long versionId,
		boolean readOnly) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "getResource",
				_getResourceParameterTypes11);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject removeLoolToken(
		HttpPrincipal httpPrincipal, String token) {

		try {
			MethodKey methodKey = new MethodKey(
				FileUtilsServiceUtil.class, "removeLoolToken",
				_removeLoolTokenParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, token);

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

	private static Log _log = LogFactoryUtil.getLog(FileUtilsServiceHttp.class);

	private static final Class<?>[] _uploadFileParameterTypes0 = new Class[] {
		long.class, String.class, java.io.File.class, int.class
	};
	private static final Class<?>[] _uploadTmpFileParameterTypes1 =
		new Class[] {String.class, java.io.File.class};
	private static final Class<?>[] _renameFileParameterTypes2 = new Class[] {
		long.class, String.class
	};
	private static final Class<?>[] _createAudioFileParameterTypes3 =
		new Class[] {long.class, String.class, java.io.File.class};
	private static final Class<?>[] _createGeogebraFileParameterTypes4 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _createMindmapFileParameterTypes5 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _createScratchFileParameterTypes6 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _createLoolFileParameterTypes7 =
		new Class[] {long.class, String.class, String.class};
	private static final Class<?>[] _createHTMLFileParameterTypes8 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _addLockParameterTypes9 = new Class[] {
		long.class
	};
	private static final Class<?>[] _removeLockParameterTypes10 = new Class[] {
		long.class
	};
	private static final Class<?>[] _getResourceParameterTypes11 = new Class[] {
		long.class, long.class, boolean.class
	};
	private static final Class<?>[] _removeLoolTokenParameterTypes12 =
		new Class[] {String.class};

}