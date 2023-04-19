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
 * Provides a wrapper for {@link DailyScheduleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DailyScheduleLocalService
 * @generated
 */
public class DailyScheduleLocalServiceWrapper
	implements DailyScheduleLocalService,
			   ServiceWrapper<DailyScheduleLocalService> {

	public DailyScheduleLocalServiceWrapper(
		DailyScheduleLocalService dailyScheduleLocalService) {

		_dailyScheduleLocalService = dailyScheduleLocalService;
	}

	/**
	 * Adds the daily schedule to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DailyScheduleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dailySchedule the daily schedule
	 * @return the daily schedule that was added
	 */
	@Override
	public com.weprode.nero.schedule.model.DailySchedule addDailySchedule(
		com.weprode.nero.schedule.model.DailySchedule dailySchedule) {

		return _dailyScheduleLocalService.addDailySchedule(dailySchedule);
	}

	/**
	 * Add school daily configuration for 1 given session Id
	 */
	@Override
	public void addSchoolDailySchedule(
		long schoolId, int sessionId, String sessionStartHour,
		String sessionEndHour) {

		_dailyScheduleLocalService.addSchoolDailySchedule(
			schoolId, sessionId, sessionStartHour, sessionEndHour);
	}

	/**
	 * Creates a new daily schedule with the primary key. Does not add the daily schedule to the database.
	 *
	 * @param dailySchedulePK the primary key for the new daily schedule
	 * @return the new daily schedule
	 */
	@Override
	public com.weprode.nero.schedule.model.DailySchedule createDailySchedule(
		com.weprode.nero.schedule.service.persistence.DailySchedulePK
			dailySchedulePK) {

		return _dailyScheduleLocalService.createDailySchedule(dailySchedulePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dailyScheduleLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the daily schedule from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DailyScheduleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dailySchedule the daily schedule
	 * @return the daily schedule that was removed
	 */
	@Override
	public com.weprode.nero.schedule.model.DailySchedule deleteDailySchedule(
		com.weprode.nero.schedule.model.DailySchedule dailySchedule) {

		return _dailyScheduleLocalService.deleteDailySchedule(dailySchedule);
	}

	/**
	 * Deletes the daily schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DailyScheduleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule that was removed
	 * @throws PortalException if a daily schedule with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.DailySchedule deleteDailySchedule(
			com.weprode.nero.schedule.service.persistence.DailySchedulePK
				dailySchedulePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dailyScheduleLocalService.deleteDailySchedule(dailySchedulePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dailyScheduleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Delete daily school configuration
	 */
	@Override
	public void deleteSchoolDailySchedule(long schoolId) {
		_dailyScheduleLocalService.deleteSchoolDailySchedule(schoolId);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _dailyScheduleLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _dailyScheduleLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dailyScheduleLocalService.dynamicQuery();
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

		return _dailyScheduleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.DailyScheduleModelImpl</code>.
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

		return _dailyScheduleLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.DailyScheduleModelImpl</code>.
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

		return _dailyScheduleLocalService.dynamicQuery(
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

		return _dailyScheduleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _dailyScheduleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.DailySchedule fetchDailySchedule(
		com.weprode.nero.schedule.service.persistence.DailySchedulePK
			dailySchedulePK) {

		return _dailyScheduleLocalService.fetchDailySchedule(dailySchedulePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _dailyScheduleLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the daily schedule with the primary key.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule
	 * @throws PortalException if a daily schedule with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.DailySchedule getDailySchedule(
			com.weprode.nero.schedule.service.persistence.DailySchedulePK
				dailySchedulePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dailyScheduleLocalService.getDailySchedule(dailySchedulePK);
	}

	/**
	 * Returns a range of all the daily schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @return the range of daily schedules
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.DailySchedule>
		getDailySchedules(int start, int end) {

		return _dailyScheduleLocalService.getDailySchedules(start, end);
	}

	/**
	 * Returns the number of daily schedules.
	 *
	 * @return the number of daily schedules
	 */
	@Override
	public int getDailySchedulesCount() {
		return _dailyScheduleLocalService.getDailySchedulesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _dailyScheduleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _dailyScheduleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dailyScheduleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the daily schedule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DailyScheduleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dailySchedule the daily schedule
	 * @return the daily schedule that was updated
	 */
	@Override
	public com.weprode.nero.schedule.model.DailySchedule updateDailySchedule(
		com.weprode.nero.schedule.model.DailySchedule dailySchedule) {

		return _dailyScheduleLocalService.updateDailySchedule(dailySchedule);
	}

	@Override
	public DailyScheduleLocalService getWrappedService() {
		return _dailyScheduleLocalService;
	}

	@Override
	public void setWrappedService(
		DailyScheduleLocalService dailyScheduleLocalService) {

		_dailyScheduleLocalService = dailyScheduleLocalService;
	}

	private DailyScheduleLocalService _dailyScheduleLocalService;

}