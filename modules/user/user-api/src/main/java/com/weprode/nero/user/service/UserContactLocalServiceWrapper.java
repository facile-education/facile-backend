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
 * Provides a wrapper for {@link UserContactLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserContactLocalService
 * @generated
 */
public class UserContactLocalServiceWrapper
	implements ServiceWrapper<UserContactLocalService>,
			   UserContactLocalService {

	public UserContactLocalServiceWrapper(
		UserContactLocalService userContactLocalService) {

		_userContactLocalService = userContactLocalService;
	}

	@Override
	public com.weprode.nero.user.model.UserContact addUserContact()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userContactLocalService.addUserContact();
	}

	/**
	 * Create a UserContact, with default values
	 */
	@Override
	public com.weprode.nero.user.model.UserContact addUserContact(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userContactLocalService.addUserContact(userId);
	}

	/**
	 * Adds the user contact to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserContactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userContact the user contact
	 * @return the user contact that was added
	 */
	@Override
	public com.weprode.nero.user.model.UserContact addUserContact(
		com.weprode.nero.user.model.UserContact userContact) {

		return _userContactLocalService.addUserContact(userContact);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userContactLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new user contact with the primary key. Does not add the user contact to the database.
	 *
	 * @param contactId the primary key for the new user contact
	 * @return the new user contact
	 */
	@Override
	public com.weprode.nero.user.model.UserContact createUserContact(
		long contactId) {

		return _userContactLocalService.createUserContact(contactId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userContactLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user contact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserContactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact that was removed
	 * @throws PortalException if a user contact with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.user.model.UserContact deleteUserContact(
			long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userContactLocalService.deleteUserContact(contactId);
	}

	/**
	 * Deletes the user contact from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserContactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userContact the user contact
	 * @return the user contact that was removed
	 */
	@Override
	public com.weprode.nero.user.model.UserContact deleteUserContact(
		com.weprode.nero.user.model.UserContact userContact) {

		return _userContactLocalService.deleteUserContact(userContact);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _userContactLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _userContactLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userContactLocalService.dynamicQuery();
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

		return _userContactLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.UserContactModelImpl</code>.
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

		return _userContactLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.UserContactModelImpl</code>.
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

		return _userContactLocalService.dynamicQuery(
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

		return _userContactLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userContactLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.user.model.UserContact fetchUserContact(
		long contactId) {

		return _userContactLocalService.fetchUserContact(contactId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userContactLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userContactLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userContactLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userContactLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user contact with the primary key.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact
	 * @throws PortalException if a user contact with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.user.model.UserContact getUserContact(
			long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userContactLocalService.getUserContact(contactId);
	}

	/**
	 * Return the user contact, given the userId
	 * If not existing, create it
	 */
	@Override
	public com.weprode.nero.user.model.UserContact getUserContactByUserId(
			long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userContactLocalService.getUserContactByUserId(userId);
	}

	/**
	 * Returns a range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.user.model.impl.UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @return the range of user contacts
	 */
	@Override
	public java.util.List<com.weprode.nero.user.model.UserContact>
		getUserContacts(int start, int end) {

		return _userContactLocalService.getUserContacts(start, end);
	}

	/**
	 * Returns the number of user contacts.
	 *
	 * @return the number of user contacts
	 */
	@Override
	public int getUserContactsCount() {
		return _userContactLocalService.getUserContactsCount();
	}

	/**
	 * Updates the user contact in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserContactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userContact the user contact
	 * @return the user contact that was updated
	 */
	@Override
	public com.weprode.nero.user.model.UserContact updateUserContact(
		com.weprode.nero.user.model.UserContact userContact) {

		return _userContactLocalService.updateUserContact(userContact);
	}

	@Override
	public UserContactLocalService getWrappedService() {
		return _userContactLocalService;
	}

	@Override
	public void setWrappedService(
		UserContactLocalService userContactLocalService) {

		_userContactLocalService = userContactLocalService;
	}

	private UserContactLocalService _userContactLocalService;

}