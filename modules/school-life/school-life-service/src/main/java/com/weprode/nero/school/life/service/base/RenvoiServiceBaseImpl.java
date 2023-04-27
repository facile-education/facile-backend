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

package com.weprode.nero.school.life.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import com.weprode.nero.school.life.model.Renvoi;
import com.weprode.nero.school.life.service.RenvoiService;
import com.weprode.nero.school.life.service.RenvoiServiceUtil;
import com.weprode.nero.school.life.service.persistence.NotificationPersistence;
import com.weprode.nero.school.life.service.persistence.RenvoiFinder;
import com.weprode.nero.school.life.service.persistence.RenvoiPersistence;
import com.weprode.nero.school.life.service.persistence.SchoollifeSessionPersistence;
import com.weprode.nero.school.life.service.persistence.SchoollifeSlotPersistence;
import com.weprode.nero.school.life.service.persistence.SessionStudentPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the renvoi remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.school.life.service.impl.RenvoiServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.school.life.service.impl.RenvoiServiceImpl
 * @generated
 */
public abstract class RenvoiServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, RenvoiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>RenvoiService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>RenvoiServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			RenvoiService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		renvoiService = (RenvoiService)aopProxy;

		_setServiceUtilService(renvoiService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return RenvoiService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Renvoi.class;
	}

	protected String getModelClassName() {
		return Renvoi.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = renvoiPersistence.getDataSource();

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

	private void _setServiceUtilService(RenvoiService renvoiService) {
		try {
			Field field = RenvoiServiceUtil.class.getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, renvoiService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected NotificationPersistence notificationPersistence;

	@Reference
	protected com.weprode.nero.school.life.service.RenvoiLocalService
		renvoiLocalService;

	protected RenvoiService renvoiService;

	@Reference
	protected RenvoiPersistence renvoiPersistence;

	@Reference
	protected RenvoiFinder renvoiFinder;

	@Reference
	protected SchoollifeSessionPersistence schoollifeSessionPersistence;

	@Reference
	protected SchoollifeSlotPersistence schoollifeSlotPersistence;

	@Reference
	protected SessionStudentPersistence sessionStudentPersistence;

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