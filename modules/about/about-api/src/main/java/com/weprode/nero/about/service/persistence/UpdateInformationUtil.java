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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.about.model.UpdateInformation;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the update information service. This utility wraps <code>com.weprode.nero.about.service.persistence.impl.UpdateInformationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UpdateInformationPersistence
 * @generated
 */
public class UpdateInformationUtil {

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
	public static void clearCache(UpdateInformation updateInformation) {
		getPersistence().clearCache(updateInformation);
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
	public static Map<Serializable, UpdateInformation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UpdateInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UpdateInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UpdateInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UpdateInformation> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UpdateInformation update(
		UpdateInformation updateInformation) {

		return getPersistence().update(updateInformation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UpdateInformation update(
		UpdateInformation updateInformation, ServiceContext serviceContext) {

		return getPersistence().update(updateInformation, serviceContext);
	}

	/**
	 * Caches the update information in the entity cache if it is enabled.
	 *
	 * @param updateInformation the update information
	 */
	public static void cacheResult(UpdateInformation updateInformation) {
		getPersistence().cacheResult(updateInformation);
	}

	/**
	 * Caches the update informations in the entity cache if it is enabled.
	 *
	 * @param updateInformations the update informations
	 */
	public static void cacheResult(List<UpdateInformation> updateInformations) {
		getPersistence().cacheResult(updateInformations);
	}

	/**
	 * Creates a new update information with the primary key. Does not add the update information to the database.
	 *
	 * @param updateInfoId the primary key for the new update information
	 * @return the new update information
	 */
	public static UpdateInformation create(long updateInfoId) {
		return getPersistence().create(updateInfoId);
	}

	/**
	 * Removes the update information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information that was removed
	 * @throws NoSuchUpdateInformationException if a update information with the primary key could not be found
	 */
	public static UpdateInformation remove(long updateInfoId)
		throws com.weprode.nero.about.exception.
			NoSuchUpdateInformationException {

		return getPersistence().remove(updateInfoId);
	}

	public static UpdateInformation updateImpl(
		UpdateInformation updateInformation) {

		return getPersistence().updateImpl(updateInformation);
	}

	/**
	 * Returns the update information with the primary key or throws a <code>NoSuchUpdateInformationException</code> if it could not be found.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information
	 * @throws NoSuchUpdateInformationException if a update information with the primary key could not be found
	 */
	public static UpdateInformation findByPrimaryKey(long updateInfoId)
		throws com.weprode.nero.about.exception.
			NoSuchUpdateInformationException {

		return getPersistence().findByPrimaryKey(updateInfoId);
	}

	/**
	 * Returns the update information with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information, or <code>null</code> if a update information with the primary key could not be found
	 */
	public static UpdateInformation fetchByPrimaryKey(long updateInfoId) {
		return getPersistence().fetchByPrimaryKey(updateInfoId);
	}

	/**
	 * Returns all the update informations.
	 *
	 * @return the update informations
	 */
	public static List<UpdateInformation> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<UpdateInformation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<UpdateInformation> findAll(
		int start, int end,
		OrderByComparator<UpdateInformation> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<UpdateInformation> findAll(
		int start, int end,
		OrderByComparator<UpdateInformation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the update informations from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of update informations.
	 *
	 * @return the number of update informations
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UpdateInformationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile UpdateInformationPersistence _persistence;

}