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

package com.weprode.nero.help.service.persistence.impl;

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

import com.weprode.nero.help.exception.NoSuchRelationException;
import com.weprode.nero.help.model.HelpRelation;
import com.weprode.nero.help.model.HelpRelationTable;
import com.weprode.nero.help.model.impl.HelpRelationImpl;
import com.weprode.nero.help.model.impl.HelpRelationModelImpl;
import com.weprode.nero.help.service.persistence.HelpRelationPersistence;
import com.weprode.nero.help.service.persistence.HelpRelationUtil;
import com.weprode.nero.help.service.persistence.impl.constants.HelpPersistenceConstants;

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
 * The persistence implementation for the help relation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {HelpRelationPersistence.class, BasePersistence.class})
public class HelpRelationPersistenceImpl
	extends BasePersistenceImpl<HelpRelation>
	implements HelpRelationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>HelpRelationUtil</code> to access the help relation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		HelpRelationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByitemId;
	private FinderPath _finderPathWithoutPaginationFindByitemId;
	private FinderPath _finderPathCountByitemId;

	/**
	 * Returns all the help relations where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help relations
	 */
	@Override
	public List<HelpRelation> findByitemId(long itemId) {
		return findByitemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the help relations where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @return the range of matching help relations
	 */
	@Override
	public List<HelpRelation> findByitemId(long itemId, int start, int end) {
		return findByitemId(itemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the help relations where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help relations
	 */
	@Override
	public List<HelpRelation> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpRelation> orderByComparator) {

		return findByitemId(itemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the help relations where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help relations
	 */
	@Override
	public List<HelpRelation> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpRelation> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByitemId;
				finderArgs = new Object[] {itemId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByitemId;
			finderArgs = new Object[] {itemId, start, end, orderByComparator};
		}

		List<HelpRelation> list = null;

		if (useFinderCache) {
			list = (List<HelpRelation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HelpRelation helpRelation : list) {
					if (itemId != helpRelation.getItemId()) {
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

			sb.append(_SQL_SELECT_HELPRELATION_WHERE);

			sb.append(_FINDER_COLUMN_ITEMID_ITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(HelpRelationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(itemId);

				list = (List<HelpRelation>)QueryUtil.list(
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
	 * Returns the first help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help relation
	 * @throws NoSuchRelationException if a matching help relation could not be found
	 */
	@Override
	public HelpRelation findByitemId_First(
			long itemId, OrderByComparator<HelpRelation> orderByComparator)
		throws NoSuchRelationException {

		HelpRelation helpRelation = fetchByitemId_First(
			itemId, orderByComparator);

		if (helpRelation != null) {
			return helpRelation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("itemId=");
		sb.append(itemId);

		sb.append("}");

		throw new NoSuchRelationException(sb.toString());
	}

	/**
	 * Returns the first help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help relation, or <code>null</code> if a matching help relation could not be found
	 */
	@Override
	public HelpRelation fetchByitemId_First(
		long itemId, OrderByComparator<HelpRelation> orderByComparator) {

		List<HelpRelation> list = findByitemId(itemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help relation
	 * @throws NoSuchRelationException if a matching help relation could not be found
	 */
	@Override
	public HelpRelation findByitemId_Last(
			long itemId, OrderByComparator<HelpRelation> orderByComparator)
		throws NoSuchRelationException {

		HelpRelation helpRelation = fetchByitemId_Last(
			itemId, orderByComparator);

		if (helpRelation != null) {
			return helpRelation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("itemId=");
		sb.append(itemId);

		sb.append("}");

		throw new NoSuchRelationException(sb.toString());
	}

	/**
	 * Returns the last help relation in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help relation, or <code>null</code> if a matching help relation could not be found
	 */
	@Override
	public HelpRelation fetchByitemId_Last(
		long itemId, OrderByComparator<HelpRelation> orderByComparator) {

		int count = countByitemId(itemId);

		if (count == 0) {
			return null;
		}

		List<HelpRelation> list = findByitemId(
			itemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the help relations before and after the current help relation in the ordered set where itemId = &#63;.
	 *
	 * @param relationId the primary key of the current help relation
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help relation
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	@Override
	public HelpRelation[] findByitemId_PrevAndNext(
			long relationId, long itemId,
			OrderByComparator<HelpRelation> orderByComparator)
		throws NoSuchRelationException {

		HelpRelation helpRelation = findByPrimaryKey(relationId);

		Session session = null;

		try {
			session = openSession();

			HelpRelation[] array = new HelpRelationImpl[3];

			array[0] = getByitemId_PrevAndNext(
				session, helpRelation, itemId, orderByComparator, true);

			array[1] = helpRelation;

			array[2] = getByitemId_PrevAndNext(
				session, helpRelation, itemId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected HelpRelation getByitemId_PrevAndNext(
		Session session, HelpRelation helpRelation, long itemId,
		OrderByComparator<HelpRelation> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_HELPRELATION_WHERE);

		sb.append(_FINDER_COLUMN_ITEMID_ITEMID_2);

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
			sb.append(HelpRelationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(itemId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(helpRelation)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<HelpRelation> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the help relations where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	@Override
	public void removeByitemId(long itemId) {
		for (HelpRelation helpRelation :
				findByitemId(
					itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(helpRelation);
		}
	}

	/**
	 * Returns the number of help relations where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help relations
	 */
	@Override
	public int countByitemId(long itemId) {
		FinderPath finderPath = _finderPathCountByitemId;

		Object[] finderArgs = new Object[] {itemId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_HELPRELATION_WHERE);

			sb.append(_FINDER_COLUMN_ITEMID_ITEMID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(itemId);

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

	private static final String _FINDER_COLUMN_ITEMID_ITEMID_2 =
		"helpRelation.itemId = ?";

	public HelpRelationPersistenceImpl() {
		setModelClass(HelpRelation.class);

		setModelImplClass(HelpRelationImpl.class);
		setModelPKClass(long.class);

		setTable(HelpRelationTable.INSTANCE);
	}

	/**
	 * Caches the help relation in the entity cache if it is enabled.
	 *
	 * @param helpRelation the help relation
	 */
	@Override
	public void cacheResult(HelpRelation helpRelation) {
		entityCache.putResult(
			HelpRelationImpl.class, helpRelation.getPrimaryKey(), helpRelation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the help relations in the entity cache if it is enabled.
	 *
	 * @param helpRelations the help relations
	 */
	@Override
	public void cacheResult(List<HelpRelation> helpRelations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (helpRelations.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (HelpRelation helpRelation : helpRelations) {
			if (entityCache.getResult(
					HelpRelationImpl.class, helpRelation.getPrimaryKey()) ==
						null) {

				cacheResult(helpRelation);
			}
		}
	}

	/**
	 * Clears the cache for all help relations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HelpRelationImpl.class);

		finderCache.clearCache(HelpRelationImpl.class);
	}

	/**
	 * Clears the cache for the help relation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HelpRelation helpRelation) {
		entityCache.removeResult(HelpRelationImpl.class, helpRelation);
	}

	@Override
	public void clearCache(List<HelpRelation> helpRelations) {
		for (HelpRelation helpRelation : helpRelations) {
			entityCache.removeResult(HelpRelationImpl.class, helpRelation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(HelpRelationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(HelpRelationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new help relation with the primary key. Does not add the help relation to the database.
	 *
	 * @param relationId the primary key for the new help relation
	 * @return the new help relation
	 */
	@Override
	public HelpRelation create(long relationId) {
		HelpRelation helpRelation = new HelpRelationImpl();

		helpRelation.setNew(true);
		helpRelation.setPrimaryKey(relationId);

		return helpRelation;
	}

	/**
	 * Removes the help relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation that was removed
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	@Override
	public HelpRelation remove(long relationId) throws NoSuchRelationException {
		return remove((Serializable)relationId);
	}

	/**
	 * Removes the help relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the help relation
	 * @return the help relation that was removed
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	@Override
	public HelpRelation remove(Serializable primaryKey)
		throws NoSuchRelationException {

		Session session = null;

		try {
			session = openSession();

			HelpRelation helpRelation = (HelpRelation)session.get(
				HelpRelationImpl.class, primaryKey);

			if (helpRelation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRelationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(helpRelation);
		}
		catch (NoSuchRelationException noSuchEntityException) {
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
	protected HelpRelation removeImpl(HelpRelation helpRelation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(helpRelation)) {
				helpRelation = (HelpRelation)session.get(
					HelpRelationImpl.class, helpRelation.getPrimaryKeyObj());
			}

			if (helpRelation != null) {
				session.delete(helpRelation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (helpRelation != null) {
			clearCache(helpRelation);
		}

		return helpRelation;
	}

	@Override
	public HelpRelation updateImpl(HelpRelation helpRelation) {
		boolean isNew = helpRelation.isNew();

		if (!(helpRelation instanceof HelpRelationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(helpRelation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					helpRelation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in helpRelation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom HelpRelation implementation " +
					helpRelation.getClass());
		}

		HelpRelationModelImpl helpRelationModelImpl =
			(HelpRelationModelImpl)helpRelation;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(helpRelation);
			}
			else {
				helpRelation = (HelpRelation)session.merge(helpRelation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			HelpRelationImpl.class, helpRelationModelImpl, false, true);

		if (isNew) {
			helpRelation.setNew(false);
		}

		helpRelation.resetOriginalValues();

		return helpRelation;
	}

	/**
	 * Returns the help relation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the help relation
	 * @return the help relation
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	@Override
	public HelpRelation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRelationException {

		HelpRelation helpRelation = fetchByPrimaryKey(primaryKey);

		if (helpRelation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRelationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return helpRelation;
	}

	/**
	 * Returns the help relation with the primary key or throws a <code>NoSuchRelationException</code> if it could not be found.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation
	 * @throws NoSuchRelationException if a help relation with the primary key could not be found
	 */
	@Override
	public HelpRelation findByPrimaryKey(long relationId)
		throws NoSuchRelationException {

		return findByPrimaryKey((Serializable)relationId);
	}

	/**
	 * Returns the help relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation, or <code>null</code> if a help relation with the primary key could not be found
	 */
	@Override
	public HelpRelation fetchByPrimaryKey(long relationId) {
		return fetchByPrimaryKey((Serializable)relationId);
	}

	/**
	 * Returns all the help relations.
	 *
	 * @return the help relations
	 */
	@Override
	public List<HelpRelation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the help relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @return the range of help relations
	 */
	@Override
	public List<HelpRelation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the help relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help relations
	 */
	@Override
	public List<HelpRelation> findAll(
		int start, int end, OrderByComparator<HelpRelation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the help relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help relations
	 */
	@Override
	public List<HelpRelation> findAll(
		int start, int end, OrderByComparator<HelpRelation> orderByComparator,
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

		List<HelpRelation> list = null;

		if (useFinderCache) {
			list = (List<HelpRelation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_HELPRELATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_HELPRELATION;

				sql = sql.concat(HelpRelationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<HelpRelation>)QueryUtil.list(
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
	 * Removes all the help relations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HelpRelation helpRelation : findAll()) {
			remove(helpRelation);
		}
	}

	/**
	 * Returns the number of help relations.
	 *
	 * @return the number of help relations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_HELPRELATION);

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
		return "relationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_HELPRELATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return HelpRelationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the help relation persistence.
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

		_finderPathWithPaginationFindByitemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByitemId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"itemId"}, true);

		_finderPathWithoutPaginationFindByitemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByitemId",
			new String[] {Long.class.getName()}, new String[] {"itemId"}, true);

		_finderPathCountByitemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByitemId",
			new String[] {Long.class.getName()}, new String[] {"itemId"},
			false);

		_setHelpRelationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setHelpRelationUtilPersistence(null);

		entityCache.removeCache(HelpRelationImpl.class.getName());
	}

	private void _setHelpRelationUtilPersistence(
		HelpRelationPersistence helpRelationPersistence) {

		try {
			Field field = HelpRelationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, helpRelationPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = HelpPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = HelpPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = HelpPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_HELPRELATION =
		"SELECT helpRelation FROM HelpRelation helpRelation";

	private static final String _SQL_SELECT_HELPRELATION_WHERE =
		"SELECT helpRelation FROM HelpRelation helpRelation WHERE ";

	private static final String _SQL_COUNT_HELPRELATION =
		"SELECT COUNT(helpRelation) FROM HelpRelation helpRelation";

	private static final String _SQL_COUNT_HELPRELATION_WHERE =
		"SELECT COUNT(helpRelation) FROM HelpRelation helpRelation WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "helpRelation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No HelpRelation exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No HelpRelation exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		HelpRelationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}