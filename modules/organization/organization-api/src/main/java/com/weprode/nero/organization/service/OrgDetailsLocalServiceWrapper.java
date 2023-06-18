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
 * Provides a wrapper for {@link OrgDetailsLocalService}.
 *
 * @author Marc Salvat
 * @see OrgDetailsLocalService
 * @generated
 */
public class OrgDetailsLocalServiceWrapper
	implements OrgDetailsLocalService, ServiceWrapper<OrgDetailsLocalService> {

	public OrgDetailsLocalServiceWrapper(
		OrgDetailsLocalService orgDetailsLocalService) {

		_orgDetailsLocalService = orgDetailsLocalService;
	}

	/**
	 * Adds the org details to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgDetails the org details
	 * @return the org details that was added
	 */
	@Override
	public com.weprode.nero.organization.model.OrgDetails addOrgDetails(
		com.weprode.nero.organization.model.OrgDetails orgDetails) {

		return _orgDetailsLocalService.addOrgDetails(orgDetails);
	}

	/**
	 * Creates a new org details with the primary key. Does not add the org details to the database.
	 *
	 * @param orgId the primary key for the new org details
	 * @return the new org details
	 */
	@Override
	public com.weprode.nero.organization.model.OrgDetails createOrgDetails(
		long orgId) {

		return _orgDetailsLocalService.createOrgDetails(orgId);
	}

	@Override
	public com.weprode.nero.organization.model.OrgDetails createOrgDetails(
		long orgId, long schoolId, String orgName, String eduLevel, int role,
		int type, boolean isArchive) {

		return _orgDetailsLocalService.createOrgDetails(
			orgId, schoolId, orgName, eduLevel, role, type, isArchive);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgDetailsLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the org details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details that was removed
	 * @throws PortalException if a org details with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.organization.model.OrgDetails deleteOrgDetails(
			long orgId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgDetailsLocalService.deleteOrgDetails(orgId);
	}

	/**
	 * Deletes the org details from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgDetails the org details
	 * @return the org details that was removed
	 */
	@Override
	public com.weprode.nero.organization.model.OrgDetails deleteOrgDetails(
		com.weprode.nero.organization.model.OrgDetails orgDetails) {

		return _orgDetailsLocalService.deleteOrgDetails(orgDetails);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _orgDetailsLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _orgDetailsLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _orgDetailsLocalService.dynamicQuery();
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

		return _orgDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.organization.model.impl.OrgDetailsModelImpl</code>.
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

		return _orgDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.organization.model.impl.OrgDetailsModelImpl</code>.
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

		return _orgDetailsLocalService.dynamicQuery(
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

		return _orgDetailsLocalService.dynamicQueryCount(dynamicQuery);
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

		return _orgDetailsLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.organization.model.OrgDetails fetchOrgDetails(
		long orgId) {

		return _orgDetailsLocalService.fetchOrgDetails(orgId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _orgDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _orgDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the org details with the primary key.
	 *
	 * @param orgId the primary key of the org details
	 * @return the org details
	 * @throws PortalException if a org details with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.organization.model.OrgDetails getOrgDetails(
			long orgId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgDetailsLocalService.getOrgDetails(orgId);
	}

	/**
	 * Returns a range of all the org detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.organization.model.impl.OrgDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org detailses
	 * @param end the upper bound of the range of org detailses (not inclusive)
	 * @return the range of org detailses
	 */
	@Override
	public java.util.List<com.weprode.nero.organization.model.OrgDetails>
		getOrgDetailses(int start, int end) {

		return _orgDetailsLocalService.getOrgDetailses(start, end);
	}

	/**
	 * Returns the number of org detailses.
	 *
	 * @return the number of org detailses
	 */
	@Override
	public int getOrgDetailsesCount() {
		return _orgDetailsLocalService.getOrgDetailsesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _orgDetailsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasType(long orgId, int type) {
		return _orgDetailsLocalService.hasType(orgId, type);
	}

	@Override
	public boolean isArchived(long orgId) {
		return _orgDetailsLocalService.isArchived(orgId);
	}

	@Override
	public boolean isClass(long orgId) {
		return _orgDetailsLocalService.isClass(orgId);
	}

	@Override
	public boolean isCours(long orgId) {
		return _orgDetailsLocalService.isCours(orgId);
	}

	@Override
	public boolean isSchool(long orgId) {
		return _orgDetailsLocalService.isSchool(orgId);
	}

	@Override
	public boolean isSubject(long orgId) {
		return _orgDetailsLocalService.isSubject(orgId);
	}

	@Override
	public boolean isVolee(long orgId) {
		return _orgDetailsLocalService.isVolee(orgId);
	}

	/**
	 * Updates the org details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgDetails the org details
	 * @return the org details that was updated
	 */
	@Override
	public com.weprode.nero.organization.model.OrgDetails updateOrgDetails(
		com.weprode.nero.organization.model.OrgDetails orgDetails) {

		return _orgDetailsLocalService.updateOrgDetails(orgDetails);
	}

	@Override
	public OrgDetailsLocalService getWrappedService() {
		return _orgDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		OrgDetailsLocalService orgDetailsLocalService) {

		_orgDetailsLocalService = orgDetailsLocalService;
	}

	private OrgDetailsLocalService _orgDetailsLocalService;

}