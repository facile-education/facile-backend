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

import com.weprode.nero.help.exception.NoSuchLinkException;
import com.weprode.nero.help.model.HelpLink;
import com.weprode.nero.help.model.HelpLinkTable;
import com.weprode.nero.help.model.impl.HelpLinkImpl;
import com.weprode.nero.help.model.impl.HelpLinkModelImpl;
import com.weprode.nero.help.service.persistence.HelpLinkPersistence;
import com.weprode.nero.help.service.persistence.HelpLinkUtil;
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
 * The persistence implementation for the help link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {HelpLinkPersistence.class, BasePersistence.class})
public class HelpLinkPersistenceImpl
	extends BasePersistenceImpl<HelpLink> implements HelpLinkPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>HelpLinkUtil</code> to access the help link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		HelpLinkImpl.class.getName();

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
	 * Returns all the help links where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help links
	 */
	@Override
	public List<HelpLink> findByitemId(long itemId) {
		return findByitemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the help links where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @return the range of matching help links
	 */
	@Override
	public List<HelpLink> findByitemId(long itemId, int start, int end) {
		return findByitemId(itemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the help links where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help links
	 */
	@Override
	public List<HelpLink> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpLink> orderByComparator) {

		return findByitemId(itemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the help links where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help links
	 */
	@Override
	public List<HelpLink> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpLink> orderByComparator, boolean useFinderCache) {

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

		List<HelpLink> list = null;

		if (useFinderCache) {
			list = (List<HelpLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HelpLink helpLink : list) {
					if (itemId != helpLink.getItemId()) {
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

			sb.append(_SQL_SELECT_HELPLINK_WHERE);

			sb.append(_FINDER_COLUMN_ITEMID_ITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(HelpLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(itemId);

				list = (List<HelpLink>)QueryUtil.list(
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
	 * Returns the first help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help link
	 * @throws NoSuchLinkException if a matching help link could not be found
	 */
	@Override
	public HelpLink findByitemId_First(
			long itemId, OrderByComparator<HelpLink> orderByComparator)
		throws NoSuchLinkException {

		HelpLink helpLink = fetchByitemId_First(itemId, orderByComparator);

		if (helpLink != null) {
			return helpLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("itemId=");
		sb.append(itemId);

		sb.append("}");

		throw new NoSuchLinkException(sb.toString());
	}

	/**
	 * Returns the first help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help link, or <code>null</code> if a matching help link could not be found
	 */
	@Override
	public HelpLink fetchByitemId_First(
		long itemId, OrderByComparator<HelpLink> orderByComparator) {

		List<HelpLink> list = findByitemId(itemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help link
	 * @throws NoSuchLinkException if a matching help link could not be found
	 */
	@Override
	public HelpLink findByitemId_Last(
			long itemId, OrderByComparator<HelpLink> orderByComparator)
		throws NoSuchLinkException {

		HelpLink helpLink = fetchByitemId_Last(itemId, orderByComparator);

		if (helpLink != null) {
			return helpLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("itemId=");
		sb.append(itemId);

		sb.append("}");

		throw new NoSuchLinkException(sb.toString());
	}

	/**
	 * Returns the last help link in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help link, or <code>null</code> if a matching help link could not be found
	 */
	@Override
	public HelpLink fetchByitemId_Last(
		long itemId, OrderByComparator<HelpLink> orderByComparator) {

		int count = countByitemId(itemId);

		if (count == 0) {
			return null;
		}

		List<HelpLink> list = findByitemId(
			itemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the help links before and after the current help link in the ordered set where itemId = &#63;.
	 *
	 * @param linkId the primary key of the current help link
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help link
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	@Override
	public HelpLink[] findByitemId_PrevAndNext(
			long linkId, long itemId,
			OrderByComparator<HelpLink> orderByComparator)
		throws NoSuchLinkException {

		HelpLink helpLink = findByPrimaryKey(linkId);

		Session session = null;

		try {
			session = openSession();

			HelpLink[] array = new HelpLinkImpl[3];

			array[0] = getByitemId_PrevAndNext(
				session, helpLink, itemId, orderByComparator, true);

			array[1] = helpLink;

			array[2] = getByitemId_PrevAndNext(
				session, helpLink, itemId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected HelpLink getByitemId_PrevAndNext(
		Session session, HelpLink helpLink, long itemId,
		OrderByComparator<HelpLink> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_HELPLINK_WHERE);

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
			sb.append(HelpLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(itemId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(helpLink)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<HelpLink> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the help links where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	@Override
	public void removeByitemId(long itemId) {
		for (HelpLink helpLink :
				findByitemId(
					itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(helpLink);
		}
	}

	/**
	 * Returns the number of help links where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help links
	 */
	@Override
	public int countByitemId(long itemId) {
		FinderPath finderPath = _finderPathCountByitemId;

		Object[] finderArgs = new Object[] {itemId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_HELPLINK_WHERE);

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
		"helpLink.itemId = ?";

	public HelpLinkPersistenceImpl() {
		setModelClass(HelpLink.class);

		setModelImplClass(HelpLinkImpl.class);
		setModelPKClass(long.class);

		setTable(HelpLinkTable.INSTANCE);
	}

	/**
	 * Caches the help link in the entity cache if it is enabled.
	 *
	 * @param helpLink the help link
	 */
	@Override
	public void cacheResult(HelpLink helpLink) {
		entityCache.putResult(
			HelpLinkImpl.class, helpLink.getPrimaryKey(), helpLink);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the help links in the entity cache if it is enabled.
	 *
	 * @param helpLinks the help links
	 */
	@Override
	public void cacheResult(List<HelpLink> helpLinks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (helpLinks.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (HelpLink helpLink : helpLinks) {
			if (entityCache.getResult(
					HelpLinkImpl.class, helpLink.getPrimaryKey()) == null) {

				cacheResult(helpLink);
			}
		}
	}

	/**
	 * Clears the cache for all help links.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HelpLinkImpl.class);

		finderCache.clearCache(HelpLinkImpl.class);
	}

	/**
	 * Clears the cache for the help link.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HelpLink helpLink) {
		entityCache.removeResult(HelpLinkImpl.class, helpLink);
	}

	@Override
	public void clearCache(List<HelpLink> helpLinks) {
		for (HelpLink helpLink : helpLinks) {
			entityCache.removeResult(HelpLinkImpl.class, helpLink);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(HelpLinkImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(HelpLinkImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new help link with the primary key. Does not add the help link to the database.
	 *
	 * @param linkId the primary key for the new help link
	 * @return the new help link
	 */
	@Override
	public HelpLink create(long linkId) {
		HelpLink helpLink = new HelpLinkImpl();

		helpLink.setNew(true);
		helpLink.setPrimaryKey(linkId);

		return helpLink;
	}

	/**
	 * Removes the help link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link that was removed
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	@Override
	public HelpLink remove(long linkId) throws NoSuchLinkException {
		return remove((Serializable)linkId);
	}

	/**
	 * Removes the help link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the help link
	 * @return the help link that was removed
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	@Override
	public HelpLink remove(Serializable primaryKey) throws NoSuchLinkException {
		Session session = null;

		try {
			session = openSession();

			HelpLink helpLink = (HelpLink)session.get(
				HelpLinkImpl.class, primaryKey);

			if (helpLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLinkException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(helpLink);
		}
		catch (NoSuchLinkException noSuchEntityException) {
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
	protected HelpLink removeImpl(HelpLink helpLink) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(helpLink)) {
				helpLink = (HelpLink)session.get(
					HelpLinkImpl.class, helpLink.getPrimaryKeyObj());
			}

			if (helpLink != null) {
				session.delete(helpLink);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (helpLink != null) {
			clearCache(helpLink);
		}

		return helpLink;
	}

	@Override
	public HelpLink updateImpl(HelpLink helpLink) {
		boolean isNew = helpLink.isNew();

		if (!(helpLink instanceof HelpLinkModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(helpLink.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(helpLink);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in helpLink proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom HelpLink implementation " +
					helpLink.getClass());
		}

		HelpLinkModelImpl helpLinkModelImpl = (HelpLinkModelImpl)helpLink;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(helpLink);
			}
			else {
				helpLink = (HelpLink)session.merge(helpLink);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			HelpLinkImpl.class, helpLinkModelImpl, false, true);

		if (isNew) {
			helpLink.setNew(false);
		}

		helpLink.resetOriginalValues();

		return helpLink;
	}

	/**
	 * Returns the help link with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the help link
	 * @return the help link
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	@Override
	public HelpLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLinkException {

		HelpLink helpLink = fetchByPrimaryKey(primaryKey);

		if (helpLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLinkException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return helpLink;
	}

	/**
	 * Returns the help link with the primary key or throws a <code>NoSuchLinkException</code> if it could not be found.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link
	 * @throws NoSuchLinkException if a help link with the primary key could not be found
	 */
	@Override
	public HelpLink findByPrimaryKey(long linkId) throws NoSuchLinkException {
		return findByPrimaryKey((Serializable)linkId);
	}

	/**
	 * Returns the help link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link, or <code>null</code> if a help link with the primary key could not be found
	 */
	@Override
	public HelpLink fetchByPrimaryKey(long linkId) {
		return fetchByPrimaryKey((Serializable)linkId);
	}

	/**
	 * Returns all the help links.
	 *
	 * @return the help links
	 */
	@Override
	public List<HelpLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the help links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @return the range of help links
	 */
	@Override
	public List<HelpLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the help links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help links
	 */
	@Override
	public List<HelpLink> findAll(
		int start, int end, OrderByComparator<HelpLink> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the help links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help links
	 */
	@Override
	public List<HelpLink> findAll(
		int start, int end, OrderByComparator<HelpLink> orderByComparator,
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

		List<HelpLink> list = null;

		if (useFinderCache) {
			list = (List<HelpLink>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_HELPLINK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_HELPLINK;

				sql = sql.concat(HelpLinkModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<HelpLink>)QueryUtil.list(
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
	 * Removes all the help links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HelpLink helpLink : findAll()) {
			remove(helpLink);
		}
	}

	/**
	 * Returns the number of help links.
	 *
	 * @return the number of help links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_HELPLINK);

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
		return "linkId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_HELPLINK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return HelpLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the help link persistence.
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

		_setHelpLinkUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setHelpLinkUtilPersistence(null);

		entityCache.removeCache(HelpLinkImpl.class.getName());
	}

	private void _setHelpLinkUtilPersistence(
		HelpLinkPersistence helpLinkPersistence) {

		try {
			Field field = HelpLinkUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, helpLinkPersistence);
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

	private static final String _SQL_SELECT_HELPLINK =
		"SELECT helpLink FROM HelpLink helpLink";

	private static final String _SQL_SELECT_HELPLINK_WHERE =
		"SELECT helpLink FROM HelpLink helpLink WHERE ";

	private static final String _SQL_COUNT_HELPLINK =
		"SELECT COUNT(helpLink) FROM HelpLink helpLink";

	private static final String _SQL_COUNT_HELPLINK_WHERE =
		"SELECT COUNT(helpLink) FROM HelpLink helpLink WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "helpLink.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No HelpLink exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No HelpLink exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		HelpLinkPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}