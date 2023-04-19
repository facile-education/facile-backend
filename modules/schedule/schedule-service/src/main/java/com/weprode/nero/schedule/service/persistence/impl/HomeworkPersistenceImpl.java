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

package com.weprode.nero.schedule.service.persistence.impl;

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

import com.weprode.nero.schedule.exception.NoSuchHomeworkException;
import com.weprode.nero.schedule.model.Homework;
import com.weprode.nero.schedule.model.HomeworkTable;
import com.weprode.nero.schedule.model.impl.HomeworkImpl;
import com.weprode.nero.schedule.model.impl.HomeworkModelImpl;
import com.weprode.nero.schedule.service.persistence.HomeworkPersistence;
import com.weprode.nero.schedule.service.persistence.HomeworkUtil;
import com.weprode.nero.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

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
 * The persistence implementation for the homework service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {HomeworkPersistence.class, BasePersistence.class})
public class HomeworkPersistenceImpl
	extends BasePersistenceImpl<Homework> implements HomeworkPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>HomeworkUtil</code> to access the homework persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		HomeworkImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBytargetSessionId;
	private FinderPath _finderPathWithoutPaginationFindBytargetSessionId;
	private FinderPath _finderPathCountBytargetSessionId;

	/**
	 * Returns all the homeworks where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @return the matching homeworks
	 */
	@Override
	public List<Homework> findBytargetSessionId(long targetSessionId) {
		return findBytargetSessionId(
			targetSessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the homeworks where targetSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetSessionId the target session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of matching homeworks
	 */
	@Override
	public List<Homework> findBytargetSessionId(
		long targetSessionId, int start, int end) {

		return findBytargetSessionId(targetSessionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the homeworks where targetSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetSessionId the target session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching homeworks
	 */
	@Override
	public List<Homework> findBytargetSessionId(
		long targetSessionId, int start, int end,
		OrderByComparator<Homework> orderByComparator) {

		return findBytargetSessionId(
			targetSessionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the homeworks where targetSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetSessionId the target session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching homeworks
	 */
	@Override
	public List<Homework> findBytargetSessionId(
		long targetSessionId, int start, int end,
		OrderByComparator<Homework> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBytargetSessionId;
				finderArgs = new Object[] {targetSessionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBytargetSessionId;
			finderArgs = new Object[] {
				targetSessionId, start, end, orderByComparator
			};
		}

		List<Homework> list = null;

		if (useFinderCache) {
			list = (List<Homework>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Homework homework : list) {
					if (targetSessionId != homework.getTargetSessionId()) {
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

			sb.append(_SQL_SELECT_HOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_TARGETSESSIONID_TARGETSESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(HomeworkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(targetSessionId);

				list = (List<Homework>)QueryUtil.list(
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
	 * Returns the first homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	@Override
	public Homework findBytargetSessionId_First(
			long targetSessionId, OrderByComparator<Homework> orderByComparator)
		throws NoSuchHomeworkException {

		Homework homework = fetchBytargetSessionId_First(
			targetSessionId, orderByComparator);

		if (homework != null) {
			return homework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("targetSessionId=");
		sb.append(targetSessionId);

		sb.append("}");

		throw new NoSuchHomeworkException(sb.toString());
	}

	/**
	 * Returns the first homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework, or <code>null</code> if a matching homework could not be found
	 */
	@Override
	public Homework fetchBytargetSessionId_First(
		long targetSessionId, OrderByComparator<Homework> orderByComparator) {

		List<Homework> list = findBytargetSessionId(
			targetSessionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	@Override
	public Homework findBytargetSessionId_Last(
			long targetSessionId, OrderByComparator<Homework> orderByComparator)
		throws NoSuchHomeworkException {

		Homework homework = fetchBytargetSessionId_Last(
			targetSessionId, orderByComparator);

		if (homework != null) {
			return homework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("targetSessionId=");
		sb.append(targetSessionId);

		sb.append("}");

		throw new NoSuchHomeworkException(sb.toString());
	}

	/**
	 * Returns the last homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework, or <code>null</code> if a matching homework could not be found
	 */
	@Override
	public Homework fetchBytargetSessionId_Last(
		long targetSessionId, OrderByComparator<Homework> orderByComparator) {

		int count = countBytargetSessionId(targetSessionId);

		if (count == 0) {
			return null;
		}

		List<Homework> list = findBytargetSessionId(
			targetSessionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the homeworks before and after the current homework in the ordered set where targetSessionId = &#63;.
	 *
	 * @param homeworkId the primary key of the current homework
	 * @param targetSessionId the target session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	@Override
	public Homework[] findBytargetSessionId_PrevAndNext(
			long homeworkId, long targetSessionId,
			OrderByComparator<Homework> orderByComparator)
		throws NoSuchHomeworkException {

		Homework homework = findByPrimaryKey(homeworkId);

		Session session = null;

		try {
			session = openSession();

			Homework[] array = new HomeworkImpl[3];

			array[0] = getBytargetSessionId_PrevAndNext(
				session, homework, targetSessionId, orderByComparator, true);

			array[1] = homework;

			array[2] = getBytargetSessionId_PrevAndNext(
				session, homework, targetSessionId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Homework getBytargetSessionId_PrevAndNext(
		Session session, Homework homework, long targetSessionId,
		OrderByComparator<Homework> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_HOMEWORK_WHERE);

		sb.append(_FINDER_COLUMN_TARGETSESSIONID_TARGETSESSIONID_2);

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
			sb.append(HomeworkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(targetSessionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(homework)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Homework> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the homeworks where targetSessionId = &#63; from the database.
	 *
	 * @param targetSessionId the target session ID
	 */
	@Override
	public void removeBytargetSessionId(long targetSessionId) {
		for (Homework homework :
				findBytargetSessionId(
					targetSessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(homework);
		}
	}

	/**
	 * Returns the number of homeworks where targetSessionId = &#63;.
	 *
	 * @param targetSessionId the target session ID
	 * @return the number of matching homeworks
	 */
	@Override
	public int countBytargetSessionId(long targetSessionId) {
		FinderPath finderPath = _finderPathCountBytargetSessionId;

		Object[] finderArgs = new Object[] {targetSessionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_HOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_TARGETSESSIONID_TARGETSESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(targetSessionId);

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
		_FINDER_COLUMN_TARGETSESSIONID_TARGETSESSIONID_2 =
			"homework.targetSessionId = ?";

	private FinderPath _finderPathWithPaginationFindBysourceSessionId;
	private FinderPath _finderPathWithoutPaginationFindBysourceSessionId;
	private FinderPath _finderPathCountBysourceSessionId;

	/**
	 * Returns all the homeworks where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @return the matching homeworks
	 */
	@Override
	public List<Homework> findBysourceSessionId(long sourceSessionId) {
		return findBysourceSessionId(
			sourceSessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the homeworks where sourceSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param sourceSessionId the source session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of matching homeworks
	 */
	@Override
	public List<Homework> findBysourceSessionId(
		long sourceSessionId, int start, int end) {

		return findBysourceSessionId(sourceSessionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the homeworks where sourceSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param sourceSessionId the source session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching homeworks
	 */
	@Override
	public List<Homework> findBysourceSessionId(
		long sourceSessionId, int start, int end,
		OrderByComparator<Homework> orderByComparator) {

		return findBysourceSessionId(
			sourceSessionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the homeworks where sourceSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param sourceSessionId the source session ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching homeworks
	 */
	@Override
	public List<Homework> findBysourceSessionId(
		long sourceSessionId, int start, int end,
		OrderByComparator<Homework> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBysourceSessionId;
				finderArgs = new Object[] {sourceSessionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBysourceSessionId;
			finderArgs = new Object[] {
				sourceSessionId, start, end, orderByComparator
			};
		}

		List<Homework> list = null;

		if (useFinderCache) {
			list = (List<Homework>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Homework homework : list) {
					if (sourceSessionId != homework.getSourceSessionId()) {
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

			sb.append(_SQL_SELECT_HOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_SOURCESESSIONID_SOURCESESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(HomeworkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceSessionId);

				list = (List<Homework>)QueryUtil.list(
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
	 * Returns the first homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	@Override
	public Homework findBysourceSessionId_First(
			long sourceSessionId, OrderByComparator<Homework> orderByComparator)
		throws NoSuchHomeworkException {

		Homework homework = fetchBysourceSessionId_First(
			sourceSessionId, orderByComparator);

		if (homework != null) {
			return homework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sourceSessionId=");
		sb.append(sourceSessionId);

		sb.append("}");

		throw new NoSuchHomeworkException(sb.toString());
	}

	/**
	 * Returns the first homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework, or <code>null</code> if a matching homework could not be found
	 */
	@Override
	public Homework fetchBysourceSessionId_First(
		long sourceSessionId, OrderByComparator<Homework> orderByComparator) {

		List<Homework> list = findBysourceSessionId(
			sourceSessionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	@Override
	public Homework findBysourceSessionId_Last(
			long sourceSessionId, OrderByComparator<Homework> orderByComparator)
		throws NoSuchHomeworkException {

		Homework homework = fetchBysourceSessionId_Last(
			sourceSessionId, orderByComparator);

		if (homework != null) {
			return homework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sourceSessionId=");
		sb.append(sourceSessionId);

		sb.append("}");

		throw new NoSuchHomeworkException(sb.toString());
	}

	/**
	 * Returns the last homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework, or <code>null</code> if a matching homework could not be found
	 */
	@Override
	public Homework fetchBysourceSessionId_Last(
		long sourceSessionId, OrderByComparator<Homework> orderByComparator) {

		int count = countBysourceSessionId(sourceSessionId);

		if (count == 0) {
			return null;
		}

		List<Homework> list = findBysourceSessionId(
			sourceSessionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the homeworks before and after the current homework in the ordered set where sourceSessionId = &#63;.
	 *
	 * @param homeworkId the primary key of the current homework
	 * @param sourceSessionId the source session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	@Override
	public Homework[] findBysourceSessionId_PrevAndNext(
			long homeworkId, long sourceSessionId,
			OrderByComparator<Homework> orderByComparator)
		throws NoSuchHomeworkException {

		Homework homework = findByPrimaryKey(homeworkId);

		Session session = null;

		try {
			session = openSession();

			Homework[] array = new HomeworkImpl[3];

			array[0] = getBysourceSessionId_PrevAndNext(
				session, homework, sourceSessionId, orderByComparator, true);

			array[1] = homework;

			array[2] = getBysourceSessionId_PrevAndNext(
				session, homework, sourceSessionId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Homework getBysourceSessionId_PrevAndNext(
		Session session, Homework homework, long sourceSessionId,
		OrderByComparator<Homework> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_HOMEWORK_WHERE);

		sb.append(_FINDER_COLUMN_SOURCESESSIONID_SOURCESESSIONID_2);

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
			sb.append(HomeworkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(sourceSessionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(homework)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Homework> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the homeworks where sourceSessionId = &#63; from the database.
	 *
	 * @param sourceSessionId the source session ID
	 */
	@Override
	public void removeBysourceSessionId(long sourceSessionId) {
		for (Homework homework :
				findBysourceSessionId(
					sourceSessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(homework);
		}
	}

	/**
	 * Returns the number of homeworks where sourceSessionId = &#63;.
	 *
	 * @param sourceSessionId the source session ID
	 * @return the number of matching homeworks
	 */
	@Override
	public int countBysourceSessionId(long sourceSessionId) {
		FinderPath finderPath = _finderPathCountBysourceSessionId;

		Object[] finderArgs = new Object[] {sourceSessionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_HOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_SOURCESESSIONID_SOURCESESSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceSessionId);

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
		_FINDER_COLUMN_SOURCESESSIONID_SOURCESESSIONID_2 =
			"homework.sourceSessionId = ?";

	private FinderPath _finderPathWithPaginationFindBytargetWeekId;
	private FinderPath _finderPathWithoutPaginationFindBytargetWeekId;
	private FinderPath _finderPathCountBytargetWeekId;

	/**
	 * Returns all the homeworks where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @return the matching homeworks
	 */
	@Override
	public List<Homework> findBytargetWeekId(int targetWeekId) {
		return findBytargetWeekId(
			targetWeekId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the homeworks where targetWeekId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetWeekId the target week ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of matching homeworks
	 */
	@Override
	public List<Homework> findBytargetWeekId(
		int targetWeekId, int start, int end) {

		return findBytargetWeekId(targetWeekId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the homeworks where targetWeekId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetWeekId the target week ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching homeworks
	 */
	@Override
	public List<Homework> findBytargetWeekId(
		int targetWeekId, int start, int end,
		OrderByComparator<Homework> orderByComparator) {

		return findBytargetWeekId(
			targetWeekId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the homeworks where targetWeekId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param targetWeekId the target week ID
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching homeworks
	 */
	@Override
	public List<Homework> findBytargetWeekId(
		int targetWeekId, int start, int end,
		OrderByComparator<Homework> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBytargetWeekId;
				finderArgs = new Object[] {targetWeekId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBytargetWeekId;
			finderArgs = new Object[] {
				targetWeekId, start, end, orderByComparator
			};
		}

		List<Homework> list = null;

		if (useFinderCache) {
			list = (List<Homework>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Homework homework : list) {
					if (targetWeekId != homework.getTargetWeekId()) {
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

			sb.append(_SQL_SELECT_HOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_TARGETWEEKID_TARGETWEEKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(HomeworkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(targetWeekId);

				list = (List<Homework>)QueryUtil.list(
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
	 * Returns the first homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	@Override
	public Homework findBytargetWeekId_First(
			int targetWeekId, OrderByComparator<Homework> orderByComparator)
		throws NoSuchHomeworkException {

		Homework homework = fetchBytargetWeekId_First(
			targetWeekId, orderByComparator);

		if (homework != null) {
			return homework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("targetWeekId=");
		sb.append(targetWeekId);

		sb.append("}");

		throw new NoSuchHomeworkException(sb.toString());
	}

	/**
	 * Returns the first homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching homework, or <code>null</code> if a matching homework could not be found
	 */
	@Override
	public Homework fetchBytargetWeekId_First(
		int targetWeekId, OrderByComparator<Homework> orderByComparator) {

		List<Homework> list = findBytargetWeekId(
			targetWeekId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework
	 * @throws NoSuchHomeworkException if a matching homework could not be found
	 */
	@Override
	public Homework findBytargetWeekId_Last(
			int targetWeekId, OrderByComparator<Homework> orderByComparator)
		throws NoSuchHomeworkException {

		Homework homework = fetchBytargetWeekId_Last(
			targetWeekId, orderByComparator);

		if (homework != null) {
			return homework;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("targetWeekId=");
		sb.append(targetWeekId);

		sb.append("}");

		throw new NoSuchHomeworkException(sb.toString());
	}

	/**
	 * Returns the last homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching homework, or <code>null</code> if a matching homework could not be found
	 */
	@Override
	public Homework fetchBytargetWeekId_Last(
		int targetWeekId, OrderByComparator<Homework> orderByComparator) {

		int count = countBytargetWeekId(targetWeekId);

		if (count == 0) {
			return null;
		}

		List<Homework> list = findBytargetWeekId(
			targetWeekId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the homeworks before and after the current homework in the ordered set where targetWeekId = &#63;.
	 *
	 * @param homeworkId the primary key of the current homework
	 * @param targetWeekId the target week ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	@Override
	public Homework[] findBytargetWeekId_PrevAndNext(
			long homeworkId, int targetWeekId,
			OrderByComparator<Homework> orderByComparator)
		throws NoSuchHomeworkException {

		Homework homework = findByPrimaryKey(homeworkId);

		Session session = null;

		try {
			session = openSession();

			Homework[] array = new HomeworkImpl[3];

			array[0] = getBytargetWeekId_PrevAndNext(
				session, homework, targetWeekId, orderByComparator, true);

			array[1] = homework;

			array[2] = getBytargetWeekId_PrevAndNext(
				session, homework, targetWeekId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Homework getBytargetWeekId_PrevAndNext(
		Session session, Homework homework, int targetWeekId,
		OrderByComparator<Homework> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_HOMEWORK_WHERE);

		sb.append(_FINDER_COLUMN_TARGETWEEKID_TARGETWEEKID_2);

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
			sb.append(HomeworkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(targetWeekId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(homework)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Homework> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the homeworks where targetWeekId = &#63; from the database.
	 *
	 * @param targetWeekId the target week ID
	 */
	@Override
	public void removeBytargetWeekId(int targetWeekId) {
		for (Homework homework :
				findBytargetWeekId(
					targetWeekId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(homework);
		}
	}

	/**
	 * Returns the number of homeworks where targetWeekId = &#63;.
	 *
	 * @param targetWeekId the target week ID
	 * @return the number of matching homeworks
	 */
	@Override
	public int countBytargetWeekId(int targetWeekId) {
		FinderPath finderPath = _finderPathCountBytargetWeekId;

		Object[] finderArgs = new Object[] {targetWeekId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_HOMEWORK_WHERE);

			sb.append(_FINDER_COLUMN_TARGETWEEKID_TARGETWEEKID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(targetWeekId);

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

	private static final String _FINDER_COLUMN_TARGETWEEKID_TARGETWEEKID_2 =
		"homework.targetWeekId = ?";

	public HomeworkPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Homework.class);

		setModelImplClass(HomeworkImpl.class);
		setModelPKClass(long.class);

		setTable(HomeworkTable.INSTANCE);
	}

	/**
	 * Caches the homework in the entity cache if it is enabled.
	 *
	 * @param homework the homework
	 */
	@Override
	public void cacheResult(Homework homework) {
		entityCache.putResult(
			HomeworkImpl.class, homework.getPrimaryKey(), homework);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the homeworks in the entity cache if it is enabled.
	 *
	 * @param homeworks the homeworks
	 */
	@Override
	public void cacheResult(List<Homework> homeworks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (homeworks.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Homework homework : homeworks) {
			if (entityCache.getResult(
					HomeworkImpl.class, homework.getPrimaryKey()) == null) {

				cacheResult(homework);
			}
		}
	}

	/**
	 * Clears the cache for all homeworks.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HomeworkImpl.class);

		finderCache.clearCache(HomeworkImpl.class);
	}

	/**
	 * Clears the cache for the homework.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Homework homework) {
		entityCache.removeResult(HomeworkImpl.class, homework);
	}

	@Override
	public void clearCache(List<Homework> homeworks) {
		for (Homework homework : homeworks) {
			entityCache.removeResult(HomeworkImpl.class, homework);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(HomeworkImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(HomeworkImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new homework with the primary key. Does not add the homework to the database.
	 *
	 * @param homeworkId the primary key for the new homework
	 * @return the new homework
	 */
	@Override
	public Homework create(long homeworkId) {
		Homework homework = new HomeworkImpl();

		homework.setNew(true);
		homework.setPrimaryKey(homeworkId);

		return homework;
	}

	/**
	 * Removes the homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework that was removed
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	@Override
	public Homework remove(long homeworkId) throws NoSuchHomeworkException {
		return remove((Serializable)homeworkId);
	}

	/**
	 * Removes the homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the homework
	 * @return the homework that was removed
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	@Override
	public Homework remove(Serializable primaryKey)
		throws NoSuchHomeworkException {

		Session session = null;

		try {
			session = openSession();

			Homework homework = (Homework)session.get(
				HomeworkImpl.class, primaryKey);

			if (homework == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHomeworkException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(homework);
		}
		catch (NoSuchHomeworkException noSuchEntityException) {
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
	protected Homework removeImpl(Homework homework) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(homework)) {
				homework = (Homework)session.get(
					HomeworkImpl.class, homework.getPrimaryKeyObj());
			}

			if (homework != null) {
				session.delete(homework);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (homework != null) {
			clearCache(homework);
		}

		return homework;
	}

	@Override
	public Homework updateImpl(Homework homework) {
		boolean isNew = homework.isNew();

		if (!(homework instanceof HomeworkModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(homework.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(homework);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in homework proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Homework implementation " +
					homework.getClass());
		}

		HomeworkModelImpl homeworkModelImpl = (HomeworkModelImpl)homework;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(homework);
			}
			else {
				homework = (Homework)session.merge(homework);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			HomeworkImpl.class, homeworkModelImpl, false, true);

		if (isNew) {
			homework.setNew(false);
		}

		homework.resetOriginalValues();

		return homework;
	}

	/**
	 * Returns the homework with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the homework
	 * @return the homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	@Override
	public Homework findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHomeworkException {

		Homework homework = fetchByPrimaryKey(primaryKey);

		if (homework == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHomeworkException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return homework;
	}

	/**
	 * Returns the homework with the primary key or throws a <code>NoSuchHomeworkException</code> if it could not be found.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework
	 * @throws NoSuchHomeworkException if a homework with the primary key could not be found
	 */
	@Override
	public Homework findByPrimaryKey(long homeworkId)
		throws NoSuchHomeworkException {

		return findByPrimaryKey((Serializable)homeworkId);
	}

	/**
	 * Returns the homework with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework, or <code>null</code> if a homework with the primary key could not be found
	 */
	@Override
	public Homework fetchByPrimaryKey(long homeworkId) {
		return fetchByPrimaryKey((Serializable)homeworkId);
	}

	/**
	 * Returns all the homeworks.
	 *
	 * @return the homeworks
	 */
	@Override
	public List<Homework> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of homeworks
	 */
	@Override
	public List<Homework> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of homeworks
	 */
	@Override
	public List<Homework> findAll(
		int start, int end, OrderByComparator<Homework> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of homeworks
	 */
	@Override
	public List<Homework> findAll(
		int start, int end, OrderByComparator<Homework> orderByComparator,
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

		List<Homework> list = null;

		if (useFinderCache) {
			list = (List<Homework>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_HOMEWORK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_HOMEWORK;

				sql = sql.concat(HomeworkModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Homework>)QueryUtil.list(
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
	 * Removes all the homeworks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Homework homework : findAll()) {
			remove(homework);
		}
	}

	/**
	 * Returns the number of homeworks.
	 *
	 * @return the number of homeworks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_HOMEWORK);

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
		return "homeworkId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_HOMEWORK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return HomeworkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the homework persistence.
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

		_finderPathWithPaginationFindBytargetSessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBytargetSessionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"targetSessionId"}, true);

		_finderPathWithoutPaginationFindBytargetSessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBytargetSessionId",
			new String[] {Long.class.getName()},
			new String[] {"targetSessionId"}, true);

		_finderPathCountBytargetSessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBytargetSessionId",
			new String[] {Long.class.getName()},
			new String[] {"targetSessionId"}, false);

		_finderPathWithPaginationFindBysourceSessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysourceSessionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"sourceSessionId"}, true);

		_finderPathWithoutPaginationFindBysourceSessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBysourceSessionId",
			new String[] {Long.class.getName()},
			new String[] {"sourceSessionId"}, true);

		_finderPathCountBysourceSessionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysourceSessionId",
			new String[] {Long.class.getName()},
			new String[] {"sourceSessionId"}, false);

		_finderPathWithPaginationFindBytargetWeekId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBytargetWeekId",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"targetWeekId"}, true);

		_finderPathWithoutPaginationFindBytargetWeekId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBytargetWeekId",
			new String[] {Integer.class.getName()},
			new String[] {"targetWeekId"}, true);

		_finderPathCountBytargetWeekId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBytargetWeekId",
			new String[] {Integer.class.getName()},
			new String[] {"targetWeekId"}, false);

		_setHomeworkUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setHomeworkUtilPersistence(null);

		entityCache.removeCache(HomeworkImpl.class.getName());
	}

	private void _setHomeworkUtilPersistence(
		HomeworkPersistence homeworkPersistence) {

		try {
			Field field = HomeworkUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, homeworkPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_HOMEWORK =
		"SELECT homework FROM Homework homework";

	private static final String _SQL_SELECT_HOMEWORK_WHERE =
		"SELECT homework FROM Homework homework WHERE ";

	private static final String _SQL_COUNT_HOMEWORK =
		"SELECT COUNT(homework) FROM Homework homework";

	private static final String _SQL_COUNT_HOMEWORK_WHERE =
		"SELECT COUNT(homework) FROM Homework homework WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "homework.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Homework exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Homework exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		HomeworkPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private HomeworkModelArgumentsResolver _homeworkModelArgumentsResolver;

}