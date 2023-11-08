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

package com.weprode.facile.preference.service.base;

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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import com.weprode.facile.preference.model.NotifyConfig;
import com.weprode.facile.preference.service.NotifyConfigLocalService;
import com.weprode.facile.preference.service.NotifyConfigLocalServiceUtil;
import com.weprode.facile.preference.service.persistence.NotifyConfigPersistence;
import com.weprode.facile.preference.service.persistence.UserPropertiesPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the notify config local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.preference.service.impl.NotifyConfigLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.preference.service.impl.NotifyConfigLocalServiceImpl
 * @generated
 */
public abstract class NotifyConfigLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, NotifyConfigLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>NotifyConfigLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>NotifyConfigLocalServiceUtil</code>.
	 */

	/**
	 * Adds the notify config to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfig the notify config
	 * @return the notify config that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public NotifyConfig addNotifyConfig(NotifyConfig notifyConfig) {
		notifyConfig.setNew(true);

		return notifyConfigPersistence.update(notifyConfig);
	}

	/**
	 * Creates a new notify config with the primary key. Does not add the notify config to the database.
	 *
	 * @param notifyConfigId the primary key for the new notify config
	 * @return the new notify config
	 */
	@Override
	@Transactional(enabled = false)
	public NotifyConfig createNotifyConfig(long notifyConfigId) {
		return notifyConfigPersistence.create(notifyConfigId);
	}

	/**
	 * Deletes the notify config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config that was removed
	 * @throws PortalException if a notify config with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public NotifyConfig deleteNotifyConfig(long notifyConfigId)
		throws PortalException {

		return notifyConfigPersistence.remove(notifyConfigId);
	}

	/**
	 * Deletes the notify config from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfig the notify config
	 * @return the notify config that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public NotifyConfig deleteNotifyConfig(NotifyConfig notifyConfig) {
		return notifyConfigPersistence.remove(notifyConfig);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return notifyConfigPersistence.dslQuery(dslQuery);
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
			NotifyConfig.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return notifyConfigPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.preference.model.impl.NotifyConfigModelImpl</code>.
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

		return notifyConfigPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.preference.model.impl.NotifyConfigModelImpl</code>.
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

		return notifyConfigPersistence.findWithDynamicQuery(
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
		return notifyConfigPersistence.countWithDynamicQuery(dynamicQuery);
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

		return notifyConfigPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public NotifyConfig fetchNotifyConfig(long notifyConfigId) {
		return notifyConfigPersistence.fetchByPrimaryKey(notifyConfigId);
	}

	/**
	 * Returns the notify config with the primary key.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config
	 * @throws PortalException if a notify config with the primary key could not be found
	 */
	@Override
	public NotifyConfig getNotifyConfig(long notifyConfigId)
		throws PortalException {

		return notifyConfigPersistence.findByPrimaryKey(notifyConfigId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(notifyConfigLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(NotifyConfig.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("notifyConfigId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			notifyConfigLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(NotifyConfig.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"notifyConfigId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(notifyConfigLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(NotifyConfig.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("notifyConfigId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return notifyConfigPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement NotifyConfigLocalServiceImpl#deleteNotifyConfig(NotifyConfig) to avoid orphaned data");
		}

		return notifyConfigLocalService.deleteNotifyConfig(
			(NotifyConfig)persistedModel);
	}

	@Override
	public BasePersistence<NotifyConfig> getBasePersistence() {
		return notifyConfigPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return notifyConfigPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the notify configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.preference.model.impl.NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @return the range of notify configs
	 */
	@Override
	public List<NotifyConfig> getNotifyConfigs(int start, int end) {
		return notifyConfigPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of notify configs.
	 *
	 * @return the number of notify configs
	 */
	@Override
	public int getNotifyConfigsCount() {
		return notifyConfigPersistence.countAll();
	}

	/**
	 * Updates the notify config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfig the notify config
	 * @return the notify config that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public NotifyConfig updateNotifyConfig(NotifyConfig notifyConfig) {
		return notifyConfigPersistence.update(notifyConfig);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			NotifyConfigLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		notifyConfigLocalService = (NotifyConfigLocalService)aopProxy;

		_setLocalServiceUtilService(notifyConfigLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return NotifyConfigLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return NotifyConfig.class;
	}

	protected String getModelClassName() {
		return NotifyConfig.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = notifyConfigPersistence.getDataSource();

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
		NotifyConfigLocalService notifyConfigLocalService) {

		try {
			Field field = NotifyConfigLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, notifyConfigLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected NotifyConfigLocalService notifyConfigLocalService;

	@Reference
	protected NotifyConfigPersistence notifyConfigPersistence;

	@Reference
	protected UserPropertiesPersistence userPropertiesPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		NotifyConfigLocalServiceBaseImpl.class);

}