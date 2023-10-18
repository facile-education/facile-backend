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

package com.weprode.nero.eel.synchronization.service.persistence.impl;

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

import com.weprode.nero.eel.synchronization.exception.NoSuchParentSynchroException;
import com.weprode.nero.eel.synchronization.model.ParentSynchro;
import com.weprode.nero.eel.synchronization.model.ParentSynchroTable;
import com.weprode.nero.eel.synchronization.model.impl.ParentSynchroImpl;
import com.weprode.nero.eel.synchronization.model.impl.ParentSynchroModelImpl;
import com.weprode.nero.eel.synchronization.service.persistence.ParentSynchroPersistence;
import com.weprode.nero.eel.synchronization.service.persistence.ParentSynchroUtil;
import com.weprode.nero.eel.synchronization.service.persistence.impl.constants.EELSynchroPersistenceConstants;

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
 * The persistence implementation for the parent synchro service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ParentSynchroPersistence.class, BasePersistence.class})
public class ParentSynchroPersistenceImpl
	extends BasePersistenceImpl<ParentSynchro>
	implements ParentSynchroPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ParentSynchroUtil</code> to access the parent synchro persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ParentSynchroImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByschoolId;
	private FinderPath _finderPathCountByschoolId;

	/**
	 * Returns the parent synchro where schoolId = &#63; or throws a <code>NoSuchParentSynchroException</code> if it could not be found.
	 *
	 * @param schoolId the school ID
	 * @return the matching parent synchro
	 * @throws NoSuchParentSynchroException if a matching parent synchro could not be found
	 */
	@Override
	public ParentSynchro findByschoolId(long schoolId)
		throws NoSuchParentSynchroException {

		ParentSynchro parentSynchro = fetchByschoolId(schoolId);

		if (parentSynchro == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("schoolId=");
			sb.append(schoolId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchParentSynchroException(sb.toString());
		}

		return parentSynchro;
	}

	/**
	 * Returns the parent synchro where schoolId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param schoolId the school ID
	 * @return the matching parent synchro, or <code>null</code> if a matching parent synchro could not be found
	 */
	@Override
	public ParentSynchro fetchByschoolId(long schoolId) {
		return fetchByschoolId(schoolId, true);
	}

	/**
	 * Returns the parent synchro where schoolId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param schoolId the school ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching parent synchro, or <code>null</code> if a matching parent synchro could not be found
	 */
	@Override
	public ParentSynchro fetchByschoolId(
		long schoolId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {schoolId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByschoolId, finderArgs, this);
		}

		if (result instanceof ParentSynchro) {
			ParentSynchro parentSynchro = (ParentSynchro)result;

			if (schoolId != parentSynchro.getSchoolId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PARENTSYNCHRO_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

				List<ParentSynchro> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByschoolId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {schoolId};
							}

							_log.warn(
								"ParentSynchroPersistenceImpl.fetchByschoolId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ParentSynchro parentSynchro = list.get(0);

					result = parentSynchro;

					cacheResult(parentSynchro);
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
			return (ParentSynchro)result;
		}
	}

	/**
	 * Removes the parent synchro where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @return the parent synchro that was removed
	 */
	@Override
	public ParentSynchro removeByschoolId(long schoolId)
		throws NoSuchParentSynchroException {

		ParentSynchro parentSynchro = findByschoolId(schoolId);

		return remove(parentSynchro);
	}

	/**
	 * Returns the number of parent synchros where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching parent synchros
	 */
	@Override
	public int countByschoolId(long schoolId) {
		FinderPath finderPath = _finderPathCountByschoolId;

		Object[] finderArgs = new Object[] {schoolId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PARENTSYNCHRO_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLID_SCHOOLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolId);

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

	private static final String _FINDER_COLUMN_SCHOOLID_SCHOOLID_2 =
		"parentSynchro.schoolId = ?";

	public ParentSynchroPersistenceImpl() {
		setModelClass(ParentSynchro.class);

		setModelImplClass(ParentSynchroImpl.class);
		setModelPKClass(long.class);

		setTable(ParentSynchroTable.INSTANCE);
	}

	/**
	 * Caches the parent synchro in the entity cache if it is enabled.
	 *
	 * @param parentSynchro the parent synchro
	 */
	@Override
	public void cacheResult(ParentSynchro parentSynchro) {
		entityCache.putResult(
			ParentSynchroImpl.class, parentSynchro.getPrimaryKey(),
			parentSynchro);

		finderCache.putResult(
			_finderPathFetchByschoolId,
			new Object[] {parentSynchro.getSchoolId()}, parentSynchro);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the parent synchros in the entity cache if it is enabled.
	 *
	 * @param parentSynchros the parent synchros
	 */
	@Override
	public void cacheResult(List<ParentSynchro> parentSynchros) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (parentSynchros.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ParentSynchro parentSynchro : parentSynchros) {
			if (entityCache.getResult(
					ParentSynchroImpl.class, parentSynchro.getPrimaryKey()) ==
						null) {

				cacheResult(parentSynchro);
			}
		}
	}

	/**
	 * Clears the cache for all parent synchros.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ParentSynchroImpl.class);

		finderCache.clearCache(ParentSynchroImpl.class);
	}

	/**
	 * Clears the cache for the parent synchro.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ParentSynchro parentSynchro) {
		entityCache.removeResult(ParentSynchroImpl.class, parentSynchro);
	}

	@Override
	public void clearCache(List<ParentSynchro> parentSynchros) {
		for (ParentSynchro parentSynchro : parentSynchros) {
			entityCache.removeResult(ParentSynchroImpl.class, parentSynchro);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ParentSynchroImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ParentSynchroImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ParentSynchroModelImpl parentSynchroModelImpl) {

		Object[] args = new Object[] {parentSynchroModelImpl.getSchoolId()};

		finderCache.putResult(
			_finderPathCountByschoolId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByschoolId, args, parentSynchroModelImpl);
	}

	/**
	 * Creates a new parent synchro with the primary key. Does not add the parent synchro to the database.
	 *
	 * @param schoolId the primary key for the new parent synchro
	 * @return the new parent synchro
	 */
	@Override
	public ParentSynchro create(long schoolId) {
		ParentSynchro parentSynchro = new ParentSynchroImpl();

		parentSynchro.setNew(true);
		parentSynchro.setPrimaryKey(schoolId);

		return parentSynchro;
	}

	/**
	 * Removes the parent synchro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro that was removed
	 * @throws NoSuchParentSynchroException if a parent synchro with the primary key could not be found
	 */
	@Override
	public ParentSynchro remove(long schoolId)
		throws NoSuchParentSynchroException {

		return remove((Serializable)schoolId);
	}

	/**
	 * Removes the parent synchro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the parent synchro
	 * @return the parent synchro that was removed
	 * @throws NoSuchParentSynchroException if a parent synchro with the primary key could not be found
	 */
	@Override
	public ParentSynchro remove(Serializable primaryKey)
		throws NoSuchParentSynchroException {

		Session session = null;

		try {
			session = openSession();

			ParentSynchro parentSynchro = (ParentSynchro)session.get(
				ParentSynchroImpl.class, primaryKey);

			if (parentSynchro == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchParentSynchroException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(parentSynchro);
		}
		catch (NoSuchParentSynchroException noSuchEntityException) {
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
	protected ParentSynchro removeImpl(ParentSynchro parentSynchro) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(parentSynchro)) {
				parentSynchro = (ParentSynchro)session.get(
					ParentSynchroImpl.class, parentSynchro.getPrimaryKeyObj());
			}

			if (parentSynchro != null) {
				session.delete(parentSynchro);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (parentSynchro != null) {
			clearCache(parentSynchro);
		}

		return parentSynchro;
	}

	@Override
	public ParentSynchro updateImpl(ParentSynchro parentSynchro) {
		boolean isNew = parentSynchro.isNew();

		if (!(parentSynchro instanceof ParentSynchroModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(parentSynchro.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					parentSynchro);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in parentSynchro proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ParentSynchro implementation " +
					parentSynchro.getClass());
		}

		ParentSynchroModelImpl parentSynchroModelImpl =
			(ParentSynchroModelImpl)parentSynchro;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(parentSynchro);
			}
			else {
				parentSynchro = (ParentSynchro)session.merge(parentSynchro);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ParentSynchroImpl.class, parentSynchroModelImpl, false, true);

		cacheUniqueFindersCache(parentSynchroModelImpl);

		if (isNew) {
			parentSynchro.setNew(false);
		}

		parentSynchro.resetOriginalValues();

		return parentSynchro;
	}

	/**
	 * Returns the parent synchro with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the parent synchro
	 * @return the parent synchro
	 * @throws NoSuchParentSynchroException if a parent synchro with the primary key could not be found
	 */
	@Override
	public ParentSynchro findByPrimaryKey(Serializable primaryKey)
		throws NoSuchParentSynchroException {

		ParentSynchro parentSynchro = fetchByPrimaryKey(primaryKey);

		if (parentSynchro == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchParentSynchroException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return parentSynchro;
	}

	/**
	 * Returns the parent synchro with the primary key or throws a <code>NoSuchParentSynchroException</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro
	 * @throws NoSuchParentSynchroException if a parent synchro with the primary key could not be found
	 */
	@Override
	public ParentSynchro findByPrimaryKey(long schoolId)
		throws NoSuchParentSynchroException {

		return findByPrimaryKey((Serializable)schoolId);
	}

	/**
	 * Returns the parent synchro with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro, or <code>null</code> if a parent synchro with the primary key could not be found
	 */
	@Override
	public ParentSynchro fetchByPrimaryKey(long schoolId) {
		return fetchByPrimaryKey((Serializable)schoolId);
	}

	/**
	 * Returns all the parent synchros.
	 *
	 * @return the parent synchros
	 */
	@Override
	public List<ParentSynchro> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @return the range of parent synchros
	 */
	@Override
	public List<ParentSynchro> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of parent synchros
	 */
	@Override
	public List<ParentSynchro> findAll(
		int start, int end,
		OrderByComparator<ParentSynchro> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of parent synchros
	 */
	@Override
	public List<ParentSynchro> findAll(
		int start, int end, OrderByComparator<ParentSynchro> orderByComparator,
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

		List<ParentSynchro> list = null;

		if (useFinderCache) {
			list = (List<ParentSynchro>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PARENTSYNCHRO);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PARENTSYNCHRO;

				sql = sql.concat(ParentSynchroModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ParentSynchro>)QueryUtil.list(
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
	 * Removes all the parent synchros from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ParentSynchro parentSynchro : findAll()) {
			remove(parentSynchro);
		}
	}

	/**
	 * Returns the number of parent synchros.
	 *
	 * @return the number of parent synchros
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PARENTSYNCHRO);

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
		return "schoolId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PARENTSYNCHRO;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ParentSynchroModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the parent synchro persistence.
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

		_finderPathFetchByschoolId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByschoolId",
			new String[] {Long.class.getName()}, new String[] {"schoolId"},
			true);

		_finderPathCountByschoolId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByschoolId",
			new String[] {Long.class.getName()}, new String[] {"schoolId"},
			false);

		_setParentSynchroUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setParentSynchroUtilPersistence(null);

		entityCache.removeCache(ParentSynchroImpl.class.getName());
	}

	private void _setParentSynchroUtilPersistence(
		ParentSynchroPersistence parentSynchroPersistence) {

		try {
			Field field = ParentSynchroUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, parentSynchroPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = EELSynchroPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = EELSynchroPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = EELSynchroPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PARENTSYNCHRO =
		"SELECT parentSynchro FROM ParentSynchro parentSynchro";

	private static final String _SQL_SELECT_PARENTSYNCHRO_WHERE =
		"SELECT parentSynchro FROM ParentSynchro parentSynchro WHERE ";

	private static final String _SQL_COUNT_PARENTSYNCHRO =
		"SELECT COUNT(parentSynchro) FROM ParentSynchro parentSynchro";

	private static final String _SQL_COUNT_PARENTSYNCHRO_WHERE =
		"SELECT COUNT(parentSynchro) FROM ParentSynchro parentSynchro WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "parentSynchro.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ParentSynchro exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ParentSynchro exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ParentSynchroPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}