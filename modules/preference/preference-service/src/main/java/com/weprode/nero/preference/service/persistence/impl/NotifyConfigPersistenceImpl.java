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

package com.weprode.nero.preference.service.persistence.impl;

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

import com.weprode.nero.preference.exception.NoSuchNotifyConfigException;
import com.weprode.nero.preference.model.NotifyConfig;
import com.weprode.nero.preference.model.NotifyConfigTable;
import com.weprode.nero.preference.model.impl.NotifyConfigImpl;
import com.weprode.nero.preference.model.impl.NotifyConfigModelImpl;
import com.weprode.nero.preference.service.persistence.NotifyConfigPersistence;
import com.weprode.nero.preference.service.persistence.NotifyConfigUtil;
import com.weprode.nero.preference.service.persistence.impl.constants.PreferencePersistenceConstants;

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
 * The persistence implementation for the notify config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {NotifyConfigPersistence.class, BasePersistence.class})
public class NotifyConfigPersistenceImpl
	extends BasePersistenceImpl<NotifyConfig>
	implements NotifyConfigPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NotifyConfigUtil</code> to access the notify config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NotifyConfigImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByuserId;
	private FinderPath _finderPathCountByuserId;

	/**
	 * Returns the notify config where userId = &#63; or throws a <code>NoSuchNotifyConfigException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig findByuserId(long userId)
		throws NoSuchNotifyConfigException {

		NotifyConfig notifyConfig = fetchByuserId(userId);

		if (notifyConfig == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchNotifyConfigException(sb.toString());
		}

		return notifyConfig;
	}

	/**
	 * Returns the notify config where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig fetchByuserId(long userId) {
		return fetchByuserId(userId, true);
	}

	/**
	 * Returns the notify config where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig fetchByuserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByuserId, finderArgs, this);
		}

		if (result instanceof NotifyConfig) {
			NotifyConfig notifyConfig = (NotifyConfig)result;

			if (userId != notifyConfig.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_NOTIFYCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<NotifyConfig> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByuserId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {userId};
							}

							_log.warn(
								"NotifyConfigPersistenceImpl.fetchByuserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					NotifyConfig notifyConfig = list.get(0);

					result = notifyConfig;

					cacheResult(notifyConfig);
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
			return (NotifyConfig)result;
		}
	}

	/**
	 * Removes the notify config where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the notify config that was removed
	 */
	@Override
	public NotifyConfig removeByuserId(long userId)
		throws NoSuchNotifyConfigException {

		NotifyConfig notifyConfig = findByuserId(userId);

		return remove(notifyConfig);
	}

	/**
	 * Returns the number of notify configs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching notify configs
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NOTIFYCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"notifyConfig.userId = ?";

	private FinderPath _finderPathWithPaginationFindByactivate;
	private FinderPath _finderPathWithoutPaginationFindByactivate;
	private FinderPath _finderPathCountByactivate;

	/**
	 * Returns all the notify configs where activate = &#63;.
	 *
	 * @param activate the activate
	 * @return the matching notify configs
	 */
	@Override
	public List<NotifyConfig> findByactivate(boolean activate) {
		return findByactivate(
			activate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notify configs where activate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @return the range of matching notify configs
	 */
	@Override
	public List<NotifyConfig> findByactivate(
		boolean activate, int start, int end) {

		return findByactivate(activate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notify configs where activate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notify configs
	 */
	@Override
	public List<NotifyConfig> findByactivate(
		boolean activate, int start, int end,
		OrderByComparator<NotifyConfig> orderByComparator) {

		return findByactivate(activate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notify configs where activate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notify configs
	 */
	@Override
	public List<NotifyConfig> findByactivate(
		boolean activate, int start, int end,
		OrderByComparator<NotifyConfig> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByactivate;
				finderArgs = new Object[] {activate};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByactivate;
			finderArgs = new Object[] {activate, start, end, orderByComparator};
		}

		List<NotifyConfig> list = null;

		if (useFinderCache) {
			list = (List<NotifyConfig>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotifyConfig notifyConfig : list) {
					if (activate != notifyConfig.isActivate()) {
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

			sb.append(_SQL_SELECT_NOTIFYCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVATE_ACTIVATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotifyConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(activate);

				list = (List<NotifyConfig>)QueryUtil.list(
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
	 * Returns the first notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig findByactivate_First(
			boolean activate, OrderByComparator<NotifyConfig> orderByComparator)
		throws NoSuchNotifyConfigException {

		NotifyConfig notifyConfig = fetchByactivate_First(
			activate, orderByComparator);

		if (notifyConfig != null) {
			return notifyConfig;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("activate=");
		sb.append(activate);

		sb.append("}");

		throw new NoSuchNotifyConfigException(sb.toString());
	}

	/**
	 * Returns the first notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig fetchByactivate_First(
		boolean activate, OrderByComparator<NotifyConfig> orderByComparator) {

		List<NotifyConfig> list = findByactivate(
			activate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig findByactivate_Last(
			boolean activate, OrderByComparator<NotifyConfig> orderByComparator)
		throws NoSuchNotifyConfigException {

		NotifyConfig notifyConfig = fetchByactivate_Last(
			activate, orderByComparator);

		if (notifyConfig != null) {
			return notifyConfig;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("activate=");
		sb.append(activate);

		sb.append("}");

		throw new NoSuchNotifyConfigException(sb.toString());
	}

	/**
	 * Returns the last notify config in the ordered set where activate = &#63;.
	 *
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig fetchByactivate_Last(
		boolean activate, OrderByComparator<NotifyConfig> orderByComparator) {

		int count = countByactivate(activate);

		if (count == 0) {
			return null;
		}

		List<NotifyConfig> list = findByactivate(
			activate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notify configs before and after the current notify config in the ordered set where activate = &#63;.
	 *
	 * @param notifyConfigId the primary key of the current notify config
	 * @param activate the activate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notify config
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	@Override
	public NotifyConfig[] findByactivate_PrevAndNext(
			long notifyConfigId, boolean activate,
			OrderByComparator<NotifyConfig> orderByComparator)
		throws NoSuchNotifyConfigException {

		NotifyConfig notifyConfig = findByPrimaryKey(notifyConfigId);

		Session session = null;

		try {
			session = openSession();

			NotifyConfig[] array = new NotifyConfigImpl[3];

			array[0] = getByactivate_PrevAndNext(
				session, notifyConfig, activate, orderByComparator, true);

			array[1] = notifyConfig;

			array[2] = getByactivate_PrevAndNext(
				session, notifyConfig, activate, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotifyConfig getByactivate_PrevAndNext(
		Session session, NotifyConfig notifyConfig, boolean activate,
		OrderByComparator<NotifyConfig> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_NOTIFYCONFIG_WHERE);

		sb.append(_FINDER_COLUMN_ACTIVATE_ACTIVATE_2);

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
			sb.append(NotifyConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(activate);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(notifyConfig)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotifyConfig> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notify configs where activate = &#63; from the database.
	 *
	 * @param activate the activate
	 */
	@Override
	public void removeByactivate(boolean activate) {
		for (NotifyConfig notifyConfig :
				findByactivate(
					activate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(notifyConfig);
		}
	}

	/**
	 * Returns the number of notify configs where activate = &#63;.
	 *
	 * @param activate the activate
	 * @return the number of matching notify configs
	 */
	@Override
	public int countByactivate(boolean activate) {
		FinderPath finderPath = _finderPathCountByactivate;

		Object[] finderArgs = new Object[] {activate};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NOTIFYCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVATE_ACTIVATE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(activate);

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

	private static final String _FINDER_COLUMN_ACTIVATE_ACTIVATE_2 =
		"notifyConfig.activate = ?";

	private FinderPath _finderPathWithPaginationFindByactivate_digestPeriod;
	private FinderPath _finderPathWithoutPaginationFindByactivate_digestPeriod;
	private FinderPath _finderPathCountByactivate_digestPeriod;

	/**
	 * Returns all the notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @return the matching notify configs
	 */
	@Override
	public List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod) {

		return findByactivate_digestPeriod(
			activate, digestPeriod, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @return the range of matching notify configs
	 */
	@Override
	public List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod, int start, int end) {

		return findByactivate_digestPeriod(
			activate, digestPeriod, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notify configs
	 */
	@Override
	public List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod, int start, int end,
		OrderByComparator<NotifyConfig> orderByComparator) {

		return findByactivate_digestPeriod(
			activate, digestPeriod, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notify configs
	 */
	@Override
	public List<NotifyConfig> findByactivate_digestPeriod(
		boolean activate, int digestPeriod, int start, int end,
		OrderByComparator<NotifyConfig> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByactivate_digestPeriod;
				finderArgs = new Object[] {activate, digestPeriod};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByactivate_digestPeriod;
			finderArgs = new Object[] {
				activate, digestPeriod, start, end, orderByComparator
			};
		}

		List<NotifyConfig> list = null;

		if (useFinderCache) {
			list = (List<NotifyConfig>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotifyConfig notifyConfig : list) {
					if ((activate != notifyConfig.isActivate()) ||
						(digestPeriod != notifyConfig.getDigestPeriod())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_NOTIFYCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVATE_DIGESTPERIOD_ACTIVATE_2);

			sb.append(_FINDER_COLUMN_ACTIVATE_DIGESTPERIOD_DIGESTPERIOD_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotifyConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(activate);

				queryPos.add(digestPeriod);

				list = (List<NotifyConfig>)QueryUtil.list(
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
	 * Returns the first notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig findByactivate_digestPeriod_First(
			boolean activate, int digestPeriod,
			OrderByComparator<NotifyConfig> orderByComparator)
		throws NoSuchNotifyConfigException {

		NotifyConfig notifyConfig = fetchByactivate_digestPeriod_First(
			activate, digestPeriod, orderByComparator);

		if (notifyConfig != null) {
			return notifyConfig;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("activate=");
		sb.append(activate);

		sb.append(", digestPeriod=");
		sb.append(digestPeriod);

		sb.append("}");

		throw new NoSuchNotifyConfigException(sb.toString());
	}

	/**
	 * Returns the first notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig fetchByactivate_digestPeriod_First(
		boolean activate, int digestPeriod,
		OrderByComparator<NotifyConfig> orderByComparator) {

		List<NotifyConfig> list = findByactivate_digestPeriod(
			activate, digestPeriod, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config
	 * @throws NoSuchNotifyConfigException if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig findByactivate_digestPeriod_Last(
			boolean activate, int digestPeriod,
			OrderByComparator<NotifyConfig> orderByComparator)
		throws NoSuchNotifyConfigException {

		NotifyConfig notifyConfig = fetchByactivate_digestPeriod_Last(
			activate, digestPeriod, orderByComparator);

		if (notifyConfig != null) {
			return notifyConfig;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("activate=");
		sb.append(activate);

		sb.append(", digestPeriod=");
		sb.append(digestPeriod);

		sb.append("}");

		throw new NoSuchNotifyConfigException(sb.toString());
	}

	/**
	 * Returns the last notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notify config, or <code>null</code> if a matching notify config could not be found
	 */
	@Override
	public NotifyConfig fetchByactivate_digestPeriod_Last(
		boolean activate, int digestPeriod,
		OrderByComparator<NotifyConfig> orderByComparator) {

		int count = countByactivate_digestPeriod(activate, digestPeriod);

		if (count == 0) {
			return null;
		}

		List<NotifyConfig> list = findByactivate_digestPeriod(
			activate, digestPeriod, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notify configs before and after the current notify config in the ordered set where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param notifyConfigId the primary key of the current notify config
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notify config
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	@Override
	public NotifyConfig[] findByactivate_digestPeriod_PrevAndNext(
			long notifyConfigId, boolean activate, int digestPeriod,
			OrderByComparator<NotifyConfig> orderByComparator)
		throws NoSuchNotifyConfigException {

		NotifyConfig notifyConfig = findByPrimaryKey(notifyConfigId);

		Session session = null;

		try {
			session = openSession();

			NotifyConfig[] array = new NotifyConfigImpl[3];

			array[0] = getByactivate_digestPeriod_PrevAndNext(
				session, notifyConfig, activate, digestPeriod,
				orderByComparator, true);

			array[1] = notifyConfig;

			array[2] = getByactivate_digestPeriod_PrevAndNext(
				session, notifyConfig, activate, digestPeriod,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotifyConfig getByactivate_digestPeriod_PrevAndNext(
		Session session, NotifyConfig notifyConfig, boolean activate,
		int digestPeriod, OrderByComparator<NotifyConfig> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_NOTIFYCONFIG_WHERE);

		sb.append(_FINDER_COLUMN_ACTIVATE_DIGESTPERIOD_ACTIVATE_2);

		sb.append(_FINDER_COLUMN_ACTIVATE_DIGESTPERIOD_DIGESTPERIOD_2);

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
			sb.append(NotifyConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(activate);

		queryPos.add(digestPeriod);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(notifyConfig)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotifyConfig> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notify configs where activate = &#63; and digestPeriod = &#63; from the database.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 */
	@Override
	public void removeByactivate_digestPeriod(
		boolean activate, int digestPeriod) {

		for (NotifyConfig notifyConfig :
				findByactivate_digestPeriod(
					activate, digestPeriod, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(notifyConfig);
		}
	}

	/**
	 * Returns the number of notify configs where activate = &#63; and digestPeriod = &#63;.
	 *
	 * @param activate the activate
	 * @param digestPeriod the digest period
	 * @return the number of matching notify configs
	 */
	@Override
	public int countByactivate_digestPeriod(
		boolean activate, int digestPeriod) {

		FinderPath finderPath = _finderPathCountByactivate_digestPeriod;

		Object[] finderArgs = new Object[] {activate, digestPeriod};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_NOTIFYCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVATE_DIGESTPERIOD_ACTIVATE_2);

			sb.append(_FINDER_COLUMN_ACTIVATE_DIGESTPERIOD_DIGESTPERIOD_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(activate);

				queryPos.add(digestPeriod);

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
		_FINDER_COLUMN_ACTIVATE_DIGESTPERIOD_ACTIVATE_2 =
			"notifyConfig.activate = ? AND ";

	private static final String
		_FINDER_COLUMN_ACTIVATE_DIGESTPERIOD_DIGESTPERIOD_2 =
			"notifyConfig.digestPeriod = ?";

	public NotifyConfigPersistenceImpl() {
		setModelClass(NotifyConfig.class);

		setModelImplClass(NotifyConfigImpl.class);
		setModelPKClass(long.class);

		setTable(NotifyConfigTable.INSTANCE);
	}

	/**
	 * Caches the notify config in the entity cache if it is enabled.
	 *
	 * @param notifyConfig the notify config
	 */
	@Override
	public void cacheResult(NotifyConfig notifyConfig) {
		entityCache.putResult(
			NotifyConfigImpl.class, notifyConfig.getPrimaryKey(), notifyConfig);

		finderCache.putResult(
			_finderPathFetchByuserId, new Object[] {notifyConfig.getUserId()},
			notifyConfig);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the notify configs in the entity cache if it is enabled.
	 *
	 * @param notifyConfigs the notify configs
	 */
	@Override
	public void cacheResult(List<NotifyConfig> notifyConfigs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (notifyConfigs.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (NotifyConfig notifyConfig : notifyConfigs) {
			if (entityCache.getResult(
					NotifyConfigImpl.class, notifyConfig.getPrimaryKey()) ==
						null) {

				cacheResult(notifyConfig);
			}
		}
	}

	/**
	 * Clears the cache for all notify configs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NotifyConfigImpl.class);

		finderCache.clearCache(NotifyConfigImpl.class);
	}

	/**
	 * Clears the cache for the notify config.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NotifyConfig notifyConfig) {
		entityCache.removeResult(NotifyConfigImpl.class, notifyConfig);
	}

	@Override
	public void clearCache(List<NotifyConfig> notifyConfigs) {
		for (NotifyConfig notifyConfig : notifyConfigs) {
			entityCache.removeResult(NotifyConfigImpl.class, notifyConfig);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(NotifyConfigImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(NotifyConfigImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		NotifyConfigModelImpl notifyConfigModelImpl) {

		Object[] args = new Object[] {notifyConfigModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByuserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByuserId, args, notifyConfigModelImpl);
	}

	/**
	 * Creates a new notify config with the primary key. Does not add the notify config to the database.
	 *
	 * @param notifyConfigId the primary key for the new notify config
	 * @return the new notify config
	 */
	@Override
	public NotifyConfig create(long notifyConfigId) {
		NotifyConfig notifyConfig = new NotifyConfigImpl();

		notifyConfig.setNew(true);
		notifyConfig.setPrimaryKey(notifyConfigId);

		return notifyConfig;
	}

	/**
	 * Removes the notify config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config that was removed
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	@Override
	public NotifyConfig remove(long notifyConfigId)
		throws NoSuchNotifyConfigException {

		return remove((Serializable)notifyConfigId);
	}

	/**
	 * Removes the notify config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the notify config
	 * @return the notify config that was removed
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	@Override
	public NotifyConfig remove(Serializable primaryKey)
		throws NoSuchNotifyConfigException {

		Session session = null;

		try {
			session = openSession();

			NotifyConfig notifyConfig = (NotifyConfig)session.get(
				NotifyConfigImpl.class, primaryKey);

			if (notifyConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotifyConfigException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(notifyConfig);
		}
		catch (NoSuchNotifyConfigException noSuchEntityException) {
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
	protected NotifyConfig removeImpl(NotifyConfig notifyConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(notifyConfig)) {
				notifyConfig = (NotifyConfig)session.get(
					NotifyConfigImpl.class, notifyConfig.getPrimaryKeyObj());
			}

			if (notifyConfig != null) {
				session.delete(notifyConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (notifyConfig != null) {
			clearCache(notifyConfig);
		}

		return notifyConfig;
	}

	@Override
	public NotifyConfig updateImpl(NotifyConfig notifyConfig) {
		boolean isNew = notifyConfig.isNew();

		if (!(notifyConfig instanceof NotifyConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(notifyConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					notifyConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in notifyConfig proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom NotifyConfig implementation " +
					notifyConfig.getClass());
		}

		NotifyConfigModelImpl notifyConfigModelImpl =
			(NotifyConfigModelImpl)notifyConfig;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(notifyConfig);
			}
			else {
				notifyConfig = (NotifyConfig)session.merge(notifyConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			NotifyConfigImpl.class, notifyConfigModelImpl, false, true);

		cacheUniqueFindersCache(notifyConfigModelImpl);

		if (isNew) {
			notifyConfig.setNew(false);
		}

		notifyConfig.resetOriginalValues();

		return notifyConfig;
	}

	/**
	 * Returns the notify config with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the notify config
	 * @return the notify config
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	@Override
	public NotifyConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotifyConfigException {

		NotifyConfig notifyConfig = fetchByPrimaryKey(primaryKey);

		if (notifyConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotifyConfigException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return notifyConfig;
	}

	/**
	 * Returns the notify config with the primary key or throws a <code>NoSuchNotifyConfigException</code> if it could not be found.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config
	 * @throws NoSuchNotifyConfigException if a notify config with the primary key could not be found
	 */
	@Override
	public NotifyConfig findByPrimaryKey(long notifyConfigId)
		throws NoSuchNotifyConfigException {

		return findByPrimaryKey((Serializable)notifyConfigId);
	}

	/**
	 * Returns the notify config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config, or <code>null</code> if a notify config with the primary key could not be found
	 */
	@Override
	public NotifyConfig fetchByPrimaryKey(long notifyConfigId) {
		return fetchByPrimaryKey((Serializable)notifyConfigId);
	}

	/**
	 * Returns all the notify configs.
	 *
	 * @return the notify configs
	 */
	@Override
	public List<NotifyConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notify configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @return the range of notify configs
	 */
	@Override
	public List<NotifyConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the notify configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notify configs
	 */
	@Override
	public List<NotifyConfig> findAll(
		int start, int end, OrderByComparator<NotifyConfig> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notify configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of notify configs
	 */
	@Override
	public List<NotifyConfig> findAll(
		int start, int end, OrderByComparator<NotifyConfig> orderByComparator,
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

		List<NotifyConfig> list = null;

		if (useFinderCache) {
			list = (List<NotifyConfig>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NOTIFYCONFIG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NOTIFYCONFIG;

				sql = sql.concat(NotifyConfigModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<NotifyConfig>)QueryUtil.list(
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
	 * Removes all the notify configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NotifyConfig notifyConfig : findAll()) {
			remove(notifyConfig);
		}
	}

	/**
	 * Returns the number of notify configs.
	 *
	 * @return the number of notify configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_NOTIFYCONFIG);

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
		return "notifyConfigId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NOTIFYCONFIG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NotifyConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the notify config persistence.
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

		_finderPathFetchByuserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_finderPathWithPaginationFindByactivate = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByactivate",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"activate"}, true);

		_finderPathWithoutPaginationFindByactivate = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByactivate",
			new String[] {Boolean.class.getName()}, new String[] {"activate"},
			true);

		_finderPathCountByactivate = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByactivate",
			new String[] {Boolean.class.getName()}, new String[] {"activate"},
			false);

		_finderPathWithPaginationFindByactivate_digestPeriod = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByactivate_digestPeriod",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"activate", "digestPeriod"}, true);

		_finderPathWithoutPaginationFindByactivate_digestPeriod =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByactivate_digestPeriod",
				new String[] {Boolean.class.getName(), Integer.class.getName()},
				new String[] {"activate", "digestPeriod"}, true);

		_finderPathCountByactivate_digestPeriod = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByactivate_digestPeriod",
			new String[] {Boolean.class.getName(), Integer.class.getName()},
			new String[] {"activate", "digestPeriod"}, false);

		_setNotifyConfigUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setNotifyConfigUtilPersistence(null);

		entityCache.removeCache(NotifyConfigImpl.class.getName());
	}

	private void _setNotifyConfigUtilPersistence(
		NotifyConfigPersistence notifyConfigPersistence) {

		try {
			Field field = NotifyConfigUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, notifyConfigPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = PreferencePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = PreferencePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = PreferencePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_NOTIFYCONFIG =
		"SELECT notifyConfig FROM NotifyConfig notifyConfig";

	private static final String _SQL_SELECT_NOTIFYCONFIG_WHERE =
		"SELECT notifyConfig FROM NotifyConfig notifyConfig WHERE ";

	private static final String _SQL_COUNT_NOTIFYCONFIG =
		"SELECT COUNT(notifyConfig) FROM NotifyConfig notifyConfig";

	private static final String _SQL_COUNT_NOTIFYCONFIG_WHERE =
		"SELECT COUNT(notifyConfig) FROM NotifyConfig notifyConfig WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "notifyConfig.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No NotifyConfig exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No NotifyConfig exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NotifyConfigPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}