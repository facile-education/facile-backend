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

package com.weprode.nero.about.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.about.exception.NoSuchUpdateInformationException;
import com.weprode.nero.about.model.UpdateInformation;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the update information service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UpdateInformationUtil
 * @generated
 */
@ProviderType
public interface UpdateInformationPersistence
	extends BasePersistence<UpdateInformation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UpdateInformationUtil} to access the update information persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the update information in the entity cache if it is enabled.
	 *
	 * @param updateInformation the update information
	 */
	public void cacheResult(UpdateInformation updateInformation);

	/**
	 * Caches the update informations in the entity cache if it is enabled.
	 *
	 * @param updateInformations the update informations
	 */
	public void cacheResult(
		java.util.List<UpdateInformation> updateInformations);

	/**
	 * Creates a new update information with the primary key. Does not add the update information to the database.
	 *
	 * @param updateInfoId the primary key for the new update information
	 * @return the new update information
	 */
	public UpdateInformation create(long updateInfoId);

	/**
	 * Removes the update information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information that was removed
	 * @throws NoSuchUpdateInformationException if a update information with the primary key could not be found
	 */
	public UpdateInformation remove(long updateInfoId)
		throws NoSuchUpdateInformationException;

	public UpdateInformation updateImpl(UpdateInformation updateInformation);

	/**
	 * Returns the update information with the primary key or throws a <code>NoSuchUpdateInformationException</code> if it could not be found.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information
	 * @throws NoSuchUpdateInformationException if a update information with the primary key could not be found
	 */
	public UpdateInformation findByPrimaryKey(long updateInfoId)
		throws NoSuchUpdateInformationException;

	/**
	 * Returns the update information with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information, or <code>null</code> if a update information with the primary key could not be found
	 */
	public UpdateInformation fetchByPrimaryKey(long updateInfoId);

	/**
	 * Returns all the update informations.
	 *
	 * @return the update informations
	 */
	public java.util.List<UpdateInformation> findAll();

	/**
	 * Returns a range of all the update informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UpdateInformationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of update informations
	 * @param end the upper bound of the range of update informations (not inclusive)
	 * @return the range of update informations
	 */
	public java.util.List<UpdateInformation> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the update informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UpdateInformationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of update informations
	 * @param end the upper bound of the range of update informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of update informations
	 */
	public java.util.List<UpdateInformation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UpdateInformation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the update informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UpdateInformationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of update informations
	 * @param end the upper bound of the range of update informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of update informations
	 */
	public java.util.List<UpdateInformation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UpdateInformation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the update informations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of update informations.
	 *
	 * @return the number of update informations
	 */
	public int countAll();

}