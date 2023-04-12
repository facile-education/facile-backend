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
import com.liferay.portal.kernel.util.StringUtil;

import com.weprode.nero.document.exception.NoSuchLoolTokenException;
import com.weprode.nero.document.model.LoolToken;
import com.weprode.nero.document.model.LoolTokenTable;
import com.weprode.nero.document.model.impl.LoolTokenImpl;
import com.weprode.nero.document.model.impl.LoolTokenModelImpl;
import com.weprode.nero.document.service.persistence.LoolTokenPersistence;
import com.weprode.nero.document.service.persistence.LoolTokenUtil;
import com.weprode.nero.document.service.persistence.impl.constants.DocumentPersistenceConstants;

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
 * The persistence implementation for the lool token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {LoolTokenPersistence.class, BasePersistence.class})
public class LoolTokenPersistenceImpl
	extends BasePersistenceImpl<LoolToken> implements LoolTokenPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LoolTokenUtil</code> to access the lool token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LoolTokenImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchBytoken;
	private FinderPath _finderPathCountBytoken;

	/**
	 * Returns the lool token where token = &#63; or throws a <code>NoSuchLoolTokenException</code> if it could not be found.
	 *
	 * @param token the token
	 * @return the matching lool token
	 * @throws NoSuchLoolTokenException if a matching lool token could not be found
	 */
	@Override
	public LoolToken findBytoken(String token) throws NoSuchLoolTokenException {
		LoolToken loolToken = fetchBytoken(token);

		if (loolToken == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("token=");
			sb.append(token);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLoolTokenException(sb.toString());
		}

		return loolToken;
	}

	/**
	 * Returns the lool token where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param token the token
	 * @return the matching lool token, or <code>null</code> if a matching lool token could not be found
	 */
	@Override
	public LoolToken fetchBytoken(String token) {
		return fetchBytoken(token, true);
	}

	/**
	 * Returns the lool token where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param token the token
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching lool token, or <code>null</code> if a matching lool token could not be found
	 */
	@Override
	public LoolToken fetchBytoken(String token, boolean useFinderCache) {
		token = Objects.toString(token, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {token};
		}

		Object result = null;

		if (useFinderCache) {
			result = dummyFinderCache.getResult(
				_finderPathFetchBytoken, finderArgs);
		}

		if (result instanceof LoolToken) {
			LoolToken loolToken = (LoolToken)result;

			if (!Objects.equals(token, loolToken.getToken())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_LOOLTOKEN_WHERE);

			boolean bindToken = false;

			if (token.isEmpty()) {
				sb.append(_FINDER_COLUMN_TOKEN_TOKEN_3);
			}
			else {
				bindToken = true;

				sb.append(_FINDER_COLUMN_TOKEN_TOKEN_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindToken) {
					queryPos.add(token);
				}

				List<LoolToken> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						dummyFinderCache.putResult(
							_finderPathFetchBytoken, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {token};
							}

							_log.warn(
								"LoolTokenPersistenceImpl.fetchBytoken(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LoolToken loolToken = list.get(0);

					result = loolToken;

					cacheResult(loolToken);
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
			return (LoolToken)result;
		}
	}

	/**
	 * Removes the lool token where token = &#63; from the database.
	 *
	 * @param token the token
	 * @return the lool token that was removed
	 */
	@Override
	public LoolToken removeBytoken(String token)
		throws NoSuchLoolTokenException {

		LoolToken loolToken = findBytoken(token);

		return remove(loolToken);
	}

	/**
	 * Returns the number of lool tokens where token = &#63;.
	 *
	 * @param token the token
	 * @return the number of matching lool tokens
	 */
	@Override
	public int countBytoken(String token) {
		token = Objects.toString(token, "");

		FinderPath finderPath = _finderPathCountBytoken;

		Object[] finderArgs = new Object[] {token};

		Long count = (Long)dummyFinderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LOOLTOKEN_WHERE);

			boolean bindToken = false;

			if (token.isEmpty()) {
				sb.append(_FINDER_COLUMN_TOKEN_TOKEN_3);
			}
			else {
				bindToken = true;

				sb.append(_FINDER_COLUMN_TOKEN_TOKEN_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindToken) {
					queryPos.add(token);
				}

				count = (Long)query.uniqueResult();

				dummyFinderCache.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_TOKEN_TOKEN_2 =
		"loolToken.token = ?";

	private static final String _FINDER_COLUMN_TOKEN_TOKEN_3 =
		"(loolToken.token IS NULL OR loolToken.token = '')";

	public LoolTokenPersistenceImpl() {
		setModelClass(LoolToken.class);

		setModelImplClass(LoolTokenImpl.class);
		setModelPKClass(long.class);

		setTable(LoolTokenTable.INSTANCE);
	}

	/**
	 * Caches the lool token in the entity cache if it is enabled.
	 *
	 * @param loolToken the lool token
	 */
	@Override
	public void cacheResult(LoolToken loolToken) {
		dummyEntityCache.putResult(
			LoolTokenImpl.class, loolToken.getPrimaryKey(), loolToken);

		dummyFinderCache.putResult(
			_finderPathFetchBytoken, new Object[] {loolToken.getToken()},
			loolToken);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the lool tokens in the entity cache if it is enabled.
	 *
	 * @param loolTokens the lool tokens
	 */
	@Override
	public void cacheResult(List<LoolToken> loolTokens) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (loolTokens.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (LoolToken loolToken : loolTokens) {
			if (dummyEntityCache.getResult(
					LoolTokenImpl.class, loolToken.getPrimaryKey()) == null) {

				cacheResult(loolToken);
			}
		}
	}

	/**
	 * Clears the cache for all lool tokens.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(LoolTokenImpl.class);

		dummyFinderCache.clearCache(LoolTokenImpl.class);
	}

	/**
	 * Clears the cache for the lool token.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoolToken loolToken) {
		dummyEntityCache.removeResult(LoolTokenImpl.class, loolToken);
	}

	@Override
	public void clearCache(List<LoolToken> loolTokens) {
		for (LoolToken loolToken : loolTokens) {
			dummyEntityCache.removeResult(LoolTokenImpl.class, loolToken);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(LoolTokenImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(LoolTokenImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		LoolTokenModelImpl loolTokenModelImpl) {

		Object[] args = new Object[] {loolTokenModelImpl.getToken()};

		dummyFinderCache.putResult(
			_finderPathCountBytoken, args, Long.valueOf(1));
		dummyFinderCache.putResult(
			_finderPathFetchBytoken, args, loolTokenModelImpl);
	}

	/**
	 * Creates a new lool token with the primary key. Does not add the lool token to the database.
	 *
	 * @param loolTokenId the primary key for the new lool token
	 * @return the new lool token
	 */
	@Override
	public LoolToken create(long loolTokenId) {
		LoolToken loolToken = new LoolTokenImpl();

		loolToken.setNew(true);
		loolToken.setPrimaryKey(loolTokenId);

		return loolToken;
	}

	/**
	 * Removes the lool token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token that was removed
	 * @throws NoSuchLoolTokenException if a lool token with the primary key could not be found
	 */
	@Override
	public LoolToken remove(long loolTokenId) throws NoSuchLoolTokenException {
		return remove((Serializable)loolTokenId);
	}

	/**
	 * Removes the lool token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the lool token
	 * @return the lool token that was removed
	 * @throws NoSuchLoolTokenException if a lool token with the primary key could not be found
	 */
	@Override
	public LoolToken remove(Serializable primaryKey)
		throws NoSuchLoolTokenException {

		Session session = null;

		try {
			session = openSession();

			LoolToken loolToken = (LoolToken)session.get(
				LoolTokenImpl.class, primaryKey);

			if (loolToken == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoolTokenException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(loolToken);
		}
		catch (NoSuchLoolTokenException noSuchEntityException) {
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
	protected LoolToken removeImpl(LoolToken loolToken) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loolToken)) {
				loolToken = (LoolToken)session.get(
					LoolTokenImpl.class, loolToken.getPrimaryKeyObj());
			}

			if (loolToken != null) {
				session.delete(loolToken);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (loolToken != null) {
			clearCache(loolToken);
		}

		return loolToken;
	}

	@Override
	public LoolToken updateImpl(LoolToken loolToken) {
		boolean isNew = loolToken.isNew();

		if (!(loolToken instanceof LoolTokenModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(loolToken.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(loolToken);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in loolToken proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LoolToken implementation " +
					loolToken.getClass());
		}

		LoolTokenModelImpl loolTokenModelImpl = (LoolTokenModelImpl)loolToken;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(loolToken);
			}
			else {
				loolToken = (LoolToken)session.merge(loolToken);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			LoolTokenImpl.class, loolTokenModelImpl, false, true);

		cacheUniqueFindersCache(loolTokenModelImpl);

		if (isNew) {
			loolToken.setNew(false);
		}

		loolToken.resetOriginalValues();

		return loolToken;
	}

	/**
	 * Returns the lool token with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the lool token
	 * @return the lool token
	 * @throws NoSuchLoolTokenException if a lool token with the primary key could not be found
	 */
	@Override
	public LoolToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoolTokenException {

		LoolToken loolToken = fetchByPrimaryKey(primaryKey);

		if (loolToken == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoolTokenException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return loolToken;
	}

	/**
	 * Returns the lool token with the primary key or throws a <code>NoSuchLoolTokenException</code> if it could not be found.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token
	 * @throws NoSuchLoolTokenException if a lool token with the primary key could not be found
	 */
	@Override
	public LoolToken findByPrimaryKey(long loolTokenId)
		throws NoSuchLoolTokenException {

		return findByPrimaryKey((Serializable)loolTokenId);
	}

	/**
	 * Returns the lool token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token, or <code>null</code> if a lool token with the primary key could not be found
	 */
	@Override
	public LoolToken fetchByPrimaryKey(long loolTokenId) {
		return fetchByPrimaryKey((Serializable)loolTokenId);
	}

	/**
	 * Returns all the lool tokens.
	 *
	 * @return the lool tokens
	 */
	@Override
	public List<LoolToken> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lool tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool tokens
	 * @param end the upper bound of the range of lool tokens (not inclusive)
	 * @return the range of lool tokens
	 */
	@Override
	public List<LoolToken> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the lool tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool tokens
	 * @param end the upper bound of the range of lool tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lool tokens
	 */
	@Override
	public List<LoolToken> findAll(
		int start, int end, OrderByComparator<LoolToken> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lool tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool tokens
	 * @param end the upper bound of the range of lool tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of lool tokens
	 */
	@Override
	public List<LoolToken> findAll(
		int start, int end, OrderByComparator<LoolToken> orderByComparator,
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

		List<LoolToken> list = null;

		if (useFinderCache) {
			list = (List<LoolToken>)dummyFinderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LOOLTOKEN);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LOOLTOKEN;

				sql = sql.concat(LoolTokenModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LoolToken>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					dummyFinderCache.putResult(finderPath, finderArgs, list);
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
	 * Removes all the lool tokens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoolToken loolToken : findAll()) {
			remove(loolToken);
		}
	}

	/**
	 * Returns the number of lool tokens.
	 *
	 * @return the number of lool tokens
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LOOLTOKEN);

				count = (Long)query.uniqueResult();

				dummyFinderCache.putResult(
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
		return dummyEntityCache;
	}

	@Override
	protected String getPKDBName() {
		return "loolTokenId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LOOLTOKEN;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LoolTokenModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the lool token persistence.
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

		_finderPathFetchBytoken = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBytoken",
			new String[] {String.class.getName()}, new String[] {"token"},
			true);

		_finderPathCountBytoken = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBytoken",
			new String[] {String.class.getName()}, new String[] {"token"},
			false);

		_setLoolTokenUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setLoolTokenUtilPersistence(null);

		dummyEntityCache.removeCache(LoolTokenImpl.class.getName());
	}

	private void _setLoolTokenUtilPersistence(
		LoolTokenPersistence loolTokenPersistence) {

		try {
			Field field = LoolTokenUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, loolTokenPersistence);
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

	private static final String _SQL_SELECT_LOOLTOKEN =
		"SELECT loolToken FROM LoolToken loolToken";

	private static final String _SQL_SELECT_LOOLTOKEN_WHERE =
		"SELECT loolToken FROM LoolToken loolToken WHERE ";

	private static final String _SQL_COUNT_LOOLTOKEN =
		"SELECT COUNT(loolToken) FROM LoolToken loolToken";

	private static final String _SQL_COUNT_LOOLTOKEN_WHERE =
		"SELECT COUNT(loolToken) FROM LoolToken loolToken WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "loolToken.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LoolToken exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LoolToken exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LoolTokenPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

	@Reference
	private LoolTokenModelArgumentsResolver _loolTokenModelArgumentsResolver;

}