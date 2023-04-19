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

package com.weprode.nero.schedule.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.schedule.exception.NoSuchSessionParentClassException;
import com.weprode.nero.schedule.model.SessionParentClass;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the session parent class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionParentClassUtil
 * @generated
 */
@ProviderType
public interface SessionParentClassPersistence
	extends BasePersistence<SessionParentClass> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SessionParentClassUtil} to access the session parent class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the session parent classes where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBysessionId(long sessionId);

	/**
	 * Returns a range of all the session parent classes where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBysessionId(
		long sessionId, int start, int end);

	/**
	 * Returns an ordered range of all the session parent classes where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBysessionId(
		long sessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session parent classes where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBysessionId(
		long sessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	public SessionParentClass findBysessionId_First(
			long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
				orderByComparator)
		throws NoSuchSessionParentClassException;

	/**
	 * Returns the first session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	public SessionParentClass fetchBysessionId_First(
		long sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns the last session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	public SessionParentClass findBysessionId_Last(
			long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
				orderByComparator)
		throws NoSuchSessionParentClassException;

	/**
	 * Returns the last session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	public SessionParentClass fetchBysessionId_Last(
		long sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns the session parent classes before and after the current session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionParentClassId the primary key of the current session parent class
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session parent class
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	public SessionParentClass[] findBysessionId_PrevAndNext(
			long sessionParentClassId, long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
				orderByComparator)
		throws NoSuchSessionParentClassException;

	/**
	 * Removes all the session parent classes where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	public void removeBysessionId(long sessionId);

	/**
	 * Returns the number of session parent classes where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching session parent classes
	 */
	public int countBysessionId(long sessionId);

	/**
	 * Returns all the session parent classes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBygroupId(long groupId);

	/**
	 * Returns a range of all the session parent classes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBygroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the session parent classes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session parent classes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	public SessionParentClass findBygroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
				orderByComparator)
		throws NoSuchSessionParentClassException;

	/**
	 * Returns the first session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	public SessionParentClass fetchBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns the last session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	public SessionParentClass findBygroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
				orderByComparator)
		throws NoSuchSessionParentClassException;

	/**
	 * Returns the last session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	public SessionParentClass fetchBygroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns the session parent classes before and after the current session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param sessionParentClassId the primary key of the current session parent class
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session parent class
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	public SessionParentClass[] findBygroupId_PrevAndNext(
			long sessionParentClassId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
				orderByComparator)
		throws NoSuchSessionParentClassException;

	/**
	 * Removes all the session parent classes where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeBygroupId(long groupId);

	/**
	 * Returns the number of session parent classes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching session parent classes
	 */
	public int countBygroupId(long groupId);

	/**
	 * Returns all the session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @return the matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBysessionId_groupId(
		long sessionId, long groupId);

	/**
	 * Returns a range of all the session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBysessionId_groupId(
		long sessionId, long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBysessionId_groupId(
		long sessionId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session parent classes
	 */
	public java.util.List<SessionParentClass> findBysessionId_groupId(
		long sessionId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	public SessionParentClass findBysessionId_groupId_First(
			long sessionId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
				orderByComparator)
		throws NoSuchSessionParentClassException;

	/**
	 * Returns the first session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	public SessionParentClass fetchBysessionId_groupId_First(
		long sessionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns the last session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	public SessionParentClass findBysessionId_groupId_Last(
			long sessionId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
				orderByComparator)
		throws NoSuchSessionParentClassException;

	/**
	 * Returns the last session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	public SessionParentClass fetchBysessionId_groupId_Last(
		long sessionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns the session parent classes before and after the current session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionParentClassId the primary key of the current session parent class
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session parent class
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	public SessionParentClass[] findBysessionId_groupId_PrevAndNext(
			long sessionParentClassId, long sessionId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
				orderByComparator)
		throws NoSuchSessionParentClassException;

	/**
	 * Removes all the session parent classes where sessionId = &#63; and groupId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 */
	public void removeBysessionId_groupId(long sessionId, long groupId);

	/**
	 * Returns the number of session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @return the number of matching session parent classes
	 */
	public int countBysessionId_groupId(long sessionId, long groupId);

	/**
	 * Caches the session parent class in the entity cache if it is enabled.
	 *
	 * @param sessionParentClass the session parent class
	 */
	public void cacheResult(SessionParentClass sessionParentClass);

	/**
	 * Caches the session parent classes in the entity cache if it is enabled.
	 *
	 * @param sessionParentClasses the session parent classes
	 */
	public void cacheResult(
		java.util.List<SessionParentClass> sessionParentClasses);

	/**
	 * Creates a new session parent class with the primary key. Does not add the session parent class to the database.
	 *
	 * @param sessionParentClassId the primary key for the new session parent class
	 * @return the new session parent class
	 */
	public SessionParentClass create(long sessionParentClassId);

	/**
	 * Removes the session parent class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class that was removed
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	public SessionParentClass remove(long sessionParentClassId)
		throws NoSuchSessionParentClassException;

	public SessionParentClass updateImpl(SessionParentClass sessionParentClass);

	/**
	 * Returns the session parent class with the primary key or throws a <code>NoSuchSessionParentClassException</code> if it could not be found.
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	public SessionParentClass findByPrimaryKey(long sessionParentClassId)
		throws NoSuchSessionParentClassException;

	/**
	 * Returns the session parent class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class, or <code>null</code> if a session parent class with the primary key could not be found
	 */
	public SessionParentClass fetchByPrimaryKey(long sessionParentClassId);

	/**
	 * Returns all the session parent classes.
	 *
	 * @return the session parent classes
	 */
	public java.util.List<SessionParentClass> findAll();

	/**
	 * Returns a range of all the session parent classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of session parent classes
	 */
	public java.util.List<SessionParentClass> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the session parent classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of session parent classes
	 */
	public java.util.List<SessionParentClass> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session parent classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of session parent classes
	 */
	public java.util.List<SessionParentClass> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionParentClass>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the session parent classes from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of session parent classes.
	 *
	 * @return the number of session parent classes
	 */
	public int countAll();

}