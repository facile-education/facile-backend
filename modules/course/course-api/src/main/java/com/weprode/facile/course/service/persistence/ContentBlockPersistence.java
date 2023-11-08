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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.course.exception.NoSuchContentBlockException;
import com.weprode.facile.course.model.ContentBlock;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the content block service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlockUtil
 * @generated
 */
@ProviderType
public interface ContentBlockPersistence extends BasePersistence<ContentBlock> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContentBlockUtil} to access the content block persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the content blocks where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @return the matching content blocks
	 */
	public java.util.List<ContentBlock> findBycourseItemId(long courseItemId);

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
	public java.util.List<ContentBlock> findBycourseItemId(
		long courseItemId, int start, int end);

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
	public java.util.List<ContentBlock> findBycourseItemId(
		long courseItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentBlock>
			orderByComparator);

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
	public java.util.List<ContentBlock> findBycourseItemId(
		long courseItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentBlock>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content block
	 * @throws NoSuchContentBlockException if a matching content block could not be found
	 */
	public ContentBlock findBycourseItemId_First(
			long courseItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ContentBlock>
				orderByComparator)
		throws NoSuchContentBlockException;

	/**
	 * Returns the first content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content block, or <code>null</code> if a matching content block could not be found
	 */
	public ContentBlock fetchBycourseItemId_First(
		long courseItemId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentBlock>
			orderByComparator);

	/**
	 * Returns the last content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content block
	 * @throws NoSuchContentBlockException if a matching content block could not be found
	 */
	public ContentBlock findBycourseItemId_Last(
			long courseItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ContentBlock>
				orderByComparator)
		throws NoSuchContentBlockException;

	/**
	 * Returns the last content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content block, or <code>null</code> if a matching content block could not be found
	 */
	public ContentBlock fetchBycourseItemId_Last(
		long courseItemId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentBlock>
			orderByComparator);

	/**
	 * Returns the content blocks before and after the current content block in the ordered set where courseItemId = &#63;.
	 *
	 * @param blockId the primary key of the current content block
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content block
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	public ContentBlock[] findBycourseItemId_PrevAndNext(
			long blockId, long courseItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ContentBlock>
				orderByComparator)
		throws NoSuchContentBlockException;

	/**
	 * Removes all the content blocks where courseItemId = &#63; from the database.
	 *
	 * @param courseItemId the course item ID
	 */
	public void removeBycourseItemId(long courseItemId);

	/**
	 * Returns the number of content blocks where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @return the number of matching content blocks
	 */
	public int countBycourseItemId(long courseItemId);

	/**
	 * Caches the content block in the entity cache if it is enabled.
	 *
	 * @param contentBlock the content block
	 */
	public void cacheResult(ContentBlock contentBlock);

	/**
	 * Caches the content blocks in the entity cache if it is enabled.
	 *
	 * @param contentBlocks the content blocks
	 */
	public void cacheResult(java.util.List<ContentBlock> contentBlocks);

	/**
	 * Creates a new content block with the primary key. Does not add the content block to the database.
	 *
	 * @param blockId the primary key for the new content block
	 * @return the new content block
	 */
	public ContentBlock create(long blockId);

	/**
	 * Removes the content block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block that was removed
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	public ContentBlock remove(long blockId) throws NoSuchContentBlockException;

	public ContentBlock updateImpl(ContentBlock contentBlock);

	/**
	 * Returns the content block with the primary key or throws a <code>NoSuchContentBlockException</code> if it could not be found.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block
	 * @throws NoSuchContentBlockException if a content block with the primary key could not be found
	 */
	public ContentBlock findByPrimaryKey(long blockId)
		throws NoSuchContentBlockException;

	/**
	 * Returns the content block with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block, or <code>null</code> if a content block with the primary key could not be found
	 */
	public ContentBlock fetchByPrimaryKey(long blockId);

	/**
	 * Returns all the content blocks.
	 *
	 * @return the content blocks
	 */
	public java.util.List<ContentBlock> findAll();

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
	public java.util.List<ContentBlock> findAll(int start, int end);

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
	public java.util.List<ContentBlock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentBlock>
			orderByComparator);

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
	public java.util.List<ContentBlock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentBlock>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the content blocks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of content blocks.
	 *
	 * @return the number of content blocks
	 */
	public int countAll();

}