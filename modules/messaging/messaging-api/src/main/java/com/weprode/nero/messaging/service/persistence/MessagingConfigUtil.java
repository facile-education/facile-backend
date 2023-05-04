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

package com.weprode.nero.messaging.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.messaging.model.MessagingConfig;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the messaging config service. This utility wraps <code>com.weprode.nero.messaging.service.persistence.impl.MessagingConfigPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingConfigPersistence
 * @generated
 */
public class MessagingConfigUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(MessagingConfig messagingConfig) {
		getPersistence().clearCache(messagingConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, MessagingConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MessagingConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MessagingConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MessagingConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MessagingConfig> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MessagingConfig update(MessagingConfig messagingConfig) {
		return getPersistence().update(messagingConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MessagingConfig update(
		MessagingConfig messagingConfig, ServiceContext serviceContext) {

		return getPersistence().update(messagingConfig, serviceContext);
	}

	/**
	 * Caches the messaging config in the entity cache if it is enabled.
	 *
	 * @param messagingConfig the messaging config
	 */
	public static void cacheResult(MessagingConfig messagingConfig) {
		getPersistence().cacheResult(messagingConfig);
	}

	/**
	 * Caches the messaging configs in the entity cache if it is enabled.
	 *
	 * @param messagingConfigs the messaging configs
	 */
	public static void cacheResult(List<MessagingConfig> messagingConfigs) {
		getPersistence().cacheResult(messagingConfigs);
	}

	/**
	 * Creates a new messaging config with the primary key. Does not add the messaging config to the database.
	 *
	 * @param userId the primary key for the new messaging config
	 * @return the new messaging config
	 */
	public static MessagingConfig create(long userId) {
		return getPersistence().create(userId);
	}

	/**
	 * Removes the messaging config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config that was removed
	 * @throws NoSuchConfigException if a messaging config with the primary key could not be found
	 */
	public static MessagingConfig remove(long userId)
		throws com.weprode.nero.messaging.exception.NoSuchConfigException {

		return getPersistence().remove(userId);
	}

	public static MessagingConfig updateImpl(MessagingConfig messagingConfig) {
		return getPersistence().updateImpl(messagingConfig);
	}

	/**
	 * Returns the messaging config with the primary key or throws a <code>NoSuchConfigException</code> if it could not be found.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config
	 * @throws NoSuchConfigException if a messaging config with the primary key could not be found
	 */
	public static MessagingConfig findByPrimaryKey(long userId)
		throws com.weprode.nero.messaging.exception.NoSuchConfigException {

		return getPersistence().findByPrimaryKey(userId);
	}

	/**
	 * Returns the messaging config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config, or <code>null</code> if a messaging config with the primary key could not be found
	 */
	public static MessagingConfig fetchByPrimaryKey(long userId) {
		return getPersistence().fetchByPrimaryKey(userId);
	}

	/**
	 * Returns all the messaging configs.
	 *
	 * @return the messaging configs
	 */
	public static List<MessagingConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the messaging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messaging configs
	 * @param end the upper bound of the range of messaging configs (not inclusive)
	 * @return the range of messaging configs
	 */
	public static List<MessagingConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the messaging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messaging configs
	 * @param end the upper bound of the range of messaging configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of messaging configs
	 */
	public static List<MessagingConfig> findAll(
		int start, int end,
		OrderByComparator<MessagingConfig> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messaging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messaging configs
	 * @param end the upper bound of the range of messaging configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of messaging configs
	 */
	public static List<MessagingConfig> findAll(
		int start, int end,
		OrderByComparator<MessagingConfig> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the messaging configs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of messaging configs.
	 *
	 * @return the number of messaging configs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MessagingConfigPersistence getPersistence() {
		return _persistence;
	}

	private static volatile MessagingConfigPersistence _persistence;

}