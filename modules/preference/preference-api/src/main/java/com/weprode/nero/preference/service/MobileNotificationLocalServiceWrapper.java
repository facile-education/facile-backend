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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MobileNotificationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MobileNotificationLocalService
 * @generated
 */
public class MobileNotificationLocalServiceWrapper
	implements MobileNotificationLocalService,
			   ServiceWrapper<MobileNotificationLocalService> {

	public MobileNotificationLocalServiceWrapper(
		MobileNotificationLocalService mobileNotificationLocalService) {

		_mobileNotificationLocalService = mobileNotificationLocalService;
	}

	@Override
	public com.weprode.nero.preference.model.MobileNotification
			addMobileNotification()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _mobileNotificationLocalService.addMobileNotification();
	}

	@Override
	public com.weprode.nero.preference.model.MobileNotification
			addMobileNotification(
				long userId, long etabId, String token, String device)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _mobileNotificationLocalService.addMobileNotification(
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
	@Override
	public com.weprode.nero.preference.model.MobileNotification
		addMobileNotification(
			com.weprode.nero.preference.model.MobileNotification
				mobileNotification) {

		return _mobileNotificationLocalService.addMobileNotification(
			mobileNotification);
	}

	/**
	 * Creates a new mobile notification with the primary key. Does not add the mobile notification to the database.
	 *
	 * @param mobileNotificationId the primary key for the new mobile notification
	 * @return the new mobile notification
	 */
	@Override
	public com.weprode.nero.preference.model.MobileNotification
		createMobileNotification(long mobileNotificationId) {

		return _mobileNotificationLocalService.createMobileNotification(
			mobileNotificationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileNotificationLocalService.createPersistedModel(
			primaryKeyObj);
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
	@Override
	public com.weprode.nero.preference.model.MobileNotification
			deleteMobileNotification(long mobileNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileNotificationLocalService.deleteMobileNotification(
			mobileNotificationId);
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
	@Override
	public com.weprode.nero.preference.model.MobileNotification
		deleteMobileNotification(
			com.weprode.nero.preference.model.MobileNotification
				mobileNotification) {

		return _mobileNotificationLocalService.deleteMobileNotification(
			mobileNotification);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileNotificationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _mobileNotificationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _mobileNotificationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mobileNotificationLocalService.dynamicQuery();
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

		return _mobileNotificationLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _mobileNotificationLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _mobileNotificationLocalService.dynamicQuery(
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

		return _mobileNotificationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _mobileNotificationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.preference.model.MobileNotification
		fetchMobileNotification(long mobileNotificationId) {

		return _mobileNotificationLocalService.fetchMobileNotification(
			mobileNotificationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _mobileNotificationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _mobileNotificationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the mobile notification with the primary key.
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification
	 * @throws PortalException if a mobile notification with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.preference.model.MobileNotification
			getMobileNotification(long mobileNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileNotificationLocalService.getMobileNotification(
			mobileNotificationId);
	}

	@Override
	public java.util.List<com.weprode.nero.preference.model.MobileNotification>
			getMobileNotificationByEtabId(long etabId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _mobileNotificationLocalService.getMobileNotificationByEtabId(
			etabId);
	}

	@Override
	public java.util.List<com.weprode.nero.preference.model.MobileNotification>
			getMobileNotificationByEtabIdEnable(long etabId, Boolean enable)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _mobileNotificationLocalService.
			getMobileNotificationByEtabIdEnable(etabId, enable);
	}

	@Override
	public java.util.List<com.weprode.nero.preference.model.MobileNotification>
			getMobileNotificationByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _mobileNotificationLocalService.getMobileNotificationByUserId(
			userId);
	}

	@Override
	public java.util.List<com.weprode.nero.preference.model.MobileNotification>
			getMobileNotificationByUserIdEnable(long userId, Boolean enable)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _mobileNotificationLocalService.
			getMobileNotificationByUserIdEnable(userId, enable);
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
	@Override
	public java.util.List<com.weprode.nero.preference.model.MobileNotification>
		getMobileNotifications(int start, int end) {

		return _mobileNotificationLocalService.getMobileNotifications(
			start, end);
	}

	/**
	 * Returns the number of mobile notifications.
	 *
	 * @return the number of mobile notifications
	 */
	@Override
	public int getMobileNotificationsCount() {
		return _mobileNotificationLocalService.getMobileNotificationsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _mobileNotificationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mobileNotificationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void removeMobileNotification(long mobileNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_mobileNotificationLocalService.removeMobileNotification(
			mobileNotificationId);
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
	@Override
	public com.weprode.nero.preference.model.MobileNotification
		updateMobileNotification(
			com.weprode.nero.preference.model.MobileNotification
				mobileNotification) {

		return _mobileNotificationLocalService.updateMobileNotification(
			mobileNotification);
	}

	@Override
	public MobileNotificationLocalService getWrappedService() {
		return _mobileNotificationLocalService;
	}

	@Override
	public void setWrappedService(
		MobileNotificationLocalService mobileNotificationLocalService) {

		_mobileNotificationLocalService = mobileNotificationLocalService;
	}

	private MobileNotificationLocalService _mobileNotificationLocalService;

}