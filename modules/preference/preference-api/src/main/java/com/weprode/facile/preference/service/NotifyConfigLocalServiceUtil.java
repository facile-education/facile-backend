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

package com.weprode.facile.preference.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.preference.model.NotifyConfig;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for NotifyConfig. This utility wraps
 * <code>com.weprode.facile.preference.service.impl.NotifyConfigLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see NotifyConfigLocalService
 * @generated
 */
public class NotifyConfigLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.preference.service.impl.NotifyConfigLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static NotifyConfig addNotifyConfig() throws SystemException {
		return getService().addNotifyConfig();
	}

	/**
	 * Adds the notify config to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfig the notify config
	 * @return the notify config that was added
	 */
	public static NotifyConfig addNotifyConfig(NotifyConfig notifyConfig) {
		return getService().addNotifyConfig(notifyConfig);
	}

	/**
	 * Creates a new notify config with the primary key. Does not add the notify config to the database.
	 *
	 * @param notifyConfigId the primary key for the new notify config
	 * @return the new notify config
	 */
	public static NotifyConfig createNotifyConfig(long notifyConfigId) {
		return getService().createNotifyConfig(notifyConfigId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the notify config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config that was removed
	 * @throws PortalException if a notify config with the primary key could not be found
	 */
	public static NotifyConfig deleteNotifyConfig(long notifyConfigId)
		throws PortalException {

		return getService().deleteNotifyConfig(notifyConfigId);
	}

	/**
	 * Deletes the notify config from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfig the notify config
	 * @return the notify config that was removed
	 */
	public static NotifyConfig deleteNotifyConfig(NotifyConfig notifyConfig) {
		return getService().deleteNotifyConfig(notifyConfig);
	}

	public static void deleteNotifyConfigByUser(long userId)
		throws com.weprode.facile.preference.exception.
			NoSuchNotifyConfigException,
			   SystemException {

		getService().deleteNotifyConfigByUser(userId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.preference.model.impl.NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.preference.model.impl.NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static NotifyConfig fetchNotifyConfig(long notifyConfigId) {
		return getService().fetchNotifyConfig(notifyConfigId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the notify config with the primary key.
	 *
	 * @param notifyConfigId the primary key of the notify config
	 * @return the notify config
	 * @throws PortalException if a notify config with the primary key could not be found
	 */
	public static NotifyConfig getNotifyConfig(long notifyConfigId)
		throws PortalException {

		return getService().getNotifyConfig(notifyConfigId);
	}

	public static List<NotifyConfig> getNotifyConfigActivate(boolean activate)
		throws SystemException {

		return getService().getNotifyConfigActivate(activate);
	}

	public static List<NotifyConfig> getNotifyConfigActivateDigestPeriod(
			boolean activate, int digestPeriod)
		throws SystemException {

		return getService().getNotifyConfigActivateDigestPeriod(
			activate, digestPeriod);
	}

	/**
	 * Returns a range of all the notify configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.preference.model.impl.NotifyConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notify configs
	 * @param end the upper bound of the range of notify configs (not inclusive)
	 * @return the range of notify configs
	 */
	public static List<NotifyConfig> getNotifyConfigs(int start, int end) {
		return getService().getNotifyConfigs(start, end);
	}

	/**
	 * Returns the number of notify configs.
	 *
	 * @return the number of notify configs
	 */
	public static int getNotifyConfigsCount() {
		return getService().getNotifyConfigsCount();
	}

	public static NotifyConfig getOrCreateNotifyConfig(long userId)
		throws SystemException {

		return getService().getOrCreateNotifyConfig(userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the notify config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotifyConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notifyConfig the notify config
	 * @return the notify config that was updated
	 */
	public static NotifyConfig updateNotifyConfig(NotifyConfig notifyConfig) {
		return getService().updateNotifyConfig(notifyConfig);
	}

	public static NotifyConfigLocalService getService() {
		return _service;
	}

	private static volatile NotifyConfigLocalService _service;

}