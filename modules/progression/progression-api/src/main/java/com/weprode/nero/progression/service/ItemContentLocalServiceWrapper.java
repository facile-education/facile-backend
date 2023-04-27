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
 * Provides a wrapper for {@link ItemContentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ItemContentLocalService
 * @generated
 */
public class ItemContentLocalServiceWrapper
	implements ItemContentLocalService,
			   ServiceWrapper<ItemContentLocalService> {

	public ItemContentLocalServiceWrapper(
		ItemContentLocalService itemContentLocalService) {

		_itemContentLocalService = itemContentLocalService;
	}

	@Override
	public com.weprode.nero.progression.model.ItemContent addContent(
			long itemId, int contentType, String contentName,
			String contentValue, long fileEntryId, boolean isToBeCompleted,
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.progression.exception.UnauthorizedUrlException,
			   java.io.IOException {

		return _itemContentLocalService.addContent(
			itemId, contentType, contentName, contentValue, fileEntryId,
			isToBeCompleted, userId);
	}

	/**
	 * Adds the item content to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemContent the item content
	 * @return the item content that was added
	 */
	@Override
	public com.weprode.nero.progression.model.ItemContent addItemContent(
		com.weprode.nero.progression.model.ItemContent itemContent) {

		return _itemContentLocalService.addItemContent(itemContent);
	}

	@Override
	public String convertContentToHtml(long contentId) {
		return _itemContentLocalService.convertContentToHtml(contentId);
	}

	/**
	 * Creates a new item content with the primary key. Does not add the item content to the database.
	 *
	 * @param contentId the primary key for the new item content
	 * @return the new item content
	 */
	@Override
	public com.weprode.nero.progression.model.ItemContent createItemContent(
		long contentId) {

		return _itemContentLocalService.createItemContent(contentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemContentLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean deleteContent(long contentId) {
		return _itemContentLocalService.deleteContent(contentId);
	}

	@Override
	public boolean deleteContentsByItemId(long itemId) {
		return _itemContentLocalService.deleteContentsByItemId(itemId);
	}

	/**
	 * Deletes the item content from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemContent the item content
	 * @return the item content that was removed
	 */
	@Override
	public com.weprode.nero.progression.model.ItemContent deleteItemContent(
		com.weprode.nero.progression.model.ItemContent itemContent) {

		return _itemContentLocalService.deleteItemContent(itemContent);
	}

	/**
	 * Deletes the item content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content that was removed
	 * @throws PortalException if a item content with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.progression.model.ItemContent deleteItemContent(
			long contentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemContentLocalService.deleteItemContent(contentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemContentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _itemContentLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _itemContentLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemContentLocalService.dynamicQuery();
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

		return _itemContentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemContentModelImpl</code>.
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

		return _itemContentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemContentModelImpl</code>.
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

		return _itemContentLocalService.dynamicQuery(
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

		return _itemContentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _itemContentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.progression.model.ItemContent fetchItemContent(
		long contentId) {

		return _itemContentLocalService.fetchItemContent(contentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _itemContentLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<Long> getAudioFileIds(long itemId) {
		return _itemContentLocalService.getAudioFileIds(itemId);
	}

	@Override
	public java.util.List<com.weprode.nero.progression.model.ItemContent>
		getContentsByItemId(long itemId) {

		return _itemContentLocalService.getContentsByItemId(itemId);
	}

	@Override
	public java.util.List<Long> getEditableFileIds(long itemId) {
		return _itemContentLocalService.getEditableFileIds(itemId);
	}

	@Override
	public java.util.List<Long> getFileIds(long itemId) {
		return _itemContentLocalService.getFileIds(itemId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _itemContentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the item content with the primary key.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content
	 * @throws PortalException if a item content with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.progression.model.ItemContent getItemContent(
			long contentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemContentLocalService.getItemContent(contentId);
	}

	/**
	 * Returns a range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @return the range of item contents
	 */
	@Override
	public java.util.List<com.weprode.nero.progression.model.ItemContent>
		getItemContents(int start, int end) {

		return _itemContentLocalService.getItemContents(start, end);
	}

	/**
	 * Returns the number of item contents.
	 *
	 * @return the number of item contents
	 */
	@Override
	public int getItemContentsCount() {
		return _itemContentLocalService.getItemContentsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _itemContentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemContentLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.weprode.nero.progression.model.ItemContent updateContent(
			long contentId, String contentName, String contentValue, int order)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.progression.exception.UnauthorizedUrlException {

		return _itemContentLocalService.updateContent(
			contentId, contentName, contentValue, order);
	}

	/**
	 * Updates the item content in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemContentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemContent the item content
	 * @return the item content that was updated
	 */
	@Override
	public com.weprode.nero.progression.model.ItemContent updateItemContent(
		com.weprode.nero.progression.model.ItemContent itemContent) {

		return _itemContentLocalService.updateItemContent(itemContent);
	}

	@Override
	public ItemContentLocalService getWrappedService() {
		return _itemContentLocalService;
	}

	@Override
	public void setWrappedService(
		ItemContentLocalService itemContentLocalService) {

		_itemContentLocalService = itemContentLocalService;
	}

	private ItemContentLocalService _itemContentLocalService;

}