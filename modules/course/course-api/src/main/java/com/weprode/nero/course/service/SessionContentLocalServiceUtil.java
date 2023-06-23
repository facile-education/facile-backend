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

package com.weprode.nero.course.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.course.model.SessionContent;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for SessionContent. This utility wraps
 * <code>com.weprode.nero.course.service.impl.SessionContentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SessionContentLocalService
 * @generated
 */
public class SessionContentLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.course.service.impl.SessionContentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static SessionContent addSessionContent(
			long sessionId, long teacherId, String title,
			java.util.Date publicationDate, boolean isDraft)
		throws SystemException {

		return getService().addSessionContent(
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
	public static SessionContent addSessionContent(
		SessionContent sessionContent) {

		return getService().addSessionContent(sessionContent);
	}

	public static String convertContentAsHtml(long sessionId) {
		return getService().convertContentAsHtml(sessionId);
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
	 * Creates a new session content with the primary key. Does not add the session content to the database.
	 *
	 * @param sessionId the primary key for the new session content
	 * @return the new session content
	 */
	public static SessionContent createSessionContent(long sessionId) {
		return getService().createSessionContent(sessionId);
	}

	public static void deleteContent(long sessionId)
		throws PortalException, SystemException {

		getService().deleteContent(sessionId);
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
	public static SessionContent deleteSessionContent(long sessionId)
		throws PortalException {

		return getService().deleteSessionContent(sessionId);
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
	public static SessionContent deleteSessionContent(
		SessionContent sessionContent) {

		return getService().deleteSessionContent(sessionContent);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.SessionContentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.SessionContentModelImpl</code>.
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

	public static SessionContent fetchSessionContent(long sessionId) {
		return getService().fetchSessionContent(sessionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<SessionContent> getCourseContents(
		com.liferay.portal.kernel.model.User user, long courseId,
		java.util.Date minDate, java.util.Date maxDate) {

		return getService().getCourseContents(user, courseId, minDate, maxDate);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
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
	 * Returns the session content with the primary key.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content
	 * @throws PortalException if a session content with the primary key could not be found
	 */
	public static SessionContent getSessionContent(long sessionId)
		throws PortalException {

		return getService().getSessionContent(sessionId);
	}

	/**
	 * Returns a range of all the session contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.SessionContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session contents
	 * @param end the upper bound of the range of session contents (not inclusive)
	 * @return the range of session contents
	 */
	public static List<SessionContent> getSessionContents(int start, int end) {
		return getService().getSessionContents(start, end);
	}

	/**
	 * Returns the number of session contents.
	 *
	 * @return the number of session contents
	 */
	public static int getSessionContentsCount() {
		return getService().getSessionContentsCount();
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getSessionFolder(long sessionId)
		throws PortalException, SystemException {

		return getService().getSessionFolder(sessionId);
	}

	public static boolean hasSessionContent(long sessionId) {
		return getService().hasSessionContent(sessionId);
	}

	public static SessionContent updateSessionContent(
			long teacherId, long sessionId, String title,
			org.json.JSONArray blocks, java.util.Date publicationDate,
			boolean isDraft)
		throws com.weprode.nero.course.exception.UnauthorizedUrlException,
			   java.io.IOException, PortalException, SystemException {

		return getService().updateSessionContent(
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
	public static SessionContent updateSessionContent(
		SessionContent sessionContent) {

		return getService().updateSessionContent(sessionContent);
	}

	public static SessionContentLocalService getService() {
		return _service;
	}

	private static volatile SessionContentLocalService _service;

}