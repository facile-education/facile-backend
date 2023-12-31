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

package com.weprode.facile.application.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import com.weprode.facile.application.model.Application;
import com.weprode.facile.application.service.ApplicationService;
import com.weprode.facile.application.service.ApplicationServiceUtil;
import com.weprode.facile.application.service.persistence.ApplicationPersistence;
import com.weprode.facile.application.service.persistence.AuthorizedSchoolPersistence;
import com.weprode.facile.application.service.persistence.BroadcastPersistence;
import com.weprode.facile.application.service.persistence.BroadcastRulePersistence;
import com.weprode.facile.application.service.persistence.DefaultRolePersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the application remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.application.service.impl.ApplicationServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.application.service.impl.ApplicationServiceImpl
 * @generated
 */
public abstract class ApplicationServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, ApplicationService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ApplicationService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ApplicationServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			ApplicationService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		applicationService = (ApplicationService)aopProxy;

		_setServiceUtilService(applicationService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ApplicationService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Application.class;
	}

	protected String getModelClassName() {
		return Application.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = applicationPersistence.getDataSource();

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

	private void _setServiceUtilService(ApplicationService applicationService) {
		try {
			Field field = ApplicationServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, applicationService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected com.weprode.facile.application.service.ApplicationLocalService
		applicationLocalService;

	protected ApplicationService applicationService;

	@Reference
	protected ApplicationPersistence applicationPersistence;

	@Reference
	protected AuthorizedSchoolPersistence authorizedSchoolPersistence;

	@Reference
	protected BroadcastPersistence broadcastPersistence;

	@Reference
	protected BroadcastRulePersistence broadcastRulePersistence;

	@Reference
	protected DefaultRolePersistence defaultRolePersistence;

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

	private static final Log _log = LogFactoryUtil.getLog(
		ApplicationServiceBaseImpl.class);

}