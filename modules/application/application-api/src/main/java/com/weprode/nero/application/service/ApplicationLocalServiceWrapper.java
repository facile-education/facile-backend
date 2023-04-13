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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ApplicationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationLocalService
 * @generated
 */
public class ApplicationLocalServiceWrapper
	implements ApplicationLocalService,
			   ServiceWrapper<ApplicationLocalService> {

	public ApplicationLocalServiceWrapper(
		ApplicationLocalService applicationLocalService) {

		_applicationLocalService = applicationLocalService;
	}

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
	@Override
	public com.weprode.nero.application.model.Application addApplication(
		com.weprode.nero.application.model.Application application) {

		return _applicationLocalService.addApplication(application);
	}

	@Override
	public com.weprode.nero.application.model.Application addApplication(
			String applicationName, String applicationKey, String category,
			long menuEntryId, String image, boolean hasCustomUrl,
			String globalUrl, boolean exportUser, boolean exportStudent,
			boolean exportParent, boolean exportTeacher, boolean exportOther,
			String defaultRoles, String authorizedSchools)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _applicationLocalService.addApplication(
			applicationName, applicationKey, category, menuEntryId, image,
			hasCustomUrl, globalUrl, exportUser, exportStudent, exportParent,
			exportTeacher, exportOther, defaultRoles, authorizedSchools);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject convertToJSON(
		com.weprode.nero.application.model.Application application,
		boolean withDetails) {

		return _applicationLocalService.convertToJSON(application, withDetails);
	}

	/**
	 * Creates a new application with the primary key. Does not add the application to the database.
	 *
	 * @param applicationId the primary key for the new application
	 * @return the new application
	 */
	@Override
	public com.weprode.nero.application.model.Application createApplication(
		long applicationId) {

		return _applicationLocalService.createApplication(applicationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public com.weprode.nero.application.model.Application deleteApplication(
		com.weprode.nero.application.model.Application application) {

		return _applicationLocalService.deleteApplication(application);
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
	@Override
	public com.weprode.nero.application.model.Application deleteApplication(
			long applicationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationLocalService.deleteApplication(applicationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _applicationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _applicationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicationLocalService.dynamicQuery();
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

		return _applicationLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _applicationLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _applicationLocalService.dynamicQuery(
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

		return _applicationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _applicationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.application.model.Application editApplication(
			long applicationId, String applicationName, String applicationKey,
			String category, long menuEntryId, String image,
			boolean hasCustomUrl, String globalUrl, boolean exportUser,
			boolean exportStudent, boolean exportParent, boolean exportTeacher,
			boolean exportOther, String defaultRoles, String authorizedSchools)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _applicationLocalService.editApplication(
			applicationId, applicationName, applicationKey, category,
			menuEntryId, image, hasCustomUrl, globalUrl, exportUser,
			exportStudent, exportParent, exportTeacher, exportOther,
			defaultRoles, authorizedSchools);
	}

	@Override
	public com.weprode.nero.application.model.Application fetchApplication(
		long applicationId) {

		return _applicationLocalService.fetchApplication(applicationId);
	}

	@Override
	public com.weprode.nero.application.model.Application findApplicationByKey(
			String key)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _applicationLocalService.findApplicationByKey(key);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _applicationLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.UserGroup>
		getAllApplicationGroups(long companyId) {

		return _applicationLocalService.getAllApplicationGroups(companyId);
	}

	@Override
	public java.util.List<com.weprode.nero.application.model.Application>
			getAllApplications()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _applicationLocalService.getAllApplications();
	}

	/**
	 * Returns the application with the primary key.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application
	 * @throws PortalException if a application with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.application.model.Application getApplication(
			long applicationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationLocalService.getApplication(applicationId);
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
	@Override
	public java.util.List<com.weprode.nero.application.model.Application>
		getApplications(int start, int end) {

		return _applicationLocalService.getApplications(start, end);
	}

	/**
	 * Returns the number of applications.
	 *
	 * @return the number of applications
	 */
	@Override
	public int getApplicationsCount() {
		return _applicationLocalService.getApplicationsCount();
	}

	@Override
	public String getApplicationURLByKey(long schoolId, String applicationKey) {
		return _applicationLocalService.getApplicationURLByKey(
			schoolId, applicationKey);
	}

	@Override
	public com.weprode.nero.application.model.Application getById(
			long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _applicationLocalService.getById(applicationId);
	}

	@Override
	public java.util.List<String> getCategories()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _applicationLocalService.getCategories();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _applicationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPortlets(
		com.liferay.portal.kernel.model.User user) {

		return _applicationLocalService.getPortlets(user);
	}

	@Override
	public java.util.List<com.weprode.nero.application.model.Application>
		getSchoolApplications(long schoolId) {

		return _applicationLocalService.getSchoolApplications(schoolId);
	}

	@Override
	public java.util.List<com.weprode.nero.application.model.Application>
		getUserApplications(com.liferay.portal.kernel.model.User user) {

		return _applicationLocalService.getUserApplications(user);
	}

	@Override
	public boolean removeApplication(long applicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _applicationLocalService.removeApplication(applicationId);
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
	@Override
	public com.weprode.nero.application.model.Application updateApplication(
		com.weprode.nero.application.model.Application application) {

		return _applicationLocalService.updateApplication(application);
	}

	@Override
	public ApplicationLocalService getWrappedService() {
		return _applicationLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicationLocalService applicationLocalService) {

		_applicationLocalService = applicationLocalService;
	}

	private ApplicationLocalService _applicationLocalService;

}