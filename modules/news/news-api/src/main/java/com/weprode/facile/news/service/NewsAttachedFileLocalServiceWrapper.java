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
 * Provides a wrapper for {@link NewsAttachedFileLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NewsAttachedFileLocalService
 * @generated
 */
public class NewsAttachedFileLocalServiceWrapper
	implements NewsAttachedFileLocalService,
			   ServiceWrapper<NewsAttachedFileLocalService> {

	public NewsAttachedFileLocalServiceWrapper() {
		this(null);
	}

	public NewsAttachedFileLocalServiceWrapper(
		NewsAttachedFileLocalService newsAttachedFileLocalService) {

		_newsAttachedFileLocalService = newsAttachedFileLocalService;
	}

	@Override
	public boolean addFile(long newsId, long groupId, long fileId) {
		return _newsAttachedFileLocalService.addFile(newsId, groupId, fileId);
	}

	/**
	 * Adds the news attached file to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAttachedFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAttachedFile the news attached file
	 * @return the news attached file that was added
	 */
	@Override
	public com.weprode.facile.news.model.NewsAttachedFile addNewsAttachedFile(
		com.weprode.facile.news.model.NewsAttachedFile newsAttachedFile) {

		return _newsAttachedFileLocalService.addNewsAttachedFile(
			newsAttachedFile);
	}

	@Override
	public org.json.JSONArray convertNewsFiles(long newsId, long userId) {
		return _newsAttachedFileLocalService.convertNewsFiles(newsId, userId);
	}

	/**
	 * Creates a new news attached file with the primary key. Does not add the news attached file to the database.
	 *
	 * @param newsFileId the primary key for the new news attached file
	 * @return the new news attached file
	 */
	@Override
	public com.weprode.facile.news.model.NewsAttachedFile
		createNewsAttachedFile(long newsFileId) {

		return _newsAttachedFileLocalService.createNewsAttachedFile(newsFileId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAttachedFileLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public void deleteAttachedFile(
		com.weprode.facile.news.model.NewsAttachedFile newsAttachedFile) {

		_newsAttachedFileLocalService.deleteAttachedFile(newsAttachedFile);
	}

	@Override
	public void deleteByNewsId(long newsId)
		throws com.liferay.portal.kernel.exception.SystemException {

		_newsAttachedFileLocalService.deleteByNewsId(newsId);
	}

	/**
	 * Deletes the news attached file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAttachedFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsFileId the primary key of the news attached file
	 * @return the news attached file that was removed
	 * @throws PortalException if a news attached file with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.news.model.NewsAttachedFile
			deleteNewsAttachedFile(long newsFileId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAttachedFileLocalService.deleteNewsAttachedFile(newsFileId);
	}

	/**
	 * Deletes the news attached file from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAttachedFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAttachedFile the news attached file
	 * @return the news attached file that was removed
	 */
	@Override
	public com.weprode.facile.news.model.NewsAttachedFile
		deleteNewsAttachedFile(
			com.weprode.facile.news.model.NewsAttachedFile newsAttachedFile) {

		return _newsAttachedFileLocalService.deleteNewsAttachedFile(
			newsAttachedFile);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAttachedFileLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _newsAttachedFileLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _newsAttachedFileLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _newsAttachedFileLocalService.dynamicQuery();
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

		return _newsAttachedFileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsAttachedFileModelImpl</code>.
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

		return _newsAttachedFileLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsAttachedFileModelImpl</code>.
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

		return _newsAttachedFileLocalService.dynamicQuery(
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

		return _newsAttachedFileLocalService.dynamicQueryCount(dynamicQuery);
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

		return _newsAttachedFileLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.news.model.NewsAttachedFile fetchNewsAttachedFile(
		long newsFileId) {

		return _newsAttachedFileLocalService.fetchNewsAttachedFile(newsFileId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _newsAttachedFileLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _newsAttachedFileLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the news attached file with the primary key.
	 *
	 * @param newsFileId the primary key of the news attached file
	 * @return the news attached file
	 * @throws PortalException if a news attached file with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.news.model.NewsAttachedFile getNewsAttachedFile(
			long newsFileId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAttachedFileLocalService.getNewsAttachedFile(newsFileId);
	}

	/**
	 * Returns a range of all the news attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @return the range of news attached files
	 */
	@Override
	public java.util.List<com.weprode.facile.news.model.NewsAttachedFile>
		getNewsAttachedFiles(int start, int end) {

		return _newsAttachedFileLocalService.getNewsAttachedFiles(start, end);
	}

	@Override
	public java.util.List<com.weprode.facile.news.model.NewsAttachedFile>
		getNewsAttachedFiles(long newsId) {

		return _newsAttachedFileLocalService.getNewsAttachedFiles(newsId);
	}

	/**
	 * Returns the number of news attached files.
	 *
	 * @return the number of news attached files
	 */
	@Override
	public int getNewsAttachedFilesCount() {
		return _newsAttachedFileLocalService.getNewsAttachedFilesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _newsAttachedFileLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _newsAttachedFileLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasAttachedFiles(long newsId) {
		return _newsAttachedFileLocalService.hasAttachedFiles(newsId);
	}

	/**
	 * Updates the news attached file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAttachedFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAttachedFile the news attached file
	 * @return the news attached file that was updated
	 */
	@Override
	public com.weprode.facile.news.model.NewsAttachedFile
		updateNewsAttachedFile(
			com.weprode.facile.news.model.NewsAttachedFile newsAttachedFile) {

		return _newsAttachedFileLocalService.updateNewsAttachedFile(
			newsAttachedFile);
	}

	@Override
	public NewsAttachedFileLocalService getWrappedService() {
		return _newsAttachedFileLocalService;
	}

	@Override
	public void setWrappedService(
		NewsAttachedFileLocalService newsAttachedFileLocalService) {

		_newsAttachedFileLocalService = newsAttachedFileLocalService;
	}

	private NewsAttachedFileLocalService _newsAttachedFileLocalService;

}