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

package com.weprode.nero.group.service.persistence.impl;

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

import com.weprode.nero.group.exception.NoSuchCommunityInfosException;
import com.weprode.nero.group.model.CommunityInfos;
import com.weprode.nero.group.model.CommunityInfosTable;
import com.weprode.nero.group.model.impl.CommunityInfosImpl;
import com.weprode.nero.group.model.impl.CommunityInfosModelImpl;
import com.weprode.nero.group.service.persistence.CommunityInfosPersistence;
import com.weprode.nero.group.service.persistence.CommunityInfosUtil;
import com.weprode.nero.group.service.persistence.impl.constants.GroupPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the community infos service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {CommunityInfosPersistence.class, BasePersistence.class})
public class CommunityInfosPersistenceImpl
	extends BasePersistenceImpl<CommunityInfos>
	implements CommunityInfosPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommunityInfosUtil</code> to access the community infos persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommunityInfosImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchBygroupId;
	private FinderPath _finderPathCountBygroupId;

	/**
	 * Returns the community infos where groupId = &#63; or throws a <code>NoSuchCommunityInfosException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @return the matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos findBygroupId(long groupId)
		throws NoSuchCommunityInfosException {

		CommunityInfos communityInfos = fetchBygroupId(groupId);

		if (communityInfos == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCommunityInfosException(sb.toString());
		}

		return communityInfos;
	}

	/**
	 * Returns the community infos where groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @return the matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos fetchBygroupId(long groupId) {
		return fetchBygroupId(groupId, true);
	}

	/**
	 * Returns the community infos where groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos fetchBygroupId(long groupId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBygroupId, finderArgs);
		}

		if (result instanceof CommunityInfos) {
			CommunityInfos communityInfos = (CommunityInfos)result;

			if (groupId != communityInfos.getGroupId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMMUNITYINFOS_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				List<CommunityInfos> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBygroupId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {groupId};
							}

							_log.warn(
								"CommunityInfosPersistenceImpl.fetchBygroupId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommunityInfos communityInfos = list.get(0);

					result = communityInfos;

					cacheResult(communityInfos);
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
			return (CommunityInfos)result;
		}
	}

	/**
	 * Removes the community infos where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @return the community infos that was removed
	 */
	@Override
	public CommunityInfos removeBygroupId(long groupId)
		throws NoSuchCommunityInfosException {

		CommunityInfos communityInfos = findBygroupId(groupId);

		return remove(communityInfos);
	}

	/**
	 * Returns the number of community infoses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching community infoses
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = _finderPathCountBygroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMUNITYINFOS_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"communityInfos.groupId = ?";

	private FinderPath _finderPathWithPaginationFindBycreatorId_status;
	private FinderPath _finderPathWithoutPaginationFindBycreatorId_status;
	private FinderPath _finderPathCountBycreatorId_status;

	/**
	 * Returns all the community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @return the matching community infoses
	 */
	@Override
	public List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status) {

		return findBycreatorId_status(
			creatorId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @return the range of matching community infoses
	 */
	@Override
	public List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status, int start, int end) {

		return findBycreatorId_status(creatorId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching community infoses
	 */
	@Override
	public List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status, int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return findBycreatorId_status(
			creatorId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching community infoses
	 */
	@Override
	public List<CommunityInfos> findBycreatorId_status(
		long creatorId, int status, int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBycreatorId_status;
				finderArgs = new Object[] {creatorId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBycreatorId_status;
			finderArgs = new Object[] {
				creatorId, status, start, end, orderByComparator
			};
		}

		List<CommunityInfos> list = null;

		if (useFinderCache) {
			list = (List<CommunityInfos>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommunityInfos communityInfos : list) {
					if ((creatorId != communityInfos.getCreatorId()) ||
						(status != communityInfos.getStatus())) {

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

			sb.append(_SQL_SELECT_COMMUNITYINFOS_WHERE);

			sb.append(_FINDER_COLUMN_CREATORID_STATUS_CREATORID_2);

			sb.append(_FINDER_COLUMN_CREATORID_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommunityInfosModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(creatorId);

				queryPos.add(status);

				list = (List<CommunityInfos>)QueryUtil.list(
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
	 * Returns the first community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos findBycreatorId_status_First(
			long creatorId, int status,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws NoSuchCommunityInfosException {

		CommunityInfos communityInfos = fetchBycreatorId_status_First(
			creatorId, status, orderByComparator);

		if (communityInfos != null) {
			return communityInfos;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("creatorId=");
		sb.append(creatorId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchCommunityInfosException(sb.toString());
	}

	/**
	 * Returns the first community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos fetchBycreatorId_status_First(
		long creatorId, int status,
		OrderByComparator<CommunityInfos> orderByComparator) {

		List<CommunityInfos> list = findBycreatorId_status(
			creatorId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos findBycreatorId_status_Last(
			long creatorId, int status,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws NoSuchCommunityInfosException {

		CommunityInfos communityInfos = fetchBycreatorId_status_Last(
			creatorId, status, orderByComparator);

		if (communityInfos != null) {
			return communityInfos;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("creatorId=");
		sb.append(creatorId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchCommunityInfosException(sb.toString());
	}

	/**
	 * Returns the last community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos fetchBycreatorId_status_Last(
		long creatorId, int status,
		OrderByComparator<CommunityInfos> orderByComparator) {

		int count = countBycreatorId_status(creatorId, status);

		if (count == 0) {
			return null;
		}

		List<CommunityInfos> list = findBycreatorId_status(
			creatorId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the community infoses before and after the current community infos in the ordered set where creatorId = &#63; and status = &#63;.
	 *
	 * @param communityInfosId the primary key of the current community infos
	 * @param creatorId the creator ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next community infos
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	@Override
	public CommunityInfos[] findBycreatorId_status_PrevAndNext(
			long communityInfosId, long creatorId, int status,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws NoSuchCommunityInfosException {

		CommunityInfos communityInfos = findByPrimaryKey(communityInfosId);

		Session session = null;

		try {
			session = openSession();

			CommunityInfos[] array = new CommunityInfosImpl[3];

			array[0] = getBycreatorId_status_PrevAndNext(
				session, communityInfos, creatorId, status, orderByComparator,
				true);

			array[1] = communityInfos;

			array[2] = getBycreatorId_status_PrevAndNext(
				session, communityInfos, creatorId, status, orderByComparator,
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

	protected CommunityInfos getBycreatorId_status_PrevAndNext(
		Session session, CommunityInfos communityInfos, long creatorId,
		int status, OrderByComparator<CommunityInfos> orderByComparator,
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

		sb.append(_SQL_SELECT_COMMUNITYINFOS_WHERE);

		sb.append(_FINDER_COLUMN_CREATORID_STATUS_CREATORID_2);

		sb.append(_FINDER_COLUMN_CREATORID_STATUS_STATUS_2);

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
			sb.append(CommunityInfosModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(creatorId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						communityInfos)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommunityInfos> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the community infoses where creatorId = &#63; and status = &#63; from the database.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 */
	@Override
	public void removeBycreatorId_status(long creatorId, int status) {
		for (CommunityInfos communityInfos :
				findBycreatorId_status(
					creatorId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(communityInfos);
		}
	}

	/**
	 * Returns the number of community infoses where creatorId = &#63; and status = &#63;.
	 *
	 * @param creatorId the creator ID
	 * @param status the status
	 * @return the number of matching community infoses
	 */
	@Override
	public int countBycreatorId_status(long creatorId, int status) {
		FinderPath finderPath = _finderPathCountBycreatorId_status;

		Object[] finderArgs = new Object[] {creatorId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMUNITYINFOS_WHERE);

			sb.append(_FINDER_COLUMN_CREATORID_STATUS_CREATORID_2);

			sb.append(_FINDER_COLUMN_CREATORID_STATUS_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(creatorId);

				queryPos.add(status);

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

	private static final String _FINDER_COLUMN_CREATORID_STATUS_CREATORID_2 =
		"communityInfos.creatorId = ? AND ";

	private static final String _FINDER_COLUMN_CREATORID_STATUS_STATUS_2 =
		"communityInfos.status = ?";

	private FinderPath _finderPathWithPaginationFindByexpirationDate;
	private FinderPath _finderPathWithoutPaginationFindByexpirationDate;
	private FinderPath _finderPathCountByexpirationDate;

	/**
	 * Returns all the community infoses where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the matching community infoses
	 */
	@Override
	public List<CommunityInfos> findByexpirationDate(Date expirationDate) {
		return findByexpirationDate(
			expirationDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the community infoses where expirationDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @return the range of matching community infoses
	 */
	@Override
	public List<CommunityInfos> findByexpirationDate(
		Date expirationDate, int start, int end) {

		return findByexpirationDate(expirationDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the community infoses where expirationDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching community infoses
	 */
	@Override
	public List<CommunityInfos> findByexpirationDate(
		Date expirationDate, int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return findByexpirationDate(
			expirationDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the community infoses where expirationDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching community infoses
	 */
	@Override
	public List<CommunityInfos> findByexpirationDate(
		Date expirationDate, int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByexpirationDate;
				finderArgs = new Object[] {_getTime(expirationDate)};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByexpirationDate;
			finderArgs = new Object[] {
				_getTime(expirationDate), start, end, orderByComparator
			};
		}

		List<CommunityInfos> list = null;

		if (useFinderCache) {
			list = (List<CommunityInfos>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommunityInfos communityInfos : list) {
					if (!Objects.equals(
							expirationDate,
							communityInfos.getExpirationDate())) {

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

			sb.append(_SQL_SELECT_COMMUNITYINFOS_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				sb.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				sb.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommunityInfosModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindExpirationDate) {
					queryPos.add(new Timestamp(expirationDate.getTime()));
				}

				list = (List<CommunityInfos>)QueryUtil.list(
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
	 * Returns the first community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos findByexpirationDate_First(
			Date expirationDate,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws NoSuchCommunityInfosException {

		CommunityInfos communityInfos = fetchByexpirationDate_First(
			expirationDate, orderByComparator);

		if (communityInfos != null) {
			return communityInfos;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("expirationDate=");
		sb.append(expirationDate);

		sb.append("}");

		throw new NoSuchCommunityInfosException(sb.toString());
	}

	/**
	 * Returns the first community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos fetchByexpirationDate_First(
		Date expirationDate,
		OrderByComparator<CommunityInfos> orderByComparator) {

		List<CommunityInfos> list = findByexpirationDate(
			expirationDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos
	 * @throws NoSuchCommunityInfosException if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos findByexpirationDate_Last(
			Date expirationDate,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws NoSuchCommunityInfosException {

		CommunityInfos communityInfos = fetchByexpirationDate_Last(
			expirationDate, orderByComparator);

		if (communityInfos != null) {
			return communityInfos;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("expirationDate=");
		sb.append(expirationDate);

		sb.append("}");

		throw new NoSuchCommunityInfosException(sb.toString());
	}

	/**
	 * Returns the last community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching community infos, or <code>null</code> if a matching community infos could not be found
	 */
	@Override
	public CommunityInfos fetchByexpirationDate_Last(
		Date expirationDate,
		OrderByComparator<CommunityInfos> orderByComparator) {

		int count = countByexpirationDate(expirationDate);

		if (count == 0) {
			return null;
		}

		List<CommunityInfos> list = findByexpirationDate(
			expirationDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the community infoses before and after the current community infos in the ordered set where expirationDate = &#63;.
	 *
	 * @param communityInfosId the primary key of the current community infos
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next community infos
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	@Override
	public CommunityInfos[] findByexpirationDate_PrevAndNext(
			long communityInfosId, Date expirationDate,
			OrderByComparator<CommunityInfos> orderByComparator)
		throws NoSuchCommunityInfosException {

		CommunityInfos communityInfos = findByPrimaryKey(communityInfosId);

		Session session = null;

		try {
			session = openSession();

			CommunityInfos[] array = new CommunityInfosImpl[3];

			array[0] = getByexpirationDate_PrevAndNext(
				session, communityInfos, expirationDate, orderByComparator,
				true);

			array[1] = communityInfos;

			array[2] = getByexpirationDate_PrevAndNext(
				session, communityInfos, expirationDate, orderByComparator,
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

	protected CommunityInfos getByexpirationDate_PrevAndNext(
		Session session, CommunityInfos communityInfos, Date expirationDate,
		OrderByComparator<CommunityInfos> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COMMUNITYINFOS_WHERE);

		boolean bindExpirationDate = false;

		if (expirationDate == null) {
			sb.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1);
		}
		else {
			bindExpirationDate = true;

			sb.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2);
		}

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
			sb.append(CommunityInfosModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindExpirationDate) {
			queryPos.add(new Timestamp(expirationDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						communityInfos)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommunityInfos> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the community infoses where expirationDate = &#63; from the database.
	 *
	 * @param expirationDate the expiration date
	 */
	@Override
	public void removeByexpirationDate(Date expirationDate) {
		for (CommunityInfos communityInfos :
				findByexpirationDate(
					expirationDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(communityInfos);
		}
	}

	/**
	 * Returns the number of community infoses where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the number of matching community infoses
	 */
	@Override
	public int countByexpirationDate(Date expirationDate) {
		FinderPath finderPath = _finderPathCountByexpirationDate;

		Object[] finderArgs = new Object[] {_getTime(expirationDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMUNITYINFOS_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				sb.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				sb.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindExpirationDate) {
					queryPos.add(new Timestamp(expirationDate.getTime()));
				}

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

	private static final String _FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1 =
		"communityInfos.expirationDate IS NULL";

	private static final String _FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2 =
		"communityInfos.expirationDate = ?";

	public CommunityInfosPersistenceImpl() {
		setModelClass(CommunityInfos.class);

		setModelImplClass(CommunityInfosImpl.class);
		setModelPKClass(long.class);

		setTable(CommunityInfosTable.INSTANCE);
	}

	/**
	 * Caches the community infos in the entity cache if it is enabled.
	 *
	 * @param communityInfos the community infos
	 */
	@Override
	public void cacheResult(CommunityInfos communityInfos) {
		entityCache.putResult(
			CommunityInfosImpl.class, communityInfos.getPrimaryKey(),
			communityInfos);

		finderCache.putResult(
			_finderPathFetchBygroupId,
			new Object[] {communityInfos.getGroupId()}, communityInfos);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the community infoses in the entity cache if it is enabled.
	 *
	 * @param communityInfoses the community infoses
	 */
	@Override
	public void cacheResult(List<CommunityInfos> communityInfoses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (communityInfoses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CommunityInfos communityInfos : communityInfoses) {
			if (entityCache.getResult(
					CommunityInfosImpl.class, communityInfos.getPrimaryKey()) ==
						null) {

				cacheResult(communityInfos);
			}
		}
	}

	/**
	 * Clears the cache for all community infoses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommunityInfosImpl.class);

		finderCache.clearCache(CommunityInfosImpl.class);
	}

	/**
	 * Clears the cache for the community infos.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommunityInfos communityInfos) {
		entityCache.removeResult(CommunityInfosImpl.class, communityInfos);
	}

	@Override
	public void clearCache(List<CommunityInfos> communityInfoses) {
		for (CommunityInfos communityInfos : communityInfoses) {
			entityCache.removeResult(CommunityInfosImpl.class, communityInfos);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CommunityInfosImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CommunityInfosImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CommunityInfosModelImpl communityInfosModelImpl) {

		Object[] args = new Object[] {communityInfosModelImpl.getGroupId()};

		finderCache.putResult(_finderPathCountBygroupId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBygroupId, args, communityInfosModelImpl);
	}

	/**
	 * Creates a new community infos with the primary key. Does not add the community infos to the database.
	 *
	 * @param communityInfosId the primary key for the new community infos
	 * @return the new community infos
	 */
	@Override
	public CommunityInfos create(long communityInfosId) {
		CommunityInfos communityInfos = new CommunityInfosImpl();

		communityInfos.setNew(true);
		communityInfos.setPrimaryKey(communityInfosId);

		return communityInfos;
	}

	/**
	 * Removes the community infos with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos that was removed
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	@Override
	public CommunityInfos remove(long communityInfosId)
		throws NoSuchCommunityInfosException {

		return remove((Serializable)communityInfosId);
	}

	/**
	 * Removes the community infos with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the community infos
	 * @return the community infos that was removed
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	@Override
	public CommunityInfos remove(Serializable primaryKey)
		throws NoSuchCommunityInfosException {

		Session session = null;

		try {
			session = openSession();

			CommunityInfos communityInfos = (CommunityInfos)session.get(
				CommunityInfosImpl.class, primaryKey);

			if (communityInfos == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommunityInfosException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(communityInfos);
		}
		catch (NoSuchCommunityInfosException noSuchEntityException) {
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
	protected CommunityInfos removeImpl(CommunityInfos communityInfos) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(communityInfos)) {
				communityInfos = (CommunityInfos)session.get(
					CommunityInfosImpl.class,
					communityInfos.getPrimaryKeyObj());
			}

			if (communityInfos != null) {
				session.delete(communityInfos);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (communityInfos != null) {
			clearCache(communityInfos);
		}

		return communityInfos;
	}

	@Override
	public CommunityInfos updateImpl(CommunityInfos communityInfos) {
		boolean isNew = communityInfos.isNew();

		if (!(communityInfos instanceof CommunityInfosModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(communityInfos.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					communityInfos);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in communityInfos proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommunityInfos implementation " +
					communityInfos.getClass());
		}

		CommunityInfosModelImpl communityInfosModelImpl =
			(CommunityInfosModelImpl)communityInfos;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(communityInfos);
			}
			else {
				communityInfos = (CommunityInfos)session.merge(communityInfos);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CommunityInfosImpl.class, communityInfosModelImpl, false, true);

		cacheUniqueFindersCache(communityInfosModelImpl);

		if (isNew) {
			communityInfos.setNew(false);
		}

		communityInfos.resetOriginalValues();

		return communityInfos;
	}

	/**
	 * Returns the community infos with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the community infos
	 * @return the community infos
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	@Override
	public CommunityInfos findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommunityInfosException {

		CommunityInfos communityInfos = fetchByPrimaryKey(primaryKey);

		if (communityInfos == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommunityInfosException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return communityInfos;
	}

	/**
	 * Returns the community infos with the primary key or throws a <code>NoSuchCommunityInfosException</code> if it could not be found.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos
	 * @throws NoSuchCommunityInfosException if a community infos with the primary key could not be found
	 */
	@Override
	public CommunityInfos findByPrimaryKey(long communityInfosId)
		throws NoSuchCommunityInfosException {

		return findByPrimaryKey((Serializable)communityInfosId);
	}

	/**
	 * Returns the community infos with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos, or <code>null</code> if a community infos with the primary key could not be found
	 */
	@Override
	public CommunityInfos fetchByPrimaryKey(long communityInfosId) {
		return fetchByPrimaryKey((Serializable)communityInfosId);
	}

	/**
	 * Returns all the community infoses.
	 *
	 * @return the community infoses
	 */
	@Override
	public List<CommunityInfos> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the community infoses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @return the range of community infoses
	 */
	@Override
	public List<CommunityInfos> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the community infoses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of community infoses
	 */
	@Override
	public List<CommunityInfos> findAll(
		int start, int end,
		OrderByComparator<CommunityInfos> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the community infoses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of community infoses
	 */
	@Override
	public List<CommunityInfos> findAll(
		int start, int end, OrderByComparator<CommunityInfos> orderByComparator,
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

		List<CommunityInfos> list = null;

		if (useFinderCache) {
			list = (List<CommunityInfos>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMUNITYINFOS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMUNITYINFOS;

				sql = sql.concat(CommunityInfosModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CommunityInfos>)QueryUtil.list(
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
	 * Removes all the community infoses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommunityInfos communityInfos : findAll()) {
			remove(communityInfos);
		}
	}

	/**
	 * Returns the number of community infoses.
	 *
	 * @return the number of community infoses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COMMUNITYINFOS);

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
		return "communityInfosId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMUNITYINFOS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommunityInfosModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the community infos persistence.
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

		_finderPathFetchBygroupId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBygroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindBycreatorId_status = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBycreatorId_status",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"creatorId", "status"}, true);

		_finderPathWithoutPaginationFindBycreatorId_status = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBycreatorId_status",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"creatorId", "status"}, true);

		_finderPathCountBycreatorId_status = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBycreatorId_status",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"creatorId", "status"}, false);

		_finderPathWithPaginationFindByexpirationDate = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByexpirationDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"expirationDate"}, true);

		_finderPathWithoutPaginationFindByexpirationDate = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByexpirationDate",
			new String[] {Date.class.getName()},
			new String[] {"expirationDate"}, true);

		_finderPathCountByexpirationDate = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByexpirationDate",
			new String[] {Date.class.getName()},
			new String[] {"expirationDate"}, false);

		_setCommunityInfosUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setCommunityInfosUtilPersistence(null);

		entityCache.removeCache(CommunityInfosImpl.class.getName());
	}

	private void _setCommunityInfosUtilPersistence(
		CommunityInfosPersistence communityInfosPersistence) {

		try {
			Field field = CommunityInfosUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, communityInfosPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = GroupPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = GroupPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = GroupPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_COMMUNITYINFOS =
		"SELECT communityInfos FROM CommunityInfos communityInfos";

	private static final String _SQL_SELECT_COMMUNITYINFOS_WHERE =
		"SELECT communityInfos FROM CommunityInfos communityInfos WHERE ";

	private static final String _SQL_COUNT_COMMUNITYINFOS =
		"SELECT COUNT(communityInfos) FROM CommunityInfos communityInfos";

	private static final String _SQL_COUNT_COMMUNITYINFOS_WHERE =
		"SELECT COUNT(communityInfos) FROM CommunityInfos communityInfos WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "communityInfos.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommunityInfos exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommunityInfos exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommunityInfosPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private CommunityInfosModelArgumentsResolver
		_communityInfosModelArgumentsResolver;

}