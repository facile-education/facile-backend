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
 * Provides a wrapper for {@link ProgressionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionLocalService
 * @generated
 */
public class ProgressionLocalServiceWrapper
	implements ProgressionLocalService,
			   ServiceWrapper<ProgressionLocalService> {

	public ProgressionLocalServiceWrapper(
		ProgressionLocalService progressionLocalService) {

		_progressionLocalService = progressionLocalService;
	}

	@Override
	public com.weprode.nero.progression.model.Progression addProgression(
			long teacherId, String name, String description, long subjectId,
			String volee, String color)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _progressionLocalService.addProgression(
			teacherId, name, description, subjectId, volee, color);
	}

	/**
	 * Adds the progression to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progression the progression
	 * @return the progression that was added
	 */
	@Override
	public com.weprode.nero.progression.model.Progression addProgression(
		com.weprode.nero.progression.model.Progression progression) {

		return _progressionLocalService.addProgression(progression);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new progression with the primary key. Does not add the progression to the database.
	 *
	 * @param progressionId the primary key for the new progression
	 * @return the new progression
	 */
	@Override
	public com.weprode.nero.progression.model.Progression createProgression(
		long progressionId) {

		return _progressionLocalService.createProgression(progressionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the progression with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression that was removed
	 * @throws PortalException if a progression with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.progression.model.Progression deleteProgression(
			long progressionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionLocalService.deleteProgression(progressionId);
	}

	/**
	 * Deletes the progression from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progression the progression
	 * @return the progression that was removed
	 */
	@Override
	public com.weprode.nero.progression.model.Progression deleteProgression(
		com.weprode.nero.progression.model.Progression progression) {

		return _progressionLocalService.deleteProgression(progression);
	}

	@Override
	public void deleteProgressionById(long userId, long progressionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_progressionLocalService.deleteProgressionById(userId, progressionId);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _progressionLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _progressionLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _progressionLocalService.dynamicQuery();
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

		return _progressionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ProgressionModelImpl</code>.
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

		return _progressionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ProgressionModelImpl</code>.
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

		return _progressionLocalService.dynamicQuery(
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

		return _progressionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _progressionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.progression.model.Progression fetchProgression(
		long progressionId) {

		return _progressionLocalService.fetchProgression(progressionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _progressionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _progressionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _progressionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the progression with the primary key.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression
	 * @throws PortalException if a progression with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.progression.model.Progression getProgression(
			long progressionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _progressionLocalService.getProgression(progressionId);
	}

	/**
	 * Returns a range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.progression.model.impl.ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @return the range of progressions
	 */
	@Override
	public java.util.List<com.weprode.nero.progression.model.Progression>
		getProgressions(int start, int end) {

		return _progressionLocalService.getProgressions(start, end);
	}

	/**
	 * Returns the number of progressions.
	 *
	 * @return the number of progressions
	 */
	@Override
	public int getProgressionsCount() {
		return _progressionLocalService.getProgressionsCount();
	}

	@Override
	public java.util.List<com.weprode.nero.progression.model.Progression>
			getTeacherProgressions(long teacherId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _progressionLocalService.getTeacherProgressions(teacherId);
	}

	@Override
	public com.weprode.nero.progression.model.Progression updateProgression(
			long progressionId, String name, String description, long subjectId,
			String volee, String color)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _progressionLocalService.updateProgression(
			progressionId, name, description, subjectId, volee, color);
	}

	/**
	 * Updates the progression in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProgressionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param progression the progression
	 * @return the progression that was updated
	 */
	@Override
	public com.weprode.nero.progression.model.Progression updateProgression(
		com.weprode.nero.progression.model.Progression progression) {

		return _progressionLocalService.updateProgression(progression);
	}

	@Override
	public void updateProgressionModifiedDate(long progressionId) {
		_progressionLocalService.updateProgressionModifiedDate(progressionId);
	}

	@Override
	public ProgressionLocalService getWrappedService() {
		return _progressionLocalService;
	}

	@Override
	public void setWrappedService(
		ProgressionLocalService progressionLocalService) {

		_progressionLocalService = progressionLocalService;
	}

	private ProgressionLocalService _progressionLocalService;

}