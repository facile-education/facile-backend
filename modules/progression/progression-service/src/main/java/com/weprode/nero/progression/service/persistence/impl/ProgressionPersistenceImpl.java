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

package com.weprode.nero.progression.service.persistence.impl;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.progression.exception.NoSuchProgressionException;
import com.weprode.nero.progression.model.Progression;
import com.weprode.nero.progression.model.ProgressionTable;
import com.weprode.nero.progression.model.impl.ProgressionImpl;
import com.weprode.nero.progression.model.impl.ProgressionModelImpl;
import com.weprode.nero.progression.service.persistence.ProgressionPersistence;
import com.weprode.nero.progression.service.persistence.ProgressionUtil;
import com.weprode.nero.progression.service.persistence.impl.constants.ProgressionPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the progression service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ProgressionPersistence.class, BasePersistence.class})
public class ProgressionPersistenceImpl
	extends BasePersistenceImpl<Progression> implements ProgressionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProgressionUtil</code> to access the progression persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProgressionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByprogressionId;
	private FinderPath _finderPathCountByprogressionId;

	/**
	 * Returns the progression where progressionId = &#63; or throws a <code>NoSuchProgressionException</code> if it could not be found.
	 *
	 * @param progressionId the progression ID
	 * @return the matching progression
	 * @throws NoSuchProgressionException if a matching progression could not be found
	 */
	@Override
	public Progression findByprogressionId(long progressionId)
		throws NoSuchProgressionException {

		Progression progression = fetchByprogressionId(progressionId);

		if (progression == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("progressionId=");
			sb.append(progressionId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchProgressionException(sb.toString());
		}

		return progression;
	}

	/**
	 * Returns the progression where progressionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionId the progression ID
	 * @return the matching progression, or <code>null</code> if a matching progression could not be found
	 */
	@Override
	public Progression fetchByprogressionId(long progressionId) {
		return fetchByprogressionId(progressionId, true);
	}

	/**
	 * Returns the progression where progressionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionId the progression ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression, or <code>null</code> if a matching progression could not be found
	 */
	@Override
	public Progression fetchByprogressionId(
		long progressionId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {progressionId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByprogressionId, finderArgs);
		}

		if (result instanceof Progression) {
			Progression progression = (Progression)result;

			if (progressionId != progression.getProgressionId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PROGRESSION_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONID_PROGRESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionId);

				List<Progression> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByprogressionId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {progressionId};
							}

							_log.warn(
								"ProgressionPersistenceImpl.fetchByprogressionId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Progression progression = list.get(0);

					result = progression;

					cacheResult(progression);
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
			return (Progression)result;
		}
	}

	/**
	 * Removes the progression where progressionId = &#63; from the database.
	 *
	 * @param progressionId the progression ID
	 * @return the progression that was removed
	 */
	@Override
	public Progression removeByprogressionId(long progressionId)
		throws NoSuchProgressionException {

		Progression progression = findByprogressionId(progressionId);

		return remove(progression);
	}

	/**
	 * Returns the number of progressions where progressionId = &#63;.
	 *
	 * @param progressionId the progression ID
	 * @return the number of matching progressions
	 */
	@Override
	public int countByprogressionId(long progressionId) {
		FinderPath finderPath = _finderPathCountByprogressionId;

		Object[] finderArgs = new Object[] {progressionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROGRESSION_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONID_PROGRESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionId);

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

	private static final String _FINDER_COLUMN_PROGRESSIONID_PROGRESSIONID_2 =
		"progression.progressionId = ?";

	private FinderPath _finderPathWithPaginationFindByteacherId;
	private FinderPath _finderPathWithoutPaginationFindByteacherId;
	private FinderPath _finderPathCountByteacherId;

	/**
	 * Returns all the progressions where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching progressions
	 */
	@Override
	public List<Progression> findByteacherId(long teacherId) {
		return findByteacherId(
			teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the progressions where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @return the range of matching progressions
	 */
	@Override
	public List<Progression> findByteacherId(
		long teacherId, int start, int end) {

		return findByteacherId(teacherId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the progressions where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progressions
	 */
	@Override
	public List<Progression> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<Progression> orderByComparator) {

		return findByteacherId(teacherId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the progressions where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progressions
	 */
	@Override
	public List<Progression> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<Progression> orderByComparator,
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

		List<Progression> list = null;

		if (useFinderCache) {
			list = (List<Progression>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Progression progression : list) {
					if (teacherId != progression.getTeacherId()) {
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

			sb.append(_SQL_SELECT_PROGRESSION_WHERE);

			sb.append(_FINDER_COLUMN_TEACHERID_TEACHERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProgressionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teacherId);

				list = (List<Progression>)QueryUtil.list(
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
	 * Returns the first progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression
	 * @throws NoSuchProgressionException if a matching progression could not be found
	 */
	@Override
	public Progression findByteacherId_First(
			long teacherId, OrderByComparator<Progression> orderByComparator)
		throws NoSuchProgressionException {

		Progression progression = fetchByteacherId_First(
			teacherId, orderByComparator);

		if (progression != null) {
			return progression;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchProgressionException(sb.toString());
	}

	/**
	 * Returns the first progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression, or <code>null</code> if a matching progression could not be found
	 */
	@Override
	public Progression fetchByteacherId_First(
		long teacherId, OrderByComparator<Progression> orderByComparator) {

		List<Progression> list = findByteacherId(
			teacherId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression
	 * @throws NoSuchProgressionException if a matching progression could not be found
	 */
	@Override
	public Progression findByteacherId_Last(
			long teacherId, OrderByComparator<Progression> orderByComparator)
		throws NoSuchProgressionException {

		Progression progression = fetchByteacherId_Last(
			teacherId, orderByComparator);

		if (progression != null) {
			return progression;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teacherId=");
		sb.append(teacherId);

		sb.append("}");

		throw new NoSuchProgressionException(sb.toString());
	}

	/**
	 * Returns the last progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression, or <code>null</code> if a matching progression could not be found
	 */
	@Override
	public Progression fetchByteacherId_Last(
		long teacherId, OrderByComparator<Progression> orderByComparator) {

		int count = countByteacherId(teacherId);

		if (count == 0) {
			return null;
		}

		List<Progression> list = findByteacherId(
			teacherId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the progressions before and after the current progression in the ordered set where teacherId = &#63;.
	 *
	 * @param progressionId the primary key of the current progression
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	@Override
	public Progression[] findByteacherId_PrevAndNext(
			long progressionId, long teacherId,
			OrderByComparator<Progression> orderByComparator)
		throws NoSuchProgressionException {

		Progression progression = findByPrimaryKey(progressionId);

		Session session = null;

		try {
			session = openSession();

			Progression[] array = new ProgressionImpl[3];

			array[0] = getByteacherId_PrevAndNext(
				session, progression, teacherId, orderByComparator, true);

			array[1] = progression;

			array[2] = getByteacherId_PrevAndNext(
				session, progression, teacherId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Progression getByteacherId_PrevAndNext(
		Session session, Progression progression, long teacherId,
		OrderByComparator<Progression> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_PROGRESSION_WHERE);

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
			sb.append(ProgressionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(teacherId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(progression)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Progression> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the progressions where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	@Override
	public void removeByteacherId(long teacherId) {
		for (Progression progression :
				findByteacherId(
					teacherId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(progression);
		}
	}

	/**
	 * Returns the number of progressions where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching progressions
	 */
	@Override
	public int countByteacherId(long teacherId) {
		FinderPath finderPath = _finderPathCountByteacherId;

		Object[] finderArgs = new Object[] {teacherId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROGRESSION_WHERE);

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
		"progression.teacherId = ?";

	public ProgressionPersistenceImpl() {
		setModelClass(Progression.class);

		setModelImplClass(ProgressionImpl.class);
		setModelPKClass(long.class);

		setTable(ProgressionTable.INSTANCE);
	}

	/**
	 * Caches the progression in the entity cache if it is enabled.
	 *
	 * @param progression the progression
	 */
	@Override
	public void cacheResult(Progression progression) {
		entityCache.putResult(
			ProgressionImpl.class, progression.getPrimaryKey(), progression);

		finderCache.putResult(
			_finderPathFetchByprogressionId,
			new Object[] {progression.getProgressionId()}, progression);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the progressions in the entity cache if it is enabled.
	 *
	 * @param progressions the progressions
	 */
	@Override
	public void cacheResult(List<Progression> progressions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (progressions.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Progression progression : progressions) {
			if (entityCache.getResult(
					ProgressionImpl.class, progression.getPrimaryKey()) ==
						null) {

				cacheResult(progression);
			}
		}
	}

	/**
	 * Clears the cache for all progressions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProgressionImpl.class);

		finderCache.clearCache(ProgressionImpl.class);
	}

	/**
	 * Clears the cache for the progression.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Progression progression) {
		entityCache.removeResult(ProgressionImpl.class, progression);
	}

	@Override
	public void clearCache(List<Progression> progressions) {
		for (Progression progression : progressions) {
			entityCache.removeResult(ProgressionImpl.class, progression);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ProgressionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ProgressionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ProgressionModelImpl progressionModelImpl) {

		Object[] args = new Object[] {progressionModelImpl.getProgressionId()};

		finderCache.putResult(
			_finderPathCountByprogressionId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByprogressionId, args, progressionModelImpl);
	}

	/**
	 * Creates a new progression with the primary key. Does not add the progression to the database.
	 *
	 * @param progressionId the primary key for the new progression
	 * @return the new progression
	 */
	@Override
	public Progression create(long progressionId) {
		Progression progression = new ProgressionImpl();

		progression.setNew(true);
		progression.setPrimaryKey(progressionId);

		return progression;
	}

	/**
	 * Removes the progression with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression that was removed
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	@Override
	public Progression remove(long progressionId)
		throws NoSuchProgressionException {

		return remove((Serializable)progressionId);
	}

	/**
	 * Removes the progression with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the progression
	 * @return the progression that was removed
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	@Override
	public Progression remove(Serializable primaryKey)
		throws NoSuchProgressionException {

		Session session = null;

		try {
			session = openSession();

			Progression progression = (Progression)session.get(
				ProgressionImpl.class, primaryKey);

			if (progression == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProgressionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(progression);
		}
		catch (NoSuchProgressionException noSuchEntityException) {
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
	protected Progression removeImpl(Progression progression) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(progression)) {
				progression = (Progression)session.get(
					ProgressionImpl.class, progression.getPrimaryKeyObj());
			}

			if (progression != null) {
				session.delete(progression);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (progression != null) {
			clearCache(progression);
		}

		return progression;
	}

	@Override
	public Progression updateImpl(Progression progression) {
		boolean isNew = progression.isNew();

		if (!(progression instanceof ProgressionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(progression.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(progression);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in progression proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Progression implementation " +
					progression.getClass());
		}

		ProgressionModelImpl progressionModelImpl =
			(ProgressionModelImpl)progression;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (progression.getCreateDate() == null)) {
			if (serviceContext == null) {
				progression.setCreateDate(date);
			}
			else {
				progression.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!progressionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				progression.setModifiedDate(date);
			}
			else {
				progression.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(progression);
			}
			else {
				progression = (Progression)session.merge(progression);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ProgressionImpl.class, progressionModelImpl, false, true);

		cacheUniqueFindersCache(progressionModelImpl);

		if (isNew) {
			progression.setNew(false);
		}

		progression.resetOriginalValues();

		return progression;
	}

	/**
	 * Returns the progression with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the progression
	 * @return the progression
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	@Override
	public Progression findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProgressionException {

		Progression progression = fetchByPrimaryKey(primaryKey);

		if (progression == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProgressionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return progression;
	}

	/**
	 * Returns the progression with the primary key or throws a <code>NoSuchProgressionException</code> if it could not be found.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	@Override
	public Progression findByPrimaryKey(long progressionId)
		throws NoSuchProgressionException {

		return findByPrimaryKey((Serializable)progressionId);
	}

	/**
	 * Returns the progression with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression, or <code>null</code> if a progression with the primary key could not be found
	 */
	@Override
	public Progression fetchByPrimaryKey(long progressionId) {
		return fetchByPrimaryKey((Serializable)progressionId);
	}

	/**
	 * Returns all the progressions.
	 *
	 * @return the progressions
	 */
	@Override
	public List<Progression> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @return the range of progressions
	 */
	@Override
	public List<Progression> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of progressions
	 */
	@Override
	public List<Progression> findAll(
		int start, int end, OrderByComparator<Progression> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of progressions
	 */
	@Override
	public List<Progression> findAll(
		int start, int end, OrderByComparator<Progression> orderByComparator,
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

		List<Progression> list = null;

		if (useFinderCache) {
			list = (List<Progression>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PROGRESSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PROGRESSION;

				sql = sql.concat(ProgressionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Progression>)QueryUtil.list(
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
	 * Removes all the progressions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Progression progression : findAll()) {
			remove(progression);
		}
	}

	/**
	 * Returns the number of progressions.
	 *
	 * @return the number of progressions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PROGRESSION);

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
		return "progressionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PROGRESSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProgressionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the progression persistence.
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

		_finderPathFetchByprogressionId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByprogressionId",
			new String[] {Long.class.getName()}, new String[] {"progressionId"},
			true);

		_finderPathCountByprogressionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByprogressionId",
			new String[] {Long.class.getName()}, new String[] {"progressionId"},
			false);

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

		_setProgressionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setProgressionUtilPersistence(null);

		entityCache.removeCache(ProgressionImpl.class.getName());
	}

	private void _setProgressionUtilPersistence(
		ProgressionPersistence progressionPersistence) {

		try {
			Field field = ProgressionUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, progressionPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = ProgressionPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ProgressionPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ProgressionPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PROGRESSION =
		"SELECT progression FROM Progression progression";

	private static final String _SQL_SELECT_PROGRESSION_WHERE =
		"SELECT progression FROM Progression progression WHERE ";

	private static final String _SQL_COUNT_PROGRESSION =
		"SELECT COUNT(progression) FROM Progression progression";

	private static final String _SQL_COUNT_PROGRESSION_WHERE =
		"SELECT COUNT(progression) FROM Progression progression WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "progression.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Progression exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Progression exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProgressionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private ProgressionModelArgumentsResolver
		_progressionModelArgumentsResolver;

}