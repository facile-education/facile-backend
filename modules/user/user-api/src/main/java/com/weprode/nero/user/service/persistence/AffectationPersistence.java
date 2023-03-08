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

package com.weprode.nero.user.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.user.exception.NoSuchAffectationException;
import com.weprode.nero.user.model.Affectation;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the affectation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AffectationUtil
 * @generated
 */
@ProviderType
public interface AffectationPersistence extends BasePersistence<Affectation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AffectationUtil} to access the affectation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the affectations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching affectations
	 */
	public java.util.List<Affectation> findByuserId(long userId);

	/**
	 * Returns a range of all the affectations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of matching affectations
	 */
	public java.util.List<Affectation> findByuserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the affectations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching affectations
	 */
	public java.util.List<Affectation> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the affectations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching affectations
	 */
	public java.util.List<Affectation> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public Affectation findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Affectation>
				orderByComparator)
		throws NoSuchAffectationException;

	/**
	 * Returns the first affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public Affectation fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator);

	/**
	 * Returns the last affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public Affectation findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Affectation>
				orderByComparator)
		throws NoSuchAffectationException;

	/**
	 * Returns the last affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public Affectation fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator);

	/**
	 * Returns the affectations before and after the current affectation in the ordered set where userId = &#63;.
	 *
	 * @param affectationId the primary key of the current affectation
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	public Affectation[] findByuserId_PrevAndNext(
			long affectationId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Affectation>
				orderByComparator)
		throws NoSuchAffectationException;

	/**
	 * Removes all the affectations where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of affectations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching affectations
	 */
	public int countByuserId(long userId);

	/**
	 * Returns all the affectations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching affectations
	 */
	public java.util.List<Affectation> findByschoolId(long schoolId);

	/**
	 * Returns a range of all the affectations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of matching affectations
	 */
	public java.util.List<Affectation> findByschoolId(
		long schoolId, int start, int end);

	/**
	 * Returns an ordered range of all the affectations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching affectations
	 */
	public java.util.List<Affectation> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the affectations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching affectations
	 */
	public java.util.List<Affectation> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public Affectation findByschoolId_First(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<Affectation>
				orderByComparator)
		throws NoSuchAffectationException;

	/**
	 * Returns the first affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public Affectation fetchByschoolId_First(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator);

	/**
	 * Returns the last affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public Affectation findByschoolId_Last(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<Affectation>
				orderByComparator)
		throws NoSuchAffectationException;

	/**
	 * Returns the last affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public Affectation fetchByschoolId_Last(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator);

	/**
	 * Returns the affectations before and after the current affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param affectationId the primary key of the current affectation
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	public Affectation[] findByschoolId_PrevAndNext(
			long affectationId, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<Affectation>
				orderByComparator)
		throws NoSuchAffectationException;

	/**
	 * Removes all the affectations where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public void removeByschoolId(long schoolId);

	/**
	 * Returns the number of affectations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching affectations
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Caches the affectation in the entity cache if it is enabled.
	 *
	 * @param affectation the affectation
	 */
	public void cacheResult(Affectation affectation);

	/**
	 * Caches the affectations in the entity cache if it is enabled.
	 *
	 * @param affectations the affectations
	 */
	public void cacheResult(java.util.List<Affectation> affectations);

	/**
	 * Creates a new affectation with the primary key. Does not add the affectation to the database.
	 *
	 * @param affectationId the primary key for the new affectation
	 * @return the new affectation
	 */
	public Affectation create(long affectationId);

	/**
	 * Removes the affectation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation that was removed
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	public Affectation remove(long affectationId)
		throws NoSuchAffectationException;

	public Affectation updateImpl(Affectation affectation);

	/**
	 * Returns the affectation with the primary key or throws a <code>NoSuchAffectationException</code> if it could not be found.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	public Affectation findByPrimaryKey(long affectationId)
		throws NoSuchAffectationException;

	/**
	 * Returns the affectation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation, or <code>null</code> if a affectation with the primary key could not be found
	 */
	public Affectation fetchByPrimaryKey(long affectationId);

	/**
	 * Returns all the affectations.
	 *
	 * @return the affectations
	 */
	public java.util.List<Affectation> findAll();

	/**
	 * Returns a range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of affectations
	 */
	public java.util.List<Affectation> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of affectations
	 */
	public java.util.List<Affectation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of affectations
	 */
	public java.util.List<Affectation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Affectation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the affectations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of affectations.
	 *
	 * @return the number of affectations
	 */
	public int countAll();

}