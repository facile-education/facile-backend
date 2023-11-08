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

package com.weprode.facile.application.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.application.model.AuthorizedSchool;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AuthorizedSchool. This utility wraps
 * <code>com.weprode.facile.application.service.impl.AuthorizedSchoolLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AuthorizedSchoolLocalService
 * @generated
 */
public class AuthorizedSchoolLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.application.service.impl.AuthorizedSchoolLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static AuthorizedSchool addAuthorizedSchool(
		AuthorizedSchool authorizedSchool) {

		return getService().addAuthorizedSchool(authorizedSchool);
	}

	public static boolean addAuthorizedSchool(long applicationId, long schoolId)
		throws SystemException {

		return getService().addAuthorizedSchool(applicationId, schoolId);
	}

	/**
	 * Creates a new authorized school with the primary key. Does not add the authorized school to the database.
	 *
	 * @param authorizedSchoolId the primary key for the new authorized school
	 * @return the new authorized school
	 */
	public static AuthorizedSchool createAuthorizedSchool(
		long authorizedSchoolId) {

		return getService().createAuthorizedSchool(authorizedSchoolId);
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
	 * Deletes the authorized school from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthorizedSchoolLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authorizedSchool the authorized school
	 * @return the authorized school that was removed
	 */
	public static AuthorizedSchool deleteAuthorizedSchool(
		AuthorizedSchool authorizedSchool) {

		return getService().deleteAuthorizedSchool(authorizedSchool);
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
	public static AuthorizedSchool deleteAuthorizedSchool(
			long authorizedSchoolId)
		throws PortalException {

		return getService().deleteAuthorizedSchool(authorizedSchoolId);
	}

	public static boolean deleteByApplicationId(long applicationId)
		throws SystemException {

		return getService().deleteByApplicationId(applicationId);
	}

	public static boolean deleteByApplicationIdSchoolId(
			long applicationId, long schoolId)
		throws SystemException {

		return getService().deleteByApplicationIdSchoolId(
			applicationId, schoolId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.application.model.impl.AuthorizedSchoolModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.application.model.impl.AuthorizedSchoolModelImpl</code>.
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

	public static AuthorizedSchool fetchAuthorizedSchool(
		long authorizedSchoolId) {

		return getService().fetchAuthorizedSchool(authorizedSchoolId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the authorized school with the primary key.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school
	 * @throws PortalException if a authorized school with the primary key could not be found
	 */
	public static AuthorizedSchool getAuthorizedSchool(long authorizedSchoolId)
		throws PortalException {

		return getService().getAuthorizedSchool(authorizedSchoolId);
	}

	public static List<Long> getAuthorizedSchoolIds(long applicationId)
		throws SystemException {

		return getService().getAuthorizedSchoolIds(applicationId);
	}

	/**
	 * Returns a range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.application.model.impl.AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @return the range of authorized schools
	 */
	public static List<AuthorizedSchool> getAuthorizedSchools(
		int start, int end) {

		return getService().getAuthorizedSchools(start, end);
	}

	/**
	 * Returns the number of authorized schools.
	 *
	 * @return the number of authorized schools
	 */
	public static int getAuthorizedSchoolsCount() {
		return getService().getAuthorizedSchoolsCount();
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

	public static boolean isSchoolAuthorized(long applicationId, long schoolId)
		throws SystemException {

		return getService().isSchoolAuthorized(applicationId, schoolId);
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
	public static AuthorizedSchool updateAuthorizedSchool(
		AuthorizedSchool authorizedSchool) {

		return getService().updateAuthorizedSchool(authorizedSchool);
	}

	public static AuthorizedSchoolLocalService getService() {
		return _service;
	}

	private static volatile AuthorizedSchoolLocalService _service;

}