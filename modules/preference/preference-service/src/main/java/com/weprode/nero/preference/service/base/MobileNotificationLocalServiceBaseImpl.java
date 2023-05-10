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

package com.weprode.nero.preference.service.base;

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
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import com.weprode.nero.preference.model.MobileNotification;
import com.weprode.nero.preference.service.MobileNotificationLocalService;
import com.weprode.nero.preference.service.MobileNotificationLocalServiceUtil;
import com.weprode.nero.preference.service.persistence.MobileDevicePersistence;
import com.weprode.nero.preference.service.persistence.MobileNotificationPersistence;
import com.weprode.nero.preference.service.persistence.NotifyConfigPersistence;
import com.weprode.nero.preference.service.persistence.UserMobileTokenPersistence;
import com.weprode.nero.preference.service.persistence.UserPropertiesPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the mobile notification local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.preference.service.impl.MobileNotificationLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.preference.service.impl.MobileNotificationLocalServiceImpl
 * @generated
 */
public abstract class MobileNotificationLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   MobileNotificationLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MobileNotificationLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>MobileNotificationLocalServiceUtil</code>.
	 */

	/**
	 * Adds the mobile notification to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileNotificationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileNotification the mobile notification
	 * @return the mobile notification that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MobileNotification addMobileNotification(
		MobileNotification mobileNotification) {

		mobileNotification.setNew(true);

		return mobileNotificationPersistence.update(mobileNotification);
	}

	/**
	 * Creates a new mobile notification with the primary key. Does not add the mobile notification to the database.
	 *
	 * @param mobileNotificationId the primary key for the new mobile notification
	 * @return the new mobile notification
	 */
	@Override
	@Transactional(enabled = false)
	public MobileNotification createMobileNotification(
		long mobileNotificationId) {

		return mobileNotificationPersistence.create(mobileNotificationId);
	}

	/**
	 * Deletes the mobile notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileNotificationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification that was removed
	 * @throws PortalException if a mobile notification with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MobileNotification deleteMobileNotification(
			long mobileNotificationId)
		throws PortalException {

		return mobileNotificationPersistence.remove(mobileNotificationId);
	}

	/**
	 * Deletes the mobile notification from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileNotificationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileNotification the mobile notification
	 * @return the mobile notification that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MobileNotification deleteMobileNotification(
		MobileNotification mobileNotification) {

		return mobileNotificationPersistence.remove(mobileNotification);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return mobileNotificationPersistence.dslQuery(dslQuery);
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
			MobileNotification.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return mobileNotificationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.MobileNotificationModelImpl</code>.
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

		return mobileNotificationPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.MobileNotificationModelImpl</code>.
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

		return mobileNotificationPersistence.findWithDynamicQuery(
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
		return mobileNotificationPersistence.countWithDynamicQuery(
			dynamicQuery);
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

		return mobileNotificationPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public MobileNotification fetchMobileNotification(
		long mobileNotificationId) {

		return mobileNotificationPersistence.fetchByPrimaryKey(
			mobileNotificationId);
	}

	/**
	 * Returns the mobile notification with the primary key.
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification
	 * @throws PortalException if a mobile notification with the primary key could not be found
	 */
	@Override
	public MobileNotification getMobileNotification(long mobileNotificationId)
		throws PortalException {

		return mobileNotificationPersistence.findByPrimaryKey(
			mobileNotificationId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			mobileNotificationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MobileNotification.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"mobileNotificationId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			mobileNotificationLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(MobileNotification.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"mobileNotificationId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			mobileNotificationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MobileNotification.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"mobileNotificationId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mobileNotificationPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return mobileNotificationLocalService.deleteMobileNotification(
			(MobileNotification)persistedModel);
	}

	@Override
	public BasePersistence<MobileNotification> getBasePersistence() {
		return mobileNotificationPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mobileNotificationPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the mobile notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of mobile notifications
	 */
	@Override
	public List<MobileNotification> getMobileNotifications(int start, int end) {
		return mobileNotificationPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of mobile notifications.
	 *
	 * @return the number of mobile notifications
	 */
	@Override
	public int getMobileNotificationsCount() {
		return mobileNotificationPersistence.countAll();
	}

	/**
	 * Updates the mobile notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MobileNotificationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mobileNotification the mobile notification
	 * @return the mobile notification that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MobileNotification updateMobileNotification(
		MobileNotification mobileNotification) {

		return mobileNotificationPersistence.update(mobileNotification);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			MobileNotificationLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		mobileNotificationLocalService =
			(MobileNotificationLocalService)aopProxy;

		_setLocalServiceUtilService(mobileNotificationLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MobileNotificationLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MobileNotification.class;
	}

	protected String getModelClassName() {
		return MobileNotification.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				mobileNotificationPersistence.getDataSource();

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
		MobileNotificationLocalService mobileNotificationLocalService) {

		try {
			Field field =
				MobileNotificationLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, mobileNotificationLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected MobileDevicePersistence mobileDevicePersistence;

	protected MobileNotificationLocalService mobileNotificationLocalService;

	@Reference
	protected MobileNotificationPersistence mobileNotificationPersistence;

	@Reference
	protected NotifyConfigPersistence notifyConfigPersistence;

	@Reference
	protected UserMobileTokenPersistence userMobileTokenPersistence;

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

}