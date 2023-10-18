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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import com.weprode.nero.news.exception.NoSuchNewsException;
import com.weprode.nero.news.model.News;
import com.weprode.nero.news.model.NewsTable;
import com.weprode.nero.news.model.impl.NewsImpl;
import com.weprode.nero.news.model.impl.NewsModelImpl;
import com.weprode.nero.news.service.persistence.NewsPersistence;
import com.weprode.nero.news.service.persistence.NewsUtil;
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
 * The persistence implementation for the news service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {NewsPersistence.class, BasePersistence.class})
public class NewsPersistenceImpl
	extends BasePersistenceImpl<News> implements NewsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NewsUtil</code> to access the news persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NewsImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByauthorId;
	private FinderPath _finderPathWithoutPaginationFindByauthorId;
	private FinderPath _finderPathCountByauthorId;

	/**
	 * Returns all the newses where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @return the matching newses
	 */
	@Override
	public List<News> findByauthorId(long authorId) {
		return findByauthorId(
			authorId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the newses where authorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param authorId the author ID
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @return the range of matching newses
	 */
	@Override
	public List<News> findByauthorId(long authorId, int start, int end) {
		return findByauthorId(authorId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the newses where authorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param authorId the author ID
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching newses
	 */
	@Override
	public List<News> findByauthorId(
		long authorId, int start, int end,
		OrderByComparator<News> orderByComparator) {

		return findByauthorId(authorId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the newses where authorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param authorId the author ID
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching newses
	 */
	@Override
	public List<News> findByauthorId(
		long authorId, int start, int end,
		OrderByComparator<News> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByauthorId;
				finderArgs = new Object[] {authorId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByauthorId;
			finderArgs = new Object[] {authorId, start, end, orderByComparator};
		}

		List<News> list = null;

		if (useFinderCache) {
			list = (List<News>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (News news : list) {
					if (authorId != news.getAuthorId()) {
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

			sb.append(_SQL_SELECT_NEWS_WHERE);

			sb.append(_FINDER_COLUMN_AUTHORID_AUTHORID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NewsModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(authorId);

				list = (List<News>)QueryUtil.list(
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
	 * Returns the first news in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news
	 * @throws NoSuchNewsException if a matching news could not be found
	 */
	@Override
	public News findByauthorId_First(
			long authorId, OrderByComparator<News> orderByComparator)
		throws NoSuchNewsException {

		News news = fetchByauthorId_First(authorId, orderByComparator);

		if (news != null) {
			return news;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("authorId=");
		sb.append(authorId);

		sb.append("}");

		throw new NoSuchNewsException(sb.toString());
	}

	/**
	 * Returns the first news in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news, or <code>null</code> if a matching news could not be found
	 */
	@Override
	public News fetchByauthorId_First(
		long authorId, OrderByComparator<News> orderByComparator) {

		List<News> list = findByauthorId(authorId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last news in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news
	 * @throws NoSuchNewsException if a matching news could not be found
	 */
	@Override
	public News findByauthorId_Last(
			long authorId, OrderByComparator<News> orderByComparator)
		throws NoSuchNewsException {

		News news = fetchByauthorId_Last(authorId, orderByComparator);

		if (news != null) {
			return news;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("authorId=");
		sb.append(authorId);

		sb.append("}");

		throw new NoSuchNewsException(sb.toString());
	}

	/**
	 * Returns the last news in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news, or <code>null</code> if a matching news could not be found
	 */
	@Override
	public News fetchByauthorId_Last(
		long authorId, OrderByComparator<News> orderByComparator) {

		int count = countByauthorId(authorId);

		if (count == 0) {
			return null;
		}

		List<News> list = findByauthorId(
			authorId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the newses before and after the current news in the ordered set where authorId = &#63;.
	 *
	 * @param newsId the primary key of the current news
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	@Override
	public News[] findByauthorId_PrevAndNext(
			long newsId, long authorId,
			OrderByComparator<News> orderByComparator)
		throws NoSuchNewsException {

		News news = findByPrimaryKey(newsId);

		Session session = null;

		try {
			session = openSession();

			News[] array = new NewsImpl[3];

			array[0] = getByauthorId_PrevAndNext(
				session, news, authorId, orderByComparator, true);

			array[1] = news;

			array[2] = getByauthorId_PrevAndNext(
				session, news, authorId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected News getByauthorId_PrevAndNext(
		Session session, News news, long authorId,
		OrderByComparator<News> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_NEWS_WHERE);

		sb.append(_FINDER_COLUMN_AUTHORID_AUTHORID_2);

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
			sb.append(NewsModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(authorId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(news)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<News> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the newses where authorId = &#63; from the database.
	 *
	 * @param authorId the author ID
	 */
	@Override
	public void removeByauthorId(long authorId) {
		for (News news :
				findByauthorId(
					authorId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(news);
		}
	}

	/**
	 * Returns the number of newses where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @return the number of matching newses
	 */
	@Override
	public int countByauthorId(long authorId) {
		FinderPath finderPath = _finderPathCountByauthorId;

		Object[] finderArgs = new Object[] {authorId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NEWS_WHERE);

			sb.append(_FINDER_COLUMN_AUTHORID_AUTHORID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(authorId);

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

	private static final String _FINDER_COLUMN_AUTHORID_AUTHORID_2 =
		"news.authorId = ?";

	public NewsPersistenceImpl() {
		setModelClass(News.class);

		setModelImplClass(NewsImpl.class);
		setModelPKClass(long.class);

		setTable(NewsTable.INSTANCE);
	}

	/**
	 * Caches the news in the entity cache if it is enabled.
	 *
	 * @param news the news
	 */
	@Override
	public void cacheResult(News news) {
		entityCache.putResult(NewsImpl.class, news.getPrimaryKey(), news);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the newses in the entity cache if it is enabled.
	 *
	 * @param newses the newses
	 */
	@Override
	public void cacheResult(List<News> newses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (newses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (News news : newses) {
			if (entityCache.getResult(NewsImpl.class, news.getPrimaryKey()) ==
					null) {

				cacheResult(news);
			}
		}
	}

	/**
	 * Clears the cache for all newses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NewsImpl.class);

		finderCache.clearCache(NewsImpl.class);
	}

	/**
	 * Clears the cache for the news.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(News news) {
		entityCache.removeResult(NewsImpl.class, news);
	}

	@Override
	public void clearCache(List<News> newses) {
		for (News news : newses) {
			entityCache.removeResult(NewsImpl.class, news);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(NewsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(NewsImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new news with the primary key. Does not add the news to the database.
	 *
	 * @param newsId the primary key for the new news
	 * @return the new news
	 */
	@Override
	public News create(long newsId) {
		News news = new NewsImpl();

		news.setNew(true);
		news.setPrimaryKey(newsId);

		news.setCompanyId(CompanyThreadLocal.getCompanyId());

		return news;
	}

	/**
	 * Removes the news with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsId the primary key of the news
	 * @return the news that was removed
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	@Override
	public News remove(long newsId) throws NoSuchNewsException {
		return remove((Serializable)newsId);
	}

	/**
	 * Removes the news with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the news
	 * @return the news that was removed
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	@Override
	public News remove(Serializable primaryKey) throws NoSuchNewsException {
		Session session = null;

		try {
			session = openSession();

			News news = (News)session.get(NewsImpl.class, primaryKey);

			if (news == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNewsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(news);
		}
		catch (NoSuchNewsException noSuchEntityException) {
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
	protected News removeImpl(News news) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(news)) {
				news = (News)session.get(
					NewsImpl.class, news.getPrimaryKeyObj());
			}

			if (news != null) {
				session.delete(news);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (news != null) {
			clearCache(news);
		}

		return news;
	}

	@Override
	public News updateImpl(News news) {
		boolean isNew = news.isNew();

		if (!(news instanceof NewsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(news.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(news);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in news proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom News implementation " +
					news.getClass());
		}

		NewsModelImpl newsModelImpl = (NewsModelImpl)news;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(news);
			}
			else {
				news = (News)session.merge(news);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(NewsImpl.class, newsModelImpl, false, true);

		if (isNew) {
			news.setNew(false);
		}

		news.resetOriginalValues();

		return news;
	}

	/**
	 * Returns the news with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the news
	 * @return the news
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	@Override
	public News findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNewsException {

		News news = fetchByPrimaryKey(primaryKey);

		if (news == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNewsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return news;
	}

	/**
	 * Returns the news with the primary key or throws a <code>NoSuchNewsException</code> if it could not be found.
	 *
	 * @param newsId the primary key of the news
	 * @return the news
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	@Override
	public News findByPrimaryKey(long newsId) throws NoSuchNewsException {
		return findByPrimaryKey((Serializable)newsId);
	}

	/**
	 * Returns the news with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsId the primary key of the news
	 * @return the news, or <code>null</code> if a news with the primary key could not be found
	 */
	@Override
	public News fetchByPrimaryKey(long newsId) {
		return fetchByPrimaryKey((Serializable)newsId);
	}

	/**
	 * Returns all the newses.
	 *
	 * @return the newses
	 */
	@Override
	public List<News> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @return the range of newses
	 */
	@Override
	public List<News> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of newses
	 */
	@Override
	public List<News> findAll(
		int start, int end, OrderByComparator<News> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of newses
	 */
	@Override
	public List<News> findAll(
		int start, int end, OrderByComparator<News> orderByComparator,
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

		List<News> list = null;

		if (useFinderCache) {
			list = (List<News>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NEWS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NEWS;

				sql = sql.concat(NewsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<News>)QueryUtil.list(
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
	 * Removes all the newses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (News news : findAll()) {
			remove(news);
		}
	}

	/**
	 * Returns the number of newses.
	 *
	 * @return the number of newses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_NEWS);

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
		return "newsId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NEWS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NewsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the news persistence.
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

		_finderPathWithPaginationFindByauthorId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByauthorId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"authorId"}, true);

		_finderPathWithoutPaginationFindByauthorId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByauthorId",
			new String[] {Long.class.getName()}, new String[] {"authorId"},
			true);

		_finderPathCountByauthorId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByauthorId",
			new String[] {Long.class.getName()}, new String[] {"authorId"},
			false);

		_setNewsUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setNewsUtilPersistence(null);

		entityCache.removeCache(NewsImpl.class.getName());
	}

	private void _setNewsUtilPersistence(NewsPersistence newsPersistence) {
		try {
			Field field = NewsUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, newsPersistence);
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

	private static final String _SQL_SELECT_NEWS = "SELECT news FROM News news";

	private static final String _SQL_SELECT_NEWS_WHERE =
		"SELECT news FROM News news WHERE ";

	private static final String _SQL_COUNT_NEWS =
		"SELECT COUNT(news) FROM News news";

	private static final String _SQL_COUNT_NEWS_WHERE =
		"SELECT COUNT(news) FROM News news WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "news.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No News exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No News exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NewsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}