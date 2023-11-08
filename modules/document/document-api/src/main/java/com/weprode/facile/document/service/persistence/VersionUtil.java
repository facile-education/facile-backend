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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.document.model.Version;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the version service. This utility wraps <code>com.weprode.facile.document.service.persistence.impl.VersionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VersionPersistence
 * @generated
 */
public class VersionUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Version version) {
		getPersistence().clearCache(version);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Version> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Version> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Version> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Version> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Version> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Version update(Version version) {
		return getPersistence().update(version);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Version update(
		Version version, ServiceContext serviceContext) {

		return getPersistence().update(version, serviceContext);
	}

	/**
	 * Returns the version where dlFileEntryId = &#63; and versionNumber = &#63; or throws a <code>NoSuchVersionException</code> if it could not be found.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the matching version
	 * @throws NoSuchVersionException if a matching version could not be found
	 */
	public static Version findBydlFileEntryId_versionNumber(
			long dlFileEntryId, double versionNumber)
		throws com.weprode.facile.document.exception.NoSuchVersionException {

		return getPersistence().findBydlFileEntryId_versionNumber(
			dlFileEntryId, versionNumber);
	}

	/**
	 * Returns the version where dlFileEntryId = &#63; and versionNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the matching version, or <code>null</code> if a matching version could not be found
	 */
	public static Version fetchBydlFileEntryId_versionNumber(
		long dlFileEntryId, double versionNumber) {

		return getPersistence().fetchBydlFileEntryId_versionNumber(
			dlFileEntryId, versionNumber);
	}

	/**
	 * Returns the version where dlFileEntryId = &#63; and versionNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching version, or <code>null</code> if a matching version could not be found
	 */
	public static Version fetchBydlFileEntryId_versionNumber(
		long dlFileEntryId, double versionNumber, boolean useFinderCache) {

		return getPersistence().fetchBydlFileEntryId_versionNumber(
			dlFileEntryId, versionNumber, useFinderCache);
	}

	/**
	 * Removes the version where dlFileEntryId = &#63; and versionNumber = &#63; from the database.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the version that was removed
	 */
	public static Version removeBydlFileEntryId_versionNumber(
			long dlFileEntryId, double versionNumber)
		throws com.weprode.facile.document.exception.NoSuchVersionException {

		return getPersistence().removeBydlFileEntryId_versionNumber(
			dlFileEntryId, versionNumber);
	}

	/**
	 * Returns the number of versions where dlFileEntryId = &#63; and versionNumber = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param versionNumber the version number
	 * @return the number of matching versions
	 */
	public static int countBydlFileEntryId_versionNumber(
		long dlFileEntryId, double versionNumber) {

		return getPersistence().countBydlFileEntryId_versionNumber(
			dlFileEntryId, versionNumber);
	}

	/**
	 * Returns all the versions where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @return the matching versions
	 */
	public static List<Version> findBydlFileEntryId(long dlFileEntryId) {
		return getPersistence().findBydlFileEntryId(dlFileEntryId);
	}

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
	public static List<Version> findBydlFileEntryId(
		long dlFileEntryId, int start, int end) {

		return getPersistence().findBydlFileEntryId(dlFileEntryId, start, end);
	}

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
	public static List<Version> findBydlFileEntryId(
		long dlFileEntryId, int start, int end,
		OrderByComparator<Version> orderByComparator) {

		return getPersistence().findBydlFileEntryId(
			dlFileEntryId, start, end, orderByComparator);
	}

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
	public static List<Version> findBydlFileEntryId(
		long dlFileEntryId, int start, int end,
		OrderByComparator<Version> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBydlFileEntryId(
			dlFileEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching version
	 * @throws NoSuchVersionException if a matching version could not be found
	 */
	public static Version findBydlFileEntryId_First(
			long dlFileEntryId, OrderByComparator<Version> orderByComparator)
		throws com.weprode.facile.document.exception.NoSuchVersionException {

		return getPersistence().findBydlFileEntryId_First(
			dlFileEntryId, orderByComparator);
	}

	/**
	 * Returns the first version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching version, or <code>null</code> if a matching version could not be found
	 */
	public static Version fetchBydlFileEntryId_First(
		long dlFileEntryId, OrderByComparator<Version> orderByComparator) {

		return getPersistence().fetchBydlFileEntryId_First(
			dlFileEntryId, orderByComparator);
	}

	/**
	 * Returns the last version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching version
	 * @throws NoSuchVersionException if a matching version could not be found
	 */
	public static Version findBydlFileEntryId_Last(
			long dlFileEntryId, OrderByComparator<Version> orderByComparator)
		throws com.weprode.facile.document.exception.NoSuchVersionException {

		return getPersistence().findBydlFileEntryId_Last(
			dlFileEntryId, orderByComparator);
	}

	/**
	 * Returns the last version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching version, or <code>null</code> if a matching version could not be found
	 */
	public static Version fetchBydlFileEntryId_Last(
		long dlFileEntryId, OrderByComparator<Version> orderByComparator) {

		return getPersistence().fetchBydlFileEntryId_Last(
			dlFileEntryId, orderByComparator);
	}

	/**
	 * Returns the versions before and after the current version in the ordered set where dlFileEntryId = &#63;.
	 *
	 * @param fileVersionId the primary key of the current version
	 * @param dlFileEntryId the dl file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next version
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	public static Version[] findBydlFileEntryId_PrevAndNext(
			long fileVersionId, long dlFileEntryId,
			OrderByComparator<Version> orderByComparator)
		throws com.weprode.facile.document.exception.NoSuchVersionException {

		return getPersistence().findBydlFileEntryId_PrevAndNext(
			fileVersionId, dlFileEntryId, orderByComparator);
	}

	/**
	 * Removes all the versions where dlFileEntryId = &#63; from the database.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 */
	public static void removeBydlFileEntryId(long dlFileEntryId) {
		getPersistence().removeBydlFileEntryId(dlFileEntryId);
	}

	/**
	 * Returns the number of versions where dlFileEntryId = &#63;.
	 *
	 * @param dlFileEntryId the dl file entry ID
	 * @return the number of matching versions
	 */
	public static int countBydlFileEntryId(long dlFileEntryId) {
		return getPersistence().countBydlFileEntryId(dlFileEntryId);
	}

	/**
	 * Caches the version in the entity cache if it is enabled.
	 *
	 * @param version the version
	 */
	public static void cacheResult(Version version) {
		getPersistence().cacheResult(version);
	}

	/**
	 * Caches the versions in the entity cache if it is enabled.
	 *
	 * @param versions the versions
	 */
	public static void cacheResult(List<Version> versions) {
		getPersistence().cacheResult(versions);
	}

	/**
	 * Creates a new version with the primary key. Does not add the version to the database.
	 *
	 * @param fileVersionId the primary key for the new version
	 * @return the new version
	 */
	public static Version create(long fileVersionId) {
		return getPersistence().create(fileVersionId);
	}

	/**
	 * Removes the version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version that was removed
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	public static Version remove(long fileVersionId)
		throws com.weprode.facile.document.exception.NoSuchVersionException {

		return getPersistence().remove(fileVersionId);
	}

	public static Version updateImpl(Version version) {
		return getPersistence().updateImpl(version);
	}

	/**
	 * Returns the version with the primary key or throws a <code>NoSuchVersionException</code> if it could not be found.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version
	 * @throws NoSuchVersionException if a version with the primary key could not be found
	 */
	public static Version findByPrimaryKey(long fileVersionId)
		throws com.weprode.facile.document.exception.NoSuchVersionException {

		return getPersistence().findByPrimaryKey(fileVersionId);
	}

	/**
	 * Returns the version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileVersionId the primary key of the version
	 * @return the version, or <code>null</code> if a version with the primary key could not be found
	 */
	public static Version fetchByPrimaryKey(long fileVersionId) {
		return getPersistence().fetchByPrimaryKey(fileVersionId);
	}

	/**
	 * Returns all the versions.
	 *
	 * @return the versions
	 */
	public static List<Version> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Version> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Version> findAll(
		int start, int end, OrderByComparator<Version> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Version> findAll(
		int start, int end, OrderByComparator<Version> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the versions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of versions.
	 *
	 * @return the number of versions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static VersionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile VersionPersistence _persistence;

}