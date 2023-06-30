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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.mobile.model.MobileDevice;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for MobileDevice. This utility wraps
 * <code>com.weprode.nero.mobile.service.impl.MobileDeviceLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MobileDeviceLocalService
 * @generated
 */
public class MobileDeviceLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.mobile.service.impl.MobileDeviceLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static MobileDevice addMobileDevice(MobileDevice mobileDevice) {
		return getService().addMobileDevice(mobileDevice);
	}

	/**
	 * Add new mobile device for user
	 */
	public static MobileDevice addMobileDevice(
			String manufacturerDeviceId, long userId, String model,
			String manufacturer, String os, String osVersion, String browserUA)
		throws SystemException {

		return getService().addMobileDevice(
			manufacturerDeviceId, userId, model, manufacturer, os, osVersion,
			browserUA);
	}

	/**
	 * Creates a new mobile device with the primary key. Does not add the mobile device to the database.
	 *
	 * @param mobileDeviceId the primary key for the new mobile device
	 * @return the new mobile device
	 */
	public static MobileDevice createMobileDevice(long mobileDeviceId) {
		return getService().createMobileDevice(mobileDeviceId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static MobileDevice deleteMobileDevice(long mobileDeviceId)
		throws PortalException {

		return getService().deleteMobileDevice(mobileDeviceId);
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
	public static MobileDevice deleteMobileDevice(MobileDevice mobileDevice) {
		return getService().deleteMobileDevice(mobileDevice);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.mobile.model.impl.MobileDeviceModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.mobile.model.impl.MobileDeviceModelImpl</code>.
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

	public static MobileDevice fetchMobileDevice(long mobileDeviceId) {
		return getService().fetchMobileDevice(mobileDeviceId);
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
	 * Returns the mobile device with the primary key.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device
	 * @throws PortalException if a mobile device with the primary key could not be found
	 */
	public static MobileDevice getMobileDevice(long mobileDeviceId)
		throws PortalException {

		return getService().getMobileDevice(mobileDeviceId);
	}

	public static MobileDevice getMobileDeviceByManufacturerDeviceId(
			String manufacturerDeviceId)
		throws SystemException {

		return getService().getMobileDeviceByManufacturerDeviceId(
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
	public static List<MobileDevice> getMobileDevices(int start, int end) {
		return getService().getMobileDevices(start, end);
	}

	/**
	 * Returns the number of mobile devices.
	 *
	 * @return the number of mobile devices
	 */
	public static int getMobileDevicesCount() {
		return getService().getMobileDevicesCount();
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

	public static List<MobileDevice> getUserMobileDevices(long userId)
		throws SystemException {

		return getService().getUserMobileDevices(userId);
	}

	public static void pushNotificationToGroup(
		List<Long> groupIds, long senderId, String title, String subtitle,
		String message, String service, long paramId) {

		getService().pushNotificationToGroup(
			groupIds, senderId, title, subtitle, message, service, paramId);
	}

	public static void pushNotificationToUser(
		long userId, String title, String subtitle, String message,
		String service, long paramId) {

		getService().pushNotificationToUser(
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
	public static MobileDevice updateMobileDevice(MobileDevice mobileDevice) {
		return getService().updateMobileDevice(mobileDevice);
	}

	public static MobileDeviceLocalService getService() {
		return _service;
	}

	private static volatile MobileDeviceLocalService _service;

}