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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.about.model.UserReadVersionNote;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for UserReadVersionNote. This utility wraps
 * <code>com.weprode.facile.about.service.impl.UserReadVersionNoteLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserReadVersionNoteLocalService
 * @generated
 */
public class UserReadVersionNoteLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.about.service.impl.UserReadVersionNoteLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addNewVersionNote() {
		getService().addNewVersionNote();
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
	public static UserReadVersionNote addUserReadVersionNote(
		UserReadVersionNote userReadVersionNote) {

		return getService().addUserReadVersionNote(userReadVersionNote);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new user read version note with the primary key. Does not add the user read version note to the database.
	 *
	 * @param userId the primary key for the new user read version note
	 * @return the new user read version note
	 */
	public static UserReadVersionNote createUserReadVersionNote(long userId) {
		return getService().createUserReadVersionNote(userId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	public static UserReadVersionNote deleteUserReadVersionNote(long userId)
		throws PortalException {

		return getService().deleteUserReadVersionNote(userId);
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
	public static UserReadVersionNote deleteUserReadVersionNote(
		UserReadVersionNote userReadVersionNote) {

		return getService().deleteUserReadVersionNote(userReadVersionNote);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.about.model.impl.UserReadVersionNoteModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.about.model.impl.UserReadVersionNoteModelImpl</code>.
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

	public static UserReadVersionNote fetchUserReadVersionNote(long userId) {
		return getService().fetchUserReadVersionNote(userId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
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

	/**
	 * Returns the user read version note with the primary key.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note
	 * @throws PortalException if a user read version note with the primary key could not be found
	 */
	public static UserReadVersionNote getUserReadVersionNote(long userId)
		throws PortalException {

		return getService().getUserReadVersionNote(userId);
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
	public static List<UserReadVersionNote> getUserReadVersionNotes(
		int start, int end) {

		return getService().getUserReadVersionNotes(start, end);
	}

	/**
	 * Returns the number of user read version notes.
	 *
	 * @return the number of user read version notes
	 */
	public static int getUserReadVersionNotesCount() {
		return getService().getUserReadVersionNotesCount();
	}

	public static boolean hasReadLastVersionNote(long userId) {
		return getService().hasReadLastVersionNote(userId);
	}

	public static void markAsReadForAllRegisteredUsers() {
		getService().markAsReadForAllRegisteredUsers();
	}

	public static void setLastVersionNoteAsReadForUser(long userId) {
		getService().setLastVersionNoteAsReadForUser(userId);
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
	public static UserReadVersionNote updateUserReadVersionNote(
		UserReadVersionNote userReadVersionNote) {

		return getService().updateUserReadVersionNote(userReadVersionNote);
	}

	public static UserReadVersionNoteLocalService getService() {
		return _service;
	}

	private static volatile UserReadVersionNoteLocalService _service;

}