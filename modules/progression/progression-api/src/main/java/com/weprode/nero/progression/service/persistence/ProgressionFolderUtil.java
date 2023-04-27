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

package com.weprode.nero.progression.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.progression.model.ProgressionFolder;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the progression folder service. This utility wraps <code>com.weprode.nero.progression.service.persistence.impl.ProgressionFolderPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionFolderPersistence
 * @generated
 */
public class ProgressionFolderUtil {

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
	public static void clearCache(ProgressionFolder progressionFolder) {
		getPersistence().clearCache(progressionFolder);
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
	public static Map<Serializable, ProgressionFolder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProgressionFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProgressionFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProgressionFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProgressionFolder update(
		ProgressionFolder progressionFolder) {

		return getPersistence().update(progressionFolder);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProgressionFolder update(
		ProgressionFolder progressionFolder, ServiceContext serviceContext) {

		return getPersistence().update(progressionFolder, serviceContext);
	}

	/**
	 * Returns the progression folder where progressionFolderId = &#63; or throws a <code>NoSuchFolderException</code> if it could not be found.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public static ProgressionFolder findByprogressionFolderId(
			long progressionFolderId)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().findByprogressionFolderId(progressionFolderId);
	}

	/**
	 * Returns the progression folder where progressionFolderId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public static ProgressionFolder fetchByprogressionFolderId(
		long progressionFolderId) {

		return getPersistence().fetchByprogressionFolderId(progressionFolderId);
	}

	/**
	 * Returns the progression folder where progressionFolderId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public static ProgressionFolder fetchByprogressionFolderId(
		long progressionFolderId, boolean useFinderCache) {

		return getPersistence().fetchByprogressionFolderId(
			progressionFolderId, useFinderCache);
	}

	/**
	 * Removes the progression folder where progressionFolderId = &#63; from the database.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the progression folder that was removed
	 */
	public static ProgressionFolder removeByprogressionFolderId(
			long progressionFolderId)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().removeByprogressionFolderId(
			progressionFolderId);
	}

	/**
	 * Returns the number of progression folders where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the number of matching progression folders
	 */
	public static int countByprogressionFolderId(long progressionFolderId) {
		return getPersistence().countByprogressionFolderId(progressionFolderId);
	}

	/**
	 * Returns all the progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @return the matching progression folders
	 */
	public static List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId) {

		return getPersistence().findByparentFolderId_progressionId(
			parentFolderId, progressionId);
	}

	/**
	 * Returns a range of all the progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @return the range of matching progression folders
	 */
	public static List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId, int start, int end) {

		return getPersistence().findByparentFolderId_progressionId(
			parentFolderId, progressionId, start, end);
	}

	/**
	 * Returns an ordered range of all the progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progression folders
	 */
	public static List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId, int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return getPersistence().findByparentFolderId_progressionId(
			parentFolderId, progressionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progression folders
	 */
	public static List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId, int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByparentFolderId_progressionId(
			parentFolderId, progressionId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public static ProgressionFolder findByparentFolderId_progressionId_First(
			long parentFolderId, long progressionId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().findByparentFolderId_progressionId_First(
			parentFolderId, progressionId, orderByComparator);
	}

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public static ProgressionFolder fetchByparentFolderId_progressionId_First(
		long parentFolderId, long progressionId,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return getPersistence().fetchByparentFolderId_progressionId_First(
			parentFolderId, progressionId, orderByComparator);
	}

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public static ProgressionFolder findByparentFolderId_progressionId_Last(
			long parentFolderId, long progressionId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().findByparentFolderId_progressionId_Last(
			parentFolderId, progressionId, orderByComparator);
	}

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public static ProgressionFolder fetchByparentFolderId_progressionId_Last(
		long parentFolderId, long progressionId,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return getPersistence().fetchByparentFolderId_progressionId_Last(
			parentFolderId, progressionId, orderByComparator);
	}

	/**
	 * Returns the progression folders before and after the current progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the primary key of the current progression folder
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression folder
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	public static ProgressionFolder[]
			findByparentFolderId_progressionId_PrevAndNext(
				long progressionFolderId, long parentFolderId,
				long progressionId,
				OrderByComparator<ProgressionFolder> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().findByparentFolderId_progressionId_PrevAndNext(
			progressionFolderId, parentFolderId, progressionId,
			orderByComparator);
	}

	/**
	 * Removes all the progression folders where parentFolderId = &#63; and progressionId = &#63; from the database.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 */
	public static void removeByparentFolderId_progressionId(
		long parentFolderId, long progressionId) {

		getPersistence().removeByparentFolderId_progressionId(
			parentFolderId, progressionId);
	}

	/**
	 * Returns the number of progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @return the number of matching progression folders
	 */
	public static int countByparentFolderId_progressionId(
		long parentFolderId, long progressionId) {

		return getPersistence().countByparentFolderId_progressionId(
			parentFolderId, progressionId);
	}

	/**
	 * Returns all the progression folders where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @return the matching progression folders
	 */
	public static List<ProgressionFolder> findByparentFolderId(
		long parentFolderId) {

		return getPersistence().findByparentFolderId(parentFolderId);
	}

	/**
	 * Returns a range of all the progression folders where parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @return the range of matching progression folders
	 */
	public static List<ProgressionFolder> findByparentFolderId(
		long parentFolderId, int start, int end) {

		return getPersistence().findByparentFolderId(
			parentFolderId, start, end);
	}

	/**
	 * Returns an ordered range of all the progression folders where parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progression folders
	 */
	public static List<ProgressionFolder> findByparentFolderId(
		long parentFolderId, int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return getPersistence().findByparentFolderId(
			parentFolderId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the progression folders where parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progression folders
	 */
	public static List<ProgressionFolder> findByparentFolderId(
		long parentFolderId, int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByparentFolderId(
			parentFolderId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public static ProgressionFolder findByparentFolderId_First(
			long parentFolderId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().findByparentFolderId_First(
			parentFolderId, orderByComparator);
	}

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public static ProgressionFolder fetchByparentFolderId_First(
		long parentFolderId,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return getPersistence().fetchByparentFolderId_First(
			parentFolderId, orderByComparator);
	}

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public static ProgressionFolder findByparentFolderId_Last(
			long parentFolderId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().findByparentFolderId_Last(
			parentFolderId, orderByComparator);
	}

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public static ProgressionFolder fetchByparentFolderId_Last(
		long parentFolderId,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return getPersistence().fetchByparentFolderId_Last(
			parentFolderId, orderByComparator);
	}

	/**
	 * Returns the progression folders before and after the current progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param progressionFolderId the primary key of the current progression folder
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression folder
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	public static ProgressionFolder[] findByparentFolderId_PrevAndNext(
			long progressionFolderId, long parentFolderId,
			OrderByComparator<ProgressionFolder> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().findByparentFolderId_PrevAndNext(
			progressionFolderId, parentFolderId, orderByComparator);
	}

	/**
	 * Removes all the progression folders where parentFolderId = &#63; from the database.
	 *
	 * @param parentFolderId the parent folder ID
	 */
	public static void removeByparentFolderId(long parentFolderId) {
		getPersistence().removeByparentFolderId(parentFolderId);
	}

	/**
	 * Returns the number of progression folders where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @return the number of matching progression folders
	 */
	public static int countByparentFolderId(long parentFolderId) {
		return getPersistence().countByparentFolderId(parentFolderId);
	}

	/**
	 * Caches the progression folder in the entity cache if it is enabled.
	 *
	 * @param progressionFolder the progression folder
	 */
	public static void cacheResult(ProgressionFolder progressionFolder) {
		getPersistence().cacheResult(progressionFolder);
	}

	/**
	 * Caches the progression folders in the entity cache if it is enabled.
	 *
	 * @param progressionFolders the progression folders
	 */
	public static void cacheResult(List<ProgressionFolder> progressionFolders) {
		getPersistence().cacheResult(progressionFolders);
	}

	/**
	 * Creates a new progression folder with the primary key. Does not add the progression folder to the database.
	 *
	 * @param progressionFolderId the primary key for the new progression folder
	 * @return the new progression folder
	 */
	public static ProgressionFolder create(long progressionFolderId) {
		return getPersistence().create(progressionFolderId);
	}

	/**
	 * Removes the progression folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder that was removed
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	public static ProgressionFolder remove(long progressionFolderId)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().remove(progressionFolderId);
	}

	public static ProgressionFolder updateImpl(
		ProgressionFolder progressionFolder) {

		return getPersistence().updateImpl(progressionFolder);
	}

	/**
	 * Returns the progression folder with the primary key or throws a <code>NoSuchFolderException</code> if it could not be found.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	public static ProgressionFolder findByPrimaryKey(long progressionFolderId)
		throws com.weprode.nero.progression.exception.NoSuchFolderException {

		return getPersistence().findByPrimaryKey(progressionFolderId);
	}

	/**
	 * Returns the progression folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder, or <code>null</code> if a progression folder with the primary key could not be found
	 */
	public static ProgressionFolder fetchByPrimaryKey(
		long progressionFolderId) {

		return getPersistence().fetchByPrimaryKey(progressionFolderId);
	}

	/**
	 * Returns all the progression folders.
	 *
	 * @return the progression folders
	 */
	public static List<ProgressionFolder> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the progression folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @return the range of progression folders
	 */
	public static List<ProgressionFolder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the progression folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of progression folders
	 */
	public static List<ProgressionFolder> findAll(
		int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the progression folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of progression folders
	 */
	public static List<ProgressionFolder> findAll(
		int start, int end,
		OrderByComparator<ProgressionFolder> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the progression folders from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of progression folders.
	 *
	 * @return the number of progression folders
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProgressionFolderPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ProgressionFolderPersistence _persistence;

}