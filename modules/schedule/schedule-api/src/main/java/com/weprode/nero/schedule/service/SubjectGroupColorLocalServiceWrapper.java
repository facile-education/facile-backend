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
 * Provides a wrapper for {@link SubjectGroupColorLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SubjectGroupColorLocalService
 * @generated
 */
public class SubjectGroupColorLocalServiceWrapper
	implements ServiceWrapper<SubjectGroupColorLocalService>,
			   SubjectGroupColorLocalService {

	public SubjectGroupColorLocalServiceWrapper(
		SubjectGroupColorLocalService subjectGroupColorLocalService) {

		_subjectGroupColorLocalService = subjectGroupColorLocalService;
	}

	/**
	 * Adds the subject group color to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubjectGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subjectGroupColor the subject group color
	 * @return the subject group color that was added
	 */
	@Override
	public com.weprode.nero.schedule.model.SubjectGroupColor
		addSubjectGroupColor(
			com.weprode.nero.schedule.model.SubjectGroupColor
				subjectGroupColor) {

		return _subjectGroupColorLocalService.addSubjectGroupColor(
			subjectGroupColor);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectGroupColorLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new subject group color with the primary key. Does not add the subject group color to the database.
	 *
	 * @param subjectGroupColorId the primary key for the new subject group color
	 * @return the new subject group color
	 */
	@Override
	public com.weprode.nero.schedule.model.SubjectGroupColor
		createSubjectGroupColor(long subjectGroupColorId) {

		return _subjectGroupColorLocalService.createSubjectGroupColor(
			subjectGroupColorId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectGroupColorLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the subject group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubjectGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color that was removed
	 * @throws PortalException if a subject group color with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.SubjectGroupColor
			deleteSubjectGroupColor(long subjectGroupColorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectGroupColorLocalService.deleteSubjectGroupColor(
			subjectGroupColorId);
	}

	/**
	 * Deletes the subject group color from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubjectGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subjectGroupColor the subject group color
	 * @return the subject group color that was removed
	 */
	@Override
	public com.weprode.nero.schedule.model.SubjectGroupColor
		deleteSubjectGroupColor(
			com.weprode.nero.schedule.model.SubjectGroupColor
				subjectGroupColor) {

		return _subjectGroupColorLocalService.deleteSubjectGroupColor(
			subjectGroupColor);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _subjectGroupColorLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _subjectGroupColorLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _subjectGroupColorLocalService.dynamicQuery();
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

		return _subjectGroupColorLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SubjectGroupColorModelImpl</code>.
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

		return _subjectGroupColorLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SubjectGroupColorModelImpl</code>.
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

		return _subjectGroupColorLocalService.dynamicQuery(
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

		return _subjectGroupColorLocalService.dynamicQueryCount(dynamicQuery);
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

		return _subjectGroupColorLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.SubjectGroupColor
		fetchSubjectGroupColor(long subjectGroupColorId) {

		return _subjectGroupColorLocalService.fetchSubjectGroupColor(
			subjectGroupColorId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _subjectGroupColorLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _subjectGroupColorLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _subjectGroupColorLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectGroupColorLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the subject group color with the primary key.
	 *
	 * @param subjectGroupColorId the primary key of the subject group color
	 * @return the subject group color
	 * @throws PortalException if a subject group color with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.SubjectGroupColor
			getSubjectGroupColor(long subjectGroupColorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectGroupColorLocalService.getSubjectGroupColor(
			subjectGroupColorId);
	}

	/**
	 * Returns the color for given teacherId and groupId
	 * Create it if it does not exist
	 */
	@Override
	public String getSubjectGroupColor(long groupId, String subject) {
		return _subjectGroupColorLocalService.getSubjectGroupColor(
			groupId, subject);
	}

	/**
	 * Returns a range of all the subject group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SubjectGroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject group colors
	 * @param end the upper bound of the range of subject group colors (not inclusive)
	 * @return the range of subject group colors
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.SubjectGroupColor>
		getSubjectGroupColors(int start, int end) {

		return _subjectGroupColorLocalService.getSubjectGroupColors(start, end);
	}

	/**
	 * Returns the number of subject group colors.
	 *
	 * @return the number of subject group colors
	 */
	@Override
	public int getSubjectGroupColorsCount() {
		return _subjectGroupColorLocalService.getSubjectGroupColorsCount();
	}

	/**
	 * Updates the subject group color in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubjectGroupColorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subjectGroupColor the subject group color
	 * @return the subject group color that was updated
	 */
	@Override
	public com.weprode.nero.schedule.model.SubjectGroupColor
		updateSubjectGroupColor(
			com.weprode.nero.schedule.model.SubjectGroupColor
				subjectGroupColor) {

		return _subjectGroupColorLocalService.updateSubjectGroupColor(
			subjectGroupColor);
	}

	@Override
	public SubjectGroupColorLocalService getWrappedService() {
		return _subjectGroupColorLocalService;
	}

	@Override
	public void setWrappedService(
		SubjectGroupColorLocalService subjectGroupColorLocalService) {

		_subjectGroupColorLocalService = subjectGroupColorLocalService;
	}

	private SubjectGroupColorLocalService _subjectGroupColorLocalService;

}