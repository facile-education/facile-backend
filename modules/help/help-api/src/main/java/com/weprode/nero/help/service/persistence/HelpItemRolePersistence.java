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

package com.weprode.nero.help.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.help.exception.NoSuchItemRoleException;
import com.weprode.nero.help.model.HelpItemRole;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the help item role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpItemRoleUtil
 * @generated
 */
@ProviderType
public interface HelpItemRolePersistence extends BasePersistence<HelpItemRole> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelpItemRoleUtil} to access the help item role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the help item roles where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help item roles
	 */
	public java.util.List<HelpItemRole> findByitemId(long itemId);

	/**
	 * Returns a range of all the help item roles where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @return the range of matching help item roles
	 */
	public java.util.List<HelpItemRole> findByitemId(
		long itemId, int start, int end);

	/**
	 * Returns an ordered range of all the help item roles where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help item roles
	 */
	public java.util.List<HelpItemRole> findByitemId(
		long itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItemRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help item roles where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help item roles
	 */
	public java.util.List<HelpItemRole> findByitemId(
		long itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItemRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item role
	 * @throws NoSuchItemRoleException if a matching help item role could not be found
	 */
	public HelpItemRole findByitemId_First(
			long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpItemRole>
				orderByComparator)
		throws NoSuchItemRoleException;

	/**
	 * Returns the first help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item role, or <code>null</code> if a matching help item role could not be found
	 */
	public HelpItemRole fetchByitemId_First(
		long itemId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItemRole>
			orderByComparator);

	/**
	 * Returns the last help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item role
	 * @throws NoSuchItemRoleException if a matching help item role could not be found
	 */
	public HelpItemRole findByitemId_Last(
			long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpItemRole>
				orderByComparator)
		throws NoSuchItemRoleException;

	/**
	 * Returns the last help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item role, or <code>null</code> if a matching help item role could not be found
	 */
	public HelpItemRole fetchByitemId_Last(
		long itemId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItemRole>
			orderByComparator);

	/**
	 * Returns the help item roles before and after the current help item role in the ordered set where itemId = &#63;.
	 *
	 * @param helpItemRoleId the primary key of the current help item role
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help item role
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	public HelpItemRole[] findByitemId_PrevAndNext(
			long helpItemRoleId, long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpItemRole>
				orderByComparator)
		throws NoSuchItemRoleException;

	/**
	 * Removes all the help item roles where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	public void removeByitemId(long itemId);

	/**
	 * Returns the number of help item roles where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help item roles
	 */
	public int countByitemId(long itemId);

	/**
	 * Caches the help item role in the entity cache if it is enabled.
	 *
	 * @param helpItemRole the help item role
	 */
	public void cacheResult(HelpItemRole helpItemRole);

	/**
	 * Caches the help item roles in the entity cache if it is enabled.
	 *
	 * @param helpItemRoles the help item roles
	 */
	public void cacheResult(java.util.List<HelpItemRole> helpItemRoles);

	/**
	 * Creates a new help item role with the primary key. Does not add the help item role to the database.
	 *
	 * @param helpItemRoleId the primary key for the new help item role
	 * @return the new help item role
	 */
	public HelpItemRole create(long helpItemRoleId);

	/**
	 * Removes the help item role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role that was removed
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	public HelpItemRole remove(long helpItemRoleId)
		throws NoSuchItemRoleException;

	public HelpItemRole updateImpl(HelpItemRole helpItemRole);

	/**
	 * Returns the help item role with the primary key or throws a <code>NoSuchItemRoleException</code> if it could not be found.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	public HelpItemRole findByPrimaryKey(long helpItemRoleId)
		throws NoSuchItemRoleException;

	/**
	 * Returns the help item role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role, or <code>null</code> if a help item role with the primary key could not be found
	 */
	public HelpItemRole fetchByPrimaryKey(long helpItemRoleId);

	/**
	 * Returns all the help item roles.
	 *
	 * @return the help item roles
	 */
	public java.util.List<HelpItemRole> findAll();

	/**
	 * Returns a range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @return the range of help item roles
	 */
	public java.util.List<HelpItemRole> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help item roles
	 */
	public java.util.List<HelpItemRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItemRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help item roles
	 */
	public java.util.List<HelpItemRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItemRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the help item roles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of help item roles.
	 *
	 * @return the number of help item roles
	 */
	public int countAll();

}