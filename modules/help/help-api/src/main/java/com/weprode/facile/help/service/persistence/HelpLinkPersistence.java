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

import com.weprode.facile.help.exception.NoSuchLinkException;
import com.weprode.facile.help.model.HelpLink;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the help link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpLinkUtil
 * @generated
 */
@ProviderType
public interface HelpLinkPersistence extends BasePersistence<HelpLink> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelpLinkUtil} to access the help link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the help links where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help links
	 */
	public java.util.List<HelpLink> findByitemId(long itemId);

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
	public java.util.List<HelpLink> findByitemId(
		long itemId, int start, int end);

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
	public java.util.List<HelpLink> findByitemId(
		long itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpLink>
			orderByComparator);

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
	public java.util.List<HelpLink> findByitemId(
		long itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpLink>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help link
	 * @throws NoSuchLinkException if a matching help link could not be found
	 */
	public HelpLink findByitemId_First(
			long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpLink>
				orderByComparator)
		throws NoSuchLinkException;

	/**
	 * Returns the first help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help link, or <code>null</code> if a matching help link could not be found
	 */
	public HelpLink fetchByitemId_First(
		long itemId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpLink>
			orderByComparator);

	/**
	 * Returns the last help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help link
	 * @throws NoSuchLinkException if a matching help link could not be found
	 */
	public HelpLink findByitemId_Last(
			long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpLink>
				orderByComparator)
		throws NoSuchLinkException;

	/**
	 * Returns the last help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help link, or <code>null</code> if a matching help link could not be found
	 */
	public HelpLink fetchByitemId_Last(
		long itemId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpLink>
			orderByComparator);

	/**
	 * Returns the help links before and after the current help link in the ordered set where itemId = &#63;.
	 *
	 * @param linkId the primary key of the current help link
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help link
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	public HelpLink[] findByitemId_PrevAndNext(
			long linkId, long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpLink>
				orderByComparator)
		throws NoSuchLinkException;

	/**
	 * Removes all the help links where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	public void removeByitemId(long itemId);

	/**
	 * Returns the number of help links where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help links
	 */
	public int countByitemId(long itemId);

	/**
	 * Caches the help link in the entity cache if it is enabled.
	 *
	 * @param helpLink the help link
	 */
	public void cacheResult(HelpLink helpLink);

	/**
	 * Caches the help links in the entity cache if it is enabled.
	 *
	 * @param helpLinks the help links
	 */
	public void cacheResult(java.util.List<HelpLink> helpLinks);

	/**
	 * Creates a new help link with the primary key. Does not add the help link to the database.
	 *
	 * @param linkId the primary key for the new help link
	 * @return the new help link
	 */
	public HelpLink create(long linkId);

	/**
	 * Removes the help link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link that was removed
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	public HelpLink remove(long linkId) throws NoSuchLinkException;

	public HelpLink updateImpl(HelpLink helpLink);

	/**
	 * Returns the help link with the primary key or throws a <code>NoSuchLinkException</code> if it could not be found.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	public HelpLink findByPrimaryKey(long linkId) throws NoSuchLinkException;

	/**
	 * Returns the help link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link, or <code>null</code> if a help link with the primary key could not be found
	 */
	public HelpLink fetchByPrimaryKey(long linkId);

	/**
	 * Returns all the help links.
	 *
	 * @return the help links
	 */
	public java.util.List<HelpLink> findAll();

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
	public java.util.List<HelpLink> findAll(int start, int end);

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
	public java.util.List<HelpLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpLink>
			orderByComparator);

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
	public java.util.List<HelpLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpLink>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the help links from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of help links.
	 *
	 * @return the number of help links
	 */
	public int countAll();

}