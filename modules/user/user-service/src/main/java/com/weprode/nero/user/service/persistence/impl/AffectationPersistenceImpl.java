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

package com.weprode.nero.user.service.persistence.impl;

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

import com.weprode.nero.user.exception.NoSuchAffectationException;
import com.weprode.nero.user.model.Affectation;
import com.weprode.nero.user.model.AffectationTable;
import com.weprode.nero.user.model.impl.AffectationImpl;
import com.weprode.nero.user.model.impl.AffectationModelImpl;
import com.weprode.nero.user.service.persistence.AffectationPersistence;
import com.weprode.nero.user.service.persistence.AffectationUtil;
import com.weprode.nero.user.service.persistence.impl.constants.UserPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the affectation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {AffectationPersistence.class, BasePersistence.class})
public class AffectationPersistenceImpl
	extends BasePersistenceImpl<Affectation> implements AffectationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AffectationUtil</code> to access the affectation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AffectationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByuserId;
	private FinderPath _finderPathWithoutPaginationFindByuserId;
	private FinderPath _finderPathCountByuserId;

	/**
	 * Returns all the affectations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching affectations
	 */
	@Override
	public List<Affectation> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the affectations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of matching affectations
	 */
	@Override
	public List<Affectation> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the affectations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching affectations
	 */
	@Override
	public List<Affectation> findByuserId(
		long userId, int start, int end,
		OrderByComparator<Affectation> orderByComparator) {

		return findByuserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the affectations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching affectations
	 */
	@Override
	public List<Affectation> findByuserId(
		long userId, int start, int end,
		OrderByComparator<Affectation> orderByComparator,
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

		List<Affectation> list = null;

		if (useFinderCache) {
			list = (List<Affectation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Affectation affectation : list) {
					if (userId != affectation.getUserId()) {
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

			sb.append(_SQL_SELECT_AFFECTATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AffectationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<Affectation>)QueryUtil.list(
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
	 * Returns the first affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	@Override
	public Affectation findByuserId_First(
			long userId, OrderByComparator<Affectation> orderByComparator)
		throws NoSuchAffectationException {

		Affectation affectation = fetchByuserId_First(
			userId, orderByComparator);

		if (affectation != null) {
			return affectation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchAffectationException(sb.toString());
	}

	/**
	 * Returns the first affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	@Override
	public Affectation fetchByuserId_First(
		long userId, OrderByComparator<Affectation> orderByComparator) {

		List<Affectation> list = findByuserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	@Override
	public Affectation findByuserId_Last(
			long userId, OrderByComparator<Affectation> orderByComparator)
		throws NoSuchAffectationException {

		Affectation affectation = fetchByuserId_Last(userId, orderByComparator);

		if (affectation != null) {
			return affectation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchAffectationException(sb.toString());
	}

	/**
	 * Returns the last affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	@Override
	public Affectation fetchByuserId_Last(
		long userId, OrderByComparator<Affectation> orderByComparator) {

		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<Affectation> list = findByuserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the affectations before and after the current affectation in the ordered set where userId = &#63;.
	 *
	 * @param affectationId the primary key of the current affectation
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	@Override
	public Affectation[] findByuserId_PrevAndNext(
			long affectationId, long userId,
			OrderByComparator<Affectation> orderByComparator)
		throws NoSuchAffectationException {

		Affectation affectation = findByPrimaryKey(affectationId);

		Session session = null;

		try {
			session = openSession();

			Affectation[] array = new AffectationImpl[3];

			array[0] = getByuserId_PrevAndNext(
				session, affectation, userId, orderByComparator, true);

			array[1] = affectation;

			array[2] = getByuserId_PrevAndNext(
				session, affectation, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Affectation getByuserId_PrevAndNext(
		Session session, Affectation affectation, long userId,
		OrderByComparator<Affectation> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_AFFECTATION_WHERE);

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
			sb.append(AffectationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(affectation)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Affectation> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the affectations where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (Affectation affectation :
				findByuserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(affectation);
		}
	}

	/**
	 * Returns the number of affectations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching affectations
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AFFECTATION_WHERE);

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
		"affectation.userId = ?";

	private FinderPath _finderPathWithPaginationFindByschoolId;
	private FinderPath _finderPathWithoutPaginationFindByschoolId;
	private FinderPath _finderPathCountByschoolId;

	/**
	 * Returns all the affectations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching affectations
	 */
	@Override
	public List<Affectation> findByschoolId(long schoolId) {
		return findByschoolId(
			schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the affectations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of matching affectations
	 */
	@Override
	public List<Affectation> findByschoolId(long schoolId, int start, int end) {
		return findByschoolId(schoolId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the affectations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching affectations
	 */
	@Override
	public List<Affectation> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<Affectation> orderByComparator) {

		return findByschoolId(schoolId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the affectations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching affectations
	 */
	@Override
	public List<Affectation> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<Affectation> orderByComparator,
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

		List<Affectation> list = null;

		if (useFinderCache) {
			list = (List<Affectation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Affectation affectation : list) {
					if (schoolId != affectation.getSchoolId()) {
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

			sb.append(_SQL_SELECT_AFFECTATION_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AffectationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				list = (List<Affectation>)QueryUtil.list(
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
	 * Returns the first affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	@Override
	public Affectation findByschoolId_First(
			long schoolId, OrderByComparator<Affectation> orderByComparator)
		throws NoSuchAffectationException {

		Affectation affectation = fetchByschoolId_First(
			schoolId, orderByComparator);

		if (affectation != null) {
			return affectation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchAffectationException(sb.toString());
	}

	/**
	 * Returns the first affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	@Override
	public Affectation fetchByschoolId_First(
		long schoolId, OrderByComparator<Affectation> orderByComparator) {

		List<Affectation> list = findByschoolId(
			schoolId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	@Override
	public Affectation findByschoolId_Last(
			long schoolId, OrderByComparator<Affectation> orderByComparator)
		throws NoSuchAffectationException {

		Affectation affectation = fetchByschoolId_Last(
			schoolId, orderByComparator);

		if (affectation != null) {
			return affectation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchAffectationException(sb.toString());
	}

	/**
	 * Returns the last affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	@Override
	public Affectation fetchByschoolId_Last(
		long schoolId, OrderByComparator<Affectation> orderByComparator) {

		int count = countByschoolId(schoolId);

		if (count == 0) {
			return null;
		}

		List<Affectation> list = findByschoolId(
			schoolId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the affectations before and after the current affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param affectationId the primary key of the current affectation
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	@Override
	public Affectation[] findByschoolId_PrevAndNext(
			long affectationId, long schoolId,
			OrderByComparator<Affectation> orderByComparator)
		throws NoSuchAffectationException {

		Affectation affectation = findByPrimaryKey(affectationId);

		Session session = null;

		try {
			session = openSession();

			Affectation[] array = new AffectationImpl[3];

			array[0] = getByschoolId_PrevAndNext(
				session, affectation, schoolId, orderByComparator, true);

			array[1] = affectation;

			array[2] = getByschoolId_PrevAndNext(
				session, affectation, schoolId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Affectation getByschoolId_PrevAndNext(
		Session session, Affectation affectation, long schoolId,
		OrderByComparator<Affectation> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_AFFECTATION_WHERE);

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
			sb.append(AffectationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoolId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(affectation)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Affectation> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the affectations where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	@Override
	public void removeByschoolId(long schoolId) {
		for (Affectation affectation :
				findByschoolId(
					schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(affectation);
		}
	}

	/**
	 * Returns the number of affectations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching affectations
	 */
	@Override
	public int countByschoolId(long schoolId) {
		FinderPath finderPath = _finderPathCountByschoolId;

		Object[] finderArgs = new Object[] {schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AFFECTATION_WHERE);

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
		"affectation.schoolId = ?";

	public AffectationPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Affectation.class);

		setModelImplClass(AffectationImpl.class);
		setModelPKClass(long.class);

		setTable(AffectationTable.INSTANCE);
	}

	/**
	 * Caches the affectation in the entity cache if it is enabled.
	 *
	 * @param affectation the affectation
	 */
	@Override
	public void cacheResult(Affectation affectation) {
		entityCache.putResult(
			AffectationImpl.class, affectation.getPrimaryKey(), affectation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the affectations in the entity cache if it is enabled.
	 *
	 * @param affectations the affectations
	 */
	@Override
	public void cacheResult(List<Affectation> affectations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (affectations.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Affectation affectation : affectations) {
			if (entityCache.getResult(
					AffectationImpl.class, affectation.getPrimaryKey()) ==
						null) {

				cacheResult(affectation);
			}
		}
	}

	/**
	 * Clears the cache for all affectations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AffectationImpl.class);

		finderCache.clearCache(AffectationImpl.class);
	}

	/**
	 * Clears the cache for the affectation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Affectation affectation) {
		entityCache.removeResult(AffectationImpl.class, affectation);
	}

	@Override
	public void clearCache(List<Affectation> affectations) {
		for (Affectation affectation : affectations) {
			entityCache.removeResult(AffectationImpl.class, affectation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AffectationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AffectationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new affectation with the primary key. Does not add the affectation to the database.
	 *
	 * @param affectationId the primary key for the new affectation
	 * @return the new affectation
	 */
	@Override
	public Affectation create(long affectationId) {
		Affectation affectation = new AffectationImpl();

		affectation.setNew(true);
		affectation.setPrimaryKey(affectationId);

		return affectation;
	}

	/**
	 * Removes the affectation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation that was removed
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	@Override
	public Affectation remove(long affectationId)
		throws NoSuchAffectationException {

		return remove((Serializable)affectationId);
	}

	/**
	 * Removes the affectation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the affectation
	 * @return the affectation that was removed
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	@Override
	public Affectation remove(Serializable primaryKey)
		throws NoSuchAffectationException {

		Session session = null;

		try {
			session = openSession();

			Affectation affectation = (Affectation)session.get(
				AffectationImpl.class, primaryKey);

			if (affectation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAffectationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(affectation);
		}
		catch (NoSuchAffectationException noSuchEntityException) {
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
	protected Affectation removeImpl(Affectation affectation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(affectation)) {
				affectation = (Affectation)session.get(
					AffectationImpl.class, affectation.getPrimaryKeyObj());
			}

			if (affectation != null) {
				session.delete(affectation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (affectation != null) {
			clearCache(affectation);
		}

		return affectation;
	}

	@Override
	public Affectation updateImpl(Affectation affectation) {
		boolean isNew = affectation.isNew();

		if (!(affectation instanceof AffectationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(affectation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(affectation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in affectation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Affectation implementation " +
					affectation.getClass());
		}

		AffectationModelImpl affectationModelImpl =
			(AffectationModelImpl)affectation;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(affectation);
			}
			else {
				affectation = (Affectation)session.merge(affectation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AffectationImpl.class, affectationModelImpl, false, true);

		if (isNew) {
			affectation.setNew(false);
		}

		affectation.resetOriginalValues();

		return affectation;
	}

	/**
	 * Returns the affectation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the affectation
	 * @return the affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	@Override
	public Affectation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAffectationException {

		Affectation affectation = fetchByPrimaryKey(primaryKey);

		if (affectation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAffectationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return affectation;
	}

	/**
	 * Returns the affectation with the primary key or throws a <code>NoSuchAffectationException</code> if it could not be found.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	@Override
	public Affectation findByPrimaryKey(long affectationId)
		throws NoSuchAffectationException {

		return findByPrimaryKey((Serializable)affectationId);
	}

	/**
	 * Returns the affectation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation, or <code>null</code> if a affectation with the primary key could not be found
	 */
	@Override
	public Affectation fetchByPrimaryKey(long affectationId) {
		return fetchByPrimaryKey((Serializable)affectationId);
	}

	/**
	 * Returns all the affectations.
	 *
	 * @return the affectations
	 */
	@Override
	public List<Affectation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of affectations
	 */
	@Override
	public List<Affectation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of affectations
	 */
	@Override
	public List<Affectation> findAll(
		int start, int end, OrderByComparator<Affectation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of affectations
	 */
	@Override
	public List<Affectation> findAll(
		int start, int end, OrderByComparator<Affectation> orderByComparator,
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

		List<Affectation> list = null;

		if (useFinderCache) {
			list = (List<Affectation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AFFECTATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AFFECTATION;

				sql = sql.concat(AffectationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Affectation>)QueryUtil.list(
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
	 * Removes all the affectations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Affectation affectation : findAll()) {
			remove(affectation);
		}
	}

	/**
	 * Returns the number of affectations.
	 *
	 * @return the number of affectations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AFFECTATION);

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
		return "affectationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AFFECTATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AffectationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the affectation persistence.
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

		_setAffectationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setAffectationUtilPersistence(null);

		entityCache.removeCache(AffectationImpl.class.getName());
	}

	private void _setAffectationUtilPersistence(
		AffectationPersistence affectationPersistence) {

		try {
			Field field = AffectationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, affectationPersistence);
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

	private static final String _SQL_SELECT_AFFECTATION =
		"SELECT affectation FROM Affectation affectation";

	private static final String _SQL_SELECT_AFFECTATION_WHERE =
		"SELECT affectation FROM Affectation affectation WHERE ";

	private static final String _SQL_COUNT_AFFECTATION =
		"SELECT COUNT(affectation) FROM Affectation affectation";

	private static final String _SQL_COUNT_AFFECTATION_WHERE =
		"SELECT COUNT(affectation) FROM Affectation affectation WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "affectation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Affectation exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Affectation exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AffectationPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}