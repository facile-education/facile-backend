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

import com.weprode.facile.group.model.CommunityInfos;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the community infos service. This utility wraps <code>com.weprode.facile.group.service.persistence.impl.CommunityInfosPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommunityInfosPersistence
 * @generated
 */
public class CommunityInfosUtil {

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
	public static void clearCache(CommunityInfos communityInfos) {
		getPersistence().clearCache(communityInfos);
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
	public static Map<Serializable, CommunityInfos> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommunityInfos> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommunityInfos> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommunityInfos> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommunityInfos update(CommunityInfos communityInfos) {
		return getPersistence().update(communityInfos);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommunityInfos update(
		CommunityInfos communityInfos, ServiceContext serviceContext) {

		return getPersistence().update(communityInfos, serviceContext);
	}

	/**
	 * Returns the community infos where groupId = &#63; or throws a <code>NoSuchCommunityInfosException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @return the matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public static CommunityInfos findBygroupId(long groupId)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().findBygroupId(groupId);
	}

	/**
	 * Returns the community infos where groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @return the matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public static CommunityInfos fetchBygroupId(long groupId) {
		return getPersistence().fetchBygroupId(groupId);
	}

	/**
	 * Returns the community infos where groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public static CommunityInfos fetchBygroupId(
		long groupId, boolean useFinderCache) {

		return getPersistence().fetchBygroupId(groupId, useFinderCache);
	}

	/**
	 * Removes the community infos where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @return the community infos that was removed
	 */
	public static CommunityInfos removeBygroupId(long groupId)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().removeBygroupId(groupId);
	}

	/**
	 * Returns the number of community infoses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching community infoses
	 */
	public static int countBygroupId(long groupId) {
		return getPersistence().countBygroupId(groupId);
	}

	/**
	 * Returns all the community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @return the matching community infoses
	 */
	public static List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status) {

		return getPersistence().findBycreatorId_status(creatorId, status);
	}

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
	public static List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status, int start, int end) {

		return getPersistence().findBycreatorId_status(
			creatorId, status, start, end);
	}

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
	public static List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status, int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return getPersistence().findBycreatorId_status(
			creatorId, status, start, end, orderByComparator);
	}

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
	public static List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status, int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBycreatorId_status(
			creatorId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public static CommunityInfos findBycreatorId_status_First(
			long creatorId, int status,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().findBycreatorId_status_First(
			creatorId, status, orderByComparator);
	}

	/**
	 * Returns the first community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public static CommunityInfos fetchBycreatorId_status_First(
		long creatorId, int status,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return getPersistence().fetchBycreatorId_status_First(
			creatorId, status, orderByComparator);
	}

	/**
	 * Returns the last community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public static CommunityInfos findBycreatorId_status_Last(
			long creatorId, int status,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().findBycreatorId_status_Last(
			creatorId, status, orderByComparator);
	}

	/**
	 * Returns the last community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public static CommunityInfos fetchBycreatorId_status_Last(
		long creatorId, int status,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return getPersistence().fetchBycreatorId_status_Last(
			creatorId, status, orderByComparator);
	}

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
	public static CommunityInfos[] findBycreatorId_status_PrevAndNext(
			long communityInfosId, long creatorId, int status,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().findBycreatorId_status_PrevAndNext(
			communityInfosId, creatorId, status, orderByComparator);
	}

	/**
	 * Removes all the community infoses where creatorId = &#63; and status = &#63; from the database.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 */
	public static void removeBycreatorId_status(long creatorId, int status) {
		getPersistence().removeBycreatorId_status(creatorId, status);
	}

	/**
	 * Returns the number of community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @return the number of matching community infoses
	 */
	public static int countBycreatorId_status(long creatorId, int status) {
		return getPersistence().countBycreatorId_status(creatorId, status);
	}

	/**
	 * Returns all the community infoses where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the matching community infoses
	 */
	public static List<CommunityInfos> findByexpirationDate(
		Date expirationDate) {

		return getPersistence().findByexpirationDate(expirationDate);
	}

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
	public static List<CommunityInfos> findByexpirationDate(
		Date expirationDate, int start, int end) {

		return getPersistence().findByexpirationDate(
			expirationDate, start, end);
	}

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
	public static List<CommunityInfos> findByexpirationDate(
		Date expirationDate, int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return getPersistence().findByexpirationDate(
			expirationDate, start, end, orderByComparator);
	}

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
	public static List<CommunityInfos> findByexpirationDate(
		Date expirationDate, int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByexpirationDate(
			expirationDate, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public static CommunityInfos findByexpirationDate_First(
			Date expirationDate,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().findByexpirationDate_First(
			expirationDate, orderByComparator);
	}

	/**
	 * Returns the first community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public static CommunityInfos fetchByexpirationDate_First(
		Date expirationDate,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return getPersistence().fetchByexpirationDate_First(
			expirationDate, orderByComparator);
	}

	/**
	 * Returns the last community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	public static CommunityInfos findByexpirationDate_Last(
			Date expirationDate,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().findByexpirationDate_Last(
			expirationDate, orderByComparator);
	}

	/**
	 * Returns the last community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	public static CommunityInfos fetchByexpirationDate_Last(
		Date expirationDate,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return getPersistence().fetchByexpirationDate_Last(
			expirationDate, orderByComparator);
	}

	/**
	 * Returns the community infoses before and after the current community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param communityInfosId the primary key of the current community infos
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next community infos
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	public static CommunityInfos[] findByexpirationDate_PrevAndNext(
			long communityInfosId, Date expirationDate,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().findByexpirationDate_PrevAndNext(
			communityInfosId, expirationDate, orderByComparator);
	}

	/**
	 * Removes all the community infoses where expirationDate = &#63; from the database.
	 *
	 * @param expirationDate the expiration date
	 */
	public static void removeByexpirationDate(Date expirationDate) {
		getPersistence().removeByexpirationDate(expirationDate);
	}

	/**
	 * Returns the number of community infoses where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the number of matching community infoses
	 */
	public static int countByexpirationDate(Date expirationDate) {
		return getPersistence().countByexpirationDate(expirationDate);
	}

	/**
	 * Caches the community infos in the entity cache if it is enabled.
	 *
	 * @param communityInfos the community infos
	 */
	public static void cacheResult(CommunityInfos communityInfos) {
		getPersistence().cacheResult(communityInfos);
	}

	/**
	 * Caches the community infoses in the entity cache if it is enabled.
	 *
	 * @param communityInfoses the community infoses
	 */
	public static void cacheResult(List<CommunityInfos> communityInfoses) {
		getPersistence().cacheResult(communityInfoses);
	}

	/**
	 * Creates a new community infos with the primary key. Does not add the community infos to the database.
	 *
	 * @param communityInfosId the primary key for the new community infos
	 * @return the new community infos
	 */
	public static CommunityInfos create(long communityInfosId) {
		return getPersistence().create(communityInfosId);
	}

	/**
	 * Removes the community infos with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos that was removed
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	public static CommunityInfos remove(long communityInfosId)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().remove(communityInfosId);
	}

	public static CommunityInfos updateImpl(CommunityInfos communityInfos) {
		return getPersistence().updateImpl(communityInfos);
	}

	/**
	 * Returns the community infos with the primary key or throws a <code>NoSuchCommunityInfosException</code> if it could not be found.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	public static CommunityInfos findByPrimaryKey(long communityInfosId)
		throws com.weprode.facile.group.exception.
			NoSuchCommunityInfosException {

		return getPersistence().findByPrimaryKey(communityInfosId);
	}

	/**
	 * Returns the community infos with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos, or <code>null</code> if a community infos with the primary key could not be found
	 */
	public static CommunityInfos fetchByPrimaryKey(long communityInfosId) {
		return getPersistence().fetchByPrimaryKey(communityInfosId);
	}

	/**
	 * Returns all the community infoses.
	 *
	 * @return the community infoses
	 */
	public static List<CommunityInfos> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CommunityInfos> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CommunityInfos> findAll(
		int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CommunityInfos> findAll(
		int start, int end, OrderByComparator<CommunityInfos> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the community infoses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of community infoses.
	 *
	 * @return the number of community infoses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommunityInfosPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CommunityInfosPersistence _persistence;

}