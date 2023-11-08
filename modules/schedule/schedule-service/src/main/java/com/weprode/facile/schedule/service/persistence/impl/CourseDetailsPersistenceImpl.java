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

package com.weprode.facile.schedule.service.persistence.impl;

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

import com.weprode.facile.schedule.exception.NoSuchCourseDetailsException;
import com.weprode.facile.schedule.model.CourseDetails;
import com.weprode.facile.schedule.model.CourseDetailsTable;
import com.weprode.facile.schedule.model.impl.CourseDetailsImpl;
import com.weprode.facile.schedule.model.impl.CourseDetailsModelImpl;
import com.weprode.facile.schedule.service.persistence.CourseDetailsPersistence;
import com.weprode.facile.schedule.service.persistence.CourseDetailsUtil;
import com.weprode.facile.schedule.service.persistence.impl.constants.SchedulePersistenceConstants;

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
 * The persistence implementation for the course details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {CourseDetailsPersistence.class, BasePersistence.class})
public class CourseDetailsPersistenceImpl
	extends BasePersistenceImpl<CourseDetails>
	implements CourseDetailsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CourseDetailsUtil</code> to access the course details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CourseDetailsImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CourseDetailsPersistenceImpl() {
		setModelClass(CourseDetails.class);

		setModelImplClass(CourseDetailsImpl.class);
		setModelPKClass(long.class);

		setTable(CourseDetailsTable.INSTANCE);
	}

	/**
	 * Caches the course details in the entity cache if it is enabled.
	 *
	 * @param courseDetails the course details
	 */
	@Override
	public void cacheResult(CourseDetails courseDetails) {
		entityCache.putResult(
			CourseDetailsImpl.class, courseDetails.getPrimaryKey(),
			courseDetails);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the course detailses in the entity cache if it is enabled.
	 *
	 * @param courseDetailses the course detailses
	 */
	@Override
	public void cacheResult(List<CourseDetails> courseDetailses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (courseDetailses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CourseDetails courseDetails : courseDetailses) {
			if (entityCache.getResult(
					CourseDetailsImpl.class, courseDetails.getPrimaryKey()) ==
						null) {

				cacheResult(courseDetails);
			}
		}
	}

	/**
	 * Clears the cache for all course detailses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CourseDetailsImpl.class);

		finderCache.clearCache(CourseDetailsImpl.class);
	}

	/**
	 * Clears the cache for the course details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CourseDetails courseDetails) {
		entityCache.removeResult(CourseDetailsImpl.class, courseDetails);
	}

	@Override
	public void clearCache(List<CourseDetails> courseDetailses) {
		for (CourseDetails courseDetails : courseDetailses) {
			entityCache.removeResult(CourseDetailsImpl.class, courseDetails);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CourseDetailsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CourseDetailsImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new course details with the primary key. Does not add the course details to the database.
	 *
	 * @param courseGroupId the primary key for the new course details
	 * @return the new course details
	 */
	@Override
	public CourseDetails create(long courseGroupId) {
		CourseDetails courseDetails = new CourseDetailsImpl();

		courseDetails.setNew(true);
		courseDetails.setPrimaryKey(courseGroupId);

		return courseDetails;
	}

	/**
	 * Removes the course details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details that was removed
	 * @throws NoSuchCourseDetailsException if a course details with the primary key could not be found
	 */
	@Override
	public CourseDetails remove(long courseGroupId)
		throws NoSuchCourseDetailsException {

		return remove((Serializable)courseGroupId);
	}

	/**
	 * Removes the course details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the course details
	 * @return the course details that was removed
	 * @throws NoSuchCourseDetailsException if a course details with the primary key could not be found
	 */
	@Override
	public CourseDetails remove(Serializable primaryKey)
		throws NoSuchCourseDetailsException {

		Session session = null;

		try {
			session = openSession();

			CourseDetails courseDetails = (CourseDetails)session.get(
				CourseDetailsImpl.class, primaryKey);

			if (courseDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCourseDetailsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(courseDetails);
		}
		catch (NoSuchCourseDetailsException noSuchEntityException) {
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
	protected CourseDetails removeImpl(CourseDetails courseDetails) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(courseDetails)) {
				courseDetails = (CourseDetails)session.get(
					CourseDetailsImpl.class, courseDetails.getPrimaryKeyObj());
			}

			if (courseDetails != null) {
				session.delete(courseDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (courseDetails != null) {
			clearCache(courseDetails);
		}

		return courseDetails;
	}

	@Override
	public CourseDetails updateImpl(CourseDetails courseDetails) {
		boolean isNew = courseDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(courseDetails);
			}
			else {
				courseDetails = (CourseDetails)session.merge(courseDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CourseDetailsImpl.class, courseDetails, false, true);

		if (isNew) {
			courseDetails.setNew(false);
		}

		courseDetails.resetOriginalValues();

		return courseDetails;
	}

	/**
	 * Returns the course details with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the course details
	 * @return the course details
	 * @throws NoSuchCourseDetailsException if a course details with the primary key could not be found
	 */
	@Override
	public CourseDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCourseDetailsException {

		CourseDetails courseDetails = fetchByPrimaryKey(primaryKey);

		if (courseDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCourseDetailsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return courseDetails;
	}

	/**
	 * Returns the course details with the primary key or throws a <code>NoSuchCourseDetailsException</code> if it could not be found.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details
	 * @throws NoSuchCourseDetailsException if a course details with the primary key could not be found
	 */
	@Override
	public CourseDetails findByPrimaryKey(long courseGroupId)
		throws NoSuchCourseDetailsException {

		return findByPrimaryKey((Serializable)courseGroupId);
	}

	/**
	 * Returns the course details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details, or <code>null</code> if a course details with the primary key could not be found
	 */
	@Override
	public CourseDetails fetchByPrimaryKey(long courseGroupId) {
		return fetchByPrimaryKey((Serializable)courseGroupId);
	}

	/**
	 * Returns all the course detailses.
	 *
	 * @return the course detailses
	 */
	@Override
	public List<CourseDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @return the range of course detailses
	 */
	@Override
	public List<CourseDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of course detailses
	 */
	@Override
	public List<CourseDetails> findAll(
		int start, int end,
		OrderByComparator<CourseDetails> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of course detailses
	 */
	@Override
	public List<CourseDetails> findAll(
		int start, int end, OrderByComparator<CourseDetails> orderByComparator,
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

		List<CourseDetails> list = null;

		if (useFinderCache) {
			list = (List<CourseDetails>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COURSEDETAILS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COURSEDETAILS;

				sql = sql.concat(CourseDetailsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CourseDetails>)QueryUtil.list(
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
	 * Removes all the course detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CourseDetails courseDetails : findAll()) {
			remove(courseDetails);
		}
	}

	/**
	 * Returns the number of course detailses.
	 *
	 * @return the number of course detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COURSEDETAILS);

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
		return "courseGroupId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COURSEDETAILS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CourseDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the course details persistence.
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

		_setCourseDetailsUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setCourseDetailsUtilPersistence(null);

		entityCache.removeCache(CourseDetailsImpl.class.getName());
	}

	private void _setCourseDetailsUtilPersistence(
		CourseDetailsPersistence courseDetailsPersistence) {

		try {
			Field field = CourseDetailsUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, courseDetailsPersistence);
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

	private static final String _SQL_SELECT_COURSEDETAILS =
		"SELECT courseDetails FROM CourseDetails courseDetails";

	private static final String _SQL_COUNT_COURSEDETAILS =
		"SELECT COUNT(courseDetails) FROM CourseDetails courseDetails";

	private static final String _ORDER_BY_ENTITY_ALIAS = "courseDetails.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CourseDetails exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CourseDetailsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}