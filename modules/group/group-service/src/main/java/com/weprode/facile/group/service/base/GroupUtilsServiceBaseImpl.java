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

package com.weprode.facile.group.service.base;

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
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import com.weprode.facile.group.service.GroupUtilsService;
import com.weprode.facile.group.service.GroupUtilsServiceUtil;
import com.weprode.facile.group.service.persistence.CommunityInfosFinder;
import com.weprode.facile.group.service.persistence.CommunityInfosPersistence;
import com.weprode.facile.group.service.persistence.GroupMembershipPersistence;
import com.weprode.facile.group.service.persistence.GroupUtilsFinder;
import com.weprode.facile.group.service.persistence.MembershipActivityPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the group utils remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.facile.group.service.impl.GroupUtilsServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.facile.group.service.impl.GroupUtilsServiceImpl
 * @generated
 */
public abstract class GroupUtilsServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, GroupUtilsService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>GroupUtilsService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>GroupUtilsServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			GroupUtilsService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		groupUtilsService = (GroupUtilsService)aopProxy;

		_setServiceUtilService(groupUtilsService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return GroupUtilsService.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

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

	private void _setServiceUtilService(GroupUtilsService groupUtilsService) {
		try {
			Field field = GroupUtilsServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, groupUtilsService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected CommunityInfosPersistence communityInfosPersistence;

	@Reference
	protected CommunityInfosFinder communityInfosFinder;

	@Reference
	protected GroupMembershipPersistence groupMembershipPersistence;

	@Reference
	protected com.weprode.facile.group.service.GroupUtilsLocalService
		groupUtilsLocalService;

	protected GroupUtilsService groupUtilsService;

	@Reference
	protected GroupUtilsFinder groupUtilsFinder;

	@Reference
	protected MembershipActivityPersistence membershipActivityPersistence;

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
		GroupUtilsServiceBaseImpl.class);

}