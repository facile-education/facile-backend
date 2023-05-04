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

package com.weprode.nero.preference.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.preference.exception.NoSuchNotifyConfigException;
import com.weprode.nero.preference.model.NotifyConfig;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the notify config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotifyConfigUtil
 * @generated
 */
@ProviderType
public interface NotifyConfigPersistence extends BasePersistence<NotifyConfig> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotifyConfigUtil} to access the notify config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the notify config where userId = &#63; or throws a <code>NoSuchNotifyConfigException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public NotifyConfig findByuserId(long userId)
		throws NoSuchNotifyConfigException;

	/**
	 * Returns the notify config where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public NotifyConfig fetchByuserId(long userId);

	/**
	 * Returns the notify config where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public NotifyConfig fetchByuserId(long userId, boolean useFinderCache);

	/**
	 * Removes the notify config where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the notify config that was removed
	 */
	public NotifyConfig removeByuserId(long userId)
		throws NoSuchNotifyConfigException;

	/**
	 * Returns the number of notify configs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching notify configs
	 */
	public int countByuserId(long userId);

	/**
	 * Returns all the notify configs where activate = &#63;.
	 *
	 * @param activate the activate
	 * @return the matching notify configs
	 */
	public java.util.List<NotifyConfig> findByactivate(boolean activate);

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
	public java.util.List<NotifyConfig> findByactivate(
		boolean activate, int start, int end);

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
	public java.util.List<NotifyConfig> findByactivate(
		boolean activate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator);

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
	public java.util.List<NotifyConfig> findByactivate(
		boolean activate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public NotifyConfig findByactivate_First(
			boolean activate,
			com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
				orderByComparator)
		throws NoSuchNotifyConfigException;

	/**
	 * Returns the first notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public NotifyConfig fetchByactivate_First(
		boolean activate,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator);

	/**
	 * Returns the last notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public NotifyConfig findByactivate_Last(
			boolean activate,
			com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
				orderByComparator)
		throws NoSuchNotifyConfigException;

	/**
	 * Returns the last notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public NotifyConfig fetchByactivate_Last(
		boolean activate,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator);

	/**
	 * Returns the notify configs before and after the current notify config in the ordered set where activate = &#63;.
	 *
	 * @param notifyConfigId the primary key of the current notify config
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notify config
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	public NotifyConfig[] findByactivate_PrevAndNext(
			long notifyConfigId, boolean activate,
			com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
				orderByComparator)
		throws NoSuchNotifyConfigException;

	/**
	 * Removes all the notify configs where activate = &#63; from the database.
	 *
	 * @param activate the activate
	 */
	public void removeByactivate(boolean activate);

	/**
	 * Returns the number of notify configs where activate = &#63;.
	 *
	 * @param activate the activate
	 * @return the number of matching notify configs
	 */
	public int countByactivate(boolean activate);

	/**
	 * Returns all the notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @return the matching notify configs
	 */
	public java.util.List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod);

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
	public java.util.List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod, int start, int end);

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
	public java.util.List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator);

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
	public java.util.List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public NotifyConfig findByactivate_digestPeriod_First(
			boolean activate, int digestPeriod,
			com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
				orderByComparator)
		throws NoSuchNotifyConfigException;

	/**
	 * Returns the first notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public NotifyConfig fetchByactivate_digestPeriod_First(
		boolean activate, int digestPeriod,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator);

	/**
	 * Returns the last notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	public NotifyConfig findByactivate_digestPeriod_Last(
			boolean activate, int digestPeriod,
			com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
				orderByComparator)
		throws NoSuchNotifyConfigException;

	/**
	 * Returns the last notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	public NotifyConfig fetchByactivate_digestPeriod_Last(
		boolean activate, int digestPeriod,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator);

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
	public NotifyConfig[] findByactivate_digestPeriod_PrevAndNext(
			long notifyConfigId, boolean activate, int digestPeriod,
			com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
				orderByComparator)
		throws NoSuchNotifyConfigException;

	/**
	 * Removes all the notify configs where activate = &#63; and digestPeriod = &#63; from the database.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 */
	public void removeByactivate_digestPeriod(
		boolean activate, int digestPeriod);

	/**
	 * Returns the number of notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @return the number of matching notify configs
	 */
	public int countByactivate_digestPeriod(boolean activate, int digestPeriod);

	/**
	 * Caches the notify config in the entity cache if it is enabled.
	 *
	 * @param notifyConfig the notify config
	 */
	public void cacheResult(NotifyConfig notifyConfig);

	/**
	 * Caches the notify configs in the entity cache if it is enabled.
	 *
	 * @param notifyConfigs the notify configs
	 */
	public void cacheResult(java.util.List<NotifyConfig> notifyConfigs);

	/**
	 * Creates a new notify config with the primary key. Does not add the notify config to the database.
	 *
	 * @param notifyConfigId the primary key for the new notify config
	 * @return the new notify config
	 */
	public NotifyConfig create(long notifyConfigId);

	/**
	 * Removes the notify config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config that was removed
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	public NotifyConfig remove(long notifyConfigId)
		throws NoSuchNotifyConfigException;

	public NotifyConfig updateImpl(NotifyConfig notifyConfig);

	/**
	 * Returns the notify config with the primary key or throws a <code>NoSuchNotifyConfigException</code> if it could not be found.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	public NotifyConfig findByPrimaryKey(long notifyConfigId)
		throws NoSuchNotifyConfigException;

	/**
	 * Returns the notify config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config, or <code>null</code> if a notify config with the primary key could not be found
	 */
	public NotifyConfig fetchByPrimaryKey(long notifyConfigId);

	/**
	 * Returns all the notify configs.
	 *
	 * @return the notify configs
	 */
	public java.util.List<NotifyConfig> findAll();

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
	public java.util.List<NotifyConfig> findAll(int start, int end);

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
	public java.util.List<NotifyConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator);

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
	public java.util.List<NotifyConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotifyConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the notify configs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of notify configs.
	 *
	 * @return the number of notify configs
	 */
	public int countAll();

}