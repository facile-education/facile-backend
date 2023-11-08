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

import com.weprode.facile.organization.model.OrgCiteScolaire;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the org cite scolaire service. This utility wraps <code>com.weprode.facile.organization.service.persistence.impl.OrgCiteScolairePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see OrgCiteScolairePersistence
 * @generated
 */
public class OrgCiteScolaireUtil {

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
	public static void clearCache(OrgCiteScolaire orgCiteScolaire) {
		getPersistence().clearCache(orgCiteScolaire);
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
	public static Map<Serializable, OrgCiteScolaire> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OrgCiteScolaire> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OrgCiteScolaire> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OrgCiteScolaire> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OrgCiteScolaire> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OrgCiteScolaire update(OrgCiteScolaire orgCiteScolaire) {
		return getPersistence().update(orgCiteScolaire);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OrgCiteScolaire update(
		OrgCiteScolaire orgCiteScolaire, ServiceContext serviceContext) {

		return getPersistence().update(orgCiteScolaire, serviceContext);
	}

	/**
	 * Returns all the org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @return the matching org cite scolaires
	 */
	public static List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI) {

		return getPersistence().findByparentENTStructureUAI(
			parentENTStructureUAI);
	}

	/**
	 * Returns a range of all the org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @return the range of matching org cite scolaires
	 */
	public static List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI, int start, int end) {

		return getPersistence().findByparentENTStructureUAI(
			parentENTStructureUAI, start, end);
	}

	/**
	 * Returns an ordered range of all the org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org cite scolaires
	 */
	public static List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI, int start, int end,
		OrderByComparator<OrgCiteScolaire> orderByComparator) {

		return getPersistence().findByparentENTStructureUAI(
			parentENTStructureUAI, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org cite scolaires
	 */
	public static List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI, int start, int end,
		OrderByComparator<OrgCiteScolaire> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByparentENTStructureUAI(
			parentENTStructureUAI, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a matching org cite scolaire could not be found
	 */
	public static OrgCiteScolaire findByparentENTStructureUAI_First(
			String parentENTStructureUAI,
			OrderByComparator<OrgCiteScolaire> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchOrgCiteScolaireException {

		return getPersistence().findByparentENTStructureUAI_First(
			parentENTStructureUAI, orderByComparator);
	}

	/**
	 * Returns the first org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org cite scolaire, or <code>null</code> if a matching org cite scolaire could not be found
	 */
	public static OrgCiteScolaire fetchByparentENTStructureUAI_First(
		String parentENTStructureUAI,
		OrderByComparator<OrgCiteScolaire> orderByComparator) {

		return getPersistence().fetchByparentENTStructureUAI_First(
			parentENTStructureUAI, orderByComparator);
	}

	/**
	 * Returns the last org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a matching org cite scolaire could not be found
	 */
	public static OrgCiteScolaire findByparentENTStructureUAI_Last(
			String parentENTStructureUAI,
			OrderByComparator<OrgCiteScolaire> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchOrgCiteScolaireException {

		return getPersistence().findByparentENTStructureUAI_Last(
			parentENTStructureUAI, orderByComparator);
	}

	/**
	 * Returns the last org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org cite scolaire, or <code>null</code> if a matching org cite scolaire could not be found
	 */
	public static OrgCiteScolaire fetchByparentENTStructureUAI_Last(
		String parentENTStructureUAI,
		OrderByComparator<OrgCiteScolaire> orderByComparator) {

		return getPersistence().fetchByparentENTStructureUAI_Last(
			parentENTStructureUAI, orderByComparator);
	}

	/**
	 * Returns the org cite scolaires before and after the current org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param childENTStructureUAI the primary key of the current org cite scolaire
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	public static OrgCiteScolaire[] findByparentENTStructureUAI_PrevAndNext(
			String childENTStructureUAI, String parentENTStructureUAI,
			OrderByComparator<OrgCiteScolaire> orderByComparator)
		throws com.weprode.facile.organization.exception.
			NoSuchOrgCiteScolaireException {

		return getPersistence().findByparentENTStructureUAI_PrevAndNext(
			childENTStructureUAI, parentENTStructureUAI, orderByComparator);
	}

	/**
	 * Removes all the org cite scolaires where parentENTStructureUAI = &#63; from the database.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 */
	public static void removeByparentENTStructureUAI(
		String parentENTStructureUAI) {

		getPersistence().removeByparentENTStructureUAI(parentENTStructureUAI);
	}

	/**
	 * Returns the number of org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @return the number of matching org cite scolaires
	 */
	public static int countByparentENTStructureUAI(
		String parentENTStructureUAI) {

		return getPersistence().countByparentENTStructureUAI(
			parentENTStructureUAI);
	}

	/**
	 * Caches the org cite scolaire in the entity cache if it is enabled.
	 *
	 * @param orgCiteScolaire the org cite scolaire
	 */
	public static void cacheResult(OrgCiteScolaire orgCiteScolaire) {
		getPersistence().cacheResult(orgCiteScolaire);
	}

	/**
	 * Caches the org cite scolaires in the entity cache if it is enabled.
	 *
	 * @param orgCiteScolaires the org cite scolaires
	 */
	public static void cacheResult(List<OrgCiteScolaire> orgCiteScolaires) {
		getPersistence().cacheResult(orgCiteScolaires);
	}

	/**
	 * Creates a new org cite scolaire with the primary key. Does not add the org cite scolaire to the database.
	 *
	 * @param childENTStructureUAI the primary key for the new org cite scolaire
	 * @return the new org cite scolaire
	 */
	public static OrgCiteScolaire create(String childENTStructureUAI) {
		return getPersistence().create(childENTStructureUAI);
	}

	/**
	 * Removes the org cite scolaire with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire that was removed
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	public static OrgCiteScolaire remove(String childENTStructureUAI)
		throws com.weprode.facile.organization.exception.
			NoSuchOrgCiteScolaireException {

		return getPersistence().remove(childENTStructureUAI);
	}

	public static OrgCiteScolaire updateImpl(OrgCiteScolaire orgCiteScolaire) {
		return getPersistence().updateImpl(orgCiteScolaire);
	}

	/**
	 * Returns the org cite scolaire with the primary key or throws a <code>NoSuchOrgCiteScolaireException</code> if it could not be found.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	public static OrgCiteScolaire findByPrimaryKey(String childENTStructureUAI)
		throws com.weprode.facile.organization.exception.
			NoSuchOrgCiteScolaireException {

		return getPersistence().findByPrimaryKey(childENTStructureUAI);
	}

	/**
	 * Returns the org cite scolaire with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire, or <code>null</code> if a org cite scolaire with the primary key could not be found
	 */
	public static OrgCiteScolaire fetchByPrimaryKey(
		String childENTStructureUAI) {

		return getPersistence().fetchByPrimaryKey(childENTStructureUAI);
	}

	/**
	 * Returns all the org cite scolaires.
	 *
	 * @return the org cite scolaires
	 */
	public static List<OrgCiteScolaire> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the org cite scolaires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @return the range of org cite scolaires
	 */
	public static List<OrgCiteScolaire> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the org cite scolaires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of org cite scolaires
	 */
	public static List<OrgCiteScolaire> findAll(
		int start, int end,
		OrderByComparator<OrgCiteScolaire> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the org cite scolaires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of org cite scolaires
	 */
	public static List<OrgCiteScolaire> findAll(
		int start, int end,
		OrderByComparator<OrgCiteScolaire> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the org cite scolaires from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of org cite scolaires.
	 *
	 * @return the number of org cite scolaires
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static OrgCiteScolairePersistence getPersistence() {
		return _persistence;
	}

	private static volatile OrgCiteScolairePersistence _persistence;

}