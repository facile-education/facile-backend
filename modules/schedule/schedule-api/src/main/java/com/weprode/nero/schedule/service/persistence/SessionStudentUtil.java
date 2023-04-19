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

import com.weprode.nero.schedule.model.SessionStudent;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the session student service. This utility wraps <code>com.weprode.nero.schedule.service.persistence.impl.SessionStudentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionStudentPersistence
 * @generated
 */
public class SessionStudentUtil {

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
	public static void clearCache(SessionStudent sessionStudent) {
		getPersistence().clearCache(sessionStudent);
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
	public static Map<Serializable, SessionStudent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SessionStudent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SessionStudent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SessionStudent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SessionStudent update(SessionStudent sessionStudent) {
		return getPersistence().update(sessionStudent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SessionStudent update(
		SessionStudent sessionStudent, ServiceContext serviceContext) {

		return getPersistence().update(sessionStudent, serviceContext);
	}

	/**
	 * Returns all the session students where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching session students
	 */
	public static List<SessionStudent> findBysessionId(long sessionId) {
		return getPersistence().findBysessionId(sessionId);
	}

	/**
	 * Returns a range of all the session students where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of matching session students
	 */
	public static List<SessionStudent> findBysessionId(
		long sessionId, int start, int end) {

		return getPersistence().findBysessionId(sessionId, start, end);
	}

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session students
	 */
	public static List<SessionStudent> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().findBysessionId(
			sessionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session students
	 */
	public static List<SessionStudent> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBysessionId(
			sessionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public static SessionStudent findBysessionId_First(
			long sessionId, OrderByComparator<SessionStudent> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findBysessionId_First(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public static SessionStudent fetchBysessionId_First(
		long sessionId, OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().fetchBysessionId_First(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public static SessionStudent findBysessionId_Last(
			long sessionId, OrderByComparator<SessionStudent> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findBysessionId_Last(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public static SessionStudent fetchBysessionId_Last(
		long sessionId, OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().fetchBysessionId_Last(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the session students before and after the current session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionStudentId the primary key of the current session student
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public static SessionStudent[] findBysessionId_PrevAndNext(
			long sessionStudentId, long sessionId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findBysessionId_PrevAndNext(
			sessionStudentId, sessionId, orderByComparator);
	}

	/**
	 * Removes all the session students where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	public static void removeBysessionId(long sessionId) {
		getPersistence().removeBysessionId(sessionId);
	}

	/**
	 * Returns the number of session students where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching session students
	 */
	public static int countBysessionId(long sessionId) {
		return getPersistence().countBysessionId(sessionId);
	}

	/**
	 * Returns all the session students where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the matching session students
	 */
	public static List<SessionStudent> findBystudentId(long studentId) {
		return getPersistence().findBystudentId(studentId);
	}

	/**
	 * Returns a range of all the session students where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of matching session students
	 */
	public static List<SessionStudent> findBystudentId(
		long studentId, int start, int end) {

		return getPersistence().findBystudentId(studentId, start, end);
	}

	/**
	 * Returns an ordered range of all the session students where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session students
	 */
	public static List<SessionStudent> findBystudentId(
		long studentId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().findBystudentId(
			studentId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the session students where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session students
	 */
	public static List<SessionStudent> findBystudentId(
		long studentId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBystudentId(
			studentId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public static SessionStudent findBystudentId_First(
			long studentId, OrderByComparator<SessionStudent> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findBystudentId_First(
			studentId, orderByComparator);
	}

	/**
	 * Returns the first session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public static SessionStudent fetchBystudentId_First(
		long studentId, OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().fetchBystudentId_First(
			studentId, orderByComparator);
	}

	/**
	 * Returns the last session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public static SessionStudent findBystudentId_Last(
			long studentId, OrderByComparator<SessionStudent> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findBystudentId_Last(
			studentId, orderByComparator);
	}

	/**
	 * Returns the last session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public static SessionStudent fetchBystudentId_Last(
		long studentId, OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().fetchBystudentId_Last(
			studentId, orderByComparator);
	}

	/**
	 * Returns the session students before and after the current session student in the ordered set where studentId = &#63;.
	 *
	 * @param sessionStudentId the primary key of the current session student
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public static SessionStudent[] findBystudentId_PrevAndNext(
			long sessionStudentId, long studentId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findBystudentId_PrevAndNext(
			sessionStudentId, studentId, orderByComparator);
	}

	/**
	 * Removes all the session students where studentId = &#63; from the database.
	 *
	 * @param studentId the student ID
	 */
	public static void removeBystudentId(long studentId) {
		getPersistence().removeBystudentId(studentId);
	}

	/**
	 * Returns the number of session students where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the number of matching session students
	 */
	public static int countBystudentId(long studentId) {
		return getPersistence().countBystudentId(studentId);
	}

	/**
	 * Returns all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @return the matching session students
	 */
	public static List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId) {

		return getPersistence().findBysessionId_studentId(sessionId, studentId);
	}

	/**
	 * Returns a range of all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of matching session students
	 */
	public static List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId, int start, int end) {

		return getPersistence().findBysessionId_studentId(
			sessionId, studentId, start, end);
	}

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session students
	 */
	public static List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().findBysessionId_studentId(
			sessionId, studentId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session students
	 */
	public static List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBysessionId_studentId(
			sessionId, studentId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public static SessionStudent findBysessionId_studentId_First(
			long sessionId, long studentId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findBysessionId_studentId_First(
			sessionId, studentId, orderByComparator);
	}

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public static SessionStudent fetchBysessionId_studentId_First(
		long sessionId, long studentId,
		OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().fetchBysessionId_studentId_First(
			sessionId, studentId, orderByComparator);
	}

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	public static SessionStudent findBysessionId_studentId_Last(
			long sessionId, long studentId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findBysessionId_studentId_Last(
			sessionId, studentId, orderByComparator);
	}

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student, or <code>null</code> if a matching session student could not be found
	 */
	public static SessionStudent fetchBysessionId_studentId_Last(
		long sessionId, long studentId,
		OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().fetchBysessionId_studentId_Last(
			sessionId, studentId, orderByComparator);
	}

	/**
	 * Returns the session students before and after the current session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionStudentId the primary key of the current session student
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public static SessionStudent[] findBysessionId_studentId_PrevAndNext(
			long sessionStudentId, long sessionId, long studentId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findBysessionId_studentId_PrevAndNext(
			sessionStudentId, sessionId, studentId, orderByComparator);
	}

	/**
	 * Removes all the session students where sessionId = &#63; and studentId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 */
	public static void removeBysessionId_studentId(
		long sessionId, long studentId) {

		getPersistence().removeBysessionId_studentId(sessionId, studentId);
	}

	/**
	 * Returns the number of session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @return the number of matching session students
	 */
	public static int countBysessionId_studentId(
		long sessionId, long studentId) {

		return getPersistence().countBysessionId_studentId(
			sessionId, studentId);
	}

	/**
	 * Caches the session student in the entity cache if it is enabled.
	 *
	 * @param sessionStudent the session student
	 */
	public static void cacheResult(SessionStudent sessionStudent) {
		getPersistence().cacheResult(sessionStudent);
	}

	/**
	 * Caches the session students in the entity cache if it is enabled.
	 *
	 * @param sessionStudents the session students
	 */
	public static void cacheResult(List<SessionStudent> sessionStudents) {
		getPersistence().cacheResult(sessionStudents);
	}

	/**
	 * Creates a new session student with the primary key. Does not add the session student to the database.
	 *
	 * @param sessionStudentId the primary key for the new session student
	 * @return the new session student
	 */
	public static SessionStudent create(long sessionStudentId) {
		return getPersistence().create(sessionStudentId);
	}

	/**
	 * Removes the session student with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student that was removed
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public static SessionStudent remove(long sessionStudentId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().remove(sessionStudentId);
	}

	public static SessionStudent updateImpl(SessionStudent sessionStudent) {
		return getPersistence().updateImpl(sessionStudent);
	}

	/**
	 * Returns the session student with the primary key or throws a <code>NoSuchSessionStudentException</code> if it could not be found.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	public static SessionStudent findByPrimaryKey(long sessionStudentId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSessionStudentException {

		return getPersistence().findByPrimaryKey(sessionStudentId);
	}

	/**
	 * Returns the session student with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student, or <code>null</code> if a session student with the primary key could not be found
	 */
	public static SessionStudent fetchByPrimaryKey(long sessionStudentId) {
		return getPersistence().fetchByPrimaryKey(sessionStudentId);
	}

	/**
	 * Returns all the session students.
	 *
	 * @return the session students
	 */
	public static List<SessionStudent> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of session students
	 */
	public static List<SessionStudent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of session students
	 */
	public static List<SessionStudent> findAll(
		int start, int end,
		OrderByComparator<SessionStudent> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of session students
	 */
	public static List<SessionStudent> findAll(
		int start, int end, OrderByComparator<SessionStudent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the session students from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of session students.
	 *
	 * @return the number of session students
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SessionStudentPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SessionStudentPersistence _persistence;

}