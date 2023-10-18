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

package com.weprode.nero.application.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.application.exception.NoSuchDefaultRoleException;
import com.weprode.nero.application.model.DefaultRole;
import com.weprode.nero.application.model.DefaultRoleTable;
import com.weprode.nero.application.model.impl.DefaultRoleImpl;
import com.weprode.nero.application.model.impl.DefaultRoleModelImpl;
import com.weprode.nero.application.service.persistence.DefaultRolePersistence;
import com.weprode.nero.application.service.persistence.DefaultRoleUtil;
import com.weprode.nero.application.service.persistence.impl.constants.ApplicationPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the default role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {DefaultRolePersistence.class, BasePersistence.class})
public class DefaultRolePersistenceImpl
	extends BasePersistenceImpl<DefaultRole> implements DefaultRolePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DefaultRoleUtil</code> to access the default role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DefaultRoleImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByapplicationId;
	private FinderPath _finderPathWithoutPaginationFindByapplicationId;
	private FinderPath _finderPathCountByapplicationId;

	/**
	 * Returns all the default roles where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching default roles
	 */
	@Override
	public List<DefaultRole> findByapplicationId(long applicationId) {
		return findByapplicationId(
			applicationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the default roles where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @return the range of matching default roles
	 */
	@Override
	public List<DefaultRole> findByapplicationId(
		long applicationId, int start, int end) {

		return findByapplicationId(applicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the default roles where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching default roles
	 */
	@Override
	public List<DefaultRole> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<DefaultRole> orderByComparator) {

		return findByapplicationId(
			applicationId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the default roles where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching default roles
	 */
	@Override
	public List<DefaultRole> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<DefaultRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByapplicationId;
				finderArgs = new Object[] {applicationId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByapplicationId;
			finderArgs = new Object[] {
				applicationId, start, end, orderByComparator
			};
		}

		List<DefaultRole> list = null;

		if (useFinderCache) {
			list = (List<DefaultRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DefaultRole defaultRole : list) {
					if (applicationId != defaultRole.getApplicationId()) {
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

			sb.append(_SQL_SELECT_DEFAULTROLE_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DefaultRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

				list = (List<DefaultRole>)QueryUtil.list(
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
	 * Returns the first default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching default role
	 * @throws NoSuchDefaultRoleException if a matching default role could not be found
	 */
	@Override
	public DefaultRole findByapplicationId_First(
			long applicationId,
			OrderByComparator<DefaultRole> orderByComparator)
		throws NoSuchDefaultRoleException {

		DefaultRole defaultRole = fetchByapplicationId_First(
			applicationId, orderByComparator);

		if (defaultRole != null) {
			return defaultRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationId=");
		sb.append(applicationId);

		sb.append("}");

		throw new NoSuchDefaultRoleException(sb.toString());
	}

	/**
	 * Returns the first default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching default role, or <code>null</code> if a matching default role could not be found
	 */
	@Override
	public DefaultRole fetchByapplicationId_First(
		long applicationId, OrderByComparator<DefaultRole> orderByComparator) {

		List<DefaultRole> list = findByapplicationId(
			applicationId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching default role
	 * @throws NoSuchDefaultRoleException if a matching default role could not be found
	 */
	@Override
	public DefaultRole findByapplicationId_Last(
			long applicationId,
			OrderByComparator<DefaultRole> orderByComparator)
		throws NoSuchDefaultRoleException {

		DefaultRole defaultRole = fetchByapplicationId_Last(
			applicationId, orderByComparator);

		if (defaultRole != null) {
			return defaultRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationId=");
		sb.append(applicationId);

		sb.append("}");

		throw new NoSuchDefaultRoleException(sb.toString());
	}

	/**
	 * Returns the last default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching default role, or <code>null</code> if a matching default role could not be found
	 */
	@Override
	public DefaultRole fetchByapplicationId_Last(
		long applicationId, OrderByComparator<DefaultRole> orderByComparator) {

		int count = countByapplicationId(applicationId);

		if (count == 0) {
			return null;
		}

		List<DefaultRole> list = findByapplicationId(
			applicationId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the default roles before and after the current default role in the ordered set where applicationId = &#63;.
	 *
	 * @param defaultRoleId the primary key of the current default role
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next default role
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	@Override
	public DefaultRole[] findByapplicationId_PrevAndNext(
			long defaultRoleId, long applicationId,
			OrderByComparator<DefaultRole> orderByComparator)
		throws NoSuchDefaultRoleException {

		DefaultRole defaultRole = findByPrimaryKey(defaultRoleId);

		Session session = null;

		try {
			session = openSession();

			DefaultRole[] array = new DefaultRoleImpl[3];

			array[0] = getByapplicationId_PrevAndNext(
				session, defaultRole, applicationId, orderByComparator, true);

			array[1] = defaultRole;

			array[2] = getByapplicationId_PrevAndNext(
				session, defaultRole, applicationId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DefaultRole getByapplicationId_PrevAndNext(
		Session session, DefaultRole defaultRole, long applicationId,
		OrderByComparator<DefaultRole> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DEFAULTROLE_WHERE);

		sb.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

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
			sb.append(DefaultRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(applicationId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(defaultRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DefaultRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the default roles where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 */
	@Override
	public void removeByapplicationId(long applicationId) {
		for (DefaultRole defaultRole :
				findByapplicationId(
					applicationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(defaultRole);
		}
	}

	/**
	 * Returns the number of default roles where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching default roles
	 */
	@Override
	public int countByapplicationId(long applicationId) {
		FinderPath finderPath = _finderPathCountByapplicationId;

		Object[] finderArgs = new Object[] {applicationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DEFAULTROLE_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

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

	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2 =
		"defaultRole.applicationId = ?";

	private FinderPath _finderPathFetchByapplicationId_roleId;
	private FinderPath _finderPathCountByapplicationId_roleId;

	/**
	 * Returns the default role where applicationId = &#63; and roleId = &#63; or throws a <code>NoSuchDefaultRoleException</code> if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the matching default role
	 * @throws NoSuchDefaultRoleException if a matching default role could not be found
	 */
	@Override
	public DefaultRole findByapplicationId_roleId(
			long applicationId, long roleId)
		throws NoSuchDefaultRoleException {

		DefaultRole defaultRole = fetchByapplicationId_roleId(
			applicationId, roleId);

		if (defaultRole == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("applicationId=");
			sb.append(applicationId);

			sb.append(", roleId=");
			sb.append(roleId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchDefaultRoleException(sb.toString());
		}

		return defaultRole;
	}

	/**
	 * Returns the default role where applicationId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the matching default role, or <code>null</code> if a matching default role could not be found
	 */
	@Override
	public DefaultRole fetchByapplicationId_roleId(
		long applicationId, long roleId) {

		return fetchByapplicationId_roleId(applicationId, roleId, true);
	}

	/**
	 * Returns the default role where applicationId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching default role, or <code>null</code> if a matching default role could not be found
	 */
	@Override
	public DefaultRole fetchByapplicationId_roleId(
		long applicationId, long roleId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {applicationId, roleId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByapplicationId_roleId, finderArgs, this);
		}

		if (result instanceof DefaultRole) {
			DefaultRole defaultRole = (DefaultRole)result;

			if ((applicationId != defaultRole.getApplicationId()) ||
				(roleId != defaultRole.getRoleId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DEFAULTROLE_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_ROLEID_APPLICATIONID_2);

			sb.append(_FINDER_COLUMN_APPLICATIONID_ROLEID_ROLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

				queryPos.add(roleId);

				List<DefaultRole> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByapplicationId_roleId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									applicationId, roleId
								};
							}

							_log.warn(
								"DefaultRolePersistenceImpl.fetchByapplicationId_roleId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DefaultRole defaultRole = list.get(0);

					result = defaultRole;

					cacheResult(defaultRole);
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
			return (DefaultRole)result;
		}
	}

	/**
	 * Removes the default role where applicationId = &#63; and roleId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the default role that was removed
	 */
	@Override
	public DefaultRole removeByapplicationId_roleId(
			long applicationId, long roleId)
		throws NoSuchDefaultRoleException {

		DefaultRole defaultRole = findByapplicationId_roleId(
			applicationId, roleId);

		return remove(defaultRole);
	}

	/**
	 * Returns the number of default roles where applicationId = &#63; and roleId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the number of matching default roles
	 */
	@Override
	public int countByapplicationId_roleId(long applicationId, long roleId) {
		FinderPath finderPath = _finderPathCountByapplicationId_roleId;

		Object[] finderArgs = new Object[] {applicationId, roleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DEFAULTROLE_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_ROLEID_APPLICATIONID_2);

			sb.append(_FINDER_COLUMN_APPLICATIONID_ROLEID_ROLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

				queryPos.add(roleId);

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
		_FINDER_COLUMN_APPLICATIONID_ROLEID_APPLICATIONID_2 =
			"defaultRole.applicationId = ? AND ";

	private static final String _FINDER_COLUMN_APPLICATIONID_ROLEID_ROLEID_2 =
		"defaultRole.roleId = ?";

	public DefaultRolePersistenceImpl() {
		setModelClass(DefaultRole.class);

		setModelImplClass(DefaultRoleImpl.class);
		setModelPKClass(long.class);

		setTable(DefaultRoleTable.INSTANCE);
	}

	/**
	 * Caches the default role in the entity cache if it is enabled.
	 *
	 * @param defaultRole the default role
	 */
	@Override
	public void cacheResult(DefaultRole defaultRole) {
		entityCache.putResult(
			DefaultRoleImpl.class, defaultRole.getPrimaryKey(), defaultRole);

		finderCache.putResult(
			_finderPathFetchByapplicationId_roleId,
			new Object[] {
				defaultRole.getApplicationId(), defaultRole.getRoleId()
			},
			defaultRole);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the default roles in the entity cache if it is enabled.
	 *
	 * @param defaultRoles the default roles
	 */
	@Override
	public void cacheResult(List<DefaultRole> defaultRoles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (defaultRoles.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DefaultRole defaultRole : defaultRoles) {
			if (entityCache.getResult(
					DefaultRoleImpl.class, defaultRole.getPrimaryKey()) ==
						null) {

				cacheResult(defaultRole);
			}
		}
	}

	/**
	 * Clears the cache for all default roles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DefaultRoleImpl.class);

		finderCache.clearCache(DefaultRoleImpl.class);
	}

	/**
	 * Clears the cache for the default role.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DefaultRole defaultRole) {
		entityCache.removeResult(DefaultRoleImpl.class, defaultRole);
	}

	@Override
	public void clearCache(List<DefaultRole> defaultRoles) {
		for (DefaultRole defaultRole : defaultRoles) {
			entityCache.removeResult(DefaultRoleImpl.class, defaultRole);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DefaultRoleImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DefaultRoleImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DefaultRoleModelImpl defaultRoleModelImpl) {

		Object[] args = new Object[] {
			defaultRoleModelImpl.getApplicationId(),
			defaultRoleModelImpl.getRoleId()
		};

		finderCache.putResult(
			_finderPathCountByapplicationId_roleId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByapplicationId_roleId, args, defaultRoleModelImpl);
	}

	/**
	 * Creates a new default role with the primary key. Does not add the default role to the database.
	 *
	 * @param defaultRoleId the primary key for the new default role
	 * @return the new default role
	 */
	@Override
	public DefaultRole create(long defaultRoleId) {
		DefaultRole defaultRole = new DefaultRoleImpl();

		defaultRole.setNew(true);
		defaultRole.setPrimaryKey(defaultRoleId);

		return defaultRole;
	}

	/**
	 * Removes the default role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role that was removed
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	@Override
	public DefaultRole remove(long defaultRoleId)
		throws NoSuchDefaultRoleException {

		return remove((Serializable)defaultRoleId);
	}

	/**
	 * Removes the default role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the default role
	 * @return the default role that was removed
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	@Override
	public DefaultRole remove(Serializable primaryKey)
		throws NoSuchDefaultRoleException {

		Session session = null;

		try {
			session = openSession();

			DefaultRole defaultRole = (DefaultRole)session.get(
				DefaultRoleImpl.class, primaryKey);

			if (defaultRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDefaultRoleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(defaultRole);
		}
		catch (NoSuchDefaultRoleException noSuchEntityException) {
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
	protected DefaultRole removeImpl(DefaultRole defaultRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(defaultRole)) {
				defaultRole = (DefaultRole)session.get(
					DefaultRoleImpl.class, defaultRole.getPrimaryKeyObj());
			}

			if (defaultRole != null) {
				session.delete(defaultRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (defaultRole != null) {
			clearCache(defaultRole);
		}

		return defaultRole;
	}

	@Override
	public DefaultRole updateImpl(DefaultRole defaultRole) {
		boolean isNew = defaultRole.isNew();

		if (!(defaultRole instanceof DefaultRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(defaultRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(defaultRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in defaultRole proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DefaultRole implementation " +
					defaultRole.getClass());
		}

		DefaultRoleModelImpl defaultRoleModelImpl =
			(DefaultRoleModelImpl)defaultRole;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(defaultRole);
			}
			else {
				defaultRole = (DefaultRole)session.merge(defaultRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DefaultRoleImpl.class, defaultRoleModelImpl, false, true);

		cacheUniqueFindersCache(defaultRoleModelImpl);

		if (isNew) {
			defaultRole.setNew(false);
		}

		defaultRole.resetOriginalValues();

		return defaultRole;
	}

	/**
	 * Returns the default role with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the default role
	 * @return the default role
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	@Override
	public DefaultRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDefaultRoleException {

		DefaultRole defaultRole = fetchByPrimaryKey(primaryKey);

		if (defaultRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDefaultRoleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return defaultRole;
	}

	/**
	 * Returns the default role with the primary key or throws a <code>NoSuchDefaultRoleException</code> if it could not be found.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	@Override
	public DefaultRole findByPrimaryKey(long defaultRoleId)
		throws NoSuchDefaultRoleException {

		return findByPrimaryKey((Serializable)defaultRoleId);
	}

	/**
	 * Returns the default role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role, or <code>null</code> if a default role with the primary key could not be found
	 */
	@Override
	public DefaultRole fetchByPrimaryKey(long defaultRoleId) {
		return fetchByPrimaryKey((Serializable)defaultRoleId);
	}

	/**
	 * Returns all the default roles.
	 *
	 * @return the default roles
	 */
	@Override
	public List<DefaultRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @return the range of default roles
	 */
	@Override
	public List<DefaultRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of default roles
	 */
	@Override
	public List<DefaultRole> findAll(
		int start, int end, OrderByComparator<DefaultRole> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of default roles
	 */
	@Override
	public List<DefaultRole> findAll(
		int start, int end, OrderByComparator<DefaultRole> orderByComparator,
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

		List<DefaultRole> list = null;

		if (useFinderCache) {
			list = (List<DefaultRole>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DEFAULTROLE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DEFAULTROLE;

				sql = sql.concat(DefaultRoleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DefaultRole>)QueryUtil.list(
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
	 * Removes all the default roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DefaultRole defaultRole : findAll()) {
			remove(defaultRole);
		}
	}

	/**
	 * Returns the number of default roles.
	 *
	 * @return the number of default roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DEFAULTROLE);

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
		return "defaultRoleId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DEFAULTROLE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DefaultRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the default role persistence.
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

		_finderPathWithPaginationFindByapplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByapplicationId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"applicationId"}, true);

		_finderPathWithoutPaginationFindByapplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByapplicationId",
			new String[] {Long.class.getName()}, new String[] {"applicationId"},
			true);

		_finderPathCountByapplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByapplicationId",
			new String[] {Long.class.getName()}, new String[] {"applicationId"},
			false);

		_finderPathFetchByapplicationId_roleId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByapplicationId_roleId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"applicationId", "roleId"}, true);

		_finderPathCountByapplicationId_roleId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByapplicationId_roleId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"applicationId", "roleId"}, false);

		_setDefaultRoleUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setDefaultRoleUtilPersistence(null);

		entityCache.removeCache(DefaultRoleImpl.class.getName());
	}

	private void _setDefaultRoleUtilPersistence(
		DefaultRolePersistence defaultRolePersistence) {

		try {
			Field field = DefaultRoleUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, defaultRolePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = ApplicationPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ApplicationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ApplicationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DEFAULTROLE =
		"SELECT defaultRole FROM DefaultRole defaultRole";

	private static final String _SQL_SELECT_DEFAULTROLE_WHERE =
		"SELECT defaultRole FROM DefaultRole defaultRole WHERE ";

	private static final String _SQL_COUNT_DEFAULTROLE =
		"SELECT COUNT(defaultRole) FROM DefaultRole defaultRole";

	private static final String _SQL_COUNT_DEFAULTROLE_WHERE =
		"SELECT COUNT(defaultRole) FROM DefaultRole defaultRole WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "defaultRole.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DefaultRole exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DefaultRole exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultRolePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}