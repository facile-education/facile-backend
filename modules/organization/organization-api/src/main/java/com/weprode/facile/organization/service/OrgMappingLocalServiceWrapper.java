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

package com.weprode.facile.organization.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OrgMappingLocalService}.
 *
 * @author Marc Salvat
 * @see OrgMappingLocalService
 * @generated
 */
public class OrgMappingLocalServiceWrapper
	implements OrgMappingLocalService, ServiceWrapper<OrgMappingLocalService> {

	public OrgMappingLocalServiceWrapper() {
		this(null);
	}

	public OrgMappingLocalServiceWrapper(
		OrgMappingLocalService orgMappingLocalService) {

		_orgMappingLocalService = orgMappingLocalService;
	}

	@Override
	public com.weprode.facile.organization.model.OrgMapping addOrgMapping(
			com.liferay.portal.kernel.model.Organization organization,
			String uai)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _orgMappingLocalService.addOrgMapping(organization, uai);
	}

	/**
	 * Adds the org mapping to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgMapping the org mapping
	 * @return the org mapping that was added
	 */
	@Override
	public com.weprode.facile.organization.model.OrgMapping addOrgMapping(
		com.weprode.facile.organization.model.OrgMapping orgMapping) {

		return _orgMappingLocalService.addOrgMapping(orgMapping);
	}

	/**
	 * Creates a new org mapping with the primary key. Does not add the org mapping to the database.
	 *
	 * @param entStructureUAI the primary key for the new org mapping
	 * @return the new org mapping
	 */
	@Override
	public com.weprode.facile.organization.model.OrgMapping createOrgMapping(
		String entStructureUAI) {

		return _orgMappingLocalService.createOrgMapping(entStructureUAI);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMappingLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public void deleteOrgMapping(long organizationId)
		throws com.weprode.facile.organization.exception.
			NoSuchOrgMappingException {

		_orgMappingLocalService.deleteOrgMapping(organizationId);
	}

	/**
	 * Deletes the org mapping from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgMapping the org mapping
	 * @return the org mapping that was removed
	 */
	@Override
	public com.weprode.facile.organization.model.OrgMapping deleteOrgMapping(
		com.weprode.facile.organization.model.OrgMapping orgMapping) {

		return _orgMappingLocalService.deleteOrgMapping(orgMapping);
	}

	/**
	 * Deletes the org mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping that was removed
	 * @throws PortalException if a org mapping with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.organization.model.OrgMapping deleteOrgMapping(
			String entStructureUAI)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMappingLocalService.deleteOrgMapping(entStructureUAI);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMappingLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _orgMappingLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _orgMappingLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _orgMappingLocalService.dynamicQuery();
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

		return _orgMappingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.organization.model.impl.OrgMappingModelImpl</code>.
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

		return _orgMappingLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.organization.model.impl.OrgMappingModelImpl</code>.
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

		return _orgMappingLocalService.dynamicQuery(
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

		return _orgMappingLocalService.dynamicQueryCount(dynamicQuery);
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

		return _orgMappingLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.organization.model.OrgMapping fetchOrgMapping(
		String entStructureUAI) {

		return _orgMappingLocalService.fetchOrgMapping(entStructureUAI);
	}

	/**
	 * Returns the structure UAI from the organization if it exist.
	 */
	@Override
	public String getOrganizationStrutUAI(
		com.liferay.portal.kernel.model.Organization organization) {

		return _orgMappingLocalService.getOrganizationStrutUAI(organization);
	}

	/**
	 * Returns the org mapping with the primary key.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping
	 * @throws PortalException if a org mapping with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.organization.model.OrgMapping getOrgMapping(
			String entStructureUAI)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMappingLocalService.getOrgMapping(entStructureUAI);
	}

	/**
	 * Returns a range of all the org mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.organization.model.impl.OrgMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org mappings
	 * @param end the upper bound of the range of org mappings (not inclusive)
	 * @return the range of org mappings
	 */
	@Override
	public java.util.List<com.weprode.facile.organization.model.OrgMapping>
		getOrgMappings(int start, int end) {

		return _orgMappingLocalService.getOrgMappings(start, end);
	}

	/**
	 * Returns the number of org mappings.
	 *
	 * @return the number of org mappings
	 */
	@Override
	public int getOrgMappingsCount() {
		return _orgMappingLocalService.getOrgMappingsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _orgMappingLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgMappingLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.Organization getSchoolFromUai(
		String uai) {

		return _orgMappingLocalService.getSchoolFromUai(uai);
	}

	/**
	 * Updates the org mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgMapping the org mapping
	 * @return the org mapping that was updated
	 */
	@Override
	public com.weprode.facile.organization.model.OrgMapping updateOrgMapping(
		com.weprode.facile.organization.model.OrgMapping orgMapping) {

		return _orgMappingLocalService.updateOrgMapping(orgMapping);
	}

	@Override
	public OrgMappingLocalService getWrappedService() {
		return _orgMappingLocalService;
	}

	@Override
	public void setWrappedService(
		OrgMappingLocalService orgMappingLocalService) {

		_orgMappingLocalService = orgMappingLocalService;
	}

	private OrgMappingLocalService _orgMappingLocalService;

}