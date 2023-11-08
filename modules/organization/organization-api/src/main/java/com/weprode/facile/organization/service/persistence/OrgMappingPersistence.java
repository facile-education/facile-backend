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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.organization.exception.NoSuchOrgMappingException;
import com.weprode.facile.organization.model.OrgMapping;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the org mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see OrgMappingUtil
 * @generated
 */
@ProviderType
public interface OrgMappingPersistence extends BasePersistence<OrgMapping> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OrgMappingUtil} to access the org mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the org mapping where organizationId = &#63; or throws a <code>NoSuchOrgMappingException</code> if it could not be found.
	 *
	 * @param organizationId the organization ID
	 * @return the matching org mapping
	 * @throws NoSuchOrgMappingException if a matching org mapping could not be found
	 */
	public OrgMapping findByorganisationId(long organizationId)
		throws NoSuchOrgMappingException;

	/**
	 * Returns the org mapping where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @return the matching org mapping, or <code>null</code> if a matching org mapping could not be found
	 */
	public OrgMapping fetchByorganisationId(long organizationId);

	/**
	 * Returns the org mapping where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching org mapping, or <code>null</code> if a matching org mapping could not be found
	 */
	public OrgMapping fetchByorganisationId(
		long organizationId, boolean useFinderCache);

	/**
	 * Removes the org mapping where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 * @return the org mapping that was removed
	 */
	public OrgMapping removeByorganisationId(long organizationId)
		throws NoSuchOrgMappingException;

	/**
	 * Returns the number of org mappings where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the number of matching org mappings
	 */
	public int countByorganisationId(long organizationId);

	/**
	 * Caches the org mapping in the entity cache if it is enabled.
	 *
	 * @param orgMapping the org mapping
	 */
	public void cacheResult(OrgMapping orgMapping);

	/**
	 * Caches the org mappings in the entity cache if it is enabled.
	 *
	 * @param orgMappings the org mappings
	 */
	public void cacheResult(java.util.List<OrgMapping> orgMappings);

	/**
	 * Creates a new org mapping with the primary key. Does not add the org mapping to the database.
	 *
	 * @param entStructureUAI the primary key for the new org mapping
	 * @return the new org mapping
	 */
	public OrgMapping create(String entStructureUAI);

	/**
	 * Removes the org mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping that was removed
	 * @throws NoSuchOrgMappingException if a org mapping with the primary key could not be found
	 */
	public OrgMapping remove(String entStructureUAI)
		throws NoSuchOrgMappingException;

	public OrgMapping updateImpl(OrgMapping orgMapping);

	/**
	 * Returns the org mapping with the primary key or throws a <code>NoSuchOrgMappingException</code> if it could not be found.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping
	 * @throws NoSuchOrgMappingException if a org mapping with the primary key could not be found
	 */
	public OrgMapping findByPrimaryKey(String entStructureUAI)
		throws NoSuchOrgMappingException;

	/**
	 * Returns the org mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping, or <code>null</code> if a org mapping with the primary key could not be found
	 */
	public OrgMapping fetchByPrimaryKey(String entStructureUAI);

	/**
	 * Returns all the org mappings.
	 *
	 * @return the org mappings
	 */
	public java.util.List<OrgMapping> findAll();

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
	public java.util.List<OrgMapping> findAll(int start, int end);

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
	public java.util.List<OrgMapping> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMapping>
			orderByComparator);

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
	public java.util.List<OrgMapping> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgMapping>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the org mappings from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of org mappings.
	 *
	 * @return the number of org mappings
	 */
	public int countAll();

}