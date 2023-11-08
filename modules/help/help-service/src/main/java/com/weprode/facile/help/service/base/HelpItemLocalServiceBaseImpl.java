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

package com.weprode.facile.help.service.base;

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

import com.weprode.facile.help.model.HelpItem;
import com.weprode.facile.help.service.HelpItemLocalService;
import com.weprode.facile.help.service.HelpItemLocalServiceUtil;
import com.weprode.facile.help.service.persistence.HelpCategoryPersistence;
import com.weprode.facile.help.service.persistence.HelpItemPersistence;
import com.weprode.facile.help.service.persistence.HelpItemRolePersistence;
import com.weprode.facile.help.service.persistence.HelpLinkPersistence;
import com.weprode.facile.help.service.persistence.HelpQuestionPersistence;
import com.weprode.facile.help.service.persistence.HelpRelationPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the help item local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.help.service.impl.HelpItemLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.help.service.impl.HelpItemLocalServiceImpl
 * @generated
 */
public abstract class HelpItemLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, HelpItemLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>HelpItemLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>HelpItemLocalServiceUtil</code>.
	 */

	/**
	 * Adds the help item to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItem the help item
	 * @return the help item that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public HelpItem addHelpItem(HelpItem helpItem) {
		helpItem.setNew(true);

		return helpItemPersistence.update(helpItem);
	}

	/**
	 * Creates a new help item with the primary key. Does not add the help item to the database.
	 *
	 * @param itemId the primary key for the new help item
	 * @return the new help item
	 */
	@Override
	@Transactional(enabled = false)
	public HelpItem createHelpItem(long itemId) {
		return helpItemPersistence.create(itemId);
	}

	/**
	 * Deletes the help item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item that was removed
	 * @throws PortalException if a help item with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public HelpItem deleteHelpItem(long itemId) throws PortalException {
		return helpItemPersistence.remove(itemId);
	}

	/**
	 * Deletes the help item from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItem the help item
	 * @return the help item that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public HelpItem deleteHelpItem(HelpItem helpItem) {
		return helpItemPersistence.remove(helpItem);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return helpItemPersistence.dslQuery(dslQuery);
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
			HelpItem.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return helpItemPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.help.model.impl.HelpItemModelImpl</code>.
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

		return helpItemPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.help.model.impl.HelpItemModelImpl</code>.
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

		return helpItemPersistence.findWithDynamicQuery(
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
		return helpItemPersistence.countWithDynamicQuery(dynamicQuery);
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

		return helpItemPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public HelpItem fetchHelpItem(long itemId) {
		return helpItemPersistence.fetchByPrimaryKey(itemId);
	}

	/**
	 * Returns the help item with the primary key.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item
	 * @throws PortalException if a help item with the primary key could not be found
	 */
	@Override
	public HelpItem getHelpItem(long itemId) throws PortalException {
		return helpItemPersistence.findByPrimaryKey(itemId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(helpItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(HelpItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("itemId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			helpItemLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(HelpItem.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("itemId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(helpItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(HelpItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("itemId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return helpItemPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement HelpItemLocalServiceImpl#deleteHelpItem(HelpItem) to avoid orphaned data");
		}

		return helpItemLocalService.deleteHelpItem((HelpItem)persistedModel);
	}

	@Override
	public BasePersistence<HelpItem> getBasePersistence() {
		return helpItemPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return helpItemPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.help.model.impl.HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @return the range of help items
	 */
	@Override
	public List<HelpItem> getHelpItems(int start, int end) {
		return helpItemPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of help items.
	 *
	 * @return the number of help items
	 */
	@Override
	public int getHelpItemsCount() {
		return helpItemPersistence.countAll();
	}

	/**
	 * Updates the help item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItem the help item
	 * @return the help item that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public HelpItem updateHelpItem(HelpItem helpItem) {
		return helpItemPersistence.update(helpItem);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			HelpItemLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		helpItemLocalService = (HelpItemLocalService)aopProxy;

		_setLocalServiceUtilService(helpItemLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return HelpItemLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return HelpItem.class;
	}

	protected String getModelClassName() {
		return HelpItem.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = helpItemPersistence.getDataSource();

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
		HelpItemLocalService helpItemLocalService) {

		try {
			Field field = HelpItemLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, helpItemLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected HelpCategoryPersistence helpCategoryPersistence;

	protected HelpItemLocalService helpItemLocalService;

	@Reference
	protected HelpItemPersistence helpItemPersistence;

	@Reference
	protected HelpItemRolePersistence helpItemRolePersistence;

	@Reference
	protected HelpLinkPersistence helpLinkPersistence;

	@Reference
	protected HelpQuestionPersistence helpQuestionPersistence;

	@Reference
	protected HelpRelationPersistence helpRelationPersistence;

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
		HelpItemLocalServiceBaseImpl.class);

}