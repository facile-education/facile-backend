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

package com.weprode.facile.application.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.facile.application.exception.NoSuchAuthorizedSchoolException;
import com.weprode.facile.application.model.AuthorizedSchool;
import com.weprode.facile.application.model.AuthorizedSchoolTable;
import com.weprode.facile.application.model.impl.AuthorizedSchoolImpl;
import com.weprode.facile.application.model.impl.AuthorizedSchoolModelImpl;
import com.weprode.facile.application.service.persistence.AuthorizedSchoolPersistence;
import com.weprode.facile.application.service.persistence.AuthorizedSchoolUtil;
import com.weprode.facile.application.service.persistence.impl.constants.ApplicationPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the authorized school service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {AuthorizedSchoolPersistence.class, BasePersistence.class})
public class AuthorizedSchoolPersistenceImpl
	extends BasePersistenceImpl<AuthorizedSchool>
	implements AuthorizedSchoolPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AuthorizedSchoolUtil</code> to access the authorized school persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AuthorizedSchoolImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByapplicationId;
	private FinderPath _finderPathWithoutPaginationFindByapplicationId;
	private FinderPath _finderPathCountByapplicationId;

	/**
	 * Returns all the authorized schools where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching authorized schools
	 */
	@Override
	public List<AuthorizedSchool> findByapplicationId(long applicationId) {
		return findByapplicationId(
			applicationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the authorized schools where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @return the range of matching authorized schools
	 */
	@Override
	public List<AuthorizedSchool> findByapplicationId(
		long applicationId, int start, int end) {

		return findByapplicationId(applicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the authorized schools where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching authorized schools
	 */
	@Override
	public List<AuthorizedSchool> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<AuthorizedSchool> orderByComparator) {

		return findByapplicationId(
			applicationId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the authorized schools where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching authorized schools
	 */
	@Override
	public List<AuthorizedSchool> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<AuthorizedSchool> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByapplicationId;
				finderArgs = new Object[] {applicationId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByapplicationId;
			finderArgs = new Object[] {
				applicationId, start, end, orderByComparator
			};
		}

		List<AuthorizedSchool> list = null;

		if (useFinderCache) {
			list = (List<AuthorizedSchool>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuthorizedSchool authorizedSchool : list) {
					if (applicationId != authorizedSchool.getApplicationId()) {
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

			sb.append(_SQL_SELECT_AUTHORIZEDSCHOOL_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AuthorizedSchoolModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

				list = (List<AuthorizedSchool>)QueryUtil.list(
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
	 * Returns the first authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authorized school
	 * @throws NoSuchAuthorizedSchoolException if a matching authorized school could not be found
	 */
	@Override
	public AuthorizedSchool findByapplicationId_First(
			long applicationId,
			OrderByComparator<AuthorizedSchool> orderByComparator)
		throws NoSuchAuthorizedSchoolException {

		AuthorizedSchool authorizedSchool = fetchByapplicationId_First(
			applicationId, orderByComparator);

		if (authorizedSchool != null) {
			return authorizedSchool;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationId=");
		sb.append(applicationId);

		sb.append("}");

		throw new NoSuchAuthorizedSchoolException(sb.toString());
	}

	/**
	 * Returns the first authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	@Override
	public AuthorizedSchool fetchByapplicationId_First(
		long applicationId,
		OrderByComparator<AuthorizedSchool> orderByComparator) {

		List<AuthorizedSchool> list = findByapplicationId(
			applicationId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authorized school
	 * @throws NoSuchAuthorizedSchoolException if a matching authorized school could not be found
	 */
	@Override
	public AuthorizedSchool findByapplicationId_Last(
			long applicationId,
			OrderByComparator<AuthorizedSchool> orderByComparator)
		throws NoSuchAuthorizedSchoolException {

		AuthorizedSchool authorizedSchool = fetchByapplicationId_Last(
			applicationId, orderByComparator);

		if (authorizedSchool != null) {
			return authorizedSchool;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationId=");
		sb.append(applicationId);

		sb.append("}");

		throw new NoSuchAuthorizedSchoolException(sb.toString());
	}

	/**
	 * Returns the last authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	@Override
	public AuthorizedSchool fetchByapplicationId_Last(
		long applicationId,
		OrderByComparator<AuthorizedSchool> orderByComparator) {

		int count = countByapplicationId(applicationId);

		if (count == 0) {
			return null;
		}

		List<AuthorizedSchool> list = findByapplicationId(
			applicationId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the authorized schools before and after the current authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param authorizedSchoolId the primary key of the current authorized school
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next authorized school
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	@Override
	public AuthorizedSchool[] findByapplicationId_PrevAndNext(
			long authorizedSchoolId, long applicationId,
			OrderByComparator<AuthorizedSchool> orderByComparator)
		throws NoSuchAuthorizedSchoolException {

		AuthorizedSchool authorizedSchool = findByPrimaryKey(
			authorizedSchoolId);

		Session session = null;

		try {
			session = openSession();

			AuthorizedSchool[] array = new AuthorizedSchoolImpl[3];

			array[0] = getByapplicationId_PrevAndNext(
				session, authorizedSchool, applicationId, orderByComparator,
				true);

			array[1] = authorizedSchool;

			array[2] = getByapplicationId_PrevAndNext(
				session, authorizedSchool, applicationId, orderByComparator,
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

	protected AuthorizedSchool getByapplicationId_PrevAndNext(
		Session session, AuthorizedSchool authorizedSchool, long applicationId,
		OrderByComparator<AuthorizedSchool> orderByComparator,
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

		sb.append(_SQL_SELECT_AUTHORIZEDSCHOOL_WHERE);

		sb.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

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
			sb.append(AuthorizedSchoolModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(applicationId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						authorizedSchool)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AuthorizedSchool> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the authorized schools where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 */
	@Override
	public void removeByapplicationId(long applicationId) {
		for (AuthorizedSchool authorizedSchool :
				findByapplicationId(
					applicationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(authorizedSchool);
		}
	}

	/**
	 * Returns the number of authorized schools where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching authorized schools
	 */
	@Override
	public int countByapplicationId(long applicationId) {
		FinderPath finderPath = _finderPathCountByapplicationId;

		Object[] finderArgs = new Object[] {applicationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AUTHORIZEDSCHOOL_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationId);

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

	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2 =
		"authorizedSchool.applicationId = ?";

	private FinderPath _finderPathFetchByapplicationId_schoolId;
	private FinderPath _finderPathCountByapplicationId_schoolId;

	/**
	 * Returns the authorized school where applicationId = &#63; and schoolId = &#63; or throws a <code>NoSuchAuthorizedSchoolException</code> if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching authorized school
	 * @throws NoSuchAuthorizedSchoolException if a matching authorized school could not be found
	 */
	@Override
	public AuthorizedSchool findByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws NoSuchAuthorizedSchoolException {

		AuthorizedSchool authorizedSchool = fetchByapplicationId_schoolId(
			applicationId, schoolId);

		if (authorizedSchool == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("applicationId=");
			sb.append(applicationId);

			sb.append(", schoolId=");
			sb.append(schoolId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAuthorizedSchoolException(sb.toString());
		}

		return authorizedSchool;
	}

	/**
	 * Returns the authorized school where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	@Override
	public AuthorizedSchool fetchByapplicationId_schoolId(
		long applicationId, long schoolId) {

		return fetchByapplicationId_schoolId(applicationId, schoolId, true);
	}

	/**
	 * Returns the authorized school where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	@Override
	public AuthorizedSchool fetchByapplicationId_schoolId(
		long applicationId, long schoolId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {applicationId, schoolId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByapplicationId_schoolId, finderArgs, this);
		}

		if (result instanceof AuthorizedSchool) {
			AuthorizedSchool authorizedSchool = (AuthorizedSchool)result;

			if ((applicationId != authorizedSchool.getApplicationId()) ||
				(schoolId != authorizedSchool.getSchoolId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_AUTHORIZEDSCHOOL_WHERE);

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

				List<AuthorizedSchool> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByapplicationId_schoolId,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									applicationId, schoolId
								};
							}

							_log.warn(
								"AuthorizedSchoolPersistenceImpl.fetchByapplicationId_schoolId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AuthorizedSchool authorizedSchool = list.get(0);

					result = authorizedSchool;

					cacheResult(authorizedSchool);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AuthorizedSchool)result;
		}
	}

	/**
	 * Removes the authorized school where applicationId = &#63; and schoolId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the authorized school that was removed
	 */
	@Override
	public AuthorizedSchool removeByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws NoSuchAuthorizedSchoolException {

		AuthorizedSchool authorizedSchool = findByapplicationId_schoolId(
			applicationId, schoolId);

		return remove(authorizedSchool);
	}

	/**
	 * Returns the number of authorized schools where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the number of matching authorized schools
	 */
	@Override
	public int countByapplicationId_schoolId(
		long applicationId, long schoolId) {

		FinderPath finderPath = _finderPathCountByapplicationId_schoolId;

		Object[] finderArgs = new Object[] {applicationId, schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_AUTHORIZEDSCHOOL_WHERE);

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
			"authorizedSchool.applicationId = ? AND ";

	private static final String
		_FINDER_COLUMN_APPLICATIONID_SCHOOLID_SCHOOLID_2 =
			"authorizedSchool.schoolId = ?";

	public AuthorizedSchoolPersistenceImpl() {
		setModelClass(AuthorizedSchool.class);

		setModelImplClass(AuthorizedSchoolImpl.class);
		setModelPKClass(long.class);

		setTable(AuthorizedSchoolTable.INSTANCE);
	}

	/**
	 * Caches the authorized school in the entity cache if it is enabled.
	 *
	 * @param authorizedSchool the authorized school
	 */
	@Override
	public void cacheResult(AuthorizedSchool authorizedSchool) {
		entityCache.putResult(
			AuthorizedSchoolImpl.class, authorizedSchool.getPrimaryKey(),
			authorizedSchool);

		finderCache.putResult(
			_finderPathFetchByapplicationId_schoolId,
			new Object[] {
				authorizedSchool.getApplicationId(),
				authorizedSchool.getSchoolId()
			},
			authorizedSchool);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the authorized schools in the entity cache if it is enabled.
	 *
	 * @param authorizedSchools the authorized schools
	 */
	@Override
	public void cacheResult(List<AuthorizedSchool> authorizedSchools) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (authorizedSchools.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AuthorizedSchool authorizedSchool : authorizedSchools) {
			if (entityCache.getResult(
					AuthorizedSchoolImpl.class,
					authorizedSchool.getPrimaryKey()) == null) {

				cacheResult(authorizedSchool);
			}
		}
	}

	/**
	 * Clears the cache for all authorized schools.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AuthorizedSchoolImpl.class);

		finderCache.clearCache(AuthorizedSchoolImpl.class);
	}

	/**
	 * Clears the cache for the authorized school.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuthorizedSchool authorizedSchool) {
		entityCache.removeResult(AuthorizedSchoolImpl.class, authorizedSchool);
	}

	@Override
	public void clearCache(List<AuthorizedSchool> authorizedSchools) {
		for (AuthorizedSchool authorizedSchool : authorizedSchools) {
			entityCache.removeResult(
				AuthorizedSchoolImpl.class, authorizedSchool);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AuthorizedSchoolImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AuthorizedSchoolImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AuthorizedSchoolModelImpl authorizedSchoolModelImpl) {

		Object[] args = new Object[] {
			authorizedSchoolModelImpl.getApplicationId(),
			authorizedSchoolModelImpl.getSchoolId()
		};

		finderCache.putResult(
			_finderPathCountByapplicationId_schoolId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByapplicationId_schoolId, args,
			authorizedSchoolModelImpl);
	}

	/**
	 * Creates a new authorized school with the primary key. Does not add the authorized school to the database.
	 *
	 * @param authorizedSchoolId the primary key for the new authorized school
	 * @return the new authorized school
	 */
	@Override
	public AuthorizedSchool create(long authorizedSchoolId) {
		AuthorizedSchool authorizedSchool = new AuthorizedSchoolImpl();

		authorizedSchool.setNew(true);
		authorizedSchool.setPrimaryKey(authorizedSchoolId);

		return authorizedSchool;
	}

	/**
	 * Removes the authorized school with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school that was removed
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	@Override
	public AuthorizedSchool remove(long authorizedSchoolId)
		throws NoSuchAuthorizedSchoolException {

		return remove((Serializable)authorizedSchoolId);
	}

	/**
	 * Removes the authorized school with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the authorized school
	 * @return the authorized school that was removed
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	@Override
	public AuthorizedSchool remove(Serializable primaryKey)
		throws NoSuchAuthorizedSchoolException {

		Session session = null;

		try {
			session = openSession();

			AuthorizedSchool authorizedSchool = (AuthorizedSchool)session.get(
				AuthorizedSchoolImpl.class, primaryKey);

			if (authorizedSchool == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuthorizedSchoolException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(authorizedSchool);
		}
		catch (NoSuchAuthorizedSchoolException noSuchEntityException) {
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
	protected AuthorizedSchool removeImpl(AuthorizedSchool authorizedSchool) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(authorizedSchool)) {
				authorizedSchool = (AuthorizedSchool)session.get(
					AuthorizedSchoolImpl.class,
					authorizedSchool.getPrimaryKeyObj());
			}

			if (authorizedSchool != null) {
				session.delete(authorizedSchool);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (authorizedSchool != null) {
			clearCache(authorizedSchool);
		}

		return authorizedSchool;
	}

	@Override
	public AuthorizedSchool updateImpl(AuthorizedSchool authorizedSchool) {
		boolean isNew = authorizedSchool.isNew();

		if (!(authorizedSchool instanceof AuthorizedSchoolModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(authorizedSchool.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					authorizedSchool);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in authorizedSchool proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AuthorizedSchool implementation " +
					authorizedSchool.getClass());
		}

		AuthorizedSchoolModelImpl authorizedSchoolModelImpl =
			(AuthorizedSchoolModelImpl)authorizedSchool;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(authorizedSchool);
			}
			else {
				authorizedSchool = (AuthorizedSchool)session.merge(
					authorizedSchool);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AuthorizedSchoolImpl.class, authorizedSchoolModelImpl, false, true);

		cacheUniqueFindersCache(authorizedSchoolModelImpl);

		if (isNew) {
			authorizedSchool.setNew(false);
		}

		authorizedSchool.resetOriginalValues();

		return authorizedSchool;
	}

	/**
	 * Returns the authorized school with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the authorized school
	 * @return the authorized school
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	@Override
	public AuthorizedSchool findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAuthorizedSchoolException {

		AuthorizedSchool authorizedSchool = fetchByPrimaryKey(primaryKey);

		if (authorizedSchool == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAuthorizedSchoolException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return authorizedSchool;
	}

	/**
	 * Returns the authorized school with the primary key or throws a <code>NoSuchAuthorizedSchoolException</code> if it could not be found.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	@Override
	public AuthorizedSchool findByPrimaryKey(long authorizedSchoolId)
		throws NoSuchAuthorizedSchoolException {

		return findByPrimaryKey((Serializable)authorizedSchoolId);
	}

	/**
	 * Returns the authorized school with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school, or <code>null</code> if a authorized school with the primary key could not be found
	 */
	@Override
	public AuthorizedSchool fetchByPrimaryKey(long authorizedSchoolId) {
		return fetchByPrimaryKey((Serializable)authorizedSchoolId);
	}

	/**
	 * Returns all the authorized schools.
	 *
	 * @return the authorized schools
	 */
	@Override
	public List<AuthorizedSchool> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @return the range of authorized schools
	 */
	@Override
	public List<AuthorizedSchool> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of authorized schools
	 */
	@Override
	public List<AuthorizedSchool> findAll(
		int start, int end,
		OrderByComparator<AuthorizedSchool> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of authorized schools
	 */
	@Override
	public List<AuthorizedSchool> findAll(
		int start, int end,
		OrderByComparator<AuthorizedSchool> orderByComparator,
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

		List<AuthorizedSchool> list = null;

		if (useFinderCache) {
			list = (List<AuthorizedSchool>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AUTHORIZEDSCHOOL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AUTHORIZEDSCHOOL;

				sql = sql.concat(AuthorizedSchoolModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AuthorizedSchool>)QueryUtil.list(
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
	 * Removes all the authorized schools from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AuthorizedSchool authorizedSchool : findAll()) {
			remove(authorizedSchool);
		}
	}

	/**
	 * Returns the number of authorized schools.
	 *
	 * @return the number of authorized schools
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AUTHORIZEDSCHOOL);

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
		return "authorizedSchoolId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AUTHORIZEDSCHOOL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AuthorizedSchoolModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the authorized school persistence.
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

		_finderPathWithPaginationFindByapplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByapplicationId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"applicationId"}, true);

		_finderPathWithoutPaginationFindByapplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByapplicationId",
			new String[] {Long.class.getName()}, new String[] {"applicationId"},
			true);

		_finderPathCountByapplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByapplicationId",
			new String[] {Long.class.getName()}, new String[] {"applicationId"},
			false);

		_finderPathFetchByapplicationId_schoolId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByapplicationId_schoolId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"applicationId", "schoolId"}, true);

		_finderPathCountByapplicationId_schoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByapplicationId_schoolId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"applicationId", "schoolId"}, false);

		_setAuthorizedSchoolUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setAuthorizedSchoolUtilPersistence(null);

		entityCache.removeCache(AuthorizedSchoolImpl.class.getName());
	}

	private void _setAuthorizedSchoolUtilPersistence(
		AuthorizedSchoolPersistence authorizedSchoolPersistence) {

		try {
			Field field = AuthorizedSchoolUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, authorizedSchoolPersistence);
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

	private static final String _SQL_SELECT_AUTHORIZEDSCHOOL =
		"SELECT authorizedSchool FROM AuthorizedSchool authorizedSchool";

	private static final String _SQL_SELECT_AUTHORIZEDSCHOOL_WHERE =
		"SELECT authorizedSchool FROM AuthorizedSchool authorizedSchool WHERE ";

	private static final String _SQL_COUNT_AUTHORIZEDSCHOOL =
		"SELECT COUNT(authorizedSchool) FROM AuthorizedSchool authorizedSchool";

	private static final String _SQL_COUNT_AUTHORIZEDSCHOOL_WHERE =
		"SELECT COUNT(authorizedSchool) FROM AuthorizedSchool authorizedSchool WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "authorizedSchool.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AuthorizedSchool exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AuthorizedSchool exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AuthorizedSchoolPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}