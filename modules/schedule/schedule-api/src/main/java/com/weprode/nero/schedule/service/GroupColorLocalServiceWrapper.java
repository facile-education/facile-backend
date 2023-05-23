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

package com.weprode.nero.schedule.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GroupColorLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupColorLocalService
 * @generated
 */
public class GroupColorLocalServiceWrapper
	implements GroupColorLocalService, ServiceWrapper<GroupColorLocalService> {

	public GroupColorLocalServiceWrapper(
		GroupColorLocalService groupColorLocalService) {

		_groupColorLocalService = groupColorLocalService;
	}

	/**
	 * Adds the group color to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param groupColor the group color
	 * @return the group color that was added
	 */
	@Override
	public com.weprode.nero.schedule.model.GroupColor addGroupColor(
		com.weprode.nero.schedule.model.GroupColor groupColor) {

		return _groupColorLocalService.addGroupColor(groupColor);
	}

	/**
	 * Creates a new group color with the primary key. Does not add the group color to the database.
	 *
	 * @param groupId the primary key for the new group color
	 * @return the new group color
	 */
	@Override
	public com.weprode.nero.schedule.model.GroupColor createGroupColor(
		long groupId) {

		return _groupColorLocalService.createGroupColor(groupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupColorLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the group color from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param groupColor the group color
	 * @return the group color that was removed
	 */
	@Override
	public com.weprode.nero.schedule.model.GroupColor deleteGroupColor(
		com.weprode.nero.schedule.model.GroupColor groupColor) {

		return _groupColorLocalService.deleteGroupColor(groupColor);
	}

	/**
	 * Deletes the group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color that was removed
	 * @throws PortalException if a group color with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.GroupColor deleteGroupColor(
			long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupColorLocalService.deleteGroupColor(groupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupColorLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _groupColorLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _groupColorLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _groupColorLocalService.dynamicQuery();
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

		return _groupColorLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.GroupColorModelImpl</code>.
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

		return _groupColorLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.GroupColorModelImpl</code>.
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

		return _groupColorLocalService.dynamicQuery(
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

		return _groupColorLocalService.dynamicQueryCount(dynamicQuery);
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

		return _groupColorLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.GroupColor fetchGroupColor(
		long groupId) {

		return _groupColorLocalService.fetchGroupColor(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _groupColorLocalService.getActionableDynamicQuery();
	}

	@Override
	public String getColor(long groupId) {
		return _groupColorLocalService.getColor(groupId);
	}

	/**
	 * Returns the group color with the primary key.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color
	 * @throws PortalException if a group color with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.GroupColor getGroupColor(
			long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupColorLocalService.getGroupColor(groupId);
	}

	/**
	 * Returns a range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @return the range of group colors
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.GroupColor>
		getGroupColors(int start, int end) {

		return _groupColorLocalService.getGroupColors(start, end);
	}

	/**
	 * Returns the number of group colors.
	 *
	 * @return the number of group colors
	 */
	@Override
	public int getGroupColorsCount() {
		return _groupColorLocalService.getGroupColorsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _groupColorLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _groupColorLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupColorLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the group color in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param groupColor the group color
	 * @return the group color that was updated
	 */
	@Override
	public com.weprode.nero.schedule.model.GroupColor updateGroupColor(
		com.weprode.nero.schedule.model.GroupColor groupColor) {

		return _groupColorLocalService.updateGroupColor(groupColor);
	}

	@Override
	public GroupColorLocalService getWrappedService() {
		return _groupColorLocalService;
	}

	@Override
	public void setWrappedService(
		GroupColorLocalService groupColorLocalService) {

		_groupColorLocalService = groupColorLocalService;
	}

	private GroupColorLocalService _groupColorLocalService;

}