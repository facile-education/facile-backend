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

package com.weprode.nero.progression.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.progression.model.ItemAssignment;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ItemAssignment. This utility wraps
 * <code>com.weprode.nero.progression.service.impl.ItemAssignmentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ItemAssignmentLocalService
 * @generated
 */
public class ItemAssignmentLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.progression.service.impl.ItemAssignmentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the item assignment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAssignment the item assignment
	 * @return the item assignment that was added
	 */
	public static ItemAssignment addItemAssignment(
		ItemAssignment itemAssignment) {

		return getService().addItemAssignment(itemAssignment);
	}

	public static ItemAssignment assignHomework(
			long itemId, long sessionId, long homeworkId)
		throws SystemException {

		return getService().assignHomework(itemId, sessionId, homeworkId);
	}

	public static ItemAssignment assignSession(long itemId, long sessionId)
		throws SystemException {

		return getService().assignSession(itemId, sessionId);
	}

	/**
	 * Creates a new item assignment with the primary key. Does not add the item assignment to the database.
	 *
	 * @param itemAssignmentPK the primary key for the new item assignment
	 * @return the new item assignment
	 */
	public static ItemAssignment createItemAssignment(
		com.weprode.nero.progression.service.persistence.ItemAssignmentPK
			itemAssignmentPK) {

		return getService().createItemAssignment(itemAssignmentPK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteItemAssigment(ItemAssignment itemAssignment)
		throws SystemException {

		getService().deleteItemAssigment(itemAssignment);
	}

	/**
	 * Deletes the item assignment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAssignment the item assignment
	 * @return the item assignment that was removed
	 */
	public static ItemAssignment deleteItemAssignment(
		ItemAssignment itemAssignment) {

		return getService().deleteItemAssignment(itemAssignment);
	}

	/**
	 * Deletes the item assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment that was removed
	 * @throws PortalException if a item assignment with the primary key could not be found
	 */
	public static ItemAssignment deleteItemAssignment(
			com.weprode.nero.progression.service.persistence.ItemAssignmentPK
				itemAssignmentPK)
		throws PortalException {

		return getService().deleteItemAssignment(itemAssignmentPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemAssignmentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemAssignmentModelImpl</code>.
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

	public static ItemAssignment fetchItemAssignment(
		com.weprode.nero.progression.service.persistence.ItemAssignmentPK
			itemAssignmentPK) {

		return getService().fetchItemAssignment(itemAssignmentPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static ItemAssignment getByItemIdHomeworkId(
			long itemId, long homeworkId)
		throws SystemException {

		return getService().getByItemIdHomeworkId(itemId, homeworkId);
	}

	public static long getHomeworkAssignmentItemId(long homeworkId) {
		return getService().getHomeworkAssignmentItemId(homeworkId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the item assignment with the primary key.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment
	 * @throws PortalException if a item assignment with the primary key could not be found
	 */
	public static ItemAssignment getItemAssignment(
			com.weprode.nero.progression.service.persistence.ItemAssignmentPK
				itemAssignmentPK)
		throws PortalException {

		return getService().getItemAssignment(itemAssignmentPK);
	}

	/**
	 * Returns a range of all the item assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of item assignments
	 */
	public static List<ItemAssignment> getItemAssignments(int start, int end) {
		return getService().getItemAssignments(start, end);
	}

	public static List<ItemAssignment> getItemAssignments(long itemId)
		throws SystemException {

		return getService().getItemAssignments(itemId);
	}

	/**
	 * Returns the number of item assignments.
	 *
	 * @return the number of item assignments
	 */
	public static int getItemAssignmentsCount() {
		return getService().getItemAssignmentsCount();
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

	public static long getSessionAssignmentItemId(long sessionId) {
		return getService().getSessionAssignmentItemId(sessionId);
	}

	public static boolean isSessionAffected(long sessionId) {
		return getService().isSessionAffected(sessionId);
	}

	public static ItemAssignment markAssigmentAsOverride(
			long itemId, long sessionId)
		throws SystemException {

		return getService().markAssigmentAsOverride(itemId, sessionId);
	}

	/**
	 * Updates the item assignment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAssignment the item assignment
	 * @return the item assignment that was updated
	 */
	public static ItemAssignment updateItemAssignment(
		ItemAssignment itemAssignment) {

		return getService().updateItemAssignment(itemAssignment);
	}

	public static ItemAssignmentLocalService getService() {
		return _service;
	}

	private static volatile ItemAssignmentLocalService _service;

}