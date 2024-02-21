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

import com.weprode.facile.user.model.LDAPMapping;
import com.weprode.facile.user.service.LDAPMappingLocalService;
import com.weprode.facile.user.service.LDAPMappingLocalServiceUtil;
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
 * Provides the base implementation for the ldap mapping local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.user.service.impl.LDAPMappingLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.user.service.impl.LDAPMappingLocalServiceImpl
 * @generated
 */
public abstract class LDAPMappingLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, LDAPMappingLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LDAPMappingLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>LDAPMappingLocalServiceUtil</code>.
	 */

	/**
	 * Adds the ldap mapping to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LDAPMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ldapMapping the ldap mapping
	 * @return the ldap mapping that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LDAPMapping addLDAPMapping(LDAPMapping ldapMapping) {
		ldapMapping.setNew(true);

		return ldapMappingPersistence.update(ldapMapping);
	}

	/**
	 * Creates a new ldap mapping with the primary key. Does not add the ldap mapping to the database.
	 *
	 * @param userId the primary key for the new ldap mapping
	 * @return the new ldap mapping
	 */
	@Override
	@Transactional(enabled = false)
	public LDAPMapping createLDAPMapping(long userId) {
		return ldapMappingPersistence.create(userId);
	}

	/**
	 * Deletes the ldap mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LDAPMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userId the primary key of the ldap mapping
	 * @return the ldap mapping that was removed
	 * @throws PortalException if a ldap mapping with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LDAPMapping deleteLDAPMapping(long userId) throws PortalException {
		return ldapMappingPersistence.remove(userId);
	}

	/**
	 * Deletes the ldap mapping from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LDAPMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ldapMapping the ldap mapping
	 * @return the ldap mapping that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LDAPMapping deleteLDAPMapping(LDAPMapping ldapMapping) {
		return ldapMappingPersistence.remove(ldapMapping);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return ldapMappingPersistence.dslQuery(dslQuery);
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
			LDAPMapping.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ldapMappingPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.LDAPMappingModelImpl</code>.
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

		return ldapMappingPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.LDAPMappingModelImpl</code>.
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

		return ldapMappingPersistence.findWithDynamicQuery(
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
		return ldapMappingPersistence.countWithDynamicQuery(dynamicQuery);
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

		return ldapMappingPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public LDAPMapping fetchLDAPMapping(long userId) {
		return ldapMappingPersistence.fetchByPrimaryKey(userId);
	}

	/**
	 * Returns the ldap mapping with the primary key.
	 *
	 * @param userId the primary key of the ldap mapping
	 * @return the ldap mapping
	 * @throws PortalException if a ldap mapping with the primary key could not be found
	 */
	@Override
	public LDAPMapping getLDAPMapping(long userId) throws PortalException {
		return ldapMappingPersistence.findByPrimaryKey(userId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ldapMappingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LDAPMapping.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("userId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			ldapMappingLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(LDAPMapping.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("userId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(ldapMappingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LDAPMapping.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("userId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ldapMappingPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement LDAPMappingLocalServiceImpl#deleteLDAPMapping(LDAPMapping) to avoid orphaned data");
		}

		return ldapMappingLocalService.deleteLDAPMapping(
			(LDAPMapping)persistedModel);
	}

	@Override
	public BasePersistence<LDAPMapping> getBasePersistence() {
		return ldapMappingPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ldapMappingPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the ldap mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.LDAPMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ldap mappings
	 * @param end the upper bound of the range of ldap mappings (not inclusive)
	 * @return the range of ldap mappings
	 */
	@Override
	public List<LDAPMapping> getLDAPMappings(int start, int end) {
		return ldapMappingPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of ldap mappings.
	 *
	 * @return the number of ldap mappings
	 */
	@Override
	public int getLDAPMappingsCount() {
		return ldapMappingPersistence.countAll();
	}

	/**
	 * Updates the ldap mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LDAPMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ldapMapping the ldap mapping
	 * @return the ldap mapping that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LDAPMapping updateLDAPMapping(LDAPMapping ldapMapping) {
		return ldapMappingPersistence.update(ldapMapping);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			LDAPMappingLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		ldapMappingLocalService = (LDAPMappingLocalService)aopProxy;

		_setLocalServiceUtilService(ldapMappingLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LDAPMappingLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return LDAPMapping.class;
	}

	protected String getModelClassName() {
		return LDAPMapping.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ldapMappingPersistence.getDataSource();

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
		LDAPMappingLocalService ldapMappingLocalService) {

		try {
			Field field = LDAPMappingLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, ldapMappingLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected AffectationPersistence affectationPersistence;

	protected LDAPMappingLocalService ldapMappingLocalService;

	@Reference
	protected LDAPMappingPersistence ldapMappingPersistence;

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
		LDAPMappingLocalServiceBaseImpl.class);

}