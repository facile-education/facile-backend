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

package com.weprode.nero.preference.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserMobileTokenLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserMobileTokenLocalService
 * @generated
 */
public class UserMobileTokenLocalServiceWrapper
	implements ServiceWrapper<UserMobileTokenLocalService>,
			   UserMobileTokenLocalService {

	public UserMobileTokenLocalServiceWrapper(
		UserMobileTokenLocalService userMobileTokenLocalService) {

		_userMobileTokenLocalService = userMobileTokenLocalService;
	}

	/**
	 * Add new userMobileToken
	 */
	@Override
	public com.weprode.nero.preference.model.UserMobileToken addUserMobileToken(
			long userId, String mobileToken)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userMobileTokenLocalService.addUserMobileToken(
			userId, mobileToken);
	}

	/**
	 * Adds the user mobile token to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserMobileTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userMobileToken the user mobile token
	 * @return the user mobile token that was added
	 */
	@Override
	public com.weprode.nero.preference.model.UserMobileToken addUserMobileToken(
		com.weprode.nero.preference.model.UserMobileToken userMobileToken) {

		return _userMobileTokenLocalService.addUserMobileToken(userMobileToken);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userMobileTokenLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new user mobile token with the primary key. Does not add the user mobile token to the database.
	 *
	 * @param userMobileTokenId the primary key for the new user mobile token
	 * @return the new user mobile token
	 */
	@Override
	public com.weprode.nero.preference.model.UserMobileToken
		createUserMobileToken(long userMobileTokenId) {

		return _userMobileTokenLocalService.createUserMobileToken(
			userMobileTokenId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userMobileTokenLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the user mobile token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserMobileTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userMobileTokenId the primary key of the user mobile token
	 * @return the user mobile token that was removed
	 * @throws PortalException if a user mobile token with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.preference.model.UserMobileToken
			deleteUserMobileToken(long userMobileTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userMobileTokenLocalService.deleteUserMobileToken(
			userMobileTokenId);
	}

	/**
	 * Deletes the user mobile token from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserMobileTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userMobileToken the user mobile token
	 * @return the user mobile token that was removed
	 */
	@Override
	public com.weprode.nero.preference.model.UserMobileToken
		deleteUserMobileToken(
			com.weprode.nero.preference.model.UserMobileToken userMobileToken) {

		return _userMobileTokenLocalService.deleteUserMobileToken(
			userMobileToken);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _userMobileTokenLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _userMobileTokenLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userMobileTokenLocalService.dynamicQuery();
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

		return _userMobileTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.UserMobileTokenModelImpl</code>.
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

		return _userMobileTokenLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.UserMobileTokenModelImpl</code>.
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

		return _userMobileTokenLocalService.dynamicQuery(
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

		return _userMobileTokenLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userMobileTokenLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.preference.model.UserMobileToken
		fetchUserMobileToken(long userMobileTokenId) {

		return _userMobileTokenLocalService.fetchUserMobileToken(
			userMobileTokenId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userMobileTokenLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.nero.preference.model.UserMobileToken>
			getAllUserMobileTokens(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userMobileTokenLocalService.getAllUserMobileTokens(userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userMobileTokenLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userMobileTokenLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userMobileTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user mobile token with the primary key.
	 *
	 * @param userMobileTokenId the primary key of the user mobile token
	 * @return the user mobile token
	 * @throws PortalException if a user mobile token with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.preference.model.UserMobileToken getUserMobileToken(
			long userMobileTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userMobileTokenLocalService.getUserMobileToken(
			userMobileTokenId);
	}

	/**
	 * Fetch userMobileToken by userId and mobile token
	 */
	@Override
	public com.weprode.nero.preference.model.UserMobileToken getUserMobileToken(
			long userId, String mobileToken)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userMobileTokenLocalService.getUserMobileToken(
			userId, mobileToken);
	}

	/**
	 * Returns a range of all the user mobile tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.UserMobileTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user mobile tokens
	 * @param end the upper bound of the range of user mobile tokens (not inclusive)
	 * @return the range of user mobile tokens
	 */
	@Override
	public java.util.List<com.weprode.nero.preference.model.UserMobileToken>
		getUserMobileTokens(int start, int end) {

		return _userMobileTokenLocalService.getUserMobileTokens(start, end);
	}

	/**
	 * Returns the number of user mobile tokens.
	 *
	 * @return the number of user mobile tokens
	 */
	@Override
	public int getUserMobileTokensCount() {
		return _userMobileTokenLocalService.getUserMobileTokensCount();
	}

	/**
	 * Updates the user mobile token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserMobileTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userMobileToken the user mobile token
	 * @return the user mobile token that was updated
	 */
	@Override
	public com.weprode.nero.preference.model.UserMobileToken
		updateUserMobileToken(
			com.weprode.nero.preference.model.UserMobileToken userMobileToken) {

		return _userMobileTokenLocalService.updateUserMobileToken(
			userMobileToken);
	}

	@Override
	public UserMobileTokenLocalService getWrappedService() {
		return _userMobileTokenLocalService;
	}

	@Override
	public void setWrappedService(
		UserMobileTokenLocalService userMobileTokenLocalService) {

		_userMobileTokenLocalService = userMobileTokenLocalService;
	}

	private UserMobileTokenLocalService _userMobileTokenLocalService;

}