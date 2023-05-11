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

import com.weprode.nero.news.exception.NoSuchPopulationException;
import com.weprode.nero.news.model.NewsPopulation;
import com.weprode.nero.news.model.NewsPopulationTable;
import com.weprode.nero.news.model.impl.NewsPopulationImpl;
import com.weprode.nero.news.model.impl.NewsPopulationModelImpl;
import com.weprode.nero.news.service.persistence.NewsPopulationPK;
import com.weprode.nero.news.service.persistence.NewsPopulationPersistence;
import com.weprode.nero.news.service.persistence.NewsPopulationUtil;
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
 * The persistence implementation for the news population service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {NewsPopulationPersistence.class, BasePersistence.class})
public class NewsPopulationPersistenceImpl
	extends BasePersistenceImpl<NewsPopulation>
	implements NewsPopulationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NewsPopulationUtil</code> to access the news population persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NewsPopulationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBygroupId_roleId;
	private FinderPath _finderPathWithoutPaginationFindBygroupId_roleId;
	private FinderPath _finderPathCountBygroupId_roleId;

	/**
	 * Returns all the news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @return the matching news populations
	 */
	@Override
	public List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId) {

		return findBygroupId_roleId(
			groupId, roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of matching news populations
	 */
	@Override
	public List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId, int start, int end) {

		return findBygroupId_roleId(groupId, roleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news populations
	 */
	@Override
	public List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return findBygroupId_roleId(
			groupId, roleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news populations
	 */
	@Override
	public List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBygroupId_roleId;
				finderArgs = new Object[] {groupId, roleId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBygroupId_roleId;
			finderArgs = new Object[] {
				groupId, roleId, start, end, orderByComparator
			};
		}

		List<NewsPopulation> list = null;

		if (useFinderCache) {
			list = (List<NewsPopulation>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (NewsPopulation newsPopulation : list) {
					if ((groupId != newsPopulation.getGroupId()) ||
						(roleId != newsPopulation.getRoleId())) {

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

			sb.append(_SQL_SELECT_NEWSPOPULATION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_ROLEID_GROUPID_2);

			sb.append(_FINDER_COLUMN_GROUPID_ROLEID_ROLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NewsPopulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(roleId);

				list = (List<NewsPopulation>)QueryUtil.list(
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
	 * Returns the first news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	@Override
	public NewsPopulation findBygroupId_roleId_First(
			long groupId, long roleId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = fetchBygroupId_roleId_First(
			groupId, roleId, orderByComparator);

		if (newsPopulation != null) {
			return newsPopulation;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", roleId=");
		sb.append(roleId);

		sb.append("}");

		throw new NoSuchPopulationException(sb.toString());
	}

	/**
	 * Returns the first news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population, or <code>null</code> if a matching news population could not be found
	 */
	@Override
	public NewsPopulation fetchBygroupId_roleId_First(
		long groupId, long roleId,
		OrderByComparator<NewsPopulation> orderByComparator) {

		List<NewsPopulation> list = findBygroupId_roleId(
			groupId, roleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	@Override
	public NewsPopulation findBygroupId_roleId_Last(
			long groupId, long roleId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = fetchBygroupId_roleId_Last(
			groupId, roleId, orderByComparator);

		if (newsPopulation != null) {
			return newsPopulation;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", roleId=");
		sb.append(roleId);

		sb.append("}");

		throw new NoSuchPopulationException(sb.toString());
	}

	/**
	 * Returns the last news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population, or <code>null</code> if a matching news population could not be found
	 */
	@Override
	public NewsPopulation fetchBygroupId_roleId_Last(
		long groupId, long roleId,
		OrderByComparator<NewsPopulation> orderByComparator) {

		int count = countBygroupId_roleId(groupId, roleId);

		if (count == 0) {
			return null;
		}

		List<NewsPopulation> list = findBygroupId_roleId(
			groupId, roleId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the news populations before and after the current news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param newsPopulationPK the primary key of the current news population
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	@Override
	public NewsPopulation[] findBygroupId_roleId_PrevAndNext(
			NewsPopulationPK newsPopulationPK, long groupId, long roleId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = findByPrimaryKey(newsPopulationPK);

		Session session = null;

		try {
			session = openSession();

			NewsPopulation[] array = new NewsPopulationImpl[3];

			array[0] = getBygroupId_roleId_PrevAndNext(
				session, newsPopulation, groupId, roleId, orderByComparator,
				true);

			array[1] = newsPopulation;

			array[2] = getBygroupId_roleId_PrevAndNext(
				session, newsPopulation, groupId, roleId, orderByComparator,
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

	protected NewsPopulation getBygroupId_roleId_PrevAndNext(
		Session session, NewsPopulation newsPopulation, long groupId,
		long roleId, OrderByComparator<NewsPopulation> orderByComparator,
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

		sb.append(_SQL_SELECT_NEWSPOPULATION_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_ROLEID_GROUPID_2);

		sb.append(_FINDER_COLUMN_GROUPID_ROLEID_ROLEID_2);

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
			sb.append(NewsPopulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(roleId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						newsPopulation)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NewsPopulation> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the news populations where groupId = &#63; and roleId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 */
	@Override
	public void removeBygroupId_roleId(long groupId, long roleId) {
		for (NewsPopulation newsPopulation :
				findBygroupId_roleId(
					groupId, roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(newsPopulation);
		}
	}

	/**
	 * Returns the number of news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @return the number of matching news populations
	 */
	@Override
	public int countBygroupId_roleId(long groupId, long roleId) {
		FinderPath finderPath = _finderPathCountBygroupId_roleId;

		Object[] finderArgs = new Object[] {groupId, roleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_NEWSPOPULATION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_ROLEID_GROUPID_2);

			sb.append(_FINDER_COLUMN_GROUPID_ROLEID_ROLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_ROLEID_GROUPID_2 =
		"newsPopulation.id.groupId = ? AND ";

	private static final String _FINDER_COLUMN_GROUPID_ROLEID_ROLEID_2 =
		"newsPopulation.id.roleId = ?";

	private FinderPath _finderPathWithPaginationFindBynewsId;
	private FinderPath _finderPathWithoutPaginationFindBynewsId;
	private FinderPath _finderPathCountBynewsId;

	/**
	 * Returns all the news populations where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the matching news populations
	 */
	@Override
	public List<NewsPopulation> findBynewsId(long newsId) {
		return findBynewsId(newsId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the news populations where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of matching news populations
	 */
	@Override
	public List<NewsPopulation> findBynewsId(long newsId, int start, int end) {
		return findBynewsId(newsId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the news populations where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news populations
	 */
	@Override
	public List<NewsPopulation> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return findBynewsId(newsId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the news populations where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news populations
	 */
	@Override
	public List<NewsPopulation> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator,
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

		List<NewsPopulation> list = null;

		if (useFinderCache) {
			list = (List<NewsPopulation>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (NewsPopulation newsPopulation : list) {
					if (newsId != newsPopulation.getNewsId()) {
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

			sb.append(_SQL_SELECT_NEWSPOPULATION_WHERE);

			sb.append(_FINDER_COLUMN_NEWSID_NEWSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NewsPopulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(newsId);

				list = (List<NewsPopulation>)QueryUtil.list(
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
	 * Returns the first news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	@Override
	public NewsPopulation findBynewsId_First(
			long newsId, OrderByComparator<NewsPopulation> orderByComparator)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = fetchBynewsId_First(
			newsId, orderByComparator);

		if (newsPopulation != null) {
			return newsPopulation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("newsId=");
		sb.append(newsId);

		sb.append("}");

		throw new NoSuchPopulationException(sb.toString());
	}

	/**
	 * Returns the first news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population, or <code>null</code> if a matching news population could not be found
	 */
	@Override
	public NewsPopulation fetchBynewsId_First(
		long newsId, OrderByComparator<NewsPopulation> orderByComparator) {

		List<NewsPopulation> list = findBynewsId(
			newsId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	@Override
	public NewsPopulation findBynewsId_Last(
			long newsId, OrderByComparator<NewsPopulation> orderByComparator)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = fetchBynewsId_Last(
			newsId, orderByComparator);

		if (newsPopulation != null) {
			return newsPopulation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("newsId=");
		sb.append(newsId);

		sb.append("}");

		throw new NoSuchPopulationException(sb.toString());
	}

	/**
	 * Returns the last news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population, or <code>null</code> if a matching news population could not be found
	 */
	@Override
	public NewsPopulation fetchBynewsId_Last(
		long newsId, OrderByComparator<NewsPopulation> orderByComparator) {

		int count = countBynewsId(newsId);

		if (count == 0) {
			return null;
		}

		List<NewsPopulation> list = findBynewsId(
			newsId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the news populations before and after the current news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsPopulationPK the primary key of the current news population
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	@Override
	public NewsPopulation[] findBynewsId_PrevAndNext(
			NewsPopulationPK newsPopulationPK, long newsId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = findByPrimaryKey(newsPopulationPK);

		Session session = null;

		try {
			session = openSession();

			NewsPopulation[] array = new NewsPopulationImpl[3];

			array[0] = getBynewsId_PrevAndNext(
				session, newsPopulation, newsId, orderByComparator, true);

			array[1] = newsPopulation;

			array[2] = getBynewsId_PrevAndNext(
				session, newsPopulation, newsId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsPopulation getBynewsId_PrevAndNext(
		Session session, NewsPopulation newsPopulation, long newsId,
		OrderByComparator<NewsPopulation> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_NEWSPOPULATION_WHERE);

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
			sb.append(NewsPopulationModelImpl.ORDER_BY_JPQL);
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
						newsPopulation)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NewsPopulation> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the news populations where newsId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 */
	@Override
	public void removeBynewsId(long newsId) {
		for (NewsPopulation newsPopulation :
				findBynewsId(
					newsId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(newsPopulation);
		}
	}

	/**
	 * Returns the number of news populations where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the number of matching news populations
	 */
	@Override
	public int countBynewsId(long newsId) {
		FinderPath finderPath = _finderPathCountBynewsId;

		Object[] finderArgs = new Object[] {newsId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NEWSPOPULATION_WHERE);

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
		"newsPopulation.id.newsId = ?";

	private FinderPath _finderPathWithPaginationFindBygroupId;
	private FinderPath _finderPathWithoutPaginationFindBygroupId;
	private FinderPath _finderPathCountBygroupId;

	/**
	 * Returns all the news populations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching news populations
	 */
	@Override
	public List<NewsPopulation> findBygroupId(long groupId) {
		return findBygroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the news populations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of matching news populations
	 */
	@Override
	public List<NewsPopulation> findBygroupId(
		long groupId, int start, int end) {

		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the news populations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news populations
	 */
	@Override
	public List<NewsPopulation> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return findBygroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the news populations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news populations
	 */
	@Override
	public List<NewsPopulation> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBygroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBygroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<NewsPopulation> list = null;

		if (useFinderCache) {
			list = (List<NewsPopulation>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (NewsPopulation newsPopulation : list) {
					if (groupId != newsPopulation.getGroupId()) {
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

			sb.append(_SQL_SELECT_NEWSPOPULATION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NewsPopulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<NewsPopulation>)QueryUtil.list(
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
	 * Returns the first news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	@Override
	public NewsPopulation findBygroupId_First(
			long groupId, OrderByComparator<NewsPopulation> orderByComparator)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = fetchBygroupId_First(
			groupId, orderByComparator);

		if (newsPopulation != null) {
			return newsPopulation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchPopulationException(sb.toString());
	}

	/**
	 * Returns the first news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population, or <code>null</code> if a matching news population could not be found
	 */
	@Override
	public NewsPopulation fetchBygroupId_First(
		long groupId, OrderByComparator<NewsPopulation> orderByComparator) {

		List<NewsPopulation> list = findBygroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	@Override
	public NewsPopulation findBygroupId_Last(
			long groupId, OrderByComparator<NewsPopulation> orderByComparator)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = fetchBygroupId_Last(
			groupId, orderByComparator);

		if (newsPopulation != null) {
			return newsPopulation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchPopulationException(sb.toString());
	}

	/**
	 * Returns the last news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population, or <code>null</code> if a matching news population could not be found
	 */
	@Override
	public NewsPopulation fetchBygroupId_Last(
		long groupId, OrderByComparator<NewsPopulation> orderByComparator) {

		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<NewsPopulation> list = findBygroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the news populations before and after the current news population in the ordered set where groupId = &#63;.
	 *
	 * @param newsPopulationPK the primary key of the current news population
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	@Override
	public NewsPopulation[] findBygroupId_PrevAndNext(
			NewsPopulationPK newsPopulationPK, long groupId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = findByPrimaryKey(newsPopulationPK);

		Session session = null;

		try {
			session = openSession();

			NewsPopulation[] array = new NewsPopulationImpl[3];

			array[0] = getBygroupId_PrevAndNext(
				session, newsPopulation, groupId, orderByComparator, true);

			array[1] = newsPopulation;

			array[2] = getBygroupId_PrevAndNext(
				session, newsPopulation, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsPopulation getBygroupId_PrevAndNext(
		Session session, NewsPopulation newsPopulation, long groupId,
		OrderByComparator<NewsPopulation> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_NEWSPOPULATION_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(NewsPopulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						newsPopulation)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NewsPopulation> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the news populations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupId(long groupId) {
		for (NewsPopulation newsPopulation :
				findBygroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(newsPopulation);
		}
	}

	/**
	 * Returns the number of news populations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching news populations
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = _finderPathCountBygroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NEWSPOPULATION_WHERE);

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
		"newsPopulation.id.groupId = ?";

	public NewsPopulationPersistenceImpl() {
		setModelClass(NewsPopulation.class);

		setModelImplClass(NewsPopulationImpl.class);
		setModelPKClass(NewsPopulationPK.class);

		setTable(NewsPopulationTable.INSTANCE);
	}

	/**
	 * Caches the news population in the entity cache if it is enabled.
	 *
	 * @param newsPopulation the news population
	 */
	@Override
	public void cacheResult(NewsPopulation newsPopulation) {
		entityCache.putResult(
			NewsPopulationImpl.class, newsPopulation.getPrimaryKey(),
			newsPopulation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the news populations in the entity cache if it is enabled.
	 *
	 * @param newsPopulations the news populations
	 */
	@Override
	public void cacheResult(List<NewsPopulation> newsPopulations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (newsPopulations.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (NewsPopulation newsPopulation : newsPopulations) {
			if (entityCache.getResult(
					NewsPopulationImpl.class, newsPopulation.getPrimaryKey()) ==
						null) {

				cacheResult(newsPopulation);
			}
		}
	}

	/**
	 * Clears the cache for all news populations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NewsPopulationImpl.class);

		finderCache.clearCache(NewsPopulationImpl.class);
	}

	/**
	 * Clears the cache for the news population.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NewsPopulation newsPopulation) {
		entityCache.removeResult(NewsPopulationImpl.class, newsPopulation);
	}

	@Override
	public void clearCache(List<NewsPopulation> newsPopulations) {
		for (NewsPopulation newsPopulation : newsPopulations) {
			entityCache.removeResult(NewsPopulationImpl.class, newsPopulation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(NewsPopulationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(NewsPopulationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new news population with the primary key. Does not add the news population to the database.
	 *
	 * @param newsPopulationPK the primary key for the new news population
	 * @return the new news population
	 */
	@Override
	public NewsPopulation create(NewsPopulationPK newsPopulationPK) {
		NewsPopulation newsPopulation = new NewsPopulationImpl();

		newsPopulation.setNew(true);
		newsPopulation.setPrimaryKey(newsPopulationPK);

		return newsPopulation;
	}

	/**
	 * Removes the news population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population that was removed
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	@Override
	public NewsPopulation remove(NewsPopulationPK newsPopulationPK)
		throws NoSuchPopulationException {

		return remove((Serializable)newsPopulationPK);
	}

	/**
	 * Removes the news population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the news population
	 * @return the news population that was removed
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	@Override
	public NewsPopulation remove(Serializable primaryKey)
		throws NoSuchPopulationException {

		Session session = null;

		try {
			session = openSession();

			NewsPopulation newsPopulation = (NewsPopulation)session.get(
				NewsPopulationImpl.class, primaryKey);

			if (newsPopulation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPopulationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(newsPopulation);
		}
		catch (NoSuchPopulationException noSuchEntityException) {
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
	protected NewsPopulation removeImpl(NewsPopulation newsPopulation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(newsPopulation)) {
				newsPopulation = (NewsPopulation)session.get(
					NewsPopulationImpl.class,
					newsPopulation.getPrimaryKeyObj());
			}

			if (newsPopulation != null) {
				session.delete(newsPopulation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (newsPopulation != null) {
			clearCache(newsPopulation);
		}

		return newsPopulation;
	}

	@Override
	public NewsPopulation updateImpl(NewsPopulation newsPopulation) {
		boolean isNew = newsPopulation.isNew();

		if (!(newsPopulation instanceof NewsPopulationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(newsPopulation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					newsPopulation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in newsPopulation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom NewsPopulation implementation " +
					newsPopulation.getClass());
		}

		NewsPopulationModelImpl newsPopulationModelImpl =
			(NewsPopulationModelImpl)newsPopulation;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(newsPopulation);
			}
			else {
				newsPopulation = (NewsPopulation)session.merge(newsPopulation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			NewsPopulationImpl.class, newsPopulationModelImpl, false, true);

		if (isNew) {
			newsPopulation.setNew(false);
		}

		newsPopulation.resetOriginalValues();

		return newsPopulation;
	}

	/**
	 * Returns the news population with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the news population
	 * @return the news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	@Override
	public NewsPopulation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPopulationException {

		NewsPopulation newsPopulation = fetchByPrimaryKey(primaryKey);

		if (newsPopulation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPopulationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return newsPopulation;
	}

	/**
	 * Returns the news population with the primary key or throws a <code>NoSuchPopulationException</code> if it could not be found.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	@Override
	public NewsPopulation findByPrimaryKey(NewsPopulationPK newsPopulationPK)
		throws NoSuchPopulationException {

		return findByPrimaryKey((Serializable)newsPopulationPK);
	}

	/**
	 * Returns the news population with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population, or <code>null</code> if a news population with the primary key could not be found
	 */
	@Override
	public NewsPopulation fetchByPrimaryKey(NewsPopulationPK newsPopulationPK) {
		return fetchByPrimaryKey((Serializable)newsPopulationPK);
	}

	/**
	 * Returns all the news populations.
	 *
	 * @return the news populations
	 */
	@Override
	public List<NewsPopulation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the news populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of news populations
	 */
	@Override
	public List<NewsPopulation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the news populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of news populations
	 */
	@Override
	public List<NewsPopulation> findAll(
		int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the news populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of news populations
	 */
	@Override
	public List<NewsPopulation> findAll(
		int start, int end, OrderByComparator<NewsPopulation> orderByComparator,
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

		List<NewsPopulation> list = null;

		if (useFinderCache) {
			list = (List<NewsPopulation>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NEWSPOPULATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NEWSPOPULATION;

				sql = sql.concat(NewsPopulationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<NewsPopulation>)QueryUtil.list(
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
	 * Removes all the news populations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NewsPopulation newsPopulation : findAll()) {
			remove(newsPopulation);
		}
	}

	/**
	 * Returns the number of news populations.
	 *
	 * @return the number of news populations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_NEWSPOPULATION);

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
		return "newsPopulationPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NEWSPOPULATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NewsPopulationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the news population persistence.
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

		_finderPathWithPaginationFindBygroupId_roleId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygroupId_roleId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "roleId"}, true);

		_finderPathWithoutPaginationFindBygroupId_roleId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygroupId_roleId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "roleId"}, true);

		_finderPathCountBygroupId_roleId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupId_roleId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "roleId"}, false);

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

		_finderPathWithPaginationFindBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountBygroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_setNewsPopulationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setNewsPopulationUtilPersistence(null);

		entityCache.removeCache(NewsPopulationImpl.class.getName());
	}

	private void _setNewsPopulationUtilPersistence(
		NewsPopulationPersistence newsPopulationPersistence) {

		try {
			Field field = NewsPopulationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, newsPopulationPersistence);
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

	private static final String _SQL_SELECT_NEWSPOPULATION =
		"SELECT newsPopulation FROM NewsPopulation newsPopulation";

	private static final String _SQL_SELECT_NEWSPOPULATION_WHERE =
		"SELECT newsPopulation FROM NewsPopulation newsPopulation WHERE ";

	private static final String _SQL_COUNT_NEWSPOPULATION =
		"SELECT COUNT(newsPopulation) FROM NewsPopulation newsPopulation";

	private static final String _SQL_COUNT_NEWSPOPULATION_WHERE =
		"SELECT COUNT(newsPopulation) FROM NewsPopulation newsPopulation WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "newsPopulation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No NewsPopulation exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No NewsPopulation exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NewsPopulationPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"newsId", "groupId", "roleId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private NewsPopulationModelArgumentsResolver
		_newsPopulationModelArgumentsResolver;

}