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

package com.weprode.nero.user.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.user.model.NewsAdmin;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for NewsAdmin. This utility wraps
 * <code>com.weprode.nero.user.service.impl.NewsAdminLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see NewsAdminLocalService
 * @generated
 */
public class NewsAdminLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.user.service.impl.NewsAdminLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static NewsAdmin addDelegate() throws SystemException {
		return getService().addDelegate();
	}

	/**
	 * Adds the news admin to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdmin the news admin
	 * @return the news admin that was added
	 */
	public static NewsAdmin addNewsAdmin(NewsAdmin newsAdmin) {
		return getService().addNewsAdmin(newsAdmin);
	}

	public static boolean addSchoolDelegate(long userId, long schoolId) {
		return getService().addSchoolDelegate(userId, schoolId);
	}

	/**
	 * Creates a new news admin with the primary key. Does not add the news admin to the database.
	 *
	 * @param newsAdminId the primary key for the new news admin
	 * @return the new news admin
	 */
	public static NewsAdmin createNewsAdmin(long newsAdminId) {
		return getService().createNewsAdmin(newsAdminId);
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
	 * Deletes the news admin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin that was removed
	 * @throws PortalException if a news admin with the primary key could not be found
	 */
	public static NewsAdmin deleteNewsAdmin(long newsAdminId)
		throws PortalException {

		return getService().deleteNewsAdmin(newsAdminId);
	}

	/**
	 * Deletes the news admin from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdmin the news admin
	 * @return the news admin that was removed
	 */
	public static NewsAdmin deleteNewsAdmin(NewsAdmin newsAdmin) {
		return getService().deleteNewsAdmin(newsAdmin);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.NewsAdminModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.NewsAdminModelImpl</code>.
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

	public static NewsAdmin fetchNewsAdmin(long newsAdminId) {
		return getService().fetchNewsAdmin(newsAdminId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the news admin with the primary key.
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin
	 * @throws PortalException if a news admin with the primary key could not be found
	 */
	public static NewsAdmin getNewsAdmin(long newsAdminId)
		throws PortalException {

		return getService().getNewsAdmin(newsAdminId);
	}

	/**
	 * Returns a range of all the news admins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @return the range of news admins
	 */
	public static List<NewsAdmin> getNewsAdmins(int start, int end) {
		return getService().getNewsAdmins(start, end);
	}

	/**
	 * Returns the number of news admins.
	 *
	 * @return the number of news admins
	 */
	public static int getNewsAdminsCount() {
		return getService().getNewsAdminsCount();
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

	public static List<com.liferay.portal.kernel.model.User> getSchoolDelegates(
		Long schoolId) {

		return getService().getSchoolDelegates(schoolId);
	}

	public static List<com.liferay.portal.kernel.model.User>
		getSchoolDelegationCandidates(long schoolId, String filter) {

		return getService().getSchoolDelegationCandidates(schoolId, filter);
	}

	public static boolean isUserDelegate(
		com.liferay.portal.kernel.model.User user) {

		return getService().isUserDelegate(user);
	}

	public static boolean isUserSchoolDelegate(
		com.liferay.portal.kernel.model.User user, long schoolId) {

		return getService().isUserSchoolDelegate(user, schoolId);
	}

	public static boolean removeSchoolDelegate(long userId, long schoolId) {
		return getService().removeSchoolDelegate(userId, schoolId);
	}

	/**
	 * Updates the news admin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdmin the news admin
	 * @return the news admin that was updated
	 */
	public static NewsAdmin updateNewsAdmin(NewsAdmin newsAdmin) {
		return getService().updateNewsAdmin(newsAdmin);
	}

	public static NewsAdminLocalService getService() {
		return _service;
	}

	private static volatile NewsAdminLocalService _service;

}