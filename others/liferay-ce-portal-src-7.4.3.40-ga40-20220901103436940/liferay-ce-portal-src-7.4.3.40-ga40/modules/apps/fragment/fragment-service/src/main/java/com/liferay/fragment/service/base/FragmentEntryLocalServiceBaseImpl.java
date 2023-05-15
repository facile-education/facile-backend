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

package com.liferay.fragment.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerRegistryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryVersion;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.fragment.service.FragmentEntryLocalServiceUtil;
import com.liferay.fragment.service.persistence.FragmentEntryFinder;
import com.liferay.fragment.service.persistence.FragmentEntryPersistence;
import com.liferay.fragment.service.persistence.FragmentEntryVersionPersistence;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
import com.liferay.portal.kernel.service.version.VersionService;
import com.liferay.portal.kernel.service.version.VersionServiceListener;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the fragment entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.fragment.service.impl.FragmentEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.fragment.service.impl.FragmentEntryLocalServiceImpl
 * @generated
 */
public abstract class FragmentEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, FragmentEntryLocalService, IdentifiableOSGiService,
			   VersionService<FragmentEntry, FragmentEntryVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>FragmentEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>FragmentEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the fragment entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FragmentEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fragmentEntry the fragment entry
	 * @return the fragment entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FragmentEntry addFragmentEntry(FragmentEntry fragmentEntry) {
		fragmentEntry.setNew(true);

		return fragmentEntryPersistence.update(fragmentEntry);
	}

	/**
	 * Creates a new fragment entry. Does not add the fragment entry to the database.
	 *
	 * @return the new fragment entry
	 */
	@Override
	@Transactional(enabled = false)
	public FragmentEntry create() {
		long primaryKey = counterLocalService.increment(
			FragmentEntry.class.getName());

		FragmentEntry draftFragmentEntry = fragmentEntryPersistence.create(
			primaryKey);

		draftFragmentEntry.setHeadId(primaryKey);

		return draftFragmentEntry;
	}

	/**
	 * Deletes the fragment entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FragmentEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fragmentEntryId the primary key of the fragment entry
	 * @return the fragment entry that was removed
	 * @throws PortalException if a fragment entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FragmentEntry deleteFragmentEntry(long fragmentEntryId)
		throws PortalException {

		FragmentEntry fragmentEntry =
			fragmentEntryPersistence.fetchByPrimaryKey(fragmentEntryId);

		if (fragmentEntry != null) {
			delete(fragmentEntry);
		}

		return fragmentEntry;
	}

	/**
	 * Deletes the fragment entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FragmentEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fragmentEntry the fragment entry
	 * @return the fragment entry that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FragmentEntry deleteFragmentEntry(FragmentEntry fragmentEntry)
		throws PortalException {

		delete(fragmentEntry);

		return fragmentEntry;
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return fragmentEntryPersistence.dslQuery(dslQuery);
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
			FragmentEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return fragmentEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fragment.model.impl.FragmentEntryModelImpl</code>.
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

		return fragmentEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fragment.model.impl.FragmentEntryModelImpl</code>.
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

		return fragmentEntryPersistence.findWithDynamicQuery(
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
		return fragmentEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return fragmentEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public FragmentEntry fetchFragmentEntry(long fragmentEntryId) {
		return fragmentEntryPersistence.fetchByPrimaryKey(fragmentEntryId);
	}

	/**
	 * Returns the fragment entry with the primary key.
	 *
	 * @param fragmentEntryId the primary key of the fragment entry
	 * @return the fragment entry
	 * @throws PortalException if a fragment entry with the primary key could not be found
	 */
	@Override
	public FragmentEntry getFragmentEntry(long fragmentEntryId)
		throws PortalException {

		return fragmentEntryPersistence.findByPrimaryKey(fragmentEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(fragmentEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FragmentEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fragmentEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			fragmentEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(FragmentEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"fragmentEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(fragmentEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FragmentEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fragmentEntryId");
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
					Criterion modifiedDateCriterion =
						portletDataContext.getDateRangeCriteria("modifiedDate");

					if (modifiedDateCriterion != null) {
						Conjunction conjunction =
							RestrictionsFactoryUtil.conjunction();

						conjunction.add(modifiedDateCriterion);

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(
							RestrictionsFactoryUtil.gtProperty(
								"modifiedDate", "lastPublishDate"));

						Property lastPublishDateProperty =
							PropertyFactoryUtil.forName("lastPublishDate");

						disjunction.add(lastPublishDateProperty.isNull());

						conjunction.add(disjunction);

						modifiedDateCriterion = conjunction;
					}

					Criterion statusDateCriterion =
						portletDataContext.getDateRangeCriteria("statusDate");

					if ((modifiedDateCriterion != null) &&
						(statusDateCriterion != null)) {

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(modifiedDateCriterion);
						disjunction.add(statusDateCriterion);

						dynamicQuery.add(disjunction);
					}

					Property workflowStatusProperty =
						PropertyFactoryUtil.forName("status");

					if (portletDataContext.isInitialPublication()) {
						dynamicQuery.add(
							workflowStatusProperty.ne(
								WorkflowConstants.STATUS_IN_TRASH));
					}
					else {
						StagedModelDataHandler<?> stagedModelDataHandler =
							StagedModelDataHandlerRegistryUtil.
								getStagedModelDataHandler(
									FragmentEntry.class.getName());

						dynamicQuery.add(
							workflowStatusProperty.in(
								stagedModelDataHandler.
									getExportableStatuses()));
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<FragmentEntry>() {

				@Override
				public void performAction(FragmentEntry fragmentEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, fragmentEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(FragmentEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return fragmentEntryPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return fragmentEntryLocalService.deleteFragmentEntry(
			(FragmentEntry)persistedModel);
	}

	@Override
	public BasePersistence<FragmentEntry> getBasePersistence() {
		return fragmentEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return fragmentEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the fragment entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fragment.model.impl.FragmentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment entries
	 * @param end the upper bound of the range of fragment entries (not inclusive)
	 * @return the range of fragment entries
	 */
	@Override
	public List<FragmentEntry> getFragmentEntries(int start, int end) {
		return fragmentEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of fragment entries.
	 *
	 * @return the number of fragment entries
	 */
	@Override
	public int getFragmentEntriesCount() {
		return fragmentEntryPersistence.countAll();
	}

	/**
	 * Updates the fragment entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FragmentEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param draftFragmentEntry the fragment entry
	 * @return the fragment entry that was updated
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FragmentEntry updateFragmentEntry(FragmentEntry draftFragmentEntry)
		throws PortalException {

		return updateDraft(draftFragmentEntry);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			FragmentEntryLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		fragmentEntryLocalService = (FragmentEntryLocalService)aopProxy;

		_setLocalServiceUtilService(fragmentEntryLocalService);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FragmentEntry checkout(
			FragmentEntry publishedFragmentEntry, int version)
		throws PortalException {

		if (!publishedFragmentEntry.isHead()) {
			throw new IllegalArgumentException(
				"Unable to checkout with unpublished changes " +
					publishedFragmentEntry.getHeadId());
		}

		FragmentEntry draftFragmentEntry =
			fragmentEntryPersistence.fetchByHeadId(
				publishedFragmentEntry.getPrimaryKey());

		if (draftFragmentEntry != null) {
			throw new IllegalArgumentException(
				"Unable to checkout with unpublished changes " +
					publishedFragmentEntry.getPrimaryKey());
		}

		FragmentEntryVersion fragmentEntryVersion = getVersion(
			publishedFragmentEntry, version);

		draftFragmentEntry = _createDraft(publishedFragmentEntry);

		fragmentEntryVersion.populateVersionedModel(draftFragmentEntry);

		draftFragmentEntry = fragmentEntryPersistence.update(
			draftFragmentEntry);

		for (VersionServiceListener<FragmentEntry, FragmentEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterCheckout(draftFragmentEntry, version);
		}

		return draftFragmentEntry;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public FragmentEntry delete(FragmentEntry publishedFragmentEntry)
		throws PortalException {

		if (!publishedFragmentEntry.isHead()) {
			throw new IllegalArgumentException(
				"FragmentEntry is a draft " +
					publishedFragmentEntry.getPrimaryKey());
		}

		FragmentEntry draftFragmentEntry =
			fragmentEntryPersistence.fetchByHeadId(
				publishedFragmentEntry.getPrimaryKey());

		if (draftFragmentEntry != null) {
			deleteDraft(draftFragmentEntry);
		}

		for (FragmentEntryVersion fragmentEntryVersion :
				getVersions(publishedFragmentEntry)) {

			fragmentEntryVersionPersistence.remove(fragmentEntryVersion);
		}

		fragmentEntryPersistence.remove(publishedFragmentEntry);

		for (VersionServiceListener<FragmentEntry, FragmentEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterDelete(publishedFragmentEntry);
		}

		return publishedFragmentEntry;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public FragmentEntry deleteDraft(FragmentEntry draftFragmentEntry)
		throws PortalException {

		if (draftFragmentEntry.isHead()) {
			throw new IllegalArgumentException(
				"FragmentEntry is not a draft " +
					draftFragmentEntry.getPrimaryKey());
		}

		fragmentEntryPersistence.remove(draftFragmentEntry);

		for (VersionServiceListener<FragmentEntry, FragmentEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterDeleteDraft(draftFragmentEntry);
		}

		return draftFragmentEntry;
	}

	@Override
	public FragmentEntryVersion deleteVersion(
			FragmentEntryVersion fragmentEntryVersion)
		throws PortalException {

		FragmentEntryVersion latestFragmentEntryVersion =
			fragmentEntryVersionPersistence.findByFragmentEntryId_First(
				fragmentEntryVersion.getVersionedModelId(), null);

		if (latestFragmentEntryVersion.getVersion() ==
				fragmentEntryVersion.getVersion()) {

			throw new IllegalArgumentException(
				"Unable to delete latest version " +
					fragmentEntryVersion.getVersion());
		}

		fragmentEntryVersion = fragmentEntryVersionPersistence.remove(
			fragmentEntryVersion);

		for (VersionServiceListener<FragmentEntry, FragmentEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterDeleteVersion(fragmentEntryVersion);
		}

		return fragmentEntryVersion;
	}

	@Override
	public FragmentEntry fetchDraft(FragmentEntry fragmentEntry) {
		if (fragmentEntry.isHead()) {
			return fragmentEntryPersistence.fetchByHeadId(
				fragmentEntry.getPrimaryKey());
		}

		return fragmentEntry;
	}

	@Override
	public FragmentEntry fetchDraft(long primaryKey) {
		return fragmentEntryPersistence.fetchByHeadId(primaryKey);
	}

	@Override
	public FragmentEntryVersion fetchLatestVersion(
		FragmentEntry fragmentEntry) {

		long primaryKey = fragmentEntry.getHeadId();

		if (fragmentEntry.isHead()) {
			primaryKey = fragmentEntry.getPrimaryKey();
		}

		return fragmentEntryVersionPersistence.fetchByFragmentEntryId_First(
			primaryKey, null);
	}

	@Override
	public FragmentEntry fetchPublished(FragmentEntry fragmentEntry) {
		if (fragmentEntry.isHead()) {
			return fragmentEntry;
		}

		if (fragmentEntry.getHeadId() == fragmentEntry.getPrimaryKey()) {
			return null;
		}

		return fragmentEntryPersistence.fetchByPrimaryKey(
			fragmentEntry.getHeadId());
	}

	@Override
	public FragmentEntry fetchPublished(long primaryKey) {
		FragmentEntry fragmentEntry =
			fragmentEntryPersistence.fetchByPrimaryKey(primaryKey);

		if ((fragmentEntry == null) ||
			(fragmentEntry.getHeadId() == fragmentEntry.getPrimaryKey())) {

			return null;
		}

		return fragmentEntry;
	}

	@Override
	public FragmentEntry getDraft(FragmentEntry fragmentEntry)
		throws PortalException {

		if (!fragmentEntry.isHead()) {
			return fragmentEntry;
		}

		FragmentEntry draftFragmentEntry =
			fragmentEntryPersistence.fetchByHeadId(
				fragmentEntry.getPrimaryKey());

		if (draftFragmentEntry == null) {
			draftFragmentEntry = fragmentEntryLocalService.updateDraft(
				_createDraft(fragmentEntry));
		}

		return draftFragmentEntry;
	}

	@Override
	public FragmentEntry getDraft(long primaryKey) throws PortalException {
		FragmentEntry draftFragmentEntry =
			fragmentEntryPersistence.fetchByHeadId(primaryKey);

		if (draftFragmentEntry == null) {
			FragmentEntry fragmentEntry =
				fragmentEntryPersistence.findByPrimaryKey(primaryKey);

			draftFragmentEntry = fragmentEntryLocalService.updateDraft(
				_createDraft(fragmentEntry));
		}

		return draftFragmentEntry;
	}

	@Override
	public FragmentEntryVersion getVersion(
			FragmentEntry fragmentEntry, int version)
		throws PortalException {

		long primaryKey = fragmentEntry.getHeadId();

		if (fragmentEntry.isHead()) {
			primaryKey = fragmentEntry.getPrimaryKey();
		}

		return fragmentEntryVersionPersistence.findByFragmentEntryId_Version(
			primaryKey, version);
	}

	@Override
	public List<FragmentEntryVersion> getVersions(FragmentEntry fragmentEntry) {
		long primaryKey = fragmentEntry.getPrimaryKey();

		if (!fragmentEntry.isHead()) {
			if (fragmentEntry.getHeadId() == fragmentEntry.getPrimaryKey()) {
				return Collections.emptyList();
			}

			primaryKey = fragmentEntry.getHeadId();
		}

		return fragmentEntryVersionPersistence.findByFragmentEntryId(
			primaryKey);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FragmentEntry publishDraft(FragmentEntry draftFragmentEntry)
		throws PortalException {

		if (draftFragmentEntry.isHead()) {
			throw new IllegalArgumentException(
				"Can only publish drafts " +
					draftFragmentEntry.getPrimaryKey());
		}

		FragmentEntry headFragmentEntry = null;

		int version = 1;

		if (draftFragmentEntry.getHeadId() ==
				draftFragmentEntry.getPrimaryKey()) {

			headFragmentEntry = create();

			draftFragmentEntry.setHeadId(headFragmentEntry.getPrimaryKey());
		}
		else {
			headFragmentEntry = fragmentEntryPersistence.findByPrimaryKey(
				draftFragmentEntry.getHeadId());

			FragmentEntryVersion latestFragmentEntryVersion =
				fragmentEntryVersionPersistence.findByFragmentEntryId_First(
					draftFragmentEntry.getHeadId(), null);

			version = latestFragmentEntryVersion.getVersion() + 1;
		}

		FragmentEntryVersion fragmentEntryVersion =
			fragmentEntryVersionPersistence.create(
				counterLocalService.increment(
					FragmentEntryVersion.class.getName()));

		fragmentEntryVersion.setVersion(version);
		fragmentEntryVersion.setVersionedModelId(
			headFragmentEntry.getPrimaryKey());

		draftFragmentEntry.populateVersionModel(fragmentEntryVersion);

		fragmentEntryVersionPersistence.update(fragmentEntryVersion);

		fragmentEntryVersion.populateVersionedModel(headFragmentEntry);

		headFragmentEntry.setHeadId(-headFragmentEntry.getPrimaryKey());

		headFragmentEntry = fragmentEntryPersistence.update(headFragmentEntry);

		for (VersionServiceListener<FragmentEntry, FragmentEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterPublishDraft(
				draftFragmentEntry, version);
		}

		deleteDraft(draftFragmentEntry);

		return headFragmentEntry;
	}

	@Override
	public void registerListener(
		VersionServiceListener<FragmentEntry, FragmentEntryVersion>
			versionServiceListener) {

		_versionServiceListeners.add(versionServiceListener);
	}

	@Override
	public void unregisterListener(
		VersionServiceListener<FragmentEntry, FragmentEntryVersion>
			versionServiceListener) {

		_versionServiceListeners.remove(versionServiceListener);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FragmentEntry updateDraft(FragmentEntry draftFragmentEntry)
		throws PortalException {

		if (draftFragmentEntry.isHead()) {
			throw new IllegalArgumentException(
				"Can only update draft entries " +
					draftFragmentEntry.getPrimaryKey());
		}

		FragmentEntry previousFragmentEntry =
			fragmentEntryPersistence.fetchByPrimaryKey(
				draftFragmentEntry.getPrimaryKey());

		draftFragmentEntry = fragmentEntryPersistence.update(
			draftFragmentEntry);

		if (previousFragmentEntry == null) {
			for (VersionServiceListener<FragmentEntry, FragmentEntryVersion>
					versionServiceListener : _versionServiceListeners) {

				versionServiceListener.afterCreateDraft(draftFragmentEntry);
			}
		}
		else {
			for (VersionServiceListener<FragmentEntry, FragmentEntryVersion>
					versionServiceListener : _versionServiceListeners) {

				versionServiceListener.afterUpdateDraft(draftFragmentEntry);
			}
		}

		return draftFragmentEntry;
	}

	private FragmentEntry _createDraft(FragmentEntry publishedFragmentEntry)
		throws PortalException {

		FragmentEntry draftFragmentEntry = create();

		draftFragmentEntry.setCtCollectionId(
			publishedFragmentEntry.getCtCollectionId());
		draftFragmentEntry.setUuid(publishedFragmentEntry.getUuid());
		draftFragmentEntry.setHeadId(publishedFragmentEntry.getPrimaryKey());
		draftFragmentEntry.setGroupId(publishedFragmentEntry.getGroupId());
		draftFragmentEntry.setCompanyId(publishedFragmentEntry.getCompanyId());
		draftFragmentEntry.setUserId(publishedFragmentEntry.getUserId());
		draftFragmentEntry.setUserName(publishedFragmentEntry.getUserName());
		draftFragmentEntry.setCreateDate(
			publishedFragmentEntry.getCreateDate());
		draftFragmentEntry.setModifiedDate(
			publishedFragmentEntry.getModifiedDate());
		draftFragmentEntry.setFragmentCollectionId(
			publishedFragmentEntry.getFragmentCollectionId());
		draftFragmentEntry.setFragmentEntryKey(
			publishedFragmentEntry.getFragmentEntryKey());
		draftFragmentEntry.setName(publishedFragmentEntry.getName());
		draftFragmentEntry.setCss(publishedFragmentEntry.getCss());
		draftFragmentEntry.setHtml(publishedFragmentEntry.getHtml());
		draftFragmentEntry.setJs(publishedFragmentEntry.getJs());
		draftFragmentEntry.setCacheable(publishedFragmentEntry.getCacheable());
		draftFragmentEntry.setConfiguration(
			publishedFragmentEntry.getConfiguration());
		draftFragmentEntry.setIcon(publishedFragmentEntry.getIcon());
		draftFragmentEntry.setPreviewFileEntryId(
			publishedFragmentEntry.getPreviewFileEntryId());
		draftFragmentEntry.setReadOnly(publishedFragmentEntry.getReadOnly());
		draftFragmentEntry.setType(publishedFragmentEntry.getType());
		draftFragmentEntry.setTypeOptions(
			publishedFragmentEntry.getTypeOptions());
		draftFragmentEntry.setLastPublishDate(
			publishedFragmentEntry.getLastPublishDate());
		draftFragmentEntry.setStatus(publishedFragmentEntry.getStatus());
		draftFragmentEntry.setStatusByUserId(
			publishedFragmentEntry.getStatusByUserId());
		draftFragmentEntry.setStatusByUserName(
			publishedFragmentEntry.getStatusByUserName());
		draftFragmentEntry.setStatusDate(
			publishedFragmentEntry.getStatusDate());

		draftFragmentEntry.resetOriginalValues();

		return draftFragmentEntry;
	}

	private final Set
		<VersionServiceListener<FragmentEntry, FragmentEntryVersion>>
			_versionServiceListeners = Collections.newSetFromMap(
				new ConcurrentHashMap
					<VersionServiceListener
						<FragmentEntry, FragmentEntryVersion>,
					 Boolean>());

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FragmentEntryLocalService.class.getName();
	}

	@Override
	public CTPersistence<FragmentEntry> getCTPersistence() {
		return fragmentEntryPersistence;
	}

	@Override
	public Class<FragmentEntry> getModelClass() {
		return FragmentEntry.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<FragmentEntry>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(fragmentEntryPersistence);
	}

	protected String getModelClassName() {
		return FragmentEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = fragmentEntryPersistence.getDataSource();

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
		FragmentEntryLocalService fragmentEntryLocalService) {

		try {
			Field field = FragmentEntryLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, fragmentEntryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected FragmentEntryLocalService fragmentEntryLocalService;

	@Reference
	protected FragmentEntryPersistence fragmentEntryPersistence;

	@Reference
	protected FragmentEntryFinder fragmentEntryFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected FragmentEntryVersionPersistence fragmentEntryVersionPersistence;

}