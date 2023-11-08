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

package com.weprode.facile.school.life.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.school.life.model.SchoollifeSession;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for SchoollifeSession. This utility wraps
 * <code>com.weprode.facile.school.life.service.impl.SchoollifeSessionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSessionLocalService
 * @generated
 */
public class SchoollifeSessionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.school.life.service.impl.SchoollifeSessionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the schoollife session to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSession the schoollife session
	 * @return the schoollife session that was added
	 */
	public static SchoollifeSession addSchoollifeSession(
		SchoollifeSession schoollifeSession) {

		return getService().addSchoollifeSession(schoollifeSession);
	}

	public static SchoollifeSession addSession(
		long schoollifeSlotId, long schoolId, java.util.Date startDate,
		java.util.Date endDate, int type) {

		return getService().addSession(
			schoollifeSlotId, schoolId, startDate, endDate, type);
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
	 * Creates a new schoollife session with the primary key. Does not add the schoollife session to the database.
	 *
	 * @param schoollifeSessionId the primary key for the new schoollife session
	 * @return the new schoollife session
	 */
	public static SchoollifeSession createSchoollifeSession(
		long schoollifeSessionId) {

		return getService().createSchoollifeSession(schoollifeSessionId);
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
	 * Deletes the schoollife session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session that was removed
	 * @throws PortalException if a schoollife session with the primary key could not be found
	 */
	public static SchoollifeSession deleteSchoollifeSession(
			long schoollifeSessionId)
		throws PortalException {

		return getService().deleteSchoollifeSession(schoollifeSessionId);
	}

	/**
	 * Deletes the schoollife session from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSession the schoollife session
	 * @return the schoollife session that was removed
	 */
	public static SchoollifeSession deleteSchoollifeSession(
		SchoollifeSession schoollifeSession) {

		return getService().deleteSchoollifeSession(schoollifeSession);
	}

	public static boolean deleteSession(long schoollifeSessionId) {
		return getService().deleteSession(schoollifeSessionId);
	}

	public static boolean deleteSlotSessions(
		long schoollifeSlotId, java.util.Date startDate,
		java.util.Date endDate) {

		return getService().deleteSlotSessions(
			schoollifeSlotId, startDate, endDate);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.school.life.model.impl.SchoollifeSessionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.school.life.model.impl.SchoollifeSessionModelImpl</code>.
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

	public static SchoollifeSession fetchSchoollifeSession(
		long schoollifeSessionId) {

		return getService().fetchSchoollifeSession(schoollifeSessionId);
	}

	public static org.json.JSONObject formatSchoollifeSession(
			SchoollifeSession session,
			com.liferay.portal.kernel.model.User user)
		throws PortalException {

		return getService().formatSchoollifeSession(session, user);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static String getColorFromSchoollifeType(int schoollifeType) {
		return getService().getColorFromSchoollifeType(schoollifeType);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static SchoollifeSession getLastSession(long schoollifeSlotId) {
		return getService().getLastSession(schoollifeSlotId);
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
	 * Returns the schoollife session with the primary key.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session
	 * @throws PortalException if a schoollife session with the primary key could not be found
	 */
	public static SchoollifeSession getSchoollifeSession(
			long schoollifeSessionId)
		throws PortalException {

		return getService().getSchoollifeSession(schoollifeSessionId);
	}

	/**
	 * Returns a range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.school.life.model.impl.SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of schoollife sessions
	 */
	public static List<SchoollifeSession> getSchoollifeSessions(
		int start, int end) {

		return getService().getSchoollifeSessions(start, end);
	}

	/**
	 * Returns the number of schoollife sessions.
	 *
	 * @return the number of schoollife sessions
	 */
	public static int getSchoollifeSessionsCount() {
		return getService().getSchoollifeSessionsCount();
	}

	public static String getSessionName(long schoollifeSessionId) {
		return getService().getSessionName(schoollifeSessionId);
	}

	public static List<SchoollifeSession> getSlotSessions(
		long schoollifeSlotId) {

		return getService().getSlotSessions(schoollifeSlotId);
	}

	public static org.json.JSONArray getTeacherSessions(
		long teacherId, java.util.Date minDate, java.util.Date maxDate) {

		return getService().getTeacherSessions(teacherId, minDate, maxDate);
	}

	public static List<SchoollifeSession> getUnnotifiedSessions(
		int type, java.util.Date startDate, java.util.Date endDate) {

		return getService().getUnnotifiedSessions(type, startDate, endDate);
	}

	public static List<SchoollifeSession> getWeekSessions(
		long schoolId, int type, java.util.Date fromDate) {

		return getService().getWeekSessions(schoolId, type, fromDate);
	}

	public static boolean setRollCalled(long schoollifeSessionId) {
		return getService().setRollCalled(schoollifeSessionId);
	}

	/**
	 * Updates the schoollife session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSession the schoollife session
	 * @return the schoollife session that was updated
	 */
	public static SchoollifeSession updateSchoollifeSession(
		SchoollifeSession schoollifeSession) {

		return getService().updateSchoollifeSession(schoollifeSession);
	}

	public static SchoollifeSessionLocalService getService() {
		return _service;
	}

	private static volatile SchoollifeSessionLocalService _service;

}