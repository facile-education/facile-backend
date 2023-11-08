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

package com.weprode.facile.group.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.group.exception.NoSuchMembershipException;
import com.weprode.facile.group.model.GroupMembership;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the group membership service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GroupMembershipUtil
 * @generated
 */
@ProviderType
public interface GroupMembershipPersistence
	extends BasePersistence<GroupMembership> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GroupMembershipUtil} to access the group membership persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the group memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching group memberships
	 */
	public java.util.List<GroupMembership> findBygroupId(long groupId);

	/**
	 * Returns a range of all the group memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @return the range of matching group memberships
	 */
	public java.util.List<GroupMembership> findBygroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the group memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching group memberships
	 */
	public java.util.List<GroupMembership> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns an ordered range of all the group memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching group memberships
	 */
	public java.util.List<GroupMembership> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public GroupMembership findBygroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
				orderByComparator)
		throws NoSuchMembershipException;

	/**
	 * Returns the first group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public GroupMembership fetchBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns the last group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public GroupMembership findBygroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
				orderByComparator)
		throws NoSuchMembershipException;

	/**
	 * Returns the last group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public GroupMembership fetchBygroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns the group memberships before and after the current group membership in the ordered set where groupId = &#63;.
	 *
	 * @param membershipId the primary key of the current group membership
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	public GroupMembership[] findBygroupId_PrevAndNext(
			long membershipId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
				orderByComparator)
		throws NoSuchMembershipException;

	/**
	 * Removes all the group memberships where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeBygroupId(long groupId);

	/**
	 * Returns the number of group memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching group memberships
	 */
	public int countBygroupId(long groupId);

	/**
	 * Returns all the group memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching group memberships
	 */
	public java.util.List<GroupMembership> findByuserId(long userId);

	/**
	 * Returns a range of all the group memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @return the range of matching group memberships
	 */
	public java.util.List<GroupMembership> findByuserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the group memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching group memberships
	 */
	public java.util.List<GroupMembership> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns an ordered range of all the group memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching group memberships
	 */
	public java.util.List<GroupMembership> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public GroupMembership findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
				orderByComparator)
		throws NoSuchMembershipException;

	/**
	 * Returns the first group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public GroupMembership fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns the last group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public GroupMembership findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
				orderByComparator)
		throws NoSuchMembershipException;

	/**
	 * Returns the last group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public GroupMembership fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns the group memberships before and after the current group membership in the ordered set where userId = &#63;.
	 *
	 * @param membershipId the primary key of the current group membership
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	public GroupMembership[] findByuserId_PrevAndNext(
			long membershipId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
				orderByComparator)
		throws NoSuchMembershipException;

	/**
	 * Removes all the group memberships where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of group memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching group memberships
	 */
	public int countByuserId(long userId);

	/**
	 * Returns all the group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching group memberships
	 */
	public java.util.List<GroupMembership> findByuserId_groupId(
		long userId, long groupId);

	/**
	 * Returns a range of all the group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @return the range of matching group memberships
	 */
	public java.util.List<GroupMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching group memberships
	 */
	public java.util.List<GroupMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns an ordered range of all the group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching group memberships
	 */
	public java.util.List<GroupMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public GroupMembership findByuserId_groupId_First(
			long userId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
				orderByComparator)
		throws NoSuchMembershipException;

	/**
	 * Returns the first group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public GroupMembership fetchByuserId_groupId_First(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns the last group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public GroupMembership findByuserId_groupId_Last(
			long userId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
				orderByComparator)
		throws NoSuchMembershipException;

	/**
	 * Returns the last group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public GroupMembership fetchByuserId_groupId_Last(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns the group memberships before and after the current group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param membershipId the primary key of the current group membership
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	public GroupMembership[] findByuserId_groupId_PrevAndNext(
			long membershipId, long userId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
				orderByComparator)
		throws NoSuchMembershipException;

	/**
	 * Removes all the group memberships where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 */
	public void removeByuserId_groupId(long userId, long groupId);

	/**
	 * Returns the number of group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching group memberships
	 */
	public int countByuserId_groupId(long userId, long groupId);

	/**
	 * Caches the group membership in the entity cache if it is enabled.
	 *
	 * @param groupMembership the group membership
	 */
	public void cacheResult(GroupMembership groupMembership);

	/**
	 * Caches the group memberships in the entity cache if it is enabled.
	 *
	 * @param groupMemberships the group memberships
	 */
	public void cacheResult(java.util.List<GroupMembership> groupMemberships);

	/**
	 * Creates a new group membership with the primary key. Does not add the group membership to the database.
	 *
	 * @param membershipId the primary key for the new group membership
	 * @return the new group membership
	 */
	public GroupMembership create(long membershipId);

	/**
	 * Removes the group membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership that was removed
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	public GroupMembership remove(long membershipId)
		throws NoSuchMembershipException;

	public GroupMembership updateImpl(GroupMembership groupMembership);

	/**
	 * Returns the group membership with the primary key or throws a <code>NoSuchMembershipException</code> if it could not be found.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	public GroupMembership findByPrimaryKey(long membershipId)
		throws NoSuchMembershipException;

	/**
	 * Returns the group membership with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership, or <code>null</code> if a group membership with the primary key could not be found
	 */
	public GroupMembership fetchByPrimaryKey(long membershipId);

	/**
	 * Returns all the group memberships.
	 *
	 * @return the group memberships
	 */
	public java.util.List<GroupMembership> findAll();

	/**
	 * Returns a range of all the group memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @return the range of group memberships
	 */
	public java.util.List<GroupMembership> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the group memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of group memberships
	 */
	public java.util.List<GroupMembership> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator);

	/**
	 * Returns an ordered range of all the group memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of group memberships
	 */
	public java.util.List<GroupMembership> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GroupMembership>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the group memberships from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of group memberships.
	 *
	 * @return the number of group memberships
	 */
	public int countAll();

}