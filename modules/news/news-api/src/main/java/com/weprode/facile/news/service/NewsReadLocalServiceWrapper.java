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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NewsReadLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NewsReadLocalService
 * @generated
 */
public class NewsReadLocalServiceWrapper
	implements NewsReadLocalService, ServiceWrapper<NewsReadLocalService> {

	public NewsReadLocalServiceWrapper() {
		this(null);
	}

	public NewsReadLocalServiceWrapper(
		NewsReadLocalService newsReadLocalService) {

		_newsReadLocalService = newsReadLocalService;
	}

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
	@Override
	public com.weprode.facile.news.model.NewsRead addNewsRead(
		com.weprode.facile.news.model.NewsRead newsRead) {

		return _newsReadLocalService.addNewsRead(newsRead);
	}

	/**
	 * Creates a new news read with the primary key. Does not add the news read to the database.
	 *
	 * @param newsReadPK the primary key for the new news read
	 * @return the new news read
	 */
	@Override
	public com.weprode.facile.news.model.NewsRead createNewsRead(
		com.weprode.facile.news.service.persistence.NewsReadPK newsReadPK) {

		return _newsReadLocalService.createNewsRead(newsReadPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsReadLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public void deleteByNewsId(long newsId)
		throws com.liferay.portal.kernel.exception.SystemException {

		_newsReadLocalService.deleteByNewsId(newsId);
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
	@Override
	public com.weprode.facile.news.model.NewsRead deleteNewsRead(
		com.weprode.facile.news.model.NewsRead newsRead) {

		return _newsReadLocalService.deleteNewsRead(newsRead);
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
	@Override
	public com.weprode.facile.news.model.NewsRead deleteNewsRead(
			com.weprode.facile.news.service.persistence.NewsReadPK newsReadPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsReadLocalService.deleteNewsRead(newsReadPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsReadLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _newsReadLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _newsReadLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _newsReadLocalService.dynamicQuery();
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

		return _newsReadLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsReadModelImpl</code>.
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

		return _newsReadLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsReadModelImpl</code>.
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

		return _newsReadLocalService.dynamicQuery(
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

		return _newsReadLocalService.dynamicQueryCount(dynamicQuery);
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

		return _newsReadLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.news.model.NewsRead fetchNewsRead(
		com.weprode.facile.news.service.persistence.NewsReadPK newsReadPK) {

		return _newsReadLocalService.fetchNewsRead(newsReadPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _newsReadLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _newsReadLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the news read with the primary key.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read
	 * @throws PortalException if a news read with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.news.model.NewsRead getNewsRead(
			com.weprode.facile.news.service.persistence.NewsReadPK newsReadPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsReadLocalService.getNewsRead(newsReadPK);
	}

	/**
	 * Returns a range of all the news reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @return the range of news reads
	 */
	@Override
	public java.util.List<com.weprode.facile.news.model.NewsRead> getNewsReads(
		int start, int end) {

		return _newsReadLocalService.getNewsReads(start, end);
	}

	/**
	 * Returns the number of news reads.
	 *
	 * @return the number of news reads
	 */
	@Override
	public int getNewsReadsCount() {
		return _newsReadLocalService.getNewsReadsCount();
	}

	@Override
	public org.json.JSONArray getNewsReadStatus(long newsId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _newsReadLocalService.getNewsReadStatus(newsId, userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _newsReadLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsReadLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.weprode.facile.news.model.NewsRead getUserReadNews(
		long userId, long newsId) {

		return _newsReadLocalService.getUserReadNews(userId, newsId);
	}

	@Override
	public java.util.List<Long> getUsersHavingRead(long newsId) {
		return _newsReadLocalService.getUsersHavingRead(newsId);
	}

	@Override
	public boolean hasUserReadNews(long userId, long newsId) {
		return _newsReadLocalService.hasUserReadNews(userId, newsId);
	}

	@Override
	public boolean setNewsAsUnReadForAll(long newsId) {
		return _newsReadLocalService.setNewsAsUnReadForAll(newsId);
	}

	@Override
	public boolean setNewsRead(long userId, long newsId) {
		return _newsReadLocalService.setNewsRead(userId, newsId);
	}

	@Override
	public boolean setNewsUnRead(long userId, long newsId) {
		return _newsReadLocalService.setNewsUnRead(userId, newsId);
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
	@Override
	public com.weprode.facile.news.model.NewsRead updateNewsRead(
		com.weprode.facile.news.model.NewsRead newsRead) {

		return _newsReadLocalService.updateNewsRead(newsRead);
	}

	@Override
	public NewsReadLocalService getWrappedService() {
		return _newsReadLocalService;
	}

	@Override
	public void setWrappedService(NewsReadLocalService newsReadLocalService) {
		_newsReadLocalService = newsReadLocalService;
	}

	private NewsReadLocalService _newsReadLocalService;

}