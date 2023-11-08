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

package com.weprode.facile.news.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.facile.news.service.NewsServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>NewsServiceUtil</code> service
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
public class NewsServiceHttp {

	public static org.json.JSONObject addNews(
		HttpPrincipal httpPrincipal, String title, String content,
		boolean isSchoolNews, boolean isImportant, long imageId,
		String publicationDate, String population, String attachFiles) {

		try {
			MethodKey methodKey = new MethodKey(
				NewsServiceUtil.class, "addNews", _addNewsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, title, content, isSchoolNews, isImportant, imageId,
				publicationDate, population, attachFiles);

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

	public static org.json.JSONObject editNews(
		HttpPrincipal httpPrincipal, long newsId, String title, String content,
		boolean isImportant, long imageId, String publicationDate,
		String population, String attachFiles, boolean markAsUnreadForAll) {

		try {
			MethodKey methodKey = new MethodKey(
				NewsServiceUtil.class, "editNews", _editNewsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, newsId, title, content, isImportant, imageId,
				publicationDate, population, attachFiles, markAsUnreadForAll);

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

	public static org.json.JSONObject getSchoolNews(
		HttpPrincipal httpPrincipal, String maxDateString, int nbNews,
		boolean importantOnly, boolean unreadOnly) {

		try {
			MethodKey methodKey = new MethodKey(
				NewsServiceUtil.class, "getSchoolNews",
				_getSchoolNewsParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, maxDateString, nbNews, importantOnly, unreadOnly);

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

	public static org.json.JSONObject setNewsRead(
		HttpPrincipal httpPrincipal, long newsId) {

		try {
			MethodKey methodKey = new MethodKey(
				NewsServiceUtil.class, "setNewsRead",
				_setNewsReadParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, newsId);

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

	public static org.json.JSONObject getGroupNewsBroadcastGroups(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				NewsServiceUtil.class, "getGroupNewsBroadcastGroups",
				_getGroupNewsBroadcastGroupsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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

	public static org.json.JSONObject getSchoolNewsBroadcastGroups(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				NewsServiceUtil.class, "getSchoolNewsBroadcastGroups",
				_getSchoolNewsBroadcastGroupsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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

	public static org.json.JSONObject getNewsDetails(
		HttpPrincipal httpPrincipal, long newsId) {

		try {
			MethodKey methodKey = new MethodKey(
				NewsServiceUtil.class, "getNewsDetails",
				_getNewsDetailsParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, newsId);

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

	public static org.json.JSONObject deleteNews(
		HttpPrincipal httpPrincipal, long newsId) {

		try {
			MethodKey methodKey = new MethodKey(
				NewsServiceUtil.class, "deleteNews",
				_deleteNewsParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, newsId);

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

	private static Log _log = LogFactoryUtil.getLog(NewsServiceHttp.class);

	private static final Class<?>[] _addNewsParameterTypes0 = new Class[] {
		String.class, String.class, boolean.class, boolean.class, long.class,
		String.class, String.class, String.class
	};
	private static final Class<?>[] _editNewsParameterTypes1 = new Class[] {
		long.class, String.class, String.class, boolean.class, long.class,
		String.class, String.class, String.class, boolean.class
	};
	private static final Class<?>[] _getSchoolNewsParameterTypes2 =
		new Class[] {String.class, int.class, boolean.class, boolean.class};
	private static final Class<?>[] _setNewsReadParameterTypes3 = new Class[] {
		long.class
	};
	private static final Class<?>[]
		_getGroupNewsBroadcastGroupsParameterTypes4 = new Class[] {};
	private static final Class<?>[]
		_getSchoolNewsBroadcastGroupsParameterTypes5 = new Class[] {};
	private static final Class<?>[] _getNewsDetailsParameterTypes6 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteNewsParameterTypes7 = new Class[] {
		long.class
	};

}