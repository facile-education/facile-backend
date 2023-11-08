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

package com.weprode.facile.agenda.service.persistence.impl;

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

import com.weprode.facile.agenda.exception.NoSuchEventPopulationException;
import com.weprode.facile.agenda.model.EventPopulation;
import com.weprode.facile.agenda.model.EventPopulationTable;
import com.weprode.facile.agenda.model.impl.EventPopulationImpl;
import com.weprode.facile.agenda.model.impl.EventPopulationModelImpl;
import com.weprode.facile.agenda.service.persistence.EventPopulationPK;
import com.weprode.facile.agenda.service.persistence.EventPopulationPersistence;
import com.weprode.facile.agenda.service.persistence.EventPopulationUtil;
import com.weprode.facile.agenda.service.persistence.impl.constants.AgendaPersistenceConstants;

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
 * The persistence implementation for the event population service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {EventPopulationPersistence.class, BasePersistence.class})
public class EventPopulationPersistenceImpl
	extends BasePersistenceImpl<EventPopulation>
	implements EventPopulationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EventPopulationUtil</code> to access the event population persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EventPopulationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByeventId;
	private FinderPath _finderPathWithoutPaginationFindByeventId;
	private FinderPath _finderPathCountByeventId;

	/**
	 * Returns all the event populations where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching event populations
	 */
	@Override
	public List<EventPopulation> findByeventId(long eventId) {
		return findByeventId(
			eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event populations where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @return the range of matching event populations
	 */
	@Override
	public List<EventPopulation> findByeventId(
		long eventId, int start, int end) {

		return findByeventId(eventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the event populations where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event populations
	 */
	@Override
	public List<EventPopulation> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<EventPopulation> orderByComparator) {

		return findByeventId(eventId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event populations where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching event populations
	 */
	@Override
	public List<EventPopulation> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<EventPopulation> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByeventId;
				finderArgs = new Object[] {eventId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByeventId;
			finderArgs = new Object[] {eventId, start, end, orderByComparator};
		}

		List<EventPopulation> list = null;

		if (useFinderCache) {
			list = (List<EventPopulation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventPopulation eventPopulation : list) {
					if (eventId != eventPopulation.getEventId()) {
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

			sb.append(_SQL_SELECT_EVENTPOPULATION_WHERE);

			sb.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EventPopulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(eventId);

				list = (List<EventPopulation>)QueryUtil.list(
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
	 * Returns the first event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event population
	 * @throws NoSuchEventPopulationException if a matching event population could not be found
	 */
	@Override
	public EventPopulation findByeventId_First(
			long eventId, OrderByComparator<EventPopulation> orderByComparator)
		throws NoSuchEventPopulationException {

		EventPopulation eventPopulation = fetchByeventId_First(
			eventId, orderByComparator);

		if (eventPopulation != null) {
			return eventPopulation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("eventId=");
		sb.append(eventId);

		sb.append("}");

		throw new NoSuchEventPopulationException(sb.toString());
	}

	/**
	 * Returns the first event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event population, or <code>null</code> if a matching event population could not be found
	 */
	@Override
	public EventPopulation fetchByeventId_First(
		long eventId, OrderByComparator<EventPopulation> orderByComparator) {

		List<EventPopulation> list = findByeventId(
			eventId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event population
	 * @throws NoSuchEventPopulationException if a matching event population could not be found
	 */
	@Override
	public EventPopulation findByeventId_Last(
			long eventId, OrderByComparator<EventPopulation> orderByComparator)
		throws NoSuchEventPopulationException {

		EventPopulation eventPopulation = fetchByeventId_Last(
			eventId, orderByComparator);

		if (eventPopulation != null) {
			return eventPopulation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("eventId=");
		sb.append(eventId);

		sb.append("}");

		throw new NoSuchEventPopulationException(sb.toString());
	}

	/**
	 * Returns the last event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event population, or <code>null</code> if a matching event population could not be found
	 */
	@Override
	public EventPopulation fetchByeventId_Last(
		long eventId, OrderByComparator<EventPopulation> orderByComparator) {

		int count = countByeventId(eventId);

		if (count == 0) {
			return null;
		}

		List<EventPopulation> list = findByeventId(
			eventId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the event populations before and after the current event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventPopulationPK the primary key of the current event population
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event population
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	@Override
	public EventPopulation[] findByeventId_PrevAndNext(
			EventPopulationPK eventPopulationPK, long eventId,
			OrderByComparator<EventPopulation> orderByComparator)
		throws NoSuchEventPopulationException {

		EventPopulation eventPopulation = findByPrimaryKey(eventPopulationPK);

		Session session = null;

		try {
			session = openSession();

			EventPopulation[] array = new EventPopulationImpl[3];

			array[0] = getByeventId_PrevAndNext(
				session, eventPopulation, eventId, orderByComparator, true);

			array[1] = eventPopulation;

			array[2] = getByeventId_PrevAndNext(
				session, eventPopulation, eventId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventPopulation getByeventId_PrevAndNext(
		Session session, EventPopulation eventPopulation, long eventId,
		OrderByComparator<EventPopulation> orderByComparator,
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

		sb.append(_SQL_SELECT_EVENTPOPULATION_WHERE);

		sb.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

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
			sb.append(EventPopulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(eventId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						eventPopulation)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EventPopulation> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event populations where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	@Override
	public void removeByeventId(long eventId) {
		for (EventPopulation eventPopulation :
				findByeventId(
					eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(eventPopulation);
		}
	}

	/**
	 * Returns the number of event populations where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching event populations
	 */
	@Override
	public int countByeventId(long eventId) {
		FinderPath finderPath = _finderPathCountByeventId;

		Object[] finderArgs = new Object[] {eventId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_EVENTPOPULATION_WHERE);

			sb.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(eventId);

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

	private static final String _FINDER_COLUMN_EVENTID_EVENTID_2 =
		"eventPopulation.id.eventId = ?";

	public EventPopulationPersistenceImpl() {
		setModelClass(EventPopulation.class);

		setModelImplClass(EventPopulationImpl.class);
		setModelPKClass(EventPopulationPK.class);

		setTable(EventPopulationTable.INSTANCE);
	}

	/**
	 * Caches the event population in the entity cache if it is enabled.
	 *
	 * @param eventPopulation the event population
	 */
	@Override
	public void cacheResult(EventPopulation eventPopulation) {
		entityCache.putResult(
			EventPopulationImpl.class, eventPopulation.getPrimaryKey(),
			eventPopulation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the event populations in the entity cache if it is enabled.
	 *
	 * @param eventPopulations the event populations
	 */
	@Override
	public void cacheResult(List<EventPopulation> eventPopulations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (eventPopulations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EventPopulation eventPopulation : eventPopulations) {
			if (entityCache.getResult(
					EventPopulationImpl.class,
					eventPopulation.getPrimaryKey()) == null) {

				cacheResult(eventPopulation);
			}
		}
	}

	/**
	 * Clears the cache for all event populations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EventPopulationImpl.class);

		finderCache.clearCache(EventPopulationImpl.class);
	}

	/**
	 * Clears the cache for the event population.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EventPopulation eventPopulation) {
		entityCache.removeResult(EventPopulationImpl.class, eventPopulation);
	}

	@Override
	public void clearCache(List<EventPopulation> eventPopulations) {
		for (EventPopulation eventPopulation : eventPopulations) {
			entityCache.removeResult(
				EventPopulationImpl.class, eventPopulation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(EventPopulationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(EventPopulationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new event population with the primary key. Does not add the event population to the database.
	 *
	 * @param eventPopulationPK the primary key for the new event population
	 * @return the new event population
	 */
	@Override
	public EventPopulation create(EventPopulationPK eventPopulationPK) {
		EventPopulation eventPopulation = new EventPopulationImpl();

		eventPopulation.setNew(true);
		eventPopulation.setPrimaryKey(eventPopulationPK);

		return eventPopulation;
	}

	/**
	 * Removes the event population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population that was removed
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	@Override
	public EventPopulation remove(EventPopulationPK eventPopulationPK)
		throws NoSuchEventPopulationException {

		return remove((Serializable)eventPopulationPK);
	}

	/**
	 * Removes the event population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the event population
	 * @return the event population that was removed
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	@Override
	public EventPopulation remove(Serializable primaryKey)
		throws NoSuchEventPopulationException {

		Session session = null;

		try {
			session = openSession();

			EventPopulation eventPopulation = (EventPopulation)session.get(
				EventPopulationImpl.class, primaryKey);

			if (eventPopulation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventPopulationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(eventPopulation);
		}
		catch (NoSuchEventPopulationException noSuchEntityException) {
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
	protected EventPopulation removeImpl(EventPopulation eventPopulation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(eventPopulation)) {
				eventPopulation = (EventPopulation)session.get(
					EventPopulationImpl.class,
					eventPopulation.getPrimaryKeyObj());
			}

			if (eventPopulation != null) {
				session.delete(eventPopulation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (eventPopulation != null) {
			clearCache(eventPopulation);
		}

		return eventPopulation;
	}

	@Override
	public EventPopulation updateImpl(EventPopulation eventPopulation) {
		boolean isNew = eventPopulation.isNew();

		if (!(eventPopulation instanceof EventPopulationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(eventPopulation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					eventPopulation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in eventPopulation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EventPopulation implementation " +
					eventPopulation.getClass());
		}

		EventPopulationModelImpl eventPopulationModelImpl =
			(EventPopulationModelImpl)eventPopulation;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(eventPopulation);
			}
			else {
				eventPopulation = (EventPopulation)session.merge(
					eventPopulation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			EventPopulationImpl.class, eventPopulationModelImpl, false, true);

		if (isNew) {
			eventPopulation.setNew(false);
		}

		eventPopulation.resetOriginalValues();

		return eventPopulation;
	}

	/**
	 * Returns the event population with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the event population
	 * @return the event population
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	@Override
	public EventPopulation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEventPopulationException {

		EventPopulation eventPopulation = fetchByPrimaryKey(primaryKey);

		if (eventPopulation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEventPopulationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return eventPopulation;
	}

	/**
	 * Returns the event population with the primary key or throws a <code>NoSuchEventPopulationException</code> if it could not be found.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	@Override
	public EventPopulation findByPrimaryKey(EventPopulationPK eventPopulationPK)
		throws NoSuchEventPopulationException {

		return findByPrimaryKey((Serializable)eventPopulationPK);
	}

	/**
	 * Returns the event population with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population, or <code>null</code> if a event population with the primary key could not be found
	 */
	@Override
	public EventPopulation fetchByPrimaryKey(
		EventPopulationPK eventPopulationPK) {

		return fetchByPrimaryKey((Serializable)eventPopulationPK);
	}

	/**
	 * Returns all the event populations.
	 *
	 * @return the event populations
	 */
	@Override
	public List<EventPopulation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @return the range of event populations
	 */
	@Override
	public List<EventPopulation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the event populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of event populations
	 */
	@Override
	public List<EventPopulation> findAll(
		int start, int end,
		OrderByComparator<EventPopulation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of event populations
	 */
	@Override
	public List<EventPopulation> findAll(
		int start, int end,
		OrderByComparator<EventPopulation> orderByComparator,
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

		List<EventPopulation> list = null;

		if (useFinderCache) {
			list = (List<EventPopulation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EVENTPOPULATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EVENTPOPULATION;

				sql = sql.concat(EventPopulationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EventPopulation>)QueryUtil.list(
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
	 * Removes all the event populations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EventPopulation eventPopulation : findAll()) {
			remove(eventPopulation);
		}
	}

	/**
	 * Returns the number of event populations.
	 *
	 * @return the number of event populations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EVENTPOPULATION);

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
		return "eventPopulationPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EVENTPOPULATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EventPopulationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the event population persistence.
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

		_finderPathWithPaginationFindByeventId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByeventId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"eventId"}, true);

		_finderPathWithoutPaginationFindByeventId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByeventId",
			new String[] {Long.class.getName()}, new String[] {"eventId"},
			true);

		_finderPathCountByeventId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByeventId",
			new String[] {Long.class.getName()}, new String[] {"eventId"},
			false);

		_setEventPopulationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setEventPopulationUtilPersistence(null);

		entityCache.removeCache(EventPopulationImpl.class.getName());
	}

	private void _setEventPopulationUtilPersistence(
		EventPopulationPersistence eventPopulationPersistence) {

		try {
			Field field = EventPopulationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, eventPopulationPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = AgendaPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = AgendaPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AgendaPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_EVENTPOPULATION =
		"SELECT eventPopulation FROM EventPopulation eventPopulation";

	private static final String _SQL_SELECT_EVENTPOPULATION_WHERE =
		"SELECT eventPopulation FROM EventPopulation eventPopulation WHERE ";

	private static final String _SQL_COUNT_EVENTPOPULATION =
		"SELECT COUNT(eventPopulation) FROM EventPopulation eventPopulation";

	private static final String _SQL_COUNT_EVENTPOPULATION_WHERE =
		"SELECT COUNT(eventPopulation) FROM EventPopulation eventPopulation WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "eventPopulation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EventPopulation exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No EventPopulation exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EventPopulationPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"eventId", "groupId", "roleId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}