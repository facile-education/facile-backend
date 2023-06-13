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

package com.weprode.nero.course.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.course.service.HomeworkServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>HomeworkServiceUtil</code> service
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
 * @see HomeworkServiceSoap
 * @generated
 */
public class HomeworkServiceHttp {

	public static org.json.JSONObject getFutureStudentHomeworks(
			HttpPrincipal httpPrincipal, long studentId, boolean undoneOnly)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "getFutureStudentHomeworks",
				_getFutureStudentHomeworksParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, studentId, undoneOnly);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.SystemException) {

					throw (com.liferay.portal.kernel.exception.SystemException)
						exception;
				}

				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

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

	public static org.json.JSONObject getPreviousStudentHomeworks(
			HttpPrincipal httpPrincipal, long studentId, String maxDateStr,
			boolean undoneOnly)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "getPreviousStudentHomeworks",
				_getPreviousStudentHomeworksParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, studentId, maxDateStr, undoneOnly);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.SystemException) {

					throw (com.liferay.portal.kernel.exception.SystemException)
						exception;
				}

				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

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

	public static org.json.JSONObject getTeacherHomeworksToCorrect(
			HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "getTeacherHomeworksToCorrect",
				_getTeacherHomeworksToCorrectParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.SystemException) {

					throw (com.liferay.portal.kernel.exception.SystemException)
						exception;
				}

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

	public static org.json.JSONObject setHomeworkDone(
		HttpPrincipal httpPrincipal, long homeworkId, boolean isDone) {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "setHomeworkDone",
				_setHomeworkDoneParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, homeworkId, isDone);

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

	public static org.json.JSONObject createHomework(
			HttpPrincipal httpPrincipal, long courseId, long sourceSessionId,
			long targetSessionId, String targetDate, int homeworkType,
			String students, String blocks)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "createHomework",
				_createHomeworkParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, sourceSessionId, targetSessionId,
				targetDate, homeworkType, students, blocks);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.SystemException) {

					throw (com.liferay.portal.kernel.exception.SystemException)
						exception;
				}

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

	public static org.json.JSONObject updateHomework(
			HttpPrincipal httpPrincipal, long homeworkId, long targetSessionId,
			String targetDate, int homeworkType, String students, String blocks)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "updateHomework",
				_updateHomeworkParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, homeworkId, targetSessionId, targetDate,
				homeworkType, students, blocks);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.SystemException) {

					throw (com.liferay.portal.kernel.exception.SystemException)
						exception;
				}

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

	public static org.json.JSONObject dropHomeworkFile(
			HttpPrincipal httpPrincipal, long homeworkId, long fileEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "dropHomeworkFile",
				_dropHomeworkFileParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, homeworkId, fileEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.SystemException) {

					throw (com.liferay.portal.kernel.exception.SystemException)
						exception;
				}

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

	public static org.json.JSONObject correctFile(
			HttpPrincipal httpPrincipal, long homeworkId, long studentId,
			String comment)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "correctFile",
				_correctFileParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, homeworkId, studentId, comment);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.SystemException) {

					throw (com.liferay.portal.kernel.exception.SystemException)
						exception;
				}

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

	private static Log _log = LogFactoryUtil.getLog(HomeworkServiceHttp.class);

	private static final Class<?>[] _getFutureStudentHomeworksParameterTypes0 =
		new Class[] {long.class, boolean.class};
	private static final Class<?>[]
		_getPreviousStudentHomeworksParameterTypes1 = new Class[] {
			long.class, String.class, boolean.class
		};
	private static final Class<?>[]
		_getTeacherHomeworksToCorrectParameterTypes2 = new Class[] {};
	private static final Class<?>[] _setHomeworkDoneParameterTypes3 =
		new Class[] {long.class, boolean.class};
	private static final Class<?>[] _createHomeworkParameterTypes4 =
		new Class[] {
			long.class, long.class, long.class, String.class, int.class,
			String.class, String.class
		};
	private static final Class<?>[] _updateHomeworkParameterTypes5 =
		new Class[] {
			long.class, long.class, String.class, int.class, String.class,
			String.class
		};
	private static final Class<?>[] _dropHomeworkFileParameterTypes6 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _correctFileParameterTypes7 = new Class[] {
		long.class, long.class, String.class
	};

}