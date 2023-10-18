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

package com.weprode.nero.course.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HomeworkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkLocalService
 * @generated
 */
public class HomeworkLocalServiceWrapper
	implements HomeworkLocalService, ServiceWrapper<HomeworkLocalService> {

	public HomeworkLocalServiceWrapper() {
		this(null);
	}

	public HomeworkLocalServiceWrapper(
		HomeworkLocalService homeworkLocalService) {

		_homeworkLocalService = homeworkLocalService;
	}

	/**
	 * Adds the homework to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param homework the homework
	 * @return the homework that was added
	 */
	@Override
	public com.weprode.nero.course.model.Homework addHomework(
		com.weprode.nero.course.model.Homework homework) {

		return _homeworkLocalService.addHomework(homework);
	}

	@Override
	public void cancelDrop(long studentId, long homeworkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_homeworkLocalService.cancelDrop(studentId, homeworkId);
	}

	@Override
	public void correctFile(long homeworkId, long studentId, String comment) {
		_homeworkLocalService.correctFile(homeworkId, studentId, comment);
	}

	@Override
	public int countHomeworksToCorrect(long teacherId) {
		return _homeworkLocalService.countHomeworksToCorrect(teacherId);
	}

	@Override
	public int countUndoneHomeworks(long studentId) {
		return _homeworkLocalService.countUndoneHomeworks(studentId);
	}

	@Override
	public int countUndoneHomeworks(
		long studentId, java.util.Date minDate, java.util.Date maxDate) {

		return _homeworkLocalService.countUndoneHomeworks(
			studentId, minDate, maxDate);
	}

	/**
	 * Creates a new homework with the primary key. Does not add the homework to the database.
	 *
	 * @param homeworkId the primary key for the new homework
	 * @return the new homework
	 */
	@Override
	public com.weprode.nero.course.model.Homework createHomework(
		long homeworkId) {

		return _homeworkLocalService.createHomework(homeworkId);
	}

	@Override
	public com.weprode.nero.course.model.Homework createHomework(
		com.liferay.portal.kernel.model.User teacher, String title,
		long sourceSessionId, long targetSessionId, long courseId,
		java.util.Date targetDate, int homeworkType, int estimatedTime,
		java.util.List<Long> studentIds, java.util.Date publicationDate,
		boolean isDraft) {

		return _homeworkLocalService.createHomework(
			teacher, title, sourceSessionId, targetSessionId, courseId,
			targetDate, homeworkType, estimatedTime, studentIds,
			publicationDate, isDraft);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _homeworkLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the homework from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param homework the homework
	 * @return the homework that was removed
	 */
	@Override
	public com.weprode.nero.course.model.Homework deleteHomework(
		com.weprode.nero.course.model.Homework homework) {

		return _homeworkLocalService.deleteHomework(homework);
	}

	/**
	 * Deletes the homework with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework that was removed
	 * @throws PortalException if a homework with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.course.model.Homework deleteHomework(
			long homeworkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _homeworkLocalService.deleteHomework(homeworkId);
	}

	/**
	 * Remove an homework and its associated objects
	 */
	@Override
	public void deleteHomeworkAndDependencies(long homeworkId) {
		_homeworkLocalService.deleteHomeworkAndDependencies(homeworkId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _homeworkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public boolean deleteSessionHomeworks(long sessionId) {
		return _homeworkLocalService.deleteSessionHomeworks(sessionId);
	}

	@Override
	public void dropHomeworkFile(
			long studentId, long homeworkId, long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_homeworkLocalService.dropHomeworkFile(
			studentId, homeworkId, fileEntryId);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _homeworkLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _homeworkLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _homeworkLocalService.dynamicQuery();
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

		return _homeworkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.HomeworkModelImpl</code>.
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

		return _homeworkLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.HomeworkModelImpl</code>.
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

		return _homeworkLocalService.dynamicQuery(
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

		return _homeworkLocalService.dynamicQueryCount(dynamicQuery);
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

		return _homeworkLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.course.model.Homework fetchHomework(
		long homeworkId) {

		return _homeworkLocalService.fetchHomework(homeworkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _homeworkLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.nero.course.model.Homework>
		getCourseHomeworkActivity(
			long userId, long courseId, java.util.Date minDate,
			java.util.Date maxDate) {

		return _homeworkLocalService.getCourseHomeworkActivity(
			userId, courseId, minDate, maxDate);
	}

	/**
	 * Returns the homework with the primary key.
	 *
	 * @param homeworkId the primary key of the homework
	 * @return the homework
	 * @throws PortalException if a homework with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.course.model.Homework getHomework(long homeworkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _homeworkLocalService.getHomework(homeworkId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder
			getHomeworkDropFolder(long homeworkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _homeworkLocalService.getHomeworkDropFolder(homeworkId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder getHomeworkFolder(
			long homeworkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _homeworkLocalService.getHomeworkFolder(homeworkId);
	}

	/**
	 * Returns a range of all the homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.course.model.impl.HomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of homeworks
	 * @param end the upper bound of the range of homeworks (not inclusive)
	 * @return the range of homeworks
	 */
	@Override
	public java.util.List<com.weprode.nero.course.model.Homework> getHomeworks(
		int start, int end) {

		return _homeworkLocalService.getHomeworks(start, end);
	}

	/**
	 * Returns the number of homeworks.
	 *
	 * @return the number of homeworks
	 */
	@Override
	public int getHomeworksCount() {
		return _homeworkLocalService.getHomeworksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _homeworkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _homeworkLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _homeworkLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.weprode.nero.course.model.Homework>
		getSessionGivenHomeworks(
			com.liferay.portal.kernel.model.User user, long sessionId,
			boolean hideDraftsForTeachers) {

		return _homeworkLocalService.getSessionGivenHomeworks(
			user, sessionId, hideDraftsForTeachers);
	}

	@Override
	public java.util.List<com.weprode.nero.course.model.Homework>
		getSessionToDoHomeworks(
			com.liferay.portal.kernel.model.User user, long sessionId,
			boolean hideDraftsForTeachers) {

		return _homeworkLocalService.getSessionToDoHomeworks(
			user, sessionId, hideDraftsForTeachers);
	}

	@Override
	public java.util.List<com.weprode.nero.course.model.Homework>
		getStudentHomeworkActivity(
			long studentId, java.util.Date minDate, java.util.Date maxDate) {

		return _homeworkLocalService.getStudentHomeworkActivity(
			studentId, minDate, maxDate);
	}

	@Override
	public java.util.List<com.weprode.nero.course.model.Homework>
		getStudentHomeworks(
			long studentId, java.util.Date minDate, java.util.Date maxDate,
			boolean undoneOnly) {

		return _homeworkLocalService.getStudentHomeworks(
			studentId, minDate, maxDate, undoneOnly);
	}

	@Override
	public java.util.List<com.weprode.nero.course.model.Homework>
		getTeacherHomeworksToCorrect(
			com.liferay.portal.kernel.model.User teacher) {

		return _homeworkLocalService.getTeacherHomeworksToCorrect(teacher);
	}

	@Override
	public boolean hasHomeworksGivenDuringSession(long sessionId) {
		return _homeworkLocalService.hasHomeworksGivenDuringSession(sessionId);
	}

	@Override
	public boolean hasHomeworksToDoForSession(long sessionId) {
		return _homeworkLocalService.hasHomeworksToDoForSession(sessionId);
	}

	/**
	 * Updates the homework in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HomeworkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param homework the homework
	 * @return the homework that was updated
	 */
	@Override
	public com.weprode.nero.course.model.Homework updateHomework(
		com.weprode.nero.course.model.Homework homework) {

		return _homeworkLocalService.updateHomework(homework);
	}

	@Override
	public com.weprode.nero.course.model.Homework updateHomework(
		long homeworkId, String title, long targetSessionId,
		java.util.Date targetDate, int estimatedTime,
		java.util.List<Long> studentIds, java.util.Date publicationDate,
		boolean isDraft) {

		return _homeworkLocalService.updateHomework(
			homeworkId, title, targetSessionId, targetDate, estimatedTime,
			studentIds, publicationDate, isDraft);
	}

	@Override
	public HomeworkLocalService getWrappedService() {
		return _homeworkLocalService;
	}

	@Override
	public void setWrappedService(HomeworkLocalService homeworkLocalService) {
		_homeworkLocalService = homeworkLocalService;
	}

	private HomeworkLocalService _homeworkLocalService;

}