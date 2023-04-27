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

package com.weprode.nero.school.life.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.school.life.exception.NoSuchRenvoiException;
import com.weprode.nero.school.life.model.Renvoi;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the renvoi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RenvoiUtil
 * @generated
 */
@ProviderType
public interface RenvoiPersistence extends BasePersistence<Renvoi> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RenvoiUtil} to access the renvoi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the renvois where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching renvois
	 */
	public java.util.List<Renvoi> findByschoolId(long schoolId);

	/**
	 * Returns a range of all the renvois where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of matching renvois
	 */
	public java.util.List<Renvoi> findByschoolId(
		long schoolId, int start, int end);

	/**
	 * Returns an ordered range of all the renvois where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching renvois
	 */
	public java.util.List<Renvoi> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator);

	/**
	 * Returns an ordered range of all the renvois where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching renvois
	 */
	public java.util.List<Renvoi> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	public Renvoi findByschoolId_First(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
				orderByComparator)
		throws NoSuchRenvoiException;

	/**
	 * Returns the first renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	public Renvoi fetchByschoolId_First(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator);

	/**
	 * Returns the last renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	public Renvoi findByschoolId_Last(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
				orderByComparator)
		throws NoSuchRenvoiException;

	/**
	 * Returns the last renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	public Renvoi fetchByschoolId_Last(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator);

	/**
	 * Returns the renvois before and after the current renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param renvoiPK the primary key of the current renvoi
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	public Renvoi[] findByschoolId_PrevAndNext(
			RenvoiPK renvoiPK, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
				orderByComparator)
		throws NoSuchRenvoiException;

	/**
	 * Removes all the renvois where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public void removeByschoolId(long schoolId);

	/**
	 * Returns the number of renvois where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching renvois
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Returns all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @return the matching renvois
	 */
	public java.util.List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status);

	/**
	 * Returns a range of all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of matching renvois
	 */
	public java.util.List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status, int start, int end);

	/**
	 * Returns an ordered range of all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching renvois
	 */
	public java.util.List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator);

	/**
	 * Returns an ordered range of all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching renvois
	 */
	public java.util.List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	public Renvoi findBysourceTeacherId_status_First(
			long sourceTeacherId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
				orderByComparator)
		throws NoSuchRenvoiException;

	/**
	 * Returns the first renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	public Renvoi fetchBysourceTeacherId_status_First(
		long sourceTeacherId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator);

	/**
	 * Returns the last renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	public Renvoi findBysourceTeacherId_status_Last(
			long sourceTeacherId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
				orderByComparator)
		throws NoSuchRenvoiException;

	/**
	 * Returns the last renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	public Renvoi fetchBysourceTeacherId_status_Last(
		long sourceTeacherId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator);

	/**
	 * Returns the renvois before and after the current renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param renvoiPK the primary key of the current renvoi
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	public Renvoi[] findBysourceTeacherId_status_PrevAndNext(
			RenvoiPK renvoiPK, long sourceTeacherId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
				orderByComparator)
		throws NoSuchRenvoiException;

	/**
	 * Removes all the renvois where sourceTeacherId = &#63; and status = &#63; from the database.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 */
	public void removeBysourceTeacherId_status(
		long sourceTeacherId, int status);

	/**
	 * Returns the number of renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @return the number of matching renvois
	 */
	public int countBysourceTeacherId_status(long sourceTeacherId, int status);

	/**
	 * Caches the renvoi in the entity cache if it is enabled.
	 *
	 * @param renvoi the renvoi
	 */
	public void cacheResult(Renvoi renvoi);

	/**
	 * Caches the renvois in the entity cache if it is enabled.
	 *
	 * @param renvois the renvois
	 */
	public void cacheResult(java.util.List<Renvoi> renvois);

	/**
	 * Creates a new renvoi with the primary key. Does not add the renvoi to the database.
	 *
	 * @param renvoiPK the primary key for the new renvoi
	 * @return the new renvoi
	 */
	public Renvoi create(RenvoiPK renvoiPK);

	/**
	 * Removes the renvoi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi that was removed
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	public Renvoi remove(RenvoiPK renvoiPK) throws NoSuchRenvoiException;

	public Renvoi updateImpl(Renvoi renvoi);

	/**
	 * Returns the renvoi with the primary key or throws a <code>NoSuchRenvoiException</code> if it could not be found.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	public Renvoi findByPrimaryKey(RenvoiPK renvoiPK)
		throws NoSuchRenvoiException;

	/**
	 * Returns the renvoi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi, or <code>null</code> if a renvoi with the primary key could not be found
	 */
	public Renvoi fetchByPrimaryKey(RenvoiPK renvoiPK);

	/**
	 * Returns all the renvois.
	 *
	 * @return the renvois
	 */
	public java.util.List<Renvoi> findAll();

	/**
	 * Returns a range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of renvois
	 */
	public java.util.List<Renvoi> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of renvois
	 */
	public java.util.List<Renvoi> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator);

	/**
	 * Returns an ordered range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of renvois
	 */
	public java.util.List<Renvoi> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Renvoi>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the renvois from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of renvois.
	 *
	 * @return the number of renvois
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}