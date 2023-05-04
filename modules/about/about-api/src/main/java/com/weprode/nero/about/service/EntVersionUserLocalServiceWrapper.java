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

package com.weprode.nero.about.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntVersionUserLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntVersionUserLocalService
 * @generated
 */
public class EntVersionUserLocalServiceWrapper
	implements EntVersionUserLocalService,
			   ServiceWrapper<EntVersionUserLocalService> {

	public EntVersionUserLocalServiceWrapper(
		EntVersionUserLocalService entVersionUserLocalService) {

		_entVersionUserLocalService = entVersionUserLocalService;
	}

	/**
	 * Adds the ent version user to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntVersionUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entVersionUser the ent version user
	 * @return the ent version user that was added
	 */
	@Override
	public com.weprode.nero.about.model.EntVersionUser addEntVersionUser(
		com.weprode.nero.about.model.EntVersionUser entVersionUser) {

		return _entVersionUserLocalService.addEntVersionUser(entVersionUser);
	}

	/**
	 * Add new entVersionUser
	 */
	@Override
	public com.weprode.nero.about.model.EntVersionUser addEntVersionUser(
			long entVersionId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _entVersionUserLocalService.addEntVersionUser(
			entVersionId, userId);
	}

	/**
	 * Creates a new ent version user with the primary key. Does not add the ent version user to the database.
	 *
	 * @param versionUserId the primary key for the new ent version user
	 * @return the new ent version user
	 */
	@Override
	public com.weprode.nero.about.model.EntVersionUser createEntVersionUser(
		long versionUserId) {

		return _entVersionUserLocalService.createEntVersionUser(versionUserId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionUserLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the ent version user from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntVersionUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entVersionUser the ent version user
	 * @return the ent version user that was removed
	 */
	@Override
	public com.weprode.nero.about.model.EntVersionUser deleteEntVersionUser(
		com.weprode.nero.about.model.EntVersionUser entVersionUser) {

		return _entVersionUserLocalService.deleteEntVersionUser(entVersionUser);
	}

	/**
	 * Deletes the ent version user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntVersionUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user that was removed
	 * @throws PortalException if a ent version user with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.about.model.EntVersionUser deleteEntVersionUser(
			long versionUserId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionUserLocalService.deleteEntVersionUser(versionUserId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionUserLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _entVersionUserLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _entVersionUserLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entVersionUserLocalService.dynamicQuery();
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

		return _entVersionUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.EntVersionUserModelImpl</code>.
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

		return _entVersionUserLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.EntVersionUserModelImpl</code>.
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

		return _entVersionUserLocalService.dynamicQuery(
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

		return _entVersionUserLocalService.dynamicQueryCount(dynamicQuery);
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

		return _entVersionUserLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.about.model.EntVersionUser fetchEntVersionUser(
		long versionUserId) {

		return _entVersionUserLocalService.fetchEntVersionUser(versionUserId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _entVersionUserLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the ent version user with the primary key.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user
	 * @throws PortalException if a ent version user with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.about.model.EntVersionUser getEntVersionUser(
			long versionUserId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionUserLocalService.getEntVersionUser(versionUserId);
	}

	/**
	 * Returns a range of all the ent version users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.EntVersionUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent version users
	 * @param end the upper bound of the range of ent version users (not inclusive)
	 * @return the range of ent version users
	 */
	@Override
	public java.util.List<com.weprode.nero.about.model.EntVersionUser>
		getEntVersionUsers(int start, int end) {

		return _entVersionUserLocalService.getEntVersionUsers(start, end);
	}

	/**
	 * Returns the number of ent version users.
	 *
	 * @return the number of ent version users
	 */
	@Override
	public int getEntVersionUsersCount() {
		return _entVersionUserLocalService.getEntVersionUsersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _entVersionUserLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _entVersionUserLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Return true if user has already read the last version
	 */
	@Override
	public boolean hasUserDispelledLastVersion(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _entVersionUserLocalService.hasUserDispelledLastVersion(userId);
	}

	@Override
	public boolean markLastVersionAsRead(long userId) {
		return _entVersionUserLocalService.markLastVersionAsRead(userId);
	}

	/**
	 * Updates the ent version user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntVersionUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entVersionUser the ent version user
	 * @return the ent version user that was updated
	 */
	@Override
	public com.weprode.nero.about.model.EntVersionUser updateEntVersionUser(
		com.weprode.nero.about.model.EntVersionUser entVersionUser) {

		return _entVersionUserLocalService.updateEntVersionUser(entVersionUser);
	}

	@Override
	public EntVersionUserLocalService getWrappedService() {
		return _entVersionUserLocalService;
	}

	@Override
	public void setWrappedService(
		EntVersionUserLocalService entVersionUserLocalService) {

		_entVersionUserLocalService = entVersionUserLocalService;
	}

	private EntVersionUserLocalService _entVersionUserLocalService;

}