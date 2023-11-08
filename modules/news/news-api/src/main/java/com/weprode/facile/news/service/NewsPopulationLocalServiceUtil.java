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

import com.weprode.facile.news.model.NewsPopulation;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for NewsPopulation. This utility wraps
 * <code>com.weprode.facile.news.service.impl.NewsPopulationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see NewsPopulationLocalService
 * @generated
 */
public class NewsPopulationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.news.service.impl.NewsPopulationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static NewsPopulation addNewsPopulation(
		NewsPopulation newsPopulation) {

		return getService().addNewsPopulation(newsPopulation);
	}

	public static NewsPopulation addPopulation(
			long newsId, long groupId, long roleId)
		throws SystemException {

		return getService().addPopulation(newsId, groupId, roleId);
	}

	public static org.json.JSONArray convertNewsPopulations(
			long newsId, long userId)
		throws SystemException {

		return getService().convertNewsPopulations(newsId, userId);
	}

	/**
	 * Creates a new news population with the primary key. Does not add the news population to the database.
	 *
	 * @param newsPopulationPK the primary key for the new news population
	 * @return the new news population
	 */
	public static NewsPopulation createNewsPopulation(
		com.weprode.facile.news.service.persistence.NewsPopulationPK
			newsPopulationPK) {

		return getService().createNewsPopulation(newsPopulationPK);
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

	public static void deleteByNewsId(long newsId) throws SystemException {
		getService().deleteByNewsId(newsId);
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
	public static NewsPopulation deleteNewsPopulation(
		NewsPopulation newsPopulation) {

		return getService().deleteNewsPopulation(newsPopulation);
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
	public static NewsPopulation deleteNewsPopulation(
			com.weprode.facile.news.service.persistence.NewsPopulationPK
				newsPopulationPK)
		throws PortalException {

		return getService().deleteNewsPopulation(newsPopulationPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsPopulationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsPopulationModelImpl</code>.
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

	public static NewsPopulation fetchNewsPopulation(
		com.weprode.facile.news.service.persistence.NewsPopulationPK
			newsPopulationPK) {

		return getService().fetchNewsPopulation(newsPopulationPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<NewsPopulation> getByGroupId(long groupId) {
		return getService().getByGroupId(groupId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the news population with the primary key.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population
	 * @throws PortalException if a news population with the primary key could not be found
	 */
	public static NewsPopulation getNewsPopulation(
			com.weprode.facile.news.service.persistence.NewsPopulationPK
				newsPopulationPK)
		throws PortalException {

		return getService().getNewsPopulation(newsPopulationPK);
	}

	/**
	 * Returns a range of all the news populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.news.model.impl.NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of news populations
	 */
	public static List<NewsPopulation> getNewsPopulations(int start, int end) {
		return getService().getNewsPopulations(start, end);
	}

	public static List<NewsPopulation> getNewsPopulations(long newsId)
		throws SystemException {

		return getService().getNewsPopulations(newsId);
	}

	/**
	 * Returns the number of news populations.
	 *
	 * @return the number of news populations
	 */
	public static int getNewsPopulationsCount() {
		return getService().getNewsPopulationsCount();
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
	 * Updates the news population in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsPopulationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsPopulation the news population
	 * @return the news population that was updated
	 */
	public static NewsPopulation updateNewsPopulation(
		NewsPopulation newsPopulation) {

		return getService().updateNewsPopulation(newsPopulation);
	}

	public static NewsPopulationLocalService getService() {
		return _service;
	}

	private static volatile NewsPopulationLocalService _service;

}