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

package com.liferay.portal.workflow.kaleo.service.base;

import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the kaleo task local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskLocalServiceImpl
 * @generated
 */
public abstract class KaleoTaskLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, KaleoTaskLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>KaleoTaskLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>KaleoTaskLocalServiceUtil</code>.
	 */

	/**
	 * Adds the kaleo task to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTask the kaleo task
	 * @return the kaleo task that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KaleoTask addKaleoTask(KaleoTask kaleoTask) {
		kaleoTask.setNew(true);

		return kaleoTaskPersistence.update(kaleoTask);
	}

	/**
	 * Creates a new kaleo task with the primary key. Does not add the kaleo task to the database.
	 *
	 * @param kaleoTaskId the primary key for the new kaleo task
	 * @return the new kaleo task
	 */
	@Override
	@Transactional(enabled = false)
	public KaleoTask createKaleoTask(long kaleoTaskId) {
		return kaleoTaskPersistence.create(kaleoTaskId);
	}

	/**
	 * Deletes the kaleo task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskId the primary key of the kaleo task
	 * @return the kaleo task that was removed
	 * @throws PortalException if a kaleo task with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KaleoTask deleteKaleoTask(long kaleoTaskId) throws PortalException {
		return kaleoTaskPersistence.remove(kaleoTaskId);
	}

	/**
	 * Deletes the kaleo task from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTask the kaleo task
	 * @return the kaleo task that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KaleoTask deleteKaleoTask(KaleoTask kaleoTask) {
		return kaleoTaskPersistence.remove(kaleoTask);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return kaleoTaskPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			KaleoTask.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return kaleoTaskPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return kaleoTaskPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return kaleoTaskPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return kaleoTaskPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return kaleoTaskPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public KaleoTask fetchKaleoTask(long kaleoTaskId) {
		return kaleoTaskPersistence.fetchByPrimaryKey(kaleoTaskId);
	}

	/**
	 * Returns the kaleo task with the primary key.
	 *
	 * @param kaleoTaskId the primary key of the kaleo task
	 * @return the kaleo task
	 * @throws PortalException if a kaleo task with the primary key could not be found
	 */
	@Override
	public KaleoTask getKaleoTask(long kaleoTaskId) throws PortalException {
		return kaleoTaskPersistence.findByPrimaryKey(kaleoTaskId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(kaleoTaskLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(KaleoTask.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("kaleoTaskId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			kaleoTaskLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(KaleoTask.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"kaleoTaskId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(kaleoTaskLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(KaleoTask.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("kaleoTaskId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return kaleoTaskPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return kaleoTaskLocalService.deleteKaleoTask((KaleoTask)persistedModel);
	}

	@Override
	public BasePersistence<KaleoTask> getBasePersistence() {
		return kaleoTaskPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return kaleoTaskPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the kaleo tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo tasks
	 * @param end the upper bound of the range of kaleo tasks (not inclusive)
	 * @return the range of kaleo tasks
	 */
	@Override
	public List<KaleoTask> getKaleoTasks(int start, int end) {
		return kaleoTaskPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of kaleo tasks.
	 *
	 * @return the number of kaleo tasks
	 */
	@Override
	public int getKaleoTasksCount() {
		return kaleoTaskPersistence.countAll();
	}

	/**
	 * Updates the kaleo task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTask the kaleo task
	 * @return the kaleo task that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KaleoTask updateKaleoTask(KaleoTask kaleoTask) {
		return kaleoTaskPersistence.update(kaleoTask);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			KaleoTaskLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		kaleoTaskLocalService = (KaleoTaskLocalService)aopProxy;

		_setLocalServiceUtilService(kaleoTaskLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return KaleoTaskLocalService.class.getName();
	}

	@Override
	public CTPersistence<KaleoTask> getCTPersistence() {
		return kaleoTaskPersistence;
	}

	@Override
	public Class<KaleoTask> getModelClass() {
		return KaleoTask.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<KaleoTask>, R, E> updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(kaleoTaskPersistence);
	}

	protected String getModelClassName() {
		return KaleoTask.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = kaleoTaskPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		KaleoTaskLocalService kaleoTaskLocalService) {

		try {
			Field field = KaleoTaskLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, kaleoTaskLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected KaleoTaskLocalService kaleoTaskLocalService;

	@Reference
	protected KaleoTaskPersistence kaleoTaskPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}