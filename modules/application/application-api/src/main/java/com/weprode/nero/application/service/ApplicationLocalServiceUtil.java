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

package com.weprode.nero.application.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.application.model.Application;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Application. This utility wraps
 * <code>com.weprode.nero.application.service.impl.ApplicationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationLocalService
 * @generated
 */
public class ApplicationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.application.service.impl.ApplicationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the application to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param application the application
	 * @return the application that was added
	 */
	public static Application addApplication(Application application) {
		return getService().addApplication(application);
	}

	public static Application addApplication(
			String applicationName, String applicationKey, String category,
			long menuEntryId, String image, boolean hasCustomUrl,
			String globalUrl, boolean exportUser, boolean exportStudent,
			boolean exportParent, boolean exportTeacher, boolean exportOther,
			String defaultRoles, String authorizedSchools)
		throws SystemException {

		return getService().addApplication(
			applicationName, applicationKey, category, menuEntryId, image,
			hasCustomUrl, globalUrl, exportUser, exportStudent, exportParent,
			exportTeacher, exportOther, defaultRoles, authorizedSchools);
	}

	public static com.liferay.portal.kernel.json.JSONObject convertToJSON(
		Application application, boolean withDetails) {

		return getService().convertToJSON(application, withDetails);
	}

	/**
	 * Creates a new application with the primary key. Does not add the application to the database.
	 *
	 * @param applicationId the primary key for the new application
	 * @return the new application
	 */
	public static Application createApplication(long applicationId) {
		return getService().createApplication(applicationId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the application from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param application the application
	 * @return the application that was removed
	 */
	public static Application deleteApplication(Application application) {
		return getService().deleteApplication(application);
	}

	/**
	 * Deletes the application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationId the primary key of the application
	 * @return the application that was removed
	 * @throws PortalException if a application with the primary key could not be found
	 */
	public static Application deleteApplication(long applicationId)
		throws PortalException {

		return getService().deleteApplication(applicationId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Application editApplication(
			long applicationId, String applicationName, String applicationKey,
			String category, long menuEntryId, String image,
			boolean hasCustomUrl, String globalUrl, boolean exportUser,
			boolean exportStudent, boolean exportParent, boolean exportTeacher,
			boolean exportOther, String defaultRoles, String authorizedSchools)
		throws SystemException {

		return getService().editApplication(
			applicationId, applicationName, applicationKey, category,
			menuEntryId, image, hasCustomUrl, globalUrl, exportUser,
			exportStudent, exportParent, exportTeacher, exportOther,
			defaultRoles, authorizedSchools);
	}

	public static Application fetchApplication(long applicationId) {
		return getService().fetchApplication(applicationId);
	}

	public static Application findApplicationByKey(String key)
		throws SystemException {

		return getService().findApplicationByKey(key);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<com.liferay.portal.kernel.model.UserGroup>
		getAllApplicationGroups(long companyId) {

		return getService().getAllApplicationGroups(companyId);
	}

	public static List<Application> getAllApplications()
		throws SystemException {

		return getService().getAllApplications();
	}

	/**
	 * Returns the application with the primary key.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application
	 * @throws PortalException if a application with the primary key could not be found
	 */
	public static Application getApplication(long applicationId)
		throws PortalException {

		return getService().getApplication(applicationId);
	}

	/**
	 * Returns a range of all the applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of applications
	 */
	public static List<Application> getApplications(int start, int end) {
		return getService().getApplications(start, end);
	}

	/**
	 * Returns the number of applications.
	 *
	 * @return the number of applications
	 */
	public static int getApplicationsCount() {
		return getService().getApplicationsCount();
	}

	public static String getApplicationURLByKey(
		long schoolId, String applicationKey) {

		return getService().getApplicationURLByKey(schoolId, applicationKey);
	}

	public static Application getById(long applicationId)
		throws SystemException {

		return getService().getById(applicationId);
	}

	public static List<String> getCategories() throws SystemException {
		return getService().getCategories();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.liferay.portal.kernel.json.JSONObject getPortlets(
		com.liferay.portal.kernel.model.User user) {

		return getService().getPortlets(user);
	}

	public static List<Application> getSchoolApplications(long schoolId) {
		return getService().getSchoolApplications(schoolId);
	}

	public static List<Application> getUserApplications(
		com.liferay.portal.kernel.model.User user) {

		return getService().getUserApplications(user);
	}

	public static boolean removeApplication(long applicationId)
		throws PortalException, SystemException {

		return getService().removeApplication(applicationId);
	}

	/**
	 * Updates the application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param application the application
	 * @return the application that was updated
	 */
	public static Application updateApplication(Application application) {
		return getService().updateApplication(application);
	}

	public static ApplicationLocalService getService() {
		return _service;
	}

	private static volatile ApplicationLocalService _service;

}