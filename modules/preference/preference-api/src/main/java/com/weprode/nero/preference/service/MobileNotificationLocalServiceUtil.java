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

package com.weprode.nero.preference.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.preference.model.MobileNotification;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for MobileNotification. This utility wraps
 * <code>com.weprode.nero.preference.service.impl.MobileNotificationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MobileNotificationLocalService
 * @generated
 */
public class MobileNotificationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.preference.service.impl.MobileNotificationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static MobileNotification addMobileNotification()
		throws SystemException {

		return getService().addMobileNotification();
	}

	public static MobileNotification addMobileNotification(
			long userId, long etabId, String token, String device)
		throws PortalException, SystemException {

		return getService().addMobileNotification(
			userId, etabId, token, device);
	}

	/**
	 * Adds the mobile notification to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileNotificationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileNotification the mobile notification
	 * @return the mobile notification that was added
	 */
	public static MobileNotification addMobileNotification(
		MobileNotification mobileNotification) {

		return getService().addMobileNotification(mobileNotification);
	}

	/**
	 * Creates a new mobile notification with the primary key. Does not add the mobile notification to the database.
	 *
	 * @param mobileNotificationId the primary key for the new mobile notification
	 * @return the new mobile notification
	 */
	public static MobileNotification createMobileNotification(
		long mobileNotificationId) {

		return getService().createMobileNotification(mobileNotificationId);
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
	 * Deletes the mobile notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileNotificationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification that was removed
	 * @throws PortalException if a mobile notification with the primary key could not be found
	 */
	public static MobileNotification deleteMobileNotification(
			long mobileNotificationId)
		throws PortalException {

		return getService().deleteMobileNotification(mobileNotificationId);
	}

	/**
	 * Deletes the mobile notification from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileNotificationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileNotification the mobile notification
	 * @return the mobile notification that was removed
	 */
	public static MobileNotification deleteMobileNotification(
		MobileNotification mobileNotification) {

		return getService().deleteMobileNotification(mobileNotification);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.MobileNotificationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.MobileNotificationModelImpl</code>.
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

	public static MobileNotification fetchMobileNotification(
		long mobileNotificationId) {

		return getService().fetchMobileNotification(mobileNotificationId);
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
	 * Returns the mobile notification with the primary key.
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification
	 * @throws PortalException if a mobile notification with the primary key could not be found
	 */
	public static MobileNotification getMobileNotification(
			long mobileNotificationId)
		throws PortalException {

		return getService().getMobileNotification(mobileNotificationId);
	}

	public static List<MobileNotification> getMobileNotificationByEtabId(
			long etabId)
		throws SystemException {

		return getService().getMobileNotificationByEtabId(etabId);
	}

	public static List<MobileNotification> getMobileNotificationByEtabIdEnable(
			long etabId, Boolean enable)
		throws SystemException {

		return getService().getMobileNotificationByEtabIdEnable(etabId, enable);
	}

	public static List<MobileNotification> getMobileNotificationByUserId(
			long userId)
		throws SystemException {

		return getService().getMobileNotificationByUserId(userId);
	}

	public static List<MobileNotification> getMobileNotificationByUserIdEnable(
			long userId, Boolean enable)
		throws SystemException {

		return getService().getMobileNotificationByUserIdEnable(userId, enable);
	}

	/**
	 * Returns a range of all the mobile notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of mobile notifications
	 */
	public static List<MobileNotification> getMobileNotifications(
		int start, int end) {

		return getService().getMobileNotifications(start, end);
	}

	/**
	 * Returns the number of mobile notifications.
	 *
	 * @return the number of mobile notifications
	 */
	public static int getMobileNotificationsCount() {
		return getService().getMobileNotificationsCount();
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

	public static void removeMobileNotification(long mobileNotificationId)
		throws PortalException, SystemException {

		getService().removeMobileNotification(mobileNotificationId);
	}

	/**
	 * Updates the mobile notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileNotificationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileNotification the mobile notification
	 * @return the mobile notification that was updated
	 */
	public static MobileNotification updateMobileNotification(
		MobileNotification mobileNotification) {

		return getService().updateMobileNotification(mobileNotification);
	}

	public static MobileNotificationLocalService getService() {
		return _service;
	}

	private static volatile MobileNotificationLocalService _service;

}