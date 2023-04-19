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
 * Provides a wrapper for {@link WeeklyScheduleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see WeeklyScheduleLocalService
 * @generated
 */
public class WeeklyScheduleLocalServiceWrapper
	implements ServiceWrapper<WeeklyScheduleLocalService>,
			   WeeklyScheduleLocalService {

	public WeeklyScheduleLocalServiceWrapper(
		WeeklyScheduleLocalService weeklyScheduleLocalService) {

		_weeklyScheduleLocalService = weeklyScheduleLocalService;
	}

	/**
	 * Adds the weekly schedule to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeeklyScheduleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weeklySchedule the weekly schedule
	 * @return the weekly schedule that was added
	 */
	@Override
	public com.weprode.nero.schedule.model.WeeklySchedule addWeeklySchedule(
		com.weprode.nero.schedule.model.WeeklySchedule weeklySchedule) {

		return _weeklyScheduleLocalService.addWeeklySchedule(weeklySchedule);
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.WeeklySchedule>
		createDefaultWeeklySchedule(long schoolId) {

		return _weeklyScheduleLocalService.createDefaultWeeklySchedule(
			schoolId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _weeklyScheduleLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new weekly schedule with the primary key. Does not add the weekly schedule to the database.
	 *
	 * @param weeklySchedulePK the primary key for the new weekly schedule
	 * @return the new weekly schedule
	 */
	@Override
	public com.weprode.nero.schedule.model.WeeklySchedule createWeeklySchedule(
		com.weprode.nero.schedule.service.persistence.WeeklySchedulePK
			weeklySchedulePK) {

		return _weeklyScheduleLocalService.createWeeklySchedule(
			weeklySchedulePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _weeklyScheduleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the weekly schedule from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeeklyScheduleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weeklySchedule the weekly schedule
	 * @return the weekly schedule that was removed
	 */
	@Override
	public com.weprode.nero.schedule.model.WeeklySchedule deleteWeeklySchedule(
		com.weprode.nero.schedule.model.WeeklySchedule weeklySchedule) {

		return _weeklyScheduleLocalService.deleteWeeklySchedule(weeklySchedule);
	}

	/**
	 * Deletes the weekly schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeeklyScheduleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weeklySchedulePK the primary key of the weekly schedule
	 * @return the weekly schedule that was removed
	 * @throws PortalException if a weekly schedule with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.WeeklySchedule deleteWeeklySchedule(
			com.weprode.nero.schedule.service.persistence.WeeklySchedulePK
				weeklySchedulePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _weeklyScheduleLocalService.deleteWeeklySchedule(
			weeklySchedulePK);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _weeklyScheduleLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _weeklyScheduleLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _weeklyScheduleLocalService.dynamicQuery();
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

		return _weeklyScheduleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.WeeklyScheduleModelImpl</code>.
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

		return _weeklyScheduleLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.WeeklyScheduleModelImpl</code>.
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

		return _weeklyScheduleLocalService.dynamicQuery(
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

		return _weeklyScheduleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _weeklyScheduleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.WeeklySchedule fetchWeeklySchedule(
		com.weprode.nero.schedule.service.persistence.WeeklySchedulePK
			weeklySchedulePK) {

		return _weeklyScheduleLocalService.fetchWeeklySchedule(
			weeklySchedulePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _weeklyScheduleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _weeklyScheduleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _weeklyScheduleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _weeklyScheduleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the weekly schedule with the primary key.
	 *
	 * @param weeklySchedulePK the primary key of the weekly schedule
	 * @return the weekly schedule
	 * @throws PortalException if a weekly schedule with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.WeeklySchedule getWeeklySchedule(
			com.weprode.nero.schedule.service.persistence.WeeklySchedulePK
				weeklySchedulePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _weeklyScheduleLocalService.getWeeklySchedule(weeklySchedulePK);
	}

	@Override
	public java.util.List<com.weprode.nero.schedule.model.WeeklySchedule>
		getWeeklyScheduleBySchoolId(Long schoolId) {

		return _weeklyScheduleLocalService.getWeeklyScheduleBySchoolId(
			schoolId);
	}

	/**
	 * Returns a range of all the weekly schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.WeeklyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of weekly schedules
	 * @param end the upper bound of the range of weekly schedules (not inclusive)
	 * @return the range of weekly schedules
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.WeeklySchedule>
		getWeeklySchedules(int start, int end) {

		return _weeklyScheduleLocalService.getWeeklySchedules(start, end);
	}

	/**
	 * Returns the number of weekly schedules.
	 *
	 * @return the number of weekly schedules
	 */
	@Override
	public int getWeeklySchedulesCount() {
		return _weeklyScheduleLocalService.getWeeklySchedulesCount();
	}

	/**
	 * Delete all weekly schedule for etab thne create new from list of DayiD in parameter
	 * Sunday = 0 / Monday = 1 ...... / Saturday = 6
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.WeeklySchedule>
			replaceWeeklyScheduleBySchoolId(
				com.liferay.portal.kernel.json.JSONArray dayIdList,
				Long schoolId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _weeklyScheduleLocalService.replaceWeeklyScheduleBySchoolId(
			dayIdList, schoolId);
	}

	/**
	 * Updates the weekly schedule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeeklyScheduleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weeklySchedule the weekly schedule
	 * @return the weekly schedule that was updated
	 */
	@Override
	public com.weprode.nero.schedule.model.WeeklySchedule updateWeeklySchedule(
		com.weprode.nero.schedule.model.WeeklySchedule weeklySchedule) {

		return _weeklyScheduleLocalService.updateWeeklySchedule(weeklySchedule);
	}

	@Override
	public WeeklyScheduleLocalService getWrappedService() {
		return _weeklyScheduleLocalService;
	}

	@Override
	public void setWrappedService(
		WeeklyScheduleLocalService weeklyScheduleLocalService) {

		_weeklyScheduleLocalService = weeklyScheduleLocalService;
	}

	private WeeklyScheduleLocalService _weeklyScheduleLocalService;

}