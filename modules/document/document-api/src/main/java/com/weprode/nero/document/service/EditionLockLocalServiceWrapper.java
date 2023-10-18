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

package com.weprode.nero.document.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EditionLockLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EditionLockLocalService
 * @generated
 */
public class EditionLockLocalServiceWrapper
	implements EditionLockLocalService,
			   ServiceWrapper<EditionLockLocalService> {

	public EditionLockLocalServiceWrapper() {
		this(null);
	}

	public EditionLockLocalServiceWrapper(
		EditionLockLocalService editionLockLocalService) {

		_editionLockLocalService = editionLockLocalService;
	}

	/**
	 * Adds the edition lock to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EditionLockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param editionLock the edition lock
	 * @return the edition lock that was added
	 */
	@Override
	public com.weprode.nero.document.model.EditionLock addEditionLock(
		com.weprode.nero.document.model.EditionLock editionLock) {

		return _editionLockLocalService.addEditionLock(editionLock);
	}

	@Override
	public com.weprode.nero.document.model.EditionLock addEditionLock(
			long fileId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _editionLockLocalService.addEditionLock(fileId, userId);
	}

	/**
	 * Creates a new edition lock with the primary key. Does not add the edition lock to the database.
	 *
	 * @param fileId the primary key for the new edition lock
	 * @return the new edition lock
	 */
	@Override
	public com.weprode.nero.document.model.EditionLock createEditionLock(
		long fileId) {

		return _editionLockLocalService.createEditionLock(fileId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _editionLockLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the edition lock from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EditionLockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param editionLock the edition lock
	 * @return the edition lock that was removed
	 */
	@Override
	public com.weprode.nero.document.model.EditionLock deleteEditionLock(
		com.weprode.nero.document.model.EditionLock editionLock) {

		return _editionLockLocalService.deleteEditionLock(editionLock);
	}

	/**
	 * Deletes the edition lock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EditionLockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock that was removed
	 * @throws PortalException if a edition lock with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.document.model.EditionLock deleteEditionLock(
			long fileId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _editionLockLocalService.deleteEditionLock(fileId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _editionLockLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _editionLockLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _editionLockLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _editionLockLocalService.dynamicQuery();
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

		return _editionLockLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.document.model.impl.EditionLockModelImpl</code>.
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

		return _editionLockLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.document.model.impl.EditionLockModelImpl</code>.
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

		return _editionLockLocalService.dynamicQuery(
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

		return _editionLockLocalService.dynamicQueryCount(dynamicQuery);
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

		return _editionLockLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.document.model.EditionLock fetchEditionLock(
		long fileId) {

		return _editionLockLocalService.fetchEditionLock(fileId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _editionLockLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the edition lock with the primary key.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock
	 * @throws PortalException if a edition lock with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.document.model.EditionLock getEditionLock(
			long fileId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _editionLockLocalService.getEditionLock(fileId);
	}

	/**
	 * Returns a range of all the edition locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.document.model.impl.EditionLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition locks
	 * @param end the upper bound of the range of edition locks (not inclusive)
	 * @return the range of edition locks
	 */
	@Override
	public java.util.List<com.weprode.nero.document.model.EditionLock>
		getEditionLocks(int start, int end) {

		return _editionLockLocalService.getEditionLocks(start, end);
	}

	/**
	 * Returns the number of edition locks.
	 *
	 * @return the number of edition locks
	 */
	@Override
	public int getEditionLocksCount() {
		return _editionLockLocalService.getEditionLocksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _editionLockLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _editionLockLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _editionLockLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.weprode.nero.document.model.EditionLock isFileEdited(Long fileId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _editionLockLocalService.isFileEdited(fileId);
	}

	@Override
	public boolean removeEditionLock(long fileId) {
		return _editionLockLocalService.removeEditionLock(fileId);
	}

	/**
	 * Updates the edition lock in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EditionLockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param editionLock the edition lock
	 * @return the edition lock that was updated
	 */
	@Override
	public com.weprode.nero.document.model.EditionLock updateEditionLock(
		com.weprode.nero.document.model.EditionLock editionLock) {

		return _editionLockLocalService.updateEditionLock(editionLock);
	}

	@Override
	public EditionLockLocalService getWrappedService() {
		return _editionLockLocalService;
	}

	@Override
	public void setWrappedService(
		EditionLockLocalService editionLockLocalService) {

		_editionLockLocalService = editionLockLocalService;
	}

	private EditionLockLocalService _editionLockLocalService;

}