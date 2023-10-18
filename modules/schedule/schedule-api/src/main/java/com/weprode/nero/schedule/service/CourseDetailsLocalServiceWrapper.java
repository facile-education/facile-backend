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
 * Provides a wrapper for {@link CourseDetailsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CourseDetailsLocalService
 * @generated
 */
public class CourseDetailsLocalServiceWrapper
	implements CourseDetailsLocalService,
			   ServiceWrapper<CourseDetailsLocalService> {

	public CourseDetailsLocalServiceWrapper() {
		this(null);
	}

	public CourseDetailsLocalServiceWrapper(
		CourseDetailsLocalService courseDetailsLocalService) {

		_courseDetailsLocalService = courseDetailsLocalService;
	}

	/**
	 * Adds the course details to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CourseDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param courseDetails the course details
	 * @return the course details that was added
	 */
	@Override
	public com.weprode.nero.schedule.model.CourseDetails addCourseDetails(
		com.weprode.nero.schedule.model.CourseDetails courseDetails) {

		return _courseDetailsLocalService.addCourseDetails(courseDetails);
	}

	/**
	 * Creates a new course details with the primary key. Does not add the course details to the database.
	 *
	 * @param courseGroupId the primary key for the new course details
	 * @return the new course details
	 */
	@Override
	public com.weprode.nero.schedule.model.CourseDetails createCourseDetails(
		long courseGroupId) {

		return _courseDetailsLocalService.createCourseDetails(courseGroupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseDetailsLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the course details from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CourseDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param courseDetails the course details
	 * @return the course details that was removed
	 */
	@Override
	public com.weprode.nero.schedule.model.CourseDetails deleteCourseDetails(
		com.weprode.nero.schedule.model.CourseDetails courseDetails) {

		return _courseDetailsLocalService.deleteCourseDetails(courseDetails);
	}

	/**
	 * Deletes the course details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CourseDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details that was removed
	 * @throws PortalException if a course details with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.CourseDetails deleteCourseDetails(
			long courseGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseDetailsLocalService.deleteCourseDetails(courseGroupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _courseDetailsLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _courseDetailsLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _courseDetailsLocalService.dynamicQuery();
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

		return _courseDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.CourseDetailsModelImpl</code>.
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

		return _courseDetailsLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.CourseDetailsModelImpl</code>.
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

		return _courseDetailsLocalService.dynamicQuery(
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

		return _courseDetailsLocalService.dynamicQueryCount(dynamicQuery);
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

		return _courseDetailsLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.CourseDetails fetchCourseDetails(
		long courseGroupId) {

		return _courseDetailsLocalService.fetchCourseDetails(courseGroupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _courseDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public String getCourseColor(long groupId) {
		return _courseDetailsLocalService.getCourseColor(groupId);
	}

	/**
	 * Returns the course details with the primary key.
	 *
	 * @param courseGroupId the primary key of the course details
	 * @return the course details
	 * @throws PortalException if a course details with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.CourseDetails getCourseDetails(
			long courseGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseDetailsLocalService.getCourseDetails(courseGroupId);
	}

	/**
	 * Returns a range of all the course detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.CourseDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course detailses
	 * @param end the upper bound of the range of course detailses (not inclusive)
	 * @return the range of course detailses
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.CourseDetails>
		getCourseDetailses(int start, int end) {

		return _courseDetailsLocalService.getCourseDetailses(start, end);
	}

	/**
	 * Returns the number of course detailses.
	 *
	 * @return the number of course detailses
	 */
	@Override
	public int getCourseDetailsesCount() {
		return _courseDetailsLocalService.getCourseDetailsesCount();
	}

	@Override
	public String getCourseSubject(long groupId) {
		return _courseDetailsLocalService.getCourseSubject(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _courseDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _courseDetailsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void setCourseSubject(long groupId, long subjectId) {
		_courseDetailsLocalService.setCourseSubject(groupId, subjectId);
	}

	/**
	 * Updates the course details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CourseDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param courseDetails the course details
	 * @return the course details that was updated
	 */
	@Override
	public com.weprode.nero.schedule.model.CourseDetails updateCourseDetails(
		com.weprode.nero.schedule.model.CourseDetails courseDetails) {

		return _courseDetailsLocalService.updateCourseDetails(courseDetails);
	}

	@Override
	public CourseDetailsLocalService getWrappedService() {
		return _courseDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		CourseDetailsLocalService courseDetailsLocalService) {

		_courseDetailsLocalService = courseDetailsLocalService;
	}

	private CourseDetailsLocalService _courseDetailsLocalService;

}