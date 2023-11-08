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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.schedule.model.CourseDetails;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the course details service. This utility wraps <code>com.weprode.facile.schedule.service.persistence.impl.CourseDetailsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseDetailsPersistence
 * @generated
 */
public class CourseDetailsUtil {

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
	public static void clearCache(CourseDetails courseDetails) {
		getPersistence().clearCache(courseDetails);
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
	public static Map<Serializable, CourseDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CourseDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CourseDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CourseDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CourseDetails> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CourseDetails update(CourseDetails courseDetails) {
		return getPersistence().update(courseDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CourseDetails update(
		CourseDetails courseDetails, ServiceContext serviceContext) {

		return getPersistence().update(courseDetails, serviceContext);
	}

	/**
	 * Caches the course details in the entity cache if it is enabled.
	 *
	 * @param courseDetails the course details
	 */
	public static void cacheResult(CourseDetails courseDetails) {
		getPersistence().cacheResult(courseDetails);
	}

	/**
	 * Caches the course detailses in the entity cache if it is enabled.
	 *
	 * @param courseDetailses the course detailses
	 */
	public static void cacheResult(List<CourseDetails> courseDetailses) {
		getPersistence().cacheResult(courseDetailses);
	}

	/**
	 * Creates a new course details with the primary key. Does not add the course details to the database.
	 *
	 * @param courseGroupId the primary key for the new course details
	 * @return the new course details
	 */
	public static CourseDetails create(long courseGroupId) {
		return getPersistence().create(courseGroupId);
	}

	/**
	 * Removes the course details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details that was removed
	 * @throws NoSuchCourseDetailsException if a course details with the primary key could not be found
	 */
	public static CourseDetails remove(long courseGroupId)
		throws com.weprode.facile.schedule.exception.
			NoSuchCourseDetailsException {

		return getPersistence().remove(courseGroupId);
	}

	public static CourseDetails updateImpl(CourseDetails courseDetails) {
		return getPersistence().updateImpl(courseDetails);
	}

	/**
	 * Returns the course details with the primary key or throws a <code>NoSuchCourseDetailsException</code> if it could not be found.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details
	 * @throws NoSuchCourseDetailsException if a course details with the primary key could not be found
	 */
	public static CourseDetails findByPrimaryKey(long courseGroupId)
		throws com.weprode.facile.schedule.exception.
			NoSuchCourseDetailsException {

		return getPersistence().findByPrimaryKey(courseGroupId);
	}

	/**
	 * Returns the course details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details, or <code>null</code> if a course details with the primary key could not be found
	 */
	public static CourseDetails fetchByPrimaryKey(long courseGroupId) {
		return getPersistence().fetchByPrimaryKey(courseGroupId);
	}

	/**
	 * Returns all the course detailses.
	 *
	 * @return the course detailses
	 */
	public static List<CourseDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @return the range of course detailses
	 */
	public static List<CourseDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of course detailses
	 */
	public static List<CourseDetails> findAll(
		int start, int end,
		OrderByComparator<CourseDetails> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of course detailses
	 */
	public static List<CourseDetails> findAll(
		int start, int end, OrderByComparator<CourseDetails> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the course detailses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of course detailses.
	 *
	 * @return the number of course detailses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CourseDetailsPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CourseDetailsPersistence _persistence;

}