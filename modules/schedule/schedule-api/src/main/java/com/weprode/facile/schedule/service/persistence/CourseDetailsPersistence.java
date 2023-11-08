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

package com.weprode.facile.schedule.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.schedule.exception.NoSuchCourseDetailsException;
import com.weprode.facile.schedule.model.CourseDetails;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the course details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseDetailsUtil
 * @generated
 */
@ProviderType
public interface CourseDetailsPersistence
	extends BasePersistence<CourseDetails> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseDetailsUtil} to access the course details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the course details in the entity cache if it is enabled.
	 *
	 * @param courseDetails the course details
	 */
	public void cacheResult(CourseDetails courseDetails);

	/**
	 * Caches the course detailses in the entity cache if it is enabled.
	 *
	 * @param courseDetailses the course detailses
	 */
	public void cacheResult(java.util.List<CourseDetails> courseDetailses);

	/**
	 * Creates a new course details with the primary key. Does not add the course details to the database.
	 *
	 * @param courseGroupId the primary key for the new course details
	 * @return the new course details
	 */
	public CourseDetails create(long courseGroupId);

	/**
	 * Removes the course details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details that was removed
	 * @throws NoSuchCourseDetailsException if a course details with the primary key could not be found
	 */
	public CourseDetails remove(long courseGroupId)
		throws NoSuchCourseDetailsException;

	public CourseDetails updateImpl(CourseDetails courseDetails);

	/**
	 * Returns the course details with the primary key or throws a <code>NoSuchCourseDetailsException</code> if it could not be found.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details
	 * @throws NoSuchCourseDetailsException if a course details with the primary key could not be found
	 */
	public CourseDetails findByPrimaryKey(long courseGroupId)
		throws NoSuchCourseDetailsException;

	/**
	 * Returns the course details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details, or <code>null</code> if a course details with the primary key could not be found
	 */
	public CourseDetails fetchByPrimaryKey(long courseGroupId);

	/**
	 * Returns all the course detailses.
	 *
	 * @return the course detailses
	 */
	public java.util.List<CourseDetails> findAll();

	/**
	 * Returns a range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @return the range of course detailses
	 */
	public java.util.List<CourseDetails> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of course detailses
	 */
	public java.util.List<CourseDetails> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseDetails>
			orderByComparator);

	/**
	 * Returns an ordered range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of course detailses
	 */
	public java.util.List<CourseDetails> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseDetails>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the course detailses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of course detailses.
	 *
	 * @return the number of course detailses
	 */
	public int countAll();

}