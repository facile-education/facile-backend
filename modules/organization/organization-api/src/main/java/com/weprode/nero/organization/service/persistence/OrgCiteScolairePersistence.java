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

import com.weprode.nero.organization.exception.NoSuchOrgCiteScolaireException;
import com.weprode.nero.organization.model.OrgCiteScolaire;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the org cite scolaire service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see OrgCiteScolaireUtil
 * @generated
 */
@ProviderType
public interface OrgCiteScolairePersistence
	extends BasePersistence<OrgCiteScolaire> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OrgCiteScolaireUtil} to access the org cite scolaire persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @return the matching org cite scolaires
	 */
	public java.util.List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI);

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
	public java.util.List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI, int start, int end);

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
	public java.util.List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgCiteScolaire>
			orderByComparator);

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
	public java.util.List<OrgCiteScolaire> findByparentENTStructureUAI(
		String parentENTStructureUAI, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgCiteScolaire>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a matching org cite scolaire could not be found
	 */
	public OrgCiteScolaire findByparentENTStructureUAI_First(
			String parentENTStructureUAI,
			com.liferay.portal.kernel.util.OrderByComparator<OrgCiteScolaire>
				orderByComparator)
		throws NoSuchOrgCiteScolaireException;

	/**
	 * Returns the first org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org cite scolaire, or <code>null</code> if a matching org cite scolaire could not be found
	 */
	public OrgCiteScolaire fetchByparentENTStructureUAI_First(
		String parentENTStructureUAI,
		com.liferay.portal.kernel.util.OrderByComparator<OrgCiteScolaire>
			orderByComparator);

	/**
	 * Returns the last org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a matching org cite scolaire could not be found
	 */
	public OrgCiteScolaire findByparentENTStructureUAI_Last(
			String parentENTStructureUAI,
			com.liferay.portal.kernel.util.OrderByComparator<OrgCiteScolaire>
				orderByComparator)
		throws NoSuchOrgCiteScolaireException;

	/**
	 * Returns the last org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org cite scolaire, or <code>null</code> if a matching org cite scolaire could not be found
	 */
	public OrgCiteScolaire fetchByparentENTStructureUAI_Last(
		String parentENTStructureUAI,
		com.liferay.portal.kernel.util.OrderByComparator<OrgCiteScolaire>
			orderByComparator);

	/**
	 * Returns the org cite scolaires before and after the current org cite scolaire in the ordered set where parentENTStructureUAI = &#63;.
	 *
	 * @param childENTStructureUAI the primary key of the current org cite scolaire
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	public OrgCiteScolaire[] findByparentENTStructureUAI_PrevAndNext(
			String childENTStructureUAI, String parentENTStructureUAI,
			com.liferay.portal.kernel.util.OrderByComparator<OrgCiteScolaire>
				orderByComparator)
		throws NoSuchOrgCiteScolaireException;

	/**
	 * Removes all the org cite scolaires where parentENTStructureUAI = &#63; from the database.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 */
	public void removeByparentENTStructureUAI(String parentENTStructureUAI);

	/**
	 * Returns the number of org cite scolaires where parentENTStructureUAI = &#63;.
	 *
	 * @param parentENTStructureUAI the parent ent structure uai
	 * @return the number of matching org cite scolaires
	 */
	public int countByparentENTStructureUAI(String parentENTStructureUAI);

	/**
	 * Caches the org cite scolaire in the entity cache if it is enabled.
	 *
	 * @param orgCiteScolaire the org cite scolaire
	 */
	public void cacheResult(OrgCiteScolaire orgCiteScolaire);

	/**
	 * Caches the org cite scolaires in the entity cache if it is enabled.
	 *
	 * @param orgCiteScolaires the org cite scolaires
	 */
	public void cacheResult(java.util.List<OrgCiteScolaire> orgCiteScolaires);

	/**
	 * Creates a new org cite scolaire with the primary key. Does not add the org cite scolaire to the database.
	 *
	 * @param childENTStructureUAI the primary key for the new org cite scolaire
	 * @return the new org cite scolaire
	 */
	public OrgCiteScolaire create(String childENTStructureUAI);

	/**
	 * Removes the org cite scolaire with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire that was removed
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	public OrgCiteScolaire remove(String childENTStructureUAI)
		throws NoSuchOrgCiteScolaireException;

	public OrgCiteScolaire updateImpl(OrgCiteScolaire orgCiteScolaire);

	/**
	 * Returns the org cite scolaire with the primary key or throws a <code>NoSuchOrgCiteScolaireException</code> if it could not be found.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire
	 * @throws NoSuchOrgCiteScolaireException if a org cite scolaire with the primary key could not be found
	 */
	public OrgCiteScolaire findByPrimaryKey(String childENTStructureUAI)
		throws NoSuchOrgCiteScolaireException;

	/**
	 * Returns the org cite scolaire with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire, or <code>null</code> if a org cite scolaire with the primary key could not be found
	 */
	public OrgCiteScolaire fetchByPrimaryKey(String childENTStructureUAI);

	/**
	 * Returns all the org cite scolaires.
	 *
	 * @return the org cite scolaires
	 */
	public java.util.List<OrgCiteScolaire> findAll();

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
	public java.util.List<OrgCiteScolaire> findAll(int start, int end);

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
	public java.util.List<OrgCiteScolaire> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgCiteScolaire>
			orderByComparator);

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
	public java.util.List<OrgCiteScolaire> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OrgCiteScolaire>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the org cite scolaires from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of org cite scolaires.
	 *
	 * @return the number of org cite scolaires
	 */
	public int countAll();

}