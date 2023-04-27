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

import com.weprode.nero.progression.model.ProgressionItem;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ProgressionItem. This utility wraps
 * <code>com.weprode.nero.progression.service.impl.ProgressionItemLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItemLocalService
 * @generated
 */
public class ProgressionItemLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.progression.service.impl.ProgressionItemLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ProgressionItem addItem(
			long progressionId, long userId, long folderId, boolean isHomework)
		throws SystemException {

		return getService().addItem(
			progressionId, userId, folderId, isHomework);
	}

	/**
	 * Adds the progression item to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progressionItem the progression item
	 * @return the progression item that was added
	 */
	public static ProgressionItem addProgressionItem(
		ProgressionItem progressionItem) {

		return getService().addProgressionItem(progressionItem);
	}

	public static ProgressionItem cloneItemForSpecificHomework(
		long userId, long sourceItemId, long homeworkId) {

		return getService().cloneItemForSpecificHomework(
			userId, sourceItemId, homeworkId);
	}

	public static ProgressionItem cloneItemForSpecificSession(
		long userId, long sourceItemId, long sessionId) {

		return getService().cloneItemForSpecificSession(
			userId, sourceItemId, sessionId);
	}

	public static String convertContentAsHtml(long itemId) {
		return getService().convertContentAsHtml(itemId);
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
	 * Creates a new progression item with the primary key. Does not add the progression item to the database.
	 *
	 * @param progressionItemId the primary key for the new progression item
	 * @return the new progression item
	 */
	public static ProgressionItem createProgressionItem(
		long progressionItemId) {

		return getService().createProgressionItem(progressionItemId);
	}

	public static void deleteItem(long userId, long progressionItemId)
		throws PortalException, SystemException {

		getService().deleteItem(userId, progressionItemId);
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
	 * Deletes the progression item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item that was removed
	 * @throws PortalException if a progression item with the primary key could not be found
	 */
	public static ProgressionItem deleteProgressionItem(long progressionItemId)
		throws PortalException {

		return getService().deleteProgressionItem(progressionItemId);
	}

	/**
	 * Deletes the progression item from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progressionItem the progression item
	 * @return the progression item that was removed
	 */
	public static ProgressionItem deleteProgressionItem(
		ProgressionItem progressionItem) {

		return getService().deleteProgressionItem(progressionItem);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ProgressionItemModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ProgressionItemModelImpl</code>.
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

	public static ProgressionItem fetchProgressionItem(long progressionItemId) {
		return getService().fetchProgressionItem(progressionItemId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<ProgressionItem> getFolderItems(long folderId)
		throws SystemException {

		return getService().getFolderItems(folderId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getOrCreateDLFolder(long itemId, long userId)
		throws PortalException, SystemException {

		return getService().getOrCreateDLFolder(itemId, userId);
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
	 * Returns the progression item with the primary key.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item
	 * @throws PortalException if a progression item with the primary key could not be found
	 */
	public static ProgressionItem getProgressionItem(long progressionItemId)
		throws PortalException {

		return getService().getProgressionItem(progressionItemId);
	}

	/**
	 * Returns a range of all the progression items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @return the range of progression items
	 */
	public static List<ProgressionItem> getProgressionItems(
		int start, int end) {

		return getService().getProgressionItems(start, end);
	}

	/**
	 * Returns the number of progression items.
	 *
	 * @return the number of progression items
	 */
	public static int getProgressionItemsCount() {
		return getService().getProgressionItemsCount();
	}

	public static ProgressionItem getSpecificHomeworkItem(long homeworkId) {
		return getService().getSpecificHomeworkItem(homeworkId);
	}

	public static ProgressionItem getSpecificSessionItem(long sessionId) {
		return getService().getSpecificSessionItem(sessionId);
	}

	public static ProgressionItem updateItem(
			long progressionItemId, long folderId, String name, int type,
			String duration, int order)
		throws com.weprode.nero.progression.exception.NoSuchItemException,
			   SystemException {

		return getService().updateItem(
			progressionItemId, folderId, name, type, duration, order);
	}

	/**
	 * Updates the progression item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progressionItem the progression item
	 * @return the progression item that was updated
	 */
	public static ProgressionItem updateProgressionItem(
		ProgressionItem progressionItem) {

		return getService().updateProgressionItem(progressionItem);
	}

	public static ProgressionItemLocalService getService() {
		return _service;
	}

	private static volatile ProgressionItemLocalService _service;

}