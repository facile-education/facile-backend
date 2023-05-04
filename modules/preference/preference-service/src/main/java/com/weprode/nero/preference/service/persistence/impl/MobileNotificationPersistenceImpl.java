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

package com.weprode.nero.preference.service.persistence.impl;

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

import com.weprode.nero.preference.exception.NoSuchMobileNotificationException;
import com.weprode.nero.preference.model.MobileNotification;
import com.weprode.nero.preference.model.MobileNotificationTable;
import com.weprode.nero.preference.model.impl.MobileNotificationImpl;
import com.weprode.nero.preference.model.impl.MobileNotificationModelImpl;
import com.weprode.nero.preference.service.persistence.MobileNotificationPersistence;
import com.weprode.nero.preference.service.persistence.MobileNotificationUtil;
import com.weprode.nero.preference.service.persistence.impl.constants.PreferencePersistenceConstants;

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
 * The persistence implementation for the mobile notification service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {MobileNotificationPersistence.class, BasePersistence.class}
)
public class MobileNotificationPersistenceImpl
	extends BasePersistenceImpl<MobileNotification>
	implements MobileNotificationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MobileNotificationUtil</code> to access the mobile notification persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MobileNotificationImpl.class.getName();

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
	 * Returns all the mobile notifications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByuserId(
		long userId, int start, int end) {

		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return findByuserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
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

		List<MobileNotification> list = null;

		if (useFinderCache) {
			list = (List<MobileNotification>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (MobileNotification mobileNotification : list) {
					if (userId != mobileNotification.getUserId()) {
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

			sb.append(_SQL_SELECT_MOBILENOTIFICATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MobileNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<MobileNotification>)QueryUtil.list(
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
	 * Returns the first mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification findByuserId_First(
			long userId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = fetchByuserId_First(
			userId, orderByComparator);

		if (mobileNotification != null) {
			return mobileNotification;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMobileNotificationException(sb.toString());
	}

	/**
	 * Returns the first mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification fetchByuserId_First(
		long userId, OrderByComparator<MobileNotification> orderByComparator) {

		List<MobileNotification> list = findByuserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification findByuserId_Last(
			long userId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = fetchByuserId_Last(
			userId, orderByComparator);

		if (mobileNotification != null) {
			return mobileNotification;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMobileNotificationException(sb.toString());
	}

	/**
	 * Returns the last mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification fetchByuserId_Last(
		long userId, OrderByComparator<MobileNotification> orderByComparator) {

		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<MobileNotification> list = findByuserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the mobile notifications before and after the current mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param mobileNotificationId the primary key of the current mobile notification
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification[] findByuserId_PrevAndNext(
			long mobileNotificationId, long userId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = findByPrimaryKey(
			mobileNotificationId);

		Session session = null;

		try {
			session = openSession();

			MobileNotification[] array = new MobileNotificationImpl[3];

			array[0] = getByuserId_PrevAndNext(
				session, mobileNotification, userId, orderByComparator, true);

			array[1] = mobileNotification;

			array[2] = getByuserId_PrevAndNext(
				session, mobileNotification, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MobileNotification getByuserId_PrevAndNext(
		Session session, MobileNotification mobileNotification, long userId,
		OrderByComparator<MobileNotification> orderByComparator,
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

		sb.append(_SQL_SELECT_MOBILENOTIFICATION_WHERE);

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
			sb.append(MobileNotificationModelImpl.ORDER_BY_JPQL);
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
						mobileNotification)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MobileNotification> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mobile notifications where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (MobileNotification mobileNotification :
				findByuserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(mobileNotification);
		}
	}

	/**
	 * Returns the number of mobile notifications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mobile notifications
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MOBILENOTIFICATION_WHERE);

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
		"mobileNotification.userId = ?";

	private FinderPath _finderPathWithPaginationFindByetabId;
	private FinderPath _finderPathWithoutPaginationFindByetabId;
	private FinderPath _finderPathCountByetabId;

	/**
	 * Returns all the mobile notifications where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @return the matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByetabId(long etabId) {
		return findByetabId(etabId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile notifications where etabId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByetabId(
		long etabId, int start, int end) {

		return findByetabId(etabId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where etabId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByetabId(
		long etabId, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return findByetabId(etabId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where etabId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByetabId(
		long etabId, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByetabId;
				finderArgs = new Object[] {etabId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByetabId;
			finderArgs = new Object[] {etabId, start, end, orderByComparator};
		}

		List<MobileNotification> list = null;

		if (useFinderCache) {
			list = (List<MobileNotification>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (MobileNotification mobileNotification : list) {
					if (etabId != mobileNotification.getEtabId()) {
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

			sb.append(_SQL_SELECT_MOBILENOTIFICATION_WHERE);

			sb.append(_FINDER_COLUMN_ETABID_ETABID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MobileNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(etabId);

				list = (List<MobileNotification>)QueryUtil.list(
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
	 * Returns the first mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification findByetabId_First(
			long etabId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = fetchByetabId_First(
			etabId, orderByComparator);

		if (mobileNotification != null) {
			return mobileNotification;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("etabId=");
		sb.append(etabId);

		sb.append("}");

		throw new NoSuchMobileNotificationException(sb.toString());
	}

	/**
	 * Returns the first mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification fetchByetabId_First(
		long etabId, OrderByComparator<MobileNotification> orderByComparator) {

		List<MobileNotification> list = findByetabId(
			etabId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification findByetabId_Last(
			long etabId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = fetchByetabId_Last(
			etabId, orderByComparator);

		if (mobileNotification != null) {
			return mobileNotification;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("etabId=");
		sb.append(etabId);

		sb.append("}");

		throw new NoSuchMobileNotificationException(sb.toString());
	}

	/**
	 * Returns the last mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification fetchByetabId_Last(
		long etabId, OrderByComparator<MobileNotification> orderByComparator) {

		int count = countByetabId(etabId);

		if (count == 0) {
			return null;
		}

		List<MobileNotification> list = findByetabId(
			etabId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the mobile notifications before and after the current mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param mobileNotificationId the primary key of the current mobile notification
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification[] findByetabId_PrevAndNext(
			long mobileNotificationId, long etabId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = findByPrimaryKey(
			mobileNotificationId);

		Session session = null;

		try {
			session = openSession();

			MobileNotification[] array = new MobileNotificationImpl[3];

			array[0] = getByetabId_PrevAndNext(
				session, mobileNotification, etabId, orderByComparator, true);

			array[1] = mobileNotification;

			array[2] = getByetabId_PrevAndNext(
				session, mobileNotification, etabId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MobileNotification getByetabId_PrevAndNext(
		Session session, MobileNotification mobileNotification, long etabId,
		OrderByComparator<MobileNotification> orderByComparator,
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

		sb.append(_SQL_SELECT_MOBILENOTIFICATION_WHERE);

		sb.append(_FINDER_COLUMN_ETABID_ETABID_2);

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
			sb.append(MobileNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(etabId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						mobileNotification)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MobileNotification> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mobile notifications where etabId = &#63; from the database.
	 *
	 * @param etabId the etab ID
	 */
	@Override
	public void removeByetabId(long etabId) {
		for (MobileNotification mobileNotification :
				findByetabId(
					etabId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(mobileNotification);
		}
	}

	/**
	 * Returns the number of mobile notifications where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @return the number of matching mobile notifications
	 */
	@Override
	public int countByetabId(long etabId) {
		FinderPath finderPath = _finderPathCountByetabId;

		Object[] finderArgs = new Object[] {etabId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MOBILENOTIFICATION_WHERE);

			sb.append(_FINDER_COLUMN_ETABID_ETABID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(etabId);

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

	private static final String _FINDER_COLUMN_ETABID_ETABID_2 =
		"mobileNotification.etabId = ?";

	private FinderPath _finderPathWithPaginationFindByuserId_enable;
	private FinderPath _finderPathWithoutPaginationFindByuserId_enable;
	private FinderPath _finderPathCountByuserId_enable;

	/**
	 * Returns all the mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @return the matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByuserId_enable(
		long userId, boolean enable) {

		return findByuserId_enable(
			userId, enable, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByuserId_enable(
		long userId, boolean enable, int start, int end) {

		return findByuserId_enable(userId, enable, start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByuserId_enable(
		long userId, boolean enable, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return findByuserId_enable(
			userId, enable, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByuserId_enable(
		long userId, boolean enable, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByuserId_enable;
				finderArgs = new Object[] {userId, enable};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByuserId_enable;
			finderArgs = new Object[] {
				userId, enable, start, end, orderByComparator
			};
		}

		List<MobileNotification> list = null;

		if (useFinderCache) {
			list = (List<MobileNotification>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (MobileNotification mobileNotification : list) {
					if ((userId != mobileNotification.getUserId()) ||
						(enable != mobileNotification.isEnable())) {

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

			sb.append(_SQL_SELECT_MOBILENOTIFICATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_ENABLE_USERID_2);

			sb.append(_FINDER_COLUMN_USERID_ENABLE_ENABLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MobileNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(enable);

				list = (List<MobileNotification>)QueryUtil.list(
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
	 * Returns the first mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification findByuserId_enable_First(
			long userId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = fetchByuserId_enable_First(
			userId, enable, orderByComparator);

		if (mobileNotification != null) {
			return mobileNotification;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", enable=");
		sb.append(enable);

		sb.append("}");

		throw new NoSuchMobileNotificationException(sb.toString());
	}

	/**
	 * Returns the first mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification fetchByuserId_enable_First(
		long userId, boolean enable,
		OrderByComparator<MobileNotification> orderByComparator) {

		List<MobileNotification> list = findByuserId_enable(
			userId, enable, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification findByuserId_enable_Last(
			long userId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = fetchByuserId_enable_Last(
			userId, enable, orderByComparator);

		if (mobileNotification != null) {
			return mobileNotification;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", enable=");
		sb.append(enable);

		sb.append("}");

		throw new NoSuchMobileNotificationException(sb.toString());
	}

	/**
	 * Returns the last mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification fetchByuserId_enable_Last(
		long userId, boolean enable,
		OrderByComparator<MobileNotification> orderByComparator) {

		int count = countByuserId_enable(userId, enable);

		if (count == 0) {
			return null;
		}

		List<MobileNotification> list = findByuserId_enable(
			userId, enable, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the mobile notifications before and after the current mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param mobileNotificationId the primary key of the current mobile notification
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification[] findByuserId_enable_PrevAndNext(
			long mobileNotificationId, long userId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = findByPrimaryKey(
			mobileNotificationId);

		Session session = null;

		try {
			session = openSession();

			MobileNotification[] array = new MobileNotificationImpl[3];

			array[0] = getByuserId_enable_PrevAndNext(
				session, mobileNotification, userId, enable, orderByComparator,
				true);

			array[1] = mobileNotification;

			array[2] = getByuserId_enable_PrevAndNext(
				session, mobileNotification, userId, enable, orderByComparator,
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

	protected MobileNotification getByuserId_enable_PrevAndNext(
		Session session, MobileNotification mobileNotification, long userId,
		boolean enable, OrderByComparator<MobileNotification> orderByComparator,
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

		sb.append(_SQL_SELECT_MOBILENOTIFICATION_WHERE);

		sb.append(_FINDER_COLUMN_USERID_ENABLE_USERID_2);

		sb.append(_FINDER_COLUMN_USERID_ENABLE_ENABLE_2);

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
			sb.append(MobileNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(enable);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						mobileNotification)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MobileNotification> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mobile notifications where userId = &#63; and enable = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 */
	@Override
	public void removeByuserId_enable(long userId, boolean enable) {
		for (MobileNotification mobileNotification :
				findByuserId_enable(
					userId, enable, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(mobileNotification);
		}
	}

	/**
	 * Returns the number of mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @return the number of matching mobile notifications
	 */
	@Override
	public int countByuserId_enable(long userId, boolean enable) {
		FinderPath finderPath = _finderPathCountByuserId_enable;

		Object[] finderArgs = new Object[] {userId, enable};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MOBILENOTIFICATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_ENABLE_USERID_2);

			sb.append(_FINDER_COLUMN_USERID_ENABLE_ENABLE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(enable);

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

	private static final String _FINDER_COLUMN_USERID_ENABLE_USERID_2 =
		"mobileNotification.userId = ? AND ";

	private static final String _FINDER_COLUMN_USERID_ENABLE_ENABLE_2 =
		"mobileNotification.enable = ?";

	private FinderPath _finderPathWithPaginationFindByetabId_enable;
	private FinderPath _finderPathWithoutPaginationFindByetabId_enable;
	private FinderPath _finderPathCountByetabId_enable;

	/**
	 * Returns all the mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @return the matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByetabId_enable(
		long etabId, boolean enable) {

		return findByetabId_enable(
			etabId, enable, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByetabId_enable(
		long etabId, boolean enable, int start, int end) {

		return findByetabId_enable(etabId, enable, start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByetabId_enable(
		long etabId, boolean enable, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return findByetabId_enable(
			etabId, enable, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile notifications
	 */
	@Override
	public List<MobileNotification> findByetabId_enable(
		long etabId, boolean enable, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByetabId_enable;
				finderArgs = new Object[] {etabId, enable};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByetabId_enable;
			finderArgs = new Object[] {
				etabId, enable, start, end, orderByComparator
			};
		}

		List<MobileNotification> list = null;

		if (useFinderCache) {
			list = (List<MobileNotification>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (MobileNotification mobileNotification : list) {
					if ((etabId != mobileNotification.getEtabId()) ||
						(enable != mobileNotification.isEnable())) {

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

			sb.append(_SQL_SELECT_MOBILENOTIFICATION_WHERE);

			sb.append(_FINDER_COLUMN_ETABID_ENABLE_ETABID_2);

			sb.append(_FINDER_COLUMN_ETABID_ENABLE_ENABLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MobileNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(etabId);

				queryPos.add(enable);

				list = (List<MobileNotification>)QueryUtil.list(
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
	 * Returns the first mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification findByetabId_enable_First(
			long etabId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = fetchByetabId_enable_First(
			etabId, enable, orderByComparator);

		if (mobileNotification != null) {
			return mobileNotification;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("etabId=");
		sb.append(etabId);

		sb.append(", enable=");
		sb.append(enable);

		sb.append("}");

		throw new NoSuchMobileNotificationException(sb.toString());
	}

	/**
	 * Returns the first mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification fetchByetabId_enable_First(
		long etabId, boolean enable,
		OrderByComparator<MobileNotification> orderByComparator) {

		List<MobileNotification> list = findByetabId_enable(
			etabId, enable, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification findByetabId_enable_Last(
			long etabId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = fetchByetabId_enable_Last(
			etabId, enable, orderByComparator);

		if (mobileNotification != null) {
			return mobileNotification;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("etabId=");
		sb.append(etabId);

		sb.append(", enable=");
		sb.append(enable);

		sb.append("}");

		throw new NoSuchMobileNotificationException(sb.toString());
	}

	/**
	 * Returns the last mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	@Override
	public MobileNotification fetchByetabId_enable_Last(
		long etabId, boolean enable,
		OrderByComparator<MobileNotification> orderByComparator) {

		int count = countByetabId_enable(etabId, enable);

		if (count == 0) {
			return null;
		}

		List<MobileNotification> list = findByetabId_enable(
			etabId, enable, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the mobile notifications before and after the current mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param mobileNotificationId the primary key of the current mobile notification
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification[] findByetabId_enable_PrevAndNext(
			long mobileNotificationId, long etabId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = findByPrimaryKey(
			mobileNotificationId);

		Session session = null;

		try {
			session = openSession();

			MobileNotification[] array = new MobileNotificationImpl[3];

			array[0] = getByetabId_enable_PrevAndNext(
				session, mobileNotification, etabId, enable, orderByComparator,
				true);

			array[1] = mobileNotification;

			array[2] = getByetabId_enable_PrevAndNext(
				session, mobileNotification, etabId, enable, orderByComparator,
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

	protected MobileNotification getByetabId_enable_PrevAndNext(
		Session session, MobileNotification mobileNotification, long etabId,
		boolean enable, OrderByComparator<MobileNotification> orderByComparator,
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

		sb.append(_SQL_SELECT_MOBILENOTIFICATION_WHERE);

		sb.append(_FINDER_COLUMN_ETABID_ENABLE_ETABID_2);

		sb.append(_FINDER_COLUMN_ETABID_ENABLE_ENABLE_2);

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
			sb.append(MobileNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(etabId);

		queryPos.add(enable);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						mobileNotification)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MobileNotification> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mobile notifications where etabId = &#63; and enable = &#63; from the database.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 */
	@Override
	public void removeByetabId_enable(long etabId, boolean enable) {
		for (MobileNotification mobileNotification :
				findByetabId_enable(
					etabId, enable, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(mobileNotification);
		}
	}

	/**
	 * Returns the number of mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @return the number of matching mobile notifications
	 */
	@Override
	public int countByetabId_enable(long etabId, boolean enable) {
		FinderPath finderPath = _finderPathCountByetabId_enable;

		Object[] finderArgs = new Object[] {etabId, enable};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MOBILENOTIFICATION_WHERE);

			sb.append(_FINDER_COLUMN_ETABID_ENABLE_ETABID_2);

			sb.append(_FINDER_COLUMN_ETABID_ENABLE_ENABLE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(etabId);

				queryPos.add(enable);

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

	private static final String _FINDER_COLUMN_ETABID_ENABLE_ETABID_2 =
		"mobileNotification.etabId = ? AND ";

	private static final String _FINDER_COLUMN_ETABID_ENABLE_ENABLE_2 =
		"mobileNotification.enable = ?";

	public MobileNotificationPersistenceImpl() {
		setModelClass(MobileNotification.class);

		setModelImplClass(MobileNotificationImpl.class);
		setModelPKClass(long.class);

		setTable(MobileNotificationTable.INSTANCE);
	}

	/**
	 * Caches the mobile notification in the entity cache if it is enabled.
	 *
	 * @param mobileNotification the mobile notification
	 */
	@Override
	public void cacheResult(MobileNotification mobileNotification) {
		entityCache.putResult(
			MobileNotificationImpl.class, mobileNotification.getPrimaryKey(),
			mobileNotification);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the mobile notifications in the entity cache if it is enabled.
	 *
	 * @param mobileNotifications the mobile notifications
	 */
	@Override
	public void cacheResult(List<MobileNotification> mobileNotifications) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (mobileNotifications.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MobileNotification mobileNotification : mobileNotifications) {
			if (entityCache.getResult(
					MobileNotificationImpl.class,
					mobileNotification.getPrimaryKey()) == null) {

				cacheResult(mobileNotification);
			}
		}
	}

	/**
	 * Clears the cache for all mobile notifications.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MobileNotificationImpl.class);

		finderCache.clearCache(MobileNotificationImpl.class);
	}

	/**
	 * Clears the cache for the mobile notification.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MobileNotification mobileNotification) {
		entityCache.removeResult(
			MobileNotificationImpl.class, mobileNotification);
	}

	@Override
	public void clearCache(List<MobileNotification> mobileNotifications) {
		for (MobileNotification mobileNotification : mobileNotifications) {
			entityCache.removeResult(
				MobileNotificationImpl.class, mobileNotification);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MobileNotificationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MobileNotificationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new mobile notification with the primary key. Does not add the mobile notification to the database.
	 *
	 * @param mobileNotificationId the primary key for the new mobile notification
	 * @return the new mobile notification
	 */
	@Override
	public MobileNotification create(long mobileNotificationId) {
		MobileNotification mobileNotification = new MobileNotificationImpl();

		mobileNotification.setNew(true);
		mobileNotification.setPrimaryKey(mobileNotificationId);

		return mobileNotification;
	}

	/**
	 * Removes the mobile notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification that was removed
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification remove(long mobileNotificationId)
		throws NoSuchMobileNotificationException {

		return remove((Serializable)mobileNotificationId);
	}

	/**
	 * Removes the mobile notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mobile notification
	 * @return the mobile notification that was removed
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification remove(Serializable primaryKey)
		throws NoSuchMobileNotificationException {

		Session session = null;

		try {
			session = openSession();

			MobileNotification mobileNotification =
				(MobileNotification)session.get(
					MobileNotificationImpl.class, primaryKey);

			if (mobileNotification == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMobileNotificationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(mobileNotification);
		}
		catch (NoSuchMobileNotificationException noSuchEntityException) {
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
	protected MobileNotification removeImpl(
		MobileNotification mobileNotification) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mobileNotification)) {
				mobileNotification = (MobileNotification)session.get(
					MobileNotificationImpl.class,
					mobileNotification.getPrimaryKeyObj());
			}

			if (mobileNotification != null) {
				session.delete(mobileNotification);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (mobileNotification != null) {
			clearCache(mobileNotification);
		}

		return mobileNotification;
	}

	@Override
	public MobileNotification updateImpl(
		MobileNotification mobileNotification) {

		boolean isNew = mobileNotification.isNew();

		if (!(mobileNotification instanceof MobileNotificationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(mobileNotification.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					mobileNotification);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in mobileNotification proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MobileNotification implementation " +
					mobileNotification.getClass());
		}

		MobileNotificationModelImpl mobileNotificationModelImpl =
			(MobileNotificationModelImpl)mobileNotification;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(mobileNotification);
			}
			else {
				mobileNotification = (MobileNotification)session.merge(
					mobileNotification);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MobileNotificationImpl.class, mobileNotificationModelImpl, false,
			true);

		if (isNew) {
			mobileNotification.setNew(false);
		}

		mobileNotification.resetOriginalValues();

		return mobileNotification;
	}

	/**
	 * Returns the mobile notification with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mobile notification
	 * @return the mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMobileNotificationException {

		MobileNotification mobileNotification = fetchByPrimaryKey(primaryKey);

		if (mobileNotification == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMobileNotificationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return mobileNotification;
	}

	/**
	 * Returns the mobile notification with the primary key or throws a <code>NoSuchMobileNotificationException</code> if it could not be found.
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification findByPrimaryKey(long mobileNotificationId)
		throws NoSuchMobileNotificationException {

		return findByPrimaryKey((Serializable)mobileNotificationId);
	}

	/**
	 * Returns the mobile notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification, or <code>null</code> if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification fetchByPrimaryKey(long mobileNotificationId) {
		return fetchByPrimaryKey((Serializable)mobileNotificationId);
	}

	/**
	 * Returns all the mobile notifications.
	 *
	 * @return the mobile notifications
	 */
	@Override
	public List<MobileNotification> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of mobile notifications
	 */
	@Override
	public List<MobileNotification> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of mobile notifications
	 */
	@Override
	public List<MobileNotification> findAll(
		int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mobile notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of mobile notifications
	 */
	@Override
	public List<MobileNotification> findAll(
		int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
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

		List<MobileNotification> list = null;

		if (useFinderCache) {
			list = (List<MobileNotification>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MOBILENOTIFICATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MOBILENOTIFICATION;

				sql = sql.concat(MobileNotificationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MobileNotification>)QueryUtil.list(
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
	 * Removes all the mobile notifications from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MobileNotification mobileNotification : findAll()) {
			remove(mobileNotification);
		}
	}

	/**
	 * Returns the number of mobile notifications.
	 *
	 * @return the number of mobile notifications
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_MOBILENOTIFICATION);

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
		return "mobileNotificationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MOBILENOTIFICATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MobileNotificationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the mobile notification persistence.
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

		_finderPathWithPaginationFindByetabId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByetabId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"etabId"}, true);

		_finderPathWithoutPaginationFindByetabId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByetabId",
			new String[] {Long.class.getName()}, new String[] {"etabId"}, true);

		_finderPathCountByetabId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByetabId",
			new String[] {Long.class.getName()}, new String[] {"etabId"},
			false);

		_finderPathWithPaginationFindByuserId_enable = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId_enable",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"userId", "enable"}, true);

		_finderPathWithoutPaginationFindByuserId_enable = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId_enable",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"userId", "enable"}, true);

		_finderPathCountByuserId_enable = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId_enable",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"userId", "enable"}, false);

		_finderPathWithPaginationFindByetabId_enable = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByetabId_enable",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"etabId", "enable"}, true);

		_finderPathWithoutPaginationFindByetabId_enable = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByetabId_enable",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"etabId", "enable"}, true);

		_finderPathCountByetabId_enable = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByetabId_enable",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"etabId", "enable"}, false);

		_setMobileNotificationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setMobileNotificationUtilPersistence(null);

		entityCache.removeCache(MobileNotificationImpl.class.getName());
	}

	private void _setMobileNotificationUtilPersistence(
		MobileNotificationPersistence mobileNotificationPersistence) {

		try {
			Field field = MobileNotificationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, mobileNotificationPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = PreferencePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = PreferencePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = PreferencePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MOBILENOTIFICATION =
		"SELECT mobileNotification FROM MobileNotification mobileNotification";

	private static final String _SQL_SELECT_MOBILENOTIFICATION_WHERE =
		"SELECT mobileNotification FROM MobileNotification mobileNotification WHERE ";

	private static final String _SQL_COUNT_MOBILENOTIFICATION =
		"SELECT COUNT(mobileNotification) FROM MobileNotification mobileNotification";

	private static final String _SQL_COUNT_MOBILENOTIFICATION_WHERE =
		"SELECT COUNT(mobileNotification) FROM MobileNotification mobileNotification WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "mobileNotification.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MobileNotification exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MobileNotification exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MobileNotificationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private MobileNotificationModelArgumentsResolver
		_mobileNotificationModelArgumentsResolver;

}