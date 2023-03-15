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

package com.weprode.nero.preference.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.preference.exception.NoSuchUserPropertiesException;
import com.weprode.nero.preference.model.UserProperties;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user properties service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserPropertiesUtil
 * @generated
 */
@ProviderType
public interface UserPropertiesPersistence
	extends BasePersistence<UserProperties> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserPropertiesUtil} to access the user properties persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @return the matching user propertieses
	 */
	public java.util.List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount);

	/**
	 * Returns a range of all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @return the range of matching user propertieses
	 */
	public java.util.List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount, int start, int end);

	/**
	 * Returns an ordered range of all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user propertieses
	 */
	public java.util.List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProperties>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user propertieses
	 */
	public java.util.List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProperties>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user properties
	 * @throws NoSuchUserPropertiesException if a matching user properties could not be found
	 */
	public UserProperties findByetabId_manualAccount_First(
			long etabId, boolean manualAccount,
			com.liferay.portal.kernel.util.OrderByComparator<UserProperties>
				orderByComparator)
		throws NoSuchUserPropertiesException;

	/**
	 * Returns the first user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user properties, or <code>null</code> if a matching user properties could not be found
	 */
	public UserProperties fetchByetabId_manualAccount_First(
		long etabId, boolean manualAccount,
		com.liferay.portal.kernel.util.OrderByComparator<UserProperties>
			orderByComparator);

	/**
	 * Returns the last user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user properties
	 * @throws NoSuchUserPropertiesException if a matching user properties could not be found
	 */
	public UserProperties findByetabId_manualAccount_Last(
			long etabId, boolean manualAccount,
			com.liferay.portal.kernel.util.OrderByComparator<UserProperties>
				orderByComparator)
		throws NoSuchUserPropertiesException;

	/**
	 * Returns the last user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user properties, or <code>null</code> if a matching user properties could not be found
	 */
	public UserProperties fetchByetabId_manualAccount_Last(
		long etabId, boolean manualAccount,
		com.liferay.portal.kernel.util.OrderByComparator<UserProperties>
			orderByComparator);

	/**
	 * Returns the user propertieses before and after the current user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param userId the primary key of the current user properties
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user properties
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	public UserProperties[] findByetabId_manualAccount_PrevAndNext(
			long userId, long etabId, boolean manualAccount,
			com.liferay.portal.kernel.util.OrderByComparator<UserProperties>
				orderByComparator)
		throws NoSuchUserPropertiesException;

	/**
	 * Removes all the user propertieses where etabId = &#63; and manualAccount = &#63; from the database.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 */
	public void removeByetabId_manualAccount(
		long etabId, boolean manualAccount);

	/**
	 * Returns the number of user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @return the number of matching user propertieses
	 */
	public int countByetabId_manualAccount(long etabId, boolean manualAccount);

	/**
	 * Caches the user properties in the entity cache if it is enabled.
	 *
	 * @param userProperties the user properties
	 */
	public void cacheResult(UserProperties userProperties);

	/**
	 * Caches the user propertieses in the entity cache if it is enabled.
	 *
	 * @param userPropertieses the user propertieses
	 */
	public void cacheResult(java.util.List<UserProperties> userPropertieses);

	/**
	 * Creates a new user properties with the primary key. Does not add the user properties to the database.
	 *
	 * @param userId the primary key for the new user properties
	 * @return the new user properties
	 */
	public UserProperties create(long userId);

	/**
	 * Removes the user properties with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties that was removed
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	public UserProperties remove(long userId)
		throws NoSuchUserPropertiesException;

	public UserProperties updateImpl(UserProperties userProperties);

	/**
	 * Returns the user properties with the primary key or throws a <code>NoSuchUserPropertiesException</code> if it could not be found.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	public UserProperties findByPrimaryKey(long userId)
		throws NoSuchUserPropertiesException;

	/**
	 * Returns the user properties with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties, or <code>null</code> if a user properties with the primary key could not be found
	 */
	public UserProperties fetchByPrimaryKey(long userId);

	/**
	 * Returns all the user propertieses.
	 *
	 * @return the user propertieses
	 */
	public java.util.List<UserProperties> findAll();

	/**
	 * Returns a range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @return the range of user propertieses
	 */
	public java.util.List<UserProperties> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user propertieses
	 */
	public java.util.List<UserProperties> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProperties>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user propertieses
	 */
	public java.util.List<UserProperties> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProperties>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user propertieses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user propertieses.
	 *
	 * @return the number of user propertieses
	 */
	public int countAll();

}