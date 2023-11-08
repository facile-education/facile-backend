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

import com.weprode.facile.schedule.exception.NoSuchTeacherSubjectException;
import com.weprode.facile.schedule.model.TeacherSubject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the teacher subject service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeacherSubjectUtil
 * @generated
 */
@ProviderType
public interface TeacherSubjectPersistence
	extends BasePersistence<TeacherSubject> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TeacherSubjectUtil} to access the teacher subject persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the teacher subjects where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching teacher subjects
	 */
	public java.util.List<TeacherSubject> findByteacherId(long teacherId);

	/**
	 * Returns a range of all the teacher subjects where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @return the range of matching teacher subjects
	 */
	public java.util.List<TeacherSubject> findByteacherId(
		long teacherId, int start, int end);

	/**
	 * Returns an ordered range of all the teacher subjects where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teacher subjects
	 */
	public java.util.List<TeacherSubject> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teacher subjects where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teacher subjects
	 */
	public java.util.List<TeacherSubject> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	public TeacherSubject findByteacherId_First(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
				orderByComparator)
		throws NoSuchTeacherSubjectException;

	/**
	 * Returns the first teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	public TeacherSubject fetchByteacherId_First(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator);

	/**
	 * Returns the last teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	public TeacherSubject findByteacherId_Last(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
				orderByComparator)
		throws NoSuchTeacherSubjectException;

	/**
	 * Returns the last teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	public TeacherSubject fetchByteacherId_Last(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator);

	/**
	 * Returns the teacher subjects before and after the current teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherSubjectId the primary key of the current teacher subject
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	public TeacherSubject[] findByteacherId_PrevAndNext(
			long teacherSubjectId, long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
				orderByComparator)
		throws NoSuchTeacherSubjectException;

	/**
	 * Removes all the teacher subjects where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public void removeByteacherId(long teacherId);

	/**
	 * Returns the number of teacher subjects where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching teacher subjects
	 */
	public int countByteacherId(long teacherId);

	/**
	 * Returns all the teacher subjects where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching teacher subjects
	 */
	public java.util.List<TeacherSubject> findByschoolId(long schoolId);

	/**
	 * Returns a range of all the teacher subjects where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @return the range of matching teacher subjects
	 */
	public java.util.List<TeacherSubject> findByschoolId(
		long schoolId, int start, int end);

	/**
	 * Returns an ordered range of all the teacher subjects where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teacher subjects
	 */
	public java.util.List<TeacherSubject> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teacher subjects where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teacher subjects
	 */
	public java.util.List<TeacherSubject> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	public TeacherSubject findByschoolId_First(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
				orderByComparator)
		throws NoSuchTeacherSubjectException;

	/**
	 * Returns the first teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	public TeacherSubject fetchByschoolId_First(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator);

	/**
	 * Returns the last teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	public TeacherSubject findByschoolId_Last(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
				orderByComparator)
		throws NoSuchTeacherSubjectException;

	/**
	 * Returns the last teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	public TeacherSubject fetchByschoolId_Last(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator);

	/**
	 * Returns the teacher subjects before and after the current teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param teacherSubjectId the primary key of the current teacher subject
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	public TeacherSubject[] findByschoolId_PrevAndNext(
			long teacherSubjectId, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
				orderByComparator)
		throws NoSuchTeacherSubjectException;

	/**
	 * Removes all the teacher subjects where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public void removeByschoolId(long schoolId);

	/**
	 * Returns the number of teacher subjects where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching teacher subjects
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Caches the teacher subject in the entity cache if it is enabled.
	 *
	 * @param teacherSubject the teacher subject
	 */
	public void cacheResult(TeacherSubject teacherSubject);

	/**
	 * Caches the teacher subjects in the entity cache if it is enabled.
	 *
	 * @param teacherSubjects the teacher subjects
	 */
	public void cacheResult(java.util.List<TeacherSubject> teacherSubjects);

	/**
	 * Creates a new teacher subject with the primary key. Does not add the teacher subject to the database.
	 *
	 * @param teacherSubjectId the primary key for the new teacher subject
	 * @return the new teacher subject
	 */
	public TeacherSubject create(long teacherSubjectId);

	/**
	 * Removes the teacher subject with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject that was removed
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	public TeacherSubject remove(long teacherSubjectId)
		throws NoSuchTeacherSubjectException;

	public TeacherSubject updateImpl(TeacherSubject teacherSubject);

	/**
	 * Returns the teacher subject with the primary key or throws a <code>NoSuchTeacherSubjectException</code> if it could not be found.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	public TeacherSubject findByPrimaryKey(long teacherSubjectId)
		throws NoSuchTeacherSubjectException;

	/**
	 * Returns the teacher subject with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject, or <code>null</code> if a teacher subject with the primary key could not be found
	 */
	public TeacherSubject fetchByPrimaryKey(long teacherSubjectId);

	/**
	 * Returns all the teacher subjects.
	 *
	 * @return the teacher subjects
	 */
	public java.util.List<TeacherSubject> findAll();

	/**
	 * Returns a range of all the teacher subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @return the range of teacher subjects
	 */
	public java.util.List<TeacherSubject> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the teacher subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of teacher subjects
	 */
	public java.util.List<TeacherSubject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teacher subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of teacher subjects
	 */
	public java.util.List<TeacherSubject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherSubject>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the teacher subjects from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of teacher subjects.
	 *
	 * @return the number of teacher subjects
	 */
	public int countAll();

}