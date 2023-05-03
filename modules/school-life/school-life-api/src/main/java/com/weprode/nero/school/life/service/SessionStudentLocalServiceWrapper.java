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
	public com.weprode.nero.school.life.model.SessionStudent addSessionStudent(
		com.weprode.nero.school.life.model.SessionStudent sessionStudent) {

		return _sessionStudentLocalService.addSessionStudent(sessionStudent);
	}

	@Override
	public com.weprode.nero.school.life.model.SessionStudent
		addStudentToSession(
			long teacherId, long studentId, long schoollifeSessionId,
			String comment, String subject, boolean notifyParents) {

		return _sessionStudentLocalService.addStudentToSession(
			teacherId, studentId, schoollifeSessionId, comment, subject,
			notifyParents);
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
	 * @param sessionStudentPK the primary key for the new session student
	 * @return the new session student
	 */
	@Override
	public com.weprode.nero.school.life.model.SessionStudent
		createSessionStudent(
			com.weprode.nero.school.life.service.persistence.SessionStudentPK
				sessionStudentPK) {

		return _sessionStudentLocalService.createSessionStudent(
			sessionStudentPK);
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

	@Override
	public boolean deleteSession(long schoollifeSessionId) {
		return _sessionStudentLocalService.deleteSession(schoollifeSessionId);
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
	public com.weprode.nero.school.life.model.SessionStudent
		deleteSessionStudent(
			com.weprode.nero.school.life.model.SessionStudent sessionStudent) {

		return _sessionStudentLocalService.deleteSessionStudent(sessionStudent);
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
	@Override
	public com.weprode.nero.school.life.model.SessionStudent
			deleteSessionStudent(
				com.weprode.nero.school.life.service.persistence.
					SessionStudentPK sessionStudentPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionStudentLocalService.deleteSessionStudent(
			sessionStudentPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.SessionStudentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.SessionStudentModelImpl</code>.
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
	public com.weprode.nero.school.life.model.SessionStudent
		fetchSessionStudent(
			com.weprode.nero.school.life.service.persistence.SessionStudentPK
				sessionStudentPK) {

		return _sessionStudentLocalService.fetchSessionStudent(
			sessionStudentPK);
	}

	@Override
	public java.util.List<Long> getAbsentStudents(long schoollifeSessionId) {
		return _sessionStudentLocalService.getAbsentStudents(
			schoollifeSessionId);
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

	@Override
	public int getNbRegisteredStudents(long schoollifeSessionId) {
		return _sessionStudentLocalService.getNbRegisteredStudents(
			schoollifeSessionId);
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

	@Override
	public java.util.List<com.weprode.nero.school.life.model.SessionStudent>
		getSessionMembers(long schoollifeSessionId) {

		return _sessionStudentLocalService.getSessionMembers(
			schoollifeSessionId);
	}

	/**
	 * Returns the session student with the primary key.
	 *
	 * @param sessionStudentPK the primary key of the session student
	 * @return the session student
	 * @throws PortalException if a session student with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.school.life.model.SessionStudent getSessionStudent(
			com.weprode.nero.school.life.service.persistence.SessionStudentPK
				sessionStudentPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionStudentLocalService.getSessionStudent(sessionStudentPK);
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
	@Override
	public java.util.List<com.weprode.nero.school.life.model.SessionStudent>
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

	@Override
	public org.json.JSONArray getStudentSessions(
		long studentId, java.util.Date minDate, java.util.Date maxDate) {

		return _sessionStudentLocalService.getStudentSessions(
			studentId, minDate, maxDate);
	}

	@Override
	public org.json.JSONArray getStudentSessions(
		long studentId, java.util.Date minDate, java.util.Date maxDate,
		Boolean withFired) {

		return _sessionStudentLocalService.getStudentSessions(
			studentId, minDate, maxDate, withFired);
	}

	@Override
	public boolean markStudentPresent(
		long schoollifeSessionId, long studentId, boolean isPresent) {

		return _sessionStudentLocalService.markStudentPresent(
			schoollifeSessionId, studentId, isPresent);
	}

	@Override
	public boolean registerStudentToSession(
		long teacherId, long studentId, long schoollifeSessionId,
		String comment, String replayTestSubject, boolean notifyParents) {

		return _sessionStudentLocalService.registerStudentToSession(
			teacherId, studentId, schoollifeSessionId, comment,
			replayTestSubject, notifyParents);
	}

	@Override
	public boolean removeStudentFromSession(
		long studentId, long schoollifeSessionId) {

		return _sessionStudentLocalService.removeStudentFromSession(
			studentId, schoollifeSessionId);
	}

	@Override
	public boolean unregisterStudentToSession(
		long studentId, long schoollifeSessionId, boolean allSessions) {

		return _sessionStudentLocalService.unregisterStudentToSession(
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
	@Override
	public com.weprode.nero.school.life.model.SessionStudent
		updateSessionStudent(
			com.weprode.nero.school.life.model.SessionStudent sessionStudent) {

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