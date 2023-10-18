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

package com.weprode.nero.news.service.base;

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

import com.weprode.nero.news.model.News;
import com.weprode.nero.news.service.NewsService;
import com.weprode.nero.news.service.NewsServiceUtil;
import com.weprode.nero.news.service.persistence.NewsAttachedFilePersistence;
import com.weprode.nero.news.service.persistence.NewsFinder;
import com.weprode.nero.news.service.persistence.NewsPersistence;
import com.weprode.nero.news.service.persistence.NewsPopulationPersistence;
import com.weprode.nero.news.service.persistence.NewsReadPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the news remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.weprode.nero.news.service.impl.NewsServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.weprode.nero.news.service.impl.NewsServiceImpl
 * @generated
 */
public abstract class NewsServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, NewsService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>NewsService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>NewsServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			NewsService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		newsService = (NewsService)aopProxy;

		_setServiceUtilService(newsService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return NewsService.class.getName();
	}

	protected Class<?> getModelClass() {
		return News.class;
	}

	protected String getModelClassName() {
		return News.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = newsPersistence.getDataSource();

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

	private void _setServiceUtilService(NewsService newsService) {
		try {
			Field field = NewsServiceUtil.class.getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, newsService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected com.weprode.nero.news.service.NewsLocalService newsLocalService;

	protected NewsService newsService;

	@Reference
	protected NewsPersistence newsPersistence;

	@Reference
	protected NewsFinder newsFinder;

	@Reference
	protected NewsAttachedFilePersistence newsAttachedFilePersistence;

	@Reference
	protected NewsPopulationPersistence newsPopulationPersistence;

	@Reference
	protected NewsReadPersistence newsReadPersistence;

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
		NewsServiceBaseImpl.class);

}