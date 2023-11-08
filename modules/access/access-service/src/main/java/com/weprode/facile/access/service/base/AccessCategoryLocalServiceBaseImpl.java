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

package com.weprode.facile.access.service.base;

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

import com.weprode.facile.access.model.AccessCategory;
import com.weprode.facile.access.service.AccessCategoryLocalService;
import com.weprode.facile.access.service.AccessCategoryLocalServiceUtil;
import com.weprode.facile.access.service.persistence.AccessCategoryPersistence;
import com.weprode.facile.access.service.persistence.AccessPersistence;
import com.weprode.facile.access.service.persistence.AccessProfilePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the access category local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.access.service.impl.AccessCategoryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.access.service.impl.AccessCategoryLocalServiceImpl
 * @generated
 */
public abstract class AccessCategoryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AccessCategoryLocalService, AopService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AccessCategoryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>AccessCategoryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the access category to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessCategory the access category
	 * @return the access category that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AccessCategory addAccessCategory(AccessCategory accessCategory) {
		accessCategory.setNew(true);

		return accessCategoryPersistence.update(accessCategory);
	}

	/**
	 * Creates a new access category with the primary key. Does not add the access category to the database.
	 *
	 * @param categoryId the primary key for the new access category
	 * @return the new access category
	 */
	@Override
	@Transactional(enabled = false)
	public AccessCategory createAccessCategory(long categoryId) {
		return accessCategoryPersistence.create(categoryId);
	}

	/**
	 * Deletes the access category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category that was removed
	 * @throws PortalException if a access category with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AccessCategory deleteAccessCategory(long categoryId)
		throws PortalException {

		return accessCategoryPersistence.remove(categoryId);
	}

	/**
	 * Deletes the access category from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessCategory the access category
	 * @return the access category that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AccessCategory deleteAccessCategory(AccessCategory accessCategory) {
		return accessCategoryPersistence.remove(accessCategory);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return accessCategoryPersistence.dslQuery(dslQuery);
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
			AccessCategory.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return accessCategoryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.access.model.impl.AccessCategoryModelImpl</code>.
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

		return accessCategoryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.access.model.impl.AccessCategoryModelImpl</code>.
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

		return accessCategoryPersistence.findWithDynamicQuery(
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
		return accessCategoryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return accessCategoryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public AccessCategory fetchAccessCategory(long categoryId) {
		return accessCategoryPersistence.fetchByPrimaryKey(categoryId);
	}

	/**
	 * Returns the access category with the primary key.
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category
	 * @throws PortalException if a access category with the primary key could not be found
	 */
	@Override
	public AccessCategory getAccessCategory(long categoryId)
		throws PortalException {

		return accessCategoryPersistence.findByPrimaryKey(categoryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(accessCategoryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AccessCategory.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("categoryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			accessCategoryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(AccessCategory.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("categoryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(accessCategoryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AccessCategory.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("categoryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return accessCategoryPersistence.create(
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
				"Implement AccessCategoryLocalServiceImpl#deleteAccessCategory(AccessCategory) to avoid orphaned data");
		}

		return accessCategoryLocalService.deleteAccessCategory(
			(AccessCategory)persistedModel);
	}

	@Override
	public BasePersistence<AccessCategory> getBasePersistence() {
		return accessCategoryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return accessCategoryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the access categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.access.model.impl.AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @return the range of access categories
	 */
	@Override
	public List<AccessCategory> getAccessCategories(int start, int end) {
		return accessCategoryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of access categories.
	 *
	 * @return the number of access categories
	 */
	@Override
	public int getAccessCategoriesCount() {
		return accessCategoryPersistence.countAll();
	}

	/**
	 * Updates the access category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessCategory the access category
	 * @return the access category that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AccessCategory updateAccessCategory(AccessCategory accessCategory) {
		return accessCategoryPersistence.update(accessCategory);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			AccessCategoryLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		accessCategoryLocalService = (AccessCategoryLocalService)aopProxy;

		_setLocalServiceUtilService(accessCategoryLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AccessCategoryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AccessCategory.class;
	}

	protected String getModelClassName() {
		return AccessCategory.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = accessCategoryPersistence.getDataSource();

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
		AccessCategoryLocalService accessCategoryLocalService) {

		try {
			Field field = AccessCategoryLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, accessCategoryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected AccessPersistence accessPersistence;

	protected AccessCategoryLocalService accessCategoryLocalService;

	@Reference
	protected AccessCategoryPersistence accessCategoryPersistence;

	@Reference
	protected AccessProfilePersistence accessProfilePersistence;

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
		AccessCategoryLocalServiceBaseImpl.class);

}