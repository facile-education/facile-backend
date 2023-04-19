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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import com.weprode.nero.schedule.model.SessionTeacher;
import com.weprode.nero.schedule.service.SessionTeacherService;
import com.weprode.nero.schedule.service.SessionTeacherServiceUtil;
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

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the session teacher remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.schedule.service.impl.SessionTeacherServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.schedule.service.impl.SessionTeacherServiceImpl
 * @generated
 */
public abstract class SessionTeacherServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, SessionTeacherService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SessionTeacherService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SessionTeacherServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SessionTeacherService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		sessionTeacherService = (SessionTeacherService)aopProxy;

		_setServiceUtilService(sessionTeacherService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SessionTeacherService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SessionTeacher.class;
	}

	protected String getModelClassName() {
		return SessionTeacher.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = sessionTeacherPersistence.getDataSource();

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

	private void _setServiceUtilService(
		SessionTeacherService sessionTeacherService) {

		try {
			Field field = SessionTeacherServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, sessionTeacherService);
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
	protected com.weprode.nero.schedule.service.SessionTeacherLocalService
		sessionTeacherLocalService;

	protected SessionTeacherService sessionTeacherService;

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
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserService userService;

}