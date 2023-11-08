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

package com.weprode.facile.eel.synchronization.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.eel.synchronization.exception.NoSuchParentSynchroException;
import com.weprode.facile.eel.synchronization.model.ParentSynchro;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the parent synchro service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ParentSynchroUtil
 * @generated
 */
@ProviderType
public interface ParentSynchroPersistence
	extends BasePersistence<ParentSynchro> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ParentSynchroUtil} to access the parent synchro persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the parent synchro where schoolId = &#63; or throws a <code>NoSuchParentSynchroException</code> if it could not be found.
	 *
	 * @param schoolId the school ID
	 * @return the matching parent synchro
	 * @throws NoSuchParentSynchroException if a matching parent synchro could not be found
	 */
	public ParentSynchro findByschoolId(long schoolId)
		throws NoSuchParentSynchroException;

	/**
	 * Returns the parent synchro where schoolId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param schoolId the school ID
	 * @return the matching parent synchro, or <code>null</code> if a matching parent synchro could not be found
	 */
	public ParentSynchro fetchByschoolId(long schoolId);

	/**
	 * Returns the parent synchro where schoolId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param schoolId the school ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching parent synchro, or <code>null</code> if a matching parent synchro could not be found
	 */
	public ParentSynchro fetchByschoolId(long schoolId, boolean useFinderCache);

	/**
	 * Removes the parent synchro where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @return the parent synchro that was removed
	 */
	public ParentSynchro removeByschoolId(long schoolId)
		throws NoSuchParentSynchroException;

	/**
	 * Returns the number of parent synchros where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching parent synchros
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Caches the parent synchro in the entity cache if it is enabled.
	 *
	 * @param parentSynchro the parent synchro
	 */
	public void cacheResult(ParentSynchro parentSynchro);

	/**
	 * Caches the parent synchros in the entity cache if it is enabled.
	 *
	 * @param parentSynchros the parent synchros
	 */
	public void cacheResult(java.util.List<ParentSynchro> parentSynchros);

	/**
	 * Creates a new parent synchro with the primary key. Does not add the parent synchro to the database.
	 *
	 * @param schoolId the primary key for the new parent synchro
	 * @return the new parent synchro
	 */
	public ParentSynchro create(long schoolId);

	/**
	 * Removes the parent synchro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro that was removed
	 * @throws NoSuchParentSynchroException if a parent synchro with the primary key could not be found
	 */
	public ParentSynchro remove(long schoolId)
		throws NoSuchParentSynchroException;

	public ParentSynchro updateImpl(ParentSynchro parentSynchro);

	/**
	 * Returns the parent synchro with the primary key or throws a <code>NoSuchParentSynchroException</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro
	 * @throws NoSuchParentSynchroException if a parent synchro with the primary key could not be found
	 */
	public ParentSynchro findByPrimaryKey(long schoolId)
		throws NoSuchParentSynchroException;

	/**
	 * Returns the parent synchro with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro, or <code>null</code> if a parent synchro with the primary key could not be found
	 */
	public ParentSynchro fetchByPrimaryKey(long schoolId);

	/**
	 * Returns all the parent synchros.
	 *
	 * @return the parent synchros
	 */
	public java.util.List<ParentSynchro> findAll();

	/**
	 * Returns a range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @return the range of parent synchros
	 */
	public java.util.List<ParentSynchro> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of parent synchros
	 */
	public java.util.List<ParentSynchro> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ParentSynchro>
			orderByComparator);

	/**
	 * Returns an ordered range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of parent synchros
	 */
	public java.util.List<ParentSynchro> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ParentSynchro>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the parent synchros from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of parent synchros.
	 *
	 * @return the number of parent synchros
	 */
	public int countAll();

}