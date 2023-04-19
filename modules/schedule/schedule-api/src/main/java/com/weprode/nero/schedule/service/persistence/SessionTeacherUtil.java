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

import com.weprode.nero.schedule.model.SessionTeacher;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the session teacher service. This utility wraps <code>com.weprode.nero.schedule.service.persistence.impl.SessionTeacherPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacherPersistence
 * @generated
 */
public class SessionTeacherUtil {

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
	public static void clearCache(SessionTeacher sessionTeacher) {
		getPersistence().clearCache(sessionTeacher);
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
	public static Map<Serializable, SessionTeacher> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SessionTeacher> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SessionTeacher> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SessionTeacher> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SessionTeacher update(SessionTeacher sessionTeacher) {
		return getPersistence().update(sessionTeacher);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SessionTeacher update(
		SessionTeacher sessionTeacher, ServiceContext serviceContext) {

		return getPersistence().update(sessionTeacher, serviceContext);
	}

	/**
	 * Returns all the session teachers where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching session teachers
	 */
	public static List<SessionTeacher> findByteacherId(long teacherId) {
		return getPersistence().findByteacherId(teacherId);
	}

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
	public static List<SessionTeacher> findByteacherId(
		long teacherId, int start, int end) {

		return getPersistence().findByteacherId(teacherId, start, end);
	}

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
	public static List<SessionTeacher> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator);
	}

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
	public static List<SessionTeacher> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public static SessionTeacher findByteacherId_First(
			long teacherId, OrderByComparator<SessionTeacher> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().findByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the first session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public static SessionTeacher fetchByteacherId_First(
		long teacherId, OrderByComparator<SessionTeacher> orderByComparator) {

		return getPersistence().fetchByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public static SessionTeacher findByteacherId_Last(
			long teacherId, OrderByComparator<SessionTeacher> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().findByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public static SessionTeacher fetchByteacherId_Last(
		long teacherId, OrderByComparator<SessionTeacher> orderByComparator) {

		return getPersistence().fetchByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the session teachers before and after the current session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param sessionTeacherId the primary key of the current session teacher
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	public static SessionTeacher[] findByteacherId_PrevAndNext(
			long sessionTeacherId, long teacherId,
			OrderByComparator<SessionTeacher> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().findByteacherId_PrevAndNext(
			sessionTeacherId, teacherId, orderByComparator);
	}

	/**
	 * Removes all the session teachers where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public static void removeByteacherId(long teacherId) {
		getPersistence().removeByteacherId(teacherId);
	}

	/**
	 * Returns the number of session teachers where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching session teachers
	 */
	public static int countByteacherId(long teacherId) {
		return getPersistence().countByteacherId(teacherId);
	}

	/**
	 * Returns all the session teachers where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching session teachers
	 */
	public static List<SessionTeacher> findBysessionId(long sessionId) {
		return getPersistence().findBysessionId(sessionId);
	}

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
	public static List<SessionTeacher> findBysessionId(
		long sessionId, int start, int end) {

		return getPersistence().findBysessionId(sessionId, start, end);
	}

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
	public static List<SessionTeacher> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator) {

		return getPersistence().findBysessionId(
			sessionId, start, end, orderByComparator);
	}

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
	public static List<SessionTeacher> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBysessionId(
			sessionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public static SessionTeacher findBysessionId_First(
			long sessionId, OrderByComparator<SessionTeacher> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().findBysessionId_First(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the first session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public static SessionTeacher fetchBysessionId_First(
		long sessionId, OrderByComparator<SessionTeacher> orderByComparator) {

		return getPersistence().fetchBysessionId_First(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the last session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public static SessionTeacher findBysessionId_Last(
			long sessionId, OrderByComparator<SessionTeacher> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().findBysessionId_Last(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the last session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public static SessionTeacher fetchBysessionId_Last(
		long sessionId, OrderByComparator<SessionTeacher> orderByComparator) {

		return getPersistence().fetchBysessionId_Last(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the session teachers before and after the current session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionTeacherId the primary key of the current session teacher
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	public static SessionTeacher[] findBysessionId_PrevAndNext(
			long sessionTeacherId, long sessionId,
			OrderByComparator<SessionTeacher> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().findBysessionId_PrevAndNext(
			sessionTeacherId, sessionId, orderByComparator);
	}

	/**
	 * Removes all the session teachers where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	public static void removeBysessionId(long sessionId) {
		getPersistence().removeBysessionId(sessionId);
	}

	/**
	 * Returns the number of session teachers where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching session teachers
	 */
	public static int countBysessionId(long sessionId) {
		return getPersistence().countBysessionId(sessionId);
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and teacherId = &#63; or throws a <code>NoSuchSessionTeacherException</code> if it could not be found.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public static SessionTeacher findBysessionId_teacherId(
			long sessionId, long teacherId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().findBysessionId_teacherId(sessionId, teacherId);
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and teacherId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public static SessionTeacher fetchBysessionId_teacherId(
		long sessionId, long teacherId) {

		return getPersistence().fetchBysessionId_teacherId(
			sessionId, teacherId);
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and teacherId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public static SessionTeacher fetchBysessionId_teacherId(
		long sessionId, long teacherId, boolean useFinderCache) {

		return getPersistence().fetchBysessionId_teacherId(
			sessionId, teacherId, useFinderCache);
	}

	/**
	 * Removes the session teacher where sessionId = &#63; and teacherId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the session teacher that was removed
	 */
	public static SessionTeacher removeBysessionId_teacherId(
			long sessionId, long teacherId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().removeBysessionId_teacherId(
			sessionId, teacherId);
	}

	/**
	 * Returns the number of session teachers where sessionId = &#63; and teacherId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the number of matching session teachers
	 */
	public static int countBysessionId_teacherId(
		long sessionId, long teacherId) {

		return getPersistence().countBysessionId_teacherId(
			sessionId, teacherId);
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and substituteId = &#63; or throws a <code>NoSuchSessionTeacherException</code> if it could not be found.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	public static SessionTeacher findBysessionId_substituteId(
			long sessionId, long substituteId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().findBysessionId_substituteId(
			sessionId, substituteId);
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and substituteId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public static SessionTeacher fetchBysessionId_substituteId(
		long sessionId, long substituteId) {

		return getPersistence().fetchBysessionId_substituteId(
			sessionId, substituteId);
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and substituteId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	public static SessionTeacher fetchBysessionId_substituteId(
		long sessionId, long substituteId, boolean useFinderCache) {

		return getPersistence().fetchBysessionId_substituteId(
			sessionId, substituteId, useFinderCache);
	}

	/**
	 * Removes the session teacher where sessionId = &#63; and substituteId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the session teacher that was removed
	 */
	public static SessionTeacher removeBysessionId_substituteId(
			long sessionId, long substituteId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().removeBysessionId_substituteId(
			sessionId, substituteId);
	}

	/**
	 * Returns the number of session teachers where sessionId = &#63; and substituteId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the number of matching session teachers
	 */
	public static int countBysessionId_substituteId(
		long sessionId, long substituteId) {

		return getPersistence().countBysessionId_substituteId(
			sessionId, substituteId);
	}

	/**
	 * Caches the session teacher in the entity cache if it is enabled.
	 *
	 * @param sessionTeacher the session teacher
	 */
	public static void cacheResult(SessionTeacher sessionTeacher) {
		getPersistence().cacheResult(sessionTeacher);
	}

	/**
	 * Caches the session teachers in the entity cache if it is enabled.
	 *
	 * @param sessionTeachers the session teachers
	 */
	public static void cacheResult(List<SessionTeacher> sessionTeachers) {
		getPersistence().cacheResult(sessionTeachers);
	}

	/**
	 * Creates a new session teacher with the primary key. Does not add the session teacher to the database.
	 *
	 * @param sessionTeacherId the primary key for the new session teacher
	 * @return the new session teacher
	 */
	public static SessionTeacher create(long sessionTeacherId) {
		return getPersistence().create(sessionTeacherId);
	}

	/**
	 * Removes the session teacher with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher that was removed
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	public static SessionTeacher remove(long sessionTeacherId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().remove(sessionTeacherId);
	}

	public static SessionTeacher updateImpl(SessionTeacher sessionTeacher) {
		return getPersistence().updateImpl(sessionTeacher);
	}

	/**
	 * Returns the session teacher with the primary key or throws a <code>NoSuchSessionTeacherException</code> if it could not be found.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	public static SessionTeacher findByPrimaryKey(long sessionTeacherId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionTeacherException {

		return getPersistence().findByPrimaryKey(sessionTeacherId);
	}

	/**
	 * Returns the session teacher with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher, or <code>null</code> if a session teacher with the primary key could not be found
	 */
	public static SessionTeacher fetchByPrimaryKey(long sessionTeacherId) {
		return getPersistence().fetchByPrimaryKey(sessionTeacherId);
	}

	/**
	 * Returns all the session teachers.
	 *
	 * @return the session teachers
	 */
	public static List<SessionTeacher> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<SessionTeacher> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<SessionTeacher> findAll(
		int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<SessionTeacher> findAll(
		int start, int end, OrderByComparator<SessionTeacher> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the session teachers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of session teachers.
	 *
	 * @return the number of session teachers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SessionTeacherPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SessionTeacherPersistence _persistence;

}