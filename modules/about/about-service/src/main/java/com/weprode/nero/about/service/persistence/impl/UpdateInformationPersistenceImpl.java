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

import com.weprode.nero.about.exception.NoSuchUpdateInformationException;
import com.weprode.nero.about.model.UpdateInformation;
import com.weprode.nero.about.model.UpdateInformationTable;
import com.weprode.nero.about.model.impl.UpdateInformationImpl;
import com.weprode.nero.about.model.impl.UpdateInformationModelImpl;
import com.weprode.nero.about.service.persistence.UpdateInformationPersistence;
import com.weprode.nero.about.service.persistence.UpdateInformationUtil;
import com.weprode.nero.about.service.persistence.impl.constants.AboutPersistenceConstants;

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
 * The persistence implementation for the update information service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {UpdateInformationPersistence.class, BasePersistence.class}
)
public class UpdateInformationPersistenceImpl
	extends BasePersistenceImpl<UpdateInformation>
	implements UpdateInformationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UpdateInformationUtil</code> to access the update information persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UpdateInformationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public UpdateInformationPersistenceImpl() {
		setModelClass(UpdateInformation.class);

		setModelImplClass(UpdateInformationImpl.class);
		setModelPKClass(long.class);

		setTable(UpdateInformationTable.INSTANCE);
	}

	/**
	 * Caches the update information in the entity cache if it is enabled.
	 *
	 * @param updateInformation the update information
	 */
	@Override
	public void cacheResult(UpdateInformation updateInformation) {
		entityCache.putResult(
			UpdateInformationImpl.class, updateInformation.getPrimaryKey(),
			updateInformation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the update informations in the entity cache if it is enabled.
	 *
	 * @param updateInformations the update informations
	 */
	@Override
	public void cacheResult(List<UpdateInformation> updateInformations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (updateInformations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UpdateInformation updateInformation : updateInformations) {
			if (entityCache.getResult(
					UpdateInformationImpl.class,
					updateInformation.getPrimaryKey()) == null) {

				cacheResult(updateInformation);
			}
		}
	}

	/**
	 * Clears the cache for all update informations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UpdateInformationImpl.class);

		finderCache.clearCache(UpdateInformationImpl.class);
	}

	/**
	 * Clears the cache for the update information.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UpdateInformation updateInformation) {
		entityCache.removeResult(
			UpdateInformationImpl.class, updateInformation);
	}

	@Override
	public void clearCache(List<UpdateInformation> updateInformations) {
		for (UpdateInformation updateInformation : updateInformations) {
			entityCache.removeResult(
				UpdateInformationImpl.class, updateInformation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UpdateInformationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UpdateInformationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new update information with the primary key. Does not add the update information to the database.
	 *
	 * @param updateInfoId the primary key for the new update information
	 * @return the new update information
	 */
	@Override
	public UpdateInformation create(long updateInfoId) {
		UpdateInformation updateInformation = new UpdateInformationImpl();

		updateInformation.setNew(true);
		updateInformation.setPrimaryKey(updateInfoId);

		return updateInformation;
	}

	/**
	 * Removes the update information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information that was removed
	 * @throws NoSuchUpdateInformationException if a update information with the primary key could not be found
	 */
	@Override
	public UpdateInformation remove(long updateInfoId)
		throws NoSuchUpdateInformationException {

		return remove((Serializable)updateInfoId);
	}

	/**
	 * Removes the update information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the update information
	 * @return the update information that was removed
	 * @throws NoSuchUpdateInformationException if a update information with the primary key could not be found
	 */
	@Override
	public UpdateInformation remove(Serializable primaryKey)
		throws NoSuchUpdateInformationException {

		Session session = null;

		try {
			session = openSession();

			UpdateInformation updateInformation =
				(UpdateInformation)session.get(
					UpdateInformationImpl.class, primaryKey);

			if (updateInformation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUpdateInformationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(updateInformation);
		}
		catch (NoSuchUpdateInformationException noSuchEntityException) {
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
	protected UpdateInformation removeImpl(
		UpdateInformation updateInformation) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(updateInformation)) {
				updateInformation = (UpdateInformation)session.get(
					UpdateInformationImpl.class,
					updateInformation.getPrimaryKeyObj());
			}

			if (updateInformation != null) {
				session.delete(updateInformation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (updateInformation != null) {
			clearCache(updateInformation);
		}

		return updateInformation;
	}

	@Override
	public UpdateInformation updateImpl(UpdateInformation updateInformation) {
		boolean isNew = updateInformation.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(updateInformation);
			}
			else {
				updateInformation = (UpdateInformation)session.merge(
					updateInformation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UpdateInformationImpl.class, updateInformation, false, true);

		if (isNew) {
			updateInformation.setNew(false);
		}

		updateInformation.resetOriginalValues();

		return updateInformation;
	}

	/**
	 * Returns the update information with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the update information
	 * @return the update information
	 * @throws NoSuchUpdateInformationException if a update information with the primary key could not be found
	 */
	@Override
	public UpdateInformation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUpdateInformationException {

		UpdateInformation updateInformation = fetchByPrimaryKey(primaryKey);

		if (updateInformation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUpdateInformationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return updateInformation;
	}

	/**
	 * Returns the update information with the primary key or throws a <code>NoSuchUpdateInformationException</code> if it could not be found.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information
	 * @throws NoSuchUpdateInformationException if a update information with the primary key could not be found
	 */
	@Override
	public UpdateInformation findByPrimaryKey(long updateInfoId)
		throws NoSuchUpdateInformationException {

		return findByPrimaryKey((Serializable)updateInfoId);
	}

	/**
	 * Returns the update information with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information, or <code>null</code> if a update information with the primary key could not be found
	 */
	@Override
	public UpdateInformation fetchByPrimaryKey(long updateInfoId) {
		return fetchByPrimaryKey((Serializable)updateInfoId);
	}

	/**
	 * Returns all the update informations.
	 *
	 * @return the update informations
	 */
	@Override
	public List<UpdateInformation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the update informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UpdateInformationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of update informations
	 * @param end the upper bound of the range of update informations (not inclusive)
	 * @return the range of update informations
	 */
	@Override
	public List<UpdateInformation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the update informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UpdateInformationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of update informations
	 * @param end the upper bound of the range of update informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of update informations
	 */
	@Override
	public List<UpdateInformation> findAll(
		int start, int end,
		OrderByComparator<UpdateInformation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the update informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UpdateInformationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of update informations
	 * @param end the upper bound of the range of update informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of update informations
	 */
	@Override
	public List<UpdateInformation> findAll(
		int start, int end,
		OrderByComparator<UpdateInformation> orderByComparator,
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

		List<UpdateInformation> list = null;

		if (useFinderCache) {
			list = (List<UpdateInformation>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_UPDATEINFORMATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_UPDATEINFORMATION;

				sql = sql.concat(UpdateInformationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UpdateInformation>)QueryUtil.list(
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
	 * Removes all the update informations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UpdateInformation updateInformation : findAll()) {
			remove(updateInformation);
		}
	}

	/**
	 * Returns the number of update informations.
	 *
	 * @return the number of update informations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_UPDATEINFORMATION);

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
		return "updateInfoId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_UPDATEINFORMATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UpdateInformationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the update information persistence.
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

		_setUpdateInformationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setUpdateInformationUtilPersistence(null);

		entityCache.removeCache(UpdateInformationImpl.class.getName());
	}

	private void _setUpdateInformationUtilPersistence(
		UpdateInformationPersistence updateInformationPersistence) {

		try {
			Field field = UpdateInformationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, updateInformationPersistence);
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

	private static final String _SQL_SELECT_UPDATEINFORMATION =
		"SELECT updateInformation FROM UpdateInformation updateInformation";

	private static final String _SQL_COUNT_UPDATEINFORMATION =
		"SELECT COUNT(updateInformation) FROM UpdateInformation updateInformation";

	private static final String _ORDER_BY_ENTITY_ALIAS = "updateInformation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UpdateInformation exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		UpdateInformationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private UpdateInformationModelArgumentsResolver
		_updateInformationModelArgumentsResolver;

}