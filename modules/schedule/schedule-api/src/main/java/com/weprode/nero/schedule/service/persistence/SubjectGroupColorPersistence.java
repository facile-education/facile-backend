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

import com.weprode.nero.schedule.exception.NoSuchSubjectGroupColorException;
import com.weprode.nero.schedule.model.SubjectGroupColor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the subject group color service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubjectGroupColorUtil
 * @generated
 */
@ProviderType
public interface SubjectGroupColorPersistence
	extends BasePersistence<SubjectGroupColor> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SubjectGroupColorUtil} to access the subject group color persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the subject group colors where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching subject group colors
	 */
	public java.util.List<SubjectGroupColor> findBygroupId(long groupId);

	/**
	 * Returns a range of all the subject group colors where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of matching subject group colors
	 */
	public java.util.List<SubjectGroupColor> findBygroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subject group colors
	 */
	public java.util.List<SubjectGroupColor> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator);

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subject group colors
	 */
	public java.util.List<SubjectGroupColor> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	public SubjectGroupColor findBygroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
				orderByComparator)
		throws NoSuchSubjectGroupColorException;

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	public SubjectGroupColor fetchBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator);

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	public SubjectGroupColor findBygroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
				orderByComparator)
		throws NoSuchSubjectGroupColorException;

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	public SubjectGroupColor fetchBygroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator);

	/**
	 * Returns the subject group colors before and after the current subject group color in the ordered set where groupId = &#63;.
	 *
	 * @param subjectGroupColorId the primary key of the current subject group color
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	public SubjectGroupColor[] findBygroupId_PrevAndNext(
			long subjectGroupColorId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
				orderByComparator)
		throws NoSuchSubjectGroupColorException;

	/**
	 * Removes all the subject group colors where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeBygroupId(long groupId);

	/**
	 * Returns the number of subject group colors where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching subject group colors
	 */
	public int countBygroupId(long groupId);

	/**
	 * Returns all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @return the matching subject group colors
	 */
	public java.util.List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject);

	/**
	 * Returns a range of all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of matching subject group colors
	 */
	public java.util.List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject, int start, int end);

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subject group colors
	 */
	public java.util.List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator);

	/**
	 * Returns an ordered range of all the subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subject group colors
	 */
	public java.util.List<SubjectGroupColor> findBygroupId_subject(
		long groupId, String subject, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	public SubjectGroupColor findBygroupId_subject_First(
			long groupId, String subject,
			com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
				orderByComparator)
		throws NoSuchSubjectGroupColorException;

	/**
	 * Returns the first subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	public SubjectGroupColor fetchBygroupId_subject_First(
		long groupId, String subject,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator);

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color
	 * @throws NoSuchSubjectGroupColorException if a matching subject group color could not be found
	 */
	public SubjectGroupColor findBygroupId_subject_Last(
			long groupId, String subject,
			com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
				orderByComparator)
		throws NoSuchSubjectGroupColorException;

	/**
	 * Returns the last subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subject group color, or <code>null</code> if a matching subject group color could not be found
	 */
	public SubjectGroupColor fetchBygroupId_subject_Last(
		long groupId, String subject,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator);

	/**
	 * Returns the subject group colors before and after the current subject group color in the ordered set where groupId = &#63; and subject = &#63;.
	 *
	 * @param subjectGroupColorId the primary key of the current subject group color
	 * @param groupId the group ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	public SubjectGroupColor[] findBygroupId_subject_PrevAndNext(
			long subjectGroupColorId, long groupId, String subject,
			com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
				orderByComparator)
		throws NoSuchSubjectGroupColorException;

	/**
	 * Removes all the subject group colors where groupId = &#63; and subject = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 */
	public void removeBygroupId_subject(long groupId, String subject);

	/**
	 * Returns the number of subject group colors where groupId = &#63; and subject = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subject the subject
	 * @return the number of matching subject group colors
	 */
	public int countBygroupId_subject(long groupId, String subject);

	/**
	 * Caches the subject group color in the entity cache if it is enabled.
	 *
	 * @param subjectGroupColor the subject group color
	 */
	public void cacheResult(SubjectGroupColor subjectGroupColor);

	/**
	 * Caches the subject group colors in the entity cache if it is enabled.
	 *
	 * @param subjectGroupColors the subject group colors
	 */
	public void cacheResult(
		java.util.List<SubjectGroupColor> subjectGroupColors);

	/**
	 * Creates a new subject group color with the primary key. Does not add the subject group color to the database.
	 *
	 * @param subjectGroupColorId the primary key for the new subject group color
	 * @return the new subject group color
	 */
	public SubjectGroupColor create(long subjectGroupColorId);

	/**
	 * Removes the subject group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color that was removed
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	public SubjectGroupColor remove(long subjectGroupColorId)
		throws NoSuchSubjectGroupColorException;

	public SubjectGroupColor updateImpl(SubjectGroupColor subjectGroupColor);

	/**
	 * Returns the subject group color with the primary key or throws a <code>NoSuchSubjectGroupColorException</code> if it could not be found.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color
	 * @throws NoSuchSubjectGroupColorException if a subject group color with the primary key could not be found
	 */
	public SubjectGroupColor findByPrimaryKey(long subjectGroupColorId)
		throws NoSuchSubjectGroupColorException;

	/**
	 * Returns the subject group color with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color, or <code>null</code> if a subject group color with the primary key could not be found
	 */
	public SubjectGroupColor fetchByPrimaryKey(long subjectGroupColorId);

	/**
	 * Returns all the subject group colors.
	 *
	 * @return the subject group colors
	 */
	public java.util.List<SubjectGroupColor> findAll();

	/**
	 * Returns a range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of subject group colors
	 */
	public java.util.List<SubjectGroupColor> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of subject group colors
	 */
	public java.util.List<SubjectGroupColor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator);

	/**
	 * Returns an ordered range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of subject group colors
	 */
	public java.util.List<SubjectGroupColor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubjectGroupColor>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the subject group colors from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of subject group colors.
	 *
	 * @return the number of subject group colors
	 */
	public int countAll();

}