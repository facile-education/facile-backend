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

package com.weprode.facile.help.service.persistence.impl;

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

import com.weprode.facile.help.exception.NoSuchItemException;
import com.weprode.facile.help.model.HelpItem;
import com.weprode.facile.help.model.HelpItemTable;
import com.weprode.facile.help.model.impl.HelpItemImpl;
import com.weprode.facile.help.model.impl.HelpItemModelImpl;
import com.weprode.facile.help.service.persistence.HelpItemPersistence;
import com.weprode.facile.help.service.persistence.HelpItemUtil;
import com.weprode.facile.help.service.persistence.impl.constants.HelpPersistenceConstants;

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
 * The persistence implementation for the help item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {HelpItemPersistence.class, BasePersistence.class})
public class HelpItemPersistenceImpl
	extends BasePersistenceImpl<HelpItem> implements HelpItemPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>HelpItemUtil</code> to access the help item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		HelpItemImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBycategoryId;
	private FinderPath _finderPathWithoutPaginationFindBycategoryId;
	private FinderPath _finderPathCountBycategoryId;

	/**
	 * Returns all the help items where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the matching help items
	 */
	@Override
	public List<HelpItem> findBycategoryId(long categoryId) {
		return findBycategoryId(
			categoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the help items where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @return the range of matching help items
	 */
	@Override
	public List<HelpItem> findBycategoryId(
		long categoryId, int start, int end) {

		return findBycategoryId(categoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the help items where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help items
	 */
	@Override
	public List<HelpItem> findBycategoryId(
		long categoryId, int start, int end,
		OrderByComparator<HelpItem> orderByComparator) {

		return findBycategoryId(
			categoryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the help items where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help items
	 */
	@Override
	public List<HelpItem> findBycategoryId(
		long categoryId, int start, int end,
		OrderByComparator<HelpItem> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBycategoryId;
				finderArgs = new Object[] {categoryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBycategoryId;
			finderArgs = new Object[] {
				categoryId, start, end, orderByComparator
			};
		}

		List<HelpItem> list = null;

		if (useFinderCache) {
			list = (List<HelpItem>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HelpItem helpItem : list) {
					if (categoryId != helpItem.getCategoryId()) {
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

			sb.append(_SQL_SELECT_HELPITEM_WHERE);

			sb.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(HelpItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(categoryId);

				list = (List<HelpItem>)QueryUtil.list(
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
	 * Returns the first help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item
	 * @throws NoSuchItemException if a matching help item could not be found
	 */
	@Override
	public HelpItem findBycategoryId_First(
			long categoryId, OrderByComparator<HelpItem> orderByComparator)
		throws NoSuchItemException {

		HelpItem helpItem = fetchBycategoryId_First(
			categoryId, orderByComparator);

		if (helpItem != null) {
			return helpItem;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("categoryId=");
		sb.append(categoryId);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the first help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item, or <code>null</code> if a matching help item could not be found
	 */
	@Override
	public HelpItem fetchBycategoryId_First(
		long categoryId, OrderByComparator<HelpItem> orderByComparator) {

		List<HelpItem> list = findBycategoryId(
			categoryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item
	 * @throws NoSuchItemException if a matching help item could not be found
	 */
	@Override
	public HelpItem findBycategoryId_Last(
			long categoryId, OrderByComparator<HelpItem> orderByComparator)
		throws NoSuchItemException {

		HelpItem helpItem = fetchBycategoryId_Last(
			categoryId, orderByComparator);

		if (helpItem != null) {
			return helpItem;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("categoryId=");
		sb.append(categoryId);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the last help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item, or <code>null</code> if a matching help item could not be found
	 */
	@Override
	public HelpItem fetchBycategoryId_Last(
		long categoryId, OrderByComparator<HelpItem> orderByComparator) {

		int count = countBycategoryId(categoryId);

		if (count == 0) {
			return null;
		}

		List<HelpItem> list = findBycategoryId(
			categoryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the help items before and after the current help item in the ordered set where categoryId = &#63;.
	 *
	 * @param itemId the primary key of the current help item
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help item
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	@Override
	public HelpItem[] findBycategoryId_PrevAndNext(
			long itemId, long categoryId,
			OrderByComparator<HelpItem> orderByComparator)
		throws NoSuchItemException {

		HelpItem helpItem = findByPrimaryKey(itemId);

		Session session = null;

		try {
			session = openSession();

			HelpItem[] array = new HelpItemImpl[3];

			array[0] = getBycategoryId_PrevAndNext(
				session, helpItem, categoryId, orderByComparator, true);

			array[1] = helpItem;

			array[2] = getBycategoryId_PrevAndNext(
				session, helpItem, categoryId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected HelpItem getBycategoryId_PrevAndNext(
		Session session, HelpItem helpItem, long categoryId,
		OrderByComparator<HelpItem> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_HELPITEM_WHERE);

		sb.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

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
			sb.append(HelpItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(categoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(helpItem)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<HelpItem> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the help items where categoryId = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 */
	@Override
	public void removeBycategoryId(long categoryId) {
		for (HelpItem helpItem :
				findBycategoryId(
					categoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(helpItem);
		}
	}

	/**
	 * Returns the number of help items where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the number of matching help items
	 */
	@Override
	public int countBycategoryId(long categoryId) {
		FinderPath finderPath = _finderPathCountBycategoryId;

		Object[] finderArgs = new Object[] {categoryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_HELPITEM_WHERE);

			sb.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(categoryId);

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

	private static final String _FINDER_COLUMN_CATEGORYID_CATEGORYID_2 =
		"helpItem.categoryId = ?";

	public HelpItemPersistenceImpl() {
		setModelClass(HelpItem.class);

		setModelImplClass(HelpItemImpl.class);
		setModelPKClass(long.class);

		setTable(HelpItemTable.INSTANCE);
	}

	/**
	 * Caches the help item in the entity cache if it is enabled.
	 *
	 * @param helpItem the help item
	 */
	@Override
	public void cacheResult(HelpItem helpItem) {
		entityCache.putResult(
			HelpItemImpl.class, helpItem.getPrimaryKey(), helpItem);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the help items in the entity cache if it is enabled.
	 *
	 * @param helpItems the help items
	 */
	@Override
	public void cacheResult(List<HelpItem> helpItems) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (helpItems.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (HelpItem helpItem : helpItems) {
			if (entityCache.getResult(
					HelpItemImpl.class, helpItem.getPrimaryKey()) == null) {

				cacheResult(helpItem);
			}
		}
	}

	/**
	 * Clears the cache for all help items.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HelpItemImpl.class);

		finderCache.clearCache(HelpItemImpl.class);
	}

	/**
	 * Clears the cache for the help item.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HelpItem helpItem) {
		entityCache.removeResult(HelpItemImpl.class, helpItem);
	}

	@Override
	public void clearCache(List<HelpItem> helpItems) {
		for (HelpItem helpItem : helpItems) {
			entityCache.removeResult(HelpItemImpl.class, helpItem);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(HelpItemImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(HelpItemImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new help item with the primary key. Does not add the help item to the database.
	 *
	 * @param itemId the primary key for the new help item
	 * @return the new help item
	 */
	@Override
	public HelpItem create(long itemId) {
		HelpItem helpItem = new HelpItemImpl();

		helpItem.setNew(true);
		helpItem.setPrimaryKey(itemId);

		return helpItem;
	}

	/**
	 * Removes the help item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item that was removed
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	@Override
	public HelpItem remove(long itemId) throws NoSuchItemException {
		return remove((Serializable)itemId);
	}

	/**
	 * Removes the help item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the help item
	 * @return the help item that was removed
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	@Override
	public HelpItem remove(Serializable primaryKey) throws NoSuchItemException {
		Session session = null;

		try {
			session = openSession();

			HelpItem helpItem = (HelpItem)session.get(
				HelpItemImpl.class, primaryKey);

			if (helpItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(helpItem);
		}
		catch (NoSuchItemException noSuchEntityException) {
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
	protected HelpItem removeImpl(HelpItem helpItem) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(helpItem)) {
				helpItem = (HelpItem)session.get(
					HelpItemImpl.class, helpItem.getPrimaryKeyObj());
			}

			if (helpItem != null) {
				session.delete(helpItem);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (helpItem != null) {
			clearCache(helpItem);
		}

		return helpItem;
	}

	@Override
	public HelpItem updateImpl(HelpItem helpItem) {
		boolean isNew = helpItem.isNew();

		if (!(helpItem instanceof HelpItemModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(helpItem.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(helpItem);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in helpItem proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom HelpItem implementation " +
					helpItem.getClass());
		}

		HelpItemModelImpl helpItemModelImpl = (HelpItemModelImpl)helpItem;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(helpItem);
			}
			else {
				helpItem = (HelpItem)session.merge(helpItem);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			HelpItemImpl.class, helpItemModelImpl, false, true);

		if (isNew) {
			helpItem.setNew(false);
		}

		helpItem.resetOriginalValues();

		return helpItem;
	}

	/**
	 * Returns the help item with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the help item
	 * @return the help item
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	@Override
	public HelpItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemException {

		HelpItem helpItem = fetchByPrimaryKey(primaryKey);

		if (helpItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return helpItem;
	}

	/**
	 * Returns the help item with the primary key or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	@Override
	public HelpItem findByPrimaryKey(long itemId) throws NoSuchItemException {
		return findByPrimaryKey((Serializable)itemId);
	}

	/**
	 * Returns the help item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item, or <code>null</code> if a help item with the primary key could not be found
	 */
	@Override
	public HelpItem fetchByPrimaryKey(long itemId) {
		return fetchByPrimaryKey((Serializable)itemId);
	}

	/**
	 * Returns all the help items.
	 *
	 * @return the help items
	 */
	@Override
	public List<HelpItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @return the range of help items
	 */
	@Override
	public List<HelpItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help items
	 */
	@Override
	public List<HelpItem> findAll(
		int start, int end, OrderByComparator<HelpItem> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help items
	 */
	@Override
	public List<HelpItem> findAll(
		int start, int end, OrderByComparator<HelpItem> orderByComparator,
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

		List<HelpItem> list = null;

		if (useFinderCache) {
			list = (List<HelpItem>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_HELPITEM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_HELPITEM;

				sql = sql.concat(HelpItemModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<HelpItem>)QueryUtil.list(
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
	 * Removes all the help items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HelpItem helpItem : findAll()) {
			remove(helpItem);
		}
	}

	/**
	 * Returns the number of help items.
	 *
	 * @return the number of help items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_HELPITEM);

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
		return "itemId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_HELPITEM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return HelpItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the help item persistence.
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

		_finderPathWithPaginationFindBycategoryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBycategoryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"categoryId"}, true);

		_finderPathWithoutPaginationFindBycategoryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBycategoryId",
			new String[] {Long.class.getName()}, new String[] {"categoryId"},
			true);

		_finderPathCountBycategoryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycategoryId",
			new String[] {Long.class.getName()}, new String[] {"categoryId"},
			false);

		_setHelpItemUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setHelpItemUtilPersistence(null);

		entityCache.removeCache(HelpItemImpl.class.getName());
	}

	private void _setHelpItemUtilPersistence(
		HelpItemPersistence helpItemPersistence) {

		try {
			Field field = HelpItemUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, helpItemPersistence);
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

	private static final String _SQL_SELECT_HELPITEM =
		"SELECT helpItem FROM HelpItem helpItem";

	private static final String _SQL_SELECT_HELPITEM_WHERE =
		"SELECT helpItem FROM HelpItem helpItem WHERE ";

	private static final String _SQL_COUNT_HELPITEM =
		"SELECT COUNT(helpItem) FROM HelpItem helpItem";

	private static final String _SQL_COUNT_HELPITEM_WHERE =
		"SELECT COUNT(helpItem) FROM HelpItem helpItem WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "helpItem.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No HelpItem exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No HelpItem exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		HelpItemPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}