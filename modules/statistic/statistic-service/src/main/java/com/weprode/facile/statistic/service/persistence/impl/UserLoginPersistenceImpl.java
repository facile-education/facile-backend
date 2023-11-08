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

package com.weprode.facile.statistic.service.persistence.impl;

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

import com.weprode.facile.statistic.exception.NoSuchUserLoginException;
import com.weprode.facile.statistic.model.UserLogin;
import com.weprode.facile.statistic.model.UserLoginTable;
import com.weprode.facile.statistic.model.impl.UserLoginImpl;
import com.weprode.facile.statistic.model.impl.UserLoginModelImpl;
import com.weprode.facile.statistic.service.persistence.UserLoginPersistence;
import com.weprode.facile.statistic.service.persistence.UserLoginUtil;
import com.weprode.facile.statistic.service.persistence.impl.constants.StatisticsPersistenceConstants;

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
 * The persistence implementation for the user login service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {UserLoginPersistence.class, BasePersistence.class})
public class UserLoginPersistenceImpl
	extends BasePersistenceImpl<UserLogin> implements UserLoginPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserLoginUtil</code> to access the user login persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserLoginImpl.class.getName();

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
	 * Returns all the user logins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching user logins
	 */
	@Override
	public List<UserLogin> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user logins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of matching user logins
	 */
	@Override
	public List<UserLogin> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user logins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user logins
	 */
	@Override
	public List<UserLogin> findByuserId(
		long userId, int start, int end,
		OrderByComparator<UserLogin> orderByComparator) {

		return findByuserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user logins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user logins
	 */
	@Override
	public List<UserLogin> findByuserId(
		long userId, int start, int end,
		OrderByComparator<UserLogin> orderByComparator,
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

		List<UserLogin> list = null;

		if (useFinderCache) {
			list = (List<UserLogin>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserLogin userLogin : list) {
					if (userId != userLogin.getUserId()) {
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

			sb.append(_SQL_SELECT_USERLOGIN_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserLoginModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<UserLogin>)QueryUtil.list(
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
	 * Returns the first user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	@Override
	public UserLogin findByuserId_First(
			long userId, OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException {

		UserLogin userLogin = fetchByuserId_First(userId, orderByComparator);

		if (userLogin != null) {
			return userLogin;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchUserLoginException(sb.toString());
	}

	/**
	 * Returns the first user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login, or <code>null</code> if a matching user login could not be found
	 */
	@Override
	public UserLogin fetchByuserId_First(
		long userId, OrderByComparator<UserLogin> orderByComparator) {

		List<UserLogin> list = findByuserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	@Override
	public UserLogin findByuserId_Last(
			long userId, OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException {

		UserLogin userLogin = fetchByuserId_Last(userId, orderByComparator);

		if (userLogin != null) {
			return userLogin;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchUserLoginException(sb.toString());
	}

	/**
	 * Returns the last user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login, or <code>null</code> if a matching user login could not be found
	 */
	@Override
	public UserLogin fetchByuserId_Last(
		long userId, OrderByComparator<UserLogin> orderByComparator) {

		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<UserLogin> list = findByuserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user logins before and after the current user login in the ordered set where userId = &#63;.
	 *
	 * @param userLoginId the primary key of the current user login
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	@Override
	public UserLogin[] findByuserId_PrevAndNext(
			long userLoginId, long userId,
			OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException {

		UserLogin userLogin = findByPrimaryKey(userLoginId);

		Session session = null;

		try {
			session = openSession();

			UserLogin[] array = new UserLoginImpl[3];

			array[0] = getByuserId_PrevAndNext(
				session, userLogin, userId, orderByComparator, true);

			array[1] = userLogin;

			array[2] = getByuserId_PrevAndNext(
				session, userLogin, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserLogin getByuserId_PrevAndNext(
		Session session, UserLogin userLogin, long userId,
		OrderByComparator<UserLogin> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_USERLOGIN_WHERE);

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
			sb.append(UserLoginModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(userLogin)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserLogin> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user logins where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (UserLogin userLogin :
				findByuserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(userLogin);
		}
	}

	/**
	 * Returns the number of user logins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user logins
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERLOGIN_WHERE);

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
		"userLogin.userId = ?";

	private FinderPath _finderPathWithPaginationFindByschoolId_role;
	private FinderPath _finderPathWithoutPaginationFindByschoolId_role;
	private FinderPath _finderPathCountByschoolId_role;

	/**
	 * Returns all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @return the matching user logins
	 */
	@Override
	public List<UserLogin> findByschoolId_role(long schoolId, int role) {
		return findByschoolId_role(
			schoolId, role, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of matching user logins
	 */
	@Override
	public List<UserLogin> findByschoolId_role(
		long schoolId, int role, int start, int end) {

		return findByschoolId_role(schoolId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user logins
	 */
	@Override
	public List<UserLogin> findByschoolId_role(
		long schoolId, int role, int start, int end,
		OrderByComparator<UserLogin> orderByComparator) {

		return findByschoolId_role(
			schoolId, role, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user logins
	 */
	@Override
	public List<UserLogin> findByschoolId_role(
		long schoolId, int role, int start, int end,
		OrderByComparator<UserLogin> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByschoolId_role;
				finderArgs = new Object[] {schoolId, role};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByschoolId_role;
			finderArgs = new Object[] {
				schoolId, role, start, end, orderByComparator
			};
		}

		List<UserLogin> list = null;

		if (useFinderCache) {
			list = (List<UserLogin>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserLogin userLogin : list) {
					if ((schoolId != userLogin.getSchoolId()) ||
						(role != userLogin.getRole())) {

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

			sb.append(_SQL_SELECT_USERLOGIN_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_ROLE_SCHOOLID_2);

			sb.append(_FINDER_COLUMN_SCHOOLID_ROLE_ROLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserLoginModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				queryPos.add(role);

				list = (List<UserLogin>)QueryUtil.list(
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
	 * Returns the first user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	@Override
	public UserLogin findByschoolId_role_First(
			long schoolId, int role,
			OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException {

		UserLogin userLogin = fetchByschoolId_role_First(
			schoolId, role, orderByComparator);

		if (userLogin != null) {
			return userLogin;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append(", role=");
		sb.append(role);

		sb.append("}");

		throw new NoSuchUserLoginException(sb.toString());
	}

	/**
	 * Returns the first user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login, or <code>null</code> if a matching user login could not be found
	 */
	@Override
	public UserLogin fetchByschoolId_role_First(
		long schoolId, int role,
		OrderByComparator<UserLogin> orderByComparator) {

		List<UserLogin> list = findByschoolId_role(
			schoolId, role, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	@Override
	public UserLogin findByschoolId_role_Last(
			long schoolId, int role,
			OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException {

		UserLogin userLogin = fetchByschoolId_role_Last(
			schoolId, role, orderByComparator);

		if (userLogin != null) {
			return userLogin;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append(", role=");
		sb.append(role);

		sb.append("}");

		throw new NoSuchUserLoginException(sb.toString());
	}

	/**
	 * Returns the last user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login, or <code>null</code> if a matching user login could not be found
	 */
	@Override
	public UserLogin fetchByschoolId_role_Last(
		long schoolId, int role,
		OrderByComparator<UserLogin> orderByComparator) {

		int count = countByschoolId_role(schoolId, role);

		if (count == 0) {
			return null;
		}

		List<UserLogin> list = findByschoolId_role(
			schoolId, role, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user logins before and after the current user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param userLoginId the primary key of the current user login
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	@Override
	public UserLogin[] findByschoolId_role_PrevAndNext(
			long userLoginId, long schoolId, int role,
			OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException {

		UserLogin userLogin = findByPrimaryKey(userLoginId);

		Session session = null;

		try {
			session = openSession();

			UserLogin[] array = new UserLoginImpl[3];

			array[0] = getByschoolId_role_PrevAndNext(
				session, userLogin, schoolId, role, orderByComparator, true);

			array[1] = userLogin;

			array[2] = getByschoolId_role_PrevAndNext(
				session, userLogin, schoolId, role, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserLogin getByschoolId_role_PrevAndNext(
		Session session, UserLogin userLogin, long schoolId, int role,
		OrderByComparator<UserLogin> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_USERLOGIN_WHERE);

		sb.append(_FINDER_COLUMN_SCHOOLID_ROLE_SCHOOLID_2);

		sb.append(_FINDER_COLUMN_SCHOOLID_ROLE_ROLE_2);

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
			sb.append(UserLoginModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoolId);

		queryPos.add(role);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(userLogin)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserLogin> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user logins where schoolId = &#63; and role = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 */
	@Override
	public void removeByschoolId_role(long schoolId, int role) {
		for (UserLogin userLogin :
				findByschoolId_role(
					schoolId, role, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(userLogin);
		}
	}

	/**
	 * Returns the number of user logins where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @return the number of matching user logins
	 */
	@Override
	public int countByschoolId_role(long schoolId, int role) {
		FinderPath finderPath = _finderPathCountByschoolId_role;

		Object[] finderArgs = new Object[] {schoolId, role};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_USERLOGIN_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_ROLE_SCHOOLID_2);

			sb.append(_FINDER_COLUMN_SCHOOLID_ROLE_ROLE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				queryPos.add(role);

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

	private static final String _FINDER_COLUMN_SCHOOLID_ROLE_SCHOOLID_2 =
		"userLogin.schoolId = ? AND ";

	private static final String _FINDER_COLUMN_SCHOOLID_ROLE_ROLE_2 =
		"userLogin.role = ?";

	public UserLoginPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("role", "role_");

		setDBColumnNames(dbColumnNames);

		setModelClass(UserLogin.class);

		setModelImplClass(UserLoginImpl.class);
		setModelPKClass(long.class);

		setTable(UserLoginTable.INSTANCE);
	}

	/**
	 * Caches the user login in the entity cache if it is enabled.
	 *
	 * @param userLogin the user login
	 */
	@Override
	public void cacheResult(UserLogin userLogin) {
		entityCache.putResult(
			UserLoginImpl.class, userLogin.getPrimaryKey(), userLogin);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the user logins in the entity cache if it is enabled.
	 *
	 * @param userLogins the user logins
	 */
	@Override
	public void cacheResult(List<UserLogin> userLogins) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (userLogins.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UserLogin userLogin : userLogins) {
			if (entityCache.getResult(
					UserLoginImpl.class, userLogin.getPrimaryKey()) == null) {

				cacheResult(userLogin);
			}
		}
	}

	/**
	 * Clears the cache for all user logins.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserLoginImpl.class);

		finderCache.clearCache(UserLoginImpl.class);
	}

	/**
	 * Clears the cache for the user login.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserLogin userLogin) {
		entityCache.removeResult(UserLoginImpl.class, userLogin);
	}

	@Override
	public void clearCache(List<UserLogin> userLogins) {
		for (UserLogin userLogin : userLogins) {
			entityCache.removeResult(UserLoginImpl.class, userLogin);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UserLoginImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UserLoginImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new user login with the primary key. Does not add the user login to the database.
	 *
	 * @param userLoginId the primary key for the new user login
	 * @return the new user login
	 */
	@Override
	public UserLogin create(long userLoginId) {
		UserLogin userLogin = new UserLoginImpl();

		userLogin.setNew(true);
		userLogin.setPrimaryKey(userLoginId);

		return userLogin;
	}

	/**
	 * Removes the user login with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login that was removed
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	@Override
	public UserLogin remove(long userLoginId) throws NoSuchUserLoginException {
		return remove((Serializable)userLoginId);
	}

	/**
	 * Removes the user login with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user login
	 * @return the user login that was removed
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	@Override
	public UserLogin remove(Serializable primaryKey)
		throws NoSuchUserLoginException {

		Session session = null;

		try {
			session = openSession();

			UserLogin userLogin = (UserLogin)session.get(
				UserLoginImpl.class, primaryKey);

			if (userLogin == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserLoginException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userLogin);
		}
		catch (NoSuchUserLoginException noSuchEntityException) {
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
	protected UserLogin removeImpl(UserLogin userLogin) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userLogin)) {
				userLogin = (UserLogin)session.get(
					UserLoginImpl.class, userLogin.getPrimaryKeyObj());
			}

			if (userLogin != null) {
				session.delete(userLogin);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userLogin != null) {
			clearCache(userLogin);
		}

		return userLogin;
	}

	@Override
	public UserLogin updateImpl(UserLogin userLogin) {
		boolean isNew = userLogin.isNew();

		if (!(userLogin instanceof UserLoginModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userLogin.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(userLogin);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userLogin proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserLogin implementation " +
					userLogin.getClass());
		}

		UserLoginModelImpl userLoginModelImpl = (UserLoginModelImpl)userLogin;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(userLogin);
			}
			else {
				userLogin = (UserLogin)session.merge(userLogin);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UserLoginImpl.class, userLoginModelImpl, false, true);

		if (isNew) {
			userLogin.setNew(false);
		}

		userLogin.resetOriginalValues();

		return userLogin;
	}

	/**
	 * Returns the user login with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user login
	 * @return the user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	@Override
	public UserLogin findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserLoginException {

		UserLogin userLogin = fetchByPrimaryKey(primaryKey);

		if (userLogin == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserLoginException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userLogin;
	}

	/**
	 * Returns the user login with the primary key or throws a <code>NoSuchUserLoginException</code> if it could not be found.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	@Override
	public UserLogin findByPrimaryKey(long userLoginId)
		throws NoSuchUserLoginException {

		return findByPrimaryKey((Serializable)userLoginId);
	}

	/**
	 * Returns the user login with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login, or <code>null</code> if a user login with the primary key could not be found
	 */
	@Override
	public UserLogin fetchByPrimaryKey(long userLoginId) {
		return fetchByPrimaryKey((Serializable)userLoginId);
	}

	/**
	 * Returns all the user logins.
	 *
	 * @return the user logins
	 */
	@Override
	public List<UserLogin> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of user logins
	 */
	@Override
	public List<UserLogin> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user logins
	 */
	@Override
	public List<UserLogin> findAll(
		int start, int end, OrderByComparator<UserLogin> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user logins
	 */
	@Override
	public List<UserLogin> findAll(
		int start, int end, OrderByComparator<UserLogin> orderByComparator,
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

		List<UserLogin> list = null;

		if (useFinderCache) {
			list = (List<UserLogin>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERLOGIN);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERLOGIN;

				sql = sql.concat(UserLoginModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserLogin>)QueryUtil.list(
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
	 * Removes all the user logins from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserLogin userLogin : findAll()) {
			remove(userLogin);
		}
	}

	/**
	 * Returns the number of user logins.
	 *
	 * @return the number of user logins
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USERLOGIN);

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
		return "userLoginId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_USERLOGIN;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserLoginModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user login persistence.
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

		_finderPathWithPaginationFindByschoolId_role = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByschoolId_role",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"schoolId", "role_"}, true);

		_finderPathWithoutPaginationFindByschoolId_role = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByschoolId_role",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"schoolId", "role_"}, true);

		_finderPathCountByschoolId_role = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByschoolId_role",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"schoolId", "role_"}, false);

		_setUserLoginUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setUserLoginUtilPersistence(null);

		entityCache.removeCache(UserLoginImpl.class.getName());
	}

	private void _setUserLoginUtilPersistence(
		UserLoginPersistence userLoginPersistence) {

		try {
			Field field = UserLoginUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, userLoginPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = StatisticsPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = StatisticsPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = StatisticsPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_USERLOGIN =
		"SELECT userLogin FROM UserLogin userLogin";

	private static final String _SQL_SELECT_USERLOGIN_WHERE =
		"SELECT userLogin FROM UserLogin userLogin WHERE ";

	private static final String _SQL_COUNT_USERLOGIN =
		"SELECT COUNT(userLogin) FROM UserLogin userLogin";

	private static final String _SQL_COUNT_USERLOGIN_WHERE =
		"SELECT COUNT(userLogin) FROM UserLogin userLogin WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userLogin.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserLogin exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserLogin exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserLoginPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"role"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}