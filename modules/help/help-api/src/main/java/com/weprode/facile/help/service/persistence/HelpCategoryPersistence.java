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

package com.weprode.facile.help.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.help.exception.NoSuchCategoryException;
import com.weprode.facile.help.model.HelpCategory;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the help category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpCategoryUtil
 * @generated
 */
@ProviderType
public interface HelpCategoryPersistence extends BasePersistence<HelpCategory> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelpCategoryUtil} to access the help category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the help category in the entity cache if it is enabled.
	 *
	 * @param helpCategory the help category
	 */
	public void cacheResult(HelpCategory helpCategory);

	/**
	 * Caches the help categories in the entity cache if it is enabled.
	 *
	 * @param helpCategories the help categories
	 */
	public void cacheResult(java.util.List<HelpCategory> helpCategories);

	/**
	 * Creates a new help category with the primary key. Does not add the help category to the database.
	 *
	 * @param categoryId the primary key for the new help category
	 * @return the new help category
	 */
	public HelpCategory create(long categoryId);

	/**
	 * Removes the help category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category that was removed
	 * @throws NoSuchCategoryException if a help category with the primary key could not be found
	 */
	public HelpCategory remove(long categoryId) throws NoSuchCategoryException;

	public HelpCategory updateImpl(HelpCategory helpCategory);

	/**
	 * Returns the help category with the primary key or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category
	 * @throws NoSuchCategoryException if a help category with the primary key could not be found
	 */
	public HelpCategory findByPrimaryKey(long categoryId)
		throws NoSuchCategoryException;

	/**
	 * Returns the help category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category, or <code>null</code> if a help category with the primary key could not be found
	 */
	public HelpCategory fetchByPrimaryKey(long categoryId);

	/**
	 * Returns all the help categories.
	 *
	 * @return the help categories
	 */
	public java.util.List<HelpCategory> findAll();

	/**
	 * Returns a range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @return the range of help categories
	 */
	public java.util.List<HelpCategory> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help categories
	 */
	public java.util.List<HelpCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpCategory>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help categories
	 */
	public java.util.List<HelpCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpCategory>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the help categories from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of help categories.
	 *
	 * @return the number of help categories
	 */
	public int countAll();

}