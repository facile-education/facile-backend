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

package com.weprode.nero.schedule.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.schedule.exception.NoSuchGroupColorException;
import com.weprode.nero.schedule.model.GroupColor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the group color service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GroupColorUtil
 * @generated
 */
@ProviderType
public interface GroupColorPersistence extends BasePersistence<GroupColor> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GroupColorUtil} to access the group color persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the group color in the entity cache if it is enabled.
	 *
	 * @param groupColor the group color
	 */
	public void cacheResult(GroupColor groupColor);

	/**
	 * Caches the group colors in the entity cache if it is enabled.
	 *
	 * @param groupColors the group colors
	 */
	public void cacheResult(java.util.List<GroupColor> groupColors);

	/**
	 * Creates a new group color with the primary key. Does not add the group color to the database.
	 *
	 * @param groupId the primary key for the new group color
	 * @return the new group color
	 */
	public GroupColor create(long groupId);

	/**
	 * Removes the group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color that was removed
	 * @throws NoSuchGroupColorException if a group color with the primary key could not be found
	 */
	public GroupColor remove(long groupId) throws NoSuchGroupColorException;

	public GroupColor updateImpl(GroupColor groupColor);

	/**
	 * Returns the group color with the primary key or throws a <code>NoSuchGroupColorException</code> if it could not be found.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color
	 * @throws NoSuchGroupColorException if a group color with the primary key could not be found
	 */
	public GroupColor findByPrimaryKey(long groupId)
		throws NoSuchGroupColorException;

	/**
	 * Returns the group color with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color, or <code>null</code> if a group color with the primary key could not be found
	 */
	public GroupColor fetchByPrimaryKey(long groupId);

	/**
	 * Returns all the group colors.
	 *
	 * @return the group colors
	 */
	public java.util.List<GroupColor> findAll();

	/**
	 * Returns a range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @return the range of group colors
	 */
	public java.util.List<GroupColor> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of group colors
	 */
	public java.util.List<GroupColor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupColor>
			orderByComparator);

	/**
	 * Returns an ordered range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of group colors
	 */
	public java.util.List<GroupColor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupColor>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the group colors from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of group colors.
	 *
	 * @return the number of group colors
	 */
	public int countAll();

}