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
 * Provides a wrapper for {@link DefaultRoleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DefaultRoleLocalService
 * @generated
 */
public class DefaultRoleLocalServiceWrapper
	implements DefaultRoleLocalService,
			   ServiceWrapper<DefaultRoleLocalService> {

	public DefaultRoleLocalServiceWrapper() {
		this(null);
	}

	public DefaultRoleLocalServiceWrapper(
		DefaultRoleLocalService defaultRoleLocalService) {

		_defaultRoleLocalService = defaultRoleLocalService;
	}

	/**
	 * Adds the default role to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DefaultRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param defaultRole the default role
	 * @return the default role that was added
	 */
	@Override
	public com.weprode.nero.application.model.DefaultRole addDefaultRole(
		com.weprode.nero.application.model.DefaultRole defaultRole) {

		return _defaultRoleLocalService.addDefaultRole(defaultRole);
	}

	@Override
	public boolean addDefaultRole(long roleId, long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _defaultRoleLocalService.addDefaultRole(roleId, applicationId);
	}

	/**
	 * Creates a new default role with the primary key. Does not add the default role to the database.
	 *
	 * @param defaultRoleId the primary key for the new default role
	 * @return the new default role
	 */
	@Override
	public com.weprode.nero.application.model.DefaultRole createDefaultRole(
		long defaultRoleId) {

		return _defaultRoleLocalService.createDefaultRole(defaultRoleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _defaultRoleLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the default role from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DefaultRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param defaultRole the default role
	 * @return the default role that was removed
	 */
	@Override
	public com.weprode.nero.application.model.DefaultRole deleteDefaultRole(
		com.weprode.nero.application.model.DefaultRole defaultRole) {

		return _defaultRoleLocalService.deleteDefaultRole(defaultRole);
	}

	/**
	 * Deletes the default role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DefaultRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role that was removed
	 * @throws PortalException if a default role with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.application.model.DefaultRole deleteDefaultRole(
			long defaultRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _defaultRoleLocalService.deleteDefaultRole(defaultRoleId);
	}

	@Override
	public boolean deleteDefaultRole(long roleId, long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.application.exception.
				   NoSuchDefaultRoleException {

		return _defaultRoleLocalService.deleteDefaultRole(
			roleId, applicationId);
	}

	@Override
	public boolean deleteDefaultRoleByApplicationId(long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _defaultRoleLocalService.deleteDefaultRoleByApplicationId(
			applicationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _defaultRoleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _defaultRoleLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _defaultRoleLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _defaultRoleLocalService.dynamicQuery();
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

		return _defaultRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.DefaultRoleModelImpl</code>.
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

		return _defaultRoleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.DefaultRoleModelImpl</code>.
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

		return _defaultRoleLocalService.dynamicQuery(
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

		return _defaultRoleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _defaultRoleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.application.model.DefaultRole fetchDefaultRole(
		long defaultRoleId) {

		return _defaultRoleLocalService.fetchDefaultRole(defaultRoleId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _defaultRoleLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the default role with the primary key.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role
	 * @throws PortalException if a default role with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.application.model.DefaultRole getDefaultRole(
			long defaultRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _defaultRoleLocalService.getDefaultRole(defaultRoleId);
	}

	@Override
	public org.json.JSONArray getDefaultRoleJson(long applicationId) {
		return _defaultRoleLocalService.getDefaultRoleJson(applicationId);
	}

	/**
	 * Returns a range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @return the range of default roles
	 */
	@Override
	public java.util.List<com.weprode.nero.application.model.DefaultRole>
		getDefaultRoles(int start, int end) {

		return _defaultRoleLocalService.getDefaultRoles(start, end);
	}

	/**
	 * Returns the number of default roles.
	 *
	 * @return the number of default roles
	 */
	@Override
	public int getDefaultRolesCount() {
		return _defaultRoleLocalService.getDefaultRolesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _defaultRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _defaultRoleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _defaultRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasUserRole(long applicationId, long userId) {
		return _defaultRoleLocalService.hasUserRole(applicationId, userId);
	}

	/**
	 * Updates the default role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DefaultRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param defaultRole the default role
	 * @return the default role that was updated
	 */
	@Override
	public com.weprode.nero.application.model.DefaultRole updateDefaultRole(
		com.weprode.nero.application.model.DefaultRole defaultRole) {

		return _defaultRoleLocalService.updateDefaultRole(defaultRole);
	}

	@Override
	public DefaultRoleLocalService getWrappedService() {
		return _defaultRoleLocalService;
	}

	@Override
	public void setWrappedService(
		DefaultRoleLocalService defaultRoleLocalService) {

		_defaultRoleLocalService = defaultRoleLocalService;
	}

	private DefaultRoleLocalService _defaultRoleLocalService;

}