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

package com.weprode.nero.school.life.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.school.life.model.SchoollifeSlot;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the schoollife slot service. This utility wraps <code>com.weprode.nero.school.life.service.persistence.impl.SchoollifeSlotPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlotPersistence
 * @generated
 */
public class SchoollifeSlotUtil {

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
	public static void clearCache(SchoollifeSlot schoollifeSlot) {
		getPersistence().clearCache(schoollifeSlot);
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
	public static Map<Serializable, SchoollifeSlot> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SchoollifeSlot> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SchoollifeSlot> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SchoollifeSlot> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SchoollifeSlot update(SchoollifeSlot schoollifeSlot) {
		return getPersistence().update(schoollifeSlot);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SchoollifeSlot update(
		SchoollifeSlot schoollifeSlot, ServiceContext serviceContext) {

		return getPersistence().update(schoollifeSlot, serviceContext);
	}

	/**
	 * Returns all the schoollife slots where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching schoollife slots
	 */
	public static List<SchoollifeSlot> findByteacherId(long teacherId) {
		return getPersistence().findByteacherId(teacherId);
	}

	/**
	 * Returns a range of all the schoollife slots where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @return the range of matching schoollife slots
	 */
	public static List<SchoollifeSlot> findByteacherId(
		long teacherId, int start, int end) {

		return getPersistence().findByteacherId(teacherId, start, end);
	}

	/**
	 * Returns an ordered range of all the schoollife slots where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife slots
	 */
	public static List<SchoollifeSlot> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the schoollife slots where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife slots
	 */
	public static List<SchoollifeSlot> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	public static SchoollifeSlot findByteacherId_First(
			long teacherId, OrderByComparator<SchoollifeSlot> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSlotException {

		return getPersistence().findByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the first schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	public static SchoollifeSlot fetchByteacherId_First(
		long teacherId, OrderByComparator<SchoollifeSlot> orderByComparator) {

		return getPersistence().fetchByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	public static SchoollifeSlot findByteacherId_Last(
			long teacherId, OrderByComparator<SchoollifeSlot> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSlotException {

		return getPersistence().findByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	public static SchoollifeSlot fetchByteacherId_Last(
		long teacherId, OrderByComparator<SchoollifeSlot> orderByComparator) {

		return getPersistence().fetchByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the schoollife slots before and after the current schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param schoollifeSlotId the primary key of the current schoollife slot
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife slot
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	public static SchoollifeSlot[] findByteacherId_PrevAndNext(
			long schoollifeSlotId, long teacherId,
			OrderByComparator<SchoollifeSlot> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSlotException {

		return getPersistence().findByteacherId_PrevAndNext(
			schoollifeSlotId, teacherId, orderByComparator);
	}

	/**
	 * Removes all the schoollife slots where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public static void removeByteacherId(long teacherId) {
		getPersistence().removeByteacherId(teacherId);
	}

	/**
	 * Returns the number of schoollife slots where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching schoollife slots
	 */
	public static int countByteacherId(long teacherId) {
		return getPersistence().countByteacherId(teacherId);
	}

	/**
	 * Returns all the schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the matching schoollife slots
	 */
	public static List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type) {

		return getPersistence().findByschoolId_type(schoolId, type);
	}

	/**
	 * Returns a range of all the schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @return the range of matching schoollife slots
	 */
	public static List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type, int start, int end) {

		return getPersistence().findByschoolId_type(schoolId, type, start, end);
	}

	/**
	 * Returns an ordered range of all the schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife slots
	 */
	public static List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type, int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		return getPersistence().findByschoolId_type(
			schoolId, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife slots
	 */
	public static List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type, int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId_type(
			schoolId, type, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	public static SchoollifeSlot findByschoolId_type_First(
			long schoolId, int type,
			OrderByComparator<SchoollifeSlot> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSlotException {

		return getPersistence().findByschoolId_type_First(
			schoolId, type, orderByComparator);
	}

	/**
	 * Returns the first schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	public static SchoollifeSlot fetchByschoolId_type_First(
		long schoolId, int type,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		return getPersistence().fetchByschoolId_type_First(
			schoolId, type, orderByComparator);
	}

	/**
	 * Returns the last schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	public static SchoollifeSlot findByschoolId_type_Last(
			long schoolId, int type,
			OrderByComparator<SchoollifeSlot> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSlotException {

		return getPersistence().findByschoolId_type_Last(
			schoolId, type, orderByComparator);
	}

	/**
	 * Returns the last schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	public static SchoollifeSlot fetchByschoolId_type_Last(
		long schoolId, int type,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		return getPersistence().fetchByschoolId_type_Last(
			schoolId, type, orderByComparator);
	}

	/**
	 * Returns the schoollife slots before and after the current schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoollifeSlotId the primary key of the current schoollife slot
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife slot
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	public static SchoollifeSlot[] findByschoolId_type_PrevAndNext(
			long schoollifeSlotId, long schoolId, int type,
			OrderByComparator<SchoollifeSlot> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSlotException {

		return getPersistence().findByschoolId_type_PrevAndNext(
			schoollifeSlotId, schoolId, type, orderByComparator);
	}

	/**
	 * Removes all the schoollife slots where schoolId = &#63; and type = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 */
	public static void removeByschoolId_type(long schoolId, int type) {
		getPersistence().removeByschoolId_type(schoolId, type);
	}

	/**
	 * Returns the number of schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the number of matching schoollife slots
	 */
	public static int countByschoolId_type(long schoolId, int type) {
		return getPersistence().countByschoolId_type(schoolId, type);
	}

	/**
	 * Caches the schoollife slot in the entity cache if it is enabled.
	 *
	 * @param schoollifeSlot the schoollife slot
	 */
	public static void cacheResult(SchoollifeSlot schoollifeSlot) {
		getPersistence().cacheResult(schoollifeSlot);
	}

	/**
	 * Caches the schoollife slots in the entity cache if it is enabled.
	 *
	 * @param schoollifeSlots the schoollife slots
	 */
	public static void cacheResult(List<SchoollifeSlot> schoollifeSlots) {
		getPersistence().cacheResult(schoollifeSlots);
	}

	/**
	 * Creates a new schoollife slot with the primary key. Does not add the schoollife slot to the database.
	 *
	 * @param schoollifeSlotId the primary key for the new schoollife slot
	 * @return the new schoollife slot
	 */
	public static SchoollifeSlot create(long schoollifeSlotId) {
		return getPersistence().create(schoollifeSlotId);
	}

	/**
	 * Removes the schoollife slot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot that was removed
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	public static SchoollifeSlot remove(long schoollifeSlotId)
		throws com.weprode.nero.school.life.exception.NoSuchSlotException {

		return getPersistence().remove(schoollifeSlotId);
	}

	public static SchoollifeSlot updateImpl(SchoollifeSlot schoollifeSlot) {
		return getPersistence().updateImpl(schoollifeSlot);
	}

	/**
	 * Returns the schoollife slot with the primary key or throws a <code>NoSuchSlotException</code> if it could not be found.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	public static SchoollifeSlot findByPrimaryKey(long schoollifeSlotId)
		throws com.weprode.nero.school.life.exception.NoSuchSlotException {

		return getPersistence().findByPrimaryKey(schoollifeSlotId);
	}

	/**
	 * Returns the schoollife slot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot, or <code>null</code> if a schoollife slot with the primary key could not be found
	 */
	public static SchoollifeSlot fetchByPrimaryKey(long schoollifeSlotId) {
		return getPersistence().fetchByPrimaryKey(schoollifeSlotId);
	}

	/**
	 * Returns all the schoollife slots.
	 *
	 * @return the schoollife slots
	 */
	public static List<SchoollifeSlot> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the schoollife slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @return the range of schoollife slots
	 */
	public static List<SchoollifeSlot> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the schoollife slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schoollife slots
	 */
	public static List<SchoollifeSlot> findAll(
		int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the schoollife slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of schoollife slots
	 */
	public static List<SchoollifeSlot> findAll(
		int start, int end, OrderByComparator<SchoollifeSlot> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the schoollife slots from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of schoollife slots.
	 *
	 * @return the number of schoollife slots
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SchoollifeSlotPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SchoollifeSlotPersistence _persistence;

}