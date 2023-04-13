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

package com.weprode.nero.application.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import com.weprode.nero.application.model.Broadcast;
import com.weprode.nero.application.service.BroadcastService;
import com.weprode.nero.application.service.BroadcastServiceUtil;
import com.weprode.nero.application.service.persistence.ApplicationPersistence;
import com.weprode.nero.application.service.persistence.AuthorizedSchoolPersistence;
import com.weprode.nero.application.service.persistence.BroadcastPersistence;
import com.weprode.nero.application.service.persistence.BroadcastRulePersistence;
import com.weprode.nero.application.service.persistence.DefaultRolePersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the broadcast remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.application.service.impl.BroadcastServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.application.service.impl.BroadcastServiceImpl
 * @generated
 */
public abstract class BroadcastServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, BroadcastService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>BroadcastService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>BroadcastServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			BroadcastService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		broadcastService = (BroadcastService)aopProxy;

		_setServiceUtilService(broadcastService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return BroadcastService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Broadcast.class;
	}

	protected String getModelClassName() {
		return Broadcast.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = broadcastPersistence.getDataSource();

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

	private void _setServiceUtilService(BroadcastService broadcastService) {
		try {
			Field field = BroadcastServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, broadcastService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected ApplicationPersistence applicationPersistence;

	@Reference
	protected AuthorizedSchoolPersistence authorizedSchoolPersistence;

	@Reference
	protected com.weprode.nero.application.service.BroadcastLocalService
		broadcastLocalService;

	protected BroadcastService broadcastService;

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

}