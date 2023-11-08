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

import com.weprode.facile.schedule.exception.NoSuchSessionStudentException;
import com.weprode.facile.schedule.model.SessionStudent;
import com.weprode.facile.schedule.model.SessionStudentTable;
import com.weprode.facile.schedule.model.impl.SessionStudentImpl;
import com.weprode.facile.schedule.model.impl.SessionStudentModelImpl;
import com.weprode.facile.schedule.service.persistence.SessionStudentPersistence;
import com.weprode.facile.schedule.service.persistence.SessionStudentUtil;
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
 * The persistence implementation for the session student service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {SessionStudentPersistence.class, BasePersistence.class})
public class SessionStudentPersistenceImpl
	extends BasePersistenceImpl<SessionStudent>
	implements SessionStudentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SessionStudentUtil</code> to access the session student persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SessionStudentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBysessionId;
	private FinderPath _finderPathWithoutPaginationFindBysessionId;
	private FinderPath _finderPathCountBysessionId;

	/**
	 * Returns all the session students where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching session students
	 */
	@Override
	public List<SessionStudent> findBysessionId(long sessionId) {
		return findBysessionId(
			sessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session students where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of matching session students
	 */
	@Override
	public List<SessionStudent> findBysessionId(
		long sessionId, int start, int end) {

		return findBysessionId(sessionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session students
	 */
	@Override
	public List<SessionStudent> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator) {

		return findBysessionId(sessionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session students
	 */
	@Override
	public List<SessionStudent> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator,
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

		List<SessionStudent> list = null;

		if (useFinderCache) {
			list = (List<SessionStudent>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SessionStudent sessionStudent : list) {
					if (sessionId != sessionStudent.getSessionId()) {
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

			sb.append(_SQL_SELECT_SESSIONSTUDENT_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_SESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SessionStudentModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				list = (List<SessionStudent>)QueryUtil.list(
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
	 * Returns the first session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	@Override
	public SessionStudent findBysessionId_First(
			long sessionId, OrderByComparator<SessionStudent> orderByComparator)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = fetchBysessionId_First(
			sessionId, orderByComparator);

		if (sessionStudent != null) {
			return sessionStudent;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append("}");

		throw new NoSuchSessionStudentException(sb.toString());
	}

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student, or <code>null</code> if a matching session student could not be found
	 */
	@Override
	public SessionStudent fetchBysessionId_First(
		long sessionId, OrderByComparator<SessionStudent> orderByComparator) {

		List<SessionStudent> list = findBysessionId(
			sessionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	@Override
	public SessionStudent findBysessionId_Last(
			long sessionId, OrderByComparator<SessionStudent> orderByComparator)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = fetchBysessionId_Last(
			sessionId, orderByComparator);

		if (sessionStudent != null) {
			return sessionStudent;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append("}");

		throw new NoSuchSessionStudentException(sb.toString());
	}

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student, or <code>null</code> if a matching session student could not be found
	 */
	@Override
	public SessionStudent fetchBysessionId_Last(
		long sessionId, OrderByComparator<SessionStudent> orderByComparator) {

		int count = countBysessionId(sessionId);

		if (count == 0) {
			return null;
		}

		List<SessionStudent> list = findBysessionId(
			sessionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the session students before and after the current session student in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionStudentId the primary key of the current session student
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	@Override
	public SessionStudent[] findBysessionId_PrevAndNext(
			long sessionStudentId, long sessionId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = findByPrimaryKey(sessionStudentId);

		Session session = null;

		try {
			session = openSession();

			SessionStudent[] array = new SessionStudentImpl[3];

			array[0] = getBysessionId_PrevAndNext(
				session, sessionStudent, sessionId, orderByComparator, true);

			array[1] = sessionStudent;

			array[2] = getBysessionId_PrevAndNext(
				session, sessionStudent, sessionId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SessionStudent getBysessionId_PrevAndNext(
		Session session, SessionStudent sessionStudent, long sessionId,
		OrderByComparator<SessionStudent> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SESSIONSTUDENT_WHERE);

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
			sb.append(SessionStudentModelImpl.ORDER_BY_JPQL);
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
						sessionStudent)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SessionStudent> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the session students where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	@Override
	public void removeBysessionId(long sessionId) {
		for (SessionStudent sessionStudent :
				findBysessionId(
					sessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sessionStudent);
		}
	}

	/**
	 * Returns the number of session students where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching session students
	 */
	@Override
	public int countBysessionId(long sessionId) {
		FinderPath finderPath = _finderPathCountBysessionId;

		Object[] finderArgs = new Object[] {sessionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SESSIONSTUDENT_WHERE);

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
		"sessionStudent.sessionId = ?";

	private FinderPath _finderPathWithPaginationFindBystudentId;
	private FinderPath _finderPathWithoutPaginationFindBystudentId;
	private FinderPath _finderPathCountBystudentId;

	/**
	 * Returns all the session students where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the matching session students
	 */
	@Override
	public List<SessionStudent> findBystudentId(long studentId) {
		return findBystudentId(
			studentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session students where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of matching session students
	 */
	@Override
	public List<SessionStudent> findBystudentId(
		long studentId, int start, int end) {

		return findBystudentId(studentId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the session students where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session students
	 */
	@Override
	public List<SessionStudent> findBystudentId(
		long studentId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator) {

		return findBystudentId(studentId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session students where studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session students
	 */
	@Override
	public List<SessionStudent> findBystudentId(
		long studentId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator,
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

		List<SessionStudent> list = null;

		if (useFinderCache) {
			list = (List<SessionStudent>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SessionStudent sessionStudent : list) {
					if (studentId != sessionStudent.getStudentId()) {
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

			sb.append(_SQL_SELECT_SESSIONSTUDENT_WHERE);

			sb.append(_FINDER_COLUMN_STUDENTID_STUDENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SessionStudentModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(studentId);

				list = (List<SessionStudent>)QueryUtil.list(
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
	 * Returns the first session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	@Override
	public SessionStudent findBystudentId_First(
			long studentId, OrderByComparator<SessionStudent> orderByComparator)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = fetchBystudentId_First(
			studentId, orderByComparator);

		if (sessionStudent != null) {
			return sessionStudent;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("studentId=");
		sb.append(studentId);

		sb.append("}");

		throw new NoSuchSessionStudentException(sb.toString());
	}

	/**
	 * Returns the first session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student, or <code>null</code> if a matching session student could not be found
	 */
	@Override
	public SessionStudent fetchBystudentId_First(
		long studentId, OrderByComparator<SessionStudent> orderByComparator) {

		List<SessionStudent> list = findBystudentId(
			studentId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	@Override
	public SessionStudent findBystudentId_Last(
			long studentId, OrderByComparator<SessionStudent> orderByComparator)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = fetchBystudentId_Last(
			studentId, orderByComparator);

		if (sessionStudent != null) {
			return sessionStudent;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("studentId=");
		sb.append(studentId);

		sb.append("}");

		throw new NoSuchSessionStudentException(sb.toString());
	}

	/**
	 * Returns the last session student in the ordered set where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student, or <code>null</code> if a matching session student could not be found
	 */
	@Override
	public SessionStudent fetchBystudentId_Last(
		long studentId, OrderByComparator<SessionStudent> orderByComparator) {

		int count = countBystudentId(studentId);

		if (count == 0) {
			return null;
		}

		List<SessionStudent> list = findBystudentId(
			studentId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the session students before and after the current session student in the ordered set where studentId = &#63;.
	 *
	 * @param sessionStudentId the primary key of the current session student
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	@Override
	public SessionStudent[] findBystudentId_PrevAndNext(
			long sessionStudentId, long studentId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = findByPrimaryKey(sessionStudentId);

		Session session = null;

		try {
			session = openSession();

			SessionStudent[] array = new SessionStudentImpl[3];

			array[0] = getBystudentId_PrevAndNext(
				session, sessionStudent, studentId, orderByComparator, true);

			array[1] = sessionStudent;

			array[2] = getBystudentId_PrevAndNext(
				session, sessionStudent, studentId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SessionStudent getBystudentId_PrevAndNext(
		Session session, SessionStudent sessionStudent, long studentId,
		OrderByComparator<SessionStudent> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SESSIONSTUDENT_WHERE);

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
			sb.append(SessionStudentModelImpl.ORDER_BY_JPQL);
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
						sessionStudent)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SessionStudent> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the session students where studentId = &#63; from the database.
	 *
	 * @param studentId the student ID
	 */
	@Override
	public void removeBystudentId(long studentId) {
		for (SessionStudent sessionStudent :
				findBystudentId(
					studentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sessionStudent);
		}
	}

	/**
	 * Returns the number of session students where studentId = &#63;.
	 *
	 * @param studentId the student ID
	 * @return the number of matching session students
	 */
	@Override
	public int countBystudentId(long studentId) {
		FinderPath finderPath = _finderPathCountBystudentId;

		Object[] finderArgs = new Object[] {studentId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SESSIONSTUDENT_WHERE);

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
		"sessionStudent.studentId = ?";

	private FinderPath _finderPathWithPaginationFindBysessionId_studentId;
	private FinderPath _finderPathWithoutPaginationFindBysessionId_studentId;
	private FinderPath _finderPathCountBysessionId_studentId;

	/**
	 * Returns all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @return the matching session students
	 */
	@Override
	public List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId) {

		return findBysessionId_studentId(
			sessionId, studentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of matching session students
	 */
	@Override
	public List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId, int start, int end) {

		return findBysessionId_studentId(
			sessionId, studentId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session students
	 */
	@Override
	public List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator) {

		return findBysessionId_studentId(
			sessionId, studentId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session students
	 */
	@Override
	public List<SessionStudent> findBysessionId_studentId(
		long sessionId, long studentId, int start, int end,
		OrderByComparator<SessionStudent> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindBysessionId_studentId;
				finderArgs = new Object[] {sessionId, studentId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBysessionId_studentId;
			finderArgs = new Object[] {
				sessionId, studentId, start, end, orderByComparator
			};
		}

		List<SessionStudent> list = null;

		if (useFinderCache) {
			list = (List<SessionStudent>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SessionStudent sessionStudent : list) {
					if ((sessionId != sessionStudent.getSessionId()) ||
						(studentId != sessionStudent.getStudentId())) {

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

			sb.append(_SQL_SELECT_SESSIONSTUDENT_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_STUDENTID_SESSIONID_2);

			sb.append(_FINDER_COLUMN_SESSIONID_STUDENTID_STUDENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SessionStudentModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				queryPos.add(studentId);

				list = (List<SessionStudent>)QueryUtil.list(
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
	 * Returns the first session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	@Override
	public SessionStudent findBysessionId_studentId_First(
			long sessionId, long studentId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = fetchBysessionId_studentId_First(
			sessionId, studentId, orderByComparator);

		if (sessionStudent != null) {
			return sessionStudent;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append(", studentId=");
		sb.append(studentId);

		sb.append("}");

		throw new NoSuchSessionStudentException(sb.toString());
	}

	/**
	 * Returns the first session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session student, or <code>null</code> if a matching session student could not be found
	 */
	@Override
	public SessionStudent fetchBysessionId_studentId_First(
		long sessionId, long studentId,
		OrderByComparator<SessionStudent> orderByComparator) {

		List<SessionStudent> list = findBysessionId_studentId(
			sessionId, studentId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student
	 * @throws NoSuchSessionStudentException if a matching session student could not be found
	 */
	@Override
	public SessionStudent findBysessionId_studentId_Last(
			long sessionId, long studentId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = fetchBysessionId_studentId_Last(
			sessionId, studentId, orderByComparator);

		if (sessionStudent != null) {
			return sessionStudent;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append(", studentId=");
		sb.append(studentId);

		sb.append("}");

		throw new NoSuchSessionStudentException(sb.toString());
	}

	/**
	 * Returns the last session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session student, or <code>null</code> if a matching session student could not be found
	 */
	@Override
	public SessionStudent fetchBysessionId_studentId_Last(
		long sessionId, long studentId,
		OrderByComparator<SessionStudent> orderByComparator) {

		int count = countBysessionId_studentId(sessionId, studentId);

		if (count == 0) {
			return null;
		}

		List<SessionStudent> list = findBysessionId_studentId(
			sessionId, studentId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the session students before and after the current session student in the ordered set where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionStudentId the primary key of the current session student
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	@Override
	public SessionStudent[] findBysessionId_studentId_PrevAndNext(
			long sessionStudentId, long sessionId, long studentId,
			OrderByComparator<SessionStudent> orderByComparator)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = findByPrimaryKey(sessionStudentId);

		Session session = null;

		try {
			session = openSession();

			SessionStudent[] array = new SessionStudentImpl[3];

			array[0] = getBysessionId_studentId_PrevAndNext(
				session, sessionStudent, sessionId, studentId,
				orderByComparator, true);

			array[1] = sessionStudent;

			array[2] = getBysessionId_studentId_PrevAndNext(
				session, sessionStudent, sessionId, studentId,
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

	protected SessionStudent getBysessionId_studentId_PrevAndNext(
		Session session, SessionStudent sessionStudent, long sessionId,
		long studentId, OrderByComparator<SessionStudent> orderByComparator,
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

		sb.append(_SQL_SELECT_SESSIONSTUDENT_WHERE);

		sb.append(_FINDER_COLUMN_SESSIONID_STUDENTID_SESSIONID_2);

		sb.append(_FINDER_COLUMN_SESSIONID_STUDENTID_STUDENTID_2);

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
			sb.append(SessionStudentModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(sessionId);

		queryPos.add(studentId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sessionStudent)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SessionStudent> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the session students where sessionId = &#63; and studentId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 */
	@Override
	public void removeBysessionId_studentId(long sessionId, long studentId) {
		for (SessionStudent sessionStudent :
				findBysessionId_studentId(
					sessionId, studentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(sessionStudent);
		}
	}

	/**
	 * Returns the number of session students where sessionId = &#63; and studentId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param studentId the student ID
	 * @return the number of matching session students
	 */
	@Override
	public int countBysessionId_studentId(long sessionId, long studentId) {
		FinderPath finderPath = _finderPathCountBysessionId_studentId;

		Object[] finderArgs = new Object[] {sessionId, studentId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SESSIONSTUDENT_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_STUDENTID_SESSIONID_2);

			sb.append(_FINDER_COLUMN_SESSIONID_STUDENTID_STUDENTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

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

	private static final String _FINDER_COLUMN_SESSIONID_STUDENTID_SESSIONID_2 =
		"sessionStudent.sessionId = ? AND ";

	private static final String _FINDER_COLUMN_SESSIONID_STUDENTID_STUDENTID_2 =
		"sessionStudent.studentId = ?";

	public SessionStudentPersistenceImpl() {
		setModelClass(SessionStudent.class);

		setModelImplClass(SessionStudentImpl.class);
		setModelPKClass(long.class);

		setTable(SessionStudentTable.INSTANCE);
	}

	/**
	 * Caches the session student in the entity cache if it is enabled.
	 *
	 * @param sessionStudent the session student
	 */
	@Override
	public void cacheResult(SessionStudent sessionStudent) {
		entityCache.putResult(
			SessionStudentImpl.class, sessionStudent.getPrimaryKey(),
			sessionStudent);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the session students in the entity cache if it is enabled.
	 *
	 * @param sessionStudents the session students
	 */
	@Override
	public void cacheResult(List<SessionStudent> sessionStudents) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sessionStudents.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SessionStudent sessionStudent : sessionStudents) {
			if (entityCache.getResult(
					SessionStudentImpl.class, sessionStudent.getPrimaryKey()) ==
						null) {

				cacheResult(sessionStudent);
			}
		}
	}

	/**
	 * Clears the cache for all session students.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SessionStudentImpl.class);

		finderCache.clearCache(SessionStudentImpl.class);
	}

	/**
	 * Clears the cache for the session student.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SessionStudent sessionStudent) {
		entityCache.removeResult(SessionStudentImpl.class, sessionStudent);
	}

	@Override
	public void clearCache(List<SessionStudent> sessionStudents) {
		for (SessionStudent sessionStudent : sessionStudents) {
			entityCache.removeResult(SessionStudentImpl.class, sessionStudent);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SessionStudentImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SessionStudentImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new session student with the primary key. Does not add the session student to the database.
	 *
	 * @param sessionStudentId the primary key for the new session student
	 * @return the new session student
	 */
	@Override
	public SessionStudent create(long sessionStudentId) {
		SessionStudent sessionStudent = new SessionStudentImpl();

		sessionStudent.setNew(true);
		sessionStudent.setPrimaryKey(sessionStudentId);

		return sessionStudent;
	}

	/**
	 * Removes the session student with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student that was removed
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	@Override
	public SessionStudent remove(long sessionStudentId)
		throws NoSuchSessionStudentException {

		return remove((Serializable)sessionStudentId);
	}

	/**
	 * Removes the session student with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the session student
	 * @return the session student that was removed
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	@Override
	public SessionStudent remove(Serializable primaryKey)
		throws NoSuchSessionStudentException {

		Session session = null;

		try {
			session = openSession();

			SessionStudent sessionStudent = (SessionStudent)session.get(
				SessionStudentImpl.class, primaryKey);

			if (sessionStudent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSessionStudentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sessionStudent);
		}
		catch (NoSuchSessionStudentException noSuchEntityException) {
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
	protected SessionStudent removeImpl(SessionStudent sessionStudent) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sessionStudent)) {
				sessionStudent = (SessionStudent)session.get(
					SessionStudentImpl.class,
					sessionStudent.getPrimaryKeyObj());
			}

			if (sessionStudent != null) {
				session.delete(sessionStudent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sessionStudent != null) {
			clearCache(sessionStudent);
		}

		return sessionStudent;
	}

	@Override
	public SessionStudent updateImpl(SessionStudent sessionStudent) {
		boolean isNew = sessionStudent.isNew();

		if (!(sessionStudent instanceof SessionStudentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(sessionStudent.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					sessionStudent);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sessionStudent proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SessionStudent implementation " +
					sessionStudent.getClass());
		}

		SessionStudentModelImpl sessionStudentModelImpl =
			(SessionStudentModelImpl)sessionStudent;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sessionStudent);
			}
			else {
				sessionStudent = (SessionStudent)session.merge(sessionStudent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SessionStudentImpl.class, sessionStudentModelImpl, false, true);

		if (isNew) {
			sessionStudent.setNew(false);
		}

		sessionStudent.resetOriginalValues();

		return sessionStudent;
	}

	/**
	 * Returns the session student with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the session student
	 * @return the session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	@Override
	public SessionStudent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSessionStudentException {

		SessionStudent sessionStudent = fetchByPrimaryKey(primaryKey);

		if (sessionStudent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSessionStudentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sessionStudent;
	}

	/**
	 * Returns the session student with the primary key or throws a <code>NoSuchSessionStudentException</code> if it could not be found.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student
	 * @throws NoSuchSessionStudentException if a session student with the primary key could not be found
	 */
	@Override
	public SessionStudent findByPrimaryKey(long sessionStudentId)
		throws NoSuchSessionStudentException {

		return findByPrimaryKey((Serializable)sessionStudentId);
	}

	/**
	 * Returns the session student with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionStudentId the primary key of the session student
	 * @return the session student, or <code>null</code> if a session student with the primary key could not be found
	 */
	@Override
	public SessionStudent fetchByPrimaryKey(long sessionStudentId) {
		return fetchByPrimaryKey((Serializable)sessionStudentId);
	}

	/**
	 * Returns all the session students.
	 *
	 * @return the session students
	 */
	@Override
	public List<SessionStudent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @return the range of session students
	 */
	@Override
	public List<SessionStudent> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of session students
	 */
	@Override
	public List<SessionStudent> findAll(
		int start, int end,
		OrderByComparator<SessionStudent> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session students.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionStudentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session students
	 * @param end the upper bound of the range of session students (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of session students
	 */
	@Override
	public List<SessionStudent> findAll(
		int start, int end, OrderByComparator<SessionStudent> orderByComparator,
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

		List<SessionStudent> list = null;

		if (useFinderCache) {
			list = (List<SessionStudent>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SESSIONSTUDENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SESSIONSTUDENT;

				sql = sql.concat(SessionStudentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SessionStudent>)QueryUtil.list(
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
	 * Removes all the session students from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SessionStudent sessionStudent : findAll()) {
			remove(sessionStudent);
		}
	}

	/**
	 * Returns the number of session students.
	 *
	 * @return the number of session students
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SESSIONSTUDENT);

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
		return "sessionStudentId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SESSIONSTUDENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SessionStudentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the session student persistence.
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

		_finderPathWithPaginationFindBysessionId_studentId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysessionId_studentId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"sessionId", "studentId"}, true);

		_finderPathWithoutPaginationFindBysessionId_studentId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBysessionId_studentId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sessionId", "studentId"}, true);

		_finderPathCountBysessionId_studentId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBysessionId_studentId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sessionId", "studentId"}, false);

		_setSessionStudentUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSessionStudentUtilPersistence(null);

		entityCache.removeCache(SessionStudentImpl.class.getName());
	}

	private void _setSessionStudentUtilPersistence(
		SessionStudentPersistence sessionStudentPersistence) {

		try {
			Field field = SessionStudentUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, sessionStudentPersistence);
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

	private static final String _SQL_SELECT_SESSIONSTUDENT =
		"SELECT sessionStudent FROM SessionStudent sessionStudent";

	private static final String _SQL_SELECT_SESSIONSTUDENT_WHERE =
		"SELECT sessionStudent FROM SessionStudent sessionStudent WHERE ";

	private static final String _SQL_COUNT_SESSIONSTUDENT =
		"SELECT COUNT(sessionStudent) FROM SessionStudent sessionStudent";

	private static final String _SQL_COUNT_SESSIONSTUDENT_WHERE =
		"SELECT COUNT(sessionStudent) FROM SessionStudent sessionStudent WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "sessionStudent.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SessionStudent exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SessionStudent exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SessionStudentPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}