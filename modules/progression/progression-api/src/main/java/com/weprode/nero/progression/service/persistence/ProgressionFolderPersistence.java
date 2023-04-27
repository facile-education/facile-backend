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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.progression.exception.NoSuchFolderException;
import com.weprode.nero.progression.model.ProgressionFolder;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the progression folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionFolderUtil
 * @generated
 */
@ProviderType
public interface ProgressionFolderPersistence
	extends BasePersistence<ProgressionFolder> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProgressionFolderUtil} to access the progression folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the progression folder where progressionFolderId = &#63; or throws a <code>NoSuchFolderException</code> if it could not be found.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public ProgressionFolder findByprogressionFolderId(long progressionFolderId)
		throws NoSuchFolderException;

	/**
	 * Returns the progression folder where progressionFolderId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public ProgressionFolder fetchByprogressionFolderId(
		long progressionFolderId);

	/**
	 * Returns the progression folder where progressionFolderId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public ProgressionFolder fetchByprogressionFolderId(
		long progressionFolderId, boolean useFinderCache);

	/**
	 * Removes the progression folder where progressionFolderId = &#63; from the database.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the progression folder that was removed
	 */
	public ProgressionFolder removeByprogressionFolderId(
			long progressionFolderId)
		throws NoSuchFolderException;

	/**
	 * Returns the number of progression folders where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the number of matching progression folders
	 */
	public int countByprogressionFolderId(long progressionFolderId);

	/**
	 * Returns all the progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @return the matching progression folders
	 */
	public java.util.List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId);

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
	public java.util.List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId, int start, int end);

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
	public java.util.List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator);

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
	public java.util.List<ProgressionFolder> findByparentFolderId_progressionId(
		long parentFolderId, long progressionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public ProgressionFolder findByparentFolderId_progressionId_First(
			long parentFolderId, long progressionId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
				orderByComparator)
		throws NoSuchFolderException;

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public ProgressionFolder fetchByparentFolderId_progressionId_First(
		long parentFolderId, long progressionId,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator);

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public ProgressionFolder findByparentFolderId_progressionId_Last(
			long parentFolderId, long progressionId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
				orderByComparator)
		throws NoSuchFolderException;

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public ProgressionFolder fetchByparentFolderId_progressionId_Last(
		long parentFolderId, long progressionId,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator);

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
	public ProgressionFolder[] findByparentFolderId_progressionId_PrevAndNext(
			long progressionFolderId, long parentFolderId, long progressionId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
				orderByComparator)
		throws NoSuchFolderException;

	/**
	 * Removes all the progression folders where parentFolderId = &#63; and progressionId = &#63; from the database.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 */
	public void removeByparentFolderId_progressionId(
		long parentFolderId, long progressionId);

	/**
	 * Returns the number of progression folders where parentFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param progressionId the progression ID
	 * @return the number of matching progression folders
	 */
	public int countByparentFolderId_progressionId(
		long parentFolderId, long progressionId);

	/**
	 * Returns all the progression folders where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @return the matching progression folders
	 */
	public java.util.List<ProgressionFolder> findByparentFolderId(
		long parentFolderId);

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
	public java.util.List<ProgressionFolder> findByparentFolderId(
		long parentFolderId, int start, int end);

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
	public java.util.List<ProgressionFolder> findByparentFolderId(
		long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator);

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
	public java.util.List<ProgressionFolder> findByparentFolderId(
		long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public ProgressionFolder findByparentFolderId_First(
			long parentFolderId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
				orderByComparator)
		throws NoSuchFolderException;

	/**
	 * Returns the first progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public ProgressionFolder fetchByparentFolderId_First(
		long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator);

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder
	 * @throws NoSuchFolderException if a matching progression folder could not be found
	 */
	public ProgressionFolder findByparentFolderId_Last(
			long parentFolderId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
				orderByComparator)
		throws NoSuchFolderException;

	/**
	 * Returns the last progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression folder, or <code>null</code> if a matching progression folder could not be found
	 */
	public ProgressionFolder fetchByparentFolderId_Last(
		long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator);

	/**
	 * Returns the progression folders before and after the current progression folder in the ordered set where parentFolderId = &#63;.
	 *
	 * @param progressionFolderId the primary key of the current progression folder
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression folder
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	public ProgressionFolder[] findByparentFolderId_PrevAndNext(
			long progressionFolderId, long parentFolderId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
				orderByComparator)
		throws NoSuchFolderException;

	/**
	 * Removes all the progression folders where parentFolderId = &#63; from the database.
	 *
	 * @param parentFolderId the parent folder ID
	 */
	public void removeByparentFolderId(long parentFolderId);

	/**
	 * Returns the number of progression folders where parentFolderId = &#63;.
	 *
	 * @param parentFolderId the parent folder ID
	 * @return the number of matching progression folders
	 */
	public int countByparentFolderId(long parentFolderId);

	/**
	 * Caches the progression folder in the entity cache if it is enabled.
	 *
	 * @param progressionFolder the progression folder
	 */
	public void cacheResult(ProgressionFolder progressionFolder);

	/**
	 * Caches the progression folders in the entity cache if it is enabled.
	 *
	 * @param progressionFolders the progression folders
	 */
	public void cacheResult(
		java.util.List<ProgressionFolder> progressionFolders);

	/**
	 * Creates a new progression folder with the primary key. Does not add the progression folder to the database.
	 *
	 * @param progressionFolderId the primary key for the new progression folder
	 * @return the new progression folder
	 */
	public ProgressionFolder create(long progressionFolderId);

	/**
	 * Removes the progression folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder that was removed
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	public ProgressionFolder remove(long progressionFolderId)
		throws NoSuchFolderException;

	public ProgressionFolder updateImpl(ProgressionFolder progressionFolder);

	/**
	 * Returns the progression folder with the primary key or throws a <code>NoSuchFolderException</code> if it could not be found.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder
	 * @throws NoSuchFolderException if a progression folder with the primary key could not be found
	 */
	public ProgressionFolder findByPrimaryKey(long progressionFolderId)
		throws NoSuchFolderException;

	/**
	 * Returns the progression folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder, or <code>null</code> if a progression folder with the primary key could not be found
	 */
	public ProgressionFolder fetchByPrimaryKey(long progressionFolderId);

	/**
	 * Returns all the progression folders.
	 *
	 * @return the progression folders
	 */
	public java.util.List<ProgressionFolder> findAll();

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
	public java.util.List<ProgressionFolder> findAll(int start, int end);

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
	public java.util.List<ProgressionFolder> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator);

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
	public java.util.List<ProgressionFolder> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionFolder>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the progression folders from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of progression folders.
	 *
	 * @return the number of progression folders
	 */
	public int countAll();

}