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

package com.weprode.facile.school.life.service.persistence.impl;

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

import com.weprode.facile.school.life.exception.NoSuchSessionException;
import com.weprode.facile.school.life.model.SchoollifeSession;
import com.weprode.facile.school.life.model.SchoollifeSessionTable;
import com.weprode.facile.school.life.model.impl.SchoollifeSessionImpl;
import com.weprode.facile.school.life.model.impl.SchoollifeSessionModelImpl;
import com.weprode.facile.school.life.service.persistence.SchoollifeSessionPersistence;
import com.weprode.facile.school.life.service.persistence.SchoollifeSessionUtil;
import com.weprode.facile.school.life.service.persistence.impl.constants.SchoollifePersistenceConstants;

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
 * The persistence implementation for the schoollife session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {SchoollifeSessionPersistence.class, BasePersistence.class}
)
public class SchoollifeSessionPersistenceImpl
	extends BasePersistenceImpl<SchoollifeSession>
	implements SchoollifeSessionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SchoollifeSessionUtil</code> to access the schoollife session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SchoollifeSessionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByschoollifeSlotId;
	private FinderPath _finderPathWithoutPaginationFindByschoollifeSlotId;
	private FinderPath _finderPathCountByschoollifeSlotId;

	/**
	 * Returns all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @return the matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId) {

		return findByschoollifeSlotId(
			schoollifeSlotId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId, int start, int end) {

		return findByschoollifeSlotId(schoollifeSlotId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return findByschoollifeSlotId(
			schoollifeSlotId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findByschoollifeSlotId(
		long schoollifeSlotId, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByschoollifeSlotId;
				finderArgs = new Object[] {schoollifeSlotId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByschoollifeSlotId;
			finderArgs = new Object[] {
				schoollifeSlotId, start, end, orderByComparator
			};
		}

		List<SchoollifeSession> list = null;

		if (useFinderCache) {
			list = (List<SchoollifeSession>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SchoollifeSession schoollifeSession : list) {
					if (schoollifeSlotId !=
							schoollifeSession.getSchoollifeSlotId()) {

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

			sb.append(_SQL_SELECT_SCHOOLLIFESESSION_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLLIFESLOTID_SCHOOLLIFESLOTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SchoollifeSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoollifeSlotId);

				list = (List<SchoollifeSession>)QueryUtil.list(
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
	 * Returns the first schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession findByschoollifeSlotId_First(
			long schoollifeSlotId,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = fetchByschoollifeSlotId_First(
			schoollifeSlotId, orderByComparator);

		if (schoollifeSession != null) {
			return schoollifeSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoollifeSlotId=");
		sb.append(schoollifeSlotId);

		sb.append("}");

		throw new NoSuchSessionException(sb.toString());
	}

	/**
	 * Returns the first schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession fetchByschoollifeSlotId_First(
		long schoollifeSlotId,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		List<SchoollifeSession> list = findByschoollifeSlotId(
			schoollifeSlotId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession findByschoollifeSlotId_Last(
			long schoollifeSlotId,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = fetchByschoollifeSlotId_Last(
			schoollifeSlotId, orderByComparator);

		if (schoollifeSession != null) {
			return schoollifeSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoollifeSlotId=");
		sb.append(schoollifeSlotId);

		sb.append("}");

		throw new NoSuchSessionException(sb.toString());
	}

	/**
	 * Returns the last schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession fetchByschoollifeSlotId_Last(
		long schoollifeSlotId,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		int count = countByschoollifeSlotId(schoollifeSlotId);

		if (count == 0) {
			return null;
		}

		List<SchoollifeSession> list = findByschoollifeSlotId(
			schoollifeSlotId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the schoollife sessions before and after the current schoollife session in the ordered set where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSessionId the primary key of the current schoollife session
	 * @param schoollifeSlotId the schoollife slot ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	@Override
	public SchoollifeSession[] findByschoollifeSlotId_PrevAndNext(
			long schoollifeSessionId, long schoollifeSlotId,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = findByPrimaryKey(
			schoollifeSessionId);

		Session session = null;

		try {
			session = openSession();

			SchoollifeSession[] array = new SchoollifeSessionImpl[3];

			array[0] = getByschoollifeSlotId_PrevAndNext(
				session, schoollifeSession, schoollifeSlotId, orderByComparator,
				true);

			array[1] = schoollifeSession;

			array[2] = getByschoollifeSlotId_PrevAndNext(
				session, schoollifeSession, schoollifeSlotId, orderByComparator,
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

	protected SchoollifeSession getByschoollifeSlotId_PrevAndNext(
		Session session, SchoollifeSession schoollifeSession,
		long schoollifeSlotId,
		OrderByComparator<SchoollifeSession> orderByComparator,
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

		sb.append(_SQL_SELECT_SCHOOLLIFESESSION_WHERE);

		sb.append(_FINDER_COLUMN_SCHOOLLIFESLOTID_SCHOOLLIFESLOTID_2);

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
			sb.append(SchoollifeSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoollifeSlotId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						schoollifeSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SchoollifeSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the schoollife sessions where schoollifeSlotId = &#63; from the database.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 */
	@Override
	public void removeByschoollifeSlotId(long schoollifeSlotId) {
		for (SchoollifeSession schoollifeSession :
				findByschoollifeSlotId(
					schoollifeSlotId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(schoollifeSession);
		}
	}

	/**
	 * Returns the number of schoollife sessions where schoollifeSlotId = &#63;.
	 *
	 * @param schoollifeSlotId the schoollife slot ID
	 * @return the number of matching schoollife sessions
	 */
	@Override
	public int countByschoollifeSlotId(long schoollifeSlotId) {
		FinderPath finderPath = _finderPathCountByschoollifeSlotId;

		Object[] finderArgs = new Object[] {schoollifeSlotId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SCHOOLLIFESESSION_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLLIFESLOTID_SCHOOLLIFESLOTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoollifeSlotId);

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
		_FINDER_COLUMN_SCHOOLLIFESLOTID_SCHOOLLIFESLOTID_2 =
			"schoollifeSession.schoollifeSlotId = ?";

	private FinderPath _finderPathWithPaginationFindBytype;
	private FinderPath _finderPathWithoutPaginationFindBytype;
	private FinderPath _finderPathCountBytype;

	/**
	 * Returns all the schoollife sessions where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findBytype(int type) {
		return findBytype(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schoollife sessions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findBytype(int type, int start, int end) {
		return findBytype(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findBytype(
		int type, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return findBytype(type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findBytype(
		int type, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBytype;
				finderArgs = new Object[] {type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBytype;
			finderArgs = new Object[] {type, start, end, orderByComparator};
		}

		List<SchoollifeSession> list = null;

		if (useFinderCache) {
			list = (List<SchoollifeSession>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SchoollifeSession schoollifeSession : list) {
					if (type != schoollifeSession.getType()) {
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

			sb.append(_SQL_SELECT_SCHOOLLIFESESSION_WHERE);

			sb.append(_FINDER_COLUMN_TYPE_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SchoollifeSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(type);

				list = (List<SchoollifeSession>)QueryUtil.list(
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
	 * Returns the first schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession findBytype_First(
			int type, OrderByComparator<SchoollifeSession> orderByComparator)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = fetchBytype_First(
			type, orderByComparator);

		if (schoollifeSession != null) {
			return schoollifeSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchSessionException(sb.toString());
	}

	/**
	 * Returns the first schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession fetchBytype_First(
		int type, OrderByComparator<SchoollifeSession> orderByComparator) {

		List<SchoollifeSession> list = findBytype(
			type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession findBytype_Last(
			int type, OrderByComparator<SchoollifeSession> orderByComparator)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = fetchBytype_Last(
			type, orderByComparator);

		if (schoollifeSession != null) {
			return schoollifeSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchSessionException(sb.toString());
	}

	/**
	 * Returns the last schoollife session in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession fetchBytype_Last(
		int type, OrderByComparator<SchoollifeSession> orderByComparator) {

		int count = countBytype(type);

		if (count == 0) {
			return null;
		}

		List<SchoollifeSession> list = findBytype(
			type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the schoollife sessions before and after the current schoollife session in the ordered set where type = &#63;.
	 *
	 * @param schoollifeSessionId the primary key of the current schoollife session
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	@Override
	public SchoollifeSession[] findBytype_PrevAndNext(
			long schoollifeSessionId, int type,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = findByPrimaryKey(
			schoollifeSessionId);

		Session session = null;

		try {
			session = openSession();

			SchoollifeSession[] array = new SchoollifeSessionImpl[3];

			array[0] = getBytype_PrevAndNext(
				session, schoollifeSession, type, orderByComparator, true);

			array[1] = schoollifeSession;

			array[2] = getBytype_PrevAndNext(
				session, schoollifeSession, type, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SchoollifeSession getBytype_PrevAndNext(
		Session session, SchoollifeSession schoollifeSession, int type,
		OrderByComparator<SchoollifeSession> orderByComparator,
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

		sb.append(_SQL_SELECT_SCHOOLLIFESESSION_WHERE);

		sb.append(_FINDER_COLUMN_TYPE_TYPE_2);

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
			sb.append(SchoollifeSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(type);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						schoollifeSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SchoollifeSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the schoollife sessions where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeBytype(int type) {
		for (SchoollifeSession schoollifeSession :
				findBytype(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(schoollifeSession);
		}
	}

	/**
	 * Returns the number of schoollife sessions where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching schoollife sessions
	 */
	@Override
	public int countBytype(int type) {
		FinderPath finderPath = _finderPathCountBytype;

		Object[] finderArgs = new Object[] {type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SCHOOLLIFESESSION_WHERE);

			sb.append(_FINDER_COLUMN_TYPE_TYPE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(type);

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

	private static final String _FINDER_COLUMN_TYPE_TYPE_2 =
		"schoollifeSession.type = ?";

	private FinderPath _finderPathWithPaginationFindByschoolId_type;
	private FinderPath _finderPathWithoutPaginationFindByschoolId_type;
	private FinderPath _finderPathCountByschoolId_type;

	/**
	 * Returns all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type) {

		return findByschoolId_type(
			schoolId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type, int start, int end) {

		return findByschoolId_type(schoolId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return findByschoolId_type(
			schoolId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findByschoolId_type(
		long schoolId, int type, int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByschoolId_type;
				finderArgs = new Object[] {schoolId, type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByschoolId_type;
			finderArgs = new Object[] {
				schoolId, type, start, end, orderByComparator
			};
		}

		List<SchoollifeSession> list = null;

		if (useFinderCache) {
			list = (List<SchoollifeSession>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SchoollifeSession schoollifeSession : list) {
					if ((schoolId != schoollifeSession.getSchoolId()) ||
						(type != schoollifeSession.getType())) {

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

			sb.append(_SQL_SELECT_SCHOOLLIFESESSION_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_SCHOOLID_2);

			sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SchoollifeSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				queryPos.add(type);

				list = (List<SchoollifeSession>)QueryUtil.list(
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
	 * Returns the first schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession findByschoolId_type_First(
			long schoolId, int type,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = fetchByschoolId_type_First(
			schoolId, type, orderByComparator);

		if (schoollifeSession != null) {
			return schoollifeSession;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchSessionException(sb.toString());
	}

	/**
	 * Returns the first schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession fetchByschoolId_type_First(
		long schoolId, int type,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		List<SchoollifeSession> list = findByschoolId_type(
			schoolId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session
	 * @throws NoSuchSessionException if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession findByschoolId_type_Last(
			long schoolId, int type,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = fetchByschoolId_type_Last(
			schoolId, type, orderByComparator);

		if (schoollifeSession != null) {
			return schoollifeSession;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("schoolId=");
		sb.append(schoolId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchSessionException(sb.toString());
	}

	/**
	 * Returns the last schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schoollife session, or <code>null</code> if a matching schoollife session could not be found
	 */
	@Override
	public SchoollifeSession fetchByschoolId_type_Last(
		long schoolId, int type,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		int count = countByschoolId_type(schoolId, type);

		if (count == 0) {
			return null;
		}

		List<SchoollifeSession> list = findByschoolId_type(
			schoolId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the schoollife sessions before and after the current schoollife session in the ordered set where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoollifeSessionId the primary key of the current schoollife session
	 * @param schoolId the school ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	@Override
	public SchoollifeSession[] findByschoolId_type_PrevAndNext(
			long schoollifeSessionId, long schoolId, int type,
			OrderByComparator<SchoollifeSession> orderByComparator)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = findByPrimaryKey(
			schoollifeSessionId);

		Session session = null;

		try {
			session = openSession();

			SchoollifeSession[] array = new SchoollifeSessionImpl[3];

			array[0] = getByschoolId_type_PrevAndNext(
				session, schoollifeSession, schoolId, type, orderByComparator,
				true);

			array[1] = schoollifeSession;

			array[2] = getByschoolId_type_PrevAndNext(
				session, schoollifeSession, schoolId, type, orderByComparator,
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

	protected SchoollifeSession getByschoolId_type_PrevAndNext(
		Session session, SchoollifeSession schoollifeSession, long schoolId,
		int type, OrderByComparator<SchoollifeSession> orderByComparator,
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

		sb.append(_SQL_SELECT_SCHOOLLIFESESSION_WHERE);

		sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_SCHOOLID_2);

		sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_TYPE_2);

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
			sb.append(SchoollifeSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(schoolId);

		queryPos.add(type);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						schoollifeSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SchoollifeSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the schoollife sessions where schoolId = &#63; and type = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 */
	@Override
	public void removeByschoolId_type(long schoolId, int type) {
		for (SchoollifeSession schoollifeSession :
				findByschoolId_type(
					schoolId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(schoollifeSession);
		}
	}

	/**
	 * Returns the number of schoollife sessions where schoolId = &#63; and type = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param type the type
	 * @return the number of matching schoollife sessions
	 */
	@Override
	public int countByschoolId_type(long schoolId, int type) {
		FinderPath finderPath = _finderPathCountByschoolId_type;

		Object[] finderArgs = new Object[] {schoolId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SCHOOLLIFESESSION_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_SCHOOLID_2);

			sb.append(_FINDER_COLUMN_SCHOOLID_TYPE_TYPE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				queryPos.add(type);

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

	private static final String _FINDER_COLUMN_SCHOOLID_TYPE_SCHOOLID_2 =
		"schoollifeSession.schoolId = ? AND ";

	private static final String _FINDER_COLUMN_SCHOOLID_TYPE_TYPE_2 =
		"schoollifeSession.type = ?";

	public SchoollifeSessionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(SchoollifeSession.class);

		setModelImplClass(SchoollifeSessionImpl.class);
		setModelPKClass(long.class);

		setTable(SchoollifeSessionTable.INSTANCE);
	}

	/**
	 * Caches the schoollife session in the entity cache if it is enabled.
	 *
	 * @param schoollifeSession the schoollife session
	 */
	@Override
	public void cacheResult(SchoollifeSession schoollifeSession) {
		entityCache.putResult(
			SchoollifeSessionImpl.class, schoollifeSession.getPrimaryKey(),
			schoollifeSession);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the schoollife sessions in the entity cache if it is enabled.
	 *
	 * @param schoollifeSessions the schoollife sessions
	 */
	@Override
	public void cacheResult(List<SchoollifeSession> schoollifeSessions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (schoollifeSessions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SchoollifeSession schoollifeSession : schoollifeSessions) {
			if (entityCache.getResult(
					SchoollifeSessionImpl.class,
					schoollifeSession.getPrimaryKey()) == null) {

				cacheResult(schoollifeSession);
			}
		}
	}

	/**
	 * Clears the cache for all schoollife sessions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SchoollifeSessionImpl.class);

		finderCache.clearCache(SchoollifeSessionImpl.class);
	}

	/**
	 * Clears the cache for the schoollife session.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SchoollifeSession schoollifeSession) {
		entityCache.removeResult(
			SchoollifeSessionImpl.class, schoollifeSession);
	}

	@Override
	public void clearCache(List<SchoollifeSession> schoollifeSessions) {
		for (SchoollifeSession schoollifeSession : schoollifeSessions) {
			entityCache.removeResult(
				SchoollifeSessionImpl.class, schoollifeSession);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SchoollifeSessionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SchoollifeSessionImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new schoollife session with the primary key. Does not add the schoollife session to the database.
	 *
	 * @param schoollifeSessionId the primary key for the new schoollife session
	 * @return the new schoollife session
	 */
	@Override
	public SchoollifeSession create(long schoollifeSessionId) {
		SchoollifeSession schoollifeSession = new SchoollifeSessionImpl();

		schoollifeSession.setNew(true);
		schoollifeSession.setPrimaryKey(schoollifeSessionId);

		return schoollifeSession;
	}

	/**
	 * Removes the schoollife session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session that was removed
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	@Override
	public SchoollifeSession remove(long schoollifeSessionId)
		throws NoSuchSessionException {

		return remove((Serializable)schoollifeSessionId);
	}

	/**
	 * Removes the schoollife session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the schoollife session
	 * @return the schoollife session that was removed
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	@Override
	public SchoollifeSession remove(Serializable primaryKey)
		throws NoSuchSessionException {

		Session session = null;

		try {
			session = openSession();

			SchoollifeSession schoollifeSession =
				(SchoollifeSession)session.get(
					SchoollifeSessionImpl.class, primaryKey);

			if (schoollifeSession == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSessionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(schoollifeSession);
		}
		catch (NoSuchSessionException noSuchEntityException) {
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
	protected SchoollifeSession removeImpl(
		SchoollifeSession schoollifeSession) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(schoollifeSession)) {
				schoollifeSession = (SchoollifeSession)session.get(
					SchoollifeSessionImpl.class,
					schoollifeSession.getPrimaryKeyObj());
			}

			if (schoollifeSession != null) {
				session.delete(schoollifeSession);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (schoollifeSession != null) {
			clearCache(schoollifeSession);
		}

		return schoollifeSession;
	}

	@Override
	public SchoollifeSession updateImpl(SchoollifeSession schoollifeSession) {
		boolean isNew = schoollifeSession.isNew();

		if (!(schoollifeSession instanceof SchoollifeSessionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(schoollifeSession.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					schoollifeSession);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in schoollifeSession proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SchoollifeSession implementation " +
					schoollifeSession.getClass());
		}

		SchoollifeSessionModelImpl schoollifeSessionModelImpl =
			(SchoollifeSessionModelImpl)schoollifeSession;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(schoollifeSession);
			}
			else {
				schoollifeSession = (SchoollifeSession)session.merge(
					schoollifeSession);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SchoollifeSessionImpl.class, schoollifeSessionModelImpl, false,
			true);

		if (isNew) {
			schoollifeSession.setNew(false);
		}

		schoollifeSession.resetOriginalValues();

		return schoollifeSession;
	}

	/**
	 * Returns the schoollife session with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the schoollife session
	 * @return the schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	@Override
	public SchoollifeSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSessionException {

		SchoollifeSession schoollifeSession = fetchByPrimaryKey(primaryKey);

		if (schoollifeSession == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSessionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return schoollifeSession;
	}

	/**
	 * Returns the schoollife session with the primary key or throws a <code>NoSuchSessionException</code> if it could not be found.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session
	 * @throws NoSuchSessionException if a schoollife session with the primary key could not be found
	 */
	@Override
	public SchoollifeSession findByPrimaryKey(long schoollifeSessionId)
		throws NoSuchSessionException {

		return findByPrimaryKey((Serializable)schoollifeSessionId);
	}

	/**
	 * Returns the schoollife session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session, or <code>null</code> if a schoollife session with the primary key could not be found
	 */
	@Override
	public SchoollifeSession fetchByPrimaryKey(long schoollifeSessionId) {
		return fetchByPrimaryKey((Serializable)schoollifeSessionId);
	}

	/**
	 * Returns all the schoollife sessions.
	 *
	 * @return the schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findAll(
		int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of schoollife sessions
	 */
	@Override
	public List<SchoollifeSession> findAll(
		int start, int end,
		OrderByComparator<SchoollifeSession> orderByComparator,
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

		List<SchoollifeSession> list = null;

		if (useFinderCache) {
			list = (List<SchoollifeSession>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SCHOOLLIFESESSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SCHOOLLIFESESSION;

				sql = sql.concat(SchoollifeSessionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SchoollifeSession>)QueryUtil.list(
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
	 * Removes all the schoollife sessions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SchoollifeSession schoollifeSession : findAll()) {
			remove(schoollifeSession);
		}
	}

	/**
	 * Returns the number of schoollife sessions.
	 *
	 * @return the number of schoollife sessions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SCHOOLLIFESESSION);

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
		return "schoollifeSessionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SCHOOLLIFESESSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SchoollifeSessionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the schoollife session persistence.
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

		_finderPathWithPaginationFindByschoollifeSlotId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByschoollifeSlotId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"schoollifeSlotId"}, true);

		_finderPathWithoutPaginationFindByschoollifeSlotId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByschoollifeSlotId",
			new String[] {Long.class.getName()},
			new String[] {"schoollifeSlotId"}, true);

		_finderPathCountByschoollifeSlotId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByschoollifeSlotId", new String[] {Long.class.getName()},
			new String[] {"schoollifeSlotId"}, false);

		_finderPathWithPaginationFindBytype = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBytype",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"type_"}, true);

		_finderPathWithoutPaginationFindBytype = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBytype",
			new String[] {Integer.class.getName()}, new String[] {"type_"},
			true);

		_finderPathCountBytype = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBytype",
			new String[] {Integer.class.getName()}, new String[] {"type_"},
			false);

		_finderPathWithPaginationFindByschoolId_type = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByschoolId_type",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"schoolId", "type_"}, true);

		_finderPathWithoutPaginationFindByschoolId_type = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByschoolId_type",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"schoolId", "type_"}, true);

		_finderPathCountByschoolId_type = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByschoolId_type",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"schoolId", "type_"}, false);

		_setSchoollifeSessionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSchoollifeSessionUtilPersistence(null);

		entityCache.removeCache(SchoollifeSessionImpl.class.getName());
	}

	private void _setSchoollifeSessionUtilPersistence(
		SchoollifeSessionPersistence schoollifeSessionPersistence) {

		try {
			Field field = SchoollifeSessionUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, schoollifeSessionPersistence);
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

	private static final String _SQL_SELECT_SCHOOLLIFESESSION =
		"SELECT schoollifeSession FROM SchoollifeSession schoollifeSession";

	private static final String _SQL_SELECT_SCHOOLLIFESESSION_WHERE =
		"SELECT schoollifeSession FROM SchoollifeSession schoollifeSession WHERE ";

	private static final String _SQL_COUNT_SCHOOLLIFESESSION =
		"SELECT COUNT(schoollifeSession) FROM SchoollifeSession schoollifeSession";

	private static final String _SQL_COUNT_SCHOOLLIFESESSION_WHERE =
		"SELECT COUNT(schoollifeSession) FROM SchoollifeSession schoollifeSession WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "schoollifeSession.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SchoollifeSession exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SchoollifeSession exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SchoollifeSessionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}