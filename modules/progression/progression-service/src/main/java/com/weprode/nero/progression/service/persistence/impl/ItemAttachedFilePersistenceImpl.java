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
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.progression.exception.NoSuchItemAttachedFileException;
import com.weprode.nero.progression.model.ItemAttachedFile;
import com.weprode.nero.progression.model.ItemAttachedFileTable;
import com.weprode.nero.progression.model.impl.ItemAttachedFileImpl;
import com.weprode.nero.progression.model.impl.ItemAttachedFileModelImpl;
import com.weprode.nero.progression.service.persistence.ItemAttachedFilePersistence;
import com.weprode.nero.progression.service.persistence.ItemAttachedFileUtil;
import com.weprode.nero.progression.service.persistence.impl.constants.ProgressionPersistenceConstants;

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
 * The persistence implementation for the item attached file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ItemAttachedFilePersistence.class, BasePersistence.class})
public class ItemAttachedFilePersistenceImpl
	extends BasePersistenceImpl<ItemAttachedFile>
	implements ItemAttachedFilePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ItemAttachedFileUtil</code> to access the item attached file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ItemAttachedFileImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByitemAttachedFileId;
	private FinderPath _finderPathCountByitemAttachedFileId;

	/**
	 * Returns the item attached file where itemAttachedFileId = &#63; or throws a <code>NoSuchItemAttachedFileException</code> if it could not be found.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @return the matching item attached file
	 * @throws NoSuchItemAttachedFileException if a matching item attached file could not be found
	 */
	@Override
	public ItemAttachedFile findByitemAttachedFileId(long itemAttachedFileId)
		throws NoSuchItemAttachedFileException {

		ItemAttachedFile itemAttachedFile = fetchByitemAttachedFileId(
			itemAttachedFileId);

		if (itemAttachedFile == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("itemAttachedFileId=");
			sb.append(itemAttachedFileId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchItemAttachedFileException(sb.toString());
		}

		return itemAttachedFile;
	}

	/**
	 * Returns the item attached file where itemAttachedFileId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @return the matching item attached file, or <code>null</code> if a matching item attached file could not be found
	 */
	@Override
	public ItemAttachedFile fetchByitemAttachedFileId(long itemAttachedFileId) {
		return fetchByitemAttachedFileId(itemAttachedFileId, true);
	}

	/**
	 * Returns the item attached file where itemAttachedFileId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching item attached file, or <code>null</code> if a matching item attached file could not be found
	 */
	@Override
	public ItemAttachedFile fetchByitemAttachedFileId(
		long itemAttachedFileId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {itemAttachedFileId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByitemAttachedFileId, finderArgs);
		}

		if (result instanceof ItemAttachedFile) {
			ItemAttachedFile itemAttachedFile = (ItemAttachedFile)result;

			if (itemAttachedFileId !=
					itemAttachedFile.getItemAttachedFileId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_ITEMATTACHEDFILE_WHERE);

			sb.append(_FINDER_COLUMN_ITEMATTACHEDFILEID_ITEMATTACHEDFILEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(itemAttachedFileId);

				List<ItemAttachedFile> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByitemAttachedFileId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {itemAttachedFileId};
							}

							_log.warn(
								"ItemAttachedFilePersistenceImpl.fetchByitemAttachedFileId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ItemAttachedFile itemAttachedFile = list.get(0);

					result = itemAttachedFile;

					cacheResult(itemAttachedFile);
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
			return (ItemAttachedFile)result;
		}
	}

	/**
	 * Removes the item attached file where itemAttachedFileId = &#63; from the database.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @return the item attached file that was removed
	 */
	@Override
	public ItemAttachedFile removeByitemAttachedFileId(long itemAttachedFileId)
		throws NoSuchItemAttachedFileException {

		ItemAttachedFile itemAttachedFile = findByitemAttachedFileId(
			itemAttachedFileId);

		return remove(itemAttachedFile);
	}

	/**
	 * Returns the number of item attached files where itemAttachedFileId = &#63;.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @return the number of matching item attached files
	 */
	@Override
	public int countByitemAttachedFileId(long itemAttachedFileId) {
		FinderPath finderPath = _finderPathCountByitemAttachedFileId;

		Object[] finderArgs = new Object[] {itemAttachedFileId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ITEMATTACHEDFILE_WHERE);

			sb.append(_FINDER_COLUMN_ITEMATTACHEDFILEID_ITEMATTACHEDFILEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(itemAttachedFileId);

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
		_FINDER_COLUMN_ITEMATTACHEDFILEID_ITEMATTACHEDFILEID_2 =
			"itemAttachedFile.itemAttachedFileId = ?";

	private FinderPath _finderPathWithPaginationFindByprogressionItemId;
	private FinderPath _finderPathWithoutPaginationFindByprogressionItemId;
	private FinderPath _finderPathCountByprogressionItemId;

	/**
	 * Returns all the item attached files where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching item attached files
	 */
	@Override
	public List<ItemAttachedFile> findByprogressionItemId(
		long progressionItemId) {

		return findByprogressionItemId(
			progressionItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item attached files where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @return the range of matching item attached files
	 */
	@Override
	public List<ItemAttachedFile> findByprogressionItemId(
		long progressionItemId, int start, int end) {

		return findByprogressionItemId(progressionItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item attached files where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item attached files
	 */
	@Override
	public List<ItemAttachedFile> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemAttachedFile> orderByComparator) {

		return findByprogressionItemId(
			progressionItemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item attached files where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item attached files
	 */
	@Override
	public List<ItemAttachedFile> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemAttachedFile> orderByComparator,
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

		List<ItemAttachedFile> list = null;

		if (useFinderCache) {
			list = (List<ItemAttachedFile>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ItemAttachedFile itemAttachedFile : list) {
					if (progressionItemId !=
							itemAttachedFile.getProgressionItemId()) {

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

			sb.append(_SQL_SELECT_ITEMATTACHEDFILE_WHERE);

			sb.append(_FINDER_COLUMN_PROGRESSIONITEMID_PROGRESSIONITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ItemAttachedFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(progressionItemId);

				list = (List<ItemAttachedFile>)QueryUtil.list(
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
	 * Returns the first item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item attached file
	 * @throws NoSuchItemAttachedFileException if a matching item attached file could not be found
	 */
	@Override
	public ItemAttachedFile findByprogressionItemId_First(
			long progressionItemId,
			OrderByComparator<ItemAttachedFile> orderByComparator)
		throws NoSuchItemAttachedFileException {

		ItemAttachedFile itemAttachedFile = fetchByprogressionItemId_First(
			progressionItemId, orderByComparator);

		if (itemAttachedFile != null) {
			return itemAttachedFile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionItemId=");
		sb.append(progressionItemId);

		sb.append("}");

		throw new NoSuchItemAttachedFileException(sb.toString());
	}

	/**
	 * Returns the first item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item attached file, or <code>null</code> if a matching item attached file could not be found
	 */
	@Override
	public ItemAttachedFile fetchByprogressionItemId_First(
		long progressionItemId,
		OrderByComparator<ItemAttachedFile> orderByComparator) {

		List<ItemAttachedFile> list = findByprogressionItemId(
			progressionItemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item attached file
	 * @throws NoSuchItemAttachedFileException if a matching item attached file could not be found
	 */
	@Override
	public ItemAttachedFile findByprogressionItemId_Last(
			long progressionItemId,
			OrderByComparator<ItemAttachedFile> orderByComparator)
		throws NoSuchItemAttachedFileException {

		ItemAttachedFile itemAttachedFile = fetchByprogressionItemId_Last(
			progressionItemId, orderByComparator);

		if (itemAttachedFile != null) {
			return itemAttachedFile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("progressionItemId=");
		sb.append(progressionItemId);

		sb.append("}");

		throw new NoSuchItemAttachedFileException(sb.toString());
	}

	/**
	 * Returns the last item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item attached file, or <code>null</code> if a matching item attached file could not be found
	 */
	@Override
	public ItemAttachedFile fetchByprogressionItemId_Last(
		long progressionItemId,
		OrderByComparator<ItemAttachedFile> orderByComparator) {

		int count = countByprogressionItemId(progressionItemId);

		if (count == 0) {
			return null;
		}

		List<ItemAttachedFile> list = findByprogressionItemId(
			progressionItemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item attached files before and after the current item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param itemAttachedFileId the primary key of the current item attached file
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item attached file
	 * @throws NoSuchItemAttachedFileException if a item attached file with the primary key could not be found
	 */
	@Override
	public ItemAttachedFile[] findByprogressionItemId_PrevAndNext(
			long itemAttachedFileId, long progressionItemId,
			OrderByComparator<ItemAttachedFile> orderByComparator)
		throws NoSuchItemAttachedFileException {

		ItemAttachedFile itemAttachedFile = findByPrimaryKey(
			itemAttachedFileId);

		Session session = null;

		try {
			session = openSession();

			ItemAttachedFile[] array = new ItemAttachedFileImpl[3];

			array[0] = getByprogressionItemId_PrevAndNext(
				session, itemAttachedFile, progressionItemId, orderByComparator,
				true);

			array[1] = itemAttachedFile;

			array[2] = getByprogressionItemId_PrevAndNext(
				session, itemAttachedFile, progressionItemId, orderByComparator,
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

	protected ItemAttachedFile getByprogressionItemId_PrevAndNext(
		Session session, ItemAttachedFile itemAttachedFile,
		long progressionItemId,
		OrderByComparator<ItemAttachedFile> orderByComparator,
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

		sb.append(_SQL_SELECT_ITEMATTACHEDFILE_WHERE);

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
			sb.append(ItemAttachedFileModelImpl.ORDER_BY_JPQL);
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
						itemAttachedFile)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ItemAttachedFile> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item attached files where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 */
	@Override
	public void removeByprogressionItemId(long progressionItemId) {
		for (ItemAttachedFile itemAttachedFile :
				findByprogressionItemId(
					progressionItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(itemAttachedFile);
		}
	}

	/**
	 * Returns the number of item attached files where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching item attached files
	 */
	@Override
	public int countByprogressionItemId(long progressionItemId) {
		FinderPath finderPath = _finderPathCountByprogressionItemId;

		Object[] finderArgs = new Object[] {progressionItemId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ITEMATTACHEDFILE_WHERE);

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
			"itemAttachedFile.progressionItemId = ?";

	public ItemAttachedFilePersistenceImpl() {
		setModelClass(ItemAttachedFile.class);

		setModelImplClass(ItemAttachedFileImpl.class);
		setModelPKClass(long.class);

		setTable(ItemAttachedFileTable.INSTANCE);
	}

	/**
	 * Caches the item attached file in the entity cache if it is enabled.
	 *
	 * @param itemAttachedFile the item attached file
	 */
	@Override
	public void cacheResult(ItemAttachedFile itemAttachedFile) {
		entityCache.putResult(
			ItemAttachedFileImpl.class, itemAttachedFile.getPrimaryKey(),
			itemAttachedFile);

		finderCache.putResult(
			_finderPathFetchByitemAttachedFileId,
			new Object[] {itemAttachedFile.getItemAttachedFileId()},
			itemAttachedFile);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the item attached files in the entity cache if it is enabled.
	 *
	 * @param itemAttachedFiles the item attached files
	 */
	@Override
	public void cacheResult(List<ItemAttachedFile> itemAttachedFiles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (itemAttachedFiles.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ItemAttachedFile itemAttachedFile : itemAttachedFiles) {
			if (entityCache.getResult(
					ItemAttachedFileImpl.class,
					itemAttachedFile.getPrimaryKey()) == null) {

				cacheResult(itemAttachedFile);
			}
		}
	}

	/**
	 * Clears the cache for all item attached files.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemAttachedFileImpl.class);

		finderCache.clearCache(ItemAttachedFileImpl.class);
	}

	/**
	 * Clears the cache for the item attached file.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemAttachedFile itemAttachedFile) {
		entityCache.removeResult(ItemAttachedFileImpl.class, itemAttachedFile);
	}

	@Override
	public void clearCache(List<ItemAttachedFile> itemAttachedFiles) {
		for (ItemAttachedFile itemAttachedFile : itemAttachedFiles) {
			entityCache.removeResult(
				ItemAttachedFileImpl.class, itemAttachedFile);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ItemAttachedFileImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ItemAttachedFileImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ItemAttachedFileModelImpl itemAttachedFileModelImpl) {

		Object[] args = new Object[] {
			itemAttachedFileModelImpl.getItemAttachedFileId()
		};

		finderCache.putResult(
			_finderPathCountByitemAttachedFileId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByitemAttachedFileId, args,
			itemAttachedFileModelImpl);
	}

	/**
	 * Creates a new item attached file with the primary key. Does not add the item attached file to the database.
	 *
	 * @param itemAttachedFileId the primary key for the new item attached file
	 * @return the new item attached file
	 */
	@Override
	public ItemAttachedFile create(long itemAttachedFileId) {
		ItemAttachedFile itemAttachedFile = new ItemAttachedFileImpl();

		itemAttachedFile.setNew(true);
		itemAttachedFile.setPrimaryKey(itemAttachedFileId);

		return itemAttachedFile;
	}

	/**
	 * Removes the item attached file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemAttachedFileId the primary key of the item attached file
	 * @return the item attached file that was removed
	 * @throws NoSuchItemAttachedFileException if a item attached file with the primary key could not be found
	 */
	@Override
	public ItemAttachedFile remove(long itemAttachedFileId)
		throws NoSuchItemAttachedFileException {

		return remove((Serializable)itemAttachedFileId);
	}

	/**
	 * Removes the item attached file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item attached file
	 * @return the item attached file that was removed
	 * @throws NoSuchItemAttachedFileException if a item attached file with the primary key could not be found
	 */
	@Override
	public ItemAttachedFile remove(Serializable primaryKey)
		throws NoSuchItemAttachedFileException {

		Session session = null;

		try {
			session = openSession();

			ItemAttachedFile itemAttachedFile = (ItemAttachedFile)session.get(
				ItemAttachedFileImpl.class, primaryKey);

			if (itemAttachedFile == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemAttachedFileException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(itemAttachedFile);
		}
		catch (NoSuchItemAttachedFileException noSuchEntityException) {
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
	protected ItemAttachedFile removeImpl(ItemAttachedFile itemAttachedFile) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemAttachedFile)) {
				itemAttachedFile = (ItemAttachedFile)session.get(
					ItemAttachedFileImpl.class,
					itemAttachedFile.getPrimaryKeyObj());
			}

			if (itemAttachedFile != null) {
				session.delete(itemAttachedFile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (itemAttachedFile != null) {
			clearCache(itemAttachedFile);
		}

		return itemAttachedFile;
	}

	@Override
	public ItemAttachedFile updateImpl(ItemAttachedFile itemAttachedFile) {
		boolean isNew = itemAttachedFile.isNew();

		if (!(itemAttachedFile instanceof ItemAttachedFileModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(itemAttachedFile.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					itemAttachedFile);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in itemAttachedFile proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ItemAttachedFile implementation " +
					itemAttachedFile.getClass());
		}

		ItemAttachedFileModelImpl itemAttachedFileModelImpl =
			(ItemAttachedFileModelImpl)itemAttachedFile;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(itemAttachedFile);
			}
			else {
				itemAttachedFile = (ItemAttachedFile)session.merge(
					itemAttachedFile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ItemAttachedFileImpl.class, itemAttachedFileModelImpl, false, true);

		cacheUniqueFindersCache(itemAttachedFileModelImpl);

		if (isNew) {
			itemAttachedFile.setNew(false);
		}

		itemAttachedFile.resetOriginalValues();

		return itemAttachedFile;
	}

	/**
	 * Returns the item attached file with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item attached file
	 * @return the item attached file
	 * @throws NoSuchItemAttachedFileException if a item attached file with the primary key could not be found
	 */
	@Override
	public ItemAttachedFile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemAttachedFileException {

		ItemAttachedFile itemAttachedFile = fetchByPrimaryKey(primaryKey);

		if (itemAttachedFile == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemAttachedFileException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return itemAttachedFile;
	}

	/**
	 * Returns the item attached file with the primary key or throws a <code>NoSuchItemAttachedFileException</code> if it could not be found.
	 *
	 * @param itemAttachedFileId the primary key of the item attached file
	 * @return the item attached file
	 * @throws NoSuchItemAttachedFileException if a item attached file with the primary key could not be found
	 */
	@Override
	public ItemAttachedFile findByPrimaryKey(long itemAttachedFileId)
		throws NoSuchItemAttachedFileException {

		return findByPrimaryKey((Serializable)itemAttachedFileId);
	}

	/**
	 * Returns the item attached file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemAttachedFileId the primary key of the item attached file
	 * @return the item attached file, or <code>null</code> if a item attached file with the primary key could not be found
	 */
	@Override
	public ItemAttachedFile fetchByPrimaryKey(long itemAttachedFileId) {
		return fetchByPrimaryKey((Serializable)itemAttachedFileId);
	}

	/**
	 * Returns all the item attached files.
	 *
	 * @return the item attached files
	 */
	@Override
	public List<ItemAttachedFile> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @return the range of item attached files
	 */
	@Override
	public List<ItemAttachedFile> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item attached files
	 */
	@Override
	public List<ItemAttachedFile> findAll(
		int start, int end,
		OrderByComparator<ItemAttachedFile> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of item attached files
	 */
	@Override
	public List<ItemAttachedFile> findAll(
		int start, int end,
		OrderByComparator<ItemAttachedFile> orderByComparator,
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

		List<ItemAttachedFile> list = null;

		if (useFinderCache) {
			list = (List<ItemAttachedFile>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ITEMATTACHEDFILE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMATTACHEDFILE;

				sql = sql.concat(ItemAttachedFileModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ItemAttachedFile>)QueryUtil.list(
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
	 * Removes all the item attached files from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemAttachedFile itemAttachedFile : findAll()) {
			remove(itemAttachedFile);
		}
	}

	/**
	 * Returns the number of item attached files.
	 *
	 * @return the number of item attached files
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ITEMATTACHEDFILE);

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
		return "itemAttachedFileId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ITEMATTACHEDFILE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ItemAttachedFileModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item attached file persistence.
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

		_finderPathFetchByitemAttachedFileId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByitemAttachedFileId",
			new String[] {Long.class.getName()},
			new String[] {"itemAttachedFileId"}, true);

		_finderPathCountByitemAttachedFileId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByitemAttachedFileId", new String[] {Long.class.getName()},
			new String[] {"itemAttachedFileId"}, false);

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

		_setItemAttachedFileUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setItemAttachedFileUtilPersistence(null);

		entityCache.removeCache(ItemAttachedFileImpl.class.getName());
	}

	private void _setItemAttachedFileUtilPersistence(
		ItemAttachedFilePersistence itemAttachedFilePersistence) {

		try {
			Field field = ItemAttachedFileUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, itemAttachedFilePersistence);
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

	private static final String _SQL_SELECT_ITEMATTACHEDFILE =
		"SELECT itemAttachedFile FROM ItemAttachedFile itemAttachedFile";

	private static final String _SQL_SELECT_ITEMATTACHEDFILE_WHERE =
		"SELECT itemAttachedFile FROM ItemAttachedFile itemAttachedFile WHERE ";

	private static final String _SQL_COUNT_ITEMATTACHEDFILE =
		"SELECT COUNT(itemAttachedFile) FROM ItemAttachedFile itemAttachedFile";

	private static final String _SQL_COUNT_ITEMATTACHEDFILE_WHERE =
		"SELECT COUNT(itemAttachedFile) FROM ItemAttachedFile itemAttachedFile WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "itemAttachedFile.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ItemAttachedFile exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ItemAttachedFile exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ItemAttachedFilePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private ItemAttachedFileModelArgumentsResolver
		_itemAttachedFileModelArgumentsResolver;

}