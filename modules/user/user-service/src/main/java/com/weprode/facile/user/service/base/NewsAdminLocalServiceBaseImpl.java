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

package com.weprode.facile.user.service.base;

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

import com.weprode.facile.user.model.NewsAdmin;
import com.weprode.facile.user.service.NewsAdminLocalService;
import com.weprode.facile.user.service.NewsAdminLocalServiceUtil;
import com.weprode.facile.user.service.persistence.AffectationPersistence;
import com.weprode.facile.user.service.persistence.LDAPMappingPersistence;
import com.weprode.facile.user.service.persistence.NewsAdminPersistence;
import com.weprode.facile.user.service.persistence.UserContactPersistence;
import com.weprode.facile.user.service.persistence.UserRelationshipPersistence;
import com.weprode.facile.user.service.persistence.UserUtilsFinder;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the news admin local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.user.service.impl.NewsAdminLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.user.service.impl.NewsAdminLocalServiceImpl
 * @generated
 */
public abstract class NewsAdminLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, NewsAdminLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>NewsAdminLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>NewsAdminLocalServiceUtil</code>.
	 */

	/**
	 * Adds the news admin to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdmin the news admin
	 * @return the news admin that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public NewsAdmin addNewsAdmin(NewsAdmin newsAdmin) {
		newsAdmin.setNew(true);

		return newsAdminPersistence.update(newsAdmin);
	}

	/**
	 * Creates a new news admin with the primary key. Does not add the news admin to the database.
	 *
	 * @param newsAdminId the primary key for the new news admin
	 * @return the new news admin
	 */
	@Override
	@Transactional(enabled = false)
	public NewsAdmin createNewsAdmin(long newsAdminId) {
		return newsAdminPersistence.create(newsAdminId);
	}

	/**
	 * Deletes the news admin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin that was removed
	 * @throws PortalException if a news admin with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public NewsAdmin deleteNewsAdmin(long newsAdminId) throws PortalException {
		return newsAdminPersistence.remove(newsAdminId);
	}

	/**
	 * Deletes the news admin from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdmin the news admin
	 * @return the news admin that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public NewsAdmin deleteNewsAdmin(NewsAdmin newsAdmin) {
		return newsAdminPersistence.remove(newsAdmin);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return newsAdminPersistence.dslQuery(dslQuery);
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
			NewsAdmin.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return newsAdminPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.NewsAdminModelImpl</code>.
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

		return newsAdminPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.NewsAdminModelImpl</code>.
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

		return newsAdminPersistence.findWithDynamicQuery(
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
		return newsAdminPersistence.countWithDynamicQuery(dynamicQuery);
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

		return newsAdminPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public NewsAdmin fetchNewsAdmin(long newsAdminId) {
		return newsAdminPersistence.fetchByPrimaryKey(newsAdminId);
	}

	/**
	 * Returns the news admin with the primary key.
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin
	 * @throws PortalException if a news admin with the primary key could not be found
	 */
	@Override
	public NewsAdmin getNewsAdmin(long newsAdminId) throws PortalException {
		return newsAdminPersistence.findByPrimaryKey(newsAdminId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(newsAdminLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(NewsAdmin.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("newsAdminId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			newsAdminLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(NewsAdmin.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"newsAdminId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(newsAdminLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(NewsAdmin.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("newsAdminId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return newsAdminPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement NewsAdminLocalServiceImpl#deleteNewsAdmin(NewsAdmin) to avoid orphaned data");
		}

		return newsAdminLocalService.deleteNewsAdmin((NewsAdmin)persistedModel);
	}

	@Override
	public BasePersistence<NewsAdmin> getBasePersistence() {
		return newsAdminPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return newsAdminPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the news admins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @return the range of news admins
	 */
	@Override
	public List<NewsAdmin> getNewsAdmins(int start, int end) {
		return newsAdminPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of news admins.
	 *
	 * @return the number of news admins
	 */
	@Override
	public int getNewsAdminsCount() {
		return newsAdminPersistence.countAll();
	}

	/**
	 * Updates the news admin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NewsAdminLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param newsAdmin the news admin
	 * @return the news admin that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public NewsAdmin updateNewsAdmin(NewsAdmin newsAdmin) {
		return newsAdminPersistence.update(newsAdmin);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			NewsAdminLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		newsAdminLocalService = (NewsAdminLocalService)aopProxy;

		_setLocalServiceUtilService(newsAdminLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return NewsAdminLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return NewsAdmin.class;
	}

	protected String getModelClassName() {
		return NewsAdmin.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = newsAdminPersistence.getDataSource();

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
		NewsAdminLocalService newsAdminLocalService) {

		try {
			Field field = NewsAdminLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, newsAdminLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected AffectationPersistence affectationPersistence;

	@Reference
	protected LDAPMappingPersistence ldapMappingPersistence;

	protected NewsAdminLocalService newsAdminLocalService;

	@Reference
	protected NewsAdminPersistence newsAdminPersistence;

	@Reference
	protected UserContactPersistence userContactPersistence;

	@Reference
	protected UserRelationshipPersistence userRelationshipPersistence;

	@Reference
	protected UserUtilsFinder userUtilsFinder;

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
		NewsAdminLocalServiceBaseImpl.class);

}