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

package com.weprode.nero.statistic.service.persistence.impl;

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

import com.weprode.nero.statistic.exception.NoSuchLoolStatException;
import com.weprode.nero.statistic.model.LoolStat;
import com.weprode.nero.statistic.model.LoolStatTable;
import com.weprode.nero.statistic.model.impl.LoolStatImpl;
import com.weprode.nero.statistic.model.impl.LoolStatModelImpl;
import com.weprode.nero.statistic.service.persistence.LoolStatPersistence;
import com.weprode.nero.statistic.service.persistence.LoolStatUtil;
import com.weprode.nero.statistic.service.persistence.impl.constants.StatisticsPersistenceConstants;

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
 * The persistence implementation for the lool stat service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {LoolStatPersistence.class, BasePersistence.class})
public class LoolStatPersistenceImpl
	extends BasePersistenceImpl<LoolStat> implements LoolStatPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LoolStatUtil</code> to access the lool stat persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LoolStatImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByuserId;
	private FinderPath _finderPathWithoutPaginationFindByuserId;
	private FinderPath _finderPathCountByuserId;

	/**
	 * Returns all the lool stats where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching lool stats
	 */
	@Override
	public List<LoolStat> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lool stats where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @return the range of matching lool stats
	 */
	@Override
	public List<LoolStat> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lool stats where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lool stats
	 */
	@Override
	public List<LoolStat> findByuserId(
		long userId, int start, int end,
		OrderByComparator<LoolStat> orderByComparator) {

		return findByuserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lool stats where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching lool stats
	 */
	@Override
	public List<LoolStat> findByuserId(
		long userId, int start, int end,
		OrderByComparator<LoolStat> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByuserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByuserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<LoolStat> list = null;

		if (useFinderCache) {
			list = (List<LoolStat>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LoolStat loolStat : list) {
					if (userId != loolStat.getUserId()) {
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

			sb.append(_SQL_SELECT_LOOLSTAT_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LoolStatModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<LoolStat>)QueryUtil.list(
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
	 * Returns the first lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lool stat
	 * @throws NoSuchLoolStatException if a matching lool stat could not be found
	 */
	@Override
	public LoolStat findByuserId_First(
			long userId, OrderByComparator<LoolStat> orderByComparator)
		throws NoSuchLoolStatException {

		LoolStat loolStat = fetchByuserId_First(userId, orderByComparator);

		if (loolStat != null) {
			return loolStat;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchLoolStatException(sb.toString());
	}

	/**
	 * Returns the first lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lool stat, or <code>null</code> if a matching lool stat could not be found
	 */
	@Override
	public LoolStat fetchByuserId_First(
		long userId, OrderByComparator<LoolStat> orderByComparator) {

		List<LoolStat> list = findByuserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lool stat
	 * @throws NoSuchLoolStatException if a matching lool stat could not be found
	 */
	@Override
	public LoolStat findByuserId_Last(
			long userId, OrderByComparator<LoolStat> orderByComparator)
		throws NoSuchLoolStatException {

		LoolStat loolStat = fetchByuserId_Last(userId, orderByComparator);

		if (loolStat != null) {
			return loolStat;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchLoolStatException(sb.toString());
	}

	/**
	 * Returns the last lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lool stat, or <code>null</code> if a matching lool stat could not be found
	 */
	@Override
	public LoolStat fetchByuserId_Last(
		long userId, OrderByComparator<LoolStat> orderByComparator) {

		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<LoolStat> list = findByuserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lool stats before and after the current lool stat in the ordered set where userId = &#63;.
	 *
	 * @param statId the primary key of the current lool stat
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lool stat
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	@Override
	public LoolStat[] findByuserId_PrevAndNext(
			long statId, long userId,
			OrderByComparator<LoolStat> orderByComparator)
		throws NoSuchLoolStatException {

		LoolStat loolStat = findByPrimaryKey(statId);

		Session session = null;

		try {
			session = openSession();

			LoolStat[] array = new LoolStatImpl[3];

			array[0] = getByuserId_PrevAndNext(
				session, loolStat, userId, orderByComparator, true);

			array[1] = loolStat;

			array[2] = getByuserId_PrevAndNext(
				session, loolStat, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LoolStat getByuserId_PrevAndNext(
		Session session, LoolStat loolStat, long userId,
		OrderByComparator<LoolStat> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LOOLSTAT_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

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
			sb.append(LoolStatModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(loolStat)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LoolStat> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lool stats where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (LoolStat loolStat :
				findByuserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(loolStat);
		}
	}

	/**
	 * Returns the number of lool stats where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching lool stats
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LOOLSTAT_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"loolStat.userId = ?";

	public LoolStatPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(LoolStat.class);

		setModelImplClass(LoolStatImpl.class);
		setModelPKClass(long.class);

		setTable(LoolStatTable.INSTANCE);
	}

	/**
	 * Caches the lool stat in the entity cache if it is enabled.
	 *
	 * @param loolStat the lool stat
	 */
	@Override
	public void cacheResult(LoolStat loolStat) {
		entityCache.putResult(
			LoolStatImpl.class, loolStat.getPrimaryKey(), loolStat);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the lool stats in the entity cache if it is enabled.
	 *
	 * @param loolStats the lool stats
	 */
	@Override
	public void cacheResult(List<LoolStat> loolStats) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (loolStats.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (LoolStat loolStat : loolStats) {
			if (entityCache.getResult(
					LoolStatImpl.class, loolStat.getPrimaryKey()) == null) {

				cacheResult(loolStat);
			}
		}
	}

	/**
	 * Clears the cache for all lool stats.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoolStatImpl.class);

		finderCache.clearCache(LoolStatImpl.class);
	}

	/**
	 * Clears the cache for the lool stat.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoolStat loolStat) {
		entityCache.removeResult(LoolStatImpl.class, loolStat);
	}

	@Override
	public void clearCache(List<LoolStat> loolStats) {
		for (LoolStat loolStat : loolStats) {
			entityCache.removeResult(LoolStatImpl.class, loolStat);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(LoolStatImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(LoolStatImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new lool stat with the primary key. Does not add the lool stat to the database.
	 *
	 * @param statId the primary key for the new lool stat
	 * @return the new lool stat
	 */
	@Override
	public LoolStat create(long statId) {
		LoolStat loolStat = new LoolStatImpl();

		loolStat.setNew(true);
		loolStat.setPrimaryKey(statId);

		return loolStat;
	}

	/**
	 * Removes the lool stat with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat that was removed
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	@Override
	public LoolStat remove(long statId) throws NoSuchLoolStatException {
		return remove((Serializable)statId);
	}

	/**
	 * Removes the lool stat with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the lool stat
	 * @return the lool stat that was removed
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	@Override
	public LoolStat remove(Serializable primaryKey)
		throws NoSuchLoolStatException {

		Session session = null;

		try {
			session = openSession();

			LoolStat loolStat = (LoolStat)session.get(
				LoolStatImpl.class, primaryKey);

			if (loolStat == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoolStatException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(loolStat);
		}
		catch (NoSuchLoolStatException noSuchEntityException) {
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
	protected LoolStat removeImpl(LoolStat loolStat) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loolStat)) {
				loolStat = (LoolStat)session.get(
					LoolStatImpl.class, loolStat.getPrimaryKeyObj());
			}

			if (loolStat != null) {
				session.delete(loolStat);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (loolStat != null) {
			clearCache(loolStat);
		}

		return loolStat;
	}

	@Override
	public LoolStat updateImpl(LoolStat loolStat) {
		boolean isNew = loolStat.isNew();

		if (!(loolStat instanceof LoolStatModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(loolStat.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(loolStat);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in loolStat proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LoolStat implementation " +
					loolStat.getClass());
		}

		LoolStatModelImpl loolStatModelImpl = (LoolStatModelImpl)loolStat;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(loolStat);
			}
			else {
				loolStat = (LoolStat)session.merge(loolStat);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			LoolStatImpl.class, loolStatModelImpl, false, true);

		if (isNew) {
			loolStat.setNew(false);
		}

		loolStat.resetOriginalValues();

		return loolStat;
	}

	/**
	 * Returns the lool stat with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the lool stat
	 * @return the lool stat
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	@Override
	public LoolStat findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoolStatException {

		LoolStat loolStat = fetchByPrimaryKey(primaryKey);

		if (loolStat == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoolStatException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return loolStat;
	}

	/**
	 * Returns the lool stat with the primary key or throws a <code>NoSuchLoolStatException</code> if it could not be found.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	@Override
	public LoolStat findByPrimaryKey(long statId)
		throws NoSuchLoolStatException {

		return findByPrimaryKey((Serializable)statId);
	}

	/**
	 * Returns the lool stat with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat, or <code>null</code> if a lool stat with the primary key could not be found
	 */
	@Override
	public LoolStat fetchByPrimaryKey(long statId) {
		return fetchByPrimaryKey((Serializable)statId);
	}

	/**
	 * Returns all the lool stats.
	 *
	 * @return the lool stats
	 */
	@Override
	public List<LoolStat> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @return the range of lool stats
	 */
	@Override
	public List<LoolStat> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lool stats
	 */
	@Override
	public List<LoolStat> findAll(
		int start, int end, OrderByComparator<LoolStat> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of lool stats
	 */
	@Override
	public List<LoolStat> findAll(
		int start, int end, OrderByComparator<LoolStat> orderByComparator,
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

		List<LoolStat> list = null;

		if (useFinderCache) {
			list = (List<LoolStat>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LOOLSTAT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LOOLSTAT;

				sql = sql.concat(LoolStatModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LoolStat>)QueryUtil.list(
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
	 * Removes all the lool stats from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoolStat loolStat : findAll()) {
			remove(loolStat);
		}
	}

	/**
	 * Returns the number of lool stats.
	 *
	 * @return the number of lool stats
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LOOLSTAT);

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
		return "statId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LOOLSTAT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LoolStatModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the lool stat persistence.
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

		_finderPathWithPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_setLoolStatUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setLoolStatUtilPersistence(null);

		entityCache.removeCache(LoolStatImpl.class.getName());
	}

	private void _setLoolStatUtilPersistence(
		LoolStatPersistence loolStatPersistence) {

		try {
			Field field = LoolStatUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, loolStatPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = StatisticsPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = StatisticsPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = StatisticsPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_LOOLSTAT =
		"SELECT loolStat FROM LoolStat loolStat";

	private static final String _SQL_SELECT_LOOLSTAT_WHERE =
		"SELECT loolStat FROM LoolStat loolStat WHERE ";

	private static final String _SQL_COUNT_LOOLSTAT =
		"SELECT COUNT(loolStat) FROM LoolStat loolStat";

	private static final String _SQL_COUNT_LOOLSTAT_WHERE =
		"SELECT COUNT(loolStat) FROM LoolStat loolStat WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "loolStat.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LoolStat exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LoolStat exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LoolStatPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}