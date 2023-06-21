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

	public static org.json.JSONObject getStudentHomeworks(
			HttpPrincipal httpPrincipal, long studentId, String minDateStr,
			String maxDateStr, boolean undoneOnly)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "getStudentHomeworks",
				_getStudentHomeworksParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, studentId, minDateStr, maxDateStr, undoneOnly);

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
				_getTeacherHomeworksToCorrectParameterTypes1);

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

	public static org.json.JSONObject countUndoneHomeworks(
			HttpPrincipal httpPrincipal, long studentId)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "countUndoneHomeworks",
				_countUndoneHomeworksParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, studentId);

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

	public static org.json.JSONObject countHomeworksToCorrect(
			HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "countHomeworksToCorrect",
				_countHomeworksToCorrectParameterTypes3);

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
				_setHomeworkDoneParameterTypes4);

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
			long targetSessionId, String targetDateStr, int homeworkType,
			int estimatedTime, String students, String blocks,
			String publicationDateStr, boolean isDraft)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "createHomework",
				_createHomeworkParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, sourceSessionId, targetSessionId,
				targetDateStr, homeworkType, estimatedTime, students, blocks,
				publicationDateStr, isDraft);

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
			String targetDateStr, int estimatedTime, String students,
			String blocks, String publicationDateStr, boolean isDraft)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "updateHomework",
				_updateHomeworkParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, homeworkId, targetSessionId, targetDateStr,
				estimatedTime, students, blocks, publicationDateStr, isDraft);

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

	public static org.json.JSONObject deleteHomework(
			HttpPrincipal httpPrincipal, long homeworkId)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "deleteHomework",
				_deleteHomeworkParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, homeworkId);

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
				_dropHomeworkFileParameterTypes8);

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

	public static org.json.JSONObject cancelDrop(
			HttpPrincipal httpPrincipal, long homeworkId)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				HomeworkServiceUtil.class, "cancelDrop",
				_cancelDropParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, homeworkId);

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
				_correctFileParameterTypes10);

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

	private static final Class<?>[] _getStudentHomeworksParameterTypes0 =
		new Class[] {long.class, String.class, String.class, boolean.class};
	private static final Class<?>[]
		_getTeacherHomeworksToCorrectParameterTypes1 = new Class[] {};
	private static final Class<?>[] _countUndoneHomeworksParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _countHomeworksToCorrectParameterTypes3 =
		new Class[] {};
	private static final Class<?>[] _setHomeworkDoneParameterTypes4 =
		new Class[] {long.class, boolean.class};
	private static final Class<?>[] _createHomeworkParameterTypes5 =
		new Class[] {
			long.class, long.class, long.class, String.class, int.class,
			int.class, String.class, String.class, String.class, boolean.class
		};
	private static final Class<?>[] _updateHomeworkParameterTypes6 =
		new Class[] {
			long.class, long.class, String.class, int.class, String.class,
			String.class, String.class, boolean.class
		};
	private static final Class<?>[] _deleteHomeworkParameterTypes7 =
		new Class[] {long.class};
	private static final Class<?>[] _dropHomeworkFileParameterTypes8 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _cancelDropParameterTypes9 = new Class[] {
		long.class
	};
	private static final Class<?>[] _correctFileParameterTypes10 = new Class[] {
		long.class, long.class, String.class
	};

}