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

package com.weprode.facile.course.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.course.model.ContentBlock;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the content block service. This utility wraps <code>com.weprode.facile.course.service.persistence.impl.ContentBlockPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlockPersistence
 * @generated
 */
public class ContentBlockUtil {

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
	public static void clearCache(ContentBlock contentBlock) {
		getPersistence().clearCache(contentBlock);
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
	public static Map<Serializable, ContentBlock> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ContentBlock> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContentBlock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContentBlock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContentBlock> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContentBlock update(ContentBlock contentBlock) {
		return getPersistence().update(contentBlock);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContentBlock update(
		ContentBlock contentBlock, ServiceContext serviceContext) {

		return getPersistence().update(contentBlock, serviceContext);
	}

	/**
	 * Returns all the content blocks where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @return the matching content blocks
	 */
	public static List<ContentBlock> findBycourseItemId(long courseItemId) {
		return getPersistence().findBycourseItemId(courseItemId);
	}

	/**
	 * Returns a range of all the content blocks where courseItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param courseItemId the course item ID
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @return the range of matching content blocks
	 */
	public static List<ContentBlock> findBycourseItemId(
		long courseItemId, int start, int end) {

		return getPersistence().findBycourseItemId(courseItemId, start, end);
	}

	/**
	 * Returns an ordered range of all the content blocks where courseItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param courseItemId the course item ID
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching content blocks
	 */
	public static List<ContentBlock> findBycourseItemId(
		long courseItemId, int start, int end,
		OrderByComparator<ContentBlock> orderByComparator) {

		return getPersistence().findBycourseItemId(
			courseItemId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the content blocks where courseItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param courseItemId the course item ID
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching content blocks
	 */
	public static List<ContentBlock> findBycourseItemId(
		long courseItemId, int start, int end,
		OrderByComparator<ContentBlock> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBycourseItemId(
			courseItemId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content block
	 * @throws NoSuchContentBlockException if a matching content block could not be found
	 */
	public static ContentBlock findBycourseItemId_First(
			long courseItemId,
			OrderByComparator<ContentBlock> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchContentBlockException {

		return getPersistence().findBycourseItemId_First(
			courseItemId, orderByComparator);
	}

	/**
	 * Returns the first content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content block, or <code>null</code> if a matching content block could not be found
	 */
	public static ContentBlock fetchBycourseItemId_First(
		long courseItemId, OrderByComparator<ContentBlock> orderByComparator) {

		return getPersistence().fetchBycourseItemId_First(
			courseItemId, orderByComparator);
	}

	/**
	 * Returns the last content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content block
	 * @throws NoSuchContentBlockException if a matching content block could not be found
	 */
	public static ContentBlock findBycourseItemId_Last(
			long courseItemId,
			OrderByComparator<ContentBlock> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchContentBlockException {

		return getPersistence().findBycourseItemId_Last(
			courseItemId, orderByComparator);
	}

	/**
	 * Returns the last content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content block, or <code>null</code> if a matching content block could not be found
	 */
	public static ContentBlock fetchBycourseItemId_Last(
		long courseItemId, OrderByComparator<ContentBlock> orderByComparator) {

		return getPersistence().fetchBycourseItemId_Last(
			courseItemId, orderByComparator);
	}

	/**
	 * Returns the content blocks before and after the current content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param blockId the primary key of the current content block
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content block
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	public static ContentBlock[] findBycourseItemId_PrevAndNext(
			long blockId, long courseItemId,
			OrderByComparator<ContentBlock> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchContentBlockException {

		return getPersistence().findBycourseItemId_PrevAndNext(
			blockId, courseItemId, orderByComparator);
	}

	/**
	 * Removes all the content blocks where courseItemId = &#63; from the database.
	 *
	 * @param courseItemId the course item ID
	 */
	public static void removeBycourseItemId(long courseItemId) {
		getPersistence().removeBycourseItemId(courseItemId);
	}

	/**
	 * Returns the number of content blocks where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @return the number of matching content blocks
	 */
	public static int countBycourseItemId(long courseItemId) {
		return getPersistence().countBycourseItemId(courseItemId);
	}

	/**
	 * Caches the content block in the entity cache if it is enabled.
	 *
	 * @param contentBlock the content block
	 */
	public static void cacheResult(ContentBlock contentBlock) {
		getPersistence().cacheResult(contentBlock);
	}

	/**
	 * Caches the content blocks in the entity cache if it is enabled.
	 *
	 * @param contentBlocks the content blocks
	 */
	public static void cacheResult(List<ContentBlock> contentBlocks) {
		getPersistence().cacheResult(contentBlocks);
	}

	/**
	 * Creates a new content block with the primary key. Does not add the content block to the database.
	 *
	 * @param blockId the primary key for the new content block
	 * @return the new content block
	 */
	public static ContentBlock create(long blockId) {
		return getPersistence().create(blockId);
	}

	/**
	 * Removes the content block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block that was removed
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	public static ContentBlock remove(long blockId)
		throws com.weprode.facile.course.exception.NoSuchContentBlockException {

		return getPersistence().remove(blockId);
	}

	public static ContentBlock updateImpl(ContentBlock contentBlock) {
		return getPersistence().updateImpl(contentBlock);
	}

	/**
	 * Returns the content block with the primary key or throws a <code>NoSuchContentBlockException</code> if it could not be found.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	public static ContentBlock findByPrimaryKey(long blockId)
		throws com.weprode.facile.course.exception.NoSuchContentBlockException {

		return getPersistence().findByPrimaryKey(blockId);
	}

	/**
	 * Returns the content block with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block, or <code>null</code> if a content block with the primary key could not be found
	 */
	public static ContentBlock fetchByPrimaryKey(long blockId) {
		return getPersistence().fetchByPrimaryKey(blockId);
	}

	/**
	 * Returns all the content blocks.
	 *
	 * @return the content blocks
	 */
	public static List<ContentBlock> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the content blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @return the range of content blocks
	 */
	public static List<ContentBlock> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the content blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of content blocks
	 */
	public static List<ContentBlock> findAll(
		int start, int end, OrderByComparator<ContentBlock> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the content blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of content blocks
	 */
	public static List<ContentBlock> findAll(
		int start, int end, OrderByComparator<ContentBlock> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the content blocks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of content blocks.
	 *
	 * @return the number of content blocks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ContentBlockPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ContentBlockPersistence _persistence;

}