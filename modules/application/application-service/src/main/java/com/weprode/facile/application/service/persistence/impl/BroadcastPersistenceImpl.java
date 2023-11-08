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

package com.weprode.facile.application.service.persistence.impl;

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

import com.weprode.facile.application.exception.NoSuchBroadcastException;
import com.weprode.facile.application.model.Broadcast;
import com.weprode.facile.application.model.BroadcastTable;
import com.weprode.facile.application.model.impl.BroadcastImpl;
import com.weprode.facile.application.model.impl.BroadcastModelImpl;
import com.weprode.facile.application.service.persistence.BroadcastPersistence;
import com.weprode.facile.application.service.persistence.BroadcastUtil;
import com.weprode.facile.application.service.persistence.impl.constants.ApplicationPersistenceConstants;

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
 * The persistence implementation for the broadcast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {BroadcastPersistence.class, BasePersistence.class})
public class BroadcastPersistenceImpl
	extends BasePersistenceImpl<Broadcast> implements BroadcastPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BroadcastUtil</code> to access the broadcast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BroadcastImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByapplicationId_schoolId;
	private FinderPath _finderPathCountByapplicationId_schoolId;

	/**
	 * Returns the broadcast where applicationId = &#63; and schoolId = &#63; or throws a <code>NoSuchBroadcastException</code> if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching broadcast
	 * @throws NoSuchBroadcastException if a matching broadcast could not be found
	 */
	@Override
	public Broadcast findByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws NoSuchBroadcastException {

		Broadcast broadcast = fetchByapplicationId_schoolId(
			applicationId, schoolId);

		if (broadcast == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("applicationId=");
			sb.append(applicationId);

			sb.append(", schoolId=");
			sb.append(schoolId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchBroadcastException(sb.toString());
		}

		return broadcast;
	}

	/**
	 * Returns the broadcast where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	@Override
	public Broadcast fetchByapplicationId_schoolId(
		long applicationId, long schoolId) {

		return fetchByapplicationId_schoolId(applicationId, schoolId, true);
	}

	/**
	 * Returns the broadcast where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	@Override
	public Broadcast fetchByapplicationId_schoolId(
		long applicationId, long schoolId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {applicationId, schoolId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByapplicationId_schoolId, finderArgs, this);
		}

		if (result instanceof Broadcast) {
			Broadcast broadcast = (Broadcast)result;

			if ((applicationId != broadcast.getApplicationId()) ||
				(schoolId != broadcast.getSchoolId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_BROADCAST_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_APPLICATIONID_2);

			sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_SCHOOLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

				queryPos.add(schoolId);

				List<Broadcast> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByapplicationId_schoolId,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									applicationId, schoolId
								};
							}

							_log.warn(
								"BroadcastPersistenceImpl.fetchByapplicationId_schoolId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Broadcast broadcast = list.get(0);

					result = broadcast;

					cacheResult(broadcast);
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
			return (Broadcast)result;
		}
	}

	/**
	 * Removes the broadcast where applicationId = &#63; and schoolId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the broadcast that was removed
	 */
	@Override
	public Broadcast removeByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws NoSuchBroadcastException {

		Broadcast broadcast = findByapplicationId_schoolId(
			applicationId, schoolId);

		return remove(broadcast);
	}

	/**
	 * Returns the number of broadcasts where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the number of matching broadcasts
	 */
	@Override
	public int countByapplicationId_schoolId(
		long applicationId, long schoolId) {

		FinderPath finderPath = _finderPathCountByapplicationId_schoolId;

		Object[] finderArgs = new Object[] {applicationId, schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BROADCAST_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_APPLICATIONID_2);

			sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_SCHOOLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

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

	private static final String
		_FINDER_COLUMN_APPLICATIONID_SCHOOLID_APPLICATIONID_2 =
			"broadcast.applicationId = ? AND ";

	private static final String
		_FINDER_COLUMN_APPLICATIONID_SCHOOLID_SCHOOLID_2 =
			"broadcast.schoolId = ?";

	private FinderPath _finderPathWithPaginationFindByapplicationId;
	private FinderPath _finderPathWithoutPaginationFindByapplicationId;
	private FinderPath _finderPathCountByapplicationId;

	/**
	 * Returns all the broadcasts where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching broadcasts
	 */
	@Override
	public List<Broadcast> findByapplicationId(long applicationId) {
		return findByapplicationId(
			applicationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the broadcasts where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @return the range of matching broadcasts
	 */
	@Override
	public List<Broadcast> findByapplicationId(
		long applicationId, int start, int end) {

		return findByapplicationId(applicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the broadcasts where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching broadcasts
	 */
	@Override
	public List<Broadcast> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<Broadcast> orderByComparator) {

		return findByapplicationId(
			applicationId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the broadcasts where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching broadcasts
	 */
	@Override
	public List<Broadcast> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<Broadcast> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByapplicationId;
				finderArgs = new Object[] {applicationId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByapplicationId;
			finderArgs = new Object[] {
				applicationId, start, end, orderByComparator
			};
		}

		List<Broadcast> list = null;

		if (useFinderCache) {
			list = (List<Broadcast>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Broadcast broadcast : list) {
					if (applicationId != broadcast.getApplicationId()) {
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

			sb.append(_SQL_SELECT_BROADCAST_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BroadcastModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

				list = (List<Broadcast>)QueryUtil.list(
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
	 * Returns the first broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast
	 * @throws NoSuchBroadcastException if a matching broadcast could not be found
	 */
	@Override
	public Broadcast findByapplicationId_First(
			long applicationId, OrderByComparator<Broadcast> orderByComparator)
		throws NoSuchBroadcastException {

		Broadcast broadcast = fetchByapplicationId_First(
			applicationId, orderByComparator);

		if (broadcast != null) {
			return broadcast;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationId=");
		sb.append(applicationId);

		sb.append("}");

		throw new NoSuchBroadcastException(sb.toString());
	}

	/**
	 * Returns the first broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	@Override
	public Broadcast fetchByapplicationId_First(
		long applicationId, OrderByComparator<Broadcast> orderByComparator) {

		List<Broadcast> list = findByapplicationId(
			applicationId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast
	 * @throws NoSuchBroadcastException if a matching broadcast could not be found
	 */
	@Override
	public Broadcast findByapplicationId_Last(
			long applicationId, OrderByComparator<Broadcast> orderByComparator)
		throws NoSuchBroadcastException {

		Broadcast broadcast = fetchByapplicationId_Last(
			applicationId, orderByComparator);

		if (broadcast != null) {
			return broadcast;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationId=");
		sb.append(applicationId);

		sb.append("}");

		throw new NoSuchBroadcastException(sb.toString());
	}

	/**
	 * Returns the last broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	@Override
	public Broadcast fetchByapplicationId_Last(
		long applicationId, OrderByComparator<Broadcast> orderByComparator) {

		int count = countByapplicationId(applicationId);

		if (count == 0) {
			return null;
		}

		List<Broadcast> list = findByapplicationId(
			applicationId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the broadcasts before and after the current broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param broadcastId the primary key of the current broadcast
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next broadcast
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	@Override
	public Broadcast[] findByapplicationId_PrevAndNext(
			long broadcastId, long applicationId,
			OrderByComparator<Broadcast> orderByComparator)
		throws NoSuchBroadcastException {

		Broadcast broadcast = findByPrimaryKey(broadcastId);

		Session session = null;

		try {
			session = openSession();

			Broadcast[] array = new BroadcastImpl[3];

			array[0] = getByapplicationId_PrevAndNext(
				session, broadcast, applicationId, orderByComparator, true);

			array[1] = broadcast;

			array[2] = getByapplicationId_PrevAndNext(
				session, broadcast, applicationId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Broadcast getByapplicationId_PrevAndNext(
		Session session, Broadcast broadcast, long applicationId,
		OrderByComparator<Broadcast> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BROADCAST_WHERE);

		sb.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

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
			sb.append(BroadcastModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(applicationId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(broadcast)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Broadcast> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the broadcasts where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 */
	@Override
	public void removeByapplicationId(long applicationId) {
		for (Broadcast broadcast :
				findByapplicationId(
					applicationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(broadcast);
		}
	}

	/**
	 * Returns the number of broadcasts where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching broadcasts
	 */
	@Override
	public int countByapplicationId(long applicationId) {
		FinderPath finderPath = _finderPathCountByapplicationId;

		Object[] finderArgs = new Object[] {applicationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BROADCAST_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

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

	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2 =
		"broadcast.applicationId = ?";

	public BroadcastPersistenceImpl() {
		setModelClass(Broadcast.class);

		setModelImplClass(BroadcastImpl.class);
		setModelPKClass(long.class);

		setTable(BroadcastTable.INSTANCE);
	}

	/**
	 * Caches the broadcast in the entity cache if it is enabled.
	 *
	 * @param broadcast the broadcast
	 */
	@Override
	public void cacheResult(Broadcast broadcast) {
		entityCache.putResult(
			BroadcastImpl.class, broadcast.getPrimaryKey(), broadcast);

		finderCache.putResult(
			_finderPathFetchByapplicationId_schoolId,
			new Object[] {
				broadcast.getApplicationId(), broadcast.getSchoolId()
			},
			broadcast);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the broadcasts in the entity cache if it is enabled.
	 *
	 * @param broadcasts the broadcasts
	 */
	@Override
	public void cacheResult(List<Broadcast> broadcasts) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (broadcasts.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Broadcast broadcast : broadcasts) {
			if (entityCache.getResult(
					BroadcastImpl.class, broadcast.getPrimaryKey()) == null) {

				cacheResult(broadcast);
			}
		}
	}

	/**
	 * Clears the cache for all broadcasts.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BroadcastImpl.class);

		finderCache.clearCache(BroadcastImpl.class);
	}

	/**
	 * Clears the cache for the broadcast.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Broadcast broadcast) {
		entityCache.removeResult(BroadcastImpl.class, broadcast);
	}

	@Override
	public void clearCache(List<Broadcast> broadcasts) {
		for (Broadcast broadcast : broadcasts) {
			entityCache.removeResult(BroadcastImpl.class, broadcast);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(BroadcastImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(BroadcastImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		BroadcastModelImpl broadcastModelImpl) {

		Object[] args = new Object[] {
			broadcastModelImpl.getApplicationId(),
			broadcastModelImpl.getSchoolId()
		};

		finderCache.putResult(
			_finderPathCountByapplicationId_schoolId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByapplicationId_schoolId, args, broadcastModelImpl);
	}

	/**
	 * Creates a new broadcast with the primary key. Does not add the broadcast to the database.
	 *
	 * @param broadcastId the primary key for the new broadcast
	 * @return the new broadcast
	 */
	@Override
	public Broadcast create(long broadcastId) {
		Broadcast broadcast = new BroadcastImpl();

		broadcast.setNew(true);
		broadcast.setPrimaryKey(broadcastId);

		return broadcast;
	}

	/**
	 * Removes the broadcast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast that was removed
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	@Override
	public Broadcast remove(long broadcastId) throws NoSuchBroadcastException {
		return remove((Serializable)broadcastId);
	}

	/**
	 * Removes the broadcast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the broadcast
	 * @return the broadcast that was removed
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	@Override
	public Broadcast remove(Serializable primaryKey)
		throws NoSuchBroadcastException {

		Session session = null;

		try {
			session = openSession();

			Broadcast broadcast = (Broadcast)session.get(
				BroadcastImpl.class, primaryKey);

			if (broadcast == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBroadcastException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(broadcast);
		}
		catch (NoSuchBroadcastException noSuchEntityException) {
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
	protected Broadcast removeImpl(Broadcast broadcast) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(broadcast)) {
				broadcast = (Broadcast)session.get(
					BroadcastImpl.class, broadcast.getPrimaryKeyObj());
			}

			if (broadcast != null) {
				session.delete(broadcast);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (broadcast != null) {
			clearCache(broadcast);
		}

		return broadcast;
	}

	@Override
	public Broadcast updateImpl(Broadcast broadcast) {
		boolean isNew = broadcast.isNew();

		if (!(broadcast instanceof BroadcastModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(broadcast.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(broadcast);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in broadcast proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Broadcast implementation " +
					broadcast.getClass());
		}

		BroadcastModelImpl broadcastModelImpl = (BroadcastModelImpl)broadcast;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(broadcast);
			}
			else {
				broadcast = (Broadcast)session.merge(broadcast);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			BroadcastImpl.class, broadcastModelImpl, false, true);

		cacheUniqueFindersCache(broadcastModelImpl);

		if (isNew) {
			broadcast.setNew(false);
		}

		broadcast.resetOriginalValues();

		return broadcast;
	}

	/**
	 * Returns the broadcast with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the broadcast
	 * @return the broadcast
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	@Override
	public Broadcast findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBroadcastException {

		Broadcast broadcast = fetchByPrimaryKey(primaryKey);

		if (broadcast == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBroadcastException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return broadcast;
	}

	/**
	 * Returns the broadcast with the primary key or throws a <code>NoSuchBroadcastException</code> if it could not be found.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	@Override
	public Broadcast findByPrimaryKey(long broadcastId)
		throws NoSuchBroadcastException {

		return findByPrimaryKey((Serializable)broadcastId);
	}

	/**
	 * Returns the broadcast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast, or <code>null</code> if a broadcast with the primary key could not be found
	 */
	@Override
	public Broadcast fetchByPrimaryKey(long broadcastId) {
		return fetchByPrimaryKey((Serializable)broadcastId);
	}

	/**
	 * Returns all the broadcasts.
	 *
	 * @return the broadcasts
	 */
	@Override
	public List<Broadcast> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @return the range of broadcasts
	 */
	@Override
	public List<Broadcast> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of broadcasts
	 */
	@Override
	public List<Broadcast> findAll(
		int start, int end, OrderByComparator<Broadcast> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of broadcasts
	 */
	@Override
	public List<Broadcast> findAll(
		int start, int end, OrderByComparator<Broadcast> orderByComparator,
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

		List<Broadcast> list = null;

		if (useFinderCache) {
			list = (List<Broadcast>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_BROADCAST);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_BROADCAST;

				sql = sql.concat(BroadcastModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Broadcast>)QueryUtil.list(
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
	 * Removes all the broadcasts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Broadcast broadcast : findAll()) {
			remove(broadcast);
		}
	}

	/**
	 * Returns the number of broadcasts.
	 *
	 * @return the number of broadcasts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_BROADCAST);

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
		return "broadcastId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_BROADCAST;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BroadcastModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the broadcast persistence.
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

		_finderPathFetchByapplicationId_schoolId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByapplicationId_schoolId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"applicationId", "schoolId"}, true);

		_finderPathCountByapplicationId_schoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByapplicationId_schoolId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"applicationId", "schoolId"}, false);

		_finderPathWithPaginationFindByapplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByapplicationId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"applicationId"}, true);

		_finderPathWithoutPaginationFindByapplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByapplicationId",
			new String[] {Long.class.getName()}, new String[] {"applicationId"},
			true);

		_finderPathCountByapplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByapplicationId",
			new String[] {Long.class.getName()}, new String[] {"applicationId"},
			false);

		_setBroadcastUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setBroadcastUtilPersistence(null);

		entityCache.removeCache(BroadcastImpl.class.getName());
	}

	private void _setBroadcastUtilPersistence(
		BroadcastPersistence broadcastPersistence) {

		try {
			Field field = BroadcastUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, broadcastPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = ApplicationPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ApplicationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ApplicationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_BROADCAST =
		"SELECT broadcast FROM Broadcast broadcast";

	private static final String _SQL_SELECT_BROADCAST_WHERE =
		"SELECT broadcast FROM Broadcast broadcast WHERE ";

	private static final String _SQL_COUNT_BROADCAST =
		"SELECT COUNT(broadcast) FROM Broadcast broadcast";

	private static final String _SQL_COUNT_BROADCAST_WHERE =
		"SELECT COUNT(broadcast) FROM Broadcast broadcast WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "broadcast.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Broadcast exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Broadcast exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		BroadcastPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}