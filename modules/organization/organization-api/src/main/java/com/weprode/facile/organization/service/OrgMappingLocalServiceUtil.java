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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.organization.model.OrgMapping;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for OrgMapping. This utility wraps
 * <code>com.weprode.facile.organization.service.impl.OrgMappingLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marc Salvat
 * @see OrgMappingLocalService
 * @generated
 */
public class OrgMappingLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.organization.service.impl.OrgMappingLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static OrgMapping addOrgMapping(
			com.liferay.portal.kernel.model.Organization organization,
			String uai)
		throws SystemException {

		return getService().addOrgMapping(organization, uai);
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
	public static OrgMapping addOrgMapping(OrgMapping orgMapping) {
		return getService().addOrgMapping(orgMapping);
	}

	/**
	 * Creates a new org mapping with the primary key. Does not add the org mapping to the database.
	 *
	 * @param entStructureUAI the primary key for the new org mapping
	 * @return the new org mapping
	 */
	public static OrgMapping createOrgMapping(String entStructureUAI) {
		return getService().createOrgMapping(entStructureUAI);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteOrgMapping(long organizationId)
		throws com.weprode.facile.organization.exception.
			NoSuchOrgMappingException {

		getService().deleteOrgMapping(organizationId);
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
	public static OrgMapping deleteOrgMapping(OrgMapping orgMapping) {
		return getService().deleteOrgMapping(orgMapping);
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
	public static OrgMapping deleteOrgMapping(String entStructureUAI)
		throws PortalException {

		return getService().deleteOrgMapping(entStructureUAI);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.organization.model.impl.OrgMappingModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.organization.model.impl.OrgMappingModelImpl</code>.
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

	public static OrgMapping fetchOrgMapping(String entStructureUAI) {
		return getService().fetchOrgMapping(entStructureUAI);
	}

	/**
	 * Returns the structure UAI from the organization if it exist.
	 */
	public static String getOrganizationStrutUAI(
		com.liferay.portal.kernel.model.Organization organization) {

		return getService().getOrganizationStrutUAI(organization);
	}

	/**
	 * Returns the org mapping with the primary key.
	 *
	 * @param entStructureUAI the primary key of the org mapping
	 * @return the org mapping
	 * @throws PortalException if a org mapping with the primary key could not be found
	 */
	public static OrgMapping getOrgMapping(String entStructureUAI)
		throws PortalException {

		return getService().getOrgMapping(entStructureUAI);
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
	public static List<OrgMapping> getOrgMappings(int start, int end) {
		return getService().getOrgMappings(start, end);
	}

	/**
	 * Returns the number of org mappings.
	 *
	 * @return the number of org mappings
	 */
	public static int getOrgMappingsCount() {
		return getService().getOrgMappingsCount();
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

	public static com.liferay.portal.kernel.model.Organization getSchoolFromUai(
		String uai) {

		return getService().getSchoolFromUai(uai);
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
	public static OrgMapping updateOrgMapping(OrgMapping orgMapping) {
		return getService().updateOrgMapping(orgMapping);
	}

	public static OrgMappingLocalService getService() {
		return _service;
	}

	private static volatile OrgMappingLocalService _service;

}