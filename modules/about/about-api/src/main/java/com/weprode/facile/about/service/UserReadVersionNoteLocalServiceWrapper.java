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

package com.weprode.facile.about.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserReadVersionNoteLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserReadVersionNoteLocalService
 * @generated
 */
public class UserReadVersionNoteLocalServiceWrapper
	implements ServiceWrapper<UserReadVersionNoteLocalService>,
			   UserReadVersionNoteLocalService {

	public UserReadVersionNoteLocalServiceWrapper() {
		this(null);
	}

	public UserReadVersionNoteLocalServiceWrapper(
		UserReadVersionNoteLocalService userReadVersionNoteLocalService) {

		_userReadVersionNoteLocalService = userReadVersionNoteLocalService;
	}

	@Override
	public void addNewVersionNote() {
		_userReadVersionNoteLocalService.addNewVersionNote();
	}

	/**
	 * Adds the user read version note to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserReadVersionNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userReadVersionNote the user read version note
	 * @return the user read version note that was added
	 */
	@Override
	public com.weprode.facile.about.model.UserReadVersionNote
		addUserReadVersionNote(
			com.weprode.facile.about.model.UserReadVersionNote
				userReadVersionNote) {

		return _userReadVersionNoteLocalService.addUserReadVersionNote(
			userReadVersionNote);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userReadVersionNoteLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new user read version note with the primary key. Does not add the user read version note to the database.
	 *
	 * @param userId the primary key for the new user read version note
	 * @return the new user read version note
	 */
	@Override
	public com.weprode.facile.about.model.UserReadVersionNote
		createUserReadVersionNote(long userId) {

		return _userReadVersionNoteLocalService.createUserReadVersionNote(
			userId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userReadVersionNoteLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the user read version note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserReadVersionNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note that was removed
	 * @throws PortalException if a user read version note with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.about.model.UserReadVersionNote
			deleteUserReadVersionNote(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userReadVersionNoteLocalService.deleteUserReadVersionNote(
			userId);
	}

	/**
	 * Deletes the user read version note from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserReadVersionNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userReadVersionNote the user read version note
	 * @return the user read version note that was removed
	 */
	@Override
	public com.weprode.facile.about.model.UserReadVersionNote
		deleteUserReadVersionNote(
			com.weprode.facile.about.model.UserReadVersionNote
				userReadVersionNote) {

		return _userReadVersionNoteLocalService.deleteUserReadVersionNote(
			userReadVersionNote);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _userReadVersionNoteLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _userReadVersionNoteLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userReadVersionNoteLocalService.dynamicQuery();
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

		return _userReadVersionNoteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.about.model.impl.UserReadVersionNoteModelImpl</code>.
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

		return _userReadVersionNoteLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.about.model.impl.UserReadVersionNoteModelImpl</code>.
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

		return _userReadVersionNoteLocalService.dynamicQuery(
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

		return _userReadVersionNoteLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userReadVersionNoteLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.about.model.UserReadVersionNote
		fetchUserReadVersionNote(long userId) {

		return _userReadVersionNoteLocalService.fetchUserReadVersionNote(
			userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userReadVersionNoteLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userReadVersionNoteLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userReadVersionNoteLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userReadVersionNoteLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the user read version note with the primary key.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note
	 * @throws PortalException if a user read version note with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.about.model.UserReadVersionNote
			getUserReadVersionNote(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userReadVersionNoteLocalService.getUserReadVersionNote(userId);
	}

	/**
	 * Returns a range of all the user read version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.about.model.impl.UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @return the range of user read version notes
	 */
	@Override
	public java.util.List<com.weprode.facile.about.model.UserReadVersionNote>
		getUserReadVersionNotes(int start, int end) {

		return _userReadVersionNoteLocalService.getUserReadVersionNotes(
			start, end);
	}

	/**
	 * Returns the number of user read version notes.
	 *
	 * @return the number of user read version notes
	 */
	@Override
	public int getUserReadVersionNotesCount() {
		return _userReadVersionNoteLocalService.getUserReadVersionNotesCount();
	}

	@Override
	public boolean hasReadLastVersionNote(long userId) {
		return _userReadVersionNoteLocalService.hasReadLastVersionNote(userId);
	}

	@Override
	public void markAsReadForAllRegisteredUsers() {
		_userReadVersionNoteLocalService.markAsReadForAllRegisteredUsers();
	}

	@Override
	public void setLastVersionNoteAsReadForUser(long userId) {
		_userReadVersionNoteLocalService.setLastVersionNoteAsReadForUser(
			userId);
	}

	/**
	 * Updates the user read version note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserReadVersionNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userReadVersionNote the user read version note
	 * @return the user read version note that was updated
	 */
	@Override
	public com.weprode.facile.about.model.UserReadVersionNote
		updateUserReadVersionNote(
			com.weprode.facile.about.model.UserReadVersionNote
				userReadVersionNote) {

		return _userReadVersionNoteLocalService.updateUserReadVersionNote(
			userReadVersionNote);
	}

	@Override
	public UserReadVersionNoteLocalService getWrappedService() {
		return _userReadVersionNoteLocalService;
	}

	@Override
	public void setWrappedService(
		UserReadVersionNoteLocalService userReadVersionNoteLocalService) {

		_userReadVersionNoteLocalService = userReadVersionNoteLocalService;
	}

	private UserReadVersionNoteLocalService _userReadVersionNoteLocalService;

}