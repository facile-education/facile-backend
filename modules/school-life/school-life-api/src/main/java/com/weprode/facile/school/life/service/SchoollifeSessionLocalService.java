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

package com.weprode.facile.school.life.service;

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

import com.weprode.facile.school.life.model.SchoollifeSession;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for SchoollifeSession. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSessionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface SchoollifeSessionLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.school.life.service.impl.SchoollifeSessionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the schoollife session local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link SchoollifeSessionLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the schoollife session to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSession the schoollife session
	 * @return the schoollife session that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public SchoollifeSession addSchoollifeSession(
		SchoollifeSession schoollifeSession);

	public SchoollifeSession addSession(
		long schoollifeSlotId, long schoolId, Date startDate, Date endDate,
		int type);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Creates a new schoollife session with the primary key. Does not add the schoollife session to the database.
	 *
	 * @param schoollifeSessionId the primary key for the new schoollife session
	 * @return the new schoollife session
	 */
	@Transactional(enabled = false)
	public SchoollifeSession createSchoollifeSession(long schoollifeSessionId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the schoollife session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session that was removed
	 * @throws PortalException if a schoollife session with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public SchoollifeSession deleteSchoollifeSession(long schoollifeSessionId)
		throws PortalException;

	/**
	 * Deletes the schoollife session from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSession the schoollife session
	 * @return the schoollife session that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public SchoollifeSession deleteSchoollifeSession(
		SchoollifeSession schoollifeSession);

	public boolean deleteSession(long schoollifeSessionId);

	public boolean deleteSlotSessions(
		long schoollifeSlotId, Date startDate, Date endDate);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.school.life.model.impl.SchoollifeSessionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.school.life.model.impl.SchoollifeSessionModelImpl</code>.
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
	public SchoollifeSession fetchSchoollifeSession(long schoollifeSessionId);

	public JSONObject formatSchoollifeSession(
			SchoollifeSession session, User user)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getColorFromSchoollifeType(int schoollifeType);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SchoollifeSession getLastSession(long schoollifeSlotId);

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
	 * Returns the schoollife session with the primary key.
	 *
	 * @param schoollifeSessionId the primary key of the schoollife session
	 * @return the schoollife session
	 * @throws PortalException if a schoollife session with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SchoollifeSession getSchoollifeSession(long schoollifeSessionId)
		throws PortalException;

	/**
	 * Returns a range of all the schoollife sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.school.life.model.impl.SchoollifeSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schoollife sessions
	 * @param end the upper bound of the range of schoollife sessions (not inclusive)
	 * @return the range of schoollife sessions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SchoollifeSession> getSchoollifeSessions(int start, int end);

	/**
	 * Returns the number of schoollife sessions.
	 *
	 * @return the number of schoollife sessions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSchoollifeSessionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getSessionName(long schoollifeSessionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SchoollifeSession> getSlotSessions(long schoollifeSlotId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getTeacherSessions(
		long teacherId, Date minDate, Date maxDate);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SchoollifeSession> getUnnotifiedSessions(
		int type, Date startDate, Date endDate);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SchoollifeSession> getWeekSessions(
		long schoolId, int type, Date fromDate);

	public boolean setRollCalled(long schoollifeSessionId);

	/**
	 * Updates the schoollife session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchoollifeSessionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoollifeSession the schoollife session
	 * @return the schoollife session that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public SchoollifeSession updateSchoollifeSession(
		SchoollifeSession schoollifeSession);

}