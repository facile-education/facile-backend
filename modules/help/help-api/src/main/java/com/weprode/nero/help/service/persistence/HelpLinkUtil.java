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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.help.model.HelpLink;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the help link service. This utility wraps <code>com.weprode.nero.help.service.persistence.impl.HelpLinkPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpLinkPersistence
 * @generated
 */
public class HelpLinkUtil {

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
	public static void clearCache(HelpLink helpLink) {
		getPersistence().clearCache(helpLink);
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
	public static Map<Serializable, HelpLink> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HelpLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HelpLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HelpLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HelpLink> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HelpLink update(HelpLink helpLink) {
		return getPersistence().update(helpLink);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HelpLink update(
		HelpLink helpLink, ServiceContext serviceContext) {

		return getPersistence().update(helpLink, serviceContext);
	}

	/**
	 * Returns all the help links where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help links
	 */
	public static List<HelpLink> findByitemId(long itemId) {
		return getPersistence().findByitemId(itemId);
	}

	/**
	 * Returns a range of all the help links where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @return the range of matching help links
	 */
	public static List<HelpLink> findByitemId(long itemId, int start, int end) {
		return getPersistence().findByitemId(itemId, start, end);
	}

	/**
	 * Returns an ordered range of all the help links where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help links
	 */
	public static List<HelpLink> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpLink> orderByComparator) {

		return getPersistence().findByitemId(
			itemId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help links where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help links
	 */
	public static List<HelpLink> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpLink> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByitemId(
			itemId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help link
	 * @throws NoSuchLinkException if a matching help link could not be found
	 */
	public static HelpLink findByitemId_First(
			long itemId, OrderByComparator<HelpLink> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchLinkException {

		return getPersistence().findByitemId_First(itemId, orderByComparator);
	}

	/**
	 * Returns the first help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help link, or <code>null</code> if a matching help link could not be found
	 */
	public static HelpLink fetchByitemId_First(
		long itemId, OrderByComparator<HelpLink> orderByComparator) {

		return getPersistence().fetchByitemId_First(itemId, orderByComparator);
	}

	/**
	 * Returns the last help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help link
	 * @throws NoSuchLinkException if a matching help link could not be found
	 */
	public static HelpLink findByitemId_Last(
			long itemId, OrderByComparator<HelpLink> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchLinkException {

		return getPersistence().findByitemId_Last(itemId, orderByComparator);
	}

	/**
	 * Returns the last help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help link, or <code>null</code> if a matching help link could not be found
	 */
	public static HelpLink fetchByitemId_Last(
		long itemId, OrderByComparator<HelpLink> orderByComparator) {

		return getPersistence().fetchByitemId_Last(itemId, orderByComparator);
	}

	/**
	 * Returns the help links before and after the current help link in the ordered set where itemId = &#63;.
	 *
	 * @param linkId the primary key of the current help link
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help link
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	public static HelpLink[] findByitemId_PrevAndNext(
			long linkId, long itemId,
			OrderByComparator<HelpLink> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchLinkException {

		return getPersistence().findByitemId_PrevAndNext(
			linkId, itemId, orderByComparator);
	}

	/**
	 * Removes all the help links where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	public static void removeByitemId(long itemId) {
		getPersistence().removeByitemId(itemId);
	}

	/**
	 * Returns the number of help links where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help links
	 */
	public static int countByitemId(long itemId) {
		return getPersistence().countByitemId(itemId);
	}

	/**
	 * Caches the help link in the entity cache if it is enabled.
	 *
	 * @param helpLink the help link
	 */
	public static void cacheResult(HelpLink helpLink) {
		getPersistence().cacheResult(helpLink);
	}

	/**
	 * Caches the help links in the entity cache if it is enabled.
	 *
	 * @param helpLinks the help links
	 */
	public static void cacheResult(List<HelpLink> helpLinks) {
		getPersistence().cacheResult(helpLinks);
	}

	/**
	 * Creates a new help link with the primary key. Does not add the help link to the database.
	 *
	 * @param linkId the primary key for the new help link
	 * @return the new help link
	 */
	public static HelpLink create(long linkId) {
		return getPersistence().create(linkId);
	}

	/**
	 * Removes the help link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link that was removed
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	public static HelpLink remove(long linkId)
		throws com.weprode.nero.help.exception.NoSuchLinkException {

		return getPersistence().remove(linkId);
	}

	public static HelpLink updateImpl(HelpLink helpLink) {
		return getPersistence().updateImpl(helpLink);
	}

	/**
	 * Returns the help link with the primary key or throws a <code>NoSuchLinkException</code> if it could not be found.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	public static HelpLink findByPrimaryKey(long linkId)
		throws com.weprode.nero.help.exception.NoSuchLinkException {

		return getPersistence().findByPrimaryKey(linkId);
	}

	/**
	 * Returns the help link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link, or <code>null</code> if a help link with the primary key could not be found
	 */
	public static HelpLink fetchByPrimaryKey(long linkId) {
		return getPersistence().fetchByPrimaryKey(linkId);
	}

	/**
	 * Returns all the help links.
	 *
	 * @return the help links
	 */
	public static List<HelpLink> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the help links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @return the range of help links
	 */
	public static List<HelpLink> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the help links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help links
	 */
	public static List<HelpLink> findAll(
		int start, int end, OrderByComparator<HelpLink> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help links
	 */
	public static List<HelpLink> findAll(
		int start, int end, OrderByComparator<HelpLink> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the help links from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of help links.
	 *
	 * @return the number of help links
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HelpLinkPersistence getPersistence() {
		return _persistence;
	}

	private static volatile HelpLinkPersistence _persistence;

}