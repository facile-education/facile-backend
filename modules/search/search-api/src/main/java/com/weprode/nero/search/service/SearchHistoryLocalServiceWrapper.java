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

package com.weprode.nero.search.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SearchHistoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SearchHistoryLocalService
 * @generated
 */
public class SearchHistoryLocalServiceWrapper
	implements SearchHistoryLocalService,
			   ServiceWrapper<SearchHistoryLocalService> {

	public SearchHistoryLocalServiceWrapper() {
		this(null);
	}

	public SearchHistoryLocalServiceWrapper(
		SearchHistoryLocalService searchHistoryLocalService) {

		_searchHistoryLocalService = searchHistoryLocalService;
	}

	@Override
	public boolean addQuery(long userId, String query) {
		return _searchHistoryLocalService.addQuery(userId, query);
	}

	/**
	 * Adds the search history to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SearchHistoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param searchHistory the search history
	 * @return the search history that was added
	 */
	@Override
	public com.weprode.nero.search.model.SearchHistory addSearchHistory(
		com.weprode.nero.search.model.SearchHistory searchHistory) {

		return _searchHistoryLocalService.addSearchHistory(searchHistory);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _searchHistoryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new search history with the primary key. Does not add the search history to the database.
	 *
	 * @param searchHistoryId the primary key for the new search history
	 * @return the new search history
	 */
	@Override
	public com.weprode.nero.search.model.SearchHistory createSearchHistory(
		long searchHistoryId) {

		return _searchHistoryLocalService.createSearchHistory(searchHistoryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _searchHistoryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the search history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SearchHistoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param searchHistoryId the primary key of the search history
	 * @return the search history that was removed
	 * @throws PortalException if a search history with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.search.model.SearchHistory deleteSearchHistory(
			long searchHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _searchHistoryLocalService.deleteSearchHistory(searchHistoryId);
	}

	/**
	 * Deletes the search history from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SearchHistoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param searchHistory the search history
	 * @return the search history that was removed
	 */
	@Override
	public com.weprode.nero.search.model.SearchHistory deleteSearchHistory(
		com.weprode.nero.search.model.SearchHistory searchHistory) {

		return _searchHistoryLocalService.deleteSearchHistory(searchHistory);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _searchHistoryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _searchHistoryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _searchHistoryLocalService.dynamicQuery();
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

		return _searchHistoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.search.model.impl.SearchHistoryModelImpl</code>.
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

		return _searchHistoryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.search.model.impl.SearchHistoryModelImpl</code>.
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

		return _searchHistoryLocalService.dynamicQuery(
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

		return _searchHistoryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _searchHistoryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.search.model.SearchHistory fetchSearchHistory(
		long searchHistoryId) {

		return _searchHistoryLocalService.fetchSearchHistory(searchHistoryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _searchHistoryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _searchHistoryLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.nero.search.model.SearchHistory>
		getLastSearchQueries(long userId) {

		return _searchHistoryLocalService.getLastSearchQueries(userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _searchHistoryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _searchHistoryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the search histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.search.model.impl.SearchHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of search histories
	 * @param end the upper bound of the range of search histories (not inclusive)
	 * @return the range of search histories
	 */
	@Override
	public java.util.List<com.weprode.nero.search.model.SearchHistory>
		getSearchHistories(int start, int end) {

		return _searchHistoryLocalService.getSearchHistories(start, end);
	}

	/**
	 * Returns the number of search histories.
	 *
	 * @return the number of search histories
	 */
	@Override
	public int getSearchHistoriesCount() {
		return _searchHistoryLocalService.getSearchHistoriesCount();
	}

	/**
	 * Returns the search history with the primary key.
	 *
	 * @param searchHistoryId the primary key of the search history
	 * @return the search history
	 * @throws PortalException if a search history with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.search.model.SearchHistory getSearchHistory(
			long searchHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _searchHistoryLocalService.getSearchHistory(searchHistoryId);
	}

	/**
	 * Updates the search history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SearchHistoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param searchHistory the search history
	 * @return the search history that was updated
	 */
	@Override
	public com.weprode.nero.search.model.SearchHistory updateSearchHistory(
		com.weprode.nero.search.model.SearchHistory searchHistory) {

		return _searchHistoryLocalService.updateSearchHistory(searchHistory);
	}

	@Override
	public SearchHistoryLocalService getWrappedService() {
		return _searchHistoryLocalService;
	}

	@Override
	public void setWrappedService(
		SearchHistoryLocalService searchHistoryLocalService) {

		_searchHistoryLocalService = searchHistoryLocalService;
	}

	private SearchHistoryLocalService _searchHistoryLocalService;

}