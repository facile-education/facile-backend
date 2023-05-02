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

import com.weprode.nero.help.model.HelpRelation;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the help relation service. This utility wraps <code>com.weprode.nero.help.service.persistence.impl.HelpRelationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRelationPersistence
 * @generated
 */
public class HelpRelationUtil {

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
	public static void clearCache(HelpRelation helpRelation) {
		getPersistence().clearCache(helpRelation);
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
	public static Map<Serializable, HelpRelation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HelpRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HelpRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HelpRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HelpRelation> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HelpRelation update(HelpRelation helpRelation) {
		return getPersistence().update(helpRelation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HelpRelation update(
		HelpRelation helpRelation, ServiceContext serviceContext) {

		return getPersistence().update(helpRelation, serviceContext);
	}

	/**
	 * Returns all the help relations where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help relations
	 */
	public static List<HelpRelation> findByitemId(long itemId) {
		return getPersistence().findByitemId(itemId);
	}

	/**
	 * Returns a range of all the help relations where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @return the range of matching help relations
	 */
	public static List<HelpRelation> findByitemId(
		long itemId, int start, int end) {

		return getPersistence().findByitemId(itemId, start, end);
	}

	/**
	 * Returns an ordered range of all the help relations where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help relations
	 */
	public static List<HelpRelation> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpRelation> orderByComparator) {

		return getPersistence().findByitemId(
			itemId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help relations where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help relations
	 */
	public static List<HelpRelation> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpRelation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByitemId(
			itemId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help relation
	 * @throws NoSuchRelationException if a matching help relation could not be found
	 */
	public static HelpRelation findByitemId_First(
			long itemId, OrderByComparator<HelpRelation> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchRelationException {

		return getPersistence().findByitemId_First(itemId, orderByComparator);
	}

	/**
	 * Returns the first help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help relation, or <code>null</code> if a matching help relation could not be found
	 */
	public static HelpRelation fetchByitemId_First(
		long itemId, OrderByComparator<HelpRelation> orderByComparator) {

		return getPersistence().fetchByitemId_First(itemId, orderByComparator);
	}

	/**
	 * Returns the last help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help relation
	 * @throws NoSuchRelationException if a matching help relation could not be found
	 */
	public static HelpRelation findByitemId_Last(
			long itemId, OrderByComparator<HelpRelation> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchRelationException {

		return getPersistence().findByitemId_Last(itemId, orderByComparator);
	}

	/**
	 * Returns the last help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help relation, or <code>null</code> if a matching help relation could not be found
	 */
	public static HelpRelation fetchByitemId_Last(
		long itemId, OrderByComparator<HelpRelation> orderByComparator) {

		return getPersistence().fetchByitemId_Last(itemId, orderByComparator);
	}

	/**
	 * Returns the help relations before and after the current help relation in the ordered set where itemId = &#63;.
	 *
	 * @param relationId the primary key of the current help relation
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help relation
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	public static HelpRelation[] findByitemId_PrevAndNext(
			long relationId, long itemId,
			OrderByComparator<HelpRelation> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchRelationException {

		return getPersistence().findByitemId_PrevAndNext(
			relationId, itemId, orderByComparator);
	}

	/**
	 * Removes all the help relations where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	public static void removeByitemId(long itemId) {
		getPersistence().removeByitemId(itemId);
	}

	/**
	 * Returns the number of help relations where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help relations
	 */
	public static int countByitemId(long itemId) {
		return getPersistence().countByitemId(itemId);
	}

	/**
	 * Caches the help relation in the entity cache if it is enabled.
	 *
	 * @param helpRelation the help relation
	 */
	public static void cacheResult(HelpRelation helpRelation) {
		getPersistence().cacheResult(helpRelation);
	}

	/**
	 * Caches the help relations in the entity cache if it is enabled.
	 *
	 * @param helpRelations the help relations
	 */
	public static void cacheResult(List<HelpRelation> helpRelations) {
		getPersistence().cacheResult(helpRelations);
	}

	/**
	 * Creates a new help relation with the primary key. Does not add the help relation to the database.
	 *
	 * @param relationId the primary key for the new help relation
	 * @return the new help relation
	 */
	public static HelpRelation create(long relationId) {
		return getPersistence().create(relationId);
	}

	/**
	 * Removes the help relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation that was removed
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	public static HelpRelation remove(long relationId)
		throws com.weprode.nero.help.exception.NoSuchRelationException {

		return getPersistence().remove(relationId);
	}

	public static HelpRelation updateImpl(HelpRelation helpRelation) {
		return getPersistence().updateImpl(helpRelation);
	}

	/**
	 * Returns the help relation with the primary key or throws a <code>NoSuchRelationException</code> if it could not be found.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	public static HelpRelation findByPrimaryKey(long relationId)
		throws com.weprode.nero.help.exception.NoSuchRelationException {

		return getPersistence().findByPrimaryKey(relationId);
	}

	/**
	 * Returns the help relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation, or <code>null</code> if a help relation with the primary key could not be found
	 */
	public static HelpRelation fetchByPrimaryKey(long relationId) {
		return getPersistence().fetchByPrimaryKey(relationId);
	}

	/**
	 * Returns all the help relations.
	 *
	 * @return the help relations
	 */
	public static List<HelpRelation> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the help relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @return the range of help relations
	 */
	public static List<HelpRelation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the help relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help relations
	 */
	public static List<HelpRelation> findAll(
		int start, int end, OrderByComparator<HelpRelation> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help relations
	 */
	public static List<HelpRelation> findAll(
		int start, int end, OrderByComparator<HelpRelation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the help relations from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of help relations.
	 *
	 * @return the number of help relations
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HelpRelationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile HelpRelationPersistence _persistence;

}