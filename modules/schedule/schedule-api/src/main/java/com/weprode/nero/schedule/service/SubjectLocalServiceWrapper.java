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
 * Provides a wrapper for {@link SubjectLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SubjectLocalService
 * @generated
 */
public class SubjectLocalServiceWrapper
	implements ServiceWrapper<SubjectLocalService>, SubjectLocalService {

	public SubjectLocalServiceWrapper(SubjectLocalService subjectLocalService) {
		_subjectLocalService = subjectLocalService;
	}

	/**
	 * Adds the subject to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subject the subject
	 * @return the subject that was added
	 */
	@Override
	public com.weprode.nero.schedule.model.Subject addSubject(
		com.weprode.nero.schedule.model.Subject subject) {

		return _subjectLocalService.addSubject(subject);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new subject with the primary key. Does not add the subject to the database.
	 *
	 * @param subjectId the primary key for the new subject
	 * @return the new subject
	 */
	@Override
	public com.weprode.nero.schedule.model.Subject createSubject(
		long subjectId) {

		return _subjectLocalService.createSubject(subjectId);
	}

	@Override
	public com.weprode.nero.schedule.model.Subject createSubject(String name) {
		return _subjectLocalService.createSubject(name);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the subject with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subjectId the primary key of the subject
	 * @return the subject that was removed
	 * @throws PortalException if a subject with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.Subject deleteSubject(long subjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectLocalService.deleteSubject(subjectId);
	}

	/**
	 * Deletes the subject from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subject the subject
	 * @return the subject that was removed
	 */
	@Override
	public com.weprode.nero.schedule.model.Subject deleteSubject(
		com.weprode.nero.schedule.model.Subject subject) {

		return _subjectLocalService.deleteSubject(subject);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _subjectLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _subjectLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _subjectLocalService.dynamicQuery();
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

		return _subjectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SubjectModelImpl</code>.
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

		return _subjectLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SubjectModelImpl</code>.
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

		return _subjectLocalService.dynamicQuery(
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

		return _subjectLocalService.dynamicQueryCount(dynamicQuery);
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

		return _subjectLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.Subject fetchSubject(
		long subjectId) {

		return _subjectLocalService.fetchSubject(subjectId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _subjectLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _subjectLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.weprode.nero.schedule.model.Subject getOrCreateSubject(
		String name) {

		return _subjectLocalService.getOrCreateSubject(name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _subjectLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the subject with the primary key.
	 *
	 * @param subjectId the primary key of the subject
	 * @return the subject
	 * @throws PortalException if a subject with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.Subject getSubject(long subjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subjectLocalService.getSubject(subjectId);
	}

	@Override
	public com.weprode.nero.schedule.model.Subject getSubjectByName(
		String name) {

		return _subjectLocalService.getSubjectByName(name);
	}

	/**
	 * Returns a range of all the subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subjects
	 * @param end the upper bound of the range of subjects (not inclusive)
	 * @return the range of subjects
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.Subject> getSubjects(
		int start, int end) {

		return _subjectLocalService.getSubjects(start, end);
	}

	/**
	 * Returns the number of subjects.
	 *
	 * @return the number of subjects
	 */
	@Override
	public int getSubjectsCount() {
		return _subjectLocalService.getSubjectsCount();
	}

	@Override
	public org.json.JSONObject getSubjectsJSON() {
		return _subjectLocalService.getSubjectsJSON();
	}

	/**
	 * Updates the subject in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subject the subject
	 * @return the subject that was updated
	 */
	@Override
	public com.weprode.nero.schedule.model.Subject updateSubject(
		com.weprode.nero.schedule.model.Subject subject) {

		return _subjectLocalService.updateSubject(subject);
	}

	@Override
	public SubjectLocalService getWrappedService() {
		return _subjectLocalService;
	}

	@Override
	public void setWrappedService(SubjectLocalService subjectLocalService) {
		_subjectLocalService = subjectLocalService;
	}

	private SubjectLocalService _subjectLocalService;

}