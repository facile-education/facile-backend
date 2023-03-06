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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.organization.model.OrgMembership;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the org membership service. This utility wraps <code>com.weprode.nero.organization.service.persistence.impl.OrgMembershipPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see OrgMembershipPersistence
 * @generated
 */
public class OrgMembershipUtil {

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
	public static void clearCache(OrgMembership orgMembership) {
		getPersistence().clearCache(orgMembership);
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
	public static Map<Serializable, OrgMembership> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OrgMembership> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OrgMembership> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OrgMembership> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OrgMembership update(OrgMembership orgMembership) {
		return getPersistence().update(orgMembership);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OrgMembership update(
		OrgMembership orgMembership, ServiceContext serviceContext) {

		return getPersistence().update(orgMembership, serviceContext);
	}

	/**
	 * Returns all the org memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching org memberships
	 */
	public static List<OrgMembership> findBygroupId(long groupId) {
		return getPersistence().findBygroupId(groupId);
	}

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
	public static List<OrgMembership> findBygroupId(
		long groupId, int start, int end) {

		return getPersistence().findBygroupId(groupId, start, end);
	}

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
	public static List<OrgMembership> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator);
	}

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
	public static List<OrgMembership> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public static OrgMembership findBygroupId_First(
			long groupId, OrderByComparator<OrgMembership> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findBygroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public static OrgMembership fetchBygroupId_First(
		long groupId, OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().fetchBygroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public static OrgMembership findBygroupId_Last(
			long groupId, OrderByComparator<OrgMembership> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public static OrgMembership fetchBygroupId_Last(
		long groupId, OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().fetchBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the org memberships before and after the current org membership in the ordered set where groupId = &#63;.
	 *
	 * @param orgMemberId the primary key of the current org membership
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	public static OrgMembership[] findBygroupId_PrevAndNext(
			long orgMemberId, long groupId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findBygroupId_PrevAndNext(
			orgMemberId, groupId, orderByComparator);
	}

	/**
	 * Removes all the org memberships where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeBygroupId(long groupId) {
		getPersistence().removeBygroupId(groupId);
	}

	/**
	 * Returns the number of org memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching org memberships
	 */
	public static int countBygroupId(long groupId) {
		return getPersistence().countBygroupId(groupId);
	}

	/**
	 * Returns all the org memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching org memberships
	 */
	public static List<OrgMembership> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

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
	public static List<OrgMembership> findByuserId(
		long userId, int start, int end) {

		return getPersistence().findByuserId(userId, start, end);
	}

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
	public static List<OrgMembership> findByuserId(
		long userId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

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
	public static List<OrgMembership> findByuserId(
		long userId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public static OrgMembership findByuserId_First(
			long userId, OrderByComparator<OrgMembership> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public static OrgMembership fetchByuserId_First(
		long userId, OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public static OrgMembership findByuserId_Last(
			long userId, OrderByComparator<OrgMembership> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public static OrgMembership fetchByuserId_Last(
		long userId, OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the org memberships before and after the current org membership in the ordered set where userId = &#63;.
	 *
	 * @param orgMemberId the primary key of the current org membership
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	public static OrgMembership[] findByuserId_PrevAndNext(
			long orgMemberId, long userId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findByuserId_PrevAndNext(
			orgMemberId, userId, orderByComparator);
	}

	/**
	 * Removes all the org memberships where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of org memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching org memberships
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns all the org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching org memberships
	 */
	public static List<OrgMembership> findByuserId_groupId(
		long userId, long groupId) {

		return getPersistence().findByuserId_groupId(userId, groupId);
	}

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
	public static List<OrgMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end) {

		return getPersistence().findByuserId_groupId(
			userId, groupId, start, end);
	}

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
	public static List<OrgMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().findByuserId_groupId(
			userId, groupId, start, end, orderByComparator);
	}

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
	public static List<OrgMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId_groupId(
			userId, groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public static OrgMembership findByuserId_groupId_First(
			long userId, long groupId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findByuserId_groupId_First(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the first org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public static OrgMembership fetchByuserId_groupId_First(
		long userId, long groupId,
		OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().fetchByuserId_groupId_First(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the last org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	public static OrgMembership findByuserId_groupId_Last(
			long userId, long groupId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findByuserId_groupId_Last(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the last org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	public static OrgMembership fetchByuserId_groupId_Last(
		long userId, long groupId,
		OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().fetchByuserId_groupId_Last(
			userId, groupId, orderByComparator);
	}

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
	public static OrgMembership[] findByuserId_groupId_PrevAndNext(
			long orgMemberId, long userId, long groupId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findByuserId_groupId_PrevAndNext(
			orgMemberId, userId, groupId, orderByComparator);
	}

	/**
	 * Removes all the org memberships where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 */
	public static void removeByuserId_groupId(long userId, long groupId) {
		getPersistence().removeByuserId_groupId(userId, groupId);
	}

	/**
	 * Returns the number of org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching org memberships
	 */
	public static int countByuserId_groupId(long userId, long groupId) {
		return getPersistence().countByuserId_groupId(userId, groupId);
	}

	/**
	 * Caches the org membership in the entity cache if it is enabled.
	 *
	 * @param orgMembership the org membership
	 */
	public static void cacheResult(OrgMembership orgMembership) {
		getPersistence().cacheResult(orgMembership);
	}

	/**
	 * Caches the org memberships in the entity cache if it is enabled.
	 *
	 * @param orgMemberships the org memberships
	 */
	public static void cacheResult(List<OrgMembership> orgMemberships) {
		getPersistence().cacheResult(orgMemberships);
	}

	/**
	 * Creates a new org membership with the primary key. Does not add the org membership to the database.
	 *
	 * @param orgMemberId the primary key for the new org membership
	 * @return the new org membership
	 */
	public static OrgMembership create(long orgMemberId) {
		return getPersistence().create(orgMemberId);
	}

	/**
	 * Removes the org membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership that was removed
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	public static OrgMembership remove(long orgMemberId)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().remove(orgMemberId);
	}

	public static OrgMembership updateImpl(OrgMembership orgMembership) {
		return getPersistence().updateImpl(orgMembership);
	}

	/**
	 * Returns the org membership with the primary key or throws a <code>NoSuchOrgMembershipException</code> if it could not be found.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	public static OrgMembership findByPrimaryKey(long orgMemberId)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMembershipException {

		return getPersistence().findByPrimaryKey(orgMemberId);
	}

	/**
	 * Returns the org membership with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership, or <code>null</code> if a org membership with the primary key could not be found
	 */
	public static OrgMembership fetchByPrimaryKey(long orgMemberId) {
		return getPersistence().fetchByPrimaryKey(orgMemberId);
	}

	/**
	 * Returns all the org memberships.
	 *
	 * @return the org memberships
	 */
	public static List<OrgMembership> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<OrgMembership> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<OrgMembership> findAll(
		int start, int end,
		OrderByComparator<OrgMembership> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<OrgMembership> findAll(
		int start, int end, OrderByComparator<OrgMembership> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the org memberships from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of org memberships.
	 *
	 * @return the number of org memberships
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static OrgMembershipPersistence getPersistence() {
		return _persistence;
	}

	private static volatile OrgMembershipPersistence _persistence;

}