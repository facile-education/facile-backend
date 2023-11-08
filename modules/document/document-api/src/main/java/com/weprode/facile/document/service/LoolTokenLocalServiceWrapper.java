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

package com.weprode.facile.document.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoolTokenLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LoolTokenLocalService
 * @generated
 */
public class LoolTokenLocalServiceWrapper
	implements LoolTokenLocalService, ServiceWrapper<LoolTokenLocalService> {

	public LoolTokenLocalServiceWrapper() {
		this(null);
	}

	public LoolTokenLocalServiceWrapper(
		LoolTokenLocalService loolTokenLocalService) {

		_loolTokenLocalService = loolTokenLocalService;
	}

	/**
	 * Adds the lool token to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoolTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loolToken the lool token
	 * @return the lool token that was added
	 */
	@Override
	public com.weprode.facile.document.model.LoolToken addLoolToken(
		com.weprode.facile.document.model.LoolToken loolToken) {

		return _loolTokenLocalService.addLoolToken(loolToken);
	}

	/**
	 * Creates a new lool token with the primary key. Does not add the lool token to the database.
	 *
	 * @param loolTokenId the primary key for the new lool token
	 * @return the new lool token
	 */
	@Override
	public com.weprode.facile.document.model.LoolToken createLoolToken(
		long loolTokenId) {

		return _loolTokenLocalService.createLoolToken(loolTokenId);
	}

	/**
	 * Creates a LoolToken with gisven userID and token
	 */
	@Override
	public com.weprode.facile.document.model.LoolToken createLoolToken(
		long userId, String token) {

		return _loolTokenLocalService.createLoolToken(userId, token);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolTokenLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the lool token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoolTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token that was removed
	 * @throws PortalException if a lool token with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.document.model.LoolToken deleteLoolToken(
			long loolTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolTokenLocalService.deleteLoolToken(loolTokenId);
	}

	/**
	 * Deletes the lool token from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoolTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loolToken the lool token
	 * @return the lool token that was removed
	 */
	@Override
	public com.weprode.facile.document.model.LoolToken deleteLoolToken(
		com.weprode.facile.document.model.LoolToken loolToken) {

		return _loolTokenLocalService.deleteLoolToken(loolToken);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolTokenLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _loolTokenLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _loolTokenLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loolTokenLocalService.dynamicQuery();
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

		return _loolTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.document.model.impl.LoolTokenModelImpl</code>.
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

		return _loolTokenLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.document.model.impl.LoolTokenModelImpl</code>.
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

		return _loolTokenLocalService.dynamicQuery(
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

		return _loolTokenLocalService.dynamicQueryCount(dynamicQuery);
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

		return _loolTokenLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.document.model.LoolToken fetchLoolToken(
		long loolTokenId) {

		return _loolTokenLocalService.fetchLoolToken(loolTokenId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _loolTokenLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _loolTokenLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the lool token with the primary key.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token
	 * @throws PortalException if a lool token with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.document.model.LoolToken getLoolToken(
			long loolTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolTokenLocalService.getLoolToken(loolTokenId);
	}

	/**
	 * Returns a LoolToken with given token
	 */
	@Override
	public com.weprode.facile.document.model.LoolToken getLoolToken(
		String token) {

		return _loolTokenLocalService.getLoolToken(token);
	}

	/**
	 * Returns a range of all the lool tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.document.model.impl.LoolTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool tokens
	 * @param end the upper bound of the range of lool tokens (not inclusive)
	 * @return the range of lool tokens
	 */
	@Override
	public java.util.List<com.weprode.facile.document.model.LoolToken>
		getLoolTokens(int start, int end) {

		return _loolTokenLocalService.getLoolTokens(start, end);
	}

	/**
	 * Returns the number of lool tokens.
	 *
	 * @return the number of lool tokens
	 */
	@Override
	public int getLoolTokensCount() {
		return _loolTokenLocalService.getLoolTokensCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loolTokenLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loolTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the lool token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoolTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loolToken the lool token
	 * @return the lool token that was updated
	 */
	@Override
	public com.weprode.facile.document.model.LoolToken updateLoolToken(
		com.weprode.facile.document.model.LoolToken loolToken) {

		return _loolTokenLocalService.updateLoolToken(loolToken);
	}

	@Override
	public LoolTokenLocalService getWrappedService() {
		return _loolTokenLocalService;
	}

	@Override
	public void setWrappedService(LoolTokenLocalService loolTokenLocalService) {
		_loolTokenLocalService = loolTokenLocalService;
	}

	private LoolTokenLocalService _loolTokenLocalService;

}