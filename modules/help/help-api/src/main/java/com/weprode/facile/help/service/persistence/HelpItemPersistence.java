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

package com.weprode.facile.help.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.help.exception.NoSuchItemException;
import com.weprode.facile.help.model.HelpItem;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the help item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpItemUtil
 * @generated
 */
@ProviderType
public interface HelpItemPersistence extends BasePersistence<HelpItem> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelpItemUtil} to access the help item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the help items where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the matching help items
	 */
	public java.util.List<HelpItem> findBycategoryId(long categoryId);

	/**
	 * Returns a range of all the help items where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @return the range of matching help items
	 */
	public java.util.List<HelpItem> findBycategoryId(
		long categoryId, int start, int end);

	/**
	 * Returns an ordered range of all the help items where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help items
	 */
	public java.util.List<HelpItem> findBycategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItem>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help items where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help items
	 */
	public java.util.List<HelpItem> findBycategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItem>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item
	 * @throws NoSuchItemException if a matching help item could not be found
	 */
	public HelpItem findBycategoryId_First(
			long categoryId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpItem>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the first help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item, or <code>null</code> if a matching help item could not be found
	 */
	public HelpItem fetchBycategoryId_First(
		long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItem>
			orderByComparator);

	/**
	 * Returns the last help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item
	 * @throws NoSuchItemException if a matching help item could not be found
	 */
	public HelpItem findBycategoryId_Last(
			long categoryId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpItem>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the last help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item, or <code>null</code> if a matching help item could not be found
	 */
	public HelpItem fetchBycategoryId_Last(
		long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItem>
			orderByComparator);

	/**
	 * Returns the help items before and after the current help item in the ordered set where categoryId = &#63;.
	 *
	 * @param itemId the primary key of the current help item
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help item
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	public HelpItem[] findBycategoryId_PrevAndNext(
			long itemId, long categoryId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpItem>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Removes all the help items where categoryId = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 */
	public void removeBycategoryId(long categoryId);

	/**
	 * Returns the number of help items where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the number of matching help items
	 */
	public int countBycategoryId(long categoryId);

	/**
	 * Caches the help item in the entity cache if it is enabled.
	 *
	 * @param helpItem the help item
	 */
	public void cacheResult(HelpItem helpItem);

	/**
	 * Caches the help items in the entity cache if it is enabled.
	 *
	 * @param helpItems the help items
	 */
	public void cacheResult(java.util.List<HelpItem> helpItems);

	/**
	 * Creates a new help item with the primary key. Does not add the help item to the database.
	 *
	 * @param itemId the primary key for the new help item
	 * @return the new help item
	 */
	public HelpItem create(long itemId);

	/**
	 * Removes the help item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item that was removed
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	public HelpItem remove(long itemId) throws NoSuchItemException;

	public HelpItem updateImpl(HelpItem helpItem);

	/**
	 * Returns the help item with the primary key or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	public HelpItem findByPrimaryKey(long itemId) throws NoSuchItemException;

	/**
	 * Returns the help item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item, or <code>null</code> if a help item with the primary key could not be found
	 */
	public HelpItem fetchByPrimaryKey(long itemId);

	/**
	 * Returns all the help items.
	 *
	 * @return the help items
	 */
	public java.util.List<HelpItem> findAll();

	/**
	 * Returns a range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @return the range of help items
	 */
	public java.util.List<HelpItem> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help items
	 */
	public java.util.List<HelpItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItem>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help items
	 */
	public java.util.List<HelpItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpItem>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the help items from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of help items.
	 *
	 * @return the number of help items
	 */
	public int countAll();

}