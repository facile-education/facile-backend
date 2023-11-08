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

package com.weprode.facile.help.service.persistence.impl;

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

import com.weprode.facile.help.exception.NoSuchCategoryException;
import com.weprode.facile.help.model.HelpCategory;
import com.weprode.facile.help.model.HelpCategoryTable;
import com.weprode.facile.help.model.impl.HelpCategoryImpl;
import com.weprode.facile.help.model.impl.HelpCategoryModelImpl;
import com.weprode.facile.help.service.persistence.HelpCategoryPersistence;
import com.weprode.facile.help.service.persistence.HelpCategoryUtil;
import com.weprode.facile.help.service.persistence.impl.constants.HelpPersistenceConstants;

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
 * The persistence implementation for the help category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {HelpCategoryPersistence.class, BasePersistence.class})
public class HelpCategoryPersistenceImpl
	extends BasePersistenceImpl<HelpCategory>
	implements HelpCategoryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>HelpCategoryUtil</code> to access the help category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		HelpCategoryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public HelpCategoryPersistenceImpl() {
		setModelClass(HelpCategory.class);

		setModelImplClass(HelpCategoryImpl.class);
		setModelPKClass(long.class);

		setTable(HelpCategoryTable.INSTANCE);
	}

	/**
	 * Caches the help category in the entity cache if it is enabled.
	 *
	 * @param helpCategory the help category
	 */
	@Override
	public void cacheResult(HelpCategory helpCategory) {
		entityCache.putResult(
			HelpCategoryImpl.class, helpCategory.getPrimaryKey(), helpCategory);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the help categories in the entity cache if it is enabled.
	 *
	 * @param helpCategories the help categories
	 */
	@Override
	public void cacheResult(List<HelpCategory> helpCategories) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (helpCategories.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (HelpCategory helpCategory : helpCategories) {
			if (entityCache.getResult(
					HelpCategoryImpl.class, helpCategory.getPrimaryKey()) ==
						null) {

				cacheResult(helpCategory);
			}
		}
	}

	/**
	 * Clears the cache for all help categories.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HelpCategoryImpl.class);

		finderCache.clearCache(HelpCategoryImpl.class);
	}

	/**
	 * Clears the cache for the help category.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HelpCategory helpCategory) {
		entityCache.removeResult(HelpCategoryImpl.class, helpCategory);
	}

	@Override
	public void clearCache(List<HelpCategory> helpCategories) {
		for (HelpCategory helpCategory : helpCategories) {
			entityCache.removeResult(HelpCategoryImpl.class, helpCategory);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(HelpCategoryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(HelpCategoryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new help category with the primary key. Does not add the help category to the database.
	 *
	 * @param categoryId the primary key for the new help category
	 * @return the new help category
	 */
	@Override
	public HelpCategory create(long categoryId) {
		HelpCategory helpCategory = new HelpCategoryImpl();

		helpCategory.setNew(true);
		helpCategory.setPrimaryKey(categoryId);

		return helpCategory;
	}

	/**
	 * Removes the help category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category that was removed
	 * @throws NoSuchCategoryException if a help category with the primary key could not be found
	 */
	@Override
	public HelpCategory remove(long categoryId) throws NoSuchCategoryException {
		return remove((Serializable)categoryId);
	}

	/**
	 * Removes the help category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the help category
	 * @return the help category that was removed
	 * @throws NoSuchCategoryException if a help category with the primary key could not be found
	 */
	@Override
	public HelpCategory remove(Serializable primaryKey)
		throws NoSuchCategoryException {

		Session session = null;

		try {
			session = openSession();

			HelpCategory helpCategory = (HelpCategory)session.get(
				HelpCategoryImpl.class, primaryKey);

			if (helpCategory == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCategoryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(helpCategory);
		}
		catch (NoSuchCategoryException noSuchEntityException) {
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
	protected HelpCategory removeImpl(HelpCategory helpCategory) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(helpCategory)) {
				helpCategory = (HelpCategory)session.get(
					HelpCategoryImpl.class, helpCategory.getPrimaryKeyObj());
			}

			if (helpCategory != null) {
				session.delete(helpCategory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (helpCategory != null) {
			clearCache(helpCategory);
		}

		return helpCategory;
	}

	@Override
	public HelpCategory updateImpl(HelpCategory helpCategory) {
		boolean isNew = helpCategory.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(helpCategory);
			}
			else {
				helpCategory = (HelpCategory)session.merge(helpCategory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			HelpCategoryImpl.class, helpCategory, false, true);

		if (isNew) {
			helpCategory.setNew(false);
		}

		helpCategory.resetOriginalValues();

		return helpCategory;
	}

	/**
	 * Returns the help category with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the help category
	 * @return the help category
	 * @throws NoSuchCategoryException if a help category with the primary key could not be found
	 */
	@Override
	public HelpCategory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCategoryException {

		HelpCategory helpCategory = fetchByPrimaryKey(primaryKey);

		if (helpCategory == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCategoryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return helpCategory;
	}

	/**
	 * Returns the help category with the primary key or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category
	 * @throws NoSuchCategoryException if a help category with the primary key could not be found
	 */
	@Override
	public HelpCategory findByPrimaryKey(long categoryId)
		throws NoSuchCategoryException {

		return findByPrimaryKey((Serializable)categoryId);
	}

	/**
	 * Returns the help category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category, or <code>null</code> if a help category with the primary key could not be found
	 */
	@Override
	public HelpCategory fetchByPrimaryKey(long categoryId) {
		return fetchByPrimaryKey((Serializable)categoryId);
	}

	/**
	 * Returns all the help categories.
	 *
	 * @return the help categories
	 */
	@Override
	public List<HelpCategory> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @return the range of help categories
	 */
	@Override
	public List<HelpCategory> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help categories
	 */
	@Override
	public List<HelpCategory> findAll(
		int start, int end, OrderByComparator<HelpCategory> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help categories
	 */
	@Override
	public List<HelpCategory> findAll(
		int start, int end, OrderByComparator<HelpCategory> orderByComparator,
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

		List<HelpCategory> list = null;

		if (useFinderCache) {
			list = (List<HelpCategory>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_HELPCATEGORY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_HELPCATEGORY;

				sql = sql.concat(HelpCategoryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<HelpCategory>)QueryUtil.list(
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
	 * Removes all the help categories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HelpCategory helpCategory : findAll()) {
			remove(helpCategory);
		}
	}

	/**
	 * Returns the number of help categories.
	 *
	 * @return the number of help categories
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_HELPCATEGORY);

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
		return "categoryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_HELPCATEGORY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return HelpCategoryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the help category persistence.
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

		_setHelpCategoryUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setHelpCategoryUtilPersistence(null);

		entityCache.removeCache(HelpCategoryImpl.class.getName());
	}

	private void _setHelpCategoryUtilPersistence(
		HelpCategoryPersistence helpCategoryPersistence) {

		try {
			Field field = HelpCategoryUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, helpCategoryPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = HelpPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = HelpPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = HelpPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_HELPCATEGORY =
		"SELECT helpCategory FROM HelpCategory helpCategory";

	private static final String _SQL_COUNT_HELPCATEGORY =
		"SELECT COUNT(helpCategory) FROM HelpCategory helpCategory";

	private static final String _ORDER_BY_ENTITY_ALIAS = "helpCategory.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No HelpCategory exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		HelpCategoryPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}