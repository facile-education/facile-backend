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

import com.weprode.nero.news.model.NewsAttachedFile;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for NewsAttachedFile. This utility wraps
 * <code>com.weprode.nero.news.service.impl.NewsAttachedFileLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see NewsAttachedFileLocalService
 * @generated
 */
public class NewsAttachedFileLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.news.service.impl.NewsAttachedFileLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean addFile(long newsId, long groupId, long fileId) {
		return getService().addFile(newsId, groupId, fileId);
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
	public static NewsAttachedFile addNewsAttachedFile(
		NewsAttachedFile newsAttachedFile) {

		return getService().addNewsAttachedFile(newsAttachedFile);
	}

	public static org.json.JSONArray convertNewsFiles(
		long newsId, long userId) {

		return getService().convertNewsFiles(newsId, userId);
	}

	/**
	 * Creates a new news attached file with the primary key. Does not add the news attached file to the database.
	 *
	 * @param newsFileId the primary key for the new news attached file
	 * @return the new news attached file
	 */
	public static NewsAttachedFile createNewsAttachedFile(long newsFileId) {
		return getService().createNewsAttachedFile(newsFileId);
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
	public static NewsAttachedFile deleteNewsAttachedFile(long newsFileId)
		throws PortalException {

		return getService().deleteNewsAttachedFile(newsFileId);
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
	public static NewsAttachedFile deleteNewsAttachedFile(
		NewsAttachedFile newsAttachedFile) {

		return getService().deleteNewsAttachedFile(newsAttachedFile);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsAttachedFileModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsAttachedFileModelImpl</code>.
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

	public static NewsAttachedFile fetchNewsAttachedFile(long newsFileId) {
		return getService().fetchNewsAttachedFile(newsFileId);
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
	 * Returns the news attached file with the primary key.
	 *
	 * @param newsFileId the primary key of the news attached file
	 * @return the news attached file
	 * @throws PortalException if a news attached file with the primary key could not be found
	 */
	public static NewsAttachedFile getNewsAttachedFile(long newsFileId)
		throws PortalException {

		return getService().getNewsAttachedFile(newsFileId);
	}

	/**
	 * Returns a range of all the news attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.news.model.impl.NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @return the range of news attached files
	 */
	public static List<NewsAttachedFile> getNewsAttachedFiles(
		int start, int end) {

		return getService().getNewsAttachedFiles(start, end);
	}

	public static List<NewsAttachedFile> getNewsAttachedFiles(long newsId) {
		return getService().getNewsAttachedFiles(newsId);
	}

	/**
	 * Returns the number of news attached files.
	 *
	 * @return the number of news attached files
	 */
	public static int getNewsAttachedFilesCount() {
		return getService().getNewsAttachedFilesCount();
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

	public static boolean hasAttachedFiles(long newsId) {
		return getService().hasAttachedFiles(newsId);
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
	public static NewsAttachedFile updateNewsAttachedFile(
		NewsAttachedFile newsAttachedFile) {

		return getService().updateNewsAttachedFile(newsAttachedFile);
	}

	public static NewsAttachedFileLocalService getService() {
		return _service;
	}

	private static volatile NewsAttachedFileLocalService _service;

}