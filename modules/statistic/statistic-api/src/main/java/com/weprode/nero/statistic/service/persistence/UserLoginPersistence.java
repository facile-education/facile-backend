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

package com.weprode.nero.statistic.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.statistic.exception.NoSuchUserLoginException;
import com.weprode.nero.statistic.model.UserLogin;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user login service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLoginUtil
 * @generated
 */
@ProviderType
public interface UserLoginPersistence extends BasePersistence<UserLogin> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserLoginUtil} to access the user login persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the user logins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching user logins
	 */
	public java.util.List<UserLogin> findByuserId(long userId);

	/**
	 * Returns a range of all the user logins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of matching user logins
	 */
	public java.util.List<UserLogin> findByuserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the user logins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user logins
	 */
	public java.util.List<UserLogin> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user logins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user logins
	 */
	public java.util.List<UserLogin> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	public UserLogin findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
				orderByComparator)
		throws NoSuchUserLoginException;

	/**
	 * Returns the first user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login, or <code>null</code> if a matching user login could not be found
	 */
	public UserLogin fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator);

	/**
	 * Returns the last user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	public UserLogin findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
				orderByComparator)
		throws NoSuchUserLoginException;

	/**
	 * Returns the last user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login, or <code>null</code> if a matching user login could not be found
	 */
	public UserLogin fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator);

	/**
	 * Returns the user logins before and after the current user login in the ordered set where userId = &#63;.
	 *
	 * @param userLoginId the primary key of the current user login
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	public UserLogin[] findByuserId_PrevAndNext(
			long userLoginId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
				orderByComparator)
		throws NoSuchUserLoginException;

	/**
	 * Removes all the user logins where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of user logins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user logins
	 */
	public int countByuserId(long userId);

	/**
	 * Returns all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @return the matching user logins
	 */
	public java.util.List<UserLogin> findByschoolId_role(
		long schoolId, int role);

	/**
	 * Returns a range of all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of matching user logins
	 */
	public java.util.List<UserLogin> findByschoolId_role(
		long schoolId, int role, int start, int end);

	/**
	 * Returns an ordered range of all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user logins
	 */
	public java.util.List<UserLogin> findByschoolId_role(
		long schoolId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user logins
	 */
	public java.util.List<UserLogin> findByschoolId_role(
		long schoolId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	public UserLogin findByschoolId_role_First(
			long schoolId, int role,
			com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
				orderByComparator)
		throws NoSuchUserLoginException;

	/**
	 * Returns the first user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login, or <code>null</code> if a matching user login could not be found
	 */
	public UserLogin fetchByschoolId_role_First(
		long schoolId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator);

	/**
	 * Returns the last user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	public UserLogin findByschoolId_role_Last(
			long schoolId, int role,
			com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
				orderByComparator)
		throws NoSuchUserLoginException;

	/**
	 * Returns the last user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login, or <code>null</code> if a matching user login could not be found
	 */
	public UserLogin fetchByschoolId_role_Last(
		long schoolId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator);

	/**
	 * Returns the user logins before and after the current user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param userLoginId the primary key of the current user login
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	public UserLogin[] findByschoolId_role_PrevAndNext(
			long userLoginId, long schoolId, int role,
			com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
				orderByComparator)
		throws NoSuchUserLoginException;

	/**
	 * Removes all the user logins where schoolId = &#63; and role = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 */
	public void removeByschoolId_role(long schoolId, int role);

	/**
	 * Returns the number of user logins where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @return the number of matching user logins
	 */
	public int countByschoolId_role(long schoolId, int role);

	/**
	 * Caches the user login in the entity cache if it is enabled.
	 *
	 * @param userLogin the user login
	 */
	public void cacheResult(UserLogin userLogin);

	/**
	 * Caches the user logins in the entity cache if it is enabled.
	 *
	 * @param userLogins the user logins
	 */
	public void cacheResult(java.util.List<UserLogin> userLogins);

	/**
	 * Creates a new user login with the primary key. Does not add the user login to the database.
	 *
	 * @param userLoginId the primary key for the new user login
	 * @return the new user login
	 */
	public UserLogin create(long userLoginId);

	/**
	 * Removes the user login with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login that was removed
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	public UserLogin remove(long userLoginId) throws NoSuchUserLoginException;

	public UserLogin updateImpl(UserLogin userLogin);

	/**
	 * Returns the user login with the primary key or throws a <code>NoSuchUserLoginException</code> if it could not be found.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	public UserLogin findByPrimaryKey(long userLoginId)
		throws NoSuchUserLoginException;

	/**
	 * Returns the user login with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login, or <code>null</code> if a user login with the primary key could not be found
	 */
	public UserLogin fetchByPrimaryKey(long userLoginId);

	/**
	 * Returns all the user logins.
	 *
	 * @return the user logins
	 */
	public java.util.List<UserLogin> findAll();

	/**
	 * Returns a range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of user logins
	 */
	public java.util.List<UserLogin> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user logins
	 */
	public java.util.List<UserLogin> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user logins
	 */
	public java.util.List<UserLogin> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user logins from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user logins.
	 *
	 * @return the number of user logins
	 */
	public int countAll();

}