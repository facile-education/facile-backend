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

package com.weprode.facile.user.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.user.model.LDAPMapping;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the ldap mapping service. This utility wraps <code>com.weprode.facile.user.service.persistence.impl.LDAPMappingPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LDAPMappingPersistence
 * @generated
 */
public class LDAPMappingUtil {

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
	public static void clearCache(LDAPMapping ldapMapping) {
		getPersistence().clearCache(ldapMapping);
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
	public static Map<Serializable, LDAPMapping> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LDAPMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LDAPMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LDAPMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LDAPMapping> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LDAPMapping update(LDAPMapping ldapMapping) {
		return getPersistence().update(ldapMapping);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LDAPMapping update(
		LDAPMapping ldapMapping, ServiceContext serviceContext) {

		return getPersistence().update(ldapMapping, serviceContext);
	}

	/**
	 * Returns the ldap mapping where UID = &#63; or throws a <code>NoSuchLDAPMappingException</code> if it could not be found.
	 *
	 * @param UID the uid
	 * @return the matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	public static LDAPMapping findByUID(String UID)
		throws com.weprode.facile.user.exception.NoSuchLDAPMappingException {

		return getPersistence().findByUID(UID);
	}

	/**
	 * Returns the ldap mapping where UID = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param UID the uid
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public static LDAPMapping fetchByUID(String UID) {
		return getPersistence().fetchByUID(UID);
	}

	/**
	 * Returns the ldap mapping where UID = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param UID the uid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public static LDAPMapping fetchByUID(String UID, boolean useFinderCache) {
		return getPersistence().fetchByUID(UID, useFinderCache);
	}

	/**
	 * Removes the ldap mapping where UID = &#63; from the database.
	 *
	 * @param UID the uid
	 * @return the ldap mapping that was removed
	 */
	public static LDAPMapping removeByUID(String UID)
		throws com.weprode.facile.user.exception.NoSuchLDAPMappingException {

		return getPersistence().removeByUID(UID);
	}

	/**
	 * Returns the number of ldap mappings where UID = &#63;.
	 *
	 * @param UID the uid
	 * @return the number of matching ldap mappings
	 */
	public static int countByUID(String UID) {
		return getPersistence().countByUID(UID);
	}

	/**
	 * Returns all the ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @return the matching ldap mappings
	 */
	public static List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId) {

		return getPersistence().findByEntEleveStructRattachId(
			EntEleveStructRattachId);
	}

	/**
	 * Returns a range of all the ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @return the range of matching ldap mappings
	 */
	public static List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId, int start, int end) {

		return getPersistence().findByEntEleveStructRattachId(
			EntEleveStructRattachId, start, end);
	}

	/**
	 * Returns an ordered range of all the ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ldap mappings
	 */
	public static List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId, int start, int end,
		OrderByComparator<LDAPMapping> orderByComparator) {

		return getPersistence().findByEntEleveStructRattachId(
			EntEleveStructRattachId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ldap mappings
	 */
	public static List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId, int start, int end,
		OrderByComparator<LDAPMapping> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByEntEleveStructRattachId(
			EntEleveStructRattachId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	public static LDAPMapping findByEntEleveStructRattachId_First(
			String EntEleveStructRattachId,
			OrderByComparator<LDAPMapping> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchLDAPMappingException {

		return getPersistence().findByEntEleveStructRattachId_First(
			EntEleveStructRattachId, orderByComparator);
	}

	/**
	 * Returns the first ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public static LDAPMapping fetchByEntEleveStructRattachId_First(
		String EntEleveStructRattachId,
		OrderByComparator<LDAPMapping> orderByComparator) {

		return getPersistence().fetchByEntEleveStructRattachId_First(
			EntEleveStructRattachId, orderByComparator);
	}

	/**
	 * Returns the last ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	public static LDAPMapping findByEntEleveStructRattachId_Last(
			String EntEleveStructRattachId,
			OrderByComparator<LDAPMapping> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchLDAPMappingException {

		return getPersistence().findByEntEleveStructRattachId_Last(
			EntEleveStructRattachId, orderByComparator);
	}

	/**
	 * Returns the last ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public static LDAPMapping fetchByEntEleveStructRattachId_Last(
		String EntEleveStructRattachId,
		OrderByComparator<LDAPMapping> orderByComparator) {

		return getPersistence().fetchByEntEleveStructRattachId_Last(
			EntEleveStructRattachId, orderByComparator);
	}

	/**
	 * Returns the ldap mappings before and after the current ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param UserId the primary key of the current ldap mapping
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ldap mapping
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	public static LDAPMapping[] findByEntEleveStructRattachId_PrevAndNext(
			long UserId, String EntEleveStructRattachId,
			OrderByComparator<LDAPMapping> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchLDAPMappingException {

		return getPersistence().findByEntEleveStructRattachId_PrevAndNext(
			UserId, EntEleveStructRattachId, orderByComparator);
	}

	/**
	 * Removes all the ldap mappings where EntEleveStructRattachId = &#63; from the database.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 */
	public static void removeByEntEleveStructRattachId(
		String EntEleveStructRattachId) {

		getPersistence().removeByEntEleveStructRattachId(
			EntEleveStructRattachId);
	}

	/**
	 * Returns the number of ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @return the number of matching ldap mappings
	 */
	public static int countByEntEleveStructRattachId(
		String EntEleveStructRattachId) {

		return getPersistence().countByEntEleveStructRattachId(
			EntEleveStructRattachId);
	}

	/**
	 * Returns the ldap mapping where EntPersonJointure = &#63; or throws a <code>NoSuchLDAPMappingException</code> if it could not be found.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	public static LDAPMapping findByEntPersonJointure(String EntPersonJointure)
		throws com.weprode.facile.user.exception.NoSuchLDAPMappingException {

		return getPersistence().findByEntPersonJointure(EntPersonJointure);
	}

	/**
	 * Returns the ldap mapping where EntPersonJointure = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public static LDAPMapping fetchByEntPersonJointure(
		String EntPersonJointure) {

		return getPersistence().fetchByEntPersonJointure(EntPersonJointure);
	}

	/**
	 * Returns the ldap mapping where EntPersonJointure = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public static LDAPMapping fetchByEntPersonJointure(
		String EntPersonJointure, boolean useFinderCache) {

		return getPersistence().fetchByEntPersonJointure(
			EntPersonJointure, useFinderCache);
	}

	/**
	 * Removes the ldap mapping where EntPersonJointure = &#63; from the database.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the ldap mapping that was removed
	 */
	public static LDAPMapping removeByEntPersonJointure(
			String EntPersonJointure)
		throws com.weprode.facile.user.exception.NoSuchLDAPMappingException {

		return getPersistence().removeByEntPersonJointure(EntPersonJointure);
	}

	/**
	 * Returns the number of ldap mappings where EntPersonJointure = &#63;.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the number of matching ldap mappings
	 */
	public static int countByEntPersonJointure(String EntPersonJointure) {
		return getPersistence().countByEntPersonJointure(EntPersonJointure);
	}

	/**
	 * Caches the ldap mapping in the entity cache if it is enabled.
	 *
	 * @param ldapMapping the ldap mapping
	 */
	public static void cacheResult(LDAPMapping ldapMapping) {
		getPersistence().cacheResult(ldapMapping);
	}

	/**
	 * Caches the ldap mappings in the entity cache if it is enabled.
	 *
	 * @param ldapMappings the ldap mappings
	 */
	public static void cacheResult(List<LDAPMapping> ldapMappings) {
		getPersistence().cacheResult(ldapMappings);
	}

	/**
	 * Creates a new ldap mapping with the primary key. Does not add the ldap mapping to the database.
	 *
	 * @param UserId the primary key for the new ldap mapping
	 * @return the new ldap mapping
	 */
	public static LDAPMapping create(long UserId) {
		return getPersistence().create(UserId);
	}

	/**
	 * Removes the ldap mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param UserId the primary key of the ldap mapping
	 * @return the ldap mapping that was removed
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	public static LDAPMapping remove(long UserId)
		throws com.weprode.facile.user.exception.NoSuchLDAPMappingException {

		return getPersistence().remove(UserId);
	}

	public static LDAPMapping updateImpl(LDAPMapping ldapMapping) {
		return getPersistence().updateImpl(ldapMapping);
	}

	/**
	 * Returns the ldap mapping with the primary key or throws a <code>NoSuchLDAPMappingException</code> if it could not be found.
	 *
	 * @param UserId the primary key of the ldap mapping
	 * @return the ldap mapping
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	public static LDAPMapping findByPrimaryKey(long UserId)
		throws com.weprode.facile.user.exception.NoSuchLDAPMappingException {

		return getPersistence().findByPrimaryKey(UserId);
	}

	/**
	 * Returns the ldap mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param UserId the primary key of the ldap mapping
	 * @return the ldap mapping, or <code>null</code> if a ldap mapping with the primary key could not be found
	 */
	public static LDAPMapping fetchByPrimaryKey(long UserId) {
		return getPersistence().fetchByPrimaryKey(UserId);
	}

	/**
	 * Returns all the ldap mappings.
	 *
	 * @return the ldap mappings
	 */
	public static List<LDAPMapping> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the ldap mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @return the range of ldap mappings
	 */
	public static List<LDAPMapping> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the ldap mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ldap mappings
	 */
	public static List<LDAPMapping> findAll(
		int start, int end, OrderByComparator<LDAPMapping> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ldap mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ldap mappings
	 */
	public static List<LDAPMapping> findAll(
		int start, int end, OrderByComparator<LDAPMapping> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the ldap mappings from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of ldap mappings.
	 *
	 * @return the number of ldap mappings
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LDAPMappingPersistence getPersistence() {
		return _persistence;
	}

	private static volatile LDAPMappingPersistence _persistence;

}