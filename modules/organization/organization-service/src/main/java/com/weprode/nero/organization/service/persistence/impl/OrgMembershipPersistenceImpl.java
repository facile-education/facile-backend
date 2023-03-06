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

package com.weprode.nero.organization.service.persistence.impl;

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

import com.weprode.nero.organization.exception.NoSuchOrgMembershipException;
import com.weprode.nero.organization.model.OrgMembership;
import com.weprode.nero.organization.model.OrgMembershipTable;
import com.weprode.nero.organization.model.impl.OrgMembershipImpl;
import com.weprode.nero.organization.model.impl.OrgMembershipModelImpl;
import com.weprode.nero.organization.service.persistence.OrgMembershipPersistence;
import com.weprode.nero.organization.service.persistence.OrgMembershipUtil;
import com.weprode.nero.organization.service.persistence.impl.constants.OrganizationPersistenceConstants;

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
 * The persistence implementation for the org membership service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marc Salvat
 * @generated
 */
@Component(service = {OrgMembershipPersistence.class, BasePersistence.class})
public class OrgMembershipPersistenceImpl
	extends BasePersistenceImpl<OrgMembership>
	implements OrgMembershipPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>OrgMembershipUtil</code> to access the org membership persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		OrgMembershipImpl.class.getName();

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
	 * Returns all the org memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching org memberships
	 */
	@Override
	public List<OrgMembership> findBygroupId(long groupId) {
		return findBygroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @return the range of matching org memberships
	 */
	@Override
	public List<OrgMembership> findBygroupId(long groupId, int start, int end) {
		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the org memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org memberships
	 */
	@Override
	public List<OrgMembership> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator) {

		return findBygroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org memberships where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org memberships
	 */
	@Override
	public List<OrgMembership> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator,
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

		List<OrgMembership> list = null;

		if (useFinderCache) {
			list = (List<OrgMembership>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (OrgMembership orgMembership : list) {
					if (groupId != orgMembership.getGroupId()) {
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

			sb.append(_SQL_SELECT_ORGMEMBERSHIP_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OrgMembershipModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<OrgMembership>)QueryUtil.list(
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
	 * Returns the first org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	@Override
	public OrgMembership findBygroupId_First(
			long groupId, OrderByComparator<OrgMembership> orderByComparator)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = fetchBygroupId_First(
			groupId, orderByComparator);

		if (orgMembership != null) {
			return orgMembership;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchOrgMembershipException(sb.toString());
	}

	/**
	 * Returns the first org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	@Override
	public OrgMembership fetchBygroupId_First(
		long groupId, OrderByComparator<OrgMembership> orderByComparator) {

		List<OrgMembership> list = findBygroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	@Override
	public OrgMembership findBygroupId_Last(
			long groupId, OrderByComparator<OrgMembership> orderByComparator)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = fetchBygroupId_Last(
			groupId, orderByComparator);

		if (orgMembership != null) {
			return orgMembership;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchOrgMembershipException(sb.toString());
	}

	/**
	 * Returns the last org membership in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	@Override
	public OrgMembership fetchBygroupId_Last(
		long groupId, OrderByComparator<OrgMembership> orderByComparator) {

		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<OrgMembership> list = findBygroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the org memberships before and after the current org membership in the ordered set where groupId = &#63;.
	 *
	 * @param orgMemberId the primary key of the current org membership
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	@Override
	public OrgMembership[] findBygroupId_PrevAndNext(
			long orgMemberId, long groupId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = findByPrimaryKey(orgMemberId);

		Session session = null;

		try {
			session = openSession();

			OrgMembership[] array = new OrgMembershipImpl[3];

			array[0] = getBygroupId_PrevAndNext(
				session, orgMembership, groupId, orderByComparator, true);

			array[1] = orgMembership;

			array[2] = getBygroupId_PrevAndNext(
				session, orgMembership, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected OrgMembership getBygroupId_PrevAndNext(
		Session session, OrgMembership orgMembership, long groupId,
		OrderByComparator<OrgMembership> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ORGMEMBERSHIP_WHERE);

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
			sb.append(OrgMembershipModelImpl.ORDER_BY_JPQL);
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
						orgMembership)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OrgMembership> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the org memberships where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupId(long groupId) {
		for (OrgMembership orgMembership :
				findBygroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(orgMembership);
		}
	}

	/**
	 * Returns the number of org memberships where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching org memberships
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = _finderPathCountBygroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ORGMEMBERSHIP_WHERE);

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
		"orgMembership.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByuserId;
	private FinderPath _finderPathWithoutPaginationFindByuserId;
	private FinderPath _finderPathCountByuserId;

	/**
	 * Returns all the org memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching org memberships
	 */
	@Override
	public List<OrgMembership> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @return the range of matching org memberships
	 */
	@Override
	public List<OrgMembership> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the org memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org memberships
	 */
	@Override
	public List<OrgMembership> findByuserId(
		long userId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator) {

		return findByuserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org memberships where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org memberships
	 */
	@Override
	public List<OrgMembership> findByuserId(
		long userId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator,
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

		List<OrgMembership> list = null;

		if (useFinderCache) {
			list = (List<OrgMembership>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (OrgMembership orgMembership : list) {
					if (userId != orgMembership.getUserId()) {
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

			sb.append(_SQL_SELECT_ORGMEMBERSHIP_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OrgMembershipModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<OrgMembership>)QueryUtil.list(
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
	 * Returns the first org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	@Override
	public OrgMembership findByuserId_First(
			long userId, OrderByComparator<OrgMembership> orderByComparator)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = fetchByuserId_First(
			userId, orderByComparator);

		if (orgMembership != null) {
			return orgMembership;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchOrgMembershipException(sb.toString());
	}

	/**
	 * Returns the first org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	@Override
	public OrgMembership fetchByuserId_First(
		long userId, OrderByComparator<OrgMembership> orderByComparator) {

		List<OrgMembership> list = findByuserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	@Override
	public OrgMembership findByuserId_Last(
			long userId, OrderByComparator<OrgMembership> orderByComparator)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = fetchByuserId_Last(
			userId, orderByComparator);

		if (orgMembership != null) {
			return orgMembership;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchOrgMembershipException(sb.toString());
	}

	/**
	 * Returns the last org membership in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	@Override
	public OrgMembership fetchByuserId_Last(
		long userId, OrderByComparator<OrgMembership> orderByComparator) {

		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<OrgMembership> list = findByuserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the org memberships before and after the current org membership in the ordered set where userId = &#63;.
	 *
	 * @param orgMemberId the primary key of the current org membership
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	@Override
	public OrgMembership[] findByuserId_PrevAndNext(
			long orgMemberId, long userId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = findByPrimaryKey(orgMemberId);

		Session session = null;

		try {
			session = openSession();

			OrgMembership[] array = new OrgMembershipImpl[3];

			array[0] = getByuserId_PrevAndNext(
				session, orgMembership, userId, orderByComparator, true);

			array[1] = orgMembership;

			array[2] = getByuserId_PrevAndNext(
				session, orgMembership, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected OrgMembership getByuserId_PrevAndNext(
		Session session, OrgMembership orgMembership, long userId,
		OrderByComparator<OrgMembership> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ORGMEMBERSHIP_WHERE);

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
			sb.append(OrgMembershipModelImpl.ORDER_BY_JPQL);
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
						orgMembership)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OrgMembership> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the org memberships where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (OrgMembership orgMembership :
				findByuserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(orgMembership);
		}
	}

	/**
	 * Returns the number of org memberships where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching org memberships
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ORGMEMBERSHIP_WHERE);

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
		"orgMembership.userId = ?";

	private FinderPath _finderPathWithPaginationFindByuserId_groupId;
	private FinderPath _finderPathWithoutPaginationFindByuserId_groupId;
	private FinderPath _finderPathCountByuserId_groupId;

	/**
	 * Returns all the org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching org memberships
	 */
	@Override
	public List<OrgMembership> findByuserId_groupId(long userId, long groupId) {
		return findByuserId_groupId(
			userId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @return the range of matching org memberships
	 */
	@Override
	public List<OrgMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end) {

		return findByuserId_groupId(userId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org memberships
	 */
	@Override
	public List<OrgMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator) {

		return findByuserId_groupId(
			userId, groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org memberships
	 */
	@Override
	public List<OrgMembership> findByuserId_groupId(
		long userId, long groupId, int start, int end,
		OrderByComparator<OrgMembership> orderByComparator,
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

		List<OrgMembership> list = null;

		if (useFinderCache) {
			list = (List<OrgMembership>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (OrgMembership orgMembership : list) {
					if ((userId != orgMembership.getUserId()) ||
						(groupId != orgMembership.getGroupId())) {

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

			sb.append(_SQL_SELECT_ORGMEMBERSHIP_WHERE);

			sb.append(_FINDER_COLUMN_USERID_GROUPID_USERID_2);

			sb.append(_FINDER_COLUMN_USERID_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OrgMembershipModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(groupId);

				list = (List<OrgMembership>)QueryUtil.list(
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
	 * Returns the first org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	@Override
	public OrgMembership findByuserId_groupId_First(
			long userId, long groupId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = fetchByuserId_groupId_First(
			userId, groupId, orderByComparator);

		if (orgMembership != null) {
			return orgMembership;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchOrgMembershipException(sb.toString());
	}

	/**
	 * Returns the first org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	@Override
	public OrgMembership fetchByuserId_groupId_First(
		long userId, long groupId,
		OrderByComparator<OrgMembership> orderByComparator) {

		List<OrgMembership> list = findByuserId_groupId(
			userId, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership
	 * @throws NoSuchOrgMembershipException if a matching org membership could not be found
	 */
	@Override
	public OrgMembership findByuserId_groupId_Last(
			long userId, long groupId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = fetchByuserId_groupId_Last(
			userId, groupId, orderByComparator);

		if (orgMembership != null) {
			return orgMembership;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchOrgMembershipException(sb.toString());
	}

	/**
	 * Returns the last org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org membership, or <code>null</code> if a matching org membership could not be found
	 */
	@Override
	public OrgMembership fetchByuserId_groupId_Last(
		long userId, long groupId,
		OrderByComparator<OrgMembership> orderByComparator) {

		int count = countByuserId_groupId(userId, groupId);

		if (count == 0) {
			return null;
		}

		List<OrgMembership> list = findByuserId_groupId(
			userId, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the org memberships before and after the current org membership in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param orgMemberId the primary key of the current org membership
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	@Override
	public OrgMembership[] findByuserId_groupId_PrevAndNext(
			long orgMemberId, long userId, long groupId,
			OrderByComparator<OrgMembership> orderByComparator)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = findByPrimaryKey(orgMemberId);

		Session session = null;

		try {
			session = openSession();

			OrgMembership[] array = new OrgMembershipImpl[3];

			array[0] = getByuserId_groupId_PrevAndNext(
				session, orgMembership, userId, groupId, orderByComparator,
				true);

			array[1] = orgMembership;

			array[2] = getByuserId_groupId_PrevAndNext(
				session, orgMembership, userId, groupId, orderByComparator,
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

	protected OrgMembership getByuserId_groupId_PrevAndNext(
		Session session, OrgMembership orgMembership, long userId, long groupId,
		OrderByComparator<OrgMembership> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_ORGMEMBERSHIP_WHERE);

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
			sb.append(OrgMembershipModelImpl.ORDER_BY_JPQL);
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
						orgMembership)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OrgMembership> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the org memberships where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByuserId_groupId(long userId, long groupId) {
		for (OrgMembership orgMembership :
				findByuserId_groupId(
					userId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(orgMembership);
		}
	}

	/**
	 * Returns the number of org memberships where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching org memberships
	 */
	@Override
	public int countByuserId_groupId(long userId, long groupId) {
		FinderPath finderPath = _finderPathCountByuserId_groupId;

		Object[] finderArgs = new Object[] {userId, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ORGMEMBERSHIP_WHERE);

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
		"orgMembership.userId = ? AND ";

	private static final String _FINDER_COLUMN_USERID_GROUPID_GROUPID_2 =
		"orgMembership.groupId = ?";

	public OrgMembershipPersistenceImpl() {
		setModelClass(OrgMembership.class);

		setModelImplClass(OrgMembershipImpl.class);
		setModelPKClass(long.class);

		setTable(OrgMembershipTable.INSTANCE);
	}

	/**
	 * Caches the org membership in the entity cache if it is enabled.
	 *
	 * @param orgMembership the org membership
	 */
	@Override
	public void cacheResult(OrgMembership orgMembership) {
		entityCache.putResult(
			OrgMembershipImpl.class, orgMembership.getPrimaryKey(),
			orgMembership);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the org memberships in the entity cache if it is enabled.
	 *
	 * @param orgMemberships the org memberships
	 */
	@Override
	public void cacheResult(List<OrgMembership> orgMemberships) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (orgMemberships.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (OrgMembership orgMembership : orgMemberships) {
			if (entityCache.getResult(
					OrgMembershipImpl.class, orgMembership.getPrimaryKey()) ==
						null) {

				cacheResult(orgMembership);
			}
		}
	}

	/**
	 * Clears the cache for all org memberships.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OrgMembershipImpl.class);

		finderCache.clearCache(OrgMembershipImpl.class);
	}

	/**
	 * Clears the cache for the org membership.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OrgMembership orgMembership) {
		entityCache.removeResult(OrgMembershipImpl.class, orgMembership);
	}

	@Override
	public void clearCache(List<OrgMembership> orgMemberships) {
		for (OrgMembership orgMembership : orgMemberships) {
			entityCache.removeResult(OrgMembershipImpl.class, orgMembership);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(OrgMembershipImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(OrgMembershipImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new org membership with the primary key. Does not add the org membership to the database.
	 *
	 * @param orgMemberId the primary key for the new org membership
	 * @return the new org membership
	 */
	@Override
	public OrgMembership create(long orgMemberId) {
		OrgMembership orgMembership = new OrgMembershipImpl();

		orgMembership.setNew(true);
		orgMembership.setPrimaryKey(orgMemberId);

		return orgMembership;
	}

	/**
	 * Removes the org membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership that was removed
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	@Override
	public OrgMembership remove(long orgMemberId)
		throws NoSuchOrgMembershipException {

		return remove((Serializable)orgMemberId);
	}

	/**
	 * Removes the org membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the org membership
	 * @return the org membership that was removed
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	@Override
	public OrgMembership remove(Serializable primaryKey)
		throws NoSuchOrgMembershipException {

		Session session = null;

		try {
			session = openSession();

			OrgMembership orgMembership = (OrgMembership)session.get(
				OrgMembershipImpl.class, primaryKey);

			if (orgMembership == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrgMembershipException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(orgMembership);
		}
		catch (NoSuchOrgMembershipException noSuchEntityException) {
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
	protected OrgMembership removeImpl(OrgMembership orgMembership) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(orgMembership)) {
				orgMembership = (OrgMembership)session.get(
					OrgMembershipImpl.class, orgMembership.getPrimaryKeyObj());
			}

			if (orgMembership != null) {
				session.delete(orgMembership);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (orgMembership != null) {
			clearCache(orgMembership);
		}

		return orgMembership;
	}

	@Override
	public OrgMembership updateImpl(OrgMembership orgMembership) {
		boolean isNew = orgMembership.isNew();

		if (!(orgMembership instanceof OrgMembershipModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(orgMembership.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					orgMembership);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in orgMembership proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OrgMembership implementation " +
					orgMembership.getClass());
		}

		OrgMembershipModelImpl orgMembershipModelImpl =
			(OrgMembershipModelImpl)orgMembership;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(orgMembership);
			}
			else {
				orgMembership = (OrgMembership)session.merge(orgMembership);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			OrgMembershipImpl.class, orgMembershipModelImpl, false, true);

		if (isNew) {
			orgMembership.setNew(false);
		}

		orgMembership.resetOriginalValues();

		return orgMembership;
	}

	/**
	 * Returns the org membership with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the org membership
	 * @return the org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	@Override
	public OrgMembership findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrgMembershipException {

		OrgMembership orgMembership = fetchByPrimaryKey(primaryKey);

		if (orgMembership == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrgMembershipException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return orgMembership;
	}

	/**
	 * Returns the org membership with the primary key or throws a <code>NoSuchOrgMembershipException</code> if it could not be found.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership
	 * @throws NoSuchOrgMembershipException if a org membership with the primary key could not be found
	 */
	@Override
	public OrgMembership findByPrimaryKey(long orgMemberId)
		throws NoSuchOrgMembershipException {

		return findByPrimaryKey((Serializable)orgMemberId);
	}

	/**
	 * Returns the org membership with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership, or <code>null</code> if a org membership with the primary key could not be found
	 */
	@Override
	public OrgMembership fetchByPrimaryKey(long orgMemberId) {
		return fetchByPrimaryKey((Serializable)orgMemberId);
	}

	/**
	 * Returns all the org memberships.
	 *
	 * @return the org memberships
	 */
	@Override
	public List<OrgMembership> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @return the range of org memberships
	 */
	@Override
	public List<OrgMembership> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the org memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of org memberships
	 */
	@Override
	public List<OrgMembership> findAll(
		int start, int end,
		OrderByComparator<OrgMembership> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of org memberships
	 */
	@Override
	public List<OrgMembership> findAll(
		int start, int end, OrderByComparator<OrgMembership> orderByComparator,
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

		List<OrgMembership> list = null;

		if (useFinderCache) {
			list = (List<OrgMembership>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ORGMEMBERSHIP);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ORGMEMBERSHIP;

				sql = sql.concat(OrgMembershipModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<OrgMembership>)QueryUtil.list(
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
	 * Removes all the org memberships from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OrgMembership orgMembership : findAll()) {
			remove(orgMembership);
		}
	}

	/**
	 * Returns the number of org memberships.
	 *
	 * @return the number of org memberships
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ORGMEMBERSHIP);

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
		return "orgMemberId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ORGMEMBERSHIP;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OrgMembershipModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the org membership persistence.
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

		_setOrgMembershipUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setOrgMembershipUtilPersistence(null);

		entityCache.removeCache(OrgMembershipImpl.class.getName());
	}

	private void _setOrgMembershipUtilPersistence(
		OrgMembershipPersistence orgMembershipPersistence) {

		try {
			Field field = OrgMembershipUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, orgMembershipPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = OrganizationPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = OrganizationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = OrganizationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ORGMEMBERSHIP =
		"SELECT orgMembership FROM OrgMembership orgMembership";

	private static final String _SQL_SELECT_ORGMEMBERSHIP_WHERE =
		"SELECT orgMembership FROM OrgMembership orgMembership WHERE ";

	private static final String _SQL_COUNT_ORGMEMBERSHIP =
		"SELECT COUNT(orgMembership) FROM OrgMembership orgMembership";

	private static final String _SQL_COUNT_ORGMEMBERSHIP_WHERE =
		"SELECT COUNT(orgMembership) FROM OrgMembership orgMembership WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "orgMembership.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No OrgMembership exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No OrgMembership exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		OrgMembershipPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private OrgMembershipModelArgumentsResolver
		_orgMembershipModelArgumentsResolver;

}