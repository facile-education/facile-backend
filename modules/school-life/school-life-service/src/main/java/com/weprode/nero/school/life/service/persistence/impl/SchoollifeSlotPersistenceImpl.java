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

package com.weprode.nero.school.life.service.persistence.impl;

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

import com.weprode.nero.school.life.exception.NoSuchSlotException;
import com.weprode.nero.school.life.model.SchoollifeSlot;
import com.weprode.nero.school.life.model.SchoollifeSlotTable;
import com.weprode.nero.school.life.model.impl.SchoollifeSlotImpl;
import com.weprode.nero.school.life.model.impl.SchoollifeSlotModelImpl;
import com.weprode.nero.school.life.service.persistence.SchoollifeSlotPersistence;
import com.weprode.nero.school.life.service.persistence.SchoollifeSlotUtil;
import com.weprode.nero.school.life.service.persistence.impl.constants.SchoollifePersistenceConstants;

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
 * The persistence implementation for the schoollife slot service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {SchoollifeSlotPersistence.class, BasePersistence.class})
public class SchoollifeSlotPersistenceImpl
	extends BasePersistenceImpl<SchoollifeSlot>
	implements SchoollifeSlotPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SchoollifeSlotUtil</code> to access the schoollife slot persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SchoollifeSlotImpl.class.getName();

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
	 * Returns all the schoollife slots where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching schoollife slots
	 */
	@Override
	public List<SchoollifeSlot> findByteacherId(long teacherId) {
		return findByteacherId(
			teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<SchoollifeSlot> findByteacherId(
		long teacherId, int start, int end) {

		return findByteacherId(teacherId, start, end, null);
	}

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
	@Override
	public List<SchoollifeSlot> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		return findByteacherId(teacherId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<SchoollifeSlot> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator,
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

		List<SchoollifeSlot> list = null;

		if (useFinderCache) {
			list = (List<SchoollifeSlot>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SchoollifeSlot schoollifeSlot : list) {
					if (teacherId != schoollifeSlot.getTeacherId()) {
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

			sb.append(_SQL_SELECT_SCHOOLLIFESLOT_WHERE);

			sb.append(_FINDER_COLUMN_TEACHERID_TEACHERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SchoollifeSlotModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teacherId);

				list = (List<SchoollifeSlot>)QueryUtil.list(
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
	 * Returns the first schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	@Override
	public SchoollifeSlot findByteacherId_First(
			long teacherId, OrderByComparator<SchoollifeSlot> orderByComparator)
		throws NoSuchSlotException {

		SchoollifeSlot schoollifeSlot = fetchByteacherId_First(
			teacherId, orderByComparator);

		if (schoollifeSlot != null) {
			return schoollifeSlot;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchSlotException(sb.toString());
	}

	/**
	 * Returns the first schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	@Override
	public SchoollifeSlot fetchByteacherId_First(
		long teacherId, OrderByComparator<SchoollifeSlot> orderByComparator) {

		List<SchoollifeSlot> list = findByteacherId(
			teacherId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	@Override
	public SchoollifeSlot findByteacherId_Last(
			long teacherId, OrderByComparator<SchoollifeSlot> orderByComparator)
		throws NoSuchSlotException {

		SchoollifeSlot schoollifeSlot = fetchByteacherId_Last(
			teacherId, orderByComparator);

		if (schoollifeSlot != null) {
			return schoollifeSlot;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchSlotException(sb.toString());
	}

	/**
	 * Returns the last schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	@Override
	public SchoollifeSlot fetchByteacherId_Last(
		long teacherId, OrderByComparator<SchoollifeSlot> orderByComparator) {

		int count = countByteacherId(teacherId);

		if (count == 0) {
			return null;
		}

		List<SchoollifeSlot> list = findByteacherId(
			teacherId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the schoollife slots before and after the current schoollife slot in the ordered set where teacherId = &#63;.
	 *
	 * @param schoollifeSlotId the primary key of the current schoollife slot
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife slot
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	@Override
	public SchoollifeSlot[] findByteacherId_PrevAndNext(
			long schoollifeSlotId, long teacherId,
			OrderByComparator<SchoollifeSlot> orderByComparator)
		throws NoSuchSlotException {

		SchoollifeSlot schoollifeSlot = findByPrimaryKey(schoollifeSlotId);

		Session session = null;

		try {
			session = openSession();

			SchoollifeSlot[] array = new SchoollifeSlotImpl[3];

			array[0] = getByteacherId_PrevAndNext(
				session, schoollifeSlot, teacherId, orderByComparator, true);

			array[1] = schoollifeSlot;

			array[2] = getByteacherId_PrevAndNext(
				session, schoollifeSlot, teacherId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SchoollifeSlot getByteacherId_PrevAndNext(
		Session session, SchoollifeSlot schoollifeSlot, long teacherId,
		OrderByComparator<SchoollifeSlot> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SCHOOLLIFESLOT_WHERE);

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
			sb.append(SchoollifeSlotModelImpl.ORDER_BY_JPQL);
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
						schoollifeSlot)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SchoollifeSlot> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the schoollife slots where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	@Override
	public void removeByteacherId(long teacherId) {
		for (SchoollifeSlot schoollifeSlot :
				findByteacherId(
					teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(schoollifeSlot);
		}
	}

	/**
	 * Returns the number of schoollife slots where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching schoollife slots
	 */
	@Override
	public int countByteacherId(long teacherId) {
		FinderPath finderPath = _finderPathCountByteacherId;

		Object[] finderArgs = new Object[] {teacherId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SCHOOLLIFESLOT_WHERE);

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
		"schoollifeSlot.teacherId = ?";

	private FinderPath _finderPathWithPaginationFindByschoolId_type;
	private FinderPath _finderPathWithoutPaginationFindByschoolId_type;
	private FinderPath _finderPathCountByschoolId_type;

	/**
	 * Returns all the schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the matching schoollife slots
	 */
	@Override
	public List<SchoollifeSlot> findByschoolId_type(long schoolId, int type) {
		return findByschoolId_type(
			schoolId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type, int start, int end) {

		return findByschoolId_type(schoolId, type, start, end, null);
	}

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
	@Override
	public List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type, int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		return findByschoolId_type(
			schoolId, type, start, end, orderByComparator, true);
	}

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
	@Override
	public List<SchoollifeSlot> findByschoolId_type(
		long schoolId, int type, int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByschoolId_type;
				finderArgs = new Object[] {schoolId, type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByschoolId_type;
			finderArgs = new Object[] {
				schoolId, type, start, end, orderByComparator
			};
		}

		List<SchoollifeSlot> list = null;

		if (useFinderCache) {
			list = (List<SchoollifeSlot>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SchoollifeSlot schoollifeSlot : list) {
					if ((schoolId != schoollifeSlot.getSchoolId()) ||
						(type != schoollifeSlot.getType())) {

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

			sb.append(_SQL_SELECT_SCHOOLLIFESLOT_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_SCHOOLID_2);

			sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SchoollifeSlotModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				queryPos.add(type);

				list = (List<SchoollifeSlot>)QueryUtil.list(
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
	 * Returns the first schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	@Override
	public SchoollifeSlot findByschoolId_type_First(
			long schoolId, int type,
			OrderByComparator<SchoollifeSlot> orderByComparator)
		throws NoSuchSlotException {

		SchoollifeSlot schoollifeSlot = fetchByschoolId_type_First(
			schoolId, type, orderByComparator);

		if (schoollifeSlot != null) {
			return schoollifeSlot;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchSlotException(sb.toString());
	}

	/**
	 * Returns the first schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	@Override
	public SchoollifeSlot fetchByschoolId_type_First(
		long schoolId, int type,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		List<SchoollifeSlot> list = findByschoolId_type(
			schoolId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot
	 * @throws NoSuchSlotException if a matching schoollife slot could not be found
	 */
	@Override
	public SchoollifeSlot findByschoolId_type_Last(
			long schoolId, int type,
			OrderByComparator<SchoollifeSlot> orderByComparator)
		throws NoSuchSlotException {

		SchoollifeSlot schoollifeSlot = fetchByschoolId_type_Last(
			schoolId, type, orderByComparator);

		if (schoollifeSlot != null) {
			return schoollifeSlot;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchSlotException(sb.toString());
	}

	/**
	 * Returns the last schoollife slot in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife slot, or <code>null</code> if a matching schoollife slot could not be found
	 */
	@Override
	public SchoollifeSlot fetchByschoolId_type_Last(
		long schoolId, int type,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		int count = countByschoolId_type(schoolId, type);

		if (count == 0) {
			return null;
		}

		List<SchoollifeSlot> list = findByschoolId_type(
			schoolId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public SchoollifeSlot[] findByschoolId_type_PrevAndNext(
			long schoollifeSlotId, long schoolId, int type,
			OrderByComparator<SchoollifeSlot> orderByComparator)
		throws NoSuchSlotException {

		SchoollifeSlot schoollifeSlot = findByPrimaryKey(schoollifeSlotId);

		Session session = null;

		try {
			session = openSession();

			SchoollifeSlot[] array = new SchoollifeSlotImpl[3];

			array[0] = getByschoolId_type_PrevAndNext(
				session, schoollifeSlot, schoolId, type, orderByComparator,
				true);

			array[1] = schoollifeSlot;

			array[2] = getByschoolId_type_PrevAndNext(
				session, schoollifeSlot, schoolId, type, orderByComparator,
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

	protected SchoollifeSlot getByschoolId_type_PrevAndNext(
		Session session, SchoollifeSlot schoollifeSlot, long schoolId, int type,
		OrderByComparator<SchoollifeSlot> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SCHOOLLIFESLOT_WHERE);

		sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_SCHOOLID_2);

		sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_TYPE_2);

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
			sb.append(SchoollifeSlotModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoolId);

		queryPos.add(type);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						schoollifeSlot)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SchoollifeSlot> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the schoollife slots where schoolId = &#63; and type = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 */
	@Override
	public void removeByschoolId_type(long schoolId, int type) {
		for (SchoollifeSlot schoollifeSlot :
				findByschoolId_type(
					schoolId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(schoollifeSlot);
		}
	}

	/**
	 * Returns the number of schoollife slots where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the number of matching schoollife slots
	 */
	@Override
	public int countByschoolId_type(long schoolId, int type) {
		FinderPath finderPath = _finderPathCountByschoolId_type;

		Object[] finderArgs = new Object[] {schoolId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SCHOOLLIFESLOT_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_SCHOOLID_2);

			sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_TYPE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				queryPos.add(type);

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

	private static final String _FINDER_COLUMN_SCHOOLID_TYPE_SCHOOLID_2 =
		"schoollifeSlot.schoolId = ? AND ";

	private static final String _FINDER_COLUMN_SCHOOLID_TYPE_TYPE_2 =
		"schoollifeSlot.type = ?";

	public SchoollifeSlotPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(SchoollifeSlot.class);

		setModelImplClass(SchoollifeSlotImpl.class);
		setModelPKClass(long.class);

		setTable(SchoollifeSlotTable.INSTANCE);
	}

	/**
	 * Caches the schoollife slot in the entity cache if it is enabled.
	 *
	 * @param schoollifeSlot the schoollife slot
	 */
	@Override
	public void cacheResult(SchoollifeSlot schoollifeSlot) {
		entityCache.putResult(
			SchoollifeSlotImpl.class, schoollifeSlot.getPrimaryKey(),
			schoollifeSlot);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the schoollife slots in the entity cache if it is enabled.
	 *
	 * @param schoollifeSlots the schoollife slots
	 */
	@Override
	public void cacheResult(List<SchoollifeSlot> schoollifeSlots) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (schoollifeSlots.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SchoollifeSlot schoollifeSlot : schoollifeSlots) {
			if (entityCache.getResult(
					SchoollifeSlotImpl.class, schoollifeSlot.getPrimaryKey()) ==
						null) {

				cacheResult(schoollifeSlot);
			}
		}
	}

	/**
	 * Clears the cache for all schoollife slots.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SchoollifeSlotImpl.class);

		finderCache.clearCache(SchoollifeSlotImpl.class);
	}

	/**
	 * Clears the cache for the schoollife slot.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SchoollifeSlot schoollifeSlot) {
		entityCache.removeResult(SchoollifeSlotImpl.class, schoollifeSlot);
	}

	@Override
	public void clearCache(List<SchoollifeSlot> schoollifeSlots) {
		for (SchoollifeSlot schoollifeSlot : schoollifeSlots) {
			entityCache.removeResult(SchoollifeSlotImpl.class, schoollifeSlot);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SchoollifeSlotImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SchoollifeSlotImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new schoollife slot with the primary key. Does not add the schoollife slot to the database.
	 *
	 * @param schoollifeSlotId the primary key for the new schoollife slot
	 * @return the new schoollife slot
	 */
	@Override
	public SchoollifeSlot create(long schoollifeSlotId) {
		SchoollifeSlot schoollifeSlot = new SchoollifeSlotImpl();

		schoollifeSlot.setNew(true);
		schoollifeSlot.setPrimaryKey(schoollifeSlotId);

		return schoollifeSlot;
	}

	/**
	 * Removes the schoollife slot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot that was removed
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	@Override
	public SchoollifeSlot remove(long schoollifeSlotId)
		throws NoSuchSlotException {

		return remove((Serializable)schoollifeSlotId);
	}

	/**
	 * Removes the schoollife slot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the schoollife slot
	 * @return the schoollife slot that was removed
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	@Override
	public SchoollifeSlot remove(Serializable primaryKey)
		throws NoSuchSlotException {

		Session session = null;

		try {
			session = openSession();

			SchoollifeSlot schoollifeSlot = (SchoollifeSlot)session.get(
				SchoollifeSlotImpl.class, primaryKey);

			if (schoollifeSlot == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSlotException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(schoollifeSlot);
		}
		catch (NoSuchSlotException noSuchEntityException) {
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
	protected SchoollifeSlot removeImpl(SchoollifeSlot schoollifeSlot) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(schoollifeSlot)) {
				schoollifeSlot = (SchoollifeSlot)session.get(
					SchoollifeSlotImpl.class,
					schoollifeSlot.getPrimaryKeyObj());
			}

			if (schoollifeSlot != null) {
				session.delete(schoollifeSlot);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (schoollifeSlot != null) {
			clearCache(schoollifeSlot);
		}

		return schoollifeSlot;
	}

	@Override
	public SchoollifeSlot updateImpl(SchoollifeSlot schoollifeSlot) {
		boolean isNew = schoollifeSlot.isNew();

		if (!(schoollifeSlot instanceof SchoollifeSlotModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(schoollifeSlot.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					schoollifeSlot);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in schoollifeSlot proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SchoollifeSlot implementation " +
					schoollifeSlot.getClass());
		}

		SchoollifeSlotModelImpl schoollifeSlotModelImpl =
			(SchoollifeSlotModelImpl)schoollifeSlot;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(schoollifeSlot);
			}
			else {
				schoollifeSlot = (SchoollifeSlot)session.merge(schoollifeSlot);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SchoollifeSlotImpl.class, schoollifeSlotModelImpl, false, true);

		if (isNew) {
			schoollifeSlot.setNew(false);
		}

		schoollifeSlot.resetOriginalValues();

		return schoollifeSlot;
	}

	/**
	 * Returns the schoollife slot with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the schoollife slot
	 * @return the schoollife slot
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	@Override
	public SchoollifeSlot findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSlotException {

		SchoollifeSlot schoollifeSlot = fetchByPrimaryKey(primaryKey);

		if (schoollifeSlot == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSlotException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return schoollifeSlot;
	}

	/**
	 * Returns the schoollife slot with the primary key or throws a <code>NoSuchSlotException</code> if it could not be found.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot
	 * @throws NoSuchSlotException if a schoollife slot with the primary key could not be found
	 */
	@Override
	public SchoollifeSlot findByPrimaryKey(long schoollifeSlotId)
		throws NoSuchSlotException {

		return findByPrimaryKey((Serializable)schoollifeSlotId);
	}

	/**
	 * Returns the schoollife slot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot, or <code>null</code> if a schoollife slot with the primary key could not be found
	 */
	@Override
	public SchoollifeSlot fetchByPrimaryKey(long schoollifeSlotId) {
		return fetchByPrimaryKey((Serializable)schoollifeSlotId);
	}

	/**
	 * Returns all the schoollife slots.
	 *
	 * @return the schoollife slots
	 */
	@Override
	public List<SchoollifeSlot> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<SchoollifeSlot> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<SchoollifeSlot> findAll(
		int start, int end,
		OrderByComparator<SchoollifeSlot> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<SchoollifeSlot> findAll(
		int start, int end, OrderByComparator<SchoollifeSlot> orderByComparator,
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

		List<SchoollifeSlot> list = null;

		if (useFinderCache) {
			list = (List<SchoollifeSlot>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SCHOOLLIFESLOT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SCHOOLLIFESLOT;

				sql = sql.concat(SchoollifeSlotModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SchoollifeSlot>)QueryUtil.list(
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
	 * Removes all the schoollife slots from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SchoollifeSlot schoollifeSlot : findAll()) {
			remove(schoollifeSlot);
		}
	}

	/**
	 * Returns the number of schoollife slots.
	 *
	 * @return the number of schoollife slots
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SCHOOLLIFESLOT);

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
		return "schoollifeSlotId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SCHOOLLIFESLOT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SchoollifeSlotModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the schoollife slot persistence.
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

		_finderPathWithPaginationFindByschoolId_type = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByschoolId_type",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"schoolId", "type_"}, true);

		_finderPathWithoutPaginationFindByschoolId_type = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByschoolId_type",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"schoolId", "type_"}, true);

		_finderPathCountByschoolId_type = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByschoolId_type",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"schoolId", "type_"}, false);

		_setSchoollifeSlotUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSchoollifeSlotUtilPersistence(null);

		entityCache.removeCache(SchoollifeSlotImpl.class.getName());
	}

	private void _setSchoollifeSlotUtilPersistence(
		SchoollifeSlotPersistence schoollifeSlotPersistence) {

		try {
			Field field = SchoollifeSlotUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, schoollifeSlotPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = SchoollifePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = SchoollifePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = SchoollifePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SCHOOLLIFESLOT =
		"SELECT schoollifeSlot FROM SchoollifeSlot schoollifeSlot";

	private static final String _SQL_SELECT_SCHOOLLIFESLOT_WHERE =
		"SELECT schoollifeSlot FROM SchoollifeSlot schoollifeSlot WHERE ";

	private static final String _SQL_COUNT_SCHOOLLIFESLOT =
		"SELECT COUNT(schoollifeSlot) FROM SchoollifeSlot schoollifeSlot";

	private static final String _SQL_COUNT_SCHOOLLIFESLOT_WHERE =
		"SELECT COUNT(schoollifeSlot) FROM SchoollifeSlot schoollifeSlot WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "schoollifeSlot.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SchoollifeSlot exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SchoollifeSlot exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SchoollifeSlotPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private SchoollifeSlotModelArgumentsResolver
		_schoollifeSlotModelArgumentsResolver;

}