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

import com.weprode.nero.schedule.model.TeacherGroupColor;
import com.weprode.nero.schedule.service.TeacherGroupColorLocalService;
import com.weprode.nero.schedule.service.TeacherGroupColorLocalServiceUtil;
import com.weprode.nero.schedule.service.persistence.CDTSessionFinder;
import com.weprode.nero.schedule.service.persistence.CDTSessionPersistence;
import com.weprode.nero.schedule.service.persistence.DailySchedulePersistence;
import com.weprode.nero.schedule.service.persistence.HomeworkFinder;
import com.weprode.nero.schedule.service.persistence.HomeworkPersistence;
import com.weprode.nero.schedule.service.persistence.ScheduleConfigurationPersistence;
import com.weprode.nero.schedule.service.persistence.SessionParentClassPersistence;
import com.weprode.nero.schedule.service.persistence.SessionStudentPersistence;
import com.weprode.nero.schedule.service.persistence.SessionTeacherPersistence;
import com.weprode.nero.schedule.service.persistence.StudentHomeworkPersistence;
import com.weprode.nero.schedule.service.persistence.SubjectGroupColorPersistence;
import com.weprode.nero.schedule.service.persistence.TeacherGroupColorPersistence;
import com.weprode.nero.schedule.service.persistence.WeeklySchedulePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the teacher group color local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.schedule.service.impl.TeacherGroupColorLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.schedule.service.impl.TeacherGroupColorLocalServiceImpl
 * @generated
 */
public abstract class TeacherGroupColorLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   TeacherGroupColorLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>TeacherGroupColorLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>TeacherGroupColorLocalServiceUtil</code>.
	 */

	/**
	 * Adds the teacher group color to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherGroupColor the teacher group color
	 * @return the teacher group color that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TeacherGroupColor addTeacherGroupColor(
		TeacherGroupColor teacherGroupColor) {

		teacherGroupColor.setNew(true);

		return teacherGroupColorPersistence.update(teacherGroupColor);
	}

	/**
	 * Creates a new teacher group color with the primary key. Does not add the teacher group color to the database.
	 *
	 * @param teacherGroupColorId the primary key for the new teacher group color
	 * @return the new teacher group color
	 */
	@Override
	@Transactional(enabled = false)
	public TeacherGroupColor createTeacherGroupColor(long teacherGroupColorId) {
		return teacherGroupColorPersistence.create(teacherGroupColorId);
	}

	/**
	 * Deletes the teacher group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color that was removed
	 * @throws PortalException if a teacher group color with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TeacherGroupColor deleteTeacherGroupColor(long teacherGroupColorId)
		throws PortalException {

		return teacherGroupColorPersistence.remove(teacherGroupColorId);
	}

	/**
	 * Deletes the teacher group color from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherGroupColor the teacher group color
	 * @return the teacher group color that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TeacherGroupColor deleteTeacherGroupColor(
		TeacherGroupColor teacherGroupColor) {

		return teacherGroupColorPersistence.remove(teacherGroupColor);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return teacherGroupColorPersistence.dslQuery(dslQuery);
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
			TeacherGroupColor.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return teacherGroupColorPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.TeacherGroupColorModelImpl</code>.
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

		return teacherGroupColorPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.TeacherGroupColorModelImpl</code>.
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

		return teacherGroupColorPersistence.findWithDynamicQuery(
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
		return teacherGroupColorPersistence.countWithDynamicQuery(dynamicQuery);
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

		return teacherGroupColorPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public TeacherGroupColor fetchTeacherGroupColor(long teacherGroupColorId) {
		return teacherGroupColorPersistence.fetchByPrimaryKey(
			teacherGroupColorId);
	}

	/**
	 * Returns the teacher group color with the primary key.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color
	 * @throws PortalException if a teacher group color with the primary key could not be found
	 */
	@Override
	public TeacherGroupColor getTeacherGroupColor(long teacherGroupColorId)
		throws PortalException {

		return teacherGroupColorPersistence.findByPrimaryKey(
			teacherGroupColorId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			teacherGroupColorLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(TeacherGroupColor.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("teacherGroupColorId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			teacherGroupColorLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(TeacherGroupColor.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"teacherGroupColorId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			teacherGroupColorLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(TeacherGroupColor.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("teacherGroupColorId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return teacherGroupColorPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return teacherGroupColorLocalService.deleteTeacherGroupColor(
			(TeacherGroupColor)persistedModel);
	}

	@Override
	public BasePersistence<TeacherGroupColor> getBasePersistence() {
		return teacherGroupColorPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return teacherGroupColorPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the teacher group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @return the range of teacher group colors
	 */
	@Override
	public List<TeacherGroupColor> getTeacherGroupColors(int start, int end) {
		return teacherGroupColorPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of teacher group colors.
	 *
	 * @return the number of teacher group colors
	 */
	@Override
	public int getTeacherGroupColorsCount() {
		return teacherGroupColorPersistence.countAll();
	}

	/**
	 * Updates the teacher group color in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherGroupColor the teacher group color
	 * @return the teacher group color that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TeacherGroupColor updateTeacherGroupColor(
		TeacherGroupColor teacherGroupColor) {

		return teacherGroupColorPersistence.update(teacherGroupColor);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			TeacherGroupColorLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		teacherGroupColorLocalService = (TeacherGroupColorLocalService)aopProxy;

		_setLocalServiceUtilService(teacherGroupColorLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return TeacherGroupColorLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return TeacherGroupColor.class;
	}

	protected String getModelClassName() {
		return TeacherGroupColor.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				teacherGroupColorPersistence.getDataSource();

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
		TeacherGroupColorLocalService teacherGroupColorLocalService) {

		try {
			Field field =
				TeacherGroupColorLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, teacherGroupColorLocalService);
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
	protected DailySchedulePersistence dailySchedulePersistence;

	@Reference
	protected HomeworkPersistence homeworkPersistence;

	@Reference
	protected HomeworkFinder homeworkFinder;

	@Reference
	protected ScheduleConfigurationPersistence scheduleConfigurationPersistence;

	@Reference
	protected SessionParentClassPersistence sessionParentClassPersistence;

	@Reference
	protected SessionStudentPersistence sessionStudentPersistence;

	@Reference
	protected SessionTeacherPersistence sessionTeacherPersistence;

	@Reference
	protected StudentHomeworkPersistence studentHomeworkPersistence;

	@Reference
	protected SubjectGroupColorPersistence subjectGroupColorPersistence;

	protected TeacherGroupColorLocalService teacherGroupColorLocalService;

	@Reference
	protected TeacherGroupColorPersistence teacherGroupColorPersistence;

	@Reference
	protected WeeklySchedulePersistence weeklySchedulePersistence;

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