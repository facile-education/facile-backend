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
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.schedule.model.SessionParentClass;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for SessionParentClass. This utility wraps
 * <code>com.weprode.nero.schedule.service.impl.SessionParentClassLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SessionParentClassLocalService
 * @generated
 */
public class SessionParentClassLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.schedule.service.impl.SessionParentClassLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static SessionParentClass addSessionParentClass(
		long sessionId, long groupId) {

		return getService().addSessionParentClass(sessionId, groupId);
	}

	/**
	 * Adds the session parent class to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClass the session parent class
	 * @return the session parent class that was added
	 */
	public static SessionParentClass addSessionParentClass(
		SessionParentClass sessionParentClass) {

		return getService().addSessionParentClass(sessionParentClass);
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
	 * Creates a new session parent class with the primary key. Does not add the session parent class to the database.
	 *
	 * @param sessionParentClassId the primary key for the new session parent class
	 * @return the new session parent class
	 */
	public static SessionParentClass createSessionParentClass(
		long sessionParentClassId) {

		return getService().createSessionParentClass(sessionParentClassId);
	}

	public static boolean deleteByGroupId(long groupId) {
		return getService().deleteByGroupId(groupId);
	}

	public static boolean deleteBySessionId(long sessionId) {
		return getService().deleteBySessionId(sessionId);
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
	 * Deletes the session parent class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class that was removed
	 * @throws PortalException if a session parent class with the primary key could not be found
	 */
	public static SessionParentClass deleteSessionParentClass(
			long sessionParentClassId)
		throws PortalException {

		return getService().deleteSessionParentClass(sessionParentClassId);
	}

	/**
	 * Deletes the session parent class from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClass the session parent class
	 * @return the session parent class that was removed
	 */
	public static SessionParentClass deleteSessionParentClass(
		SessionParentClass sessionParentClass) {

		return getService().deleteSessionParentClass(sessionParentClass);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl</code>.
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

	public static SessionParentClass fetchSessionParentClass(
		long sessionParentClassId) {

		return getService().fetchSessionParentClass(sessionParentClassId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<Long> getGroupSessions(long groupId) {
		return getService().getGroupSessions(groupId);
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

	public static String getParentClassName(long sessionId) {
		return getService().getParentClassName(sessionId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the session parent class with the primary key.
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class
	 * @throws PortalException if a session parent class with the primary key could not be found
	 */
	public static SessionParentClass getSessionParentClass(
			long sessionParentClassId)
		throws PortalException {

		return getService().getSessionParentClass(sessionParentClassId);
	}

	/**
	 * Returns a range of all the session parent classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of session parent classes
	 */
	public static List<SessionParentClass> getSessionParentClasses(
		int start, int end) {

		return getService().getSessionParentClasses(start, end);
	}

	/**
	 * Returns the number of session parent classes.
	 *
	 * @return the number of session parent classes
	 */
	public static int getSessionParentClassesCount() {
		return getService().getSessionParentClassesCount();
	}

	public static List<Long> getSessionParentGroupIds(long sessionId) {
		return getService().getSessionParentGroupIds(sessionId);
	}

	/**
	 * Updates the session parent class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClass the session parent class
	 * @return the session parent class that was updated
	 */
	public static SessionParentClass updateSessionParentClass(
		SessionParentClass sessionParentClass) {

		return getService().updateSessionParentClass(sessionParentClass);
	}

	public static SessionParentClassLocalService getService() {
		return _service;
	}

	private static volatile SessionParentClassLocalService _service;

}