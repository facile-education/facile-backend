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

package com.weprode.nero.group.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.group.model.MembershipActivity;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the membership activity service. This utility wraps <code>com.weprode.nero.group.service.persistence.impl.MembershipActivityPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipActivityPersistence
 * @generated
 */
public class MembershipActivityUtil {

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
	public static void clearCache(MembershipActivity membershipActivity) {
		getPersistence().clearCache(membershipActivity);
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
	public static Map<Serializable, MembershipActivity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MembershipActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MembershipActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MembershipActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MembershipActivity> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MembershipActivity update(
		MembershipActivity membershipActivity) {

		return getPersistence().update(membershipActivity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MembershipActivity update(
		MembershipActivity membershipActivity, ServiceContext serviceContext) {

		return getPersistence().update(membershipActivity, serviceContext);
	}

	/**
	 * Returns all the membership activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching membership activities
	 */
	public static List<MembershipActivity> findBygroupId(long groupId) {
		return getPersistence().findBygroupId(groupId);
	}

	/**
	 * Returns a range of all the membership activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @return the range of matching membership activities
	 */
	public static List<MembershipActivity> findBygroupId(
		long groupId, int start, int end) {

		return getPersistence().findBygroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the membership activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership activities
	 */
	public static List<MembershipActivity> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<MembershipActivity> orderByComparator) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the membership activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching membership activities
	 */
	public static List<MembershipActivity> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<MembershipActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership activity
	 * @throws NoSuchMembershipActivityException if a matching membership activity could not be found
	 */
	public static MembershipActivity findBygroupId_First(
			long groupId,
			OrderByComparator<MembershipActivity> orderByComparator)
		throws com.weprode.nero.group.exception.
			NoSuchMembershipActivityException {

		return getPersistence().findBygroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership activity, or <code>null</code> if a matching membership activity could not be found
	 */
	public static MembershipActivity fetchBygroupId_First(
		long groupId, OrderByComparator<MembershipActivity> orderByComparator) {

		return getPersistence().fetchBygroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership activity
	 * @throws NoSuchMembershipActivityException if a matching membership activity could not be found
	 */
	public static MembershipActivity findBygroupId_Last(
			long groupId,
			OrderByComparator<MembershipActivity> orderByComparator)
		throws com.weprode.nero.group.exception.
			NoSuchMembershipActivityException {

		return getPersistence().findBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership activity, or <code>null</code> if a matching membership activity could not be found
	 */
	public static MembershipActivity fetchBygroupId_Last(
		long groupId, OrderByComparator<MembershipActivity> orderByComparator) {

		return getPersistence().fetchBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the membership activities before and after the current membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param membershipActivityId the primary key of the current membership activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership activity
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	public static MembershipActivity[] findBygroupId_PrevAndNext(
			long membershipActivityId, long groupId,
			OrderByComparator<MembershipActivity> orderByComparator)
		throws com.weprode.nero.group.exception.
			NoSuchMembershipActivityException {

		return getPersistence().findBygroupId_PrevAndNext(
			membershipActivityId, groupId, orderByComparator);
	}

	/**
	 * Removes all the membership activities where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeBygroupId(long groupId) {
		getPersistence().removeBygroupId(groupId);
	}

	/**
	 * Returns the number of membership activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching membership activities
	 */
	public static int countBygroupId(long groupId) {
		return getPersistence().countBygroupId(groupId);
	}

	/**
	 * Caches the membership activity in the entity cache if it is enabled.
	 *
	 * @param membershipActivity the membership activity
	 */
	public static void cacheResult(MembershipActivity membershipActivity) {
		getPersistence().cacheResult(membershipActivity);
	}

	/**
	 * Caches the membership activities in the entity cache if it is enabled.
	 *
	 * @param membershipActivities the membership activities
	 */
	public static void cacheResult(
		List<MembershipActivity> membershipActivities) {

		getPersistence().cacheResult(membershipActivities);
	}

	/**
	 * Creates a new membership activity with the primary key. Does not add the membership activity to the database.
	 *
	 * @param membershipActivityId the primary key for the new membership activity
	 * @return the new membership activity
	 */
	public static MembershipActivity create(long membershipActivityId) {
		return getPersistence().create(membershipActivityId);
	}

	/**
	 * Removes the membership activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity that was removed
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	public static MembershipActivity remove(long membershipActivityId)
		throws com.weprode.nero.group.exception.
			NoSuchMembershipActivityException {

		return getPersistence().remove(membershipActivityId);
	}

	public static MembershipActivity updateImpl(
		MembershipActivity membershipActivity) {

		return getPersistence().updateImpl(membershipActivity);
	}

	/**
	 * Returns the membership activity with the primary key or throws a <code>NoSuchMembershipActivityException</code> if it could not be found.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	public static MembershipActivity findByPrimaryKey(long membershipActivityId)
		throws com.weprode.nero.group.exception.
			NoSuchMembershipActivityException {

		return getPersistence().findByPrimaryKey(membershipActivityId);
	}

	/**
	 * Returns the membership activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity, or <code>null</code> if a membership activity with the primary key could not be found
	 */
	public static MembershipActivity fetchByPrimaryKey(
		long membershipActivityId) {

		return getPersistence().fetchByPrimaryKey(membershipActivityId);
	}

	/**
	 * Returns all the membership activities.
	 *
	 * @return the membership activities
	 */
	public static List<MembershipActivity> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the membership activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @return the range of membership activities
	 */
	public static List<MembershipActivity> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the membership activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of membership activities
	 */
	public static List<MembershipActivity> findAll(
		int start, int end,
		OrderByComparator<MembershipActivity> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the membership activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of membership activities
	 */
	public static List<MembershipActivity> findAll(
		int start, int end,
		OrderByComparator<MembershipActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the membership activities from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of membership activities.
	 *
	 * @return the number of membership activities
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MembershipActivityPersistence getPersistence() {
		return _persistence;
	}

	private static volatile MembershipActivityPersistence _persistence;

}