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

package com.weprode.nero.group.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.group.service.GroupUtilsServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>GroupUtilsServiceUtil</code> service
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
 * @see GroupUtilsServiceSoap
 * @generated
 */
public class GroupUtilsServiceHttp {

	public static org.json.JSONObject getUserGroups(
		HttpPrincipal httpPrincipal, long schoolId,
		boolean includeInstitutional, boolean includeCommunities,
		boolean pedagogicalOnly) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupUtilsServiceUtil.class, "getUserGroups",
				_getUserGroupsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoolId, includeInstitutional, includeCommunities,
				pedagogicalOnly);

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

	public static org.json.JSONObject getUserCollaborativeGroups(
		HttpPrincipal httpPrincipal, String filter, boolean allCommunities,
		boolean allClasses, boolean allCours) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupUtilsServiceUtil.class, "getUserCollaborativeGroups",
				_getUserCollaborativeGroupsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, filter, allCommunities, allClasses, allCours);

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

	public static org.json.JSONObject getUsersCompletion(
		HttpPrincipal httpPrincipal, String query, long schoolId, long roleId) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupUtilsServiceUtil.class, "getUsersCompletion",
				_getUsersCompletionParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, query, schoolId, roleId);

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

	public static org.json.JSONObject getGroupMembers(
		HttpPrincipal httpPrincipal, long groupId) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupUtilsServiceUtil.class, "getGroupMembers",
				_getGroupMembersParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

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

	public static org.json.JSONObject getGroupActivity(
		HttpPrincipal httpPrincipal, long groupId, String maxDate,
		int nbResults) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupUtilsServiceUtil.class, "getGroupActivity",
				_getGroupActivityParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, maxDate, nbResults);

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

	public static org.json.JSONObject getSpecificGroupActivities(
		HttpPrincipal httpPrincipal, long groupId, String maxDate,
		int nbResults, boolean allHistory, boolean containNews,
		boolean containDocs, boolean containMembership,
		boolean containPendingFirings, boolean containFirings,
		boolean containHomework, boolean containSessions) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupUtilsServiceUtil.class, "getSpecificGroupActivities",
				_getSpecificGroupActivitiesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, maxDate, nbResults, allHistory, containNews,
				containDocs, containMembership, containPendingFirings,
				containFirings, containHomework, containSessions);

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

	public static org.json.JSONObject getGroupHistory(
		HttpPrincipal httpPrincipal, long groupId, String maxDate,
		int nbResults) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupUtilsServiceUtil.class, "getGroupHistory",
				_getGroupHistoryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, maxDate, nbResults);

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
		GroupUtilsServiceHttp.class);

	private static final Class<?>[] _getUserGroupsParameterTypes0 =
		new Class[] {long.class, boolean.class, boolean.class, boolean.class};
	private static final Class<?>[] _getUserCollaborativeGroupsParameterTypes1 =
		new Class[] {String.class, boolean.class, boolean.class, boolean.class};
	private static final Class<?>[] _getUsersCompletionParameterTypes2 =
		new Class[] {String.class, long.class, long.class};
	private static final Class<?>[] _getGroupMembersParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getGroupActivityParameterTypes4 =
		new Class[] {long.class, String.class, int.class};
	private static final Class<?>[] _getSpecificGroupActivitiesParameterTypes5 =
		new Class[] {
			long.class, String.class, int.class, boolean.class, boolean.class,
			boolean.class, boolean.class, boolean.class, boolean.class,
			boolean.class, boolean.class
		};
	private static final Class<?>[] _getGroupHistoryParameterTypes6 =
		new Class[] {long.class, String.class, int.class};

}