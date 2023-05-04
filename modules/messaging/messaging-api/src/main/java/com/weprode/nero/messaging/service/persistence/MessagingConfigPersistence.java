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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.messaging.exception.NoSuchConfigException;
import com.weprode.nero.messaging.model.MessagingConfig;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the messaging config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingConfigUtil
 * @generated
 */
@ProviderType
public interface MessagingConfigPersistence
	extends BasePersistence<MessagingConfig> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MessagingConfigUtil} to access the messaging config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the messaging config in the entity cache if it is enabled.
	 *
	 * @param messagingConfig the messaging config
	 */
	public void cacheResult(MessagingConfig messagingConfig);

	/**
	 * Caches the messaging configs in the entity cache if it is enabled.
	 *
	 * @param messagingConfigs the messaging configs
	 */
	public void cacheResult(java.util.List<MessagingConfig> messagingConfigs);

	/**
	 * Creates a new messaging config with the primary key. Does not add the messaging config to the database.
	 *
	 * @param userId the primary key for the new messaging config
	 * @return the new messaging config
	 */
	public MessagingConfig create(long userId);

	/**
	 * Removes the messaging config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config that was removed
	 * @throws NoSuchConfigException if a messaging config with the primary key could not be found
	 */
	public MessagingConfig remove(long userId) throws NoSuchConfigException;

	public MessagingConfig updateImpl(MessagingConfig messagingConfig);

	/**
	 * Returns the messaging config with the primary key or throws a <code>NoSuchConfigException</code> if it could not be found.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config
	 * @throws NoSuchConfigException if a messaging config with the primary key could not be found
	 */
	public MessagingConfig findByPrimaryKey(long userId)
		throws NoSuchConfigException;

	/**
	 * Returns the messaging config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config, or <code>null</code> if a messaging config with the primary key could not be found
	 */
	public MessagingConfig fetchByPrimaryKey(long userId);

	/**
	 * Returns all the messaging configs.
	 *
	 * @return the messaging configs
	 */
	public java.util.List<MessagingConfig> findAll();

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
	public java.util.List<MessagingConfig> findAll(int start, int end);

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
	public java.util.List<MessagingConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessagingConfig>
			orderByComparator);

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
	public java.util.List<MessagingConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MessagingConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the messaging configs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of messaging configs.
	 *
	 * @return the number of messaging configs
	 */
	public int countAll();

}