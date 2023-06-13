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
import com.liferay.portal.kernel.util.SetUtil;

import com.weprode.nero.schedule.exception.NoSuchCDTSessionException;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.CDTSessionTable;
import com.weprode.nero.schedule.model.impl.CDTSessionImpl;
import com.weprode.nero.schedule.model.impl.CDTSessionModelImpl;
import com.weprode.nero.schedule.service.persistence.CDTSessionPersistence;
import com.weprode.nero.schedule.service.persistence.CDTSessionUtil;
import com.weprode.nero.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

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
 * The persistence implementation for the cdt session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {CDTSessionPersistence.class, BasePersistence.class})
public class CDTSessionPersistenceImpl
	extends BasePersistenceImpl<CDTSession> implements CDTSessionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CDTSessionUtil</code> to access the cdt session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CDTSessionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBygroupId;
	private FinderPath _finderPathWithoutPaginationFindBygroupId;
	private FinderPath _finderPathCountBygroupId;

	/**
	 * Returns all the cdt sessions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cdt sessions
	 */
	@Override
	public List<CDTSession> findBygroupId(long groupId) {
		return findBygroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cdt sessions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @return the range of matching cdt sessions
	 */
	@Override
	public List<CDTSession> findBygroupId(long groupId, int start, int end) {
		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cdt sessions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cdt sessions
	 */
	@Override
	public List<CDTSession> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<CDTSession> orderByComparator) {

		return findBygroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cdt sessions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cdt sessions
	 */
	@Override
	public List<CDTSession> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<CDTSession> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBygroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBygroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<CDTSession> list = null;

		if (useFinderCache) {
			list = (List<CDTSession>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CDTSession cdtSession : list) {
					if (groupId != cdtSession.getGroupId()) {
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

			sb.append(_SQL_SELECT_CDTSESSION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CDTSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<CDTSession>)QueryUtil.list(
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
	 * Returns the first cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cdt session
	 * @throws NoSuchCDTSessionException if a matching cdt session could not be found
	 */
	@Override
	public CDTSession findBygroupId_First(
			long groupId, OrderByComparator<CDTSession> orderByComparator)
		throws NoSuchCDTSessionException {

		CDTSession cdtSession = fetchBygroupId_First(
			groupId, orderByComparator);

		if (cdtSession != null) {
			return cdtSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchCDTSessionException(sb.toString());
	}

	/**
	 * Returns the first cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cdt session, or <code>null</code> if a matching cdt session could not be found
	 */
	@Override
	public CDTSession fetchBygroupId_First(
		long groupId, OrderByComparator<CDTSession> orderByComparator) {

		List<CDTSession> list = findBygroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cdt session
	 * @throws NoSuchCDTSessionException if a matching cdt session could not be found
	 */
	@Override
	public CDTSession findBygroupId_Last(
			long groupId, OrderByComparator<CDTSession> orderByComparator)
		throws NoSuchCDTSessionException {

		CDTSession cdtSession = fetchBygroupId_Last(groupId, orderByComparator);

		if (cdtSession != null) {
			return cdtSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchCDTSessionException(sb.toString());
	}

	/**
	 * Returns the last cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cdt session, or <code>null</code> if a matching cdt session could not be found
	 */
	@Override
	public CDTSession fetchBygroupId_Last(
		long groupId, OrderByComparator<CDTSession> orderByComparator) {

		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CDTSession> list = findBygroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cdt sessions before and after the current cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param sessionId the primary key of the current cdt session
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cdt session
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	@Override
	public CDTSession[] findBygroupId_PrevAndNext(
			long sessionId, long groupId,
			OrderByComparator<CDTSession> orderByComparator)
		throws NoSuchCDTSessionException {

		CDTSession cdtSession = findByPrimaryKey(sessionId);

		Session session = null;

		try {
			session = openSession();

			CDTSession[] array = new CDTSessionImpl[3];

			array[0] = getBygroupId_PrevAndNext(
				session, cdtSession, groupId, orderByComparator, true);

			array[1] = cdtSession;

			array[2] = getBygroupId_PrevAndNext(
				session, cdtSession, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CDTSession getBygroupId_PrevAndNext(
		Session session, CDTSession cdtSession, long groupId,
		OrderByComparator<CDTSession> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CDTSESSION_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(CDTSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cdtSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CDTSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cdt sessions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupId(long groupId) {
		for (CDTSession cdtSession :
				findBygroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cdtSession);
		}
	}

	/**
	 * Returns the number of cdt sessions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cdt sessions
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = _finderPathCountBygroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CDTSESSION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"cdtSession.groupId = ?";

	private FinderPath _finderPathWithPaginationFindBycourseItemId;
	private FinderPath _finderPathWithoutPaginationFindBycourseItemId;
	private FinderPath _finderPathCountBycourseItemId;

	/**
	 * Returns all the cdt sessions where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @return the matching cdt sessions
	 */
	@Override
	public List<CDTSession> findBycourseItemId(long courseItemId) {
		return findBycourseItemId(
			courseItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cdt sessions where courseItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param courseItemId the course item ID
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @return the range of matching cdt sessions
	 */
	@Override
	public List<CDTSession> findBycourseItemId(
		long courseItemId, int start, int end) {

		return findBycourseItemId(courseItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cdt sessions where courseItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param courseItemId the course item ID
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cdt sessions
	 */
	@Override
	public List<CDTSession> findBycourseItemId(
		long courseItemId, int start, int end,
		OrderByComparator<CDTSession> orderByComparator) {

		return findBycourseItemId(
			courseItemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cdt sessions where courseItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param courseItemId the course item ID
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cdt sessions
	 */
	@Override
	public List<CDTSession> findBycourseItemId(
		long courseItemId, int start, int end,
		OrderByComparator<CDTSession> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBycourseItemId;
				finderArgs = new Object[] {courseItemId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBycourseItemId;
			finderArgs = new Object[] {
				courseItemId, start, end, orderByComparator
			};
		}

		List<CDTSession> list = null;

		if (useFinderCache) {
			list = (List<CDTSession>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CDTSession cdtSession : list) {
					if (courseItemId != cdtSession.getCourseItemId()) {
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

			sb.append(_SQL_SELECT_CDTSESSION_WHERE);

			sb.append(_FINDER_COLUMN_COURSEITEMID_COURSEITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CDTSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(courseItemId);

				list = (List<CDTSession>)QueryUtil.list(
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
	 * Returns the first cdt session in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cdt session
	 * @throws NoSuchCDTSessionException if a matching cdt session could not be found
	 */
	@Override
	public CDTSession findBycourseItemId_First(
			long courseItemId, OrderByComparator<CDTSession> orderByComparator)
		throws NoSuchCDTSessionException {

		CDTSession cdtSession = fetchBycourseItemId_First(
			courseItemId, orderByComparator);

		if (cdtSession != null) {
			return cdtSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("courseItemId=");
		sb.append(courseItemId);

		sb.append("}");

		throw new NoSuchCDTSessionException(sb.toString());
	}

	/**
	 * Returns the first cdt session in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cdt session, or <code>null</code> if a matching cdt session could not be found
	 */
	@Override
	public CDTSession fetchBycourseItemId_First(
		long courseItemId, OrderByComparator<CDTSession> orderByComparator) {

		List<CDTSession> list = findBycourseItemId(
			courseItemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cdt session in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cdt session
	 * @throws NoSuchCDTSessionException if a matching cdt session could not be found
	 */
	@Override
	public CDTSession findBycourseItemId_Last(
			long courseItemId, OrderByComparator<CDTSession> orderByComparator)
		throws NoSuchCDTSessionException {

		CDTSession cdtSession = fetchBycourseItemId_Last(
			courseItemId, orderByComparator);

		if (cdtSession != null) {
			return cdtSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("courseItemId=");
		sb.append(courseItemId);

		sb.append("}");

		throw new NoSuchCDTSessionException(sb.toString());
	}

	/**
	 * Returns the last cdt session in the ordered set where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cdt session, or <code>null</code> if a matching cdt session could not be found
	 */
	@Override
	public CDTSession fetchBycourseItemId_Last(
		long courseItemId, OrderByComparator<CDTSession> orderByComparator) {

		int count = countBycourseItemId(courseItemId);

		if (count == 0) {
			return null;
		}

		List<CDTSession> list = findBycourseItemId(
			courseItemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cdt sessions before and after the current cdt session in the ordered set where courseItemId = &#63;.
	 *
	 * @param sessionId the primary key of the current cdt session
	 * @param courseItemId the course item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cdt session
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	@Override
	public CDTSession[] findBycourseItemId_PrevAndNext(
			long sessionId, long courseItemId,
			OrderByComparator<CDTSession> orderByComparator)
		throws NoSuchCDTSessionException {

		CDTSession cdtSession = findByPrimaryKey(sessionId);

		Session session = null;

		try {
			session = openSession();

			CDTSession[] array = new CDTSessionImpl[3];

			array[0] = getBycourseItemId_PrevAndNext(
				session, cdtSession, courseItemId, orderByComparator, true);

			array[1] = cdtSession;

			array[2] = getBycourseItemId_PrevAndNext(
				session, cdtSession, courseItemId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CDTSession getBycourseItemId_PrevAndNext(
		Session session, CDTSession cdtSession, long courseItemId,
		OrderByComparator<CDTSession> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CDTSESSION_WHERE);

		sb.append(_FINDER_COLUMN_COURSEITEMID_COURSEITEMID_2);

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
			sb.append(CDTSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(courseItemId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cdtSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CDTSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cdt sessions where courseItemId = &#63; from the database.
	 *
	 * @param courseItemId the course item ID
	 */
	@Override
	public void removeBycourseItemId(long courseItemId) {
		for (CDTSession cdtSession :
				findBycourseItemId(
					courseItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cdtSession);
		}
	}

	/**
	 * Returns the number of cdt sessions where courseItemId = &#63;.
	 *
	 * @param courseItemId the course item ID
	 * @return the number of matching cdt sessions
	 */
	@Override
	public int countBycourseItemId(long courseItemId) {
		FinderPath finderPath = _finderPathCountBycourseItemId;

		Object[] finderArgs = new Object[] {courseItemId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CDTSESSION_WHERE);

			sb.append(_FINDER_COLUMN_COURSEITEMID_COURSEITEMID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(courseItemId);

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

	private static final String _FINDER_COLUMN_COURSEITEMID_COURSEITEMID_2 =
		"cdtSession.courseItemId = ?";

	public CDTSessionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("start", "start_");
		dbColumnNames.put("end", "end_");

		setDBColumnNames(dbColumnNames);

		setModelClass(CDTSession.class);

		setModelImplClass(CDTSessionImpl.class);
		setModelPKClass(long.class);

		setTable(CDTSessionTable.INSTANCE);
	}

	/**
	 * Caches the cdt session in the entity cache if it is enabled.
	 *
	 * @param cdtSession the cdt session
	 */
	@Override
	public void cacheResult(CDTSession cdtSession) {
		entityCache.putResult(
			CDTSessionImpl.class, cdtSession.getPrimaryKey(), cdtSession);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the cdt sessions in the entity cache if it is enabled.
	 *
	 * @param cdtSessions the cdt sessions
	 */
	@Override
	public void cacheResult(List<CDTSession> cdtSessions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (cdtSessions.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CDTSession cdtSession : cdtSessions) {
			if (entityCache.getResult(
					CDTSessionImpl.class, cdtSession.getPrimaryKey()) == null) {

				cacheResult(cdtSession);
			}
		}
	}

	/**
	 * Clears the cache for all cdt sessions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CDTSessionImpl.class);

		finderCache.clearCache(CDTSessionImpl.class);
	}

	/**
	 * Clears the cache for the cdt session.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CDTSession cdtSession) {
		entityCache.removeResult(CDTSessionImpl.class, cdtSession);
	}

	@Override
	public void clearCache(List<CDTSession> cdtSessions) {
		for (CDTSession cdtSession : cdtSessions) {
			entityCache.removeResult(CDTSessionImpl.class, cdtSession);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CDTSessionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CDTSessionImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new cdt session with the primary key. Does not add the cdt session to the database.
	 *
	 * @param sessionId the primary key for the new cdt session
	 * @return the new cdt session
	 */
	@Override
	public CDTSession create(long sessionId) {
		CDTSession cdtSession = new CDTSessionImpl();

		cdtSession.setNew(true);
		cdtSession.setPrimaryKey(sessionId);

		return cdtSession;
	}

	/**
	 * Removes the cdt session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session that was removed
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	@Override
	public CDTSession remove(long sessionId) throws NoSuchCDTSessionException {
		return remove((Serializable)sessionId);
	}

	/**
	 * Removes the cdt session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cdt session
	 * @return the cdt session that was removed
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	@Override
	public CDTSession remove(Serializable primaryKey)
		throws NoSuchCDTSessionException {

		Session session = null;

		try {
			session = openSession();

			CDTSession cdtSession = (CDTSession)session.get(
				CDTSessionImpl.class, primaryKey);

			if (cdtSession == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCDTSessionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cdtSession);
		}
		catch (NoSuchCDTSessionException noSuchEntityException) {
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
	protected CDTSession removeImpl(CDTSession cdtSession) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cdtSession)) {
				cdtSession = (CDTSession)session.get(
					CDTSessionImpl.class, cdtSession.getPrimaryKeyObj());
			}

			if (cdtSession != null) {
				session.delete(cdtSession);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (cdtSession != null) {
			clearCache(cdtSession);
		}

		return cdtSession;
	}

	@Override
	public CDTSession updateImpl(CDTSession cdtSession) {
		boolean isNew = cdtSession.isNew();

		if (!(cdtSession instanceof CDTSessionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cdtSession.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(cdtSession);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cdtSession proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CDTSession implementation " +
					cdtSession.getClass());
		}

		CDTSessionModelImpl cdtSessionModelImpl =
			(CDTSessionModelImpl)cdtSession;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(cdtSession);
			}
			else {
				cdtSession = (CDTSession)session.merge(cdtSession);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CDTSessionImpl.class, cdtSessionModelImpl, false, true);

		if (isNew) {
			cdtSession.setNew(false);
		}

		cdtSession.resetOriginalValues();

		return cdtSession;
	}

	/**
	 * Returns the cdt session with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cdt session
	 * @return the cdt session
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	@Override
	public CDTSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCDTSessionException {

		CDTSession cdtSession = fetchByPrimaryKey(primaryKey);

		if (cdtSession == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCDTSessionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cdtSession;
	}

	/**
	 * Returns the cdt session with the primary key or throws a <code>NoSuchCDTSessionException</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	@Override
	public CDTSession findByPrimaryKey(long sessionId)
		throws NoSuchCDTSessionException {

		return findByPrimaryKey((Serializable)sessionId);
	}

	/**
	 * Returns the cdt session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session, or <code>null</code> if a cdt session with the primary key could not be found
	 */
	@Override
	public CDTSession fetchByPrimaryKey(long sessionId) {
		return fetchByPrimaryKey((Serializable)sessionId);
	}

	/**
	 * Returns all the cdt sessions.
	 *
	 * @return the cdt sessions
	 */
	@Override
	public List<CDTSession> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @return the range of cdt sessions
	 */
	@Override
	public List<CDTSession> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cdt sessions
	 */
	@Override
	public List<CDTSession> findAll(
		int start, int end, OrderByComparator<CDTSession> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cdt sessions
	 */
	@Override
	public List<CDTSession> findAll(
		int start, int end, OrderByComparator<CDTSession> orderByComparator,
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

		List<CDTSession> list = null;

		if (useFinderCache) {
			list = (List<CDTSession>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CDTSESSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CDTSESSION;

				sql = sql.concat(CDTSessionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CDTSession>)QueryUtil.list(
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
	 * Removes all the cdt sessions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CDTSession cdtSession : findAll()) {
			remove(cdtSession);
		}
	}

	/**
	 * Returns the number of cdt sessions.
	 *
	 * @return the number of cdt sessions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CDTSESSION);

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
		return "sessionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CDTSESSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CDTSessionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cdt session persistence.
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

		_finderPathWithPaginationFindBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindBycourseItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBycourseItemId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"courseItemId"}, true);

		_finderPathWithoutPaginationFindBycourseItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBycourseItemId",
			new String[] {Long.class.getName()}, new String[] {"courseItemId"},
			true);

		_finderPathCountBycourseItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycourseItemId",
			new String[] {Long.class.getName()}, new String[] {"courseItemId"},
			false);

		_setCDTSessionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setCDTSessionUtilPersistence(null);

		entityCache.removeCache(CDTSessionImpl.class.getName());
	}

	private void _setCDTSessionUtilPersistence(
		CDTSessionPersistence cdtSessionPersistence) {

		try {
			Field field = CDTSessionUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, cdtSessionPersistence);
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

	private static final String _SQL_SELECT_CDTSESSION =
		"SELECT cdtSession FROM CDTSession cdtSession";

	private static final String _SQL_SELECT_CDTSESSION_WHERE =
		"SELECT cdtSession FROM CDTSession cdtSession WHERE ";

	private static final String _SQL_COUNT_CDTSESSION =
		"SELECT COUNT(cdtSession) FROM CDTSession cdtSession";

	private static final String _SQL_COUNT_CDTSESSION_WHERE =
		"SELECT COUNT(cdtSession) FROM CDTSession cdtSession WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cdtSession.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CDTSession exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CDTSession exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CDTSessionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"start", "end"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private CDTSessionModelArgumentsResolver _cdtSessionModelArgumentsResolver;

}