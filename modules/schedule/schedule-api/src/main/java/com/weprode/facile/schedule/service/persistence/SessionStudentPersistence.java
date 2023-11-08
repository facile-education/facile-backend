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

package com.weprode.facile.schedule.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.schedule.exception.NoSuchSessionStudentException;
import com.weprode.facile.schedule.model.SessionStudent;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the session student service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionStudentUtil
 * @generated
 */
@ProviderType
public interface SessionStudentPersistence
	extends BasePersistence<SessionStudent> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SessionStudentUtil} to access the session student persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the session students where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching session students
	 */
	public java.util.List<SessionStudent> findBysessionId(long sessionId);

	/**
	 * Returns a range of all the session students where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of matching session students
	 */
	public java.util.List<SessionStudent> findBysessionId(
		long sessionId, int start, int end);

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session students
	 */
	public java.util.List<SessionStudent> findBysessionId(
		long sessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session students
	 */
	public java.util.List<SessionStudent> findBysessionId(
		long sessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public SessionStudent findBysessionId_First(
			long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
				orderByComparator)
		throws NoSuchSessionStudentException;

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public SessionStudent fetchBysessionId_First(
		long sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public SessionStudent findBysessionId_Last(
			long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
				orderByComparator)
		throws NoSuchSessionStudentException;

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public SessionStudent fetchBysessionId_Last(
		long sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns the session students before and after the current session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionStudentId the primary key of the current session student
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public SessionStudent[] findBysessionId_PrevAndNext(
			long sessionStudentId, long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
				orderByComparator)
		throws NoSuchSessionStudentException;

	/**
	 * Removes all the session students where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	public void removeBysessionId(long sessionId);

	/**
	 * Returns the number of session students where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching session students
	 */
	public int countBysessionId(long sessionId);

	/**
	 * Returns all the session students where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the matching session students
	 */
	public java.util.List<SessionStudent> findBystudentId(long studentId);

	/**
	 * Returns a range of all the session students where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of matching session students
	 */
	public java.util.List<SessionStudent> findBystudentId(
		long studentId, int start, int end);

	/**
	 * Returns an ordered range of all the session students where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session students
	 */
	public java.util.List<SessionStudent> findBystudentId(
		long studentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session students where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session students
	 */
	public java.util.List<SessionStudent> findBystudentId(
		long studentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public SessionStudent findBystudentId_First(
			long studentId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
				orderByComparator)
		throws NoSuchSessionStudentException;

	/**
	 * Returns the first session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public SessionStudent fetchBystudentId_First(
		long studentId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns the last session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public SessionStudent findBystudentId_Last(
			long studentId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
				orderByComparator)
		throws NoSuchSessionStudentException;

	/**
	 * Returns the last session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public SessionStudent fetchBystudentId_Last(
		long studentId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns the session students before and after the current session student in the ordered set where studentId = &#63;.
	 *
	 * @param sessionStudentId the primary key of the current session student
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public SessionStudent[] findBystudentId_PrevAndNext(
			long sessionStudentId, long studentId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
				orderByComparator)
		throws NoSuchSessionStudentException;

	/**
	 * Removes all the session students where studentId = &#63; from the database.
	 *
	 * @param studentId the student ID
	 */
	public void removeBystudentId(long studentId);

	/**
	 * Returns the number of session students where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the number of matching session students
	 */
	public int countBystudentId(long studentId);

	/**
	 * Returns all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @return the matching session students
	 */
	public java.util.List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId);

	/**
	 * Returns a range of all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of matching session students
	 */
	public java.util.List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId, int start, int end);

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session students
	 */
	public java.util.List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session students
	 */
	public java.util.List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public SessionStudent findBysessionId_studentId_First(
			long sessionId, long studentId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
				orderByComparator)
		throws NoSuchSessionStudentException;

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public SessionStudent fetchBysessionId_studentId_First(
		long sessionId, long studentId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public SessionStudent findBysessionId_studentId_Last(
			long sessionId, long studentId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
				orderByComparator)
		throws NoSuchSessionStudentException;

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public SessionStudent fetchBysessionId_studentId_Last(
		long sessionId, long studentId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns the session students before and after the current session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionStudentId the primary key of the current session student
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public SessionStudent[] findBysessionId_studentId_PrevAndNext(
			long sessionStudentId, long sessionId, long studentId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
				orderByComparator)
		throws NoSuchSessionStudentException;

	/**
	 * Removes all the session students where sessionId = &#63; and studentId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 */
	public void removeBysessionId_studentId(long sessionId, long studentId);

	/**
	 * Returns the number of session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @return the number of matching session students
	 */
	public int countBysessionId_studentId(long sessionId, long studentId);

	/**
	 * Caches the session student in the entity cache if it is enabled.
	 *
	 * @param sessionStudent the session student
	 */
	public void cacheResult(SessionStudent sessionStudent);

	/**
	 * Caches the session students in the entity cache if it is enabled.
	 *
	 * @param sessionStudents the session students
	 */
	public void cacheResult(java.util.List<SessionStudent> sessionStudents);

	/**
	 * Creates a new session student with the primary key. Does not add the session student to the database.
	 *
	 * @param sessionStudentId the primary key for the new session student
	 * @return the new session student
	 */
	public SessionStudent create(long sessionStudentId);

	/**
	 * Removes the session student with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student that was removed
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public SessionStudent remove(long sessionStudentId)
		throws NoSuchSessionStudentException;

	public SessionStudent updateImpl(SessionStudent sessionStudent);

	/**
	 * Returns the session student with the primary key or throws a <code>NoSuchSessionStudentException</code> if it could not be found.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public SessionStudent findByPrimaryKey(long sessionStudentId)
		throws NoSuchSessionStudentException;

	/**
	 * Returns the session student with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student, or <code>null</code> if a session student with the primary key could not be found
	 */
	public SessionStudent fetchByPrimaryKey(long sessionStudentId);

	/**
	 * Returns all the session students.
	 *
	 * @return the session students
	 */
	public java.util.List<SessionStudent> findAll();

	/**
	 * Returns a range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of session students
	 */
	public java.util.List<SessionStudent> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of session students
	 */
	public java.util.List<SessionStudent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of session students
	 */
	public java.util.List<SessionStudent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionStudent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the session students from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of session students.
	 *
	 * @return the number of session students
	 */
	public int countAll();

}