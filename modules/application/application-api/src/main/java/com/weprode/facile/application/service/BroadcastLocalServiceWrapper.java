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

package com.weprode.facile.application.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BroadcastLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastLocalService
 * @generated
 */
public class BroadcastLocalServiceWrapper
	implements BroadcastLocalService, ServiceWrapper<BroadcastLocalService> {

	public BroadcastLocalServiceWrapper() {
		this(null);
	}

	public BroadcastLocalServiceWrapper(
		BroadcastLocalService broadcastLocalService) {

		_broadcastLocalService = broadcastLocalService;
	}

	/**
	 * Adds the broadcast to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcast the broadcast
	 * @return the broadcast that was added
	 */
	@Override
	public com.weprode.facile.application.model.Broadcast addBroadcast(
		com.weprode.facile.application.model.Broadcast broadcast) {

		return _broadcastLocalService.addBroadcast(broadcast);
	}

	/**
	 * Creates a new broadcast with the primary key. Does not add the broadcast to the database.
	 *
	 * @param broadcastId the primary key for the new broadcast
	 * @return the new broadcast
	 */
	@Override
	public com.weprode.facile.application.model.Broadcast createBroadcast(
		long broadcastId) {

		return _broadcastLocalService.createBroadcast(broadcastId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public com.weprode.facile.application.model.Broadcast createSchoolBroadcast(
			long applicationId, long schoolId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _broadcastLocalService.createSchoolBroadcast(
			applicationId, schoolId);
	}

	/**
	 * Deletes the broadcast from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcast the broadcast
	 * @return the broadcast that was removed
	 */
	@Override
	public com.weprode.facile.application.model.Broadcast deleteBroadcast(
		com.weprode.facile.application.model.Broadcast broadcast) {

		return _broadcastLocalService.deleteBroadcast(broadcast);
	}

	/**
	 * Deletes the broadcast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast that was removed
	 * @throws PortalException if a broadcast with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.application.model.Broadcast deleteBroadcast(
			long broadcastId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastLocalService.deleteBroadcast(broadcastId);
	}

	@Override
	public boolean deleteBroadcastForSchool(long applicationId, long schoolId) {
		return _broadcastLocalService.deleteBroadcastForSchool(
			applicationId, schoolId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _broadcastLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _broadcastLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _broadcastLocalService.dynamicQuery();
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

		return _broadcastLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.application.model.impl.BroadcastModelImpl</code>.
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

		return _broadcastLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.application.model.impl.BroadcastModelImpl</code>.
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

		return _broadcastLocalService.dynamicQuery(
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

		return _broadcastLocalService.dynamicQueryCount(dynamicQuery);
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

		return _broadcastLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.application.model.Broadcast fetchBroadcast(
		long broadcastId) {

		return _broadcastLocalService.fetchBroadcast(broadcastId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _broadcastLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the broadcast with the primary key.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast
	 * @throws PortalException if a broadcast with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.application.model.Broadcast getBroadcast(
			long broadcastId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastLocalService.getBroadcast(broadcastId);
	}

	@Override
	public java.util.List<Long> getBroadcastedSchools(long applicationId) {
		return _broadcastLocalService.getBroadcastedSchools(applicationId);
	}

	/**
	 * Returns a range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.application.model.impl.BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @return the range of broadcasts
	 */
	@Override
	public java.util.List<com.weprode.facile.application.model.Broadcast>
		getBroadcasts(int start, int end) {

		return _broadcastLocalService.getBroadcasts(start, end);
	}

	/**
	 * Returns the number of broadcasts.
	 *
	 * @return the number of broadcasts
	 */
	@Override
	public int getBroadcastsCount() {
		return _broadcastLocalService.getBroadcastsCount();
	}

	@Override
	public com.weprode.facile.application.model.Broadcast
			getByApplicationIdEtabId(long applicationId, long schoolId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _broadcastLocalService.getByApplicationIdEtabId(
			applicationId, schoolId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _broadcastLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _broadcastLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean isApplicationBroadcastedToUser(
		long userId, String applicationKey) {

		return _broadcastLocalService.isApplicationBroadcastedToUser(
			userId, applicationKey);
	}

	@Override
	public boolean removeBroadcast(Long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _broadcastLocalService.removeBroadcast(applicationId);
	}

	/**
	 * Updates the broadcast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcast the broadcast
	 * @return the broadcast that was updated
	 */
	@Override
	public com.weprode.facile.application.model.Broadcast updateBroadcast(
		com.weprode.facile.application.model.Broadcast broadcast) {

		return _broadcastLocalService.updateBroadcast(broadcast);
	}

	@Override
	public com.weprode.facile.application.model.Broadcast updateBroadcast(
			long applicationId, long schoolId, boolean isBroadcasted,
			String applicationUrl)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _broadcastLocalService.updateBroadcast(
			applicationId, schoolId, isBroadcasted, applicationUrl);
	}

	@Override
	public BroadcastLocalService getWrappedService() {
		return _broadcastLocalService;
	}

	@Override
	public void setWrappedService(BroadcastLocalService broadcastLocalService) {
		_broadcastLocalService = broadcastLocalService;
	}

	private BroadcastLocalService _broadcastLocalService;

}