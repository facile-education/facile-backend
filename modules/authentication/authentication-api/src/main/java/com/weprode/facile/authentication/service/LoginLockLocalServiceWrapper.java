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

package com.weprode.facile.authentication.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoginLockLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LoginLockLocalService
 * @generated
 */
public class LoginLockLocalServiceWrapper
	implements LoginLockLocalService, ServiceWrapper<LoginLockLocalService> {

	public LoginLockLocalServiceWrapper() {
		this(null);
	}

	public LoginLockLocalServiceWrapper(
		LoginLockLocalService loginLockLocalService) {

		_loginLockLocalService = loginLockLocalService;
	}

	@Override
	public void addLoginAttempt(String login) {
		_loginLockLocalService.addLoginAttempt(login);
	}

	/**
	 * Adds the login lock to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoginLockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loginLock the login lock
	 * @return the login lock that was added
	 */
	@Override
	public com.weprode.facile.authentication.model.LoginLock addLoginLock(
		com.weprode.facile.authentication.model.LoginLock loginLock) {

		return _loginLockLocalService.addLoginLock(loginLock);
	}

	/**
	 * Creates a new login lock with the primary key. Does not add the login lock to the database.
	 *
	 * @param login the primary key for the new login lock
	 * @return the new login lock
	 */
	@Override
	public com.weprode.facile.authentication.model.LoginLock createLoginLock(
		String login) {

		return _loginLockLocalService.createLoginLock(login);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loginLockLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the login lock from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoginLockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loginLock the login lock
	 * @return the login lock that was removed
	 */
	@Override
	public com.weprode.facile.authentication.model.LoginLock deleteLoginLock(
		com.weprode.facile.authentication.model.LoginLock loginLock) {

		return _loginLockLocalService.deleteLoginLock(loginLock);
	}

	/**
	 * Deletes the login lock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoginLockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param login the primary key of the login lock
	 * @return the login lock that was removed
	 * @throws PortalException if a login lock with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.authentication.model.LoginLock deleteLoginLock(
			String login)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loginLockLocalService.deleteLoginLock(login);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loginLockLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _loginLockLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _loginLockLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loginLockLocalService.dynamicQuery();
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

		return _loginLockLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.authentication.model.impl.LoginLockModelImpl</code>.
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

		return _loginLockLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.authentication.model.impl.LoginLockModelImpl</code>.
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

		return _loginLockLocalService.dynamicQuery(
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

		return _loginLockLocalService.dynamicQueryCount(dynamicQuery);
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

		return _loginLockLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.authentication.model.LoginLock fetchLoginLock(
		String login) {

		return _loginLockLocalService.fetchLoginLock(login);
	}

	/**
	 * Returns the login lock with the primary key.
	 *
	 * @param login the primary key of the login lock
	 * @return the login lock
	 * @throws PortalException if a login lock with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.authentication.model.LoginLock getLoginLock(
			String login)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loginLockLocalService.getLoginLock(login);
	}

	/**
	 * Returns a range of all the login locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.authentication.model.impl.LoginLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of login locks
	 * @param end the upper bound of the range of login locks (not inclusive)
	 * @return the range of login locks
	 */
	@Override
	public java.util.List<com.weprode.facile.authentication.model.LoginLock>
		getLoginLocks(int start, int end) {

		return _loginLockLocalService.getLoginLocks(start, end);
	}

	/**
	 * Returns the number of login locks.
	 *
	 * @return the number of login locks
	 */
	@Override
	public int getLoginLocksCount() {
		return _loginLockLocalService.getLoginLocksCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loginLockLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loginLockLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the login lock in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoginLockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loginLock the login lock
	 * @return the login lock that was updated
	 */
	@Override
	public com.weprode.facile.authentication.model.LoginLock updateLoginLock(
		com.weprode.facile.authentication.model.LoginLock loginLock) {

		return _loginLockLocalService.updateLoginLock(loginLock);
	}

	@Override
	public LoginLockLocalService getWrappedService() {
		return _loginLockLocalService;
	}

	@Override
	public void setWrappedService(LoginLockLocalService loginLockLocalService) {
		_loginLockLocalService = loginLockLocalService;
	}

	private LoginLockLocalService _loginLockLocalService;

}