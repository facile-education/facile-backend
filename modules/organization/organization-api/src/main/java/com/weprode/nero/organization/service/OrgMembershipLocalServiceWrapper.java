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

package com.weprode.nero.organization.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OrgMembershipLocalService}.
 *
 * @author Marc Salvat
 * @see OrgMembershipLocalService
 * @generated
 */
public class OrgMembershipLocalServiceWrapper
	implements OrgMembershipLocalService,
			   ServiceWrapper<OrgMembershipLocalService> {

	public OrgMembershipLocalServiceWrapper(
		OrgMembershipLocalService orgMembershipLocalService) {

		_orgMembershipLocalService = orgMembershipLocalService;
	}

	@Override
	public com.weprode.nero.organization.model.OrgMembership addMembership(
		long userId, long groupId, java.util.Date startDate,
		java.util.Date endDate) {

		return _orgMembershipLocalService.addMembership(
			userId, groupId, startDate, endDate);
	}

	/**
	 * Adds the org membership to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgMembershipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgMembership the org membership
	 * @return the org membership that was added
	 */
	@Override
	public com.weprode.nero.organization.model.OrgMembership addOrgMembership(
		com.weprode.nero.organization.model.OrgMembership orgMembership) {

		return _orgMembershipLocalService.addOrgMembership(orgMembership);
	}

	/**
	 * Creates a new org membership with the primary key. Does not add the org membership to the database.
	 *
	 * @param orgMemberId the primary key for the new org membership
	 * @return the new org membership
	 */
	@Override
	public com.weprode.nero.organization.model.OrgMembership
		createOrgMembership(long orgMemberId) {

		return _orgMembershipLocalService.createOrgMembership(orgMemberId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMembershipLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the org membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgMembershipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership that was removed
	 * @throws PortalException if a org membership with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.organization.model.OrgMembership
			deleteOrgMembership(long orgMemberId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMembershipLocalService.deleteOrgMembership(orgMemberId);
	}

	/**
	 * Deletes the org membership from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgMembershipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgMembership the org membership
	 * @return the org membership that was removed
	 */
	@Override
	public com.weprode.nero.organization.model.OrgMembership
		deleteOrgMembership(
			com.weprode.nero.organization.model.OrgMembership orgMembership) {

		return _orgMembershipLocalService.deleteOrgMembership(orgMembership);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMembershipLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _orgMembershipLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _orgMembershipLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _orgMembershipLocalService.dynamicQuery();
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

		return _orgMembershipLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.organization.model.impl.OrgMembershipModelImpl</code>.
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

		return _orgMembershipLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.organization.model.impl.OrgMembershipModelImpl</code>.
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

		return _orgMembershipLocalService.dynamicQuery(
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

		return _orgMembershipLocalService.dynamicQueryCount(dynamicQuery);
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

		return _orgMembershipLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.organization.model.OrgMembership fetchOrgMembership(
		long orgMemberId) {

		return _orgMembershipLocalService.fetchOrgMembership(orgMemberId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _orgMembershipLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _orgMembershipLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getOrgMembers(
		long groupId, java.util.Date date) {

		return _orgMembershipLocalService.getOrgMembers(groupId, date);
	}

	/**
	 * Returns the org membership with the primary key.
	 *
	 * @param orgMemberId the primary key of the org membership
	 * @return the org membership
	 * @throws PortalException if a org membership with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.organization.model.OrgMembership getOrgMembership(
			long orgMemberId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMembershipLocalService.getOrgMembership(orgMemberId);
	}

	/**
	 * Returns a range of all the org memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.organization.model.impl.OrgMembershipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org memberships
	 * @param end the upper bound of the range of org memberships (not inclusive)
	 * @return the range of org memberships
	 */
	@Override
	public java.util.List<com.weprode.nero.organization.model.OrgMembership>
		getOrgMemberships(int start, int end) {

		return _orgMembershipLocalService.getOrgMemberships(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.organization.model.OrgMembership>
		getOrgMemberships(long userId, long groupId) {

		return _orgMembershipLocalService.getOrgMemberships(userId, groupId);
	}

	/**
	 * Returns the number of org memberships.
	 *
	 * @return the number of org memberships
	 */
	@Override
	public int getOrgMembershipsCount() {
		return _orgMembershipLocalService.getOrgMembershipsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _orgMembershipLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMembershipLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.weprode.nero.organization.model.OrgMembership>
		getStudentOrgMemberships(long userId) {

		return _orgMembershipLocalService.getStudentOrgMemberships(userId);
	}

	@Override
	public boolean isStudentOrgMember(
		long userId, long groupId, java.util.Date date) {

		return _orgMembershipLocalService.isStudentOrgMember(
			userId, groupId, date);
	}

	@Override
	public boolean removeOrgMemberships(long groupId) {
		return _orgMembershipLocalService.removeOrgMemberships(groupId);
	}

	@Override
	public boolean removeUserMemberships(long userId) {
		return _orgMembershipLocalService.removeUserMemberships(userId);
	}

	/**
	 * Updates the org membership in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgMembershipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgMembership the org membership
	 * @return the org membership that was updated
	 */
	@Override
	public com.weprode.nero.organization.model.OrgMembership
		updateOrgMembership(
			com.weprode.nero.organization.model.OrgMembership orgMembership) {

		return _orgMembershipLocalService.updateOrgMembership(orgMembership);
	}

	@Override
	public OrgMembershipLocalService getWrappedService() {
		return _orgMembershipLocalService;
	}

	@Override
	public void setWrappedService(
		OrgMembershipLocalService orgMembershipLocalService) {

		_orgMembershipLocalService = orgMembershipLocalService;
	}

	private OrgMembershipLocalService _orgMembershipLocalService;

}