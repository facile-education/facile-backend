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

package com.liferay.dynamic.data.mapping.service.base;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalService;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstancePersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the ddm form instance local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceLocalServiceImpl
 * @generated
 */
public abstract class DDMFormInstanceLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, DDMFormInstanceLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DDMFormInstanceLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>DDMFormInstanceLocalServiceUtil</code>.
	 */

	/**
	 * Adds the ddm form instance to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMFormInstanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmFormInstance the ddm form instance
	 * @return the ddm form instance that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMFormInstance addDDMFormInstance(DDMFormInstance ddmFormInstance) {
		ddmFormInstance.setNew(true);

		return ddmFormInstancePersistence.update(ddmFormInstance);
	}

	/**
	 * Creates a new ddm form instance with the primary key. Does not add the ddm form instance to the database.
	 *
	 * @param formInstanceId the primary key for the new ddm form instance
	 * @return the new ddm form instance
	 */
	@Override
	@Transactional(enabled = false)
	public DDMFormInstance createDDMFormInstance(long formInstanceId) {
		return ddmFormInstancePersistence.create(formInstanceId);
	}

	/**
	 * Deletes the ddm form instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMFormInstanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formInstanceId the primary key of the ddm form instance
	 * @return the ddm form instance that was removed
	 * @throws PortalException if a ddm form instance with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMFormInstance deleteDDMFormInstance(long formInstanceId)
		throws PortalException {

		return ddmFormInstancePersistence.remove(formInstanceId);
	}

	/**
	 * Deletes the ddm form instance from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMFormInstanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmFormInstance the ddm form instance
	 * @return the ddm form instance that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMFormInstance deleteDDMFormInstance(
		DDMFormInstance ddmFormInstance) {

		return ddmFormInstancePersistence.remove(ddmFormInstance);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return ddmFormInstancePersistence.dslQuery(dslQuery);
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
			DDMFormInstance.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ddmFormInstancePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceModelImpl</code>.
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

		return ddmFormInstancePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceModelImpl</code>.
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

		return ddmFormInstancePersistence.findWithDynamicQuery(
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
		return ddmFormInstancePersistence.countWithDynamicQuery(dynamicQuery);
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

		return ddmFormInstancePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DDMFormInstance fetchDDMFormInstance(long formInstanceId) {
		return ddmFormInstancePersistence.fetchByPrimaryKey(formInstanceId);
	}

	/**
	 * Returns the ddm form instance matching the UUID and group.
	 *
	 * @param uuid the ddm form instance's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddm form instance, or <code>null</code> if a matching ddm form instance could not be found
	 */
	@Override
	public DDMFormInstance fetchDDMFormInstanceByUuidAndGroupId(
		String uuid, long groupId) {

		return ddmFormInstancePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the ddm form instance with the primary key.
	 *
	 * @param formInstanceId the primary key of the ddm form instance
	 * @return the ddm form instance
	 * @throws PortalException if a ddm form instance with the primary key could not be found
	 */
	@Override
	public DDMFormInstance getDDMFormInstance(long formInstanceId)
		throws PortalException {

		return ddmFormInstancePersistence.findByPrimaryKey(formInstanceId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ddmFormInstanceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMFormInstance.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("formInstanceId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			ddmFormInstanceLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DDMFormInstance.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"formInstanceId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(ddmFormInstanceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMFormInstance.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("formInstanceId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DDMFormInstance>() {

				@Override
				public void performAction(DDMFormInstance ddmFormInstance)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, ddmFormInstance);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(DDMFormInstance.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ddmFormInstancePersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return ddmFormInstanceLocalService.deleteDDMFormInstance(
			(DDMFormInstance)persistedModel);
	}

	@Override
	public BasePersistence<DDMFormInstance> getBasePersistence() {
		return ddmFormInstancePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ddmFormInstancePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the ddm form instances matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddm form instances
	 * @param companyId the primary key of the company
	 * @return the matching ddm form instances, or an empty list if no matches were found
	 */
	@Override
	public List<DDMFormInstance> getDDMFormInstancesByUuidAndCompanyId(
		String uuid, long companyId) {

		return ddmFormInstancePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of ddm form instances matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddm form instances
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of ddm form instances
	 * @param end the upper bound of the range of ddm form instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching ddm form instances, or an empty list if no matches were found
	 */
	@Override
	public List<DDMFormInstance> getDDMFormInstancesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DDMFormInstance> orderByComparator) {

		return ddmFormInstancePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the ddm form instance matching the UUID and group.
	 *
	 * @param uuid the ddm form instance's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddm form instance
	 * @throws PortalException if a matching ddm form instance could not be found
	 */
	@Override
	public DDMFormInstance getDDMFormInstanceByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return ddmFormInstancePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the ddm form instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instances
	 * @param end the upper bound of the range of ddm form instances (not inclusive)
	 * @return the range of ddm form instances
	 */
	@Override
	public List<DDMFormInstance> getDDMFormInstances(int start, int end) {
		return ddmFormInstancePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of ddm form instances.
	 *
	 * @return the number of ddm form instances
	 */
	@Override
	public int getDDMFormInstancesCount() {
		return ddmFormInstancePersistence.countAll();
	}

	/**
	 * Updates the ddm form instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMFormInstanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmFormInstance the ddm form instance
	 * @return the ddm form instance that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMFormInstance updateDDMFormInstance(
		DDMFormInstance ddmFormInstance) {

		return ddmFormInstancePersistence.update(ddmFormInstance);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			DDMFormInstanceLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		ddmFormInstanceLocalService = (DDMFormInstanceLocalService)aopProxy;

		_setLocalServiceUtilService(ddmFormInstanceLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMFormInstanceLocalService.class.getName();
	}

	@Override
	public CTPersistence<DDMFormInstance> getCTPersistence() {
		return ddmFormInstancePersistence;
	}

	@Override
	public Class<DDMFormInstance> getModelClass() {
		return DDMFormInstance.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<DDMFormInstance>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(ddmFormInstancePersistence);
	}

	protected String getModelClassName() {
		return DDMFormInstance.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmFormInstancePersistence.getDataSource();

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
		DDMFormInstanceLocalService ddmFormInstanceLocalService) {

		try {
			Field field =
				DDMFormInstanceLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, ddmFormInstanceLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected DDMFormInstanceLocalService ddmFormInstanceLocalService;

	@Reference
	protected DDMFormInstancePersistence ddmFormInstancePersistence;

	@Reference
	protected DDMFormInstanceFinder ddmFormInstanceFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}