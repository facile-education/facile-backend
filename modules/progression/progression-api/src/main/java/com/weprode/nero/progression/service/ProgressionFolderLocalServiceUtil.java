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

import com.weprode.nero.progression.model.ProgressionFolder;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ProgressionFolder. This utility wraps
 * <code>com.weprode.nero.progression.service.impl.ProgressionFolderLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionFolderLocalService
 * @generated
 */
public class ProgressionFolderLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.progression.service.impl.ProgressionFolderLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ProgressionFolder addFolder(
			long progressionId, long parentFolderId)
		throws SystemException {

		return getService().addFolder(progressionId, parentFolderId);
	}

	/**
	 * Adds the progression folder to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionFolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progressionFolder the progression folder
	 * @return the progression folder that was added
	 */
	public static ProgressionFolder addProgressionFolder(
		ProgressionFolder progressionFolder) {

		return getService().addProgressionFolder(progressionFolder);
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
	 * Creates a new progression folder with the primary key. Does not add the progression folder to the database.
	 *
	 * @param progressionFolderId the primary key for the new progression folder
	 * @return the new progression folder
	 */
	public static ProgressionFolder createProgressionFolder(
		long progressionFolderId) {

		return getService().createProgressionFolder(progressionFolderId);
	}

	public static void deleteFolder(long userId, long progressionFolderId)
		throws com.weprode.nero.progression.exception.NoSuchFolderException,
			   SystemException {

		getService().deleteFolder(userId, progressionFolderId);
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
	 * Deletes the progression folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionFolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder that was removed
	 * @throws PortalException if a progression folder with the primary key could not be found
	 */
	public static ProgressionFolder deleteProgressionFolder(
			long progressionFolderId)
		throws PortalException {

		return getService().deleteProgressionFolder(progressionFolderId);
	}

	/**
	 * Deletes the progression folder from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionFolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progressionFolder the progression folder
	 * @return the progression folder that was removed
	 */
	public static ProgressionFolder deleteProgressionFolder(
		ProgressionFolder progressionFolder) {

		return getService().deleteProgressionFolder(progressionFolder);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ProgressionFolderModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ProgressionFolderModelImpl</code>.
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

	public static ProgressionFolder fetchProgressionFolder(
		long progressionFolderId) {

		return getService().fetchProgressionFolder(progressionFolderId);
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
	 * Returns the progression folder with the primary key.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder
	 * @throws PortalException if a progression folder with the primary key could not be found
	 */
	public static ProgressionFolder getProgressionFolder(
			long progressionFolderId)
		throws PortalException {

		return getService().getProgressionFolder(progressionFolderId);
	}

	/**
	 * Returns a range of all the progression folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ProgressionFolderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression folders
	 * @param end the upper bound of the range of progression folders (not inclusive)
	 * @return the range of progression folders
	 */
	public static List<ProgressionFolder> getProgressionFolders(
		int start, int end) {

		return getService().getProgressionFolders(start, end);
	}

	/**
	 * Returns the number of progression folders.
	 *
	 * @return the number of progression folders
	 */
	public static int getProgressionFoldersCount() {
		return getService().getProgressionFoldersCount();
	}

	public static List<ProgressionFolder> getProgressionRootFolders(
			long progressionId)
		throws SystemException {

		return getService().getProgressionRootFolders(progressionId);
	}

	public static List<ProgressionFolder> getSubFolders(long parentFolderId)
		throws SystemException {

		return getService().getSubFolders(parentFolderId);
	}

	public static ProgressionFolder updateFolder(
			long progressionFolderId, long parentFolderId, String name,
			int order)
		throws SystemException {

		return getService().updateFolder(
			progressionFolderId, parentFolderId, name, order);
	}

	/**
	 * Updates the progression folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionFolderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progressionFolder the progression folder
	 * @return the progression folder that was updated
	 */
	public static ProgressionFolder updateProgressionFolder(
		ProgressionFolder progressionFolder) {

		return getService().updateProgressionFolder(progressionFolder);
	}

	public static ProgressionFolderLocalService getService() {
		return _service;
	}

	private static volatile ProgressionFolderLocalService _service;

}