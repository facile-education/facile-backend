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

import com.weprode.nero.schedule.model.ScheduleConfiguration;
import com.weprode.nero.schedule.service.ScheduleConfigurationService;
import com.weprode.nero.schedule.service.ScheduleConfigurationServiceUtil;
import com.weprode.nero.schedule.service.persistence.CDTSessionFinder;
import com.weprode.nero.schedule.service.persistence.CDTSessionPersistence;
import com.weprode.nero.schedule.service.persistence.DailySchedulePersistence;
import com.weprode.nero.schedule.service.persistence.GroupColorPersistence;
import com.weprode.nero.schedule.service.persistence.HomeworkFinder;
import com.weprode.nero.schedule.service.persistence.HomeworkPersistence;
import com.weprode.nero.schedule.service.persistence.ScheduleConfigurationPersistence;
import com.weprode.nero.schedule.service.persistence.SessionStudentPersistence;
import com.weprode.nero.schedule.service.persistence.SessionTeacherPersistence;
import com.weprode.nero.schedule.service.persistence.StudentHomeworkPersistence;
import com.weprode.nero.schedule.service.persistence.SubjectGroupColorPersistence;
import com.weprode.nero.schedule.service.persistence.WeeklySchedulePersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the schedule configuration remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.schedule.service.impl.ScheduleConfigurationServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.schedule.service.impl.ScheduleConfigurationServiceImpl
 * @generated
 */
public abstract class ScheduleConfigurationServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService,
			   ScheduleConfigurationService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ScheduleConfigurationService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ScheduleConfigurationServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			ScheduleConfigurationService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		scheduleConfigurationService = (ScheduleConfigurationService)aopProxy;

		_setServiceUtilService(scheduleConfigurationService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ScheduleConfigurationService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ScheduleConfiguration.class;
	}

	protected String getModelClassName() {
		return ScheduleConfiguration.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				scheduleConfigurationPersistence.getDataSource();

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
		ScheduleConfigurationService scheduleConfigurationService) {

		try {
			Field field =
				ScheduleConfigurationServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, scheduleConfigurationService);
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
	protected GroupColorPersistence groupColorPersistence;

	@Reference
	protected HomeworkPersistence homeworkPersistence;

	@Reference
	protected HomeworkFinder homeworkFinder;

	@Reference
	protected
		com.weprode.nero.schedule.service.ScheduleConfigurationLocalService
			scheduleConfigurationLocalService;

	protected ScheduleConfigurationService scheduleConfigurationService;

	@Reference
	protected ScheduleConfigurationPersistence scheduleConfigurationPersistence;

	@Reference
	protected SessionStudentPersistence sessionStudentPersistence;

	@Reference
	protected SessionTeacherPersistence sessionTeacherPersistence;

	@Reference
	protected StudentHomeworkPersistence studentHomeworkPersistence;

	@Reference
	protected SubjectGroupColorPersistence subjectGroupColorPersistence;

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