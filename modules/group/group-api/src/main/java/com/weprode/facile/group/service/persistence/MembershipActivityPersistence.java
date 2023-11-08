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

import com.weprode.facile.group.exception.NoSuchMembershipActivityException;
import com.weprode.facile.group.model.MembershipActivity;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the membership activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipActivityUtil
 * @generated
 */
@ProviderType
public interface MembershipActivityPersistence
	extends BasePersistence<MembershipActivity> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MembershipActivityUtil} to access the membership activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the membership activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching membership activities
	 */
	public java.util.List<MembershipActivity> findBygroupId(long groupId);

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
	public java.util.List<MembershipActivity> findBygroupId(
		long groupId, int start, int end);

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
	public java.util.List<MembershipActivity> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipActivity>
			orderByComparator);

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
	public java.util.List<MembershipActivity> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipActivity>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership activity
	 * @throws NoSuchMembershipActivityException if a matching membership activity could not be found
	 */
	public MembershipActivity findBygroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<MembershipActivity>
				orderByComparator)
		throws NoSuchMembershipActivityException;

	/**
	 * Returns the first membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership activity, or <code>null</code> if a matching membership activity could not be found
	 */
	public MembershipActivity fetchBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipActivity>
			orderByComparator);

	/**
	 * Returns the last membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership activity
	 * @throws NoSuchMembershipActivityException if a matching membership activity could not be found
	 */
	public MembershipActivity findBygroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<MembershipActivity>
				orderByComparator)
		throws NoSuchMembershipActivityException;

	/**
	 * Returns the last membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership activity, or <code>null</code> if a matching membership activity could not be found
	 */
	public MembershipActivity fetchBygroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipActivity>
			orderByComparator);

	/**
	 * Returns the membership activities before and after the current membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param membershipActivityId the primary key of the current membership activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership activity
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	public MembershipActivity[] findBygroupId_PrevAndNext(
			long membershipActivityId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<MembershipActivity>
				orderByComparator)
		throws NoSuchMembershipActivityException;

	/**
	 * Removes all the membership activities where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeBygroupId(long groupId);

	/**
	 * Returns the number of membership activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching membership activities
	 */
	public int countBygroupId(long groupId);

	/**
	 * Caches the membership activity in the entity cache if it is enabled.
	 *
	 * @param membershipActivity the membership activity
	 */
	public void cacheResult(MembershipActivity membershipActivity);

	/**
	 * Caches the membership activities in the entity cache if it is enabled.
	 *
	 * @param membershipActivities the membership activities
	 */
	public void cacheResult(
		java.util.List<MembershipActivity> membershipActivities);

	/**
	 * Creates a new membership activity with the primary key. Does not add the membership activity to the database.
	 *
	 * @param membershipActivityId the primary key for the new membership activity
	 * @return the new membership activity
	 */
	public MembershipActivity create(long membershipActivityId);

	/**
	 * Removes the membership activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity that was removed
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	public MembershipActivity remove(long membershipActivityId)
		throws NoSuchMembershipActivityException;

	public MembershipActivity updateImpl(MembershipActivity membershipActivity);

	/**
	 * Returns the membership activity with the primary key or throws a <code>NoSuchMembershipActivityException</code> if it could not be found.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	public MembershipActivity findByPrimaryKey(long membershipActivityId)
		throws NoSuchMembershipActivityException;

	/**
	 * Returns the membership activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity, or <code>null</code> if a membership activity with the primary key could not be found
	 */
	public MembershipActivity fetchByPrimaryKey(long membershipActivityId);

	/**
	 * Returns all the membership activities.
	 *
	 * @return the membership activities
	 */
	public java.util.List<MembershipActivity> findAll();

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
	public java.util.List<MembershipActivity> findAll(int start, int end);

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
	public java.util.List<MembershipActivity> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipActivity>
			orderByComparator);

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
	public java.util.List<MembershipActivity> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipActivity>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the membership activities from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of membership activities.
	 *
	 * @return the number of membership activities
	 */
	public int countAll();

}