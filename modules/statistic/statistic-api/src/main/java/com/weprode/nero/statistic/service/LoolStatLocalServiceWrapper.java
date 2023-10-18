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

package com.weprode.nero.statistic.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoolStatLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LoolStatLocalService
 * @generated
 */
public class LoolStatLocalServiceWrapper
	implements LoolStatLocalService, ServiceWrapper<LoolStatLocalService> {

	public LoolStatLocalServiceWrapper() {
		this(null);
	}

	public LoolStatLocalServiceWrapper(
		LoolStatLocalService loolStatLocalService) {

		_loolStatLocalService = loolStatLocalService;
	}

	@Override
	public com.weprode.nero.statistic.model.LoolStat addLoolStat(
		long objectId, long userId, boolean saveAction, int type) {

		return _loolStatLocalService.addLoolStat(
			objectId, userId, saveAction, type);
	}

	/**
	 * Adds the lool stat to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoolStatLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loolStat the lool stat
	 * @return the lool stat that was added
	 */
	@Override
	public com.weprode.nero.statistic.model.LoolStat addLoolStat(
		com.weprode.nero.statistic.model.LoolStat loolStat) {

		return _loolStatLocalService.addLoolStat(loolStat);
	}

	/**
	 * Creates a new lool stat with the primary key. Does not add the lool stat to the database.
	 *
	 * @param statId the primary key for the new lool stat
	 * @return the new lool stat
	 */
	@Override
	public com.weprode.nero.statistic.model.LoolStat createLoolStat(
		long statId) {

		return _loolStatLocalService.createLoolStat(statId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolStatLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the lool stat with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoolStatLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat that was removed
	 * @throws PortalException if a lool stat with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.statistic.model.LoolStat deleteLoolStat(long statId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolStatLocalService.deleteLoolStat(statId);
	}

	/**
	 * Deletes the lool stat from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoolStatLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loolStat the lool stat
	 * @return the lool stat that was removed
	 */
	@Override
	public com.weprode.nero.statistic.model.LoolStat deleteLoolStat(
		com.weprode.nero.statistic.model.LoolStat loolStat) {

		return _loolStatLocalService.deleteLoolStat(loolStat);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolStatLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _loolStatLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _loolStatLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loolStatLocalService.dynamicQuery();
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

		return _loolStatLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.statistic.model.impl.LoolStatModelImpl</code>.
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

		return _loolStatLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.statistic.model.impl.LoolStatModelImpl</code>.
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

		return _loolStatLocalService.dynamicQuery(
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

		return _loolStatLocalService.dynamicQueryCount(dynamicQuery);
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

		return _loolStatLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.statistic.model.LoolStat fetchLoolStat(
		long statId) {

		return _loolStatLocalService.fetchLoolStat(statId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _loolStatLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _loolStatLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the lool stat with the primary key.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat
	 * @throws PortalException if a lool stat with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.statistic.model.LoolStat getLoolStat(long statId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolStatLocalService.getLoolStat(statId);
	}

	/**
	 * Returns a range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.statistic.model.impl.LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @return the range of lool stats
	 */
	@Override
	public java.util.List<com.weprode.nero.statistic.model.LoolStat>
		getLoolStats(int start, int end) {

		return _loolStatLocalService.getLoolStats(start, end);
	}

	/**
	 * Returns the number of lool stats.
	 *
	 * @return the number of lool stats
	 */
	@Override
	public int getLoolStatsCount() {
		return _loolStatLocalService.getLoolStatsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loolStatLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolStatLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the lool stat in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoolStatLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loolStat the lool stat
	 * @return the lool stat that was updated
	 */
	@Override
	public com.weprode.nero.statistic.model.LoolStat updateLoolStat(
		com.weprode.nero.statistic.model.LoolStat loolStat) {

		return _loolStatLocalService.updateLoolStat(loolStat);
	}

	@Override
	public LoolStatLocalService getWrappedService() {
		return _loolStatLocalService;
	}

	@Override
	public void setWrappedService(LoolStatLocalService loolStatLocalService) {
		_loolStatLocalService = loolStatLocalService;
	}

	private LoolStatLocalService _loolStatLocalService;

}