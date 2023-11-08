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

package com.weprode.facile.school.life.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.school.life.exception.NoSuchSessionException;
import com.weprode.facile.school.life.model.SchoollifeSession;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the schoollife session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSessionUtil
 * @generated
 */
@ProviderType
public interface SchoollifeSessionPersistence
	extends BasePersistence<SchoollifeSession> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SchoollifeSessionUtil} to access the schoollife session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @return the matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId);

	/**
	 * Returns a range of all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId, int start, int end);

	/**
	 * Returns an ordered range of all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns an ordered range of all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public SchoollifeSession findByschoollifeSlotId_First(
			long schoollifeSlotId,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
				orderByComparator)
		throws NoSuchSessionException;

	/**
	 * Returns the first schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public SchoollifeSession fetchByschoollifeSlotId_First(
		long schoollifeSlotId,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns the last schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public SchoollifeSession findByschoollifeSlotId_Last(
			long schoollifeSlotId,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
				orderByComparator)
		throws NoSuchSessionException;

	/**
	 * Returns the last schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public SchoollifeSession fetchByschoollifeSlotId_Last(
		long schoollifeSlotId,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns the schoollife sessions before and after the current schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSessionId the primary key of the current schoollife session
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public SchoollifeSession[] findByschoollifeSlotId_PrevAndNext(
			long schoollifeSessionId, long schoollifeSlotId,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
				orderByComparator)
		throws NoSuchSessionException;

	/**
	 * Removes all the schoollife sessions where schoollifeSlotId = &#63; from the database.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 */
	public void removeByschoollifeSlotId(long schoollifeSlotId);

	/**
	 * Returns the number of schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @return the number of matching schoollife sessions
	 */
	public int countByschoollifeSlotId(long schoollifeSlotId);

	/**
	 * Returns all the schoollife sessions where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findBytype(int type);

	/**
	 * Returns a range of all the schoollife sessions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findBytype(
		int type, int start, int end);

	/**
	 * Returns an ordered range of all the schoollife sessions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findBytype(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns an ordered range of all the schoollife sessions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findBytype(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public SchoollifeSession findBytype_First(
			int type,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
				orderByComparator)
		throws NoSuchSessionException;

	/**
	 * Returns the first schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public SchoollifeSession fetchBytype_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns the last schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public SchoollifeSession findBytype_Last(
			int type,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
				orderByComparator)
		throws NoSuchSessionException;

	/**
	 * Returns the last schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public SchoollifeSession fetchBytype_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns the schoollife sessions before and after the current schoollife session in the ordered set where type = &#63;.
	 *
	 * @param schoollifeSessionId the primary key of the current schoollife session
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public SchoollifeSession[] findBytype_PrevAndNext(
			long schoollifeSessionId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
				orderByComparator)
		throws NoSuchSessionException;

	/**
	 * Removes all the schoollife sessions where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public void removeBytype(int type);

	/**
	 * Returns the number of schoollife sessions where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching schoollife sessions
	 */
	public int countBytype(int type);

	/**
	 * Returns all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type);

	/**
	 * Returns a range of all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type, int start, int end);

	/**
	 * Returns an ordered range of all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns an ordered range of all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public SchoollifeSession findByschoolId_type_First(
			long schoolId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
				orderByComparator)
		throws NoSuchSessionException;

	/**
	 * Returns the first schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public SchoollifeSession fetchByschoolId_type_First(
		long schoolId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns the last schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public SchoollifeSession findByschoolId_type_Last(
			long schoolId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
				orderByComparator)
		throws NoSuchSessionException;

	/**
	 * Returns the last schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public SchoollifeSession fetchByschoolId_type_Last(
		long schoolId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns the schoollife sessions before and after the current schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoollifeSessionId the primary key of the current schoollife session
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public SchoollifeSession[] findByschoolId_type_PrevAndNext(
			long schoollifeSessionId, long schoolId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
				orderByComparator)
		throws NoSuchSessionException;

	/**
	 * Removes all the schoollife sessions where schoolId = &#63; and type = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 */
	public void removeByschoolId_type(long schoolId, int type);

	/**
	 * Returns the number of schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the number of matching schoollife sessions
	 */
	public int countByschoolId_type(long schoolId, int type);

	/**
	 * Caches the schoollife session in the entity cache if it is enabled.
	 *
	 * @param schoollifeSession the schoollife session
	 */
	public void cacheResult(SchoollifeSession schoollifeSession);

	/**
	 * Caches the schoollife sessions in the entity cache if it is enabled.
	 *
	 * @param schoollifeSessions the schoollife sessions
	 */
	public void cacheResult(
		java.util.List<SchoollifeSession> schoollifeSessions);

	/**
	 * Creates a new schoollife session with the primary key. Does not add the schoollife session to the database.
	 *
	 * @param schoollifeSessionId the primary key for the new schoollife session
	 * @return the new schoollife session
	 */
	public SchoollifeSession create(long schoollifeSessionId);

	/**
	 * Removes the schoollife session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session that was removed
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public SchoollifeSession remove(long schoollifeSessionId)
		throws NoSuchSessionException;

	public SchoollifeSession updateImpl(SchoollifeSession schoollifeSession);

	/**
	 * Returns the schoollife session with the primary key or throws a <code>NoSuchSessionException</code> if it could not be found.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public SchoollifeSession findByPrimaryKey(long schoollifeSessionId)
		throws NoSuchSessionException;

	/**
	 * Returns the schoollife session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session, or <code>null</code> if a schoollife session with the primary key could not be found
	 */
	public SchoollifeSession fetchByPrimaryKey(long schoollifeSessionId);

	/**
	 * Returns all the schoollife sessions.
	 *
	 * @return the schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findAll();

	/**
	 * Returns a range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator);

	/**
	 * Returns an ordered range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of schoollife sessions
	 */
	public java.util.List<SchoollifeSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSession>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the schoollife sessions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of schoollife sessions.
	 *
	 * @return the number of schoollife sessions
	 */
	public int countAll();

}