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

package com.weprode.facile.user.service.persistence.impl;

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

import com.weprode.facile.user.exception.NoSuchRelationshipException;
import com.weprode.facile.user.model.UserRelationship;
import com.weprode.facile.user.model.UserRelationshipTable;
import com.weprode.facile.user.model.impl.UserRelationshipImpl;
import com.weprode.facile.user.model.impl.UserRelationshipModelImpl;
import com.weprode.facile.user.service.persistence.UserRelationshipPK;
import com.weprode.facile.user.service.persistence.UserRelationshipPersistence;
import com.weprode.facile.user.service.persistence.UserRelationshipUtil;
import com.weprode.facile.user.service.persistence.impl.constants.UserPersistenceConstants;

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
 * The persistence implementation for the user relationship service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {UserRelationshipPersistence.class, BasePersistence.class})
public class UserRelationshipPersistenceImpl
	extends BasePersistenceImpl<UserRelationship>
	implements UserRelationshipPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserRelationshipUtil</code> to access the user relationship persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserRelationshipImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBychildUserId;
	private FinderPath _finderPathWithoutPaginationFindBychildUserId;
	private FinderPath _finderPathCountBychildUserId;

	/**
	 * Returns all the user relationships where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @return the matching user relationships
	 */
	@Override
	public List<UserRelationship> findBychildUserId(long childUserId) {
		return findBychildUserId(
			childUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user relationships where childUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param childUserId the child user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of matching user relationships
	 */
	@Override
	public List<UserRelationship> findBychildUserId(
		long childUserId, int start, int end) {

		return findBychildUserId(childUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user relationships where childUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param childUserId the child user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user relationships
	 */
	@Override
	public List<UserRelationship> findBychildUserId(
		long childUserId, int start, int end,
		OrderByComparator<UserRelationship> orderByComparator) {

		return findBychildUserId(
			childUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user relationships where childUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param childUserId the child user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user relationships
	 */
	@Override
	public List<UserRelationship> findBychildUserId(
		long childUserId, int start, int end,
		OrderByComparator<UserRelationship> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBychildUserId;
				finderArgs = new Object[] {childUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBychildUserId;
			finderArgs = new Object[] {
				childUserId, start, end, orderByComparator
			};
		}

		List<UserRelationship> list = null;

		if (useFinderCache) {
			list = (List<UserRelationship>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserRelationship userRelationship : list) {
					if (childUserId != userRelationship.getChildUserId()) {
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

			sb.append(_SQL_SELECT_USERRELATIONSHIP_WHERE);

			sb.append(_FINDER_COLUMN_CHILDUSERID_CHILDUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserRelationshipModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(childUserId);

				list = (List<UserRelationship>)QueryUtil.list(
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
	 * Returns the first user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	@Override
	public UserRelationship findBychildUserId_First(
			long childUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws NoSuchRelationshipException {

		UserRelationship userRelationship = fetchBychildUserId_First(
			childUserId, orderByComparator);

		if (userRelationship != null) {
			return userRelationship;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("childUserId=");
		sb.append(childUserId);

		sb.append("}");

		throw new NoSuchRelationshipException(sb.toString());
	}

	/**
	 * Returns the first user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	@Override
	public UserRelationship fetchBychildUserId_First(
		long childUserId,
		OrderByComparator<UserRelationship> orderByComparator) {

		List<UserRelationship> list = findBychildUserId(
			childUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	@Override
	public UserRelationship findBychildUserId_Last(
			long childUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws NoSuchRelationshipException {

		UserRelationship userRelationship = fetchBychildUserId_Last(
			childUserId, orderByComparator);

		if (userRelationship != null) {
			return userRelationship;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("childUserId=");
		sb.append(childUserId);

		sb.append("}");

		throw new NoSuchRelationshipException(sb.toString());
	}

	/**
	 * Returns the last user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	@Override
	public UserRelationship fetchBychildUserId_Last(
		long childUserId,
		OrderByComparator<UserRelationship> orderByComparator) {

		int count = countBychildUserId(childUserId);

		if (count == 0) {
			return null;
		}

		List<UserRelationship> list = findBychildUserId(
			childUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user relationships before and after the current user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param userRelationshipPK the primary key of the current user relationship
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	@Override
	public UserRelationship[] findBychildUserId_PrevAndNext(
			UserRelationshipPK userRelationshipPK, long childUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws NoSuchRelationshipException {

		UserRelationship userRelationship = findByPrimaryKey(
			userRelationshipPK);

		Session session = null;

		try {
			session = openSession();

			UserRelationship[] array = new UserRelationshipImpl[3];

			array[0] = getBychildUserId_PrevAndNext(
				session, userRelationship, childUserId, orderByComparator,
				true);

			array[1] = userRelationship;

			array[2] = getBychildUserId_PrevAndNext(
				session, userRelationship, childUserId, orderByComparator,
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

	protected UserRelationship getBychildUserId_PrevAndNext(
		Session session, UserRelationship userRelationship, long childUserId,
		OrderByComparator<UserRelationship> orderByComparator,
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

		sb.append(_SQL_SELECT_USERRELATIONSHIP_WHERE);

		sb.append(_FINDER_COLUMN_CHILDUSERID_CHILDUSERID_2);

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
			sb.append(UserRelationshipModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(childUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						userRelationship)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserRelationship> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user relationships where childUserId = &#63; from the database.
	 *
	 * @param childUserId the child user ID
	 */
	@Override
	public void removeBychildUserId(long childUserId) {
		for (UserRelationship userRelationship :
				findBychildUserId(
					childUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(userRelationship);
		}
	}

	/**
	 * Returns the number of user relationships where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @return the number of matching user relationships
	 */
	@Override
	public int countBychildUserId(long childUserId) {
		FinderPath finderPath = _finderPathCountBychildUserId;

		Object[] finderArgs = new Object[] {childUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERRELATIONSHIP_WHERE);

			sb.append(_FINDER_COLUMN_CHILDUSERID_CHILDUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(childUserId);

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

	private static final String _FINDER_COLUMN_CHILDUSERID_CHILDUSERID_2 =
		"userRelationship.id.childUserId = ?";

	private FinderPath _finderPathWithPaginationFindByparentUserId;
	private FinderPath _finderPathWithoutPaginationFindByparentUserId;
	private FinderPath _finderPathCountByparentUserId;

	/**
	 * Returns all the user relationships where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @return the matching user relationships
	 */
	@Override
	public List<UserRelationship> findByparentUserId(long parentUserId) {
		return findByparentUserId(
			parentUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user relationships where parentUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param parentUserId the parent user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of matching user relationships
	 */
	@Override
	public List<UserRelationship> findByparentUserId(
		long parentUserId, int start, int end) {

		return findByparentUserId(parentUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user relationships where parentUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param parentUserId the parent user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user relationships
	 */
	@Override
	public List<UserRelationship> findByparentUserId(
		long parentUserId, int start, int end,
		OrderByComparator<UserRelationship> orderByComparator) {

		return findByparentUserId(
			parentUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user relationships where parentUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param parentUserId the parent user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user relationships
	 */
	@Override
	public List<UserRelationship> findByparentUserId(
		long parentUserId, int start, int end,
		OrderByComparator<UserRelationship> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByparentUserId;
				finderArgs = new Object[] {parentUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByparentUserId;
			finderArgs = new Object[] {
				parentUserId, start, end, orderByComparator
			};
		}

		List<UserRelationship> list = null;

		if (useFinderCache) {
			list = (List<UserRelationship>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserRelationship userRelationship : list) {
					if (parentUserId != userRelationship.getParentUserId()) {
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

			sb.append(_SQL_SELECT_USERRELATIONSHIP_WHERE);

			sb.append(_FINDER_COLUMN_PARENTUSERID_PARENTUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserRelationshipModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(parentUserId);

				list = (List<UserRelationship>)QueryUtil.list(
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
	 * Returns the first user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	@Override
	public UserRelationship findByparentUserId_First(
			long parentUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws NoSuchRelationshipException {

		UserRelationship userRelationship = fetchByparentUserId_First(
			parentUserId, orderByComparator);

		if (userRelationship != null) {
			return userRelationship;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("parentUserId=");
		sb.append(parentUserId);

		sb.append("}");

		throw new NoSuchRelationshipException(sb.toString());
	}

	/**
	 * Returns the first user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	@Override
	public UserRelationship fetchByparentUserId_First(
		long parentUserId,
		OrderByComparator<UserRelationship> orderByComparator) {

		List<UserRelationship> list = findByparentUserId(
			parentUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	@Override
	public UserRelationship findByparentUserId_Last(
			long parentUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws NoSuchRelationshipException {

		UserRelationship userRelationship = fetchByparentUserId_Last(
			parentUserId, orderByComparator);

		if (userRelationship != null) {
			return userRelationship;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("parentUserId=");
		sb.append(parentUserId);

		sb.append("}");

		throw new NoSuchRelationshipException(sb.toString());
	}

	/**
	 * Returns the last user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	@Override
	public UserRelationship fetchByparentUserId_Last(
		long parentUserId,
		OrderByComparator<UserRelationship> orderByComparator) {

		int count = countByparentUserId(parentUserId);

		if (count == 0) {
			return null;
		}

		List<UserRelationship> list = findByparentUserId(
			parentUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user relationships before and after the current user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param userRelationshipPK the primary key of the current user relationship
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	@Override
	public UserRelationship[] findByparentUserId_PrevAndNext(
			UserRelationshipPK userRelationshipPK, long parentUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws NoSuchRelationshipException {

		UserRelationship userRelationship = findByPrimaryKey(
			userRelationshipPK);

		Session session = null;

		try {
			session = openSession();

			UserRelationship[] array = new UserRelationshipImpl[3];

			array[0] = getByparentUserId_PrevAndNext(
				session, userRelationship, parentUserId, orderByComparator,
				true);

			array[1] = userRelationship;

			array[2] = getByparentUserId_PrevAndNext(
				session, userRelationship, parentUserId, orderByComparator,
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

	protected UserRelationship getByparentUserId_PrevAndNext(
		Session session, UserRelationship userRelationship, long parentUserId,
		OrderByComparator<UserRelationship> orderByComparator,
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

		sb.append(_SQL_SELECT_USERRELATIONSHIP_WHERE);

		sb.append(_FINDER_COLUMN_PARENTUSERID_PARENTUSERID_2);

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
			sb.append(UserRelationshipModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(parentUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						userRelationship)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserRelationship> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user relationships where parentUserId = &#63; from the database.
	 *
	 * @param parentUserId the parent user ID
	 */
	@Override
	public void removeByparentUserId(long parentUserId) {
		for (UserRelationship userRelationship :
				findByparentUserId(
					parentUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(userRelationship);
		}
	}

	/**
	 * Returns the number of user relationships where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @return the number of matching user relationships
	 */
	@Override
	public int countByparentUserId(long parentUserId) {
		FinderPath finderPath = _finderPathCountByparentUserId;

		Object[] finderArgs = new Object[] {parentUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERRELATIONSHIP_WHERE);

			sb.append(_FINDER_COLUMN_PARENTUSERID_PARENTUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(parentUserId);

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

	private static final String _FINDER_COLUMN_PARENTUSERID_PARENTUSERID_2 =
		"userRelationship.id.parentUserId = ?";

	public UserRelationshipPersistenceImpl() {
		setModelClass(UserRelationship.class);

		setModelImplClass(UserRelationshipImpl.class);
		setModelPKClass(UserRelationshipPK.class);

		setTable(UserRelationshipTable.INSTANCE);
	}

	/**
	 * Caches the user relationship in the entity cache if it is enabled.
	 *
	 * @param userRelationship the user relationship
	 */
	@Override
	public void cacheResult(UserRelationship userRelationship) {
		entityCache.putResult(
			UserRelationshipImpl.class, userRelationship.getPrimaryKey(),
			userRelationship);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the user relationships in the entity cache if it is enabled.
	 *
	 * @param userRelationships the user relationships
	 */
	@Override
	public void cacheResult(List<UserRelationship> userRelationships) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (userRelationships.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UserRelationship userRelationship : userRelationships) {
			if (entityCache.getResult(
					UserRelationshipImpl.class,
					userRelationship.getPrimaryKey()) == null) {

				cacheResult(userRelationship);
			}
		}
	}

	/**
	 * Clears the cache for all user relationships.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserRelationshipImpl.class);

		finderCache.clearCache(UserRelationshipImpl.class);
	}

	/**
	 * Clears the cache for the user relationship.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserRelationship userRelationship) {
		entityCache.removeResult(UserRelationshipImpl.class, userRelationship);
	}

	@Override
	public void clearCache(List<UserRelationship> userRelationships) {
		for (UserRelationship userRelationship : userRelationships) {
			entityCache.removeResult(
				UserRelationshipImpl.class, userRelationship);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UserRelationshipImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UserRelationshipImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new user relationship with the primary key. Does not add the user relationship to the database.
	 *
	 * @param userRelationshipPK the primary key for the new user relationship
	 * @return the new user relationship
	 */
	@Override
	public UserRelationship create(UserRelationshipPK userRelationshipPK) {
		UserRelationship userRelationship = new UserRelationshipImpl();

		userRelationship.setNew(true);
		userRelationship.setPrimaryKey(userRelationshipPK);

		return userRelationship;
	}

	/**
	 * Removes the user relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship that was removed
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	@Override
	public UserRelationship remove(UserRelationshipPK userRelationshipPK)
		throws NoSuchRelationshipException {

		return remove((Serializable)userRelationshipPK);
	}

	/**
	 * Removes the user relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user relationship
	 * @return the user relationship that was removed
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	@Override
	public UserRelationship remove(Serializable primaryKey)
		throws NoSuchRelationshipException {

		Session session = null;

		try {
			session = openSession();

			UserRelationship userRelationship = (UserRelationship)session.get(
				UserRelationshipImpl.class, primaryKey);

			if (userRelationship == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRelationshipException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userRelationship);
		}
		catch (NoSuchRelationshipException noSuchEntityException) {
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
	protected UserRelationship removeImpl(UserRelationship userRelationship) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userRelationship)) {
				userRelationship = (UserRelationship)session.get(
					UserRelationshipImpl.class,
					userRelationship.getPrimaryKeyObj());
			}

			if (userRelationship != null) {
				session.delete(userRelationship);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userRelationship != null) {
			clearCache(userRelationship);
		}

		return userRelationship;
	}

	@Override
	public UserRelationship updateImpl(UserRelationship userRelationship) {
		boolean isNew = userRelationship.isNew();

		if (!(userRelationship instanceof UserRelationshipModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userRelationship.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					userRelationship);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userRelationship proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserRelationship implementation " +
					userRelationship.getClass());
		}

		UserRelationshipModelImpl userRelationshipModelImpl =
			(UserRelationshipModelImpl)userRelationship;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(userRelationship);
			}
			else {
				userRelationship = (UserRelationship)session.merge(
					userRelationship);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UserRelationshipImpl.class, userRelationshipModelImpl, false, true);

		if (isNew) {
			userRelationship.setNew(false);
		}

		userRelationship.resetOriginalValues();

		return userRelationship;
	}

	/**
	 * Returns the user relationship with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user relationship
	 * @return the user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	@Override
	public UserRelationship findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRelationshipException {

		UserRelationship userRelationship = fetchByPrimaryKey(primaryKey);

		if (userRelationship == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRelationshipException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userRelationship;
	}

	/**
	 * Returns the user relationship with the primary key or throws a <code>NoSuchRelationshipException</code> if it could not be found.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	@Override
	public UserRelationship findByPrimaryKey(
			UserRelationshipPK userRelationshipPK)
		throws NoSuchRelationshipException {

		return findByPrimaryKey((Serializable)userRelationshipPK);
	}

	/**
	 * Returns the user relationship with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship, or <code>null</code> if a user relationship with the primary key could not be found
	 */
	@Override
	public UserRelationship fetchByPrimaryKey(
		UserRelationshipPK userRelationshipPK) {

		return fetchByPrimaryKey((Serializable)userRelationshipPK);
	}

	/**
	 * Returns all the user relationships.
	 *
	 * @return the user relationships
	 */
	@Override
	public List<UserRelationship> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of user relationships
	 */
	@Override
	public List<UserRelationship> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user relationships
	 */
	@Override
	public List<UserRelationship> findAll(
		int start, int end,
		OrderByComparator<UserRelationship> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user relationships
	 */
	@Override
	public List<UserRelationship> findAll(
		int start, int end,
		OrderByComparator<UserRelationship> orderByComparator,
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

		List<UserRelationship> list = null;

		if (useFinderCache) {
			list = (List<UserRelationship>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERRELATIONSHIP);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERRELATIONSHIP;

				sql = sql.concat(UserRelationshipModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserRelationship>)QueryUtil.list(
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
	 * Removes all the user relationships from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserRelationship userRelationship : findAll()) {
			remove(userRelationship);
		}
	}

	/**
	 * Returns the number of user relationships.
	 *
	 * @return the number of user relationships
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USERRELATIONSHIP);

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
		return "userRelationshipPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_USERRELATIONSHIP;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserRelationshipModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user relationship persistence.
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

		_finderPathWithPaginationFindBychildUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBychildUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"childUserId"}, true);

		_finderPathWithoutPaginationFindBychildUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBychildUserId",
			new String[] {Long.class.getName()}, new String[] {"childUserId"},
			true);

		_finderPathCountBychildUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBychildUserId",
			new String[] {Long.class.getName()}, new String[] {"childUserId"},
			false);

		_finderPathWithPaginationFindByparentUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByparentUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"parentUserId"}, true);

		_finderPathWithoutPaginationFindByparentUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByparentUserId",
			new String[] {Long.class.getName()}, new String[] {"parentUserId"},
			true);

		_finderPathCountByparentUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByparentUserId",
			new String[] {Long.class.getName()}, new String[] {"parentUserId"},
			false);

		_setUserRelationshipUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setUserRelationshipUtilPersistence(null);

		entityCache.removeCache(UserRelationshipImpl.class.getName());
	}

	private void _setUserRelationshipUtilPersistence(
		UserRelationshipPersistence userRelationshipPersistence) {

		try {
			Field field = UserRelationshipUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, userRelationshipPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = UserPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = UserPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = UserPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_USERRELATIONSHIP =
		"SELECT userRelationship FROM UserRelationship userRelationship";

	private static final String _SQL_SELECT_USERRELATIONSHIP_WHERE =
		"SELECT userRelationship FROM UserRelationship userRelationship WHERE ";

	private static final String _SQL_COUNT_USERRELATIONSHIP =
		"SELECT COUNT(userRelationship) FROM UserRelationship userRelationship";

	private static final String _SQL_COUNT_USERRELATIONSHIP_WHERE =
		"SELECT COUNT(userRelationship) FROM UserRelationship userRelationship WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userRelationship.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserRelationship exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserRelationship exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserRelationshipPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"childUserId", "parentUserId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}