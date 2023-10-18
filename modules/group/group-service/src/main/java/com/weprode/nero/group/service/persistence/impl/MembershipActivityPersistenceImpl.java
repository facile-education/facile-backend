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

package com.weprode.nero.group.service.persistence.impl;

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

import com.weprode.nero.group.exception.NoSuchMembershipActivityException;
import com.weprode.nero.group.model.MembershipActivity;
import com.weprode.nero.group.model.MembershipActivityTable;
import com.weprode.nero.group.model.impl.MembershipActivityImpl;
import com.weprode.nero.group.model.impl.MembershipActivityModelImpl;
import com.weprode.nero.group.service.persistence.MembershipActivityPersistence;
import com.weprode.nero.group.service.persistence.MembershipActivityUtil;
import com.weprode.nero.group.service.persistence.impl.constants.GroupPersistenceConstants;

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
 * The persistence implementation for the membership activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {MembershipActivityPersistence.class, BasePersistence.class}
)
public class MembershipActivityPersistenceImpl
	extends BasePersistenceImpl<MembershipActivity>
	implements MembershipActivityPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MembershipActivityUtil</code> to access the membership activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MembershipActivityImpl.class.getName();

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
	 * Returns all the membership activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching membership activities
	 */
	@Override
	public List<MembershipActivity> findBygroupId(long groupId) {
		return findBygroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @return the range of matching membership activities
	 */
	@Override
	public List<MembershipActivity> findBygroupId(
		long groupId, int start, int end) {

		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership activities
	 */
	@Override
	public List<MembershipActivity> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<MembershipActivity> orderByComparator) {

		return findBygroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the membership activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching membership activities
	 */
	@Override
	public List<MembershipActivity> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<MembershipActivity> orderByComparator,
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

		List<MembershipActivity> list = null;

		if (useFinderCache) {
			list = (List<MembershipActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MembershipActivity membershipActivity : list) {
					if (groupId != membershipActivity.getGroupId()) {
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

			sb.append(_SQL_SELECT_MEMBERSHIPACTIVITY_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MembershipActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<MembershipActivity>)QueryUtil.list(
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
	 * Returns the first membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership activity
	 * @throws NoSuchMembershipActivityException if a matching membership activity could not be found
	 */
	@Override
	public MembershipActivity findBygroupId_First(
			long groupId,
			OrderByComparator<MembershipActivity> orderByComparator)
		throws NoSuchMembershipActivityException {

		MembershipActivity membershipActivity = fetchBygroupId_First(
			groupId, orderByComparator);

		if (membershipActivity != null) {
			return membershipActivity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchMembershipActivityException(sb.toString());
	}

	/**
	 * Returns the first membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership activity, or <code>null</code> if a matching membership activity could not be found
	 */
	@Override
	public MembershipActivity fetchBygroupId_First(
		long groupId, OrderByComparator<MembershipActivity> orderByComparator) {

		List<MembershipActivity> list = findBygroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership activity
	 * @throws NoSuchMembershipActivityException if a matching membership activity could not be found
	 */
	@Override
	public MembershipActivity findBygroupId_Last(
			long groupId,
			OrderByComparator<MembershipActivity> orderByComparator)
		throws NoSuchMembershipActivityException {

		MembershipActivity membershipActivity = fetchBygroupId_Last(
			groupId, orderByComparator);

		if (membershipActivity != null) {
			return membershipActivity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchMembershipActivityException(sb.toString());
	}

	/**
	 * Returns the last membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership activity, or <code>null</code> if a matching membership activity could not be found
	 */
	@Override
	public MembershipActivity fetchBygroupId_Last(
		long groupId, OrderByComparator<MembershipActivity> orderByComparator) {

		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<MembershipActivity> list = findBygroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the membership activities before and after the current membership activity in the ordered set where groupId = &#63;.
	 *
	 * @param membershipActivityId the primary key of the current membership activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership activity
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	@Override
	public MembershipActivity[] findBygroupId_PrevAndNext(
			long membershipActivityId, long groupId,
			OrderByComparator<MembershipActivity> orderByComparator)
		throws NoSuchMembershipActivityException {

		MembershipActivity membershipActivity = findByPrimaryKey(
			membershipActivityId);

		Session session = null;

		try {
			session = openSession();

			MembershipActivity[] array = new MembershipActivityImpl[3];

			array[0] = getBygroupId_PrevAndNext(
				session, membershipActivity, groupId, orderByComparator, true);

			array[1] = membershipActivity;

			array[2] = getBygroupId_PrevAndNext(
				session, membershipActivity, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MembershipActivity getBygroupId_PrevAndNext(
		Session session, MembershipActivity membershipActivity, long groupId,
		OrderByComparator<MembershipActivity> orderByComparator,
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

		sb.append(_SQL_SELECT_MEMBERSHIPACTIVITY_WHERE);

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
			sb.append(MembershipActivityModelImpl.ORDER_BY_JPQL);
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
						membershipActivity)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MembershipActivity> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the membership activities where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupId(long groupId) {
		for (MembershipActivity membershipActivity :
				findBygroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(membershipActivity);
		}
	}

	/**
	 * Returns the number of membership activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching membership activities
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = _finderPathCountBygroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MEMBERSHIPACTIVITY_WHERE);

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
		"membershipActivity.groupId = ?";

	public MembershipActivityPersistenceImpl() {
		setModelClass(MembershipActivity.class);

		setModelImplClass(MembershipActivityImpl.class);
		setModelPKClass(long.class);

		setTable(MembershipActivityTable.INSTANCE);
	}

	/**
	 * Caches the membership activity in the entity cache if it is enabled.
	 *
	 * @param membershipActivity the membership activity
	 */
	@Override
	public void cacheResult(MembershipActivity membershipActivity) {
		entityCache.putResult(
			MembershipActivityImpl.class, membershipActivity.getPrimaryKey(),
			membershipActivity);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the membership activities in the entity cache if it is enabled.
	 *
	 * @param membershipActivities the membership activities
	 */
	@Override
	public void cacheResult(List<MembershipActivity> membershipActivities) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (membershipActivities.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MembershipActivity membershipActivity : membershipActivities) {
			if (entityCache.getResult(
					MembershipActivityImpl.class,
					membershipActivity.getPrimaryKey()) == null) {

				cacheResult(membershipActivity);
			}
		}
	}

	/**
	 * Clears the cache for all membership activities.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MembershipActivityImpl.class);

		finderCache.clearCache(MembershipActivityImpl.class);
	}

	/**
	 * Clears the cache for the membership activity.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MembershipActivity membershipActivity) {
		entityCache.removeResult(
			MembershipActivityImpl.class, membershipActivity);
	}

	@Override
	public void clearCache(List<MembershipActivity> membershipActivities) {
		for (MembershipActivity membershipActivity : membershipActivities) {
			entityCache.removeResult(
				MembershipActivityImpl.class, membershipActivity);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MembershipActivityImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MembershipActivityImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new membership activity with the primary key. Does not add the membership activity to the database.
	 *
	 * @param membershipActivityId the primary key for the new membership activity
	 * @return the new membership activity
	 */
	@Override
	public MembershipActivity create(long membershipActivityId) {
		MembershipActivity membershipActivity = new MembershipActivityImpl();

		membershipActivity.setNew(true);
		membershipActivity.setPrimaryKey(membershipActivityId);

		return membershipActivity;
	}

	/**
	 * Removes the membership activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity that was removed
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	@Override
	public MembershipActivity remove(long membershipActivityId)
		throws NoSuchMembershipActivityException {

		return remove((Serializable)membershipActivityId);
	}

	/**
	 * Removes the membership activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the membership activity
	 * @return the membership activity that was removed
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	@Override
	public MembershipActivity remove(Serializable primaryKey)
		throws NoSuchMembershipActivityException {

		Session session = null;

		try {
			session = openSession();

			MembershipActivity membershipActivity =
				(MembershipActivity)session.get(
					MembershipActivityImpl.class, primaryKey);

			if (membershipActivity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMembershipActivityException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(membershipActivity);
		}
		catch (NoSuchMembershipActivityException noSuchEntityException) {
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
	protected MembershipActivity removeImpl(
		MembershipActivity membershipActivity) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(membershipActivity)) {
				membershipActivity = (MembershipActivity)session.get(
					MembershipActivityImpl.class,
					membershipActivity.getPrimaryKeyObj());
			}

			if (membershipActivity != null) {
				session.delete(membershipActivity);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (membershipActivity != null) {
			clearCache(membershipActivity);
		}

		return membershipActivity;
	}

	@Override
	public MembershipActivity updateImpl(
		MembershipActivity membershipActivity) {

		boolean isNew = membershipActivity.isNew();

		if (!(membershipActivity instanceof MembershipActivityModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(membershipActivity.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					membershipActivity);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in membershipActivity proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MembershipActivity implementation " +
					membershipActivity.getClass());
		}

		MembershipActivityModelImpl membershipActivityModelImpl =
			(MembershipActivityModelImpl)membershipActivity;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(membershipActivity);
			}
			else {
				membershipActivity = (MembershipActivity)session.merge(
					membershipActivity);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MembershipActivityImpl.class, membershipActivityModelImpl, false,
			true);

		if (isNew) {
			membershipActivity.setNew(false);
		}

		membershipActivity.resetOriginalValues();

		return membershipActivity;
	}

	/**
	 * Returns the membership activity with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the membership activity
	 * @return the membership activity
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	@Override
	public MembershipActivity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMembershipActivityException {

		MembershipActivity membershipActivity = fetchByPrimaryKey(primaryKey);

		if (membershipActivity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMembershipActivityException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return membershipActivity;
	}

	/**
	 * Returns the membership activity with the primary key or throws a <code>NoSuchMembershipActivityException</code> if it could not be found.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity
	 * @throws NoSuchMembershipActivityException if a membership activity with the primary key could not be found
	 */
	@Override
	public MembershipActivity findByPrimaryKey(long membershipActivityId)
		throws NoSuchMembershipActivityException {

		return findByPrimaryKey((Serializable)membershipActivityId);
	}

	/**
	 * Returns the membership activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity, or <code>null</code> if a membership activity with the primary key could not be found
	 */
	@Override
	public MembershipActivity fetchByPrimaryKey(long membershipActivityId) {
		return fetchByPrimaryKey((Serializable)membershipActivityId);
	}

	/**
	 * Returns all the membership activities.
	 *
	 * @return the membership activities
	 */
	@Override
	public List<MembershipActivity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @return the range of membership activities
	 */
	@Override
	public List<MembershipActivity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of membership activities
	 */
	@Override
	public List<MembershipActivity> findAll(
		int start, int end,
		OrderByComparator<MembershipActivity> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the membership activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of membership activities
	 */
	@Override
	public List<MembershipActivity> findAll(
		int start, int end,
		OrderByComparator<MembershipActivity> orderByComparator,
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

		List<MembershipActivity> list = null;

		if (useFinderCache) {
			list = (List<MembershipActivity>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MEMBERSHIPACTIVITY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MEMBERSHIPACTIVITY;

				sql = sql.concat(MembershipActivityModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MembershipActivity>)QueryUtil.list(
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
	 * Removes all the membership activities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MembershipActivity membershipActivity : findAll()) {
			remove(membershipActivity);
		}
	}

	/**
	 * Returns the number of membership activities.
	 *
	 * @return the number of membership activities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_MEMBERSHIPACTIVITY);

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
		return "membershipActivityId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MEMBERSHIPACTIVITY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MembershipActivityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the membership activity persistence.
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

		_setMembershipActivityUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setMembershipActivityUtilPersistence(null);

		entityCache.removeCache(MembershipActivityImpl.class.getName());
	}

	private void _setMembershipActivityUtilPersistence(
		MembershipActivityPersistence membershipActivityPersistence) {

		try {
			Field field = MembershipActivityUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, membershipActivityPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = GroupPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = GroupPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = GroupPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MEMBERSHIPACTIVITY =
		"SELECT membershipActivity FROM MembershipActivity membershipActivity";

	private static final String _SQL_SELECT_MEMBERSHIPACTIVITY_WHERE =
		"SELECT membershipActivity FROM MembershipActivity membershipActivity WHERE ";

	private static final String _SQL_COUNT_MEMBERSHIPACTIVITY =
		"SELECT COUNT(membershipActivity) FROM MembershipActivity membershipActivity";

	private static final String _SQL_COUNT_MEMBERSHIPACTIVITY_WHERE =
		"SELECT COUNT(membershipActivity) FROM MembershipActivity membershipActivity WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "membershipActivity.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MembershipActivity exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MembershipActivity exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MembershipActivityPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}