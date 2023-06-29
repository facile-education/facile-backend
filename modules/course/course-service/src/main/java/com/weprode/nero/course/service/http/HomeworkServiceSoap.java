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

import com.weprode.nero.course.service.HomeworkServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>HomeworkServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.weprode.nero.course.model.HomeworkSoap</code>. If the method in the
 * service utility returns a
 * <code>com.weprode.nero.course.model.Homework</code>, that is translated to a
 * <code>com.weprode.nero.course.model.HomeworkSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class HomeworkServiceSoap {

	public static org.json.JSONObject getStudentHomeworks(
			long studentId, String minDateStr, String maxDateStr,
			boolean undoneOnly)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				HomeworkServiceUtil.getStudentHomeworks(
					studentId, minDateStr, maxDateStr, undoneOnly);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getTeacherHomeworksToCorrect()
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				HomeworkServiceUtil.getTeacherHomeworksToCorrect();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject countUndoneHomeworks(
			long studentId, String minDateStr, String maxDateStr)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				HomeworkServiceUtil.countUndoneHomeworks(
					studentId, minDateStr, maxDateStr);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject countHomeworksToCorrect()
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				HomeworkServiceUtil.countHomeworksToCorrect();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject setHomeworkDone(
			long homeworkId, boolean isDone)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				HomeworkServiceUtil.setHomeworkDone(homeworkId, isDone);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject createHomework(
			long courseId, String title, long sourceSessionId,
			long targetSessionId, String targetDateStr, int homeworkType,
			int estimatedTime, String students, String blocks,
			String publicationDateStr, boolean isDraft)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				HomeworkServiceUtil.createHomework(
					courseId, title, sourceSessionId, targetSessionId,
					targetDateStr, homeworkType, estimatedTime, students,
					blocks, publicationDateStr, isDraft);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject updateHomework(
			long homeworkId, String title, long targetSessionId,
			String targetDateStr, int estimatedTime, String students,
			String blocks, String publicationDateStr, boolean isDraft)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				HomeworkServiceUtil.updateHomework(
					homeworkId, title, targetSessionId, targetDateStr,
					estimatedTime, students, blocks, publicationDateStr,
					isDraft);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject deleteHomework(long homeworkId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				HomeworkServiceUtil.deleteHomework(homeworkId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject dropHomeworkFile(
			long homeworkId, long fileEntryId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				HomeworkServiceUtil.dropHomeworkFile(homeworkId, fileEntryId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject cancelDrop(long homeworkId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = HomeworkServiceUtil.cancelDrop(
				homeworkId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject correctFile(
			long homeworkId, long studentId, String comment)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = HomeworkServiceUtil.correctFile(
				homeworkId, studentId, comment);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(HomeworkServiceSoap.class);

}