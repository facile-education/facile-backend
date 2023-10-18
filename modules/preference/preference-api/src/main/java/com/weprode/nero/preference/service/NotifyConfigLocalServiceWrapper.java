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
 * Provides a wrapper for {@link NotifyConfigLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NotifyConfigLocalService
 * @generated
 */
public class NotifyConfigLocalServiceWrapper
	implements NotifyConfigLocalService,
			   ServiceWrapper<NotifyConfigLocalService> {

	public NotifyConfigLocalServiceWrapper() {
		this(null);
	}

	public NotifyConfigLocalServiceWrapper(
		NotifyConfigLocalService notifyConfigLocalService) {

		_notifyConfigLocalService = notifyConfigLocalService;
	}

	@Override
	public com.weprode.nero.preference.model.NotifyConfig addNotifyConfig()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _notifyConfigLocalService.addNotifyConfig();
	}

	/**
	 * Adds the notify config to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfig the notify config
	 * @return the notify config that was added
	 */
	@Override
	public com.weprode.nero.preference.model.NotifyConfig addNotifyConfig(
		com.weprode.nero.preference.model.NotifyConfig notifyConfig) {

		return _notifyConfigLocalService.addNotifyConfig(notifyConfig);
	}

	/**
	 * Creates a new notify config with the primary key. Does not add the notify config to the database.
	 *
	 * @param notifyConfigId the primary key for the new notify config
	 * @return the new notify config
	 */
	@Override
	public com.weprode.nero.preference.model.NotifyConfig createNotifyConfig(
		long notifyConfigId) {

		return _notifyConfigLocalService.createNotifyConfig(notifyConfigId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notifyConfigLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the notify config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config that was removed
	 * @throws PortalException if a notify config with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.preference.model.NotifyConfig deleteNotifyConfig(
			long notifyConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notifyConfigLocalService.deleteNotifyConfig(notifyConfigId);
	}

	/**
	 * Deletes the notify config from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfig the notify config
	 * @return the notify config that was removed
	 */
	@Override
	public com.weprode.nero.preference.model.NotifyConfig deleteNotifyConfig(
		com.weprode.nero.preference.model.NotifyConfig notifyConfig) {

		return _notifyConfigLocalService.deleteNotifyConfig(notifyConfig);
	}

	@Override
	public void deleteNotifyConfigByUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.preference.exception.
				   NoSuchNotifyConfigException {

		_notifyConfigLocalService.deleteNotifyConfigByUser(userId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notifyConfigLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _notifyConfigLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _notifyConfigLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _notifyConfigLocalService.dynamicQuery();
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

		return _notifyConfigLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.NotifyConfigModelImpl</code>.
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

		return _notifyConfigLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.NotifyConfigModelImpl</code>.
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

		return _notifyConfigLocalService.dynamicQuery(
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

		return _notifyConfigLocalService.dynamicQueryCount(dynamicQuery);
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

		return _notifyConfigLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.preference.model.NotifyConfig fetchNotifyConfig(
		long notifyConfigId) {

		return _notifyConfigLocalService.fetchNotifyConfig(notifyConfigId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _notifyConfigLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _notifyConfigLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the notify config with the primary key.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config
	 * @throws PortalException if a notify config with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.preference.model.NotifyConfig getNotifyConfig(
			long notifyConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notifyConfigLocalService.getNotifyConfig(notifyConfigId);
	}

	@Override
	public java.util.List<com.weprode.nero.preference.model.NotifyConfig>
			getNotifyConfigActivate(boolean activate)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _notifyConfigLocalService.getNotifyConfigActivate(activate);
	}

	@Override
	public java.util.List<com.weprode.nero.preference.model.NotifyConfig>
			getNotifyConfigActivateDigestPeriod(
				boolean activate, int digestPeriod)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _notifyConfigLocalService.getNotifyConfigActivateDigestPeriod(
			activate, digestPeriod);
	}

	/**
	 * Returns a range of all the notify configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @return the range of notify configs
	 */
	@Override
	public java.util.List<com.weprode.nero.preference.model.NotifyConfig>
		getNotifyConfigs(int start, int end) {

		return _notifyConfigLocalService.getNotifyConfigs(start, end);
	}

	/**
	 * Returns the number of notify configs.
	 *
	 * @return the number of notify configs
	 */
	@Override
	public int getNotifyConfigsCount() {
		return _notifyConfigLocalService.getNotifyConfigsCount();
	}

	@Override
	public com.weprode.nero.preference.model.NotifyConfig
			getOrCreateNotifyConfig(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _notifyConfigLocalService.getOrCreateNotifyConfig(userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _notifyConfigLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notifyConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the notify config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfig the notify config
	 * @return the notify config that was updated
	 */
	@Override
	public com.weprode.nero.preference.model.NotifyConfig updateNotifyConfig(
		com.weprode.nero.preference.model.NotifyConfig notifyConfig) {

		return _notifyConfigLocalService.updateNotifyConfig(notifyConfig);
	}

	@Override
	public NotifyConfigLocalService getWrappedService() {
		return _notifyConfigLocalService;
	}

	@Override
	public void setWrappedService(
		NotifyConfigLocalService notifyConfigLocalService) {

		_notifyConfigLocalService = notifyConfigLocalService;
	}

	private NotifyConfigLocalService _notifyConfigLocalService;

}