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

package com.weprode.facile.application.service.base;

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

import com.weprode.facile.application.model.Broadcast;
import com.weprode.facile.application.service.BroadcastLocalService;
import com.weprode.facile.application.service.BroadcastLocalServiceUtil;
import com.weprode.facile.application.service.persistence.ApplicationPersistence;
import com.weprode.facile.application.service.persistence.AuthorizedSchoolPersistence;
import com.weprode.facile.application.service.persistence.BroadcastPersistence;
import com.weprode.facile.application.service.persistence.BroadcastRulePersistence;
import com.weprode.facile.application.service.persistence.DefaultRolePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the broadcast local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.application.service.impl.BroadcastLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.application.service.impl.BroadcastLocalServiceImpl
 * @generated
 */
public abstract class BroadcastLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, BroadcastLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>BroadcastLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>BroadcastLocalServiceUtil</code>.
	 */

	/**
	 * Adds the broadcast to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcast the broadcast
	 * @return the broadcast that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Broadcast addBroadcast(Broadcast broadcast) {
		broadcast.setNew(true);

		return broadcastPersistence.update(broadcast);
	}

	/**
	 * Creates a new broadcast with the primary key. Does not add the broadcast to the database.
	 *
	 * @param broadcastId the primary key for the new broadcast
	 * @return the new broadcast
	 */
	@Override
	@Transactional(enabled = false)
	public Broadcast createBroadcast(long broadcastId) {
		return broadcastPersistence.create(broadcastId);
	}

	/**
	 * Deletes the broadcast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast that was removed
	 * @throws PortalException if a broadcast with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Broadcast deleteBroadcast(long broadcastId) throws PortalException {
		return broadcastPersistence.remove(broadcastId);
	}

	/**
	 * Deletes the broadcast from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcast the broadcast
	 * @return the broadcast that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Broadcast deleteBroadcast(Broadcast broadcast) {
		return broadcastPersistence.remove(broadcast);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return broadcastPersistence.dslQuery(dslQuery);
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
			Broadcast.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return broadcastPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.application.model.impl.BroadcastModelImpl</code>.
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

		return broadcastPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.application.model.impl.BroadcastModelImpl</code>.
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

		return broadcastPersistence.findWithDynamicQuery(
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
		return broadcastPersistence.countWithDynamicQuery(dynamicQuery);
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

		return broadcastPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Broadcast fetchBroadcast(long broadcastId) {
		return broadcastPersistence.fetchByPrimaryKey(broadcastId);
	}

	/**
	 * Returns the broadcast with the primary key.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast
	 * @throws PortalException if a broadcast with the primary key could not be found
	 */
	@Override
	public Broadcast getBroadcast(long broadcastId) throws PortalException {
		return broadcastPersistence.findByPrimaryKey(broadcastId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(broadcastLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Broadcast.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("broadcastId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			broadcastLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Broadcast.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"broadcastId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(broadcastLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Broadcast.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("broadcastId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return broadcastPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement BroadcastLocalServiceImpl#deleteBroadcast(Broadcast) to avoid orphaned data");
		}

		return broadcastLocalService.deleteBroadcast((Broadcast)persistedModel);
	}

	@Override
	public BasePersistence<Broadcast> getBasePersistence() {
		return broadcastPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return broadcastPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.application.model.impl.BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @return the range of broadcasts
	 */
	@Override
	public List<Broadcast> getBroadcasts(int start, int end) {
		return broadcastPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of broadcasts.
	 *
	 * @return the number of broadcasts
	 */
	@Override
	public int getBroadcastsCount() {
		return broadcastPersistence.countAll();
	}

	/**
	 * Updates the broadcast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcast the broadcast
	 * @return the broadcast that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Broadcast updateBroadcast(Broadcast broadcast) {
		return broadcastPersistence.update(broadcast);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			BroadcastLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		broadcastLocalService = (BroadcastLocalService)aopProxy;

		_setLocalServiceUtilService(broadcastLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return BroadcastLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Broadcast.class;
	}

	protected String getModelClassName() {
		return Broadcast.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = broadcastPersistence.getDataSource();

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
		BroadcastLocalService broadcastLocalService) {

		try {
			Field field = BroadcastLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, broadcastLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected ApplicationPersistence applicationPersistence;

	@Reference
	protected AuthorizedSchoolPersistence authorizedSchoolPersistence;

	protected BroadcastLocalService broadcastLocalService;

	@Reference
	protected BroadcastPersistence broadcastPersistence;

	@Reference
	protected BroadcastRulePersistence broadcastRulePersistence;

	@Reference
	protected DefaultRolePersistence defaultRolePersistence;

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
		BroadcastLocalServiceBaseImpl.class);

}