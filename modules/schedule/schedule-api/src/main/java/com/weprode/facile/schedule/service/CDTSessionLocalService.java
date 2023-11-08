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

import com.weprode.facile.schedule.model.CDTSession;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for CDTSession. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see CDTSessionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CDTSessionLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.schedule.service.impl.CDTSessionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the cdt session local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CDTSessionLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the cdt session to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cdtSession the cdt session
	 * @return the cdt session that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CDTSession addCDTSession(CDTSession cdtSession);

	public JSONArray convertSessions(List<CDTSession> sessions, User user);

	/**
	 * Creates a new cdt session with the primary key. Does not add the cdt session to the database.
	 *
	 * @param sessionId the primary key for the new cdt session
	 * @return the new cdt session
	 */
	@Transactional(enabled = false)
	public CDTSession createCDTSession(long sessionId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public boolean createRecurrentSessions(
		long groupId, String subject, String room, Date startDate, Date endDate,
		int slot, List<Long> teacherIdList);

	public CDTSession createSession(
			long groupId, String subject, Date startDate, Date endDate,
			int slot, List<Long> teacherIdList, String room,
			String fullCoursName, boolean isManual)
		throws SystemException;

	/**
	 * Deletes the cdt session from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cdtSession the cdt session
	 * @return the cdt session that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public CDTSession deleteCDTSession(CDTSession cdtSession);

	/**
	 * Deletes the cdt session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session that was removed
	 * @throws PortalException if a cdt session with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public CDTSession deleteCDTSession(long sessionId) throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public void deleteSessionAndDependencies(long sessionId)
		throws PortalException, SystemException;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.CDTSessionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.CDTSessionModelImpl</code>.
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
	public CDTSession fetchCDTSession(long sessionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the cdt session with the primary key.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session
	 * @throws PortalException if a cdt session with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CDTSession getCDTSession(long sessionId) throws PortalException;

	/**
	 * Returns a range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @return the range of cdt sessions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getCDTSessions(int start, int end);

	/**
	 * Returns the number of cdt sessions.
	 *
	 * @return the number of cdt sessions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCDTSessionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getGroupSessions(
		long groupId, Date minDate, Date maxDate, boolean includeSubClasses);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getGroupsSessionActivity(
		long userId, List<Long> groupIds, Date minDate, Date maxDate);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getNextSessions(User user, long sessionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getNextStudentDaySessions(
		long studentId, Date targetDate, boolean goForward);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getNextTeacherDaySessions(
		long teacherId, Date targetDate, boolean goForward);

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
	 * Get the session for a school id that begin between 2 dates
	 * Used by synchronization process
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getSchoolSessions(
			Long schoolId, Date startDate, Date endDate)
		throws SystemException;

	/**
	 * Get session student list
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getSessionStudents(long sessionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getStudentSessions(
		long studentId, Date minDate, Date maxDate);

	/**
	 * Get all sessions for a given student in a given date range, that are not attached to a group (eg. subClass)
	 * Returns empty for GVA
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getStudentSpecificSessions(
		long studentId, Date minDate, Date maxDate);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CDTSession> getTeacherSessions(
		long teacherId, Date minDate, Date maxDate);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserSession(User user, long sessionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isSession(long itemId);

	/**
	 * Updates the cdt session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CDTSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cdtSession the cdt session
	 * @return the cdt session that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CDTSession updateCDTSession(CDTSession cdtSession);

}