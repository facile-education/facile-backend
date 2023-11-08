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

package com.weprode.facile.schedule.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TeacherSubjectLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TeacherSubjectLocalService
 * @generated
 */
public class TeacherSubjectLocalServiceWrapper
	implements ServiceWrapper<TeacherSubjectLocalService>,
			   TeacherSubjectLocalService {

	public TeacherSubjectLocalServiceWrapper() {
		this(null);
	}

	public TeacherSubjectLocalServiceWrapper(
		TeacherSubjectLocalService teacherSubjectLocalService) {

		_teacherSubjectLocalService = teacherSubjectLocalService;
	}

	/**
	 * Adds the teacher subject to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherSubjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherSubject the teacher subject
	 * @return the teacher subject that was added
	 */
	@Override
	public com.weprode.facile.schedule.model.TeacherSubject addTeacherSubject(
		com.weprode.facile.schedule.model.TeacherSubject teacherSubject) {

		return _teacherSubjectLocalService.addTeacherSubject(teacherSubject);
	}

	/**
	 * Returns true if the mapping has been created, false if it already exists
	 */
	@Override
	public com.weprode.facile.schedule.model.TeacherSubject
		addTeacherSubjectInSchool(
			long teacherId, long subjectId, long schoolId) {

		return _teacherSubjectLocalService.addTeacherSubjectInSchool(
			teacherId, subjectId, schoolId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherSubjectLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new teacher subject with the primary key. Does not add the teacher subject to the database.
	 *
	 * @param teacherSubjectId the primary key for the new teacher subject
	 * @return the new teacher subject
	 */
	@Override
	public com.weprode.facile.schedule.model.TeacherSubject
		createTeacherSubject(long teacherSubjectId) {

		return _teacherSubjectLocalService.createTeacherSubject(
			teacherSubjectId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherSubjectLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the teacher subject with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherSubjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject that was removed
	 * @throws PortalException if a teacher subject with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.schedule.model.TeacherSubject
			deleteTeacherSubject(long teacherSubjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherSubjectLocalService.deleteTeacherSubject(
			teacherSubjectId);
	}

	/**
	 * Deletes the teacher subject from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherSubjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherSubject the teacher subject
	 * @return the teacher subject that was removed
	 */
	@Override
	public com.weprode.facile.schedule.model.TeacherSubject
		deleteTeacherSubject(
			com.weprode.facile.schedule.model.TeacherSubject teacherSubject) {

		return _teacherSubjectLocalService.deleteTeacherSubject(teacherSubject);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _teacherSubjectLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _teacherSubjectLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _teacherSubjectLocalService.dynamicQuery();
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

		return _teacherSubjectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.TeacherSubjectModelImpl</code>.
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

		return _teacherSubjectLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.TeacherSubjectModelImpl</code>.
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

		return _teacherSubjectLocalService.dynamicQuery(
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

		return _teacherSubjectLocalService.dynamicQueryCount(dynamicQuery);
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

		return _teacherSubjectLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.schedule.model.TeacherSubject fetchTeacherSubject(
		long teacherSubjectId) {

		return _teacherSubjectLocalService.fetchTeacherSubject(
			teacherSubjectId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _teacherSubjectLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _teacherSubjectLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _teacherSubjectLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherSubjectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the teacher subject with the primary key.
	 *
	 * @param teacherSubjectId the primary key of the teacher subject
	 * @return the teacher subject
	 * @throws PortalException if a teacher subject with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.schedule.model.TeacherSubject getTeacherSubject(
			long teacherSubjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherSubjectLocalService.getTeacherSubject(teacherSubjectId);
	}

	@Override
	public String getTeacherSubjectList(
		com.liferay.portal.kernel.model.User teacher) {

		return _teacherSubjectLocalService.getTeacherSubjectList(teacher);
	}

	/**
	 * Returns a range of all the teacher subjects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.TeacherSubjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teacher subjects
	 * @param end the upper bound of the range of teacher subjects (not inclusive)
	 * @return the range of teacher subjects
	 */
	@Override
	public java.util.List<com.weprode.facile.schedule.model.TeacherSubject>
		getTeacherSubjects(int start, int end) {

		return _teacherSubjectLocalService.getTeacherSubjects(start, end);
	}

	@Override
	public java.util.List<String> getTeacherSubjects(long teacherId) {
		return _teacherSubjectLocalService.getTeacherSubjects(teacherId);
	}

	/**
	 * Returns the number of teacher subjects.
	 *
	 * @return the number of teacher subjects
	 */
	@Override
	public int getTeacherSubjectsCount() {
		return _teacherSubjectLocalService.getTeacherSubjectsCount();
	}

	/**
	 * Updates the teacher subject in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeacherSubjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teacherSubject the teacher subject
	 * @return the teacher subject that was updated
	 */
	@Override
	public com.weprode.facile.schedule.model.TeacherSubject
		updateTeacherSubject(
			com.weprode.facile.schedule.model.TeacherSubject teacherSubject) {

		return _teacherSubjectLocalService.updateTeacherSubject(teacherSubject);
	}

	@Override
	public TeacherSubjectLocalService getWrappedService() {
		return _teacherSubjectLocalService;
	}

	@Override
	public void setWrappedService(
		TeacherSubjectLocalService teacherSubjectLocalService) {

		_teacherSubjectLocalService = teacherSubjectLocalService;
	}

	private TeacherSubjectLocalService _teacherSubjectLocalService;

}