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

package com.weprode.nero.course.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;

import com.weprode.nero.course.exception.NoSuchStudentHomeworkException;
import com.weprode.nero.course.model.StudentHomework;
import com.weprode.nero.course.model.StudentHomeworkTable;
import com.weprode.nero.course.model.impl.StudentHomeworkImpl;
import com.weprode.nero.course.model.impl.StudentHomeworkModelImpl;
import com.weprode.nero.course.service.persistence.StudentHomeworkPersistence;
import com.weprode.nero.course.service.persistence.StudentHomeworkUtil;
import com.weprode.nero.course.service.persistence.impl.constants.CoursePersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the student homework service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {StudentHomeworkPersistence.class, BasePersistence.class})
public class StudentHomeworkPersistenceImpl
	extends BasePersistenceImpl<StudentHomework>
	implements StudentHomeworkPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>StudentHomeworkUtil</code> to access the student homework persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		StudentHomeworkImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByhomeworkId;
	private FinderPath _finderPathWithoutPaginationFindByhomeworkId;
	private FinderPath _finderPathCountByhomeworkId;

	/**
	 * Returns all the student homeworks where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching student homeworks
	 */
	@Override
	public List<StudentHomework> findByhomeworkId(long homeworkId) {
		return findByhomeworkId(
			homeworkId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the student homeworks where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of matching student homeworks
	 */
	@Override
	public List<StudentHomework> findByhomeworkId(
		long homeworkId, int start, int end) {

		return findByhomeworkId(homeworkId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the student homeworks where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching student homeworks
	 */
	@Override
	public List<StudentHomework> findByhomeworkId(
		long homeworkId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator) {

		return findByhomeworkId(
			homeworkId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the student homeworks where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching student homeworks
	 */
	@Override
	public List<StudentHomework> findByhomeworkId(
		long homeworkId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByhomeworkId;
				finderArgs = new Object[] {homeworkId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByhomeworkId;
			finderArgs = new Object[] {
				homeworkId, start, end, orderByComparator
			};
		}

		List<StudentHomework> list = null;

		if (useFinderCache) {
			list = (List<StudentHomework>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StudentHomework studentHomework : list) {
					if (homeworkId != studentHomework.getHomeworkId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_STUDENTHOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_HOMEWORKID_HOMEWORKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(StudentHomeworkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(homeworkId);

				list = (List<StudentHomework>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	@Override
	public StudentHomework findByhomeworkId_First(
			long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = fetchByhomeworkId_First(
			homeworkId, orderByComparator);

		if (studentHomework != null) {
			return studentHomework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("homeworkId=");
		sb.append(homeworkId);

		sb.append("}");

		throw new NoSuchStudentHomeworkException(sb.toString());
	}

	/**
	 * Returns the first student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	@Override
	public StudentHomework fetchByhomeworkId_First(
		long homeworkId, OrderByComparator<StudentHomework> orderByComparator) {

		List<StudentHomework> list = findByhomeworkId(
			homeworkId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	@Override
	public StudentHomework findByhomeworkId_Last(
			long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = fetchByhomeworkId_Last(
			homeworkId, orderByComparator);

		if (studentHomework != null) {
			return studentHomework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("homeworkId=");
		sb.append(homeworkId);

		sb.append("}");

		throw new NoSuchStudentHomeworkException(sb.toString());
	}

	/**
	 * Returns the last student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	@Override
	public StudentHomework fetchByhomeworkId_Last(
		long homeworkId, OrderByComparator<StudentHomework> orderByComparator) {

		int count = countByhomeworkId(homeworkId);

		if (count == 0) {
			return null;
		}

		List<StudentHomework> list = findByhomeworkId(
			homeworkId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the student homeworks before and after the current student homework in the ordered set where homeworkId = &#63;.
	 *
	 * @param studentHomeworkId the primary key of the current student homework
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	@Override
	public StudentHomework[] findByhomeworkId_PrevAndNext(
			long studentHomeworkId, long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = findByPrimaryKey(studentHomeworkId);

		Session session = null;

		try {
			session = openSession();

			StudentHomework[] array = new StudentHomeworkImpl[3];

			array[0] = getByhomeworkId_PrevAndNext(
				session, studentHomework, homeworkId, orderByComparator, true);

			array[1] = studentHomework;

			array[2] = getByhomeworkId_PrevAndNext(
				session, studentHomework, homeworkId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected StudentHomework getByhomeworkId_PrevAndNext(
		Session session, StudentHomework studentHomework, long homeworkId,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_STUDENTHOMEWORK_WHERE);

		sb.append(_FINDER_COLUMN_HOMEWORKID_HOMEWORKID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(StudentHomeworkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(homeworkId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						studentHomework)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<StudentHomework> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the student homeworks where homeworkId = &#63; from the database.
	 *
	 * @param homeworkId the homework ID
	 */
	@Override
	public void removeByhomeworkId(long homeworkId) {
		for (StudentHomework studentHomework :
				findByhomeworkId(
					homeworkId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(studentHomework);
		}
	}

	/**
	 * Returns the number of student homeworks where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the number of matching student homeworks
	 */
	@Override
	public int countByhomeworkId(long homeworkId) {
		FinderPath finderPath = _finderPathCountByhomeworkId;

		Object[] finderArgs = new Object[] {homeworkId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_STUDENTHOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_HOMEWORKID_HOMEWORKID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(homeworkId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_HOMEWORKID_HOMEWORKID_2 =
		"studentHomework.homeworkId = ?";

	private FinderPath _finderPathWithPaginationFindBystudentId;
	private FinderPath _finderPathWithoutPaginationFindBystudentId;
	private FinderPath _finderPathCountBystudentId;

	/**
	 * Returns all the student homeworks where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the matching student homeworks
	 */
	@Override
	public List<StudentHomework> findBystudentId(long studentId) {
		return findBystudentId(
			studentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the student homeworks where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of matching student homeworks
	 */
	@Override
	public List<StudentHomework> findBystudentId(
		long studentId, int start, int end) {

		return findBystudentId(studentId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the student homeworks where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching student homeworks
	 */
	@Override
	public List<StudentHomework> findBystudentId(
		long studentId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator) {

		return findBystudentId(studentId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the student homeworks where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching student homeworks
	 */
	@Override
	public List<StudentHomework> findBystudentId(
		long studentId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBystudentId;
				finderArgs = new Object[] {studentId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBystudentId;
			finderArgs = new Object[] {
				studentId, start, end, orderByComparator
			};
		}

		List<StudentHomework> list = null;

		if (useFinderCache) {
			list = (List<StudentHomework>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StudentHomework studentHomework : list) {
					if (studentId != studentHomework.getStudentId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_STUDENTHOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_STUDENTID_STUDENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(StudentHomeworkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(studentId);

				list = (List<StudentHomework>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	@Override
	public StudentHomework findBystudentId_First(
			long studentId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = fetchBystudentId_First(
			studentId, orderByComparator);

		if (studentHomework != null) {
			return studentHomework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("studentId=");
		sb.append(studentId);

		sb.append("}");

		throw new NoSuchStudentHomeworkException(sb.toString());
	}

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	@Override
	public StudentHomework fetchBystudentId_First(
		long studentId, OrderByComparator<StudentHomework> orderByComparator) {

		List<StudentHomework> list = findBystudentId(
			studentId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	@Override
	public StudentHomework findBystudentId_Last(
			long studentId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = fetchBystudentId_Last(
			studentId, orderByComparator);

		if (studentHomework != null) {
			return studentHomework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("studentId=");
		sb.append(studentId);

		sb.append("}");

		throw new NoSuchStudentHomeworkException(sb.toString());
	}

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	@Override
	public StudentHomework fetchBystudentId_Last(
		long studentId, OrderByComparator<StudentHomework> orderByComparator) {

		int count = countBystudentId(studentId);

		if (count == 0) {
			return null;
		}

		List<StudentHomework> list = findBystudentId(
			studentId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the student homeworks before and after the current student homework in the ordered set where studentId = &#63;.
	 *
	 * @param studentHomeworkId the primary key of the current student homework
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	@Override
	public StudentHomework[] findBystudentId_PrevAndNext(
			long studentHomeworkId, long studentId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = findByPrimaryKey(studentHomeworkId);

		Session session = null;

		try {
			session = openSession();

			StudentHomework[] array = new StudentHomeworkImpl[3];

			array[0] = getBystudentId_PrevAndNext(
				session, studentHomework, studentId, orderByComparator, true);

			array[1] = studentHomework;

			array[2] = getBystudentId_PrevAndNext(
				session, studentHomework, studentId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected StudentHomework getBystudentId_PrevAndNext(
		Session session, StudentHomework studentHomework, long studentId,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_STUDENTHOMEWORK_WHERE);

		sb.append(_FINDER_COLUMN_STUDENTID_STUDENTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(StudentHomeworkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(studentId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						studentHomework)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<StudentHomework> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the student homeworks where studentId = &#63; from the database.
	 *
	 * @param studentId the student ID
	 */
	@Override
	public void removeBystudentId(long studentId) {
		for (StudentHomework studentHomework :
				findBystudentId(
					studentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(studentHomework);
		}
	}

	/**
	 * Returns the number of student homeworks where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the number of matching student homeworks
	 */
	@Override
	public int countBystudentId(long studentId) {
		FinderPath finderPath = _finderPathCountBystudentId;

		Object[] finderArgs = new Object[] {studentId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_STUDENTHOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_STUDENTID_STUDENTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(studentId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_STUDENTID_STUDENTID_2 =
		"studentHomework.studentId = ?";

	private FinderPath _finderPathWithPaginationFindBystudentId_homeworkId;
	private FinderPath _finderPathWithoutPaginationFindBystudentId_homeworkId;
	private FinderPath _finderPathCountBystudentId_homeworkId;

	/**
	 * Returns all the student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @return the matching student homeworks
	 */
	@Override
	public List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId) {

		return findBystudentId_homeworkId(
			studentId, homeworkId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of matching student homeworks
	 */
	@Override
	public List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId, int start, int end) {

		return findBystudentId_homeworkId(
			studentId, homeworkId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching student homeworks
	 */
	@Override
	public List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator) {

		return findBystudentId_homeworkId(
			studentId, homeworkId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching student homeworks
	 */
	@Override
	public List<StudentHomework> findBystudentId_homeworkId(
		long studentId, long homeworkId, int start, int end,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindBystudentId_homeworkId;
				finderArgs = new Object[] {studentId, homeworkId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBystudentId_homeworkId;
			finderArgs = new Object[] {
				studentId, homeworkId, start, end, orderByComparator
			};
		}

		List<StudentHomework> list = null;

		if (useFinderCache) {
			list = (List<StudentHomework>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StudentHomework studentHomework : list) {
					if ((studentId != studentHomework.getStudentId()) ||
						(homeworkId != studentHomework.getHomeworkId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_STUDENTHOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_STUDENTID_HOMEWORKID_STUDENTID_2);

			sb.append(_FINDER_COLUMN_STUDENTID_HOMEWORKID_HOMEWORKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(StudentHomeworkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(studentId);

				queryPos.add(homeworkId);

				list = (List<StudentHomework>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	@Override
	public StudentHomework findBystudentId_homeworkId_First(
			long studentId, long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = fetchBystudentId_homeworkId_First(
			studentId, homeworkId, orderByComparator);

		if (studentHomework != null) {
			return studentHomework;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("studentId=");
		sb.append(studentId);

		sb.append(", homeworkId=");
		sb.append(homeworkId);

		sb.append("}");

		throw new NoSuchStudentHomeworkException(sb.toString());
	}

	/**
	 * Returns the first student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	@Override
	public StudentHomework fetchBystudentId_homeworkId_First(
		long studentId, long homeworkId,
		OrderByComparator<StudentHomework> orderByComparator) {

		List<StudentHomework> list = findBystudentId_homeworkId(
			studentId, homeworkId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework
	 * @throws NoSuchStudentHomeworkException if a matching student homework could not be found
	 */
	@Override
	public StudentHomework findBystudentId_homeworkId_Last(
			long studentId, long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = fetchBystudentId_homeworkId_Last(
			studentId, homeworkId, orderByComparator);

		if (studentHomework != null) {
			return studentHomework;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("studentId=");
		sb.append(studentId);

		sb.append(", homeworkId=");
		sb.append(homeworkId);

		sb.append("}");

		throw new NoSuchStudentHomeworkException(sb.toString());
	}

	/**
	 * Returns the last student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student homework, or <code>null</code> if a matching student homework could not be found
	 */
	@Override
	public StudentHomework fetchBystudentId_homeworkId_Last(
		long studentId, long homeworkId,
		OrderByComparator<StudentHomework> orderByComparator) {

		int count = countBystudentId_homeworkId(studentId, homeworkId);

		if (count == 0) {
			return null;
		}

		List<StudentHomework> list = findBystudentId_homeworkId(
			studentId, homeworkId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the student homeworks before and after the current student homework in the ordered set where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentHomeworkId the primary key of the current student homework
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	@Override
	public StudentHomework[] findBystudentId_homeworkId_PrevAndNext(
			long studentHomeworkId, long studentId, long homeworkId,
			OrderByComparator<StudentHomework> orderByComparator)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = findByPrimaryKey(studentHomeworkId);

		Session session = null;

		try {
			session = openSession();

			StudentHomework[] array = new StudentHomeworkImpl[3];

			array[0] = getBystudentId_homeworkId_PrevAndNext(
				session, studentHomework, studentId, homeworkId,
				orderByComparator, true);

			array[1] = studentHomework;

			array[2] = getBystudentId_homeworkId_PrevAndNext(
				session, studentHomework, studentId, homeworkId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected StudentHomework getBystudentId_homeworkId_PrevAndNext(
		Session session, StudentHomework studentHomework, long studentId,
		long homeworkId, OrderByComparator<StudentHomework> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_STUDENTHOMEWORK_WHERE);

		sb.append(_FINDER_COLUMN_STUDENTID_HOMEWORKID_STUDENTID_2);

		sb.append(_FINDER_COLUMN_STUDENTID_HOMEWORKID_HOMEWORKID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(StudentHomeworkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(studentId);

		queryPos.add(homeworkId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						studentHomework)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<StudentHomework> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the student homeworks where studentId = &#63; and homeworkId = &#63; from the database.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 */
	@Override
	public void removeBystudentId_homeworkId(long studentId, long homeworkId) {
		for (StudentHomework studentHomework :
				findBystudentId_homeworkId(
					studentId, homeworkId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(studentHomework);
		}
	}

	/**
	 * Returns the number of student homeworks where studentId = &#63; and homeworkId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param homeworkId the homework ID
	 * @return the number of matching student homeworks
	 */
	@Override
	public int countBystudentId_homeworkId(long studentId, long homeworkId) {
		FinderPath finderPath = _finderPathCountBystudentId_homeworkId;

		Object[] finderArgs = new Object[] {studentId, homeworkId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_STUDENTHOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_STUDENTID_HOMEWORKID_STUDENTID_2);

			sb.append(_FINDER_COLUMN_STUDENTID_HOMEWORKID_HOMEWORKID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(studentId);

				queryPos.add(homeworkId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_STUDENTID_HOMEWORKID_STUDENTID_2 =
			"studentHomework.studentId = ? AND ";

	private static final String
		_FINDER_COLUMN_STUDENTID_HOMEWORKID_HOMEWORKID_2 =
			"studentHomework.homeworkId = ?";

	public StudentHomeworkPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("comment", "comment_");

		setDBColumnNames(dbColumnNames);

		setModelClass(StudentHomework.class);

		setModelImplClass(StudentHomeworkImpl.class);
		setModelPKClass(long.class);

		setTable(StudentHomeworkTable.INSTANCE);
	}

	/**
	 * Caches the student homework in the entity cache if it is enabled.
	 *
	 * @param studentHomework the student homework
	 */
	@Override
	public void cacheResult(StudentHomework studentHomework) {
		entityCache.putResult(
			StudentHomeworkImpl.class, studentHomework.getPrimaryKey(),
			studentHomework);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the student homeworks in the entity cache if it is enabled.
	 *
	 * @param studentHomeworks the student homeworks
	 */
	@Override
	public void cacheResult(List<StudentHomework> studentHomeworks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (studentHomeworks.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (StudentHomework studentHomework : studentHomeworks) {
			if (entityCache.getResult(
					StudentHomeworkImpl.class,
					studentHomework.getPrimaryKey()) == null) {

				cacheResult(studentHomework);
			}
		}
	}

	/**
	 * Clears the cache for all student homeworks.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StudentHomeworkImpl.class);

		finderCache.clearCache(StudentHomeworkImpl.class);
	}

	/**
	 * Clears the cache for the student homework.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StudentHomework studentHomework) {
		entityCache.removeResult(StudentHomeworkImpl.class, studentHomework);
	}

	@Override
	public void clearCache(List<StudentHomework> studentHomeworks) {
		for (StudentHomework studentHomework : studentHomeworks) {
			entityCache.removeResult(
				StudentHomeworkImpl.class, studentHomework);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(StudentHomeworkImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(StudentHomeworkImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new student homework with the primary key. Does not add the student homework to the database.
	 *
	 * @param studentHomeworkId the primary key for the new student homework
	 * @return the new student homework
	 */
	@Override
	public StudentHomework create(long studentHomeworkId) {
		StudentHomework studentHomework = new StudentHomeworkImpl();

		studentHomework.setNew(true);
		studentHomework.setPrimaryKey(studentHomeworkId);

		return studentHomework;
	}

	/**
	 * Removes the student homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework that was removed
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	@Override
	public StudentHomework remove(long studentHomeworkId)
		throws NoSuchStudentHomeworkException {

		return remove((Serializable)studentHomeworkId);
	}

	/**
	 * Removes the student homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the student homework
	 * @return the student homework that was removed
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	@Override
	public StudentHomework remove(Serializable primaryKey)
		throws NoSuchStudentHomeworkException {

		Session session = null;

		try {
			session = openSession();

			StudentHomework studentHomework = (StudentHomework)session.get(
				StudentHomeworkImpl.class, primaryKey);

			if (studentHomework == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStudentHomeworkException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(studentHomework);
		}
		catch (NoSuchStudentHomeworkException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected StudentHomework removeImpl(StudentHomework studentHomework) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(studentHomework)) {
				studentHomework = (StudentHomework)session.get(
					StudentHomeworkImpl.class,
					studentHomework.getPrimaryKeyObj());
			}

			if (studentHomework != null) {
				session.delete(studentHomework);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (studentHomework != null) {
			clearCache(studentHomework);
		}

		return studentHomework;
	}

	@Override
	public StudentHomework updateImpl(StudentHomework studentHomework) {
		boolean isNew = studentHomework.isNew();

		if (!(studentHomework instanceof StudentHomeworkModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(studentHomework.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					studentHomework);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in studentHomework proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom StudentHomework implementation " +
					studentHomework.getClass());
		}

		StudentHomeworkModelImpl studentHomeworkModelImpl =
			(StudentHomeworkModelImpl)studentHomework;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(studentHomework);
			}
			else {
				studentHomework = (StudentHomework)session.merge(
					studentHomework);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			StudentHomeworkImpl.class, studentHomeworkModelImpl, false, true);

		if (isNew) {
			studentHomework.setNew(false);
		}

		studentHomework.resetOriginalValues();

		return studentHomework;
	}

	/**
	 * Returns the student homework with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the student homework
	 * @return the student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	@Override
	public StudentHomework findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStudentHomeworkException {

		StudentHomework studentHomework = fetchByPrimaryKey(primaryKey);

		if (studentHomework == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStudentHomeworkException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return studentHomework;
	}

	/**
	 * Returns the student homework with the primary key or throws a <code>NoSuchStudentHomeworkException</code> if it could not be found.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework
	 * @throws NoSuchStudentHomeworkException if a student homework with the primary key could not be found
	 */
	@Override
	public StudentHomework findByPrimaryKey(long studentHomeworkId)
		throws NoSuchStudentHomeworkException {

		return findByPrimaryKey((Serializable)studentHomeworkId);
	}

	/**
	 * Returns the student homework with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework, or <code>null</code> if a student homework with the primary key could not be found
	 */
	@Override
	public StudentHomework fetchByPrimaryKey(long studentHomeworkId) {
		return fetchByPrimaryKey((Serializable)studentHomeworkId);
	}

	/**
	 * Returns all the student homeworks.
	 *
	 * @return the student homeworks
	 */
	@Override
	public List<StudentHomework> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the student homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of student homeworks
	 */
	@Override
	public List<StudentHomework> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the student homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of student homeworks
	 */
	@Override
	public List<StudentHomework> findAll(
		int start, int end,
		OrderByComparator<StudentHomework> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the student homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of student homeworks
	 */
	@Override
	public List<StudentHomework> findAll(
		int start, int end,
		OrderByComparator<StudentHomework> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<StudentHomework> list = null;

		if (useFinderCache) {
			list = (List<StudentHomework>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_STUDENTHOMEWORK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_STUDENTHOMEWORK;

				sql = sql.concat(StudentHomeworkModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<StudentHomework>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the student homeworks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StudentHomework studentHomework : findAll()) {
			remove(studentHomework);
		}
	}

	/**
	 * Returns the number of student homeworks.
	 *
	 * @return the number of student homeworks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_STUDENTHOMEWORK);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "studentHomeworkId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_STUDENTHOMEWORK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return StudentHomeworkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the student homework persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByhomeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByhomeworkId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"homeworkId"}, true);

		_finderPathWithoutPaginationFindByhomeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByhomeworkId",
			new String[] {Long.class.getName()}, new String[] {"homeworkId"},
			true);

		_finderPathCountByhomeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByhomeworkId",
			new String[] {Long.class.getName()}, new String[] {"homeworkId"},
			false);

		_finderPathWithPaginationFindBystudentId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBystudentId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"studentId"}, true);

		_finderPathWithoutPaginationFindBystudentId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBystudentId",
			new String[] {Long.class.getName()}, new String[] {"studentId"},
			true);

		_finderPathCountBystudentId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBystudentId",
			new String[] {Long.class.getName()}, new String[] {"studentId"},
			false);

		_finderPathWithPaginationFindBystudentId_homeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBystudentId_homeworkId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"studentId", "homeworkId"}, true);

		_finderPathWithoutPaginationFindBystudentId_homeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBystudentId_homeworkId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"studentId", "homeworkId"}, true);

		_finderPathCountBystudentId_homeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBystudentId_homeworkId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"studentId", "homeworkId"}, false);

		_setStudentHomeworkUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setStudentHomeworkUtilPersistence(null);

		entityCache.removeCache(StudentHomeworkImpl.class.getName());
	}

	private void _setStudentHomeworkUtilPersistence(
		StudentHomeworkPersistence studentHomeworkPersistence) {

		try {
			Field field = StudentHomeworkUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, studentHomeworkPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = CoursePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CoursePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CoursePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_STUDENTHOMEWORK =
		"SELECT studentHomework FROM StudentHomework studentHomework";

	private static final String _SQL_SELECT_STUDENTHOMEWORK_WHERE =
		"SELECT studentHomework FROM StudentHomework studentHomework WHERE ";

	private static final String _SQL_COUNT_STUDENTHOMEWORK =
		"SELECT COUNT(studentHomework) FROM StudentHomework studentHomework";

	private static final String _SQL_COUNT_STUDENTHOMEWORK_WHERE =
		"SELECT COUNT(studentHomework) FROM StudentHomework studentHomework WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "studentHomework.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No StudentHomework exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No StudentHomework exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		StudentHomeworkPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"comment"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}