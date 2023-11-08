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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccessProfileLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccessProfileLocalService
 * @generated
 */
public class AccessProfileLocalServiceWrapper
	implements AccessProfileLocalService,
			   ServiceWrapper<AccessProfileLocalService> {

	public AccessProfileLocalServiceWrapper() {
		this(null);
	}

	public AccessProfileLocalServiceWrapper(
		AccessProfileLocalService accessProfileLocalService) {

		_accessProfileLocalService = accessProfileLocalService;
	}

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
	@Override
	public com.weprode.facile.access.model.AccessProfile addAccessProfile(
		com.weprode.facile.access.model.AccessProfile accessProfile) {

		return _accessProfileLocalService.addAccessProfile(accessProfile);
	}

	@Override
	public com.weprode.facile.access.model.AccessProfile addAccessProfile(
		long accessId, long roleId) {

		return _accessProfileLocalService.addAccessProfile(accessId, roleId);
	}

	/**
	 * Creates a new access profile with the primary key. Does not add the access profile to the database.
	 *
	 * @param accessProfilePK the primary key for the new access profile
	 * @return the new access profile
	 */
	@Override
	public com.weprode.facile.access.model.AccessProfile createAccessProfile(
		com.weprode.facile.access.service.persistence.AccessProfilePK
			accessProfilePK) {

		return _accessProfileLocalService.createAccessProfile(accessProfilePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessProfileLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public com.weprode.facile.access.model.AccessProfile deleteAccessProfile(
		com.weprode.facile.access.model.AccessProfile accessProfile) {

		return _accessProfileLocalService.deleteAccessProfile(accessProfile);
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
	@Override
	public com.weprode.facile.access.model.AccessProfile deleteAccessProfile(
			com.weprode.facile.access.service.persistence.AccessProfilePK
				accessProfilePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessProfileLocalService.deleteAccessProfile(accessProfilePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessProfileLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _accessProfileLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _accessProfileLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accessProfileLocalService.dynamicQuery();
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

		return _accessProfileLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _accessProfileLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _accessProfileLocalService.dynamicQuery(
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

		return _accessProfileLocalService.dynamicQueryCount(dynamicQuery);
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

		return _accessProfileLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.access.model.AccessProfile fetchAccessProfile(
		com.weprode.facile.access.service.persistence.AccessProfilePK
			accessProfilePK) {

		return _accessProfileLocalService.fetchAccessProfile(accessProfilePK);
	}

	/**
	 * Returns the access profile with the primary key.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile
	 * @throws PortalException if a access profile with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.access.model.AccessProfile getAccessProfile(
			com.weprode.facile.access.service.persistence.AccessProfilePK
				accessProfilePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessProfileLocalService.getAccessProfile(accessProfilePK);
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
	@Override
	public java.util.List<com.weprode.facile.access.model.AccessProfile>
		getAccessProfiles(int start, int end) {

		return _accessProfileLocalService.getAccessProfiles(start, end);
	}

	@Override
	public org.json.JSONArray getAccessProfiles(long accessId) {
		return _accessProfileLocalService.getAccessProfiles(accessId);
	}

	/**
	 * Returns the number of access profiles.
	 *
	 * @return the number of access profiles
	 */
	@Override
	public int getAccessProfilesCount() {
		return _accessProfileLocalService.getAccessProfilesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _accessProfileLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _accessProfileLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accessProfileLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessProfileLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasRoleAccess(long roleId, long accessId) {
		return _accessProfileLocalService.hasRoleAccess(roleId, accessId);
	}

	@Override
	public boolean hasUserAccess(long userId, long accessId) {
		return _accessProfileLocalService.hasUserAccess(userId, accessId);
	}

	@Override
	public void removeByAccessId(long accessId) {
		_accessProfileLocalService.removeByAccessId(accessId);
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
	@Override
	public com.weprode.facile.access.model.AccessProfile updateAccessProfile(
		com.weprode.facile.access.model.AccessProfile accessProfile) {

		return _accessProfileLocalService.updateAccessProfile(accessProfile);
	}

	@Override
	public AccessProfileLocalService getWrappedService() {
		return _accessProfileLocalService;
	}

	@Override
	public void setWrappedService(
		AccessProfileLocalService accessProfileLocalService) {

		_accessProfileLocalService = accessProfileLocalService;
	}

	private AccessProfileLocalService _accessProfileLocalService;

}