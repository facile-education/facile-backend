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

package com.weprode.nero.course.service.base;

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

import com.weprode.nero.course.model.ContentBlock;
import com.weprode.nero.course.service.ContentBlockLocalService;
import com.weprode.nero.course.service.ContentBlockLocalServiceUtil;
import com.weprode.nero.course.service.persistence.ContentBlockPersistence;
import com.weprode.nero.course.service.persistence.HomeworkFinder;
import com.weprode.nero.course.service.persistence.HomeworkPersistence;
import com.weprode.nero.course.service.persistence.SessionContentFinder;
import com.weprode.nero.course.service.persistence.SessionContentPersistence;
import com.weprode.nero.course.service.persistence.StudentHomeworkPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the content block local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.course.service.impl.ContentBlockLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.course.service.impl.ContentBlockLocalServiceImpl
 * @generated
 */
public abstract class ContentBlockLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, ContentBlockLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ContentBlockLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ContentBlockLocalServiceUtil</code>.
	 */

	/**
	 * Adds the content block to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContentBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contentBlock the content block
	 * @return the content block that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ContentBlock addContentBlock(ContentBlock contentBlock) {
		contentBlock.setNew(true);

		return contentBlockPersistence.update(contentBlock);
	}

	/**
	 * Creates a new content block with the primary key. Does not add the content block to the database.
	 *
	 * @param blockId the primary key for the new content block
	 * @return the new content block
	 */
	@Override
	@Transactional(enabled = false)
	public ContentBlock createContentBlock(long blockId) {
		return contentBlockPersistence.create(blockId);
	}

	/**
	 * Deletes the content block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContentBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block that was removed
	 * @throws PortalException if a content block with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ContentBlock deleteContentBlock(long blockId)
		throws PortalException {

		return contentBlockPersistence.remove(blockId);
	}

	/**
	 * Deletes the content block from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContentBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contentBlock the content block
	 * @return the content block that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ContentBlock deleteContentBlock(ContentBlock contentBlock) {
		return contentBlockPersistence.remove(contentBlock);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return contentBlockPersistence.dslQuery(dslQuery);
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
			ContentBlock.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return contentBlockPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.ContentBlockModelImpl</code>.
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

		return contentBlockPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.ContentBlockModelImpl</code>.
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

		return contentBlockPersistence.findWithDynamicQuery(
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
		return contentBlockPersistence.countWithDynamicQuery(dynamicQuery);
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

		return contentBlockPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public ContentBlock fetchContentBlock(long blockId) {
		return contentBlockPersistence.fetchByPrimaryKey(blockId);
	}

	/**
	 * Returns the content block with the primary key.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block
	 * @throws PortalException if a content block with the primary key could not be found
	 */
	@Override
	public ContentBlock getContentBlock(long blockId) throws PortalException {
		return contentBlockPersistence.findByPrimaryKey(blockId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(contentBlockLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ContentBlock.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("blockId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			contentBlockLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ContentBlock.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("blockId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(contentBlockLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ContentBlock.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("blockId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return contentBlockPersistence.create(
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
				"Implement ContentBlockLocalServiceImpl#deleteContentBlock(ContentBlock) to avoid orphaned data");
		}

		return contentBlockLocalService.deleteContentBlock(
			(ContentBlock)persistedModel);
	}

	@Override
	public BasePersistence<ContentBlock> getBasePersistence() {
		return contentBlockPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return contentBlockPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the content blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @return the range of content blocks
	 */
	@Override
	public List<ContentBlock> getContentBlocks(int start, int end) {
		return contentBlockPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of content blocks.
	 *
	 * @return the number of content blocks
	 */
	@Override
	public int getContentBlocksCount() {
		return contentBlockPersistence.countAll();
	}

	/**
	 * Updates the content block in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContentBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contentBlock the content block
	 * @return the content block that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ContentBlock updateContentBlock(ContentBlock contentBlock) {
		return contentBlockPersistence.update(contentBlock);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			ContentBlockLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		contentBlockLocalService = (ContentBlockLocalService)aopProxy;

		_setLocalServiceUtilService(contentBlockLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ContentBlockLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ContentBlock.class;
	}

	protected String getModelClassName() {
		return ContentBlock.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = contentBlockPersistence.getDataSource();

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
		ContentBlockLocalService contentBlockLocalService) {

		try {
			Field field = ContentBlockLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, contentBlockLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected ContentBlockLocalService contentBlockLocalService;

	@Reference
	protected ContentBlockPersistence contentBlockPersistence;

	@Reference
	protected HomeworkPersistence homeworkPersistence;

	@Reference
	protected HomeworkFinder homeworkFinder;

	@Reference
	protected SessionContentPersistence sessionContentPersistence;

	@Reference
	protected SessionContentFinder sessionContentFinder;

	@Reference
	protected StudentHomeworkPersistence studentHomeworkPersistence;

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
		ContentBlockLocalServiceBaseImpl.class);

}