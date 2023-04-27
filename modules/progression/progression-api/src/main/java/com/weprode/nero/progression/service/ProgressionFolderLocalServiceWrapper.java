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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProgressionFolderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionFolderLocalService
 * @generated
 */
public class ProgressionFolderLocalServiceWrapper
	implements ProgressionFolderLocalService,
			   ServiceWrapper<ProgressionFolderLocalService> {

	public ProgressionFolderLocalServiceWrapper(
		ProgressionFolderLocalService progressionFolderLocalService) {

		_progressionFolderLocalService = progressionFolderLocalService;
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionFolder addFolder(
			long progressionId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _progressionFolderLocalService.addFolder(
			progressionId, parentFolderId);
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
	@Override
	public com.weprode.nero.progression.model.ProgressionFolder
		addProgressionFolder(
			com.weprode.nero.progression.model.ProgressionFolder
				progressionFolder) {

		return _progressionFolderLocalService.addProgressionFolder(
			progressionFolder);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionFolderLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new progression folder with the primary key. Does not add the progression folder to the database.
	 *
	 * @param progressionFolderId the primary key for the new progression folder
	 * @return the new progression folder
	 */
	@Override
	public com.weprode.nero.progression.model.ProgressionFolder
		createProgressionFolder(long progressionFolderId) {

		return _progressionFolderLocalService.createProgressionFolder(
			progressionFolderId);
	}

	@Override
	public void deleteFolder(long userId, long progressionFolderId)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.progression.exception.NoSuchFolderException {

		_progressionFolderLocalService.deleteFolder(
			userId, progressionFolderId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionFolderLocalService.deletePersistedModel(
			persistedModel);
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
	@Override
	public com.weprode.nero.progression.model.ProgressionFolder
			deleteProgressionFolder(long progressionFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionFolderLocalService.deleteProgressionFolder(
			progressionFolderId);
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
	@Override
	public com.weprode.nero.progression.model.ProgressionFolder
		deleteProgressionFolder(
			com.weprode.nero.progression.model.ProgressionFolder
				progressionFolder) {

		return _progressionFolderLocalService.deleteProgressionFolder(
			progressionFolder);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _progressionFolderLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _progressionFolderLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _progressionFolderLocalService.dynamicQuery();
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

		return _progressionFolderLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _progressionFolderLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _progressionFolderLocalService.dynamicQuery(
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

		return _progressionFolderLocalService.dynamicQueryCount(dynamicQuery);
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

		return _progressionFolderLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionFolder
		fetchProgressionFolder(long progressionFolderId) {

		return _progressionFolderLocalService.fetchProgressionFolder(
			progressionFolderId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _progressionFolderLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _progressionFolderLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _progressionFolderLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionFolderLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the progression folder with the primary key.
	 *
	 * @param progressionFolderId the primary key of the progression folder
	 * @return the progression folder
	 * @throws PortalException if a progression folder with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.progression.model.ProgressionFolder
			getProgressionFolder(long progressionFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionFolderLocalService.getProgressionFolder(
			progressionFolderId);
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
	@Override
	public java.util.List<com.weprode.nero.progression.model.ProgressionFolder>
		getProgressionFolders(int start, int end) {

		return _progressionFolderLocalService.getProgressionFolders(start, end);
	}

	/**
	 * Returns the number of progression folders.
	 *
	 * @return the number of progression folders
	 */
	@Override
	public int getProgressionFoldersCount() {
		return _progressionFolderLocalService.getProgressionFoldersCount();
	}

	@Override
	public java.util.List<com.weprode.nero.progression.model.ProgressionFolder>
			getProgressionRootFolders(long progressionId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _progressionFolderLocalService.getProgressionRootFolders(
			progressionId);
	}

	@Override
	public java.util.List<com.weprode.nero.progression.model.ProgressionFolder>
			getSubFolders(long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _progressionFolderLocalService.getSubFolders(parentFolderId);
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionFolder updateFolder(
			long progressionFolderId, long parentFolderId, String name,
			int order)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _progressionFolderLocalService.updateFolder(
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
	@Override
	public com.weprode.nero.progression.model.ProgressionFolder
		updateProgressionFolder(
			com.weprode.nero.progression.model.ProgressionFolder
				progressionFolder) {

		return _progressionFolderLocalService.updateProgressionFolder(
			progressionFolder);
	}

	@Override
	public ProgressionFolderLocalService getWrappedService() {
		return _progressionFolderLocalService;
	}

	@Override
	public void setWrappedService(
		ProgressionFolderLocalService progressionFolderLocalService) {

		_progressionFolderLocalService = progressionFolderLocalService;
	}

	private ProgressionFolderLocalService _progressionFolderLocalService;

}