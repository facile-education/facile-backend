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

package com.weprode.facile.organization.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.organization.exception.NoSuchClassCoursMappingException;
import com.weprode.facile.organization.model.ClassCoursMapping;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the class cours mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @see ClassCoursMappingUtil
 * @generated
 */
@ProviderType
public interface ClassCoursMappingPersistence
	extends BasePersistence<ClassCoursMapping> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ClassCoursMappingUtil} to access the class cours mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the class cours mappings where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @return the matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findByclassOrgId(long classOrgId);

	/**
	 * Returns a range of all the class cours mappings where classOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findByclassOrgId(
		long classOrgId, int start, int end);

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findByclassOrgId(
		long classOrgId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findByclassOrgId(
		long classOrgId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping findByclassOrgId_First(
			long classOrgId,
			com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
				orderByComparator)
		throws NoSuchClassCoursMappingException;

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping fetchByclassOrgId_First(
		long classOrgId,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping findByclassOrgId_Last(
			long classOrgId,
			com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
				orderByComparator)
		throws NoSuchClassCoursMappingException;

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping fetchByclassOrgId_Last(
		long classOrgId,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns the class cours mappings before and after the current class cours mapping in the ordered set where classOrgId = &#63;.
	 *
	 * @param mappingId the primary key of the current class cours mapping
	 * @param classOrgId the class org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public ClassCoursMapping[] findByclassOrgId_PrevAndNext(
			long mappingId, long classOrgId,
			com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
				orderByComparator)
		throws NoSuchClassCoursMappingException;

	/**
	 * Removes all the class cours mappings where classOrgId = &#63; from the database.
	 *
	 * @param classOrgId the class org ID
	 */
	public void removeByclassOrgId(long classOrgId);

	/**
	 * Returns the number of class cours mappings where classOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @return the number of matching class cours mappings
	 */
	public int countByclassOrgId(long classOrgId);

	/**
	 * Returns all the class cours mappings where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @return the matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findBycoursOrgId(long coursOrgId);

	/**
	 * Returns a range of all the class cours mappings where coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findBycoursOrgId(
		long coursOrgId, int start, int end);

	/**
	 * Returns an ordered range of all the class cours mappings where coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findBycoursOrgId(
		long coursOrgId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns an ordered range of all the class cours mappings where coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findBycoursOrgId(
		long coursOrgId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping findBycoursOrgId_First(
			long coursOrgId,
			com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
				orderByComparator)
		throws NoSuchClassCoursMappingException;

	/**
	 * Returns the first class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping fetchBycoursOrgId_First(
		long coursOrgId,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns the last class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping findBycoursOrgId_Last(
			long coursOrgId,
			com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
				orderByComparator)
		throws NoSuchClassCoursMappingException;

	/**
	 * Returns the last class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping fetchBycoursOrgId_Last(
		long coursOrgId,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns the class cours mappings before and after the current class cours mapping in the ordered set where coursOrgId = &#63;.
	 *
	 * @param mappingId the primary key of the current class cours mapping
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public ClassCoursMapping[] findBycoursOrgId_PrevAndNext(
			long mappingId, long coursOrgId,
			com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
				orderByComparator)
		throws NoSuchClassCoursMappingException;

	/**
	 * Removes all the class cours mappings where coursOrgId = &#63; from the database.
	 *
	 * @param coursOrgId the cours org ID
	 */
	public void removeBycoursOrgId(long coursOrgId);

	/**
	 * Returns the number of class cours mappings where coursOrgId = &#63;.
	 *
	 * @param coursOrgId the cours org ID
	 * @return the number of matching class cours mappings
	 */
	public int countBycoursOrgId(long coursOrgId);

	/**
	 * Returns all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @return the matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId);

	/**
	 * Returns a range of all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId, int start, int end);

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns an ordered range of all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findByclassOrgId_CoursOrgId(
		long classOrgId, long coursOrgId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping findByclassOrgId_CoursOrgId_First(
			long classOrgId, long coursOrgId,
			com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
				orderByComparator)
		throws NoSuchClassCoursMappingException;

	/**
	 * Returns the first class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping fetchByclassOrgId_CoursOrgId_First(
		long classOrgId, long coursOrgId,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping
	 * @throws NoSuchClassCoursMappingException if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping findByclassOrgId_CoursOrgId_Last(
			long classOrgId, long coursOrgId,
			com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
				orderByComparator)
		throws NoSuchClassCoursMappingException;

	/**
	 * Returns the last class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class cours mapping, or <code>null</code> if a matching class cours mapping could not be found
	 */
	public ClassCoursMapping fetchByclassOrgId_CoursOrgId_Last(
		long classOrgId, long coursOrgId,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns the class cours mappings before and after the current class cours mapping in the ordered set where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param mappingId the primary key of the current class cours mapping
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public ClassCoursMapping[] findByclassOrgId_CoursOrgId_PrevAndNext(
			long mappingId, long classOrgId, long coursOrgId,
			com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
				orderByComparator)
		throws NoSuchClassCoursMappingException;

	/**
	 * Removes all the class cours mappings where classOrgId = &#63; and coursOrgId = &#63; from the database.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 */
	public void removeByclassOrgId_CoursOrgId(long classOrgId, long coursOrgId);

	/**
	 * Returns the number of class cours mappings where classOrgId = &#63; and coursOrgId = &#63;.
	 *
	 * @param classOrgId the class org ID
	 * @param coursOrgId the cours org ID
	 * @return the number of matching class cours mappings
	 */
	public int countByclassOrgId_CoursOrgId(long classOrgId, long coursOrgId);

	/**
	 * Caches the class cours mapping in the entity cache if it is enabled.
	 *
	 * @param classCoursMapping the class cours mapping
	 */
	public void cacheResult(ClassCoursMapping classCoursMapping);

	/**
	 * Caches the class cours mappings in the entity cache if it is enabled.
	 *
	 * @param classCoursMappings the class cours mappings
	 */
	public void cacheResult(
		java.util.List<ClassCoursMapping> classCoursMappings);

	/**
	 * Creates a new class cours mapping with the primary key. Does not add the class cours mapping to the database.
	 *
	 * @param mappingId the primary key for the new class cours mapping
	 * @return the new class cours mapping
	 */
	public ClassCoursMapping create(long mappingId);

	/**
	 * Removes the class cours mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping that was removed
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public ClassCoursMapping remove(long mappingId)
		throws NoSuchClassCoursMappingException;

	public ClassCoursMapping updateImpl(ClassCoursMapping classCoursMapping);

	/**
	 * Returns the class cours mapping with the primary key or throws a <code>NoSuchClassCoursMappingException</code> if it could not be found.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping
	 * @throws NoSuchClassCoursMappingException if a class cours mapping with the primary key could not be found
	 */
	public ClassCoursMapping findByPrimaryKey(long mappingId)
		throws NoSuchClassCoursMappingException;

	/**
	 * Returns the class cours mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping, or <code>null</code> if a class cours mapping with the primary key could not be found
	 */
	public ClassCoursMapping fetchByPrimaryKey(long mappingId);

	/**
	 * Returns all the class cours mappings.
	 *
	 * @return the class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findAll();

	/**
	 * Returns a range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator);

	/**
	 * Returns an ordered range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of class cours mappings
	 */
	public java.util.List<ClassCoursMapping> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ClassCoursMapping>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the class cours mappings from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of class cours mappings.
	 *
	 * @return the number of class cours mappings
	 */
	public int countAll();

}