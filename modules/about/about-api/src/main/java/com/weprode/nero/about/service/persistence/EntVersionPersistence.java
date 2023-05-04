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

package com.weprode.nero.about.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.about.exception.NoSuchEntVersionException;
import com.weprode.nero.about.model.EntVersion;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ent version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntVersionUtil
 * @generated
 */
@ProviderType
public interface EntVersionPersistence extends BasePersistence<EntVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntVersionUtil} to access the ent version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the ent version where isLast = &#63; or throws a <code>NoSuchEntVersionException</code> if it could not be found.
	 *
	 * @param isLast the is last
	 * @return the matching ent version
	 * @throws NoSuchEntVersionException if a matching ent version could not be found
	 */
	public EntVersion findByisLast(boolean isLast)
		throws NoSuchEntVersionException;

	/**
	 * Returns the ent version where isLast = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param isLast the is last
	 * @return the matching ent version, or <code>null</code> if a matching ent version could not be found
	 */
	public EntVersion fetchByisLast(boolean isLast);

	/**
	 * Returns the ent version where isLast = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param isLast the is last
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ent version, or <code>null</code> if a matching ent version could not be found
	 */
	public EntVersion fetchByisLast(boolean isLast, boolean useFinderCache);

	/**
	 * Removes the ent version where isLast = &#63; from the database.
	 *
	 * @param isLast the is last
	 * @return the ent version that was removed
	 */
	public EntVersion removeByisLast(boolean isLast)
		throws NoSuchEntVersionException;

	/**
	 * Returns the number of ent versions where isLast = &#63;.
	 *
	 * @param isLast the is last
	 * @return the number of matching ent versions
	 */
	public int countByisLast(boolean isLast);

	/**
	 * Caches the ent version in the entity cache if it is enabled.
	 *
	 * @param entVersion the ent version
	 */
	public void cacheResult(EntVersion entVersion);

	/**
	 * Caches the ent versions in the entity cache if it is enabled.
	 *
	 * @param entVersions the ent versions
	 */
	public void cacheResult(java.util.List<EntVersion> entVersions);

	/**
	 * Creates a new ent version with the primary key. Does not add the ent version to the database.
	 *
	 * @param entVersionId the primary key for the new ent version
	 * @return the new ent version
	 */
	public EntVersion create(long entVersionId);

	/**
	 * Removes the ent version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version that was removed
	 * @throws NoSuchEntVersionException if a ent version with the primary key could not be found
	 */
	public EntVersion remove(long entVersionId)
		throws NoSuchEntVersionException;

	public EntVersion updateImpl(EntVersion entVersion);

	/**
	 * Returns the ent version with the primary key or throws a <code>NoSuchEntVersionException</code> if it could not be found.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version
	 * @throws NoSuchEntVersionException if a ent version with the primary key could not be found
	 */
	public EntVersion findByPrimaryKey(long entVersionId)
		throws NoSuchEntVersionException;

	/**
	 * Returns the ent version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version, or <code>null</code> if a ent version with the primary key could not be found
	 */
	public EntVersion fetchByPrimaryKey(long entVersionId);

	/**
	 * Returns all the ent versions.
	 *
	 * @return the ent versions
	 */
	public java.util.List<EntVersion> findAll();

	/**
	 * Returns a range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @return the range of ent versions
	 */
	public java.util.List<EntVersion> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ent versions
	 */
	public java.util.List<EntVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntVersion>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ent versions
	 */
	public java.util.List<EntVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntVersion>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ent versions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of ent versions.
	 *
	 * @return the number of ent versions
	 */
	public int countAll();

}