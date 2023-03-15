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

package com.weprode.nero.eel.synchronization.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.eel.synchronization.model.Subject;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the subject service. This utility wraps <code>com.weprode.nero.eel.synchronization.service.persistence.impl.SubjectPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubjectPersistence
 * @generated
 */
public class SubjectUtil {

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
	public static void clearCache(Subject subject) {
		getPersistence().clearCache(subject);
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
	public static Map<Serializable, Subject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Subject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Subject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Subject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Subject> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Subject update(Subject subject) {
		return getPersistence().update(subject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Subject update(
		Subject subject, ServiceContext serviceContext) {

		return getPersistence().update(subject, serviceContext);
	}

	/**
	 * Returns all the subjects where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching subjects
	 */
	public static List<Subject> findByname(String name) {
		return getPersistence().findByname(name);
	}

	/**
	 * Returns a range of all the subjects where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @return the range of matching subjects
	 */
	public static List<Subject> findByname(String name, int start, int end) {
		return getPersistence().findByname(name, start, end);
	}

	/**
	 * Returns an ordered range of all the subjects where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subjects
	 */
	public static List<Subject> findByname(
		String name, int start, int end,
		OrderByComparator<Subject> orderByComparator) {

		return getPersistence().findByname(name, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the subjects where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subjects
	 */
	public static List<Subject> findByname(
		String name, int start, int end,
		OrderByComparator<Subject> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByname(
			name, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first subject in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject
	 * @throws NoSuchSubjectException if a matching subject could not be found
	 */
	public static Subject findByname_First(
			String name, OrderByComparator<Subject> orderByComparator)
		throws com.weprode.nero.eel.synchronization.exception.
			NoSuchSubjectException {

		return getPersistence().findByname_First(name, orderByComparator);
	}

	/**
	 * Returns the first subject in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject, or <code>null</code> if a matching subject could not be found
	 */
	public static Subject fetchByname_First(
		String name, OrderByComparator<Subject> orderByComparator) {

		return getPersistence().fetchByname_First(name, orderByComparator);
	}

	/**
	 * Returns the last subject in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject
	 * @throws NoSuchSubjectException if a matching subject could not be found
	 */
	public static Subject findByname_Last(
			String name, OrderByComparator<Subject> orderByComparator)
		throws com.weprode.nero.eel.synchronization.exception.
			NoSuchSubjectException {

		return getPersistence().findByname_Last(name, orderByComparator);
	}

	/**
	 * Returns the last subject in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject, or <code>null</code> if a matching subject could not be found
	 */
	public static Subject fetchByname_Last(
		String name, OrderByComparator<Subject> orderByComparator) {

		return getPersistence().fetchByname_Last(name, orderByComparator);
	}

	/**
	 * Returns the subjects before and after the current subject in the ordered set where name = &#63;.
	 *
	 * @param subjectId the primary key of the current subject
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subject
	 * @throws NoSuchSubjectException if a subject with the primary key could not be found
	 */
	public static Subject[] findByname_PrevAndNext(
			long subjectId, String name,
			OrderByComparator<Subject> orderByComparator)
		throws com.weprode.nero.eel.synchronization.exception.
			NoSuchSubjectException {

		return getPersistence().findByname_PrevAndNext(
			subjectId, name, orderByComparator);
	}

	/**
	 * Removes all the subjects where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	public static void removeByname(String name) {
		getPersistence().removeByname(name);
	}

	/**
	 * Returns the number of subjects where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching subjects
	 */
	public static int countByname(String name) {
		return getPersistence().countByname(name);
	}

	/**
	 * Caches the subject in the entity cache if it is enabled.
	 *
	 * @param subject the subject
	 */
	public static void cacheResult(Subject subject) {
		getPersistence().cacheResult(subject);
	}

	/**
	 * Caches the subjects in the entity cache if it is enabled.
	 *
	 * @param subjects the subjects
	 */
	public static void cacheResult(List<Subject> subjects) {
		getPersistence().cacheResult(subjects);
	}

	/**
	 * Creates a new subject with the primary key. Does not add the subject to the database.
	 *
	 * @param subjectId the primary key for the new subject
	 * @return the new subject
	 */
	public static Subject create(long subjectId) {
		return getPersistence().create(subjectId);
	}

	/**
	 * Removes the subject with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subjectId the primary key of the subject
	 * @return the subject that was removed
	 * @throws NoSuchSubjectException if a subject with the primary key could not be found
	 */
	public static Subject remove(long subjectId)
		throws com.weprode.nero.eel.synchronization.exception.
			NoSuchSubjectException {

		return getPersistence().remove(subjectId);
	}

	public static Subject updateImpl(Subject subject) {
		return getPersistence().updateImpl(subject);
	}

	/**
	 * Returns the subject with the primary key or throws a <code>NoSuchSubjectException</code> if it could not be found.
	 *
	 * @param subjectId the primary key of the subject
	 * @return the subject
	 * @throws NoSuchSubjectException if a subject with the primary key could not be found
	 */
	public static Subject findByPrimaryKey(long subjectId)
		throws com.weprode.nero.eel.synchronization.exception.
			NoSuchSubjectException {

		return getPersistence().findByPrimaryKey(subjectId);
	}

	/**
	 * Returns the subject with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subjectId the primary key of the subject
	 * @return the subject, or <code>null</code> if a subject with the primary key could not be found
	 */
	public static Subject fetchByPrimaryKey(long subjectId) {
		return getPersistence().fetchByPrimaryKey(subjectId);
	}

	/**
	 * Returns all the subjects.
	 *
	 * @return the subjects
	 */
	public static List<Subject> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @return the range of subjects
	 */
	public static List<Subject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of subjects
	 */
	public static List<Subject> findAll(
		int start, int end, OrderByComparator<Subject> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of subjects
	 */
	public static List<Subject> findAll(
		int start, int end, OrderByComparator<Subject> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the subjects from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of subjects.
	 *
	 * @return the number of subjects
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SubjectPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SubjectPersistence _persistence;

}