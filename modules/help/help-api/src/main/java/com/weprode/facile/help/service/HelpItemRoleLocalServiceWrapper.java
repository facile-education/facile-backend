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

package com.weprode.facile.help.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HelpItemRoleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpItemRoleLocalService
 * @generated
 */
public class HelpItemRoleLocalServiceWrapper
	implements HelpItemRoleLocalService,
			   ServiceWrapper<HelpItemRoleLocalService> {

	public HelpItemRoleLocalServiceWrapper() {
		this(null);
	}

	public HelpItemRoleLocalServiceWrapper(
		HelpItemRoleLocalService helpItemRoleLocalService) {

		_helpItemRoleLocalService = helpItemRoleLocalService;
	}

	/**
	 * Adds the help item role to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItemRole the help item role
	 * @return the help item role that was added
	 */
	@Override
	public com.weprode.facile.help.model.HelpItemRole addHelpItemRole(
		com.weprode.facile.help.model.HelpItemRole helpItemRole) {

		return _helpItemRoleLocalService.addHelpItemRole(helpItemRole);
	}

	@Override
	public boolean addItemRole(long itemId, long roleId) {
		return _helpItemRoleLocalService.addItemRole(itemId, roleId);
	}

	/**
	 * Creates a new help item role with the primary key. Does not add the help item role to the database.
	 *
	 * @param helpItemRoleId the primary key for the new help item role
	 * @return the new help item role
	 */
	@Override
	public com.weprode.facile.help.model.HelpItemRole createHelpItemRole(
		long helpItemRoleId) {

		return _helpItemRoleLocalService.createHelpItemRole(helpItemRoleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemRoleLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the help item role from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItemRole the help item role
	 * @return the help item role that was removed
	 */
	@Override
	public com.weprode.facile.help.model.HelpItemRole deleteHelpItemRole(
		com.weprode.facile.help.model.HelpItemRole helpItemRole) {

		return _helpItemRoleLocalService.deleteHelpItemRole(helpItemRole);
	}

	/**
	 * Deletes the help item role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role that was removed
	 * @throws PortalException if a help item role with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.help.model.HelpItemRole deleteHelpItemRole(
			long helpItemRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemRoleLocalService.deleteHelpItemRole(helpItemRoleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemRoleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _helpItemRoleLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _helpItemRoleLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helpItemRoleLocalService.dynamicQuery();
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

		return _helpItemRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.help.model.impl.HelpItemRoleModelImpl</code>.
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

		return _helpItemRoleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.help.model.impl.HelpItemRoleModelImpl</code>.
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

		return _helpItemRoleLocalService.dynamicQuery(
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

		return _helpItemRoleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _helpItemRoleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.help.model.HelpItemRole fetchHelpItemRole(
		long helpItemRoleId) {

		return _helpItemRoleLocalService.fetchHelpItemRole(helpItemRoleId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _helpItemRoleLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the help item role with the primary key.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role
	 * @throws PortalException if a help item role with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.help.model.HelpItemRole getHelpItemRole(
			long helpItemRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemRoleLocalService.getHelpItemRole(helpItemRoleId);
	}

	/**
	 * Returns a range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.help.model.impl.HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @return the range of help item roles
	 */
	@Override
	public java.util.List<com.weprode.facile.help.model.HelpItemRole>
		getHelpItemRoles(int start, int end) {

		return _helpItemRoleLocalService.getHelpItemRoles(start, end);
	}

	/**
	 * Returns the number of help item roles.
	 *
	 * @return the number of help item roles
	 */
	@Override
	public int getHelpItemRolesCount() {
		return _helpItemRoleLocalService.getHelpItemRolesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _helpItemRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpItemRoleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean isUserAllowedToSeeItem(
		com.liferay.portal.kernel.model.User user, long itemId) {

		return _helpItemRoleLocalService.isUserAllowedToSeeItem(user, itemId);
	}

	/**
	 * Updates the help item role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItemRole the help item role
	 * @return the help item role that was updated
	 */
	@Override
	public com.weprode.facile.help.model.HelpItemRole updateHelpItemRole(
		com.weprode.facile.help.model.HelpItemRole helpItemRole) {

		return _helpItemRoleLocalService.updateHelpItemRole(helpItemRole);
	}

	@Override
	public HelpItemRoleLocalService getWrappedService() {
		return _helpItemRoleLocalService;
	}

	@Override
	public void setWrappedService(
		HelpItemRoleLocalService helpItemRoleLocalService) {

		_helpItemRoleLocalService = helpItemRoleLocalService;
	}

	private HelpItemRoleLocalService _helpItemRoleLocalService;

}