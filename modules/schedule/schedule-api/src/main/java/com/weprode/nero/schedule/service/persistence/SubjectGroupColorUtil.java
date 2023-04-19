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

import com.weprode.nero.schedule.model.SubjectGroupColor;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the subject group color service. This utility wraps <code>com.weprode.nero.schedule.service.persistence.impl.SubjectGroupColorPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubjectGroupColorPersistence
 * @generated
 */
public class SubjectGroupColorUtil {

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
	public static void clearCache(SubjectGroupColor subjectGroupColor) {
		getPersistence().clearCache(subjectGroupColor);
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
	public static Map<Serializable, SubjectGroupColor> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SubjectGroupColor> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SubjectGroupColor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SubjectGroupColor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SubjectGroupColor update(
		SubjectGroupColor subjectGroupColor) {

		return getPersistence().update(subjectGroupColor);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SubjectGroupColor update(
		SubjectGroupColor subjectGroupColor, ServiceContext serviceContext) {

		return getPersistence().update(subjectGroupColor, serviceContext);
	}

	/**
	 * Returns all the subject group colors where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching subject group colors
	 */
	public static List<SubjectGroupColor> findBygroupId(long groupId) {
		return getPersistence().findBygroupId(groupId);
	}

	/**
	 * Returns a range of all the subject group colors where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of matching subject group colors
	 */
	public static List<SubjectGroupColor> findBygroupId(
		long groupId, int start, int end) {

		return getPersistence().findBygroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subject group colors
	 */
	public static List<SubjectGroupColor> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subject group colors
	 */
	public static List<SubjectGroupColor> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	public static SubjectGroupColor findBygroupId_First(
			long groupId,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSubjectGroupColorException {

		return getPersistence().findBygroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	public static SubjectGroupColor fetchBygroupId_First(
		long groupId, OrderByComparator<SubjectGroupColor> orderByComparator) {

		return getPersistence().fetchBygroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	public static SubjectGroupColor findBygroupId_Last(
			long groupId,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSubjectGroupColorException {

		return getPersistence().findBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	public static SubjectGroupColor fetchBygroupId_Last(
		long groupId, OrderByComparator<SubjectGroupColor> orderByComparator) {

		return getPersistence().fetchBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the subject group colors before and after the current subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param subjectGroupColorId the primary key of the current subject group color
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	public static SubjectGroupColor[] findBygroupId_PrevAndNext(
			long subjectGroupColorId, long groupId,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSubjectGroupColorException {

		return getPersistence().findBygroupId_PrevAndNext(
			subjectGroupColorId, groupId, orderByComparator);
	}

	/**
	 * Removes all the subject group colors where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeBygroupId(long groupId) {
		getPersistence().removeBygroupId(groupId);
	}

	/**
	 * Returns the number of subject group colors where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching subject group colors
	 */
	public static int countBygroupId(long groupId) {
		return getPersistence().countBygroupId(groupId);
	}

	/**
	 * Returns all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @return the matching subject group colors
	 */
	public static List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject) {

		return getPersistence().findBygroupId_subject(groupId, subject);
	}

	/**
	 * Returns a range of all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of matching subject group colors
	 */
	public static List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject, int start, int end) {

		return getPersistence().findBygroupId_subject(
			groupId, subject, start, end);
	}

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subject group colors
	 */
	public static List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject, int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		return getPersistence().findBygroupId_subject(
			groupId, subject, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subject group colors
	 */
	public static List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject, int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBygroupId_subject(
			groupId, subject, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	public static SubjectGroupColor findBygroupId_subject_First(
			long groupId, String subject,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSubjectGroupColorException {

		return getPersistence().findBygroupId_subject_First(
			groupId, subject, orderByComparator);
	}

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	public static SubjectGroupColor fetchBygroupId_subject_First(
		long groupId, String subject,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		return getPersistence().fetchBygroupId_subject_First(
			groupId, subject, orderByComparator);
	}

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	public static SubjectGroupColor findBygroupId_subject_Last(
			long groupId, String subject,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSubjectGroupColorException {

		return getPersistence().findBygroupId_subject_Last(
			groupId, subject, orderByComparator);
	}

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	public static SubjectGroupColor fetchBygroupId_subject_Last(
		long groupId, String subject,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		return getPersistence().fetchBygroupId_subject_Last(
			groupId, subject, orderByComparator);
	}

	/**
	 * Returns the subject group colors before and after the current subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param subjectGroupColorId the primary key of the current subject group color
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	public static SubjectGroupColor[] findBygroupId_subject_PrevAndNext(
			long subjectGroupColorId, long groupId, String subject,
			OrderByComparator<SubjectGroupColor> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSubjectGroupColorException {

		return getPersistence().findBygroupId_subject_PrevAndNext(
			subjectGroupColorId, groupId, subject, orderByComparator);
	}

	/**
	 * Removes all the subject group colors where groupId = &#63; and subject = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 */
	public static void removeBygroupId_subject(long groupId, String subject) {
		getPersistence().removeBygroupId_subject(groupId, subject);
	}

	/**
	 * Returns the number of subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @return the number of matching subject group colors
	 */
	public static int countBygroupId_subject(long groupId, String subject) {
		return getPersistence().countBygroupId_subject(groupId, subject);
	}

	/**
	 * Caches the subject group color in the entity cache if it is enabled.
	 *
	 * @param subjectGroupColor the subject group color
	 */
	public static void cacheResult(SubjectGroupColor subjectGroupColor) {
		getPersistence().cacheResult(subjectGroupColor);
	}

	/**
	 * Caches the subject group colors in the entity cache if it is enabled.
	 *
	 * @param subjectGroupColors the subject group colors
	 */
	public static void cacheResult(List<SubjectGroupColor> subjectGroupColors) {
		getPersistence().cacheResult(subjectGroupColors);
	}

	/**
	 * Creates a new subject group color with the primary key. Does not add the subject group color to the database.
	 *
	 * @param subjectGroupColorId the primary key for the new subject group color
	 * @return the new subject group color
	 */
	public static SubjectGroupColor create(long subjectGroupColorId) {
		return getPersistence().create(subjectGroupColorId);
	}

	/**
	 * Removes the subject group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color that was removed
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	public static SubjectGroupColor remove(long subjectGroupColorId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSubjectGroupColorException {

		return getPersistence().remove(subjectGroupColorId);
	}

	public static SubjectGroupColor updateImpl(
		SubjectGroupColor subjectGroupColor) {

		return getPersistence().updateImpl(subjectGroupColor);
	}

	/**
	 * Returns the subject group color with the primary key or throws a <code>NoSuchSubjectGroupColorException</code> if it could not be found.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	public static SubjectGroupColor findByPrimaryKey(long subjectGroupColorId)
		throws com.weprode.nero.schedule.exception.
			NoSuchSubjectGroupColorException {

		return getPersistence().findByPrimaryKey(subjectGroupColorId);
	}

	/**
	 * Returns the subject group color with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color, or <code>null</code> if a subject group color with the primary key could not be found
	 */
	public static SubjectGroupColor fetchByPrimaryKey(
		long subjectGroupColorId) {

		return getPersistence().fetchByPrimaryKey(subjectGroupColorId);
	}

	/**
	 * Returns all the subject group colors.
	 *
	 * @return the subject group colors
	 */
	public static List<SubjectGroupColor> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of subject group colors
	 */
	public static List<SubjectGroupColor> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of subject group colors
	 */
	public static List<SubjectGroupColor> findAll(
		int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of subject group colors
	 */
	public static List<SubjectGroupColor> findAll(
		int start, int end,
		OrderByComparator<SubjectGroupColor> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the subject group colors from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of subject group colors.
	 *
	 * @return the number of subject group colors
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SubjectGroupColorPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SubjectGroupColorPersistence _persistence;

}