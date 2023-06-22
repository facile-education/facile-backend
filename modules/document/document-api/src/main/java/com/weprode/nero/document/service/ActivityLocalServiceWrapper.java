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

package com.weprode.nero.document.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ActivityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityLocalService
 * @generated
 */
public class ActivityLocalServiceWrapper
	implements ActivityLocalService, ServiceWrapper<ActivityLocalService> {

	public ActivityLocalServiceWrapper(
		ActivityLocalService activityLocalService) {

		_activityLocalService = activityLocalService;
	}

	/**
	 * Adds the activity to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param activity the activity
	 * @return the activity that was added
	 */
	@Override
	public com.weprode.nero.document.model.Activity addActivity(
		com.weprode.nero.document.model.Activity activity) {

		return _activityLocalService.addActivity(activity);
	}

	@Override
	public com.weprode.nero.document.model.Activity addActivity(
		long fileEntryId, long folderId, long userId, long groupId,
		String fileName, String folderName, int type) {

		return _activityLocalService.addActivity(
			fileEntryId, folderId, userId, groupId, fileName, folderName, type);
	}

	@Override
	public org.json.JSONObject convertActivityToJson(
		com.weprode.nero.document.model.Activity activity) {

		return _activityLocalService.convertActivityToJson(activity);
	}

	/**
	 * Creates a new activity with the primary key. Does not add the activity to the database.
	 *
	 * @param activityId the primary key for the new activity
	 * @return the new activity
	 */
	@Override
	public com.weprode.nero.document.model.Activity createActivity(
		long activityId) {

		return _activityLocalService.createActivity(activityId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the activity from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param activity the activity
	 * @return the activity that was removed
	 */
	@Override
	public com.weprode.nero.document.model.Activity deleteActivity(
		com.weprode.nero.document.model.Activity activity) {

		return _activityLocalService.deleteActivity(activity);
	}

	/**
	 * Deletes the activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param activityId the primary key of the activity
	 * @return the activity that was removed
	 * @throws PortalException if a activity with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.document.model.Activity deleteActivity(
			long activityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityLocalService.deleteActivity(activityId);
	}

	@Override
	public void deleteFileActivity(long fileEntryId) {
		_activityLocalService.deleteFileActivity(fileEntryId);
	}

	@Override
	public void deleteFolderActivity(long folderId) {
		_activityLocalService.deleteFolderActivity(folderId);
	}

	@Override
	public boolean deleteGroupActivity(long groupId) {
		return _activityLocalService.deleteGroupActivity(groupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _activityLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _activityLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _activityLocalService.dynamicQuery();
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

		return _activityLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.document.model.impl.ActivityModelImpl</code>.
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

		return _activityLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.document.model.impl.ActivityModelImpl</code>.
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

		return _activityLocalService.dynamicQuery(
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

		return _activityLocalService.dynamicQueryCount(dynamicQuery);
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

		return _activityLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.document.model.Activity fetchActivity(
		long activityId) {

		return _activityLocalService.fetchActivity(activityId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _activityLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.document.model.impl.ActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of activities
	 * @param end the upper bound of the range of activities (not inclusive)
	 * @return the range of activities
	 */
	@Override
	public java.util.List<com.weprode.nero.document.model.Activity>
		getActivities(int start, int end) {

		return _activityLocalService.getActivities(start, end);
	}

	/**
	 * Returns the number of activities.
	 *
	 * @return the number of activities
	 */
	@Override
	public int getActivitiesCount() {
		return _activityLocalService.getActivitiesCount();
	}

	@Override
	public java.util.List<com.weprode.nero.document.model.Activity> getActivity(
		java.util.List<Long> groupIdList, long creatorId, int start, int end) {

		return _activityLocalService.getActivity(
			groupIdList, creatorId, start, end);
	}

	/**
	 * Returns the activity with the primary key.
	 *
	 * @param activityId the primary key of the activity
	 * @return the activity
	 * @throws PortalException if a activity with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.document.model.Activity getActivity(long activityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityLocalService.getActivity(activityId);
	}

	@Override
	public java.util.List<com.weprode.nero.document.model.Activity>
		getGroupsActivity(
			long userId, java.util.List<Long> groupIdList,
			java.util.Date minDate, java.util.Date maxDate,
			boolean includeUserActivity, boolean withFileCreation,
			boolean withFileModification, boolean withFolderCreation,
			boolean withFolderModification) {

		return _activityLocalService.getGroupsActivity(
			userId, groupIdList, minDate, maxDate, includeUserActivity,
			withFileCreation, withFileModification, withFolderCreation,
			withFolderModification);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _activityLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _activityLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param activity the activity
	 * @return the activity that was updated
	 */
	@Override
	public com.weprode.nero.document.model.Activity updateActivity(
		com.weprode.nero.document.model.Activity activity) {

		return _activityLocalService.updateActivity(activity);
	}

	@Override
	public ActivityLocalService getWrappedService() {
		return _activityLocalService;
	}

	@Override
	public void setWrappedService(ActivityLocalService activityLocalService) {
		_activityLocalService = activityLocalService;
	}

	private ActivityLocalService _activityLocalService;

}