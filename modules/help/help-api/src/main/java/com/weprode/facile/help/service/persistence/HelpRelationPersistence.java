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

import com.weprode.facile.help.exception.NoSuchRelationException;
import com.weprode.facile.help.model.HelpRelation;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the help relation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRelationUtil
 * @generated
 */
@ProviderType
public interface HelpRelationPersistence extends BasePersistence<HelpRelation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelpRelationUtil} to access the help relation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the help relations where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help relations
	 */
	public java.util.List<HelpRelation> findByitemId(long itemId);

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
	public java.util.List<HelpRelation> findByitemId(
		long itemId, int start, int end);

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
	public java.util.List<HelpRelation> findByitemId(
		long itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRelation>
			orderByComparator);

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
	public java.util.List<HelpRelation> findByitemId(
		long itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRelation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help relation
	 * @throws NoSuchRelationException if a matching help relation could not be found
	 */
	public HelpRelation findByitemId_First(
			long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRelation>
				orderByComparator)
		throws NoSuchRelationException;

	/**
	 * Returns the first help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help relation, or <code>null</code> if a matching help relation could not be found
	 */
	public HelpRelation fetchByitemId_First(
		long itemId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRelation>
			orderByComparator);

	/**
	 * Returns the last help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help relation
	 * @throws NoSuchRelationException if a matching help relation could not be found
	 */
	public HelpRelation findByitemId_Last(
			long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRelation>
				orderByComparator)
		throws NoSuchRelationException;

	/**
	 * Returns the last help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help relation, or <code>null</code> if a matching help relation could not be found
	 */
	public HelpRelation fetchByitemId_Last(
		long itemId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRelation>
			orderByComparator);

	/**
	 * Returns the help relations before and after the current help relation in the ordered set where itemId = &#63;.
	 *
	 * @param relationId the primary key of the current help relation
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help relation
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	public HelpRelation[] findByitemId_PrevAndNext(
			long relationId, long itemId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRelation>
				orderByComparator)
		throws NoSuchRelationException;

	/**
	 * Removes all the help relations where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	public void removeByitemId(long itemId);

	/**
	 * Returns the number of help relations where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help relations
	 */
	public int countByitemId(long itemId);

	/**
	 * Caches the help relation in the entity cache if it is enabled.
	 *
	 * @param helpRelation the help relation
	 */
	public void cacheResult(HelpRelation helpRelation);

	/**
	 * Caches the help relations in the entity cache if it is enabled.
	 *
	 * @param helpRelations the help relations
	 */
	public void cacheResult(java.util.List<HelpRelation> helpRelations);

	/**
	 * Creates a new help relation with the primary key. Does not add the help relation to the database.
	 *
	 * @param relationId the primary key for the new help relation
	 * @return the new help relation
	 */
	public HelpRelation create(long relationId);

	/**
	 * Removes the help relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation that was removed
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	public HelpRelation remove(long relationId) throws NoSuchRelationException;

	public HelpRelation updateImpl(HelpRelation helpRelation);

	/**
	 * Returns the help relation with the primary key or throws a <code>NoSuchRelationException</code> if it could not be found.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	public HelpRelation findByPrimaryKey(long relationId)
		throws NoSuchRelationException;

	/**
	 * Returns the help relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation, or <code>null</code> if a help relation with the primary key could not be found
	 */
	public HelpRelation fetchByPrimaryKey(long relationId);

	/**
	 * Returns all the help relations.
	 *
	 * @return the help relations
	 */
	public java.util.List<HelpRelation> findAll();

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
	public java.util.List<HelpRelation> findAll(int start, int end);

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
	public java.util.List<HelpRelation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRelation>
			orderByComparator);

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
	public java.util.List<HelpRelation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRelation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the help relations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of help relations.
	 *
	 * @return the number of help relations
	 */
	public int countAll();

}