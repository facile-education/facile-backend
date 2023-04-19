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

import com.weprode.nero.schedule.exception.NoSuchDailyScheduleException;
import com.weprode.nero.schedule.model.DailySchedule;
import com.weprode.nero.schedule.model.DailyScheduleTable;
import com.weprode.nero.schedule.model.impl.DailyScheduleImpl;
import com.weprode.nero.schedule.model.impl.DailyScheduleModelImpl;
import com.weprode.nero.schedule.service.persistence.DailySchedulePK;
import com.weprode.nero.schedule.service.persistence.DailySchedulePersistence;
import com.weprode.nero.schedule.service.persistence.DailyScheduleUtil;
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
 * The persistence implementation for the daily schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {DailySchedulePersistence.class, BasePersistence.class})
public class DailySchedulePersistenceImpl
	extends BasePersistenceImpl<DailySchedule>
	implements DailySchedulePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DailyScheduleUtil</code> to access the daily schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DailyScheduleImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByschoolId;
	private FinderPath _finderPathWithoutPaginationFindByschoolId;
	private FinderPath _finderPathCountByschoolId;

	/**
	 * Returns all the daily schedules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching daily schedules
	 */
	@Override
	public List<DailySchedule> findByschoolId(long schoolId) {
		return findByschoolId(
			schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the daily schedules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @return the range of matching daily schedules
	 */
	@Override
	public List<DailySchedule> findByschoolId(
		long schoolId, int start, int end) {

		return findByschoolId(schoolId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the daily schedules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching daily schedules
	 */
	@Override
	public List<DailySchedule> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<DailySchedule> orderByComparator) {

		return findByschoolId(schoolId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the daily schedules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching daily schedules
	 */
	@Override
	public List<DailySchedule> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<DailySchedule> orderByComparator,
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

		List<DailySchedule> list = null;

		if (useFinderCache) {
			list = (List<DailySchedule>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (DailySchedule dailySchedule : list) {
					if (schoolId != dailySchedule.getSchoolId()) {
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

			sb.append(_SQL_SELECT_DAILYSCHEDULE_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DailyScheduleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				list = (List<DailySchedule>)QueryUtil.list(
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
	 * Returns the first daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching daily schedule
	 * @throws NoSuchDailyScheduleException if a matching daily schedule could not be found
	 */
	@Override
	public DailySchedule findByschoolId_First(
			long schoolId, OrderByComparator<DailySchedule> orderByComparator)
		throws NoSuchDailyScheduleException {

		DailySchedule dailySchedule = fetchByschoolId_First(
			schoolId, orderByComparator);

		if (dailySchedule != null) {
			return dailySchedule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchDailyScheduleException(sb.toString());
	}

	/**
	 * Returns the first daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching daily schedule, or <code>null</code> if a matching daily schedule could not be found
	 */
	@Override
	public DailySchedule fetchByschoolId_First(
		long schoolId, OrderByComparator<DailySchedule> orderByComparator) {

		List<DailySchedule> list = findByschoolId(
			schoolId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching daily schedule
	 * @throws NoSuchDailyScheduleException if a matching daily schedule could not be found
	 */
	@Override
	public DailySchedule findByschoolId_Last(
			long schoolId, OrderByComparator<DailySchedule> orderByComparator)
		throws NoSuchDailyScheduleException {

		DailySchedule dailySchedule = fetchByschoolId_Last(
			schoolId, orderByComparator);

		if (dailySchedule != null) {
			return dailySchedule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchDailyScheduleException(sb.toString());
	}

	/**
	 * Returns the last daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching daily schedule, or <code>null</code> if a matching daily schedule could not be found
	 */
	@Override
	public DailySchedule fetchByschoolId_Last(
		long schoolId, OrderByComparator<DailySchedule> orderByComparator) {

		int count = countByschoolId(schoolId);

		if (count == 0) {
			return null;
		}

		List<DailySchedule> list = findByschoolId(
			schoolId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the daily schedules before and after the current daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param dailySchedulePK the primary key of the current daily schedule
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next daily schedule
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	@Override
	public DailySchedule[] findByschoolId_PrevAndNext(
			DailySchedulePK dailySchedulePK, long schoolId,
			OrderByComparator<DailySchedule> orderByComparator)
		throws NoSuchDailyScheduleException {

		DailySchedule dailySchedule = findByPrimaryKey(dailySchedulePK);

		Session session = null;

		try {
			session = openSession();

			DailySchedule[] array = new DailyScheduleImpl[3];

			array[0] = getByschoolId_PrevAndNext(
				session, dailySchedule, schoolId, orderByComparator, true);

			array[1] = dailySchedule;

			array[2] = getByschoolId_PrevAndNext(
				session, dailySchedule, schoolId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DailySchedule getByschoolId_PrevAndNext(
		Session session, DailySchedule dailySchedule, long schoolId,
		OrderByComparator<DailySchedule> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DAILYSCHEDULE_WHERE);

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
			sb.append(DailyScheduleModelImpl.ORDER_BY_JPQL);
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
						dailySchedule)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DailySchedule> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the daily schedules where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	@Override
	public void removeByschoolId(long schoolId) {
		for (DailySchedule dailySchedule :
				findByschoolId(
					schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dailySchedule);
		}
	}

	/**
	 * Returns the number of daily schedules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching daily schedules
	 */
	@Override
	public int countByschoolId(long schoolId) {
		FinderPath finderPath = _finderPathCountByschoolId;

		Object[] finderArgs = new Object[] {schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DAILYSCHEDULE_WHERE);

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
		"dailySchedule.id.schoolId = ?";

	public DailySchedulePersistenceImpl() {
		setModelClass(DailySchedule.class);

		setModelImplClass(DailyScheduleImpl.class);
		setModelPKClass(DailySchedulePK.class);

		setTable(DailyScheduleTable.INSTANCE);
	}

	/**
	 * Caches the daily schedule in the entity cache if it is enabled.
	 *
	 * @param dailySchedule the daily schedule
	 */
	@Override
	public void cacheResult(DailySchedule dailySchedule) {
		entityCache.putResult(
			DailyScheduleImpl.class, dailySchedule.getPrimaryKey(),
			dailySchedule);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the daily schedules in the entity cache if it is enabled.
	 *
	 * @param dailySchedules the daily schedules
	 */
	@Override
	public void cacheResult(List<DailySchedule> dailySchedules) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (dailySchedules.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DailySchedule dailySchedule : dailySchedules) {
			if (entityCache.getResult(
					DailyScheduleImpl.class, dailySchedule.getPrimaryKey()) ==
						null) {

				cacheResult(dailySchedule);
			}
		}
	}

	/**
	 * Clears the cache for all daily schedules.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DailyScheduleImpl.class);

		finderCache.clearCache(DailyScheduleImpl.class);
	}

	/**
	 * Clears the cache for the daily schedule.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DailySchedule dailySchedule) {
		entityCache.removeResult(DailyScheduleImpl.class, dailySchedule);
	}

	@Override
	public void clearCache(List<DailySchedule> dailySchedules) {
		for (DailySchedule dailySchedule : dailySchedules) {
			entityCache.removeResult(DailyScheduleImpl.class, dailySchedule);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DailyScheduleImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DailyScheduleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new daily schedule with the primary key. Does not add the daily schedule to the database.
	 *
	 * @param dailySchedulePK the primary key for the new daily schedule
	 * @return the new daily schedule
	 */
	@Override
	public DailySchedule create(DailySchedulePK dailySchedulePK) {
		DailySchedule dailySchedule = new DailyScheduleImpl();

		dailySchedule.setNew(true);
		dailySchedule.setPrimaryKey(dailySchedulePK);

		return dailySchedule;
	}

	/**
	 * Removes the daily schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule that was removed
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	@Override
	public DailySchedule remove(DailySchedulePK dailySchedulePK)
		throws NoSuchDailyScheduleException {

		return remove((Serializable)dailySchedulePK);
	}

	/**
	 * Removes the daily schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the daily schedule
	 * @return the daily schedule that was removed
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	@Override
	public DailySchedule remove(Serializable primaryKey)
		throws NoSuchDailyScheduleException {

		Session session = null;

		try {
			session = openSession();

			DailySchedule dailySchedule = (DailySchedule)session.get(
				DailyScheduleImpl.class, primaryKey);

			if (dailySchedule == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDailyScheduleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(dailySchedule);
		}
		catch (NoSuchDailyScheduleException noSuchEntityException) {
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
	protected DailySchedule removeImpl(DailySchedule dailySchedule) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dailySchedule)) {
				dailySchedule = (DailySchedule)session.get(
					DailyScheduleImpl.class, dailySchedule.getPrimaryKeyObj());
			}

			if (dailySchedule != null) {
				session.delete(dailySchedule);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (dailySchedule != null) {
			clearCache(dailySchedule);
		}

		return dailySchedule;
	}

	@Override
	public DailySchedule updateImpl(DailySchedule dailySchedule) {
		boolean isNew = dailySchedule.isNew();

		if (!(dailySchedule instanceof DailyScheduleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dailySchedule.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					dailySchedule);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dailySchedule proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DailySchedule implementation " +
					dailySchedule.getClass());
		}

		DailyScheduleModelImpl dailyScheduleModelImpl =
			(DailyScheduleModelImpl)dailySchedule;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(dailySchedule);
			}
			else {
				dailySchedule = (DailySchedule)session.merge(dailySchedule);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DailyScheduleImpl.class, dailyScheduleModelImpl, false, true);

		if (isNew) {
			dailySchedule.setNew(false);
		}

		dailySchedule.resetOriginalValues();

		return dailySchedule;
	}

	/**
	 * Returns the daily schedule with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the daily schedule
	 * @return the daily schedule
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	@Override
	public DailySchedule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDailyScheduleException {

		DailySchedule dailySchedule = fetchByPrimaryKey(primaryKey);

		if (dailySchedule == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDailyScheduleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return dailySchedule;
	}

	/**
	 * Returns the daily schedule with the primary key or throws a <code>NoSuchDailyScheduleException</code> if it could not be found.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	@Override
	public DailySchedule findByPrimaryKey(DailySchedulePK dailySchedulePK)
		throws NoSuchDailyScheduleException {

		return findByPrimaryKey((Serializable)dailySchedulePK);
	}

	/**
	 * Returns the daily schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule, or <code>null</code> if a daily schedule with the primary key could not be found
	 */
	@Override
	public DailySchedule fetchByPrimaryKey(DailySchedulePK dailySchedulePK) {
		return fetchByPrimaryKey((Serializable)dailySchedulePK);
	}

	/**
	 * Returns all the daily schedules.
	 *
	 * @return the daily schedules
	 */
	@Override
	public List<DailySchedule> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the daily schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @return the range of daily schedules
	 */
	@Override
	public List<DailySchedule> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the daily schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of daily schedules
	 */
	@Override
	public List<DailySchedule> findAll(
		int start, int end,
		OrderByComparator<DailySchedule> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the daily schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of daily schedules
	 */
	@Override
	public List<DailySchedule> findAll(
		int start, int end, OrderByComparator<DailySchedule> orderByComparator,
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

		List<DailySchedule> list = null;

		if (useFinderCache) {
			list = (List<DailySchedule>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DAILYSCHEDULE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DAILYSCHEDULE;

				sql = sql.concat(DailyScheduleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DailySchedule>)QueryUtil.list(
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
	 * Removes all the daily schedules from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DailySchedule dailySchedule : findAll()) {
			remove(dailySchedule);
		}
	}

	/**
	 * Returns the number of daily schedules.
	 *
	 * @return the number of daily schedules
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DAILYSCHEDULE);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "dailySchedulePK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DAILYSCHEDULE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DailyScheduleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the daily schedule persistence.
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

		_setDailyScheduleUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setDailyScheduleUtilPersistence(null);

		entityCache.removeCache(DailyScheduleImpl.class.getName());
	}

	private void _setDailyScheduleUtilPersistence(
		DailySchedulePersistence dailySchedulePersistence) {

		try {
			Field field = DailyScheduleUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, dailySchedulePersistence);
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

	private static final String _SQL_SELECT_DAILYSCHEDULE =
		"SELECT dailySchedule FROM DailySchedule dailySchedule";

	private static final String _SQL_SELECT_DAILYSCHEDULE_WHERE =
		"SELECT dailySchedule FROM DailySchedule dailySchedule WHERE ";

	private static final String _SQL_COUNT_DAILYSCHEDULE =
		"SELECT COUNT(dailySchedule) FROM DailySchedule dailySchedule";

	private static final String _SQL_COUNT_DAILYSCHEDULE_WHERE =
		"SELECT COUNT(dailySchedule) FROM DailySchedule dailySchedule WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "dailySchedule.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DailySchedule exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DailySchedule exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DailySchedulePersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"schoolId", "sessionId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private DailyScheduleModelArgumentsResolver
		_dailyScheduleModelArgumentsResolver;

}