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

package com.weprode.nero.application.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.application.model.Application;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the application service. This utility wraps <code>com.weprode.nero.application.service.persistence.impl.ApplicationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationPersistence
 * @generated
 */
public class ApplicationUtil {

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
	public static void clearCache(Application application) {
		getPersistence().clearCache(application);
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
	public static Map<Serializable, Application> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Application> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Application> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Application> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Application> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Application update(Application application) {
		return getPersistence().update(application);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Application update(
		Application application, ServiceContext serviceContext) {

		return getPersistence().update(application, serviceContext);
	}

	/**
	 * Returns the application where applicationKey = &#63; or throws a <code>NoSuchApplicationException</code> if it could not be found.
	 *
	 * @param applicationKey the application key
	 * @return the matching application
	 * @throws NoSuchApplicationException if a matching application could not be found
	 */
	public static Application findByapplicationKey(String applicationKey)
		throws com.weprode.nero.application.exception.
			NoSuchApplicationException {

		return getPersistence().findByapplicationKey(applicationKey);
	}

	/**
	 * Returns the application where applicationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationKey the application key
	 * @return the matching application, or <code>null</code> if a matching application could not be found
	 */
	public static Application fetchByapplicationKey(String applicationKey) {
		return getPersistence().fetchByapplicationKey(applicationKey);
	}

	/**
	 * Returns the application where applicationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationKey the application key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching application, or <code>null</code> if a matching application could not be found
	 */
	public static Application fetchByapplicationKey(
		String applicationKey, boolean useFinderCache) {

		return getPersistence().fetchByapplicationKey(
			applicationKey, useFinderCache);
	}

	/**
	 * Removes the application where applicationKey = &#63; from the database.
	 *
	 * @param applicationKey the application key
	 * @return the application that was removed
	 */
	public static Application removeByapplicationKey(String applicationKey)
		throws com.weprode.nero.application.exception.
			NoSuchApplicationException {

		return getPersistence().removeByapplicationKey(applicationKey);
	}

	/**
	 * Returns the number of applications where applicationKey = &#63;.
	 *
	 * @param applicationKey the application key
	 * @return the number of matching applications
	 */
	public static int countByapplicationKey(String applicationKey) {
		return getPersistence().countByapplicationKey(applicationKey);
	}

	/**
	 * Caches the application in the entity cache if it is enabled.
	 *
	 * @param application the application
	 */
	public static void cacheResult(Application application) {
		getPersistence().cacheResult(application);
	}

	/**
	 * Caches the applications in the entity cache if it is enabled.
	 *
	 * @param applications the applications
	 */
	public static void cacheResult(List<Application> applications) {
		getPersistence().cacheResult(applications);
	}

	/**
	 * Creates a new application with the primary key. Does not add the application to the database.
	 *
	 * @param applicationId the primary key for the new application
	 * @return the new application
	 */
	public static Application create(long applicationId) {
		return getPersistence().create(applicationId);
	}

	/**
	 * Removes the application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application that was removed
	 * @throws NoSuchApplicationException if a application with the primary key could not be found
	 */
	public static Application remove(long applicationId)
		throws com.weprode.nero.application.exception.
			NoSuchApplicationException {

		return getPersistence().remove(applicationId);
	}

	public static Application updateImpl(Application application) {
		return getPersistence().updateImpl(application);
	}

	/**
	 * Returns the application with the primary key or throws a <code>NoSuchApplicationException</code> if it could not be found.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application
	 * @throws NoSuchApplicationException if a application with the primary key could not be found
	 */
	public static Application findByPrimaryKey(long applicationId)
		throws com.weprode.nero.application.exception.
			NoSuchApplicationException {

		return getPersistence().findByPrimaryKey(applicationId);
	}

	/**
	 * Returns the application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application, or <code>null</code> if a application with the primary key could not be found
	 */
	public static Application fetchByPrimaryKey(long applicationId) {
		return getPersistence().fetchByPrimaryKey(applicationId);
	}

	/**
	 * Returns all the applications.
	 *
	 * @return the applications
	 */
	public static List<Application> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of applications
	 */
	public static List<Application> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of applications
	 */
	public static List<Application> findAll(
		int start, int end, OrderByComparator<Application> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of applications
	 */
	public static List<Application> findAll(
		int start, int end, OrderByComparator<Application> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the applications from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of applications.
	 *
	 * @return the number of applications
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ApplicationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ApplicationPersistence _persistence;

}