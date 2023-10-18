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

package com.weprode.nero.news.service.persistence.impl;

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

import com.weprode.nero.news.exception.NoSuchAttachedFileException;
import com.weprode.nero.news.model.NewsAttachedFile;
import com.weprode.nero.news.model.NewsAttachedFileTable;
import com.weprode.nero.news.model.impl.NewsAttachedFileImpl;
import com.weprode.nero.news.model.impl.NewsAttachedFileModelImpl;
import com.weprode.nero.news.service.persistence.NewsAttachedFilePersistence;
import com.weprode.nero.news.service.persistence.NewsAttachedFileUtil;
import com.weprode.nero.news.service.persistence.impl.constants.NewsPersistenceConstants;

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
 * The persistence implementation for the news attached file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {NewsAttachedFilePersistence.class, BasePersistence.class})
public class NewsAttachedFilePersistenceImpl
	extends BasePersistenceImpl<NewsAttachedFile>
	implements NewsAttachedFilePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NewsAttachedFileUtil</code> to access the news attached file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NewsAttachedFileImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBynewsId;
	private FinderPath _finderPathWithoutPaginationFindBynewsId;
	private FinderPath _finderPathCountBynewsId;

	/**
	 * Returns all the news attached files where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the matching news attached files
	 */
	@Override
	public List<NewsAttachedFile> findBynewsId(long newsId) {
		return findBynewsId(newsId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the news attached files where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @return the range of matching news attached files
	 */
	@Override
	public List<NewsAttachedFile> findBynewsId(
		long newsId, int start, int end) {

		return findBynewsId(newsId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the news attached files where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news attached files
	 */
	@Override
	public List<NewsAttachedFile> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator) {

		return findBynewsId(newsId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the news attached files where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news attached files
	 */
	@Override
	public List<NewsAttachedFile> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBynewsId;
				finderArgs = new Object[] {newsId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBynewsId;
			finderArgs = new Object[] {newsId, start, end, orderByComparator};
		}

		List<NewsAttachedFile> list = null;

		if (useFinderCache) {
			list = (List<NewsAttachedFile>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NewsAttachedFile newsAttachedFile : list) {
					if (newsId != newsAttachedFile.getNewsId()) {
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

			sb.append(_SQL_SELECT_NEWSATTACHEDFILE_WHERE);

			sb.append(_FINDER_COLUMN_NEWSID_NEWSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NewsAttachedFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(newsId);

				list = (List<NewsAttachedFile>)QueryUtil.list(
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
	 * Returns the first news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news attached file
	 * @throws NoSuchAttachedFileException if a matching news attached file could not be found
	 */
	@Override
	public NewsAttachedFile findBynewsId_First(
			long newsId, OrderByComparator<NewsAttachedFile> orderByComparator)
		throws NoSuchAttachedFileException {

		NewsAttachedFile newsAttachedFile = fetchBynewsId_First(
			newsId, orderByComparator);

		if (newsAttachedFile != null) {
			return newsAttachedFile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("newsId=");
		sb.append(newsId);

		sb.append("}");

		throw new NoSuchAttachedFileException(sb.toString());
	}

	/**
	 * Returns the first news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news attached file, or <code>null</code> if a matching news attached file could not be found
	 */
	@Override
	public NewsAttachedFile fetchBynewsId_First(
		long newsId, OrderByComparator<NewsAttachedFile> orderByComparator) {

		List<NewsAttachedFile> list = findBynewsId(
			newsId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news attached file
	 * @throws NoSuchAttachedFileException if a matching news attached file could not be found
	 */
	@Override
	public NewsAttachedFile findBynewsId_Last(
			long newsId, OrderByComparator<NewsAttachedFile> orderByComparator)
		throws NoSuchAttachedFileException {

		NewsAttachedFile newsAttachedFile = fetchBynewsId_Last(
			newsId, orderByComparator);

		if (newsAttachedFile != null) {
			return newsAttachedFile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("newsId=");
		sb.append(newsId);

		sb.append("}");

		throw new NoSuchAttachedFileException(sb.toString());
	}

	/**
	 * Returns the last news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news attached file, or <code>null</code> if a matching news attached file could not be found
	 */
	@Override
	public NewsAttachedFile fetchBynewsId_Last(
		long newsId, OrderByComparator<NewsAttachedFile> orderByComparator) {

		int count = countBynewsId(newsId);

		if (count == 0) {
			return null;
		}

		List<NewsAttachedFile> list = findBynewsId(
			newsId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the news attached files before and after the current news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsFileId the primary key of the current news attached file
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news attached file
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	@Override
	public NewsAttachedFile[] findBynewsId_PrevAndNext(
			long newsFileId, long newsId,
			OrderByComparator<NewsAttachedFile> orderByComparator)
		throws NoSuchAttachedFileException {

		NewsAttachedFile newsAttachedFile = findByPrimaryKey(newsFileId);

		Session session = null;

		try {
			session = openSession();

			NewsAttachedFile[] array = new NewsAttachedFileImpl[3];

			array[0] = getBynewsId_PrevAndNext(
				session, newsAttachedFile, newsId, orderByComparator, true);

			array[1] = newsAttachedFile;

			array[2] = getBynewsId_PrevAndNext(
				session, newsAttachedFile, newsId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsAttachedFile getBynewsId_PrevAndNext(
		Session session, NewsAttachedFile newsAttachedFile, long newsId,
		OrderByComparator<NewsAttachedFile> orderByComparator,
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

		sb.append(_SQL_SELECT_NEWSATTACHEDFILE_WHERE);

		sb.append(_FINDER_COLUMN_NEWSID_NEWSID_2);

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
			sb.append(NewsAttachedFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(newsId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						newsAttachedFile)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NewsAttachedFile> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the news attached files where newsId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 */
	@Override
	public void removeBynewsId(long newsId) {
		for (NewsAttachedFile newsAttachedFile :
				findBynewsId(
					newsId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(newsAttachedFile);
		}
	}

	/**
	 * Returns the number of news attached files where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the number of matching news attached files
	 */
	@Override
	public int countBynewsId(long newsId) {
		FinderPath finderPath = _finderPathCountBynewsId;

		Object[] finderArgs = new Object[] {newsId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NEWSATTACHEDFILE_WHERE);

			sb.append(_FINDER_COLUMN_NEWSID_NEWSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(newsId);

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

	private static final String _FINDER_COLUMN_NEWSID_NEWSID_2 =
		"newsAttachedFile.newsId = ?";

	private FinderPath _finderPathWithPaginationFindBynewsId_groupId;
	private FinderPath _finderPathWithoutPaginationFindBynewsId_groupId;
	private FinderPath _finderPathCountBynewsId_groupId;

	/**
	 * Returns all the news attached files where newsId = &#63; and groupId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @return the matching news attached files
	 */
	@Override
	public List<NewsAttachedFile> findBynewsId_groupId(
		long newsId, long groupId) {

		return findBynewsId_groupId(
			newsId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the news attached files where newsId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @return the range of matching news attached files
	 */
	@Override
	public List<NewsAttachedFile> findBynewsId_groupId(
		long newsId, long groupId, int start, int end) {

		return findBynewsId_groupId(newsId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the news attached files where newsId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news attached files
	 */
	@Override
	public List<NewsAttachedFile> findBynewsId_groupId(
		long newsId, long groupId, int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator) {

		return findBynewsId_groupId(
			newsId, groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the news attached files where newsId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news attached files
	 */
	@Override
	public List<NewsAttachedFile> findBynewsId_groupId(
		long newsId, long groupId, int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBynewsId_groupId;
				finderArgs = new Object[] {newsId, groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBynewsId_groupId;
			finderArgs = new Object[] {
				newsId, groupId, start, end, orderByComparator
			};
		}

		List<NewsAttachedFile> list = null;

		if (useFinderCache) {
			list = (List<NewsAttachedFile>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NewsAttachedFile newsAttachedFile : list) {
					if ((newsId != newsAttachedFile.getNewsId()) ||
						(groupId != newsAttachedFile.getGroupId())) {

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

			sb.append(_SQL_SELECT_NEWSATTACHEDFILE_WHERE);

			sb.append(_FINDER_COLUMN_NEWSID_GROUPID_NEWSID_2);

			sb.append(_FINDER_COLUMN_NEWSID_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NewsAttachedFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(newsId);

				queryPos.add(groupId);

				list = (List<NewsAttachedFile>)QueryUtil.list(
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
	 * Returns the first news attached file in the ordered set where newsId = &#63; and groupId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news attached file
	 * @throws NoSuchAttachedFileException if a matching news attached file could not be found
	 */
	@Override
	public NewsAttachedFile findBynewsId_groupId_First(
			long newsId, long groupId,
			OrderByComparator<NewsAttachedFile> orderByComparator)
		throws NoSuchAttachedFileException {

		NewsAttachedFile newsAttachedFile = fetchBynewsId_groupId_First(
			newsId, groupId, orderByComparator);

		if (newsAttachedFile != null) {
			return newsAttachedFile;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("newsId=");
		sb.append(newsId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchAttachedFileException(sb.toString());
	}

	/**
	 * Returns the first news attached file in the ordered set where newsId = &#63; and groupId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news attached file, or <code>null</code> if a matching news attached file could not be found
	 */
	@Override
	public NewsAttachedFile fetchBynewsId_groupId_First(
		long newsId, long groupId,
		OrderByComparator<NewsAttachedFile> orderByComparator) {

		List<NewsAttachedFile> list = findBynewsId_groupId(
			newsId, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last news attached file in the ordered set where newsId = &#63; and groupId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news attached file
	 * @throws NoSuchAttachedFileException if a matching news attached file could not be found
	 */
	@Override
	public NewsAttachedFile findBynewsId_groupId_Last(
			long newsId, long groupId,
			OrderByComparator<NewsAttachedFile> orderByComparator)
		throws NoSuchAttachedFileException {

		NewsAttachedFile newsAttachedFile = fetchBynewsId_groupId_Last(
			newsId, groupId, orderByComparator);

		if (newsAttachedFile != null) {
			return newsAttachedFile;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("newsId=");
		sb.append(newsId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchAttachedFileException(sb.toString());
	}

	/**
	 * Returns the last news attached file in the ordered set where newsId = &#63; and groupId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news attached file, or <code>null</code> if a matching news attached file could not be found
	 */
	@Override
	public NewsAttachedFile fetchBynewsId_groupId_Last(
		long newsId, long groupId,
		OrderByComparator<NewsAttachedFile> orderByComparator) {

		int count = countBynewsId_groupId(newsId, groupId);

		if (count == 0) {
			return null;
		}

		List<NewsAttachedFile> list = findBynewsId_groupId(
			newsId, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the news attached files before and after the current news attached file in the ordered set where newsId = &#63; and groupId = &#63;.
	 *
	 * @param newsFileId the primary key of the current news attached file
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news attached file
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	@Override
	public NewsAttachedFile[] findBynewsId_groupId_PrevAndNext(
			long newsFileId, long newsId, long groupId,
			OrderByComparator<NewsAttachedFile> orderByComparator)
		throws NoSuchAttachedFileException {

		NewsAttachedFile newsAttachedFile = findByPrimaryKey(newsFileId);

		Session session = null;

		try {
			session = openSession();

			NewsAttachedFile[] array = new NewsAttachedFileImpl[3];

			array[0] = getBynewsId_groupId_PrevAndNext(
				session, newsAttachedFile, newsId, groupId, orderByComparator,
				true);

			array[1] = newsAttachedFile;

			array[2] = getBynewsId_groupId_PrevAndNext(
				session, newsAttachedFile, newsId, groupId, orderByComparator,
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

	protected NewsAttachedFile getBynewsId_groupId_PrevAndNext(
		Session session, NewsAttachedFile newsAttachedFile, long newsId,
		long groupId, OrderByComparator<NewsAttachedFile> orderByComparator,
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

		sb.append(_SQL_SELECT_NEWSATTACHEDFILE_WHERE);

		sb.append(_FINDER_COLUMN_NEWSID_GROUPID_NEWSID_2);

		sb.append(_FINDER_COLUMN_NEWSID_GROUPID_GROUPID_2);

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
			sb.append(NewsAttachedFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(newsId);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						newsAttachedFile)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NewsAttachedFile> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the news attached files where newsId = &#63; and groupId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeBynewsId_groupId(long newsId, long groupId) {
		for (NewsAttachedFile newsAttachedFile :
				findBynewsId_groupId(
					newsId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(newsAttachedFile);
		}
	}

	/**
	 * Returns the number of news attached files where newsId = &#63; and groupId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param groupId the group ID
	 * @return the number of matching news attached files
	 */
	@Override
	public int countBynewsId_groupId(long newsId, long groupId) {
		FinderPath finderPath = _finderPathCountBynewsId_groupId;

		Object[] finderArgs = new Object[] {newsId, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_NEWSATTACHEDFILE_WHERE);

			sb.append(_FINDER_COLUMN_NEWSID_GROUPID_NEWSID_2);

			sb.append(_FINDER_COLUMN_NEWSID_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(newsId);

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

	private static final String _FINDER_COLUMN_NEWSID_GROUPID_NEWSID_2 =
		"newsAttachedFile.newsId = ? AND ";

	private static final String _FINDER_COLUMN_NEWSID_GROUPID_GROUPID_2 =
		"newsAttachedFile.groupId = ?";

	public NewsAttachedFilePersistenceImpl() {
		setModelClass(NewsAttachedFile.class);

		setModelImplClass(NewsAttachedFileImpl.class);
		setModelPKClass(long.class);

		setTable(NewsAttachedFileTable.INSTANCE);
	}

	/**
	 * Caches the news attached file in the entity cache if it is enabled.
	 *
	 * @param newsAttachedFile the news attached file
	 */
	@Override
	public void cacheResult(NewsAttachedFile newsAttachedFile) {
		entityCache.putResult(
			NewsAttachedFileImpl.class, newsAttachedFile.getPrimaryKey(),
			newsAttachedFile);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the news attached files in the entity cache if it is enabled.
	 *
	 * @param newsAttachedFiles the news attached files
	 */
	@Override
	public void cacheResult(List<NewsAttachedFile> newsAttachedFiles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (newsAttachedFiles.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (NewsAttachedFile newsAttachedFile : newsAttachedFiles) {
			if (entityCache.getResult(
					NewsAttachedFileImpl.class,
					newsAttachedFile.getPrimaryKey()) == null) {

				cacheResult(newsAttachedFile);
			}
		}
	}

	/**
	 * Clears the cache for all news attached files.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NewsAttachedFileImpl.class);

		finderCache.clearCache(NewsAttachedFileImpl.class);
	}

	/**
	 * Clears the cache for the news attached file.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NewsAttachedFile newsAttachedFile) {
		entityCache.removeResult(NewsAttachedFileImpl.class, newsAttachedFile);
	}

	@Override
	public void clearCache(List<NewsAttachedFile> newsAttachedFiles) {
		for (NewsAttachedFile newsAttachedFile : newsAttachedFiles) {
			entityCache.removeResult(
				NewsAttachedFileImpl.class, newsAttachedFile);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(NewsAttachedFileImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(NewsAttachedFileImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new news attached file with the primary key. Does not add the news attached file to the database.
	 *
	 * @param newsFileId the primary key for the new news attached file
	 * @return the new news attached file
	 */
	@Override
	public NewsAttachedFile create(long newsFileId) {
		NewsAttachedFile newsAttachedFile = new NewsAttachedFileImpl();

		newsAttachedFile.setNew(true);
		newsAttachedFile.setPrimaryKey(newsFileId);

		return newsAttachedFile;
	}

	/**
	 * Removes the news attached file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsFileId the primary key of the news attached file
	 * @return the news attached file that was removed
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	@Override
	public NewsAttachedFile remove(long newsFileId)
		throws NoSuchAttachedFileException {

		return remove((Serializable)newsFileId);
	}

	/**
	 * Removes the news attached file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the news attached file
	 * @return the news attached file that was removed
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	@Override
	public NewsAttachedFile remove(Serializable primaryKey)
		throws NoSuchAttachedFileException {

		Session session = null;

		try {
			session = openSession();

			NewsAttachedFile newsAttachedFile = (NewsAttachedFile)session.get(
				NewsAttachedFileImpl.class, primaryKey);

			if (newsAttachedFile == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAttachedFileException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(newsAttachedFile);
		}
		catch (NoSuchAttachedFileException noSuchEntityException) {
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
	protected NewsAttachedFile removeImpl(NewsAttachedFile newsAttachedFile) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(newsAttachedFile)) {
				newsAttachedFile = (NewsAttachedFile)session.get(
					NewsAttachedFileImpl.class,
					newsAttachedFile.getPrimaryKeyObj());
			}

			if (newsAttachedFile != null) {
				session.delete(newsAttachedFile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (newsAttachedFile != null) {
			clearCache(newsAttachedFile);
		}

		return newsAttachedFile;
	}

	@Override
	public NewsAttachedFile updateImpl(NewsAttachedFile newsAttachedFile) {
		boolean isNew = newsAttachedFile.isNew();

		if (!(newsAttachedFile instanceof NewsAttachedFileModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(newsAttachedFile.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					newsAttachedFile);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in newsAttachedFile proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom NewsAttachedFile implementation " +
					newsAttachedFile.getClass());
		}

		NewsAttachedFileModelImpl newsAttachedFileModelImpl =
			(NewsAttachedFileModelImpl)newsAttachedFile;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(newsAttachedFile);
			}
			else {
				newsAttachedFile = (NewsAttachedFile)session.merge(
					newsAttachedFile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			NewsAttachedFileImpl.class, newsAttachedFileModelImpl, false, true);

		if (isNew) {
			newsAttachedFile.setNew(false);
		}

		newsAttachedFile.resetOriginalValues();

		return newsAttachedFile;
	}

	/**
	 * Returns the news attached file with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the news attached file
	 * @return the news attached file
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	@Override
	public NewsAttachedFile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAttachedFileException {

		NewsAttachedFile newsAttachedFile = fetchByPrimaryKey(primaryKey);

		if (newsAttachedFile == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAttachedFileException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return newsAttachedFile;
	}

	/**
	 * Returns the news attached file with the primary key or throws a <code>NoSuchAttachedFileException</code> if it could not be found.
	 *
	 * @param newsFileId the primary key of the news attached file
	 * @return the news attached file
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	@Override
	public NewsAttachedFile findByPrimaryKey(long newsFileId)
		throws NoSuchAttachedFileException {

		return findByPrimaryKey((Serializable)newsFileId);
	}

	/**
	 * Returns the news attached file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsFileId the primary key of the news attached file
	 * @return the news attached file, or <code>null</code> if a news attached file with the primary key could not be found
	 */
	@Override
	public NewsAttachedFile fetchByPrimaryKey(long newsFileId) {
		return fetchByPrimaryKey((Serializable)newsFileId);
	}

	/**
	 * Returns all the news attached files.
	 *
	 * @return the news attached files
	 */
	@Override
	public List<NewsAttachedFile> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the news attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @return the range of news attached files
	 */
	@Override
	public List<NewsAttachedFile> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the news attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of news attached files
	 */
	@Override
	public List<NewsAttachedFile> findAll(
		int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the news attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of news attached files
	 */
	@Override
	public List<NewsAttachedFile> findAll(
		int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator,
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

		List<NewsAttachedFile> list = null;

		if (useFinderCache) {
			list = (List<NewsAttachedFile>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NEWSATTACHEDFILE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NEWSATTACHEDFILE;

				sql = sql.concat(NewsAttachedFileModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<NewsAttachedFile>)QueryUtil.list(
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
	 * Removes all the news attached files from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NewsAttachedFile newsAttachedFile : findAll()) {
			remove(newsAttachedFile);
		}
	}

	/**
	 * Returns the number of news attached files.
	 *
	 * @return the number of news attached files
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_NEWSATTACHEDFILE);

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
		return "newsFileId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NEWSATTACHEDFILE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NewsAttachedFileModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the news attached file persistence.
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

		_finderPathWithPaginationFindBynewsId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBynewsId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"newsId"}, true);

		_finderPathWithoutPaginationFindBynewsId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBynewsId",
			new String[] {Long.class.getName()}, new String[] {"newsId"}, true);

		_finderPathCountBynewsId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBynewsId",
			new String[] {Long.class.getName()}, new String[] {"newsId"},
			false);

		_finderPathWithPaginationFindBynewsId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBynewsId_groupId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"newsId", "groupId"}, true);

		_finderPathWithoutPaginationFindBynewsId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBynewsId_groupId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"newsId", "groupId"}, true);

		_finderPathCountBynewsId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBynewsId_groupId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"newsId", "groupId"}, false);

		_setNewsAttachedFileUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setNewsAttachedFileUtilPersistence(null);

		entityCache.removeCache(NewsAttachedFileImpl.class.getName());
	}

	private void _setNewsAttachedFileUtilPersistence(
		NewsAttachedFilePersistence newsAttachedFilePersistence) {

		try {
			Field field = NewsAttachedFileUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, newsAttachedFilePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = NewsPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = NewsPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = NewsPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_NEWSATTACHEDFILE =
		"SELECT newsAttachedFile FROM NewsAttachedFile newsAttachedFile";

	private static final String _SQL_SELECT_NEWSATTACHEDFILE_WHERE =
		"SELECT newsAttachedFile FROM NewsAttachedFile newsAttachedFile WHERE ";

	private static final String _SQL_COUNT_NEWSATTACHEDFILE =
		"SELECT COUNT(newsAttachedFile) FROM NewsAttachedFile newsAttachedFile";

	private static final String _SQL_COUNT_NEWSATTACHEDFILE_WHERE =
		"SELECT COUNT(newsAttachedFile) FROM NewsAttachedFile newsAttachedFile WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "newsAttachedFile.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No NewsAttachedFile exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No NewsAttachedFile exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NewsAttachedFilePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}