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

package com.weprode.facile.school.life.service.base;

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

import com.weprode.facile.school.life.model.SchoollifeSlot;
import com.weprode.facile.school.life.service.SchoollifeSlotService;
import com.weprode.facile.school.life.service.SchoollifeSlotServiceUtil;
import com.weprode.facile.school.life.service.persistence.RenvoiFinder;
import com.weprode.facile.school.life.service.persistence.RenvoiPersistence;
import com.weprode.facile.school.life.service.persistence.SchoollifeSessionPersistence;
import com.weprode.facile.school.life.service.persistence.SchoollifeSlotPersistence;
import com.weprode.facile.school.life.service.persistence.SessionStudentPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the schoollife slot remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.school.life.service.impl.SchoollifeSlotServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.school.life.service.impl.SchoollifeSlotServiceImpl
 * @generated
 */
public abstract class SchoollifeSlotServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, SchoollifeSlotService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SchoollifeSlotService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SchoollifeSlotServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SchoollifeSlotService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		schoollifeSlotService = (SchoollifeSlotService)aopProxy;

		_setServiceUtilService(schoollifeSlotService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SchoollifeSlotService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SchoollifeSlot.class;
	}

	protected String getModelClassName() {
		return SchoollifeSlot.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = schoollifeSlotPersistence.getDataSource();

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
		SchoollifeSlotService schoollifeSlotService) {

		try {
			Field field = SchoollifeSlotServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, schoollifeSlotService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected RenvoiPersistence renvoiPersistence;

	@Reference
	protected RenvoiFinder renvoiFinder;

	@Reference
	protected SchoollifeSessionPersistence schoollifeSessionPersistence;

	@Reference
	protected com.weprode.facile.school.life.service.SchoollifeSlotLocalService
		schoollifeSlotLocalService;

	protected SchoollifeSlotService schoollifeSlotService;

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

	private static final Log _log = LogFactoryUtil.getLog(
		SchoollifeSlotServiceBaseImpl.class);

}