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
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for Homework. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface HomeworkService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.course.service.impl.HomeworkServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the homework remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link HomeworkServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(method = "GET", value = "correct-file")
	public JSONObject correctFile(
			long homeworkId, long studentId, String comment)
		throws SystemException;

	@JSONWebService(method = "POST", value = "create-homework")
	public JSONObject createHomework(
			long courseId, long sourceSessionId, long targetSessionId,
			String targetDate, int homeworkType, String students, String blocks)
		throws SystemException;

	@JSONWebService(method = "GET", value = "drop-homework-file")
	public JSONObject dropHomeworkFile(long homeworkId, long fileEntryId)
		throws SystemException;

	@JSONWebService(method = "GET", value = "get-future-student-homeworks")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getFutureStudentHomeworks(
			long studentId, boolean undoneOnly)
		throws PortalException, SystemException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "GET", value = "get-previous-student-homeworks")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getPreviousStudentHomeworks(
			long studentId, String maxDateStr, boolean undoneOnly)
		throws PortalException, SystemException;

	@JSONWebService(method = "GET", value = "get-teacher-homeworks-to-correct")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getTeacherHomeworksToCorrect() throws SystemException;

	@JSONWebService(method = "GET", value = "set-homework-done")
	public JSONObject setHomeworkDone(long homeworkId, boolean isDone);

	@JSONWebService(method = "POST", value = "update-homework")
	public JSONObject updateHomework(
			long homeworkId, long targetSessionId, String targetDate,
			int homeworkType, String students, String blocks)
		throws SystemException;

}