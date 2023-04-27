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

import com.weprode.nero.school.life.model.SchoollifeSession;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the schoollife session service. This utility wraps <code>com.weprode.nero.school.life.service.persistence.impl.SchoollifeSessionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSessionPersistence
 * @generated
 */
public class SchoollifeSessionUtil {

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
	public static void clearCache(SchoollifeSession schoollifeSession) {
		getPersistence().clearCache(schoollifeSession);
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
	public static Map<Serializable, SchoollifeSession> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SchoollifeSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SchoollifeSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SchoollifeSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SchoollifeSession update(
		SchoollifeSession schoollifeSession) {

		return getPersistence().update(schoollifeSession);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SchoollifeSession update(
		SchoollifeSession schoollifeSession, ServiceContext serviceContext) {

		return getPersistence().update(schoollifeSession, serviceContext);
	}

	/**
	 * Returns all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @return the matching schoollife sessions
	 */
	public static List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId) {

		return getPersistence().findByschoollifeSlotId(schoollifeSlotId);
	}

	/**
	 * Returns a range of all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of matching schoollife sessions
	 */
	public static List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId, int start, int end) {

		return getPersistence().findByschoollifeSlotId(
			schoollifeSlotId, start, end);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife sessions
	 */
	public static List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().findByschoollifeSlotId(
			schoollifeSlotId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife sessions
	 */
	public static List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoollifeSlotId(
			schoollifeSlotId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public static SchoollifeSession findByschoollifeSlotId_First(
			long schoollifeSlotId,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findByschoollifeSlotId_First(
			schoollifeSlotId, orderByComparator);
	}

	/**
	 * Returns the first schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public static SchoollifeSession fetchByschoollifeSlotId_First(
		long schoollifeSlotId,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().fetchByschoollifeSlotId_First(
			schoollifeSlotId, orderByComparator);
	}

	/**
	 * Returns the last schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public static SchoollifeSession findByschoollifeSlotId_Last(
			long schoollifeSlotId,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findByschoollifeSlotId_Last(
			schoollifeSlotId, orderByComparator);
	}

	/**
	 * Returns the last schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public static SchoollifeSession fetchByschoollifeSlotId_Last(
		long schoollifeSlotId,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().fetchByschoollifeSlotId_Last(
			schoollifeSlotId, orderByComparator);
	}

	/**
	 * Returns the schoollife sessions before and after the current schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSessionId the primary key of the current schoollife session
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public static SchoollifeSession[] findByschoollifeSlotId_PrevAndNext(
			long schoollifeSessionId, long schoollifeSlotId,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findByschoollifeSlotId_PrevAndNext(
			schoollifeSessionId, schoollifeSlotId, orderByComparator);
	}

	/**
	 * Removes all the schoollife sessions where schoollifeSlotId = &#63; from the database.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 */
	public static void removeByschoollifeSlotId(long schoollifeSlotId) {
		getPersistence().removeByschoollifeSlotId(schoollifeSlotId);
	}

	/**
	 * Returns the number of schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @return the number of matching schoollife sessions
	 */
	public static int countByschoollifeSlotId(long schoollifeSlotId) {
		return getPersistence().countByschoollifeSlotId(schoollifeSlotId);
	}

	/**
	 * Returns all the schoollife sessions where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching schoollife sessions
	 */
	public static List<SchoollifeSession> findBytype(int type) {
		return getPersistence().findBytype(type);
	}

	/**
	 * Returns a range of all the schoollife sessions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of matching schoollife sessions
	 */
	public static List<SchoollifeSession> findBytype(
		int type, int start, int end) {

		return getPersistence().findBytype(type, start, end);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife sessions
	 */
	public static List<SchoollifeSession> findBytype(
		int type, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().findBytype(type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife sessions
	 */
	public static List<SchoollifeSession> findBytype(
		int type, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBytype(
			type, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public static SchoollifeSession findBytype_First(
			int type, OrderByComparator<SchoollifeSession> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findBytype_First(type, orderByComparator);
	}

	/**
	 * Returns the first schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public static SchoollifeSession fetchBytype_First(
		int type, OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().fetchBytype_First(type, orderByComparator);
	}

	/**
	 * Returns the last schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public static SchoollifeSession findBytype_Last(
			int type, OrderByComparator<SchoollifeSession> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findBytype_Last(type, orderByComparator);
	}

	/**
	 * Returns the last schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public static SchoollifeSession fetchBytype_Last(
		int type, OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().fetchBytype_Last(type, orderByComparator);
	}

	/**
	 * Returns the schoollife sessions before and after the current schoollife session in the ordered set where type = &#63;.
	 *
	 * @param schoollifeSessionId the primary key of the current schoollife session
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public static SchoollifeSession[] findBytype_PrevAndNext(
			long schoollifeSessionId, int type,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findBytype_PrevAndNext(
			schoollifeSessionId, type, orderByComparator);
	}

	/**
	 * Removes all the schoollife sessions where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public static void removeBytype(int type) {
		getPersistence().removeBytype(type);
	}

	/**
	 * Returns the number of schoollife sessions where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching schoollife sessions
	 */
	public static int countBytype(int type) {
		return getPersistence().countBytype(type);
	}

	/**
	 * Returns all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the matching schoollife sessions
	 */
	public static List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type) {

		return getPersistence().findByschoolId_type(schoolId, type);
	}

	/**
	 * Returns a range of all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of matching schoollife sessions
	 */
	public static List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type, int start, int end) {

		return getPersistence().findByschoolId_type(schoolId, type, start, end);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife sessions
	 */
	public static List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().findByschoolId_type(
			schoolId, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife sessions
	 */
	public static List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId_type(
			schoolId, type, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public static SchoollifeSession findByschoolId_type_First(
			long schoolId, int type,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findByschoolId_type_First(
			schoolId, type, orderByComparator);
	}

	/**
	 * Returns the first schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public static SchoollifeSession fetchByschoolId_type_First(
		long schoolId, int type,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().fetchByschoolId_type_First(
			schoolId, type, orderByComparator);
	}

	/**
	 * Returns the last schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	public static SchoollifeSession findByschoolId_type_Last(
			long schoolId, int type,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findByschoolId_type_Last(
			schoolId, type, orderByComparator);
	}

	/**
	 * Returns the last schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	public static SchoollifeSession fetchByschoolId_type_Last(
		long schoolId, int type,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().fetchByschoolId_type_Last(
			schoolId, type, orderByComparator);
	}

	/**
	 * Returns the schoollife sessions before and after the current schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoollifeSessionId the primary key of the current schoollife session
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public static SchoollifeSession[] findByschoolId_type_PrevAndNext(
			long schoollifeSessionId, long schoolId, int type,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findByschoolId_type_PrevAndNext(
			schoollifeSessionId, schoolId, type, orderByComparator);
	}

	/**
	 * Removes all the schoollife sessions where schoolId = &#63; and type = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 */
	public static void removeByschoolId_type(long schoolId, int type) {
		getPersistence().removeByschoolId_type(schoolId, type);
	}

	/**
	 * Returns the number of schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the number of matching schoollife sessions
	 */
	public static int countByschoolId_type(long schoolId, int type) {
		return getPersistence().countByschoolId_type(schoolId, type);
	}

	/**
	 * Caches the schoollife session in the entity cache if it is enabled.
	 *
	 * @param schoollifeSession the schoollife session
	 */
	public static void cacheResult(SchoollifeSession schoollifeSession) {
		getPersistence().cacheResult(schoollifeSession);
	}

	/**
	 * Caches the schoollife sessions in the entity cache if it is enabled.
	 *
	 * @param schoollifeSessions the schoollife sessions
	 */
	public static void cacheResult(List<SchoollifeSession> schoollifeSessions) {
		getPersistence().cacheResult(schoollifeSessions);
	}

	/**
	 * Creates a new schoollife session with the primary key. Does not add the schoollife session to the database.
	 *
	 * @param schoollifeSessionId the primary key for the new schoollife session
	 * @return the new schoollife session
	 */
	public static SchoollifeSession create(long schoollifeSessionId) {
		return getPersistence().create(schoollifeSessionId);
	}

	/**
	 * Removes the schoollife session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session that was removed
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public static SchoollifeSession remove(long schoollifeSessionId)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().remove(schoollifeSessionId);
	}

	public static SchoollifeSession updateImpl(
		SchoollifeSession schoollifeSession) {

		return getPersistence().updateImpl(schoollifeSession);
	}

	/**
	 * Returns the schoollife session with the primary key or throws a <code>NoSuchSessionException</code> if it could not be found.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	public static SchoollifeSession findByPrimaryKey(long schoollifeSessionId)
		throws com.weprode.nero.school.life.exception.NoSuchSessionException {

		return getPersistence().findByPrimaryKey(schoollifeSessionId);
	}

	/**
	 * Returns the schoollife session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session, or <code>null</code> if a schoollife session with the primary key could not be found
	 */
	public static SchoollifeSession fetchByPrimaryKey(
		long schoollifeSessionId) {

		return getPersistence().fetchByPrimaryKey(schoollifeSessionId);
	}

	/**
	 * Returns all the schoollife sessions.
	 *
	 * @return the schoollife sessions
	 */
	public static List<SchoollifeSession> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of schoollife sessions
	 */
	public static List<SchoollifeSession> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schoollife sessions
	 */
	public static List<SchoollifeSession> findAll(
		int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of schoollife sessions
	 */
	public static List<SchoollifeSession> findAll(
		int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the schoollife sessions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of schoollife sessions.
	 *
	 * @return the number of schoollife sessions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SchoollifeSessionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SchoollifeSessionPersistence _persistence;

}