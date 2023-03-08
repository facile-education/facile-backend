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

package com.weprode.nero.user.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AffectationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AffectationLocalService
 * @generated
 */
public class AffectationLocalServiceWrapper
	implements AffectationLocalService,
			   ServiceWrapper<AffectationLocalService> {

	public AffectationLocalServiceWrapper(
		AffectationLocalService affectationLocalService) {

		_affectationLocalService = affectationLocalService;
	}

	/**
	 * Adds the affectation to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AffectationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param affectation the affectation
	 * @return the affectation that was added
	 */
	@Override
	public com.weprode.nero.user.model.Affectation addAffectation(
		com.weprode.nero.user.model.Affectation affectation) {

		return _affectationLocalService.addAffectation(affectation);
	}

	@Override
	public boolean addUserAffectation(
		long userId, long orgId, long adminUserId,
		java.util.Date expirationDate) {

		return _affectationLocalService.addUserAffectation(
			userId, orgId, adminUserId, expirationDate);
	}

	/**
	 * Creates a new affectation with the primary key. Does not add the affectation to the database.
	 *
	 * @param affectationId the primary key for the new affectation
	 * @return the new affectation
	 */
	@Override
	public com.weprode.nero.user.model.Affectation createAffectation(
		long affectationId) {

		return _affectationLocalService.createAffectation(affectationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _affectationLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the affectation from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AffectationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param affectation the affectation
	 * @return the affectation that was removed
	 */
	@Override
	public com.weprode.nero.user.model.Affectation deleteAffectation(
		com.weprode.nero.user.model.Affectation affectation) {

		return _affectationLocalService.deleteAffectation(affectation);
	}

	/**
	 * Deletes the affectation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AffectationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation that was removed
	 * @throws PortalException if a affectation with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.user.model.Affectation deleteAffectation(
			long affectationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _affectationLocalService.deleteAffectation(affectationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _affectationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _affectationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _affectationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _affectationLocalService.dynamicQuery();
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

		return _affectationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.AffectationModelImpl</code>.
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

		return _affectationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.AffectationModelImpl</code>.
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

		return _affectationLocalService.dynamicQuery(
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

		return _affectationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _affectationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.user.model.Affectation fetchAffectation(
		long affectationId) {

		return _affectationLocalService.fetchAffectation(affectationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _affectationLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the affectation with the primary key.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation
	 * @throws PortalException if a affectation with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.user.model.Affectation getAffectation(
			long affectationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _affectationLocalService.getAffectation(affectationId);
	}

	/**
	 * Returns a range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of affectations
	 */
	@Override
	public java.util.List<com.weprode.nero.user.model.Affectation>
		getAffectations(int start, int end) {

		return _affectationLocalService.getAffectations(start, end);
	}

	/**
	 * Returns the number of affectations.
	 *
	 * @return the number of affectations
	 */
	@Override
	public int getAffectationsCount() {
		return _affectationLocalService.getAffectationsCount();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getAffectedUsers(long schoolId, String filter) {

		return _affectationLocalService.getAffectedUsers(schoolId, filter);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _affectationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _affectationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _affectationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.weprode.nero.user.model.Affectation>
		getUserAffectations(long userId, long schoolId) {

		return _affectationLocalService.getUserAffectations(userId, schoolId);
	}

	@Override
	public java.util.List<Long> getUserAffectedOrgs(long userId) {
		return _affectationLocalService.getUserAffectedOrgs(userId);
	}

	@Override
	public boolean removeUserAffectation(long userId, long orgId) {
		return _affectationLocalService.removeUserAffectation(userId, orgId);
	}

	/**
	 * Updates the affectation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AffectationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param affectation the affectation
	 * @return the affectation that was updated
	 */
	@Override
	public com.weprode.nero.user.model.Affectation updateAffectation(
		com.weprode.nero.user.model.Affectation affectation) {

		return _affectationLocalService.updateAffectation(affectation);
	}

	@Override
	public AffectationLocalService getWrappedService() {
		return _affectationLocalService;
	}

	@Override
	public void setWrappedService(
		AffectationLocalService affectationLocalService) {

		_affectationLocalService = affectationLocalService;
	}

	private AffectationLocalService _affectationLocalService;

}