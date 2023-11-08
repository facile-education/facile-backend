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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SessionStudentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SessionStudentLocalService
 * @generated
 */
public class SessionStudentLocalServiceWrapper
	implements ServiceWrapper<SessionStudentLocalService>,
			   SessionStudentLocalService {

	public SessionStudentLocalServiceWrapper() {
		this(null);
	}

	public SessionStudentLocalServiceWrapper(
		SessionStudentLocalService sessionStudentLocalService) {

		_sessionStudentLocalService = sessionStudentLocalService;
	}

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
	@Override
	public com.weprode.facile.schedule.model.SessionStudent addSessionStudent(
		com.weprode.facile.schedule.model.SessionStudent sessionStudent) {

		return _sessionStudentLocalService.addSessionStudent(sessionStudent);
	}

	/**
	 * Add student to a session
	 */
	@Override
	public com.weprode.facile.schedule.model.SessionStudent addStudentToSession(
			long sessionId, long studentId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionStudentLocalService.addStudentToSession(
			sessionId, studentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionStudentLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new session student with the primary key. Does not add the session student to the database.
	 *
	 * @param sessionStudentId the primary key for the new session student
	 * @return the new session student
	 */
	@Override
	public com.weprode.facile.schedule.model.SessionStudent
		createSessionStudent(long sessionStudentId) {

		return _sessionStudentLocalService.createSessionStudent(
			sessionStudentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionStudentLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the session student with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionStudentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student that was removed
	 * @throws PortalException if a session student with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.schedule.model.SessionStudent
			deleteSessionStudent(long sessionStudentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionStudentLocalService.deleteSessionStudent(
			sessionStudentId);
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
	@Override
	public com.weprode.facile.schedule.model.SessionStudent
		deleteSessionStudent(
			com.weprode.facile.schedule.model.SessionStudent sessionStudent) {

		return _sessionStudentLocalService.deleteSessionStudent(sessionStudent);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _sessionStudentLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _sessionStudentLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sessionStudentLocalService.dynamicQuery();
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

		return _sessionStudentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.SessionStudentModelImpl</code>.
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

		return _sessionStudentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.SessionStudentModelImpl</code>.
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

		return _sessionStudentLocalService.dynamicQuery(
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

		return _sessionStudentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _sessionStudentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.schedule.model.SessionStudent fetchSessionStudent(
		long sessionStudentId) {

		return _sessionStudentLocalService.fetchSessionStudent(
			sessionStudentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _sessionStudentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _sessionStudentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sessionStudentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionStudentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the session student with the primary key.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student
	 * @throws PortalException if a session student with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.schedule.model.SessionStudent getSessionStudent(
			long sessionStudentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionStudentLocalService.getSessionStudent(sessionStudentId);
	}

	/**
	 * Returns a range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of session students
	 */
	@Override
	public java.util.List<com.weprode.facile.schedule.model.SessionStudent>
		getSessionStudents(int start, int end) {

		return _sessionStudentLocalService.getSessionStudents(start, end);
	}

	/**
	 * Returns the number of session students.
	 *
	 * @return the number of session students
	 */
	@Override
	public int getSessionStudentsCount() {
		return _sessionStudentLocalService.getSessionStudentsCount();
	}

	/**
	 * Returns all students involved in a given sessionId
	 */
	@Override
	public java.util.List<Long> getStudentIdsBySession(long sessionId) {
		return _sessionStudentLocalService.getStudentIdsBySession(sessionId);
	}

	/**
	 * Returns all students involved in a given sessionId
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getStudentsBySession(long sessionId) {

		return _sessionStudentLocalService.getStudentsBySession(sessionId);
	}

	@Override
	public boolean hasStudentSession(long studentId, long sessionId) {
		return _sessionStudentLocalService.hasStudentSession(
			studentId, sessionId);
	}

	@Override
	public boolean removeBySessionId(long sessionId) {
		return _sessionStudentLocalService.removeBySessionId(sessionId);
	}

	/**
	 * Remove a student from a session
	 */
	@Override
	public boolean removeStudentFromSession(long sessionId, long studentId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionStudentLocalService.removeStudentFromSession(
			sessionId, studentId);
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
	@Override
	public com.weprode.facile.schedule.model.SessionStudent
		updateSessionStudent(
			com.weprode.facile.schedule.model.SessionStudent sessionStudent) {

		return _sessionStudentLocalService.updateSessionStudent(sessionStudent);
	}

	@Override
	public SessionStudentLocalService getWrappedService() {
		return _sessionStudentLocalService;
	}

	@Override
	public void setWrappedService(
		SessionStudentLocalService sessionStudentLocalService) {

		_sessionStudentLocalService = sessionStudentLocalService;
	}

	private SessionStudentLocalService _sessionStudentLocalService;

}