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

package com.weprode.facile.group.service.persistence.impl;

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

import com.weprode.facile.group.exception.NoSuchMembershipException;
import com.weprode.facile.group.model.GroupMembership;
import com.weprode.facile.group.model.GroupMembershipTable;
import com.weprode.facile.group.model.impl.GroupMembershipImpl;
import com.weprode.facile.group.model.impl.GroupMembershipModelImpl;
import com.weprode.facile.group.service.persistence.GroupMembershipPersistence;
import com.weprode.facile.group.service.persistence.GroupMembershipUtil;
import com.weprode.facile.group.service.persistence.impl.constants.GroupPersistenceConstants;

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
 * The persistence implementation for the group membership service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {GroupMembershipPersistence.class, BasePersistence.class})
public class GroupMembershipPersistenceImpl
	extends BasePersistenceImpl<GroupMembership>
	implements GroupMembershipPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>GroupMembershipUtil</code> to access the group membership persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		GroupMembershipImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBygroupId;
	private FinderPath _finderPathWithoutPaginationFindBygroupId;
	private FinderPath _finderPathCountBygroupId;

	/**
	 * Returns all the group memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching group memberships
	 */
	@Override
	public List<GroupMembership> findBygroupId(long groupId) {
		return findBygroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the group memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @return the range of matching group memberships
	 */
	@Override
	public List<GroupMembership> findBygroupId(
		long groupId, int start, int end) {

		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the group memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching group memberships
	 */
	@Override
	public List<GroupMembership> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator) {

		return findBygroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the group memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching group memberships
	 */
	@Override
	public List<GroupMembership> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator,
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

		List<GroupMembership> list = null;

		if (useFinderCache) {
			list = (List<GroupMembership>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GroupMembership groupMembership : list) {
					if (groupId != groupMembership.getGroupId()) {
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

			sb.append(_SQL_SELECT_GROUPMEMBERSHIP_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GroupMembershipModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<GroupMembership>)QueryUtil.list(
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
	 * Returns the first group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	@Override
	public GroupMembership findBygroupId_First(
			long groupId, OrderByComparator<GroupMembership> orderByComparator)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = fetchBygroupId_First(
			groupId, orderByComparator);

		if (groupMembership != null) {
			return groupMembership;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchMembershipException(sb.toString());
	}

	/**
	 * Returns the first group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	@Override
	public GroupMembership fetchBygroupId_First(
		long groupId, OrderByComparator<GroupMembership> orderByComparator) {

		List<GroupMembership> list = findBygroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	@Override
	public GroupMembership findBygroupId_Last(
			long groupId, OrderByComparator<GroupMembership> orderByComparator)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = fetchBygroupId_Last(
			groupId, orderByComparator);

		if (groupMembership != null) {
			return groupMembership;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchMembershipException(sb.toString());
	}

	/**
	 * Returns the last group membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	@Override
	public GroupMembership fetchBygroupId_Last(
		long groupId, OrderByComparator<GroupMembership> orderByComparator) {

		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<GroupMembership> list = findBygroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the group memberships before and after the current group membership in the ordered set where groupId = &#63;.
	 *
	 * @param membershipId the primary key of the current group membership
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	@Override
	public GroupMembership[] findBygroupId_PrevAndNext(
			long membershipId, long groupId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = findByPrimaryKey(membershipId);

		Session session = null;

		try {
			session = openSession();

			GroupMembership[] array = new GroupMembershipImpl[3];

			array[0] = getBygroupId_PrevAndNext(
				session, groupMembership, groupId, orderByComparator, true);

			array[1] = groupMembership;

			array[2] = getBygroupId_PrevAndNext(
				session, groupMembership, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected GroupMembership getBygroupId_PrevAndNext(
		Session session, GroupMembership groupMembership, long groupId,
		OrderByComparator<GroupMembership> orderByComparator,
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

		sb.append(_SQL_SELECT_GROUPMEMBERSHIP_WHERE);

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
			sb.append(GroupMembershipModelImpl.ORDER_BY_JPQL);
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
						groupMembership)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GroupMembership> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the group memberships where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupId(long groupId) {
		for (GroupMembership groupMembership :
				findBygroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(groupMembership);
		}
	}

	/**
	 * Returns the number of group memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching group memberships
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = _finderPathCountBygroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_GROUPMEMBERSHIP_WHERE);

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
		"groupMembership.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByuserId;
	private FinderPath _finderPathWithoutPaginationFindByuserId;
	private FinderPath _finderPathCountByuserId;

	/**
	 * Returns all the group memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching group memberships
	 */
	@Override
	public List<GroupMembership> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the group memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @return the range of matching group memberships
	 */
	@Override
	public List<GroupMembership> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the group memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching group memberships
	 */
	@Override
	public List<GroupMembership> findByuserId(
		long userId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator) {

		return findByuserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the group memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching group memberships
	 */
	@Override
	public List<GroupMembership> findByuserId(
		long userId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByuserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByuserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<GroupMembership> list = null;

		if (useFinderCache) {
			list = (List<GroupMembership>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GroupMembership groupMembership : list) {
					if (userId != groupMembership.getUserId()) {
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

			sb.append(_SQL_SELECT_GROUPMEMBERSHIP_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GroupMembershipModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<GroupMembership>)QueryUtil.list(
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
	 * Returns the first group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	@Override
	public GroupMembership findByuserId_First(
			long userId, OrderByComparator<GroupMembership> orderByComparator)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = fetchByuserId_First(
			userId, orderByComparator);

		if (groupMembership != null) {
			return groupMembership;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMembershipException(sb.toString());
	}

	/**
	 * Returns the first group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	@Override
	public GroupMembership fetchByuserId_First(
		long userId, OrderByComparator<GroupMembership> orderByComparator) {

		List<GroupMembership> list = findByuserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	@Override
	public GroupMembership findByuserId_Last(
			long userId, OrderByComparator<GroupMembership> orderByComparator)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = fetchByuserId_Last(
			userId, orderByComparator);

		if (groupMembership != null) {
			return groupMembership;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMembershipException(sb.toString());
	}

	/**
	 * Returns the last group membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	@Override
	public GroupMembership fetchByuserId_Last(
		long userId, OrderByComparator<GroupMembership> orderByComparator) {

		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<GroupMembership> list = findByuserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the group memberships before and after the current group membership in the ordered set where userId = &#63;.
	 *
	 * @param membershipId the primary key of the current group membership
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	@Override
	public GroupMembership[] findByuserId_PrevAndNext(
			long membershipId, long userId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = findByPrimaryKey(membershipId);

		Session session = null;

		try {
			session = openSession();

			GroupMembership[] array = new GroupMembershipImpl[3];

			array[0] = getByuserId_PrevAndNext(
				session, groupMembership, userId, orderByComparator, true);

			array[1] = groupMembership;

			array[2] = getByuserId_PrevAndNext(
				session, groupMembership, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected GroupMembership getByuserId_PrevAndNext(
		Session session, GroupMembership groupMembership, long userId,
		OrderByComparator<GroupMembership> orderByComparator,
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

		sb.append(_SQL_SELECT_GROUPMEMBERSHIP_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

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
			sb.append(GroupMembershipModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						groupMembership)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GroupMembership> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the group memberships where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (GroupMembership groupMembership :
				findByuserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(groupMembership);
		}
	}

	/**
	 * Returns the number of group memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching group memberships
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_GROUPMEMBERSHIP_WHERE);

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
		"groupMembership.userId = ?";

	private FinderPath _finderPathWithPaginationFindByuserId_groupId;
	private FinderPath _finderPathWithoutPaginationFindByuserId_groupId;
	private FinderPath _finderPathCountByuserId_groupId;

	/**
	 * Returns all the group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching group memberships
	 */
	@Override
	public List<GroupMembership> findByuserId_groupId(
		long userId, long groupId) {

		return findByuserId_groupId(
			userId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @return the range of matching group memberships
	 */
	@Override
	public List<GroupMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end) {

		return findByuserId_groupId(userId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching group memberships
	 */
	@Override
	public List<GroupMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator) {

		return findByuserId_groupId(
			userId, groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching group memberships
	 */
	@Override
	public List<GroupMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		OrderByComparator<GroupMembership> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByuserId_groupId;
				finderArgs = new Object[] {userId, groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByuserId_groupId;
			finderArgs = new Object[] {
				userId, groupId, start, end, orderByComparator
			};
		}

		List<GroupMembership> list = null;

		if (useFinderCache) {
			list = (List<GroupMembership>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GroupMembership groupMembership : list) {
					if ((userId != groupMembership.getUserId()) ||
						(groupId != groupMembership.getGroupId())) {

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

			sb.append(_SQL_SELECT_GROUPMEMBERSHIP_WHERE);

			sb.append(_FINDER_COLUMN_USERID_GROUPID_USERID_2);

			sb.append(_FINDER_COLUMN_USERID_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GroupMembershipModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(groupId);

				list = (List<GroupMembership>)QueryUtil.list(
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
	 * Returns the first group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	@Override
	public GroupMembership findByuserId_groupId_First(
			long userId, long groupId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = fetchByuserId_groupId_First(
			userId, groupId, orderByComparator);

		if (groupMembership != null) {
			return groupMembership;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchMembershipException(sb.toString());
	}

	/**
	 * Returns the first group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	@Override
	public GroupMembership fetchByuserId_groupId_First(
		long userId, long groupId,
		OrderByComparator<GroupMembership> orderByComparator) {

		List<GroupMembership> list = findByuserId_groupId(
			userId, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership
	 * @throws NoSuchMembershipException if a matching group membership could not be found
	 */
	@Override
	public GroupMembership findByuserId_groupId_Last(
			long userId, long groupId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = fetchByuserId_groupId_Last(
			userId, groupId, orderByComparator);

		if (groupMembership != null) {
			return groupMembership;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchMembershipException(sb.toString());
	}

	/**
	 * Returns the last group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching group membership, or <code>null</code> if a matching group membership could not be found
	 */
	@Override
	public GroupMembership fetchByuserId_groupId_Last(
		long userId, long groupId,
		OrderByComparator<GroupMembership> orderByComparator) {

		int count = countByuserId_groupId(userId, groupId);

		if (count == 0) {
			return null;
		}

		List<GroupMembership> list = findByuserId_groupId(
			userId, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the group memberships before and after the current group membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param membershipId the primary key of the current group membership
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	@Override
	public GroupMembership[] findByuserId_groupId_PrevAndNext(
			long membershipId, long userId, long groupId,
			OrderByComparator<GroupMembership> orderByComparator)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = findByPrimaryKey(membershipId);

		Session session = null;

		try {
			session = openSession();

			GroupMembership[] array = new GroupMembershipImpl[3];

			array[0] = getByuserId_groupId_PrevAndNext(
				session, groupMembership, userId, groupId, orderByComparator,
				true);

			array[1] = groupMembership;

			array[2] = getByuserId_groupId_PrevAndNext(
				session, groupMembership, userId, groupId, orderByComparator,
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

	protected GroupMembership getByuserId_groupId_PrevAndNext(
		Session session, GroupMembership groupMembership, long userId,
		long groupId, OrderByComparator<GroupMembership> orderByComparator,
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

		sb.append(_SQL_SELECT_GROUPMEMBERSHIP_WHERE);

		sb.append(_FINDER_COLUMN_USERID_GROUPID_USERID_2);

		sb.append(_FINDER_COLUMN_USERID_GROUPID_GROUPID_2);

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
			sb.append(GroupMembershipModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						groupMembership)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GroupMembership> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the group memberships where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByuserId_groupId(long userId, long groupId) {
		for (GroupMembership groupMembership :
				findByuserId_groupId(
					userId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(groupMembership);
		}
	}

	/**
	 * Returns the number of group memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching group memberships
	 */
	@Override
	public int countByuserId_groupId(long userId, long groupId) {
		FinderPath finderPath = _finderPathCountByuserId_groupId;

		Object[] finderArgs = new Object[] {userId, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_GROUPMEMBERSHIP_WHERE);

			sb.append(_FINDER_COLUMN_USERID_GROUPID_USERID_2);

			sb.append(_FINDER_COLUMN_USERID_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_GROUPID_USERID_2 =
		"groupMembership.userId = ? AND ";

	private static final String _FINDER_COLUMN_USERID_GROUPID_GROUPID_2 =
		"groupMembership.groupId = ?";

	public GroupMembershipPersistenceImpl() {
		setModelClass(GroupMembership.class);

		setModelImplClass(GroupMembershipImpl.class);
		setModelPKClass(long.class);

		setTable(GroupMembershipTable.INSTANCE);
	}

	/**
	 * Caches the group membership in the entity cache if it is enabled.
	 *
	 * @param groupMembership the group membership
	 */
	@Override
	public void cacheResult(GroupMembership groupMembership) {
		entityCache.putResult(
			GroupMembershipImpl.class, groupMembership.getPrimaryKey(),
			groupMembership);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the group memberships in the entity cache if it is enabled.
	 *
	 * @param groupMemberships the group memberships
	 */
	@Override
	public void cacheResult(List<GroupMembership> groupMemberships) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (groupMemberships.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (GroupMembership groupMembership : groupMemberships) {
			if (entityCache.getResult(
					GroupMembershipImpl.class,
					groupMembership.getPrimaryKey()) == null) {

				cacheResult(groupMembership);
			}
		}
	}

	/**
	 * Clears the cache for all group memberships.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GroupMembershipImpl.class);

		finderCache.clearCache(GroupMembershipImpl.class);
	}

	/**
	 * Clears the cache for the group membership.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GroupMembership groupMembership) {
		entityCache.removeResult(GroupMembershipImpl.class, groupMembership);
	}

	@Override
	public void clearCache(List<GroupMembership> groupMemberships) {
		for (GroupMembership groupMembership : groupMemberships) {
			entityCache.removeResult(
				GroupMembershipImpl.class, groupMembership);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(GroupMembershipImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(GroupMembershipImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new group membership with the primary key. Does not add the group membership to the database.
	 *
	 * @param membershipId the primary key for the new group membership
	 * @return the new group membership
	 */
	@Override
	public GroupMembership create(long membershipId) {
		GroupMembership groupMembership = new GroupMembershipImpl();

		groupMembership.setNew(true);
		groupMembership.setPrimaryKey(membershipId);

		return groupMembership;
	}

	/**
	 * Removes the group membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership that was removed
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	@Override
	public GroupMembership remove(long membershipId)
		throws NoSuchMembershipException {

		return remove((Serializable)membershipId);
	}

	/**
	 * Removes the group membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the group membership
	 * @return the group membership that was removed
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	@Override
	public GroupMembership remove(Serializable primaryKey)
		throws NoSuchMembershipException {

		Session session = null;

		try {
			session = openSession();

			GroupMembership groupMembership = (GroupMembership)session.get(
				GroupMembershipImpl.class, primaryKey);

			if (groupMembership == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMembershipException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(groupMembership);
		}
		catch (NoSuchMembershipException noSuchEntityException) {
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
	protected GroupMembership removeImpl(GroupMembership groupMembership) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(groupMembership)) {
				groupMembership = (GroupMembership)session.get(
					GroupMembershipImpl.class,
					groupMembership.getPrimaryKeyObj());
			}

			if (groupMembership != null) {
				session.delete(groupMembership);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (groupMembership != null) {
			clearCache(groupMembership);
		}

		return groupMembership;
	}

	@Override
	public GroupMembership updateImpl(GroupMembership groupMembership) {
		boolean isNew = groupMembership.isNew();

		if (!(groupMembership instanceof GroupMembershipModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(groupMembership.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					groupMembership);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in groupMembership proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom GroupMembership implementation " +
					groupMembership.getClass());
		}

		GroupMembershipModelImpl groupMembershipModelImpl =
			(GroupMembershipModelImpl)groupMembership;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(groupMembership);
			}
			else {
				groupMembership = (GroupMembership)session.merge(
					groupMembership);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			GroupMembershipImpl.class, groupMembershipModelImpl, false, true);

		if (isNew) {
			groupMembership.setNew(false);
		}

		groupMembership.resetOriginalValues();

		return groupMembership;
	}

	/**
	 * Returns the group membership with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the group membership
	 * @return the group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	@Override
	public GroupMembership findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMembershipException {

		GroupMembership groupMembership = fetchByPrimaryKey(primaryKey);

		if (groupMembership == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMembershipException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return groupMembership;
	}

	/**
	 * Returns the group membership with the primary key or throws a <code>NoSuchMembershipException</code> if it could not be found.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership
	 * @throws NoSuchMembershipException if a group membership with the primary key could not be found
	 */
	@Override
	public GroupMembership findByPrimaryKey(long membershipId)
		throws NoSuchMembershipException {

		return findByPrimaryKey((Serializable)membershipId);
	}

	/**
	 * Returns the group membership with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership, or <code>null</code> if a group membership with the primary key could not be found
	 */
	@Override
	public GroupMembership fetchByPrimaryKey(long membershipId) {
		return fetchByPrimaryKey((Serializable)membershipId);
	}

	/**
	 * Returns all the group memberships.
	 *
	 * @return the group memberships
	 */
	@Override
	public List<GroupMembership> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the group memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @return the range of group memberships
	 */
	@Override
	public List<GroupMembership> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the group memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of group memberships
	 */
	@Override
	public List<GroupMembership> findAll(
		int start, int end,
		OrderByComparator<GroupMembership> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the group memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of group memberships
	 */
	@Override
	public List<GroupMembership> findAll(
		int start, int end,
		OrderByComparator<GroupMembership> orderByComparator,
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

		List<GroupMembership> list = null;

		if (useFinderCache) {
			list = (List<GroupMembership>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_GROUPMEMBERSHIP);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_GROUPMEMBERSHIP;

				sql = sql.concat(GroupMembershipModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<GroupMembership>)QueryUtil.list(
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
	 * Removes all the group memberships from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GroupMembership groupMembership : findAll()) {
			remove(groupMembership);
		}
	}

	/**
	 * Returns the number of group memberships.
	 *
	 * @return the number of group memberships
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_GROUPMEMBERSHIP);

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
		return "membershipId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_GROUPMEMBERSHIP;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GroupMembershipModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the group membership persistence.
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

		_finderPathWithPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_finderPathWithPaginationFindByuserId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId_groupId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"userId", "groupId"}, true);

		_finderPathWithoutPaginationFindByuserId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId_groupId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"userId", "groupId"}, true);

		_finderPathCountByuserId_groupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId_groupId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"userId", "groupId"}, false);

		_setGroupMembershipUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setGroupMembershipUtilPersistence(null);

		entityCache.removeCache(GroupMembershipImpl.class.getName());
	}

	private void _setGroupMembershipUtilPersistence(
		GroupMembershipPersistence groupMembershipPersistence) {

		try {
			Field field = GroupMembershipUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, groupMembershipPersistence);
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

	private static final String _SQL_SELECT_GROUPMEMBERSHIP =
		"SELECT groupMembership FROM GroupMembership groupMembership";

	private static final String _SQL_SELECT_GROUPMEMBERSHIP_WHERE =
		"SELECT groupMembership FROM GroupMembership groupMembership WHERE ";

	private static final String _SQL_COUNT_GROUPMEMBERSHIP =
		"SELECT COUNT(groupMembership) FROM GroupMembership groupMembership";

	private static final String _SQL_COUNT_GROUPMEMBERSHIP_WHERE =
		"SELECT COUNT(groupMembership) FROM GroupMembership groupMembership WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "groupMembership.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No GroupMembership exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No GroupMembership exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		GroupMembershipPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}