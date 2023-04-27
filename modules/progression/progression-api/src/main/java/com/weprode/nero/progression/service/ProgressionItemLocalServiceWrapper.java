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
 * Provides a wrapper for {@link ProgressionItemLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItemLocalService
 * @generated
 */
public class ProgressionItemLocalServiceWrapper
	implements ProgressionItemLocalService,
			   ServiceWrapper<ProgressionItemLocalService> {

	public ProgressionItemLocalServiceWrapper(
		ProgressionItemLocalService progressionItemLocalService) {

		_progressionItemLocalService = progressionItemLocalService;
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionItem addItem(
			long progressionId, long userId, long folderId, boolean isHomework)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _progressionItemLocalService.addItem(
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
	@Override
	public com.weprode.nero.progression.model.ProgressionItem
		addProgressionItem(
			com.weprode.nero.progression.model.ProgressionItem
				progressionItem) {

		return _progressionItemLocalService.addProgressionItem(progressionItem);
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionItem
		cloneItemForSpecificHomework(
			long userId, long sourceItemId, long homeworkId) {

		return _progressionItemLocalService.cloneItemForSpecificHomework(
			userId, sourceItemId, homeworkId);
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionItem
		cloneItemForSpecificSession(
			long userId, long sourceItemId, long sessionId) {

		return _progressionItemLocalService.cloneItemForSpecificSession(
			userId, sourceItemId, sessionId);
	}

	@Override
	public String convertContentAsHtml(long itemId) {
		return _progressionItemLocalService.convertContentAsHtml(itemId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionItemLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new progression item with the primary key. Does not add the progression item to the database.
	 *
	 * @param progressionItemId the primary key for the new progression item
	 * @return the new progression item
	 */
	@Override
	public com.weprode.nero.progression.model.ProgressionItem
		createProgressionItem(long progressionItemId) {

		return _progressionItemLocalService.createProgressionItem(
			progressionItemId);
	}

	@Override
	public void deleteItem(long userId, long progressionItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_progressionItemLocalService.deleteItem(userId, progressionItemId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionItemLocalService.deletePersistedModel(
			persistedModel);
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
	@Override
	public com.weprode.nero.progression.model.ProgressionItem
			deleteProgressionItem(long progressionItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionItemLocalService.deleteProgressionItem(
			progressionItemId);
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
	@Override
	public com.weprode.nero.progression.model.ProgressionItem
		deleteProgressionItem(
			com.weprode.nero.progression.model.ProgressionItem
				progressionItem) {

		return _progressionItemLocalService.deleteProgressionItem(
			progressionItem);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _progressionItemLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _progressionItemLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _progressionItemLocalService.dynamicQuery();
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

		return _progressionItemLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _progressionItemLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _progressionItemLocalService.dynamicQuery(
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

		return _progressionItemLocalService.dynamicQueryCount(dynamicQuery);
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

		return _progressionItemLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionItem
		fetchProgressionItem(long progressionItemId) {

		return _progressionItemLocalService.fetchProgressionItem(
			progressionItemId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _progressionItemLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.nero.progression.model.ProgressionItem>
			getFolderItems(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _progressionItemLocalService.getFolderItems(folderId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _progressionItemLocalService.
			getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder
			getOrCreateDLFolder(long itemId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _progressionItemLocalService.getOrCreateDLFolder(itemId, userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _progressionItemLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionItemLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the progression item with the primary key.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item
	 * @throws PortalException if a progression item with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.progression.model.ProgressionItem
			getProgressionItem(long progressionItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionItemLocalService.getProgressionItem(
			progressionItemId);
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
	@Override
	public java.util.List<com.weprode.nero.progression.model.ProgressionItem>
		getProgressionItems(int start, int end) {

		return _progressionItemLocalService.getProgressionItems(start, end);
	}

	/**
	 * Returns the number of progression items.
	 *
	 * @return the number of progression items
	 */
	@Override
	public int getProgressionItemsCount() {
		return _progressionItemLocalService.getProgressionItemsCount();
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionItem
		getSpecificHomeworkItem(long homeworkId) {

		return _progressionItemLocalService.getSpecificHomeworkItem(homeworkId);
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionItem
		getSpecificSessionItem(long sessionId) {

		return _progressionItemLocalService.getSpecificSessionItem(sessionId);
	}

	@Override
	public com.weprode.nero.progression.model.ProgressionItem updateItem(
			long progressionItemId, long folderId, String name, int type,
			String duration, int order)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.progression.exception.NoSuchItemException {

		return _progressionItemLocalService.updateItem(
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
	@Override
	public com.weprode.nero.progression.model.ProgressionItem
		updateProgressionItem(
			com.weprode.nero.progression.model.ProgressionItem
				progressionItem) {

		return _progressionItemLocalService.updateProgressionItem(
			progressionItem);
	}

	@Override
	public ProgressionItemLocalService getWrappedService() {
		return _progressionItemLocalService;
	}

	@Override
	public void setWrappedService(
		ProgressionItemLocalService progressionItemLocalService) {

		_progressionItemLocalService = progressionItemLocalService;
	}

	private ProgressionItemLocalService _progressionItemLocalService;

}