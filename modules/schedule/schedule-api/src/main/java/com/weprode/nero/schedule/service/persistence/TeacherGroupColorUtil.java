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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.schedule.model.TeacherGroupColor;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the teacher group color service. This utility wraps <code>com.weprode.nero.schedule.service.persistence.impl.TeacherGroupColorPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeacherGroupColorPersistence
 * @generated
 */
public class TeacherGroupColorUtil {

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
	public static void clearCache(TeacherGroupColor teacherGroupColor) {
		getPersistence().clearCache(teacherGroupColor);
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
	public static Map<Serializable, TeacherGroupColor> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TeacherGroupColor> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TeacherGroupColor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TeacherGroupColor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TeacherGroupColor update(
		TeacherGroupColor teacherGroupColor) {

		return getPersistence().update(teacherGroupColor);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TeacherGroupColor update(
		TeacherGroupColor teacherGroupColor, ServiceContext serviceContext) {

		return getPersistence().update(teacherGroupColor, serviceContext);
	}

	/**
	 * Returns all the teacher group colors where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching teacher group colors
	 */
	public static List<TeacherGroupColor> findByteacherId(long teacherId) {
		return getPersistence().findByteacherId(teacherId);
	}

	/**
	 * Returns a range of all the teacher group colors where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @return the range of matching teacher group colors
	 */
	public static List<TeacherGroupColor> findByteacherId(
		long teacherId, int start, int end) {

		return getPersistence().findByteacherId(teacherId, start, end);
	}

	/**
	 * Returns an ordered range of all the teacher group colors where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teacher group colors
	 */
	public static List<TeacherGroupColor> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the teacher group colors where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teacher group colors
	 */
	public static List<TeacherGroupColor> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	public static TeacherGroupColor findByteacherId_First(
			long teacherId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchTeacherGroupColorException {

		return getPersistence().findByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	public static TeacherGroupColor fetchByteacherId_First(
		long teacherId,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return getPersistence().fetchByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	public static TeacherGroupColor findByteacherId_Last(
			long teacherId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchTeacherGroupColorException {

		return getPersistence().findByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	public static TeacherGroupColor fetchByteacherId_Last(
		long teacherId,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return getPersistence().fetchByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the teacher group colors before and after the current teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherGroupColorId the primary key of the current teacher group color
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher group color
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	public static TeacherGroupColor[] findByteacherId_PrevAndNext(
			long teacherGroupColorId, long teacherId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchTeacherGroupColorException {

		return getPersistence().findByteacherId_PrevAndNext(
			teacherGroupColorId, teacherId, orderByComparator);
	}

	/**
	 * Removes all the teacher group colors where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public static void removeByteacherId(long teacherId) {
		getPersistence().removeByteacherId(teacherId);
	}

	/**
	 * Returns the number of teacher group colors where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching teacher group colors
	 */
	public static int countByteacherId(long teacherId) {
		return getPersistence().countByteacherId(teacherId);
	}

	/**
	 * Returns all the teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @return the matching teacher group colors
	 */
	public static List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId) {

		return getPersistence().findByteacherId_groupId(teacherId, groupId);
	}

	/**
	 * Returns a range of all the teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @return the range of matching teacher group colors
	 */
	public static List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId, int start, int end) {

		return getPersistence().findByteacherId_groupId(
			teacherId, groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teacher group colors
	 */
	public static List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId, int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return getPersistence().findByteacherId_groupId(
			teacherId, groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teacher group colors
	 */
	public static List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId, int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByteacherId_groupId(
			teacherId, groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	public static TeacherGroupColor findByteacherId_groupId_First(
			long teacherId, long groupId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchTeacherGroupColorException {

		return getPersistence().findByteacherId_groupId_First(
			teacherId, groupId, orderByComparator);
	}

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	public static TeacherGroupColor fetchByteacherId_groupId_First(
		long teacherId, long groupId,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return getPersistence().fetchByteacherId_groupId_First(
			teacherId, groupId, orderByComparator);
	}

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	public static TeacherGroupColor findByteacherId_groupId_Last(
			long teacherId, long groupId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchTeacherGroupColorException {

		return getPersistence().findByteacherId_groupId_Last(
			teacherId, groupId, orderByComparator);
	}

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	public static TeacherGroupColor fetchByteacherId_groupId_Last(
		long teacherId, long groupId,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return getPersistence().fetchByteacherId_groupId_Last(
			teacherId, groupId, orderByComparator);
	}

	/**
	 * Returns the teacher group colors before and after the current teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherGroupColorId the primary key of the current teacher group color
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher group color
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	public static TeacherGroupColor[] findByteacherId_groupId_PrevAndNext(
			long teacherGroupColorId, long teacherId, long groupId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchTeacherGroupColorException {

		return getPersistence().findByteacherId_groupId_PrevAndNext(
			teacherGroupColorId, teacherId, groupId, orderByComparator);
	}

	/**
	 * Removes all the teacher group colors where teacherId = &#63; and groupId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 */
	public static void removeByteacherId_groupId(long teacherId, long groupId) {
		getPersistence().removeByteacherId_groupId(teacherId, groupId);
	}

	/**
	 * Returns the number of teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @return the number of matching teacher group colors
	 */
	public static int countByteacherId_groupId(long teacherId, long groupId) {
		return getPersistence().countByteacherId_groupId(teacherId, groupId);
	}

	/**
	 * Caches the teacher group color in the entity cache if it is enabled.
	 *
	 * @param teacherGroupColor the teacher group color
	 */
	public static void cacheResult(TeacherGroupColor teacherGroupColor) {
		getPersistence().cacheResult(teacherGroupColor);
	}

	/**
	 * Caches the teacher group colors in the entity cache if it is enabled.
	 *
	 * @param teacherGroupColors the teacher group colors
	 */
	public static void cacheResult(List<TeacherGroupColor> teacherGroupColors) {
		getPersistence().cacheResult(teacherGroupColors);
	}

	/**
	 * Creates a new teacher group color with the primary key. Does not add the teacher group color to the database.
	 *
	 * @param teacherGroupColorId the primary key for the new teacher group color
	 * @return the new teacher group color
	 */
	public static TeacherGroupColor create(long teacherGroupColorId) {
		return getPersistence().create(teacherGroupColorId);
	}

	/**
	 * Removes the teacher group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color that was removed
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	public static TeacherGroupColor remove(long teacherGroupColorId)
		throws com.weprode.nero.schedule.exception.
			NoSuchTeacherGroupColorException {

		return getPersistence().remove(teacherGroupColorId);
	}

	public static TeacherGroupColor updateImpl(
		TeacherGroupColor teacherGroupColor) {

		return getPersistence().updateImpl(teacherGroupColor);
	}

	/**
	 * Returns the teacher group color with the primary key or throws a <code>NoSuchTeacherGroupColorException</code> if it could not be found.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	public static TeacherGroupColor findByPrimaryKey(long teacherGroupColorId)
		throws com.weprode.nero.schedule.exception.
			NoSuchTeacherGroupColorException {

		return getPersistence().findByPrimaryKey(teacherGroupColorId);
	}

	/**
	 * Returns the teacher group color with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color, or <code>null</code> if a teacher group color with the primary key could not be found
	 */
	public static TeacherGroupColor fetchByPrimaryKey(
		long teacherGroupColorId) {

		return getPersistence().fetchByPrimaryKey(teacherGroupColorId);
	}

	/**
	 * Returns all the teacher group colors.
	 *
	 * @return the teacher group colors
	 */
	public static List<TeacherGroupColor> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the teacher group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @return the range of teacher group colors
	 */
	public static List<TeacherGroupColor> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the teacher group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of teacher group colors
	 */
	public static List<TeacherGroupColor> findAll(
		int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the teacher group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of teacher group colors
	 */
	public static List<TeacherGroupColor> findAll(
		int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the teacher group colors from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of teacher group colors.
	 *
	 * @return the number of teacher group colors
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TeacherGroupColorPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TeacherGroupColorPersistence _persistence;

}