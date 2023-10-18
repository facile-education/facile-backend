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

import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalService;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.persistence.CDTSessionFinder;
import com.weprode.nero.schedule.service.persistence.CDTSessionPersistence;
import com.weprode.nero.schedule.service.persistence.CourseDetailsPersistence;
import com.weprode.nero.schedule.service.persistence.HolidayPersistence;
import com.weprode.nero.schedule.service.persistence.ScheduleConfigurationPersistence;
import com.weprode.nero.schedule.service.persistence.SessionStudentPersistence;
import com.weprode.nero.schedule.service.persistence.SessionTeacherPersistence;
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
 * Provides the base implementation for the cdt session local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.schedule.service.impl.CDTSessionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.schedule.service.impl.CDTSessionLocalServiceImpl
 * @generated
 */
public abstract class CDTSessionLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CDTSessionLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CDTSessionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CDTSessionLocalServiceUtil</code>.
	 */

	/**
	 * Adds the cdt session to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cdtSession the cdt session
	 * @return the cdt session that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CDTSession addCDTSession(CDTSession cdtSession) {
		cdtSession.setNew(true);

		return cdtSessionPersistence.update(cdtSession);
	}

	/**
	 * Creates a new cdt session with the primary key. Does not add the cdt session to the database.
	 *
	 * @param sessionId the primary key for the new cdt session
	 * @return the new cdt session
	 */
	@Override
	@Transactional(enabled = false)
	public CDTSession createCDTSession(long sessionId) {
		return cdtSessionPersistence.create(sessionId);
	}

	/**
	 * Deletes the cdt session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session that was removed
	 * @throws PortalException if a cdt session with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CDTSession deleteCDTSession(long sessionId) throws PortalException {
		return cdtSessionPersistence.remove(sessionId);
	}

	/**
	 * Deletes the cdt session from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cdtSession the cdt session
	 * @return the cdt session that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CDTSession deleteCDTSession(CDTSession cdtSession) {
		return cdtSessionPersistence.remove(cdtSession);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return cdtSessionPersistence.dslQuery(dslQuery);
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
			CDTSession.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return cdtSessionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.CDTSessionModelImpl</code>.
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

		return cdtSessionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.CDTSessionModelImpl</code>.
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

		return cdtSessionPersistence.findWithDynamicQuery(
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
		return cdtSessionPersistence.countWithDynamicQuery(dynamicQuery);
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

		return cdtSessionPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CDTSession fetchCDTSession(long sessionId) {
		return cdtSessionPersistence.fetchByPrimaryKey(sessionId);
	}

	/**
	 * Returns the cdt session with the primary key.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session
	 * @throws PortalException if a cdt session with the primary key could not be found
	 */
	@Override
	public CDTSession getCDTSession(long sessionId) throws PortalException {
		return cdtSessionPersistence.findByPrimaryKey(sessionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(cdtSessionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CDTSession.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("sessionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			cdtSessionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CDTSession.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("sessionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(cdtSessionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CDTSession.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("sessionId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cdtSessionPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement CDTSessionLocalServiceImpl#deleteCDTSession(CDTSession) to avoid orphaned data");
		}

		return cdtSessionLocalService.deleteCDTSession(
			(CDTSession)persistedModel);
	}

	@Override
	public BasePersistence<CDTSession> getBasePersistence() {
		return cdtSessionPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cdtSessionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @return the range of cdt sessions
	 */
	@Override
	public List<CDTSession> getCDTSessions(int start, int end) {
		return cdtSessionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cdt sessions.
	 *
	 * @return the number of cdt sessions
	 */
	@Override
	public int getCDTSessionsCount() {
		return cdtSessionPersistence.countAll();
	}

	/**
	 * Updates the cdt session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cdtSession the cdt session
	 * @return the cdt session that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CDTSession updateCDTSession(CDTSession cdtSession) {
		return cdtSessionPersistence.update(cdtSession);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CDTSessionLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		cdtSessionLocalService = (CDTSessionLocalService)aopProxy;

		_setLocalServiceUtilService(cdtSessionLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CDTSessionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CDTSession.class;
	}

	protected String getModelClassName() {
		return CDTSession.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = cdtSessionPersistence.getDataSource();

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
		CDTSessionLocalService cdtSessionLocalService) {

		try {
			Field field = CDTSessionLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, cdtSessionLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected CDTSessionLocalService cdtSessionLocalService;

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

	private static final Log _log = LogFactoryUtil.getLog(
		CDTSessionLocalServiceBaseImpl.class);

}