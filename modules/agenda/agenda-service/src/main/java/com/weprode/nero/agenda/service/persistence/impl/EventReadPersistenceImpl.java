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

package com.weprode.nero.agenda.service.persistence.impl;

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

import com.weprode.nero.agenda.exception.NoSuchEventReadException;
import com.weprode.nero.agenda.model.EventRead;
import com.weprode.nero.agenda.model.EventReadTable;
import com.weprode.nero.agenda.model.impl.EventReadImpl;
import com.weprode.nero.agenda.model.impl.EventReadModelImpl;
import com.weprode.nero.agenda.service.persistence.EventReadPK;
import com.weprode.nero.agenda.service.persistence.EventReadPersistence;
import com.weprode.nero.agenda.service.persistence.EventReadUtil;
import com.weprode.nero.agenda.service.persistence.impl.constants.AgendaPersistenceConstants;

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
 * The persistence implementation for the event read service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {EventReadPersistence.class, BasePersistence.class})
public class EventReadPersistenceImpl
	extends BasePersistenceImpl<EventRead> implements EventReadPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EventReadUtil</code> to access the event read persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EventReadImpl.class.getName();

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
	 * Returns all the event reads where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching event reads
	 */
	@Override
	public List<EventRead> findByeventId(long eventId) {
		return findByeventId(
			eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event reads where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @return the range of matching event reads
	 */
	@Override
	public List<EventRead> findByeventId(long eventId, int start, int end) {
		return findByeventId(eventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the event reads where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event reads
	 */
	@Override
	public List<EventRead> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<EventRead> orderByComparator) {

		return findByeventId(eventId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event reads where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching event reads
	 */
	@Override
	public List<EventRead> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<EventRead> orderByComparator,
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

		List<EventRead> list = null;

		if (useFinderCache) {
			list = (List<EventRead>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventRead eventRead : list) {
					if (eventId != eventRead.getEventId()) {
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

			sb.append(_SQL_SELECT_EVENTREAD_WHERE);

			sb.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EventReadModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(eventId);

				list = (List<EventRead>)QueryUtil.list(
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
	 * Returns the first event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event read
	 * @throws NoSuchEventReadException if a matching event read could not be found
	 */
	@Override
	public EventRead findByeventId_First(
			long eventId, OrderByComparator<EventRead> orderByComparator)
		throws NoSuchEventReadException {

		EventRead eventRead = fetchByeventId_First(eventId, orderByComparator);

		if (eventRead != null) {
			return eventRead;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("eventId=");
		sb.append(eventId);

		sb.append("}");

		throw new NoSuchEventReadException(sb.toString());
	}

	/**
	 * Returns the first event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event read, or <code>null</code> if a matching event read could not be found
	 */
	@Override
	public EventRead fetchByeventId_First(
		long eventId, OrderByComparator<EventRead> orderByComparator) {

		List<EventRead> list = findByeventId(eventId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event read
	 * @throws NoSuchEventReadException if a matching event read could not be found
	 */
	@Override
	public EventRead findByeventId_Last(
			long eventId, OrderByComparator<EventRead> orderByComparator)
		throws NoSuchEventReadException {

		EventRead eventRead = fetchByeventId_Last(eventId, orderByComparator);

		if (eventRead != null) {
			return eventRead;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("eventId=");
		sb.append(eventId);

		sb.append("}");

		throw new NoSuchEventReadException(sb.toString());
	}

	/**
	 * Returns the last event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event read, or <code>null</code> if a matching event read could not be found
	 */
	@Override
	public EventRead fetchByeventId_Last(
		long eventId, OrderByComparator<EventRead> orderByComparator) {

		int count = countByeventId(eventId);

		if (count == 0) {
			return null;
		}

		List<EventRead> list = findByeventId(
			eventId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the event reads before and after the current event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventReadPK the primary key of the current event read
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event read
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	@Override
	public EventRead[] findByeventId_PrevAndNext(
			EventReadPK eventReadPK, long eventId,
			OrderByComparator<EventRead> orderByComparator)
		throws NoSuchEventReadException {

		EventRead eventRead = findByPrimaryKey(eventReadPK);

		Session session = null;

		try {
			session = openSession();

			EventRead[] array = new EventReadImpl[3];

			array[0] = getByeventId_PrevAndNext(
				session, eventRead, eventId, orderByComparator, true);

			array[1] = eventRead;

			array[2] = getByeventId_PrevAndNext(
				session, eventRead, eventId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventRead getByeventId_PrevAndNext(
		Session session, EventRead eventRead, long eventId,
		OrderByComparator<EventRead> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_EVENTREAD_WHERE);

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
			sb.append(EventReadModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(eventId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(eventRead)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EventRead> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event reads where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	@Override
	public void removeByeventId(long eventId) {
		for (EventRead eventRead :
				findByeventId(
					eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(eventRead);
		}
	}

	/**
	 * Returns the number of event reads where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching event reads
	 */
	@Override
	public int countByeventId(long eventId) {
		FinderPath finderPath = _finderPathCountByeventId;

		Object[] finderArgs = new Object[] {eventId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_EVENTREAD_WHERE);

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
		"eventRead.id.eventId = ?";

	public EventReadPersistenceImpl() {
		setModelClass(EventRead.class);

		setModelImplClass(EventReadImpl.class);
		setModelPKClass(EventReadPK.class);

		setTable(EventReadTable.INSTANCE);
	}

	/**
	 * Caches the event read in the entity cache if it is enabled.
	 *
	 * @param eventRead the event read
	 */
	@Override
	public void cacheResult(EventRead eventRead) {
		entityCache.putResult(
			EventReadImpl.class, eventRead.getPrimaryKey(), eventRead);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the event reads in the entity cache if it is enabled.
	 *
	 * @param eventReads the event reads
	 */
	@Override
	public void cacheResult(List<EventRead> eventReads) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (eventReads.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EventRead eventRead : eventReads) {
			if (entityCache.getResult(
					EventReadImpl.class, eventRead.getPrimaryKey()) == null) {

				cacheResult(eventRead);
			}
		}
	}

	/**
	 * Clears the cache for all event reads.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EventReadImpl.class);

		finderCache.clearCache(EventReadImpl.class);
	}

	/**
	 * Clears the cache for the event read.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EventRead eventRead) {
		entityCache.removeResult(EventReadImpl.class, eventRead);
	}

	@Override
	public void clearCache(List<EventRead> eventReads) {
		for (EventRead eventRead : eventReads) {
			entityCache.removeResult(EventReadImpl.class, eventRead);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(EventReadImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(EventReadImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new event read with the primary key. Does not add the event read to the database.
	 *
	 * @param eventReadPK the primary key for the new event read
	 * @return the new event read
	 */
	@Override
	public EventRead create(EventReadPK eventReadPK) {
		EventRead eventRead = new EventReadImpl();

		eventRead.setNew(true);
		eventRead.setPrimaryKey(eventReadPK);

		return eventRead;
	}

	/**
	 * Removes the event read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read that was removed
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	@Override
	public EventRead remove(EventReadPK eventReadPK)
		throws NoSuchEventReadException {

		return remove((Serializable)eventReadPK);
	}

	/**
	 * Removes the event read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the event read
	 * @return the event read that was removed
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	@Override
	public EventRead remove(Serializable primaryKey)
		throws NoSuchEventReadException {

		Session session = null;

		try {
			session = openSession();

			EventRead eventRead = (EventRead)session.get(
				EventReadImpl.class, primaryKey);

			if (eventRead == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventReadException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(eventRead);
		}
		catch (NoSuchEventReadException noSuchEntityException) {
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
	protected EventRead removeImpl(EventRead eventRead) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(eventRead)) {
				eventRead = (EventRead)session.get(
					EventReadImpl.class, eventRead.getPrimaryKeyObj());
			}

			if (eventRead != null) {
				session.delete(eventRead);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (eventRead != null) {
			clearCache(eventRead);
		}

		return eventRead;
	}

	@Override
	public EventRead updateImpl(EventRead eventRead) {
		boolean isNew = eventRead.isNew();

		if (!(eventRead instanceof EventReadModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(eventRead.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(eventRead);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in eventRead proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EventRead implementation " +
					eventRead.getClass());
		}

		EventReadModelImpl eventReadModelImpl = (EventReadModelImpl)eventRead;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(eventRead);
			}
			else {
				eventRead = (EventRead)session.merge(eventRead);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			EventReadImpl.class, eventReadModelImpl, false, true);

		if (isNew) {
			eventRead.setNew(false);
		}

		eventRead.resetOriginalValues();

		return eventRead;
	}

	/**
	 * Returns the event read with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the event read
	 * @return the event read
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	@Override
	public EventRead findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEventReadException {

		EventRead eventRead = fetchByPrimaryKey(primaryKey);

		if (eventRead == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEventReadException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return eventRead;
	}

	/**
	 * Returns the event read with the primary key or throws a <code>NoSuchEventReadException</code> if it could not be found.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	@Override
	public EventRead findByPrimaryKey(EventReadPK eventReadPK)
		throws NoSuchEventReadException {

		return findByPrimaryKey((Serializable)eventReadPK);
	}

	/**
	 * Returns the event read with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read, or <code>null</code> if a event read with the primary key could not be found
	 */
	@Override
	public EventRead fetchByPrimaryKey(EventReadPK eventReadPK) {
		return fetchByPrimaryKey((Serializable)eventReadPK);
	}

	/**
	 * Returns all the event reads.
	 *
	 * @return the event reads
	 */
	@Override
	public List<EventRead> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @return the range of event reads
	 */
	@Override
	public List<EventRead> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the event reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of event reads
	 */
	@Override
	public List<EventRead> findAll(
		int start, int end, OrderByComparator<EventRead> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of event reads
	 */
	@Override
	public List<EventRead> findAll(
		int start, int end, OrderByComparator<EventRead> orderByComparator,
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

		List<EventRead> list = null;

		if (useFinderCache) {
			list = (List<EventRead>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EVENTREAD);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EVENTREAD;

				sql = sql.concat(EventReadModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EventRead>)QueryUtil.list(
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
	 * Removes all the event reads from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EventRead eventRead : findAll()) {
			remove(eventRead);
		}
	}

	/**
	 * Returns the number of event reads.
	 *
	 * @return the number of event reads
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EVENTREAD);

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
		return "eventReadPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EVENTREAD;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EventReadModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the event read persistence.
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

		_setEventReadUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setEventReadUtilPersistence(null);

		entityCache.removeCache(EventReadImpl.class.getName());
	}

	private void _setEventReadUtilPersistence(
		EventReadPersistence eventReadPersistence) {

		try {
			Field field = EventReadUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, eventReadPersistence);
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

	private static final String _SQL_SELECT_EVENTREAD =
		"SELECT eventRead FROM EventRead eventRead";

	private static final String _SQL_SELECT_EVENTREAD_WHERE =
		"SELECT eventRead FROM EventRead eventRead WHERE ";

	private static final String _SQL_COUNT_EVENTREAD =
		"SELECT COUNT(eventRead) FROM EventRead eventRead";

	private static final String _SQL_COUNT_EVENTREAD_WHERE =
		"SELECT COUNT(eventRead) FROM EventRead eventRead WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "eventRead.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EventRead exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No EventRead exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EventReadPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"eventId", "userId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}