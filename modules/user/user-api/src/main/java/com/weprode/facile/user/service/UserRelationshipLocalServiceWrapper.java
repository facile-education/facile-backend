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

package com.weprode.facile.user.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserRelationshipLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserRelationshipLocalService
 * @generated
 */
public class UserRelationshipLocalServiceWrapper
	implements ServiceWrapper<UserRelationshipLocalService>,
			   UserRelationshipLocalService {

	public UserRelationshipLocalServiceWrapper() {
		this(null);
	}

	public UserRelationshipLocalServiceWrapper(
		UserRelationshipLocalService userRelationshipLocalService) {

		_userRelationshipLocalService = userRelationshipLocalService;
	}

	/**
	 * Adds the user relationship to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRelationshipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRelationship the user relationship
	 * @return the user relationship that was added
	 */
	@Override
	public com.weprode.facile.user.model.UserRelationship addUserRelationship(
		com.weprode.facile.user.model.UserRelationship userRelationship) {

		return _userRelationshipLocalService.addUserRelationship(
			userRelationship);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRelationshipLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public com.weprode.facile.user.model.UserRelationship
		createUserRelationship(long childUserId, long parentUserId) {

		return _userRelationshipLocalService.createUserRelationship(
			childUserId, parentUserId);
	}

	/**
	 * Creates a new user relationship with the primary key. Does not add the user relationship to the database.
	 *
	 * @param userRelationshipPK the primary key for the new user relationship
	 * @return the new user relationship
	 */
	@Override
	public com.weprode.facile.user.model.UserRelationship
		createUserRelationship(
			com.weprode.facile.user.service.persistence.UserRelationshipPK
				userRelationshipPK) {

		return _userRelationshipLocalService.createUserRelationship(
			userRelationshipPK);
	}

	@Override
	public void deleteChild(long childId) {
		_userRelationshipLocalService.deleteChild(childId);
	}

	@Override
	public void deleteParent(long parentId) {
		_userRelationshipLocalService.deleteParent(parentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRelationshipLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the user relationship from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRelationshipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRelationship the user relationship
	 * @return the user relationship that was removed
	 */
	@Override
	public com.weprode.facile.user.model.UserRelationship
		deleteUserRelationship(
			com.weprode.facile.user.model.UserRelationship userRelationship) {

		return _userRelationshipLocalService.deleteUserRelationship(
			userRelationship);
	}

	/**
	 * Deletes the user relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRelationshipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship that was removed
	 * @throws PortalException if a user relationship with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.user.model.UserRelationship
			deleteUserRelationship(
				com.weprode.facile.user.service.persistence.UserRelationshipPK
					userRelationshipPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRelationshipLocalService.deleteUserRelationship(
			userRelationshipPK);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _userRelationshipLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _userRelationshipLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userRelationshipLocalService.dynamicQuery();
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

		return _userRelationshipLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.UserRelationshipModelImpl</code>.
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

		return _userRelationshipLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.UserRelationshipModelImpl</code>.
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

		return _userRelationshipLocalService.dynamicQuery(
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

		return _userRelationshipLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userRelationshipLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.user.model.UserRelationship fetchUserRelationship(
		com.weprode.facile.user.service.persistence.UserRelationshipPK
			userRelationshipPK) {

		return _userRelationshipLocalService.fetchUserRelationship(
			userRelationshipPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userRelationshipLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getAllRelatives(
		long parentId) {

		return _userRelationshipLocalService.getAllRelatives(parentId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getChildren(
		long parentId) {

		return _userRelationshipLocalService.getChildren(parentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userRelationshipLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userRelationshipLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getParents(
		long childId) {

		return _userRelationshipLocalService.getParents(childId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRelationshipLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user relationship with the primary key.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship
	 * @throws PortalException if a user relationship with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.user.model.UserRelationship getUserRelationship(
			com.weprode.facile.user.service.persistence.UserRelationshipPK
				userRelationshipPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRelationshipLocalService.getUserRelationship(
			userRelationshipPK);
	}

	/**
	 * Returns a range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.user.model.impl.UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of user relationships
	 */
	@Override
	public java.util.List<com.weprode.facile.user.model.UserRelationship>
		getUserRelationships(int start, int end) {

		return _userRelationshipLocalService.getUserRelationships(start, end);
	}

	/**
	 * Returns the number of user relationships.
	 *
	 * @return the number of user relationships
	 */
	@Override
	public int getUserRelationshipsCount() {
		return _userRelationshipLocalService.getUserRelationshipsCount();
	}

	@Override
	public boolean isChild(long parentId, long childId) {
		return _userRelationshipLocalService.isChild(parentId, childId);
	}

	/**
	 * Updates the user relationship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRelationshipLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRelationship the user relationship
	 * @return the user relationship that was updated
	 */
	@Override
	public com.weprode.facile.user.model.UserRelationship
		updateUserRelationship(
			com.weprode.facile.user.model.UserRelationship userRelationship) {

		return _userRelationshipLocalService.updateUserRelationship(
			userRelationship);
	}

	@Override
	public UserRelationshipLocalService getWrappedService() {
		return _userRelationshipLocalService;
	}

	@Override
	public void setWrappedService(
		UserRelationshipLocalService userRelationshipLocalService) {

		_userRelationshipLocalService = userRelationshipLocalService;
	}

	private UserRelationshipLocalService _userRelationshipLocalService;

}