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

package com.weprode.nero.application.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.application.exception.NoSuchDefaultRoleException;
import com.weprode.nero.application.model.DefaultRole;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the default role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DefaultRoleUtil
 * @generated
 */
@ProviderType
public interface DefaultRolePersistence extends BasePersistence<DefaultRole> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DefaultRoleUtil} to access the default role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the default roles where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching default roles
	 */
	public java.util.List<DefaultRole> findByapplicationId(long applicationId);

	/**
	 * Returns a range of all the default roles where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @return the range of matching default roles
	 */
	public java.util.List<DefaultRole> findByapplicationId(
		long applicationId, int start, int end);

	/**
	 * Returns an ordered range of all the default roles where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching default roles
	 */
	public java.util.List<DefaultRole> findByapplicationId(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DefaultRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the default roles where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching default roles
	 */
	public java.util.List<DefaultRole> findByapplicationId(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DefaultRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching default role
	 * @throws NoSuchDefaultRoleException if a matching default role could not be found
	 */
	public DefaultRole findByapplicationId_First(
			long applicationId,
			com.liferay.portal.kernel.util.OrderByComparator<DefaultRole>
				orderByComparator)
		throws NoSuchDefaultRoleException;

	/**
	 * Returns the first default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching default role, or <code>null</code> if a matching default role could not be found
	 */
	public DefaultRole fetchByapplicationId_First(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator<DefaultRole>
			orderByComparator);

	/**
	 * Returns the last default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching default role
	 * @throws NoSuchDefaultRoleException if a matching default role could not be found
	 */
	public DefaultRole findByapplicationId_Last(
			long applicationId,
			com.liferay.portal.kernel.util.OrderByComparator<DefaultRole>
				orderByComparator)
		throws NoSuchDefaultRoleException;

	/**
	 * Returns the last default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching default role, or <code>null</code> if a matching default role could not be found
	 */
	public DefaultRole fetchByapplicationId_Last(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator<DefaultRole>
			orderByComparator);

	/**
	 * Returns the default roles before and after the current default role in the ordered set where applicationId = &#63;.
	 *
	 * @param defaultRoleId the primary key of the current default role
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next default role
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	public DefaultRole[] findByapplicationId_PrevAndNext(
			long defaultRoleId, long applicationId,
			com.liferay.portal.kernel.util.OrderByComparator<DefaultRole>
				orderByComparator)
		throws NoSuchDefaultRoleException;

	/**
	 * Removes all the default roles where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 */
	public void removeByapplicationId(long applicationId);

	/**
	 * Returns the number of default roles where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching default roles
	 */
	public int countByapplicationId(long applicationId);

	/**
	 * Returns the default role where applicationId = &#63; and roleId = &#63; or throws a <code>NoSuchDefaultRoleException</code> if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the matching default role
	 * @throws NoSuchDefaultRoleException if a matching default role could not be found
	 */
	public DefaultRole findByapplicationId_roleId(
			long applicationId, long roleId)
		throws NoSuchDefaultRoleException;

	/**
	 * Returns the default role where applicationId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the matching default role, or <code>null</code> if a matching default role could not be found
	 */
	public DefaultRole fetchByapplicationId_roleId(
		long applicationId, long roleId);

	/**
	 * Returns the default role where applicationId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching default role, or <code>null</code> if a matching default role could not be found
	 */
	public DefaultRole fetchByapplicationId_roleId(
		long applicationId, long roleId, boolean useFinderCache);

	/**
	 * Removes the default role where applicationId = &#63; and roleId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the default role that was removed
	 */
	public DefaultRole removeByapplicationId_roleId(
			long applicationId, long roleId)
		throws NoSuchDefaultRoleException;

	/**
	 * Returns the number of default roles where applicationId = &#63; and roleId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the number of matching default roles
	 */
	public int countByapplicationId_roleId(long applicationId, long roleId);

	/**
	 * Caches the default role in the entity cache if it is enabled.
	 *
	 * @param defaultRole the default role
	 */
	public void cacheResult(DefaultRole defaultRole);

	/**
	 * Caches the default roles in the entity cache if it is enabled.
	 *
	 * @param defaultRoles the default roles
	 */
	public void cacheResult(java.util.List<DefaultRole> defaultRoles);

	/**
	 * Creates a new default role with the primary key. Does not add the default role to the database.
	 *
	 * @param defaultRoleId the primary key for the new default role
	 * @return the new default role
	 */
	public DefaultRole create(long defaultRoleId);

	/**
	 * Removes the default role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role that was removed
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	public DefaultRole remove(long defaultRoleId)
		throws NoSuchDefaultRoleException;

	public DefaultRole updateImpl(DefaultRole defaultRole);

	/**
	 * Returns the default role with the primary key or throws a <code>NoSuchDefaultRoleException</code> if it could not be found.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	public DefaultRole findByPrimaryKey(long defaultRoleId)
		throws NoSuchDefaultRoleException;

	/**
	 * Returns the default role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role, or <code>null</code> if a default role with the primary key could not be found
	 */
	public DefaultRole fetchByPrimaryKey(long defaultRoleId);

	/**
	 * Returns all the default roles.
	 *
	 * @return the default roles
	 */
	public java.util.List<DefaultRole> findAll();

	/**
	 * Returns a range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @return the range of default roles
	 */
	public java.util.List<DefaultRole> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of default roles
	 */
	public java.util.List<DefaultRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DefaultRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of default roles
	 */
	public java.util.List<DefaultRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DefaultRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the default roles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of default roles.
	 *
	 * @return the number of default roles
	 */
	public int countAll();

}