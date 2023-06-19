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
 * Provides a wrapper for {@link SessionTeacherLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacherLocalService
 * @generated
 */
public class SessionTeacherLocalServiceWrapper
	implements ServiceWrapper<SessionTeacherLocalService>,
			   SessionTeacherLocalService {

	public SessionTeacherLocalServiceWrapper(
		SessionTeacherLocalService sessionTeacherLocalService) {

		_sessionTeacherLocalService = sessionTeacherLocalService;
	}

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
	@Override
	public com.weprode.nero.schedule.model.SessionTeacher addSessionTeacher(
		com.weprode.nero.schedule.model.SessionTeacher sessionTeacher) {

		return _sessionTeacherLocalService.addSessionTeacher(sessionTeacher);
	}

	@Override
	public boolean canSaveTeacherSubstitutes(
		com.liferay.portal.kernel.model.User user) {

		return _sessionTeacherLocalService.canSaveTeacherSubstitutes(user);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionTeacherLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new session teacher with the primary key. Does not add the session teacher to the database.
	 *
	 * @param sessionTeacherId the primary key for the new session teacher
	 * @return the new session teacher
	 */
	@Override
	public com.weprode.nero.schedule.model.SessionTeacher createSessionTeacher(
		long sessionTeacherId) {

		return _sessionTeacherLocalService.createSessionTeacher(
			sessionTeacherId);
	}

	@Override
	public com.weprode.nero.schedule.model.SessionTeacher createSessionTeacher(
			long sessionId, long teacherId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionTeacherLocalService.createSessionTeacher(
			sessionId, teacherId);
	}

	@Override
	public com.weprode.nero.schedule.model.SessionTeacher createSessionTeacher(
			long sessionId, long teacherId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionTeacherLocalService.createSessionTeacher(
			sessionId, teacherId, status);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionTeacherLocalService.deletePersistedModel(persistedModel);
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
	@Override
	public com.weprode.nero.schedule.model.SessionTeacher deleteSessionTeacher(
			long sessionTeacherId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionTeacherLocalService.deleteSessionTeacher(
			sessionTeacherId);
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
	@Override
	public com.weprode.nero.schedule.model.SessionTeacher deleteSessionTeacher(
		com.weprode.nero.schedule.model.SessionTeacher sessionTeacher) {

		return _sessionTeacherLocalService.deleteSessionTeacher(sessionTeacher);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _sessionTeacherLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _sessionTeacherLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sessionTeacherLocalService.dynamicQuery();
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

		return _sessionTeacherLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _sessionTeacherLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _sessionTeacherLocalService.dynamicQuery(
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

		return _sessionTeacherLocalService.dynamicQueryCount(dynamicQuery);
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

		return _sessionTeacherLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.SessionTeacher fetchSessionTeacher(
		long sessionTeacherId) {

		return _sessionTeacherLocalService.fetchSessionTeacher(
			sessionTeacherId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _sessionTeacherLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _sessionTeacherLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.model.User getLastEditor(
			long sessionId, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _sessionTeacherLocalService.getLastEditor(
			sessionId, modifiedDate);
	}

	@Override
	public java.util.Date getLastModificationDate(
		long sessionId, java.util.Date minDate, java.util.Date maxDate) {

		return _sessionTeacherLocalService.getLastModificationDate(
			sessionId, minDate, maxDate);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sessionTeacherLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionTeacherLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public String getPrivateNotes(long teacherId, long sessionId) {
		return _sessionTeacherLocalService.getPrivateNotes(
			teacherId, sessionId);
	}

	/**
	 * Returns the session teacher with the primary key.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher
	 * @throws PortalException if a session teacher with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.SessionTeacher getSessionTeacher(
			long sessionTeacherId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionTeacherLocalService.getSessionTeacher(sessionTeacherId);
	}

	@Override
	public com.weprode.nero.schedule.model.SessionTeacher getSessionTeacher(
		long sessionId, long teacherId) {

		return _sessionTeacherLocalService.getSessionTeacher(
			sessionId, teacherId);
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
	@Override
	public java.util.List<com.weprode.nero.schedule.model.SessionTeacher>
		getSessionTeachers(int start, int end) {

		return _sessionTeacherLocalService.getSessionTeachers(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.SessionTeacher>
		getSessionTeachers(long sessionId) {

		return _sessionTeacherLocalService.getSessionTeachers(sessionId);
	}

	/**
	 * Returns the number of session teachers.
	 *
	 * @return the number of session teachers
	 */
	@Override
	public int getSessionTeachersCount() {
		return _sessionTeacherLocalService.getSessionTeachersCount();
	}

	@Override
	public java.util.List<Long> getTeacherIds(long sessionId) {
		return _sessionTeacherLocalService.getTeacherIds(sessionId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getTeachers(
		long sessionId) {

		return _sessionTeacherLocalService.getTeachers(sessionId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getTeachers(
		long sessionId, boolean includeSubstitutedTeachers) {

		return _sessionTeacherLocalService.getTeachers(
			sessionId, includeSubstitutedTeachers);
	}

	/**
	 * Returns true if the given teacher teaches the given session
	 */
	@Override
	public boolean hasTeacherSession(long teacherId, long sessionId) {
		return _sessionTeacherLocalService.hasTeacherSession(
			teacherId, sessionId);
	}

	@Override
	public boolean isSubstituted(long teacherId, long sessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.schedule.exception.
				   NoSuchSessionTeacherException {

		return _sessionTeacherLocalService.isSubstituted(teacherId, sessionId);
	}

	@Override
	public boolean removeBySessionId(long sessionId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionTeacherLocalService.removeBySessionId(sessionId);
	}

	@Override
	public com.weprode.nero.schedule.model.SessionTeacher removeSubstitute(
			long sessionId, long substituteId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionTeacherLocalService.removeSubstitute(
			sessionId, substituteId);
	}

	@Override
	public void saveNotes(long teacherId, long sessionId, String notes) {
		_sessionTeacherLocalService.saveNotes(teacherId, sessionId, notes);
	}

	@Override
	public com.weprode.nero.schedule.model.SessionTeacher substituteTeacher(
			long teacherId, long sessionId, long substituteId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionTeacherLocalService.substituteTeacher(
			teacherId, sessionId, substituteId);
	}

	@Override
	public void updateModificationDate(long teacherId, long sessionId) {
		_sessionTeacherLocalService.updateModificationDate(
			teacherId, sessionId);
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
	@Override
	public com.weprode.nero.schedule.model.SessionTeacher updateSessionTeacher(
		com.weprode.nero.schedule.model.SessionTeacher sessionTeacher) {

		return _sessionTeacherLocalService.updateSessionTeacher(sessionTeacher);
	}

	@Override
	public com.weprode.nero.schedule.model.SessionTeacher updateSessionTeacher(
			com.weprode.nero.schedule.model.SessionTeacher sessionTeacher,
			int status, long substituteId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionTeacherLocalService.updateSessionTeacher(
			sessionTeacher, status, substituteId);
	}

	/**
	 * Update the list of teachers for a given session
	 */
	@Override
	public boolean updateTeacherListForSession(
			long sessionId, java.util.List<Long> newTeacherIdList)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionTeacherLocalService.updateTeacherListForSession(
			sessionId, newTeacherIdList);
	}

	@Override
	public SessionTeacherLocalService getWrappedService() {
		return _sessionTeacherLocalService;
	}

	@Override
	public void setWrappedService(
		SessionTeacherLocalService sessionTeacherLocalService) {

		_sessionTeacherLocalService = sessionTeacherLocalService;
	}

	private SessionTeacherLocalService _sessionTeacherLocalService;

}