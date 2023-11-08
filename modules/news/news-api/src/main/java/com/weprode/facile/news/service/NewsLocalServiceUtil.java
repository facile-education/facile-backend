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

package com.weprode.facile.news.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.news.model.News;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for News. This utility wraps
 * <code>com.weprode.facile.news.service.impl.NewsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see NewsLocalService
 * @generated
 */
public class NewsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.news.service.impl.NewsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static News addNews(
		long authorId, String title, String content, boolean isSchoolNews,
		boolean isImportant, long imageId, java.util.Date publicationDate,
		java.util.Date expirationDate, org.json.JSONArray populations,
		List<Long> attachFileIds) {

		return getService().addNews(
			authorId, title, content, isSchoolNews, isImportant, imageId,
			publicationDate, expirationDate, populations, attachFileIds);
	}

	/**
	 * Adds the news to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param news the news
	 * @return the news that was added
	 */
	public static News addNews(News news) {
		return getService().addNews(news);
	}

	public static org.json.JSONObject convertNewsToJson(
			long newsId, long userId, boolean withDetails)
		throws PortalException, SystemException {

		return getService().convertNewsToJson(newsId, userId, withDetails);
	}

	/**
	 * Creates a new news with the primary key. Does not add the news to the database.
	 *
	 * @param newsId the primary key for the new news
	 * @return the new news
	 */
	public static News createNews(long newsId) {
		return getService().createNews(newsId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteByGroupId(long groupId) {
		getService().deleteByGroupId(groupId);
	}

	/**
	 * Deletes the news with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsId the primary key of the news
	 * @return the news that was removed
	 * @throws PortalException if a news with the primary key could not be found
	 */
	public static News deleteNews(long newsId) throws PortalException {
		return getService().deleteNews(newsId);
	}

	/**
	 * Deletes the news from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param news the news
	 * @return the news that was removed
	 */
	public static News deleteNews(News news) {
		return getService().deleteNews(news);
	}

	public static News deleteNewsAndDependencies(News news)
		throws SystemException {

		return getService().deleteNewsAndDependencies(news);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsModelImpl</code>.
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

	public static News editNews(
		long newsId, String title, String content, boolean isImportant,
		long imageId, java.util.Date publicationDate,
		java.util.Date expirationDate, org.json.JSONArray populations,
		List<Long> attachFileIds, boolean notifyRecipients) {

		return getService().editNews(
			newsId, title, content, isImportant, imageId, publicationDate,
			expirationDate, populations, attachFileIds, notifyRecipients);
	}

	public static News fetchNews(long newsId) {
		return getService().fetchNews(newsId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<News> getGroupNewsActivities(
			com.liferay.portal.kernel.model.User user, long groupId,
			java.util.Date minDate, java.util.Date maxDate, int nbNews)
		throws SystemException {

		return getService().getGroupNewsActivities(
			user, groupId, minDate, maxDate, nbNews);
	}

	public static org.json.JSONObject getGroupNewsBroadcastGroups(
		com.liferay.portal.kernel.model.User user) {

		return getService().getGroupNewsBroadcastGroups(user);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the news with the primary key.
	 *
	 * @param newsId the primary key of the news
	 * @return the news
	 * @throws PortalException if a news with the primary key could not be found
	 */
	public static News getNews(long newsId) throws PortalException {
		return getService().getNews(newsId);
	}

	public static List<News> getNews(
			com.liferay.portal.kernel.model.User user, long groupId,
			java.util.Date maxDate, int nbNews, boolean groupNews,
			boolean importantOnly, boolean unreadOnly)
		throws SystemException {

		return getService().getNews(
			user, groupId, maxDate, nbNews, groupNews, importantOnly,
			unreadOnly);
	}

	public static List<News> getNewsActivities(
			com.liferay.portal.kernel.model.User user, List<Long> groupIds,
			java.util.Date minDate, java.util.Date maxDate, int nbNews,
			boolean groupNewsOnly)
		throws SystemException {

		return getService().getNewsActivities(
			user, groupIds, minDate, maxDate, nbNews, groupNewsOnly);
	}

	public static int getNewsCount(
			com.liferay.portal.kernel.model.User user, long groupId,
			boolean groupNews, boolean importantOnly, boolean unreadOnly)
		throws SystemException {

		return getService().getNewsCount(
			user, groupId, groupNews, importantOnly, unreadOnly);
	}

	/**
	 * Returns a range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @return the range of newses
	 */
	public static List<News> getNewses(int start, int end) {
		return getService().getNewses(start, end);
	}

	/**
	 * Returns the number of newses.
	 *
	 * @return the number of newses
	 */
	public static int getNewsesCount() {
		return getService().getNewsesCount();
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
	 * Get groups available to be broadcasted a news for school
	 */
	public static org.json.JSONObject getSchoolNewsBroadcastGroups(
		com.liferay.portal.kernel.model.User user) {

		return getService().getSchoolNewsBroadcastGroups(user);
	}

	public static boolean hasUserNews(long userId, long newsId) {
		return getService().hasUserNews(userId, newsId);
	}

	/**
	 * Updates the news in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param news the news
	 * @return the news that was updated
	 */
	public static News updateNews(News news) {
		return getService().updateNews(news);
	}

	public static NewsLocalService getService() {
		return _service;
	}

	private static volatile NewsLocalService _service;

}