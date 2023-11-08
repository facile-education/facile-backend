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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.course.model.Homework;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the homework service. This utility wraps <code>com.weprode.facile.course.service.persistence.impl.HomeworkPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkPersistence
 * @generated
 */
public class HomeworkUtil {

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
	public static void clearCache(Homework homework) {
		getPersistence().clearCache(homework);
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
	public static Map<Serializable, Homework> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Homework> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Homework> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Homework> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Homework> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Homework update(Homework homework) {
		return getPersistence().update(homework);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Homework update(
		Homework homework, ServiceContext serviceContext) {

		return getPersistence().update(homework, serviceContext);
	}

	/**
	 * Returns all the homeworks where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the matching homeworks
	 */
	public static List<Homework> findBycourseId(long courseId) {
		return getPersistence().findBycourseId(courseId);
	}

	/**
	 * Returns a range of all the homeworks where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of matching homeworks
	 */
	public static List<Homework> findBycourseId(
		long courseId, int start, int end) {

		return getPersistence().findBycourseId(courseId, start, end);
	}

	/**
	 * Returns an ordered range of all the homeworks where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching homeworks
	 */
	public static List<Homework> findBycourseId(
		long courseId, int start, int end,
		OrderByComparator<Homework> orderByComparator) {

		return getPersistence().findBycourseId(
			courseId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the homeworks where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching homeworks
	 */
	public static List<Homework> findBycourseId(
		long courseId, int start, int end,
		OrderByComparator<Homework> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBycourseId(
			courseId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first homework in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public static Homework findBycourseId_First(
			long courseId, OrderByComparator<Homework> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findBycourseId_First(
			courseId, orderByComparator);
	}

	/**
	 * Returns the first homework in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public static Homework fetchBycourseId_First(
		long courseId, OrderByComparator<Homework> orderByComparator) {

		return getPersistence().fetchBycourseId_First(
			courseId, orderByComparator);
	}

	/**
	 * Returns the last homework in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public static Homework findBycourseId_Last(
			long courseId, OrderByComparator<Homework> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findBycourseId_Last(
			courseId, orderByComparator);
	}

	/**
	 * Returns the last homework in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public static Homework fetchBycourseId_Last(
		long courseId, OrderByComparator<Homework> orderByComparator) {

		return getPersistence().fetchBycourseId_Last(
			courseId, orderByComparator);
	}

	/**
	 * Returns the homeworks before and after the current homework in the ordered set where courseId = &#63;.
	 *
	 * @param homeworkId the primary key of the current homework
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public static Homework[] findBycourseId_PrevAndNext(
			long homeworkId, long courseId,
			OrderByComparator<Homework> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findBycourseId_PrevAndNext(
			homeworkId, courseId, orderByComparator);
	}

	/**
	 * Removes all the homeworks where courseId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 */
	public static void removeBycourseId(long courseId) {
		getPersistence().removeBycourseId(courseId);
	}

	/**
	 * Returns the number of homeworks where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the number of matching homeworks
	 */
	public static int countBycourseId(long courseId) {
		return getPersistence().countBycourseId(courseId);
	}

	/**
	 * Returns all the homeworks where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @return the matching homeworks
	 */
	public static List<Homework> findBysourceSessionId(long sourceSessionId) {
		return getPersistence().findBysourceSessionId(sourceSessionId);
	}

	/**
	 * Returns a range of all the homeworks where sourceSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param sourceSessionId the source session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of matching homeworks
	 */
	public static List<Homework> findBysourceSessionId(
		long sourceSessionId, int start, int end) {

		return getPersistence().findBysourceSessionId(
			sourceSessionId, start, end);
	}

	/**
	 * Returns an ordered range of all the homeworks where sourceSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param sourceSessionId the source session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching homeworks
	 */
	public static List<Homework> findBysourceSessionId(
		long sourceSessionId, int start, int end,
		OrderByComparator<Homework> orderByComparator) {

		return getPersistence().findBysourceSessionId(
			sourceSessionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the homeworks where sourceSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param sourceSessionId the source session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching homeworks
	 */
	public static List<Homework> findBysourceSessionId(
		long sourceSessionId, int start, int end,
		OrderByComparator<Homework> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBysourceSessionId(
			sourceSessionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public static Homework findBysourceSessionId_First(
			long sourceSessionId, OrderByComparator<Homework> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findBysourceSessionId_First(
			sourceSessionId, orderByComparator);
	}

	/**
	 * Returns the first homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public static Homework fetchBysourceSessionId_First(
		long sourceSessionId, OrderByComparator<Homework> orderByComparator) {

		return getPersistence().fetchBysourceSessionId_First(
			sourceSessionId, orderByComparator);
	}

	/**
	 * Returns the last homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public static Homework findBysourceSessionId_Last(
			long sourceSessionId, OrderByComparator<Homework> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findBysourceSessionId_Last(
			sourceSessionId, orderByComparator);
	}

	/**
	 * Returns the last homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public static Homework fetchBysourceSessionId_Last(
		long sourceSessionId, OrderByComparator<Homework> orderByComparator) {

		return getPersistence().fetchBysourceSessionId_Last(
			sourceSessionId, orderByComparator);
	}

	/**
	 * Returns the homeworks before and after the current homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param homeworkId the primary key of the current homework
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public static Homework[] findBysourceSessionId_PrevAndNext(
			long homeworkId, long sourceSessionId,
			OrderByComparator<Homework> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findBysourceSessionId_PrevAndNext(
			homeworkId, sourceSessionId, orderByComparator);
	}

	/**
	 * Removes all the homeworks where sourceSessionId = &#63; from the database.
	 *
	 * @param sourceSessionId the source session ID
	 */
	public static void removeBysourceSessionId(long sourceSessionId) {
		getPersistence().removeBysourceSessionId(sourceSessionId);
	}

	/**
	 * Returns the number of homeworks where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @return the number of matching homeworks
	 */
	public static int countBysourceSessionId(long sourceSessionId) {
		return getPersistence().countBysourceSessionId(sourceSessionId);
	}

	/**
	 * Returns all the homeworks where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @return the matching homeworks
	 */
	public static List<Homework> findBytargetSessionId(long targetSessionId) {
		return getPersistence().findBytargetSessionId(targetSessionId);
	}

	/**
	 * Returns a range of all the homeworks where targetSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetSessionId the target session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of matching homeworks
	 */
	public static List<Homework> findBytargetSessionId(
		long targetSessionId, int start, int end) {

		return getPersistence().findBytargetSessionId(
			targetSessionId, start, end);
	}

	/**
	 * Returns an ordered range of all the homeworks where targetSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetSessionId the target session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching homeworks
	 */
	public static List<Homework> findBytargetSessionId(
		long targetSessionId, int start, int end,
		OrderByComparator<Homework> orderByComparator) {

		return getPersistence().findBytargetSessionId(
			targetSessionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the homeworks where targetSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetSessionId the target session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching homeworks
	 */
	public static List<Homework> findBytargetSessionId(
		long targetSessionId, int start, int end,
		OrderByComparator<Homework> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBytargetSessionId(
			targetSessionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public static Homework findBytargetSessionId_First(
			long targetSessionId, OrderByComparator<Homework> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findBytargetSessionId_First(
			targetSessionId, orderByComparator);
	}

	/**
	 * Returns the first homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public static Homework fetchBytargetSessionId_First(
		long targetSessionId, OrderByComparator<Homework> orderByComparator) {

		return getPersistence().fetchBytargetSessionId_First(
			targetSessionId, orderByComparator);
	}

	/**
	 * Returns the last homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public static Homework findBytargetSessionId_Last(
			long targetSessionId, OrderByComparator<Homework> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findBytargetSessionId_Last(
			targetSessionId, orderByComparator);
	}

	/**
	 * Returns the last homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public static Homework fetchBytargetSessionId_Last(
		long targetSessionId, OrderByComparator<Homework> orderByComparator) {

		return getPersistence().fetchBytargetSessionId_Last(
			targetSessionId, orderByComparator);
	}

	/**
	 * Returns the homeworks before and after the current homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param homeworkId the primary key of the current homework
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public static Homework[] findBytargetSessionId_PrevAndNext(
			long homeworkId, long targetSessionId,
			OrderByComparator<Homework> orderByComparator)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findBytargetSessionId_PrevAndNext(
			homeworkId, targetSessionId, orderByComparator);
	}

	/**
	 * Removes all the homeworks where targetSessionId = &#63; from the database.
	 *
	 * @param targetSessionId the target session ID
	 */
	public static void removeBytargetSessionId(long targetSessionId) {
		getPersistence().removeBytargetSessionId(targetSessionId);
	}

	/**
	 * Returns the number of homeworks where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @return the number of matching homeworks
	 */
	public static int countBytargetSessionId(long targetSessionId) {
		return getPersistence().countBytargetSessionId(targetSessionId);
	}

	/**
	 * Caches the homework in the entity cache if it is enabled.
	 *
	 * @param homework the homework
	 */
	public static void cacheResult(Homework homework) {
		getPersistence().cacheResult(homework);
	}

	/**
	 * Caches the homeworks in the entity cache if it is enabled.
	 *
	 * @param homeworks the homeworks
	 */
	public static void cacheResult(List<Homework> homeworks) {
		getPersistence().cacheResult(homeworks);
	}

	/**
	 * Creates a new homework with the primary key. Does not add the homework to the database.
	 *
	 * @param homeworkId the primary key for the new homework
	 * @return the new homework
	 */
	public static Homework create(long homeworkId) {
		return getPersistence().create(homeworkId);
	}

	/**
	 * Removes the homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework that was removed
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public static Homework remove(long homeworkId)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().remove(homeworkId);
	}

	public static Homework updateImpl(Homework homework) {
		return getPersistence().updateImpl(homework);
	}

	/**
	 * Returns the homework with the primary key or throws a <code>NoSuchHomeworkException</code> if it could not be found.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public static Homework findByPrimaryKey(long homeworkId)
		throws com.weprode.facile.course.exception.NoSuchHomeworkException {

		return getPersistence().findByPrimaryKey(homeworkId);
	}

	/**
	 * Returns the homework with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework, or <code>null</code> if a homework with the primary key could not be found
	 */
	public static Homework fetchByPrimaryKey(long homeworkId) {
		return getPersistence().fetchByPrimaryKey(homeworkId);
	}

	/**
	 * Returns all the homeworks.
	 *
	 * @return the homeworks
	 */
	public static List<Homework> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of homeworks
	 */
	public static List<Homework> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of homeworks
	 */
	public static List<Homework> findAll(
		int start, int end, OrderByComparator<Homework> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of homeworks
	 */
	public static List<Homework> findAll(
		int start, int end, OrderByComparator<Homework> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the homeworks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of homeworks.
	 *
	 * @return the number of homeworks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HomeworkPersistence getPersistence() {
		return _persistence;
	}

	private static volatile HomeworkPersistence _persistence;

}