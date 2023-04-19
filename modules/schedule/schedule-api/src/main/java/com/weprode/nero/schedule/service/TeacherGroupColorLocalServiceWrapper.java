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
 * Provides a wrapper for {@link TeacherGroupColorLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TeacherGroupColorLocalService
 * @generated
 */
public class TeacherGroupColorLocalServiceWrapper
	implements ServiceWrapper<TeacherGroupColorLocalService>,
			   TeacherGroupColorLocalService {

	public TeacherGroupColorLocalServiceWrapper(
		TeacherGroupColorLocalService teacherGroupColorLocalService) {

		_teacherGroupColorLocalService = teacherGroupColorLocalService;
	}

	/**
	 * Adds the teacher group color to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherGroupColor the teacher group color
	 * @return the teacher group color that was added
	 */
	@Override
	public com.weprode.nero.schedule.model.TeacherGroupColor
		addTeacherGroupColor(
			com.weprode.nero.schedule.model.TeacherGroupColor
				teacherGroupColor) {

		return _teacherGroupColorLocalService.addTeacherGroupColor(
			teacherGroupColor);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherGroupColorLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new teacher group color with the primary key. Does not add the teacher group color to the database.
	 *
	 * @param teacherGroupColorId the primary key for the new teacher group color
	 * @return the new teacher group color
	 */
	@Override
	public com.weprode.nero.schedule.model.TeacherGroupColor
		createTeacherGroupColor(long teacherGroupColorId) {

		return _teacherGroupColorLocalService.createTeacherGroupColor(
			teacherGroupColorId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherGroupColorLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the teacher group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color that was removed
	 * @throws PortalException if a teacher group color with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.TeacherGroupColor
			deleteTeacherGroupColor(long teacherGroupColorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherGroupColorLocalService.deleteTeacherGroupColor(
			teacherGroupColorId);
	}

	/**
	 * Deletes the teacher group color from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherGroupColor the teacher group color
	 * @return the teacher group color that was removed
	 */
	@Override
	public com.weprode.nero.schedule.model.TeacherGroupColor
		deleteTeacherGroupColor(
			com.weprode.nero.schedule.model.TeacherGroupColor
				teacherGroupColor) {

		return _teacherGroupColorLocalService.deleteTeacherGroupColor(
			teacherGroupColor);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _teacherGroupColorLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _teacherGroupColorLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _teacherGroupColorLocalService.dynamicQuery();
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

		return _teacherGroupColorLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.TeacherGroupColorModelImpl</code>.
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

		return _teacherGroupColorLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.TeacherGroupColorModelImpl</code>.
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

		return _teacherGroupColorLocalService.dynamicQuery(
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

		return _teacherGroupColorLocalService.dynamicQueryCount(dynamicQuery);
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

		return _teacherGroupColorLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.TeacherGroupColor
		fetchTeacherGroupColor(long teacherGroupColorId) {

		return _teacherGroupColorLocalService.fetchTeacherGroupColor(
			teacherGroupColorId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _teacherGroupColorLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _teacherGroupColorLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _teacherGroupColorLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherGroupColorLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the teacher group color with the primary key.
	 *
	 * @param teacherGroupColorId the primary key of the teacher group color
	 * @return the teacher group color
	 * @throws PortalException if a teacher group color with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.TeacherGroupColor
			getTeacherGroupColor(long teacherGroupColorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherGroupColorLocalService.getTeacherGroupColor(
			teacherGroupColorId);
	}

	/**
	 * Returns the color for given teacherId and groupId
	 * Create it if it does not exist
	 * groupId can also be a schoollife type in case of schoollife sessions
	 */
	@Override
	public String getTeacherGroupColor(long teacherId, long groupId) {
		return _teacherGroupColorLocalService.getTeacherGroupColor(
			teacherId, groupId);
	}

	/**
	 * Returns a range of all the teacher group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.TeacherGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher group colors
	 * @param end the upper bound of the range of teacher group colors (not inclusive)
	 * @return the range of teacher group colors
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.TeacherGroupColor>
		getTeacherGroupColors(int start, int end) {

		return _teacherGroupColorLocalService.getTeacherGroupColors(start, end);
	}

	/**
	 * Returns the number of teacher group colors.
	 *
	 * @return the number of teacher group colors
	 */
	@Override
	public int getTeacherGroupColorsCount() {
		return _teacherGroupColorLocalService.getTeacherGroupColorsCount();
	}

	/**
	 * Updates the teacher group color in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherGroupColor the teacher group color
	 * @return the teacher group color that was updated
	 */
	@Override
	public com.weprode.nero.schedule.model.TeacherGroupColor
		updateTeacherGroupColor(
			com.weprode.nero.schedule.model.TeacherGroupColor
				teacherGroupColor) {

		return _teacherGroupColorLocalService.updateTeacherGroupColor(
			teacherGroupColor);
	}

	@Override
	public TeacherGroupColorLocalService getWrappedService() {
		return _teacherGroupColorLocalService;
	}

	@Override
	public void setWrappedService(
		TeacherGroupColorLocalService teacherGroupColorLocalService) {

		_teacherGroupColorLocalService = teacherGroupColorLocalService;
	}

	private TeacherGroupColorLocalService _teacherGroupColorLocalService;

}