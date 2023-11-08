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

package com.weprode.facile.school.life.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.school.life.exception.NoSuchSlotException;
import com.weprode.facile.school.life.model.SchoollifeSlot;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the schoollife slot service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlotUtil
 * @generated
 */
@ProviderType
public interface SchoollifeSlotPersistence
	extends BasePersistence<SchoollifeSlot> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SchoollifeSlotUtil} to access the schoollife slot persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the schoollife slots where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findByteacherId(long teacherId);

	/**
	 * Returns a range of all the schoollife slots where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @return the range of matching schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findByteacherId(
		long teacherId, int start, int end);

	/**
	 * Returns an ordered range of all the schoollife slots where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator);

	/**
	 * Returns an ordered range of all the schoollife slots where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findByteacherId(
		long teacherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	public SchoollifeSlot findByteacherId_First(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
				orderByComparator)
		throws NoSuchSlotException;

	/**
	 * Returns the first schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	public SchoollifeSlot fetchByteacherId_First(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator);

	/**
	 * Returns the last schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	public SchoollifeSlot findByteacherId_Last(
			long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
				orderByComparator)
		throws NoSuchSlotException;

	/**
	 * Returns the last schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	public SchoollifeSlot fetchByteacherId_Last(
		long teacherId,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator);

	/**
	 * Returns the schoollife slots before and after the current schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param schoollifeSlotId the primary key of the current schoollife slot
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife slot
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	public SchoollifeSlot[] findByteacherId_PrevAndNext(
			long schoollifeSlotId, long teacherId,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
				orderByComparator)
		throws NoSuchSlotException;

	/**
	 * Removes all the schoollife slots where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public void removeByteacherId(long teacherId);

	/**
	 * Returns the number of schoollife slots where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching schoollife slots
	 */
	public int countByteacherId(long teacherId);

	/**
	 * Returns all the schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the matching schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type);

	/**
	 * Returns a range of all the schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @return the range of matching schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type, int start, int end);

	/**
	 * Returns an ordered range of all the schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator);

	/**
	 * Returns an ordered range of all the schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	public SchoollifeSlot findByschoolId_type_First(
			long schoolId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
				orderByComparator)
		throws NoSuchSlotException;

	/**
	 * Returns the first schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	public SchoollifeSlot fetchByschoolId_type_First(
		long schoolId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator);

	/**
	 * Returns the last schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	public SchoollifeSlot findByschoolId_type_Last(
			long schoolId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
				orderByComparator)
		throws NoSuchSlotException;

	/**
	 * Returns the last schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	public SchoollifeSlot fetchByschoolId_type_Last(
		long schoolId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator);

	/**
	 * Returns the schoollife slots before and after the current schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoollifeSlotId the primary key of the current schoollife slot
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife slot
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	public SchoollifeSlot[] findByschoolId_type_PrevAndNext(
			long schoollifeSlotId, long schoolId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
				orderByComparator)
		throws NoSuchSlotException;

	/**
	 * Removes all the schoollife slots where schoolId = &#63; and type = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 */
	public void removeByschoolId_type(long schoolId, int type);

	/**
	 * Returns the number of schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the number of matching schoollife slots
	 */
	public int countByschoolId_type(long schoolId, int type);

	/**
	 * Caches the schoollife slot in the entity cache if it is enabled.
	 *
	 * @param schoollifeSlot the schoollife slot
	 */
	public void cacheResult(SchoollifeSlot schoollifeSlot);

	/**
	 * Caches the schoollife slots in the entity cache if it is enabled.
	 *
	 * @param schoollifeSlots the schoollife slots
	 */
	public void cacheResult(java.util.List<SchoollifeSlot> schoollifeSlots);

	/**
	 * Creates a new schoollife slot with the primary key. Does not add the schoollife slot to the database.
	 *
	 * @param schoollifeSlotId the primary key for the new schoollife slot
	 * @return the new schoollife slot
	 */
	public SchoollifeSlot create(long schoollifeSlotId);

	/**
	 * Removes the schoollife slot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot that was removed
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	public SchoollifeSlot remove(long schoollifeSlotId)
		throws NoSuchSlotException;

	public SchoollifeSlot updateImpl(SchoollifeSlot schoollifeSlot);

	/**
	 * Returns the schoollife slot with the primary key or throws a <code>NoSuchSlotException</code> if it could not be found.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	public SchoollifeSlot findByPrimaryKey(long schoollifeSlotId)
		throws NoSuchSlotException;

	/**
	 * Returns the schoollife slot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot, or <code>null</code> if a schoollife slot with the primary key could not be found
	 */
	public SchoollifeSlot fetchByPrimaryKey(long schoollifeSlotId);

	/**
	 * Returns all the schoollife slots.
	 *
	 * @return the schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findAll();

	/**
	 * Returns a range of all the schoollife slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @return the range of schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the schoollife slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator);

	/**
	 * Returns an ordered range of all the schoollife slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of schoollife slots
	 */
	public java.util.List<SchoollifeSlot> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchoollifeSlot>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the schoollife slots from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of schoollife slots.
	 *
	 * @return the number of schoollife slots
	 */
	public int countAll();

}