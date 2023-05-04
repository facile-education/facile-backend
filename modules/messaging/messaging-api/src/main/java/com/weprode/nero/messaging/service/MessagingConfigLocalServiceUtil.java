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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.messaging.model.MessagingConfig;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for MessagingConfig. This utility wraps
 * <code>com.weprode.nero.messaging.service.impl.MessagingConfigLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingConfigLocalService
 * @generated
 */
public class MessagingConfigLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.messaging.service.impl.MessagingConfigLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static MessagingConfig addDefaultMessagingConfig(long userId) {
		return getService().addDefaultMessagingConfig(userId);
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
	public static MessagingConfig addMessagingConfig(
		MessagingConfig messagingConfig) {

		return getService().addMessagingConfig(messagingConfig);
	}

	/**
	 * Creates a new messaging config with the primary key. Does not add the messaging config to the database.
	 *
	 * @param userId the primary key for the new messaging config
	 * @return the new messaging config
	 */
	public static MessagingConfig createMessagingConfig(long userId) {
		return getService().createMessagingConfig(userId);
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
	public static MessagingConfig deleteMessagingConfig(long userId)
		throws PortalException {

		return getService().deleteMessagingConfig(userId);
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
	public static MessagingConfig deleteMessagingConfig(
		MessagingConfig messagingConfig) {

		return getService().deleteMessagingConfig(messagingConfig);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessagingConfigModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.messaging.model.impl.MessagingConfigModelImpl</code>.
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

	public static MessagingConfig fetchMessagingConfig(long userId) {
		return getService().fetchMessagingConfig(userId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns user messaging autoReply content if it is active
	 */
	public static String getAutoReply(long userId) {
		return getService().getAutoReply(userId);
	}

	public static List<String> getForwardAddresses(long userId)
		throws SystemException {

		return getService().getForwardAddresses(userId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the messaging config with the primary key.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config
	 * @throws PortalException if a messaging config with the primary key could not be found
	 */
	public static MessagingConfig getMessagingConfig(long userId)
		throws PortalException {

		return getService().getMessagingConfig(userId);
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
	public static List<MessagingConfig> getMessagingConfigs(
		int start, int end) {

		return getService().getMessagingConfigs(start, end);
	}

	/**
	 * Returns the number of messaging configs.
	 *
	 * @return the number of messaging configs
	 */
	public static int getMessagingConfigsCount() {
		return getService().getMessagingConfigsCount();
	}

	public static MessagingConfig getOrCreateMessagingConfig(long userId)
		throws SystemException {

		return getService().getOrCreateMessagingConfig(userId);
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
	 * Returns user messaging signature if it is active
	 */
	public static String getSignature(long userId) {
		return getService().getSignature(userId);
	}

	/**
	 * Returns true if user messaging autoReply is active
	 */
	public static boolean hasAutoReplyActive(long userId) {
		return getService().hasAutoReplyActive(userId);
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
	public static MessagingConfig updateMessagingConfig(
		MessagingConfig messagingConfig) {

		return getService().updateMessagingConfig(messagingConfig);
	}

	public static MessagingConfigLocalService getService() {
		return _service;
	}

	private static volatile MessagingConfigLocalService _service;

}