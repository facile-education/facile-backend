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

import com.weprode.nero.schedule.exception.NoSuchGroupColorException;
import com.weprode.nero.schedule.model.GroupColor;
import com.weprode.nero.schedule.model.GroupColorTable;
import com.weprode.nero.schedule.model.impl.GroupColorImpl;
import com.weprode.nero.schedule.model.impl.GroupColorModelImpl;
import com.weprode.nero.schedule.service.persistence.GroupColorPersistence;
import com.weprode.nero.schedule.service.persistence.GroupColorUtil;
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
 * The persistence implementation for the group color service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {GroupColorPersistence.class, BasePersistence.class})
public class GroupColorPersistenceImpl
	extends BasePersistenceImpl<GroupColor> implements GroupColorPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>GroupColorUtil</code> to access the group color persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		GroupColorImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public GroupColorPersistenceImpl() {
		setModelClass(GroupColor.class);

		setModelImplClass(GroupColorImpl.class);
		setModelPKClass(long.class);

		setTable(GroupColorTable.INSTANCE);
	}

	/**
	 * Caches the group color in the entity cache if it is enabled.
	 *
	 * @param groupColor the group color
	 */
	@Override
	public void cacheResult(GroupColor groupColor) {
		entityCache.putResult(
			GroupColorImpl.class, groupColor.getPrimaryKey(), groupColor);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the group colors in the entity cache if it is enabled.
	 *
	 * @param groupColors the group colors
	 */
	@Override
	public void cacheResult(List<GroupColor> groupColors) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (groupColors.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (GroupColor groupColor : groupColors) {
			if (entityCache.getResult(
					GroupColorImpl.class, groupColor.getPrimaryKey()) == null) {

				cacheResult(groupColor);
			}
		}
	}

	/**
	 * Clears the cache for all group colors.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GroupColorImpl.class);

		finderCache.clearCache(GroupColorImpl.class);
	}

	/**
	 * Clears the cache for the group color.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GroupColor groupColor) {
		entityCache.removeResult(GroupColorImpl.class, groupColor);
	}

	@Override
	public void clearCache(List<GroupColor> groupColors) {
		for (GroupColor groupColor : groupColors) {
			entityCache.removeResult(GroupColorImpl.class, groupColor);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(GroupColorImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(GroupColorImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new group color with the primary key. Does not add the group color to the database.
	 *
	 * @param groupId the primary key for the new group color
	 * @return the new group color
	 */
	@Override
	public GroupColor create(long groupId) {
		GroupColor groupColor = new GroupColorImpl();

		groupColor.setNew(true);
		groupColor.setPrimaryKey(groupId);

		return groupColor;
	}

	/**
	 * Removes the group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color that was removed
	 * @throws NoSuchGroupColorException if a group color with the primary key could not be found
	 */
	@Override
	public GroupColor remove(long groupId) throws NoSuchGroupColorException {
		return remove((Serializable)groupId);
	}

	/**
	 * Removes the group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the group color
	 * @return the group color that was removed
	 * @throws NoSuchGroupColorException if a group color with the primary key could not be found
	 */
	@Override
	public GroupColor remove(Serializable primaryKey)
		throws NoSuchGroupColorException {

		Session session = null;

		try {
			session = openSession();

			GroupColor groupColor = (GroupColor)session.get(
				GroupColorImpl.class, primaryKey);

			if (groupColor == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGroupColorException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(groupColor);
		}
		catch (NoSuchGroupColorException noSuchEntityException) {
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
	protected GroupColor removeImpl(GroupColor groupColor) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(groupColor)) {
				groupColor = (GroupColor)session.get(
					GroupColorImpl.class, groupColor.getPrimaryKeyObj());
			}

			if (groupColor != null) {
				session.delete(groupColor);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (groupColor != null) {
			clearCache(groupColor);
		}

		return groupColor;
	}

	@Override
	public GroupColor updateImpl(GroupColor groupColor) {
		boolean isNew = groupColor.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(groupColor);
			}
			else {
				groupColor = (GroupColor)session.merge(groupColor);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(GroupColorImpl.class, groupColor, false, true);

		if (isNew) {
			groupColor.setNew(false);
		}

		groupColor.resetOriginalValues();

		return groupColor;
	}

	/**
	 * Returns the group color with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the group color
	 * @return the group color
	 * @throws NoSuchGroupColorException if a group color with the primary key could not be found
	 */
	@Override
	public GroupColor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGroupColorException {

		GroupColor groupColor = fetchByPrimaryKey(primaryKey);

		if (groupColor == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGroupColorException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return groupColor;
	}

	/**
	 * Returns the group color with the primary key or throws a <code>NoSuchGroupColorException</code> if it could not be found.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color
	 * @throws NoSuchGroupColorException if a group color with the primary key could not be found
	 */
	@Override
	public GroupColor findByPrimaryKey(long groupId)
		throws NoSuchGroupColorException {

		return findByPrimaryKey((Serializable)groupId);
	}

	/**
	 * Returns the group color with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color, or <code>null</code> if a group color with the primary key could not be found
	 */
	@Override
	public GroupColor fetchByPrimaryKey(long groupId) {
		return fetchByPrimaryKey((Serializable)groupId);
	}

	/**
	 * Returns all the group colors.
	 *
	 * @return the group colors
	 */
	@Override
	public List<GroupColor> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @return the range of group colors
	 */
	@Override
	public List<GroupColor> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of group colors
	 */
	@Override
	public List<GroupColor> findAll(
		int start, int end, OrderByComparator<GroupColor> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of group colors
	 */
	@Override
	public List<GroupColor> findAll(
		int start, int end, OrderByComparator<GroupColor> orderByComparator,
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

		List<GroupColor> list = null;

		if (useFinderCache) {
			list = (List<GroupColor>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_GROUPCOLOR);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_GROUPCOLOR;

				sql = sql.concat(GroupColorModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<GroupColor>)QueryUtil.list(
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
	 * Removes all the group colors from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GroupColor groupColor : findAll()) {
			remove(groupColor);
		}
	}

	/**
	 * Returns the number of group colors.
	 *
	 * @return the number of group colors
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_GROUPCOLOR);

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
		return "groupId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_GROUPCOLOR;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GroupColorModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the group color persistence.
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

		_setGroupColorUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setGroupColorUtilPersistence(null);

		entityCache.removeCache(GroupColorImpl.class.getName());
	}

	private void _setGroupColorUtilPersistence(
		GroupColorPersistence groupColorPersistence) {

		try {
			Field field = GroupColorUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, groupColorPersistence);
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

	private static final String _SQL_SELECT_GROUPCOLOR =
		"SELECT groupColor FROM GroupColor groupColor";

	private static final String _SQL_COUNT_GROUPCOLOR =
		"SELECT COUNT(groupColor) FROM GroupColor groupColor";

	private static final String _ORDER_BY_ENTITY_ALIAS = "groupColor.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No GroupColor exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		GroupColorPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private GroupColorModelArgumentsResolver _groupColorModelArgumentsResolver;

}