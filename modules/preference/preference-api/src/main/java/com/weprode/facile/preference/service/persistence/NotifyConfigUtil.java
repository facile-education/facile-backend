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

package com.weprode.facile.preference.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.preference.model.NotifyConfig;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the notify config service. This utility wraps <code>com.weprode.facile.preference.service.persistence.impl.NotifyConfigPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotifyConfigPersistence
 * @generated
 */
public class NotifyConfigUtil {

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
	public static void clearCache(NotifyConfig notifyConfig) {
		getPersistence().clearCache(notifyConfig);
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
	public static Map<Serializable, NotifyConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<NotifyConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NotifyConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NotifyConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NotifyConfig> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NotifyConfig update(NotifyConfig notifyConfig) {
		return getPersistence().update(notifyConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NotifyConfig update(
		NotifyConfig notifyConfig, ServiceContext serviceContext) {

		return getPersistence().update(notifyConfig, serviceContext);
	}

	/**
	 * Returns the notify config where userId = &#63; or throws a <code>NoSuchNotifyConfigException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public static NotifyConfig findByuserId(long userId)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns the notify config where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public static NotifyConfig fetchByuserId(long userId) {
		return getPersistence().fetchByuserId(userId);
	}

	/**
	 * Returns the notify config where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public static NotifyConfig fetchByuserId(
		long userId, boolean useFinderCache) {

		return getPersistence().fetchByuserId(userId, useFinderCache);
	}

	/**
	 * Removes the notify config where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the notify config that was removed
	 */
	public static NotifyConfig removeByuserId(long userId)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of notify configs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching notify configs
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns all the notify configs where activate = &#63;.
	 *
	 * @param activate the activate
	 * @return the matching notify configs
	 */
	public static List<NotifyConfig> findByactivate(boolean activate) {
		return getPersistence().findByactivate(activate);
	}

	/**
	 * Returns a range of all the notify configs where activate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @return the range of matching notify configs
	 */
	public static List<NotifyConfig> findByactivate(
		boolean activate, int start, int end) {

		return getPersistence().findByactivate(activate, start, end);
	}

	/**
	 * Returns an ordered range of all the notify configs where activate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notify configs
	 */
	public static List<NotifyConfig> findByactivate(
		boolean activate, int start, int end,
		OrderByComparator<NotifyConfig> orderByComparator) {

		return getPersistence().findByactivate(
			activate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the notify configs where activate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notify configs
	 */
	public static List<NotifyConfig> findByactivate(
		boolean activate, int start, int end,
		OrderByComparator<NotifyConfig> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByactivate(
			activate, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public static NotifyConfig findByactivate_First(
			boolean activate, OrderByComparator<NotifyConfig> orderByComparator)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().findByactivate_First(
			activate, orderByComparator);
	}

	/**
	 * Returns the first notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public static NotifyConfig fetchByactivate_First(
		boolean activate, OrderByComparator<NotifyConfig> orderByComparator) {

		return getPersistence().fetchByactivate_First(
			activate, orderByComparator);
	}

	/**
	 * Returns the last notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public static NotifyConfig findByactivate_Last(
			boolean activate, OrderByComparator<NotifyConfig> orderByComparator)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().findByactivate_Last(
			activate, orderByComparator);
	}

	/**
	 * Returns the last notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public static NotifyConfig fetchByactivate_Last(
		boolean activate, OrderByComparator<NotifyConfig> orderByComparator) {

		return getPersistence().fetchByactivate_Last(
			activate, orderByComparator);
	}

	/**
	 * Returns the notify configs before and after the current notify config in the ordered set where activate = &#63;.
	 *
	 * @param notifyConfigId the primary key of the current notify config
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notify config
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	public static NotifyConfig[] findByactivate_PrevAndNext(
			long notifyConfigId, boolean activate,
			OrderByComparator<NotifyConfig> orderByComparator)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().findByactivate_PrevAndNext(
			notifyConfigId, activate, orderByComparator);
	}

	/**
	 * Removes all the notify configs where activate = &#63; from the database.
	 *
	 * @param activate the activate
	 */
	public static void removeByactivate(boolean activate) {
		getPersistence().removeByactivate(activate);
	}

	/**
	 * Returns the number of notify configs where activate = &#63;.
	 *
	 * @param activate the activate
	 * @return the number of matching notify configs
	 */
	public static int countByactivate(boolean activate) {
		return getPersistence().countByactivate(activate);
	}

	/**
	 * Returns all the notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @return the matching notify configs
	 */
	public static List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod) {

		return getPersistence().findByactivate_digestPeriod(
			activate, digestPeriod);
	}

	/**
	 * Returns a range of all the notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @return the range of matching notify configs
	 */
	public static List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod, int start, int end) {

		return getPersistence().findByactivate_digestPeriod(
			activate, digestPeriod, start, end);
	}

	/**
	 * Returns an ordered range of all the notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notify configs
	 */
	public static List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod, int start, int end,
		OrderByComparator<NotifyConfig> orderByComparator) {

		return getPersistence().findByactivate_digestPeriod(
			activate, digestPeriod, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notify configs
	 */
	public static List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod, int start, int end,
		OrderByComparator<NotifyConfig> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByactivate_digestPeriod(
			activate, digestPeriod, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public static NotifyConfig findByactivate_digestPeriod_First(
			boolean activate, int digestPeriod,
			OrderByComparator<NotifyConfig> orderByComparator)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().findByactivate_digestPeriod_First(
			activate, digestPeriod, orderByComparator);
	}

	/**
	 * Returns the first notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public static NotifyConfig fetchByactivate_digestPeriod_First(
		boolean activate, int digestPeriod,
		OrderByComparator<NotifyConfig> orderByComparator) {

		return getPersistence().fetchByactivate_digestPeriod_First(
			activate, digestPeriod, orderByComparator);
	}

	/**
	 * Returns the last notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public static NotifyConfig findByactivate_digestPeriod_Last(
			boolean activate, int digestPeriod,
			OrderByComparator<NotifyConfig> orderByComparator)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().findByactivate_digestPeriod_Last(
			activate, digestPeriod, orderByComparator);
	}

	/**
	 * Returns the last notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public static NotifyConfig fetchByactivate_digestPeriod_Last(
		boolean activate, int digestPeriod,
		OrderByComparator<NotifyConfig> orderByComparator) {

		return getPersistence().fetchByactivate_digestPeriod_Last(
			activate, digestPeriod, orderByComparator);
	}

	/**
	 * Returns the notify configs before and after the current notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param notifyConfigId the primary key of the current notify config
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notify config
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	public static NotifyConfig[] findByactivate_digestPeriod_PrevAndNext(
			long notifyConfigId, boolean activate, int digestPeriod,
			OrderByComparator<NotifyConfig> orderByComparator)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().findByactivate_digestPeriod_PrevAndNext(
			notifyConfigId, activate, digestPeriod, orderByComparator);
	}

	/**
	 * Removes all the notify configs where activate = &#63; and digestPeriod = &#63; from the database.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 */
	public static void removeByactivate_digestPeriod(
		boolean activate, int digestPeriod) {

		getPersistence().removeByactivate_digestPeriod(activate, digestPeriod);
	}

	/**
	 * Returns the number of notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @return the number of matching notify configs
	 */
	public static int countByactivate_digestPeriod(
		boolean activate, int digestPeriod) {

		return getPersistence().countByactivate_digestPeriod(
			activate, digestPeriod);
	}

	/**
	 * Caches the notify config in the entity cache if it is enabled.
	 *
	 * @param notifyConfig the notify config
	 */
	public static void cacheResult(NotifyConfig notifyConfig) {
		getPersistence().cacheResult(notifyConfig);
	}

	/**
	 * Caches the notify configs in the entity cache if it is enabled.
	 *
	 * @param notifyConfigs the notify configs
	 */
	public static void cacheResult(List<NotifyConfig> notifyConfigs) {
		getPersistence().cacheResult(notifyConfigs);
	}

	/**
	 * Creates a new notify config with the primary key. Does not add the notify config to the database.
	 *
	 * @param notifyConfigId the primary key for the new notify config
	 * @return the new notify config
	 */
	public static NotifyConfig create(long notifyConfigId) {
		return getPersistence().create(notifyConfigId);
	}

	/**
	 * Removes the notify config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config that was removed
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	public static NotifyConfig remove(long notifyConfigId)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().remove(notifyConfigId);
	}

	public static NotifyConfig updateImpl(NotifyConfig notifyConfig) {
		return getPersistence().updateImpl(notifyConfig);
	}

	/**
	 * Returns the notify config with the primary key or throws a <code>NoSuchNotifyConfigException</code> if it could not be found.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	public static NotifyConfig findByPrimaryKey(long notifyConfigId)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException {

		return getPersistence().findByPrimaryKey(notifyConfigId);
	}

	/**
	 * Returns the notify config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config, or <code>null</code> if a notify config with the primary key could not be found
	 */
	public static NotifyConfig fetchByPrimaryKey(long notifyConfigId) {
		return getPersistence().fetchByPrimaryKey(notifyConfigId);
	}

	/**
	 * Returns all the notify configs.
	 *
	 * @return the notify configs
	 */
	public static List<NotifyConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the notify configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @return the range of notify configs
	 */
	public static List<NotifyConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the notify configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notify configs
	 */
	public static List<NotifyConfig> findAll(
		int start, int end, OrderByComparator<NotifyConfig> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the notify configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of notify configs
	 */
	public static List<NotifyConfig> findAll(
		int start, int end, OrderByComparator<NotifyConfig> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the notify configs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of notify configs.
	 *
	 * @return the number of notify configs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NotifyConfigPersistence getPersistence() {
		return _persistence;
	}

	private static volatile NotifyConfigPersistence _persistence;

}