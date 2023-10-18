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

package com.weprode.nero.application.service.persistence.impl;

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

import com.weprode.nero.application.exception.NoSuchBroadcastRuleException;
import com.weprode.nero.application.model.BroadcastRule;
import com.weprode.nero.application.model.BroadcastRuleTable;
import com.weprode.nero.application.model.impl.BroadcastRuleImpl;
import com.weprode.nero.application.model.impl.BroadcastRuleModelImpl;
import com.weprode.nero.application.service.persistence.BroadcastRulePersistence;
import com.weprode.nero.application.service.persistence.BroadcastRuleUtil;
import com.weprode.nero.application.service.persistence.impl.constants.ApplicationPersistenceConstants;

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
 * The persistence implementation for the broadcast rule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {BroadcastRulePersistence.class, BasePersistence.class})
public class BroadcastRulePersistenceImpl
	extends BasePersistenceImpl<BroadcastRule>
	implements BroadcastRulePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BroadcastRuleUtil</code> to access the broadcast rule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BroadcastRuleImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByschoolId;
	private FinderPath _finderPathWithoutPaginationFindByschoolId;
	private FinderPath _finderPathCountByschoolId;

	/**
	 * Returns all the broadcast rules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching broadcast rules
	 */
	@Override
	public List<BroadcastRule> findByschoolId(long schoolId) {
		return findByschoolId(
			schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the broadcast rules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @return the range of matching broadcast rules
	 */
	@Override
	public List<BroadcastRule> findByschoolId(
		long schoolId, int start, int end) {

		return findByschoolId(schoolId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the broadcast rules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching broadcast rules
	 */
	@Override
	public List<BroadcastRule> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator) {

		return findByschoolId(schoolId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the broadcast rules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching broadcast rules
	 */
	@Override
	public List<BroadcastRule> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByschoolId;
				finderArgs = new Object[] {schoolId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByschoolId;
			finderArgs = new Object[] {schoolId, start, end, orderByComparator};
		}

		List<BroadcastRule> list = null;

		if (useFinderCache) {
			list = (List<BroadcastRule>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BroadcastRule broadcastRule : list) {
					if (schoolId != broadcastRule.getSchoolId()) {
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

			sb.append(_SQL_SELECT_BROADCASTRULE_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BroadcastRuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				list = (List<BroadcastRule>)QueryUtil.list(
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
	 * Returns the first broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	@Override
	public BroadcastRule findByschoolId_First(
			long schoolId, OrderByComparator<BroadcastRule> orderByComparator)
		throws NoSuchBroadcastRuleException {

		BroadcastRule broadcastRule = fetchByschoolId_First(
			schoolId, orderByComparator);

		if (broadcastRule != null) {
			return broadcastRule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchBroadcastRuleException(sb.toString());
	}

	/**
	 * Returns the first broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	@Override
	public BroadcastRule fetchByschoolId_First(
		long schoolId, OrderByComparator<BroadcastRule> orderByComparator) {

		List<BroadcastRule> list = findByschoolId(
			schoolId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	@Override
	public BroadcastRule findByschoolId_Last(
			long schoolId, OrderByComparator<BroadcastRule> orderByComparator)
		throws NoSuchBroadcastRuleException {

		BroadcastRule broadcastRule = fetchByschoolId_Last(
			schoolId, orderByComparator);

		if (broadcastRule != null) {
			return broadcastRule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchBroadcastRuleException(sb.toString());
	}

	/**
	 * Returns the last broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	@Override
	public BroadcastRule fetchByschoolId_Last(
		long schoolId, OrderByComparator<BroadcastRule> orderByComparator) {

		int count = countByschoolId(schoolId);

		if (count == 0) {
			return null;
		}

		List<BroadcastRule> list = findByschoolId(
			schoolId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the broadcast rules before and after the current broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param broadcastRuleId the primary key of the current broadcast rule
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next broadcast rule
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	@Override
	public BroadcastRule[] findByschoolId_PrevAndNext(
			long broadcastRuleId, long schoolId,
			OrderByComparator<BroadcastRule> orderByComparator)
		throws NoSuchBroadcastRuleException {

		BroadcastRule broadcastRule = findByPrimaryKey(broadcastRuleId);

		Session session = null;

		try {
			session = openSession();

			BroadcastRule[] array = new BroadcastRuleImpl[3];

			array[0] = getByschoolId_PrevAndNext(
				session, broadcastRule, schoolId, orderByComparator, true);

			array[1] = broadcastRule;

			array[2] = getByschoolId_PrevAndNext(
				session, broadcastRule, schoolId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected BroadcastRule getByschoolId_PrevAndNext(
		Session session, BroadcastRule broadcastRule, long schoolId,
		OrderByComparator<BroadcastRule> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BROADCASTRULE_WHERE);

		sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

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
			sb.append(BroadcastRuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoolId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						broadcastRule)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BroadcastRule> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the broadcast rules where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	@Override
	public void removeByschoolId(long schoolId) {
		for (BroadcastRule broadcastRule :
				findByschoolId(
					schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(broadcastRule);
		}
	}

	/**
	 * Returns the number of broadcast rules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching broadcast rules
	 */
	@Override
	public int countByschoolId(long schoolId) {
		FinderPath finderPath = _finderPathCountByschoolId;

		Object[] finderArgs = new Object[] {schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BROADCASTRULE_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

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

	private static final String _FINDER_COLUMN_SCHOOLID_SCHOOLID_2 =
		"broadcastRule.schoolId = ?";

	private FinderPath _finderPathWithPaginationFindByapplicationId_schoolId;
	private FinderPath _finderPathWithoutPaginationFindByapplicationId_schoolId;
	private FinderPath _finderPathCountByapplicationId_schoolId;

	/**
	 * Returns all the broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching broadcast rules
	 */
	@Override
	public List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId) {

		return findByapplicationId_schoolId(
			applicationId, schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @return the range of matching broadcast rules
	 */
	@Override
	public List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId, int start, int end) {

		return findByapplicationId_schoolId(
			applicationId, schoolId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching broadcast rules
	 */
	@Override
	public List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId, int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator) {

		return findByapplicationId_schoolId(
			applicationId, schoolId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching broadcast rules
	 */
	@Override
	public List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId, int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByapplicationId_schoolId;
				finderArgs = new Object[] {applicationId, schoolId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByapplicationId_schoolId;
			finderArgs = new Object[] {
				applicationId, schoolId, start, end, orderByComparator
			};
		}

		List<BroadcastRule> list = null;

		if (useFinderCache) {
			list = (List<BroadcastRule>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BroadcastRule broadcastRule : list) {
					if ((applicationId != broadcastRule.getApplicationId()) ||
						(schoolId != broadcastRule.getSchoolId())) {

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

			sb.append(_SQL_SELECT_BROADCASTRULE_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_APPLICATIONID_2);

			sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_SCHOOLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BroadcastRuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

				queryPos.add(schoolId);

				list = (List<BroadcastRule>)QueryUtil.list(
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
	 * Returns the first broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	@Override
	public BroadcastRule findByapplicationId_schoolId_First(
			long applicationId, long schoolId,
			OrderByComparator<BroadcastRule> orderByComparator)
		throws NoSuchBroadcastRuleException {

		BroadcastRule broadcastRule = fetchByapplicationId_schoolId_First(
			applicationId, schoolId, orderByComparator);

		if (broadcastRule != null) {
			return broadcastRule;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationId=");
		sb.append(applicationId);

		sb.append(", schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchBroadcastRuleException(sb.toString());
	}

	/**
	 * Returns the first broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	@Override
	public BroadcastRule fetchByapplicationId_schoolId_First(
		long applicationId, long schoolId,
		OrderByComparator<BroadcastRule> orderByComparator) {

		List<BroadcastRule> list = findByapplicationId_schoolId(
			applicationId, schoolId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	@Override
	public BroadcastRule findByapplicationId_schoolId_Last(
			long applicationId, long schoolId,
			OrderByComparator<BroadcastRule> orderByComparator)
		throws NoSuchBroadcastRuleException {

		BroadcastRule broadcastRule = fetchByapplicationId_schoolId_Last(
			applicationId, schoolId, orderByComparator);

		if (broadcastRule != null) {
			return broadcastRule;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationId=");
		sb.append(applicationId);

		sb.append(", schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchBroadcastRuleException(sb.toString());
	}

	/**
	 * Returns the last broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	@Override
	public BroadcastRule fetchByapplicationId_schoolId_Last(
		long applicationId, long schoolId,
		OrderByComparator<BroadcastRule> orderByComparator) {

		int count = countByapplicationId_schoolId(applicationId, schoolId);

		if (count == 0) {
			return null;
		}

		List<BroadcastRule> list = findByapplicationId_schoolId(
			applicationId, schoolId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the broadcast rules before and after the current broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param broadcastRuleId the primary key of the current broadcast rule
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next broadcast rule
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	@Override
	public BroadcastRule[] findByapplicationId_schoolId_PrevAndNext(
			long broadcastRuleId, long applicationId, long schoolId,
			OrderByComparator<BroadcastRule> orderByComparator)
		throws NoSuchBroadcastRuleException {

		BroadcastRule broadcastRule = findByPrimaryKey(broadcastRuleId);

		Session session = null;

		try {
			session = openSession();

			BroadcastRule[] array = new BroadcastRuleImpl[3];

			array[0] = getByapplicationId_schoolId_PrevAndNext(
				session, broadcastRule, applicationId, schoolId,
				orderByComparator, true);

			array[1] = broadcastRule;

			array[2] = getByapplicationId_schoolId_PrevAndNext(
				session, broadcastRule, applicationId, schoolId,
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

	protected BroadcastRule getByapplicationId_schoolId_PrevAndNext(
		Session session, BroadcastRule broadcastRule, long applicationId,
		long schoolId, OrderByComparator<BroadcastRule> orderByComparator,
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

		sb.append(_SQL_SELECT_BROADCASTRULE_WHERE);

		sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_APPLICATIONID_2);

		sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_SCHOOLID_2);

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
			sb.append(BroadcastRuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(applicationId);

		queryPos.add(schoolId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						broadcastRule)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BroadcastRule> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the broadcast rules where applicationId = &#63; and schoolId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 */
	@Override
	public void removeByapplicationId_schoolId(
		long applicationId, long schoolId) {

		for (BroadcastRule broadcastRule :
				findByapplicationId_schoolId(
					applicationId, schoolId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(broadcastRule);
		}
	}

	/**
	 * Returns the number of broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the number of matching broadcast rules
	 */
	@Override
	public int countByapplicationId_schoolId(
		long applicationId, long schoolId) {

		FinderPath finderPath = _finderPathCountByapplicationId_schoolId;

		Object[] finderArgs = new Object[] {applicationId, schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BROADCASTRULE_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_APPLICATIONID_2);

			sb.append(_FINDER_COLUMN_APPLICATIONID_SCHOOLID_SCHOOLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

				queryPos.add(schoolId);

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
		_FINDER_COLUMN_APPLICATIONID_SCHOOLID_APPLICATIONID_2 =
			"broadcastRule.applicationId = ? AND ";

	private static final String
		_FINDER_COLUMN_APPLICATIONID_SCHOOLID_SCHOOLID_2 =
			"broadcastRule.schoolId = ?";

	public BroadcastRulePersistenceImpl() {
		setModelClass(BroadcastRule.class);

		setModelImplClass(BroadcastRuleImpl.class);
		setModelPKClass(long.class);

		setTable(BroadcastRuleTable.INSTANCE);
	}

	/**
	 * Caches the broadcast rule in the entity cache if it is enabled.
	 *
	 * @param broadcastRule the broadcast rule
	 */
	@Override
	public void cacheResult(BroadcastRule broadcastRule) {
		entityCache.putResult(
			BroadcastRuleImpl.class, broadcastRule.getPrimaryKey(),
			broadcastRule);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the broadcast rules in the entity cache if it is enabled.
	 *
	 * @param broadcastRules the broadcast rules
	 */
	@Override
	public void cacheResult(List<BroadcastRule> broadcastRules) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (broadcastRules.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (BroadcastRule broadcastRule : broadcastRules) {
			if (entityCache.getResult(
					BroadcastRuleImpl.class, broadcastRule.getPrimaryKey()) ==
						null) {

				cacheResult(broadcastRule);
			}
		}
	}

	/**
	 * Clears the cache for all broadcast rules.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BroadcastRuleImpl.class);

		finderCache.clearCache(BroadcastRuleImpl.class);
	}

	/**
	 * Clears the cache for the broadcast rule.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BroadcastRule broadcastRule) {
		entityCache.removeResult(BroadcastRuleImpl.class, broadcastRule);
	}

	@Override
	public void clearCache(List<BroadcastRule> broadcastRules) {
		for (BroadcastRule broadcastRule : broadcastRules) {
			entityCache.removeResult(BroadcastRuleImpl.class, broadcastRule);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(BroadcastRuleImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(BroadcastRuleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new broadcast rule with the primary key. Does not add the broadcast rule to the database.
	 *
	 * @param broadcastRuleId the primary key for the new broadcast rule
	 * @return the new broadcast rule
	 */
	@Override
	public BroadcastRule create(long broadcastRuleId) {
		BroadcastRule broadcastRule = new BroadcastRuleImpl();

		broadcastRule.setNew(true);
		broadcastRule.setPrimaryKey(broadcastRuleId);

		return broadcastRule;
	}

	/**
	 * Removes the broadcast rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule that was removed
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	@Override
	public BroadcastRule remove(long broadcastRuleId)
		throws NoSuchBroadcastRuleException {

		return remove((Serializable)broadcastRuleId);
	}

	/**
	 * Removes the broadcast rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the broadcast rule
	 * @return the broadcast rule that was removed
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	@Override
	public BroadcastRule remove(Serializable primaryKey)
		throws NoSuchBroadcastRuleException {

		Session session = null;

		try {
			session = openSession();

			BroadcastRule broadcastRule = (BroadcastRule)session.get(
				BroadcastRuleImpl.class, primaryKey);

			if (broadcastRule == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBroadcastRuleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(broadcastRule);
		}
		catch (NoSuchBroadcastRuleException noSuchEntityException) {
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
	protected BroadcastRule removeImpl(BroadcastRule broadcastRule) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(broadcastRule)) {
				broadcastRule = (BroadcastRule)session.get(
					BroadcastRuleImpl.class, broadcastRule.getPrimaryKeyObj());
			}

			if (broadcastRule != null) {
				session.delete(broadcastRule);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (broadcastRule != null) {
			clearCache(broadcastRule);
		}

		return broadcastRule;
	}

	@Override
	public BroadcastRule updateImpl(BroadcastRule broadcastRule) {
		boolean isNew = broadcastRule.isNew();

		if (!(broadcastRule instanceof BroadcastRuleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(broadcastRule.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					broadcastRule);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in broadcastRule proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom BroadcastRule implementation " +
					broadcastRule.getClass());
		}

		BroadcastRuleModelImpl broadcastRuleModelImpl =
			(BroadcastRuleModelImpl)broadcastRule;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(broadcastRule);
			}
			else {
				broadcastRule = (BroadcastRule)session.merge(broadcastRule);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			BroadcastRuleImpl.class, broadcastRuleModelImpl, false, true);

		if (isNew) {
			broadcastRule.setNew(false);
		}

		broadcastRule.resetOriginalValues();

		return broadcastRule;
	}

	/**
	 * Returns the broadcast rule with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the broadcast rule
	 * @return the broadcast rule
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	@Override
	public BroadcastRule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBroadcastRuleException {

		BroadcastRule broadcastRule = fetchByPrimaryKey(primaryKey);

		if (broadcastRule == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBroadcastRuleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return broadcastRule;
	}

	/**
	 * Returns the broadcast rule with the primary key or throws a <code>NoSuchBroadcastRuleException</code> if it could not be found.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	@Override
	public BroadcastRule findByPrimaryKey(long broadcastRuleId)
		throws NoSuchBroadcastRuleException {

		return findByPrimaryKey((Serializable)broadcastRuleId);
	}

	/**
	 * Returns the broadcast rule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule, or <code>null</code> if a broadcast rule with the primary key could not be found
	 */
	@Override
	public BroadcastRule fetchByPrimaryKey(long broadcastRuleId) {
		return fetchByPrimaryKey((Serializable)broadcastRuleId);
	}

	/**
	 * Returns all the broadcast rules.
	 *
	 * @return the broadcast rules
	 */
	@Override
	public List<BroadcastRule> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the broadcast rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @return the range of broadcast rules
	 */
	@Override
	public List<BroadcastRule> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the broadcast rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of broadcast rules
	 */
	@Override
	public List<BroadcastRule> findAll(
		int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the broadcast rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of broadcast rules
	 */
	@Override
	public List<BroadcastRule> findAll(
		int start, int end, OrderByComparator<BroadcastRule> orderByComparator,
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

		List<BroadcastRule> list = null;

		if (useFinderCache) {
			list = (List<BroadcastRule>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_BROADCASTRULE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_BROADCASTRULE;

				sql = sql.concat(BroadcastRuleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<BroadcastRule>)QueryUtil.list(
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
	 * Removes all the broadcast rules from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BroadcastRule broadcastRule : findAll()) {
			remove(broadcastRule);
		}
	}

	/**
	 * Returns the number of broadcast rules.
	 *
	 * @return the number of broadcast rules
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_BROADCASTRULE);

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
		return "broadcastRuleId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_BROADCASTRULE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BroadcastRuleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the broadcast rule persistence.
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

		_finderPathWithPaginationFindByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByschoolId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"schoolId"}, true);

		_finderPathWithoutPaginationFindByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByschoolId",
			new String[] {Long.class.getName()}, new String[] {"schoolId"},
			true);

		_finderPathCountByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByschoolId",
			new String[] {Long.class.getName()}, new String[] {"schoolId"},
			false);

		_finderPathWithPaginationFindByapplicationId_schoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByapplicationId_schoolId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"applicationId", "schoolId"}, true);

		_finderPathWithoutPaginationFindByapplicationId_schoolId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByapplicationId_schoolId",
				new String[] {Long.class.getName(), Long.class.getName()},
				new String[] {"applicationId", "schoolId"}, true);

		_finderPathCountByapplicationId_schoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByapplicationId_schoolId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"applicationId", "schoolId"}, false);

		_setBroadcastRuleUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setBroadcastRuleUtilPersistence(null);

		entityCache.removeCache(BroadcastRuleImpl.class.getName());
	}

	private void _setBroadcastRuleUtilPersistence(
		BroadcastRulePersistence broadcastRulePersistence) {

		try {
			Field field = BroadcastRuleUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, broadcastRulePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = ApplicationPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ApplicationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ApplicationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_BROADCASTRULE =
		"SELECT broadcastRule FROM BroadcastRule broadcastRule";

	private static final String _SQL_SELECT_BROADCASTRULE_WHERE =
		"SELECT broadcastRule FROM BroadcastRule broadcastRule WHERE ";

	private static final String _SQL_COUNT_BROADCASTRULE =
		"SELECT COUNT(broadcastRule) FROM BroadcastRule broadcastRule";

	private static final String _SQL_COUNT_BROADCASTRULE_WHERE =
		"SELECT COUNT(broadcastRule) FROM BroadcastRule broadcastRule WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "broadcastRule.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No BroadcastRule exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No BroadcastRule exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		BroadcastRulePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}