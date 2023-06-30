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

package com.weprode.nero.mobile.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MobileDeviceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MobileDeviceLocalService
 * @generated
 */
public class MobileDeviceLocalServiceWrapper
	implements MobileDeviceLocalService,
			   ServiceWrapper<MobileDeviceLocalService> {

	public MobileDeviceLocalServiceWrapper(
		MobileDeviceLocalService mobileDeviceLocalService) {

		_mobileDeviceLocalService = mobileDeviceLocalService;
	}

	/**
	 * Adds the mobile device to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileDeviceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileDevice the mobile device
	 * @return the mobile device that was added
	 */
	@Override
	public com.weprode.nero.mobile.model.MobileDevice addMobileDevice(
		com.weprode.nero.mobile.model.MobileDevice mobileDevice) {

		return _mobileDeviceLocalService.addMobileDevice(mobileDevice);
	}

	/**
	 * Add new mobile device for user
	 */
	@Override
	public com.weprode.nero.mobile.model.MobileDevice addMobileDevice(
			String manufacturerDeviceId, long userId, String model,
			String manufacturer, String os, String osVersion, String browserUA)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _mobileDeviceLocalService.addMobileDevice(
			manufacturerDeviceId, userId, model, manufacturer, os, osVersion,
			browserUA);
	}

	/**
	 * Creates a new mobile device with the primary key. Does not add the mobile device to the database.
	 *
	 * @param mobileDeviceId the primary key for the new mobile device
	 * @return the new mobile device
	 */
	@Override
	public com.weprode.nero.mobile.model.MobileDevice createMobileDevice(
		long mobileDeviceId) {

		return _mobileDeviceLocalService.createMobileDevice(mobileDeviceId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileDeviceLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the mobile device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileDeviceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device that was removed
	 * @throws PortalException if a mobile device with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.mobile.model.MobileDevice deleteMobileDevice(
			long mobileDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileDeviceLocalService.deleteMobileDevice(mobileDeviceId);
	}

	/**
	 * Deletes the mobile device from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileDeviceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileDevice the mobile device
	 * @return the mobile device that was removed
	 */
	@Override
	public com.weprode.nero.mobile.model.MobileDevice deleteMobileDevice(
		com.weprode.nero.mobile.model.MobileDevice mobileDevice) {

		return _mobileDeviceLocalService.deleteMobileDevice(mobileDevice);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileDeviceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _mobileDeviceLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _mobileDeviceLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mobileDeviceLocalService.dynamicQuery();
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

		return _mobileDeviceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.mobile.model.impl.MobileDeviceModelImpl</code>.
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

		return _mobileDeviceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.mobile.model.impl.MobileDeviceModelImpl</code>.
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

		return _mobileDeviceLocalService.dynamicQuery(
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

		return _mobileDeviceLocalService.dynamicQueryCount(dynamicQuery);
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

		return _mobileDeviceLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.mobile.model.MobileDevice fetchMobileDevice(
		long mobileDeviceId) {

		return _mobileDeviceLocalService.fetchMobileDevice(mobileDeviceId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _mobileDeviceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _mobileDeviceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the mobile device with the primary key.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device
	 * @throws PortalException if a mobile device with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.mobile.model.MobileDevice getMobileDevice(
			long mobileDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileDeviceLocalService.getMobileDevice(mobileDeviceId);
	}

	@Override
	public com.weprode.nero.mobile.model.MobileDevice
			getMobileDeviceByManufacturerDeviceId(String manufacturerDeviceId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _mobileDeviceLocalService.getMobileDeviceByManufacturerDeviceId(
			manufacturerDeviceId);
	}

	/**
	 * Returns a range of all the mobile devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.mobile.model.impl.MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @return the range of mobile devices
	 */
	@Override
	public java.util.List<com.weprode.nero.mobile.model.MobileDevice>
		getMobileDevices(int start, int end) {

		return _mobileDeviceLocalService.getMobileDevices(start, end);
	}

	/**
	 * Returns the number of mobile devices.
	 *
	 * @return the number of mobile devices
	 */
	@Override
	public int getMobileDevicesCount() {
		return _mobileDeviceLocalService.getMobileDevicesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _mobileDeviceLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileDeviceLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.weprode.nero.mobile.model.MobileDevice>
			getUserMobileDevices(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _mobileDeviceLocalService.getUserMobileDevices(userId);
	}

	@Override
	public void pushNotificationToGroup(
		java.util.List<Long> groupIds, long senderId, String title,
		String subtitle, String message, String service, long paramId) {

		_mobileDeviceLocalService.pushNotificationToGroup(
			groupIds, senderId, title, subtitle, message, service, paramId);
	}

	@Override
	public void pushNotificationToUser(
		long userId, String title, String subtitle, String message,
		String service, long paramId) {

		_mobileDeviceLocalService.pushNotificationToUser(
			userId, title, subtitle, message, service, paramId);
	}

	/**
	 * Updates the mobile device in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileDeviceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileDevice the mobile device
	 * @return the mobile device that was updated
	 */
	@Override
	public com.weprode.nero.mobile.model.MobileDevice updateMobileDevice(
		com.weprode.nero.mobile.model.MobileDevice mobileDevice) {

		return _mobileDeviceLocalService.updateMobileDevice(mobileDevice);
	}

	@Override
	public MobileDeviceLocalService getWrappedService() {
		return _mobileDeviceLocalService;
	}

	@Override
	public void setWrappedService(
		MobileDeviceLocalService mobileDeviceLocalService) {

		_mobileDeviceLocalService = mobileDeviceLocalService;
	}

	private MobileDeviceLocalService _mobileDeviceLocalService;

}