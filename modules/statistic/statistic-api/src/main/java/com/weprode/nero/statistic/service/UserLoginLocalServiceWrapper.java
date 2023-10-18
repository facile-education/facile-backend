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

package com.weprode.nero.statistic.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserLoginLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserLoginLocalService
 * @generated
 */
public class UserLoginLocalServiceWrapper
	implements ServiceWrapper<UserLoginLocalService>, UserLoginLocalService {

	public UserLoginLocalServiceWrapper() {
		this(null);
	}

	public UserLoginLocalServiceWrapper(
		UserLoginLocalService userLoginLocalService) {

		_userLoginLocalService = userLoginLocalService;
	}

	@Override
	public boolean addUserLogin(
		com.liferay.portal.kernel.model.User user, boolean isMobileApp) {

		return _userLoginLocalService.addUserLogin(user, isMobileApp);
	}

	/**
	 * Adds the user login to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserLoginLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userLogin the user login
	 * @return the user login that was added
	 */
	@Override
	public com.weprode.nero.statistic.model.UserLogin addUserLogin(
		com.weprode.nero.statistic.model.UserLogin userLogin) {

		return _userLoginLocalService.addUserLogin(userLogin);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userLoginLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new user login with the primary key. Does not add the user login to the database.
	 *
	 * @param userLoginId the primary key for the new user login
	 * @return the new user login
	 */
	@Override
	public com.weprode.nero.statistic.model.UserLogin createUserLogin(
		long userLoginId) {

		return _userLoginLocalService.createUserLogin(userLoginId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userLoginLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user login with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserLoginLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login that was removed
	 * @throws PortalException if a user login with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.statistic.model.UserLogin deleteUserLogin(
			long userLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userLoginLocalService.deleteUserLogin(userLoginId);
	}

	/**
	 * Deletes the user login from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserLoginLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userLogin the user login
	 * @return the user login that was removed
	 */
	@Override
	public com.weprode.nero.statistic.model.UserLogin deleteUserLogin(
		com.weprode.nero.statistic.model.UserLogin userLogin) {

		return _userLoginLocalService.deleteUserLogin(userLogin);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _userLoginLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _userLoginLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userLoginLocalService.dynamicQuery();
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

		return _userLoginLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.statistic.model.impl.UserLoginModelImpl</code>.
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

		return _userLoginLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.statistic.model.impl.UserLoginModelImpl</code>.
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

		return _userLoginLocalService.dynamicQuery(
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

		return _userLoginLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userLoginLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.statistic.model.UserLogin fetchUserLogin(
		long userLoginId) {

		return _userLoginLocalService.fetchUserLogin(userLoginId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userLoginLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userLoginLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<Long> getLoggedStudents(long schoolId, int nbDays) {
		return _userLoginLocalService.getLoggedStudents(schoolId, nbDays);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userLoginLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userLoginLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user login with the primary key.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login
	 * @throws PortalException if a user login with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.statistic.model.UserLogin getUserLogin(
			long userLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userLoginLocalService.getUserLogin(userLoginId);
	}

	/**
	 * Returns a range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.statistic.model.impl.UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of user logins
	 */
	@Override
	public java.util.List<com.weprode.nero.statistic.model.UserLogin>
		getUserLogins(int start, int end) {

		return _userLoginLocalService.getUserLogins(start, end);
	}

	/**
	 * Returns the number of user logins.
	 *
	 * @return the number of user logins
	 */
	@Override
	public int getUserLoginsCount() {
		return _userLoginLocalService.getUserLoginsCount();
	}

	/**
	 * Updates the user login in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserLoginLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userLogin the user login
	 * @return the user login that was updated
	 */
	@Override
	public com.weprode.nero.statistic.model.UserLogin updateUserLogin(
		com.weprode.nero.statistic.model.UserLogin userLogin) {

		return _userLoginLocalService.updateUserLogin(userLogin);
	}

	@Override
	public UserLoginLocalService getWrappedService() {
		return _userLoginLocalService;
	}

	@Override
	public void setWrappedService(UserLoginLocalService userLoginLocalService) {
		_userLoginLocalService = userLoginLocalService;
	}

	private UserLoginLocalService _userLoginLocalService;

}