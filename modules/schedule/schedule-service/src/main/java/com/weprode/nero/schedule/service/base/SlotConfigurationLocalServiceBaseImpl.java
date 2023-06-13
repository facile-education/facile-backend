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

package com.weprode.nero.schedule.service.base;

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

import com.weprode.nero.schedule.model.SlotConfiguration;
import com.weprode.nero.schedule.service.SlotConfigurationLocalService;
import com.weprode.nero.schedule.service.SlotConfigurationLocalServiceUtil;
import com.weprode.nero.schedule.service.persistence.CDTSessionFinder;
import com.weprode.nero.schedule.service.persistence.CDTSessionPersistence;
import com.weprode.nero.schedule.service.persistence.CourseDetailsPersistence;
import com.weprode.nero.schedule.service.persistence.HolidayPersistence;
import com.weprode.nero.schedule.service.persistence.ScheduleConfigurationPersistence;
import com.weprode.nero.schedule.service.persistence.SessionStudentPersistence;
import com.weprode.nero.schedule.service.persistence.SessionTeacherPersistence;
import com.weprode.nero.schedule.service.persistence.SlotConfigurationPK;
import com.weprode.nero.schedule.service.persistence.SlotConfigurationPersistence;
import com.weprode.nero.schedule.service.persistence.SubjectPersistence;
import com.weprode.nero.schedule.service.persistence.TeacherSubjectPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the slot configuration local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.schedule.service.impl.SlotConfigurationLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.schedule.service.impl.SlotConfigurationLocalServiceImpl
 * @generated
 */
public abstract class SlotConfigurationLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   SlotConfigurationLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SlotConfigurationLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SlotConfigurationLocalServiceUtil</code>.
	 */

	/**
	 * Adds the slot configuration to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SlotConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param slotConfiguration the slot configuration
	 * @return the slot configuration that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SlotConfiguration addSlotConfiguration(
		SlotConfiguration slotConfiguration) {

		slotConfiguration.setNew(true);

		return slotConfigurationPersistence.update(slotConfiguration);
	}

	/**
	 * Creates a new slot configuration with the primary key. Does not add the slot configuration to the database.
	 *
	 * @param slotConfigurationPK the primary key for the new slot configuration
	 * @return the new slot configuration
	 */
	@Override
	@Transactional(enabled = false)
	public SlotConfiguration createSlotConfiguration(
		SlotConfigurationPK slotConfigurationPK) {

		return slotConfigurationPersistence.create(slotConfigurationPK);
	}

	/**
	 * Deletes the slot configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SlotConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration that was removed
	 * @throws PortalException if a slot configuration with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SlotConfiguration deleteSlotConfiguration(
			SlotConfigurationPK slotConfigurationPK)
		throws PortalException {

		return slotConfigurationPersistence.remove(slotConfigurationPK);
	}

	/**
	 * Deletes the slot configuration from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SlotConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param slotConfiguration the slot configuration
	 * @return the slot configuration that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SlotConfiguration deleteSlotConfiguration(
		SlotConfiguration slotConfiguration) {

		return slotConfigurationPersistence.remove(slotConfiguration);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return slotConfigurationPersistence.dslQuery(dslQuery);
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
			SlotConfiguration.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return slotConfigurationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SlotConfigurationModelImpl</code>.
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

		return slotConfigurationPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SlotConfigurationModelImpl</code>.
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

		return slotConfigurationPersistence.findWithDynamicQuery(
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
		return slotConfigurationPersistence.countWithDynamicQuery(dynamicQuery);
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

		return slotConfigurationPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SlotConfiguration fetchSlotConfiguration(
		SlotConfigurationPK slotConfigurationPK) {

		return slotConfigurationPersistence.fetchByPrimaryKey(
			slotConfigurationPK);
	}

	/**
	 * Returns the slot configuration with the primary key.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration
	 * @throws PortalException if a slot configuration with the primary key could not be found
	 */
	@Override
	public SlotConfiguration getSlotConfiguration(
			SlotConfigurationPK slotConfigurationPK)
		throws PortalException {

		return slotConfigurationPersistence.findByPrimaryKey(
			slotConfigurationPK);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			slotConfigurationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SlotConfiguration.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("primaryKey.schoolId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			slotConfigurationLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(SlotConfiguration.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.schoolId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			slotConfigurationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SlotConfiguration.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("primaryKey.schoolId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return slotConfigurationPersistence.create(
			(SlotConfigurationPK)primaryKeyObj);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return slotConfigurationLocalService.deleteSlotConfiguration(
			(SlotConfiguration)persistedModel);
	}

	@Override
	public BasePersistence<SlotConfiguration> getBasePersistence() {
		return slotConfigurationPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return slotConfigurationPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the slot configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @return the range of slot configurations
	 */
	@Override
	public List<SlotConfiguration> getSlotConfigurations(int start, int end) {
		return slotConfigurationPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of slot configurations.
	 *
	 * @return the number of slot configurations
	 */
	@Override
	public int getSlotConfigurationsCount() {
		return slotConfigurationPersistence.countAll();
	}

	/**
	 * Updates the slot configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SlotConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param slotConfiguration the slot configuration
	 * @return the slot configuration that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SlotConfiguration updateSlotConfiguration(
		SlotConfiguration slotConfiguration) {

		return slotConfigurationPersistence.update(slotConfiguration);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SlotConfigurationLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		slotConfigurationLocalService = (SlotConfigurationLocalService)aopProxy;

		_setLocalServiceUtilService(slotConfigurationLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SlotConfigurationLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SlotConfiguration.class;
	}

	protected String getModelClassName() {
		return SlotConfiguration.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				slotConfigurationPersistence.getDataSource();

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
		SlotConfigurationLocalService slotConfigurationLocalService) {

		try {
			Field field =
				SlotConfigurationLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, slotConfigurationLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected CDTSessionPersistence cdtSessionPersistence;

	@Reference
	protected CDTSessionFinder cdtSessionFinder;

	@Reference
	protected CourseDetailsPersistence courseDetailsPersistence;

	@Reference
	protected HolidayPersistence holidayPersistence;

	@Reference
	protected ScheduleConfigurationPersistence scheduleConfigurationPersistence;

	@Reference
	protected SessionStudentPersistence sessionStudentPersistence;

	@Reference
	protected SessionTeacherPersistence sessionTeacherPersistence;

	protected SlotConfigurationLocalService slotConfigurationLocalService;

	@Reference
	protected SlotConfigurationPersistence slotConfigurationPersistence;

	@Reference
	protected SubjectPersistence subjectPersistence;

	@Reference
	protected TeacherSubjectPersistence teacherSubjectPersistence;

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