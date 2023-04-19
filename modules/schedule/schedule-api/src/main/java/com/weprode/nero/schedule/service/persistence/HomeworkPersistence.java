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

import com.weprode.nero.schedule.exception.NoSuchHomeworkException;
import com.weprode.nero.schedule.model.Homework;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the homework service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkUtil
 * @generated
 */
@ProviderType
public interface HomeworkPersistence extends BasePersistence<Homework> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HomeworkUtil} to access the homework persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the homeworks where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @return the matching homeworks
	 */
	public java.util.List<Homework> findBytargetSessionId(long targetSessionId);

	/**
	 * Returns a range of all the homeworks where targetSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetSessionId the target session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of matching homeworks
	 */
	public java.util.List<Homework> findBytargetSessionId(
		long targetSessionId, int start, int end);

	/**
	 * Returns an ordered range of all the homeworks where targetSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetSessionId the target session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching homeworks
	 */
	public java.util.List<Homework> findBytargetSessionId(
		long targetSessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns an ordered range of all the homeworks where targetSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetSessionId the target session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching homeworks
	 */
	public java.util.List<Homework> findBytargetSessionId(
		long targetSessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public Homework findBytargetSessionId_First(
			long targetSessionId,
			com.liferay.portal.kernel.util.OrderByComparator<Homework>
				orderByComparator)
		throws NoSuchHomeworkException;

	/**
	 * Returns the first homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public Homework fetchBytargetSessionId_First(
		long targetSessionId,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns the last homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public Homework findBytargetSessionId_Last(
			long targetSessionId,
			com.liferay.portal.kernel.util.OrderByComparator<Homework>
				orderByComparator)
		throws NoSuchHomeworkException;

	/**
	 * Returns the last homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public Homework fetchBytargetSessionId_Last(
		long targetSessionId,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns the homeworks before and after the current homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param homeworkId the primary key of the current homework
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public Homework[] findBytargetSessionId_PrevAndNext(
			long homeworkId, long targetSessionId,
			com.liferay.portal.kernel.util.OrderByComparator<Homework>
				orderByComparator)
		throws NoSuchHomeworkException;

	/**
	 * Removes all the homeworks where targetSessionId = &#63; from the database.
	 *
	 * @param targetSessionId the target session ID
	 */
	public void removeBytargetSessionId(long targetSessionId);

	/**
	 * Returns the number of homeworks where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @return the number of matching homeworks
	 */
	public int countBytargetSessionId(long targetSessionId);

	/**
	 * Returns all the homeworks where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @return the matching homeworks
	 */
	public java.util.List<Homework> findBysourceSessionId(long sourceSessionId);

	/**
	 * Returns a range of all the homeworks where sourceSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param sourceSessionId the source session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of matching homeworks
	 */
	public java.util.List<Homework> findBysourceSessionId(
		long sourceSessionId, int start, int end);

	/**
	 * Returns an ordered range of all the homeworks where sourceSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param sourceSessionId the source session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching homeworks
	 */
	public java.util.List<Homework> findBysourceSessionId(
		long sourceSessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns an ordered range of all the homeworks where sourceSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param sourceSessionId the source session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching homeworks
	 */
	public java.util.List<Homework> findBysourceSessionId(
		long sourceSessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public Homework findBysourceSessionId_First(
			long sourceSessionId,
			com.liferay.portal.kernel.util.OrderByComparator<Homework>
				orderByComparator)
		throws NoSuchHomeworkException;

	/**
	 * Returns the first homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public Homework fetchBysourceSessionId_First(
		long sourceSessionId,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns the last homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public Homework findBysourceSessionId_Last(
			long sourceSessionId,
			com.liferay.portal.kernel.util.OrderByComparator<Homework>
				orderByComparator)
		throws NoSuchHomeworkException;

	/**
	 * Returns the last homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public Homework fetchBysourceSessionId_Last(
		long sourceSessionId,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns the homeworks before and after the current homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param homeworkId the primary key of the current homework
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public Homework[] findBysourceSessionId_PrevAndNext(
			long homeworkId, long sourceSessionId,
			com.liferay.portal.kernel.util.OrderByComparator<Homework>
				orderByComparator)
		throws NoSuchHomeworkException;

	/**
	 * Removes all the homeworks where sourceSessionId = &#63; from the database.
	 *
	 * @param sourceSessionId the source session ID
	 */
	public void removeBysourceSessionId(long sourceSessionId);

	/**
	 * Returns the number of homeworks where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @return the number of matching homeworks
	 */
	public int countBysourceSessionId(long sourceSessionId);

	/**
	 * Returns all the homeworks where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @return the matching homeworks
	 */
	public java.util.List<Homework> findBytargetWeekId(int targetWeekId);

	/**
	 * Returns a range of all the homeworks where targetWeekId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetWeekId the target week ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of matching homeworks
	 */
	public java.util.List<Homework> findBytargetWeekId(
		int targetWeekId, int start, int end);

	/**
	 * Returns an ordered range of all the homeworks where targetWeekId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetWeekId the target week ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching homeworks
	 */
	public java.util.List<Homework> findBytargetWeekId(
		int targetWeekId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns an ordered range of all the homeworks where targetWeekId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetWeekId the target week ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching homeworks
	 */
	public java.util.List<Homework> findBytargetWeekId(
		int targetWeekId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public Homework findBytargetWeekId_First(
			int targetWeekId,
			com.liferay.portal.kernel.util.OrderByComparator<Homework>
				orderByComparator)
		throws NoSuchHomeworkException;

	/**
	 * Returns the first homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public Homework fetchBytargetWeekId_First(
		int targetWeekId,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns the last homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	public Homework findBytargetWeekId_Last(
			int targetWeekId,
			com.liferay.portal.kernel.util.OrderByComparator<Homework>
				orderByComparator)
		throws NoSuchHomeworkException;

	/**
	 * Returns the last homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework, or <code>null</code> if a matching homework could not be found
	 */
	public Homework fetchBytargetWeekId_Last(
		int targetWeekId,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns the homeworks before and after the current homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param homeworkId the primary key of the current homework
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public Homework[] findBytargetWeekId_PrevAndNext(
			long homeworkId, int targetWeekId,
			com.liferay.portal.kernel.util.OrderByComparator<Homework>
				orderByComparator)
		throws NoSuchHomeworkException;

	/**
	 * Removes all the homeworks where targetWeekId = &#63; from the database.
	 *
	 * @param targetWeekId the target week ID
	 */
	public void removeBytargetWeekId(int targetWeekId);

	/**
	 * Returns the number of homeworks where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @return the number of matching homeworks
	 */
	public int countBytargetWeekId(int targetWeekId);

	/**
	 * Caches the homework in the entity cache if it is enabled.
	 *
	 * @param homework the homework
	 */
	public void cacheResult(Homework homework);

	/**
	 * Caches the homeworks in the entity cache if it is enabled.
	 *
	 * @param homeworks the homeworks
	 */
	public void cacheResult(java.util.List<Homework> homeworks);

	/**
	 * Creates a new homework with the primary key. Does not add the homework to the database.
	 *
	 * @param homeworkId the primary key for the new homework
	 * @return the new homework
	 */
	public Homework create(long homeworkId);

	/**
	 * Removes the homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework that was removed
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public Homework remove(long homeworkId) throws NoSuchHomeworkException;

	public Homework updateImpl(Homework homework);

	/**
	 * Returns the homework with the primary key or throws a <code>NoSuchHomeworkException</code> if it could not be found.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	public Homework findByPrimaryKey(long homeworkId)
		throws NoSuchHomeworkException;

	/**
	 * Returns the homework with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework, or <code>null</code> if a homework with the primary key could not be found
	 */
	public Homework fetchByPrimaryKey(long homeworkId);

	/**
	 * Returns all the homeworks.
	 *
	 * @return the homeworks
	 */
	public java.util.List<Homework> findAll();

	/**
	 * Returns a range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of homeworks
	 */
	public java.util.List<Homework> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of homeworks
	 */
	public java.util.List<Homework> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator);

	/**
	 * Returns an ordered range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of homeworks
	 */
	public java.util.List<Homework> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Homework>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the homeworks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of homeworks.
	 *
	 * @return the number of homeworks
	 */
	public int countAll();

}