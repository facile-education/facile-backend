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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CDTSessionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CDTSessionLocalService
 * @generated
 */
public class CDTSessionLocalServiceWrapper
	implements CDTSessionLocalService, ServiceWrapper<CDTSessionLocalService> {

	public CDTSessionLocalServiceWrapper(
		CDTSessionLocalService cdtSessionLocalService) {

		_cdtSessionLocalService = cdtSessionLocalService;
	}

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
	@Override
	public com.weprode.nero.schedule.model.CDTSession addCDTSession(
		com.weprode.nero.schedule.model.CDTSession cdtSession) {

		return _cdtSessionLocalService.addCDTSession(cdtSession);
	}

	@Override
	public org.json.JSONArray convertSessions(
		java.util.List<com.weprode.nero.schedule.model.CDTSession> sessions,
		com.liferay.portal.kernel.model.User user) {

		return _cdtSessionLocalService.convertSessions(sessions, user);
	}

	/**
	 * Creates a new cdt session with the primary key. Does not add the cdt session to the database.
	 *
	 * @param sessionId the primary key for the new cdt session
	 * @return the new cdt session
	 */
	@Override
	public com.weprode.nero.schedule.model.CDTSession createCDTSession(
		long sessionId) {

		return _cdtSessionLocalService.createCDTSession(sessionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cdtSessionLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean createRecurrentSessions(
		long groupId, String subject, String room, java.util.Date startDate,
		java.util.Date endDate, int slot, java.util.List<Long> teacherIdList) {

		return _cdtSessionLocalService.createRecurrentSessions(
			groupId, subject, room, startDate, endDate, slot, teacherIdList);
	}

	@Override
	public com.weprode.nero.schedule.model.CDTSession createSession(
			long groupId, String subject, java.util.Date startDate,
			java.util.Date endDate, int slot,
			java.util.List<Long> teacherIdList, String room,
			String fullCoursName, boolean isManual)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _cdtSessionLocalService.createSession(
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
	@Override
	public com.weprode.nero.schedule.model.CDTSession deleteCDTSession(
		com.weprode.nero.schedule.model.CDTSession cdtSession) {

		return _cdtSessionLocalService.deleteCDTSession(cdtSession);
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
	@Override
	public com.weprode.nero.schedule.model.CDTSession deleteCDTSession(
			long sessionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cdtSessionLocalService.deleteCDTSession(sessionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cdtSessionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteSessionAndDependencies(long sessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_cdtSessionLocalService.deleteSessionAndDependencies(sessionId);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _cdtSessionLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _cdtSessionLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cdtSessionLocalService.dynamicQuery();
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

		return _cdtSessionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.CDTSessionModelImpl</code>.
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

		return _cdtSessionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.CDTSessionModelImpl</code>.
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

		return _cdtSessionLocalService.dynamicQuery(
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

		return _cdtSessionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _cdtSessionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.CDTSession fetchCDTSession(
		long sessionId) {

		return _cdtSessionLocalService.fetchCDTSession(sessionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _cdtSessionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the cdt session with the primary key.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session
	 * @throws PortalException if a cdt session with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.CDTSession getCDTSession(
			long sessionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cdtSessionLocalService.getCDTSession(sessionId);
	}

	/**
	 * Returns a range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @return the range of cdt sessions
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getCDTSessions(int start, int end) {

		return _cdtSessionLocalService.getCDTSessions(start, end);
	}

	/**
	 * Returns the number of cdt sessions.
	 *
	 * @return the number of cdt sessions
	 */
	@Override
	public int getCDTSessionsCount() {
		return _cdtSessionLocalService.getCDTSessionsCount();
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getGroupSessions(
			long groupId, java.util.Date minDate, java.util.Date maxDate,
			boolean includeSubClasses) {

		return _cdtSessionLocalService.getGroupSessions(
			groupId, minDate, maxDate, includeSubClasses);
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getGroupsSessionActivity(
			long userId, java.util.List<Long> groupIds, java.util.Date minDate,
			java.util.Date maxDate) {

		return _cdtSessionLocalService.getGroupsSessionActivity(
			userId, groupIds, minDate, maxDate);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _cdtSessionLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getNextSessions(
			com.liferay.portal.kernel.model.User user, long sessionId) {

		return _cdtSessionLocalService.getNextSessions(user, sessionId);
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getNextStudentDaySessions(
			long studentId, java.util.Date targetDate, boolean goForward) {

		return _cdtSessionLocalService.getNextStudentDaySessions(
			studentId, targetDate, goForward);
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getNextTeacherDaySessions(
			long teacherId, java.util.Date targetDate, boolean goForward) {

		return _cdtSessionLocalService.getNextTeacherDaySessions(
			teacherId, targetDate, goForward);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cdtSessionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cdtSessionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Get the session for a school id that begin between 2 dates
	 * Used by synchronization process
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
			getSchoolSessions(
				Long schoolId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _cdtSessionLocalService.getSchoolSessions(
			schoolId, startDate, endDate);
	}

	/**
	 * Get session student list
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getSessionStudents(long sessionId) {

		return _cdtSessionLocalService.getSessionStudents(sessionId);
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getStudentSessions(
			long studentId, java.util.Date minDate, java.util.Date maxDate) {

		return _cdtSessionLocalService.getStudentSessions(
			studentId, minDate, maxDate);
	}

	/**
	 * Get all sessions for a given student in a given date range, that are not attached to a group (eg. subClass)
	 * Returns empty for GVA
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getStudentSpecificSessions(
			long studentId, java.util.Date minDate, java.util.Date maxDate) {

		return _cdtSessionLocalService.getStudentSpecificSessions(
			studentId, minDate, maxDate);
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.CDTSession>
		getTeacherSessions(
			long teacherId, java.util.Date minDate, java.util.Date maxDate) {

		return _cdtSessionLocalService.getTeacherSessions(
			teacherId, minDate, maxDate);
	}

	@Override
	public boolean hasUserSession(
		com.liferay.portal.kernel.model.User user, long sessionId) {

		return _cdtSessionLocalService.hasUserSession(user, sessionId);
	}

	@Override
	public boolean isSession(long itemId) {
		return _cdtSessionLocalService.isSession(itemId);
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
	@Override
	public com.weprode.nero.schedule.model.CDTSession updateCDTSession(
		com.weprode.nero.schedule.model.CDTSession cdtSession) {

		return _cdtSessionLocalService.updateCDTSession(cdtSession);
	}

	@Override
	public CDTSessionLocalService getWrappedService() {
		return _cdtSessionLocalService;
	}

	@Override
	public void setWrappedService(
		CDTSessionLocalService cdtSessionLocalService) {

		_cdtSessionLocalService = cdtSessionLocalService;
	}

	private CDTSessionLocalService _cdtSessionLocalService;

}