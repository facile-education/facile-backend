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

package com.weprode.facile.mobile.service.base;

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

import com.weprode.facile.mobile.model.MobileDevice;
import com.weprode.facile.mobile.service.MobileDeviceService;
import com.weprode.facile.mobile.service.MobileDeviceServiceUtil;
import com.weprode.facile.mobile.service.persistence.MobileDevicePersistence;
import com.weprode.facile.mobile.service.persistence.UserMobileTokenPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the mobile device remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.mobile.service.impl.MobileDeviceServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.mobile.service.impl.MobileDeviceServiceImpl
 * @generated
 */
public abstract class MobileDeviceServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, MobileDeviceService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MobileDeviceService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>MobileDeviceServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			MobileDeviceService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		mobileDeviceService = (MobileDeviceService)aopProxy;

		_setServiceUtilService(mobileDeviceService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MobileDeviceService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MobileDevice.class;
	}

	protected String getModelClassName() {
		return MobileDevice.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = mobileDevicePersistence.getDataSource();

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
		MobileDeviceService mobileDeviceService) {

		try {
			Field field = MobileDeviceServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, mobileDeviceService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected com.weprode.facile.mobile.service.MobileDeviceLocalService
		mobileDeviceLocalService;

	protected MobileDeviceService mobileDeviceService;

	@Reference
	protected MobileDevicePersistence mobileDevicePersistence;

	@Reference
	protected UserMobileTokenPersistence userMobileTokenPersistence;

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
		MobileDeviceServiceBaseImpl.class);

}