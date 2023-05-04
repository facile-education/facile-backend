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

package com.weprode.nero.messaging.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingConfigLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingConfigLocalService
 * @generated
 */
public class MessagingConfigLocalServiceWrapper
	implements MessagingConfigLocalService,
			   ServiceWrapper<MessagingConfigLocalService> {

	public MessagingConfigLocalServiceWrapper(
		MessagingConfigLocalService messagingConfigLocalService) {

		_messagingConfigLocalService = messagingConfigLocalService;
	}

	@Override
	public com.weprode.nero.messaging.model.MessagingConfig
		addDefaultMessagingConfig(long userId) {

		return _messagingConfigLocalService.addDefaultMessagingConfig(userId);
	}

	/**
	 * Adds the messaging config to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessagingConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messagingConfig the messaging config
	 * @return the messaging config that was added
	 */
	@Override
	public com.weprode.nero.messaging.model.MessagingConfig addMessagingConfig(
		com.weprode.nero.messaging.model.MessagingConfig messagingConfig) {

		return _messagingConfigLocalService.addMessagingConfig(messagingConfig);
	}

	/**
	 * Creates a new messaging config with the primary key. Does not add the messaging config to the database.
	 *
	 * @param userId the primary key for the new messaging config
	 * @return the new messaging config
	 */
	@Override
	public com.weprode.nero.messaging.model.MessagingConfig
		createMessagingConfig(long userId) {

		return _messagingConfigLocalService.createMessagingConfig(userId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagingConfigLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the messaging config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessagingConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config that was removed
	 * @throws PortalException if a messaging config with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.MessagingConfig
			deleteMessagingConfig(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagingConfigLocalService.deleteMessagingConfig(userId);
	}

	/**
	 * Deletes the messaging config from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessagingConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messagingConfig the messaging config
	 * @return the messaging config that was removed
	 */
	@Override
	public com.weprode.nero.messaging.model.MessagingConfig
		deleteMessagingConfig(
			com.weprode.nero.messaging.model.MessagingConfig messagingConfig) {

		return _messagingConfigLocalService.deleteMessagingConfig(
			messagingConfig);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagingConfigLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _messagingConfigLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _messagingConfigLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _messagingConfigLocalService.dynamicQuery();
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

		return _messagingConfigLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessagingConfigModelImpl</code>.
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

		return _messagingConfigLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessagingConfigModelImpl</code>.
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

		return _messagingConfigLocalService.dynamicQuery(
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

		return _messagingConfigLocalService.dynamicQueryCount(dynamicQuery);
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

		return _messagingConfigLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.messaging.model.MessagingConfig
		fetchMessagingConfig(long userId) {

		return _messagingConfigLocalService.fetchMessagingConfig(userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _messagingConfigLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns user messaging autoReply content if it is active
	 */
	@Override
	public String getAutoReply(long userId) {
		return _messagingConfigLocalService.getAutoReply(userId);
	}

	@Override
	public java.util.List<String> getForwardAddresses(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _messagingConfigLocalService.getForwardAddresses(userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _messagingConfigLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the messaging config with the primary key.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config
	 * @throws PortalException if a messaging config with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.messaging.model.MessagingConfig getMessagingConfig(
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagingConfigLocalService.getMessagingConfig(userId);
	}

	/**
	 * Returns a range of all the messaging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessagingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messaging configs
	 * @param end the upper bound of the range of messaging configs (not inclusive)
	 * @return the range of messaging configs
	 */
	@Override
	public java.util.List<com.weprode.nero.messaging.model.MessagingConfig>
		getMessagingConfigs(int start, int end) {

		return _messagingConfigLocalService.getMessagingConfigs(start, end);
	}

	/**
	 * Returns the number of messaging configs.
	 *
	 * @return the number of messaging configs
	 */
	@Override
	public int getMessagingConfigsCount() {
		return _messagingConfigLocalService.getMessagingConfigsCount();
	}

	@Override
	public com.weprode.nero.messaging.model.MessagingConfig
			getOrCreateMessagingConfig(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _messagingConfigLocalService.getOrCreateMessagingConfig(userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messagingConfigLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagingConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns user messaging signature if it is active
	 */
	@Override
	public String getSignature(long userId) {
		return _messagingConfigLocalService.getSignature(userId);
	}

	/**
	 * Returns true if user messaging autoReply is active
	 */
	@Override
	public boolean hasAutoReplyActive(long userId) {
		return _messagingConfigLocalService.hasAutoReplyActive(userId);
	}

	/**
	 * Updates the messaging config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessagingConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messagingConfig the messaging config
	 * @return the messaging config that was updated
	 */
	@Override
	public com.weprode.nero.messaging.model.MessagingConfig
		updateMessagingConfig(
			com.weprode.nero.messaging.model.MessagingConfig messagingConfig) {

		return _messagingConfigLocalService.updateMessagingConfig(
			messagingConfig);
	}

	@Override
	public MessagingConfigLocalService getWrappedService() {
		return _messagingConfigLocalService;
	}

	@Override
	public void setWrappedService(
		MessagingConfigLocalService messagingConfigLocalService) {

		_messagingConfigLocalService = messagingConfigLocalService;
	}

	private MessagingConfigLocalService _messagingConfigLocalService;

}