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

import com.weprode.nero.about.exception.NoSuchEntVersionException;
import com.weprode.nero.about.model.EntVersion;
import com.weprode.nero.about.model.EntVersionTable;
import com.weprode.nero.about.model.impl.EntVersionImpl;
import com.weprode.nero.about.model.impl.EntVersionModelImpl;
import com.weprode.nero.about.service.persistence.EntVersionPersistence;
import com.weprode.nero.about.service.persistence.EntVersionUtil;
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
 * The persistence implementation for the ent version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {EntVersionPersistence.class, BasePersistence.class})
public class EntVersionPersistenceImpl
	extends BasePersistenceImpl<EntVersion> implements EntVersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EntVersionUtil</code> to access the ent version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EntVersionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByisLast;
	private FinderPath _finderPathCountByisLast;

	/**
	 * Returns the ent version where isLast = &#63; or throws a <code>NoSuchEntVersionException</code> if it could not be found.
	 *
	 * @param isLast the is last
	 * @return the matching ent version
	 * @throws NoSuchEntVersionException if a matching ent version could not be found
	 */
	@Override
	public EntVersion findByisLast(boolean isLast)
		throws NoSuchEntVersionException {

		EntVersion entVersion = fetchByisLast(isLast);

		if (entVersion == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("isLast=");
			sb.append(isLast);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchEntVersionException(sb.toString());
		}

		return entVersion;
	}

	/**
	 * Returns the ent version where isLast = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param isLast the is last
	 * @return the matching ent version, or <code>null</code> if a matching ent version could not be found
	 */
	@Override
	public EntVersion fetchByisLast(boolean isLast) {
		return fetchByisLast(isLast, true);
	}

	/**
	 * Returns the ent version where isLast = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param isLast the is last
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ent version, or <code>null</code> if a matching ent version could not be found
	 */
	@Override
	public EntVersion fetchByisLast(boolean isLast, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {isLast};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByisLast, finderArgs);
		}

		if (result instanceof EntVersion) {
			EntVersion entVersion = (EntVersion)result;

			if (isLast != entVersion.isIsLast()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_ENTVERSION_WHERE);

			sb.append(_FINDER_COLUMN_ISLAST_ISLAST_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isLast);

				List<EntVersion> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByisLast, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {isLast};
							}

							_log.warn(
								"EntVersionPersistenceImpl.fetchByisLast(boolean, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EntVersion entVersion = list.get(0);

					result = entVersion;

					cacheResult(entVersion);
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
			return (EntVersion)result;
		}
	}

	/**
	 * Removes the ent version where isLast = &#63; from the database.
	 *
	 * @param isLast the is last
	 * @return the ent version that was removed
	 */
	@Override
	public EntVersion removeByisLast(boolean isLast)
		throws NoSuchEntVersionException {

		EntVersion entVersion = findByisLast(isLast);

		return remove(entVersion);
	}

	/**
	 * Returns the number of ent versions where isLast = &#63;.
	 *
	 * @param isLast the is last
	 * @return the number of matching ent versions
	 */
	@Override
	public int countByisLast(boolean isLast) {
		FinderPath finderPath = _finderPathCountByisLast;

		Object[] finderArgs = new Object[] {isLast};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ENTVERSION_WHERE);

			sb.append(_FINDER_COLUMN_ISLAST_ISLAST_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isLast);

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

	private static final String _FINDER_COLUMN_ISLAST_ISLAST_2 =
		"entVersion.isLast = ?";

	public EntVersionPersistenceImpl() {
		setModelClass(EntVersion.class);

		setModelImplClass(EntVersionImpl.class);
		setModelPKClass(long.class);

		setTable(EntVersionTable.INSTANCE);
	}

	/**
	 * Caches the ent version in the entity cache if it is enabled.
	 *
	 * @param entVersion the ent version
	 */
	@Override
	public void cacheResult(EntVersion entVersion) {
		entityCache.putResult(
			EntVersionImpl.class, entVersion.getPrimaryKey(), entVersion);

		finderCache.putResult(
			_finderPathFetchByisLast, new Object[] {entVersion.isIsLast()},
			entVersion);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ent versions in the entity cache if it is enabled.
	 *
	 * @param entVersions the ent versions
	 */
	@Override
	public void cacheResult(List<EntVersion> entVersions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (entVersions.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EntVersion entVersion : entVersions) {
			if (entityCache.getResult(
					EntVersionImpl.class, entVersion.getPrimaryKey()) == null) {

				cacheResult(entVersion);
			}
		}
	}

	/**
	 * Clears the cache for all ent versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntVersionImpl.class);

		finderCache.clearCache(EntVersionImpl.class);
	}

	/**
	 * Clears the cache for the ent version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EntVersion entVersion) {
		entityCache.removeResult(EntVersionImpl.class, entVersion);
	}

	@Override
	public void clearCache(List<EntVersion> entVersions) {
		for (EntVersion entVersion : entVersions) {
			entityCache.removeResult(EntVersionImpl.class, entVersion);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(EntVersionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(EntVersionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		EntVersionModelImpl entVersionModelImpl) {

		Object[] args = new Object[] {entVersionModelImpl.isIsLast()};

		finderCache.putResult(_finderPathCountByisLast, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByisLast, args, entVersionModelImpl);
	}

	/**
	 * Creates a new ent version with the primary key. Does not add the ent version to the database.
	 *
	 * @param entVersionId the primary key for the new ent version
	 * @return the new ent version
	 */
	@Override
	public EntVersion create(long entVersionId) {
		EntVersion entVersion = new EntVersionImpl();

		entVersion.setNew(true);
		entVersion.setPrimaryKey(entVersionId);

		return entVersion;
	}

	/**
	 * Removes the ent version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version that was removed
	 * @throws NoSuchEntVersionException if a ent version with the primary key could not be found
	 */
	@Override
	public EntVersion remove(long entVersionId)
		throws NoSuchEntVersionException {

		return remove((Serializable)entVersionId);
	}

	/**
	 * Removes the ent version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ent version
	 * @return the ent version that was removed
	 * @throws NoSuchEntVersionException if a ent version with the primary key could not be found
	 */
	@Override
	public EntVersion remove(Serializable primaryKey)
		throws NoSuchEntVersionException {

		Session session = null;

		try {
			session = openSession();

			EntVersion entVersion = (EntVersion)session.get(
				EntVersionImpl.class, primaryKey);

			if (entVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(entVersion);
		}
		catch (NoSuchEntVersionException noSuchEntityException) {
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
	protected EntVersion removeImpl(EntVersion entVersion) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entVersion)) {
				entVersion = (EntVersion)session.get(
					EntVersionImpl.class, entVersion.getPrimaryKeyObj());
			}

			if (entVersion != null) {
				session.delete(entVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (entVersion != null) {
			clearCache(entVersion);
		}

		return entVersion;
	}

	@Override
	public EntVersion updateImpl(EntVersion entVersion) {
		boolean isNew = entVersion.isNew();

		if (!(entVersion instanceof EntVersionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(entVersion.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(entVersion);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in entVersion proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EntVersion implementation " +
					entVersion.getClass());
		}

		EntVersionModelImpl entVersionModelImpl =
			(EntVersionModelImpl)entVersion;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(entVersion);
			}
			else {
				entVersion = (EntVersion)session.merge(entVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			EntVersionImpl.class, entVersionModelImpl, false, true);

		cacheUniqueFindersCache(entVersionModelImpl);

		if (isNew) {
			entVersion.setNew(false);
		}

		entVersion.resetOriginalValues();

		return entVersion;
	}

	/**
	 * Returns the ent version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ent version
	 * @return the ent version
	 * @throws NoSuchEntVersionException if a ent version with the primary key could not be found
	 */
	@Override
	public EntVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntVersionException {

		EntVersion entVersion = fetchByPrimaryKey(primaryKey);

		if (entVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return entVersion;
	}

	/**
	 * Returns the ent version with the primary key or throws a <code>NoSuchEntVersionException</code> if it could not be found.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version
	 * @throws NoSuchEntVersionException if a ent version with the primary key could not be found
	 */
	@Override
	public EntVersion findByPrimaryKey(long entVersionId)
		throws NoSuchEntVersionException {

		return findByPrimaryKey((Serializable)entVersionId);
	}

	/**
	 * Returns the ent version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version, or <code>null</code> if a ent version with the primary key could not be found
	 */
	@Override
	public EntVersion fetchByPrimaryKey(long entVersionId) {
		return fetchByPrimaryKey((Serializable)entVersionId);
	}

	/**
	 * Returns all the ent versions.
	 *
	 * @return the ent versions
	 */
	@Override
	public List<EntVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @return the range of ent versions
	 */
	@Override
	public List<EntVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ent versions
	 */
	@Override
	public List<EntVersion> findAll(
		int start, int end, OrderByComparator<EntVersion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ent versions
	 */
	@Override
	public List<EntVersion> findAll(
		int start, int end, OrderByComparator<EntVersion> orderByComparator,
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

		List<EntVersion> list = null;

		if (useFinderCache) {
			list = (List<EntVersion>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ENTVERSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ENTVERSION;

				sql = sql.concat(EntVersionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EntVersion>)QueryUtil.list(
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
	 * Removes all the ent versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EntVersion entVersion : findAll()) {
			remove(entVersion);
		}
	}

	/**
	 * Returns the number of ent versions.
	 *
	 * @return the number of ent versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ENTVERSION);

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
		return "entVersionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ENTVERSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EntVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ent version persistence.
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

		_finderPathFetchByisLast = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByisLast",
			new String[] {Boolean.class.getName()}, new String[] {"isLast"},
			true);

		_finderPathCountByisLast = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByisLast",
			new String[] {Boolean.class.getName()}, new String[] {"isLast"},
			false);

		_setEntVersionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setEntVersionUtilPersistence(null);

		entityCache.removeCache(EntVersionImpl.class.getName());
	}

	private void _setEntVersionUtilPersistence(
		EntVersionPersistence entVersionPersistence) {

		try {
			Field field = EntVersionUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, entVersionPersistence);
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

	private static final String _SQL_SELECT_ENTVERSION =
		"SELECT entVersion FROM EntVersion entVersion";

	private static final String _SQL_SELECT_ENTVERSION_WHERE =
		"SELECT entVersion FROM EntVersion entVersion WHERE ";

	private static final String _SQL_COUNT_ENTVERSION =
		"SELECT COUNT(entVersion) FROM EntVersion entVersion";

	private static final String _SQL_COUNT_ENTVERSION_WHERE =
		"SELECT COUNT(entVersion) FROM EntVersion entVersion WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "entVersion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EntVersion exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No EntVersion exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EntVersionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private EntVersionModelArgumentsResolver _entVersionModelArgumentsResolver;

}