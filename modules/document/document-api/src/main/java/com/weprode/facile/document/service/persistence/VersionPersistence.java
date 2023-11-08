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

package com.weprode.facile.document.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.document.exception.NoSuchVersionException;
import com.weprode.facile.document.model.Version;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VersionUtil
 * @generated
 */
@ProviderType
public interface VersionPersistence extends BasePersistence<Version> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VersionUtil} to access the version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the version where dlFileEntryId = &#63; and versionNumber = &#63; or throws a <code>NoSuchVersionException</code> if it could not be found.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the matching version
	 * @throws NoSuchVersionException if a matching version could not be found
	 */
	public Version findBydlFileEntryId_versionNumber(
			long dlFileEntryId, double versionNumber)
		throws NoSuchVersionException;

	/**
	 * Returns the version where dlFileEntryId = &#63; and versionNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the matching version, or <code>null</code> if a matching version could not be found
	 */
	public Version fetchBydlFileEntryId_versionNumber(
		long dlFileEntryId, double versionNumber);

	/**
	 * Returns the version where dlFileEntryId = &#63; and versionNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching version, or <code>null</code> if a matching version could not be found
	 */
	public Version fetchBydlFileEntryId_versionNumber(
		long dlFileEntryId, double versionNumber, boolean useFinderCache);

	/**
	 * Removes the version where dlFileEntryId = &#63; and versionNumber = &#63; from the database.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the version that was removed
	 */
	public Version removeBydlFileEntryId_versionNumber(
			long dlFileEntryId, double versionNumber)
		throws NoSuchVersionException;

	/**
	 * Returns the number of versions where dlFileEntryId = &#63; and versionNumber = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the number of matching versions
	 */
	public int countBydlFileEntryId_versionNumber(
		long dlFileEntryId, double versionNumber);

	/**
	 * Returns all the versions where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @return the matching versions
	 */
	public java.util.List<Version> findBydlFileEntryId(long dlFileEntryId);

	/**
	 * Returns a range of all the versions where dlFileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @return the range of matching versions
	 */
	public java.util.List<Version> findBydlFileEntryId(
		long dlFileEntryId, int start, int end);

	/**
	 * Returns an ordered range of all the versions where dlFileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching versions
	 */
	public java.util.List<Version> findBydlFileEntryId(
		long dlFileEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Version>
			orderByComparator);

	/**
	 * Returns an ordered range of all the versions where dlFileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching versions
	 */
	public java.util.List<Version> findBydlFileEntryId(
		long dlFileEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Version>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching version
	 * @throws NoSuchVersionException if a matching version could not be found
	 */
	public Version findBydlFileEntryId_First(
			long dlFileEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<Version>
				orderByComparator)
		throws NoSuchVersionException;

	/**
	 * Returns the first version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching version, or <code>null</code> if a matching version could not be found
	 */
	public Version fetchBydlFileEntryId_First(
		long dlFileEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<Version>
			orderByComparator);

	/**
	 * Returns the last version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching version
	 * @throws NoSuchVersionException if a matching version could not be found
	 */
	public Version findBydlFileEntryId_Last(
			long dlFileEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<Version>
				orderByComparator)
		throws NoSuchVersionException;

	/**
	 * Returns the last version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching version, or <code>null</code> if a matching version could not be found
	 */
	public Version fetchBydlFileEntryId_Last(
		long dlFileEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<Version>
			orderByComparator);

	/**
	 * Returns the versions before and after the current version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param fileVersionId the primary key of the current version
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next version
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	public Version[] findBydlFileEntryId_PrevAndNext(
			long fileVersionId, long dlFileEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<Version>
				orderByComparator)
		throws NoSuchVersionException;

	/**
	 * Removes all the versions where dlFileEntryId = &#63; from the database.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 */
	public void removeBydlFileEntryId(long dlFileEntryId);

	/**
	 * Returns the number of versions where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @return the number of matching versions
	 */
	public int countBydlFileEntryId(long dlFileEntryId);

	/**
	 * Caches the version in the entity cache if it is enabled.
	 *
	 * @param version the version
	 */
	public void cacheResult(Version version);

	/**
	 * Caches the versions in the entity cache if it is enabled.
	 *
	 * @param versions the versions
	 */
	public void cacheResult(java.util.List<Version> versions);

	/**
	 * Creates a new version with the primary key. Does not add the version to the database.
	 *
	 * @param fileVersionId the primary key for the new version
	 * @return the new version
	 */
	public Version create(long fileVersionId);

	/**
	 * Removes the version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version that was removed
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	public Version remove(long fileVersionId) throws NoSuchVersionException;

	public Version updateImpl(Version version);

	/**
	 * Returns the version with the primary key or throws a <code>NoSuchVersionException</code> if it could not be found.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	public Version findByPrimaryKey(long fileVersionId)
		throws NoSuchVersionException;

	/**
	 * Returns the version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version, or <code>null</code> if a version with the primary key could not be found
	 */
	public Version fetchByPrimaryKey(long fileVersionId);

	/**
	 * Returns all the versions.
	 *
	 * @return the versions
	 */
	public java.util.List<Version> findAll();

	/**
	 * Returns a range of all the versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @return the range of versions
	 */
	public java.util.List<Version> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of versions
	 */
	public java.util.List<Version> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Version>
			orderByComparator);

	/**
	 * Returns an ordered range of all the versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of versions
	 * @param end the upper bound of the range of versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of versions
	 */
	public java.util.List<Version> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Version>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the versions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of versions.
	 *
	 * @return the number of versions
	 */
	public int countAll();

}