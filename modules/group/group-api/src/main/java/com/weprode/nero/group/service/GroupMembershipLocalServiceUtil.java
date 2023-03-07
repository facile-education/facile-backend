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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.group.model.GroupMembership;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for GroupMembership. This utility wraps
 * <code>com.weprode.nero.group.service.impl.GroupMembershipLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see GroupMembershipLocalService
 * @generated
 */
public class GroupMembershipLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.group.service.impl.GroupMembershipLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static GroupMembership addGroupMembership(
		GroupMembership groupMembership) {

		return getService().addGroupMembership(groupMembership);
	}

	public static GroupMembership addMembership(
		long userId, long groupId, java.util.Date startDate,
		java.util.Date endDate) {

		return getService().addMembership(userId, groupId, startDate, endDate);
	}

	/**
	 * Creates a new group membership with the primary key. Does not add the group membership to the database.
	 *
	 * @param membershipId the primary key for the new group membership
	 * @return the new group membership
	 */
	public static GroupMembership createGroupMembership(long membershipId) {
		return getService().createGroupMembership(membershipId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static GroupMembership deleteGroupMembership(
		GroupMembership groupMembership) {

		return getService().deleteGroupMembership(groupMembership);
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
	public static GroupMembership deleteGroupMembership(long membershipId)
		throws PortalException {

		return getService().deleteGroupMembership(membershipId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.group.model.impl.GroupMembershipModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.group.model.impl.GroupMembershipModelImpl</code>.
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

	public static GroupMembership fetchGroupMembership(long membershipId) {
		return getService().fetchGroupMembership(membershipId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<com.liferay.portal.kernel.model.User> getGroupMembers(
		long groupId, java.util.Date date) {

		return getService().getGroupMembers(groupId, date);
	}

	/**
	 * Returns the group membership with the primary key.
	 *
	 * @param membershipId the primary key of the group membership
	 * @return the group membership
	 * @throws PortalException if a group membership with the primary key could not be found
	 */
	public static GroupMembership getGroupMembership(long membershipId)
		throws PortalException {

		return getService().getGroupMembership(membershipId);
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
	public static List<GroupMembership> getGroupMemberships(
		int start, int end) {

		return getService().getGroupMemberships(start, end);
	}

	public static List<GroupMembership> getGroupMemberships(
		long userId, long groupId) {

		return getService().getGroupMemberships(userId, groupId);
	}

	/**
	 * Returns the number of group memberships.
	 *
	 * @return the number of group memberships
	 */
	public static int getGroupMembershipsCount() {
		return getService().getGroupMembershipsCount();
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

	public static List<GroupMembership> getStudentGroupMemberships(
		long userId) {

		return getService().getStudentGroupMemberships(userId);
	}

	public static boolean isStudentOrgMember(
		long userId, long groupId, java.util.Date date) {

		return getService().isStudentOrgMember(userId, groupId, date);
	}

	public static boolean removeGroupMemberships(long groupId) {
		return getService().removeGroupMemberships(groupId);
	}

	public static boolean removeUserMemberships(long userId) {
		return getService().removeUserMemberships(userId);
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
	public static GroupMembership updateGroupMembership(
		GroupMembership groupMembership) {

		return getService().updateGroupMembership(groupMembership);
	}

	public static GroupMembershipLocalService getService() {
		return _service;
	}

	private static volatile GroupMembershipLocalService _service;

}