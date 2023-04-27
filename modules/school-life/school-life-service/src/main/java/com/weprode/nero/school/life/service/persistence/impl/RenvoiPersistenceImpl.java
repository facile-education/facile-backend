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

package com.weprode.nero.school.life.service.persistence.impl;

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

import com.weprode.nero.school.life.exception.NoSuchRenvoiException;
import com.weprode.nero.school.life.model.Renvoi;
import com.weprode.nero.school.life.model.RenvoiTable;
import com.weprode.nero.school.life.model.impl.RenvoiImpl;
import com.weprode.nero.school.life.model.impl.RenvoiModelImpl;
import com.weprode.nero.school.life.service.persistence.RenvoiPK;
import com.weprode.nero.school.life.service.persistence.RenvoiPersistence;
import com.weprode.nero.school.life.service.persistence.RenvoiUtil;
import com.weprode.nero.school.life.service.persistence.impl.constants.SchoollifePersistenceConstants;

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
 * The persistence implementation for the renvoi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {RenvoiPersistence.class, BasePersistence.class})
public class RenvoiPersistenceImpl
	extends BasePersistenceImpl<Renvoi> implements RenvoiPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RenvoiUtil</code> to access the renvoi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RenvoiImpl.class.getName();

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
	 * Returns all the renvois where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching renvois
	 */
	@Override
	public List<Renvoi> findByschoolId(long schoolId) {
		return findByschoolId(
			schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the renvois where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of matching renvois
	 */
	@Override
	public List<Renvoi> findByschoolId(long schoolId, int start, int end) {
		return findByschoolId(schoolId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the renvois where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching renvois
	 */
	@Override
	public List<Renvoi> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<Renvoi> orderByComparator) {

		return findByschoolId(schoolId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the renvois where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching renvois
	 */
	@Override
	public List<Renvoi> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<Renvoi> orderByComparator, boolean useFinderCache) {

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

		List<Renvoi> list = null;

		if (useFinderCache) {
			list = (List<Renvoi>)finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Renvoi renvoi : list) {
					if (schoolId != renvoi.getSchoolId()) {
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

			sb.append(_SQL_SELECT_RENVOI_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RenvoiModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				list = (List<Renvoi>)QueryUtil.list(
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
	 * Returns the first renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	@Override
	public Renvoi findByschoolId_First(
			long schoolId, OrderByComparator<Renvoi> orderByComparator)
		throws NoSuchRenvoiException {

		Renvoi renvoi = fetchByschoolId_First(schoolId, orderByComparator);

		if (renvoi != null) {
			return renvoi;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchRenvoiException(sb.toString());
	}

	/**
	 * Returns the first renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	@Override
	public Renvoi fetchByschoolId_First(
		long schoolId, OrderByComparator<Renvoi> orderByComparator) {

		List<Renvoi> list = findByschoolId(schoolId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	@Override
	public Renvoi findByschoolId_Last(
			long schoolId, OrderByComparator<Renvoi> orderByComparator)
		throws NoSuchRenvoiException {

		Renvoi renvoi = fetchByschoolId_Last(schoolId, orderByComparator);

		if (renvoi != null) {
			return renvoi;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append("}");

		throw new NoSuchRenvoiException(sb.toString());
	}

	/**
	 * Returns the last renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	@Override
	public Renvoi fetchByschoolId_Last(
		long schoolId, OrderByComparator<Renvoi> orderByComparator) {

		int count = countByschoolId(schoolId);

		if (count == 0) {
			return null;
		}

		List<Renvoi> list = findByschoolId(
			schoolId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the renvois before and after the current renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param renvoiPK the primary key of the current renvoi
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	@Override
	public Renvoi[] findByschoolId_PrevAndNext(
			RenvoiPK renvoiPK, long schoolId,
			OrderByComparator<Renvoi> orderByComparator)
		throws NoSuchRenvoiException {

		Renvoi renvoi = findByPrimaryKey(renvoiPK);

		Session session = null;

		try {
			session = openSession();

			Renvoi[] array = new RenvoiImpl[3];

			array[0] = getByschoolId_PrevAndNext(
				session, renvoi, schoolId, orderByComparator, true);

			array[1] = renvoi;

			array[2] = getByschoolId_PrevAndNext(
				session, renvoi, schoolId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Renvoi getByschoolId_PrevAndNext(
		Session session, Renvoi renvoi, long schoolId,
		OrderByComparator<Renvoi> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_RENVOI_WHERE);

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
			sb.append(RenvoiModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoolId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(renvoi)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Renvoi> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the renvois where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	@Override
	public void removeByschoolId(long schoolId) {
		for (Renvoi renvoi :
				findByschoolId(
					schoolId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(renvoi);
		}
	}

	/**
	 * Returns the number of renvois where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching renvois
	 */
	@Override
	public int countByschoolId(long schoolId) {
		FinderPath finderPath = _finderPathCountByschoolId;

		Object[] finderArgs = new Object[] {schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RENVOI_WHERE);

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
		"renvoi.schoolId = ?";

	private FinderPath _finderPathWithPaginationFindBysourceTeacherId_status;
	private FinderPath _finderPathWithoutPaginationFindBysourceTeacherId_status;
	private FinderPath _finderPathCountBysourceTeacherId_status;

	/**
	 * Returns all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @return the matching renvois
	 */
	@Override
	public List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status) {

		return findBysourceTeacherId_status(
			sourceTeacherId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of matching renvois
	 */
	@Override
	public List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status, int start, int end) {

		return findBysourceTeacherId_status(
			sourceTeacherId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching renvois
	 */
	@Override
	public List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status, int start, int end,
		OrderByComparator<Renvoi> orderByComparator) {

		return findBysourceTeacherId_status(
			sourceTeacherId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching renvois
	 */
	@Override
	public List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status, int start, int end,
		OrderByComparator<Renvoi> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindBysourceTeacherId_status;
				finderArgs = new Object[] {sourceTeacherId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBysourceTeacherId_status;
			finderArgs = new Object[] {
				sourceTeacherId, status, start, end, orderByComparator
			};
		}

		List<Renvoi> list = null;

		if (useFinderCache) {
			list = (List<Renvoi>)finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Renvoi renvoi : list) {
					if ((sourceTeacherId != renvoi.getSourceTeacherId()) ||
						(status != renvoi.getStatus())) {

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

			sb.append(_SQL_SELECT_RENVOI_WHERE);

			sb.append(_FINDER_COLUMN_SOURCETEACHERID_STATUS_SOURCETEACHERID_2);

			sb.append(_FINDER_COLUMN_SOURCETEACHERID_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RenvoiModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceTeacherId);

				queryPos.add(status);

				list = (List<Renvoi>)QueryUtil.list(
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
	 * Returns the first renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	@Override
	public Renvoi findBysourceTeacherId_status_First(
			long sourceTeacherId, int status,
			OrderByComparator<Renvoi> orderByComparator)
		throws NoSuchRenvoiException {

		Renvoi renvoi = fetchBysourceTeacherId_status_First(
			sourceTeacherId, status, orderByComparator);

		if (renvoi != null) {
			return renvoi;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sourceTeacherId=");
		sb.append(sourceTeacherId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRenvoiException(sb.toString());
	}

	/**
	 * Returns the first renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	@Override
	public Renvoi fetchBysourceTeacherId_status_First(
		long sourceTeacherId, int status,
		OrderByComparator<Renvoi> orderByComparator) {

		List<Renvoi> list = findBysourceTeacherId_status(
			sourceTeacherId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	@Override
	public Renvoi findBysourceTeacherId_status_Last(
			long sourceTeacherId, int status,
			OrderByComparator<Renvoi> orderByComparator)
		throws NoSuchRenvoiException {

		Renvoi renvoi = fetchBysourceTeacherId_status_Last(
			sourceTeacherId, status, orderByComparator);

		if (renvoi != null) {
			return renvoi;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sourceTeacherId=");
		sb.append(sourceTeacherId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRenvoiException(sb.toString());
	}

	/**
	 * Returns the last renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	@Override
	public Renvoi fetchBysourceTeacherId_status_Last(
		long sourceTeacherId, int status,
		OrderByComparator<Renvoi> orderByComparator) {

		int count = countBysourceTeacherId_status(sourceTeacherId, status);

		if (count == 0) {
			return null;
		}

		List<Renvoi> list = findBysourceTeacherId_status(
			sourceTeacherId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the renvois before and after the current renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param renvoiPK the primary key of the current renvoi
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	@Override
	public Renvoi[] findBysourceTeacherId_status_PrevAndNext(
			RenvoiPK renvoiPK, long sourceTeacherId, int status,
			OrderByComparator<Renvoi> orderByComparator)
		throws NoSuchRenvoiException {

		Renvoi renvoi = findByPrimaryKey(renvoiPK);

		Session session = null;

		try {
			session = openSession();

			Renvoi[] array = new RenvoiImpl[3];

			array[0] = getBysourceTeacherId_status_PrevAndNext(
				session, renvoi, sourceTeacherId, status, orderByComparator,
				true);

			array[1] = renvoi;

			array[2] = getBysourceTeacherId_status_PrevAndNext(
				session, renvoi, sourceTeacherId, status, orderByComparator,
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

	protected Renvoi getBysourceTeacherId_status_PrevAndNext(
		Session session, Renvoi renvoi, long sourceTeacherId, int status,
		OrderByComparator<Renvoi> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_RENVOI_WHERE);

		sb.append(_FINDER_COLUMN_SOURCETEACHERID_STATUS_SOURCETEACHERID_2);

		sb.append(_FINDER_COLUMN_SOURCETEACHERID_STATUS_STATUS_2);

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
			sb.append(RenvoiModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(sourceTeacherId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(renvoi)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Renvoi> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the renvois where sourceTeacherId = &#63; and status = &#63; from the database.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 */
	@Override
	public void removeBysourceTeacherId_status(
		long sourceTeacherId, int status) {

		for (Renvoi renvoi :
				findBysourceTeacherId_status(
					sourceTeacherId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(renvoi);
		}
	}

	/**
	 * Returns the number of renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @return the number of matching renvois
	 */
	@Override
	public int countBysourceTeacherId_status(long sourceTeacherId, int status) {
		FinderPath finderPath = _finderPathCountBysourceTeacherId_status;

		Object[] finderArgs = new Object[] {sourceTeacherId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_RENVOI_WHERE);

			sb.append(_FINDER_COLUMN_SOURCETEACHERID_STATUS_SOURCETEACHERID_2);

			sb.append(_FINDER_COLUMN_SOURCETEACHERID_STATUS_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceTeacherId);

				queryPos.add(status);

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
		_FINDER_COLUMN_SOURCETEACHERID_STATUS_SOURCETEACHERID_2 =
			"renvoi.sourceTeacherId = ? AND ";

	private static final String _FINDER_COLUMN_SOURCETEACHERID_STATUS_STATUS_2 =
		"renvoi.status = ?";

	public RenvoiPersistenceImpl() {
		setModelClass(Renvoi.class);

		setModelImplClass(RenvoiImpl.class);
		setModelPKClass(RenvoiPK.class);

		setTable(RenvoiTable.INSTANCE);
	}

	/**
	 * Caches the renvoi in the entity cache if it is enabled.
	 *
	 * @param renvoi the renvoi
	 */
	@Override
	public void cacheResult(Renvoi renvoi) {
		entityCache.putResult(RenvoiImpl.class, renvoi.getPrimaryKey(), renvoi);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the renvois in the entity cache if it is enabled.
	 *
	 * @param renvois the renvois
	 */
	@Override
	public void cacheResult(List<Renvoi> renvois) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (renvois.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Renvoi renvoi : renvois) {
			if (entityCache.getResult(
					RenvoiImpl.class, renvoi.getPrimaryKey()) == null) {

				cacheResult(renvoi);
			}
		}
	}

	/**
	 * Clears the cache for all renvois.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RenvoiImpl.class);

		finderCache.clearCache(RenvoiImpl.class);
	}

	/**
	 * Clears the cache for the renvoi.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Renvoi renvoi) {
		entityCache.removeResult(RenvoiImpl.class, renvoi);
	}

	@Override
	public void clearCache(List<Renvoi> renvois) {
		for (Renvoi renvoi : renvois) {
			entityCache.removeResult(RenvoiImpl.class, renvoi);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(RenvoiImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(RenvoiImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new renvoi with the primary key. Does not add the renvoi to the database.
	 *
	 * @param renvoiPK the primary key for the new renvoi
	 * @return the new renvoi
	 */
	@Override
	public Renvoi create(RenvoiPK renvoiPK) {
		Renvoi renvoi = new RenvoiImpl();

		renvoi.setNew(true);
		renvoi.setPrimaryKey(renvoiPK);

		return renvoi;
	}

	/**
	 * Removes the renvoi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi that was removed
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	@Override
	public Renvoi remove(RenvoiPK renvoiPK) throws NoSuchRenvoiException {
		return remove((Serializable)renvoiPK);
	}

	/**
	 * Removes the renvoi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the renvoi
	 * @return the renvoi that was removed
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	@Override
	public Renvoi remove(Serializable primaryKey) throws NoSuchRenvoiException {
		Session session = null;

		try {
			session = openSession();

			Renvoi renvoi = (Renvoi)session.get(RenvoiImpl.class, primaryKey);

			if (renvoi == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRenvoiException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(renvoi);
		}
		catch (NoSuchRenvoiException noSuchEntityException) {
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
	protected Renvoi removeImpl(Renvoi renvoi) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(renvoi)) {
				renvoi = (Renvoi)session.get(
					RenvoiImpl.class, renvoi.getPrimaryKeyObj());
			}

			if (renvoi != null) {
				session.delete(renvoi);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (renvoi != null) {
			clearCache(renvoi);
		}

		return renvoi;
	}

	@Override
	public Renvoi updateImpl(Renvoi renvoi) {
		boolean isNew = renvoi.isNew();

		if (!(renvoi instanceof RenvoiModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(renvoi.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(renvoi);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in renvoi proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Renvoi implementation " +
					renvoi.getClass());
		}

		RenvoiModelImpl renvoiModelImpl = (RenvoiModelImpl)renvoi;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(renvoi);
			}
			else {
				renvoi = (Renvoi)session.merge(renvoi);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(RenvoiImpl.class, renvoiModelImpl, false, true);

		if (isNew) {
			renvoi.setNew(false);
		}

		renvoi.resetOriginalValues();

		return renvoi;
	}

	/**
	 * Returns the renvoi with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the renvoi
	 * @return the renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	@Override
	public Renvoi findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRenvoiException {

		Renvoi renvoi = fetchByPrimaryKey(primaryKey);

		if (renvoi == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRenvoiException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return renvoi;
	}

	/**
	 * Returns the renvoi with the primary key or throws a <code>NoSuchRenvoiException</code> if it could not be found.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	@Override
	public Renvoi findByPrimaryKey(RenvoiPK renvoiPK)
		throws NoSuchRenvoiException {

		return findByPrimaryKey((Serializable)renvoiPK);
	}

	/**
	 * Returns the renvoi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi, or <code>null</code> if a renvoi with the primary key could not be found
	 */
	@Override
	public Renvoi fetchByPrimaryKey(RenvoiPK renvoiPK) {
		return fetchByPrimaryKey((Serializable)renvoiPK);
	}

	/**
	 * Returns all the renvois.
	 *
	 * @return the renvois
	 */
	@Override
	public List<Renvoi> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of renvois
	 */
	@Override
	public List<Renvoi> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of renvois
	 */
	@Override
	public List<Renvoi> findAll(
		int start, int end, OrderByComparator<Renvoi> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of renvois
	 */
	@Override
	public List<Renvoi> findAll(
		int start, int end, OrderByComparator<Renvoi> orderByComparator,
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

		List<Renvoi> list = null;

		if (useFinderCache) {
			list = (List<Renvoi>)finderCache.getResult(finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_RENVOI);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_RENVOI;

				sql = sql.concat(RenvoiModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Renvoi>)QueryUtil.list(
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
	 * Removes all the renvois from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Renvoi renvoi : findAll()) {
			remove(renvoi);
		}
	}

	/**
	 * Returns the number of renvois.
	 *
	 * @return the number of renvois
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_RENVOI);

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
		return "renvoiPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_RENVOI;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RenvoiModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the renvoi persistence.
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

		_finderPathWithPaginationFindBysourceTeacherId_status = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBysourceTeacherId_status",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"sourceTeacherId", "status"}, true);

		_finderPathWithoutPaginationFindBysourceTeacherId_status =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findBysourceTeacherId_status",
				new String[] {Long.class.getName(), Integer.class.getName()},
				new String[] {"sourceTeacherId", "status"}, true);

		_finderPathCountBysourceTeacherId_status = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBysourceTeacherId_status",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"sourceTeacherId", "status"}, false);

		_setRenvoiUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setRenvoiUtilPersistence(null);

		entityCache.removeCache(RenvoiImpl.class.getName());
	}

	private void _setRenvoiUtilPersistence(
		RenvoiPersistence renvoiPersistence) {

		try {
			Field field = RenvoiUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, renvoiPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = SchoollifePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = SchoollifePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = SchoollifePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_RENVOI =
		"SELECT renvoi FROM Renvoi renvoi";

	private static final String _SQL_SELECT_RENVOI_WHERE =
		"SELECT renvoi FROM Renvoi renvoi WHERE ";

	private static final String _SQL_COUNT_RENVOI =
		"SELECT COUNT(renvoi) FROM Renvoi renvoi";

	private static final String _SQL_COUNT_RENVOI_WHERE =
		"SELECT COUNT(renvoi) FROM Renvoi renvoi WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "renvoi.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Renvoi exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Renvoi exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		RenvoiPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"schoollifeSessionId", "studentId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private RenvoiModelArgumentsResolver _renvoiModelArgumentsResolver;

}