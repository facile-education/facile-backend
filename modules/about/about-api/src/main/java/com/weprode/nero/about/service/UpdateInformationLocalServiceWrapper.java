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
 * Provides a wrapper for {@link UpdateInformationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UpdateInformationLocalService
 * @generated
 */
public class UpdateInformationLocalServiceWrapper
	implements ServiceWrapper<UpdateInformationLocalService>,
			   UpdateInformationLocalService {

	public UpdateInformationLocalServiceWrapper(
		UpdateInformationLocalService updateInformationLocalService) {

		_updateInformationLocalService = updateInformationLocalService;
	}

	@Override
	public com.weprode.nero.about.model.UpdateInformation addUpdateInformation()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _updateInformationLocalService.addUpdateInformation();
	}

	/**
	 * Adds the update information to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UpdateInformationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param updateInformation the update information
	 * @return the update information that was added
	 */
	@Override
	public com.weprode.nero.about.model.UpdateInformation addUpdateInformation(
		com.weprode.nero.about.model.UpdateInformation updateInformation) {

		return _updateInformationLocalService.addUpdateInformation(
			updateInformation);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _updateInformationLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new update information with the primary key. Does not add the update information to the database.
	 *
	 * @param updateInfoId the primary key for the new update information
	 * @return the new update information
	 */
	@Override
	public com.weprode.nero.about.model.UpdateInformation
		createUpdateInformation(long updateInfoId) {

		return _updateInformationLocalService.createUpdateInformation(
			updateInfoId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _updateInformationLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the update information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UpdateInformationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information that was removed
	 * @throws PortalException if a update information with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.about.model.UpdateInformation
			deleteUpdateInformation(long updateInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _updateInformationLocalService.deleteUpdateInformation(
			updateInfoId);
	}

	/**
	 * Deletes the update information from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UpdateInformationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param updateInformation the update information
	 * @return the update information that was removed
	 */
	@Override
	public com.weprode.nero.about.model.UpdateInformation
		deleteUpdateInformation(
			com.weprode.nero.about.model.UpdateInformation updateInformation) {

		return _updateInformationLocalService.deleteUpdateInformation(
			updateInformation);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _updateInformationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _updateInformationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _updateInformationLocalService.dynamicQuery();
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

		return _updateInformationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.UpdateInformationModelImpl</code>.
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

		return _updateInformationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.UpdateInformationModelImpl</code>.
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

		return _updateInformationLocalService.dynamicQuery(
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

		return _updateInformationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _updateInformationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.about.model.UpdateInformation
		fetchUpdateInformation(long updateInfoId) {

		return _updateInformationLocalService.fetchUpdateInformation(
			updateInfoId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _updateInformationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _updateInformationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _updateInformationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _updateInformationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.weprode.nero.about.model.UpdateInformation getUpdateInformation()
		throws com.liferay.portal.kernel.exception.SystemException {

		return _updateInformationLocalService.getUpdateInformation();
	}

	/**
	 * Returns the update information with the primary key.
	 *
	 * @param updateInfoId the primary key of the update information
	 * @return the update information
	 * @throws PortalException if a update information with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.about.model.UpdateInformation getUpdateInformation(
			long updateInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _updateInformationLocalService.getUpdateInformation(
			updateInfoId);
	}

	/**
	 * Returns a range of all the update informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.about.model.impl.UpdateInformationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of update informations
	 * @param end the upper bound of the range of update informations (not inclusive)
	 * @return the range of update informations
	 */
	@Override
	public java.util.List<com.weprode.nero.about.model.UpdateInformation>
		getUpdateInformations(int start, int end) {

		return _updateInformationLocalService.getUpdateInformations(start, end);
	}

	/**
	 * Returns the number of update informations.
	 *
	 * @return the number of update informations
	 */
	@Override
	public int getUpdateInformationsCount() {
		return _updateInformationLocalService.getUpdateInformationsCount();
	}

	@Override
	public com.weprode.nero.about.model.UpdateInformation
			UpdateUpdateInformation(String text, java.util.Date modifyDate)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _updateInformationLocalService.UpdateUpdateInformation(
			text, modifyDate);
	}

	/**
	 * Updates the update information in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UpdateInformationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param updateInformation the update information
	 * @return the update information that was updated
	 */
	@Override
	public com.weprode.nero.about.model.UpdateInformation
		updateUpdateInformation(
			com.weprode.nero.about.model.UpdateInformation updateInformation) {

		return _updateInformationLocalService.updateUpdateInformation(
			updateInformation);
	}

	@Override
	public UpdateInformationLocalService getWrappedService() {
		return _updateInformationLocalService;
	}

	@Override
	public void setWrappedService(
		UpdateInformationLocalService updateInformationLocalService) {

		_updateInformationLocalService = updateInformationLocalService;
	}

	private UpdateInformationLocalService _updateInformationLocalService;

}