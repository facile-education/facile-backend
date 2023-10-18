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

package com.weprode.nero.mobile.service.persistence.impl;

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

import com.weprode.nero.mobile.exception.NoSuchUserMobileTokenException;
import com.weprode.nero.mobile.model.UserMobileToken;
import com.weprode.nero.mobile.model.UserMobileTokenTable;
import com.weprode.nero.mobile.model.impl.UserMobileTokenImpl;
import com.weprode.nero.mobile.model.impl.UserMobileTokenModelImpl;
import com.weprode.nero.mobile.service.persistence.UserMobileTokenPersistence;
import com.weprode.nero.mobile.service.persistence.UserMobileTokenUtil;
import com.weprode.nero.mobile.service.persistence.impl.constants.MobilePersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the user mobile token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {UserMobileTokenPersistence.class, BasePersistence.class})
public class UserMobileTokenPersistenceImpl
	extends BasePersistenceImpl<UserMobileToken>
	implements UserMobileTokenPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserMobileTokenUtil</code> to access the user mobile token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserMobileTokenImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchBymobileToken;
	private FinderPath _finderPathCountBymobileToken;

	/**
	 * Returns the user mobile token where mobileToken = &#63; or throws a <code>NoSuchUserMobileTokenException</code> if it could not be found.
	 *
	 * @param mobileToken the mobile token
	 * @return the matching user mobile token
	 * @throws NoSuchUserMobileTokenException if a matching user mobile token could not be found
	 */
	@Override
	public UserMobileToken findBymobileToken(String mobileToken)
		throws NoSuchUserMobileTokenException {

		UserMobileToken userMobileToken = fetchBymobileToken(mobileToken);

		if (userMobileToken == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("mobileToken=");
			sb.append(mobileToken);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchUserMobileTokenException(sb.toString());
		}

		return userMobileToken;
	}

	/**
	 * Returns the user mobile token where mobileToken = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mobileToken the mobile token
	 * @return the matching user mobile token, or <code>null</code> if a matching user mobile token could not be found
	 */
	@Override
	public UserMobileToken fetchBymobileToken(String mobileToken) {
		return fetchBymobileToken(mobileToken, true);
	}

	/**
	 * Returns the user mobile token where mobileToken = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mobileToken the mobile token
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user mobile token, or <code>null</code> if a matching user mobile token could not be found
	 */
	@Override
	public UserMobileToken fetchBymobileToken(
		String mobileToken, boolean useFinderCache) {

		mobileToken = Objects.toString(mobileToken, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {mobileToken};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBymobileToken, finderArgs, this);
		}

		if (result instanceof UserMobileToken) {
			UserMobileToken userMobileToken = (UserMobileToken)result;

			if (!Objects.equals(
					mobileToken, userMobileToken.getMobileToken())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_USERMOBILETOKEN_WHERE);

			boolean bindMobileToken = false;

			if (mobileToken.isEmpty()) {
				sb.append(_FINDER_COLUMN_MOBILETOKEN_MOBILETOKEN_3);
			}
			else {
				bindMobileToken = true;

				sb.append(_FINDER_COLUMN_MOBILETOKEN_MOBILETOKEN_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindMobileToken) {
					queryPos.add(mobileToken);
				}

				List<UserMobileToken> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBymobileToken, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {mobileToken};
							}

							_log.warn(
								"UserMobileTokenPersistenceImpl.fetchBymobileToken(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					UserMobileToken userMobileToken = list.get(0);

					result = userMobileToken;

					cacheResult(userMobileToken);
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
			return (UserMobileToken)result;
		}
	}

	/**
	 * Removes the user mobile token where mobileToken = &#63; from the database.
	 *
	 * @param mobileToken the mobile token
	 * @return the user mobile token that was removed
	 */
	@Override
	public UserMobileToken removeBymobileToken(String mobileToken)
		throws NoSuchUserMobileTokenException {

		UserMobileToken userMobileToken = findBymobileToken(mobileToken);

		return remove(userMobileToken);
	}

	/**
	 * Returns the number of user mobile tokens where mobileToken = &#63;.
	 *
	 * @param mobileToken the mobile token
	 * @return the number of matching user mobile tokens
	 */
	@Override
	public int countBymobileToken(String mobileToken) {
		mobileToken = Objects.toString(mobileToken, "");

		FinderPath finderPath = _finderPathCountBymobileToken;

		Object[] finderArgs = new Object[] {mobileToken};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERMOBILETOKEN_WHERE);

			boolean bindMobileToken = false;

			if (mobileToken.isEmpty()) {
				sb.append(_FINDER_COLUMN_MOBILETOKEN_MOBILETOKEN_3);
			}
			else {
				bindMobileToken = true;

				sb.append(_FINDER_COLUMN_MOBILETOKEN_MOBILETOKEN_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindMobileToken) {
					queryPos.add(mobileToken);
				}

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

	private static final String _FINDER_COLUMN_MOBILETOKEN_MOBILETOKEN_2 =
		"userMobileToken.mobileToken = ?";

	private static final String _FINDER_COLUMN_MOBILETOKEN_MOBILETOKEN_3 =
		"(userMobileToken.mobileToken IS NULL OR userMobileToken.mobileToken = '')";

	public UserMobileTokenPersistenceImpl() {
		setModelClass(UserMobileToken.class);

		setModelImplClass(UserMobileTokenImpl.class);
		setModelPKClass(long.class);

		setTable(UserMobileTokenTable.INSTANCE);
	}

	/**
	 * Caches the user mobile token in the entity cache if it is enabled.
	 *
	 * @param userMobileToken the user mobile token
	 */
	@Override
	public void cacheResult(UserMobileToken userMobileToken) {
		entityCache.putResult(
			UserMobileTokenImpl.class, userMobileToken.getPrimaryKey(),
			userMobileToken);

		finderCache.putResult(
			_finderPathFetchBymobileToken,
			new Object[] {userMobileToken.getMobileToken()}, userMobileToken);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the user mobile tokens in the entity cache if it is enabled.
	 *
	 * @param userMobileTokens the user mobile tokens
	 */
	@Override
	public void cacheResult(List<UserMobileToken> userMobileTokens) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (userMobileTokens.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UserMobileToken userMobileToken : userMobileTokens) {
			if (entityCache.getResult(
					UserMobileTokenImpl.class,
					userMobileToken.getPrimaryKey()) == null) {

				cacheResult(userMobileToken);
			}
		}
	}

	/**
	 * Clears the cache for all user mobile tokens.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserMobileTokenImpl.class);

		finderCache.clearCache(UserMobileTokenImpl.class);
	}

	/**
	 * Clears the cache for the user mobile token.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserMobileToken userMobileToken) {
		entityCache.removeResult(UserMobileTokenImpl.class, userMobileToken);
	}

	@Override
	public void clearCache(List<UserMobileToken> userMobileTokens) {
		for (UserMobileToken userMobileToken : userMobileTokens) {
			entityCache.removeResult(
				UserMobileTokenImpl.class, userMobileToken);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UserMobileTokenImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UserMobileTokenImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		UserMobileTokenModelImpl userMobileTokenModelImpl) {

		Object[] args = new Object[] {
			userMobileTokenModelImpl.getMobileToken()
		};

		finderCache.putResult(
			_finderPathCountBymobileToken, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBymobileToken, args, userMobileTokenModelImpl);
	}

	/**
	 * Creates a new user mobile token with the primary key. Does not add the user mobile token to the database.
	 *
	 * @param userId the primary key for the new user mobile token
	 * @return the new user mobile token
	 */
	@Override
	public UserMobileToken create(long userId) {
		UserMobileToken userMobileToken = new UserMobileTokenImpl();

		userMobileToken.setNew(true);
		userMobileToken.setPrimaryKey(userId);

		return userMobileToken;
	}

	/**
	 * Removes the user mobile token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the user mobile token
	 * @return the user mobile token that was removed
	 * @throws NoSuchUserMobileTokenException if a user mobile token with the primary key could not be found
	 */
	@Override
	public UserMobileToken remove(long userId)
		throws NoSuchUserMobileTokenException {

		return remove((Serializable)userId);
	}

	/**
	 * Removes the user mobile token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user mobile token
	 * @return the user mobile token that was removed
	 * @throws NoSuchUserMobileTokenException if a user mobile token with the primary key could not be found
	 */
	@Override
	public UserMobileToken remove(Serializable primaryKey)
		throws NoSuchUserMobileTokenException {

		Session session = null;

		try {
			session = openSession();

			UserMobileToken userMobileToken = (UserMobileToken)session.get(
				UserMobileTokenImpl.class, primaryKey);

			if (userMobileToken == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserMobileTokenException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userMobileToken);
		}
		catch (NoSuchUserMobileTokenException noSuchEntityException) {
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
	protected UserMobileToken removeImpl(UserMobileToken userMobileToken) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userMobileToken)) {
				userMobileToken = (UserMobileToken)session.get(
					UserMobileTokenImpl.class,
					userMobileToken.getPrimaryKeyObj());
			}

			if (userMobileToken != null) {
				session.delete(userMobileToken);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userMobileToken != null) {
			clearCache(userMobileToken);
		}

		return userMobileToken;
	}

	@Override
	public UserMobileToken updateImpl(UserMobileToken userMobileToken) {
		boolean isNew = userMobileToken.isNew();

		if (!(userMobileToken instanceof UserMobileTokenModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userMobileToken.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					userMobileToken);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userMobileToken proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserMobileToken implementation " +
					userMobileToken.getClass());
		}

		UserMobileTokenModelImpl userMobileTokenModelImpl =
			(UserMobileTokenModelImpl)userMobileToken;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(userMobileToken);
			}
			else {
				userMobileToken = (UserMobileToken)session.merge(
					userMobileToken);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UserMobileTokenImpl.class, userMobileTokenModelImpl, false, true);

		cacheUniqueFindersCache(userMobileTokenModelImpl);

		if (isNew) {
			userMobileToken.setNew(false);
		}

		userMobileToken.resetOriginalValues();

		return userMobileToken;
	}

	/**
	 * Returns the user mobile token with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user mobile token
	 * @return the user mobile token
	 * @throws NoSuchUserMobileTokenException if a user mobile token with the primary key could not be found
	 */
	@Override
	public UserMobileToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserMobileTokenException {

		UserMobileToken userMobileToken = fetchByPrimaryKey(primaryKey);

		if (userMobileToken == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserMobileTokenException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userMobileToken;
	}

	/**
	 * Returns the user mobile token with the primary key or throws a <code>NoSuchUserMobileTokenException</code> if it could not be found.
	 *
	 * @param userId the primary key of the user mobile token
	 * @return the user mobile token
	 * @throws NoSuchUserMobileTokenException if a user mobile token with the primary key could not be found
	 */
	@Override
	public UserMobileToken findByPrimaryKey(long userId)
		throws NoSuchUserMobileTokenException {

		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the user mobile token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the user mobile token
	 * @return the user mobile token, or <code>null</code> if a user mobile token with the primary key could not be found
	 */
	@Override
	public UserMobileToken fetchByPrimaryKey(long userId) {
		return fetchByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns all the user mobile tokens.
	 *
	 * @return the user mobile tokens
	 */
	@Override
	public List<UserMobileToken> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user mobile tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserMobileTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user mobile tokens
	 * @param end the upper bound of the range of user mobile tokens (not inclusive)
	 * @return the range of user mobile tokens
	 */
	@Override
	public List<UserMobileToken> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user mobile tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserMobileTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user mobile tokens
	 * @param end the upper bound of the range of user mobile tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user mobile tokens
	 */
	@Override
	public List<UserMobileToken> findAll(
		int start, int end,
		OrderByComparator<UserMobileToken> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user mobile tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserMobileTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user mobile tokens
	 * @param end the upper bound of the range of user mobile tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user mobile tokens
	 */
	@Override
	public List<UserMobileToken> findAll(
		int start, int end,
		OrderByComparator<UserMobileToken> orderByComparator,
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

		List<UserMobileToken> list = null;

		if (useFinderCache) {
			list = (List<UserMobileToken>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERMOBILETOKEN);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERMOBILETOKEN;

				sql = sql.concat(UserMobileTokenModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserMobileToken>)QueryUtil.list(
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
	 * Removes all the user mobile tokens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserMobileToken userMobileToken : findAll()) {
			remove(userMobileToken);
		}
	}

	/**
	 * Returns the number of user mobile tokens.
	 *
	 * @return the number of user mobile tokens
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USERMOBILETOKEN);

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
		return _SQL_SELECT_USERMOBILETOKEN;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserMobileTokenModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user mobile token persistence.
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

		_finderPathFetchBymobileToken = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBymobileToken",
			new String[] {String.class.getName()}, new String[] {"mobileToken"},
			true);

		_finderPathCountBymobileToken = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBymobileToken",
			new String[] {String.class.getName()}, new String[] {"mobileToken"},
			false);

		_setUserMobileTokenUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setUserMobileTokenUtilPersistence(null);

		entityCache.removeCache(UserMobileTokenImpl.class.getName());
	}

	private void _setUserMobileTokenUtilPersistence(
		UserMobileTokenPersistence userMobileTokenPersistence) {

		try {
			Field field = UserMobileTokenUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, userMobileTokenPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = MobilePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = MobilePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = MobilePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_USERMOBILETOKEN =
		"SELECT userMobileToken FROM UserMobileToken userMobileToken";

	private static final String _SQL_SELECT_USERMOBILETOKEN_WHERE =
		"SELECT userMobileToken FROM UserMobileToken userMobileToken WHERE ";

	private static final String _SQL_COUNT_USERMOBILETOKEN =
		"SELECT COUNT(userMobileToken) FROM UserMobileToken userMobileToken";

	private static final String _SQL_COUNT_USERMOBILETOKEN_WHERE =
		"SELECT COUNT(userMobileToken) FROM UserMobileToken userMobileToken WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userMobileToken.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserMobileToken exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserMobileToken exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserMobileTokenPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}