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

package com.weprode.facile.schedule.service.persistence.impl;

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

import com.weprode.facile.schedule.exception.NoSuchTeacherSubjectException;
import com.weprode.facile.schedule.model.TeacherSubject;
import com.weprode.facile.schedule.model.TeacherSubjectTable;
import com.weprode.facile.schedule.model.impl.TeacherSubjectImpl;
import com.weprode.facile.schedule.model.impl.TeacherSubjectModelImpl;
import com.weprode.facile.schedule.service.persistence.TeacherSubjectPersistence;
import com.weprode.facile.schedule.service.persistence.TeacherSubjectUtil;
import com.weprode.facile.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the teacher subject service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {TeacherSubjectPersistence.class, BasePersistence.class})
public class TeacherSubjectPersistenceImpl
	extends BasePersistenceImpl<TeacherSubject>
	implements TeacherSubjectPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TeacherSubjectUtil</code> to access the teacher subject persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TeacherSubjectImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByteacherId;
	private FinderPath _finderPathWithoutPaginationFindByteacherId;
	private FinderPath _finderPathCountByteacherId;

	/**
	 * Returns all the teacher subjects where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching teacher subjects
	 */
	@Override
	public List<TeacherSubject> findByteacherId(long teacherId) {
		return findByteacherId(
			teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the teacher subjects where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @return the range of matching teacher subjects
	 */
	@Override
	public List<TeacherSubject> findByteacherId(
		long teacherId, int start, int end) {

		return findByteacherId(teacherId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the teacher subjects where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teacher subjects
	 */
	@Override
	public List<TeacherSubject> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator) {

		return findByteacherId(teacherId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the teacher subjects where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teacher subjects
	 */
	@Override
	public List<TeacherSubject> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByteacherId;
				finderArgs = new Object[] {teacherId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByteacherId;
			finderArgs = new Object[] {
				teacherId, start, end, orderByComparator
			};
		}

		List<TeacherSubject> list = null;

		if (useFinderCache) {
			list = (List<TeacherSubject>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TeacherSubject teacherSubject : list) {
					if (teacherId != teacherSubject.getTeacherId()) {
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

			sb.append(_SQL_SELECT_TEACHERSUBJECT_WHERE);

			sb.append(_FINDER_COLUMN_TEACHERID_TEACHERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TeacherSubjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teacherId);

				list = (List<TeacherSubject>)QueryUtil.list(
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
	 * Returns the first teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	@Override
	public TeacherSubject findByteacherId_First(
			long teacherId, OrderByComparator<TeacherSubject> orderByComparator)
		throws NoSuchTeacherSubjectException {

		TeacherSubject teacherSubject = fetchByteacherId_First(
			teacherId, orderByComparator);

		if (teacherSubject != null) {
			return teacherSubject;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchTeacherSubjectException(sb.toString());
	}

	/**
	 * Returns the first teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	@Override
	public TeacherSubject fetchByteacherId_First(
		long teacherId, OrderByComparator<TeacherSubject> orderByComparator) {

		List<TeacherSubject> list = findByteacherId(
			teacherId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	@Override
	public TeacherSubject findByteacherId_Last(
			long teacherId, OrderByComparator<TeacherSubject> orderByComparator)
		throws NoSuchTeacherSubjectException {

		TeacherSubject teacherSubject = fetchByteacherId_Last(
			teacherId, orderByComparator);

		if (teacherSubject != null) {
			return teacherSubject;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchTeacherSubjectException(sb.toString());
	}

	/**
	 * Returns the last teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	@Override
	public TeacherSubject fetchByteacherId_Last(
		long teacherId, OrderByComparator<TeacherSubject> orderByComparator) {

		int count = countByteacherId(teacherId);

		if (count == 0) {
			return null;
		}

		List<TeacherSubject> list = findByteacherId(
			teacherId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the teacher subjects before and after the current teacher subject in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherSubjectId the primary key of the current teacher subject
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	@Override
	public TeacherSubject[] findByteacherId_PrevAndNext(
			long teacherSubjectId, long teacherId,
			OrderByComparator<TeacherSubject> orderByComparator)
		throws NoSuchTeacherSubjectException {

		TeacherSubject teacherSubject = findByPrimaryKey(teacherSubjectId);

		Session session = null;

		try {
			session = openSession();

			TeacherSubject[] array = new TeacherSubjectImpl[3];

			array[0] = getByteacherId_PrevAndNext(
				session, teacherSubject, teacherId, orderByComparator, true);

			array[1] = teacherSubject;

			array[2] = getByteacherId_PrevAndNext(
				session, teacherSubject, teacherId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TeacherSubject getByteacherId_PrevAndNext(
		Session session, TeacherSubject teacherSubject, long teacherId,
		OrderByComparator<TeacherSubject> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TEACHERSUBJECT_WHERE);

		sb.append(_FINDER_COLUMN_TEACHERID_TEACHERID_2);

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
			sb.append(TeacherSubjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(teacherId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						teacherSubject)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TeacherSubject> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the teacher subjects where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	@Override
	public void removeByteacherId(long teacherId) {
		for (TeacherSubject teacherSubject :
				findByteacherId(
					teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(teacherSubject);
		}
	}

	/**
	 * Returns the number of teacher subjects where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching teacher subjects
	 */
	@Override
	public int countByteacherId(long teacherId) {
		FinderPath finderPath = _finderPathCountByteacherId;

		Object[] finderArgs = new Object[] {teacherId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TEACHERSUBJECT_WHERE);

			sb.append(_FINDER_COLUMN_TEACHERID_TEACHERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teacherId);

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

	private static final String _FINDER_COLUMN_TEACHERID_TEACHERID_2 =
		"teacherSubject.teacherId = ?";

	private FinderPath _finderPathWithPaginationFindByschoolId;
	private FinderPath _finderPathWithoutPaginationFindByschoolId;
	private FinderPath _finderPathCountByschoolId;

	/**
	 * Returns all the teacher subjects where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching teacher subjects
	 */
	@Override
	public List<TeacherSubject> findByschoolId(long schoolId) {
		return findByschoolId(
			schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the teacher subjects where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @return the range of matching teacher subjects
	 */
	@Override
	public List<TeacherSubject> findByschoolId(
		long schoolId, int start, int end) {

		return findByschoolId(schoolId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the teacher subjects where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teacher subjects
	 */
	@Override
	public List<TeacherSubject> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator) {

		return findByschoolId(schoolId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the teacher subjects where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teacher subjects
	 */
	@Override
	public List<TeacherSubject> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByschoolId;
				finderArgs = new Object[] {schoolId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByschoolId;
			finderArgs = new Object[] {schoolId, start, end, orderByComparator};
		}

		List<TeacherSubject> list = null;

		if (useFinderCache) {
			list = (List<TeacherSubject>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TeacherSubject teacherSubject : list) {
					if (schoolId != teacherSubject.getSchoolId()) {
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

			sb.append(_SQL_SELECT_TEACHERSUBJECT_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TeacherSubjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				list = (List<TeacherSubject>)QueryUtil.list(
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
	 * Returns the first teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	@Override
	public TeacherSubject findByschoolId_First(
			long schoolId, OrderByComparator<TeacherSubject> orderByComparator)
		throws NoSuchTeacherSubjectException {

		TeacherSubject teacherSubject = fetchByschoolId_First(
			schoolId, orderByComparator);

		if (teacherSubject != null) {
			return teacherSubject;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchTeacherSubjectException(sb.toString());
	}

	/**
	 * Returns the first teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	@Override
	public TeacherSubject fetchByschoolId_First(
		long schoolId, OrderByComparator<TeacherSubject> orderByComparator) {

		List<TeacherSubject> list = findByschoolId(
			schoolId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject
	 * @throws NoSuchTeacherSubjectException if a matching teacher subject could not be found
	 */
	@Override
	public TeacherSubject findByschoolId_Last(
			long schoolId, OrderByComparator<TeacherSubject> orderByComparator)
		throws NoSuchTeacherSubjectException {

		TeacherSubject teacherSubject = fetchByschoolId_Last(
			schoolId, orderByComparator);

		if (teacherSubject != null) {
			return teacherSubject;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchTeacherSubjectException(sb.toString());
	}

	/**
	 * Returns the last teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher subject, or <code>null</code> if a matching teacher subject could not be found
	 */
	@Override
	public TeacherSubject fetchByschoolId_Last(
		long schoolId, OrderByComparator<TeacherSubject> orderByComparator) {

		int count = countByschoolId(schoolId);

		if (count == 0) {
			return null;
		}

		List<TeacherSubject> list = findByschoolId(
			schoolId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the teacher subjects before and after the current teacher subject in the ordered set where schoolId = &#63;.
	 *
	 * @param teacherSubjectId the primary key of the current teacher subject
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	@Override
	public TeacherSubject[] findByschoolId_PrevAndNext(
			long teacherSubjectId, long schoolId,
			OrderByComparator<TeacherSubject> orderByComparator)
		throws NoSuchTeacherSubjectException {

		TeacherSubject teacherSubject = findByPrimaryKey(teacherSubjectId);

		Session session = null;

		try {
			session = openSession();

			TeacherSubject[] array = new TeacherSubjectImpl[3];

			array[0] = getByschoolId_PrevAndNext(
				session, teacherSubject, schoolId, orderByComparator, true);

			array[1] = teacherSubject;

			array[2] = getByschoolId_PrevAndNext(
				session, teacherSubject, schoolId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TeacherSubject getByschoolId_PrevAndNext(
		Session session, TeacherSubject teacherSubject, long schoolId,
		OrderByComparator<TeacherSubject> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TEACHERSUBJECT_WHERE);

		sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

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
			sb.append(TeacherSubjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoolId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						teacherSubject)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TeacherSubject> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the teacher subjects where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	@Override
	public void removeByschoolId(long schoolId) {
		for (TeacherSubject teacherSubject :
				findByschoolId(
					schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(teacherSubject);
		}
	}

	/**
	 * Returns the number of teacher subjects where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching teacher subjects
	 */
	@Override
	public int countByschoolId(long schoolId) {
		FinderPath finderPath = _finderPathCountByschoolId;

		Object[] finderArgs = new Object[] {schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TEACHERSUBJECT_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

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

	private static final String _FINDER_COLUMN_SCHOOLID_SCHOOLID_2 =
		"teacherSubject.schoolId = ?";

	public TeacherSubjectPersistenceImpl() {
		setModelClass(TeacherSubject.class);

		setModelImplClass(TeacherSubjectImpl.class);
		setModelPKClass(long.class);

		setTable(TeacherSubjectTable.INSTANCE);
	}

	/**
	 * Caches the teacher subject in the entity cache if it is enabled.
	 *
	 * @param teacherSubject the teacher subject
	 */
	@Override
	public void cacheResult(TeacherSubject teacherSubject) {
		entityCache.putResult(
			TeacherSubjectImpl.class, teacherSubject.getPrimaryKey(),
			teacherSubject);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the teacher subjects in the entity cache if it is enabled.
	 *
	 * @param teacherSubjects the teacher subjects
	 */
	@Override
	public void cacheResult(List<TeacherSubject> teacherSubjects) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (teacherSubjects.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TeacherSubject teacherSubject : teacherSubjects) {
			if (entityCache.getResult(
					TeacherSubjectImpl.class, teacherSubject.getPrimaryKey()) ==
						null) {

				cacheResult(teacherSubject);
			}
		}
	}

	/**
	 * Clears the cache for all teacher subjects.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TeacherSubjectImpl.class);

		finderCache.clearCache(TeacherSubjectImpl.class);
	}

	/**
	 * Clears the cache for the teacher subject.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TeacherSubject teacherSubject) {
		entityCache.removeResult(TeacherSubjectImpl.class, teacherSubject);
	}

	@Override
	public void clearCache(List<TeacherSubject> teacherSubjects) {
		for (TeacherSubject teacherSubject : teacherSubjects) {
			entityCache.removeResult(TeacherSubjectImpl.class, teacherSubject);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TeacherSubjectImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(TeacherSubjectImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new teacher subject with the primary key. Does not add the teacher subject to the database.
	 *
	 * @param teacherSubjectId the primary key for the new teacher subject
	 * @return the new teacher subject
	 */
	@Override
	public TeacherSubject create(long teacherSubjectId) {
		TeacherSubject teacherSubject = new TeacherSubjectImpl();

		teacherSubject.setNew(true);
		teacherSubject.setPrimaryKey(teacherSubjectId);

		return teacherSubject;
	}

	/**
	 * Removes the teacher subject with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject that was removed
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	@Override
	public TeacherSubject remove(long teacherSubjectId)
		throws NoSuchTeacherSubjectException {

		return remove((Serializable)teacherSubjectId);
	}

	/**
	 * Removes the teacher subject with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the teacher subject
	 * @return the teacher subject that was removed
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	@Override
	public TeacherSubject remove(Serializable primaryKey)
		throws NoSuchTeacherSubjectException {

		Session session = null;

		try {
			session = openSession();

			TeacherSubject teacherSubject = (TeacherSubject)session.get(
				TeacherSubjectImpl.class, primaryKey);

			if (teacherSubject == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTeacherSubjectException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(teacherSubject);
		}
		catch (NoSuchTeacherSubjectException noSuchEntityException) {
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
	protected TeacherSubject removeImpl(TeacherSubject teacherSubject) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(teacherSubject)) {
				teacherSubject = (TeacherSubject)session.get(
					TeacherSubjectImpl.class,
					teacherSubject.getPrimaryKeyObj());
			}

			if (teacherSubject != null) {
				session.delete(teacherSubject);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (teacherSubject != null) {
			clearCache(teacherSubject);
		}

		return teacherSubject;
	}

	@Override
	public TeacherSubject updateImpl(TeacherSubject teacherSubject) {
		boolean isNew = teacherSubject.isNew();

		if (!(teacherSubject instanceof TeacherSubjectModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(teacherSubject.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					teacherSubject);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in teacherSubject proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TeacherSubject implementation " +
					teacherSubject.getClass());
		}

		TeacherSubjectModelImpl teacherSubjectModelImpl =
			(TeacherSubjectModelImpl)teacherSubject;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(teacherSubject);
			}
			else {
				teacherSubject = (TeacherSubject)session.merge(teacherSubject);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			TeacherSubjectImpl.class, teacherSubjectModelImpl, false, true);

		if (isNew) {
			teacherSubject.setNew(false);
		}

		teacherSubject.resetOriginalValues();

		return teacherSubject;
	}

	/**
	 * Returns the teacher subject with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the teacher subject
	 * @return the teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	@Override
	public TeacherSubject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTeacherSubjectException {

		TeacherSubject teacherSubject = fetchByPrimaryKey(primaryKey);

		if (teacherSubject == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTeacherSubjectException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return teacherSubject;
	}

	/**
	 * Returns the teacher subject with the primary key or throws a <code>NoSuchTeacherSubjectException</code> if it could not be found.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject
	 * @throws NoSuchTeacherSubjectException if a teacher subject with the primary key could not be found
	 */
	@Override
	public TeacherSubject findByPrimaryKey(long teacherSubjectId)
		throws NoSuchTeacherSubjectException {

		return findByPrimaryKey((Serializable)teacherSubjectId);
	}

	/**
	 * Returns the teacher subject with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject, or <code>null</code> if a teacher subject with the primary key could not be found
	 */
	@Override
	public TeacherSubject fetchByPrimaryKey(long teacherSubjectId) {
		return fetchByPrimaryKey((Serializable)teacherSubjectId);
	}

	/**
	 * Returns all the teacher subjects.
	 *
	 * @return the teacher subjects
	 */
	@Override
	public List<TeacherSubject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the teacher subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @return the range of teacher subjects
	 */
	@Override
	public List<TeacherSubject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the teacher subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of teacher subjects
	 */
	@Override
	public List<TeacherSubject> findAll(
		int start, int end,
		OrderByComparator<TeacherSubject> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the teacher subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of teacher subjects
	 */
	@Override
	public List<TeacherSubject> findAll(
		int start, int end, OrderByComparator<TeacherSubject> orderByComparator,
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

		List<TeacherSubject> list = null;

		if (useFinderCache) {
			list = (List<TeacherSubject>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TEACHERSUBJECT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TEACHERSUBJECT;

				sql = sql.concat(TeacherSubjectModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TeacherSubject>)QueryUtil.list(
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
	 * Removes all the teacher subjects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TeacherSubject teacherSubject : findAll()) {
			remove(teacherSubject);
		}
	}

	/**
	 * Returns the number of teacher subjects.
	 *
	 * @return the number of teacher subjects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TEACHERSUBJECT);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "teacherSubjectId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TEACHERSUBJECT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TeacherSubjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the teacher subject persistence.
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

		_finderPathWithPaginationFindByteacherId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByteacherId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"teacherId"}, true);

		_finderPathWithoutPaginationFindByteacherId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByteacherId",
			new String[] {Long.class.getName()}, new String[] {"teacherId"},
			true);

		_finderPathCountByteacherId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByteacherId",
			new String[] {Long.class.getName()}, new String[] {"teacherId"},
			false);

		_finderPathWithPaginationFindByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByschoolId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"schoolId"}, true);

		_finderPathWithoutPaginationFindByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByschoolId",
			new String[] {Long.class.getName()}, new String[] {"schoolId"},
			true);

		_finderPathCountByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByschoolId",
			new String[] {Long.class.getName()}, new String[] {"schoolId"},
			false);

		_setTeacherSubjectUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setTeacherSubjectUtilPersistence(null);

		entityCache.removeCache(TeacherSubjectImpl.class.getName());
	}

	private void _setTeacherSubjectUtilPersistence(
		TeacherSubjectPersistence teacherSubjectPersistence) {

		try {
			Field field = TeacherSubjectUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, teacherSubjectPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_TEACHERSUBJECT =
		"SELECT teacherSubject FROM TeacherSubject teacherSubject";

	private static final String _SQL_SELECT_TEACHERSUBJECT_WHERE =
		"SELECT teacherSubject FROM TeacherSubject teacherSubject WHERE ";

	private static final String _SQL_COUNT_TEACHERSUBJECT =
		"SELECT COUNT(teacherSubject) FROM TeacherSubject teacherSubject";

	private static final String _SQL_COUNT_TEACHERSUBJECT_WHERE =
		"SELECT COUNT(teacherSubject) FROM TeacherSubject teacherSubject WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "teacherSubject.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TeacherSubject exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TeacherSubject exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TeacherSubjectPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}