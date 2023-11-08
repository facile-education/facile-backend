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

package com.weprode.facile.user.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.user.exception.NoSuchContactException;
import com.weprode.facile.user.model.UserContact;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user contact service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserContactUtil
 * @generated
 */
@ProviderType
public interface UserContactPersistence extends BasePersistence<UserContact> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserContactUtil} to access the user contact persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the user contact where userId = &#63; or throws a <code>NoSuchContactException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user contact
	 * @throws NoSuchContactException if a matching user contact could not be found
	 */
	public UserContact findByuserId(long userId) throws NoSuchContactException;

	/**
	 * Returns the user contact where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user contact, or <code>null</code> if a matching user contact could not be found
	 */
	public UserContact fetchByuserId(long userId);

	/**
	 * Returns the user contact where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user contact, or <code>null</code> if a matching user contact could not be found
	 */
	public UserContact fetchByuserId(long userId, boolean useFinderCache);

	/**
	 * Removes the user contact where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user contact that was removed
	 */
	public UserContact removeByuserId(long userId)
		throws NoSuchContactException;

	/**
	 * Returns the number of user contacts where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user contacts
	 */
	public int countByuserId(long userId);

	/**
	 * Caches the user contact in the entity cache if it is enabled.
	 *
	 * @param userContact the user contact
	 */
	public void cacheResult(UserContact userContact);

	/**
	 * Caches the user contacts in the entity cache if it is enabled.
	 *
	 * @param userContacts the user contacts
	 */
	public void cacheResult(java.util.List<UserContact> userContacts);

	/**
	 * Creates a new user contact with the primary key. Does not add the user contact to the database.
	 *
	 * @param contactId the primary key for the new user contact
	 * @return the new user contact
	 */
	public UserContact create(long contactId);

	/**
	 * Removes the user contact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact that was removed
	 * @throws NoSuchContactException if a user contact with the primary key could not be found
	 */
	public UserContact remove(long contactId) throws NoSuchContactException;

	public UserContact updateImpl(UserContact userContact);

	/**
	 * Returns the user contact with the primary key or throws a <code>NoSuchContactException</code> if it could not be found.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact
	 * @throws NoSuchContactException if a user contact with the primary key could not be found
	 */
	public UserContact findByPrimaryKey(long contactId)
		throws NoSuchContactException;

	/**
	 * Returns the user contact with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact, or <code>null</code> if a user contact with the primary key could not be found
	 */
	public UserContact fetchByPrimaryKey(long contactId);

	/**
	 * Returns all the user contacts.
	 *
	 * @return the user contacts
	 */
	public java.util.List<UserContact> findAll();

	/**
	 * Returns a range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @return the range of user contacts
	 */
	public java.util.List<UserContact> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user contacts
	 */
	public java.util.List<UserContact> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserContact>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user contacts
	 */
	public java.util.List<UserContact> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserContact>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user contacts from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user contacts.
	 *
	 * @return the number of user contacts
	 */
	public int countAll();

}