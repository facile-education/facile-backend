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

package com.weprode.facile.schedule.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.schedule.model.CDTSession;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CDTSession. This utility wraps
 * <code>com.weprode.facile.schedule.service.impl.CDTSessionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CDTSessionLocalService
 * @generated
 */
public class CDTSessionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.schedule.service.impl.CDTSessionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cdt session to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cdtSession the cdt session
	 * @return the cdt session that was added
	 */
	public static CDTSession addCDTSession(CDTSession cdtSession) {
		return getService().addCDTSession(cdtSession);
	}

	public static org.json.JSONArray convertSessions(
		List<CDTSession> sessions, com.liferay.portal.kernel.model.User user) {

		return getService().convertSessions(sessions, user);
	}

	/**
	 * Creates a new cdt session with the primary key. Does not add the cdt session to the database.
	 *
	 * @param sessionId the primary key for the new cdt session
	 * @return the new cdt session
	 */
	public static CDTSession createCDTSession(long sessionId) {
		return getService().createCDTSession(sessionId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static boolean createRecurrentSessions(
		long groupId, String subject, String room, java.util.Date startDate,
		java.util.Date endDate, int slot, List<Long> teacherIdList) {

		return getService().createRecurrentSessions(
			groupId, subject, room, startDate, endDate, slot, teacherIdList);
	}

	public static CDTSession createSession(
			long groupId, String subject, java.util.Date startDate,
			java.util.Date endDate, int slot, List<Long> teacherIdList,
			String room, String fullCoursName, boolean isManual)
		throws SystemException {

		return getService().createSession(
			groupId, subject, startDate, endDate, slot, teacherIdList, room,
			fullCoursName, isManual);
	}

	/**
	 * Deletes the cdt session from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cdtSession the cdt session
	 * @return the cdt session that was removed
	 */
	public static CDTSession deleteCDTSession(CDTSession cdtSession) {
		return getService().deleteCDTSession(cdtSession);
	}

	/**
	 * Deletes the cdt session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session that was removed
	 * @throws PortalException if a cdt session with the primary key could not be found
	 */
	public static CDTSession deleteCDTSession(long sessionId)
		throws PortalException {

		return getService().deleteCDTSession(sessionId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static void deleteSessionAndDependencies(long sessionId)
		throws PortalException, SystemException {

		getService().deleteSessionAndDependencies(sessionId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.CDTSessionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.CDTSessionModelImpl</code>.
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

	public static CDTSession fetchCDTSession(long sessionId) {
		return getService().fetchCDTSession(sessionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the cdt session with the primary key.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session
	 * @throws PortalException if a cdt session with the primary key could not be found
	 */
	public static CDTSession getCDTSession(long sessionId)
		throws PortalException {

		return getService().getCDTSession(sessionId);
	}

	/**
	 * Returns a range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @return the range of cdt sessions
	 */
	public static List<CDTSession> getCDTSessions(int start, int end) {
		return getService().getCDTSessions(start, end);
	}

	/**
	 * Returns the number of cdt sessions.
	 *
	 * @return the number of cdt sessions
	 */
	public static int getCDTSessionsCount() {
		return getService().getCDTSessionsCount();
	}

	public static List<CDTSession> getGroupSessions(
		long groupId, java.util.Date minDate, java.util.Date maxDate,
		boolean includeSubClasses) {

		return getService().getGroupSessions(
			groupId, minDate, maxDate, includeSubClasses);
	}

	public static List<CDTSession> getGroupsSessionActivity(
		long userId, List<Long> groupIds, java.util.Date minDate,
		java.util.Date maxDate) {

		return getService().getGroupsSessionActivity(
			userId, groupIds, minDate, maxDate);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static List<CDTSession> getNextSessions(
		com.liferay.portal.kernel.model.User user, long sessionId) {

		return getService().getNextSessions(user, sessionId);
	}

	public static List<CDTSession> getNextStudentDaySessions(
		long studentId, java.util.Date targetDate, boolean goForward) {

		return getService().getNextStudentDaySessions(
			studentId, targetDate, goForward);
	}

	public static List<CDTSession> getNextTeacherDaySessions(
		long teacherId, java.util.Date targetDate, boolean goForward) {

		return getService().getNextTeacherDaySessions(
			teacherId, targetDate, goForward);
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
	 * Get the session for a school id that begin between 2 dates
	 * Used by synchronization process
	 */
	public static List<CDTSession> getSchoolSessions(
			Long schoolId, java.util.Date startDate, java.util.Date endDate)
		throws SystemException {

		return getService().getSchoolSessions(schoolId, startDate, endDate);
	}

	/**
	 * Get session student list
	 */
	public static List<com.liferay.portal.kernel.model.User> getSessionStudents(
		long sessionId) {

		return getService().getSessionStudents(sessionId);
	}

	public static List<CDTSession> getStudentSessions(
		long studentId, java.util.Date minDate, java.util.Date maxDate) {

		return getService().getStudentSessions(studentId, minDate, maxDate);
	}

	/**
	 * Get all sessions for a given student in a given date range, that are not attached to a group (eg. subClass)
	 * Returns empty for GVA
	 */
	public static List<CDTSession> getStudentSpecificSessions(
		long studentId, java.util.Date minDate, java.util.Date maxDate) {

		return getService().getStudentSpecificSessions(
			studentId, minDate, maxDate);
	}

	public static List<CDTSession> getTeacherSessions(
		long teacherId, java.util.Date minDate, java.util.Date maxDate) {

		return getService().getTeacherSessions(teacherId, minDate, maxDate);
	}

	public static boolean hasUserSession(
		com.liferay.portal.kernel.model.User user, long sessionId) {

		return getService().hasUserSession(user, sessionId);
	}

	public static boolean isSession(long itemId) {
		return getService().isSession(itemId);
	}

	/**
	 * Updates the cdt session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cdtSession the cdt session
	 * @return the cdt session that was updated
	 */
	public static CDTSession updateCDTSession(CDTSession cdtSession) {
		return getService().updateCDTSession(cdtSession);
	}

	public static CDTSessionLocalService getService() {
		return _service;
	}

	private static volatile CDTSessionLocalService _service;

}