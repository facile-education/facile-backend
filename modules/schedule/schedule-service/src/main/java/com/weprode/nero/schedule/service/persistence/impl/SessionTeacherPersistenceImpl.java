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
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.schedule.exception.NoSuchSessionTeacherException;
import com.weprode.nero.schedule.model.SessionTeacher;
import com.weprode.nero.schedule.model.SessionTeacherTable;
import com.weprode.nero.schedule.model.impl.SessionTeacherImpl;
import com.weprode.nero.schedule.model.impl.SessionTeacherModelImpl;
import com.weprode.nero.schedule.service.persistence.SessionTeacherPersistence;
import com.weprode.nero.schedule.service.persistence.SessionTeacherUtil;
import com.weprode.nero.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the session teacher service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {SessionTeacherPersistence.class, BasePersistence.class})
public class SessionTeacherPersistenceImpl
	extends BasePersistenceImpl<SessionTeacher>
	implements SessionTeacherPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SessionTeacherUtil</code> to access the session teacher persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SessionTeacherImpl.class.getName();

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
	 * Returns all the session teachers where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching session teachers
	 */
	@Override
	public List<SessionTeacher> findByteacherId(long teacherId) {
		return findByteacherId(
			teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session teachers where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @return the range of matching session teachers
	 */
	@Override
	public List<SessionTeacher> findByteacherId(
		long teacherId, int start, int end) {

		return findByteacherId(teacherId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the session teachers where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session teachers
	 */
	@Override
	public List<SessionTeacher> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator) {

		return findByteacherId(teacherId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session teachers where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session teachers
	 */
	@Override
	public List<SessionTeacher> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator,
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

		List<SessionTeacher> list = null;

		if (useFinderCache) {
			list = (List<SessionTeacher>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SessionTeacher sessionTeacher : list) {
					if (teacherId != sessionTeacher.getTeacherId()) {
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

			sb.append(_SQL_SELECT_SESSIONTEACHER_WHERE);

			sb.append(_FINDER_COLUMN_TEACHERID_TEACHERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SessionTeacherModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teacherId);

				list = (List<SessionTeacher>)QueryUtil.list(
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
	 * Returns the first session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher findByteacherId_First(
			long teacherId, OrderByComparator<SessionTeacher> orderByComparator)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = fetchByteacherId_First(
			teacherId, orderByComparator);

		if (sessionTeacher != null) {
			return sessionTeacher;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchSessionTeacherException(sb.toString());
	}

	/**
	 * Returns the first session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher fetchByteacherId_First(
		long teacherId, OrderByComparator<SessionTeacher> orderByComparator) {

		List<SessionTeacher> list = findByteacherId(
			teacherId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher findByteacherId_Last(
			long teacherId, OrderByComparator<SessionTeacher> orderByComparator)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = fetchByteacherId_Last(
			teacherId, orderByComparator);

		if (sessionTeacher != null) {
			return sessionTeacher;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchSessionTeacherException(sb.toString());
	}

	/**
	 * Returns the last session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher fetchByteacherId_Last(
		long teacherId, OrderByComparator<SessionTeacher> orderByComparator) {

		int count = countByteacherId(teacherId);

		if (count == 0) {
			return null;
		}

		List<SessionTeacher> list = findByteacherId(
			teacherId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the session teachers before and after the current session teacher in the ordered set where teacherId = &#63;.
	 *
	 * @param sessionTeacherId the primary key of the current session teacher
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	@Override
	public SessionTeacher[] findByteacherId_PrevAndNext(
			long sessionTeacherId, long teacherId,
			OrderByComparator<SessionTeacher> orderByComparator)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = findByPrimaryKey(sessionTeacherId);

		Session session = null;

		try {
			session = openSession();

			SessionTeacher[] array = new SessionTeacherImpl[3];

			array[0] = getByteacherId_PrevAndNext(
				session, sessionTeacher, teacherId, orderByComparator, true);

			array[1] = sessionTeacher;

			array[2] = getByteacherId_PrevAndNext(
				session, sessionTeacher, teacherId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SessionTeacher getByteacherId_PrevAndNext(
		Session session, SessionTeacher sessionTeacher, long teacherId,
		OrderByComparator<SessionTeacher> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SESSIONTEACHER_WHERE);

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
			sb.append(SessionTeacherModelImpl.ORDER_BY_JPQL);
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
						sessionTeacher)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SessionTeacher> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the session teachers where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	@Override
	public void removeByteacherId(long teacherId) {
		for (SessionTeacher sessionTeacher :
				findByteacherId(
					teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sessionTeacher);
		}
	}

	/**
	 * Returns the number of session teachers where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching session teachers
	 */
	@Override
	public int countByteacherId(long teacherId) {
		FinderPath finderPath = _finderPathCountByteacherId;

		Object[] finderArgs = new Object[] {teacherId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SESSIONTEACHER_WHERE);

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
		"sessionTeacher.teacherId = ?";

	private FinderPath _finderPathWithPaginationFindBysessionId;
	private FinderPath _finderPathWithoutPaginationFindBysessionId;
	private FinderPath _finderPathCountBysessionId;

	/**
	 * Returns all the session teachers where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching session teachers
	 */
	@Override
	public List<SessionTeacher> findBysessionId(long sessionId) {
		return findBysessionId(
			sessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session teachers where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @return the range of matching session teachers
	 */
	@Override
	public List<SessionTeacher> findBysessionId(
		long sessionId, int start, int end) {

		return findBysessionId(sessionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the session teachers where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session teachers
	 */
	@Override
	public List<SessionTeacher> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator) {

		return findBysessionId(sessionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session teachers where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session teachers
	 */
	@Override
	public List<SessionTeacher> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBysessionId;
				finderArgs = new Object[] {sessionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBysessionId;
			finderArgs = new Object[] {
				sessionId, start, end, orderByComparator
			};
		}

		List<SessionTeacher> list = null;

		if (useFinderCache) {
			list = (List<SessionTeacher>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SessionTeacher sessionTeacher : list) {
					if (sessionId != sessionTeacher.getSessionId()) {
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

			sb.append(_SQL_SELECT_SESSIONTEACHER_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_SESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SessionTeacherModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				list = (List<SessionTeacher>)QueryUtil.list(
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
	 * Returns the first session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher findBysessionId_First(
			long sessionId, OrderByComparator<SessionTeacher> orderByComparator)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = fetchBysessionId_First(
			sessionId, orderByComparator);

		if (sessionTeacher != null) {
			return sessionTeacher;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append("}");

		throw new NoSuchSessionTeacherException(sb.toString());
	}

	/**
	 * Returns the first session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher fetchBysessionId_First(
		long sessionId, OrderByComparator<SessionTeacher> orderByComparator) {

		List<SessionTeacher> list = findBysessionId(
			sessionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher findBysessionId_Last(
			long sessionId, OrderByComparator<SessionTeacher> orderByComparator)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = fetchBysessionId_Last(
			sessionId, orderByComparator);

		if (sessionTeacher != null) {
			return sessionTeacher;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append("}");

		throw new NoSuchSessionTeacherException(sb.toString());
	}

	/**
	 * Returns the last session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher fetchBysessionId_Last(
		long sessionId, OrderByComparator<SessionTeacher> orderByComparator) {

		int count = countBysessionId(sessionId);

		if (count == 0) {
			return null;
		}

		List<SessionTeacher> list = findBysessionId(
			sessionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the session teachers before and after the current session teacher in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionTeacherId the primary key of the current session teacher
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	@Override
	public SessionTeacher[] findBysessionId_PrevAndNext(
			long sessionTeacherId, long sessionId,
			OrderByComparator<SessionTeacher> orderByComparator)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = findByPrimaryKey(sessionTeacherId);

		Session session = null;

		try {
			session = openSession();

			SessionTeacher[] array = new SessionTeacherImpl[3];

			array[0] = getBysessionId_PrevAndNext(
				session, sessionTeacher, sessionId, orderByComparator, true);

			array[1] = sessionTeacher;

			array[2] = getBysessionId_PrevAndNext(
				session, sessionTeacher, sessionId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SessionTeacher getBysessionId_PrevAndNext(
		Session session, SessionTeacher sessionTeacher, long sessionId,
		OrderByComparator<SessionTeacher> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SESSIONTEACHER_WHERE);

		sb.append(_FINDER_COLUMN_SESSIONID_SESSIONID_2);

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
			sb.append(SessionTeacherModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(sessionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sessionTeacher)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SessionTeacher> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the session teachers where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	@Override
	public void removeBysessionId(long sessionId) {
		for (SessionTeacher sessionTeacher :
				findBysessionId(
					sessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sessionTeacher);
		}
	}

	/**
	 * Returns the number of session teachers where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching session teachers
	 */
	@Override
	public int countBysessionId(long sessionId) {
		FinderPath finderPath = _finderPathCountBysessionId;

		Object[] finderArgs = new Object[] {sessionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SESSIONTEACHER_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_SESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

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

	private static final String _FINDER_COLUMN_SESSIONID_SESSIONID_2 =
		"sessionTeacher.sessionId = ?";

	private FinderPath _finderPathFetchBysessionId_teacherId;
	private FinderPath _finderPathCountBysessionId_teacherId;

	/**
	 * Returns the session teacher where sessionId = &#63; and teacherId = &#63; or throws a <code>NoSuchSessionTeacherException</code> if it could not be found.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher findBysessionId_teacherId(
			long sessionId, long teacherId)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = fetchBysessionId_teacherId(
			sessionId, teacherId);

		if (sessionTeacher == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("sessionId=");
			sb.append(sessionId);

			sb.append(", teacherId=");
			sb.append(teacherId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSessionTeacherException(sb.toString());
		}

		return sessionTeacher;
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and teacherId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher fetchBysessionId_teacherId(
		long sessionId, long teacherId) {

		return fetchBysessionId_teacherId(sessionId, teacherId, true);
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and teacherId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher fetchBysessionId_teacherId(
		long sessionId, long teacherId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {sessionId, teacherId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBysessionId_teacherId, finderArgs, this);
		}

		if (result instanceof SessionTeacher) {
			SessionTeacher sessionTeacher = (SessionTeacher)result;

			if ((sessionId != sessionTeacher.getSessionId()) ||
				(teacherId != sessionTeacher.getTeacherId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_SESSIONTEACHER_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_TEACHERID_SESSIONID_2);

			sb.append(_FINDER_COLUMN_SESSIONID_TEACHERID_TEACHERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				queryPos.add(teacherId);

				List<SessionTeacher> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBysessionId_teacherId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									sessionId, teacherId
								};
							}

							_log.warn(
								"SessionTeacherPersistenceImpl.fetchBysessionId_teacherId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SessionTeacher sessionTeacher = list.get(0);

					result = sessionTeacher;

					cacheResult(sessionTeacher);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SessionTeacher)result;
		}
	}

	/**
	 * Removes the session teacher where sessionId = &#63; and teacherId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the session teacher that was removed
	 */
	@Override
	public SessionTeacher removeBysessionId_teacherId(
			long sessionId, long teacherId)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = findBysessionId_teacherId(
			sessionId, teacherId);

		return remove(sessionTeacher);
	}

	/**
	 * Returns the number of session teachers where sessionId = &#63; and teacherId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param teacherId the teacher ID
	 * @return the number of matching session teachers
	 */
	@Override
	public int countBysessionId_teacherId(long sessionId, long teacherId) {
		FinderPath finderPath = _finderPathCountBysessionId_teacherId;

		Object[] finderArgs = new Object[] {sessionId, teacherId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SESSIONTEACHER_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_TEACHERID_SESSIONID_2);

			sb.append(_FINDER_COLUMN_SESSIONID_TEACHERID_TEACHERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

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

	private static final String _FINDER_COLUMN_SESSIONID_TEACHERID_SESSIONID_2 =
		"sessionTeacher.sessionId = ? AND ";

	private static final String _FINDER_COLUMN_SESSIONID_TEACHERID_TEACHERID_2 =
		"sessionTeacher.teacherId = ?";

	private FinderPath _finderPathFetchBysessionId_substituteId;
	private FinderPath _finderPathCountBysessionId_substituteId;

	/**
	 * Returns the session teacher where sessionId = &#63; and substituteId = &#63; or throws a <code>NoSuchSessionTeacherException</code> if it could not be found.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the matching session teacher
	 * @throws NoSuchSessionTeacherException if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher findBysessionId_substituteId(
			long sessionId, long substituteId)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = fetchBysessionId_substituteId(
			sessionId, substituteId);

		if (sessionTeacher == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("sessionId=");
			sb.append(sessionId);

			sb.append(", substituteId=");
			sb.append(substituteId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSessionTeacherException(sb.toString());
		}

		return sessionTeacher;
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and substituteId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher fetchBysessionId_substituteId(
		long sessionId, long substituteId) {

		return fetchBysessionId_substituteId(sessionId, substituteId, true);
	}

	/**
	 * Returns the session teacher where sessionId = &#63; and substituteId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching session teacher, or <code>null</code> if a matching session teacher could not be found
	 */
	@Override
	public SessionTeacher fetchBysessionId_substituteId(
		long sessionId, long substituteId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {sessionId, substituteId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBysessionId_substituteId, finderArgs, this);
		}

		if (result instanceof SessionTeacher) {
			SessionTeacher sessionTeacher = (SessionTeacher)result;

			if ((sessionId != sessionTeacher.getSessionId()) ||
				(substituteId != sessionTeacher.getSubstituteId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_SESSIONTEACHER_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_SUBSTITUTEID_SESSIONID_2);

			sb.append(_FINDER_COLUMN_SESSIONID_SUBSTITUTEID_SUBSTITUTEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				queryPos.add(substituteId);

				List<SessionTeacher> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBysessionId_substituteId,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									sessionId, substituteId
								};
							}

							_log.warn(
								"SessionTeacherPersistenceImpl.fetchBysessionId_substituteId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SessionTeacher sessionTeacher = list.get(0);

					result = sessionTeacher;

					cacheResult(sessionTeacher);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SessionTeacher)result;
		}
	}

	/**
	 * Removes the session teacher where sessionId = &#63; and substituteId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the session teacher that was removed
	 */
	@Override
	public SessionTeacher removeBysessionId_substituteId(
			long sessionId, long substituteId)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = findBysessionId_substituteId(
			sessionId, substituteId);

		return remove(sessionTeacher);
	}

	/**
	 * Returns the number of session teachers where sessionId = &#63; and substituteId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param substituteId the substitute ID
	 * @return the number of matching session teachers
	 */
	@Override
	public int countBysessionId_substituteId(
		long sessionId, long substituteId) {

		FinderPath finderPath = _finderPathCountBysessionId_substituteId;

		Object[] finderArgs = new Object[] {sessionId, substituteId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SESSIONTEACHER_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_SUBSTITUTEID_SESSIONID_2);

			sb.append(_FINDER_COLUMN_SESSIONID_SUBSTITUTEID_SUBSTITUTEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				queryPos.add(substituteId);

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
		_FINDER_COLUMN_SESSIONID_SUBSTITUTEID_SESSIONID_2 =
			"sessionTeacher.sessionId = ? AND ";

	private static final String
		_FINDER_COLUMN_SESSIONID_SUBSTITUTEID_SUBSTITUTEID_2 =
			"sessionTeacher.substituteId = ?";

	public SessionTeacherPersistenceImpl() {
		setModelClass(SessionTeacher.class);

		setModelImplClass(SessionTeacherImpl.class);
		setModelPKClass(long.class);

		setTable(SessionTeacherTable.INSTANCE);
	}

	/**
	 * Caches the session teacher in the entity cache if it is enabled.
	 *
	 * @param sessionTeacher the session teacher
	 */
	@Override
	public void cacheResult(SessionTeacher sessionTeacher) {
		entityCache.putResult(
			SessionTeacherImpl.class, sessionTeacher.getPrimaryKey(),
			sessionTeacher);

		finderCache.putResult(
			_finderPathFetchBysessionId_teacherId,
			new Object[] {
				sessionTeacher.getSessionId(), sessionTeacher.getTeacherId()
			},
			sessionTeacher);

		finderCache.putResult(
			_finderPathFetchBysessionId_substituteId,
			new Object[] {
				sessionTeacher.getSessionId(), sessionTeacher.getSubstituteId()
			},
			sessionTeacher);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the session teachers in the entity cache if it is enabled.
	 *
	 * @param sessionTeachers the session teachers
	 */
	@Override
	public void cacheResult(List<SessionTeacher> sessionTeachers) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sessionTeachers.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SessionTeacher sessionTeacher : sessionTeachers) {
			if (entityCache.getResult(
					SessionTeacherImpl.class, sessionTeacher.getPrimaryKey()) ==
						null) {

				cacheResult(sessionTeacher);
			}
		}
	}

	/**
	 * Clears the cache for all session teachers.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SessionTeacherImpl.class);

		finderCache.clearCache(SessionTeacherImpl.class);
	}

	/**
	 * Clears the cache for the session teacher.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SessionTeacher sessionTeacher) {
		entityCache.removeResult(SessionTeacherImpl.class, sessionTeacher);
	}

	@Override
	public void clearCache(List<SessionTeacher> sessionTeachers) {
		for (SessionTeacher sessionTeacher : sessionTeachers) {
			entityCache.removeResult(SessionTeacherImpl.class, sessionTeacher);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SessionTeacherImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SessionTeacherImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		SessionTeacherModelImpl sessionTeacherModelImpl) {

		Object[] args = new Object[] {
			sessionTeacherModelImpl.getSessionId(),
			sessionTeacherModelImpl.getTeacherId()
		};

		finderCache.putResult(
			_finderPathCountBysessionId_teacherId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBysessionId_teacherId, args,
			sessionTeacherModelImpl);

		args = new Object[] {
			sessionTeacherModelImpl.getSessionId(),
			sessionTeacherModelImpl.getSubstituteId()
		};

		finderCache.putResult(
			_finderPathCountBysessionId_substituteId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBysessionId_substituteId, args,
			sessionTeacherModelImpl);
	}

	/**
	 * Creates a new session teacher with the primary key. Does not add the session teacher to the database.
	 *
	 * @param sessionTeacherId the primary key for the new session teacher
	 * @return the new session teacher
	 */
	@Override
	public SessionTeacher create(long sessionTeacherId) {
		SessionTeacher sessionTeacher = new SessionTeacherImpl();

		sessionTeacher.setNew(true);
		sessionTeacher.setPrimaryKey(sessionTeacherId);

		return sessionTeacher;
	}

	/**
	 * Removes the session teacher with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher that was removed
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	@Override
	public SessionTeacher remove(long sessionTeacherId)
		throws NoSuchSessionTeacherException {

		return remove((Serializable)sessionTeacherId);
	}

	/**
	 * Removes the session teacher with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the session teacher
	 * @return the session teacher that was removed
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	@Override
	public SessionTeacher remove(Serializable primaryKey)
		throws NoSuchSessionTeacherException {

		Session session = null;

		try {
			session = openSession();

			SessionTeacher sessionTeacher = (SessionTeacher)session.get(
				SessionTeacherImpl.class, primaryKey);

			if (sessionTeacher == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSessionTeacherException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sessionTeacher);
		}
		catch (NoSuchSessionTeacherException noSuchEntityException) {
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
	protected SessionTeacher removeImpl(SessionTeacher sessionTeacher) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sessionTeacher)) {
				sessionTeacher = (SessionTeacher)session.get(
					SessionTeacherImpl.class,
					sessionTeacher.getPrimaryKeyObj());
			}

			if (sessionTeacher != null) {
				session.delete(sessionTeacher);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sessionTeacher != null) {
			clearCache(sessionTeacher);
		}

		return sessionTeacher;
	}

	@Override
	public SessionTeacher updateImpl(SessionTeacher sessionTeacher) {
		boolean isNew = sessionTeacher.isNew();

		if (!(sessionTeacher instanceof SessionTeacherModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(sessionTeacher.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					sessionTeacher);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sessionTeacher proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SessionTeacher implementation " +
					sessionTeacher.getClass());
		}

		SessionTeacherModelImpl sessionTeacherModelImpl =
			(SessionTeacherModelImpl)sessionTeacher;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sessionTeacher);
			}
			else {
				sessionTeacher = (SessionTeacher)session.merge(sessionTeacher);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SessionTeacherImpl.class, sessionTeacherModelImpl, false, true);

		cacheUniqueFindersCache(sessionTeacherModelImpl);

		if (isNew) {
			sessionTeacher.setNew(false);
		}

		sessionTeacher.resetOriginalValues();

		return sessionTeacher;
	}

	/**
	 * Returns the session teacher with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the session teacher
	 * @return the session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	@Override
	public SessionTeacher findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSessionTeacherException {

		SessionTeacher sessionTeacher = fetchByPrimaryKey(primaryKey);

		if (sessionTeacher == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSessionTeacherException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sessionTeacher;
	}

	/**
	 * Returns the session teacher with the primary key or throws a <code>NoSuchSessionTeacherException</code> if it could not be found.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher
	 * @throws NoSuchSessionTeacherException if a session teacher with the primary key could not be found
	 */
	@Override
	public SessionTeacher findByPrimaryKey(long sessionTeacherId)
		throws NoSuchSessionTeacherException {

		return findByPrimaryKey((Serializable)sessionTeacherId);
	}

	/**
	 * Returns the session teacher with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionTeacherId the primary key of the session teacher
	 * @return the session teacher, or <code>null</code> if a session teacher with the primary key could not be found
	 */
	@Override
	public SessionTeacher fetchByPrimaryKey(long sessionTeacherId) {
		return fetchByPrimaryKey((Serializable)sessionTeacherId);
	}

	/**
	 * Returns all the session teachers.
	 *
	 * @return the session teachers
	 */
	@Override
	public List<SessionTeacher> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session teachers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @return the range of session teachers
	 */
	@Override
	public List<SessionTeacher> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the session teachers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of session teachers
	 */
	@Override
	public List<SessionTeacher> findAll(
		int start, int end,
		OrderByComparator<SessionTeacher> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session teachers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionTeacherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session teachers
	 * @param end the upper bound of the range of session teachers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of session teachers
	 */
	@Override
	public List<SessionTeacher> findAll(
		int start, int end, OrderByComparator<SessionTeacher> orderByComparator,
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

		List<SessionTeacher> list = null;

		if (useFinderCache) {
			list = (List<SessionTeacher>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SESSIONTEACHER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SESSIONTEACHER;

				sql = sql.concat(SessionTeacherModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SessionTeacher>)QueryUtil.list(
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
	 * Removes all the session teachers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SessionTeacher sessionTeacher : findAll()) {
			remove(sessionTeacher);
		}
	}

	/**
	 * Returns the number of session teachers.
	 *
	 * @return the number of session teachers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SESSIONTEACHER);

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
		return "sessionTeacherId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SESSIONTEACHER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SessionTeacherModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the session teacher persistence.
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

		_finderPathWithPaginationFindBysessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysessionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"sessionId"}, true);

		_finderPathWithoutPaginationFindBysessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBysessionId",
			new String[] {Long.class.getName()}, new String[] {"sessionId"},
			true);

		_finderPathCountBysessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysessionId",
			new String[] {Long.class.getName()}, new String[] {"sessionId"},
			false);

		_finderPathFetchBysessionId_teacherId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBysessionId_teacherId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sessionId", "teacherId"}, true);

		_finderPathCountBysessionId_teacherId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBysessionId_teacherId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sessionId", "teacherId"}, false);

		_finderPathFetchBysessionId_substituteId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBysessionId_substituteId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sessionId", "substituteId"}, true);

		_finderPathCountBysessionId_substituteId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBysessionId_substituteId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sessionId", "substituteId"}, false);

		_setSessionTeacherUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSessionTeacherUtilPersistence(null);

		entityCache.removeCache(SessionTeacherImpl.class.getName());
	}

	private void _setSessionTeacherUtilPersistence(
		SessionTeacherPersistence sessionTeacherPersistence) {

		try {
			Field field = SessionTeacherUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, sessionTeacherPersistence);
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

	private static final String _SQL_SELECT_SESSIONTEACHER =
		"SELECT sessionTeacher FROM SessionTeacher sessionTeacher";

	private static final String _SQL_SELECT_SESSIONTEACHER_WHERE =
		"SELECT sessionTeacher FROM SessionTeacher sessionTeacher WHERE ";

	private static final String _SQL_COUNT_SESSIONTEACHER =
		"SELECT COUNT(sessionTeacher) FROM SessionTeacher sessionTeacher";

	private static final String _SQL_COUNT_SESSIONTEACHER_WHERE =
		"SELECT COUNT(sessionTeacher) FROM SessionTeacher sessionTeacher WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "sessionTeacher.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SessionTeacher exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SessionTeacher exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SessionTeacherPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}