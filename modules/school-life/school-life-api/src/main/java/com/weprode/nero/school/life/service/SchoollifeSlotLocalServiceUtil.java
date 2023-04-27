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

package com.weprode.nero.school.life.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.school.life.model.SchoollifeSlot;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for SchoollifeSlot. This utility wraps
 * <code>com.weprode.nero.school.life.service.impl.SchoollifeSlotLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlotLocalService
 * @generated
 */
public class SchoollifeSlotLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.school.life.service.impl.SchoollifeSlotLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static SchoollifeSlot addSchoollifeSlot(
		SchoollifeSlot schoollifeSlot) {

		return getService().addSchoollifeSlot(schoollifeSlot);
	}

	public static SchoollifeSlot addSlot(
		long schoolId, java.util.Date startDate, int day, String startHour,
		String endHour, long teacherId, int type, String room, int capacity) {

		return getService().addSlot(
			schoolId, startDate, day, startHour, endHour, teacherId, type, room,
			capacity);
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
	 * Creates a new schoollife slot with the primary key. Does not add the schoollife slot to the database.
	 *
	 * @param schoollifeSlotId the primary key for the new schoollife slot
	 * @return the new schoollife slot
	 */
	public static SchoollifeSlot createSchoollifeSlot(long schoollifeSlotId) {
		return getService().createSchoollifeSlot(schoollifeSlotId);
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
	public static SchoollifeSlot deleteSchoollifeSlot(long schoollifeSlotId)
		throws PortalException {

		return getService().deleteSchoollifeSlot(schoollifeSlotId);
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
	public static SchoollifeSlot deleteSchoollifeSlot(
		SchoollifeSlot schoollifeSlot) {

		return getService().deleteSchoollifeSlot(schoollifeSlot);
	}

	public static boolean deleteSlot(
		long schoollifeSlotId, java.util.Date limitDate) {

		return getService().deleteSlot(schoollifeSlotId, limitDate);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.SchoollifeSlotModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.SchoollifeSlotModelImpl</code>.
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

	public static SchoollifeSlot editSlot(
		long schoollifeSlotId, java.util.Date currentDate, int newDay,
		String newStartHour, String newEndHour, long newTeacherId,
		String newRoom, int newCapacity) {

		return getService().editSlot(
			schoollifeSlotId, currentDate, newDay, newStartHour, newEndHour,
			newTeacherId, newRoom, newCapacity);
	}

	public static SchoollifeSlot fetchSchoollifeSlot(long schoollifeSlotId) {
		return getService().fetchSchoollifeSlot(schoollifeSlotId);
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
	 * Returns the schoollife slot with the primary key.
	 *
	 * @param schoollifeSlotId the primary key of the schoollife slot
	 * @return the schoollife slot
	 * @throws PortalException if a schoollife slot with the primary key could not be found
	 */
	public static SchoollifeSlot getSchoollifeSlot(long schoollifeSlotId)
		throws PortalException {

		return getService().getSchoollifeSlot(schoollifeSlotId);
	}

	/**
	 * Returns a range of all the schoollife slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.school.life.model.impl.SchoollifeSlotModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife slots
	 * @param end the upper bound of the range of schoollife slots (not inclusive)
	 * @return the range of schoollife slots
	 */
	public static List<SchoollifeSlot> getSchoollifeSlots(int start, int end) {
		return getService().getSchoollifeSlots(start, end);
	}

	/**
	 * Returns the number of schoollife slots.
	 *
	 * @return the number of schoollife slots
	 */
	public static int getSchoollifeSlotsCount() {
		return getService().getSchoollifeSlotsCount();
	}

	public static List<SchoollifeSlot> getWeekSlots(long schoolId, int type) {
		return getService().getWeekSlots(schoolId, type);
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
	public static SchoollifeSlot updateSchoollifeSlot(
		SchoollifeSlot schoollifeSlot) {

		return getService().updateSchoollifeSlot(schoollifeSlot);
	}

	public static SchoollifeSlotLocalService getService() {
		return _service;
	}

	private static volatile SchoollifeSlotLocalService _service;

}