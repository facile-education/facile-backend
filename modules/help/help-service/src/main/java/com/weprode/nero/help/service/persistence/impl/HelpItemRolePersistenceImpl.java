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

import com.weprode.nero.help.exception.NoSuchItemRoleException;
import com.weprode.nero.help.model.HelpItemRole;
import com.weprode.nero.help.model.HelpItemRoleTable;
import com.weprode.nero.help.model.impl.HelpItemRoleImpl;
import com.weprode.nero.help.model.impl.HelpItemRoleModelImpl;
import com.weprode.nero.help.service.persistence.HelpItemRolePersistence;
import com.weprode.nero.help.service.persistence.HelpItemRoleUtil;
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
 * The persistence implementation for the help item role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {HelpItemRolePersistence.class, BasePersistence.class})
public class HelpItemRolePersistenceImpl
	extends BasePersistenceImpl<HelpItemRole>
	implements HelpItemRolePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>HelpItemRoleUtil</code> to access the help item role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		HelpItemRoleImpl.class.getName();

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
	 * Returns all the help item roles where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help item roles
	 */
	@Override
	public List<HelpItemRole> findByitemId(long itemId) {
		return findByitemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the help item roles where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @return the range of matching help item roles
	 */
	@Override
	public List<HelpItemRole> findByitemId(long itemId, int start, int end) {
		return findByitemId(itemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the help item roles where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help item roles
	 */
	@Override
	public List<HelpItemRole> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpItemRole> orderByComparator) {

		return findByitemId(itemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the help item roles where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help item roles
	 */
	@Override
	public List<HelpItemRole> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpItemRole> orderByComparator,
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

		List<HelpItemRole> list = null;

		if (useFinderCache) {
			list = (List<HelpItemRole>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (HelpItemRole helpItemRole : list) {
					if (itemId != helpItemRole.getItemId()) {
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

			sb.append(_SQL_SELECT_HELPITEMROLE_WHERE);

			sb.append(_FINDER_COLUMN_ITEMID_ITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(HelpItemRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(itemId);

				list = (List<HelpItemRole>)QueryUtil.list(
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
	 * Returns the first help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item role
	 * @throws NoSuchItemRoleException if a matching help item role could not be found
	 */
	@Override
	public HelpItemRole findByitemId_First(
			long itemId, OrderByComparator<HelpItemRole> orderByComparator)
		throws NoSuchItemRoleException {

		HelpItemRole helpItemRole = fetchByitemId_First(
			itemId, orderByComparator);

		if (helpItemRole != null) {
			return helpItemRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("itemId=");
		sb.append(itemId);

		sb.append("}");

		throw new NoSuchItemRoleException(sb.toString());
	}

	/**
	 * Returns the first help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item role, or <code>null</code> if a matching help item role could not be found
	 */
	@Override
	public HelpItemRole fetchByitemId_First(
		long itemId, OrderByComparator<HelpItemRole> orderByComparator) {

		List<HelpItemRole> list = findByitemId(itemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item role
	 * @throws NoSuchItemRoleException if a matching help item role could not be found
	 */
	@Override
	public HelpItemRole findByitemId_Last(
			long itemId, OrderByComparator<HelpItemRole> orderByComparator)
		throws NoSuchItemRoleException {

		HelpItemRole helpItemRole = fetchByitemId_Last(
			itemId, orderByComparator);

		if (helpItemRole != null) {
			return helpItemRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("itemId=");
		sb.append(itemId);

		sb.append("}");

		throw new NoSuchItemRoleException(sb.toString());
	}

	/**
	 * Returns the last help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item role, or <code>null</code> if a matching help item role could not be found
	 */
	@Override
	public HelpItemRole fetchByitemId_Last(
		long itemId, OrderByComparator<HelpItemRole> orderByComparator) {

		int count = countByitemId(itemId);

		if (count == 0) {
			return null;
		}

		List<HelpItemRole> list = findByitemId(
			itemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the help item roles before and after the current help item role in the ordered set where itemId = &#63;.
	 *
	 * @param helpItemRoleId the primary key of the current help item role
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help item role
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	@Override
	public HelpItemRole[] findByitemId_PrevAndNext(
			long helpItemRoleId, long itemId,
			OrderByComparator<HelpItemRole> orderByComparator)
		throws NoSuchItemRoleException {

		HelpItemRole helpItemRole = findByPrimaryKey(helpItemRoleId);

		Session session = null;

		try {
			session = openSession();

			HelpItemRole[] array = new HelpItemRoleImpl[3];

			array[0] = getByitemId_PrevAndNext(
				session, helpItemRole, itemId, orderByComparator, true);

			array[1] = helpItemRole;

			array[2] = getByitemId_PrevAndNext(
				session, helpItemRole, itemId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected HelpItemRole getByitemId_PrevAndNext(
		Session session, HelpItemRole helpItemRole, long itemId,
		OrderByComparator<HelpItemRole> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_HELPITEMROLE_WHERE);

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
			sb.append(HelpItemRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(itemId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(helpItemRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<HelpItemRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the help item roles where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	@Override
	public void removeByitemId(long itemId) {
		for (HelpItemRole helpItemRole :
				findByitemId(
					itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(helpItemRole);
		}
	}

	/**
	 * Returns the number of help item roles where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help item roles
	 */
	@Override
	public int countByitemId(long itemId) {
		FinderPath finderPath = _finderPathCountByitemId;

		Object[] finderArgs = new Object[] {itemId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_HELPITEMROLE_WHERE);

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
		"helpItemRole.itemId = ?";

	public HelpItemRolePersistenceImpl() {
		setModelClass(HelpItemRole.class);

		setModelImplClass(HelpItemRoleImpl.class);
		setModelPKClass(long.class);

		setTable(HelpItemRoleTable.INSTANCE);
	}

	/**
	 * Caches the help item role in the entity cache if it is enabled.
	 *
	 * @param helpItemRole the help item role
	 */
	@Override
	public void cacheResult(HelpItemRole helpItemRole) {
		entityCache.putResult(
			HelpItemRoleImpl.class, helpItemRole.getPrimaryKey(), helpItemRole);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the help item roles in the entity cache if it is enabled.
	 *
	 * @param helpItemRoles the help item roles
	 */
	@Override
	public void cacheResult(List<HelpItemRole> helpItemRoles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (helpItemRoles.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (HelpItemRole helpItemRole : helpItemRoles) {
			if (entityCache.getResult(
					HelpItemRoleImpl.class, helpItemRole.getPrimaryKey()) ==
						null) {

				cacheResult(helpItemRole);
			}
		}
	}

	/**
	 * Clears the cache for all help item roles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HelpItemRoleImpl.class);

		finderCache.clearCache(HelpItemRoleImpl.class);
	}

	/**
	 * Clears the cache for the help item role.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HelpItemRole helpItemRole) {
		entityCache.removeResult(HelpItemRoleImpl.class, helpItemRole);
	}

	@Override
	public void clearCache(List<HelpItemRole> helpItemRoles) {
		for (HelpItemRole helpItemRole : helpItemRoles) {
			entityCache.removeResult(HelpItemRoleImpl.class, helpItemRole);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(HelpItemRoleImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(HelpItemRoleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new help item role with the primary key. Does not add the help item role to the database.
	 *
	 * @param helpItemRoleId the primary key for the new help item role
	 * @return the new help item role
	 */
	@Override
	public HelpItemRole create(long helpItemRoleId) {
		HelpItemRole helpItemRole = new HelpItemRoleImpl();

		helpItemRole.setNew(true);
		helpItemRole.setPrimaryKey(helpItemRoleId);

		return helpItemRole;
	}

	/**
	 * Removes the help item role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role that was removed
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	@Override
	public HelpItemRole remove(long helpItemRoleId)
		throws NoSuchItemRoleException {

		return remove((Serializable)helpItemRoleId);
	}

	/**
	 * Removes the help item role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the help item role
	 * @return the help item role that was removed
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	@Override
	public HelpItemRole remove(Serializable primaryKey)
		throws NoSuchItemRoleException {

		Session session = null;

		try {
			session = openSession();

			HelpItemRole helpItemRole = (HelpItemRole)session.get(
				HelpItemRoleImpl.class, primaryKey);

			if (helpItemRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemRoleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(helpItemRole);
		}
		catch (NoSuchItemRoleException noSuchEntityException) {
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
	protected HelpItemRole removeImpl(HelpItemRole helpItemRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(helpItemRole)) {
				helpItemRole = (HelpItemRole)session.get(
					HelpItemRoleImpl.class, helpItemRole.getPrimaryKeyObj());
			}

			if (helpItemRole != null) {
				session.delete(helpItemRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (helpItemRole != null) {
			clearCache(helpItemRole);
		}

		return helpItemRole;
	}

	@Override
	public HelpItemRole updateImpl(HelpItemRole helpItemRole) {
		boolean isNew = helpItemRole.isNew();

		if (!(helpItemRole instanceof HelpItemRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(helpItemRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					helpItemRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in helpItemRole proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom HelpItemRole implementation " +
					helpItemRole.getClass());
		}

		HelpItemRoleModelImpl helpItemRoleModelImpl =
			(HelpItemRoleModelImpl)helpItemRole;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(helpItemRole);
			}
			else {
				helpItemRole = (HelpItemRole)session.merge(helpItemRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			HelpItemRoleImpl.class, helpItemRoleModelImpl, false, true);

		if (isNew) {
			helpItemRole.setNew(false);
		}

		helpItemRole.resetOriginalValues();

		return helpItemRole;
	}

	/**
	 * Returns the help item role with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the help item role
	 * @return the help item role
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	@Override
	public HelpItemRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemRoleException {

		HelpItemRole helpItemRole = fetchByPrimaryKey(primaryKey);

		if (helpItemRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemRoleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return helpItemRole;
	}

	/**
	 * Returns the help item role with the primary key or throws a <code>NoSuchItemRoleException</code> if it could not be found.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	@Override
	public HelpItemRole findByPrimaryKey(long helpItemRoleId)
		throws NoSuchItemRoleException {

		return findByPrimaryKey((Serializable)helpItemRoleId);
	}

	/**
	 * Returns the help item role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role, or <code>null</code> if a help item role with the primary key could not be found
	 */
	@Override
	public HelpItemRole fetchByPrimaryKey(long helpItemRoleId) {
		return fetchByPrimaryKey((Serializable)helpItemRoleId);
	}

	/**
	 * Returns all the help item roles.
	 *
	 * @return the help item roles
	 */
	@Override
	public List<HelpItemRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @return the range of help item roles
	 */
	@Override
	public List<HelpItemRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help item roles
	 */
	@Override
	public List<HelpItemRole> findAll(
		int start, int end, OrderByComparator<HelpItemRole> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help item roles
	 */
	@Override
	public List<HelpItemRole> findAll(
		int start, int end, OrderByComparator<HelpItemRole> orderByComparator,
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

		List<HelpItemRole> list = null;

		if (useFinderCache) {
			list = (List<HelpItemRole>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_HELPITEMROLE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_HELPITEMROLE;

				sql = sql.concat(HelpItemRoleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<HelpItemRole>)QueryUtil.list(
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
	 * Removes all the help item roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HelpItemRole helpItemRole : findAll()) {
			remove(helpItemRole);
		}
	}

	/**
	 * Returns the number of help item roles.
	 *
	 * @return the number of help item roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_HELPITEMROLE);

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
		return "helpItemRoleId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_HELPITEMROLE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return HelpItemRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the help item role persistence.
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

		_setHelpItemRoleUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setHelpItemRoleUtilPersistence(null);

		entityCache.removeCache(HelpItemRoleImpl.class.getName());
	}

	private void _setHelpItemRoleUtilPersistence(
		HelpItemRolePersistence helpItemRolePersistence) {

		try {
			Field field = HelpItemRoleUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, helpItemRolePersistence);
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

	private static final String _SQL_SELECT_HELPITEMROLE =
		"SELECT helpItemRole FROM HelpItemRole helpItemRole";

	private static final String _SQL_SELECT_HELPITEMROLE_WHERE =
		"SELECT helpItemRole FROM HelpItemRole helpItemRole WHERE ";

	private static final String _SQL_COUNT_HELPITEMROLE =
		"SELECT COUNT(helpItemRole) FROM HelpItemRole helpItemRole";

	private static final String _SQL_COUNT_HELPITEMROLE_WHERE =
		"SELECT COUNT(helpItemRole) FROM HelpItemRole helpItemRole WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "helpItemRole.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No HelpItemRole exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No HelpItemRole exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		HelpItemRolePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private HelpItemRoleModelArgumentsResolver
		_helpItemRoleModelArgumentsResolver;

}