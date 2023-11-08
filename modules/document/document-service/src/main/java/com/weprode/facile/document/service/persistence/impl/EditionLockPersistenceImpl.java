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

package com.weprode.facile.document.service.persistence.impl;

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

import com.weprode.facile.document.exception.NoSuchEditionLockException;
import com.weprode.facile.document.model.EditionLock;
import com.weprode.facile.document.model.EditionLockTable;
import com.weprode.facile.document.model.impl.EditionLockImpl;
import com.weprode.facile.document.model.impl.EditionLockModelImpl;
import com.weprode.facile.document.service.persistence.EditionLockPersistence;
import com.weprode.facile.document.service.persistence.EditionLockUtil;
import com.weprode.facile.document.service.persistence.impl.constants.DocumentPersistenceConstants;

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
 * The persistence implementation for the edition lock service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {EditionLockPersistence.class, BasePersistence.class})
public class EditionLockPersistenceImpl
	extends BasePersistenceImpl<EditionLock> implements EditionLockPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EditionLockUtil</code> to access the edition lock persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EditionLockImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByfileId_userId;
	private FinderPath _finderPathCountByfileId_userId;

	/**
	 * Returns the edition lock where fileId = &#63; and userId = &#63; or throws a <code>NoSuchEditionLockException</code> if it could not be found.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the matching edition lock
	 * @throws NoSuchEditionLockException if a matching edition lock could not be found
	 */
	@Override
	public EditionLock findByfileId_userId(long fileId, long userId)
		throws NoSuchEditionLockException {

		EditionLock editionLock = fetchByfileId_userId(fileId, userId);

		if (editionLock == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("fileId=");
			sb.append(fileId);

			sb.append(", userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchEditionLockException(sb.toString());
		}

		return editionLock;
	}

	/**
	 * Returns the edition lock where fileId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the matching edition lock, or <code>null</code> if a matching edition lock could not be found
	 */
	@Override
	public EditionLock fetchByfileId_userId(long fileId, long userId) {
		return fetchByfileId_userId(fileId, userId, true);
	}

	/**
	 * Returns the edition lock where fileId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching edition lock, or <code>null</code> if a matching edition lock could not be found
	 */
	@Override
	public EditionLock fetchByfileId_userId(
		long fileId, long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {fileId, userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = dummyFinderCache.getResult(
				_finderPathFetchByfileId_userId, finderArgs, this);
		}

		if (result instanceof EditionLock) {
			EditionLock editionLock = (EditionLock)result;

			if ((fileId != editionLock.getFileId()) ||
				(userId != editionLock.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_EDITIONLOCK_WHERE);

			sb.append(_FINDER_COLUMN_FILEID_USERID_FILEID_2);

			sb.append(_FINDER_COLUMN_FILEID_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileId);

				queryPos.add(userId);

				List<EditionLock> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						dummyFinderCache.putResult(
							_finderPathFetchByfileId_userId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {fileId, userId};
							}

							_log.warn(
								"EditionLockPersistenceImpl.fetchByfileId_userId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EditionLock editionLock = list.get(0);

					result = editionLock;

					cacheResult(editionLock);
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
			return (EditionLock)result;
		}
	}

	/**
	 * Removes the edition lock where fileId = &#63; and userId = &#63; from the database.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the edition lock that was removed
	 */
	@Override
	public EditionLock removeByfileId_userId(long fileId, long userId)
		throws NoSuchEditionLockException {

		EditionLock editionLock = findByfileId_userId(fileId, userId);

		return remove(editionLock);
	}

	/**
	 * Returns the number of edition locks where fileId = &#63; and userId = &#63;.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the number of matching edition locks
	 */
	@Override
	public int countByfileId_userId(long fileId, long userId) {
		FinderPath finderPath = _finderPathCountByfileId_userId;

		Object[] finderArgs = new Object[] {fileId, userId};

		Long count = (Long)dummyFinderCache.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_EDITIONLOCK_WHERE);

			sb.append(_FINDER_COLUMN_FILEID_USERID_FILEID_2);

			sb.append(_FINDER_COLUMN_FILEID_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileId);

				queryPos.add(userId);

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

	private static final String _FINDER_COLUMN_FILEID_USERID_FILEID_2 =
		"editionLock.fileId = ? AND ";

	private static final String _FINDER_COLUMN_FILEID_USERID_USERID_2 =
		"editionLock.userId = ?";

	public EditionLockPersistenceImpl() {
		setModelClass(EditionLock.class);

		setModelImplClass(EditionLockImpl.class);
		setModelPKClass(long.class);

		setTable(EditionLockTable.INSTANCE);
	}

	/**
	 * Caches the edition lock in the entity cache if it is enabled.
	 *
	 * @param editionLock the edition lock
	 */
	@Override
	public void cacheResult(EditionLock editionLock) {
		dummyEntityCache.putResult(
			EditionLockImpl.class, editionLock.getPrimaryKey(), editionLock);

		dummyFinderCache.putResult(
			_finderPathFetchByfileId_userId,
			new Object[] {editionLock.getFileId(), editionLock.getUserId()},
			editionLock);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the edition locks in the entity cache if it is enabled.
	 *
	 * @param editionLocks the edition locks
	 */
	@Override
	public void cacheResult(List<EditionLock> editionLocks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (editionLocks.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EditionLock editionLock : editionLocks) {
			if (dummyEntityCache.getResult(
					EditionLockImpl.class, editionLock.getPrimaryKey()) ==
						null) {

				cacheResult(editionLock);
			}
		}
	}

	/**
	 * Clears the cache for all edition locks.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(EditionLockImpl.class);

		dummyFinderCache.clearCache(EditionLockImpl.class);
	}

	/**
	 * Clears the cache for the edition lock.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EditionLock editionLock) {
		dummyEntityCache.removeResult(EditionLockImpl.class, editionLock);
	}

	@Override
	public void clearCache(List<EditionLock> editionLocks) {
		for (EditionLock editionLock : editionLocks) {
			dummyEntityCache.removeResult(EditionLockImpl.class, editionLock);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(EditionLockImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(EditionLockImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		EditionLockModelImpl editionLockModelImpl) {

		Object[] args = new Object[] {
			editionLockModelImpl.getFileId(), editionLockModelImpl.getUserId()
		};

		dummyFinderCache.putResult(
			_finderPathCountByfileId_userId, args, Long.valueOf(1));
		dummyFinderCache.putResult(
			_finderPathFetchByfileId_userId, args, editionLockModelImpl);
	}

	/**
	 * Creates a new edition lock with the primary key. Does not add the edition lock to the database.
	 *
	 * @param fileId the primary key for the new edition lock
	 * @return the new edition lock
	 */
	@Override
	public EditionLock create(long fileId) {
		EditionLock editionLock = new EditionLockImpl();

		editionLock.setNew(true);
		editionLock.setPrimaryKey(fileId);

		return editionLock;
	}

	/**
	 * Removes the edition lock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock that was removed
	 * @throws NoSuchEditionLockException if a edition lock with the primary key could not be found
	 */
	@Override
	public EditionLock remove(long fileId) throws NoSuchEditionLockException {
		return remove((Serializable)fileId);
	}

	/**
	 * Removes the edition lock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the edition lock
	 * @return the edition lock that was removed
	 * @throws NoSuchEditionLockException if a edition lock with the primary key could not be found
	 */
	@Override
	public EditionLock remove(Serializable primaryKey)
		throws NoSuchEditionLockException {

		Session session = null;

		try {
			session = openSession();

			EditionLock editionLock = (EditionLock)session.get(
				EditionLockImpl.class, primaryKey);

			if (editionLock == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEditionLockException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(editionLock);
		}
		catch (NoSuchEditionLockException noSuchEntityException) {
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
	protected EditionLock removeImpl(EditionLock editionLock) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(editionLock)) {
				editionLock = (EditionLock)session.get(
					EditionLockImpl.class, editionLock.getPrimaryKeyObj());
			}

			if (editionLock != null) {
				session.delete(editionLock);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (editionLock != null) {
			clearCache(editionLock);
		}

		return editionLock;
	}

	@Override
	public EditionLock updateImpl(EditionLock editionLock) {
		boolean isNew = editionLock.isNew();

		if (!(editionLock instanceof EditionLockModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(editionLock.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(editionLock);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in editionLock proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EditionLock implementation " +
					editionLock.getClass());
		}

		EditionLockModelImpl editionLockModelImpl =
			(EditionLockModelImpl)editionLock;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(editionLock);
			}
			else {
				editionLock = (EditionLock)session.merge(editionLock);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			EditionLockImpl.class, editionLockModelImpl, false, true);

		cacheUniqueFindersCache(editionLockModelImpl);

		if (isNew) {
			editionLock.setNew(false);
		}

		editionLock.resetOriginalValues();

		return editionLock;
	}

	/**
	 * Returns the edition lock with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the edition lock
	 * @return the edition lock
	 * @throws NoSuchEditionLockException if a edition lock with the primary key could not be found
	 */
	@Override
	public EditionLock findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEditionLockException {

		EditionLock editionLock = fetchByPrimaryKey(primaryKey);

		if (editionLock == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEditionLockException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return editionLock;
	}

	/**
	 * Returns the edition lock with the primary key or throws a <code>NoSuchEditionLockException</code> if it could not be found.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock
	 * @throws NoSuchEditionLockException if a edition lock with the primary key could not be found
	 */
	@Override
	public EditionLock findByPrimaryKey(long fileId)
		throws NoSuchEditionLockException {

		return findByPrimaryKey((Serializable)fileId);
	}

	/**
	 * Returns the edition lock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock, or <code>null</code> if a edition lock with the primary key could not be found
	 */
	@Override
	public EditionLock fetchByPrimaryKey(long fileId) {
		return fetchByPrimaryKey((Serializable)fileId);
	}

	/**
	 * Returns all the edition locks.
	 *
	 * @return the edition locks
	 */
	@Override
	public List<EditionLock> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the edition locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition locks
	 * @param end the upper bound of the range of edition locks (not inclusive)
	 * @return the range of edition locks
	 */
	@Override
	public List<EditionLock> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the edition locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition locks
	 * @param end the upper bound of the range of edition locks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of edition locks
	 */
	@Override
	public List<EditionLock> findAll(
		int start, int end, OrderByComparator<EditionLock> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the edition locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition locks
	 * @param end the upper bound of the range of edition locks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of edition locks
	 */
	@Override
	public List<EditionLock> findAll(
		int start, int end, OrderByComparator<EditionLock> orderByComparator,
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

		List<EditionLock> list = null;

		if (useFinderCache) {
			list = (List<EditionLock>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EDITIONLOCK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EDITIONLOCK;

				sql = sql.concat(EditionLockModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EditionLock>)QueryUtil.list(
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
	 * Removes all the edition locks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EditionLock editionLock : findAll()) {
			remove(editionLock);
		}
	}

	/**
	 * Returns the number of edition locks.
	 *
	 * @return the number of edition locks
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EDITIONLOCK);

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
		return "fileId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EDITIONLOCK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EditionLockModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the edition lock persistence.
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

		_finderPathFetchByfileId_userId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByfileId_userId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"fileId", "userId"}, true);

		_finderPathCountByfileId_userId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByfileId_userId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"fileId", "userId"}, false);

		_setEditionLockUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setEditionLockUtilPersistence(null);

		dummyEntityCache.removeCache(EditionLockImpl.class.getName());
	}

	private void _setEditionLockUtilPersistence(
		EditionLockPersistence editionLockPersistence) {

		try {
			Field field = EditionLockUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, editionLockPersistence);
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

	private static final String _SQL_SELECT_EDITIONLOCK =
		"SELECT editionLock FROM EditionLock editionLock";

	private static final String _SQL_SELECT_EDITIONLOCK_WHERE =
		"SELECT editionLock FROM EditionLock editionLock WHERE ";

	private static final String _SQL_COUNT_EDITIONLOCK =
		"SELECT COUNT(editionLock) FROM EditionLock editionLock";

	private static final String _SQL_COUNT_EDITIONLOCK_WHERE =
		"SELECT COUNT(editionLock) FROM EditionLock editionLock WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "editionLock.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EditionLock exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No EditionLock exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EditionLockPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}