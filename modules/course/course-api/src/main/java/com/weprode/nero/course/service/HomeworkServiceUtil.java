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

package com.weprode.nero.course.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Provides the remote service utility for Homework. This utility wraps
 * <code>com.weprode.nero.course.service.impl.HomeworkServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkService
 * @generated
 */
public class HomeworkServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.course.service.impl.HomeworkServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject correctFile(
			long homeworkId, long studentId, String comment)
		throws SystemException {

		return getService().correctFile(homeworkId, studentId, comment);
	}

	public static org.json.JSONObject createHomework(
			long courseId, long sourceSessionId, long targetSessionId,
			String targetDate, int homeworkType, String students, String blocks)
		throws SystemException {

		return getService().createHomework(
			courseId, sourceSessionId, targetSessionId, targetDate,
			homeworkType, students, blocks);
	}

	public static org.json.JSONObject dropHomeworkFile(
			long homeworkId, long fileEntryId)
		throws SystemException {

		return getService().dropHomeworkFile(homeworkId, fileEntryId);
	}

	public static org.json.JSONObject getFutureStudentHomeworks(
			long studentId, boolean undoneOnly)
		throws PortalException, SystemException {

		return getService().getFutureStudentHomeworks(studentId, undoneOnly);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getPreviousStudentHomeworks(
			long studentId, String maxDateStr, boolean undoneOnly)
		throws PortalException, SystemException {

		return getService().getPreviousStudentHomeworks(
			studentId, maxDateStr, undoneOnly);
	}

	public static org.json.JSONObject getTeacherHomeworksToCorrect()
		throws SystemException {

		return getService().getTeacherHomeworksToCorrect();
	}

	public static org.json.JSONObject setHomeworkDone(
		long homeworkId, boolean isDone) {

		return getService().setHomeworkDone(homeworkId, isDone);
	}

	public static org.json.JSONObject updateHomework(
			long homeworkId, long targetSessionId, String targetDate,
			int homeworkType, String students, String blocks)
		throws SystemException {

		return getService().updateHomework(
			homeworkId, targetSessionId, targetDate, homeworkType, students,
			blocks);
	}

	public static HomeworkService getService() {
		return _service;
	}

	private static volatile HomeworkService _service;

}