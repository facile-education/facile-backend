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

package com.weprode.facile.course.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.course.exception.NoSuchStudentHomeworkException;
import com.weprode.facile.course.model.StudentHomework;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the student homework service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StudentHomeworkUtil
 * @generated
 */
@ProviderType
public interface StudentHomeworkPersistence
	extends BasePersistence<StudentHomework> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StudentHomeworkUtil} to access the student homework persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the student homeworks where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching student homeworks
	 */
	public java.util.List<StudentHomework> findByhomeworkId(long homeworkId);

	/**
	 * Returns a range of all the student homeworks where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of matching student homeworks
	 */
	public java.util.List<StudentHomework> findByhomeworkId(
		long homeworkId, int start, int end);

	/**
	 * Returns an ordered range of all the student homeworks where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching student homeworks
	 */
	public java.util.List<StudentHomework> findByhomeworkId(
		long homeworkId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns an ordered range of all the student homeworks where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching student homeworks
	 */
	public java.util.List<StudentHomework> findByhomeworkId(
		long homeworkId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public StudentHomework findByhomeworkId_First(
			long homeworkId,
			com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
				orderByComparator)
		throws NoSuchStudentHomeworkException;

	/**
	 * Returns the first student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public StudentHomework fetchByhomeworkId_First(
		long homeworkId,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns the last student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public StudentHomework findByhomeworkId_Last(
			long homeworkId,
			com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
				orderByComparator)
		throws NoSuchStudentHomeworkException;

	/**
	 * Returns the last student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public StudentHomework fetchByhomeworkId_Last(
		long homeworkId,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns the student homeworks before and after the current student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param studentHomeworkId the primary key of the current student homework
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	public StudentHomework[] findByhomeworkId_PrevAndNext(
			long studentHomeworkId, long homeworkId,
			com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
				orderByComparator)
		throws NoSuchStudentHomeworkException;

	/**
	 * Removes all the student homeworks where homeworkId = &#63; from the database.
	 *
	 * @param homeworkId the homework ID
	 */
	public void removeByhomeworkId(long homeworkId);

	/**
	 * Returns the number of student homeworks where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the number of matching student homeworks
	 */
	public int countByhomeworkId(long homeworkId);

	/**
	 * Returns all the student homeworks where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the matching student homeworks
	 */
	public java.util.List<StudentHomework> findBystudentId(long studentId);

	/**
	 * Returns a range of all the student homeworks where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of matching student homeworks
	 */
	public java.util.List<StudentHomework> findBystudentId(
		long studentId, int start, int end);

	/**
	 * Returns an ordered range of all the student homeworks where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching student homeworks
	 */
	public java.util.List<StudentHomework> findBystudentId(
		long studentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns an ordered range of all the student homeworks where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching student homeworks
	 */
	public java.util.List<StudentHomework> findBystudentId(
		long studentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public StudentHomework findBystudentId_First(
			long studentId,
			com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
				orderByComparator)
		throws NoSuchStudentHomeworkException;

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public StudentHomework fetchBystudentId_First(
		long studentId,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public StudentHomework findBystudentId_Last(
			long studentId,
			com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
				orderByComparator)
		throws NoSuchStudentHomeworkException;

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public StudentHomework fetchBystudentId_Last(
		long studentId,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns the student homeworks before and after the current student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentHomeworkId the primary key of the current student homework
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	public StudentHomework[] findBystudentId_PrevAndNext(
			long studentHomeworkId, long studentId,
			com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
				orderByComparator)
		throws NoSuchStudentHomeworkException;

	/**
	 * Removes all the student homeworks where studentId = &#63; from the database.
	 *
	 * @param studentId the student ID
	 */
	public void removeBystudentId(long studentId);

	/**
	 * Returns the number of student homeworks where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the number of matching student homeworks
	 */
	public int countBystudentId(long studentId);

	/**
	 * Returns all the student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @return the matching student homeworks
	 */
	public java.util.List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId);

	/**
	 * Returns a range of all the student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of matching student homeworks
	 */
	public java.util.List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId, int start, int end);

	/**
	 * Returns an ordered range of all the student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching student homeworks
	 */
	public java.util.List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns an ordered range of all the student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching student homeworks
	 */
	public java.util.List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public StudentHomework findBystudentId_homeworkId_First(
			long studentId, long homeworkId,
			com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
				orderByComparator)
		throws NoSuchStudentHomeworkException;

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public StudentHomework fetchBystudentId_homeworkId_First(
		long studentId, long homeworkId,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public StudentHomework findBystudentId_homeworkId_Last(
			long studentId, long homeworkId,
			com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
				orderByComparator)
		throws NoSuchStudentHomeworkException;

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public StudentHomework fetchBystudentId_homeworkId_Last(
		long studentId, long homeworkId,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns the student homeworks before and after the current student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentHomeworkId the primary key of the current student homework
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	public StudentHomework[] findBystudentId_homeworkId_PrevAndNext(
			long studentHomeworkId, long studentId, long homeworkId,
			com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
				orderByComparator)
		throws NoSuchStudentHomeworkException;

	/**
	 * Removes all the student homeworks where studentId = &#63; and homeworkId = &#63; from the database.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 */
	public void removeBystudentId_homeworkId(long studentId, long homeworkId);

	/**
	 * Returns the number of student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @return the number of matching student homeworks
	 */
	public int countBystudentId_homeworkId(long studentId, long homeworkId);

	/**
	 * Caches the student homework in the entity cache if it is enabled.
	 *
	 * @param studentHomework the student homework
	 */
	public void cacheResult(StudentHomework studentHomework);

	/**
	 * Caches the student homeworks in the entity cache if it is enabled.
	 *
	 * @param studentHomeworks the student homeworks
	 */
	public void cacheResult(java.util.List<StudentHomework> studentHomeworks);

	/**
	 * Creates a new student homework with the primary key. Does not add the student homework to the database.
	 *
	 * @param studentHomeworkId the primary key for the new student homework
	 * @return the new student homework
	 */
	public StudentHomework create(long studentHomeworkId);

	/**
	 * Removes the student homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework that was removed
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	public StudentHomework remove(long studentHomeworkId)
		throws NoSuchStudentHomeworkException;

	public StudentHomework updateImpl(StudentHomework studentHomework);

	/**
	 * Returns the student homework with the primary key or throws a <code>NoSuchStudentHomeworkException</code> if it could not be found.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	public StudentHomework findByPrimaryKey(long studentHomeworkId)
		throws NoSuchStudentHomeworkException;

	/**
	 * Returns the student homework with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework, or <code>null</code> if a student homework with the primary key could not be found
	 */
	public StudentHomework fetchByPrimaryKey(long studentHomeworkId);

	/**
	 * Returns all the student homeworks.
	 *
	 * @return the student homeworks
	 */
	public java.util.List<StudentHomework> findAll();

	/**
	 * Returns a range of all the student homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of student homeworks
	 */
	public java.util.List<StudentHomework> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the student homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of student homeworks
	 */
	public java.util.List<StudentHomework> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator);

	/**
	 * Returns an ordered range of all the student homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of student homeworks
	 */
	public java.util.List<StudentHomework> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StudentHomework>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the student homeworks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of student homeworks.
	 *
	 * @return the number of student homeworks
	 */
	public int countAll();

}