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
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.course.model.StudentHomework;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for StudentHomework. This utility wraps
 * <code>com.weprode.nero.course.service.impl.StudentHomeworkLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see StudentHomeworkLocalService
 * @generated
 */
public class StudentHomeworkLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.course.service.impl.StudentHomeworkLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the student homework to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect StudentHomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param studentHomework the student homework
	 * @return the student homework that was added
	 */
	public static StudentHomework addStudentHomework(
		StudentHomework studentHomework) {

		return getService().addStudentHomework(studentHomework);
	}

	public static void correctFile(
		long homeworkId, long studentId, String comment) {

		getService().correctFile(homeworkId, studentId, comment);
	}

	public static int countCorrectedWorks(long homeworkId) {
		return getService().countCorrectedWorks(homeworkId);
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
	 * Creates a new student homework with the primary key. Does not add the student homework to the database.
	 *
	 * @param studentHomeworkId the primary key for the new student homework
	 * @return the new student homework
	 */
	public static StudentHomework createStudentHomework(
		long studentHomeworkId) {

		return getService().createStudentHomework(studentHomeworkId);
	}

	public static void deleteDroppedFile(
			long studentId, long homeworkId, long fileEntryId)
		throws PortalException {

		getService().deleteDroppedFile(studentId, homeworkId, fileEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the student homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect StudentHomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework that was removed
	 * @throws PortalException if a student homework with the primary key could not be found
	 */
	public static StudentHomework deleteStudentHomework(long studentHomeworkId)
		throws PortalException {

		return getService().deleteStudentHomework(studentHomeworkId);
	}

	/**
	 * Deletes the student homework from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect StudentHomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param studentHomework the student homework
	 * @return the student homework that was removed
	 */
	public static StudentHomework deleteStudentHomework(
		StudentHomework studentHomework) {

		return getService().deleteStudentHomework(studentHomework);
	}

	public static void dropHomeworkFile(
			long studentId, long homeworkId, long fileEntryId)
		throws java.io.IOException, PortalException {

		getService().dropHomeworkFile(studentId, homeworkId, fileEntryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.StudentHomeworkModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.StudentHomeworkModelImpl</code>.
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

	public static StudentHomework fetchStudentHomework(long studentHomeworkId) {
		return getService().fetchStudentHomework(studentHomeworkId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static org.json.JSONArray getHomeworkStatus(long homeworkId) {
		return getService().getHomeworkStatus(homeworkId);
	}

	/**
	 * Get all students having given homework Id
	 */
	public static List<com.liferay.portal.kernel.model.User>
		getHomeworkStudents(long homeworkId) {

		return getService().getHomeworkStudents(homeworkId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static StudentHomework getOrCreateStudentHomework(
		long homeworkId, long studentId) {

		return getService().getOrCreateStudentHomework(homeworkId, studentId);
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

	/**
	 * Returns the student homework with the primary key.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework
	 * @throws PortalException if a student homework with the primary key could not be found
	 */
	public static StudentHomework getStudentHomework(long studentHomeworkId)
		throws PortalException {

		return getService().getStudentHomework(studentHomeworkId);
	}

	public static StudentHomework getStudentHomework(
		long homeworkId, long studentId) {

		return getService().getStudentHomework(homeworkId, studentId);
	}

	/**
	 * Returns a range of all the student homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of student homeworks
	 */
	public static List<StudentHomework> getStudentHomeworks(
		int start, int end) {

		return getService().getStudentHomeworks(start, end);
	}

	/**
	 * Returns the number of student homeworks.
	 *
	 * @return the number of student homeworks
	 */
	public static int getStudentHomeworksCount() {
		return getService().getStudentHomeworksCount();
	}

	public static org.json.JSONObject getStudentSentFile(
		long studentId, long homeworkId) {

		return getService().getStudentSentFile(studentId, homeworkId);
	}

	public static List<com.liferay.portal.kernel.model.User>
		getStudentsHavingDoneHomework(long homeworkId) {

		return getService().getStudentsHavingDoneHomework(homeworkId);
	}

	/**
	 * Returns true if the given student has done the given homework
	 */
	public static boolean hasStudentDoneHomework(
		long studentId, long homeworkId) {

		return getService().hasStudentDoneHomework(studentId, homeworkId);
	}

	/**
	 * Returns true if the given student has given homework
	 */
	public static boolean hasStudentHomework(long studentId, long homeworkId) {
		return getService().hasStudentHomework(studentId, homeworkId);
	}

	public static boolean hasStudentSentFile(
		long studentId, long homeworkId, long fileEntryId) {

		return getService().hasStudentSentFile(
			studentId, homeworkId, fileEntryId);
	}

	public static boolean removeHomework(long homeworkId) {
		return getService().removeHomework(homeworkId);
	}

	public static boolean removeStudentHomework(
		long homeworkId, long studentId) {

		return getService().removeStudentHomework(homeworkId, studentId);
	}

	public static boolean setHomeworkDone(
		long homeworkId, long studentId, boolean isDone) {

		return getService().setHomeworkDone(homeworkId, studentId, isDone);
	}

	public static void setHomeworkSent(
		long studentId, long homeworkId, long fileEntryId) {

		getService().setHomeworkSent(studentId, homeworkId, fileEntryId);
	}

	/**
	 * Updates the student homework in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect StudentHomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param studentHomework the student homework
	 * @return the student homework that was updated
	 */
	public static StudentHomework updateStudentHomework(
		StudentHomework studentHomework) {

		return getService().updateStudentHomework(studentHomework);
	}

	public static StudentHomeworkLocalService getService() {
		return _service;
	}

	private static volatile StudentHomeworkLocalService _service;

}