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

package com.weprode.nero.help.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import com.weprode.nero.help.model.HelpLink;
import com.weprode.nero.help.service.HelpLinkService;
import com.weprode.nero.help.service.HelpLinkServiceUtil;
import com.weprode.nero.help.service.persistence.HelpCategoryPersistence;
import com.weprode.nero.help.service.persistence.HelpItemPersistence;
import com.weprode.nero.help.service.persistence.HelpItemRolePersistence;
import com.weprode.nero.help.service.persistence.HelpLinkPersistence;
import com.weprode.nero.help.service.persistence.HelpQuestionPersistence;
import com.weprode.nero.help.service.persistence.HelpRelationPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the help link remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.help.service.impl.HelpLinkServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.help.service.impl.HelpLinkServiceImpl
 * @generated
 */
public abstract class HelpLinkServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, HelpLinkService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>HelpLinkService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>HelpLinkServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			HelpLinkService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		helpLinkService = (HelpLinkService)aopProxy;

		_setServiceUtilService(helpLinkService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return HelpLinkService.class.getName();
	}

	protected Class<?> getModelClass() {
		return HelpLink.class;
	}

	protected String getModelClassName() {
		return HelpLink.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = helpLinkPersistence.getDataSource();

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

	private void _setServiceUtilService(HelpLinkService helpLinkService) {
		try {
			Field field = HelpLinkServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, helpLinkService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected HelpCategoryPersistence helpCategoryPersistence;

	@Reference
	protected HelpItemPersistence helpItemPersistence;

	@Reference
	protected HelpItemRolePersistence helpItemRolePersistence;

	@Reference
	protected com.weprode.nero.help.service.HelpLinkLocalService
		helpLinkLocalService;

	protected HelpLinkService helpLinkService;

	@Reference
	protected HelpLinkPersistence helpLinkPersistence;

	@Reference
	protected HelpQuestionPersistence helpQuestionPersistence;

	@Reference
	protected HelpRelationPersistence helpRelationPersistence;

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