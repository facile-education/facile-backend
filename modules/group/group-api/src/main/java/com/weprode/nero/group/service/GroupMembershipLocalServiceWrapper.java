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

package com.weprode.nero.group.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GroupMembershipLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupMembershipLocalService
 * @generated
 */
public class GroupMembershipLocalServiceWrapper
	implements GroupMembershipLocalService,
			   ServiceWrapper<GroupMembershipLocalService> {

	public GroupMembershipLocalServiceWrapper() {
		this(null);
	}

	public GroupMembershipLocalServiceWrapper(
		GroupMembershipLocalService groupMembershipLocalService) {

		_groupMembershipLocalService = groupMembershipLocalService;
	}

	/**
	 * Adds the group membership to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupMembershipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param groupMembership the group membership
	 * @return the group membership that was added
	 */
	@Override
	public com.weprode.nero.group.model.GroupMembership addGroupMembership(
		com.weprode.nero.group.model.GroupMembership groupMembership) {

		return _groupMembershipLocalService.addGroupMembership(groupMembership);
	}

	@Override
	public com.weprode.nero.group.model.GroupMembership addMembership(
		long userId, long groupId, java.util.Date startDate,
		java.util.Date endDate) {

		return _groupMembershipLocalService.addMembership(
			userId, groupId, startDate, endDate);
	}

	/**
	 * Creates a new group membership with the primary key. Does not add the group membership to the database.
	 *
	 * @param membershipId the primary key for the new group membership
	 * @return the new group membership
	 */
	@Override
	public com.weprode.nero.group.model.GroupMembership createGroupMembership(
		long membershipId) {

		return _groupMembershipLocalService.createGroupMembership(membershipId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupMembershipLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the group membership from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupMembershipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param groupMembership the group membership
	 * @return the group membership that was removed
	 */
	@Override
	public com.weprode.nero.group.model.GroupMembership deleteGroupMembership(
		com.weprode.nero.group.model.GroupMembership groupMembership) {

		return _groupMembershipLocalService.deleteGroupMembership(
			groupMembership);
	}

	/**
	 * Deletes the group membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupMembershipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership that was removed
	 * @throws PortalException if a group membership with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.group.model.GroupMembership deleteGroupMembership(
			long membershipId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupMembershipLocalService.deleteGroupMembership(membershipId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupMembershipLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _groupMembershipLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _groupMembershipLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _groupMembershipLocalService.dynamicQuery();
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

		return _groupMembershipLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.group.model.impl.GroupMembershipModelImpl</code>.
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

		return _groupMembershipLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.group.model.impl.GroupMembershipModelImpl</code>.
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

		return _groupMembershipLocalService.dynamicQuery(
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

		return _groupMembershipLocalService.dynamicQueryCount(dynamicQuery);
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

		return _groupMembershipLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.group.model.GroupMembership fetchGroupMembership(
		long membershipId) {

		return _groupMembershipLocalService.fetchGroupMembership(membershipId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _groupMembershipLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getGroupMembers(
		long groupId, java.util.Date date) {

		return _groupMembershipLocalService.getGroupMembers(groupId, date);
	}

	/**
	 * Returns the group membership with the primary key.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership
	 * @throws PortalException if a group membership with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.group.model.GroupMembership getGroupMembership(
			long membershipId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupMembershipLocalService.getGroupMembership(membershipId);
	}

	/**
	 * Returns a range of all the group memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.group.model.impl.GroupMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group memberships
	 * @param end the upper bound of the range of group memberships (not inclusive)
	 * @return the range of group memberships
	 */
	@Override
	public java.util.List<com.weprode.nero.group.model.GroupMembership>
		getGroupMemberships(int start, int end) {

		return _groupMembershipLocalService.getGroupMemberships(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.group.model.GroupMembership>
		getGroupMemberships(long userId, long groupId) {

		return _groupMembershipLocalService.getGroupMemberships(
			userId, groupId);
	}

	/**
	 * Returns the number of group memberships.
	 *
	 * @return the number of group memberships
	 */
	@Override
	public int getGroupMembershipsCount() {
		return _groupMembershipLocalService.getGroupMembershipsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _groupMembershipLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _groupMembershipLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _groupMembershipLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.weprode.nero.group.model.GroupMembership>
		getStudentGroupMemberships(long userId) {

		return _groupMembershipLocalService.getStudentGroupMemberships(userId);
	}

	@Override
	public boolean isStudentOrgMember(
		long userId, long groupId, java.util.Date date) {

		return _groupMembershipLocalService.isStudentOrgMember(
			userId, groupId, date);
	}

	@Override
	public boolean removeGroupMemberships(long groupId) {
		return _groupMembershipLocalService.removeGroupMemberships(groupId);
	}

	@Override
	public boolean removeUserMemberships(long userId) {
		return _groupMembershipLocalService.removeUserMemberships(userId);
	}

	/**
	 * Updates the group membership in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupMembershipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param groupMembership the group membership
	 * @return the group membership that was updated
	 */
	@Override
	public com.weprode.nero.group.model.GroupMembership updateGroupMembership(
		com.weprode.nero.group.model.GroupMembership groupMembership) {

		return _groupMembershipLocalService.updateGroupMembership(
			groupMembership);
	}

	@Override
	public GroupMembershipLocalService getWrappedService() {
		return _groupMembershipLocalService;
	}

	@Override
	public void setWrappedService(
		GroupMembershipLocalService groupMembershipLocalService) {

		_groupMembershipLocalService = groupMembershipLocalService;
	}

	private GroupMembershipLocalService _groupMembershipLocalService;

}