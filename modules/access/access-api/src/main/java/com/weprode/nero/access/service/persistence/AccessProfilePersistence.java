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

package com.weprode.nero.access.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.access.exception.NoSuchProfileException;
import com.weprode.nero.access.model.AccessProfile;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the access profile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccessProfileUtil
 * @generated
 */
@ProviderType
public interface AccessProfilePersistence
	extends BasePersistence<AccessProfile> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccessProfileUtil} to access the access profile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the access profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching access profiles
	 */
	public java.util.List<AccessProfile> findByUuid(String uuid);

	/**
	 * Returns a range of all the access profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of matching access profiles
	 */
	public java.util.List<AccessProfile> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the access profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching access profiles
	 */
	public java.util.List<AccessProfile> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator);

	/**
	 * Returns an ordered range of all the access profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching access profiles
	 */
	public java.util.List<AccessProfile> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	public AccessProfile findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
				orderByComparator)
		throws NoSuchProfileException;

	/**
	 * Returns the first access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	public AccessProfile fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator);

	/**
	 * Returns the last access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	public AccessProfile findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
				orderByComparator)
		throws NoSuchProfileException;

	/**
	 * Returns the last access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	public AccessProfile fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator);

	/**
	 * Returns the access profiles before and after the current access profile in the ordered set where uuid = &#63;.
	 *
	 * @param accessProfilePK the primary key of the current access profile
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	public AccessProfile[] findByUuid_PrevAndNext(
			AccessProfilePK accessProfilePK, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
				orderByComparator)
		throws NoSuchProfileException;

	/**
	 * Removes all the access profiles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of access profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching access profiles
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the access profiles where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @return the matching access profiles
	 */
	public java.util.List<AccessProfile> findByaccessId(long accessId);

	/**
	 * Returns a range of all the access profiles where accessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param accessId the access ID
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of matching access profiles
	 */
	public java.util.List<AccessProfile> findByaccessId(
		long accessId, int start, int end);

	/**
	 * Returns an ordered range of all the access profiles where accessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param accessId the access ID
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching access profiles
	 */
	public java.util.List<AccessProfile> findByaccessId(
		long accessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator);

	/**
	 * Returns an ordered range of all the access profiles where accessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param accessId the access ID
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching access profiles
	 */
	public java.util.List<AccessProfile> findByaccessId(
		long accessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	public AccessProfile findByaccessId_First(
			long accessId,
			com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
				orderByComparator)
		throws NoSuchProfileException;

	/**
	 * Returns the first access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	public AccessProfile fetchByaccessId_First(
		long accessId,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator);

	/**
	 * Returns the last access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	public AccessProfile findByaccessId_Last(
			long accessId,
			com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
				orderByComparator)
		throws NoSuchProfileException;

	/**
	 * Returns the last access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	public AccessProfile fetchByaccessId_Last(
		long accessId,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator);

	/**
	 * Returns the access profiles before and after the current access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessProfilePK the primary key of the current access profile
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	public AccessProfile[] findByaccessId_PrevAndNext(
			AccessProfilePK accessProfilePK, long accessId,
			com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
				orderByComparator)
		throws NoSuchProfileException;

	/**
	 * Removes all the access profiles where accessId = &#63; from the database.
	 *
	 * @param accessId the access ID
	 */
	public void removeByaccessId(long accessId);

	/**
	 * Returns the number of access profiles where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @return the number of matching access profiles
	 */
	public int countByaccessId(long accessId);

	/**
	 * Caches the access profile in the entity cache if it is enabled.
	 *
	 * @param accessProfile the access profile
	 */
	public void cacheResult(AccessProfile accessProfile);

	/**
	 * Caches the access profiles in the entity cache if it is enabled.
	 *
	 * @param accessProfiles the access profiles
	 */
	public void cacheResult(java.util.List<AccessProfile> accessProfiles);

	/**
	 * Creates a new access profile with the primary key. Does not add the access profile to the database.
	 *
	 * @param accessProfilePK the primary key for the new access profile
	 * @return the new access profile
	 */
	public AccessProfile create(AccessProfilePK accessProfilePK);

	/**
	 * Removes the access profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile that was removed
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	public AccessProfile remove(AccessProfilePK accessProfilePK)
		throws NoSuchProfileException;

	public AccessProfile updateImpl(AccessProfile accessProfile);

	/**
	 * Returns the access profile with the primary key or throws a <code>NoSuchProfileException</code> if it could not be found.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	public AccessProfile findByPrimaryKey(AccessProfilePK accessProfilePK)
		throws NoSuchProfileException;

	/**
	 * Returns the access profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile, or <code>null</code> if a access profile with the primary key could not be found
	 */
	public AccessProfile fetchByPrimaryKey(AccessProfilePK accessProfilePK);

	/**
	 * Returns all the access profiles.
	 *
	 * @return the access profiles
	 */
	public java.util.List<AccessProfile> findAll();

	/**
	 * Returns a range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of access profiles
	 */
	public java.util.List<AccessProfile> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of access profiles
	 */
	public java.util.List<AccessProfile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator);

	/**
	 * Returns an ordered range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of access profiles
	 */
	public java.util.List<AccessProfile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessProfile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the access profiles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of access profiles.
	 *
	 * @return the number of access profiles
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}