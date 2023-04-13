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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.application.exception.NoSuchApplicationException;
import com.weprode.nero.application.model.Application;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the application service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationUtil
 * @generated
 */
@ProviderType
public interface ApplicationPersistence extends BasePersistence<Application> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicationUtil} to access the application persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the application where applicationKey = &#63; or throws a <code>NoSuchApplicationException</code> if it could not be found.
	 *
	 * @param applicationKey the application key
	 * @return the matching application
	 * @throws NoSuchApplicationException if a matching application could not be found
	 */
	public Application findByapplicationKey(String applicationKey)
		throws NoSuchApplicationException;

	/**
	 * Returns the application where applicationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationKey the application key
	 * @return the matching application, or <code>null</code> if a matching application could not be found
	 */
	public Application fetchByapplicationKey(String applicationKey);

	/**
	 * Returns the application where applicationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationKey the application key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching application, or <code>null</code> if a matching application could not be found
	 */
	public Application fetchByapplicationKey(
		String applicationKey, boolean useFinderCache);

	/**
	 * Removes the application where applicationKey = &#63; from the database.
	 *
	 * @param applicationKey the application key
	 * @return the application that was removed
	 */
	public Application removeByapplicationKey(String applicationKey)
		throws NoSuchApplicationException;

	/**
	 * Returns the number of applications where applicationKey = &#63;.
	 *
	 * @param applicationKey the application key
	 * @return the number of matching applications
	 */
	public int countByapplicationKey(String applicationKey);

	/**
	 * Caches the application in the entity cache if it is enabled.
	 *
	 * @param application the application
	 */
	public void cacheResult(Application application);

	/**
	 * Caches the applications in the entity cache if it is enabled.
	 *
	 * @param applications the applications
	 */
	public void cacheResult(java.util.List<Application> applications);

	/**
	 * Creates a new application with the primary key. Does not add the application to the database.
	 *
	 * @param applicationId the primary key for the new application
	 * @return the new application
	 */
	public Application create(long applicationId);

	/**
	 * Removes the application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application that was removed
	 * @throws NoSuchApplicationException if a application with the primary key could not be found
	 */
	public Application remove(long applicationId)
		throws NoSuchApplicationException;

	public Application updateImpl(Application application);

	/**
	 * Returns the application with the primary key or throws a <code>NoSuchApplicationException</code> if it could not be found.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application
	 * @throws NoSuchApplicationException if a application with the primary key could not be found
	 */
	public Application findByPrimaryKey(long applicationId)
		throws NoSuchApplicationException;

	/**
	 * Returns the application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application, or <code>null</code> if a application with the primary key could not be found
	 */
	public Application fetchByPrimaryKey(long applicationId);

	/**
	 * Returns all the applications.
	 *
	 * @return the applications
	 */
	public java.util.List<Application> findAll();

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
	public java.util.List<Application> findAll(int start, int end);

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
	public java.util.List<Application> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Application>
			orderByComparator);

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
	public java.util.List<Application> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Application>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the applications from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of applications.
	 *
	 * @return the number of applications
	 */
	public int countAll();

}