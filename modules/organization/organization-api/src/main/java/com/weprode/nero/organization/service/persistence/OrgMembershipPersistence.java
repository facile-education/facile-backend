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

package com.weprode.nero.organization.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.organization.exception.NoSuchOrgMembershipException;
import com.weprode.nero.organization.model.OrgMembership;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the org membership service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see OrgMembershipUtil
 * @generated
 */
@ProviderType
public interface OrgMembershipPersistence
	extends BasePersistence<OrgMembership> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OrgMembershipUtil} to access the org membership persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the org memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching org memberships
	 */
	public java.util.List<OrgMembership> findBygroupId(long groupId);

	/**
	 * Returns a range of all the org memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @return the range of matching org memberships
	 */
	public java.util.List<OrgMembership> findBygroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the org memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org memberships
	 */
	public java.util.List<OrgMembership> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns an ordered range of all the org memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org memberships
	 */
	public java.util.List<OrgMembership> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public OrgMembership findBygroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
				orderByComparator)
		throws NoSuchOrgMembershipException;

	/**
	 * Returns the first org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public OrgMembership fetchBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns the last org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public OrgMembership findBygroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
				orderByComparator)
		throws NoSuchOrgMembershipException;

	/**
	 * Returns the last org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public OrgMembership fetchBygroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns the org memberships before and after the current org membership in the ordered set where groupId = &#63;.
	 *
	 * @param orgMemberId the primary key of the current org membership
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	public OrgMembership[] findBygroupId_PrevAndNext(
			long orgMemberId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
				orderByComparator)
		throws NoSuchOrgMembershipException;

	/**
	 * Removes all the org memberships where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeBygroupId(long groupId);

	/**
	 * Returns the number of org memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching org memberships
	 */
	public int countBygroupId(long groupId);

	/**
	 * Returns all the org memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching org memberships
	 */
	public java.util.List<OrgMembership> findByuserId(long userId);

	/**
	 * Returns a range of all the org memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @return the range of matching org memberships
	 */
	public java.util.List<OrgMembership> findByuserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the org memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org memberships
	 */
	public java.util.List<OrgMembership> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns an ordered range of all the org memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org memberships
	 */
	public java.util.List<OrgMembership> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public OrgMembership findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
				orderByComparator)
		throws NoSuchOrgMembershipException;

	/**
	 * Returns the first org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public OrgMembership fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns the last org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public OrgMembership findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
				orderByComparator)
		throws NoSuchOrgMembershipException;

	/**
	 * Returns the last org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public OrgMembership fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns the org memberships before and after the current org membership in the ordered set where userId = &#63;.
	 *
	 * @param orgMemberId the primary key of the current org membership
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	public OrgMembership[] findByuserId_PrevAndNext(
			long orgMemberId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
				orderByComparator)
		throws NoSuchOrgMembershipException;

	/**
	 * Removes all the org memberships where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of org memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching org memberships
	 */
	public int countByuserId(long userId);

	/**
	 * Returns all the org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching org memberships
	 */
	public java.util.List<OrgMembership> findByuserId_groupId(
		long userId, long groupId);

	/**
	 * Returns a range of all the org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @return the range of matching org memberships
	 */
	public java.util.List<OrgMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org memberships
	 */
	public java.util.List<OrgMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns an ordered range of all the org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org memberships
	 */
	public java.util.List<OrgMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public OrgMembership findByuserId_groupId_First(
			long userId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
				orderByComparator)
		throws NoSuchOrgMembershipException;

	/**
	 * Returns the first org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public OrgMembership fetchByuserId_groupId_First(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns the last org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public OrgMembership findByuserId_groupId_Last(
			long userId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
				orderByComparator)
		throws NoSuchOrgMembershipException;

	/**
	 * Returns the last org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public OrgMembership fetchByuserId_groupId_Last(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns the org memberships before and after the current org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param orgMemberId the primary key of the current org membership
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	public OrgMembership[] findByuserId_groupId_PrevAndNext(
			long orgMemberId, long userId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
				orderByComparator)
		throws NoSuchOrgMembershipException;

	/**
	 * Removes all the org memberships where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 */
	public void removeByuserId_groupId(long userId, long groupId);

	/**
	 * Returns the number of org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching org memberships
	 */
	public int countByuserId_groupId(long userId, long groupId);

	/**
	 * Caches the org membership in the entity cache if it is enabled.
	 *
	 * @param orgMembership the org membership
	 */
	public void cacheResult(OrgMembership orgMembership);

	/**
	 * Caches the org memberships in the entity cache if it is enabled.
	 *
	 * @param orgMemberships the org memberships
	 */
	public void cacheResult(java.util.List<OrgMembership> orgMemberships);

	/**
	 * Creates a new org membership with the primary key. Does not add the org membership to the database.
	 *
	 * @param orgMemberId the primary key for the new org membership
	 * @return the new org membership
	 */
	public OrgMembership create(long orgMemberId);

	/**
	 * Removes the org membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership that was removed
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	public OrgMembership remove(long orgMemberId)
		throws NoSuchOrgMembershipException;

	public OrgMembership updateImpl(OrgMembership orgMembership);

	/**
	 * Returns the org membership with the primary key or throws a <code>NoSuchOrgMembershipException</code> if it could not be found.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	public OrgMembership findByPrimaryKey(long orgMemberId)
		throws NoSuchOrgMembershipException;

	/**
	 * Returns the org membership with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership, or <code>null</code> if a org membership with the primary key could not be found
	 */
	public OrgMembership fetchByPrimaryKey(long orgMemberId);

	/**
	 * Returns all the org memberships.
	 *
	 * @return the org memberships
	 */
	public java.util.List<OrgMembership> findAll();

	/**
	 * Returns a range of all the org memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @return the range of org memberships
	 */
	public java.util.List<OrgMembership> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the org memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of org memberships
	 */
	public java.util.List<OrgMembership> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator);

	/**
	 * Returns an ordered range of all the org memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of org memberships
	 */
	public java.util.List<OrgMembership> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMembership>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the org memberships from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of org memberships.
	 *
	 * @return the number of org memberships
	 */
	public int countAll();

}