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

package com.weprode.facile.course.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SessionContentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SessionContentLocalService
 * @generated
 */
public class SessionContentLocalServiceWrapper
	implements ServiceWrapper<SessionContentLocalService>,
			   SessionContentLocalService {

	public SessionContentLocalServiceWrapper() {
		this(null);
	}

	public SessionContentLocalServiceWrapper(
		SessionContentLocalService sessionContentLocalService) {

		_sessionContentLocalService = sessionContentLocalService;
	}

	@Override
	public com.weprode.facile.course.model.SessionContent addSessionContent(
			long sessionId, long teacherId, String title,
			java.util.Date publicationDate, boolean isDraft)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _sessionContentLocalService.addSessionContent(
			sessionId, teacherId, title, publicationDate, isDraft);
	}

	/**
	 * Adds the session content to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionContent the session content
	 * @return the session content that was added
	 */
	@Override
	public com.weprode.facile.course.model.SessionContent addSessionContent(
		com.weprode.facile.course.model.SessionContent sessionContent) {

		return _sessionContentLocalService.addSessionContent(sessionContent);
	}

	@Override
	public String convertContentAsHtml(long sessionId) {
		return _sessionContentLocalService.convertContentAsHtml(sessionId);
	}

	@Override
	public int countSchoolSessionContents(
		long schoolId, java.util.Date minDate, java.util.Date maxDate) {

		return _sessionContentLocalService.countSchoolSessionContents(
			schoolId, minDate, maxDate);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionContentLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new session content with the primary key. Does not add the session content to the database.
	 *
	 * @param sessionId the primary key for the new session content
	 * @return the new session content
	 */
	@Override
	public com.weprode.facile.course.model.SessionContent createSessionContent(
		long sessionId) {

		return _sessionContentLocalService.createSessionContent(sessionId);
	}

	@Override
	public void deleteContent(long sessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_sessionContentLocalService.deleteContent(sessionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionContentLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the session content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content that was removed
	 * @throws PortalException if a session content with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.course.model.SessionContent deleteSessionContent(
			long sessionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionContentLocalService.deleteSessionContent(sessionId);
	}

	/**
	 * Deletes the session content from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionContent the session content
	 * @return the session content that was removed
	 */
	@Override
	public com.weprode.facile.course.model.SessionContent deleteSessionContent(
		com.weprode.facile.course.model.SessionContent sessionContent) {

		return _sessionContentLocalService.deleteSessionContent(sessionContent);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _sessionContentLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _sessionContentLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sessionContentLocalService.dynamicQuery();
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

		return _sessionContentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.SessionContentModelImpl</code>.
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

		return _sessionContentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.SessionContentModelImpl</code>.
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

		return _sessionContentLocalService.dynamicQuery(
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

		return _sessionContentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _sessionContentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.course.model.SessionContent fetchSessionContent(
		long sessionId) {

		return _sessionContentLocalService.fetchSessionContent(sessionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _sessionContentLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.facile.course.model.SessionContent>
		getCourseContents(
			com.liferay.portal.kernel.model.User user, long courseId,
			java.util.Date minDate, java.util.Date maxDate) {

		return _sessionContentLocalService.getCourseContents(
			user, courseId, minDate, maxDate);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _sessionContentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sessionContentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionContentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the session content with the primary key.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content
	 * @throws PortalException if a session content with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.course.model.SessionContent getSessionContent(
			long sessionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionContentLocalService.getSessionContent(sessionId);
	}

	/**
	 * Returns a range of all the session contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.SessionContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session contents
	 * @param end the upper bound of the range of session contents (not inclusive)
	 * @return the range of session contents
	 */
	@Override
	public java.util.List<com.weprode.facile.course.model.SessionContent>
		getSessionContents(int start, int end) {

		return _sessionContentLocalService.getSessionContents(start, end);
	}

	/**
	 * Returns the number of session contents.
	 *
	 * @return the number of session contents
	 */
	@Override
	public int getSessionContentsCount() {
		return _sessionContentLocalService.getSessionContentsCount();
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder getSessionFolder(
			long sessionId, boolean doCreate)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _sessionContentLocalService.getSessionFolder(
			sessionId, doCreate);
	}

	@Override
	public boolean hasSessionContent(long sessionId) {
		return _sessionContentLocalService.hasSessionContent(sessionId);
	}

	@Override
	public com.weprode.facile.course.model.SessionContent updateSessionContent(
			long teacherId, long sessionId, String title,
			org.json.JSONArray blocks, java.util.Date publicationDate,
			boolean isDraft)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.facile.course.exception.UnauthorizedUrlException,
			   java.io.IOException {

		return _sessionContentLocalService.updateSessionContent(
			teacherId, sessionId, title, blocks, publicationDate, isDraft);
	}

	/**
	 * Updates the session content in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionContent the session content
	 * @return the session content that was updated
	 */
	@Override
	public com.weprode.facile.course.model.SessionContent updateSessionContent(
		com.weprode.facile.course.model.SessionContent sessionContent) {

		return _sessionContentLocalService.updateSessionContent(sessionContent);
	}

	@Override
	public SessionContentLocalService getWrappedService() {
		return _sessionContentLocalService;
	}

	@Override
	public void setWrappedService(
		SessionContentLocalService sessionContentLocalService) {

		_sessionContentLocalService = sessionContentLocalService;
	}

	private SessionContentLocalService _sessionContentLocalService;

}