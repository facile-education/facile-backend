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

import com.weprode.nero.progression.model.ItemAttachedFile;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ItemAttachedFile. This utility wraps
 * <code>com.weprode.nero.progression.service.impl.ItemAttachedFileLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ItemAttachedFileLocalService
 * @generated
 */
public class ItemAttachedFileLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.progression.service.impl.ItemAttachedFileLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ItemAttachedFile addAttachedFile(
			long itemId, java.io.File file, boolean isToBeCompleted,
			boolean isAudioRecording)
		throws SystemException {

		return getService().addAttachedFile(
			itemId, file, isToBeCompleted, isAudioRecording);
	}

	/**
	 * Adds the item attached file to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAttachedFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAttachedFile the item attached file
	 * @return the item attached file that was added
	 */
	public static ItemAttachedFile addItemAttachedFile(
		ItemAttachedFile itemAttachedFile) {

		return getService().addItemAttachedFile(itemAttachedFile);
	}

	/**
	 * Creates a new item attached file with the primary key. Does not add the item attached file to the database.
	 *
	 * @param itemAttachedFileId the primary key for the new item attached file
	 * @return the new item attached file
	 */
	public static ItemAttachedFile createItemAttachedFile(
		long itemAttachedFileId) {

		return getService().createItemAttachedFile(itemAttachedFileId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteAttachedFile(long itemAttachedFileId)
		throws PortalException, SystemException {

		getService().deleteAttachedFile(itemAttachedFileId);
	}

	/**
	 * Deletes the item attached file from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAttachedFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAttachedFile the item attached file
	 * @return the item attached file that was removed
	 */
	public static ItemAttachedFile deleteItemAttachedFile(
		ItemAttachedFile itemAttachedFile) {

		return getService().deleteItemAttachedFile(itemAttachedFile);
	}

	/**
	 * Deletes the item attached file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAttachedFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAttachedFileId the primary key of the item attached file
	 * @return the item attached file that was removed
	 * @throws PortalException if a item attached file with the primary key could not be found
	 */
	public static ItemAttachedFile deleteItemAttachedFile(
			long itemAttachedFileId)
		throws PortalException {

		return getService().deleteItemAttachedFile(itemAttachedFileId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemAttachedFileModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemAttachedFileModelImpl</code>.
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

	public static ItemAttachedFile fetchItemAttachedFile(
		long itemAttachedFileId) {

		return getService().fetchItemAttachedFile(itemAttachedFileId);
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
	 * Returns the item attached file with the primary key.
	 *
	 * @param itemAttachedFileId the primary key of the item attached file
	 * @return the item attached file
	 * @throws PortalException if a item attached file with the primary key could not be found
	 */
	public static ItemAttachedFile getItemAttachedFile(long itemAttachedFileId)
		throws PortalException {

		return getService().getItemAttachedFile(itemAttachedFileId);
	}

	/**
	 * Returns a range of all the item attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @return the range of item attached files
	 */
	public static List<ItemAttachedFile> getItemAttachedFiles(
		int start, int end) {

		return getService().getItemAttachedFiles(start, end);
	}

	public static List<ItemAttachedFile> getItemAttachedFiles(long itemId)
		throws SystemException {

		return getService().getItemAttachedFiles(itemId);
	}

	/**
	 * Returns the number of item attached files.
	 *
	 * @return the number of item attached files
	 */
	public static int getItemAttachedFilesCount() {
		return getService().getItemAttachedFilesCount();
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
	 * Updates the item attached file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAttachedFileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAttachedFile the item attached file
	 * @return the item attached file that was updated
	 */
	public static ItemAttachedFile updateItemAttachedFile(
		ItemAttachedFile itemAttachedFile) {

		return getService().updateItemAttachedFile(itemAttachedFile);
	}

	public static ItemAttachedFileLocalService getService() {
		return _service;
	}

	private static volatile ItemAttachedFileLocalService _service;

}