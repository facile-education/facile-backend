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

package com.weprode.nero.user.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.user.exception.NoSuchRelationshipException;
import com.weprode.nero.user.model.UserRelationship;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user relationship service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserRelationshipUtil
 * @generated
 */
@ProviderType
public interface UserRelationshipPersistence
	extends BasePersistence<UserRelationship> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserRelationshipUtil} to access the user relationship persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the user relationships where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @return the matching user relationships
	 */
	public java.util.List<UserRelationship> findBychildUserId(long childUserId);

	/**
	 * Returns a range of all the user relationships where childUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param childUserId the child user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of matching user relationships
	 */
	public java.util.List<UserRelationship> findBychildUserId(
		long childUserId, int start, int end);

	/**
	 * Returns an ordered range of all the user relationships where childUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param childUserId the child user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user relationships
	 */
	public java.util.List<UserRelationship> findBychildUserId(
		long childUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user relationships where childUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param childUserId the child user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user relationships
	 */
	public java.util.List<UserRelationship> findBychildUserId(
		long childUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	public UserRelationship findBychildUserId_First(
			long childUserId,
			com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
				orderByComparator)
		throws NoSuchRelationshipException;

	/**
	 * Returns the first user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	public UserRelationship fetchBychildUserId_First(
		long childUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator);

	/**
	 * Returns the last user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	public UserRelationship findBychildUserId_Last(
			long childUserId,
			com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
				orderByComparator)
		throws NoSuchRelationshipException;

	/**
	 * Returns the last user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	public UserRelationship fetchBychildUserId_Last(
		long childUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator);

	/**
	 * Returns the user relationships before and after the current user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param userRelationshipPK the primary key of the current user relationship
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	public UserRelationship[] findBychildUserId_PrevAndNext(
			UserRelationshipPK userRelationshipPK, long childUserId,
			com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
				orderByComparator)
		throws NoSuchRelationshipException;

	/**
	 * Removes all the user relationships where childUserId = &#63; from the database.
	 *
	 * @param childUserId the child user ID
	 */
	public void removeBychildUserId(long childUserId);

	/**
	 * Returns the number of user relationships where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @return the number of matching user relationships
	 */
	public int countBychildUserId(long childUserId);

	/**
	 * Returns all the user relationships where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @return the matching user relationships
	 */
	public java.util.List<UserRelationship> findByparentUserId(
		long parentUserId);

	/**
	 * Returns a range of all the user relationships where parentUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param parentUserId the parent user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of matching user relationships
	 */
	public java.util.List<UserRelationship> findByparentUserId(
		long parentUserId, int start, int end);

	/**
	 * Returns an ordered range of all the user relationships where parentUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param parentUserId the parent user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user relationships
	 */
	public java.util.List<UserRelationship> findByparentUserId(
		long parentUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user relationships where parentUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param parentUserId the parent user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user relationships
	 */
	public java.util.List<UserRelationship> findByparentUserId(
		long parentUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	public UserRelationship findByparentUserId_First(
			long parentUserId,
			com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
				orderByComparator)
		throws NoSuchRelationshipException;

	/**
	 * Returns the first user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	public UserRelationship fetchByparentUserId_First(
		long parentUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator);

	/**
	 * Returns the last user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	public UserRelationship findByparentUserId_Last(
			long parentUserId,
			com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
				orderByComparator)
		throws NoSuchRelationshipException;

	/**
	 * Returns the last user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	public UserRelationship fetchByparentUserId_Last(
		long parentUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator);

	/**
	 * Returns the user relationships before and after the current user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param userRelationshipPK the primary key of the current user relationship
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	public UserRelationship[] findByparentUserId_PrevAndNext(
			UserRelationshipPK userRelationshipPK, long parentUserId,
			com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
				orderByComparator)
		throws NoSuchRelationshipException;

	/**
	 * Removes all the user relationships where parentUserId = &#63; from the database.
	 *
	 * @param parentUserId the parent user ID
	 */
	public void removeByparentUserId(long parentUserId);

	/**
	 * Returns the number of user relationships where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @return the number of matching user relationships
	 */
	public int countByparentUserId(long parentUserId);

	/**
	 * Caches the user relationship in the entity cache if it is enabled.
	 *
	 * @param userRelationship the user relationship
	 */
	public void cacheResult(UserRelationship userRelationship);

	/**
	 * Caches the user relationships in the entity cache if it is enabled.
	 *
	 * @param userRelationships the user relationships
	 */
	public void cacheResult(java.util.List<UserRelationship> userRelationships);

	/**
	 * Creates a new user relationship with the primary key. Does not add the user relationship to the database.
	 *
	 * @param userRelationshipPK the primary key for the new user relationship
	 * @return the new user relationship
	 */
	public UserRelationship create(UserRelationshipPK userRelationshipPK);

	/**
	 * Removes the user relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship that was removed
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	public UserRelationship remove(UserRelationshipPK userRelationshipPK)
		throws NoSuchRelationshipException;

	public UserRelationship updateImpl(UserRelationship userRelationship);

	/**
	 * Returns the user relationship with the primary key or throws a <code>NoSuchRelationshipException</code> if it could not be found.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	public UserRelationship findByPrimaryKey(
			UserRelationshipPK userRelationshipPK)
		throws NoSuchRelationshipException;

	/**
	 * Returns the user relationship with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship, or <code>null</code> if a user relationship with the primary key could not be found
	 */
	public UserRelationship fetchByPrimaryKey(
		UserRelationshipPK userRelationshipPK);

	/**
	 * Returns all the user relationships.
	 *
	 * @return the user relationships
	 */
	public java.util.List<UserRelationship> findAll();

	/**
	 * Returns a range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of user relationships
	 */
	public java.util.List<UserRelationship> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user relationships
	 */
	public java.util.List<UserRelationship> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user relationships
	 */
	public java.util.List<UserRelationship> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserRelationship>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user relationships from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user relationships.
	 *
	 * @return the number of user relationships
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}