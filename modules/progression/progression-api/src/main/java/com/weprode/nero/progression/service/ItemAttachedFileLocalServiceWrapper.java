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
 * Provides a wrapper for {@link ItemAttachedFileLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ItemAttachedFileLocalService
 * @generated
 */
public class ItemAttachedFileLocalServiceWrapper
	implements ItemAttachedFileLocalService,
			   ServiceWrapper<ItemAttachedFileLocalService> {

	public ItemAttachedFileLocalServiceWrapper(
		ItemAttachedFileLocalService itemAttachedFileLocalService) {

		_itemAttachedFileLocalService = itemAttachedFileLocalService;
	}

	@Override
	public com.weprode.nero.progression.model.ItemAttachedFile addAttachedFile(
			long itemId, java.io.File file, boolean isToBeCompleted,
			boolean isAudioRecording)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemAttachedFileLocalService.addAttachedFile(
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
	@Override
	public com.weprode.nero.progression.model.ItemAttachedFile
		addItemAttachedFile(
			com.weprode.nero.progression.model.ItemAttachedFile
				itemAttachedFile) {

		return _itemAttachedFileLocalService.addItemAttachedFile(
			itemAttachedFile);
	}

	/**
	 * Creates a new item attached file with the primary key. Does not add the item attached file to the database.
	 *
	 * @param itemAttachedFileId the primary key for the new item attached file
	 * @return the new item attached file
	 */
	@Override
	public com.weprode.nero.progression.model.ItemAttachedFile
		createItemAttachedFile(long itemAttachedFileId) {

		return _itemAttachedFileLocalService.createItemAttachedFile(
			itemAttachedFileId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAttachedFileLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public void deleteAttachedFile(long itemAttachedFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_itemAttachedFileLocalService.deleteAttachedFile(itemAttachedFileId);
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
	@Override
	public com.weprode.nero.progression.model.ItemAttachedFile
		deleteItemAttachedFile(
			com.weprode.nero.progression.model.ItemAttachedFile
				itemAttachedFile) {

		return _itemAttachedFileLocalService.deleteItemAttachedFile(
			itemAttachedFile);
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
	@Override
	public com.weprode.nero.progression.model.ItemAttachedFile
			deleteItemAttachedFile(long itemAttachedFileId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAttachedFileLocalService.deleteItemAttachedFile(
			itemAttachedFileId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAttachedFileLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _itemAttachedFileLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _itemAttachedFileLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemAttachedFileLocalService.dynamicQuery();
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

		return _itemAttachedFileLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _itemAttachedFileLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _itemAttachedFileLocalService.dynamicQuery(
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

		return _itemAttachedFileLocalService.dynamicQueryCount(dynamicQuery);
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

		return _itemAttachedFileLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.progression.model.ItemAttachedFile
		fetchItemAttachedFile(long itemAttachedFileId) {

		return _itemAttachedFileLocalService.fetchItemAttachedFile(
			itemAttachedFileId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _itemAttachedFileLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _itemAttachedFileLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the item attached file with the primary key.
	 *
	 * @param itemAttachedFileId the primary key of the item attached file
	 * @return the item attached file
	 * @throws PortalException if a item attached file with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.progression.model.ItemAttachedFile
			getItemAttachedFile(long itemAttachedFileId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAttachedFileLocalService.getItemAttachedFile(
			itemAttachedFileId);
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
	@Override
	public java.util.List<com.weprode.nero.progression.model.ItemAttachedFile>
		getItemAttachedFiles(int start, int end) {

		return _itemAttachedFileLocalService.getItemAttachedFiles(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.progression.model.ItemAttachedFile>
			getItemAttachedFiles(long itemId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemAttachedFileLocalService.getItemAttachedFiles(itemId);
	}

	/**
	 * Returns the number of item attached files.
	 *
	 * @return the number of item attached files
	 */
	@Override
	public int getItemAttachedFilesCount() {
		return _itemAttachedFileLocalService.getItemAttachedFilesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _itemAttachedFileLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAttachedFileLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.weprode.nero.progression.model.ItemAttachedFile
		updateItemAttachedFile(
			com.weprode.nero.progression.model.ItemAttachedFile
				itemAttachedFile) {

		return _itemAttachedFileLocalService.updateItemAttachedFile(
			itemAttachedFile);
	}

	@Override
	public ItemAttachedFileLocalService getWrappedService() {
		return _itemAttachedFileLocalService;
	}

	@Override
	public void setWrappedService(
		ItemAttachedFileLocalService itemAttachedFileLocalService) {

		_itemAttachedFileLocalService = itemAttachedFileLocalService;
	}

	private ItemAttachedFileLocalService _itemAttachedFileLocalService;

}