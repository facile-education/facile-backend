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

package com.weprode.nero.course.service.base;

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

import com.weprode.nero.course.model.ContentBlock;
import com.weprode.nero.course.service.ContentBlockService;
import com.weprode.nero.course.service.ContentBlockServiceUtil;
import com.weprode.nero.course.service.persistence.ContentBlockPersistence;
import com.weprode.nero.course.service.persistence.HomeworkFinder;
import com.weprode.nero.course.service.persistence.HomeworkPersistence;
import com.weprode.nero.course.service.persistence.SessionContentFinder;
import com.weprode.nero.course.service.persistence.SessionContentPersistence;
import com.weprode.nero.course.service.persistence.StudentHomeworkPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the content block remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.course.service.impl.ContentBlockServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.course.service.impl.ContentBlockServiceImpl
 * @generated
 */
public abstract class ContentBlockServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, ContentBlockService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ContentBlockService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ContentBlockServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			ContentBlockService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		contentBlockService = (ContentBlockService)aopProxy;

		_setServiceUtilService(contentBlockService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ContentBlockService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ContentBlock.class;
	}

	protected String getModelClassName() {
		return ContentBlock.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = contentBlockPersistence.getDataSource();

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
		ContentBlockService contentBlockService) {

		try {
			Field field = ContentBlockServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, contentBlockService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected com.weprode.nero.course.service.ContentBlockLocalService
		contentBlockLocalService;

	protected ContentBlockService contentBlockService;

	@Reference
	protected ContentBlockPersistence contentBlockPersistence;

	@Reference
	protected HomeworkPersistence homeworkPersistence;

	@Reference
	protected HomeworkFinder homeworkFinder;

	@Reference
	protected SessionContentPersistence sessionContentPersistence;

	@Reference
	protected SessionContentFinder sessionContentFinder;

	@Reference
	protected StudentHomeworkPersistence studentHomeworkPersistence;

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
		ContentBlockServiceBaseImpl.class);

}