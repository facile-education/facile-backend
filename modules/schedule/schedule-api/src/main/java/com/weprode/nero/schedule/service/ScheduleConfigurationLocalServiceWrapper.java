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

package com.weprode.nero.schedule.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScheduleConfigurationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfigurationLocalService
 * @generated
 */
public class ScheduleConfigurationLocalServiceWrapper
	implements ScheduleConfigurationLocalService,
			   ServiceWrapper<ScheduleConfigurationLocalService> {

	public ScheduleConfigurationLocalServiceWrapper(
		ScheduleConfigurationLocalService scheduleConfigurationLocalService) {

		_scheduleConfigurationLocalService = scheduleConfigurationLocalService;
	}

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
	@Override
	public com.weprode.nero.schedule.model.ScheduleConfiguration
		addScheduleConfiguration(
			com.weprode.nero.schedule.model.ScheduleConfiguration
				scheduleConfiguration) {

		return _scheduleConfigurationLocalService.addScheduleConfiguration(
			scheduleConfiguration);
	}

	@Override
	public org.json.JSONObject convertAsJson() {
		return _scheduleConfigurationLocalService.convertAsJson();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _scheduleConfigurationLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new schedule configuration with the primary key. Does not add the schedule configuration to the database.
	 *
	 * @param configId the primary key for the new schedule configuration
	 * @return the new schedule configuration
	 */
	@Override
	public com.weprode.nero.schedule.model.ScheduleConfiguration
		createScheduleConfiguration(long configId) {

		return _scheduleConfigurationLocalService.createScheduleConfiguration(
			configId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _scheduleConfigurationLocalService.deletePersistedModel(
			persistedModel);
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
	@Override
	public com.weprode.nero.schedule.model.ScheduleConfiguration
			deleteScheduleConfiguration(long configId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _scheduleConfigurationLocalService.deleteScheduleConfiguration(
			configId);
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
	@Override
	public com.weprode.nero.schedule.model.ScheduleConfiguration
		deleteScheduleConfiguration(
			com.weprode.nero.schedule.model.ScheduleConfiguration
				scheduleConfiguration) {

		return _scheduleConfigurationLocalService.deleteScheduleConfiguration(
			scheduleConfiguration);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _scheduleConfigurationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _scheduleConfigurationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scheduleConfigurationLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _scheduleConfigurationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _scheduleConfigurationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _scheduleConfigurationLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _scheduleConfigurationLocalService.dynamicQueryCount(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _scheduleConfigurationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.ScheduleConfiguration
		fetchScheduleConfiguration(long configId) {

		return _scheduleConfigurationLocalService.fetchScheduleConfiguration(
			configId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _scheduleConfigurationLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<Integer> getH1Weeks() {
		return _scheduleConfigurationLocalService.getH1Weeks();
	}

	@Override
	public java.util.List<Integer> getH2Weeks() {
		return _scheduleConfigurationLocalService.getH2Weeks();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _scheduleConfigurationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _scheduleConfigurationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _scheduleConfigurationLocalService.getPersistedModel(
			primaryKeyObj);
	}

	@Override
	public java.util.Date getProjectStartDate() {
		return _scheduleConfigurationLocalService.getProjectStartDate();
	}

	/**
	 * Returns the schedule configuration with the primary key.
	 *
	 * @param configId the primary key of the schedule configuration
	 * @return the schedule configuration
	 * @throws PortalException if a schedule configuration with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.ScheduleConfiguration
			getScheduleConfiguration(long configId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _scheduleConfigurationLocalService.getScheduleConfiguration(
			configId);
	}

	/**
	 * Returns a range of all the schedule configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule configurations
	 * @param end the upper bound of the range of schedule configurations (not inclusive)
	 * @return the range of schedule configurations
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.ScheduleConfiguration>
		getScheduleConfigurations(int start, int end) {

		return _scheduleConfigurationLocalService.getScheduleConfigurations(
			start, end);
	}

	/**
	 * Returns the number of schedule configurations.
	 *
	 * @return the number of schedule configurations
	 */
	@Override
	public int getScheduleConfigurationsCount() {
		return _scheduleConfigurationLocalService.
			getScheduleConfigurationsCount();
	}

	@Override
	public java.util.Date getSchoolYearEndDate() {
		return _scheduleConfigurationLocalService.getSchoolYearEndDate();
	}

	@Override
	public java.util.Date getSchoolYearSemesterDate() {
		return _scheduleConfigurationLocalService.getSchoolYearSemesterDate();
	}

	@Override
	public java.util.Date getSchoolYearStartDate() {
		return _scheduleConfigurationLocalService.getSchoolYearStartDate();
	}

	@Override
	public com.weprode.nero.schedule.model.ScheduleConfiguration
		setScheduleConfiguration(
			java.util.Date schoolYearStartDate, java.util.Date semesterDate,
			java.util.Date schoolYearEndDate, String h1Weeks, String h2Weeks) {

		return _scheduleConfigurationLocalService.setScheduleConfiguration(
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
	@Override
	public com.weprode.nero.schedule.model.ScheduleConfiguration
		updateScheduleConfiguration(
			com.weprode.nero.schedule.model.ScheduleConfiguration
				scheduleConfiguration) {

		return _scheduleConfigurationLocalService.updateScheduleConfiguration(
			scheduleConfiguration);
	}

	@Override
	public ScheduleConfigurationLocalService getWrappedService() {
		return _scheduleConfigurationLocalService;
	}

	@Override
	public void setWrappedService(
		ScheduleConfigurationLocalService scheduleConfigurationLocalService) {

		_scheduleConfigurationLocalService = scheduleConfigurationLocalService;
	}

	private ScheduleConfigurationLocalService
		_scheduleConfigurationLocalService;

}