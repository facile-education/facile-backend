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

import com.weprode.nero.schedule.model.SessionParentClass;
import com.weprode.nero.schedule.service.SessionParentClassLocalService;
import com.weprode.nero.schedule.service.SessionParentClassLocalServiceUtil;
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
 * Provides the base implementation for the session parent class local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.schedule.service.impl.SessionParentClassLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.schedule.service.impl.SessionParentClassLocalServiceImpl
 * @generated
 */
public abstract class SessionParentClassLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   SessionParentClassLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SessionParentClassLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SessionParentClassLocalServiceUtil</code>.
	 */

	/**
	 * Adds the session parent class to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClass the session parent class
	 * @return the session parent class that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SessionParentClass addSessionParentClass(
		SessionParentClass sessionParentClass) {

		sessionParentClass.setNew(true);

		return sessionParentClassPersistence.update(sessionParentClass);
	}

	/**
	 * Creates a new session parent class with the primary key. Does not add the session parent class to the database.
	 *
	 * @param sessionParentClassId the primary key for the new session parent class
	 * @return the new session parent class
	 */
	@Override
	@Transactional(enabled = false)
	public SessionParentClass createSessionParentClass(
		long sessionParentClassId) {

		return sessionParentClassPersistence.create(sessionParentClassId);
	}

	/**
	 * Deletes the session parent class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class that was removed
	 * @throws PortalException if a session parent class with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SessionParentClass deleteSessionParentClass(
			long sessionParentClassId)
		throws PortalException {

		return sessionParentClassPersistence.remove(sessionParentClassId);
	}

	/**
	 * Deletes the session parent class from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClass the session parent class
	 * @return the session parent class that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SessionParentClass deleteSessionParentClass(
		SessionParentClass sessionParentClass) {

		return sessionParentClassPersistence.remove(sessionParentClass);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return sessionParentClassPersistence.dslQuery(dslQuery);
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
			SessionParentClass.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return sessionParentClassPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl</code>.
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

		return sessionParentClassPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl</code>.
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

		return sessionParentClassPersistence.findWithDynamicQuery(
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
		return sessionParentClassPersistence.countWithDynamicQuery(
			dynamicQuery);
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

		return sessionParentClassPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SessionParentClass fetchSessionParentClass(
		long sessionParentClassId) {

		return sessionParentClassPersistence.fetchByPrimaryKey(
			sessionParentClassId);
	}

	/**
	 * Returns the session parent class with the primary key.
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class
	 * @throws PortalException if a session parent class with the primary key could not be found
	 */
	@Override
	public SessionParentClass getSessionParentClass(long sessionParentClassId)
		throws PortalException {

		return sessionParentClassPersistence.findByPrimaryKey(
			sessionParentClassId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			sessionParentClassLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SessionParentClass.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"sessionParentClassId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			sessionParentClassLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(SessionParentClass.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"sessionParentClassId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			sessionParentClassLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SessionParentClass.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"sessionParentClassId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return sessionParentClassPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return sessionParentClassLocalService.deleteSessionParentClass(
			(SessionParentClass)persistedModel);
	}

	@Override
	public BasePersistence<SessionParentClass> getBasePersistence() {
		return sessionParentClassPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return sessionParentClassPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the session parent classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of session parent classes
	 */
	@Override
	public List<SessionParentClass> getSessionParentClasses(
		int start, int end) {

		return sessionParentClassPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of session parent classes.
	 *
	 * @return the number of session parent classes
	 */
	@Override
	public int getSessionParentClassesCount() {
		return sessionParentClassPersistence.countAll();
	}

	/**
	 * Updates the session parent class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClass the session parent class
	 * @return the session parent class that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SessionParentClass updateSessionParentClass(
		SessionParentClass sessionParentClass) {

		return sessionParentClassPersistence.update(sessionParentClass);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SessionParentClassLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		sessionParentClassLocalService =
			(SessionParentClassLocalService)aopProxy;

		_setLocalServiceUtilService(sessionParentClassLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SessionParentClassLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SessionParentClass.class;
	}

	protected String getModelClassName() {
		return SessionParentClass.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				sessionParentClassPersistence.getDataSource();

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
		SessionParentClassLocalService sessionParentClassLocalService) {

		try {
			Field field =
				SessionParentClassLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, sessionParentClassLocalService);
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

	protected SessionParentClassLocalService sessionParentClassLocalService;

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