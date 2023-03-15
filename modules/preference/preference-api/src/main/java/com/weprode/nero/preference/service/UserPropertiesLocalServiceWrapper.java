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
 * Provides a wrapper for {@link UserPropertiesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserPropertiesLocalService
 * @generated
 */
public class UserPropertiesLocalServiceWrapper
	implements ServiceWrapper<UserPropertiesLocalService>,
			   UserPropertiesLocalService {

	public UserPropertiesLocalServiceWrapper(
		UserPropertiesLocalService userPropertiesLocalService) {

		_userPropertiesLocalService = userPropertiesLocalService;
	}

	@Override
	public com.weprode.nero.preference.model.UserProperties addUserProperties(
			long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userPropertiesLocalService.addUserProperties(userId);
	}

	/**
	 * Adds the user properties to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserPropertiesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userProperties the user properties
	 * @return the user properties that was added
	 */
	@Override
	public com.weprode.nero.preference.model.UserProperties addUserProperties(
		com.weprode.nero.preference.model.UserProperties userProperties) {

		return _userPropertiesLocalService.addUserProperties(userProperties);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userPropertiesLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new user properties with the primary key. Does not add the user properties to the database.
	 *
	 * @param userId the primary key for the new user properties
	 * @return the new user properties
	 */
	@Override
	public com.weprode.nero.preference.model.UserProperties
		createUserProperties(long userId) {

		return _userPropertiesLocalService.createUserProperties(userId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userPropertiesLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user properties with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserPropertiesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties that was removed
	 * @throws PortalException if a user properties with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.preference.model.UserProperties
			deleteUserProperties(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userPropertiesLocalService.deleteUserProperties(userId);
	}

	/**
	 * Deletes the user properties from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserPropertiesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userProperties the user properties
	 * @return the user properties that was removed
	 */
	@Override
	public com.weprode.nero.preference.model.UserProperties
		deleteUserProperties(
			com.weprode.nero.preference.model.UserProperties userProperties) {

		return _userPropertiesLocalService.deleteUserProperties(userProperties);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _userPropertiesLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _userPropertiesLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userPropertiesLocalService.dynamicQuery();
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

		return _userPropertiesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.UserPropertiesModelImpl</code>.
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

		return _userPropertiesLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.UserPropertiesModelImpl</code>.
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

		return _userPropertiesLocalService.dynamicQuery(
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

		return _userPropertiesLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userPropertiesLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.preference.model.UserProperties fetchUserProperties(
		long userId) {

		return _userPropertiesLocalService.fetchUserProperties(userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userPropertiesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userPropertiesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userPropertiesLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userPropertiesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user properties with the primary key.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties
	 * @throws PortalException if a user properties with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.preference.model.UserProperties getUserProperties(
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userPropertiesLocalService.getUserProperties(userId);
	}

	/**
	 * Returns a range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.preference.model.impl.UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @return the range of user propertieses
	 */
	@Override
	public java.util.List<com.weprode.nero.preference.model.UserProperties>
		getUserPropertieses(int start, int end) {

		return _userPropertiesLocalService.getUserPropertieses(start, end);
	}

	/**
	 * Returns the number of user propertieses.
	 *
	 * @return the number of user propertieses
	 */
	@Override
	public int getUserPropertiesesCount() {
		return _userPropertiesLocalService.getUserPropertiesesCount();
	}

	/**
	 * Updates the user properties in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserPropertiesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userProperties the user properties
	 * @return the user properties that was updated
	 */
	@Override
	public com.weprode.nero.preference.model.UserProperties
		updateUserProperties(
			com.weprode.nero.preference.model.UserProperties userProperties) {

		return _userPropertiesLocalService.updateUserProperties(userProperties);
	}

	@Override
	public UserPropertiesLocalService getWrappedService() {
		return _userPropertiesLocalService;
	}

	@Override
	public void setWrappedService(
		UserPropertiesLocalService userPropertiesLocalService) {

		_userPropertiesLocalService = userPropertiesLocalService;
	}

	private UserPropertiesLocalService _userPropertiesLocalService;

}