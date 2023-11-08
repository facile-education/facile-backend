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

import com.weprode.facile.group.exception.NoSuchCommunityInfosException;
import com.weprode.facile.group.model.CommunityInfos;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the community infos service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommunityInfosUtil
 * @generated
 */
@ProviderType
public interface CommunityInfosPersistence
	extends BasePersistence<CommunityInfos> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommunityInfosUtil} to access the community infos persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the community infos where groupId = &#63; or throws a <code>NoSuchCommunityInfosException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @return the matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public CommunityInfos findBygroupId(long groupId)
		throws NoSuchCommunityInfosException;

	/**
	 * Returns the community infos where groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @return the matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public CommunityInfos fetchBygroupId(long groupId);

	/**
	 * Returns the community infos where groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public CommunityInfos fetchBygroupId(long groupId, boolean useFinderCache);

	/**
	 * Removes the community infos where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @return the community infos that was removed
	 */
	public CommunityInfos removeBygroupId(long groupId)
		throws NoSuchCommunityInfosException;

	/**
	 * Returns the number of community infoses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching community infoses
	 */
	public int countBygroupId(long groupId);

	/**
	 * Returns all the community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @return the matching community infoses
	 */
	public java.util.List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status);

	/**
	 * Returns a range of all the community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @return the range of matching community infoses
	 */
	public java.util.List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status, int start, int end);

	/**
	 * Returns an ordered range of all the community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching community infoses
	 */
	public java.util.List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator);

	/**
	 * Returns an ordered range of all the community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching community infoses
	 */
	public java.util.List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public CommunityInfos findBycreatorId_status_First(
			long creatorId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
				orderByComparator)
		throws NoSuchCommunityInfosException;

	/**
	 * Returns the first community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public CommunityInfos fetchBycreatorId_status_First(
		long creatorId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator);

	/**
	 * Returns the last community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public CommunityInfos findBycreatorId_status_Last(
			long creatorId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
				orderByComparator)
		throws NoSuchCommunityInfosException;

	/**
	 * Returns the last community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public CommunityInfos fetchBycreatorId_status_Last(
		long creatorId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator);

	/**
	 * Returns the community infoses before and after the current community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param communityInfosId the primary key of the current community infos
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next community infos
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	public CommunityInfos[] findBycreatorId_status_PrevAndNext(
			long communityInfosId, long creatorId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
				orderByComparator)
		throws NoSuchCommunityInfosException;

	/**
	 * Removes all the community infoses where creatorId = &#63; and status = &#63; from the database.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 */
	public void removeBycreatorId_status(long creatorId, int status);

	/**
	 * Returns the number of community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @return the number of matching community infoses
	 */
	public int countBycreatorId_status(long creatorId, int status);

	/**
	 * Returns all the community infoses where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the matching community infoses
	 */
	public java.util.List<CommunityInfos> findByexpirationDate(
		Date expirationDate);

	/**
	 * Returns a range of all the community infoses where expirationDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @return the range of matching community infoses
	 */
	public java.util.List<CommunityInfos> findByexpirationDate(
		Date expirationDate, int start, int end);

	/**
	 * Returns an ordered range of all the community infoses where expirationDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching community infoses
	 */
	public java.util.List<CommunityInfos> findByexpirationDate(
		Date expirationDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator);

	/**
	 * Returns an ordered range of all the community infoses where expirationDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching community infoses
	 */
	public java.util.List<CommunityInfos> findByexpirationDate(
		Date expirationDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public CommunityInfos findByexpirationDate_First(
			Date expirationDate,
			com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
				orderByComparator)
		throws NoSuchCommunityInfosException;

	/**
	 * Returns the first community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public CommunityInfos fetchByexpirationDate_First(
		Date expirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator);

	/**
	 * Returns the last community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public CommunityInfos findByexpirationDate_Last(
			Date expirationDate,
			com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
				orderByComparator)
		throws NoSuchCommunityInfosException;

	/**
	 * Returns the last community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public CommunityInfos fetchByexpirationDate_Last(
		Date expirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator);

	/**
	 * Returns the community infoses before and after the current community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param communityInfosId the primary key of the current community infos
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next community infos
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	public CommunityInfos[] findByexpirationDate_PrevAndNext(
			long communityInfosId, Date expirationDate,
			com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
				orderByComparator)
		throws NoSuchCommunityInfosException;

	/**
	 * Removes all the community infoses where expirationDate = &#63; from the database.
	 *
	 * @param expirationDate the expiration date
	 */
	public void removeByexpirationDate(Date expirationDate);

	/**
	 * Returns the number of community infoses where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the number of matching community infoses
	 */
	public int countByexpirationDate(Date expirationDate);

	/**
	 * Caches the community infos in the entity cache if it is enabled.
	 *
	 * @param communityInfos the community infos
	 */
	public void cacheResult(CommunityInfos communityInfos);

	/**
	 * Caches the community infoses in the entity cache if it is enabled.
	 *
	 * @param communityInfoses the community infoses
	 */
	public void cacheResult(java.util.List<CommunityInfos> communityInfoses);

	/**
	 * Creates a new community infos with the primary key. Does not add the community infos to the database.
	 *
	 * @param communityInfosId the primary key for the new community infos
	 * @return the new community infos
	 */
	public CommunityInfos create(long communityInfosId);

	/**
	 * Removes the community infos with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos that was removed
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	public CommunityInfos remove(long communityInfosId)
		throws NoSuchCommunityInfosException;

	public CommunityInfos updateImpl(CommunityInfos communityInfos);

	/**
	 * Returns the community infos with the primary key or throws a <code>NoSuchCommunityInfosException</code> if it could not be found.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	public CommunityInfos findByPrimaryKey(long communityInfosId)
		throws NoSuchCommunityInfosException;

	/**
	 * Returns the community infos with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos, or <code>null</code> if a community infos with the primary key could not be found
	 */
	public CommunityInfos fetchByPrimaryKey(long communityInfosId);

	/**
	 * Returns all the community infoses.
	 *
	 * @return the community infoses
	 */
	public java.util.List<CommunityInfos> findAll();

	/**
	 * Returns a range of all the community infoses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @return the range of community infoses
	 */
	public java.util.List<CommunityInfos> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the community infoses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of community infoses
	 */
	public java.util.List<CommunityInfos> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator);

	/**
	 * Returns an ordered range of all the community infoses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of community infoses
	 */
	public java.util.List<CommunityInfos> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommunityInfos>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the community infoses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of community infoses.
	 *
	 * @return the number of community infoses
	 */
	public int countAll();

}