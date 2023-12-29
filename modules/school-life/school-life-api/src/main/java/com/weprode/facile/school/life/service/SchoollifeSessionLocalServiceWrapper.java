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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SchoollifeSessionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSessionLocalService
 * @generated
 */
public class SchoollifeSessionLocalServiceWrapper
	implements SchoollifeSessionLocalService,
			   ServiceWrapper<SchoollifeSessionLocalService> {

	public SchoollifeSessionLocalServiceWrapper() {
		this(null);
	}

	public SchoollifeSessionLocalServiceWrapper(
		SchoollifeSessionLocalService schoollifeSessionLocalService) {

		_schoollifeSessionLocalService = schoollifeSessionLocalService;
	}

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
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSession
		addSchoollifeSession(
			com.weprode.facile.school.life.model.SchoollifeSession
				schoollifeSession) {

		return _schoollifeSessionLocalService.addSchoollifeSession(
			schoollifeSession);
	}

	@Override
	public com.weprode.facile.school.life.model.SchoollifeSession addSession(
		long schoollifeSlotId, long schoolId, java.util.Date startDate,
		java.util.Date endDate, int type) {

		return _schoollifeSessionLocalService.addSession(
			schoollifeSlotId, schoolId, startDate, endDate, type);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSessionLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new schoollife session with the primary key. Does not add the schoollife session to the database.
	 *
	 * @param schoollifeSessionId the primary key for the new schoollife session
	 * @return the new schoollife session
	 */
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSession
		createSchoollifeSession(long schoollifeSessionId) {

		return _schoollifeSessionLocalService.createSchoollifeSession(
			schoollifeSessionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSessionLocalService.deletePersistedModel(
			persistedModel);
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
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSession
			deleteSchoollifeSession(long schoollifeSessionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSessionLocalService.deleteSchoollifeSession(
			schoollifeSessionId);
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
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSession
		deleteSchoollifeSession(
			com.weprode.facile.school.life.model.SchoollifeSession
				schoollifeSession) {

		return _schoollifeSessionLocalService.deleteSchoollifeSession(
			schoollifeSession);
	}

	@Override
	public boolean deleteSession(long schoollifeSessionId) {
		return _schoollifeSessionLocalService.deleteSession(
			schoollifeSessionId);
	}

	@Override
	public boolean deleteSlotSessions(
		long schoollifeSlotId, java.util.Date startDate,
		java.util.Date endDate) {

		return _schoollifeSessionLocalService.deleteSlotSessions(
			schoollifeSlotId, startDate, endDate);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _schoollifeSessionLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _schoollifeSessionLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _schoollifeSessionLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _schoollifeSessionLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _schoollifeSessionLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _schoollifeSessionLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _schoollifeSessionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _schoollifeSessionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.school.life.model.SchoollifeSession
		fetchSchoollifeSession(long schoollifeSessionId) {

		return _schoollifeSessionLocalService.fetchSchoollifeSession(
			schoollifeSessionId);
	}

	@Override
	public org.json.JSONObject formatSchoollifeSession(
			com.weprode.facile.school.life.model.SchoollifeSession session,
			com.liferay.portal.kernel.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSessionLocalService.formatSchoollifeSession(
			session, user);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _schoollifeSessionLocalService.getActionableDynamicQuery();
	}

	@Override
	public String getColorFromSchoollifeType(int schoollifeType) {
		return _schoollifeSessionLocalService.getColorFromSchoollifeType(
			schoollifeType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _schoollifeSessionLocalService.
			getIndexableActionableDynamicQuery();
	}

	@Override
	public com.weprode.facile.school.life.model.SchoollifeSession
		getLastSession(long schoollifeSlotId) {

		return _schoollifeSessionLocalService.getLastSession(schoollifeSlotId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _schoollifeSessionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSessionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the schoollife session with the primary key.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session
	 * @throws PortalException if a schoollife session with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSession
			getSchoollifeSession(long schoollifeSessionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSessionLocalService.getSchoollifeSession(
			schoollifeSessionId);
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
	@Override
	public java.util.List
		<com.weprode.facile.school.life.model.SchoollifeSession>
			getSchoollifeSessions(int start, int end) {

		return _schoollifeSessionLocalService.getSchoollifeSessions(start, end);
	}

	/**
	 * Returns the number of schoollife sessions.
	 *
	 * @return the number of schoollife sessions
	 */
	@Override
	public int getSchoollifeSessionsCount() {
		return _schoollifeSessionLocalService.getSchoollifeSessionsCount();
	}

	@Override
	public String getSessionName(long schoollifeSessionId) {
		return _schoollifeSessionLocalService.getSessionName(
			schoollifeSessionId);
	}

	@Override
	public java.util.List
		<com.weprode.facile.school.life.model.SchoollifeSession>
			getSlotSessions(long schoollifeSlotId) {

		return _schoollifeSessionLocalService.getSlotSessions(schoollifeSlotId);
	}

	@Override
	public org.json.JSONArray getTeacherSessions(
		long teacherId, java.util.Date minDate, java.util.Date maxDate) {

		return _schoollifeSessionLocalService.getTeacherSessions(
			teacherId, minDate, maxDate);
	}

	@Override
	public java.util.List
		<com.weprode.facile.school.life.model.SchoollifeSession>
			getUnnotifiedSessions(
				int type, java.util.Date startDate, java.util.Date endDate) {

		return _schoollifeSessionLocalService.getUnnotifiedSessions(
			type, startDate, endDate);
	}

	@Override
	public java.util.List
		<com.weprode.facile.school.life.model.SchoollifeSession>
			getWeekSessions(long schoolId, int type, java.util.Date fromDate) {

		return _schoollifeSessionLocalService.getWeekSessions(
			schoolId, type, fromDate);
	}

	@Override
	public void runAbsenceNotifications() {
		_schoollifeSessionLocalService.runAbsenceNotifications();
	}

	@Override
	public boolean setRollCalled(long schoollifeSessionId) {
		return _schoollifeSessionLocalService.setRollCalled(
			schoollifeSessionId);
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
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSession
		updateSchoollifeSession(
			com.weprode.facile.school.life.model.SchoollifeSession
				schoollifeSession) {

		return _schoollifeSessionLocalService.updateSchoollifeSession(
			schoollifeSession);
	}

	@Override
	public SchoollifeSessionLocalService getWrappedService() {
		return _schoollifeSessionLocalService;
	}

	@Override
	public void setWrappedService(
		SchoollifeSessionLocalService schoollifeSessionLocalService) {

		_schoollifeSessionLocalService = schoollifeSessionLocalService;
	}

	private SchoollifeSessionLocalService _schoollifeSessionLocalService;

}