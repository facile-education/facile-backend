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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NewsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NewsLocalService
 * @generated
 */
public class NewsLocalServiceWrapper
	implements NewsLocalService, ServiceWrapper<NewsLocalService> {

	public NewsLocalServiceWrapper(NewsLocalService newsLocalService) {
		_newsLocalService = newsLocalService;
	}

	@Override
	public com.weprode.nero.news.model.News addNews(
		long authorId, String title, String content, boolean isSchoolNews,
		boolean isImportant, long imageId, java.util.Date publicationDate,
		java.util.Date expirationDate, org.json.JSONArray populations,
		java.util.List<Long> attachFileIds) {

		return _newsLocalService.addNews(
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
	@Override
	public com.weprode.nero.news.model.News addNews(
		com.weprode.nero.news.model.News news) {

		return _newsLocalService.addNews(news);
	}

	@Override
	public org.json.JSONObject convertNewsToJson(
			long newsId, long userId, boolean withDetails)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _newsLocalService.convertNewsToJson(newsId, userId, withDetails);
	}

	/**
	 * Creates a new news with the primary key. Does not add the news to the database.
	 *
	 * @param newsId the primary key for the new news
	 * @return the new news
	 */
	@Override
	public com.weprode.nero.news.model.News createNews(long newsId) {
		return _newsLocalService.createNews(newsId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public com.weprode.nero.news.model.News deleteNews(long newsId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsLocalService.deleteNews(newsId);
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
	@Override
	public com.weprode.nero.news.model.News deleteNews(
		com.weprode.nero.news.model.News news) {

		return _newsLocalService.deleteNews(news);
	}

	@Override
	public com.weprode.nero.news.model.News deleteNewsAndDependencies(
			long newsId)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.news.exception.NoSuchNewsException {

		return _newsLocalService.deleteNewsAndDependencies(newsId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _newsLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _newsLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _newsLocalService.dynamicQuery();
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

		return _newsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsModelImpl</code>.
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

		return _newsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsModelImpl</code>.
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

		return _newsLocalService.dynamicQuery(
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

		return _newsLocalService.dynamicQueryCount(dynamicQuery);
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

		return _newsLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.news.model.News editNews(
		long newsId, String title, String content, boolean isImportant,
		long imageId, java.util.Date publicationDate,
		java.util.Date expirationDate, org.json.JSONArray populations,
		java.util.List<Long> attachFileIds) {

		return _newsLocalService.editNews(
			newsId, title, content, isImportant, imageId, publicationDate,
			expirationDate, populations, attachFileIds);
	}

	@Override
	public com.weprode.nero.news.model.News fetchNews(long newsId) {
		return _newsLocalService.fetchNews(newsId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _newsLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.json.JSONObject getGroupNewsBroadcastGroups(
		com.liferay.portal.kernel.model.User user) {

		return _newsLocalService.getGroupNewsBroadcastGroups(user);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _newsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the news with the primary key.
	 *
	 * @param newsId the primary key of the news
	 * @return the news
	 * @throws PortalException if a news with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.news.model.News getNews(long newsId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsLocalService.getNews(newsId);
	}

	@Override
	public java.util.List<com.weprode.nero.news.model.News> getNews(
			com.liferay.portal.kernel.model.User user, long groupId,
			java.util.Date maxDate, int nbNews, boolean groupNews,
			boolean importantOnly, boolean unreadOnly)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _newsLocalService.getNews(
			user, groupId, maxDate, nbNews, groupNews, importantOnly,
			unreadOnly);
	}

	@Override
	public java.util.List<com.weprode.nero.news.model.News> getNewsActivities(
			com.liferay.portal.kernel.model.User user, long groupId,
			java.util.Date minDate, java.util.Date maxDate, int nbNews)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _newsLocalService.getNewsActivities(
			user, groupId, minDate, maxDate, nbNews);
	}

	@Override
	public int getNewsCount(
			com.liferay.portal.kernel.model.User user, long groupId,
			boolean groupNews, boolean importantOnly, boolean unreadOnly)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _newsLocalService.getNewsCount(
			user, groupId, groupNews, importantOnly, unreadOnly);
	}

	/**
	 * Returns a range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @return the range of newses
	 */
	@Override
	public java.util.List<com.weprode.nero.news.model.News> getNewses(
		int start, int end) {

		return _newsLocalService.getNewses(start, end);
	}

	/**
	 * Returns the number of newses.
	 *
	 * @return the number of newses
	 */
	@Override
	public int getNewsesCount() {
		return _newsLocalService.getNewsesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _newsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Get groups available to be broadcasted a news for school
	 */
	@Override
	public org.json.JSONObject getSchoolNewsBroadcastGroups(
		com.liferay.portal.kernel.model.User user) {

		return _newsLocalService.getSchoolNewsBroadcastGroups(user);
	}

	@Override
	public boolean hasUserNews(long userId, long newsId) {
		return _newsLocalService.hasUserNews(userId, newsId);
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
	@Override
	public com.weprode.nero.news.model.News updateNews(
		com.weprode.nero.news.model.News news) {

		return _newsLocalService.updateNews(news);
	}

	@Override
	public NewsLocalService getWrappedService() {
		return _newsLocalService;
	}

	@Override
	public void setWrappedService(NewsLocalService newsLocalService) {
		_newsLocalService = newsLocalService;
	}

	private NewsLocalService _newsLocalService;

}