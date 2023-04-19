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

package com.weprode.nero.schedule.service.persistence.impl;

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

import com.weprode.nero.schedule.exception.NoSuchTeacherGroupColorException;
import com.weprode.nero.schedule.model.TeacherGroupColor;
import com.weprode.nero.schedule.model.TeacherGroupColorTable;
import com.weprode.nero.schedule.model.impl.TeacherGroupColorImpl;
import com.weprode.nero.schedule.model.impl.TeacherGroupColorModelImpl;
import com.weprode.nero.schedule.service.persistence.TeacherGroupColorPersistence;
import com.weprode.nero.schedule.service.persistence.TeacherGroupColorUtil;
import com.weprode.nero.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

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
 * The persistence implementation for the teacher group color service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {TeacherGroupColorPersistence.class, BasePersistence.class}
)
public class TeacherGroupColorPersistenceImpl
	extends BasePersistenceImpl<TeacherGroupColor>
	implements TeacherGroupColorPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TeacherGroupColorUtil</code> to access the teacher group color persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TeacherGroupColorImpl.class.getName();

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
	 * Returns all the teacher group colors where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching teacher group colors
	 */
	@Override
	public List<TeacherGroupColor> findByteacherId(long teacherId) {
		return findByteacherId(
			teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<TeacherGroupColor> findByteacherId(
		long teacherId, int start, int end) {

		return findByteacherId(teacherId, start, end, null);
	}

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
	@Override
	public List<TeacherGroupColor> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return findByteacherId(teacherId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<TeacherGroupColor> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator,
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

		List<TeacherGroupColor> list = null;

		if (useFinderCache) {
			list = (List<TeacherGroupColor>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (TeacherGroupColor teacherGroupColor : list) {
					if (teacherId != teacherGroupColor.getTeacherId()) {
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

			sb.append(_SQL_SELECT_TEACHERGROUPCOLOR_WHERE);

			sb.append(_FINDER_COLUMN_TEACHERID_TEACHERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TeacherGroupColorModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teacherId);

				list = (List<TeacherGroupColor>)QueryUtil.list(
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
	 * Returns the first teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	@Override
	public TeacherGroupColor findByteacherId_First(
			long teacherId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws NoSuchTeacherGroupColorException {

		TeacherGroupColor teacherGroupColor = fetchByteacherId_First(
			teacherId, orderByComparator);

		if (teacherGroupColor != null) {
			return teacherGroupColor;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchTeacherGroupColorException(sb.toString());
	}

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	@Override
	public TeacherGroupColor fetchByteacherId_First(
		long teacherId,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		List<TeacherGroupColor> list = findByteacherId(
			teacherId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	@Override
	public TeacherGroupColor findByteacherId_Last(
			long teacherId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws NoSuchTeacherGroupColorException {

		TeacherGroupColor teacherGroupColor = fetchByteacherId_Last(
			teacherId, orderByComparator);

		if (teacherGroupColor != null) {
			return teacherGroupColor;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchTeacherGroupColorException(sb.toString());
	}

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	@Override
	public TeacherGroupColor fetchByteacherId_Last(
		long teacherId,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		int count = countByteacherId(teacherId);

		if (count == 0) {
			return null;
		}

		List<TeacherGroupColor> list = findByteacherId(
			teacherId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the teacher group colors before and after the current teacher group color in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherGroupColorId the primary key of the current teacher group color
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next teacher group color
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	@Override
	public TeacherGroupColor[] findByteacherId_PrevAndNext(
			long teacherGroupColorId, long teacherId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws NoSuchTeacherGroupColorException {

		TeacherGroupColor teacherGroupColor = findByPrimaryKey(
			teacherGroupColorId);

		Session session = null;

		try {
			session = openSession();

			TeacherGroupColor[] array = new TeacherGroupColorImpl[3];

			array[0] = getByteacherId_PrevAndNext(
				session, teacherGroupColor, teacherId, orderByComparator, true);

			array[1] = teacherGroupColor;

			array[2] = getByteacherId_PrevAndNext(
				session, teacherGroupColor, teacherId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TeacherGroupColor getByteacherId_PrevAndNext(
		Session session, TeacherGroupColor teacherGroupColor, long teacherId,
		OrderByComparator<TeacherGroupColor> orderByComparator,
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

		sb.append(_SQL_SELECT_TEACHERGROUPCOLOR_WHERE);

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
			sb.append(TeacherGroupColorModelImpl.ORDER_BY_JPQL);
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
						teacherGroupColor)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TeacherGroupColor> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the teacher group colors where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	@Override
	public void removeByteacherId(long teacherId) {
		for (TeacherGroupColor teacherGroupColor :
				findByteacherId(
					teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(teacherGroupColor);
		}
	}

	/**
	 * Returns the number of teacher group colors where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching teacher group colors
	 */
	@Override
	public int countByteacherId(long teacherId) {
		FinderPath finderPath = _finderPathCountByteacherId;

		Object[] finderArgs = new Object[] {teacherId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TEACHERGROUPCOLOR_WHERE);

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
		"teacherGroupColor.teacherId = ?";

	private FinderPath _finderPathWithPaginationFindByteacherId_groupId;
	private FinderPath _finderPathWithoutPaginationFindByteacherId_groupId;
	private FinderPath _finderPathCountByteacherId_groupId;

	/**
	 * Returns all the teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @return the matching teacher group colors
	 */
	@Override
	public List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId) {

		return findByteacherId_groupId(
			teacherId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId, int start, int end) {

		return findByteacherId_groupId(teacherId, groupId, start, end, null);
	}

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
	@Override
	public List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId, int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return findByteacherId_groupId(
			teacherId, groupId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<TeacherGroupColor> findByteacherId_groupId(
		long teacherId, long groupId, int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByteacherId_groupId;
				finderArgs = new Object[] {teacherId, groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByteacherId_groupId;
			finderArgs = new Object[] {
				teacherId, groupId, start, end, orderByComparator
			};
		}

		List<TeacherGroupColor> list = null;

		if (useFinderCache) {
			list = (List<TeacherGroupColor>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (TeacherGroupColor teacherGroupColor : list) {
					if ((teacherId != teacherGroupColor.getTeacherId()) ||
						(groupId != teacherGroupColor.getGroupId())) {

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

			sb.append(_SQL_SELECT_TEACHERGROUPCOLOR_WHERE);

			sb.append(_FINDER_COLUMN_TEACHERID_GROUPID_TEACHERID_2);

			sb.append(_FINDER_COLUMN_TEACHERID_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TeacherGroupColorModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teacherId);

				queryPos.add(groupId);

				list = (List<TeacherGroupColor>)QueryUtil.list(
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
	 * Returns the first teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	@Override
	public TeacherGroupColor findByteacherId_groupId_First(
			long teacherId, long groupId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws NoSuchTeacherGroupColorException {

		TeacherGroupColor teacherGroupColor = fetchByteacherId_groupId_First(
			teacherId, groupId, orderByComparator);

		if (teacherGroupColor != null) {
			return teacherGroupColor;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchTeacherGroupColorException(sb.toString());
	}

	/**
	 * Returns the first teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	@Override
	public TeacherGroupColor fetchByteacherId_groupId_First(
		long teacherId, long groupId,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		List<TeacherGroupColor> list = findByteacherId_groupId(
			teacherId, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color
	 * @throws NoSuchTeacherGroupColorException if a matching teacher group color could not be found
	 */
	@Override
	public TeacherGroupColor findByteacherId_groupId_Last(
			long teacherId, long groupId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws NoSuchTeacherGroupColorException {

		TeacherGroupColor teacherGroupColor = fetchByteacherId_groupId_Last(
			teacherId, groupId, orderByComparator);

		if (teacherGroupColor != null) {
			return teacherGroupColor;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchTeacherGroupColorException(sb.toString());
	}

	/**
	 * Returns the last teacher group color in the ordered set where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching teacher group color, or <code>null</code> if a matching teacher group color could not be found
	 */
	@Override
	public TeacherGroupColor fetchByteacherId_groupId_Last(
		long teacherId, long groupId,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		int count = countByteacherId_groupId(teacherId, groupId);

		if (count == 0) {
			return null;
		}

		List<TeacherGroupColor> list = findByteacherId_groupId(
			teacherId, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public TeacherGroupColor[] findByteacherId_groupId_PrevAndNext(
			long teacherGroupColorId, long teacherId, long groupId,
			OrderByComparator<TeacherGroupColor> orderByComparator)
		throws NoSuchTeacherGroupColorException {

		TeacherGroupColor teacherGroupColor = findByPrimaryKey(
			teacherGroupColorId);

		Session session = null;

		try {
			session = openSession();

			TeacherGroupColor[] array = new TeacherGroupColorImpl[3];

			array[0] = getByteacherId_groupId_PrevAndNext(
				session, teacherGroupColor, teacherId, groupId,
				orderByComparator, true);

			array[1] = teacherGroupColor;

			array[2] = getByteacherId_groupId_PrevAndNext(
				session, teacherGroupColor, teacherId, groupId,
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

	protected TeacherGroupColor getByteacherId_groupId_PrevAndNext(
		Session session, TeacherGroupColor teacherGroupColor, long teacherId,
		long groupId, OrderByComparator<TeacherGroupColor> orderByComparator,
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

		sb.append(_SQL_SELECT_TEACHERGROUPCOLOR_WHERE);

		sb.append(_FINDER_COLUMN_TEACHERID_GROUPID_TEACHERID_2);

		sb.append(_FINDER_COLUMN_TEACHERID_GROUPID_GROUPID_2);

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
			sb.append(TeacherGroupColorModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(teacherId);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						teacherGroupColor)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TeacherGroupColor> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the teacher group colors where teacherId = &#63; and groupId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByteacherId_groupId(long teacherId, long groupId) {
		for (TeacherGroupColor teacherGroupColor :
				findByteacherId_groupId(
					teacherId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(teacherGroupColor);
		}
	}

	/**
	 * Returns the number of teacher group colors where teacherId = &#63; and groupId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param groupId the group ID
	 * @return the number of matching teacher group colors
	 */
	@Override
	public int countByteacherId_groupId(long teacherId, long groupId) {
		FinderPath finderPath = _finderPathCountByteacherId_groupId;

		Object[] finderArgs = new Object[] {teacherId, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_TEACHERGROUPCOLOR_WHERE);

			sb.append(_FINDER_COLUMN_TEACHERID_GROUPID_TEACHERID_2);

			sb.append(_FINDER_COLUMN_TEACHERID_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teacherId);

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_TEACHERID_GROUPID_TEACHERID_2 =
		"teacherGroupColor.teacherId = ? AND ";

	private static final String _FINDER_COLUMN_TEACHERID_GROUPID_GROUPID_2 =
		"teacherGroupColor.groupId = ?";

	public TeacherGroupColorPersistenceImpl() {
		setModelClass(TeacherGroupColor.class);

		setModelImplClass(TeacherGroupColorImpl.class);
		setModelPKClass(long.class);

		setTable(TeacherGroupColorTable.INSTANCE);
	}

	/**
	 * Caches the teacher group color in the entity cache if it is enabled.
	 *
	 * @param teacherGroupColor the teacher group color
	 */
	@Override
	public void cacheResult(TeacherGroupColor teacherGroupColor) {
		entityCache.putResult(
			TeacherGroupColorImpl.class, teacherGroupColor.getPrimaryKey(),
			teacherGroupColor);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the teacher group colors in the entity cache if it is enabled.
	 *
	 * @param teacherGroupColors the teacher group colors
	 */
	@Override
	public void cacheResult(List<TeacherGroupColor> teacherGroupColors) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (teacherGroupColors.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TeacherGroupColor teacherGroupColor : teacherGroupColors) {
			if (entityCache.getResult(
					TeacherGroupColorImpl.class,
					teacherGroupColor.getPrimaryKey()) == null) {

				cacheResult(teacherGroupColor);
			}
		}
	}

	/**
	 * Clears the cache for all teacher group colors.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TeacherGroupColorImpl.class);

		finderCache.clearCache(TeacherGroupColorImpl.class);
	}

	/**
	 * Clears the cache for the teacher group color.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TeacherGroupColor teacherGroupColor) {
		entityCache.removeResult(
			TeacherGroupColorImpl.class, teacherGroupColor);
	}

	@Override
	public void clearCache(List<TeacherGroupColor> teacherGroupColors) {
		for (TeacherGroupColor teacherGroupColor : teacherGroupColors) {
			entityCache.removeResult(
				TeacherGroupColorImpl.class, teacherGroupColor);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TeacherGroupColorImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(TeacherGroupColorImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new teacher group color with the primary key. Does not add the teacher group color to the database.
	 *
	 * @param teacherGroupColorId the primary key for the new teacher group color
	 * @return the new teacher group color
	 */
	@Override
	public TeacherGroupColor create(long teacherGroupColorId) {
		TeacherGroupColor teacherGroupColor = new TeacherGroupColorImpl();

		teacherGroupColor.setNew(true);
		teacherGroupColor.setPrimaryKey(teacherGroupColorId);

		return teacherGroupColor;
	}

	/**
	 * Removes the teacher group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color that was removed
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	@Override
	public TeacherGroupColor remove(long teacherGroupColorId)
		throws NoSuchTeacherGroupColorException {

		return remove((Serializable)teacherGroupColorId);
	}

	/**
	 * Removes the teacher group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the teacher group color
	 * @return the teacher group color that was removed
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	@Override
	public TeacherGroupColor remove(Serializable primaryKey)
		throws NoSuchTeacherGroupColorException {

		Session session = null;

		try {
			session = openSession();

			TeacherGroupColor teacherGroupColor =
				(TeacherGroupColor)session.get(
					TeacherGroupColorImpl.class, primaryKey);

			if (teacherGroupColor == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTeacherGroupColorException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(teacherGroupColor);
		}
		catch (NoSuchTeacherGroupColorException noSuchEntityException) {
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
	protected TeacherGroupColor removeImpl(
		TeacherGroupColor teacherGroupColor) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(teacherGroupColor)) {
				teacherGroupColor = (TeacherGroupColor)session.get(
					TeacherGroupColorImpl.class,
					teacherGroupColor.getPrimaryKeyObj());
			}

			if (teacherGroupColor != null) {
				session.delete(teacherGroupColor);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (teacherGroupColor != null) {
			clearCache(teacherGroupColor);
		}

		return teacherGroupColor;
	}

	@Override
	public TeacherGroupColor updateImpl(TeacherGroupColor teacherGroupColor) {
		boolean isNew = teacherGroupColor.isNew();

		if (!(teacherGroupColor instanceof TeacherGroupColorModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(teacherGroupColor.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					teacherGroupColor);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in teacherGroupColor proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TeacherGroupColor implementation " +
					teacherGroupColor.getClass());
		}

		TeacherGroupColorModelImpl teacherGroupColorModelImpl =
			(TeacherGroupColorModelImpl)teacherGroupColor;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(teacherGroupColor);
			}
			else {
				teacherGroupColor = (TeacherGroupColor)session.merge(
					teacherGroupColor);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			TeacherGroupColorImpl.class, teacherGroupColorModelImpl, false,
			true);

		if (isNew) {
			teacherGroupColor.setNew(false);
		}

		teacherGroupColor.resetOriginalValues();

		return teacherGroupColor;
	}

	/**
	 * Returns the teacher group color with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the teacher group color
	 * @return the teacher group color
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	@Override
	public TeacherGroupColor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTeacherGroupColorException {

		TeacherGroupColor teacherGroupColor = fetchByPrimaryKey(primaryKey);

		if (teacherGroupColor == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTeacherGroupColorException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return teacherGroupColor;
	}

	/**
	 * Returns the teacher group color with the primary key or throws a <code>NoSuchTeacherGroupColorException</code> if it could not be found.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color
	 * @throws NoSuchTeacherGroupColorException if a teacher group color with the primary key could not be found
	 */
	@Override
	public TeacherGroupColor findByPrimaryKey(long teacherGroupColorId)
		throws NoSuchTeacherGroupColorException {

		return findByPrimaryKey((Serializable)teacherGroupColorId);
	}

	/**
	 * Returns the teacher group color with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color, or <code>null</code> if a teacher group color with the primary key could not be found
	 */
	@Override
	public TeacherGroupColor fetchByPrimaryKey(long teacherGroupColorId) {
		return fetchByPrimaryKey((Serializable)teacherGroupColorId);
	}

	/**
	 * Returns all the teacher group colors.
	 *
	 * @return the teacher group colors
	 */
	@Override
	public List<TeacherGroupColor> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<TeacherGroupColor> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<TeacherGroupColor> findAll(
		int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<TeacherGroupColor> findAll(
		int start, int end,
		OrderByComparator<TeacherGroupColor> orderByComparator,
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

		List<TeacherGroupColor> list = null;

		if (useFinderCache) {
			list = (List<TeacherGroupColor>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TEACHERGROUPCOLOR);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TEACHERGROUPCOLOR;

				sql = sql.concat(TeacherGroupColorModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TeacherGroupColor>)QueryUtil.list(
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
	 * Removes all the teacher group colors from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TeacherGroupColor teacherGroupColor : findAll()) {
			remove(teacherGroupColor);
		}
	}

	/**
	 * Returns the number of teacher group colors.
	 *
	 * @return the number of teacher group colors
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TEACHERGROUPCOLOR);

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
		return "teacherGroupColorId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TEACHERGROUPCOLOR;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TeacherGroupColorModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the teacher group color persistence.
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

		_finderPathWithPaginationFindByteacherId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByteacherId_groupId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"teacherId", "groupId"}, true);

		_finderPathWithoutPaginationFindByteacherId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByteacherId_groupId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"teacherId", "groupId"}, true);

		_finderPathCountByteacherId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByteacherId_groupId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"teacherId", "groupId"}, false);

		_setTeacherGroupColorUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setTeacherGroupColorUtilPersistence(null);

		entityCache.removeCache(TeacherGroupColorImpl.class.getName());
	}

	private void _setTeacherGroupColorUtilPersistence(
		TeacherGroupColorPersistence teacherGroupColorPersistence) {

		try {
			Field field = TeacherGroupColorUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, teacherGroupColorPersistence);
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

	private static final String _SQL_SELECT_TEACHERGROUPCOLOR =
		"SELECT teacherGroupColor FROM TeacherGroupColor teacherGroupColor";

	private static final String _SQL_SELECT_TEACHERGROUPCOLOR_WHERE =
		"SELECT teacherGroupColor FROM TeacherGroupColor teacherGroupColor WHERE ";

	private static final String _SQL_COUNT_TEACHERGROUPCOLOR =
		"SELECT COUNT(teacherGroupColor) FROM TeacherGroupColor teacherGroupColor";

	private static final String _SQL_COUNT_TEACHERGROUPCOLOR_WHERE =
		"SELECT COUNT(teacherGroupColor) FROM TeacherGroupColor teacherGroupColor WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "teacherGroupColor.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TeacherGroupColor exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TeacherGroupColor exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TeacherGroupColorPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private TeacherGroupColorModelArgumentsResolver
		_teacherGroupColorModelArgumentsResolver;

}