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

package com.weprode.nero.school.life.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.school.life.model.SessionStudent;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for SessionStudent. This utility wraps
 * <code>com.weprode.nero.school.life.service.impl.SessionStudentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SessionStudentLocalService
 * @generated
 */
public class SessionStudentLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.school.life.service.impl.SessionStudentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the session student to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionStudentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionStudent the session student
	 * @return the session student that was added
	 */
	public static SessionStudent addSessionStudent(
		SessionStudent sessionStudent) {

		return getService().addSessionStudent(sessionStudent);
	}

	public static SessionStudent addStudentToSession(
		long teacherId, long studentId, long schoollifeSessionId,
		String comment, String subject, boolean notifyParents) {

		return getService().addStudentToSession(
			teacherId, studentId, schoollifeSessionId, comment, subject,
			notifyParents);
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
	 * Creates a new session student with the primary key. Does not add the session student to the database.
	 *
	 * @param sessionStudentPK the primary key for the new session student
	 * @return the new session student
	 */
	public static SessionStudent createSessionStudent(
		com.weprode.nero.school.life.service.persistence.SessionStudentPK
			sessionStudentPK) {

		return getService().createSessionStudent(sessionStudentPK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static boolean deleteSession(long schoollifeSessionId) {
		return getService().deleteSession(schoollifeSessionId);
	}

	/**
	 * Deletes the session student from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionStudentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionStudent the session student
	 * @return the session student that was removed
	 */
	public static SessionStudent deleteSessionStudent(
		SessionStudent sessionStudent) {

		return getService().deleteSessionStudent(sessionStudent);
	}

	/**
	 * Deletes the session student with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionStudentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionStudentPK the primary key of the session student
	 * @return the session student that was removed
	 * @throws PortalException if a session student with the primary key could not be found
	 */
	public static SessionStudent deleteSessionStudent(
			com.weprode.nero.school.life.service.persistence.SessionStudentPK
				sessionStudentPK)
		throws PortalException {

		return getService().deleteSessionStudent(sessionStudentPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.SessionStudentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.SessionStudentModelImpl</code>.
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

	public static SessionStudent fetchSessionStudent(
		com.weprode.nero.school.life.service.persistence.SessionStudentPK
			sessionStudentPK) {

		return getService().fetchSessionStudent(sessionStudentPK);
	}

	public static List<Long> getAbsentStudents(long schoollifeSessionId) {
		return getService().getAbsentStudents(schoollifeSessionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static int getNbRegisteredStudents(long schoollifeSessionId) {
		return getService().getNbRegisteredStudents(schoollifeSessionId);
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

	public static List<SessionStudent> getSessionMembers(
		long schoollifeSessionId) {

		return getService().getSessionMembers(schoollifeSessionId);
	}

	/**
	 * Returns the session student with the primary key.
	 *
	 * @param sessionStudentPK the primary key of the session student
	 * @return the session student
	 * @throws PortalException if a session student with the primary key could not be found
	 */
	public static SessionStudent getSessionStudent(
			com.weprode.nero.school.life.service.persistence.SessionStudentPK
				sessionStudentPK)
		throws PortalException {

		return getService().getSessionStudent(sessionStudentPK);
	}

	/**
	 * Returns a range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of session students
	 */
	public static List<SessionStudent> getSessionStudents(int start, int end) {
		return getService().getSessionStudents(start, end);
	}

	/**
	 * Returns the number of session students.
	 *
	 * @return the number of session students
	 */
	public static int getSessionStudentsCount() {
		return getService().getSessionStudentsCount();
	}

	public static org.json.JSONArray getStudentSessions(
		com.liferay.portal.kernel.model.User user, long studentId,
		java.util.Date minDate, java.util.Date maxDate) {

		return getService().getStudentSessions(
			user, studentId, minDate, maxDate);
	}

	public static org.json.JSONArray getStudentSessions(
		com.liferay.portal.kernel.model.User user, long studentId,
		java.util.Date minDate, java.util.Date maxDate, boolean withFired) {

		return getService().getStudentSessions(
			user, studentId, minDate, maxDate, withFired);
	}

	public static boolean markStudentPresent(
		long schoollifeSessionId, long studentId, boolean isPresent) {

		return getService().markStudentPresent(
			schoollifeSessionId, studentId, isPresent);
	}

	public static boolean registerStudentToSession(
		long teacherId, long studentId, long schoollifeSessionId,
		String comment, String replayTestSubject, boolean notifyParents) {

		return getService().registerStudentToSession(
			teacherId, studentId, schoollifeSessionId, comment,
			replayTestSubject, notifyParents);
	}

	public static boolean removeStudentFromSession(
		long studentId, long schoollifeSessionId) {

		return getService().removeStudentFromSession(
			studentId, schoollifeSessionId);
	}

	public static boolean unregisterStudentToSession(
		long studentId, long schoollifeSessionId, boolean allSessions) {

		return getService().unregisterStudentToSession(
			studentId, schoollifeSessionId, allSessions);
	}

	/**
	 * Updates the session student in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionStudentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionStudent the session student
	 * @return the session student that was updated
	 */
	public static SessionStudent updateSessionStudent(
		SessionStudent sessionStudent) {

		return getService().updateSessionStudent(sessionStudent);
	}

	public static SessionStudentLocalService getService() {
		return _service;
	}

	private static volatile SessionStudentLocalService _service;

}