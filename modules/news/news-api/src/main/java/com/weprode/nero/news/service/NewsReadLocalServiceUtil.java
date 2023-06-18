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

package com.weprode.nero.news.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.news.model.NewsRead;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for NewsRead. This utility wraps
 * <code>com.weprode.nero.news.service.impl.NewsReadLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see NewsReadLocalService
 * @generated
 */
public class NewsReadLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.news.service.impl.NewsReadLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the news read to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsReadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsRead the news read
	 * @return the news read that was added
	 */
	public static NewsRead addNewsRead(NewsRead newsRead) {
		return getService().addNewsRead(newsRead);
	}

	/**
	 * Creates a new news read with the primary key. Does not add the news read to the database.
	 *
	 * @param newsReadPK the primary key for the new news read
	 * @return the new news read
	 */
	public static NewsRead createNewsRead(
		com.weprode.nero.news.service.persistence.NewsReadPK newsReadPK) {

		return getService().createNewsRead(newsReadPK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteByNewsId(long newsId) throws SystemException {
		getService().deleteByNewsId(newsId);
	}

	/**
	 * Deletes the news read from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsReadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsRead the news read
	 * @return the news read that was removed
	 */
	public static NewsRead deleteNewsRead(NewsRead newsRead) {
		return getService().deleteNewsRead(newsRead);
	}

	/**
	 * Deletes the news read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsReadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read that was removed
	 * @throws PortalException if a news read with the primary key could not be found
	 */
	public static NewsRead deleteNewsRead(
			com.weprode.nero.news.service.persistence.NewsReadPK newsReadPK)
		throws PortalException {

		return getService().deleteNewsRead(newsReadPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsReadModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsReadModelImpl</code>.
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

	public static NewsRead fetchNewsRead(
		com.weprode.nero.news.service.persistence.NewsReadPK newsReadPK) {

		return getService().fetchNewsRead(newsReadPK);
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
	 * Returns the news read with the primary key.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read
	 * @throws PortalException if a news read with the primary key could not be found
	 */
	public static NewsRead getNewsRead(
			com.weprode.nero.news.service.persistence.NewsReadPK newsReadPK)
		throws PortalException {

		return getService().getNewsRead(newsReadPK);
	}

	/**
	 * Returns a range of all the news reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @return the range of news reads
	 */
	public static List<NewsRead> getNewsReads(int start, int end) {
		return getService().getNewsReads(start, end);
	}

	/**
	 * Returns the number of news reads.
	 *
	 * @return the number of news reads
	 */
	public static int getNewsReadsCount() {
		return getService().getNewsReadsCount();
	}

	public static org.json.JSONArray getNewsReadStatus(long newsId, long userId)
		throws PortalException, SystemException {

		return getService().getNewsReadStatus(newsId, userId);
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

	public static NewsRead getUserReadNews(long userId, long newsId) {
		return getService().getUserReadNews(userId, newsId);
	}

	public static List<Long> getUsersHavingRead(long newsId) {
		return getService().getUsersHavingRead(newsId);
	}

	public static boolean hasUserReadNews(long userId, long newsId) {
		return getService().hasUserReadNews(userId, newsId);
	}

	public static boolean setNewsAsUnReadForAll(long newsId) {
		return getService().setNewsAsUnReadForAll(newsId);
	}

	public static boolean setNewsRead(long userId, long newsId) {
		return getService().setNewsRead(userId, newsId);
	}

	public static boolean setNewsUnRead(long userId, long newsId) {
		return getService().setNewsUnRead(userId, newsId);
	}

	/**
	 * Updates the news read in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsReadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsRead the news read
	 * @return the news read that was updated
	 */
	public static NewsRead updateNewsRead(NewsRead newsRead) {
		return getService().updateNewsRead(newsRead);
	}

	public static NewsReadLocalService getService() {
		return _service;
	}

	private static volatile NewsReadLocalService _service;

}