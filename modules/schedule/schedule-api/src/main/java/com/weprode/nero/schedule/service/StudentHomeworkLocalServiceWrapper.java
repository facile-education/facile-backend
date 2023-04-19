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
 * Provides a wrapper for {@link StudentHomeworkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see StudentHomeworkLocalService
 * @generated
 */
public class StudentHomeworkLocalServiceWrapper
	implements ServiceWrapper<StudentHomeworkLocalService>,
			   StudentHomeworkLocalService {

	public StudentHomeworkLocalServiceWrapper(
		StudentHomeworkLocalService studentHomeworkLocalService) {

		_studentHomeworkLocalService = studentHomeworkLocalService;
	}

	/**
	 * Adds the student homework to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect StudentHomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param studentHomework the student homework
	 * @return the student homework that was added
	 */
	@Override
	public com.weprode.nero.schedule.model.StudentHomework addStudentHomework(
		com.weprode.nero.schedule.model.StudentHomework studentHomework) {

		return _studentHomeworkLocalService.addStudentHomework(studentHomework);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _studentHomeworkLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new student homework with the primary key. Does not add the student homework to the database.
	 *
	 * @param studentHomeworkId the primary key for the new student homework
	 * @return the new student homework
	 */
	@Override
	public com.weprode.nero.schedule.model.StudentHomework
		createStudentHomework(long studentHomeworkId) {

		return _studentHomeworkLocalService.createStudentHomework(
			studentHomeworkId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _studentHomeworkLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the student homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect StudentHomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework that was removed
	 * @throws PortalException if a student homework with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.StudentHomework
			deleteStudentHomework(long studentHomeworkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _studentHomeworkLocalService.deleteStudentHomework(
			studentHomeworkId);
	}

	/**
	 * Deletes the student homework from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect StudentHomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param studentHomework the student homework
	 * @return the student homework that was removed
	 */
	@Override
	public com.weprode.nero.schedule.model.StudentHomework
		deleteStudentHomework(
			com.weprode.nero.schedule.model.StudentHomework studentHomework) {

		return _studentHomeworkLocalService.deleteStudentHomework(
			studentHomework);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _studentHomeworkLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _studentHomeworkLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _studentHomeworkLocalService.dynamicQuery();
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

		return _studentHomeworkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.StudentHomeworkModelImpl</code>.
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

		return _studentHomeworkLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.StudentHomeworkModelImpl</code>.
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

		return _studentHomeworkLocalService.dynamicQuery(
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

		return _studentHomeworkLocalService.dynamicQueryCount(dynamicQuery);
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

		return _studentHomeworkLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.StudentHomework fetchStudentHomework(
		long studentHomeworkId) {

		return _studentHomeworkLocalService.fetchStudentHomework(
			studentHomeworkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _studentHomeworkLocalService.getActionableDynamicQuery();
	}

	/**
	 * Get all students having given homework Id
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getHomeworkStudents(long homeworkId) {

		return _studentHomeworkLocalService.getHomeworkStudents(homeworkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _studentHomeworkLocalService.
			getIndexableActionableDynamicQuery();
	}

	@Override
	public com.weprode.nero.schedule.model.StudentHomework
		getOrCreateStudentHomework(long homeworkId, long studentId) {

		return _studentHomeworkLocalService.getOrCreateStudentHomework(
			homeworkId, studentId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _studentHomeworkLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _studentHomeworkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the student homework with the primary key.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework
	 * @throws PortalException if a student homework with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.StudentHomework getStudentHomework(
			long studentHomeworkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _studentHomeworkLocalService.getStudentHomework(
			studentHomeworkId);
	}

	@Override
	public com.weprode.nero.schedule.model.StudentHomework getStudentHomework(
		long homeworkId, long studentId) {

		return _studentHomeworkLocalService.getStudentHomework(
			homeworkId, studentId);
	}

	/**
	 * Returns a range of all the student homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of student homeworks
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.StudentHomework>
		getStudentHomeworks(int start, int end) {

		return _studentHomeworkLocalService.getStudentHomeworks(start, end);
	}

	/**
	 * Returns the number of student homeworks.
	 *
	 * @return the number of student homeworks
	 */
	@Override
	public int getStudentHomeworksCount() {
		return _studentHomeworkLocalService.getStudentHomeworksCount();
	}

	/**
	 * Returns the users having done the given homework
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getStudentsHavingDoneHomework(long homeworkId) {

		return _studentHomeworkLocalService.getStudentsHavingDoneHomework(
			homeworkId);
	}

	/**
	 * Returns true if the given student has done the given homework
	 */
	@Override
	public boolean hasStudentDoneHomework(long studentId, long homeworkId) {
		return _studentHomeworkLocalService.hasStudentDoneHomework(
			studentId, homeworkId);
	}

	/**
	 * Returns true if the given student has given homework
	 */
	@Override
	public boolean hasStudentHomework(long studentId, long homeworkId) {
		return _studentHomeworkLocalService.hasStudentHomework(
			studentId, homeworkId);
	}

	@Override
	public boolean removeHomework(long homeworkId) {
		return _studentHomeworkLocalService.removeHomework(homeworkId);
	}

	@Override
	public boolean removeStudentHomework(long homeworkId, long studentId) {
		return _studentHomeworkLocalService.removeStudentHomework(
			homeworkId, studentId);
	}

	@Override
	public boolean setHomeworkDone(
		long homeworkId, long studentId, boolean isDone) {

		return _studentHomeworkLocalService.setHomeworkDone(
			homeworkId, studentId, isDone);
	}

	@Override
	public boolean setHomeworkSent(long homeworkId, long studentId) {
		return _studentHomeworkLocalService.setHomeworkSent(
			homeworkId, studentId);
	}

	/**
	 * Updates the student homework in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect StudentHomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param studentHomework the student homework
	 * @return the student homework that was updated
	 */
	@Override
	public com.weprode.nero.schedule.model.StudentHomework
		updateStudentHomework(
			com.weprode.nero.schedule.model.StudentHomework studentHomework) {

		return _studentHomeworkLocalService.updateStudentHomework(
			studentHomework);
	}

	@Override
	public StudentHomeworkLocalService getWrappedService() {
		return _studentHomeworkLocalService;
	}

	@Override
	public void setWrappedService(
		StudentHomeworkLocalService studentHomeworkLocalService) {

		_studentHomeworkLocalService = studentHomeworkLocalService;
	}

	private StudentHomeworkLocalService _studentHomeworkLocalService;

}