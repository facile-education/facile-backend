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

package com.weprode.nero.organization.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.organization.model.OrgDetails;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the org details service. This utility wraps <code>com.weprode.nero.organization.service.persistence.impl.OrgDetailsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see OrgDetailsPersistence
 * @generated
 */
public class OrgDetailsUtil {

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
	public static void clearCache(OrgDetails orgDetails) {
		getPersistence().clearCache(orgDetails);
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
	public static Map<Serializable, OrgDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OrgDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OrgDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OrgDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OrgDetails> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OrgDetails update(OrgDetails orgDetails) {
		return getPersistence().update(orgDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OrgDetails update(
		OrgDetails orgDetails, ServiceContext serviceContext) {

		return getPersistence().update(orgDetails, serviceContext);
	}

	/**
	 * Returns all the org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @return the matching org detailses
	 */
	public static List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive) {

		return getPersistence().findByschoolId_archive(schoolId, isArchive);
	}

	/**
	 * Returns a range of all the org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @return the range of matching org detailses
	 */
	public static List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive, int start, int end) {

		return getPersistence().findByschoolId_archive(
			schoolId, isArchive, start, end);
	}

	/**
	 * Returns an ordered range of all the org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org detailses
	 */
	public static List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive, int start, int end,
		OrderByComparator<OrgDetails> orderByComparator) {

		return getPersistence().findByschoolId_archive(
			schoolId, isArchive, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org detailses
	 */
	public static List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive, int start, int end,
		OrderByComparator<OrgDetails> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId_archive(
			schoolId, isArchive, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	public static OrgDetails findByschoolId_archive_First(
			long schoolId, boolean isArchive,
			OrderByComparator<OrgDetails> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgDetailsException {

		return getPersistence().findByschoolId_archive_First(
			schoolId, isArchive, orderByComparator);
	}

	/**
	 * Returns the first org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public static OrgDetails fetchByschoolId_archive_First(
		long schoolId, boolean isArchive,
		OrderByComparator<OrgDetails> orderByComparator) {

		return getPersistence().fetchByschoolId_archive_First(
			schoolId, isArchive, orderByComparator);
	}

	/**
	 * Returns the last org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	public static OrgDetails findByschoolId_archive_Last(
			long schoolId, boolean isArchive,
			OrderByComparator<OrgDetails> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgDetailsException {

		return getPersistence().findByschoolId_archive_Last(
			schoolId, isArchive, orderByComparator);
	}

	/**
	 * Returns the last org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public static OrgDetails fetchByschoolId_archive_Last(
		long schoolId, boolean isArchive,
		OrderByComparator<OrgDetails> orderByComparator) {

		return getPersistence().fetchByschoolId_archive_Last(
			schoolId, isArchive, orderByComparator);
	}

	/**
	 * Returns the org detailses before and after the current org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param orgId the primary key of the current org details
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org details
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	public static OrgDetails[] findByschoolId_archive_PrevAndNext(
			long orgId, long schoolId, boolean isArchive,
			OrderByComparator<OrgDetails> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgDetailsException {

		return getPersistence().findByschoolId_archive_PrevAndNext(
			orgId, schoolId, isArchive, orderByComparator);
	}

	/**
	 * Removes all the org detailses where schoolId = &#63; and isArchive = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 */
	public static void removeByschoolId_archive(
		long schoolId, boolean isArchive) {

		getPersistence().removeByschoolId_archive(schoolId, isArchive);
	}

	/**
	 * Returns the number of org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @return the number of matching org detailses
	 */
	public static int countByschoolId_archive(
		long schoolId, boolean isArchive) {

		return getPersistence().countByschoolId_archive(schoolId, isArchive);
	}

	/**
	 * Returns all the org detailses where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching org detailses
	 */
	public static List<OrgDetails> findBytype(int type) {
		return getPersistence().findBytype(type);
	}

	/**
	 * Returns a range of all the org detailses where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @return the range of matching org detailses
	 */
	public static List<OrgDetails> findBytype(int type, int start, int end) {
		return getPersistence().findBytype(type, start, end);
	}

	/**
	 * Returns an ordered range of all the org detailses where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org detailses
	 */
	public static List<OrgDetails> findBytype(
		int type, int start, int end,
		OrderByComparator<OrgDetails> orderByComparator) {

		return getPersistence().findBytype(type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the org detailses where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org detailses
	 */
	public static List<OrgDetails> findBytype(
		int type, int start, int end,
		OrderByComparator<OrgDetails> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBytype(
			type, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	public static OrgDetails findBytype_First(
			int type, OrderByComparator<OrgDetails> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgDetailsException {

		return getPersistence().findBytype_First(type, orderByComparator);
	}

	/**
	 * Returns the first org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public static OrgDetails fetchBytype_First(
		int type, OrderByComparator<OrgDetails> orderByComparator) {

		return getPersistence().fetchBytype_First(type, orderByComparator);
	}

	/**
	 * Returns the last org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	public static OrgDetails findBytype_Last(
			int type, OrderByComparator<OrgDetails> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgDetailsException {

		return getPersistence().findBytype_Last(type, orderByComparator);
	}

	/**
	 * Returns the last org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public static OrgDetails fetchBytype_Last(
		int type, OrderByComparator<OrgDetails> orderByComparator) {

		return getPersistence().fetchBytype_Last(type, orderByComparator);
	}

	/**
	 * Returns the org detailses before and after the current org details in the ordered set where type = &#63;.
	 *
	 * @param orgId the primary key of the current org details
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org details
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	public static OrgDetails[] findBytype_PrevAndNext(
			long orgId, int type,
			OrderByComparator<OrgDetails> orderByComparator)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgDetailsException {

		return getPersistence().findBytype_PrevAndNext(
			orgId, type, orderByComparator);
	}

	/**
	 * Removes all the org detailses where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public static void removeBytype(int type) {
		getPersistence().removeBytype(type);
	}

	/**
	 * Returns the number of org detailses where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching org detailses
	 */
	public static int countBytype(int type) {
		return getPersistence().countBytype(type);
	}

	/**
	 * Caches the org details in the entity cache if it is enabled.
	 *
	 * @param orgDetails the org details
	 */
	public static void cacheResult(OrgDetails orgDetails) {
		getPersistence().cacheResult(orgDetails);
	}

	/**
	 * Caches the org detailses in the entity cache if it is enabled.
	 *
	 * @param orgDetailses the org detailses
	 */
	public static void cacheResult(List<OrgDetails> orgDetailses) {
		getPersistence().cacheResult(orgDetailses);
	}

	/**
	 * Creates a new org details with the primary key. Does not add the org details to the database.
	 *
	 * @param orgId the primary key for the new org details
	 * @return the new org details
	 */
	public static OrgDetails create(long orgId) {
		return getPersistence().create(orgId);
	}

	/**
	 * Removes the org details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details that was removed
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	public static OrgDetails remove(long orgId)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgDetailsException {

		return getPersistence().remove(orgId);
	}

	public static OrgDetails updateImpl(OrgDetails orgDetails) {
		return getPersistence().updateImpl(orgDetails);
	}

	/**
	 * Returns the org details with the primary key or throws a <code>NoSuchOrgDetailsException</code> if it could not be found.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	public static OrgDetails findByPrimaryKey(long orgId)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgDetailsException {

		return getPersistence().findByPrimaryKey(orgId);
	}

	/**
	 * Returns the org details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details, or <code>null</code> if a org details with the primary key could not be found
	 */
	public static OrgDetails fetchByPrimaryKey(long orgId) {
		return getPersistence().fetchByPrimaryKey(orgId);
	}

	/**
	 * Returns all the org detailses.
	 *
	 * @return the org detailses
	 */
	public static List<OrgDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the org detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @return the range of org detailses
	 */
	public static List<OrgDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the org detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of org detailses
	 */
	public static List<OrgDetails> findAll(
		int start, int end, OrderByComparator<OrgDetails> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the org detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of org detailses
	 */
	public static List<OrgDetails> findAll(
		int start, int end, OrderByComparator<OrgDetails> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the org detailses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of org detailses.
	 *
	 * @return the number of org detailses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static OrgDetailsPersistence getPersistence() {
		return _persistence;
	}

	private static volatile OrgDetailsPersistence _persistence;

}