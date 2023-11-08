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

package com.weprode.facile.organization.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.organization.model.ClassCoursMapping;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the class cours mapping service. This utility wraps <code>com.weprode.facile.organization.service.persistence.impl.ClassCoursMappingPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see ClassCoursMappingPersistence
 * @generated
 */
public class ClassCoursMappingUtil {

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
	public static void clearCache(ClassCoursMapping classCoursMapping) {
		getPersistence().clearCache(classCoursMapping);
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
	public static Map<Serializable, ClassCoursMapping> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ClassCoursMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ClassCoursMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ClassCoursMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ClassCoursMapping update(
		ClassCoursMapping classCoursMapping) {

		return getPersistence().update(classCoursMapping);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ClassCoursMapping update(
		ClassCoursMapping classCoursMapping, ServiceContext serviceContext) {

		return getPersistence().update(classCoursMapping, serviceContext);
	}

	/**
	 * Returns all the class cours mappings where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @return the matching class cours mappings
	 */
	public static List<ClassCoursMapping> findByclassOrgId(long classOrgId) {
		return getPersistence().findByclassOrgId(classOrgId);
	}

	/**
	 * Returns a range of all the class cours mappings where classOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of matching class cours mappings
	 */
	public static List<ClassCoursMapping> findByclassOrgId(
		long classOrgId, int start, int end) {

		return getPersistence().findByclassOrgId(classOrgId, start, end);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class cours mappings
	 */
	public static List<ClassCoursMapping> findByclassOrgId(
		long classOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().findByclassOrgId(
			classOrgId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching class cours mappings
	 */
	public static List<ClassCoursMapping> findByclassOrgId(
		long classOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByclassOrgId(
			classOrgId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping findByclassOrgId_First(
			long classOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findByclassOrgId_First(
			classOrgId, orderByComparator);
	}

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping fetchByclassOrgId_First(
		long classOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().fetchByclassOrgId_First(
			classOrgId, orderByComparator);
	}

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping findByclassOrgId_Last(
			long classOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findByclassOrgId_Last(
			classOrgId, orderByComparator);
	}

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping fetchByclassOrgId_Last(
		long classOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().fetchByclassOrgId_Last(
			classOrgId, orderByComparator);
	}

	/**
	 * Returns the class cours mappings before and after the current class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param mappingId the primary key of the current class cours mapping
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public static ClassCoursMapping[] findByclassOrgId_PrevAndNext(
			long mappingId, long classOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findByclassOrgId_PrevAndNext(
			mappingId, classOrgId, orderByComparator);
	}

	/**
	 * Removes all the class cours mappings where classOrgId = &#63; from the database.
	 *
	 * @param classOrgId the class org ID
	 */
	public static void removeByclassOrgId(long classOrgId) {
		getPersistence().removeByclassOrgId(classOrgId);
	}

	/**
	 * Returns the number of class cours mappings where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @return the number of matching class cours mappings
	 */
	public static int countByclassOrgId(long classOrgId) {
		return getPersistence().countByclassOrgId(classOrgId);
	}

	/**
	 * Returns all the class cours mappings where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @return the matching class cours mappings
	 */
	public static List<ClassCoursMapping> findBycoursOrgId(long coursOrgId) {
		return getPersistence().findBycoursOrgId(coursOrgId);
	}

	/**
	 * Returns a range of all the class cours mappings where coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of matching class cours mappings
	 */
	public static List<ClassCoursMapping> findBycoursOrgId(
		long coursOrgId, int start, int end) {

		return getPersistence().findBycoursOrgId(coursOrgId, start, end);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class cours mappings
	 */
	public static List<ClassCoursMapping> findBycoursOrgId(
		long coursOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().findBycoursOrgId(
			coursOrgId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching class cours mappings
	 */
	public static List<ClassCoursMapping> findBycoursOrgId(
		long coursOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBycoursOrgId(
			coursOrgId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping findBycoursOrgId_First(
			long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findBycoursOrgId_First(
			coursOrgId, orderByComparator);
	}

	/**
	 * Returns the first class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping fetchBycoursOrgId_First(
		long coursOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().fetchBycoursOrgId_First(
			coursOrgId, orderByComparator);
	}

	/**
	 * Returns the last class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping findBycoursOrgId_Last(
			long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findBycoursOrgId_Last(
			coursOrgId, orderByComparator);
	}

	/**
	 * Returns the last class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping fetchBycoursOrgId_Last(
		long coursOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().fetchBycoursOrgId_Last(
			coursOrgId, orderByComparator);
	}

	/**
	 * Returns the class cours mappings before and after the current class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param mappingId the primary key of the current class cours mapping
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public static ClassCoursMapping[] findBycoursOrgId_PrevAndNext(
			long mappingId, long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findBycoursOrgId_PrevAndNext(
			mappingId, coursOrgId, orderByComparator);
	}

	/**
	 * Removes all the class cours mappings where coursOrgId = &#63; from the database.
	 *
	 * @param coursOrgId the cours org ID
	 */
	public static void removeBycoursOrgId(long coursOrgId) {
		getPersistence().removeBycoursOrgId(coursOrgId);
	}

	/**
	 * Returns the number of class cours mappings where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @return the number of matching class cours mappings
	 */
	public static int countBycoursOrgId(long coursOrgId) {
		return getPersistence().countBycoursOrgId(coursOrgId);
	}

	/**
	 * Returns all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @return the matching class cours mappings
	 */
	public static List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId) {

		return getPersistence().findByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId);
	}

	/**
	 * Returns a range of all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of matching class cours mappings
	 */
	public static List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId, int start, int end) {

		return getPersistence().findByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId, start, end);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class cours mappings
	 */
	public static List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().findByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching class cours mappings
	 */
	public static List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId, int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping findByclassOrgId_CoursOrgId_First(
			long classOrgId, long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findByclassOrgId_CoursOrgId_First(
			classOrgId, coursOrgId, orderByComparator);
	}

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping fetchByclassOrgId_CoursOrgId_First(
		long classOrgId, long coursOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().fetchByclassOrgId_CoursOrgId_First(
			classOrgId, coursOrgId, orderByComparator);
	}

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping findByclassOrgId_CoursOrgId_Last(
			long classOrgId, long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findByclassOrgId_CoursOrgId_Last(
			classOrgId, coursOrgId, orderByComparator);
	}

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public static ClassCoursMapping fetchByclassOrgId_CoursOrgId_Last(
		long classOrgId, long coursOrgId,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().fetchByclassOrgId_CoursOrgId_Last(
			classOrgId, coursOrgId, orderByComparator);
	}

	/**
	 * Returns the class cours mappings before and after the current class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param mappingId the primary key of the current class cours mapping
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public static ClassCoursMapping[] findByclassOrgId_CoursOrgId_PrevAndNext(
			long mappingId, long classOrgId, long coursOrgId,
			OrderByComparator<ClassCoursMapping> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findByclassOrgId_CoursOrgId_PrevAndNext(
			mappingId, classOrgId, coursOrgId, orderByComparator);
	}

	/**
	 * Removes all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63; from the database.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 */
	public static void removeByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId) {

		getPersistence().removeByclassOrgId_CoursOrgId(classOrgId, coursOrgId);
	}

	/**
	 * Returns the number of class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @return the number of matching class cours mappings
	 */
	public static int countByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId) {

		return getPersistence().countByclassOrgId_CoursOrgId(
			classOrgId, coursOrgId);
	}

	/**
	 * Caches the class cours mapping in the entity cache if it is enabled.
	 *
	 * @param classCoursMapping the class cours mapping
	 */
	public static void cacheResult(ClassCoursMapping classCoursMapping) {
		getPersistence().cacheResult(classCoursMapping);
	}

	/**
	 * Caches the class cours mappings in the entity cache if it is enabled.
	 *
	 * @param classCoursMappings the class cours mappings
	 */
	public static void cacheResult(List<ClassCoursMapping> classCoursMappings) {
		getPersistence().cacheResult(classCoursMappings);
	}

	/**
	 * Creates a new class cours mapping with the primary key. Does not add the class cours mapping to the database.
	 *
	 * @param mappingId the primary key for the new class cours mapping
	 * @return the new class cours mapping
	 */
	public static ClassCoursMapping create(long mappingId) {
		return getPersistence().create(mappingId);
	}

	/**
	 * Removes the class cours mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping that was removed
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public static ClassCoursMapping remove(long mappingId)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().remove(mappingId);
	}

	public static ClassCoursMapping updateImpl(
		ClassCoursMapping classCoursMapping) {

		return getPersistence().updateImpl(classCoursMapping);
	}

	/**
	 * Returns the class cours mapping with the primary key or throws a <code>NoSuchClassCoursMappingException</code> if it could not be found.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public static ClassCoursMapping findByPrimaryKey(long mappingId)
		throws com.weprode.facile.organization.exception.
			NoSuchClassCoursMappingException {

		return getPersistence().findByPrimaryKey(mappingId);
	}

	/**
	 * Returns the class cours mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping, or <code>null</code> if a class cours mapping with the primary key could not be found
	 */
	public static ClassCoursMapping fetchByPrimaryKey(long mappingId) {
		return getPersistence().fetchByPrimaryKey(mappingId);
	}

	/**
	 * Returns all the class cours mappings.
	 *
	 * @return the class cours mappings
	 */
	public static List<ClassCoursMapping> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of class cours mappings
	 */
	public static List<ClassCoursMapping> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of class cours mappings
	 */
	public static List<ClassCoursMapping> findAll(
		int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of class cours mappings
	 */
	public static List<ClassCoursMapping> findAll(
		int start, int end,
		OrderByComparator<ClassCoursMapping> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the class cours mappings from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of class cours mappings.
	 *
	 * @return the number of class cours mappings
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ClassCoursMappingPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ClassCoursMappingPersistence _persistence;

}