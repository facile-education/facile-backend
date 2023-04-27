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
import com.liferay.portal.kernel.util.SetUtil;

import com.weprode.nero.progression.exception.NoSuchItemContentException;
import com.weprode.nero.progression.model.ItemContent;
import com.weprode.nero.progression.model.ItemContentTable;
import com.weprode.nero.progression.model.impl.ItemContentImpl;
import com.weprode.nero.progression.model.impl.ItemContentModelImpl;
import com.weprode.nero.progression.service.persistence.ItemContentPersistence;
import com.weprode.nero.progression.service.persistence.ItemContentUtil;
import com.weprode.nero.progression.service.persistence.impl.constants.ProgressionPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
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
 * The persistence implementation for the item content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ItemContentPersistence.class, BasePersistence.class})
public class ItemContentPersistenceImpl
	extends BasePersistenceImpl<ItemContent> implements ItemContentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ItemContentUtil</code> to access the item content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ItemContentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByprogressionItemId;
	private FinderPath _finderPathWithoutPaginationFindByprogressionItemId;
	private FinderPath _finderPathCountByprogressionItemId;

	/**
	 * Returns all the item contents where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching item contents
	 */
	@Override
	public List<ItemContent> findByprogressionItemId(long progressionItemId) {
		return findByprogressionItemId(
			progressionItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item contents where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @return the range of matching item contents
	 */
	@Override
	public List<ItemContent> findByprogressionItemId(
		long progressionItemId, int start, int end) {

		return findByprogressionItemId(progressionItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item contents where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item contents
	 */
	@Override
	public List<ItemContent> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemContent> orderByComparator) {

		return findByprogressionItemId(
			progressionItemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item contents where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item contents
	 */
	@Override
	public List<ItemContent> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemContent> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByprogressionItemId;
				finderArgs = new Object[] {progressionItemId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByprogressionItemId;
			finderArgs = new Object[] {
				progressionItemId, start, end, orderByComparator
			};
		}

		List<ItemContent> list = null;

		if (useFinderCache) {
			list = (List<ItemContent>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ItemContent itemContent : list) {
					if (progressionItemId !=
							itemContent.getProgressionItemId()) {

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

			sb.append(_SQL_SELECT_ITEMCONTENT_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_PROGRESSIONITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ItemContentModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionItemId);

				list = (List<ItemContent>)QueryUtil.list(
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
	 * Returns the first item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item content
	 * @throws NoSuchItemContentException if a matching item content could not be found
	 */
	@Override
	public ItemContent findByprogressionItemId_First(
			long progressionItemId,
			OrderByComparator<ItemContent> orderByComparator)
		throws NoSuchItemContentException {

		ItemContent itemContent = fetchByprogressionItemId_First(
			progressionItemId, orderByComparator);

		if (itemContent != null) {
			return itemContent;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionItemId=");
		sb.append(progressionItemId);

		sb.append("}");

		throw new NoSuchItemContentException(sb.toString());
	}

	/**
	 * Returns the first item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item content, or <code>null</code> if a matching item content could not be found
	 */
	@Override
	public ItemContent fetchByprogressionItemId_First(
		long progressionItemId,
		OrderByComparator<ItemContent> orderByComparator) {

		List<ItemContent> list = findByprogressionItemId(
			progressionItemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item content
	 * @throws NoSuchItemContentException if a matching item content could not be found
	 */
	@Override
	public ItemContent findByprogressionItemId_Last(
			long progressionItemId,
			OrderByComparator<ItemContent> orderByComparator)
		throws NoSuchItemContentException {

		ItemContent itemContent = fetchByprogressionItemId_Last(
			progressionItemId, orderByComparator);

		if (itemContent != null) {
			return itemContent;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionItemId=");
		sb.append(progressionItemId);

		sb.append("}");

		throw new NoSuchItemContentException(sb.toString());
	}

	/**
	 * Returns the last item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item content, or <code>null</code> if a matching item content could not be found
	 */
	@Override
	public ItemContent fetchByprogressionItemId_Last(
		long progressionItemId,
		OrderByComparator<ItemContent> orderByComparator) {

		int count = countByprogressionItemId(progressionItemId);

		if (count == 0) {
			return null;
		}

		List<ItemContent> list = findByprogressionItemId(
			progressionItemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item contents before and after the current item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param contentId the primary key of the current item content
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item content
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	@Override
	public ItemContent[] findByprogressionItemId_PrevAndNext(
			long contentId, long progressionItemId,
			OrderByComparator<ItemContent> orderByComparator)
		throws NoSuchItemContentException {

		ItemContent itemContent = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			ItemContent[] array = new ItemContentImpl[3];

			array[0] = getByprogressionItemId_PrevAndNext(
				session, itemContent, progressionItemId, orderByComparator,
				true);

			array[1] = itemContent;

			array[2] = getByprogressionItemId_PrevAndNext(
				session, itemContent, progressionItemId, orderByComparator,
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

	protected ItemContent getByprogressionItemId_PrevAndNext(
		Session session, ItemContent itemContent, long progressionItemId,
		OrderByComparator<ItemContent> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ITEMCONTENT_WHERE);

		sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_PROGRESSIONITEMID_2);

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
			sb.append(ItemContentModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(progressionItemId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(itemContent)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ItemContent> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item contents where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 */
	@Override
	public void removeByprogressionItemId(long progressionItemId) {
		for (ItemContent itemContent :
				findByprogressionItemId(
					progressionItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(itemContent);
		}
	}

	/**
	 * Returns the number of item contents where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching item contents
	 */
	@Override
	public int countByprogressionItemId(long progressionItemId) {
		FinderPath finderPath = _finderPathCountByprogressionItemId;

		Object[] finderArgs = new Object[] {progressionItemId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ITEMCONTENT_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_PROGRESSIONITEMID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionItemId);

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
		_FINDER_COLUMN_PROGRESSIONITEMID_PROGRESSIONITEMID_2 =
			"itemContent.progressionItemId = ?";

	public ItemContentPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("order", "order_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ItemContent.class);

		setModelImplClass(ItemContentImpl.class);
		setModelPKClass(long.class);

		setTable(ItemContentTable.INSTANCE);
	}

	/**
	 * Caches the item content in the entity cache if it is enabled.
	 *
	 * @param itemContent the item content
	 */
	@Override
	public void cacheResult(ItemContent itemContent) {
		entityCache.putResult(
			ItemContentImpl.class, itemContent.getPrimaryKey(), itemContent);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the item contents in the entity cache if it is enabled.
	 *
	 * @param itemContents the item contents
	 */
	@Override
	public void cacheResult(List<ItemContent> itemContents) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (itemContents.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ItemContent itemContent : itemContents) {
			if (entityCache.getResult(
					ItemContentImpl.class, itemContent.getPrimaryKey()) ==
						null) {

				cacheResult(itemContent);
			}
		}
	}

	/**
	 * Clears the cache for all item contents.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemContentImpl.class);

		finderCache.clearCache(ItemContentImpl.class);
	}

	/**
	 * Clears the cache for the item content.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemContent itemContent) {
		entityCache.removeResult(ItemContentImpl.class, itemContent);
	}

	@Override
	public void clearCache(List<ItemContent> itemContents) {
		for (ItemContent itemContent : itemContents) {
			entityCache.removeResult(ItemContentImpl.class, itemContent);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ItemContentImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ItemContentImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new item content with the primary key. Does not add the item content to the database.
	 *
	 * @param contentId the primary key for the new item content
	 * @return the new item content
	 */
	@Override
	public ItemContent create(long contentId) {
		ItemContent itemContent = new ItemContentImpl();

		itemContent.setNew(true);
		itemContent.setPrimaryKey(contentId);

		return itemContent;
	}

	/**
	 * Removes the item content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content that was removed
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	@Override
	public ItemContent remove(long contentId)
		throws NoSuchItemContentException {

		return remove((Serializable)contentId);
	}

	/**
	 * Removes the item content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item content
	 * @return the item content that was removed
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	@Override
	public ItemContent remove(Serializable primaryKey)
		throws NoSuchItemContentException {

		Session session = null;

		try {
			session = openSession();

			ItemContent itemContent = (ItemContent)session.get(
				ItemContentImpl.class, primaryKey);

			if (itemContent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemContentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(itemContent);
		}
		catch (NoSuchItemContentException noSuchEntityException) {
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
	protected ItemContent removeImpl(ItemContent itemContent) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemContent)) {
				itemContent = (ItemContent)session.get(
					ItemContentImpl.class, itemContent.getPrimaryKeyObj());
			}

			if (itemContent != null) {
				session.delete(itemContent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (itemContent != null) {
			clearCache(itemContent);
		}

		return itemContent;
	}

	@Override
	public ItemContent updateImpl(ItemContent itemContent) {
		boolean isNew = itemContent.isNew();

		if (!(itemContent instanceof ItemContentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(itemContent.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(itemContent);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in itemContent proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ItemContent implementation " +
					itemContent.getClass());
		}

		ItemContentModelImpl itemContentModelImpl =
			(ItemContentModelImpl)itemContent;

		if (!itemContentModelImpl.hasSetModifiedDate()) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				itemContent.setModifiedDate(date);
			}
			else {
				itemContent.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(itemContent);
			}
			else {
				itemContent = (ItemContent)session.merge(itemContent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ItemContentImpl.class, itemContentModelImpl, false, true);

		if (isNew) {
			itemContent.setNew(false);
		}

		itemContent.resetOriginalValues();

		return itemContent;
	}

	/**
	 * Returns the item content with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item content
	 * @return the item content
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	@Override
	public ItemContent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemContentException {

		ItemContent itemContent = fetchByPrimaryKey(primaryKey);

		if (itemContent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemContentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return itemContent;
	}

	/**
	 * Returns the item content with the primary key or throws a <code>NoSuchItemContentException</code> if it could not be found.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	@Override
	public ItemContent findByPrimaryKey(long contentId)
		throws NoSuchItemContentException {

		return findByPrimaryKey((Serializable)contentId);
	}

	/**
	 * Returns the item content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content, or <code>null</code> if a item content with the primary key could not be found
	 */
	@Override
	public ItemContent fetchByPrimaryKey(long contentId) {
		return fetchByPrimaryKey((Serializable)contentId);
	}

	/**
	 * Returns all the item contents.
	 *
	 * @return the item contents
	 */
	@Override
	public List<ItemContent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @return the range of item contents
	 */
	@Override
	public List<ItemContent> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item contents
	 */
	@Override
	public List<ItemContent> findAll(
		int start, int end, OrderByComparator<ItemContent> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of item contents
	 */
	@Override
	public List<ItemContent> findAll(
		int start, int end, OrderByComparator<ItemContent> orderByComparator,
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

		List<ItemContent> list = null;

		if (useFinderCache) {
			list = (List<ItemContent>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ITEMCONTENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMCONTENT;

				sql = sql.concat(ItemContentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ItemContent>)QueryUtil.list(
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
	 * Removes all the item contents from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemContent itemContent : findAll()) {
			remove(itemContent);
		}
	}

	/**
	 * Returns the number of item contents.
	 *
	 * @return the number of item contents
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ITEMCONTENT);

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
		return "contentId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ITEMCONTENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ItemContentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item content persistence.
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

		_finderPathWithPaginationFindByprogressionItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByprogressionItemId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"progressionItemId"}, true);

		_finderPathWithoutPaginationFindByprogressionItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByprogressionItemId", new String[] {Long.class.getName()},
			new String[] {"progressionItemId"}, true);

		_finderPathCountByprogressionItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByprogressionItemId", new String[] {Long.class.getName()},
			new String[] {"progressionItemId"}, false);

		_setItemContentUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setItemContentUtilPersistence(null);

		entityCache.removeCache(ItemContentImpl.class.getName());
	}

	private void _setItemContentUtilPersistence(
		ItemContentPersistence itemContentPersistence) {

		try {
			Field field = ItemContentUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, itemContentPersistence);
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

	private static final String _SQL_SELECT_ITEMCONTENT =
		"SELECT itemContent FROM ItemContent itemContent";

	private static final String _SQL_SELECT_ITEMCONTENT_WHERE =
		"SELECT itemContent FROM ItemContent itemContent WHERE ";

	private static final String _SQL_COUNT_ITEMCONTENT =
		"SELECT COUNT(itemContent) FROM ItemContent itemContent";

	private static final String _SQL_COUNT_ITEMCONTENT_WHERE =
		"SELECT COUNT(itemContent) FROM ItemContent itemContent WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "itemContent.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ItemContent exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ItemContent exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ItemContentPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"order"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private ItemContentModelArgumentsResolver
		_itemContentModelArgumentsResolver;

}