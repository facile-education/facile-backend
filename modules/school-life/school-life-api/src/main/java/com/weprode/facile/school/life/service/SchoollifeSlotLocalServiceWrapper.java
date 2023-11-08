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

package com.weprode.facile.school.life.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SchoollifeSlotLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlotLocalService
 * @generated
 */
public class SchoollifeSlotLocalServiceWrapper
	implements SchoollifeSlotLocalService,
			   ServiceWrapper<SchoollifeSlotLocalService> {

	public SchoollifeSlotLocalServiceWrapper() {
		this(null);
	}

	public SchoollifeSlotLocalServiceWrapper(
		SchoollifeSlotLocalService schoollifeSlotLocalService) {

		_schoollifeSlotLocalService = schoollifeSlotLocalService;
	}

	/**
	 * Adds the schoollife slot to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSlotLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSlot the schoollife slot
	 * @return the schoollife slot that was added
	 */
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSlot
		addSchoollifeSlot(
			com.weprode.facile.school.life.model.SchoollifeSlot
				schoollifeSlot) {

		return _schoollifeSlotLocalService.addSchoollifeSlot(schoollifeSlot);
	}

	@Override
	public com.weprode.facile.school.life.model.SchoollifeSlot addSlot(
		long schoolId, java.util.Date startDate, java.util.Date endDate,
		int day, String startHour, String endHour, long teacherId, int type,
		String room, int capacity) {

		return _schoollifeSlotLocalService.addSlot(
			schoolId, startDate, endDate, day, startHour, endHour, teacherId,
			type, room, capacity);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSlotLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new schoollife slot with the primary key. Does not add the schoollife slot to the database.
	 *
	 * @param schoollifeSlotId the primary key for the new schoollife slot
	 * @return the new schoollife slot
	 */
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSlot
		createSchoollifeSlot(long schoollifeSlotId) {

		return _schoollifeSlotLocalService.createSchoollifeSlot(
			schoollifeSlotId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSlotLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the schoollife slot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSlotLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot that was removed
	 * @throws PortalException if a schoollife slot with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSlot
			deleteSchoollifeSlot(long schoollifeSlotId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSlotLocalService.deleteSchoollifeSlot(
			schoollifeSlotId);
	}

	/**
	 * Deletes the schoollife slot from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSlotLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSlot the schoollife slot
	 * @return the schoollife slot that was removed
	 */
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSlot
		deleteSchoollifeSlot(
			com.weprode.facile.school.life.model.SchoollifeSlot
				schoollifeSlot) {

		return _schoollifeSlotLocalService.deleteSchoollifeSlot(schoollifeSlot);
	}

	@Override
	public boolean deleteSlot(
		long schoollifeSlotId, java.util.Date startDate,
		java.util.Date endDate) {

		return _schoollifeSlotLocalService.deleteSlot(
			schoollifeSlotId, startDate, endDate);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _schoollifeSlotLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _schoollifeSlotLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _schoollifeSlotLocalService.dynamicQuery();
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

		return _schoollifeSlotLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.school.life.model.impl.SchoollifeSlotModelImpl</code>.
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

		return _schoollifeSlotLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.school.life.model.impl.SchoollifeSlotModelImpl</code>.
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

		return _schoollifeSlotLocalService.dynamicQuery(
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

		return _schoollifeSlotLocalService.dynamicQueryCount(dynamicQuery);
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

		return _schoollifeSlotLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.school.life.model.SchoollifeSlot editSlot(
		long schoollifeSlotId, java.util.Date startDate, java.util.Date endDate,
		int newDay, String newStartHour, String newEndHour, long newTeacherId,
		String newRoom, int newCapacity) {

		return _schoollifeSlotLocalService.editSlot(
			schoollifeSlotId, startDate, endDate, newDay, newStartHour,
			newEndHour, newTeacherId, newRoom, newCapacity);
	}

	@Override
	public com.weprode.facile.school.life.model.SchoollifeSlot
		fetchSchoollifeSlot(long schoollifeSlotId) {

		return _schoollifeSlotLocalService.fetchSchoollifeSlot(
			schoollifeSlotId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _schoollifeSlotLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _schoollifeSlotLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _schoollifeSlotLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSlotLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the schoollife slot with the primary key.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot
	 * @throws PortalException if a schoollife slot with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSlot
			getSchoollifeSlot(long schoollifeSlotId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schoollifeSlotLocalService.getSchoollifeSlot(schoollifeSlotId);
	}

	/**
	 * Returns a range of all the schoollife slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.school.life.model.impl.SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @return the range of schoollife slots
	 */
	@Override
	public java.util.List<com.weprode.facile.school.life.model.SchoollifeSlot>
		getSchoollifeSlots(int start, int end) {

		return _schoollifeSlotLocalService.getSchoollifeSlots(start, end);
	}

	/**
	 * Returns the number of schoollife slots.
	 *
	 * @return the number of schoollife slots
	 */
	@Override
	public int getSchoollifeSlotsCount() {
		return _schoollifeSlotLocalService.getSchoollifeSlotsCount();
	}

	@Override
	public java.util.List<com.weprode.facile.school.life.model.SchoollifeSlot>
		getWeekSlots(long schoolId, int type) {

		return _schoollifeSlotLocalService.getWeekSlots(schoolId, type);
	}

	/**
	 * Updates the schoollife slot in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSlotLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSlot the schoollife slot
	 * @return the schoollife slot that was updated
	 */
	@Override
	public com.weprode.facile.school.life.model.SchoollifeSlot
		updateSchoollifeSlot(
			com.weprode.facile.school.life.model.SchoollifeSlot
				schoollifeSlot) {

		return _schoollifeSlotLocalService.updateSchoollifeSlot(schoollifeSlot);
	}

	@Override
	public SchoollifeSlotLocalService getWrappedService() {
		return _schoollifeSlotLocalService;
	}

	@Override
	public void setWrappedService(
		SchoollifeSlotLocalService schoollifeSlotLocalService) {

		_schoollifeSlotLocalService = schoollifeSlotLocalService;
	}

	private SchoollifeSlotLocalService _schoollifeSlotLocalService;

}