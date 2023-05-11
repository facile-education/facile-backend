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
import com.liferay.portal.kernel.util.SetUtil;

import com.weprode.nero.news.exception.NoSuchReadException;
import com.weprode.nero.news.model.NewsRead;
import com.weprode.nero.news.model.NewsReadTable;
import com.weprode.nero.news.model.impl.NewsReadImpl;
import com.weprode.nero.news.model.impl.NewsReadModelImpl;
import com.weprode.nero.news.service.persistence.NewsReadPK;
import com.weprode.nero.news.service.persistence.NewsReadPersistence;
import com.weprode.nero.news.service.persistence.NewsReadUtil;
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
 * The persistence implementation for the news read service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {NewsReadPersistence.class, BasePersistence.class})
public class NewsReadPersistenceImpl
	extends BasePersistenceImpl<NewsRead> implements NewsReadPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NewsReadUtil</code> to access the news read persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NewsReadImpl.class.getName();

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
	 * Returns all the news reads where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the matching news reads
	 */
	@Override
	public List<NewsRead> findBynewsId(long newsId) {
		return findBynewsId(newsId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the news reads where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @return the range of matching news reads
	 */
	@Override
	public List<NewsRead> findBynewsId(long newsId, int start, int end) {
		return findBynewsId(newsId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the news reads where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news reads
	 */
	@Override
	public List<NewsRead> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsRead> orderByComparator) {

		return findBynewsId(newsId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the news reads where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news reads
	 */
	@Override
	public List<NewsRead> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsRead> orderByComparator, boolean useFinderCache) {

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

		List<NewsRead> list = null;

		if (useFinderCache) {
			list = (List<NewsRead>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (NewsRead newsRead : list) {
					if (newsId != newsRead.getNewsId()) {
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

			sb.append(_SQL_SELECT_NEWSREAD_WHERE);

			sb.append(_FINDER_COLUMN_NEWSID_NEWSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NewsReadModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(newsId);

				list = (List<NewsRead>)QueryUtil.list(
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
	 * Returns the first news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news read
	 * @throws NoSuchReadException if a matching news read could not be found
	 */
	@Override
	public NewsRead findBynewsId_First(
			long newsId, OrderByComparator<NewsRead> orderByComparator)
		throws NoSuchReadException {

		NewsRead newsRead = fetchBynewsId_First(newsId, orderByComparator);

		if (newsRead != null) {
			return newsRead;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("newsId=");
		sb.append(newsId);

		sb.append("}");

		throw new NoSuchReadException(sb.toString());
	}

	/**
	 * Returns the first news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news read, or <code>null</code> if a matching news read could not be found
	 */
	@Override
	public NewsRead fetchBynewsId_First(
		long newsId, OrderByComparator<NewsRead> orderByComparator) {

		List<NewsRead> list = findBynewsId(newsId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news read
	 * @throws NoSuchReadException if a matching news read could not be found
	 */
	@Override
	public NewsRead findBynewsId_Last(
			long newsId, OrderByComparator<NewsRead> orderByComparator)
		throws NoSuchReadException {

		NewsRead newsRead = fetchBynewsId_Last(newsId, orderByComparator);

		if (newsRead != null) {
			return newsRead;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("newsId=");
		sb.append(newsId);

		sb.append("}");

		throw new NoSuchReadException(sb.toString());
	}

	/**
	 * Returns the last news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news read, or <code>null</code> if a matching news read could not be found
	 */
	@Override
	public NewsRead fetchBynewsId_Last(
		long newsId, OrderByComparator<NewsRead> orderByComparator) {

		int count = countBynewsId(newsId);

		if (count == 0) {
			return null;
		}

		List<NewsRead> list = findBynewsId(
			newsId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the news reads before and after the current news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsReadPK the primary key of the current news read
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news read
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	@Override
	public NewsRead[] findBynewsId_PrevAndNext(
			NewsReadPK newsReadPK, long newsId,
			OrderByComparator<NewsRead> orderByComparator)
		throws NoSuchReadException {

		NewsRead newsRead = findByPrimaryKey(newsReadPK);

		Session session = null;

		try {
			session = openSession();

			NewsRead[] array = new NewsReadImpl[3];

			array[0] = getBynewsId_PrevAndNext(
				session, newsRead, newsId, orderByComparator, true);

			array[1] = newsRead;

			array[2] = getBynewsId_PrevAndNext(
				session, newsRead, newsId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsRead getBynewsId_PrevAndNext(
		Session session, NewsRead newsRead, long newsId,
		OrderByComparator<NewsRead> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_NEWSREAD_WHERE);

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
			sb.append(NewsReadModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(newsId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(newsRead)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NewsRead> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the news reads where newsId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 */
	@Override
	public void removeBynewsId(long newsId) {
		for (NewsRead newsRead :
				findBynewsId(
					newsId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(newsRead);
		}
	}

	/**
	 * Returns the number of news reads where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the number of matching news reads
	 */
	@Override
	public int countBynewsId(long newsId) {
		FinderPath finderPath = _finderPathCountBynewsId;

		Object[] finderArgs = new Object[] {newsId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NEWSREAD_WHERE);

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
		"newsRead.id.newsId = ?";

	public NewsReadPersistenceImpl() {
		setModelClass(NewsRead.class);

		setModelImplClass(NewsReadImpl.class);
		setModelPKClass(NewsReadPK.class);

		setTable(NewsReadTable.INSTANCE);
	}

	/**
	 * Caches the news read in the entity cache if it is enabled.
	 *
	 * @param newsRead the news read
	 */
	@Override
	public void cacheResult(NewsRead newsRead) {
		entityCache.putResult(
			NewsReadImpl.class, newsRead.getPrimaryKey(), newsRead);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the news reads in the entity cache if it is enabled.
	 *
	 * @param newsReads the news reads
	 */
	@Override
	public void cacheResult(List<NewsRead> newsReads) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (newsReads.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (NewsRead newsRead : newsReads) {
			if (entityCache.getResult(
					NewsReadImpl.class, newsRead.getPrimaryKey()) == null) {

				cacheResult(newsRead);
			}
		}
	}

	/**
	 * Clears the cache for all news reads.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NewsReadImpl.class);

		finderCache.clearCache(NewsReadImpl.class);
	}

	/**
	 * Clears the cache for the news read.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NewsRead newsRead) {
		entityCache.removeResult(NewsReadImpl.class, newsRead);
	}

	@Override
	public void clearCache(List<NewsRead> newsReads) {
		for (NewsRead newsRead : newsReads) {
			entityCache.removeResult(NewsReadImpl.class, newsRead);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(NewsReadImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(NewsReadImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new news read with the primary key. Does not add the news read to the database.
	 *
	 * @param newsReadPK the primary key for the new news read
	 * @return the new news read
	 */
	@Override
	public NewsRead create(NewsReadPK newsReadPK) {
		NewsRead newsRead = new NewsReadImpl();

		newsRead.setNew(true);
		newsRead.setPrimaryKey(newsReadPK);

		return newsRead;
	}

	/**
	 * Removes the news read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read that was removed
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	@Override
	public NewsRead remove(NewsReadPK newsReadPK) throws NoSuchReadException {
		return remove((Serializable)newsReadPK);
	}

	/**
	 * Removes the news read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the news read
	 * @return the news read that was removed
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	@Override
	public NewsRead remove(Serializable primaryKey) throws NoSuchReadException {
		Session session = null;

		try {
			session = openSession();

			NewsRead newsRead = (NewsRead)session.get(
				NewsReadImpl.class, primaryKey);

			if (newsRead == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReadException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(newsRead);
		}
		catch (NoSuchReadException noSuchEntityException) {
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
	protected NewsRead removeImpl(NewsRead newsRead) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(newsRead)) {
				newsRead = (NewsRead)session.get(
					NewsReadImpl.class, newsRead.getPrimaryKeyObj());
			}

			if (newsRead != null) {
				session.delete(newsRead);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (newsRead != null) {
			clearCache(newsRead);
		}

		return newsRead;
	}

	@Override
	public NewsRead updateImpl(NewsRead newsRead) {
		boolean isNew = newsRead.isNew();

		if (!(newsRead instanceof NewsReadModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(newsRead.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(newsRead);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in newsRead proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom NewsRead implementation " +
					newsRead.getClass());
		}

		NewsReadModelImpl newsReadModelImpl = (NewsReadModelImpl)newsRead;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(newsRead);
			}
			else {
				newsRead = (NewsRead)session.merge(newsRead);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			NewsReadImpl.class, newsReadModelImpl, false, true);

		if (isNew) {
			newsRead.setNew(false);
		}

		newsRead.resetOriginalValues();

		return newsRead;
	}

	/**
	 * Returns the news read with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the news read
	 * @return the news read
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	@Override
	public NewsRead findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReadException {

		NewsRead newsRead = fetchByPrimaryKey(primaryKey);

		if (newsRead == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReadException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return newsRead;
	}

	/**
	 * Returns the news read with the primary key or throws a <code>NoSuchReadException</code> if it could not be found.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	@Override
	public NewsRead findByPrimaryKey(NewsReadPK newsReadPK)
		throws NoSuchReadException {

		return findByPrimaryKey((Serializable)newsReadPK);
	}

	/**
	 * Returns the news read with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read, or <code>null</code> if a news read with the primary key could not be found
	 */
	@Override
	public NewsRead fetchByPrimaryKey(NewsReadPK newsReadPK) {
		return fetchByPrimaryKey((Serializable)newsReadPK);
	}

	/**
	 * Returns all the news reads.
	 *
	 * @return the news reads
	 */
	@Override
	public List<NewsRead> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the news reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @return the range of news reads
	 */
	@Override
	public List<NewsRead> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the news reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of news reads
	 */
	@Override
	public List<NewsRead> findAll(
		int start, int end, OrderByComparator<NewsRead> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the news reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of news reads
	 */
	@Override
	public List<NewsRead> findAll(
		int start, int end, OrderByComparator<NewsRead> orderByComparator,
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

		List<NewsRead> list = null;

		if (useFinderCache) {
			list = (List<NewsRead>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NEWSREAD);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NEWSREAD;

				sql = sql.concat(NewsReadModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<NewsRead>)QueryUtil.list(
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
	 * Removes all the news reads from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NewsRead newsRead : findAll()) {
			remove(newsRead);
		}
	}

	/**
	 * Returns the number of news reads.
	 *
	 * @return the number of news reads
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_NEWSREAD);

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
		return "newsReadPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NEWSREAD;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NewsReadModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the news read persistence.
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

		_setNewsReadUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setNewsReadUtilPersistence(null);

		entityCache.removeCache(NewsReadImpl.class.getName());
	}

	private void _setNewsReadUtilPersistence(
		NewsReadPersistence newsReadPersistence) {

		try {
			Field field = NewsReadUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, newsReadPersistence);
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

	private static final String _SQL_SELECT_NEWSREAD =
		"SELECT newsRead FROM NewsRead newsRead";

	private static final String _SQL_SELECT_NEWSREAD_WHERE =
		"SELECT newsRead FROM NewsRead newsRead WHERE ";

	private static final String _SQL_COUNT_NEWSREAD =
		"SELECT COUNT(newsRead) FROM NewsRead newsRead";

	private static final String _SQL_COUNT_NEWSREAD_WHERE =
		"SELECT COUNT(newsRead) FROM NewsRead newsRead WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "newsRead.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No NewsRead exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No NewsRead exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NewsReadPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"newsId", "userId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private NewsReadModelArgumentsResolver _newsReadModelArgumentsResolver;

}