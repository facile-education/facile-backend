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

package com.weprode.nero.schedule.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.schedule.exception.NoSuchTeacherGroupColorException;
import com.weprode.nero.schedule.model.TeacherGroupColor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the teacher group color service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeacherGroupColorUtil
 * @generated
 */
@ProviderType
public interface TeacherGroupColorPersistence
	extends BasePersistence<TeacherGroupColor> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TeacherGroupColorUtil} to access the teacher group color persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the teacher group colors where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findByteacherId(long teacherId);

	/**
	 * Returns a range of all the teacher group colors where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @return the range of matching teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findByteacherId(
		long teacherId, int start, int end);

	/**
	 * Returns an ordered range of all the teacher group colors where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teacher group colors where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	public TeacherGroupColor findByteacherId_First(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
				orderByComparator)
		throws NoSuchTeacherGroupColorException;

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	public TeacherGroupColor fetchByteacherId_First(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator);

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	public TeacherGroupColor findByteacherId_Last(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
				orderByComparator)
		throws NoSuchTeacherGroupColorException;

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	public TeacherGroupColor fetchByteacherId_Last(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator);

	/**
	 * Returns the teacher group colors before and after the current teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherGroupColorId the primary key of the current teacher group color
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher group color
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	public TeacherGroupColor[] findByteacherId_PrevAndNext(
			long teacherGroupColorId, long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
				orderByComparator)
		throws NoSuchTeacherGroupColorException;

	/**
	 * Removes all the teacher group colors where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public void removeByteacherId(long teacherId);

	/**
	 * Returns the number of teacher group colors where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching teacher group colors
	 */
	public int countByteacherId(long teacherId);

	/**
	 * Returns all the teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @return the matching teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId);

	/**
	 * Returns a range of all the teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @return the range of matching teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	public TeacherGroupColor findByteacherId_groupId_First(
			long teacherId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
				orderByComparator)
		throws NoSuchTeacherGroupColorException;

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	public TeacherGroupColor fetchByteacherId_groupId_First(
		long teacherId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator);

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	public TeacherGroupColor findByteacherId_groupId_Last(
			long teacherId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
				orderByComparator)
		throws NoSuchTeacherGroupColorException;

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	public TeacherGroupColor fetchByteacherId_groupId_Last(
		long teacherId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator);

	/**
	 * Returns the teacher group colors before and after the current teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherGroupColorId the primary key of the current teacher group color
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher group color
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	public TeacherGroupColor[] findByteacherId_groupId_PrevAndNext(
			long teacherGroupColorId, long teacherId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
				orderByComparator)
		throws NoSuchTeacherGroupColorException;

	/**
	 * Removes all the teacher group colors where teacherId = &#63; and groupId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 */
	public void removeByteacherId_groupId(long teacherId, long groupId);

	/**
	 * Returns the number of teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @return the number of matching teacher group colors
	 */
	public int countByteacherId_groupId(long teacherId, long groupId);

	/**
	 * Caches the teacher group color in the entity cache if it is enabled.
	 *
	 * @param teacherGroupColor the teacher group color
	 */
	public void cacheResult(TeacherGroupColor teacherGroupColor);

	/**
	 * Caches the teacher group colors in the entity cache if it is enabled.
	 *
	 * @param teacherGroupColors the teacher group colors
	 */
	public void cacheResult(
		java.util.List<TeacherGroupColor> teacherGroupColors);

	/**
	 * Creates a new teacher group color with the primary key. Does not add the teacher group color to the database.
	 *
	 * @param teacherGroupColorId the primary key for the new teacher group color
	 * @return the new teacher group color
	 */
	public TeacherGroupColor create(long teacherGroupColorId);

	/**
	 * Removes the teacher group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color that was removed
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	public TeacherGroupColor remove(long teacherGroupColorId)
		throws NoSuchTeacherGroupColorException;

	public TeacherGroupColor updateImpl(TeacherGroupColor teacherGroupColor);

	/**
	 * Returns the teacher group color with the primary key or throws a <code>NoSuchTeacherGroupColorException</code> if it could not be found.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	public TeacherGroupColor findByPrimaryKey(long teacherGroupColorId)
		throws NoSuchTeacherGroupColorException;

	/**
	 * Returns the teacher group color with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color, or <code>null</code> if a teacher group color with the primary key could not be found
	 */
	public TeacherGroupColor fetchByPrimaryKey(long teacherGroupColorId);

	/**
	 * Returns all the teacher group colors.
	 *
	 * @return the teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findAll();

	/**
	 * Returns a range of all the teacher group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @return the range of teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the teacher group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teacher group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of teacher group colors
	 */
	public java.util.List<TeacherGroupColor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeacherGroupColor>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the teacher group colors from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of teacher group colors.
	 *
	 * @return the number of teacher group colors
	 */
	public int countAll();

}