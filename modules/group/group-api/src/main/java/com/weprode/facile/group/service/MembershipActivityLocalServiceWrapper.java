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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MembershipActivityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MembershipActivityLocalService
 * @generated
 */
public class MembershipActivityLocalServiceWrapper
	implements MembershipActivityLocalService,
			   ServiceWrapper<MembershipActivityLocalService> {

	public MembershipActivityLocalServiceWrapper() {
		this(null);
	}

	public MembershipActivityLocalServiceWrapper(
		MembershipActivityLocalService membershipActivityLocalService) {

		_membershipActivityLocalService = membershipActivityLocalService;
	}

	@Override
	public com.weprode.facile.group.model.MembershipActivity
		addMembershipActivity(
			long groupId, long actionUserId, java.util.List<Long> targetUserIds,
			boolean incoming) {

		return _membershipActivityLocalService.addMembershipActivity(
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
	@Override
	public com.weprode.facile.group.model.MembershipActivity
		addMembershipActivity(
			com.weprode.facile.group.model.MembershipActivity
				membershipActivity) {

		return _membershipActivityLocalService.addMembershipActivity(
			membershipActivity);
	}

	@Override
	public org.json.JSONObject convertMembershipActivityToJson(
		com.weprode.facile.group.model.MembershipActivity membershipActivity,
		long userId) {

		return _membershipActivityLocalService.convertMembershipActivityToJson(
			membershipActivity, userId);
	}

	/**
	 * Creates a new membership activity with the primary key. Does not add the membership activity to the database.
	 *
	 * @param membershipActivityId the primary key for the new membership activity
	 * @return the new membership activity
	 */
	@Override
	public com.weprode.facile.group.model.MembershipActivity
		createMembershipActivity(long membershipActivityId) {

		return _membershipActivityLocalService.createMembershipActivity(
			membershipActivityId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _membershipActivityLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public boolean deleteGroupActivity(long groupId) {
		return _membershipActivityLocalService.deleteGroupActivity(groupId);
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
	@Override
	public com.weprode.facile.group.model.MembershipActivity
			deleteMembershipActivity(long membershipActivityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _membershipActivityLocalService.deleteMembershipActivity(
			membershipActivityId);
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
	@Override
	public com.weprode.facile.group.model.MembershipActivity
		deleteMembershipActivity(
			com.weprode.facile.group.model.MembershipActivity
				membershipActivity) {

		return _membershipActivityLocalService.deleteMembershipActivity(
			membershipActivity);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _membershipActivityLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _membershipActivityLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _membershipActivityLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _membershipActivityLocalService.dynamicQuery();
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

		return _membershipActivityLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _membershipActivityLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _membershipActivityLocalService.dynamicQuery(
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

		return _membershipActivityLocalService.dynamicQueryCount(dynamicQuery);
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

		return _membershipActivityLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.group.model.MembershipActivity
		fetchMembershipActivity(long membershipActivityId) {

		return _membershipActivityLocalService.fetchMembershipActivity(
			membershipActivityId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _membershipActivityLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _membershipActivityLocalService.
			getIndexableActionableDynamicQuery();
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
	@Override
	public java.util.List<com.weprode.facile.group.model.MembershipActivity>
		getMembershipActivities(int start, int end) {

		return _membershipActivityLocalService.getMembershipActivities(
			start, end);
	}

	/**
	 * Returns the number of membership activities.
	 *
	 * @return the number of membership activities
	 */
	@Override
	public int getMembershipActivitiesCount() {
		return _membershipActivityLocalService.getMembershipActivitiesCount();
	}

	/**
	 * Returns the membership activity with the primary key.
	 *
	 * @param membershipActivityId the primary key of the membership activity
	 * @return the membership activity
	 * @throws PortalException if a membership activity with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.group.model.MembershipActivity
			getMembershipActivity(long membershipActivityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _membershipActivityLocalService.getMembershipActivity(
			membershipActivityId);
	}

	@Override
	public java.util.List<com.weprode.facile.group.model.MembershipActivity>
		getMembershipActivity(
			long userId, java.util.List<Long> groupIdList,
			java.util.Date minDate, java.util.Date maxDate,
			boolean includeUserActions, boolean onlyWithUserBeingTarget,
			boolean withAdd, boolean withRemovals) {

		return _membershipActivityLocalService.getMembershipActivity(
			userId, groupIdList, minDate, maxDate, includeUserActions,
			onlyWithUserBeingTarget, withAdd, withRemovals);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _membershipActivityLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _membershipActivityLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.weprode.facile.group.model.MembershipActivity
		updateMembershipActivity(
			com.weprode.facile.group.model.MembershipActivity
				membershipActivity) {

		return _membershipActivityLocalService.updateMembershipActivity(
			membershipActivity);
	}

	@Override
	public MembershipActivityLocalService getWrappedService() {
		return _membershipActivityLocalService;
	}

	@Override
	public void setWrappedService(
		MembershipActivityLocalService membershipActivityLocalService) {

		_membershipActivityLocalService = membershipActivityLocalService;
	}

	private MembershipActivityLocalService _membershipActivityLocalService;

}