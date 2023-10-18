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
 * Provides a wrapper for {@link VersionNoteLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see VersionNoteLocalService
 * @generated
 */
public class VersionNoteLocalServiceWrapper
	implements ServiceWrapper<VersionNoteLocalService>,
			   VersionNoteLocalService {

	public VersionNoteLocalServiceWrapper() {
		this(null);
	}

	public VersionNoteLocalServiceWrapper(
		VersionNoteLocalService versionNoteLocalService) {

		_versionNoteLocalService = versionNoteLocalService;
	}

	/**
	 * Add new ENT version with today date
	 */
	@Override
	public com.weprode.nero.about.model.VersionNote addVersionNote(
			String title, String content)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _versionNoteLocalService.addVersionNote(title, content);
	}

	/**
	 * Add new ENT version
	 */
	@Override
	public com.weprode.nero.about.model.VersionNote addVersionNote(
			String title, String content, java.util.Date versionDate)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _versionNoteLocalService.addVersionNote(
			title, content, versionDate);
	}

	/**
	 * Adds the version note to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param versionNote the version note
	 * @return the version note that was added
	 */
	@Override
	public com.weprode.nero.about.model.VersionNote addVersionNote(
		com.weprode.nero.about.model.VersionNote versionNote) {

		return _versionNoteLocalService.addVersionNote(versionNote);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionNoteLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new version note with the primary key. Does not add the version note to the database.
	 *
	 * @param versionNoteId the primary key for the new version note
	 * @return the new version note
	 */
	@Override
	public com.weprode.nero.about.model.VersionNote createVersionNote(
		long versionNoteId) {

		return _versionNoteLocalService.createVersionNote(versionNoteId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionNoteLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the version note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param versionNoteId the primary key of the version note
	 * @return the version note that was removed
	 * @throws PortalException if a version note with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.about.model.VersionNote deleteVersionNote(
			long versionNoteId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionNoteLocalService.deleteVersionNote(versionNoteId);
	}

	/**
	 * Deletes the version note from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param versionNote the version note
	 * @return the version note that was removed
	 */
	@Override
	public com.weprode.nero.about.model.VersionNote deleteVersionNote(
		com.weprode.nero.about.model.VersionNote versionNote) {

		return _versionNoteLocalService.deleteVersionNote(versionNote);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _versionNoteLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _versionNoteLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _versionNoteLocalService.dynamicQuery();
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

		return _versionNoteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.VersionNoteModelImpl</code>.
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

		return _versionNoteLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.VersionNoteModelImpl</code>.
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

		return _versionNoteLocalService.dynamicQuery(
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

		return _versionNoteLocalService.dynamicQueryCount(dynamicQuery);
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

		return _versionNoteLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.about.model.VersionNote fetchVersionNote(
		long versionNoteId) {

		return _versionNoteLocalService.fetchVersionNote(versionNoteId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _versionNoteLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _versionNoteLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Get last ENT version
	 */
	@Override
	public com.weprode.nero.about.model.VersionNote getLastVersionNote() {
		return _versionNoteLocalService.getLastVersionNote();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _versionNoteLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionNoteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Get all ENT versions sorted by date (the most recent un first position)
	 */
	@Override
	public java.util.List<com.weprode.nero.about.model.VersionNote>
			getSortedVersionNotes()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _versionNoteLocalService.getSortedVersionNotes();
	}

	/**
	 * Returns the version note with the primary key.
	 *
	 * @param versionNoteId the primary key of the version note
	 * @return the version note
	 * @throws PortalException if a version note with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.about.model.VersionNote getVersionNote(
			long versionNoteId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _versionNoteLocalService.getVersionNote(versionNoteId);
	}

	/**
	 * Returns a range of all the version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.VersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of version notes
	 * @param end the upper bound of the range of version notes (not inclusive)
	 * @return the range of version notes
	 */
	@Override
	public java.util.List<com.weprode.nero.about.model.VersionNote>
		getVersionNotes(int start, int end) {

		return _versionNoteLocalService.getVersionNotes(start, end);
	}

	/**
	 * Returns the number of version notes.
	 *
	 * @return the number of version notes
	 */
	@Override
	public int getVersionNotesCount() {
		return _versionNoteLocalService.getVersionNotesCount();
	}

	@Override
	public boolean isLastVersionNote(
		com.weprode.nero.about.model.VersionNote versionNote) {

		return _versionNoteLocalService.isLastVersionNote(versionNote);
	}

	@Override
	public com.weprode.nero.about.model.VersionNote updateVersionNote(
			long versionNoteId, String title, String content)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.about.exception.NoSuchVersionNoteException {

		return _versionNoteLocalService.updateVersionNote(
			versionNoteId, title, content);
	}

	/**
	 * Updates the version note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VersionNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param versionNote the version note
	 * @return the version note that was updated
	 */
	@Override
	public com.weprode.nero.about.model.VersionNote updateVersionNote(
		com.weprode.nero.about.model.VersionNote versionNote) {

		return _versionNoteLocalService.updateVersionNote(versionNote);
	}

	@Override
	public VersionNoteLocalService getWrappedService() {
		return _versionNoteLocalService;
	}

	@Override
	public void setWrappedService(
		VersionNoteLocalService versionNoteLocalService) {

		_versionNoteLocalService = versionNoteLocalService;
	}

	private VersionNoteLocalService _versionNoteLocalService;

}