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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.group.model.GroupMembership;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the group membership service. This utility wraps <code>com.weprode.facile.group.service.persistence.impl.GroupMembershipPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GroupMembershipPersistence
 * @generated
 */
public class GroupMembershipUtil {

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
	public static void clearCache(GroupMembership groupMembership) {
		getPersistence().clearCache(groupMembership);
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
	public static Map<Serializable, GroupMembership> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<GroupMembership> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GroupMembership> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GroupMembership> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GroupMembership update(GroupMembership groupMembership) {
		return getPersistence().update(groupMembership);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GroupMembership update(
		GroupMembership groupMembership, ServiceContext serviceContext) {

		return getPersistence().update(groupMembership, serviceContext);
	}

	/**
	 * Returns all the group memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching group memberships
	 */
	public static List<GroupMembership> findBygroupId(long groupId) {
		return getPersistence().findBygroupId(groupId);
	}

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
	public static List<GroupMembership> findBygroupId(
		long groupId, int start, int end) {

		return getPersistence().findBygroupId(groupId, start, end);
	}

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
	public static List<GroupMembership> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator);
	}

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
	public static List<GroupMembership> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public static GroupMembership findBygroupId_First(
			long groupId, OrderByComparator<GroupMembership> orderByComparator)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findBygroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public static GroupMembership fetchBygroupId_First(
		long groupId, OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().fetchBygroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public static GroupMembership findBygroupId_Last(
			long groupId, OrderByComparator<GroupMembership> orderByComparator)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public static GroupMembership fetchBygroupId_Last(
		long groupId, OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().fetchBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the group memberships before and after the current group membership in the ordered set where groupId = &#63;.
	 *
	 * @param membershipId the primary key of the current group membership
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	public static GroupMembership[] findBygroupId_PrevAndNext(
			long membershipId, long groupId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findBygroupId_PrevAndNext(
			membershipId, groupId, orderByComparator);
	}

	/**
	 * Removes all the group memberships where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeBygroupId(long groupId) {
		getPersistence().removeBygroupId(groupId);
	}

	/**
	 * Returns the number of group memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching group memberships
	 */
	public static int countBygroupId(long groupId) {
		return getPersistence().countBygroupId(groupId);
	}

	/**
	 * Returns all the group memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching group memberships
	 */
	public static List<GroupMembership> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

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
	public static List<GroupMembership> findByuserId(
		long userId, int start, int end) {

		return getPersistence().findByuserId(userId, start, end);
	}

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
	public static List<GroupMembership> findByuserId(
		long userId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

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
	public static List<GroupMembership> findByuserId(
		long userId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public static GroupMembership findByuserId_First(
			long userId, OrderByComparator<GroupMembership> orderByComparator)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public static GroupMembership fetchByuserId_First(
		long userId, OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public static GroupMembership findByuserId_Last(
			long userId, OrderByComparator<GroupMembership> orderByComparator)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public static GroupMembership fetchByuserId_Last(
		long userId, OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the group memberships before and after the current group membership in the ordered set where userId = &#63;.
	 *
	 * @param membershipId the primary key of the current group membership
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	public static GroupMembership[] findByuserId_PrevAndNext(
			long membershipId, long userId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findByuserId_PrevAndNext(
			membershipId, userId, orderByComparator);
	}

	/**
	 * Removes all the group memberships where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of group memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching group memberships
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns all the group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching group memberships
	 */
	public static List<GroupMembership> findByuserId_groupId(
		long userId, long groupId) {

		return getPersistence().findByuserId_groupId(userId, groupId);
	}

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
	public static List<GroupMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end) {

		return getPersistence().findByuserId_groupId(
			userId, groupId, start, end);
	}

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
	public static List<GroupMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().findByuserId_groupId(
			userId, groupId, start, end, orderByComparator);
	}

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
	public static List<GroupMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId_groupId(
			userId, groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public static GroupMembership findByuserId_groupId_First(
			long userId, long groupId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findByuserId_groupId_First(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the first group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public static GroupMembership fetchByuserId_groupId_First(
		long userId, long groupId,
		OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().fetchByuserId_groupId_First(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the last group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	public static GroupMembership findByuserId_groupId_Last(
			long userId, long groupId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findByuserId_groupId_Last(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the last group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	public static GroupMembership fetchByuserId_groupId_Last(
		long userId, long groupId,
		OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().fetchByuserId_groupId_Last(
			userId, groupId, orderByComparator);
	}

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
	public static GroupMembership[] findByuserId_groupId_PrevAndNext(
			long membershipId, long userId, long groupId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findByuserId_groupId_PrevAndNext(
			membershipId, userId, groupId, orderByComparator);
	}

	/**
	 * Removes all the group memberships where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 */
	public static void removeByuserId_groupId(long userId, long groupId) {
		getPersistence().removeByuserId_groupId(userId, groupId);
	}

	/**
	 * Returns the number of group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching group memberships
	 */
	public static int countByuserId_groupId(long userId, long groupId) {
		return getPersistence().countByuserId_groupId(userId, groupId);
	}

	/**
	 * Caches the group membership in the entity cache if it is enabled.
	 *
	 * @param groupMembership the group membership
	 */
	public static void cacheResult(GroupMembership groupMembership) {
		getPersistence().cacheResult(groupMembership);
	}

	/**
	 * Caches the group memberships in the entity cache if it is enabled.
	 *
	 * @param groupMemberships the group memberships
	 */
	public static void cacheResult(List<GroupMembership> groupMemberships) {
		getPersistence().cacheResult(groupMemberships);
	}

	/**
	 * Creates a new group membership with the primary key. Does not add the group membership to the database.
	 *
	 * @param membershipId the primary key for the new group membership
	 * @return the new group membership
	 */
	public static GroupMembership create(long membershipId) {
		return getPersistence().create(membershipId);
	}

	/**
	 * Removes the group membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership that was removed
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	public static GroupMembership remove(long membershipId)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().remove(membershipId);
	}

	public static GroupMembership updateImpl(GroupMembership groupMembership) {
		return getPersistence().updateImpl(groupMembership);
	}

	/**
	 * Returns the group membership with the primary key or throws a <code>NoSuchMembershipException</code> if it could not be found.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	public static GroupMembership findByPrimaryKey(long membershipId)
		throws com.weprode.facile.group.exception.NoSuchMembershipException {

		return getPersistence().findByPrimaryKey(membershipId);
	}

	/**
	 * Returns the group membership with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership, or <code>null</code> if a group membership with the primary key could not be found
	 */
	public static GroupMembership fetchByPrimaryKey(long membershipId) {
		return getPersistence().fetchByPrimaryKey(membershipId);
	}

	/**
	 * Returns all the group memberships.
	 *
	 * @return the group memberships
	 */
	public static List<GroupMembership> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<GroupMembership> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<GroupMembership> findAll(
		int start, int end,
		OrderByComparator<GroupMembership> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<GroupMembership> findAll(
		int start, int end,
		OrderByComparator<GroupMembership> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the group memberships from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of group memberships.
	 *
	 * @return the number of group memberships
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static GroupMembershipPersistence getPersistence() {
		return _persistence;
	}

	private static volatile GroupMembershipPersistence _persistence;

}