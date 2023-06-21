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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HomeworkService}.
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkService
 * @generated
 */
public class HomeworkServiceWrapper
	implements HomeworkService, ServiceWrapper<HomeworkService> {

	public HomeworkServiceWrapper(HomeworkService homeworkService) {
		_homeworkService = homeworkService;
	}

	@Override
	public org.json.JSONObject cancelDrop(long homeworkId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.cancelDrop(homeworkId);
	}

	@Override
	public org.json.JSONObject correctFile(
			long homeworkId, long studentId, String comment)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.correctFile(homeworkId, studentId, comment);
	}

	@Override
	public org.json.JSONObject countHomeworksToCorrect()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.countHomeworksToCorrect();
	}

	@Override
	public org.json.JSONObject countUndoneHomeworks(long studentId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.countUndoneHomeworks(studentId);
	}

	@Override
	public org.json.JSONObject createHomework(
			long courseId, long sourceSessionId, long targetSessionId,
			String targetDateStr, int homeworkType, int estimatedTime,
			String students, String blocks, String publicationDateStr,
			boolean isDraft)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.createHomework(
			courseId, sourceSessionId, targetSessionId, targetDateStr,
			homeworkType, estimatedTime, students, blocks, publicationDateStr,
			isDraft);
	}

	@Override
	public org.json.JSONObject deleteHomework(long homeworkId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.deleteHomework(homeworkId);
	}

	@Override
	public org.json.JSONObject dropHomeworkFile(
			long homeworkId, long fileEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.dropHomeworkFile(homeworkId, fileEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _homeworkService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getStudentHomeworks(
			long studentId, String minDateStr, String maxDateStr,
			boolean undoneOnly)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.getStudentHomeworks(
			studentId, minDateStr, maxDateStr, undoneOnly);
	}

	@Override
	public org.json.JSONObject getTeacherHomeworksToCorrect()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.getTeacherHomeworksToCorrect();
	}

	@Override
	public org.json.JSONObject setHomeworkDone(
		long homeworkId, boolean isDone) {

		return _homeworkService.setHomeworkDone(homeworkId, isDone);
	}

	@Override
	public org.json.JSONObject updateHomework(
			long homeworkId, long targetSessionId, String targetDateStr,
			int estimatedTime, String students, String blocks,
			String publicationDateStr, boolean isDraft)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _homeworkService.updateHomework(
			homeworkId, targetSessionId, targetDateStr, estimatedTime, students,
			blocks, publicationDateStr, isDraft);
	}

	@Override
	public HomeworkService getWrappedService() {
		return _homeworkService;
	}

	@Override
	public void setWrappedService(HomeworkService homeworkService) {
		_homeworkService = homeworkService;
	}

	private HomeworkService _homeworkService;

}