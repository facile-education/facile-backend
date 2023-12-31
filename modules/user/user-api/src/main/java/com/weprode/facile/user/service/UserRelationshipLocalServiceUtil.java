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

package com.weprode.facile.user.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.user.model.UserRelationship;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for UserRelationship. This utility wraps
 * <code>com.weprode.facile.user.service.impl.UserRelationshipLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserRelationshipLocalService
 * @generated
 */
public class UserRelationshipLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.user.service.impl.UserRelationshipLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the user relationship to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRelationshipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRelationship the user relationship
	 * @return the user relationship that was added
	 */
	public static UserRelationship addUserRelationship(
		UserRelationship userRelationship) {

		return getService().addUserRelationship(userRelationship);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static UserRelationship createUserRelationship(
		long childUserId, long parentUserId) {

		return getService().createUserRelationship(childUserId, parentUserId);
	}

	/**
	 * Creates a new user relationship with the primary key. Does not add the user relationship to the database.
	 *
	 * @param userRelationshipPK the primary key for the new user relationship
	 * @return the new user relationship
	 */
	public static UserRelationship createUserRelationship(
		com.weprode.facile.user.service.persistence.UserRelationshipPK
			userRelationshipPK) {

		return getService().createUserRelationship(userRelationshipPK);
	}

	public static void deleteChild(long childId) {
		getService().deleteChild(childId);
	}

	public static void deleteParent(long parentId) {
		getService().deleteParent(parentId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user relationship from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRelationshipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRelationship the user relationship
	 * @return the user relationship that was removed
	 */
	public static UserRelationship deleteUserRelationship(
		UserRelationship userRelationship) {

		return getService().deleteUserRelationship(userRelationship);
	}

	/**
	 * Deletes the user relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRelationshipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship that was removed
	 * @throws PortalException if a user relationship with the primary key could not be found
	 */
	public static UserRelationship deleteUserRelationship(
			com.weprode.facile.user.service.persistence.UserRelationshipPK
				userRelationshipPK)
		throws PortalException {

		return getService().deleteUserRelationship(userRelationshipPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.UserRelationshipModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.UserRelationshipModelImpl</code>.
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

	public static UserRelationship fetchUserRelationship(
		com.weprode.facile.user.service.persistence.UserRelationshipPK
			userRelationshipPK) {

		return getService().fetchUserRelationship(userRelationshipPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<com.liferay.portal.kernel.model.User> getAllRelatives(
		long parentId) {

		return getService().getAllRelatives(parentId);
	}

	public static List<com.liferay.portal.kernel.model.User> getChildren(
		long parentId) {

		return getService().getChildren(parentId);
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

	public static List<com.liferay.portal.kernel.model.User> getParents(
		long childId) {

		return getService().getParents(childId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user relationship with the primary key.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship
	 * @throws PortalException if a user relationship with the primary key could not be found
	 */
	public static UserRelationship getUserRelationship(
			com.weprode.facile.user.service.persistence.UserRelationshipPK
				userRelationshipPK)
		throws PortalException {

		return getService().getUserRelationship(userRelationshipPK);
	}

	/**
	 * Returns a range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of user relationships
	 */
	public static List<UserRelationship> getUserRelationships(
		int start, int end) {

		return getService().getUserRelationships(start, end);
	}

	/**
	 * Returns the number of user relationships.
	 *
	 * @return the number of user relationships
	 */
	public static int getUserRelationshipsCount() {
		return getService().getUserRelationshipsCount();
	}

	public static boolean isChild(long parentId, long childId) {
		return getService().isChild(parentId, childId);
	}

	/**
	 * Updates the user relationship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRelationshipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRelationship the user relationship
	 * @return the user relationship that was updated
	 */
	public static UserRelationship updateUserRelationship(
		UserRelationship userRelationship) {

		return getService().updateUserRelationship(userRelationship);
	}

	public static UserRelationshipLocalService getService() {
		return _service;
	}

	private static volatile UserRelationshipLocalService _service;

}