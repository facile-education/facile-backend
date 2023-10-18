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

package com.weprode.nero.document.service.persistence.impl;

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

import com.weprode.nero.document.exception.NoSuchActivityException;
import com.weprode.nero.document.model.Activity;
import com.weprode.nero.document.model.ActivityTable;
import com.weprode.nero.document.model.impl.ActivityImpl;
import com.weprode.nero.document.model.impl.ActivityModelImpl;
import com.weprode.nero.document.service.persistence.ActivityPersistence;
import com.weprode.nero.document.service.persistence.ActivityUtil;
import com.weprode.nero.document.service.persistence.impl.constants.DocumentPersistenceConstants;

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
 * The persistence implementation for the activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ActivityPersistence.class, BasePersistence.class})
public class ActivityPersistenceImpl
	extends BasePersistenceImpl<Activity> implements ActivityPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ActivityUtil</code> to access the activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ActivityImpl.class.getName();

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
	 * Returns all the activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching activities
	 */
	@Override
	public List<Activity> findBygroupId(long groupId) {
		return findBygroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @return the range of matching activities
	 */
	@Override
	public List<Activity> findBygroupId(long groupId, int start, int end) {
		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activities
	 */
	@Override
	public List<Activity> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<Activity> orderByComparator) {

		return findBygroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching activities
	 */
	@Override
	public List<Activity> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<Activity> orderByComparator, boolean useFinderCache) {

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

		List<Activity> list = null;

		if (useFinderCache) {
			list = (List<Activity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Activity activity : list) {
					if (groupId != activity.getGroupId()) {
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

			sb.append(_SQL_SELECT_ACTIVITY_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<Activity>)QueryUtil.list(
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
	 * Returns the first activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity
	 * @throws NoSuchActivityException if a matching activity could not be found
	 */
	@Override
	public Activity findBygroupId_First(
			long groupId, OrderByComparator<Activity> orderByComparator)
		throws NoSuchActivityException {

		Activity activity = fetchBygroupId_First(groupId, orderByComparator);

		if (activity != null) {
			return activity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchActivityException(sb.toString());
	}

	/**
	 * Returns the first activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity, or <code>null</code> if a matching activity could not be found
	 */
	@Override
	public Activity fetchBygroupId_First(
		long groupId, OrderByComparator<Activity> orderByComparator) {

		List<Activity> list = findBygroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity
	 * @throws NoSuchActivityException if a matching activity could not be found
	 */
	@Override
	public Activity findBygroupId_Last(
			long groupId, OrderByComparator<Activity> orderByComparator)
		throws NoSuchActivityException {

		Activity activity = fetchBygroupId_Last(groupId, orderByComparator);

		if (activity != null) {
			return activity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchActivityException(sb.toString());
	}

	/**
	 * Returns the last activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity, or <code>null</code> if a matching activity could not be found
	 */
	@Override
	public Activity fetchBygroupId_Last(
		long groupId, OrderByComparator<Activity> orderByComparator) {

		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Activity> list = findBygroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activities before and after the current activity in the ordered set where groupId = &#63;.
	 *
	 * @param activityId the primary key of the current activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity
	 * @throws NoSuchActivityException if a activity with the primary key could not be found
	 */
	@Override
	public Activity[] findBygroupId_PrevAndNext(
			long activityId, long groupId,
			OrderByComparator<Activity> orderByComparator)
		throws NoSuchActivityException {

		Activity activity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			Activity[] array = new ActivityImpl[3];

			array[0] = getBygroupId_PrevAndNext(
				session, activity, groupId, orderByComparator, true);

			array[1] = activity;

			array[2] = getBygroupId_PrevAndNext(
				session, activity, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Activity getBygroupId_PrevAndNext(
		Session session, Activity activity, long groupId,
		OrderByComparator<Activity> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACTIVITY_WHERE);

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
			sb.append(ActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(activity)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Activity> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activities where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupId(long groupId) {
		for (Activity activity :
				findBygroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(activity);
		}
	}

	/**
	 * Returns the number of activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching activities
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = _finderPathCountBygroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACTIVITY_WHERE);

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
		"activity.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByfileEntryId;
	private FinderPath _finderPathWithoutPaginationFindByfileEntryId;
	private FinderPath _finderPathCountByfileEntryId;

	/**
	 * Returns all the activities where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching activities
	 */
	@Override
	public List<Activity> findByfileEntryId(long fileEntryId) {
		return findByfileEntryId(
			fileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activities where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @return the range of matching activities
	 */
	@Override
	public List<Activity> findByfileEntryId(
		long fileEntryId, int start, int end) {

		return findByfileEntryId(fileEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activities where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activities
	 */
	@Override
	public List<Activity> findByfileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<Activity> orderByComparator) {

		return findByfileEntryId(
			fileEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activities where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching activities
	 */
	@Override
	public List<Activity> findByfileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<Activity> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByfileEntryId;
				finderArgs = new Object[] {fileEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByfileEntryId;
			finderArgs = new Object[] {
				fileEntryId, start, end, orderByComparator
			};
		}

		List<Activity> list = null;

		if (useFinderCache) {
			list = (List<Activity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Activity activity : list) {
					if (fileEntryId != activity.getFileEntryId()) {
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

			sb.append(_SQL_SELECT_ACTIVITY_WHERE);

			sb.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				list = (List<Activity>)QueryUtil.list(
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
	 * Returns the first activity in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity
	 * @throws NoSuchActivityException if a matching activity could not be found
	 */
	@Override
	public Activity findByfileEntryId_First(
			long fileEntryId, OrderByComparator<Activity> orderByComparator)
		throws NoSuchActivityException {

		Activity activity = fetchByfileEntryId_First(
			fileEntryId, orderByComparator);

		if (activity != null) {
			return activity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append("}");

		throw new NoSuchActivityException(sb.toString());
	}

	/**
	 * Returns the first activity in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity, or <code>null</code> if a matching activity could not be found
	 */
	@Override
	public Activity fetchByfileEntryId_First(
		long fileEntryId, OrderByComparator<Activity> orderByComparator) {

		List<Activity> list = findByfileEntryId(
			fileEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity
	 * @throws NoSuchActivityException if a matching activity could not be found
	 */
	@Override
	public Activity findByfileEntryId_Last(
			long fileEntryId, OrderByComparator<Activity> orderByComparator)
		throws NoSuchActivityException {

		Activity activity = fetchByfileEntryId_Last(
			fileEntryId, orderByComparator);

		if (activity != null) {
			return activity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append("}");

		throw new NoSuchActivityException(sb.toString());
	}

	/**
	 * Returns the last activity in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity, or <code>null</code> if a matching activity could not be found
	 */
	@Override
	public Activity fetchByfileEntryId_Last(
		long fileEntryId, OrderByComparator<Activity> orderByComparator) {

		int count = countByfileEntryId(fileEntryId);

		if (count == 0) {
			return null;
		}

		List<Activity> list = findByfileEntryId(
			fileEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activities before and after the current activity in the ordered set where fileEntryId = &#63;.
	 *
	 * @param activityId the primary key of the current activity
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity
	 * @throws NoSuchActivityException if a activity with the primary key could not be found
	 */
	@Override
	public Activity[] findByfileEntryId_PrevAndNext(
			long activityId, long fileEntryId,
			OrderByComparator<Activity> orderByComparator)
		throws NoSuchActivityException {

		Activity activity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			Activity[] array = new ActivityImpl[3];

			array[0] = getByfileEntryId_PrevAndNext(
				session, activity, fileEntryId, orderByComparator, true);

			array[1] = activity;

			array[2] = getByfileEntryId_PrevAndNext(
				session, activity, fileEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Activity getByfileEntryId_PrevAndNext(
		Session session, Activity activity, long fileEntryId,
		OrderByComparator<Activity> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACTIVITY_WHERE);

		sb.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

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
			sb.append(ActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(fileEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(activity)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Activity> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activities where fileEntryId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 */
	@Override
	public void removeByfileEntryId(long fileEntryId) {
		for (Activity activity :
				findByfileEntryId(
					fileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(activity);
		}
	}

	/**
	 * Returns the number of activities where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the number of matching activities
	 */
	@Override
	public int countByfileEntryId(long fileEntryId) {
		FinderPath finderPath = _finderPathCountByfileEntryId;

		Object[] finderArgs = new Object[] {fileEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACTIVITY_WHERE);

			sb.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

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

	private static final String _FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2 =
		"activity.fileEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByfolderId;
	private FinderPath _finderPathWithoutPaginationFindByfolderId;
	private FinderPath _finderPathCountByfolderId;

	/**
	 * Returns all the activities where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @return the matching activities
	 */
	@Override
	public List<Activity> findByfolderId(long folderId) {
		return findByfolderId(
			folderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activities where folderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @return the range of matching activities
	 */
	@Override
	public List<Activity> findByfolderId(long folderId, int start, int end) {
		return findByfolderId(folderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activities where folderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activities
	 */
	@Override
	public List<Activity> findByfolderId(
		long folderId, int start, int end,
		OrderByComparator<Activity> orderByComparator) {

		return findByfolderId(folderId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activities where folderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching activities
	 */
	@Override
	public List<Activity> findByfolderId(
		long folderId, int start, int end,
		OrderByComparator<Activity> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByfolderId;
				finderArgs = new Object[] {folderId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByfolderId;
			finderArgs = new Object[] {folderId, start, end, orderByComparator};
		}

		List<Activity> list = null;

		if (useFinderCache) {
			list = (List<Activity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Activity activity : list) {
					if (folderId != activity.getFolderId()) {
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

			sb.append(_SQL_SELECT_ACTIVITY_WHERE);

			sb.append(_FINDER_COLUMN_FOLDERID_FOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(folderId);

				list = (List<Activity>)QueryUtil.list(
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
	 * Returns the first activity in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity
	 * @throws NoSuchActivityException if a matching activity could not be found
	 */
	@Override
	public Activity findByfolderId_First(
			long folderId, OrderByComparator<Activity> orderByComparator)
		throws NoSuchActivityException {

		Activity activity = fetchByfolderId_First(folderId, orderByComparator);

		if (activity != null) {
			return activity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("folderId=");
		sb.append(folderId);

		sb.append("}");

		throw new NoSuchActivityException(sb.toString());
	}

	/**
	 * Returns the first activity in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity, or <code>null</code> if a matching activity could not be found
	 */
	@Override
	public Activity fetchByfolderId_First(
		long folderId, OrderByComparator<Activity> orderByComparator) {

		List<Activity> list = findByfolderId(folderId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity
	 * @throws NoSuchActivityException if a matching activity could not be found
	 */
	@Override
	public Activity findByfolderId_Last(
			long folderId, OrderByComparator<Activity> orderByComparator)
		throws NoSuchActivityException {

		Activity activity = fetchByfolderId_Last(folderId, orderByComparator);

		if (activity != null) {
			return activity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("folderId=");
		sb.append(folderId);

		sb.append("}");

		throw new NoSuchActivityException(sb.toString());
	}

	/**
	 * Returns the last activity in the ordered set where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity, or <code>null</code> if a matching activity could not be found
	 */
	@Override
	public Activity fetchByfolderId_Last(
		long folderId, OrderByComparator<Activity> orderByComparator) {

		int count = countByfolderId(folderId);

		if (count == 0) {
			return null;
		}

		List<Activity> list = findByfolderId(
			folderId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activities before and after the current activity in the ordered set where folderId = &#63;.
	 *
	 * @param activityId the primary key of the current activity
	 * @param folderId the folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity
	 * @throws NoSuchActivityException if a activity with the primary key could not be found
	 */
	@Override
	public Activity[] findByfolderId_PrevAndNext(
			long activityId, long folderId,
			OrderByComparator<Activity> orderByComparator)
		throws NoSuchActivityException {

		Activity activity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			Activity[] array = new ActivityImpl[3];

			array[0] = getByfolderId_PrevAndNext(
				session, activity, folderId, orderByComparator, true);

			array[1] = activity;

			array[2] = getByfolderId_PrevAndNext(
				session, activity, folderId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Activity getByfolderId_PrevAndNext(
		Session session, Activity activity, long folderId,
		OrderByComparator<Activity> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACTIVITY_WHERE);

		sb.append(_FINDER_COLUMN_FOLDERID_FOLDERID_2);

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
			sb.append(ActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(folderId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(activity)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Activity> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activities where folderId = &#63; from the database.
	 *
	 * @param folderId the folder ID
	 */
	@Override
	public void removeByfolderId(long folderId) {
		for (Activity activity :
				findByfolderId(
					folderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(activity);
		}
	}

	/**
	 * Returns the number of activities where folderId = &#63;.
	 *
	 * @param folderId the folder ID
	 * @return the number of matching activities
	 */
	@Override
	public int countByfolderId(long folderId) {
		FinderPath finderPath = _finderPathCountByfolderId;

		Object[] finderArgs = new Object[] {folderId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACTIVITY_WHERE);

			sb.append(_FINDER_COLUMN_FOLDERID_FOLDERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(folderId);

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

	private static final String _FINDER_COLUMN_FOLDERID_FOLDERID_2 =
		"activity.folderId = ?";

	public ActivityPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Activity.class);

		setModelImplClass(ActivityImpl.class);
		setModelPKClass(long.class);

		setTable(ActivityTable.INSTANCE);
	}

	/**
	 * Caches the activity in the entity cache if it is enabled.
	 *
	 * @param activity the activity
	 */
	@Override
	public void cacheResult(Activity activity) {
		entityCache.putResult(
			ActivityImpl.class, activity.getPrimaryKey(), activity);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the activities in the entity cache if it is enabled.
	 *
	 * @param activities the activities
	 */
	@Override
	public void cacheResult(List<Activity> activities) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (activities.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Activity activity : activities) {
			if (entityCache.getResult(
					ActivityImpl.class, activity.getPrimaryKey()) == null) {

				cacheResult(activity);
			}
		}
	}

	/**
	 * Clears the cache for all activities.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ActivityImpl.class);

		finderCache.clearCache(ActivityImpl.class);
	}

	/**
	 * Clears the cache for the activity.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Activity activity) {
		entityCache.removeResult(ActivityImpl.class, activity);
	}

	@Override
	public void clearCache(List<Activity> activities) {
		for (Activity activity : activities) {
			entityCache.removeResult(ActivityImpl.class, activity);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ActivityImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ActivityImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new activity with the primary key. Does not add the activity to the database.
	 *
	 * @param activityId the primary key for the new activity
	 * @return the new activity
	 */
	@Override
	public Activity create(long activityId) {
		Activity activity = new ActivityImpl();

		activity.setNew(true);
		activity.setPrimaryKey(activityId);

		return activity;
	}

	/**
	 * Removes the activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityId the primary key of the activity
	 * @return the activity that was removed
	 * @throws NoSuchActivityException if a activity with the primary key could not be found
	 */
	@Override
	public Activity remove(long activityId) throws NoSuchActivityException {
		return remove((Serializable)activityId);
	}

	/**
	 * Removes the activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the activity
	 * @return the activity that was removed
	 * @throws NoSuchActivityException if a activity with the primary key could not be found
	 */
	@Override
	public Activity remove(Serializable primaryKey)
		throws NoSuchActivityException {

		Session session = null;

		try {
			session = openSession();

			Activity activity = (Activity)session.get(
				ActivityImpl.class, primaryKey);

			if (activity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivityException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(activity);
		}
		catch (NoSuchActivityException noSuchEntityException) {
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
	protected Activity removeImpl(Activity activity) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(activity)) {
				activity = (Activity)session.get(
					ActivityImpl.class, activity.getPrimaryKeyObj());
			}

			if (activity != null) {
				session.delete(activity);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (activity != null) {
			clearCache(activity);
		}

		return activity;
	}

	@Override
	public Activity updateImpl(Activity activity) {
		boolean isNew = activity.isNew();

		if (!(activity instanceof ActivityModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(activity.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(activity);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in activity proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Activity implementation " +
					activity.getClass());
		}

		ActivityModelImpl activityModelImpl = (ActivityModelImpl)activity;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(activity);
			}
			else {
				activity = (Activity)session.merge(activity);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ActivityImpl.class, activityModelImpl, false, true);

		if (isNew) {
			activity.setNew(false);
		}

		activity.resetOriginalValues();

		return activity;
	}

	/**
	 * Returns the activity with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the activity
	 * @return the activity
	 * @throws NoSuchActivityException if a activity with the primary key could not be found
	 */
	@Override
	public Activity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActivityException {

		Activity activity = fetchByPrimaryKey(primaryKey);

		if (activity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActivityException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return activity;
	}

	/**
	 * Returns the activity with the primary key or throws a <code>NoSuchActivityException</code> if it could not be found.
	 *
	 * @param activityId the primary key of the activity
	 * @return the activity
	 * @throws NoSuchActivityException if a activity with the primary key could not be found
	 */
	@Override
	public Activity findByPrimaryKey(long activityId)
		throws NoSuchActivityException {

		return findByPrimaryKey((Serializable)activityId);
	}

	/**
	 * Returns the activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activityId the primary key of the activity
	 * @return the activity, or <code>null</code> if a activity with the primary key could not be found
	 */
	@Override
	public Activity fetchByPrimaryKey(long activityId) {
		return fetchByPrimaryKey((Serializable)activityId);
	}

	/**
	 * Returns all the activities.
	 *
	 * @return the activities
	 */
	@Override
	public List<Activity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @return the range of activities
	 */
	@Override
	public List<Activity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of activities
	 */
	@Override
	public List<Activity> findAll(
		int start, int end, OrderByComparator<Activity> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of activities
	 */
	@Override
	public List<Activity> findAll(
		int start, int end, OrderByComparator<Activity> orderByComparator,
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

		List<Activity> list = null;

		if (useFinderCache) {
			list = (List<Activity>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ACTIVITY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ACTIVITY;

				sql = sql.concat(ActivityModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Activity>)QueryUtil.list(
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
	 * Removes all the activities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Activity activity : findAll()) {
			remove(activity);
		}
	}

	/**
	 * Returns the number of activities.
	 *
	 * @return the number of activities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ACTIVITY);

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
		return "activityId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ACTIVITY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ActivityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the activity persistence.
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

		_finderPathWithPaginationFindByfileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByfileEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"fileEntryId"}, true);

		_finderPathWithoutPaginationFindByfileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByfileEntryId",
			new String[] {Long.class.getName()}, new String[] {"fileEntryId"},
			true);

		_finderPathCountByfileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByfileEntryId",
			new String[] {Long.class.getName()}, new String[] {"fileEntryId"},
			false);

		_finderPathWithPaginationFindByfolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByfolderId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"folderId"}, true);

		_finderPathWithoutPaginationFindByfolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByfolderId",
			new String[] {Long.class.getName()}, new String[] {"folderId"},
			true);

		_finderPathCountByfolderId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByfolderId",
			new String[] {Long.class.getName()}, new String[] {"folderId"},
			false);

		_setActivityUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setActivityUtilPersistence(null);

		entityCache.removeCache(ActivityImpl.class.getName());
	}

	private void _setActivityUtilPersistence(
		ActivityPersistence activityPersistence) {

		try {
			Field field = ActivityUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, activityPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = DocumentPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = DocumentPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = DocumentPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ACTIVITY =
		"SELECT activity FROM Activity activity";

	private static final String _SQL_SELECT_ACTIVITY_WHERE =
		"SELECT activity FROM Activity activity WHERE ";

	private static final String _SQL_COUNT_ACTIVITY =
		"SELECT COUNT(activity) FROM Activity activity";

	private static final String _SQL_COUNT_ACTIVITY_WHERE =
		"SELECT COUNT(activity) FROM Activity activity WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "activity.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Activity exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Activity exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ActivityPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}