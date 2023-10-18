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

import com.weprode.nero.preference.exception.NoSuchUserPropertiesException;
import com.weprode.nero.preference.model.UserProperties;
import com.weprode.nero.preference.model.UserPropertiesTable;
import com.weprode.nero.preference.model.impl.UserPropertiesImpl;
import com.weprode.nero.preference.model.impl.UserPropertiesModelImpl;
import com.weprode.nero.preference.service.persistence.UserPropertiesPersistence;
import com.weprode.nero.preference.service.persistence.UserPropertiesUtil;
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
 * The persistence implementation for the user properties service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {UserPropertiesPersistence.class, BasePersistence.class})
public class UserPropertiesPersistenceImpl
	extends BasePersistenceImpl<UserProperties>
	implements UserPropertiesPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserPropertiesUtil</code> to access the user properties persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserPropertiesImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByetabId_manualAccount;
	private FinderPath _finderPathWithoutPaginationFindByetabId_manualAccount;
	private FinderPath _finderPathCountByetabId_manualAccount;

	/**
	 * Returns all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @return the matching user propertieses
	 */
	@Override
	public List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount) {

		return findByetabId_manualAccount(
			etabId, manualAccount, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @return the range of matching user propertieses
	 */
	@Override
	public List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount, int start, int end) {

		return findByetabId_manualAccount(
			etabId, manualAccount, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user propertieses
	 */
	@Override
	public List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount, int start, int end,
		OrderByComparator<UserProperties> orderByComparator) {

		return findByetabId_manualAccount(
			etabId, manualAccount, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user propertieses
	 */
	@Override
	public List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount, int start, int end,
		OrderByComparator<UserProperties> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByetabId_manualAccount;
				finderArgs = new Object[] {etabId, manualAccount};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByetabId_manualAccount;
			finderArgs = new Object[] {
				etabId, manualAccount, start, end, orderByComparator
			};
		}

		List<UserProperties> list = null;

		if (useFinderCache) {
			list = (List<UserProperties>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserProperties userProperties : list) {
					if ((etabId != userProperties.getEtabId()) ||
						(manualAccount != userProperties.isManualAccount())) {

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

			sb.append(_SQL_SELECT_USERPROPERTIES_WHERE);

			sb.append(_FINDER_COLUMN_ETABID_MANUALACCOUNT_ETABID_2);

			sb.append(_FINDER_COLUMN_ETABID_MANUALACCOUNT_MANUALACCOUNT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserPropertiesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(etabId);

				queryPos.add(manualAccount);

				list = (List<UserProperties>)QueryUtil.list(
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
	 * Returns the first user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user properties
	 * @throws NoSuchUserPropertiesException if a matching user properties could not be found
	 */
	@Override
	public UserProperties findByetabId_manualAccount_First(
			long etabId, boolean manualAccount,
			OrderByComparator<UserProperties> orderByComparator)
		throws NoSuchUserPropertiesException {

		UserProperties userProperties = fetchByetabId_manualAccount_First(
			etabId, manualAccount, orderByComparator);

		if (userProperties != null) {
			return userProperties;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("etabId=");
		sb.append(etabId);

		sb.append(", manualAccount=");
		sb.append(manualAccount);

		sb.append("}");

		throw new NoSuchUserPropertiesException(sb.toString());
	}

	/**
	 * Returns the first user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user properties, or <code>null</code> if a matching user properties could not be found
	 */
	@Override
	public UserProperties fetchByetabId_manualAccount_First(
		long etabId, boolean manualAccount,
		OrderByComparator<UserProperties> orderByComparator) {

		List<UserProperties> list = findByetabId_manualAccount(
			etabId, manualAccount, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user properties
	 * @throws NoSuchUserPropertiesException if a matching user properties could not be found
	 */
	@Override
	public UserProperties findByetabId_manualAccount_Last(
			long etabId, boolean manualAccount,
			OrderByComparator<UserProperties> orderByComparator)
		throws NoSuchUserPropertiesException {

		UserProperties userProperties = fetchByetabId_manualAccount_Last(
			etabId, manualAccount, orderByComparator);

		if (userProperties != null) {
			return userProperties;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("etabId=");
		sb.append(etabId);

		sb.append(", manualAccount=");
		sb.append(manualAccount);

		sb.append("}");

		throw new NoSuchUserPropertiesException(sb.toString());
	}

	/**
	 * Returns the last user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user properties, or <code>null</code> if a matching user properties could not be found
	 */
	@Override
	public UserProperties fetchByetabId_manualAccount_Last(
		long etabId, boolean manualAccount,
		OrderByComparator<UserProperties> orderByComparator) {

		int count = countByetabId_manualAccount(etabId, manualAccount);

		if (count == 0) {
			return null;
		}

		List<UserProperties> list = findByetabId_manualAccount(
			etabId, manualAccount, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user propertieses before and after the current user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param userId the primary key of the current user properties
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user properties
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	@Override
	public UserProperties[] findByetabId_manualAccount_PrevAndNext(
			long userId, long etabId, boolean manualAccount,
			OrderByComparator<UserProperties> orderByComparator)
		throws NoSuchUserPropertiesException {

		UserProperties userProperties = findByPrimaryKey(userId);

		Session session = null;

		try {
			session = openSession();

			UserProperties[] array = new UserPropertiesImpl[3];

			array[0] = getByetabId_manualAccount_PrevAndNext(
				session, userProperties, etabId, manualAccount,
				orderByComparator, true);

			array[1] = userProperties;

			array[2] = getByetabId_manualAccount_PrevAndNext(
				session, userProperties, etabId, manualAccount,
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

	protected UserProperties getByetabId_manualAccount_PrevAndNext(
		Session session, UserProperties userProperties, long etabId,
		boolean manualAccount,
		OrderByComparator<UserProperties> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_USERPROPERTIES_WHERE);

		sb.append(_FINDER_COLUMN_ETABID_MANUALACCOUNT_ETABID_2);

		sb.append(_FINDER_COLUMN_ETABID_MANUALACCOUNT_MANUALACCOUNT_2);

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
			sb.append(UserPropertiesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(etabId);

		queryPos.add(manualAccount);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						userProperties)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserProperties> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user propertieses where etabId = &#63; and manualAccount = &#63; from the database.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 */
	@Override
	public void removeByetabId_manualAccount(
		long etabId, boolean manualAccount) {

		for (UserProperties userProperties :
				findByetabId_manualAccount(
					etabId, manualAccount, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(userProperties);
		}
	}

	/**
	 * Returns the number of user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @return the number of matching user propertieses
	 */
	@Override
	public int countByetabId_manualAccount(long etabId, boolean manualAccount) {
		FinderPath finderPath = _finderPathCountByetabId_manualAccount;

		Object[] finderArgs = new Object[] {etabId, manualAccount};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_USERPROPERTIES_WHERE);

			sb.append(_FINDER_COLUMN_ETABID_MANUALACCOUNT_ETABID_2);

			sb.append(_FINDER_COLUMN_ETABID_MANUALACCOUNT_MANUALACCOUNT_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(etabId);

				queryPos.add(manualAccount);

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

	private static final String _FINDER_COLUMN_ETABID_MANUALACCOUNT_ETABID_2 =
		"userProperties.etabId = ? AND ";

	private static final String
		_FINDER_COLUMN_ETABID_MANUALACCOUNT_MANUALACCOUNT_2 =
			"userProperties.manualAccount = ?";

	public UserPropertiesPersistenceImpl() {
		setModelClass(UserProperties.class);

		setModelImplClass(UserPropertiesImpl.class);
		setModelPKClass(long.class);

		setTable(UserPropertiesTable.INSTANCE);
	}

	/**
	 * Caches the user properties in the entity cache if it is enabled.
	 *
	 * @param userProperties the user properties
	 */
	@Override
	public void cacheResult(UserProperties userProperties) {
		entityCache.putResult(
			UserPropertiesImpl.class, userProperties.getPrimaryKey(),
			userProperties);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the user propertieses in the entity cache if it is enabled.
	 *
	 * @param userPropertieses the user propertieses
	 */
	@Override
	public void cacheResult(List<UserProperties> userPropertieses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (userPropertieses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UserProperties userProperties : userPropertieses) {
			if (entityCache.getResult(
					UserPropertiesImpl.class, userProperties.getPrimaryKey()) ==
						null) {

				cacheResult(userProperties);
			}
		}
	}

	/**
	 * Clears the cache for all user propertieses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserPropertiesImpl.class);

		finderCache.clearCache(UserPropertiesImpl.class);
	}

	/**
	 * Clears the cache for the user properties.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserProperties userProperties) {
		entityCache.removeResult(UserPropertiesImpl.class, userProperties);
	}

	@Override
	public void clearCache(List<UserProperties> userPropertieses) {
		for (UserProperties userProperties : userPropertieses) {
			entityCache.removeResult(UserPropertiesImpl.class, userProperties);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UserPropertiesImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UserPropertiesImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new user properties with the primary key. Does not add the user properties to the database.
	 *
	 * @param userId the primary key for the new user properties
	 * @return the new user properties
	 */
	@Override
	public UserProperties create(long userId) {
		UserProperties userProperties = new UserPropertiesImpl();

		userProperties.setNew(true);
		userProperties.setPrimaryKey(userId);

		return userProperties;
	}

	/**
	 * Removes the user properties with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties that was removed
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	@Override
	public UserProperties remove(long userId)
		throws NoSuchUserPropertiesException {

		return remove((Serializable)userId);
	}

	/**
	 * Removes the user properties with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user properties
	 * @return the user properties that was removed
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	@Override
	public UserProperties remove(Serializable primaryKey)
		throws NoSuchUserPropertiesException {

		Session session = null;

		try {
			session = openSession();

			UserProperties userProperties = (UserProperties)session.get(
				UserPropertiesImpl.class, primaryKey);

			if (userProperties == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserPropertiesException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userProperties);
		}
		catch (NoSuchUserPropertiesException noSuchEntityException) {
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
	protected UserProperties removeImpl(UserProperties userProperties) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userProperties)) {
				userProperties = (UserProperties)session.get(
					UserPropertiesImpl.class,
					userProperties.getPrimaryKeyObj());
			}

			if (userProperties != null) {
				session.delete(userProperties);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userProperties != null) {
			clearCache(userProperties);
		}

		return userProperties;
	}

	@Override
	public UserProperties updateImpl(UserProperties userProperties) {
		boolean isNew = userProperties.isNew();

		if (!(userProperties instanceof UserPropertiesModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userProperties.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					userProperties);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userProperties proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserProperties implementation " +
					userProperties.getClass());
		}

		UserPropertiesModelImpl userPropertiesModelImpl =
			(UserPropertiesModelImpl)userProperties;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(userProperties);
			}
			else {
				userProperties = (UserProperties)session.merge(userProperties);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UserPropertiesImpl.class, userPropertiesModelImpl, false, true);

		if (isNew) {
			userProperties.setNew(false);
		}

		userProperties.resetOriginalValues();

		return userProperties;
	}

	/**
	 * Returns the user properties with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user properties
	 * @return the user properties
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	@Override
	public UserProperties findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserPropertiesException {

		UserProperties userProperties = fetchByPrimaryKey(primaryKey);

		if (userProperties == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserPropertiesException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userProperties;
	}

	/**
	 * Returns the user properties with the primary key or throws a <code>NoSuchUserPropertiesException</code> if it could not be found.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	@Override
	public UserProperties findByPrimaryKey(long userId)
		throws NoSuchUserPropertiesException {

		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the user properties with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties, or <code>null</code> if a user properties with the primary key could not be found
	 */
	@Override
	public UserProperties fetchByPrimaryKey(long userId) {
		return fetchByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns all the user propertieses.
	 *
	 * @return the user propertieses
	 */
	@Override
	public List<UserProperties> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @return the range of user propertieses
	 */
	@Override
	public List<UserProperties> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user propertieses
	 */
	@Override
	public List<UserProperties> findAll(
		int start, int end,
		OrderByComparator<UserProperties> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user propertieses
	 */
	@Override
	public List<UserProperties> findAll(
		int start, int end, OrderByComparator<UserProperties> orderByComparator,
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

		List<UserProperties> list = null;

		if (useFinderCache) {
			list = (List<UserProperties>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERPROPERTIES);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERPROPERTIES;

				sql = sql.concat(UserPropertiesModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserProperties>)QueryUtil.list(
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
	 * Removes all the user propertieses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserProperties userProperties : findAll()) {
			remove(userProperties);
		}
	}

	/**
	 * Returns the number of user propertieses.
	 *
	 * @return the number of user propertieses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USERPROPERTIES);

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
		return "userId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_USERPROPERTIES;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserPropertiesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user properties persistence.
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

		_finderPathWithPaginationFindByetabId_manualAccount = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByetabId_manualAccount",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"etabId", "manualAccount"}, true);

		_finderPathWithoutPaginationFindByetabId_manualAccount = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByetabId_manualAccount",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"etabId", "manualAccount"}, true);

		_finderPathCountByetabId_manualAccount = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByetabId_manualAccount",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			new String[] {"etabId", "manualAccount"}, false);

		_setUserPropertiesUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setUserPropertiesUtilPersistence(null);

		entityCache.removeCache(UserPropertiesImpl.class.getName());
	}

	private void _setUserPropertiesUtilPersistence(
		UserPropertiesPersistence userPropertiesPersistence) {

		try {
			Field field = UserPropertiesUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, userPropertiesPersistence);
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

	private static final String _SQL_SELECT_USERPROPERTIES =
		"SELECT userProperties FROM UserProperties userProperties";

	private static final String _SQL_SELECT_USERPROPERTIES_WHERE =
		"SELECT userProperties FROM UserProperties userProperties WHERE ";

	private static final String _SQL_COUNT_USERPROPERTIES =
		"SELECT COUNT(userProperties) FROM UserProperties userProperties";

	private static final String _SQL_COUNT_USERPROPERTIES_WHERE =
		"SELECT COUNT(userProperties) FROM UserProperties userProperties WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userProperties.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserProperties exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserProperties exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserPropertiesPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}