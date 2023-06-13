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

package com.weprode.nero.course.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import com.weprode.nero.course.exception.NoSuchSessionContentException;
import com.weprode.nero.course.model.SessionContent;
import com.weprode.nero.course.model.SessionContentTable;
import com.weprode.nero.course.model.impl.SessionContentImpl;
import com.weprode.nero.course.model.impl.SessionContentModelImpl;
import com.weprode.nero.course.service.persistence.SessionContentPersistence;
import com.weprode.nero.course.service.persistence.SessionContentUtil;
import com.weprode.nero.course.service.persistence.impl.constants.CoursePersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the session content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {SessionContentPersistence.class, BasePersistence.class})
public class SessionContentPersistenceImpl
	extends BasePersistenceImpl<SessionContent>
	implements SessionContentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SessionContentUtil</code> to access the session content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SessionContentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SessionContentPersistenceImpl() {
		setModelClass(SessionContent.class);

		setModelImplClass(SessionContentImpl.class);
		setModelPKClass(long.class);

		setTable(SessionContentTable.INSTANCE);
	}

	/**
	 * Caches the session content in the entity cache if it is enabled.
	 *
	 * @param sessionContent the session content
	 */
	@Override
	public void cacheResult(SessionContent sessionContent) {
		entityCache.putResult(
			SessionContentImpl.class, sessionContent.getPrimaryKey(),
			sessionContent);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the session contents in the entity cache if it is enabled.
	 *
	 * @param sessionContents the session contents
	 */
	@Override
	public void cacheResult(List<SessionContent> sessionContents) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sessionContents.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SessionContent sessionContent : sessionContents) {
			if (entityCache.getResult(
					SessionContentImpl.class, sessionContent.getPrimaryKey()) ==
						null) {

				cacheResult(sessionContent);
			}
		}
	}

	/**
	 * Clears the cache for all session contents.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SessionContentImpl.class);

		finderCache.clearCache(SessionContentImpl.class);
	}

	/**
	 * Clears the cache for the session content.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SessionContent sessionContent) {
		entityCache.removeResult(SessionContentImpl.class, sessionContent);
	}

	@Override
	public void clearCache(List<SessionContent> sessionContents) {
		for (SessionContent sessionContent : sessionContents) {
			entityCache.removeResult(SessionContentImpl.class, sessionContent);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SessionContentImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SessionContentImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new session content with the primary key. Does not add the session content to the database.
	 *
	 * @param sessionId the primary key for the new session content
	 * @return the new session content
	 */
	@Override
	public SessionContent create(long sessionId) {
		SessionContent sessionContent = new SessionContentImpl();

		sessionContent.setNew(true);
		sessionContent.setPrimaryKey(sessionId);

		sessionContent.setCompanyId(CompanyThreadLocal.getCompanyId());

		return sessionContent;
	}

	/**
	 * Removes the session content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content that was removed
	 * @throws NoSuchSessionContentException if a session content with the primary key could not be found
	 */
	@Override
	public SessionContent remove(long sessionId)
		throws NoSuchSessionContentException {

		return remove((Serializable)sessionId);
	}

	/**
	 * Removes the session content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the session content
	 * @return the session content that was removed
	 * @throws NoSuchSessionContentException if a session content with the primary key could not be found
	 */
	@Override
	public SessionContent remove(Serializable primaryKey)
		throws NoSuchSessionContentException {

		Session session = null;

		try {
			session = openSession();

			SessionContent sessionContent = (SessionContent)session.get(
				SessionContentImpl.class, primaryKey);

			if (sessionContent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSessionContentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sessionContent);
		}
		catch (NoSuchSessionContentException noSuchEntityException) {
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
	protected SessionContent removeImpl(SessionContent sessionContent) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sessionContent)) {
				sessionContent = (SessionContent)session.get(
					SessionContentImpl.class,
					sessionContent.getPrimaryKeyObj());
			}

			if (sessionContent != null) {
				session.delete(sessionContent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sessionContent != null) {
			clearCache(sessionContent);
		}

		return sessionContent;
	}

	@Override
	public SessionContent updateImpl(SessionContent sessionContent) {
		boolean isNew = sessionContent.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sessionContent);
			}
			else {
				sessionContent = (SessionContent)session.merge(sessionContent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SessionContentImpl.class, sessionContent, false, true);

		if (isNew) {
			sessionContent.setNew(false);
		}

		sessionContent.resetOriginalValues();

		return sessionContent;
	}

	/**
	 * Returns the session content with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the session content
	 * @return the session content
	 * @throws NoSuchSessionContentException if a session content with the primary key could not be found
	 */
	@Override
	public SessionContent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSessionContentException {

		SessionContent sessionContent = fetchByPrimaryKey(primaryKey);

		if (sessionContent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSessionContentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sessionContent;
	}

	/**
	 * Returns the session content with the primary key or throws a <code>NoSuchSessionContentException</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content
	 * @throws NoSuchSessionContentException if a session content with the primary key could not be found
	 */
	@Override
	public SessionContent findByPrimaryKey(long sessionId)
		throws NoSuchSessionContentException {

		return findByPrimaryKey((Serializable)sessionId);
	}

	/**
	 * Returns the session content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the session content
	 * @return the session content, or <code>null</code> if a session content with the primary key could not be found
	 */
	@Override
	public SessionContent fetchByPrimaryKey(long sessionId) {
		return fetchByPrimaryKey((Serializable)sessionId);
	}

	/**
	 * Returns all the session contents.
	 *
	 * @return the session contents
	 */
	@Override
	public List<SessionContent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the session contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session contents
	 * @param end the upper bound of the range of session contents (not inclusive)
	 * @return the range of session contents
	 */
	@Override
	public List<SessionContent> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the session contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session contents
	 * @param end the upper bound of the range of session contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of session contents
	 */
	@Override
	public List<SessionContent> findAll(
		int start, int end,
		OrderByComparator<SessionContent> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the session contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SessionContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session contents
	 * @param end the upper bound of the range of session contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of session contents
	 */
	@Override
	public List<SessionContent> findAll(
		int start, int end, OrderByComparator<SessionContent> orderByComparator,
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

		List<SessionContent> list = null;

		if (useFinderCache) {
			list = (List<SessionContent>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SESSIONCONTENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SESSIONCONTENT;

				sql = sql.concat(SessionContentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SessionContent>)QueryUtil.list(
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
	 * Removes all the session contents from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SessionContent sessionContent : findAll()) {
			remove(sessionContent);
		}
	}

	/**
	 * Returns the number of session contents.
	 *
	 * @return the number of session contents
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SESSIONCONTENT);

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
		return "sessionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SESSIONCONTENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SessionContentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the session content persistence.
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

		_setSessionContentUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSessionContentUtilPersistence(null);

		entityCache.removeCache(SessionContentImpl.class.getName());
	}

	private void _setSessionContentUtilPersistence(
		SessionContentPersistence sessionContentPersistence) {

		try {
			Field field = SessionContentUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, sessionContentPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = CoursePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CoursePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CoursePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SESSIONCONTENT =
		"SELECT sessionContent FROM SessionContent sessionContent";

	private static final String _SQL_COUNT_SESSIONCONTENT =
		"SELECT COUNT(sessionContent) FROM SessionContent sessionContent";

	private static final String _ORDER_BY_ENTITY_ALIAS = "sessionContent.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SessionContent exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SessionContentPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private SessionContentModelArgumentsResolver
		_sessionContentModelArgumentsResolver;

}