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

package com.weprode.nero.access.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccessLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccessLocalService
 * @generated
 */
public class AccessLocalServiceWrapper
	implements AccessLocalService, ServiceWrapper<AccessLocalService> {

	public AccessLocalServiceWrapper(AccessLocalService accessLocalService) {
		_accessLocalService = accessLocalService;
	}

	/**
	 * Adds the access to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param access the access
	 * @return the access that was added
	 */
	@Override
	public com.weprode.nero.access.model.Access addAccess(
		com.weprode.nero.access.model.Access access) {

		return _accessLocalService.addAccess(access);
	}

	@Override
	public com.weprode.nero.access.model.Access addAccess(
		long userId, long categoryId, String title, int type, String url,
		long folderId, long fileId, long thumbnailId, int position) {

		return _accessLocalService.addAccess(
			userId, categoryId, title, type, url, folderId, fileId, thumbnailId,
			position);
	}

	@Override
	public org.json.JSONObject convertToJson(
		com.weprode.nero.access.model.Access access) {

		return _accessLocalService.convertToJson(access);
	}

	/**
	 * Creates a new access with the primary key. Does not add the access to the database.
	 *
	 * @param accessId the primary key for the new access
	 * @return the new access
	 */
	@Override
	public com.weprode.nero.access.model.Access createAccess(long accessId) {
		return _accessLocalService.createAccess(accessId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the access from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param access the access
	 * @return the access that was removed
	 */
	@Override
	public com.weprode.nero.access.model.Access deleteAccess(
		com.weprode.nero.access.model.Access access) {

		return _accessLocalService.deleteAccess(access);
	}

	/**
	 * Deletes the access with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessId the primary key of the access
	 * @return the access that was removed
	 * @throws PortalException if a access with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.access.model.Access deleteAccess(long accessId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessLocalService.deleteAccess(accessId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _accessLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _accessLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accessLocalService.dynamicQuery();
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

		return _accessLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.access.model.impl.AccessModelImpl</code>.
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

		return _accessLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.access.model.impl.AccessModelImpl</code>.
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

		return _accessLocalService.dynamicQuery(
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

		return _accessLocalService.dynamicQueryCount(dynamicQuery);
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

		return _accessLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.access.model.Access fetchAccess(long accessId) {
		return _accessLocalService.fetchAccess(accessId);
	}

	/**
	 * Returns the access with the primary key.
	 *
	 * @param accessId the primary key of the access
	 * @return the access
	 * @throws PortalException if a access with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.access.model.Access getAccess(long accessId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessLocalService.getAccess(accessId);
	}

	/**
	 * Returns a range of all the accesses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.access.model.impl.AccessModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @return the range of accesses
	 */
	@Override
	public java.util.List<com.weprode.nero.access.model.Access> getAccesses(
		int start, int end) {

		return _accessLocalService.getAccesses(start, end);
	}

	/**
	 * Returns the number of accesses.
	 *
	 * @return the number of accesses
	 */
	@Override
	public int getAccessesCount() {
		return _accessLocalService.getAccessesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _accessLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _accessLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accessLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.json.JSONArray getRoleAccesses(long schoolId, long roleId) {
		return _accessLocalService.getRoleAccesses(schoolId, roleId);
	}

	@Override
	public org.json.JSONArray getSchoolAccesses(long schoolId) {
		return _accessLocalService.getSchoolAccesses(schoolId);
	}

	@Override
	public org.json.JSONArray getUserAccesses(
		com.liferay.portal.kernel.model.User user) {

		return _accessLocalService.getUserAccesses(user);
	}

	@Override
	public void removeByCategoryId(long categoryId) {
		_accessLocalService.removeByCategoryId(categoryId);
	}

	@Override
	public void saveSchoolAccesses(
		com.liferay.portal.kernel.model.User user, long schoolId,
		String accesses) {

		_accessLocalService.saveSchoolAccesses(user, schoolId, accesses);
	}

	/**
	 * Updates the access in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param access the access
	 * @return the access that was updated
	 */
	@Override
	public com.weprode.nero.access.model.Access updateAccess(
		com.weprode.nero.access.model.Access access) {

		return _accessLocalService.updateAccess(access);
	}

	@Override
	public AccessLocalService getWrappedService() {
		return _accessLocalService;
	}

	@Override
	public void setWrappedService(AccessLocalService accessLocalService) {
		_accessLocalService = accessLocalService;
	}

	private AccessLocalService _accessLocalService;

}