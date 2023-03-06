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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.organization.exception.NoSuchOrgDetailsException;
import com.weprode.nero.organization.model.OrgDetails;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the org details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see OrgDetailsUtil
 * @generated
 */
@ProviderType
public interface OrgDetailsPersistence extends BasePersistence<OrgDetails> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OrgDetailsUtil} to access the org details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the org details where schoolId = &#63; and type = &#63; and role = &#63; and isArchive = &#63; or throws a <code>NoSuchOrgDetailsException</code> if it could not be found.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param role the role
	 * @param isArchive the is archive
	 * @return the matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	public OrgDetails findByschoolId_type_role(
			long schoolId, int type, int role, boolean isArchive)
		throws NoSuchOrgDetailsException;

	/**
	 * Returns the org details where schoolId = &#63; and type = &#63; and role = &#63; and isArchive = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param role the role
	 * @param isArchive the is archive
	 * @return the matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public OrgDetails fetchByschoolId_type_role(
		long schoolId, int type, int role, boolean isArchive);

	/**
	 * Returns the org details where schoolId = &#63; and type = &#63; and role = &#63; and isArchive = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param role the role
	 * @param isArchive the is archive
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public OrgDetails fetchByschoolId_type_role(
		long schoolId, int type, int role, boolean isArchive,
		boolean useFinderCache);

	/**
	 * Removes the org details where schoolId = &#63; and type = &#63; and role = &#63; and isArchive = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param role the role
	 * @param isArchive the is archive
	 * @return the org details that was removed
	 */
	public OrgDetails removeByschoolId_type_role(
			long schoolId, int type, int role, boolean isArchive)
		throws NoSuchOrgDetailsException;

	/**
	 * Returns the number of org detailses where schoolId = &#63; and type = &#63; and role = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param role the role
	 * @param isArchive the is archive
	 * @return the number of matching org detailses
	 */
	public int countByschoolId_type_role(
		long schoolId, int type, int role, boolean isArchive);

	/**
	 * Returns all the org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @return the matching org detailses
	 */
	public java.util.List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive);

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
	public java.util.List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive, int start, int end);

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
	public java.util.List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator);

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
	public java.util.List<OrgDetails> findByschoolId_archive(
		long schoolId, boolean isArchive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	public OrgDetails findByschoolId_archive_First(
			long schoolId, boolean isArchive,
			com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
				orderByComparator)
		throws NoSuchOrgDetailsException;

	/**
	 * Returns the first org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public OrgDetails fetchByschoolId_archive_First(
		long schoolId, boolean isArchive,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator);

	/**
	 * Returns the last org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	public OrgDetails findByschoolId_archive_Last(
			long schoolId, boolean isArchive,
			com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
				orderByComparator)
		throws NoSuchOrgDetailsException;

	/**
	 * Returns the last org details in the ordered set where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public OrgDetails fetchByschoolId_archive_Last(
		long schoolId, boolean isArchive,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator);

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
	public OrgDetails[] findByschoolId_archive_PrevAndNext(
			long orgId, long schoolId, boolean isArchive,
			com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
				orderByComparator)
		throws NoSuchOrgDetailsException;

	/**
	 * Removes all the org detailses where schoolId = &#63; and isArchive = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 */
	public void removeByschoolId_archive(long schoolId, boolean isArchive);

	/**
	 * Returns the number of org detailses where schoolId = &#63; and isArchive = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param isArchive the is archive
	 * @return the number of matching org detailses
	 */
	public int countByschoolId_archive(long schoolId, boolean isArchive);

	/**
	 * Returns all the org detailses where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching org detailses
	 */
	public java.util.List<OrgDetails> findBytype(int type);

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
	public java.util.List<OrgDetails> findBytype(int type, int start, int end);

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
	public java.util.List<OrgDetails> findBytype(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator);

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
	public java.util.List<OrgDetails> findBytype(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	public OrgDetails findBytype_First(
			int type,
			com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
				orderByComparator)
		throws NoSuchOrgDetailsException;

	/**
	 * Returns the first org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public OrgDetails fetchBytype_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator);

	/**
	 * Returns the last org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details
	 * @throws NoSuchOrgDetailsException if a matching org details could not be found
	 */
	public OrgDetails findBytype_Last(
			int type,
			com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
				orderByComparator)
		throws NoSuchOrgDetailsException;

	/**
	 * Returns the last org details in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org details, or <code>null</code> if a matching org details could not be found
	 */
	public OrgDetails fetchBytype_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator);

	/**
	 * Returns the org detailses before and after the current org details in the ordered set where type = &#63;.
	 *
	 * @param orgId the primary key of the current org details
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org details
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	public OrgDetails[] findBytype_PrevAndNext(
			long orgId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
				orderByComparator)
		throws NoSuchOrgDetailsException;

	/**
	 * Removes all the org detailses where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public void removeBytype(int type);

	/**
	 * Returns the number of org detailses where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching org detailses
	 */
	public int countBytype(int type);

	/**
	 * Caches the org details in the entity cache if it is enabled.
	 *
	 * @param orgDetails the org details
	 */
	public void cacheResult(OrgDetails orgDetails);

	/**
	 * Caches the org detailses in the entity cache if it is enabled.
	 *
	 * @param orgDetailses the org detailses
	 */
	public void cacheResult(java.util.List<OrgDetails> orgDetailses);

	/**
	 * Creates a new org details with the primary key. Does not add the org details to the database.
	 *
	 * @param orgId the primary key for the new org details
	 * @return the new org details
	 */
	public OrgDetails create(long orgId);

	/**
	 * Removes the org details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details that was removed
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	public OrgDetails remove(long orgId) throws NoSuchOrgDetailsException;

	public OrgDetails updateImpl(OrgDetails orgDetails);

	/**
	 * Returns the org details with the primary key or throws a <code>NoSuchOrgDetailsException</code> if it could not be found.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details
	 * @throws NoSuchOrgDetailsException if a org details with the primary key could not be found
	 */
	public OrgDetails findByPrimaryKey(long orgId)
		throws NoSuchOrgDetailsException;

	/**
	 * Returns the org details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details, or <code>null</code> if a org details with the primary key could not be found
	 */
	public OrgDetails fetchByPrimaryKey(long orgId);

	/**
	 * Returns all the org detailses.
	 *
	 * @return the org detailses
	 */
	public java.util.List<OrgDetails> findAll();

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
	public java.util.List<OrgDetails> findAll(int start, int end);

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
	public java.util.List<OrgDetails> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator);

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
	public java.util.List<OrgDetails> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgDetails>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the org detailses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of org detailses.
	 *
	 * @return the number of org detailses
	 */
	public int countAll();

}