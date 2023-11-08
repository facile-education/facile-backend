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

package com.weprode.facile.application.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.application.exception.NoSuchAuthorizedSchoolException;
import com.weprode.facile.application.model.AuthorizedSchool;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the authorized school service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuthorizedSchoolUtil
 * @generated
 */
@ProviderType
public interface AuthorizedSchoolPersistence
	extends BasePersistence<AuthorizedSchool> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuthorizedSchoolUtil} to access the authorized school persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the authorized schools where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching authorized schools
	 */
	public java.util.List<AuthorizedSchool> findByapplicationId(
		long applicationId);

	/**
	 * Returns a range of all the authorized schools where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @return the range of matching authorized schools
	 */
	public java.util.List<AuthorizedSchool> findByapplicationId(
		long applicationId, int start, int end);

	/**
	 * Returns an ordered range of all the authorized schools where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching authorized schools
	 */
	public java.util.List<AuthorizedSchool> findByapplicationId(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuthorizedSchool>
			orderByComparator);

	/**
	 * Returns an ordered range of all the authorized schools where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching authorized schools
	 */
	public java.util.List<AuthorizedSchool> findByapplicationId(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuthorizedSchool>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authorized school
	 * @throws NoSuchAuthorizedSchoolException if a matching authorized school could not be found
	 */
	public AuthorizedSchool findByapplicationId_First(
			long applicationId,
			com.liferay.portal.kernel.util.OrderByComparator<AuthorizedSchool>
				orderByComparator)
		throws NoSuchAuthorizedSchoolException;

	/**
	 * Returns the first authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	public AuthorizedSchool fetchByapplicationId_First(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator<AuthorizedSchool>
			orderByComparator);

	/**
	 * Returns the last authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authorized school
	 * @throws NoSuchAuthorizedSchoolException if a matching authorized school could not be found
	 */
	public AuthorizedSchool findByapplicationId_Last(
			long applicationId,
			com.liferay.portal.kernel.util.OrderByComparator<AuthorizedSchool>
				orderByComparator)
		throws NoSuchAuthorizedSchoolException;

	/**
	 * Returns the last authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	public AuthorizedSchool fetchByapplicationId_Last(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator<AuthorizedSchool>
			orderByComparator);

	/**
	 * Returns the authorized schools before and after the current authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param authorizedSchoolId the primary key of the current authorized school
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next authorized school
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	public AuthorizedSchool[] findByapplicationId_PrevAndNext(
			long authorizedSchoolId, long applicationId,
			com.liferay.portal.kernel.util.OrderByComparator<AuthorizedSchool>
				orderByComparator)
		throws NoSuchAuthorizedSchoolException;

	/**
	 * Removes all the authorized schools where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 */
	public void removeByapplicationId(long applicationId);

	/**
	 * Returns the number of authorized schools where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching authorized schools
	 */
	public int countByapplicationId(long applicationId);

	/**
	 * Returns the authorized school where applicationId = &#63; and schoolId = &#63; or throws a <code>NoSuchAuthorizedSchoolException</code> if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching authorized school
	 * @throws NoSuchAuthorizedSchoolException if a matching authorized school could not be found
	 */
	public AuthorizedSchool findByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws NoSuchAuthorizedSchoolException;

	/**
	 * Returns the authorized school where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	public AuthorizedSchool fetchByapplicationId_schoolId(
		long applicationId, long schoolId);

	/**
	 * Returns the authorized school where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	public AuthorizedSchool fetchByapplicationId_schoolId(
		long applicationId, long schoolId, boolean useFinderCache);

	/**
	 * Removes the authorized school where applicationId = &#63; and schoolId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the authorized school that was removed
	 */
	public AuthorizedSchool removeByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws NoSuchAuthorizedSchoolException;

	/**
	 * Returns the number of authorized schools where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the number of matching authorized schools
	 */
	public int countByapplicationId_schoolId(long applicationId, long schoolId);

	/**
	 * Caches the authorized school in the entity cache if it is enabled.
	 *
	 * @param authorizedSchool the authorized school
	 */
	public void cacheResult(AuthorizedSchool authorizedSchool);

	/**
	 * Caches the authorized schools in the entity cache if it is enabled.
	 *
	 * @param authorizedSchools the authorized schools
	 */
	public void cacheResult(java.util.List<AuthorizedSchool> authorizedSchools);

	/**
	 * Creates a new authorized school with the primary key. Does not add the authorized school to the database.
	 *
	 * @param authorizedSchoolId the primary key for the new authorized school
	 * @return the new authorized school
	 */
	public AuthorizedSchool create(long authorizedSchoolId);

	/**
	 * Removes the authorized school with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school that was removed
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	public AuthorizedSchool remove(long authorizedSchoolId)
		throws NoSuchAuthorizedSchoolException;

	public AuthorizedSchool updateImpl(AuthorizedSchool authorizedSchool);

	/**
	 * Returns the authorized school with the primary key or throws a <code>NoSuchAuthorizedSchoolException</code> if it could not be found.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	public AuthorizedSchool findByPrimaryKey(long authorizedSchoolId)
		throws NoSuchAuthorizedSchoolException;

	/**
	 * Returns the authorized school with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school, or <code>null</code> if a authorized school with the primary key could not be found
	 */
	public AuthorizedSchool fetchByPrimaryKey(long authorizedSchoolId);

	/**
	 * Returns all the authorized schools.
	 *
	 * @return the authorized schools
	 */
	public java.util.List<AuthorizedSchool> findAll();

	/**
	 * Returns a range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @return the range of authorized schools
	 */
	public java.util.List<AuthorizedSchool> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of authorized schools
	 */
	public java.util.List<AuthorizedSchool> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuthorizedSchool>
			orderByComparator);

	/**
	 * Returns an ordered range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of authorized schools
	 */
	public java.util.List<AuthorizedSchool> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuthorizedSchool>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the authorized schools from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of authorized schools.
	 *
	 * @return the number of authorized schools
	 */
	public int countAll();

}