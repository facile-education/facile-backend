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

package com.weprode.nero.about.service.persistence.impl;

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

import com.weprode.nero.about.exception.NoSuchEntVersionUserException;
import com.weprode.nero.about.model.EntVersionUser;
import com.weprode.nero.about.model.EntVersionUserTable;
import com.weprode.nero.about.model.impl.EntVersionUserImpl;
import com.weprode.nero.about.model.impl.EntVersionUserModelImpl;
import com.weprode.nero.about.service.persistence.EntVersionUserPersistence;
import com.weprode.nero.about.service.persistence.EntVersionUserUtil;
import com.weprode.nero.about.service.persistence.impl.constants.AboutPersistenceConstants;

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
 * The persistence implementation for the ent version user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {EntVersionUserPersistence.class, BasePersistence.class})
public class EntVersionUserPersistenceImpl
	extends BasePersistenceImpl<EntVersionUser>
	implements EntVersionUserPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EntVersionUserUtil</code> to access the ent version user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EntVersionUserImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByentVersionId_userId;
	private FinderPath _finderPathCountByentVersionId_userId;

	/**
	 * Returns the ent version user where entVersionId = &#63; and userId = &#63; or throws a <code>NoSuchEntVersionUserException</code> if it could not be found.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the matching ent version user
	 * @throws NoSuchEntVersionUserException if a matching ent version user could not be found
	 */
	@Override
	public EntVersionUser findByentVersionId_userId(
			long entVersionId, long userId)
		throws NoSuchEntVersionUserException {

		EntVersionUser entVersionUser = fetchByentVersionId_userId(
			entVersionId, userId);

		if (entVersionUser == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("entVersionId=");
			sb.append(entVersionId);

			sb.append(", userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchEntVersionUserException(sb.toString());
		}

		return entVersionUser;
	}

	/**
	 * Returns the ent version user where entVersionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the matching ent version user, or <code>null</code> if a matching ent version user could not be found
	 */
	@Override
	public EntVersionUser fetchByentVersionId_userId(
		long entVersionId, long userId) {

		return fetchByentVersionId_userId(entVersionId, userId, true);
	}

	/**
	 * Returns the ent version user where entVersionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ent version user, or <code>null</code> if a matching ent version user could not be found
	 */
	@Override
	public EntVersionUser fetchByentVersionId_userId(
		long entVersionId, long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {entVersionId, userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByentVersionId_userId, finderArgs);
		}

		if (result instanceof EntVersionUser) {
			EntVersionUser entVersionUser = (EntVersionUser)result;

			if ((entVersionId != entVersionUser.getEntVersionId()) ||
				(userId != entVersionUser.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_ENTVERSIONUSER_WHERE);

			sb.append(_FINDER_COLUMN_ENTVERSIONID_USERID_ENTVERSIONID_2);

			sb.append(_FINDER_COLUMN_ENTVERSIONID_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(entVersionId);

				queryPos.add(userId);

				List<EntVersionUser> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByentVersionId_userId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									entVersionId, userId
								};
							}

							_log.warn(
								"EntVersionUserPersistenceImpl.fetchByentVersionId_userId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EntVersionUser entVersionUser = list.get(0);

					result = entVersionUser;

					cacheResult(entVersionUser);
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
			return (EntVersionUser)result;
		}
	}

	/**
	 * Removes the ent version user where entVersionId = &#63; and userId = &#63; from the database.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the ent version user that was removed
	 */
	@Override
	public EntVersionUser removeByentVersionId_userId(
			long entVersionId, long userId)
		throws NoSuchEntVersionUserException {

		EntVersionUser entVersionUser = findByentVersionId_userId(
			entVersionId, userId);

		return remove(entVersionUser);
	}

	/**
	 * Returns the number of ent version users where entVersionId = &#63; and userId = &#63;.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the number of matching ent version users
	 */
	@Override
	public int countByentVersionId_userId(long entVersionId, long userId) {
		FinderPath finderPath = _finderPathCountByentVersionId_userId;

		Object[] finderArgs = new Object[] {entVersionId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ENTVERSIONUSER_WHERE);

			sb.append(_FINDER_COLUMN_ENTVERSIONID_USERID_ENTVERSIONID_2);

			sb.append(_FINDER_COLUMN_ENTVERSIONID_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(entVersionId);

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

	private static final String
		_FINDER_COLUMN_ENTVERSIONID_USERID_ENTVERSIONID_2 =
			"entVersionUser.entVersionId = ? AND ";

	private static final String _FINDER_COLUMN_ENTVERSIONID_USERID_USERID_2 =
		"entVersionUser.userId = ?";

	public EntVersionUserPersistenceImpl() {
		setModelClass(EntVersionUser.class);

		setModelImplClass(EntVersionUserImpl.class);
		setModelPKClass(long.class);

		setTable(EntVersionUserTable.INSTANCE);
	}

	/**
	 * Caches the ent version user in the entity cache if it is enabled.
	 *
	 * @param entVersionUser the ent version user
	 */
	@Override
	public void cacheResult(EntVersionUser entVersionUser) {
		entityCache.putResult(
			EntVersionUserImpl.class, entVersionUser.getPrimaryKey(),
			entVersionUser);

		finderCache.putResult(
			_finderPathFetchByentVersionId_userId,
			new Object[] {
				entVersionUser.getEntVersionId(), entVersionUser.getUserId()
			},
			entVersionUser);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ent version users in the entity cache if it is enabled.
	 *
	 * @param entVersionUsers the ent version users
	 */
	@Override
	public void cacheResult(List<EntVersionUser> entVersionUsers) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (entVersionUsers.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EntVersionUser entVersionUser : entVersionUsers) {
			if (entityCache.getResult(
					EntVersionUserImpl.class, entVersionUser.getPrimaryKey()) ==
						null) {

				cacheResult(entVersionUser);
			}
		}
	}

	/**
	 * Clears the cache for all ent version users.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntVersionUserImpl.class);

		finderCache.clearCache(EntVersionUserImpl.class);
	}

	/**
	 * Clears the cache for the ent version user.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EntVersionUser entVersionUser) {
		entityCache.removeResult(EntVersionUserImpl.class, entVersionUser);
	}

	@Override
	public void clearCache(List<EntVersionUser> entVersionUsers) {
		for (EntVersionUser entVersionUser : entVersionUsers) {
			entityCache.removeResult(EntVersionUserImpl.class, entVersionUser);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(EntVersionUserImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(EntVersionUserImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		EntVersionUserModelImpl entVersionUserModelImpl) {

		Object[] args = new Object[] {
			entVersionUserModelImpl.getEntVersionId(),
			entVersionUserModelImpl.getUserId()
		};

		finderCache.putResult(
			_finderPathCountByentVersionId_userId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByentVersionId_userId, args,
			entVersionUserModelImpl);
	}

	/**
	 * Creates a new ent version user with the primary key. Does not add the ent version user to the database.
	 *
	 * @param versionUserId the primary key for the new ent version user
	 * @return the new ent version user
	 */
	@Override
	public EntVersionUser create(long versionUserId) {
		EntVersionUser entVersionUser = new EntVersionUserImpl();

		entVersionUser.setNew(true);
		entVersionUser.setPrimaryKey(versionUserId);

		return entVersionUser;
	}

	/**
	 * Removes the ent version user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user that was removed
	 * @throws NoSuchEntVersionUserException if a ent version user with the primary key could not be found
	 */
	@Override
	public EntVersionUser remove(long versionUserId)
		throws NoSuchEntVersionUserException {

		return remove((Serializable)versionUserId);
	}

	/**
	 * Removes the ent version user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ent version user
	 * @return the ent version user that was removed
	 * @throws NoSuchEntVersionUserException if a ent version user with the primary key could not be found
	 */
	@Override
	public EntVersionUser remove(Serializable primaryKey)
		throws NoSuchEntVersionUserException {

		Session session = null;

		try {
			session = openSession();

			EntVersionUser entVersionUser = (EntVersionUser)session.get(
				EntVersionUserImpl.class, primaryKey);

			if (entVersionUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntVersionUserException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(entVersionUser);
		}
		catch (NoSuchEntVersionUserException noSuchEntityException) {
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
	protected EntVersionUser removeImpl(EntVersionUser entVersionUser) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entVersionUser)) {
				entVersionUser = (EntVersionUser)session.get(
					EntVersionUserImpl.class,
					entVersionUser.getPrimaryKeyObj());
			}

			if (entVersionUser != null) {
				session.delete(entVersionUser);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (entVersionUser != null) {
			clearCache(entVersionUser);
		}

		return entVersionUser;
	}

	@Override
	public EntVersionUser updateImpl(EntVersionUser entVersionUser) {
		boolean isNew = entVersionUser.isNew();

		if (!(entVersionUser instanceof EntVersionUserModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(entVersionUser.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					entVersionUser);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in entVersionUser proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EntVersionUser implementation " +
					entVersionUser.getClass());
		}

		EntVersionUserModelImpl entVersionUserModelImpl =
			(EntVersionUserModelImpl)entVersionUser;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(entVersionUser);
			}
			else {
				entVersionUser = (EntVersionUser)session.merge(entVersionUser);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			EntVersionUserImpl.class, entVersionUserModelImpl, false, true);

		cacheUniqueFindersCache(entVersionUserModelImpl);

		if (isNew) {
			entVersionUser.setNew(false);
		}

		entVersionUser.resetOriginalValues();

		return entVersionUser;
	}

	/**
	 * Returns the ent version user with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ent version user
	 * @return the ent version user
	 * @throws NoSuchEntVersionUserException if a ent version user with the primary key could not be found
	 */
	@Override
	public EntVersionUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntVersionUserException {

		EntVersionUser entVersionUser = fetchByPrimaryKey(primaryKey);

		if (entVersionUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntVersionUserException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return entVersionUser;
	}

	/**
	 * Returns the ent version user with the primary key or throws a <code>NoSuchEntVersionUserException</code> if it could not be found.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user
	 * @throws NoSuchEntVersionUserException if a ent version user with the primary key could not be found
	 */
	@Override
	public EntVersionUser findByPrimaryKey(long versionUserId)
		throws NoSuchEntVersionUserException {

		return findByPrimaryKey((Serializable)versionUserId);
	}

	/**
	 * Returns the ent version user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user, or <code>null</code> if a ent version user with the primary key could not be found
	 */
	@Override
	public EntVersionUser fetchByPrimaryKey(long versionUserId) {
		return fetchByPrimaryKey((Serializable)versionUserId);
	}

	/**
	 * Returns all the ent version users.
	 *
	 * @return the ent version users
	 */
	@Override
	public List<EntVersionUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ent version users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent version users
	 * @param end the upper bound of the range of ent version users (not inclusive)
	 * @return the range of ent version users
	 */
	@Override
	public List<EntVersionUser> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ent version users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent version users
	 * @param end the upper bound of the range of ent version users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ent version users
	 */
	@Override
	public List<EntVersionUser> findAll(
		int start, int end,
		OrderByComparator<EntVersionUser> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ent version users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent version users
	 * @param end the upper bound of the range of ent version users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ent version users
	 */
	@Override
	public List<EntVersionUser> findAll(
		int start, int end, OrderByComparator<EntVersionUser> orderByComparator,
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

		List<EntVersionUser> list = null;

		if (useFinderCache) {
			list = (List<EntVersionUser>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ENTVERSIONUSER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ENTVERSIONUSER;

				sql = sql.concat(EntVersionUserModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EntVersionUser>)QueryUtil.list(
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
	 * Removes all the ent version users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EntVersionUser entVersionUser : findAll()) {
			remove(entVersionUser);
		}
	}

	/**
	 * Returns the number of ent version users.
	 *
	 * @return the number of ent version users
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ENTVERSIONUSER);

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
		return "versionUserId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ENTVERSIONUSER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EntVersionUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ent version user persistence.
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

		_finderPathFetchByentVersionId_userId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByentVersionId_userId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"entVersionId", "userId"}, true);

		_finderPathCountByentVersionId_userId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByentVersionId_userId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"entVersionId", "userId"}, false);

		_setEntVersionUserUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setEntVersionUserUtilPersistence(null);

		entityCache.removeCache(EntVersionUserImpl.class.getName());
	}

	private void _setEntVersionUserUtilPersistence(
		EntVersionUserPersistence entVersionUserPersistence) {

		try {
			Field field = EntVersionUserUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, entVersionUserPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = AboutPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = AboutPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AboutPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ENTVERSIONUSER =
		"SELECT entVersionUser FROM EntVersionUser entVersionUser";

	private static final String _SQL_SELECT_ENTVERSIONUSER_WHERE =
		"SELECT entVersionUser FROM EntVersionUser entVersionUser WHERE ";

	private static final String _SQL_COUNT_ENTVERSIONUSER =
		"SELECT COUNT(entVersionUser) FROM EntVersionUser entVersionUser";

	private static final String _SQL_COUNT_ENTVERSIONUSER_WHERE =
		"SELECT COUNT(entVersionUser) FROM EntVersionUser entVersionUser WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "entVersionUser.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EntVersionUser exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No EntVersionUser exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EntVersionUserPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private EntVersionUserModelArgumentsResolver
		_entVersionUserModelArgumentsResolver;

}