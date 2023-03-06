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

import com.weprode.nero.organization.model.OrgMapping;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the org mapping service. This utility wraps <code>com.weprode.nero.organization.service.persistence.impl.OrgMappingPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see OrgMappingPersistence
 * @generated
 */
public class OrgMappingUtil {

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
	public static void clearCache(OrgMapping orgMapping) {
		getPersistence().clearCache(orgMapping);
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
	public static Map<Serializable, OrgMapping> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OrgMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OrgMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OrgMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OrgMapping> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OrgMapping update(OrgMapping orgMapping) {
		return getPersistence().update(orgMapping);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OrgMapping update(
		OrgMapping orgMapping, ServiceContext serviceContext) {

		return getPersistence().update(orgMapping, serviceContext);
	}

	/**
	 * Returns the org mapping where organizationId = &#63; or throws a <code>NoSuchOrgMappingException</code> if it could not be found.
	 *
	 * @param organizationId the organization ID
	 * @return the matching org mapping
	 * @throws NoSuchOrgMappingException if a matching org mapping could not be found
	 */
	public static OrgMapping findByorganisationId(long organizationId)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMappingException {

		return getPersistence().findByorganisationId(organizationId);
	}

	/**
	 * Returns the org mapping where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @return the matching org mapping, or <code>null</code> if a matching org mapping could not be found
	 */
	public static OrgMapping fetchByorganisationId(long organizationId) {
		return getPersistence().fetchByorganisationId(organizationId);
	}

	/**
	 * Returns the org mapping where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching org mapping, or <code>null</code> if a matching org mapping could not be found
	 */
	public static OrgMapping fetchByorganisationId(
		long organizationId, boolean useFinderCache) {

		return getPersistence().fetchByorganisationId(
			organizationId, useFinderCache);
	}

	/**
	 * Removes the org mapping where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 * @return the org mapping that was removed
	 */
	public static OrgMapping removeByorganisationId(long organizationId)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMappingException {

		return getPersistence().removeByorganisationId(organizationId);
	}

	/**
	 * Returns the number of org mappings where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the number of matching org mappings
	 */
	public static int countByorganisationId(long organizationId) {
		return getPersistence().countByorganisationId(organizationId);
	}

	/**
	 * Caches the org mapping in the entity cache if it is enabled.
	 *
	 * @param orgMapping the org mapping
	 */
	public static void cacheResult(OrgMapping orgMapping) {
		getPersistence().cacheResult(orgMapping);
	}

	/**
	 * Caches the org mappings in the entity cache if it is enabled.
	 *
	 * @param orgMappings the org mappings
	 */
	public static void cacheResult(List<OrgMapping> orgMappings) {
		getPersistence().cacheResult(orgMappings);
	}

	/**
	 * Creates a new org mapping with the primary key. Does not add the org mapping to the database.
	 *
	 * @param entStructureUAI the primary key for the new org mapping
	 * @return the new org mapping
	 */
	public static OrgMapping create(String entStructureUAI) {
		return getPersistence().create(entStructureUAI);
	}

	/**
	 * Removes the org mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping that was removed
	 * @throws NoSuchOrgMappingException if a org mapping with the primary key could not be found
	 */
	public static OrgMapping remove(String entStructureUAI)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMappingException {

		return getPersistence().remove(entStructureUAI);
	}

	public static OrgMapping updateImpl(OrgMapping orgMapping) {
		return getPersistence().updateImpl(orgMapping);
	}

	/**
	 * Returns the org mapping with the primary key or throws a <code>NoSuchOrgMappingException</code> if it could not be found.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping
	 * @throws NoSuchOrgMappingException if a org mapping with the primary key could not be found
	 */
	public static OrgMapping findByPrimaryKey(String entStructureUAI)
		throws com.weprode.nero.organization.exception.
			NoSuchOrgMappingException {

		return getPersistence().findByPrimaryKey(entStructureUAI);
	}

	/**
	 * Returns the org mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping, or <code>null</code> if a org mapping with the primary key could not be found
	 */
	public static OrgMapping fetchByPrimaryKey(String entStructureUAI) {
		return getPersistence().fetchByPrimaryKey(entStructureUAI);
	}

	/**
	 * Returns all the org mappings.
	 *
	 * @return the org mappings
	 */
	public static List<OrgMapping> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the org mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org mappings
	 * @param end the upper bound of the range of org mappings (not inclusive)
	 * @return the range of org mappings
	 */
	public static List<OrgMapping> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the org mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org mappings
	 * @param end the upper bound of the range of org mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of org mappings
	 */
	public static List<OrgMapping> findAll(
		int start, int end, OrderByComparator<OrgMapping> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the org mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org mappings
	 * @param end the upper bound of the range of org mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of org mappings
	 */
	public static List<OrgMapping> findAll(
		int start, int end, OrderByComparator<OrgMapping> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the org mappings from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of org mappings.
	 *
	 * @return the number of org mappings
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static OrgMappingPersistence getPersistence() {
		return _persistence;
	}

	private static volatile OrgMappingPersistence _persistence;

}