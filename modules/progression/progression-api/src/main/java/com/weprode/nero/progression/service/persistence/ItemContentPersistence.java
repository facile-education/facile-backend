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

package com.weprode.nero.progression.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.progression.exception.NoSuchItemContentException;
import com.weprode.nero.progression.model.ItemContent;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the item content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItemContentUtil
 * @generated
 */
@ProviderType
public interface ItemContentPersistence extends BasePersistence<ItemContent> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemContentUtil} to access the item content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the item contents where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching item contents
	 */
	public java.util.List<ItemContent> findByprogressionItemId(
		long progressionItemId);

	/**
	 * Returns a range of all the item contents where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @return the range of matching item contents
	 */
	public java.util.List<ItemContent> findByprogressionItemId(
		long progressionItemId, int start, int end);

	/**
	 * Returns an ordered range of all the item contents where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item contents
	 */
	public java.util.List<ItemContent> findByprogressionItemId(
		long progressionItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemContent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the item contents where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item contents
	 */
	public java.util.List<ItemContent> findByprogressionItemId(
		long progressionItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemContent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item content
	 * @throws NoSuchItemContentException if a matching item content could not be found
	 */
	public ItemContent findByprogressionItemId_First(
			long progressionItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemContent>
				orderByComparator)
		throws NoSuchItemContentException;

	/**
	 * Returns the first item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item content, or <code>null</code> if a matching item content could not be found
	 */
	public ItemContent fetchByprogressionItemId_First(
		long progressionItemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemContent>
			orderByComparator);

	/**
	 * Returns the last item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item content
	 * @throws NoSuchItemContentException if a matching item content could not be found
	 */
	public ItemContent findByprogressionItemId_Last(
			long progressionItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemContent>
				orderByComparator)
		throws NoSuchItemContentException;

	/**
	 * Returns the last item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item content, or <code>null</code> if a matching item content could not be found
	 */
	public ItemContent fetchByprogressionItemId_Last(
		long progressionItemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemContent>
			orderByComparator);

	/**
	 * Returns the item contents before and after the current item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param contentId the primary key of the current item content
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item content
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	public ItemContent[] findByprogressionItemId_PrevAndNext(
			long contentId, long progressionItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemContent>
				orderByComparator)
		throws NoSuchItemContentException;

	/**
	 * Removes all the item contents where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 */
	public void removeByprogressionItemId(long progressionItemId);

	/**
	 * Returns the number of item contents where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching item contents
	 */
	public int countByprogressionItemId(long progressionItemId);

	/**
	 * Caches the item content in the entity cache if it is enabled.
	 *
	 * @param itemContent the item content
	 */
	public void cacheResult(ItemContent itemContent);

	/**
	 * Caches the item contents in the entity cache if it is enabled.
	 *
	 * @param itemContents the item contents
	 */
	public void cacheResult(java.util.List<ItemContent> itemContents);

	/**
	 * Creates a new item content with the primary key. Does not add the item content to the database.
	 *
	 * @param contentId the primary key for the new item content
	 * @return the new item content
	 */
	public ItemContent create(long contentId);

	/**
	 * Removes the item content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content that was removed
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	public ItemContent remove(long contentId) throws NoSuchItemContentException;

	public ItemContent updateImpl(ItemContent itemContent);

	/**
	 * Returns the item content with the primary key or throws a <code>NoSuchItemContentException</code> if it could not be found.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	public ItemContent findByPrimaryKey(long contentId)
		throws NoSuchItemContentException;

	/**
	 * Returns the item content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content, or <code>null</code> if a item content with the primary key could not be found
	 */
	public ItemContent fetchByPrimaryKey(long contentId);

	/**
	 * Returns all the item contents.
	 *
	 * @return the item contents
	 */
	public java.util.List<ItemContent> findAll();

	/**
	 * Returns a range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @return the range of item contents
	 */
	public java.util.List<ItemContent> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item contents
	 */
	public java.util.List<ItemContent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemContent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of item contents
	 */
	public java.util.List<ItemContent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemContent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the item contents from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of item contents.
	 *
	 * @return the number of item contents
	 */
	public int countAll();

}