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

package com.weprode.nero.schedule.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.schedule.model.SessionTeacher;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for SessionTeacher. This utility wraps
 * <code>com.weprode.nero.schedule.service.impl.SessionTeacherLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacherLocalService
 * @generated
 */
public class SessionTeacherLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.schedule.service.impl.SessionTeacherLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the session teacher to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionTeacherLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionTeacher the session teacher
	 * @return the session teacher that was added
	 */
	public static SessionTeacher addSessionTeacher(
		SessionTeacher sessionTeacher) {

		return getService().addSessionTeacher(sessionTeacher);
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
	 * Creates a new session teacher with the primary key. Does not add the session teacher to the database.
	 *
	 * @param sessionTeacherId the primary key for the new session teacher
	 * @return the new session teacher
	 */
	public static SessionTeacher createSessionTeacher(long sessionTeacherId) {
		return getService().createSessionTeacher(sessionTeacherId);
	}

	public static SessionTeacher createSessionteacher(
			long sessionId, long teacherId)
		throws SystemException {

		return getService().createSessionteacher(sessionId, teacherId);
	}

	public static SessionTeacher createSessionteacher(
			long sessionId, long teacherId, int status)
		throws SystemException {

		return getService().createSessionteacher(sessionId, teacherId, status);
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
	 * Deletes the session teacher with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionTeacherLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher that was removed
	 * @throws PortalException if a session teacher with the primary key could not be found
	 */
	public static SessionTeacher deleteSessionTeacher(long sessionTeacherId)
		throws PortalException {

		return getService().deleteSessionTeacher(sessionTeacherId);
	}

	/**
	 * Deletes the session teacher from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionTeacherLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionTeacher the session teacher
	 * @return the session teacher that was removed
	 */
	public static SessionTeacher deleteSessionTeacher(
		SessionTeacher sessionTeacher) {

		return getService().deleteSessionTeacher(sessionTeacher);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionTeacherModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionTeacherModelImpl</code>.
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

	public static SessionTeacher fetchSessionTeacher(long sessionTeacherId) {
		return getService().fetchSessionTeacher(sessionTeacherId);
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

	public static com.liferay.portal.kernel.model.User getLastEditor(
			long sessionId, java.util.Date modifiedDate)
		throws PortalException, SystemException {

		return getService().getLastEditor(sessionId, modifiedDate);
	}

	public static java.util.Date getLastModificationDate(
		long sessionId, java.util.Date minDate, java.util.Date maxDate) {

		return getService().getLastModificationDate(
			sessionId, minDate, maxDate);
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
	 * Returns the session teacher with the primary key.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher
	 * @throws PortalException if a session teacher with the primary key could not be found
	 */
	public static SessionTeacher getSessionTeacher(long sessionTeacherId)
		throws PortalException {

		return getService().getSessionTeacher(sessionTeacherId);
	}

	public static SessionTeacher getSessionTeacher(
		long sessionId, long teacherId) {

		return getService().getSessionTeacher(sessionId, teacherId);
	}

	/**
	 * Returns a range of all the session teachers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @return the range of session teachers
	 */
	public static List<SessionTeacher> getSessionTeachers(int start, int end) {
		return getService().getSessionTeachers(start, end);
	}

	public static List<SessionTeacher> getSessionTeachers(long sessionId) {
		return getService().getSessionTeachers(sessionId);
	}

	/**
	 * Returns the number of session teachers.
	 *
	 * @return the number of session teachers
	 */
	public static int getSessionTeachersCount() {
		return getService().getSessionTeachersCount();
	}

	public static List<Long> getTeacherIds(long sessionId) {
		return getService().getTeacherIds(sessionId);
	}

	public static List<com.liferay.portal.kernel.model.User> getTeachers(
		long sessionId) {

		return getService().getTeachers(sessionId);
	}

	public static List<com.liferay.portal.kernel.model.User> getTeachers(
		long sessionId, boolean includeSubstitutedTeachers) {

		return getService().getTeachers(sessionId, includeSubstitutedTeachers);
	}

	/**
	 * Returns true if the given teacher teaches the given session
	 */
	public static boolean hasTeacherSession(long teacherId, long sessionId) {
		return getService().hasTeacherSession(teacherId, sessionId);
	}

	public static boolean isSubstituted(long teacherId, long sessionId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException,
			   SystemException {

		return getService().isSubstituted(teacherId, sessionId);
	}

	public static boolean removeBySessionId(long sessionId)
		throws SystemException {

		return getService().removeBySessionId(sessionId);
	}

	public static SessionTeacher removeSubstitute(
			long sessionId, long substituteId)
		throws SystemException {

		return getService().removeSubstitute(sessionId, substituteId);
	}

	public static SessionTeacher substituteTeacher(
			long teacherId, long sessionId, long substituteId)
		throws SystemException {

		return getService().substituteTeacher(
			teacherId, sessionId, substituteId);
	}

	/**
	 * Updates the session teacher in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionTeacherLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionTeacher the session teacher
	 * @return the session teacher that was updated
	 */
	public static SessionTeacher updateSessionTeacher(
		SessionTeacher sessionTeacher) {

		return getService().updateSessionTeacher(sessionTeacher);
	}

	public static SessionTeacher updateSessionTeacher(
			SessionTeacher sessionTeacher, int status, long substituteId)
		throws SystemException {

		return getService().updateSessionTeacher(
			sessionTeacher, status, substituteId);
	}

	/**
	 * Update the list of teachers for a given session
	 */
	public static boolean updateTeacherListForSession(
			long sessionId, List<Long> newTeacherIdList)
		throws SystemException {

		return getService().updateTeacherListForSession(
			sessionId, newTeacherIdList);
	}

	public static SessionTeacherLocalService getService() {
		return _service;
	}

	private static volatile SessionTeacherLocalService _service;

}