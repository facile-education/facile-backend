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
 * Provides a wrapper for {@link EntVersionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntVersionLocalService
 * @generated
 */
public class EntVersionLocalServiceWrapper
	implements EntVersionLocalService, ServiceWrapper<EntVersionLocalService> {

	public EntVersionLocalServiceWrapper(
		EntVersionLocalService entVersionLocalService) {

		_entVersionLocalService = entVersionLocalService;
	}

	/**
	 * Adds the ent version to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entVersion the ent version
	 * @return the ent version that was added
	 */
	@Override
	public com.weprode.nero.about.model.EntVersion addEntVersion(
		com.weprode.nero.about.model.EntVersion entVersion) {

		return _entVersionLocalService.addEntVersion(entVersion);
	}

	/**
	 * Add new ENT version with today date
	 */
	@Override
	public com.weprode.nero.about.model.EntVersion addVersionNote(
			String title, String content)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _entVersionLocalService.addVersionNote(title, content);
	}

	/**
	 * Add new ENT version
	 */
	@Override
	public com.weprode.nero.about.model.EntVersion addVersionNote(
			String title, String content, java.util.Date versionDate)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _entVersionLocalService.addVersionNote(
			title, content, versionDate);
	}

	/**
	 * Creates a new ent version with the primary key. Does not add the ent version to the database.
	 *
	 * @param entVersionId the primary key for the new ent version
	 * @return the new ent version
	 */
	@Override
	public com.weprode.nero.about.model.EntVersion createEntVersion(
		long entVersionId) {

		return _entVersionLocalService.createEntVersion(entVersionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the ent version from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entVersion the ent version
	 * @return the ent version that was removed
	 */
	@Override
	public com.weprode.nero.about.model.EntVersion deleteEntVersion(
		com.weprode.nero.about.model.EntVersion entVersion) {

		return _entVersionLocalService.deleteEntVersion(entVersion);
	}

	/**
	 * Deletes the ent version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version that was removed
	 * @throws PortalException if a ent version with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.about.model.EntVersion deleteEntVersion(
			long entVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionLocalService.deleteEntVersion(entVersionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _entVersionLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _entVersionLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entVersionLocalService.dynamicQuery();
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

		return _entVersionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.EntVersionModelImpl</code>.
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

		return _entVersionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.EntVersionModelImpl</code>.
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

		return _entVersionLocalService.dynamicQuery(
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

		return _entVersionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _entVersionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.about.model.EntVersion fetchEntVersion(
		long entVersionId) {

		return _entVersionLocalService.fetchEntVersion(entVersionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _entVersionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the ent version with the primary key.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version
	 * @throws PortalException if a ent version with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.about.model.EntVersion getEntVersion(
			long entVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionLocalService.getEntVersion(entVersionId);
	}

	/**
	 * Returns a range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @return the range of ent versions
	 */
	@Override
	public java.util.List<com.weprode.nero.about.model.EntVersion>
		getEntVersions(int start, int end) {

		return _entVersionLocalService.getEntVersions(start, end);
	}

	/**
	 * Returns the number of ent versions.
	 *
	 * @return the number of ent versions
	 */
	@Override
	public int getEntVersionsCount() {
		return _entVersionLocalService.getEntVersionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _entVersionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Get last ENT version
	 */
	@Override
	public com.weprode.nero.about.model.EntVersion getLastVersionNote() {
		return _entVersionLocalService.getLastVersionNote();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _entVersionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entVersionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Get all ENT versions sorted by date (the most recent un first position)
	 */
	@Override
	public java.util.List<com.weprode.nero.about.model.EntVersion>
			getSortedVersionNotes()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _entVersionLocalService.getSortedVersionNotes();
	}

	/**
	 * Updates the ent version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entVersion the ent version
	 * @return the ent version that was updated
	 */
	@Override
	public com.weprode.nero.about.model.EntVersion updateEntVersion(
		com.weprode.nero.about.model.EntVersion entVersion) {

		return _entVersionLocalService.updateEntVersion(entVersion);
	}

	@Override
	public com.weprode.nero.about.model.EntVersion updateVersionNote(
			long versionNoteId, String title, String content)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.about.exception.NoSuchEntVersionException {

		return _entVersionLocalService.updateVersionNote(
			versionNoteId, title, content);
	}

	@Override
	public EntVersionLocalService getWrappedService() {
		return _entVersionLocalService;
	}

	@Override
	public void setWrappedService(
		EntVersionLocalService entVersionLocalService) {

		_entVersionLocalService = entVersionLocalService;
	}

	private EntVersionLocalService _entVersionLocalService;

}