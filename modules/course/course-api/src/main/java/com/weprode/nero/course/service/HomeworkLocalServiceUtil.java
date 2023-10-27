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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.course.model.Homework;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Homework. This utility wraps
 * <code>com.weprode.nero.course.service.impl.HomeworkLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkLocalService
 * @generated
 */
public class HomeworkLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.course.service.impl.HomeworkLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the homework to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param homework the homework
	 * @return the homework that was added
	 */
	public static Homework addHomework(Homework homework) {
		return getService().addHomework(homework);
	}

	public static org.json.JSONArray countHomeworksToCorrect(long teacherId) {
		return getService().countHomeworksToCorrect(teacherId);
	}

	public static int countUndoneHomeworks(long studentId) {
		return getService().countUndoneHomeworks(studentId);
	}

	public static int countUndoneHomeworks(
		long studentId, java.util.Date minDate, java.util.Date maxDate) {

		return getService().countUndoneHomeworks(studentId, minDate, maxDate);
	}

	/**
	 * Creates a new homework with the primary key. Does not add the homework to the database.
	 *
	 * @param homeworkId the primary key for the new homework
	 * @return the new homework
	 */
	public static Homework createHomework(long homeworkId) {
		return getService().createHomework(homeworkId);
	}

	public static Homework createHomework(
		com.liferay.portal.kernel.model.User teacher, String title,
		long sourceSessionId, long targetSessionId, long courseId,
		java.util.Date targetDate, int homeworkType, int estimatedTime,
		List<Long> studentIds, java.util.Date publicationDate,
		boolean isDraft) {

		return getService().createHomework(
			teacher, title, sourceSessionId, targetSessionId, courseId,
			targetDate, homeworkType, estimatedTime, studentIds,
			publicationDate, isDraft);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the homework from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param homework the homework
	 * @return the homework that was removed
	 */
	public static Homework deleteHomework(Homework homework) {
		return getService().deleteHomework(homework);
	}

	/**
	 * Deletes the homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework that was removed
	 * @throws PortalException if a homework with the primary key could not be found
	 */
	public static Homework deleteHomework(long homeworkId)
		throws PortalException {

		return getService().deleteHomework(homeworkId);
	}

	/**
	 * Remove an homework and its associated objects
	 */
	public static void deleteHomeworkAndDependencies(long homeworkId) {
		getService().deleteHomeworkAndDependencies(homeworkId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static boolean deleteSessionHomeworks(long sessionId) {
		return getService().deleteSessionHomeworks(sessionId);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Homework fetchHomework(long homeworkId) {
		return getService().fetchHomework(homeworkId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<Homework> getCourseHomeworkActivity(
		long userId, long courseId, java.util.Date minDate,
		java.util.Date maxDate) {

		return getService().getCourseHomeworkActivity(
			userId, courseId, minDate, maxDate);
	}

	/**
	 * Returns the homework with the primary key.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework
	 * @throws PortalException if a homework with the primary key could not be found
	 */
	public static Homework getHomework(long homeworkId) throws PortalException {
		return getService().getHomework(homeworkId);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getHomeworkDropFolder(long homeworkId)
		throws PortalException, SystemException {

		return getService().getHomeworkDropFolder(homeworkId);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getHomeworkFolder(long homeworkId)
		throws PortalException, SystemException {

		return getService().getHomeworkFolder(homeworkId);
	}

	/**
	 * Returns a range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of homeworks
	 */
	public static List<Homework> getHomeworks(int start, int end) {
		return getService().getHomeworks(start, end);
	}

	/**
	 * Returns the number of homeworks.
	 *
	 * @return the number of homeworks
	 */
	public static int getHomeworksCount() {
		return getService().getHomeworksCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static List<Homework> getSessionGivenHomeworks(
		com.liferay.portal.kernel.model.User user, long sessionId,
		boolean hideDraftsForTeachers) {

		return getService().getSessionGivenHomeworks(
			user, sessionId, hideDraftsForTeachers);
	}

	public static List<Homework> getSessionToDoHomeworks(
		com.liferay.portal.kernel.model.User user, long sessionId,
		boolean hideDraftsForTeachers) {

		return getService().getSessionToDoHomeworks(
			user, sessionId, hideDraftsForTeachers);
	}

	public static List<Homework> getStudentHomeworkActivity(
		long studentId, java.util.Date minDate, java.util.Date maxDate) {

		return getService().getStudentHomeworkActivity(
			studentId, minDate, maxDate);
	}

	public static List<Homework> getStudentHomeworks(
		long studentId, java.util.Date minDate, java.util.Date maxDate,
		boolean undoneOnly) {

		return getService().getStudentHomeworks(
			studentId, minDate, maxDate, undoneOnly);
	}

	public static List<Homework> getStudentsHomeworks(
		List<Long> studentIds, java.util.Date minDate, java.util.Date maxDate) {

		return getService().getStudentsHomeworks(studentIds, minDate, maxDate);
	}

	public static List<Homework> getTeacherHomeworksToCorrect(
		long teacherId, long courseId) {

		return getService().getTeacherHomeworksToCorrect(teacherId, courseId);
	}

	public static boolean hasHomeworksGivenDuringSession(long sessionId) {
		return getService().hasHomeworksGivenDuringSession(sessionId);
	}

	public static boolean hasHomeworksToDoForSession(long sessionId) {
		return getService().hasHomeworksToDoForSession(sessionId);
	}

	public static void sendCorrections(long teacherId, long homeworkId)
		throws PortalException {

		getService().sendCorrections(teacherId, homeworkId);
	}

	/**
	 * Updates the homework in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param homework the homework
	 * @return the homework that was updated
	 */
	public static Homework updateHomework(Homework homework) {
		return getService().updateHomework(homework);
	}

	public static Homework updateHomework(
		long homeworkId, String title, long targetSessionId,
		java.util.Date targetDate, int estimatedTime, List<Long> studentIds,
		java.util.Date publicationDate, boolean isDraft) {

		return getService().updateHomework(
			homeworkId, title, targetSessionId, targetDate, estimatedTime,
			studentIds, publicationDate, isDraft);
	}

	public static HomeworkLocalService getService() {
		return _service;
	}

	private static volatile HomeworkLocalService _service;

}