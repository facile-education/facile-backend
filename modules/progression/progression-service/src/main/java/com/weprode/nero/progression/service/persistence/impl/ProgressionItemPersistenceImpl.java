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

import com.weprode.nero.progression.exception.NoSuchItemException;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.model.ProgressionItemTable;
import com.weprode.nero.progression.model.impl.ProgressionItemImpl;
import com.weprode.nero.progression.model.impl.ProgressionItemModelImpl;
import com.weprode.nero.progression.service.persistence.ProgressionItemPersistence;
import com.weprode.nero.progression.service.persistence.ProgressionItemUtil;
import com.weprode.nero.progression.service.persistence.impl.constants.ProgressionPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
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
 * The persistence implementation for the progression item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ProgressionItemPersistence.class, BasePersistence.class})
public class ProgressionItemPersistenceImpl
	extends BasePersistenceImpl<ProgressionItem>
	implements ProgressionItemPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProgressionItemUtil</code> to access the progression item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProgressionItemImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByprogressionItemId;
	private FinderPath _finderPathCountByprogressionItemId;

	/**
	 * Returns the progression item where progressionItemId = &#63; or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem findByprogressionItemId(long progressionItemId)
		throws NoSuchItemException {

		ProgressionItem progressionItem = fetchByprogressionItemId(
			progressionItemId);

		if (progressionItem == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("progressionItemId=");
			sb.append(progressionItemId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchItemException(sb.toString());
		}

		return progressionItem;
	}

	/**
	 * Returns the progression item where progressionItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchByprogressionItemId(long progressionItemId) {
		return fetchByprogressionItemId(progressionItemId, true);
	}

	/**
	 * Returns the progression item where progressionItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchByprogressionItemId(
		long progressionItemId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {progressionItemId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByprogressionItemId, finderArgs);
		}

		if (result instanceof ProgressionItem) {
			ProgressionItem progressionItem = (ProgressionItem)result;

			if (progressionItemId != progressionItem.getProgressionItemId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PROGRESSIONITEM_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_PROGRESSIONITEMID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionItemId);

				List<ProgressionItem> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByprogressionItemId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {progressionItemId};
							}

							_log.warn(
								"ProgressionItemPersistenceImpl.fetchByprogressionItemId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProgressionItem progressionItem = list.get(0);

					result = progressionItem;

					cacheResult(progressionItem);
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
			return (ProgressionItem)result;
		}
	}

	/**
	 * Removes the progression item where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the progression item that was removed
	 */
	@Override
	public ProgressionItem removeByprogressionItemId(long progressionItemId)
		throws NoSuchItemException {

		ProgressionItem progressionItem = findByprogressionItemId(
			progressionItemId);

		return remove(progressionItem);
	}

	/**
	 * Returns the number of progression items where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching progression items
	 */
	@Override
	public int countByprogressionItemId(long progressionItemId) {
		FinderPath finderPath = _finderPathCountByprogressionItemId;

		Object[] finderArgs = new Object[] {progressionItemId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROGRESSIONITEM_WHERE);

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
			"progressionItem.progressionItemId = ?";

	private FinderPath _finderPathFetchBysessionId;
	private FinderPath _finderPathCountBysessionId;

	/**
	 * Returns the progression item where sessionId = &#63; or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param sessionId the session ID
	 * @return the matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem findBysessionId(long sessionId)
		throws NoSuchItemException {

		ProgressionItem progressionItem = fetchBysessionId(sessionId);

		if (progressionItem == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("sessionId=");
			sb.append(sessionId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchItemException(sb.toString());
		}

		return progressionItem;
	}

	/**
	 * Returns the progression item where sessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionId the session ID
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchBysessionId(long sessionId) {
		return fetchBysessionId(sessionId, true);
	}

	/**
	 * Returns the progression item where sessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchBysessionId(
		long sessionId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {sessionId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBysessionId, finderArgs);
		}

		if (result instanceof ProgressionItem) {
			ProgressionItem progressionItem = (ProgressionItem)result;

			if (sessionId != progressionItem.getSessionId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PROGRESSIONITEM_WHERE);

			sb.append(_FINDER_COLUMN_SESSIONID_SESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sessionId);

				List<ProgressionItem> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBysessionId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {sessionId};
							}

							_log.warn(
								"ProgressionItemPersistenceImpl.fetchBysessionId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProgressionItem progressionItem = list.get(0);

					result = progressionItem;

					cacheResult(progressionItem);
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
			return (ProgressionItem)result;
		}
	}

	/**
	 * Removes the progression item where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @return the progression item that was removed
	 */
	@Override
	public ProgressionItem removeBysessionId(long sessionId)
		throws NoSuchItemException {

		ProgressionItem progressionItem = findBysessionId(sessionId);

		return remove(progressionItem);
	}

	/**
	 * Returns the number of progression items where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching progression items
	 */
	@Override
	public int countBysessionId(long sessionId) {
		FinderPath finderPath = _finderPathCountBysessionId;

		Object[] finderArgs = new Object[] {sessionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROGRESSIONITEM_WHERE);

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
		"progressionItem.sessionId = ?";

	private FinderPath _finderPathFetchByhomeworkId;
	private FinderPath _finderPathCountByhomeworkId;

	/**
	 * Returns the progression item where homeworkId = &#63; or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem findByhomeworkId(long homeworkId)
		throws NoSuchItemException {

		ProgressionItem progressionItem = fetchByhomeworkId(homeworkId);

		if (progressionItem == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("homeworkId=");
			sb.append(homeworkId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchItemException(sb.toString());
		}

		return progressionItem;
	}

	/**
	 * Returns the progression item where homeworkId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchByhomeworkId(long homeworkId) {
		return fetchByhomeworkId(homeworkId, true);
	}

	/**
	 * Returns the progression item where homeworkId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param homeworkId the homework ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchByhomeworkId(
		long homeworkId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {homeworkId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByhomeworkId, finderArgs);
		}

		if (result instanceof ProgressionItem) {
			ProgressionItem progressionItem = (ProgressionItem)result;

			if (homeworkId != progressionItem.getHomeworkId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PROGRESSIONITEM_WHERE);

			sb.append(_FINDER_COLUMN_HOMEWORKID_HOMEWORKID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(homeworkId);

				List<ProgressionItem> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByhomeworkId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {homeworkId};
							}

							_log.warn(
								"ProgressionItemPersistenceImpl.fetchByhomeworkId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProgressionItem progressionItem = list.get(0);

					result = progressionItem;

					cacheResult(progressionItem);
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
			return (ProgressionItem)result;
		}
	}

	/**
	 * Removes the progression item where homeworkId = &#63; from the database.
	 *
	 * @param homeworkId the homework ID
	 * @return the progression item that was removed
	 */
	@Override
	public ProgressionItem removeByhomeworkId(long homeworkId)
		throws NoSuchItemException {

		ProgressionItem progressionItem = findByhomeworkId(homeworkId);

		return remove(progressionItem);
	}

	/**
	 * Returns the number of progression items where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the number of matching progression items
	 */
	@Override
	public int countByhomeworkId(long homeworkId) {
		FinderPath finderPath = _finderPathCountByhomeworkId;

		Object[] finderArgs = new Object[] {homeworkId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROGRESSIONITEM_WHERE);

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
		"progressionItem.homeworkId = ?";

	private FinderPath _finderPathWithPaginationFindByprogressionFolderId;
	private FinderPath _finderPathWithoutPaginationFindByprogressionFolderId;
	private FinderPath _finderPathCountByprogressionFolderId;

	/**
	 * Returns all the progression items where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the matching progression items
	 */
	@Override
	public List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId) {

		return findByprogressionFolderId(
			progressionFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the progression items where progressionFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @return the range of matching progression items
	 */
	@Override
	public List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId, int start, int end) {

		return findByprogressionFolderId(progressionFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the progression items where progressionFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progression items
	 */
	@Override
	public List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId, int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return findByprogressionFolderId(
			progressionFolderId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the progression items where progressionFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progression items
	 */
	@Override
	public List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId, int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByprogressionFolderId;
				finderArgs = new Object[] {progressionFolderId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByprogressionFolderId;
			finderArgs = new Object[] {
				progressionFolderId, start, end, orderByComparator
			};
		}

		List<ProgressionItem> list = null;

		if (useFinderCache) {
			list = (List<ProgressionItem>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ProgressionItem progressionItem : list) {
					if (progressionFolderId !=
							progressionItem.getProgressionFolderId()) {

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

			sb.append(_SQL_SELECT_PROGRESSIONITEM_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProgressionItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionFolderId);

				list = (List<ProgressionItem>)QueryUtil.list(
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
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem findByprogressionFolderId_First(
			long progressionFolderId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws NoSuchItemException {

		ProgressionItem progressionItem = fetchByprogressionFolderId_First(
			progressionFolderId, orderByComparator);

		if (progressionItem != null) {
			return progressionItem;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionFolderId=");
		sb.append(progressionFolderId);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchByprogressionFolderId_First(
		long progressionFolderId,
		OrderByComparator<ProgressionItem> orderByComparator) {

		List<ProgressionItem> list = findByprogressionFolderId(
			progressionFolderId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem findByprogressionFolderId_Last(
			long progressionFolderId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws NoSuchItemException {

		ProgressionItem progressionItem = fetchByprogressionFolderId_Last(
			progressionFolderId, orderByComparator);

		if (progressionItem != null) {
			return progressionItem;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionFolderId=");
		sb.append(progressionFolderId);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchByprogressionFolderId_Last(
		long progressionFolderId,
		OrderByComparator<ProgressionItem> orderByComparator) {

		int count = countByprogressionFolderId(progressionFolderId);

		if (count == 0) {
			return null;
		}

		List<ProgressionItem> list = findByprogressionFolderId(
			progressionFolderId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the progression items before and after the current progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionItemId the primary key of the current progression item
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression item
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	@Override
	public ProgressionItem[] findByprogressionFolderId_PrevAndNext(
			long progressionItemId, long progressionFolderId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws NoSuchItemException {

		ProgressionItem progressionItem = findByPrimaryKey(progressionItemId);

		Session session = null;

		try {
			session = openSession();

			ProgressionItem[] array = new ProgressionItemImpl[3];

			array[0] = getByprogressionFolderId_PrevAndNext(
				session, progressionItem, progressionFolderId,
				orderByComparator, true);

			array[1] = progressionItem;

			array[2] = getByprogressionFolderId_PrevAndNext(
				session, progressionItem, progressionFolderId,
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

	protected ProgressionItem getByprogressionFolderId_PrevAndNext(
		Session session, ProgressionItem progressionItem,
		long progressionFolderId,
		OrderByComparator<ProgressionItem> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_PROGRESSIONITEM_WHERE);

		sb.append(_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONFOLDERID_2);

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
			sb.append(ProgressionItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(progressionFolderId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						progressionItem)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProgressionItem> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the progression items where progressionFolderId = &#63; from the database.
	 *
	 * @param progressionFolderId the progression folder ID
	 */
	@Override
	public void removeByprogressionFolderId(long progressionFolderId) {
		for (ProgressionItem progressionItem :
				findByprogressionFolderId(
					progressionFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(progressionItem);
		}
	}

	/**
	 * Returns the number of progression items where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the number of matching progression items
	 */
	@Override
	public int countByprogressionFolderId(long progressionFolderId) {
		FinderPath finderPath = _finderPathCountByprogressionFolderId;

		Object[] finderArgs = new Object[] {progressionFolderId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROGRESSIONITEM_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONFOLDERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionFolderId);

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
		_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONFOLDERID_2 =
			"progressionItem.progressionFolderId = ?";

	private FinderPath
		_finderPathWithPaginationFindByprogressionFolderId_progressionId;
	private FinderPath
		_finderPathWithoutPaginationFindByprogressionFolderId_progressionId;
	private FinderPath _finderPathCountByprogressionFolderId_progressionId;

	/**
	 * Returns all the progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @return the matching progression items
	 */
	@Override
	public List<ProgressionItem> findByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId) {

		return findByprogressionFolderId_progressionId(
			progressionFolderId, progressionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @return the range of matching progression items
	 */
	@Override
	public List<ProgressionItem> findByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId, int start, int end) {

		return findByprogressionFolderId_progressionId(
			progressionFolderId, progressionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progression items
	 */
	@Override
	public List<ProgressionItem> findByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId, int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return findByprogressionFolderId_progressionId(
			progressionFolderId, progressionId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progression items
	 */
	@Override
	public List<ProgressionItem> findByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId, int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByprogressionFolderId_progressionId;
				finderArgs = new Object[] {progressionFolderId, progressionId};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByprogressionFolderId_progressionId;
			finderArgs = new Object[] {
				progressionFolderId, progressionId, start, end,
				orderByComparator
			};
		}

		List<ProgressionItem> list = null;

		if (useFinderCache) {
			list = (List<ProgressionItem>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ProgressionItem progressionItem : list) {
					if ((progressionFolderId !=
							progressionItem.getProgressionFolderId()) ||
						(progressionId != progressionItem.getProgressionId())) {

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

			sb.append(_SQL_SELECT_PROGRESSIONITEM_WHERE);

			sb.append(
				_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONID_PROGRESSIONFOLDERID_2);

			sb.append(
				_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONID_PROGRESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProgressionItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionFolderId);

				queryPos.add(progressionId);

				list = (List<ProgressionItem>)QueryUtil.list(
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
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem findByprogressionFolderId_progressionId_First(
			long progressionFolderId, long progressionId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws NoSuchItemException {

		ProgressionItem progressionItem =
			fetchByprogressionFolderId_progressionId_First(
				progressionFolderId, progressionId, orderByComparator);

		if (progressionItem != null) {
			return progressionItem;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionFolderId=");
		sb.append(progressionFolderId);

		sb.append(", progressionId=");
		sb.append(progressionId);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchByprogressionFolderId_progressionId_First(
		long progressionFolderId, long progressionId,
		OrderByComparator<ProgressionItem> orderByComparator) {

		List<ProgressionItem> list = findByprogressionFolderId_progressionId(
			progressionFolderId, progressionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem findByprogressionFolderId_progressionId_Last(
			long progressionFolderId, long progressionId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws NoSuchItemException {

		ProgressionItem progressionItem =
			fetchByprogressionFolderId_progressionId_Last(
				progressionFolderId, progressionId, orderByComparator);

		if (progressionItem != null) {
			return progressionItem;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionFolderId=");
		sb.append(progressionFolderId);

		sb.append(", progressionId=");
		sb.append(progressionId);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	@Override
	public ProgressionItem fetchByprogressionFolderId_progressionId_Last(
		long progressionFolderId, long progressionId,
		OrderByComparator<ProgressionItem> orderByComparator) {

		int count = countByprogressionFolderId_progressionId(
			progressionFolderId, progressionId);

		if (count == 0) {
			return null;
		}

		List<ProgressionItem> list = findByprogressionFolderId_progressionId(
			progressionFolderId, progressionId, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the progression items before and after the current progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionItemId the primary key of the current progression item
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression item
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	@Override
	public ProgressionItem[]
			findByprogressionFolderId_progressionId_PrevAndNext(
				long progressionItemId, long progressionFolderId,
				long progressionId,
				OrderByComparator<ProgressionItem> orderByComparator)
		throws NoSuchItemException {

		ProgressionItem progressionItem = findByPrimaryKey(progressionItemId);

		Session session = null;

		try {
			session = openSession();

			ProgressionItem[] array = new ProgressionItemImpl[3];

			array[0] = getByprogressionFolderId_progressionId_PrevAndNext(
				session, progressionItem, progressionFolderId, progressionId,
				orderByComparator, true);

			array[1] = progressionItem;

			array[2] = getByprogressionFolderId_progressionId_PrevAndNext(
				session, progressionItem, progressionFolderId, progressionId,
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

	protected ProgressionItem
		getByprogressionFolderId_progressionId_PrevAndNext(
			Session session, ProgressionItem progressionItem,
			long progressionFolderId, long progressionId,
			OrderByComparator<ProgressionItem> orderByComparator,
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

		sb.append(_SQL_SELECT_PROGRESSIONITEM_WHERE);

		sb.append(
			_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONID_PROGRESSIONFOLDERID_2);

		sb.append(
			_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONID_PROGRESSIONID_2);

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
			sb.append(ProgressionItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(progressionFolderId);

		queryPos.add(progressionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						progressionItem)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProgressionItem> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the progression items where progressionFolderId = &#63; and progressionId = &#63; from the database.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 */
	@Override
	public void removeByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId) {

		for (ProgressionItem progressionItem :
				findByprogressionFolderId_progressionId(
					progressionFolderId, progressionId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(progressionItem);
		}
	}

	/**
	 * Returns the number of progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @return the number of matching progression items
	 */
	@Override
	public int countByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId) {

		FinderPath finderPath =
			_finderPathCountByprogressionFolderId_progressionId;

		Object[] finderArgs = new Object[] {progressionFolderId, progressionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PROGRESSIONITEM_WHERE);

			sb.append(
				_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONID_PROGRESSIONFOLDERID_2);

			sb.append(
				_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONID_PROGRESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionFolderId);

				queryPos.add(progressionId);

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
		_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONID_PROGRESSIONFOLDERID_2 =
			"progressionItem.progressionFolderId = ? AND ";

	private static final String
		_FINDER_COLUMN_PROGRESSIONFOLDERID_PROGRESSIONID_PROGRESSIONID_2 =
			"progressionItem.progressionId = ?";

	public ProgressionItemPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");
		dbColumnNames.put("order", "order_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ProgressionItem.class);

		setModelImplClass(ProgressionItemImpl.class);
		setModelPKClass(long.class);

		setTable(ProgressionItemTable.INSTANCE);
	}

	/**
	 * Caches the progression item in the entity cache if it is enabled.
	 *
	 * @param progressionItem the progression item
	 */
	@Override
	public void cacheResult(ProgressionItem progressionItem) {
		entityCache.putResult(
			ProgressionItemImpl.class, progressionItem.getPrimaryKey(),
			progressionItem);

		finderCache.putResult(
			_finderPathFetchByprogressionItemId,
			new Object[] {progressionItem.getProgressionItemId()},
			progressionItem);

		finderCache.putResult(
			_finderPathFetchBysessionId,
			new Object[] {progressionItem.getSessionId()}, progressionItem);

		finderCache.putResult(
			_finderPathFetchByhomeworkId,
			new Object[] {progressionItem.getHomeworkId()}, progressionItem);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the progression items in the entity cache if it is enabled.
	 *
	 * @param progressionItems the progression items
	 */
	@Override
	public void cacheResult(List<ProgressionItem> progressionItems) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (progressionItems.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ProgressionItem progressionItem : progressionItems) {
			if (entityCache.getResult(
					ProgressionItemImpl.class,
					progressionItem.getPrimaryKey()) == null) {

				cacheResult(progressionItem);
			}
		}
	}

	/**
	 * Clears the cache for all progression items.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProgressionItemImpl.class);

		finderCache.clearCache(ProgressionItemImpl.class);
	}

	/**
	 * Clears the cache for the progression item.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProgressionItem progressionItem) {
		entityCache.removeResult(ProgressionItemImpl.class, progressionItem);
	}

	@Override
	public void clearCache(List<ProgressionItem> progressionItems) {
		for (ProgressionItem progressionItem : progressionItems) {
			entityCache.removeResult(
				ProgressionItemImpl.class, progressionItem);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ProgressionItemImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ProgressionItemImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ProgressionItemModelImpl progressionItemModelImpl) {

		Object[] args = new Object[] {
			progressionItemModelImpl.getProgressionItemId()
		};

		finderCache.putResult(
			_finderPathCountByprogressionItemId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByprogressionItemId, args,
			progressionItemModelImpl);

		args = new Object[] {progressionItemModelImpl.getSessionId()};

		finderCache.putResult(
			_finderPathCountBysessionId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBysessionId, args, progressionItemModelImpl);

		args = new Object[] {progressionItemModelImpl.getHomeworkId()};

		finderCache.putResult(
			_finderPathCountByhomeworkId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByhomeworkId, args, progressionItemModelImpl);
	}

	/**
	 * Creates a new progression item with the primary key. Does not add the progression item to the database.
	 *
	 * @param progressionItemId the primary key for the new progression item
	 * @return the new progression item
	 */
	@Override
	public ProgressionItem create(long progressionItemId) {
		ProgressionItem progressionItem = new ProgressionItemImpl();

		progressionItem.setNew(true);
		progressionItem.setPrimaryKey(progressionItemId);

		return progressionItem;
	}

	/**
	 * Removes the progression item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item that was removed
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	@Override
	public ProgressionItem remove(long progressionItemId)
		throws NoSuchItemException {

		return remove((Serializable)progressionItemId);
	}

	/**
	 * Removes the progression item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the progression item
	 * @return the progression item that was removed
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	@Override
	public ProgressionItem remove(Serializable primaryKey)
		throws NoSuchItemException {

		Session session = null;

		try {
			session = openSession();

			ProgressionItem progressionItem = (ProgressionItem)session.get(
				ProgressionItemImpl.class, primaryKey);

			if (progressionItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(progressionItem);
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
	protected ProgressionItem removeImpl(ProgressionItem progressionItem) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(progressionItem)) {
				progressionItem = (ProgressionItem)session.get(
					ProgressionItemImpl.class,
					progressionItem.getPrimaryKeyObj());
			}

			if (progressionItem != null) {
				session.delete(progressionItem);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (progressionItem != null) {
			clearCache(progressionItem);
		}

		return progressionItem;
	}

	@Override
	public ProgressionItem updateImpl(ProgressionItem progressionItem) {
		boolean isNew = progressionItem.isNew();

		if (!(progressionItem instanceof ProgressionItemModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(progressionItem.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					progressionItem);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in progressionItem proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProgressionItem implementation " +
					progressionItem.getClass());
		}

		ProgressionItemModelImpl progressionItemModelImpl =
			(ProgressionItemModelImpl)progressionItem;

		if (!progressionItemModelImpl.hasSetModifiedDate()) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				progressionItem.setModifiedDate(date);
			}
			else {
				progressionItem.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(progressionItem);
			}
			else {
				progressionItem = (ProgressionItem)session.merge(
					progressionItem);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ProgressionItemImpl.class, progressionItemModelImpl, false, true);

		cacheUniqueFindersCache(progressionItemModelImpl);

		if (isNew) {
			progressionItem.setNew(false);
		}

		progressionItem.resetOriginalValues();

		return progressionItem;
	}

	/**
	 * Returns the progression item with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the progression item
	 * @return the progression item
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	@Override
	public ProgressionItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemException {

		ProgressionItem progressionItem = fetchByPrimaryKey(primaryKey);

		if (progressionItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return progressionItem;
	}

	/**
	 * Returns the progression item with the primary key or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	@Override
	public ProgressionItem findByPrimaryKey(long progressionItemId)
		throws NoSuchItemException {

		return findByPrimaryKey((Serializable)progressionItemId);
	}

	/**
	 * Returns the progression item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item, or <code>null</code> if a progression item with the primary key could not be found
	 */
	@Override
	public ProgressionItem fetchByPrimaryKey(long progressionItemId) {
		return fetchByPrimaryKey((Serializable)progressionItemId);
	}

	/**
	 * Returns all the progression items.
	 *
	 * @return the progression items
	 */
	@Override
	public List<ProgressionItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the progression items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @return the range of progression items
	 */
	@Override
	public List<ProgressionItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the progression items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of progression items
	 */
	@Override
	public List<ProgressionItem> findAll(
		int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the progression items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of progression items
	 */
	@Override
	public List<ProgressionItem> findAll(
		int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator,
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

		List<ProgressionItem> list = null;

		if (useFinderCache) {
			list = (List<ProgressionItem>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PROGRESSIONITEM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PROGRESSIONITEM;

				sql = sql.concat(ProgressionItemModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProgressionItem>)QueryUtil.list(
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
	 * Removes all the progression items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProgressionItem progressionItem : findAll()) {
			remove(progressionItem);
		}
	}

	/**
	 * Returns the number of progression items.
	 *
	 * @return the number of progression items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PROGRESSIONITEM);

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
		return "progressionItemId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PROGRESSIONITEM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProgressionItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the progression item persistence.
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

		_finderPathFetchByprogressionItemId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByprogressionItemId",
			new String[] {Long.class.getName()},
			new String[] {"progressionItemId"}, true);

		_finderPathCountByprogressionItemId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByprogressionItemId", new String[] {Long.class.getName()},
			new String[] {"progressionItemId"}, false);

		_finderPathFetchBysessionId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBysessionId",
			new String[] {Long.class.getName()}, new String[] {"sessionId"},
			true);

		_finderPathCountBysessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysessionId",
			new String[] {Long.class.getName()}, new String[] {"sessionId"},
			false);

		_finderPathFetchByhomeworkId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByhomeworkId",
			new String[] {Long.class.getName()}, new String[] {"homeworkId"},
			true);

		_finderPathCountByhomeworkId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByhomeworkId",
			new String[] {Long.class.getName()}, new String[] {"homeworkId"},
			false);

		_finderPathWithPaginationFindByprogressionFolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByprogressionFolderId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"progressionFolderId"}, true);

		_finderPathWithoutPaginationFindByprogressionFolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByprogressionFolderId", new String[] {Long.class.getName()},
			new String[] {"progressionFolderId"}, true);

		_finderPathCountByprogressionFolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByprogressionFolderId", new String[] {Long.class.getName()},
			new String[] {"progressionFolderId"}, false);

		_finderPathWithPaginationFindByprogressionFolderId_progressionId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByprogressionFolderId_progressionId",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				},
				new String[] {"progressionFolderId", "progressionId"}, true);

		_finderPathWithoutPaginationFindByprogressionFolderId_progressionId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByprogressionFolderId_progressionId",
				new String[] {Long.class.getName(), Long.class.getName()},
				new String[] {"progressionFolderId", "progressionId"}, true);

		_finderPathCountByprogressionFolderId_progressionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByprogressionFolderId_progressionId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"progressionFolderId", "progressionId"}, false);

		_setProgressionItemUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setProgressionItemUtilPersistence(null);

		entityCache.removeCache(ProgressionItemImpl.class.getName());
	}

	private void _setProgressionItemUtilPersistence(
		ProgressionItemPersistence progressionItemPersistence) {

		try {
			Field field = ProgressionItemUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, progressionItemPersistence);
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

	private static final String _SQL_SELECT_PROGRESSIONITEM =
		"SELECT progressionItem FROM ProgressionItem progressionItem";

	private static final String _SQL_SELECT_PROGRESSIONITEM_WHERE =
		"SELECT progressionItem FROM ProgressionItem progressionItem WHERE ";

	private static final String _SQL_COUNT_PROGRESSIONITEM =
		"SELECT COUNT(progressionItem) FROM ProgressionItem progressionItem";

	private static final String _SQL_COUNT_PROGRESSIONITEM_WHERE =
		"SELECT COUNT(progressionItem) FROM ProgressionItem progressionItem WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "progressionItem.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProgressionItem exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ProgressionItem exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProgressionItemPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type", "order"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private ProgressionItemModelArgumentsResolver
		_progressionItemModelArgumentsResolver;

}