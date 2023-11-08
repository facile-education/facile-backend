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

package com.weprode.facile.group.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.group.model.MembershipActivity;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for MembershipActivity. This utility wraps
 * <code>com.weprode.facile.group.service.impl.MembershipActivityLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MembershipActivityLocalService
 * @generated
 */
public class MembershipActivityLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.group.service.impl.MembershipActivityLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static MembershipActivity addMembershipActivity(
		long groupId, long actionUserId, List<Long> targetUserIds,
		boolean incoming) {

		return getService().addMembershipActivity(
			groupId, actionUserId, targetUserIds, incoming);
	}

	/**
	 * Adds the membership activity to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MembershipActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param membershipActivity the membership activity
	 * @return the membership activity that was added
	 */
	public static MembershipActivity addMembershipActivity(
		MembershipActivity membershipActivity) {

		return getService().addMembershipActivity(membershipActivity);
	}

	public static org.json.JSONObject convertMembershipActivityToJson(
		MembershipActivity membershipActivity) {

		return getService().convertMembershipActivityToJson(membershipActivity);
	}

	/**
	 * Creates a new membership activity with the primary key. Does not add the membership activity to the database.
	 *
	 * @param membershipActivityId the primary key for the new membership activity
	 * @return the new membership activity
	 */
	public static MembershipActivity createMembershipActivity(
		long membershipActivityId) {

		return getService().createMembershipActivity(membershipActivityId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static boolean deleteGroupActivity(long groupId) {
		return getService().deleteGroupActivity(groupId);
	}

	/**
	 * Deletes the membership activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MembershipActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity that was removed
	 * @throws PortalException if a membership activity with the primary key could not be found
	 */
	public static MembershipActivity deleteMembershipActivity(
			long membershipActivityId)
		throws PortalException {

		return getService().deleteMembershipActivity(membershipActivityId);
	}

	/**
	 * Deletes the membership activity from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MembershipActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param membershipActivity the membership activity
	 * @return the membership activity that was removed
	 */
	public static MembershipActivity deleteMembershipActivity(
		MembershipActivity membershipActivity) {

		return getService().deleteMembershipActivity(membershipActivity);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.group.model.impl.MembershipActivityModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.group.model.impl.MembershipActivityModelImpl</code>.
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

	public static MembershipActivity fetchMembershipActivity(
		long membershipActivityId) {

		return getService().fetchMembershipActivity(membershipActivityId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the membership activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.group.model.impl.MembershipActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership activities
	 * @param end the upper bound of the range of membership activities (not inclusive)
	 * @return the range of membership activities
	 */
	public static List<MembershipActivity> getMembershipActivities(
		int start, int end) {

		return getService().getMembershipActivities(start, end);
	}

	/**
	 * Returns the number of membership activities.
	 *
	 * @return the number of membership activities
	 */
	public static int getMembershipActivitiesCount() {
		return getService().getMembershipActivitiesCount();
	}

	/**
	 * Returns the membership activity with the primary key.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity
	 * @throws PortalException if a membership activity with the primary key could not be found
	 */
	public static MembershipActivity getMembershipActivity(
			long membershipActivityId)
		throws PortalException {

		return getService().getMembershipActivity(membershipActivityId);
	}

	public static List<MembershipActivity> getMembershipActivity(
		long userId, List<Long> groupIdList, java.util.Date minDate,
		java.util.Date maxDate, boolean includeUserActions,
		boolean onlyWithUserBeingTarget, boolean withAdd,
		boolean withRemovals) {

		return getService().getMembershipActivity(
			userId, groupIdList, minDate, maxDate, includeUserActions,
			onlyWithUserBeingTarget, withAdd, withRemovals);
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

	/**
	 * Updates the membership activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MembershipActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param membershipActivity the membership activity
	 * @return the membership activity that was updated
	 */
	public static MembershipActivity updateMembershipActivity(
		MembershipActivity membershipActivity) {

		return getService().updateMembershipActivity(membershipActivity);
	}

	public static MembershipActivityLocalService getService() {
		return _service;
	}

	private static volatile MembershipActivityLocalService _service;

}