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

package com.weprode.nero.user.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.user.exception.NoSuchLDAPMappingException;
import com.weprode.nero.user.model.LDAPMapping;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ldap mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LDAPMappingUtil
 * @generated
 */
@ProviderType
public interface LDAPMappingPersistence extends BasePersistence<LDAPMapping> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LDAPMappingUtil} to access the ldap mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the ldap mapping where UID = &#63; or throws a <code>NoSuchLDAPMappingException</code> if it could not be found.
	 *
	 * @param UID the uid
	 * @return the matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	public LDAPMapping findByUID(String UID) throws NoSuchLDAPMappingException;

	/**
	 * Returns the ldap mapping where UID = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param UID the uid
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public LDAPMapping fetchByUID(String UID);

	/**
	 * Returns the ldap mapping where UID = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param UID the uid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public LDAPMapping fetchByUID(String UID, boolean useFinderCache);

	/**
	 * Removes the ldap mapping where UID = &#63; from the database.
	 *
	 * @param UID the uid
	 * @return the ldap mapping that was removed
	 */
	public LDAPMapping removeByUID(String UID)
		throws NoSuchLDAPMappingException;

	/**
	 * Returns the number of ldap mappings where UID = &#63;.
	 *
	 * @param UID the uid
	 * @return the number of matching ldap mappings
	 */
	public int countByUID(String UID);

	/**
	 * Returns all the ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @return the matching ldap mappings
	 */
	public java.util.List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId);

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
	public java.util.List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId, int start, int end);

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
	public java.util.List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LDAPMapping>
			orderByComparator);

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
	public java.util.List<LDAPMapping> findByEntEleveStructRattachId(
		String EntEleveStructRattachId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LDAPMapping>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	public LDAPMapping findByEntEleveStructRattachId_First(
			String EntEleveStructRattachId,
			com.liferay.portal.kernel.util.OrderByComparator<LDAPMapping>
				orderByComparator)
		throws NoSuchLDAPMappingException;

	/**
	 * Returns the first ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public LDAPMapping fetchByEntEleveStructRattachId_First(
		String EntEleveStructRattachId,
		com.liferay.portal.kernel.util.OrderByComparator<LDAPMapping>
			orderByComparator);

	/**
	 * Returns the last ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	public LDAPMapping findByEntEleveStructRattachId_Last(
			String EntEleveStructRattachId,
			com.liferay.portal.kernel.util.OrderByComparator<LDAPMapping>
				orderByComparator)
		throws NoSuchLDAPMappingException;

	/**
	 * Returns the last ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public LDAPMapping fetchByEntEleveStructRattachId_Last(
		String EntEleveStructRattachId,
		com.liferay.portal.kernel.util.OrderByComparator<LDAPMapping>
			orderByComparator);

	/**
	 * Returns the ldap mappings before and after the current ldap mapping in the ordered set where EntEleveStructRattachId = &#63;.
	 *
	 * @param UserId the primary key of the current ldap mapping
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ldap mapping
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	public LDAPMapping[] findByEntEleveStructRattachId_PrevAndNext(
			long UserId, String EntEleveStructRattachId,
			com.liferay.portal.kernel.util.OrderByComparator<LDAPMapping>
				orderByComparator)
		throws NoSuchLDAPMappingException;

	/**
	 * Removes all the ldap mappings where EntEleveStructRattachId = &#63; from the database.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 */
	public void removeByEntEleveStructRattachId(String EntEleveStructRattachId);

	/**
	 * Returns the number of ldap mappings where EntEleveStructRattachId = &#63;.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID
	 * @return the number of matching ldap mappings
	 */
	public int countByEntEleveStructRattachId(String EntEleveStructRattachId);

	/**
	 * Returns the ldap mapping where EntPersonJointure = &#63; or throws a <code>NoSuchLDAPMappingException</code> if it could not be found.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the matching ldap mapping
	 * @throws NoSuchLDAPMappingException if a matching ldap mapping could not be found
	 */
	public LDAPMapping findByEntPersonJointure(String EntPersonJointure)
		throws NoSuchLDAPMappingException;

	/**
	 * Returns the ldap mapping where EntPersonJointure = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public LDAPMapping fetchByEntPersonJointure(String EntPersonJointure);

	/**
	 * Returns the ldap mapping where EntPersonJointure = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ldap mapping, or <code>null</code> if a matching ldap mapping could not be found
	 */
	public LDAPMapping fetchByEntPersonJointure(
		String EntPersonJointure, boolean useFinderCache);

	/**
	 * Removes the ldap mapping where EntPersonJointure = &#63; from the database.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the ldap mapping that was removed
	 */
	public LDAPMapping removeByEntPersonJointure(String EntPersonJointure)
		throws NoSuchLDAPMappingException;

	/**
	 * Returns the number of ldap mappings where EntPersonJointure = &#63;.
	 *
	 * @param EntPersonJointure the ent person jointure
	 * @return the number of matching ldap mappings
	 */
	public int countByEntPersonJointure(String EntPersonJointure);

	/**
	 * Caches the ldap mapping in the entity cache if it is enabled.
	 *
	 * @param ldapMapping the ldap mapping
	 */
	public void cacheResult(LDAPMapping ldapMapping);

	/**
	 * Caches the ldap mappings in the entity cache if it is enabled.
	 *
	 * @param ldapMappings the ldap mappings
	 */
	public void cacheResult(java.util.List<LDAPMapping> ldapMappings);

	/**
	 * Creates a new ldap mapping with the primary key. Does not add the ldap mapping to the database.
	 *
	 * @param UserId the primary key for the new ldap mapping
	 * @return the new ldap mapping
	 */
	public LDAPMapping create(long UserId);

	/**
	 * Removes the ldap mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param UserId the primary key of the ldap mapping
	 * @return the ldap mapping that was removed
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	public LDAPMapping remove(long UserId) throws NoSuchLDAPMappingException;

	public LDAPMapping updateImpl(LDAPMapping ldapMapping);

	/**
	 * Returns the ldap mapping with the primary key or throws a <code>NoSuchLDAPMappingException</code> if it could not be found.
	 *
	 * @param UserId the primary key of the ldap mapping
	 * @return the ldap mapping
	 * @throws NoSuchLDAPMappingException if a ldap mapping with the primary key could not be found
	 */
	public LDAPMapping findByPrimaryKey(long UserId)
		throws NoSuchLDAPMappingException;

	/**
	 * Returns the ldap mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param UserId the primary key of the ldap mapping
	 * @return the ldap mapping, or <code>null</code> if a ldap mapping with the primary key could not be found
	 */
	public LDAPMapping fetchByPrimaryKey(long UserId);

	/**
	 * Returns all the ldap mappings.
	 *
	 * @return the ldap mappings
	 */
	public java.util.List<LDAPMapping> findAll();

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
	public java.util.List<LDAPMapping> findAll(int start, int end);

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
	public java.util.List<LDAPMapping> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LDAPMapping>
			orderByComparator);

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
	public java.util.List<LDAPMapping> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LDAPMapping>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ldap mappings from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of ldap mappings.
	 *
	 * @return the number of ldap mappings
	 */
	public int countAll();

}