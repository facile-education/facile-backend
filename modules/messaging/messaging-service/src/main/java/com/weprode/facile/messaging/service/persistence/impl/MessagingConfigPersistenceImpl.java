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

package com.weprode.facile.messaging.service.persistence.impl;

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
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import com.weprode.facile.messaging.exception.NoSuchConfigException;
import com.weprode.facile.messaging.model.MessagingConfig;
import com.weprode.facile.messaging.model.MessagingConfigTable;
import com.weprode.facile.messaging.model.impl.MessagingConfigImpl;
import com.weprode.facile.messaging.model.impl.MessagingConfigModelImpl;
import com.weprode.facile.messaging.service.persistence.MessagingConfigPersistence;
import com.weprode.facile.messaging.service.persistence.MessagingConfigUtil;
import com.weprode.facile.messaging.service.persistence.impl.constants.MessagingPersistenceConstants;

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
 * The persistence implementation for the messaging config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {MessagingConfigPersistence.class, BasePersistence.class})
public class MessagingConfigPersistenceImpl
	extends BasePersistenceImpl<MessagingConfig>
	implements MessagingConfigPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MessagingConfigUtil</code> to access the messaging config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MessagingConfigImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public MessagingConfigPersistenceImpl() {
		setModelClass(MessagingConfig.class);

		setModelImplClass(MessagingConfigImpl.class);
		setModelPKClass(long.class);

		setTable(MessagingConfigTable.INSTANCE);
	}

	/**
	 * Caches the messaging config in the entity cache if it is enabled.
	 *
	 * @param messagingConfig the messaging config
	 */
	@Override
	public void cacheResult(MessagingConfig messagingConfig) {
		entityCache.putResult(
			MessagingConfigImpl.class, messagingConfig.getPrimaryKey(),
			messagingConfig);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the messaging configs in the entity cache if it is enabled.
	 *
	 * @param messagingConfigs the messaging configs
	 */
	@Override
	public void cacheResult(List<MessagingConfig> messagingConfigs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (messagingConfigs.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MessagingConfig messagingConfig : messagingConfigs) {
			if (entityCache.getResult(
					MessagingConfigImpl.class,
					messagingConfig.getPrimaryKey()) == null) {

				cacheResult(messagingConfig);
			}
		}
	}

	/**
	 * Clears the cache for all messaging configs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MessagingConfigImpl.class);

		finderCache.clearCache(MessagingConfigImpl.class);
	}

	/**
	 * Clears the cache for the messaging config.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MessagingConfig messagingConfig) {
		entityCache.removeResult(MessagingConfigImpl.class, messagingConfig);
	}

	@Override
	public void clearCache(List<MessagingConfig> messagingConfigs) {
		for (MessagingConfig messagingConfig : messagingConfigs) {
			entityCache.removeResult(
				MessagingConfigImpl.class, messagingConfig);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MessagingConfigImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MessagingConfigImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new messaging config with the primary key. Does not add the messaging config to the database.
	 *
	 * @param userId the primary key for the new messaging config
	 * @return the new messaging config
	 */
	@Override
	public MessagingConfig create(long userId) {
		MessagingConfig messagingConfig = new MessagingConfigImpl();

		messagingConfig.setNew(true);
		messagingConfig.setPrimaryKey(userId);

		return messagingConfig;
	}

	/**
	 * Removes the messaging config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config that was removed
	 * @throws NoSuchConfigException if a messaging config with the primary key could not be found
	 */
	@Override
	public MessagingConfig remove(long userId) throws NoSuchConfigException {
		return remove((Serializable)userId);
	}

	/**
	 * Removes the messaging config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the messaging config
	 * @return the messaging config that was removed
	 * @throws NoSuchConfigException if a messaging config with the primary key could not be found
	 */
	@Override
	public MessagingConfig remove(Serializable primaryKey)
		throws NoSuchConfigException {

		Session session = null;

		try {
			session = openSession();

			MessagingConfig messagingConfig = (MessagingConfig)session.get(
				MessagingConfigImpl.class, primaryKey);

			if (messagingConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConfigException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(messagingConfig);
		}
		catch (NoSuchConfigException noSuchEntityException) {
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
	protected MessagingConfig removeImpl(MessagingConfig messagingConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(messagingConfig)) {
				messagingConfig = (MessagingConfig)session.get(
					MessagingConfigImpl.class,
					messagingConfig.getPrimaryKeyObj());
			}

			if (messagingConfig != null) {
				session.delete(messagingConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (messagingConfig != null) {
			clearCache(messagingConfig);
		}

		return messagingConfig;
	}

	@Override
	public MessagingConfig updateImpl(MessagingConfig messagingConfig) {
		boolean isNew = messagingConfig.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(messagingConfig);
			}
			else {
				messagingConfig = (MessagingConfig)session.merge(
					messagingConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MessagingConfigImpl.class, messagingConfig, false, true);

		if (isNew) {
			messagingConfig.setNew(false);
		}

		messagingConfig.resetOriginalValues();

		return messagingConfig;
	}

	/**
	 * Returns the messaging config with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the messaging config
	 * @return the messaging config
	 * @throws NoSuchConfigException if a messaging config with the primary key could not be found
	 */
	@Override
	public MessagingConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConfigException {

		MessagingConfig messagingConfig = fetchByPrimaryKey(primaryKey);

		if (messagingConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConfigException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return messagingConfig;
	}

	/**
	 * Returns the messaging config with the primary key or throws a <code>NoSuchConfigException</code> if it could not be found.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config
	 * @throws NoSuchConfigException if a messaging config with the primary key could not be found
	 */
	@Override
	public MessagingConfig findByPrimaryKey(long userId)
		throws NoSuchConfigException {

		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the messaging config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the messaging config
	 * @return the messaging config, or <code>null</code> if a messaging config with the primary key could not be found
	 */
	@Override
	public MessagingConfig fetchByPrimaryKey(long userId) {
		return fetchByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns all the messaging configs.
	 *
	 * @return the messaging configs
	 */
	@Override
	public List<MessagingConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the messaging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messaging configs
	 * @param end the upper bound of the range of messaging configs (not inclusive)
	 * @return the range of messaging configs
	 */
	@Override
	public List<MessagingConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the messaging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messaging configs
	 * @param end the upper bound of the range of messaging configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of messaging configs
	 */
	@Override
	public List<MessagingConfig> findAll(
		int start, int end,
		OrderByComparator<MessagingConfig> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the messaging configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagingConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messaging configs
	 * @param end the upper bound of the range of messaging configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of messaging configs
	 */
	@Override
	public List<MessagingConfig> findAll(
		int start, int end,
		OrderByComparator<MessagingConfig> orderByComparator,
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

		List<MessagingConfig> list = null;

		if (useFinderCache) {
			list = (List<MessagingConfig>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MESSAGINGCONFIG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MESSAGINGCONFIG;

				sql = sql.concat(MessagingConfigModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MessagingConfig>)QueryUtil.list(
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
	 * Removes all the messaging configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MessagingConfig messagingConfig : findAll()) {
			remove(messagingConfig);
		}
	}

	/**
	 * Returns the number of messaging configs.
	 *
	 * @return the number of messaging configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MESSAGINGCONFIG);

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
		return _SQL_SELECT_MESSAGINGCONFIG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MessagingConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the messaging config persistence.
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

		_setMessagingConfigUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setMessagingConfigUtilPersistence(null);

		entityCache.removeCache(MessagingConfigImpl.class.getName());
	}

	private void _setMessagingConfigUtilPersistence(
		MessagingConfigPersistence messagingConfigPersistence) {

		try {
			Field field = MessagingConfigUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, messagingConfigPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = MessagingPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = MessagingPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = MessagingPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MESSAGINGCONFIG =
		"SELECT messagingConfig FROM MessagingConfig messagingConfig";

	private static final String _SQL_COUNT_MESSAGINGCONFIG =
		"SELECT COUNT(messagingConfig) FROM MessagingConfig messagingConfig";

	private static final String _ORDER_BY_ENTITY_ALIAS = "messagingConfig.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MessagingConfig exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		MessagingConfigPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}