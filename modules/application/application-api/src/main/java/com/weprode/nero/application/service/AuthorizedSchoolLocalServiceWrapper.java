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
 * Provides a wrapper for {@link AuthorizedSchoolLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuthorizedSchoolLocalService
 * @generated
 */
public class AuthorizedSchoolLocalServiceWrapper
	implements AuthorizedSchoolLocalService,
			   ServiceWrapper<AuthorizedSchoolLocalService> {

	public AuthorizedSchoolLocalServiceWrapper(
		AuthorizedSchoolLocalService authorizedSchoolLocalService) {

		_authorizedSchoolLocalService = authorizedSchoolLocalService;
	}

	/**
	 * Adds the authorized school to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthorizedSchoolLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authorizedSchool the authorized school
	 * @return the authorized school that was added
	 */
	@Override
	public com.weprode.nero.application.model.AuthorizedSchool
		addAuthorizedSchool(
			com.weprode.nero.application.model.AuthorizedSchool
				authorizedSchool) {

		return _authorizedSchoolLocalService.addAuthorizedSchool(
			authorizedSchool);
	}

	@Override
	public boolean addAuthorizedSchool(long applicationId, long schoolId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _authorizedSchoolLocalService.addAuthorizedSchool(
			applicationId, schoolId);
	}

	/**
	 * Creates a new authorized school with the primary key. Does not add the authorized school to the database.
	 *
	 * @param authorizedSchoolId the primary key for the new authorized school
	 * @return the new authorized school
	 */
	@Override
	public com.weprode.nero.application.model.AuthorizedSchool
		createAuthorizedSchool(long authorizedSchoolId) {

		return _authorizedSchoolLocalService.createAuthorizedSchool(
			authorizedSchoolId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorizedSchoolLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the authorized school from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthorizedSchoolLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authorizedSchool the authorized school
	 * @return the authorized school that was removed
	 */
	@Override
	public com.weprode.nero.application.model.AuthorizedSchool
		deleteAuthorizedSchool(
			com.weprode.nero.application.model.AuthorizedSchool
				authorizedSchool) {

		return _authorizedSchoolLocalService.deleteAuthorizedSchool(
			authorizedSchool);
	}

	/**
	 * Deletes the authorized school with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthorizedSchoolLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school that was removed
	 * @throws PortalException if a authorized school with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.application.model.AuthorizedSchool
			deleteAuthorizedSchool(long authorizedSchoolId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorizedSchoolLocalService.deleteAuthorizedSchool(
			authorizedSchoolId);
	}

	@Override
	public boolean deleteByApplicationId(long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _authorizedSchoolLocalService.deleteByApplicationId(
			applicationId);
	}

	@Override
	public boolean deleteByApplicationIdSchoolId(
			long applicationId, long schoolId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _authorizedSchoolLocalService.deleteByApplicationIdSchoolId(
			applicationId, schoolId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorizedSchoolLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _authorizedSchoolLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _authorizedSchoolLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _authorizedSchoolLocalService.dynamicQuery();
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

		return _authorizedSchoolLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.AuthorizedSchoolModelImpl</code>.
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

		return _authorizedSchoolLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.AuthorizedSchoolModelImpl</code>.
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

		return _authorizedSchoolLocalService.dynamicQuery(
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

		return _authorizedSchoolLocalService.dynamicQueryCount(dynamicQuery);
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

		return _authorizedSchoolLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.application.model.AuthorizedSchool
		fetchAuthorizedSchool(long authorizedSchoolId) {

		return _authorizedSchoolLocalService.fetchAuthorizedSchool(
			authorizedSchoolId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _authorizedSchoolLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the authorized school with the primary key.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school
	 * @throws PortalException if a authorized school with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.application.model.AuthorizedSchool
			getAuthorizedSchool(long authorizedSchoolId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorizedSchoolLocalService.getAuthorizedSchool(
			authorizedSchoolId);
	}

	@Override
	public java.util.List<Long> getAuthorizedSchoolIds(long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _authorizedSchoolLocalService.getAuthorizedSchoolIds(
			applicationId);
	}

	/**
	 * Returns a range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @return the range of authorized schools
	 */
	@Override
	public java.util.List<com.weprode.nero.application.model.AuthorizedSchool>
		getAuthorizedSchools(int start, int end) {

		return _authorizedSchoolLocalService.getAuthorizedSchools(start, end);
	}

	/**
	 * Returns the number of authorized schools.
	 *
	 * @return the number of authorized schools
	 */
	@Override
	public int getAuthorizedSchoolsCount() {
		return _authorizedSchoolLocalService.getAuthorizedSchoolsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _authorizedSchoolLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _authorizedSchoolLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorizedSchoolLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean isSchoolAuthorized(long applicationId, long schoolId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _authorizedSchoolLocalService.isSchoolAuthorized(
			applicationId, schoolId);
	}

	/**
	 * Updates the authorized school in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthorizedSchoolLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authorizedSchool the authorized school
	 * @return the authorized school that was updated
	 */
	@Override
	public com.weprode.nero.application.model.AuthorizedSchool
		updateAuthorizedSchool(
			com.weprode.nero.application.model.AuthorizedSchool
				authorizedSchool) {

		return _authorizedSchoolLocalService.updateAuthorizedSchool(
			authorizedSchool);
	}

	@Override
	public AuthorizedSchoolLocalService getWrappedService() {
		return _authorizedSchoolLocalService;
	}

	@Override
	public void setWrappedService(
		AuthorizedSchoolLocalService authorizedSchoolLocalService) {

		_authorizedSchoolLocalService = authorizedSchoolLocalService;
	}

	private AuthorizedSchoolLocalService _authorizedSchoolLocalService;

}