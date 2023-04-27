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

import com.weprode.nero.progression.exception.NoSuchProgressionException;
import com.weprode.nero.progression.model.Progression;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the progression service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionUtil
 * @generated
 */
@ProviderType
public interface ProgressionPersistence extends BasePersistence<Progression> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProgressionUtil} to access the progression persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the progression where progressionId = &#63; or throws a <code>NoSuchProgressionException</code> if it could not be found.
	 *
	 * @param progressionId the progression ID
	 * @return the matching progression
	 * @throws NoSuchProgressionException if a matching progression could not be found
	 */
	public Progression findByprogressionId(long progressionId)
		throws NoSuchProgressionException;

	/**
	 * Returns the progression where progressionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionId the progression ID
	 * @return the matching progression, or <code>null</code> if a matching progression could not be found
	 */
	public Progression fetchByprogressionId(long progressionId);

	/**
	 * Returns the progression where progressionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionId the progression ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression, or <code>null</code> if a matching progression could not be found
	 */
	public Progression fetchByprogressionId(
		long progressionId, boolean useFinderCache);

	/**
	 * Removes the progression where progressionId = &#63; from the database.
	 *
	 * @param progressionId the progression ID
	 * @return the progression that was removed
	 */
	public Progression removeByprogressionId(long progressionId)
		throws NoSuchProgressionException;

	/**
	 * Returns the number of progressions where progressionId = &#63;.
	 *
	 * @param progressionId the progression ID
	 * @return the number of matching progressions
	 */
	public int countByprogressionId(long progressionId);

	/**
	 * Returns all the progressions where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching progressions
	 */
	public java.util.List<Progression> findByteacherId(long teacherId);

	/**
	 * Returns a range of all the progressions where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @return the range of matching progressions
	 */
	public java.util.List<Progression> findByteacherId(
		long teacherId, int start, int end);

	/**
	 * Returns an ordered range of all the progressions where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progressions
	 */
	public java.util.List<Progression> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Progression>
			orderByComparator);

	/**
	 * Returns an ordered range of all the progressions where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progressions
	 */
	public java.util.List<Progression> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Progression>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression
	 * @throws NoSuchProgressionException if a matching progression could not be found
	 */
	public Progression findByteacherId_First(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<Progression>
				orderByComparator)
		throws NoSuchProgressionException;

	/**
	 * Returns the first progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression, or <code>null</code> if a matching progression could not be found
	 */
	public Progression fetchByteacherId_First(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<Progression>
			orderByComparator);

	/**
	 * Returns the last progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression
	 * @throws NoSuchProgressionException if a matching progression could not be found
	 */
	public Progression findByteacherId_Last(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<Progression>
				orderByComparator)
		throws NoSuchProgressionException;

	/**
	 * Returns the last progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression, or <code>null</code> if a matching progression could not be found
	 */
	public Progression fetchByteacherId_Last(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<Progression>
			orderByComparator);

	/**
	 * Returns the progressions before and after the current progression in the ordered set where teacherId = &#63;.
	 *
	 * @param progressionId the primary key of the current progression
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	public Progression[] findByteacherId_PrevAndNext(
			long progressionId, long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<Progression>
				orderByComparator)
		throws NoSuchProgressionException;

	/**
	 * Removes all the progressions where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public void removeByteacherId(long teacherId);

	/**
	 * Returns the number of progressions where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching progressions
	 */
	public int countByteacherId(long teacherId);

	/**
	 * Caches the progression in the entity cache if it is enabled.
	 *
	 * @param progression the progression
	 */
	public void cacheResult(Progression progression);

	/**
	 * Caches the progressions in the entity cache if it is enabled.
	 *
	 * @param progressions the progressions
	 */
	public void cacheResult(java.util.List<Progression> progressions);

	/**
	 * Creates a new progression with the primary key. Does not add the progression to the database.
	 *
	 * @param progressionId the primary key for the new progression
	 * @return the new progression
	 */
	public Progression create(long progressionId);

	/**
	 * Removes the progression with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression that was removed
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	public Progression remove(long progressionId)
		throws NoSuchProgressionException;

	public Progression updateImpl(Progression progression);

	/**
	 * Returns the progression with the primary key or throws a <code>NoSuchProgressionException</code> if it could not be found.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	public Progression findByPrimaryKey(long progressionId)
		throws NoSuchProgressionException;

	/**
	 * Returns the progression with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression, or <code>null</code> if a progression with the primary key could not be found
	 */
	public Progression fetchByPrimaryKey(long progressionId);

	/**
	 * Returns all the progressions.
	 *
	 * @return the progressions
	 */
	public java.util.List<Progression> findAll();

	/**
	 * Returns a range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @return the range of progressions
	 */
	public java.util.List<Progression> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of progressions
	 */
	public java.util.List<Progression> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Progression>
			orderByComparator);

	/**
	 * Returns an ordered range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of progressions
	 */
	public java.util.List<Progression> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Progression>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the progressions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of progressions.
	 *
	 * @return the number of progressions
	 */
	public int countAll();

}