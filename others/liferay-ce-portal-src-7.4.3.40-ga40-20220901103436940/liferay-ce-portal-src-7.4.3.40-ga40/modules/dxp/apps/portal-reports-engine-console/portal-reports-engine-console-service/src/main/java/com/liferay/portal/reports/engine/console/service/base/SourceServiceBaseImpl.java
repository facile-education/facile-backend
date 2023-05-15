/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.reports.engine.console.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.service.SourceService;
import com.liferay.portal.reports.engine.console.service.SourceServiceUtil;
import com.liferay.portal.reports.engine.console.service.persistence.DefinitionFinder;
import com.liferay.portal.reports.engine.console.service.persistence.DefinitionPersistence;
import com.liferay.portal.reports.engine.console.service.persistence.EntryFinder;
import com.liferay.portal.reports.engine.console.service.persistence.EntryPersistence;
import com.liferay.portal.reports.engine.console.service.persistence.SourceFinder;
import com.liferay.portal.reports.engine.console.service.persistence.SourcePersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the source remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.reports.engine.console.service.impl.SourceServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.reports.engine.console.service.impl.SourceServiceImpl
 * @generated
 */
public abstract class SourceServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, SourceService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SourceService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SourceServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SourceService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		sourceService = (SourceService)aopProxy;

		_setServiceUtilService(sourceService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SourceService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Source.class;
	}

	protected String getModelClassName() {
		return Source.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = sourcePersistence.getDataSource();

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

	private void _setServiceUtilService(SourceService sourceService) {
		try {
			Field field = SourceServiceUtil.class.getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, sourceService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected DefinitionPersistence definitionPersistence;

	@Reference
	protected DefinitionFinder definitionFinder;

	@Reference
	protected EntryPersistence entryPersistence;

	@Reference
	protected EntryFinder entryFinder;

	@Reference
	protected
		com.liferay.portal.reports.engine.console.service.SourceLocalService
			sourceLocalService;

	protected SourceService sourceService;

	@Reference
	protected SourcePersistence sourcePersistence;

	@Reference
	protected SourceFinder sourceFinder;

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