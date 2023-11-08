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

package com.weprode.facile.access.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.access.model.AccessProfile;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AccessProfile. This utility wraps
 * <code>com.weprode.facile.access.service.impl.AccessProfileLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccessProfileLocalService
 * @generated
 */
public class AccessProfileLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.access.service.impl.AccessProfileLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the access profile to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessProfileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessProfile the access profile
	 * @return the access profile that was added
	 */
	public static AccessProfile addAccessProfile(AccessProfile accessProfile) {
		return getService().addAccessProfile(accessProfile);
	}

	public static AccessProfile addAccessProfile(long accessId, long roleId) {
		return getService().addAccessProfile(accessId, roleId);
	}

	/**
	 * Creates a new access profile with the primary key. Does not add the access profile to the database.
	 *
	 * @param accessProfilePK the primary key for the new access profile
	 * @return the new access profile
	 */
	public static AccessProfile createAccessProfile(
		com.weprode.facile.access.service.persistence.AccessProfilePK
			accessProfilePK) {

		return getService().createAccessProfile(accessProfilePK);
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
	 * Deletes the access profile from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessProfileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessProfile the access profile
	 * @return the access profile that was removed
	 */
	public static AccessProfile deleteAccessProfile(
		AccessProfile accessProfile) {

		return getService().deleteAccessProfile(accessProfile);
	}

	/**
	 * Deletes the access profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessProfileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile that was removed
	 * @throws PortalException if a access profile with the primary key could not be found
	 */
	public static AccessProfile deleteAccessProfile(
			com.weprode.facile.access.service.persistence.AccessProfilePK
				accessProfilePK)
		throws PortalException {

		return getService().deleteAccessProfile(accessProfilePK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.access.model.impl.AccessProfileModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.access.model.impl.AccessProfileModelImpl</code>.
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

	public static AccessProfile fetchAccessProfile(
		com.weprode.facile.access.service.persistence.AccessProfilePK
			accessProfilePK) {

		return getService().fetchAccessProfile(accessProfilePK);
	}

	/**
	 * Returns the access profile with the primary key.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile
	 * @throws PortalException if a access profile with the primary key could not be found
	 */
	public static AccessProfile getAccessProfile(
			com.weprode.facile.access.service.persistence.AccessProfilePK
				accessProfilePK)
		throws PortalException {

		return getService().getAccessProfile(accessProfilePK);
	}

	/**
	 * Returns a range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.access.model.impl.AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of access profiles
	 */
	public static List<AccessProfile> getAccessProfiles(int start, int end) {
		return getService().getAccessProfiles(start, end);
	}

	public static org.json.JSONArray getAccessProfiles(long accessId) {
		return getService().getAccessProfiles(accessId);
	}

	/**
	 * Returns the number of access profiles.
	 *
	 * @return the number of access profiles
	 */
	public static int getAccessProfilesCount() {
		return getService().getAccessProfilesCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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

	public static boolean hasRoleAccess(long roleId, long accessId) {
		return getService().hasRoleAccess(roleId, accessId);
	}

	public static boolean hasUserAccess(long userId, long accessId) {
		return getService().hasUserAccess(userId, accessId);
	}

	public static void removeByAccessId(long accessId) {
		getService().removeByAccessId(accessId);
	}

	/**
	 * Updates the access profile in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessProfileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessProfile the access profile
	 * @return the access profile that was updated
	 */
	public static AccessProfile updateAccessProfile(
		AccessProfile accessProfile) {

		return getService().updateAccessProfile(accessProfile);
	}

	public static AccessProfileLocalService getService() {
		return _service;
	}

	private static volatile AccessProfileLocalService _service;

}