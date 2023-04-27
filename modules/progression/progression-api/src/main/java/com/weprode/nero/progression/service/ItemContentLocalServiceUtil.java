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

import com.weprode.nero.progression.model.ItemContent;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ItemContent. This utility wraps
 * <code>com.weprode.nero.progression.service.impl.ItemContentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ItemContentLocalService
 * @generated
 */
public class ItemContentLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.progression.service.impl.ItemContentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ItemContent addContent(
			long itemId, int contentType, String contentName,
			String contentValue, long fileEntryId, boolean isToBeCompleted,
			long userId)
		throws com.weprode.nero.progression.exception.UnauthorizedUrlException,
			   java.io.IOException, PortalException, SystemException {

		return getService().addContent(
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
	public static ItemContent addItemContent(ItemContent itemContent) {
		return getService().addItemContent(itemContent);
	}

	public static String convertContentToHtml(long contentId) {
		return getService().convertContentToHtml(contentId);
	}

	/**
	 * Creates a new item content with the primary key. Does not add the item content to the database.
	 *
	 * @param contentId the primary key for the new item content
	 * @return the new item content
	 */
	public static ItemContent createItemContent(long contentId) {
		return getService().createItemContent(contentId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static boolean deleteContent(long contentId) {
		return getService().deleteContent(contentId);
	}

	public static boolean deleteContentsByItemId(long itemId) {
		return getService().deleteContentsByItemId(itemId);
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
	public static ItemContent deleteItemContent(ItemContent itemContent) {
		return getService().deleteItemContent(itemContent);
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
	public static ItemContent deleteItemContent(long contentId)
		throws PortalException {

		return getService().deleteItemContent(contentId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemContentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemContentModelImpl</code>.
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

	public static ItemContent fetchItemContent(long contentId) {
		return getService().fetchItemContent(contentId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<Long> getAudioFileIds(long itemId) {
		return getService().getAudioFileIds(itemId);
	}

	public static List<ItemContent> getContentsByItemId(long itemId) {
		return getService().getContentsByItemId(itemId);
	}

	public static List<Long> getEditableFileIds(long itemId) {
		return getService().getEditableFileIds(itemId);
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
	 * Returns the item content with the primary key.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content
	 * @throws PortalException if a item content with the primary key could not be found
	 */
	public static ItemContent getItemContent(long contentId)
		throws PortalException {

		return getService().getItemContent(contentId);
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
	public static List<ItemContent> getItemContents(int start, int end) {
		return getService().getItemContents(start, end);
	}

	/**
	 * Returns the number of item contents.
	 *
	 * @return the number of item contents
	 */
	public static int getItemContentsCount() {
		return getService().getItemContentsCount();
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

	public static ItemContent updateContent(
			long contentId, String contentName, String contentValue, int order)
		throws com.weprode.nero.progression.exception.UnauthorizedUrlException,
			   SystemException {

		return getService().updateContent(
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
	public static ItemContent updateItemContent(ItemContent itemContent) {
		return getService().updateItemContent(itemContent);
	}

	public static ItemContentLocalService getService() {
		return _service;
	}

	private static volatile ItemContentLocalService _service;

}