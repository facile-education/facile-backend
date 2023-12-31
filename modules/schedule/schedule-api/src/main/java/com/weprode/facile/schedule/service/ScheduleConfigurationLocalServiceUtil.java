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

package com.weprode.facile.schedule.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.schedule.model.ScheduleConfiguration;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ScheduleConfiguration. This utility wraps
 * <code>com.weprode.facile.schedule.service.impl.ScheduleConfigurationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfigurationLocalService
 * @generated
 */
public class ScheduleConfigurationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.schedule.service.impl.ScheduleConfigurationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the schedule configuration to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ScheduleConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param scheduleConfiguration the schedule configuration
	 * @return the schedule configuration that was added
	 */
	public static ScheduleConfiguration addScheduleConfiguration(
		ScheduleConfiguration scheduleConfiguration) {

		return getService().addScheduleConfiguration(scheduleConfiguration);
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
	 * Creates a new schedule configuration with the primary key. Does not add the schedule configuration to the database.
	 *
	 * @param configId the primary key for the new schedule configuration
	 * @return the new schedule configuration
	 */
	public static ScheduleConfiguration createScheduleConfiguration(
		long configId) {

		return getService().createScheduleConfiguration(configId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the schedule configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ScheduleConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param configId the primary key of the schedule configuration
	 * @return the schedule configuration that was removed
	 * @throws PortalException if a schedule configuration with the primary key could not be found
	 */
	public static ScheduleConfiguration deleteScheduleConfiguration(
			long configId)
		throws PortalException {

		return getService().deleteScheduleConfiguration(configId);
	}

	/**
	 * Deletes the schedule configuration from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ScheduleConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param scheduleConfiguration the schedule configuration
	 * @return the schedule configuration that was removed
	 */
	public static ScheduleConfiguration deleteScheduleConfiguration(
		ScheduleConfiguration scheduleConfiguration) {

		return getService().deleteScheduleConfiguration(scheduleConfiguration);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.ScheduleConfigurationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.ScheduleConfigurationModelImpl</code>.
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

	public static ScheduleConfiguration fetchScheduleConfiguration(
		long configId) {

		return getService().fetchScheduleConfiguration(configId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static org.json.JSONObject getCalendarConfiguration() {
		return getService().getCalendarConfiguration();
	}

	public static List<Integer> getH1Weeks() {
		return getService().getH1Weeks();
	}

	public static List<Integer> getH2Weeks() {
		return getService().getH2Weeks();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
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

	public static java.util.Date getProjectStartDate() {
		return getService().getProjectStartDate();
	}

	public static org.json.JSONObject getScheduleConfiguration() {
		return getService().getScheduleConfiguration();
	}

	/**
	 * Returns the schedule configuration with the primary key.
	 *
	 * @param configId the primary key of the schedule configuration
	 * @return the schedule configuration
	 * @throws PortalException if a schedule configuration with the primary key could not be found
	 */
	public static ScheduleConfiguration getScheduleConfiguration(long configId)
		throws PortalException {

		return getService().getScheduleConfiguration(configId);
	}

	/**
	 * Returns a range of all the schedule configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule configurations
	 * @param end the upper bound of the range of schedule configurations (not inclusive)
	 * @return the range of schedule configurations
	 */
	public static List<ScheduleConfiguration> getScheduleConfigurations(
		int start, int end) {

		return getService().getScheduleConfigurations(start, end);
	}

	/**
	 * Returns the number of schedule configurations.
	 *
	 * @return the number of schedule configurations
	 */
	public static int getScheduleConfigurationsCount() {
		return getService().getScheduleConfigurationsCount();
	}

	public static java.util.Date getSchoolYearEndDate() {
		return getService().getSchoolYearEndDate();
	}

	public static java.util.Date getSchoolYearSemesterDate() {
		return getService().getSchoolYearSemesterDate();
	}

	public static java.util.Date getSchoolYearStartDate() {
		return getService().getSchoolYearStartDate();
	}

	public static ScheduleConfiguration setScheduleConfiguration(
		java.util.Date schoolYearStartDate, java.util.Date semesterDate,
		java.util.Date schoolYearEndDate, String h1Weeks, String h2Weeks) {

		return getService().setScheduleConfiguration(
			schoolYearStartDate, semesterDate, schoolYearEndDate, h1Weeks,
			h2Weeks);
	}

	/**
	 * Updates the schedule configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ScheduleConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param scheduleConfiguration the schedule configuration
	 * @return the schedule configuration that was updated
	 */
	public static ScheduleConfiguration updateScheduleConfiguration(
		ScheduleConfiguration scheduleConfiguration) {

		return getService().updateScheduleConfiguration(scheduleConfiguration);
	}

	public static ScheduleConfigurationLocalService getService() {
		return _service;
	}

	private static volatile ScheduleConfigurationLocalService _service;

}