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

import com.weprode.nero.schedule.exception.NoSuchSessionParentClassException;
import com.weprode.nero.schedule.model.SessionParentClass;
import com.weprode.nero.schedule.model.SessionParentClassTable;
import com.weprode.nero.schedule.model.impl.SessionParentClassImpl;
import com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl;
import com.weprode.nero.schedule.service.persistence.SessionParentClassPersistence;
import com.weprode.nero.schedule.service.persistence.SessionParentClassUtil;
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
 * The persistence implementation for the session parent class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {SessionParentClassPersistence.class, BasePersistence.class}
)
public class SessionParentClassPersistenceImpl
	extends BasePersistenceImpl<SessionParentClass>
	implements SessionParentClassPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SessionParentClassUtil</code> to access the session parent class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SessionParentClassImpl.class.getName();

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
	 * Returns all the session parent classes where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBysessionId(long sessionId) {
		return findBysessionId(
			sessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session parent classes where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBysessionId(
		long sessionId, int start, int end) {

		return findBysessionId(sessionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the session parent classes where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionParentClass> orderByComparator) {

		return findBysessionId(sessionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session parent classes where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<SessionParentClass> orderByComparator,
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

		List<SessionParentClass> list = null;

		if (useFinderCache) {
			list = (List<SessionParentClass>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SessionParentClass sessionParentClass : list) {
					if (sessionId != sessionParentClass.getSessionId()) {
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

			sb.append(_SQL_SELECT_SESSIONPARENTCLASS_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_SESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SessionParentClassModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				list = (List<SessionParentClass>)QueryUtil.list(
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
	 * Returns the first session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass findBysessionId_First(
			long sessionId,
			OrderByComparator<SessionParentClass> orderByComparator)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = fetchBysessionId_First(
			sessionId, orderByComparator);

		if (sessionParentClass != null) {
			return sessionParentClass;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append("}");

		throw new NoSuchSessionParentClassException(sb.toString());
	}

	/**
	 * Returns the first session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass fetchBysessionId_First(
		long sessionId,
		OrderByComparator<SessionParentClass> orderByComparator) {

		List<SessionParentClass> list = findBysessionId(
			sessionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass findBysessionId_Last(
			long sessionId,
			OrderByComparator<SessionParentClass> orderByComparator)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = fetchBysessionId_Last(
			sessionId, orderByComparator);

		if (sessionParentClass != null) {
			return sessionParentClass;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append("}");

		throw new NoSuchSessionParentClassException(sb.toString());
	}

	/**
	 * Returns the last session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass fetchBysessionId_Last(
		long sessionId,
		OrderByComparator<SessionParentClass> orderByComparator) {

		int count = countBysessionId(sessionId);

		if (count == 0) {
			return null;
		}

		List<SessionParentClass> list = findBysessionId(
			sessionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the session parent classes before and after the current session parent class in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionParentClassId the primary key of the current session parent class
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session parent class
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	@Override
	public SessionParentClass[] findBysessionId_PrevAndNext(
			long sessionParentClassId, long sessionId,
			OrderByComparator<SessionParentClass> orderByComparator)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = findByPrimaryKey(
			sessionParentClassId);

		Session session = null;

		try {
			session = openSession();

			SessionParentClass[] array = new SessionParentClassImpl[3];

			array[0] = getBysessionId_PrevAndNext(
				session, sessionParentClass, sessionId, orderByComparator,
				true);

			array[1] = sessionParentClass;

			array[2] = getBysessionId_PrevAndNext(
				session, sessionParentClass, sessionId, orderByComparator,
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

	protected SessionParentClass getBysessionId_PrevAndNext(
		Session session, SessionParentClass sessionParentClass, long sessionId,
		OrderByComparator<SessionParentClass> orderByComparator,
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

		sb.append(_SQL_SELECT_SESSIONPARENTCLASS_WHERE);

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
			sb.append(SessionParentClassModelImpl.ORDER_BY_JPQL);
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
						sessionParentClass)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SessionParentClass> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the session parent classes where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	@Override
	public void removeBysessionId(long sessionId) {
		for (SessionParentClass sessionParentClass :
				findBysessionId(
					sessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sessionParentClass);
		}
	}

	/**
	 * Returns the number of session parent classes where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching session parent classes
	 */
	@Override
	public int countBysessionId(long sessionId) {
		FinderPath finderPath = _finderPathCountBysessionId;

		Object[] finderArgs = new Object[] {sessionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SESSIONPARENTCLASS_WHERE);

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
		"sessionParentClass.sessionId = ?";

	private FinderPath _finderPathWithPaginationFindBygroupId;
	private FinderPath _finderPathWithoutPaginationFindBygroupId;
	private FinderPath _finderPathCountBygroupId;

	/**
	 * Returns all the session parent classes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBygroupId(long groupId) {
		return findBygroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session parent classes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBygroupId(
		long groupId, int start, int end) {

		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the session parent classes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<SessionParentClass> orderByComparator) {

		return findBygroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session parent classes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<SessionParentClass> orderByComparator,
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

		List<SessionParentClass> list = null;

		if (useFinderCache) {
			list = (List<SessionParentClass>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SessionParentClass sessionParentClass : list) {
					if (groupId != sessionParentClass.getGroupId()) {
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

			sb.append(_SQL_SELECT_SESSIONPARENTCLASS_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SessionParentClassModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<SessionParentClass>)QueryUtil.list(
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
	 * Returns the first session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass findBygroupId_First(
			long groupId,
			OrderByComparator<SessionParentClass> orderByComparator)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = fetchBygroupId_First(
			groupId, orderByComparator);

		if (sessionParentClass != null) {
			return sessionParentClass;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchSessionParentClassException(sb.toString());
	}

	/**
	 * Returns the first session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass fetchBygroupId_First(
		long groupId, OrderByComparator<SessionParentClass> orderByComparator) {

		List<SessionParentClass> list = findBygroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass findBygroupId_Last(
			long groupId,
			OrderByComparator<SessionParentClass> orderByComparator)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = fetchBygroupId_Last(
			groupId, orderByComparator);

		if (sessionParentClass != null) {
			return sessionParentClass;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchSessionParentClassException(sb.toString());
	}

	/**
	 * Returns the last session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass fetchBygroupId_Last(
		long groupId, OrderByComparator<SessionParentClass> orderByComparator) {

		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SessionParentClass> list = findBygroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the session parent classes before and after the current session parent class in the ordered set where groupId = &#63;.
	 *
	 * @param sessionParentClassId the primary key of the current session parent class
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session parent class
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	@Override
	public SessionParentClass[] findBygroupId_PrevAndNext(
			long sessionParentClassId, long groupId,
			OrderByComparator<SessionParentClass> orderByComparator)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = findByPrimaryKey(
			sessionParentClassId);

		Session session = null;

		try {
			session = openSession();

			SessionParentClass[] array = new SessionParentClassImpl[3];

			array[0] = getBygroupId_PrevAndNext(
				session, sessionParentClass, groupId, orderByComparator, true);

			array[1] = sessionParentClass;

			array[2] = getBygroupId_PrevAndNext(
				session, sessionParentClass, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SessionParentClass getBygroupId_PrevAndNext(
		Session session, SessionParentClass sessionParentClass, long groupId,
		OrderByComparator<SessionParentClass> orderByComparator,
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

		sb.append(_SQL_SELECT_SESSIONPARENTCLASS_WHERE);

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
			sb.append(SessionParentClassModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sessionParentClass)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SessionParentClass> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the session parent classes where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupId(long groupId) {
		for (SessionParentClass sessionParentClass :
				findBygroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sessionParentClass);
		}
	}

	/**
	 * Returns the number of session parent classes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching session parent classes
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = _finderPathCountBygroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SESSIONPARENTCLASS_WHERE);

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
		"sessionParentClass.groupId = ?";

	private FinderPath _finderPathWithPaginationFindBysessionId_groupId;
	private FinderPath _finderPathWithoutPaginationFindBysessionId_groupId;
	private FinderPath _finderPathCountBysessionId_groupId;

	/**
	 * Returns all the session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @return the matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBysessionId_groupId(
		long sessionId, long groupId) {

		return findBysessionId_groupId(
			sessionId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBysessionId_groupId(
		long sessionId, long groupId, int start, int end) {

		return findBysessionId_groupId(sessionId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBysessionId_groupId(
		long sessionId, long groupId, int start, int end,
		OrderByComparator<SessionParentClass> orderByComparator) {

		return findBysessionId_groupId(
			sessionId, groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching session parent classes
	 */
	@Override
	public List<SessionParentClass> findBysessionId_groupId(
		long sessionId, long groupId, int start, int end,
		OrderByComparator<SessionParentClass> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindBysessionId_groupId;
				finderArgs = new Object[] {sessionId, groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBysessionId_groupId;
			finderArgs = new Object[] {
				sessionId, groupId, start, end, orderByComparator
			};
		}

		List<SessionParentClass> list = null;

		if (useFinderCache) {
			list = (List<SessionParentClass>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SessionParentClass sessionParentClass : list) {
					if ((sessionId != sessionParentClass.getSessionId()) ||
						(groupId != sessionParentClass.getGroupId())) {

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

			sb.append(_SQL_SELECT_SESSIONPARENTCLASS_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_GROUPID_SESSIONID_2);

			sb.append(_FINDER_COLUMN_SESSIONID_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SessionParentClassModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				queryPos.add(groupId);

				list = (List<SessionParentClass>)QueryUtil.list(
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
	 * Returns the first session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass findBysessionId_groupId_First(
			long sessionId, long groupId,
			OrderByComparator<SessionParentClass> orderByComparator)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = fetchBysessionId_groupId_First(
			sessionId, groupId, orderByComparator);

		if (sessionParentClass != null) {
			return sessionParentClass;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchSessionParentClassException(sb.toString());
	}

	/**
	 * Returns the first session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass fetchBysessionId_groupId_First(
		long sessionId, long groupId,
		OrderByComparator<SessionParentClass> orderByComparator) {

		List<SessionParentClass> list = findBysessionId_groupId(
			sessionId, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class
	 * @throws NoSuchSessionParentClassException if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass findBysessionId_groupId_Last(
			long sessionId, long groupId,
			OrderByComparator<SessionParentClass> orderByComparator)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = fetchBysessionId_groupId_Last(
			sessionId, groupId, orderByComparator);

		if (sessionParentClass != null) {
			return sessionParentClass;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchSessionParentClassException(sb.toString());
	}

	/**
	 * Returns the last session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching session parent class, or <code>null</code> if a matching session parent class could not be found
	 */
	@Override
	public SessionParentClass fetchBysessionId_groupId_Last(
		long sessionId, long groupId,
		OrderByComparator<SessionParentClass> orderByComparator) {

		int count = countBysessionId_groupId(sessionId, groupId);

		if (count == 0) {
			return null;
		}

		List<SessionParentClass> list = findBysessionId_groupId(
			sessionId, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the session parent classes before and after the current session parent class in the ordered set where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionParentClassId the primary key of the current session parent class
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next session parent class
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	@Override
	public SessionParentClass[] findBysessionId_groupId_PrevAndNext(
			long sessionParentClassId, long sessionId, long groupId,
			OrderByComparator<SessionParentClass> orderByComparator)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = findByPrimaryKey(
			sessionParentClassId);

		Session session = null;

		try {
			session = openSession();

			SessionParentClass[] array = new SessionParentClassImpl[3];

			array[0] = getBysessionId_groupId_PrevAndNext(
				session, sessionParentClass, sessionId, groupId,
				orderByComparator, true);

			array[1] = sessionParentClass;

			array[2] = getBysessionId_groupId_PrevAndNext(
				session, sessionParentClass, sessionId, groupId,
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

	protected SessionParentClass getBysessionId_groupId_PrevAndNext(
		Session session, SessionParentClass sessionParentClass, long sessionId,
		long groupId, OrderByComparator<SessionParentClass> orderByComparator,
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

		sb.append(_SQL_SELECT_SESSIONPARENTCLASS_WHERE);

		sb.append(_FINDER_COLUMN_SESSIONID_GROUPID_SESSIONID_2);

		sb.append(_FINDER_COLUMN_SESSIONID_GROUPID_GROUPID_2);

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
			sb.append(SessionParentClassModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(sessionId);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sessionParentClass)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SessionParentClass> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the session parent classes where sessionId = &#63; and groupId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeBysessionId_groupId(long sessionId, long groupId) {
		for (SessionParentClass sessionParentClass :
				findBysessionId_groupId(
					sessionId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(sessionParentClass);
		}
	}

	/**
	 * Returns the number of session parent classes where sessionId = &#63; and groupId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param groupId the group ID
	 * @return the number of matching session parent classes
	 */
	@Override
	public int countBysessionId_groupId(long sessionId, long groupId) {
		FinderPath finderPath = _finderPathCountBysessionId_groupId;

		Object[] finderArgs = new Object[] {sessionId, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SESSIONPARENTCLASS_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_GROUPID_SESSIONID_2);

			sb.append(_FINDER_COLUMN_SESSIONID_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

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

	private static final String _FINDER_COLUMN_SESSIONID_GROUPID_SESSIONID_2 =
		"sessionParentClass.sessionId = ? AND ";

	private static final String _FINDER_COLUMN_SESSIONID_GROUPID_GROUPID_2 =
		"sessionParentClass.groupId = ?";

	public SessionParentClassPersistenceImpl() {
		setModelClass(SessionParentClass.class);

		setModelImplClass(SessionParentClassImpl.class);
		setModelPKClass(long.class);

		setTable(SessionParentClassTable.INSTANCE);
	}

	/**
	 * Caches the session parent class in the entity cache if it is enabled.
	 *
	 * @param sessionParentClass the session parent class
	 */
	@Override
	public void cacheResult(SessionParentClass sessionParentClass) {
		entityCache.putResult(
			SessionParentClassImpl.class, sessionParentClass.getPrimaryKey(),
			sessionParentClass);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the session parent classes in the entity cache if it is enabled.
	 *
	 * @param sessionParentClasses the session parent classes
	 */
	@Override
	public void cacheResult(List<SessionParentClass> sessionParentClasses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sessionParentClasses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SessionParentClass sessionParentClass : sessionParentClasses) {
			if (entityCache.getResult(
					SessionParentClassImpl.class,
					sessionParentClass.getPrimaryKey()) == null) {

				cacheResult(sessionParentClass);
			}
		}
	}

	/**
	 * Clears the cache for all session parent classes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SessionParentClassImpl.class);

		finderCache.clearCache(SessionParentClassImpl.class);
	}

	/**
	 * Clears the cache for the session parent class.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SessionParentClass sessionParentClass) {
		entityCache.removeResult(
			SessionParentClassImpl.class, sessionParentClass);
	}

	@Override
	public void clearCache(List<SessionParentClass> sessionParentClasses) {
		for (SessionParentClass sessionParentClass : sessionParentClasses) {
			entityCache.removeResult(
				SessionParentClassImpl.class, sessionParentClass);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SessionParentClassImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SessionParentClassImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new session parent class with the primary key. Does not add the session parent class to the database.
	 *
	 * @param sessionParentClassId the primary key for the new session parent class
	 * @return the new session parent class
	 */
	@Override
	public SessionParentClass create(long sessionParentClassId) {
		SessionParentClass sessionParentClass = new SessionParentClassImpl();

		sessionParentClass.setNew(true);
		sessionParentClass.setPrimaryKey(sessionParentClassId);

		return sessionParentClass;
	}

	/**
	 * Removes the session parent class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class that was removed
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	@Override
	public SessionParentClass remove(long sessionParentClassId)
		throws NoSuchSessionParentClassException {

		return remove((Serializable)sessionParentClassId);
	}

	/**
	 * Removes the session parent class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the session parent class
	 * @return the session parent class that was removed
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	@Override
	public SessionParentClass remove(Serializable primaryKey)
		throws NoSuchSessionParentClassException {

		Session session = null;

		try {
			session = openSession();

			SessionParentClass sessionParentClass =
				(SessionParentClass)session.get(
					SessionParentClassImpl.class, primaryKey);

			if (sessionParentClass == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSessionParentClassException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sessionParentClass);
		}
		catch (NoSuchSessionParentClassException noSuchEntityException) {
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
	protected SessionParentClass removeImpl(
		SessionParentClass sessionParentClass) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sessionParentClass)) {
				sessionParentClass = (SessionParentClass)session.get(
					SessionParentClassImpl.class,
					sessionParentClass.getPrimaryKeyObj());
			}

			if (sessionParentClass != null) {
				session.delete(sessionParentClass);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sessionParentClass != null) {
			clearCache(sessionParentClass);
		}

		return sessionParentClass;
	}

	@Override
	public SessionParentClass updateImpl(
		SessionParentClass sessionParentClass) {

		boolean isNew = sessionParentClass.isNew();

		if (!(sessionParentClass instanceof SessionParentClassModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(sessionParentClass.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					sessionParentClass);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sessionParentClass proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SessionParentClass implementation " +
					sessionParentClass.getClass());
		}

		SessionParentClassModelImpl sessionParentClassModelImpl =
			(SessionParentClassModelImpl)sessionParentClass;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sessionParentClass);
			}
			else {
				sessionParentClass = (SessionParentClass)session.merge(
					sessionParentClass);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SessionParentClassImpl.class, sessionParentClassModelImpl, false,
			true);

		if (isNew) {
			sessionParentClass.setNew(false);
		}

		sessionParentClass.resetOriginalValues();

		return sessionParentClass;
	}

	/**
	 * Returns the session parent class with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the session parent class
	 * @return the session parent class
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	@Override
	public SessionParentClass findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSessionParentClassException {

		SessionParentClass sessionParentClass = fetchByPrimaryKey(primaryKey);

		if (sessionParentClass == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSessionParentClassException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sessionParentClass;
	}

	/**
	 * Returns the session parent class with the primary key or throws a <code>NoSuchSessionParentClassException</code> if it could not be found.
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class
	 * @throws NoSuchSessionParentClassException if a session parent class with the primary key could not be found
	 */
	@Override
	public SessionParentClass findByPrimaryKey(long sessionParentClassId)
		throws NoSuchSessionParentClassException {

		return findByPrimaryKey((Serializable)sessionParentClassId);
	}

	/**
	 * Returns the session parent class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class, or <code>null</code> if a session parent class with the primary key could not be found
	 */
	@Override
	public SessionParentClass fetchByPrimaryKey(long sessionParentClassId) {
		return fetchByPrimaryKey((Serializable)sessionParentClassId);
	}

	/**
	 * Returns all the session parent classes.
	 *
	 * @return the session parent classes
	 */
	@Override
	public List<SessionParentClass> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session parent classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of session parent classes
	 */
	@Override
	public List<SessionParentClass> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the session parent classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of session parent classes
	 */
	@Override
	public List<SessionParentClass> findAll(
		int start, int end,
		OrderByComparator<SessionParentClass> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session parent classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of session parent classes
	 */
	@Override
	public List<SessionParentClass> findAll(
		int start, int end,
		OrderByComparator<SessionParentClass> orderByComparator,
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

		List<SessionParentClass> list = null;

		if (useFinderCache) {
			list = (List<SessionParentClass>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SESSIONPARENTCLASS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SESSIONPARENTCLASS;

				sql = sql.concat(SessionParentClassModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SessionParentClass>)QueryUtil.list(
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
	 * Removes all the session parent classes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SessionParentClass sessionParentClass : findAll()) {
			remove(sessionParentClass);
		}
	}

	/**
	 * Returns the number of session parent classes.
	 *
	 * @return the number of session parent classes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_SESSIONPARENTCLASS);

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
		return "sessionParentClassId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SESSIONPARENTCLASS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SessionParentClassModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the session parent class persistence.
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

		_finderPathWithPaginationFindBysessionId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysessionId_groupId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"sessionId", "groupId"}, true);

		_finderPathWithoutPaginationFindBysessionId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBysessionId_groupId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sessionId", "groupId"}, true);

		_finderPathCountBysessionId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBysessionId_groupId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sessionId", "groupId"}, false);

		_setSessionParentClassUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSessionParentClassUtilPersistence(null);

		entityCache.removeCache(SessionParentClassImpl.class.getName());
	}

	private void _setSessionParentClassUtilPersistence(
		SessionParentClassPersistence sessionParentClassPersistence) {

		try {
			Field field = SessionParentClassUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, sessionParentClassPersistence);
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

	private static final String _SQL_SELECT_SESSIONPARENTCLASS =
		"SELECT sessionParentClass FROM SessionParentClass sessionParentClass";

	private static final String _SQL_SELECT_SESSIONPARENTCLASS_WHERE =
		"SELECT sessionParentClass FROM SessionParentClass sessionParentClass WHERE ";

	private static final String _SQL_COUNT_SESSIONPARENTCLASS =
		"SELECT COUNT(sessionParentClass) FROM SessionParentClass sessionParentClass";

	private static final String _SQL_COUNT_SESSIONPARENTCLASS_WHERE =
		"SELECT COUNT(sessionParentClass) FROM SessionParentClass sessionParentClass WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "sessionParentClass.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SessionParentClass exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SessionParentClass exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SessionParentClassPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private SessionParentClassModelArgumentsResolver
		_sessionParentClassModelArgumentsResolver;

}