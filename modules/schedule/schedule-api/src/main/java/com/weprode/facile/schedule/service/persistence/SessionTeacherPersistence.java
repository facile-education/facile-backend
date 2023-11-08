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

import com.weprode.facile.schedule.exception.NoSuchSessionTeacherException;
import com.weprode.facile.schedule.model.SessionTeacher;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the session teacher service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacherUtil
 * @generated
 */
@ProviderType
public interface SessionTeacherPersistence
	extends BasePersistence<SessionTeacher> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SessionTeacherUtil} to access the session teacher persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the session teachers where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching session teachers
	 */
	public java.util.List<SessionTeacher> findByteacherId(long teacherId);

	/**
	 * Returns a range of all the session teachers where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @return the range of matching session teachers
	 */
	public java.util.List<SessionTeacher> findByteacherId(
		long teacherId, int start, int end);

	/**
	 * Returns an ordered range of all the session teachers where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session teachers
	 */
	public java.util.List<SessionTeacher> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session teachers where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session teachers
	 */
	public java.util.List<SessionTeacher> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public SessionTeacher findByteacherId_First(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
				orderByComparator)
		throws NoSuchSessionTeacherException;

	/**
	 * Returns the first session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public SessionTeacher fetchByteacherId_First(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator);

	/**
	 * Returns the last session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public SessionTeacher findByteacherId_Last(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
				orderByComparator)
		throws NoSuchSessionTeacherException;

	/**
	 * Returns the last session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public SessionTeacher fetchByteacherId_Last(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator);

	/**
	 * Returns the session teachers before and after the current session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param sessionTeacherId the primary key of the current session teacher
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	public SessionTeacher[] findByteacherId_PrevAndNext(
			long sessionTeacherId, long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
				orderByComparator)
		throws NoSuchSessionTeacherException;

	/**
	 * Removes all the session teachers where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public void removeByteacherId(long teacherId);

	/**
	 * Returns the number of session teachers where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching session teachers
	 */
	public int countByteacherId(long teacherId);

	/**
	 * Returns all the session teachers where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching session teachers
	 */
	public java.util.List<SessionTeacher> findBysessionId(long sessionId);

	/**
	 * Returns a range of all the session teachers where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @return the range of matching session teachers
	 */
	public java.util.List<SessionTeacher> findBysessionId(
		long sessionId, int start, int end);

	/**
	 * Returns an ordered range of all the session teachers where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session teachers
	 */
	public java.util.List<SessionTeacher> findBysessionId(
		long sessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session teachers where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session teachers
	 */
	public java.util.List<SessionTeacher> findBysessionId(
		long sessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public SessionTeacher findBysessionId_First(
			long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
				orderByComparator)
		throws NoSuchSessionTeacherException;

	/**
	 * Returns the first session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public SessionTeacher fetchBysessionId_First(
		long sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator);

	/**
	 * Returns the last session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public SessionTeacher findBysessionId_Last(
			long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
				orderByComparator)
		throws NoSuchSessionTeacherException;

	/**
	 * Returns the last session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public SessionTeacher fetchBysessionId_Last(
		long sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator);

	/**
	 * Returns the session teachers before and after the current session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionTeacherId the primary key of the current session teacher
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	public SessionTeacher[] findBysessionId_PrevAndNext(
			long sessionTeacherId, long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
				orderByComparator)
		throws NoSuchSessionTeacherException;

	/**
	 * Removes all the session teachers where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	public void removeBysessionId(long sessionId);

	/**
	 * Returns the number of session teachers where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching session teachers
	 */
	public int countBysessionId(long sessionId);

	/**
	 * Returns the session teacher where sessionId = &#63; and teacherId = &#63; or throws a <code>NoSuchSessionTeacherException</code> if it could not be found.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public SessionTeacher findBysessionId_teacherId(
			long sessionId, long teacherId)
		throws NoSuchSessionTeacherException;

	/**
	 * Returns the session teacher where sessionId = &#63; and teacherId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public SessionTeacher fetchBysessionId_teacherId(
		long sessionId, long teacherId);

	/**
	 * Returns the session teacher where sessionId = &#63; and teacherId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public SessionTeacher fetchBysessionId_teacherId(
		long sessionId, long teacherId, boolean useFinderCache);

	/**
	 * Removes the session teacher where sessionId = &#63; and teacherId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the session teacher that was removed
	 */
	public SessionTeacher removeBysessionId_teacherId(
			long sessionId, long teacherId)
		throws NoSuchSessionTeacherException;

	/**
	 * Returns the number of session teachers where sessionId = &#63; and teacherId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the number of matching session teachers
	 */
	public int countBysessionId_teacherId(long sessionId, long teacherId);

	/**
	 * Returns the session teacher where sessionId = &#63; and substituteId = &#63; or throws a <code>NoSuchSessionTeacherException</code> if it could not be found.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public SessionTeacher findBysessionId_substituteId(
			long sessionId, long substituteId)
		throws NoSuchSessionTeacherException;

	/**
	 * Returns the session teacher where sessionId = &#63; and substituteId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public SessionTeacher fetchBysessionId_substituteId(
		long sessionId, long substituteId);

	/**
	 * Returns the session teacher where sessionId = &#63; and substituteId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public SessionTeacher fetchBysessionId_substituteId(
		long sessionId, long substituteId, boolean useFinderCache);

	/**
	 * Removes the session teacher where sessionId = &#63; and substituteId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the session teacher that was removed
	 */
	public SessionTeacher removeBysessionId_substituteId(
			long sessionId, long substituteId)
		throws NoSuchSessionTeacherException;

	/**
	 * Returns the number of session teachers where sessionId = &#63; and substituteId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the number of matching session teachers
	 */
	public int countBysessionId_substituteId(long sessionId, long substituteId);

	/**
	 * Caches the session teacher in the entity cache if it is enabled.
	 *
	 * @param sessionTeacher the session teacher
	 */
	public void cacheResult(SessionTeacher sessionTeacher);

	/**
	 * Caches the session teachers in the entity cache if it is enabled.
	 *
	 * @param sessionTeachers the session teachers
	 */
	public void cacheResult(java.util.List<SessionTeacher> sessionTeachers);

	/**
	 * Creates a new session teacher with the primary key. Does not add the session teacher to the database.
	 *
	 * @param sessionTeacherId the primary key for the new session teacher
	 * @return the new session teacher
	 */
	public SessionTeacher create(long sessionTeacherId);

	/**
	 * Removes the session teacher with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher that was removed
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	public SessionTeacher remove(long sessionTeacherId)
		throws NoSuchSessionTeacherException;

	public SessionTeacher updateImpl(SessionTeacher sessionTeacher);

	/**
	 * Returns the session teacher with the primary key or throws a <code>NoSuchSessionTeacherException</code> if it could not be found.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	public SessionTeacher findByPrimaryKey(long sessionTeacherId)
		throws NoSuchSessionTeacherException;

	/**
	 * Returns the session teacher with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher, or <code>null</code> if a session teacher with the primary key could not be found
	 */
	public SessionTeacher fetchByPrimaryKey(long sessionTeacherId);

	/**
	 * Returns all the session teachers.
	 *
	 * @return the session teachers
	 */
	public java.util.List<SessionTeacher> findAll();

	/**
	 * Returns a range of all the session teachers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @return the range of session teachers
	 */
	public java.util.List<SessionTeacher> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the session teachers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of session teachers
	 */
	public java.util.List<SessionTeacher> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator);

	/**
	 * Returns an ordered range of all the session teachers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of session teachers
	 */
	public java.util.List<SessionTeacher> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SessionTeacher>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the session teachers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of session teachers.
	 *
	 * @return the number of session teachers
	 */
	public int countAll();

}