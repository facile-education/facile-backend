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
 * Provides a wrapper for {@link OrgCiteScolaireLocalService}.
 *
 * @author Marc Salvat
 * @see OrgCiteScolaireLocalService
 * @generated
 */
public class OrgCiteScolaireLocalServiceWrapper
	implements OrgCiteScolaireLocalService,
			   ServiceWrapper<OrgCiteScolaireLocalService> {

	public OrgCiteScolaireLocalServiceWrapper() {
		this(null);
	}

	public OrgCiteScolaireLocalServiceWrapper(
		OrgCiteScolaireLocalService orgCiteScolaireLocalService) {

		_orgCiteScolaireLocalService = orgCiteScolaireLocalService;
	}

	/**
	 * Adds the org cite scolaire to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgCiteScolaireLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgCiteScolaire the org cite scolaire
	 * @return the org cite scolaire that was added
	 */
	@Override
	public com.weprode.facile.organization.model.OrgCiteScolaire
		addOrgCiteScolaire(
			com.weprode.facile.organization.model.OrgCiteScolaire
				orgCiteScolaire) {

		return _orgCiteScolaireLocalService.addOrgCiteScolaire(orgCiteScolaire);
	}

	@Override
	public com.weprode.facile.organization.model.OrgCiteScolaire
			addOrgCiteScolaire(
				String parentEntStructureUAI, String childEntStructureUAI)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _orgCiteScolaireLocalService.addOrgCiteScolaire(
			parentEntStructureUAI, childEntStructureUAI);
	}

	/**
	 * Creates a new org cite scolaire with the primary key. Does not add the org cite scolaire to the database.
	 *
	 * @param childENTStructureUAI the primary key for the new org cite scolaire
	 * @return the new org cite scolaire
	 */
	@Override
	public com.weprode.facile.organization.model.OrgCiteScolaire
		createOrgCiteScolaire(String childENTStructureUAI) {

		return _orgCiteScolaireLocalService.createOrgCiteScolaire(
			childENTStructureUAI);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgCiteScolaireLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the org cite scolaire from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgCiteScolaireLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgCiteScolaire the org cite scolaire
	 * @return the org cite scolaire that was removed
	 */
	@Override
	public com.weprode.facile.organization.model.OrgCiteScolaire
		deleteOrgCiteScolaire(
			com.weprode.facile.organization.model.OrgCiteScolaire
				orgCiteScolaire) {

		return _orgCiteScolaireLocalService.deleteOrgCiteScolaire(
			orgCiteScolaire);
	}

	/**
	 * Deletes the org cite scolaire with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgCiteScolaireLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire that was removed
	 * @throws PortalException if a org cite scolaire with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.organization.model.OrgCiteScolaire
			deleteOrgCiteScolaire(String childENTStructureUAI)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgCiteScolaireLocalService.deleteOrgCiteScolaire(
			childENTStructureUAI);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgCiteScolaireLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _orgCiteScolaireLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _orgCiteScolaireLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _orgCiteScolaireLocalService.dynamicQuery();
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

		return _orgCiteScolaireLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.organization.model.impl.OrgCiteScolaireModelImpl</code>.
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

		return _orgCiteScolaireLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.organization.model.impl.OrgCiteScolaireModelImpl</code>.
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

		return _orgCiteScolaireLocalService.dynamicQuery(
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

		return _orgCiteScolaireLocalService.dynamicQueryCount(dynamicQuery);
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

		return _orgCiteScolaireLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.organization.model.OrgCiteScolaire
		fetchOrgCiteScolaire(String childENTStructureUAI) {

		return _orgCiteScolaireLocalService.fetchOrgCiteScolaire(
			childENTStructureUAI);
	}

	/**
	 * Returns the org cite scolaire with the primary key.
	 *
	 * @param childENTStructureUAI the primary key of the org cite scolaire
	 * @return the org cite scolaire
	 * @throws PortalException if a org cite scolaire with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.organization.model.OrgCiteScolaire
			getOrgCiteScolaire(String childENTStructureUAI)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgCiteScolaireLocalService.getOrgCiteScolaire(
			childENTStructureUAI);
	}

	/**
	 * Returns a range of all the org cite scolaires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.organization.model.impl.OrgCiteScolaireModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org cite scolaires
	 * @param end the upper bound of the range of org cite scolaires (not inclusive)
	 * @return the range of org cite scolaires
	 */
	@Override
	public java.util.List<com.weprode.facile.organization.model.OrgCiteScolaire>
		getOrgCiteScolaires(int start, int end) {

		return _orgCiteScolaireLocalService.getOrgCiteScolaires(start, end);
	}

	/**
	 * Returns the number of org cite scolaires.
	 *
	 * @return the number of org cite scolaires
	 */
	@Override
	public int getOrgCiteScolairesCount() {
		return _orgCiteScolaireLocalService.getOrgCiteScolairesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _orgCiteScolaireLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _orgCiteScolaireLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Organization>
		getSchoolComplexSchools(
			com.liferay.portal.kernel.model.Organization school) {

		return _orgCiteScolaireLocalService.getSchoolComplexSchools(school);
	}

	/**
	 * Updates the org cite scolaire in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrgCiteScolaireLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orgCiteScolaire the org cite scolaire
	 * @return the org cite scolaire that was updated
	 */
	@Override
	public com.weprode.facile.organization.model.OrgCiteScolaire
		updateOrgCiteScolaire(
			com.weprode.facile.organization.model.OrgCiteScolaire
				orgCiteScolaire) {

		return _orgCiteScolaireLocalService.updateOrgCiteScolaire(
			orgCiteScolaire);
	}

	@Override
	public OrgCiteScolaireLocalService getWrappedService() {
		return _orgCiteScolaireLocalService;
	}

	@Override
	public void setWrappedService(
		OrgCiteScolaireLocalService orgCiteScolaireLocalService) {

		_orgCiteScolaireLocalService = orgCiteScolaireLocalService;
	}

	private OrgCiteScolaireLocalService _orgCiteScolaireLocalService;

}