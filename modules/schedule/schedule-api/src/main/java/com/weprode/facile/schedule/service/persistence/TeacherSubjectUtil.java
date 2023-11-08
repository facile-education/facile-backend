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

import com.weprode.facile.schedule.model.TeacherSubject;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the teacher subject service. This utility wraps <code>com.weprode.facile.schedule.service.persistence.impl.TeacherSubjectPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeacherSubjectPersistence
 * @generated
 */
public class TeacherSubjectUtil {

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
	public static void clearCache(TeacherSubject teacherSubject) {
		getPersistence().clearCache(teacherSubject);
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
	public static Map<Serializable, TeacherSubject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TeacherSubject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TeacherSubject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TeacherSubject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TeacherSubject update(TeacherSubject teacherSubject) {
		return getPersistence().update(teacherSubject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TeacherSubject update(
		TeacherSubject teacherSubject, ServiceContext serviceContext) {

		return getPersistence().update(teacherSubject, serviceContext);
	}

	/**
	 * Returns all the teacher subjects where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching teacher subjects
	 */
	public static List<TeacherSubject> findByteacherId(long teacherId) {
		return getPersistence().findByteacherId(teacherId);
	}

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
	public static List<TeacherSubject> findByteacherId(
		long teacherId, int start, int end) {

		return getPersistence().findByteacherId(teacherId, start, end);
	}

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
	public static List<TeacherSubject> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator);
	}

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
	public static List<TeacherSubject> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	public static TeacherSubject findByteacherId_First(
			long teacherId, OrderByComparator<TeacherSubject> orderByComparator)
		throws com.weprode.facile.schedule.exception.
			NoSuchTeacherSubjectException {

		return getPersistence().findByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the first teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	public static TeacherSubject fetchByteacherId_First(
		long teacherId, OrderByComparator<TeacherSubject> orderByComparator) {

		return getPersistence().fetchByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	public static TeacherSubject findByteacherId_Last(
			long teacherId, OrderByComparator<TeacherSubject> orderByComparator)
		throws com.weprode.facile.schedule.exception.
			NoSuchTeacherSubjectException {

		return getPersistence().findByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	public static TeacherSubject fetchByteacherId_Last(
		long teacherId, OrderByComparator<TeacherSubject> orderByComparator) {

		return getPersistence().fetchByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the teacher subjects before and after the current teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherSubjectId the primary key of the current teacher subject
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	public static TeacherSubject[] findByteacherId_PrevAndNext(
			long teacherSubjectId, long teacherId,
			OrderByComparator<TeacherSubject> orderByComparator)
		throws com.weprode.facile.schedule.exception.
			NoSuchTeacherSubjectException {

		return getPersistence().findByteacherId_PrevAndNext(
			teacherSubjectId, teacherId, orderByComparator);
	}

	/**
	 * Removes all the teacher subjects where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public static void removeByteacherId(long teacherId) {
		getPersistence().removeByteacherId(teacherId);
	}

	/**
	 * Returns the number of teacher subjects where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching teacher subjects
	 */
	public static int countByteacherId(long teacherId) {
		return getPersistence().countByteacherId(teacherId);
	}

	/**
	 * Returns all the teacher subjects where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching teacher subjects
	 */
	public static List<TeacherSubject> findByschoolId(long schoolId) {
		return getPersistence().findByschoolId(schoolId);
	}

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
	public static List<TeacherSubject> findByschoolId(
		long schoolId, int start, int end) {

		return getPersistence().findByschoolId(schoolId, start, end);
	}

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
	public static List<TeacherSubject> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator);
	}

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
	public static List<TeacherSubject> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	public static TeacherSubject findByschoolId_First(
			long schoolId, OrderByComparator<TeacherSubject> orderByComparator)
		throws com.weprode.facile.schedule.exception.
			NoSuchTeacherSubjectException {

		return getPersistence().findByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the first teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	public static TeacherSubject fetchByschoolId_First(
		long schoolId, OrderByComparator<TeacherSubject> orderByComparator) {

		return getPersistence().fetchByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	public static TeacherSubject findByschoolId_Last(
			long schoolId, OrderByComparator<TeacherSubject> orderByComparator)
		throws com.weprode.facile.schedule.exception.
			NoSuchTeacherSubjectException {

		return getPersistence().findByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	public static TeacherSubject fetchByschoolId_Last(
		long schoolId, OrderByComparator<TeacherSubject> orderByComparator) {

		return getPersistence().fetchByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the teacher subjects before and after the current teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param teacherSubjectId the primary key of the current teacher subject
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	public static TeacherSubject[] findByschoolId_PrevAndNext(
			long teacherSubjectId, long schoolId,
			OrderByComparator<TeacherSubject> orderByComparator)
		throws com.weprode.facile.schedule.exception.
			NoSuchTeacherSubjectException {

		return getPersistence().findByschoolId_PrevAndNext(
			teacherSubjectId, schoolId, orderByComparator);
	}

	/**
	 * Removes all the teacher subjects where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public static void removeByschoolId(long schoolId) {
		getPersistence().removeByschoolId(schoolId);
	}

	/**
	 * Returns the number of teacher subjects where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching teacher subjects
	 */
	public static int countByschoolId(long schoolId) {
		return getPersistence().countByschoolId(schoolId);
	}

	/**
	 * Caches the teacher subject in the entity cache if it is enabled.
	 *
	 * @param teacherSubject the teacher subject
	 */
	public static void cacheResult(TeacherSubject teacherSubject) {
		getPersistence().cacheResult(teacherSubject);
	}

	/**
	 * Caches the teacher subjects in the entity cache if it is enabled.
	 *
	 * @param teacherSubjects the teacher subjects
	 */
	public static void cacheResult(List<TeacherSubject> teacherSubjects) {
		getPersistence().cacheResult(teacherSubjects);
	}

	/**
	 * Creates a new teacher subject with the primary key. Does not add the teacher subject to the database.
	 *
	 * @param teacherSubjectId the primary key for the new teacher subject
	 * @return the new teacher subject
	 */
	public static TeacherSubject create(long teacherSubjectId) {
		return getPersistence().create(teacherSubjectId);
	}

	/**
	 * Removes the teacher subject with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject that was removed
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	public static TeacherSubject remove(long teacherSubjectId)
		throws com.weprode.facile.schedule.exception.
			NoSuchTeacherSubjectException {

		return getPersistence().remove(teacherSubjectId);
	}

	public static TeacherSubject updateImpl(TeacherSubject teacherSubject) {
		return getPersistence().updateImpl(teacherSubject);
	}

	/**
	 * Returns the teacher subject with the primary key or throws a <code>NoSuchTeacherSubjectException</code> if it could not be found.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	public static TeacherSubject findByPrimaryKey(long teacherSubjectId)
		throws com.weprode.facile.schedule.exception.
			NoSuchTeacherSubjectException {

		return getPersistence().findByPrimaryKey(teacherSubjectId);
	}

	/**
	 * Returns the teacher subject with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject, or <code>null</code> if a teacher subject with the primary key could not be found
	 */
	public static TeacherSubject fetchByPrimaryKey(long teacherSubjectId) {
		return getPersistence().fetchByPrimaryKey(teacherSubjectId);
	}

	/**
	 * Returns all the teacher subjects.
	 *
	 * @return the teacher subjects
	 */
	public static List<TeacherSubject> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<TeacherSubject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<TeacherSubject> findAll(
		int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<TeacherSubject> findAll(
		int start, int end, OrderByComparator<TeacherSubject> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the teacher subjects from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of teacher subjects.
	 *
	 * @return the number of teacher subjects
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TeacherSubjectPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TeacherSubjectPersistence _persistence;

}