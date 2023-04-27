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
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.progression.exception.NoSuchItemAssignmentException;
import com.weprode.nero.progression.model.ItemAssignment;
import com.weprode.nero.progression.model.ItemAssignmentTable;
import com.weprode.nero.progression.model.impl.ItemAssignmentImpl;
import com.weprode.nero.progression.model.impl.ItemAssignmentModelImpl;
import com.weprode.nero.progression.service.persistence.ItemAssignmentPK;
import com.weprode.nero.progression.service.persistence.ItemAssignmentPersistence;
import com.weprode.nero.progression.service.persistence.ItemAssignmentUtil;
import com.weprode.nero.progression.service.persistence.impl.constants.ProgressionPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the item assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ItemAssignmentPersistence.class, BasePersistence.class})
public class ItemAssignmentPersistenceImpl
	extends BasePersistenceImpl<ItemAssignment>
	implements ItemAssignmentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ItemAssignmentUtil</code> to access the item assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ItemAssignmentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByprogressionItemId_sessionId;
	private FinderPath _finderPathCountByprogressionItemId_sessionId;

	/**
	 * Returns the item assignment where progressionItemId = &#63; and sessionId = &#63; or throws a <code>NoSuchItemAssignmentException</code> if it could not be found.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment findByprogressionItemId_sessionId(
			long progressionItemId, long sessionId)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = fetchByprogressionItemId_sessionId(
			progressionItemId, sessionId);

		if (itemAssignment == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("progressionItemId=");
			sb.append(progressionItemId);

			sb.append(", sessionId=");
			sb.append(sessionId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchItemAssignmentException(sb.toString());
		}

		return itemAssignment;
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and sessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchByprogressionItemId_sessionId(
		long progressionItemId, long sessionId) {

		return fetchByprogressionItemId_sessionId(
			progressionItemId, sessionId, true);
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and sessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchByprogressionItemId_sessionId(
		long progressionItemId, long sessionId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {progressionItemId, sessionId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByprogressionItemId_sessionId, finderArgs);
		}

		if (result instanceof ItemAssignment) {
			ItemAssignment itemAssignment = (ItemAssignment)result;

			if ((progressionItemId != itemAssignment.getProgressionItemId()) ||
				(sessionId != itemAssignment.getSessionId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_ITEMASSIGNMENT_WHERE);

			sb.append(
				_FINDER_COLUMN_PROGRESSIONITEMID_SESSIONID_PROGRESSIONITEMID_2);

			sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_SESSIONID_SESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionItemId);

				queryPos.add(sessionId);

				List<ItemAssignment> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByprogressionItemId_sessionId,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									progressionItemId, sessionId
								};
							}

							_log.warn(
								"ItemAssignmentPersistenceImpl.fetchByprogressionItemId_sessionId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ItemAssignment itemAssignment = list.get(0);

					result = itemAssignment;

					cacheResult(itemAssignment);
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
			return (ItemAssignment)result;
		}
	}

	/**
	 * Removes the item assignment where progressionItemId = &#63; and sessionId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the item assignment that was removed
	 */
	@Override
	public ItemAssignment removeByprogressionItemId_sessionId(
			long progressionItemId, long sessionId)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = findByprogressionItemId_sessionId(
			progressionItemId, sessionId);

		return remove(itemAssignment);
	}

	/**
	 * Returns the number of item assignments where progressionItemId = &#63; and sessionId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the number of matching item assignments
	 */
	@Override
	public int countByprogressionItemId_sessionId(
		long progressionItemId, long sessionId) {

		FinderPath finderPath = _finderPathCountByprogressionItemId_sessionId;

		Object[] finderArgs = new Object[] {progressionItemId, sessionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ITEMASSIGNMENT_WHERE);

			sb.append(
				_FINDER_COLUMN_PROGRESSIONITEMID_SESSIONID_PROGRESSIONITEMID_2);

			sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_SESSIONID_SESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionItemId);

				queryPos.add(sessionId);

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
		_FINDER_COLUMN_PROGRESSIONITEMID_SESSIONID_PROGRESSIONITEMID_2 =
			"itemAssignment.id.progressionItemId = ? AND ";

	private static final String
		_FINDER_COLUMN_PROGRESSIONITEMID_SESSIONID_SESSIONID_2 =
			"itemAssignment.id.sessionId = ?";

	private FinderPath _finderPathFetchByprogressionItemId_homeworkId;
	private FinderPath _finderPathCountByprogressionItemId_homeworkId;

	/**
	 * Returns the item assignment where progressionItemId = &#63; and homeworkId = &#63; or throws a <code>NoSuchItemAssignmentException</code> if it could not be found.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment findByprogressionItemId_homeworkId(
			long progressionItemId, long homeworkId)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = fetchByprogressionItemId_homeworkId(
			progressionItemId, homeworkId);

		if (itemAssignment == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("progressionItemId=");
			sb.append(progressionItemId);

			sb.append(", homeworkId=");
			sb.append(homeworkId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchItemAssignmentException(sb.toString());
		}

		return itemAssignment;
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and homeworkId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchByprogressionItemId_homeworkId(
		long progressionItemId, long homeworkId) {

		return fetchByprogressionItemId_homeworkId(
			progressionItemId, homeworkId, true);
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and homeworkId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchByprogressionItemId_homeworkId(
		long progressionItemId, long homeworkId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {progressionItemId, homeworkId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByprogressionItemId_homeworkId, finderArgs);
		}

		if (result instanceof ItemAssignment) {
			ItemAssignment itemAssignment = (ItemAssignment)result;

			if ((progressionItemId != itemAssignment.getProgressionItemId()) ||
				(homeworkId != itemAssignment.getHomeworkId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_ITEMASSIGNMENT_WHERE);

			sb.append(
				_FINDER_COLUMN_PROGRESSIONITEMID_HOMEWORKID_PROGRESSIONITEMID_2);

			sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_HOMEWORKID_HOMEWORKID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionItemId);

				queryPos.add(homeworkId);

				List<ItemAssignment> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByprogressionItemId_homeworkId,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									progressionItemId, homeworkId
								};
							}

							_log.warn(
								"ItemAssignmentPersistenceImpl.fetchByprogressionItemId_homeworkId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ItemAssignment itemAssignment = list.get(0);

					result = itemAssignment;

					cacheResult(itemAssignment);
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
			return (ItemAssignment)result;
		}
	}

	/**
	 * Removes the item assignment where progressionItemId = &#63; and homeworkId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the item assignment that was removed
	 */
	@Override
	public ItemAssignment removeByprogressionItemId_homeworkId(
			long progressionItemId, long homeworkId)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = findByprogressionItemId_homeworkId(
			progressionItemId, homeworkId);

		return remove(itemAssignment);
	}

	/**
	 * Returns the number of item assignments where progressionItemId = &#63; and homeworkId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the number of matching item assignments
	 */
	@Override
	public int countByprogressionItemId_homeworkId(
		long progressionItemId, long homeworkId) {

		FinderPath finderPath = _finderPathCountByprogressionItemId_homeworkId;

		Object[] finderArgs = new Object[] {progressionItemId, homeworkId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ITEMASSIGNMENT_WHERE);

			sb.append(
				_FINDER_COLUMN_PROGRESSIONITEMID_HOMEWORKID_PROGRESSIONITEMID_2);

			sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_HOMEWORKID_HOMEWORKID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionItemId);

				queryPos.add(homeworkId);

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
		_FINDER_COLUMN_PROGRESSIONITEMID_HOMEWORKID_PROGRESSIONITEMID_2 =
			"itemAssignment.id.progressionItemId = ? AND ";

	private static final String
		_FINDER_COLUMN_PROGRESSIONITEMID_HOMEWORKID_HOMEWORKID_2 =
			"itemAssignment.homeworkId = ?";

	private FinderPath _finderPathWithPaginationFindByprogressionItemId;
	private FinderPath _finderPathWithoutPaginationFindByprogressionItemId;
	private FinderPath _finderPathCountByprogressionItemId;

	/**
	 * Returns all the item assignments where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching item assignments
	 */
	@Override
	public List<ItemAssignment> findByprogressionItemId(
		long progressionItemId) {

		return findByprogressionItemId(
			progressionItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item assignments where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of matching item assignments
	 */
	@Override
	public List<ItemAssignment> findByprogressionItemId(
		long progressionItemId, int start, int end) {

		return findByprogressionItemId(progressionItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item assignments where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item assignments
	 */
	@Override
	public List<ItemAssignment> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return findByprogressionItemId(
			progressionItemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item assignments where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item assignments
	 */
	@Override
	public List<ItemAssignment> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator,
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

		List<ItemAssignment> list = null;

		if (useFinderCache) {
			list = (List<ItemAssignment>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ItemAssignment itemAssignment : list) {
					if (progressionItemId !=
							itemAssignment.getProgressionItemId()) {

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

			sb.append(_SQL_SELECT_ITEMASSIGNMENT_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_PROGRESSIONITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ItemAssignmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionItemId);

				list = (List<ItemAssignment>)QueryUtil.list(
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
	 * Returns the first item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment findByprogressionItemId_First(
			long progressionItemId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = fetchByprogressionItemId_First(
			progressionItemId, orderByComparator);

		if (itemAssignment != null) {
			return itemAssignment;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionItemId=");
		sb.append(progressionItemId);

		sb.append("}");

		throw new NoSuchItemAssignmentException(sb.toString());
	}

	/**
	 * Returns the first item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchByprogressionItemId_First(
		long progressionItemId,
		OrderByComparator<ItemAssignment> orderByComparator) {

		List<ItemAssignment> list = findByprogressionItemId(
			progressionItemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment findByprogressionItemId_Last(
			long progressionItemId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = fetchByprogressionItemId_Last(
			progressionItemId, orderByComparator);

		if (itemAssignment != null) {
			return itemAssignment;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionItemId=");
		sb.append(progressionItemId);

		sb.append("}");

		throw new NoSuchItemAssignmentException(sb.toString());
	}

	/**
	 * Returns the last item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchByprogressionItemId_Last(
		long progressionItemId,
		OrderByComparator<ItemAssignment> orderByComparator) {

		int count = countByprogressionItemId(progressionItemId);

		if (count == 0) {
			return null;
		}

		List<ItemAssignment> list = findByprogressionItemId(
			progressionItemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item assignments before and after the current item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param itemAssignmentPK the primary key of the current item assignment
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	@Override
	public ItemAssignment[] findByprogressionItemId_PrevAndNext(
			ItemAssignmentPK itemAssignmentPK, long progressionItemId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = findByPrimaryKey(itemAssignmentPK);

		Session session = null;

		try {
			session = openSession();

			ItemAssignment[] array = new ItemAssignmentImpl[3];

			array[0] = getByprogressionItemId_PrevAndNext(
				session, itemAssignment, progressionItemId, orderByComparator,
				true);

			array[1] = itemAssignment;

			array[2] = getByprogressionItemId_PrevAndNext(
				session, itemAssignment, progressionItemId, orderByComparator,
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

	protected ItemAssignment getByprogressionItemId_PrevAndNext(
		Session session, ItemAssignment itemAssignment, long progressionItemId,
		OrderByComparator<ItemAssignment> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ITEMASSIGNMENT_WHERE);

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
			sb.append(ItemAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(progressionItemId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						itemAssignment)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ItemAssignment> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item assignments where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 */
	@Override
	public void removeByprogressionItemId(long progressionItemId) {
		for (ItemAssignment itemAssignment :
				findByprogressionItemId(
					progressionItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(itemAssignment);
		}
	}

	/**
	 * Returns the number of item assignments where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching item assignments
	 */
	@Override
	public int countByprogressionItemId(long progressionItemId) {
		FinderPath finderPath = _finderPathCountByprogressionItemId;

		Object[] finderArgs = new Object[] {progressionItemId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ITEMASSIGNMENT_WHERE);

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
			"itemAssignment.id.progressionItemId = ?";

	private FinderPath _finderPathWithPaginationFindBysessionId;
	private FinderPath _finderPathWithoutPaginationFindBysessionId;
	private FinderPath _finderPathCountBysessionId;

	/**
	 * Returns all the item assignments where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching item assignments
	 */
	@Override
	public List<ItemAssignment> findBysessionId(long sessionId) {
		return findBysessionId(
			sessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item assignments where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of matching item assignments
	 */
	@Override
	public List<ItemAssignment> findBysessionId(
		long sessionId, int start, int end) {

		return findBysessionId(sessionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item assignments where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item assignments
	 */
	@Override
	public List<ItemAssignment> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return findBysessionId(sessionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item assignments where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item assignments
	 */
	@Override
	public List<ItemAssignment> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBysessionId;
				finderArgs = new Object[] {sessionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBysessionId;
			finderArgs = new Object[] {
				sessionId, start, end, orderByComparator
			};
		}

		List<ItemAssignment> list = null;

		if (useFinderCache) {
			list = (List<ItemAssignment>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ItemAssignment itemAssignment : list) {
					if (sessionId != itemAssignment.getSessionId()) {
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

			sb.append(_SQL_SELECT_ITEMASSIGNMENT_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_SESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ItemAssignmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				list = (List<ItemAssignment>)QueryUtil.list(
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
	 * Returns the first item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment findBysessionId_First(
			long sessionId, OrderByComparator<ItemAssignment> orderByComparator)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = fetchBysessionId_First(
			sessionId, orderByComparator);

		if (itemAssignment != null) {
			return itemAssignment;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append("}");

		throw new NoSuchItemAssignmentException(sb.toString());
	}

	/**
	 * Returns the first item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchBysessionId_First(
		long sessionId, OrderByComparator<ItemAssignment> orderByComparator) {

		List<ItemAssignment> list = findBysessionId(
			sessionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment findBysessionId_Last(
			long sessionId, OrderByComparator<ItemAssignment> orderByComparator)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = fetchBysessionId_Last(
			sessionId, orderByComparator);

		if (itemAssignment != null) {
			return itemAssignment;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sessionId=");
		sb.append(sessionId);

		sb.append("}");

		throw new NoSuchItemAssignmentException(sb.toString());
	}

	/**
	 * Returns the last item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchBysessionId_Last(
		long sessionId, OrderByComparator<ItemAssignment> orderByComparator) {

		int count = countBysessionId(sessionId);

		if (count == 0) {
			return null;
		}

		List<ItemAssignment> list = findBysessionId(
			sessionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item assignments before and after the current item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param itemAssignmentPK the primary key of the current item assignment
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	@Override
	public ItemAssignment[] findBysessionId_PrevAndNext(
			ItemAssignmentPK itemAssignmentPK, long sessionId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = findByPrimaryKey(itemAssignmentPK);

		Session session = null;

		try {
			session = openSession();

			ItemAssignment[] array = new ItemAssignmentImpl[3];

			array[0] = getBysessionId_PrevAndNext(
				session, itemAssignment, sessionId, orderByComparator, true);

			array[1] = itemAssignment;

			array[2] = getBysessionId_PrevAndNext(
				session, itemAssignment, sessionId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemAssignment getBysessionId_PrevAndNext(
		Session session, ItemAssignment itemAssignment, long sessionId,
		OrderByComparator<ItemAssignment> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ITEMASSIGNMENT_WHERE);

		sb.append(_FINDER_COLUMN_SESSIONID_SESSIONID_2);

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
			sb.append(ItemAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(sessionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						itemAssignment)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ItemAssignment> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item assignments where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	@Override
	public void removeBysessionId(long sessionId) {
		for (ItemAssignment itemAssignment :
				findBysessionId(
					sessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(itemAssignment);
		}
	}

	/**
	 * Returns the number of item assignments where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching item assignments
	 */
	@Override
	public int countBysessionId(long sessionId) {
		FinderPath finderPath = _finderPathCountBysessionId;

		Object[] finderArgs = new Object[] {sessionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ITEMASSIGNMENT_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_SESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

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

	private static final String _FINDER_COLUMN_SESSIONID_SESSIONID_2 =
		"itemAssignment.id.sessionId = ?";

	private FinderPath _finderPathWithPaginationFindByhomeworkId;
	private FinderPath _finderPathWithoutPaginationFindByhomeworkId;
	private FinderPath _finderPathCountByhomeworkId;

	/**
	 * Returns all the item assignments where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching item assignments
	 */
	@Override
	public List<ItemAssignment> findByhomeworkId(long homeworkId) {
		return findByhomeworkId(
			homeworkId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item assignments where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of matching item assignments
	 */
	@Override
	public List<ItemAssignment> findByhomeworkId(
		long homeworkId, int start, int end) {

		return findByhomeworkId(homeworkId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item assignments where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item assignments
	 */
	@Override
	public List<ItemAssignment> findByhomeworkId(
		long homeworkId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return findByhomeworkId(
			homeworkId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item assignments where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item assignments
	 */
	@Override
	public List<ItemAssignment> findByhomeworkId(
		long homeworkId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByhomeworkId;
				finderArgs = new Object[] {homeworkId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByhomeworkId;
			finderArgs = new Object[] {
				homeworkId, start, end, orderByComparator
			};
		}

		List<ItemAssignment> list = null;

		if (useFinderCache) {
			list = (List<ItemAssignment>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ItemAssignment itemAssignment : list) {
					if (homeworkId != itemAssignment.getHomeworkId()) {
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

			sb.append(_SQL_SELECT_ITEMASSIGNMENT_WHERE);

			sb.append(_FINDER_COLUMN_HOMEWORKID_HOMEWORKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ItemAssignmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(homeworkId);

				list = (List<ItemAssignment>)QueryUtil.list(
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
	 * Returns the first item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment findByhomeworkId_First(
			long homeworkId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = fetchByhomeworkId_First(
			homeworkId, orderByComparator);

		if (itemAssignment != null) {
			return itemAssignment;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("homeworkId=");
		sb.append(homeworkId);

		sb.append("}");

		throw new NoSuchItemAssignmentException(sb.toString());
	}

	/**
	 * Returns the first item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchByhomeworkId_First(
		long homeworkId, OrderByComparator<ItemAssignment> orderByComparator) {

		List<ItemAssignment> list = findByhomeworkId(
			homeworkId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment findByhomeworkId_Last(
			long homeworkId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = fetchByhomeworkId_Last(
			homeworkId, orderByComparator);

		if (itemAssignment != null) {
			return itemAssignment;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("homeworkId=");
		sb.append(homeworkId);

		sb.append("}");

		throw new NoSuchItemAssignmentException(sb.toString());
	}

	/**
	 * Returns the last item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	@Override
	public ItemAssignment fetchByhomeworkId_Last(
		long homeworkId, OrderByComparator<ItemAssignment> orderByComparator) {

		int count = countByhomeworkId(homeworkId);

		if (count == 0) {
			return null;
		}

		List<ItemAssignment> list = findByhomeworkId(
			homeworkId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item assignments before and after the current item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param itemAssignmentPK the primary key of the current item assignment
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	@Override
	public ItemAssignment[] findByhomeworkId_PrevAndNext(
			ItemAssignmentPK itemAssignmentPK, long homeworkId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = findByPrimaryKey(itemAssignmentPK);

		Session session = null;

		try {
			session = openSession();

			ItemAssignment[] array = new ItemAssignmentImpl[3];

			array[0] = getByhomeworkId_PrevAndNext(
				session, itemAssignment, homeworkId, orderByComparator, true);

			array[1] = itemAssignment;

			array[2] = getByhomeworkId_PrevAndNext(
				session, itemAssignment, homeworkId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemAssignment getByhomeworkId_PrevAndNext(
		Session session, ItemAssignment itemAssignment, long homeworkId,
		OrderByComparator<ItemAssignment> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ITEMASSIGNMENT_WHERE);

		sb.append(_FINDER_COLUMN_HOMEWORKID_HOMEWORKID_2);

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
			sb.append(ItemAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(homeworkId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						itemAssignment)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ItemAssignment> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item assignments where homeworkId = &#63; from the database.
	 *
	 * @param homeworkId the homework ID
	 */
	@Override
	public void removeByhomeworkId(long homeworkId) {
		for (ItemAssignment itemAssignment :
				findByhomeworkId(
					homeworkId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(itemAssignment);
		}
	}

	/**
	 * Returns the number of item assignments where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the number of matching item assignments
	 */
	@Override
	public int countByhomeworkId(long homeworkId) {
		FinderPath finderPath = _finderPathCountByhomeworkId;

		Object[] finderArgs = new Object[] {homeworkId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ITEMASSIGNMENT_WHERE);

			sb.append(_FINDER_COLUMN_HOMEWORKID_HOMEWORKID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(homeworkId);

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

	private static final String _FINDER_COLUMN_HOMEWORKID_HOMEWORKID_2 =
		"itemAssignment.homeworkId = ?";

	public ItemAssignmentPersistenceImpl() {
		setModelClass(ItemAssignment.class);

		setModelImplClass(ItemAssignmentImpl.class);
		setModelPKClass(ItemAssignmentPK.class);

		setTable(ItemAssignmentTable.INSTANCE);
	}

	/**
	 * Caches the item assignment in the entity cache if it is enabled.
	 *
	 * @param itemAssignment the item assignment
	 */
	@Override
	public void cacheResult(ItemAssignment itemAssignment) {
		entityCache.putResult(
			ItemAssignmentImpl.class, itemAssignment.getPrimaryKey(),
			itemAssignment);

		finderCache.putResult(
			_finderPathFetchByprogressionItemId_sessionId,
			new Object[] {
				itemAssignment.getProgressionItemId(),
				itemAssignment.getSessionId()
			},
			itemAssignment);

		finderCache.putResult(
			_finderPathFetchByprogressionItemId_homeworkId,
			new Object[] {
				itemAssignment.getProgressionItemId(),
				itemAssignment.getHomeworkId()
			},
			itemAssignment);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the item assignments in the entity cache if it is enabled.
	 *
	 * @param itemAssignments the item assignments
	 */
	@Override
	public void cacheResult(List<ItemAssignment> itemAssignments) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (itemAssignments.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ItemAssignment itemAssignment : itemAssignments) {
			if (entityCache.getResult(
					ItemAssignmentImpl.class, itemAssignment.getPrimaryKey()) ==
						null) {

				cacheResult(itemAssignment);
			}
		}
	}

	/**
	 * Clears the cache for all item assignments.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemAssignmentImpl.class);

		finderCache.clearCache(ItemAssignmentImpl.class);
	}

	/**
	 * Clears the cache for the item assignment.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemAssignment itemAssignment) {
		entityCache.removeResult(ItemAssignmentImpl.class, itemAssignment);
	}

	@Override
	public void clearCache(List<ItemAssignment> itemAssignments) {
		for (ItemAssignment itemAssignment : itemAssignments) {
			entityCache.removeResult(ItemAssignmentImpl.class, itemAssignment);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ItemAssignmentImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ItemAssignmentImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ItemAssignmentModelImpl itemAssignmentModelImpl) {

		Object[] args = new Object[] {
			itemAssignmentModelImpl.getProgressionItemId(),
			itemAssignmentModelImpl.getSessionId()
		};

		finderCache.putResult(
			_finderPathCountByprogressionItemId_sessionId, args,
			Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByprogressionItemId_sessionId, args,
			itemAssignmentModelImpl);

		args = new Object[] {
			itemAssignmentModelImpl.getProgressionItemId(),
			itemAssignmentModelImpl.getHomeworkId()
		};

		finderCache.putResult(
			_finderPathCountByprogressionItemId_homeworkId, args,
			Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByprogressionItemId_homeworkId, args,
			itemAssignmentModelImpl);
	}

	/**
	 * Creates a new item assignment with the primary key. Does not add the item assignment to the database.
	 *
	 * @param itemAssignmentPK the primary key for the new item assignment
	 * @return the new item assignment
	 */
	@Override
	public ItemAssignment create(ItemAssignmentPK itemAssignmentPK) {
		ItemAssignment itemAssignment = new ItemAssignmentImpl();

		itemAssignment.setNew(true);
		itemAssignment.setPrimaryKey(itemAssignmentPK);

		return itemAssignment;
	}

	/**
	 * Removes the item assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment that was removed
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	@Override
	public ItemAssignment remove(ItemAssignmentPK itemAssignmentPK)
		throws NoSuchItemAssignmentException {

		return remove((Serializable)itemAssignmentPK);
	}

	/**
	 * Removes the item assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item assignment
	 * @return the item assignment that was removed
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	@Override
	public ItemAssignment remove(Serializable primaryKey)
		throws NoSuchItemAssignmentException {

		Session session = null;

		try {
			session = openSession();

			ItemAssignment itemAssignment = (ItemAssignment)session.get(
				ItemAssignmentImpl.class, primaryKey);

			if (itemAssignment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemAssignmentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(itemAssignment);
		}
		catch (NoSuchItemAssignmentException noSuchEntityException) {
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
	protected ItemAssignment removeImpl(ItemAssignment itemAssignment) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemAssignment)) {
				itemAssignment = (ItemAssignment)session.get(
					ItemAssignmentImpl.class,
					itemAssignment.getPrimaryKeyObj());
			}

			if (itemAssignment != null) {
				session.delete(itemAssignment);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (itemAssignment != null) {
			clearCache(itemAssignment);
		}

		return itemAssignment;
	}

	@Override
	public ItemAssignment updateImpl(ItemAssignment itemAssignment) {
		boolean isNew = itemAssignment.isNew();

		if (!(itemAssignment instanceof ItemAssignmentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(itemAssignment.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					itemAssignment);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in itemAssignment proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ItemAssignment implementation " +
					itemAssignment.getClass());
		}

		ItemAssignmentModelImpl itemAssignmentModelImpl =
			(ItemAssignmentModelImpl)itemAssignment;

		if (!itemAssignmentModelImpl.hasSetModifiedDate()) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				itemAssignment.setModifiedDate(date);
			}
			else {
				itemAssignment.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(itemAssignment);
			}
			else {
				itemAssignment = (ItemAssignment)session.merge(itemAssignment);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ItemAssignmentImpl.class, itemAssignmentModelImpl, false, true);

		cacheUniqueFindersCache(itemAssignmentModelImpl);

		if (isNew) {
			itemAssignment.setNew(false);
		}

		itemAssignment.resetOriginalValues();

		return itemAssignment;
	}

	/**
	 * Returns the item assignment with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item assignment
	 * @return the item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	@Override
	public ItemAssignment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemAssignmentException {

		ItemAssignment itemAssignment = fetchByPrimaryKey(primaryKey);

		if (itemAssignment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemAssignmentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return itemAssignment;
	}

	/**
	 * Returns the item assignment with the primary key or throws a <code>NoSuchItemAssignmentException</code> if it could not be found.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	@Override
	public ItemAssignment findByPrimaryKey(ItemAssignmentPK itemAssignmentPK)
		throws NoSuchItemAssignmentException {

		return findByPrimaryKey((Serializable)itemAssignmentPK);
	}

	/**
	 * Returns the item assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment, or <code>null</code> if a item assignment with the primary key could not be found
	 */
	@Override
	public ItemAssignment fetchByPrimaryKey(ItemAssignmentPK itemAssignmentPK) {
		return fetchByPrimaryKey((Serializable)itemAssignmentPK);
	}

	/**
	 * Returns all the item assignments.
	 *
	 * @return the item assignments
	 */
	@Override
	public List<ItemAssignment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of item assignments
	 */
	@Override
	public List<ItemAssignment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item assignments
	 */
	@Override
	public List<ItemAssignment> findAll(
		int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of item assignments
	 */
	@Override
	public List<ItemAssignment> findAll(
		int start, int end, OrderByComparator<ItemAssignment> orderByComparator,
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

		List<ItemAssignment> list = null;

		if (useFinderCache) {
			list = (List<ItemAssignment>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ITEMASSIGNMENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMASSIGNMENT;

				sql = sql.concat(ItemAssignmentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ItemAssignment>)QueryUtil.list(
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
	 * Removes all the item assignments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemAssignment itemAssignment : findAll()) {
			remove(itemAssignment);
		}
	}

	/**
	 * Returns the number of item assignments.
	 *
	 * @return the number of item assignments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ITEMASSIGNMENT);

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
		return "itemAssignmentPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ITEMASSIGNMENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ItemAssignmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item assignment persistence.
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

		_finderPathFetchByprogressionItemId_sessionId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByprogressionItemId_sessionId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"progressionItemId", "sessionId"}, true);

		_finderPathCountByprogressionItemId_sessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByprogressionItemId_sessionId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"progressionItemId", "sessionId"}, false);

		_finderPathFetchByprogressionItemId_homeworkId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByprogressionItemId_homeworkId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"progressionItemId", "homeworkId"}, true);

		_finderPathCountByprogressionItemId_homeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByprogressionItemId_homeworkId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"progressionItemId", "homeworkId"}, false);

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

		_finderPathWithPaginationFindBysessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysessionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"sessionId"}, true);

		_finderPathWithoutPaginationFindBysessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBysessionId",
			new String[] {Long.class.getName()}, new String[] {"sessionId"},
			true);

		_finderPathCountBysessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysessionId",
			new String[] {Long.class.getName()}, new String[] {"sessionId"},
			false);

		_finderPathWithPaginationFindByhomeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByhomeworkId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"homeworkId"}, true);

		_finderPathWithoutPaginationFindByhomeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByhomeworkId",
			new String[] {Long.class.getName()}, new String[] {"homeworkId"},
			true);

		_finderPathCountByhomeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByhomeworkId",
			new String[] {Long.class.getName()}, new String[] {"homeworkId"},
			false);

		_setItemAssignmentUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setItemAssignmentUtilPersistence(null);

		entityCache.removeCache(ItemAssignmentImpl.class.getName());
	}

	private void _setItemAssignmentUtilPersistence(
		ItemAssignmentPersistence itemAssignmentPersistence) {

		try {
			Field field = ItemAssignmentUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, itemAssignmentPersistence);
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

	private static final String _SQL_SELECT_ITEMASSIGNMENT =
		"SELECT itemAssignment FROM ItemAssignment itemAssignment";

	private static final String _SQL_SELECT_ITEMASSIGNMENT_WHERE =
		"SELECT itemAssignment FROM ItemAssignment itemAssignment WHERE ";

	private static final String _SQL_COUNT_ITEMASSIGNMENT =
		"SELECT COUNT(itemAssignment) FROM ItemAssignment itemAssignment";

	private static final String _SQL_COUNT_ITEMASSIGNMENT_WHERE =
		"SELECT COUNT(itemAssignment) FROM ItemAssignment itemAssignment WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "itemAssignment.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ItemAssignment exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ItemAssignment exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ItemAssignmentPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"progressionItemId", "sessionId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private ItemAssignmentModelArgumentsResolver
		_itemAssignmentModelArgumentsResolver;

}