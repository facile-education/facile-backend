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

package com.weprode.nero.schedule.service.persistence.impl;

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

import com.weprode.nero.schedule.exception.NoSuchConfigurationException;
import com.weprode.nero.schedule.model.ScheduleConfiguration;
import com.weprode.nero.schedule.model.ScheduleConfigurationTable;
import com.weprode.nero.schedule.model.impl.ScheduleConfigurationImpl;
import com.weprode.nero.schedule.model.impl.ScheduleConfigurationModelImpl;
import com.weprode.nero.schedule.service.persistence.ScheduleConfigurationPersistence;
import com.weprode.nero.schedule.service.persistence.ScheduleConfigurationUtil;
import com.weprode.nero.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

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
 * The persistence implementation for the schedule configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {ScheduleConfigurationPersistence.class, BasePersistence.class}
)
public class ScheduleConfigurationPersistenceImpl
	extends BasePersistenceImpl<ScheduleConfiguration>
	implements ScheduleConfigurationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ScheduleConfigurationUtil</code> to access the schedule configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ScheduleConfigurationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ScheduleConfigurationPersistenceImpl() {
		setModelClass(ScheduleConfiguration.class);

		setModelImplClass(ScheduleConfigurationImpl.class);
		setModelPKClass(long.class);

		setTable(ScheduleConfigurationTable.INSTANCE);
	}

	/**
	 * Caches the schedule configuration in the entity cache if it is enabled.
	 *
	 * @param scheduleConfiguration the schedule configuration
	 */
	@Override
	public void cacheResult(ScheduleConfiguration scheduleConfiguration) {
		entityCache.putResult(
			ScheduleConfigurationImpl.class,
			scheduleConfiguration.getPrimaryKey(), scheduleConfiguration);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the schedule configurations in the entity cache if it is enabled.
	 *
	 * @param scheduleConfigurations the schedule configurations
	 */
	@Override
	public void cacheResult(
		List<ScheduleConfiguration> scheduleConfigurations) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (scheduleConfigurations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ScheduleConfiguration scheduleConfiguration :
				scheduleConfigurations) {

			if (entityCache.getResult(
					ScheduleConfigurationImpl.class,
					scheduleConfiguration.getPrimaryKey()) == null) {

				cacheResult(scheduleConfiguration);
			}
		}
	}

	/**
	 * Clears the cache for all schedule configurations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ScheduleConfigurationImpl.class);

		finderCache.clearCache(ScheduleConfigurationImpl.class);
	}

	/**
	 * Clears the cache for the schedule configuration.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScheduleConfiguration scheduleConfiguration) {
		entityCache.removeResult(
			ScheduleConfigurationImpl.class, scheduleConfiguration);
	}

	@Override
	public void clearCache(List<ScheduleConfiguration> scheduleConfigurations) {
		for (ScheduleConfiguration scheduleConfiguration :
				scheduleConfigurations) {

			entityCache.removeResult(
				ScheduleConfigurationImpl.class, scheduleConfiguration);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ScheduleConfigurationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ScheduleConfigurationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new schedule configuration with the primary key. Does not add the schedule configuration to the database.
	 *
	 * @param configId the primary key for the new schedule configuration
	 * @return the new schedule configuration
	 */
	@Override
	public ScheduleConfiguration create(long configId) {
		ScheduleConfiguration scheduleConfiguration =
			new ScheduleConfigurationImpl();

		scheduleConfiguration.setNew(true);
		scheduleConfiguration.setPrimaryKey(configId);

		return scheduleConfiguration;
	}

	/**
	 * Removes the schedule configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param configId the primary key of the schedule configuration
	 * @return the schedule configuration that was removed
	 * @throws NoSuchConfigurationException if a schedule configuration with the primary key could not be found
	 */
	@Override
	public ScheduleConfiguration remove(long configId)
		throws NoSuchConfigurationException {

		return remove((Serializable)configId);
	}

	/**
	 * Removes the schedule configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the schedule configuration
	 * @return the schedule configuration that was removed
	 * @throws NoSuchConfigurationException if a schedule configuration with the primary key could not be found
	 */
	@Override
	public ScheduleConfiguration remove(Serializable primaryKey)
		throws NoSuchConfigurationException {

		Session session = null;

		try {
			session = openSession();

			ScheduleConfiguration scheduleConfiguration =
				(ScheduleConfiguration)session.get(
					ScheduleConfigurationImpl.class, primaryKey);

			if (scheduleConfiguration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConfigurationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(scheduleConfiguration);
		}
		catch (NoSuchConfigurationException noSuchEntityException) {
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
	protected ScheduleConfiguration removeImpl(
		ScheduleConfiguration scheduleConfiguration) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scheduleConfiguration)) {
				scheduleConfiguration = (ScheduleConfiguration)session.get(
					ScheduleConfigurationImpl.class,
					scheduleConfiguration.getPrimaryKeyObj());
			}

			if (scheduleConfiguration != null) {
				session.delete(scheduleConfiguration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (scheduleConfiguration != null) {
			clearCache(scheduleConfiguration);
		}

		return scheduleConfiguration;
	}

	@Override
	public ScheduleConfiguration updateImpl(
		ScheduleConfiguration scheduleConfiguration) {

		boolean isNew = scheduleConfiguration.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(scheduleConfiguration);
			}
			else {
				scheduleConfiguration = (ScheduleConfiguration)session.merge(
					scheduleConfiguration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ScheduleConfigurationImpl.class, scheduleConfiguration, false,
			true);

		if (isNew) {
			scheduleConfiguration.setNew(false);
		}

		scheduleConfiguration.resetOriginalValues();

		return scheduleConfiguration;
	}

	/**
	 * Returns the schedule configuration with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the schedule configuration
	 * @return the schedule configuration
	 * @throws NoSuchConfigurationException if a schedule configuration with the primary key could not be found
	 */
	@Override
	public ScheduleConfiguration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConfigurationException {

		ScheduleConfiguration scheduleConfiguration = fetchByPrimaryKey(
			primaryKey);

		if (scheduleConfiguration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConfigurationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return scheduleConfiguration;
	}

	/**
	 * Returns the schedule configuration with the primary key or throws a <code>NoSuchConfigurationException</code> if it could not be found.
	 *
	 * @param configId the primary key of the schedule configuration
	 * @return the schedule configuration
	 * @throws NoSuchConfigurationException if a schedule configuration with the primary key could not be found
	 */
	@Override
	public ScheduleConfiguration findByPrimaryKey(long configId)
		throws NoSuchConfigurationException {

		return findByPrimaryKey((Serializable)configId);
	}

	/**
	 * Returns the schedule configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param configId the primary key of the schedule configuration
	 * @return the schedule configuration, or <code>null</code> if a schedule configuration with the primary key could not be found
	 */
	@Override
	public ScheduleConfiguration fetchByPrimaryKey(long configId) {
		return fetchByPrimaryKey((Serializable)configId);
	}

	/**
	 * Returns all the schedule configurations.
	 *
	 * @return the schedule configurations
	 */
	@Override
	public List<ScheduleConfiguration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schedule configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule configurations
	 * @param end the upper bound of the range of schedule configurations (not inclusive)
	 * @return the range of schedule configurations
	 */
	@Override
	public List<ScheduleConfiguration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the schedule configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule configurations
	 * @param end the upper bound of the range of schedule configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schedule configurations
	 */
	@Override
	public List<ScheduleConfiguration> findAll(
		int start, int end,
		OrderByComparator<ScheduleConfiguration> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schedule configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule configurations
	 * @param end the upper bound of the range of schedule configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of schedule configurations
	 */
	@Override
	public List<ScheduleConfiguration> findAll(
		int start, int end,
		OrderByComparator<ScheduleConfiguration> orderByComparator,
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

		List<ScheduleConfiguration> list = null;

		if (useFinderCache) {
			list = (List<ScheduleConfiguration>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SCHEDULECONFIGURATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SCHEDULECONFIGURATION;

				sql = sql.concat(ScheduleConfigurationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ScheduleConfiguration>)QueryUtil.list(
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
	 * Removes all the schedule configurations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ScheduleConfiguration scheduleConfiguration : findAll()) {
			remove(scheduleConfiguration);
		}
	}

	/**
	 * Returns the number of schedule configurations.
	 *
	 * @return the number of schedule configurations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_SCHEDULECONFIGURATION);

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
		return "configId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SCHEDULECONFIGURATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ScheduleConfigurationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the schedule configuration persistence.
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

		_setScheduleConfigurationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setScheduleConfigurationUtilPersistence(null);

		entityCache.removeCache(ScheduleConfigurationImpl.class.getName());
	}

	private void _setScheduleConfigurationUtilPersistence(
		ScheduleConfigurationPersistence scheduleConfigurationPersistence) {

		try {
			Field field = ScheduleConfigurationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, scheduleConfigurationPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = SchedulePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SCHEDULECONFIGURATION =
		"SELECT scheduleConfiguration FROM ScheduleConfiguration scheduleConfiguration";

	private static final String _SQL_COUNT_SCHEDULECONFIGURATION =
		"SELECT COUNT(scheduleConfiguration) FROM ScheduleConfiguration scheduleConfiguration";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"scheduleConfiguration.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ScheduleConfiguration exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ScheduleConfigurationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private ScheduleConfigurationModelArgumentsResolver
		_scheduleConfigurationModelArgumentsResolver;

}