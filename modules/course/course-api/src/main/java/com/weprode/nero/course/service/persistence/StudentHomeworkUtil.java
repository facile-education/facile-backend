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

package com.weprode.nero.course.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.course.model.StudentHomework;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the student homework service. This utility wraps <code>com.weprode.nero.course.service.persistence.impl.StudentHomeworkPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StudentHomeworkPersistence
 * @generated
 */
public class StudentHomeworkUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(StudentHomework studentHomework) {
		getPersistence().clearCache(studentHomework);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, StudentHomework> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<StudentHomework> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StudentHomework> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StudentHomework> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StudentHomework update(StudentHomework studentHomework) {
		return getPersistence().update(studentHomework);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StudentHomework update(
		StudentHomework studentHomework, ServiceContext serviceContext) {

		return getPersistence().update(studentHomework, serviceContext);
	}

	/**
	 * Returns all the student homeworks where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching student homeworks
	 */
	public static List<StudentHomework> findByhomeworkId(long homeworkId) {
		return getPersistence().findByhomeworkId(homeworkId);
	}

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
	public static List<StudentHomework> findByhomeworkId(
		long homeworkId, int start, int end) {

		return getPersistence().findByhomeworkId(homeworkId, start, end);
	}

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
	public static List<StudentHomework> findByhomeworkId(
		long homeworkId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().findByhomeworkId(
			homeworkId, start, end, orderByComparator);
	}

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
	public static List<StudentHomework> findByhomeworkId(
		long homeworkId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByhomeworkId(
			homeworkId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public static StudentHomework findByhomeworkId_First(
			long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findByhomeworkId_First(
			homeworkId, orderByComparator);
	}

	/**
	 * Returns the first student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public static StudentHomework fetchByhomeworkId_First(
		long homeworkId, OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().fetchByhomeworkId_First(
			homeworkId, orderByComparator);
	}

	/**
	 * Returns the last student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public static StudentHomework findByhomeworkId_Last(
			long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findByhomeworkId_Last(
			homeworkId, orderByComparator);
	}

	/**
	 * Returns the last student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public static StudentHomework fetchByhomeworkId_Last(
		long homeworkId, OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().fetchByhomeworkId_Last(
			homeworkId, orderByComparator);
	}

	/**
	 * Returns the student homeworks before and after the current student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param studentHomeworkId the primary key of the current student homework
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	public static StudentHomework[] findByhomeworkId_PrevAndNext(
			long studentHomeworkId, long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findByhomeworkId_PrevAndNext(
			studentHomeworkId, homeworkId, orderByComparator);
	}

	/**
	 * Removes all the student homeworks where homeworkId = &#63; from the database.
	 *
	 * @param homeworkId the homework ID
	 */
	public static void removeByhomeworkId(long homeworkId) {
		getPersistence().removeByhomeworkId(homeworkId);
	}

	/**
	 * Returns the number of student homeworks where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the number of matching student homeworks
	 */
	public static int countByhomeworkId(long homeworkId) {
		return getPersistence().countByhomeworkId(homeworkId);
	}

	/**
	 * Returns all the student homeworks where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the matching student homeworks
	 */
	public static List<StudentHomework> findBystudentId(long studentId) {
		return getPersistence().findBystudentId(studentId);
	}

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
	public static List<StudentHomework> findBystudentId(
		long studentId, int start, int end) {

		return getPersistence().findBystudentId(studentId, start, end);
	}

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
	public static List<StudentHomework> findBystudentId(
		long studentId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().findBystudentId(
			studentId, start, end, orderByComparator);
	}

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
	public static List<StudentHomework> findBystudentId(
		long studentId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBystudentId(
			studentId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public static StudentHomework findBystudentId_First(
			long studentId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findBystudentId_First(
			studentId, orderByComparator);
	}

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public static StudentHomework fetchBystudentId_First(
		long studentId, OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().fetchBystudentId_First(
			studentId, orderByComparator);
	}

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public static StudentHomework findBystudentId_Last(
			long studentId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findBystudentId_Last(
			studentId, orderByComparator);
	}

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public static StudentHomework fetchBystudentId_Last(
		long studentId, OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().fetchBystudentId_Last(
			studentId, orderByComparator);
	}

	/**
	 * Returns the student homeworks before and after the current student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentHomeworkId the primary key of the current student homework
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	public static StudentHomework[] findBystudentId_PrevAndNext(
			long studentHomeworkId, long studentId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findBystudentId_PrevAndNext(
			studentHomeworkId, studentId, orderByComparator);
	}

	/**
	 * Removes all the student homeworks where studentId = &#63; from the database.
	 *
	 * @param studentId the student ID
	 */
	public static void removeBystudentId(long studentId) {
		getPersistence().removeBystudentId(studentId);
	}

	/**
	 * Returns the number of student homeworks where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the number of matching student homeworks
	 */
	public static int countBystudentId(long studentId) {
		return getPersistence().countBystudentId(studentId);
	}

	/**
	 * Returns all the student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @return the matching student homeworks
	 */
	public static List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId) {

		return getPersistence().findBystudentId_homeworkId(
			studentId, homeworkId);
	}

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
	public static List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId, int start, int end) {

		return getPersistence().findBystudentId_homeworkId(
			studentId, homeworkId, start, end);
	}

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
	public static List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().findBystudentId_homeworkId(
			studentId, homeworkId, start, end, orderByComparator);
	}

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
	public static List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBystudentId_homeworkId(
			studentId, homeworkId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public static StudentHomework findBystudentId_homeworkId_First(
			long studentId, long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findBystudentId_homeworkId_First(
			studentId, homeworkId, orderByComparator);
	}

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public static StudentHomework fetchBystudentId_homeworkId_First(
		long studentId, long homeworkId,
		OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().fetchBystudentId_homeworkId_First(
			studentId, homeworkId, orderByComparator);
	}

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	public static StudentHomework findBystudentId_homeworkId_Last(
			long studentId, long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findBystudentId_homeworkId_Last(
			studentId, homeworkId, orderByComparator);
	}

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	public static StudentHomework fetchBystudentId_homeworkId_Last(
		long studentId, long homeworkId,
		OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().fetchBystudentId_homeworkId_Last(
			studentId, homeworkId, orderByComparator);
	}

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
	public static StudentHomework[] findBystudentId_homeworkId_PrevAndNext(
			long studentHomeworkId, long studentId, long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findBystudentId_homeworkId_PrevAndNext(
			studentHomeworkId, studentId, homeworkId, orderByComparator);
	}

	/**
	 * Removes all the student homeworks where studentId = &#63; and homeworkId = &#63; from the database.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 */
	public static void removeBystudentId_homeworkId(
		long studentId, long homeworkId) {

		getPersistence().removeBystudentId_homeworkId(studentId, homeworkId);
	}

	/**
	 * Returns the number of student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @return the number of matching student homeworks
	 */
	public static int countBystudentId_homeworkId(
		long studentId, long homeworkId) {

		return getPersistence().countBystudentId_homeworkId(
			studentId, homeworkId);
	}

	/**
	 * Caches the student homework in the entity cache if it is enabled.
	 *
	 * @param studentHomework the student homework
	 */
	public static void cacheResult(StudentHomework studentHomework) {
		getPersistence().cacheResult(studentHomework);
	}

	/**
	 * Caches the student homeworks in the entity cache if it is enabled.
	 *
	 * @param studentHomeworks the student homeworks
	 */
	public static void cacheResult(List<StudentHomework> studentHomeworks) {
		getPersistence().cacheResult(studentHomeworks);
	}

	/**
	 * Creates a new student homework with the primary key. Does not add the student homework to the database.
	 *
	 * @param studentHomeworkId the primary key for the new student homework
	 * @return the new student homework
	 */
	public static StudentHomework create(long studentHomeworkId) {
		return getPersistence().create(studentHomeworkId);
	}

	/**
	 * Removes the student homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework that was removed
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	public static StudentHomework remove(long studentHomeworkId)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().remove(studentHomeworkId);
	}

	public static StudentHomework updateImpl(StudentHomework studentHomework) {
		return getPersistence().updateImpl(studentHomework);
	}

	/**
	 * Returns the student homework with the primary key or throws a <code>NoSuchStudentHomeworkException</code> if it could not be found.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	public static StudentHomework findByPrimaryKey(long studentHomeworkId)
		throws com.weprode.nero.course.exception.
			NoSuchStudentHomeworkException {

		return getPersistence().findByPrimaryKey(studentHomeworkId);
	}

	/**
	 * Returns the student homework with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework, or <code>null</code> if a student homework with the primary key could not be found
	 */
	public static StudentHomework fetchByPrimaryKey(long studentHomeworkId) {
		return getPersistence().fetchByPrimaryKey(studentHomeworkId);
	}

	/**
	 * Returns all the student homeworks.
	 *
	 * @return the student homeworks
	 */
	public static List<StudentHomework> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<StudentHomework> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<StudentHomework> findAll(
		int start, int end,
		OrderByComparator<StudentHomework> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<StudentHomework> findAll(
		int start, int end,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the student homeworks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of student homeworks.
	 *
	 * @return the number of student homeworks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static StudentHomeworkPersistence getPersistence() {
		return _persistence;
	}

	private static volatile StudentHomeworkPersistence _persistence;

}