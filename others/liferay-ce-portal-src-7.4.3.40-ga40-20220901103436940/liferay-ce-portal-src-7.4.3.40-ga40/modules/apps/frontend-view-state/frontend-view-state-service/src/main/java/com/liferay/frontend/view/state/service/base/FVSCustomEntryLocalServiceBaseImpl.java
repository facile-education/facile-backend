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

package com.liferay.frontend.view.state.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.frontend.view.state.model.FVSCustomEntry;
import com.liferay.frontend.view.state.service.FVSCustomEntryLocalService;
import com.liferay.frontend.view.state.service.FVSCustomEntryLocalServiceUtil;
import com.liferay.frontend.view.state.service.persistence.FVSCustomEntryPersistence;
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
import com.liferay.portal.kernel.service.persistence.BasePersistence;
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
 * Provides the base implementation for the fvs custom entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.frontend.view.state.service.impl.FVSCustomEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.frontend.view.state.service.impl.FVSCustomEntryLocalServiceImpl
 * @generated
 */
public abstract class FVSCustomEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, FVSCustomEntryLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>FVSCustomEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>FVSCustomEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the fvs custom entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FVSCustomEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fvsCustomEntry the fvs custom entry
	 * @return the fvs custom entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FVSCustomEntry addFVSCustomEntry(FVSCustomEntry fvsCustomEntry) {
		fvsCustomEntry.setNew(true);

		return fvsCustomEntryPersistence.update(fvsCustomEntry);
	}

	/**
	 * Creates a new fvs custom entry with the primary key. Does not add the fvs custom entry to the database.
	 *
	 * @param fvsCustomEntryId the primary key for the new fvs custom entry
	 * @return the new fvs custom entry
	 */
	@Override
	@Transactional(enabled = false)
	public FVSCustomEntry createFVSCustomEntry(long fvsCustomEntryId) {
		return fvsCustomEntryPersistence.create(fvsCustomEntryId);
	}

	/**
	 * Deletes the fvs custom entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FVSCustomEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fvsCustomEntryId the primary key of the fvs custom entry
	 * @return the fvs custom entry that was removed
	 * @throws PortalException if a fvs custom entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FVSCustomEntry deleteFVSCustomEntry(long fvsCustomEntryId)
		throws PortalException {

		return fvsCustomEntryPersistence.remove(fvsCustomEntryId);
	}

	/**
	 * Deletes the fvs custom entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FVSCustomEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fvsCustomEntry the fvs custom entry
	 * @return the fvs custom entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FVSCustomEntry deleteFVSCustomEntry(FVSCustomEntry fvsCustomEntry) {
		return fvsCustomEntryPersistence.remove(fvsCustomEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return fvsCustomEntryPersistence.dslQuery(dslQuery);
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
			FVSCustomEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return fvsCustomEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.frontend.view.state.model.impl.FVSCustomEntryModelImpl</code>.
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

		return fvsCustomEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.frontend.view.state.model.impl.FVSCustomEntryModelImpl</code>.
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

		return fvsCustomEntryPersistence.findWithDynamicQuery(
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
		return fvsCustomEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return fvsCustomEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public FVSCustomEntry fetchFVSCustomEntry(long fvsCustomEntryId) {
		return fvsCustomEntryPersistence.fetchByPrimaryKey(fvsCustomEntryId);
	}

	/**
	 * Returns the fvs custom entry with the matching UUID and company.
	 *
	 * @param uuid the fvs custom entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching fvs custom entry, or <code>null</code> if a matching fvs custom entry could not be found
	 */
	@Override
	public FVSCustomEntry fetchFVSCustomEntryByUuidAndCompanyId(
		String uuid, long companyId) {

		return fvsCustomEntryPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the fvs custom entry with the primary key.
	 *
	 * @param fvsCustomEntryId the primary key of the fvs custom entry
	 * @return the fvs custom entry
	 * @throws PortalException if a fvs custom entry with the primary key could not be found
	 */
	@Override
	public FVSCustomEntry getFVSCustomEntry(long fvsCustomEntryId)
		throws PortalException {

		return fvsCustomEntryPersistence.findByPrimaryKey(fvsCustomEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(fvsCustomEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FVSCustomEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fvsCustomEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			fvsCustomEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(FVSCustomEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"fvsCustomEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(fvsCustomEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FVSCustomEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fvsCustomEntryId");
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

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<FVSCustomEntry>() {

				@Override
				public void performAction(FVSCustomEntry fvsCustomEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, fvsCustomEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(FVSCustomEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return fvsCustomEntryPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return fvsCustomEntryLocalService.deleteFVSCustomEntry(
			(FVSCustomEntry)persistedModel);
	}

	@Override
	public BasePersistence<FVSCustomEntry> getBasePersistence() {
		return fvsCustomEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return fvsCustomEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the fvs custom entry with the matching UUID and company.
	 *
	 * @param uuid the fvs custom entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching fvs custom entry
	 * @throws PortalException if a matching fvs custom entry could not be found
	 */
	@Override
	public FVSCustomEntry getFVSCustomEntryByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return fvsCustomEntryPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the fvs custom entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.frontend.view.state.model.impl.FVSCustomEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fvs custom entries
	 * @param end the upper bound of the range of fvs custom entries (not inclusive)
	 * @return the range of fvs custom entries
	 */
	@Override
	public List<FVSCustomEntry> getFVSCustomEntries(int start, int end) {
		return fvsCustomEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of fvs custom entries.
	 *
	 * @return the number of fvs custom entries
	 */
	@Override
	public int getFVSCustomEntriesCount() {
		return fvsCustomEntryPersistence.countAll();
	}

	/**
	 * Updates the fvs custom entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FVSCustomEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fvsCustomEntry the fvs custom entry
	 * @return the fvs custom entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FVSCustomEntry updateFVSCustomEntry(FVSCustomEntry fvsCustomEntry) {
		return fvsCustomEntryPersistence.update(fvsCustomEntry);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			FVSCustomEntryLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		fvsCustomEntryLocalService = (FVSCustomEntryLocalService)aopProxy;

		_setLocalServiceUtilService(fvsCustomEntryLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FVSCustomEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return FVSCustomEntry.class;
	}

	protected String getModelClassName() {
		return FVSCustomEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = fvsCustomEntryPersistence.getDataSource();

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
		FVSCustomEntryLocalService fvsCustomEntryLocalService) {

		try {
			Field field = FVSCustomEntryLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, fvsCustomEntryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected FVSCustomEntryLocalService fvsCustomEntryLocalService;

	@Reference
	protected FVSCustomEntryPersistence fvsCustomEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}