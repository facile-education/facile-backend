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

package com.weprode.facile.course.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContentBlockLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlockLocalService
 * @generated
 */
public class ContentBlockLocalServiceWrapper
	implements ContentBlockLocalService,
			   ServiceWrapper<ContentBlockLocalService> {

	public ContentBlockLocalServiceWrapper() {
		this(null);
	}

	public ContentBlockLocalServiceWrapper(
		ContentBlockLocalService contentBlockLocalService) {

		_contentBlockLocalService = contentBlockLocalService;
	}

	@Override
	public com.weprode.facile.course.model.ContentBlock addBlock(
			long userId, long itemId, int blockType, String blockName,
			String blockValue, long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.facile.course.exception.UnauthorizedUrlException,
			   java.io.IOException {

		return _contentBlockLocalService.addBlock(
			userId, itemId, blockType, blockName, blockValue, fileEntryId);
	}

	/**
	 * Adds the content block to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContentBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contentBlock the content block
	 * @return the content block that was added
	 */
	@Override
	public com.weprode.facile.course.model.ContentBlock addContentBlock(
		com.weprode.facile.course.model.ContentBlock contentBlock) {

		return _contentBlockLocalService.addContentBlock(contentBlock);
	}

	@Override
	public String convertBlockToHtml(long blockId) {
		return _contentBlockLocalService.convertBlockToHtml(blockId);
	}

	/**
	 * Creates a new content block with the primary key. Does not add the content block to the database.
	 *
	 * @param blockId the primary key for the new content block
	 * @return the new content block
	 */
	@Override
	public com.weprode.facile.course.model.ContentBlock createContentBlock(
		long blockId) {

		return _contentBlockLocalService.createContentBlock(blockId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contentBlockLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean deleteBlock(long blockId) {
		return _contentBlockLocalService.deleteBlock(blockId);
	}

	@Override
	public boolean deleteBlocksByItemId(long itemId) {
		return _contentBlockLocalService.deleteBlocksByItemId(itemId);
	}

	/**
	 * Deletes the content block from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContentBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contentBlock the content block
	 * @return the content block that was removed
	 */
	@Override
	public com.weprode.facile.course.model.ContentBlock deleteContentBlock(
		com.weprode.facile.course.model.ContentBlock contentBlock) {

		return _contentBlockLocalService.deleteContentBlock(contentBlock);
	}

	/**
	 * Deletes the content block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContentBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block that was removed
	 * @throws PortalException if a content block with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.course.model.ContentBlock deleteContentBlock(
			long blockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contentBlockLocalService.deleteContentBlock(blockId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contentBlockLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _contentBlockLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _contentBlockLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contentBlockLocalService.dynamicQuery();
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

		return _contentBlockLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.ContentBlockModelImpl</code>.
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

		return _contentBlockLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.ContentBlockModelImpl</code>.
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

		return _contentBlockLocalService.dynamicQuery(
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

		return _contentBlockLocalService.dynamicQueryCount(dynamicQuery);
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

		return _contentBlockLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.course.model.ContentBlock fetchContentBlock(
		long blockId) {

		return _contentBlockLocalService.fetchContentBlock(blockId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _contentBlockLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<Long> getAudioFileIds(long itemId) {
		return _contentBlockLocalService.getAudioFileIds(itemId);
	}

	/**
	 * Returns the content block with the primary key.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block
	 * @throws PortalException if a content block with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.course.model.ContentBlock getContentBlock(
			long blockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contentBlockLocalService.getContentBlock(blockId);
	}

	/**
	 * Returns a range of all the content blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.ContentBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of content blocks
	 * @param end the upper bound of the range of content blocks (not inclusive)
	 * @return the range of content blocks
	 */
	@Override
	public java.util.List<com.weprode.facile.course.model.ContentBlock>
		getContentBlocks(int start, int end) {

		return _contentBlockLocalService.getContentBlocks(start, end);
	}

	/**
	 * Returns the number of content blocks.
	 *
	 * @return the number of content blocks
	 */
	@Override
	public int getContentBlocksCount() {
		return _contentBlockLocalService.getContentBlocksCount();
	}

	@Override
	public java.util.List<com.weprode.facile.course.model.ContentBlock>
		getContentsByItemId(long itemId) {

		return _contentBlockLocalService.getContentsByItemId(itemId);
	}

	@Override
	public java.util.List<Long> getFileIds(long itemId) {
		return _contentBlockLocalService.getFileIds(itemId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _contentBlockLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contentBlockLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contentBlockLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.weprode.facile.course.model.ContentBlock updateBlock(
			long blockId, String blockName, String blockValue, int order)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.facile.course.exception.UnauthorizedUrlException {

		return _contentBlockLocalService.updateBlock(
			blockId, blockName, blockValue, order);
	}

	/**
	 * Updates the content block in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContentBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contentBlock the content block
	 * @return the content block that was updated
	 */
	@Override
	public com.weprode.facile.course.model.ContentBlock updateContentBlock(
		com.weprode.facile.course.model.ContentBlock contentBlock) {

		return _contentBlockLocalService.updateContentBlock(contentBlock);
	}

	@Override
	public ContentBlockLocalService getWrappedService() {
		return _contentBlockLocalService;
	}

	@Override
	public void setWrappedService(
		ContentBlockLocalService contentBlockLocalService) {

		_contentBlockLocalService = contentBlockLocalService;
	}

	private ContentBlockLocalService _contentBlockLocalService;

}