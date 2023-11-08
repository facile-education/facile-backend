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

package com.weprode.facile.course.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.course.model.StudentHomework;

import java.io.IOException;
import java.io.Serializable;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for StudentHomework. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see StudentHomeworkLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface StudentHomeworkLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.course.service.impl.StudentHomeworkLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the student homework local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link StudentHomeworkLocalServiceUtil} if injection and service tracking are not available.
	 */

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
	@Indexable(type = IndexableType.REINDEX)
	public StudentHomework addStudentHomework(StudentHomework studentHomework);

	public void correctFile(long homeworkId, long studentId, String comment);

	public int countCorrectedWorks(long homeworkId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Creates a new student homework with the primary key. Does not add the student homework to the database.
	 *
	 * @param studentHomeworkId the primary key for the new student homework
	 * @return the new student homework
	 */
	@Transactional(enabled = false)
	public StudentHomework createStudentHomework(long studentHomeworkId);

	public void deleteDroppedFile(
			long studentId, long homeworkId, long fileEntryId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public StudentHomework deleteStudentHomework(long studentHomeworkId)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public StudentHomework deleteStudentHomework(
		StudentHomework studentHomework);

	public void dropHomeworkFile(
			long studentId, long homeworkId, long fileEntryId)
		throws IOException, PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> T dslQuery(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int dslQueryCount(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StudentHomework fetchStudentHomework(long studentHomeworkId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getHomeworkStatus(long homeworkId);

	/**
	 * Get all students having given homework Id
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getHomeworkStudents(long homeworkId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StudentHomework getOrCreateStudentHomework(
		long homeworkId, long studentId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the student homework with the primary key.
	 *
	 * @param studentHomeworkId the primary key of the student homework
	 * @return the student homework
	 * @throws PortalException if a student homework with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StudentHomework getStudentHomework(long studentHomeworkId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StudentHomework getStudentHomework(long homeworkId, long studentId);

	/**
	 * Returns a range of all the student homeworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.course.model.impl.StudentHomeworkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student homeworks
	 * @param end the upper bound of the range of student homeworks (not inclusive)
	 * @return the range of student homeworks
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StudentHomework> getStudentHomeworks(int start, int end);

	/**
	 * Returns the number of student homeworks.
	 *
	 * @return the number of student homeworks
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getStudentHomeworksCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getStudentSentFile(long studentId, long homeworkId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getStudentsHavingDoneHomework(long homeworkId);

	/**
	 * Returns true if the given student has done the given homework
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasStudentDoneHomework(long studentId, long homeworkId);

	/**
	 * Returns true if the given student has given homework
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasStudentHomework(long studentId, long homeworkId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasStudentSentFile(
		long studentId, long homeworkId, long fileEntryId);

	public boolean removeHomework(long homeworkId);

	public boolean removeStudentHomework(long homeworkId, long studentId);

	public boolean setHomeworkDone(
		long homeworkId, long studentId, boolean isDone);

	public void setHomeworkSent(
		long studentId, long homeworkId, long fileEntryId);

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
	@Indexable(type = IndexableType.REINDEX)
	public StudentHomework updateStudentHomework(
		StudentHomework studentHomework);

}