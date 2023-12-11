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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.course.model.ContentBlock;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ContentBlock. This utility wraps
 * <code>com.weprode.facile.course.service.impl.ContentBlockLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlockLocalService
 * @generated
 */
public class ContentBlockLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.course.service.impl.ContentBlockLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ContentBlock addBlock(
			long userId, long itemId, int blockType, String blockName,
			String blockValue, long fileEntryId)
		throws com.weprode.facile.course.exception.UnauthorizedUrlException,
			   java.io.IOException, PortalException, SystemException {

		return getService().addBlock(
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
	public static ContentBlock addContentBlock(ContentBlock contentBlock) {
		return getService().addContentBlock(contentBlock);
	}

	public static String convertBlockToHtml(long blockId) {
		return getService().convertBlockToHtml(blockId);
	}

	/**
	 * Creates a new content block with the primary key. Does not add the content block to the database.
	 *
	 * @param blockId the primary key for the new content block
	 * @return the new content block
	 */
	public static ContentBlock createContentBlock(long blockId) {
		return getService().createContentBlock(blockId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static boolean deleteBlock(long blockId) {
		return getService().deleteBlock(blockId);
	}

	public static boolean deleteBlocksByItemId(long itemId) {
		return getService().deleteBlocksByItemId(itemId);
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
	public static ContentBlock deleteContentBlock(ContentBlock contentBlock) {
		return getService().deleteContentBlock(contentBlock);
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
	public static ContentBlock deleteContentBlock(long blockId)
		throws PortalException {

		return getService().deleteContentBlock(blockId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.ContentBlockModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.ContentBlockModelImpl</code>.
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

	public static ContentBlock fetchContentBlock(long blockId) {
		return getService().fetchContentBlock(blockId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<Long> getAudioFileIds(long itemId) {
		return getService().getAudioFileIds(itemId);
	}

	/**
	 * Returns the content block with the primary key.
	 *
	 * @param blockId the primary key of the content block
	 * @return the content block
	 * @throws PortalException if a content block with the primary key could not be found
	 */
	public static ContentBlock getContentBlock(long blockId)
		throws PortalException {

		return getService().getContentBlock(blockId);
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
	public static List<ContentBlock> getContentBlocks(int start, int end) {
		return getService().getContentBlocks(start, end);
	}

	/**
	 * Returns the number of content blocks.
	 *
	 * @return the number of content blocks
	 */
	public static int getContentBlocksCount() {
		return getService().getContentBlocksCount();
	}

	public static List<ContentBlock> getContentsByItemId(long itemId) {
		return getService().getContentsByItemId(itemId);
	}

	public static List<Long> getFileIds(long itemId) {
		return getService().getFileIds(itemId);
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

	public static boolean isEmbedUrlWhitelisted(String url)
		throws com.weprode.facile.course.exception.UnauthorizedUrlException {

		return getService().isEmbedUrlWhitelisted(url);
	}

	public static ContentBlock updateBlock(
			long blockId, String blockName, String blockValue, int order)
		throws com.weprode.facile.course.exception.UnauthorizedUrlException,
			   SystemException {

		return getService().updateBlock(blockId, blockName, blockValue, order);
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
	public static ContentBlock updateContentBlock(ContentBlock contentBlock) {
		return getService().updateContentBlock(contentBlock);
	}

	public static ContentBlockLocalService getService() {
		return _service;
	}

	private static volatile ContentBlockLocalService _service;

}