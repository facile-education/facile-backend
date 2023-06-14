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
 * Provides a wrapper for {@link NewsPopulationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NewsPopulationLocalService
 * @generated
 */
public class NewsPopulationLocalServiceWrapper
	implements NewsPopulationLocalService,
			   ServiceWrapper<NewsPopulationLocalService> {

	public NewsPopulationLocalServiceWrapper(
		NewsPopulationLocalService newsPopulationLocalService) {

		_newsPopulationLocalService = newsPopulationLocalService;
	}

	/**
	 * Adds the news population to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsPopulationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsPopulation the news population
	 * @return the news population that was added
	 */
	@Override
	public com.weprode.nero.news.model.NewsPopulation addNewsPopulation(
		com.weprode.nero.news.model.NewsPopulation newsPopulation) {

		return _newsPopulationLocalService.addNewsPopulation(newsPopulation);
	}

	@Override
	public com.weprode.nero.news.model.NewsPopulation addPopulation(
			long newsId, long groupId, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _newsPopulationLocalService.addPopulation(
			newsId, groupId, roleId);
	}

	@Override
	public org.json.JSONArray convertNewsPopulations(long newsId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _newsPopulationLocalService.convertNewsPopulations(
			newsId, userId);
	}

	/**
	 * Creates a new news population with the primary key. Does not add the news population to the database.
	 *
	 * @param newsPopulationPK the primary key for the new news population
	 * @return the new news population
	 */
	@Override
	public com.weprode.nero.news.model.NewsPopulation createNewsPopulation(
		com.weprode.nero.news.service.persistence.NewsPopulationPK
			newsPopulationPK) {

		return _newsPopulationLocalService.createNewsPopulation(
			newsPopulationPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsPopulationLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public void deleteByGroupId(long groupId) {
		_newsPopulationLocalService.deleteByGroupId(groupId);
	}

	@Override
	public void deleteByNewsId(long newsId)
		throws com.liferay.portal.kernel.exception.SystemException {

		_newsPopulationLocalService.deleteByNewsId(newsId);
	}

	/**
	 * Deletes the news population from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsPopulationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsPopulation the news population
	 * @return the news population that was removed
	 */
	@Override
	public com.weprode.nero.news.model.NewsPopulation deleteNewsPopulation(
		com.weprode.nero.news.model.NewsPopulation newsPopulation) {

		return _newsPopulationLocalService.deleteNewsPopulation(newsPopulation);
	}

	/**
	 * Deletes the news population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsPopulationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population that was removed
	 * @throws PortalException if a news population with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.news.model.NewsPopulation deleteNewsPopulation(
			com.weprode.nero.news.service.persistence.NewsPopulationPK
				newsPopulationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsPopulationLocalService.deleteNewsPopulation(
			newsPopulationPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsPopulationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _newsPopulationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _newsPopulationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _newsPopulationLocalService.dynamicQuery();
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

		return _newsPopulationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsPopulationModelImpl</code>.
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

		return _newsPopulationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsPopulationModelImpl</code>.
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

		return _newsPopulationLocalService.dynamicQuery(
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

		return _newsPopulationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _newsPopulationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.news.model.NewsPopulation fetchNewsPopulation(
		com.weprode.nero.news.service.persistence.NewsPopulationPK
			newsPopulationPK) {

		return _newsPopulationLocalService.fetchNewsPopulation(
			newsPopulationPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _newsPopulationLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.nero.news.model.NewsPopulation>
		getByGroupId(long groupId) {

		return _newsPopulationLocalService.getByGroupId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _newsPopulationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the news population with the primary key.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population
	 * @throws PortalException if a news population with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.news.model.NewsPopulation getNewsPopulation(
			com.weprode.nero.news.service.persistence.NewsPopulationPK
				newsPopulationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsPopulationLocalService.getNewsPopulation(newsPopulationPK);
	}

	/**
	 * Returns a range of all the news populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of news populations
	 */
	@Override
	public java.util.List<com.weprode.nero.news.model.NewsPopulation>
		getNewsPopulations(int start, int end) {

		return _newsPopulationLocalService.getNewsPopulations(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.news.model.NewsPopulation>
			getNewsPopulations(long newsId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _newsPopulationLocalService.getNewsPopulations(newsId);
	}

	/**
	 * Returns the number of news populations.
	 *
	 * @return the number of news populations
	 */
	@Override
	public int getNewsPopulationsCount() {
		return _newsPopulationLocalService.getNewsPopulationsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _newsPopulationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsPopulationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the news population in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsPopulationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsPopulation the news population
	 * @return the news population that was updated
	 */
	@Override
	public com.weprode.nero.news.model.NewsPopulation updateNewsPopulation(
		com.weprode.nero.news.model.NewsPopulation newsPopulation) {

		return _newsPopulationLocalService.updateNewsPopulation(newsPopulation);
	}

	@Override
	public NewsPopulationLocalService getWrappedService() {
		return _newsPopulationLocalService;
	}

	@Override
	public void setWrappedService(
		NewsPopulationLocalService newsPopulationLocalService) {

		_newsPopulationLocalService = newsPopulationLocalService;
	}

	private NewsPopulationLocalService _newsPopulationLocalService;

}