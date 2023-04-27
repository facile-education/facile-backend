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
 * Provides a wrapper for {@link ItemAssignmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ItemAssignmentLocalService
 * @generated
 */
public class ItemAssignmentLocalServiceWrapper
	implements ItemAssignmentLocalService,
			   ServiceWrapper<ItemAssignmentLocalService> {

	public ItemAssignmentLocalServiceWrapper(
		ItemAssignmentLocalService itemAssignmentLocalService) {

		_itemAssignmentLocalService = itemAssignmentLocalService;
	}

	/**
	 * Adds the item assignment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAssignment the item assignment
	 * @return the item assignment that was added
	 */
	@Override
	public com.weprode.nero.progression.model.ItemAssignment addItemAssignment(
		com.weprode.nero.progression.model.ItemAssignment itemAssignment) {

		return _itemAssignmentLocalService.addItemAssignment(itemAssignment);
	}

	@Override
	public com.weprode.nero.progression.model.ItemAssignment assignHomework(
			long itemId, long sessionId, long homeworkId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemAssignmentLocalService.assignHomework(
			itemId, sessionId, homeworkId);
	}

	@Override
	public com.weprode.nero.progression.model.ItemAssignment assignSession(
			long itemId, long sessionId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemAssignmentLocalService.assignSession(itemId, sessionId);
	}

	/**
	 * Creates a new item assignment with the primary key. Does not add the item assignment to the database.
	 *
	 * @param itemAssignmentPK the primary key for the new item assignment
	 * @return the new item assignment
	 */
	@Override
	public com.weprode.nero.progression.model.ItemAssignment
		createItemAssignment(
			com.weprode.nero.progression.service.persistence.ItemAssignmentPK
				itemAssignmentPK) {

		return _itemAssignmentLocalService.createItemAssignment(
			itemAssignmentPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAssignmentLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public void deleteItemAssigment(
			com.weprode.nero.progression.model.ItemAssignment itemAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {

		_itemAssignmentLocalService.deleteItemAssigment(itemAssignment);
	}

	/**
	 * Deletes the item assignment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAssignment the item assignment
	 * @return the item assignment that was removed
	 */
	@Override
	public com.weprode.nero.progression.model.ItemAssignment
		deleteItemAssignment(
			com.weprode.nero.progression.model.ItemAssignment itemAssignment) {

		return _itemAssignmentLocalService.deleteItemAssignment(itemAssignment);
	}

	/**
	 * Deletes the item assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment that was removed
	 * @throws PortalException if a item assignment with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.progression.model.ItemAssignment
			deleteItemAssignment(
				com.weprode.nero.progression.service.persistence.
					ItemAssignmentPK itemAssignmentPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAssignmentLocalService.deleteItemAssignment(
			itemAssignmentPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAssignmentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _itemAssignmentLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _itemAssignmentLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemAssignmentLocalService.dynamicQuery();
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

		return _itemAssignmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemAssignmentModelImpl</code>.
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

		return _itemAssignmentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemAssignmentModelImpl</code>.
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

		return _itemAssignmentLocalService.dynamicQuery(
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

		return _itemAssignmentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _itemAssignmentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.progression.model.ItemAssignment
		fetchItemAssignment(
			com.weprode.nero.progression.service.persistence.ItemAssignmentPK
				itemAssignmentPK) {

		return _itemAssignmentLocalService.fetchItemAssignment(
			itemAssignmentPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _itemAssignmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.weprode.nero.progression.model.ItemAssignment
			getByItemIdHomeworkId(long itemId, long homeworkId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemAssignmentLocalService.getByItemIdHomeworkId(
			itemId, homeworkId);
	}

	@Override
	public long getHomeworkAssignmentItemId(long homeworkId) {
		return _itemAssignmentLocalService.getHomeworkAssignmentItemId(
			homeworkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _itemAssignmentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the item assignment with the primary key.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment
	 * @throws PortalException if a item assignment with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.progression.model.ItemAssignment getItemAssignment(
			com.weprode.nero.progression.service.persistence.ItemAssignmentPK
				itemAssignmentPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAssignmentLocalService.getItemAssignment(itemAssignmentPK);
	}

	/**
	 * Returns a range of all the item assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of item assignments
	 */
	@Override
	public java.util.List<com.weprode.nero.progression.model.ItemAssignment>
		getItemAssignments(int start, int end) {

		return _itemAssignmentLocalService.getItemAssignments(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.progression.model.ItemAssignment>
			getItemAssignments(long itemId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemAssignmentLocalService.getItemAssignments(itemId);
	}

	/**
	 * Returns the number of item assignments.
	 *
	 * @return the number of item assignments
	 */
	@Override
	public int getItemAssignmentsCount() {
		return _itemAssignmentLocalService.getItemAssignmentsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _itemAssignmentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemAssignmentLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public long getSessionAssignmentItemId(long sessionId) {
		return _itemAssignmentLocalService.getSessionAssignmentItemId(
			sessionId);
	}

	@Override
	public boolean isSessionAffected(long sessionId) {
		return _itemAssignmentLocalService.isSessionAffected(sessionId);
	}

	@Override
	public com.weprode.nero.progression.model.ItemAssignment
			markAssigmentAsOverride(long itemId, long sessionId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemAssignmentLocalService.markAssigmentAsOverride(
			itemId, sessionId);
	}

	/**
	 * Updates the item assignment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemAssignment the item assignment
	 * @return the item assignment that was updated
	 */
	@Override
	public com.weprode.nero.progression.model.ItemAssignment
		updateItemAssignment(
			com.weprode.nero.progression.model.ItemAssignment itemAssignment) {

		return _itemAssignmentLocalService.updateItemAssignment(itemAssignment);
	}

	@Override
	public ItemAssignmentLocalService getWrappedService() {
		return _itemAssignmentLocalService;
	}

	@Override
	public void setWrappedService(
		ItemAssignmentLocalService itemAssignmentLocalService) {

		_itemAssignmentLocalService = itemAssignmentLocalService;
	}

	private ItemAssignmentLocalService _itemAssignmentLocalService;

}