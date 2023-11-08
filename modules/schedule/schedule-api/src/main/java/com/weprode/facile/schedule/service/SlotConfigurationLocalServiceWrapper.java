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

package com.weprode.facile.schedule.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SlotConfigurationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SlotConfigurationLocalService
 * @generated
 */
public class SlotConfigurationLocalServiceWrapper
	implements ServiceWrapper<SlotConfigurationLocalService>,
			   SlotConfigurationLocalService {

	public SlotConfigurationLocalServiceWrapper() {
		this(null);
	}

	public SlotConfigurationLocalServiceWrapper(
		SlotConfigurationLocalService slotConfigurationLocalService) {

		_slotConfigurationLocalService = slotConfigurationLocalService;
	}

	@Override
	public void addSchoolSlot(
		long schoolId, int slotNumber, String sessionStartHour,
		String sessionEndHour) {

		_slotConfigurationLocalService.addSchoolSlot(
			schoolId, slotNumber, sessionStartHour, sessionEndHour);
	}

	/**
	 * Adds the slot configuration to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SlotConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param slotConfiguration the slot configuration
	 * @return the slot configuration that was added
	 */
	@Override
	public com.weprode.facile.schedule.model.SlotConfiguration
		addSlotConfiguration(
			com.weprode.facile.schedule.model.SlotConfiguration
				slotConfiguration) {

		return _slotConfigurationLocalService.addSlotConfiguration(
			slotConfiguration);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _slotConfigurationLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new slot configuration with the primary key. Does not add the slot configuration to the database.
	 *
	 * @param slotConfigurationPK the primary key for the new slot configuration
	 * @return the new slot configuration
	 */
	@Override
	public com.weprode.facile.schedule.model.SlotConfiguration
		createSlotConfiguration(
			com.weprode.facile.schedule.service.persistence.SlotConfigurationPK
				slotConfigurationPK) {

		return _slotConfigurationLocalService.createSlotConfiguration(
			slotConfigurationPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _slotConfigurationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public void deleteSchoolSlots(long schoolId) {
		_slotConfigurationLocalService.deleteSchoolSlots(schoolId);
	}

	/**
	 * Deletes the slot configuration from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SlotConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param slotConfiguration the slot configuration
	 * @return the slot configuration that was removed
	 */
	@Override
	public com.weprode.facile.schedule.model.SlotConfiguration
		deleteSlotConfiguration(
			com.weprode.facile.schedule.model.SlotConfiguration
				slotConfiguration) {

		return _slotConfigurationLocalService.deleteSlotConfiguration(
			slotConfiguration);
	}

	/**
	 * Deletes the slot configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SlotConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration that was removed
	 * @throws PortalException if a slot configuration with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.schedule.model.SlotConfiguration
			deleteSlotConfiguration(
				com.weprode.facile.schedule.service.persistence.
					SlotConfigurationPK slotConfigurationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _slotConfigurationLocalService.deleteSlotConfiguration(
			slotConfigurationPK);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _slotConfigurationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _slotConfigurationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _slotConfigurationLocalService.dynamicQuery();
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

		return _slotConfigurationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.SlotConfigurationModelImpl</code>.
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

		return _slotConfigurationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.SlotConfigurationModelImpl</code>.
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

		return _slotConfigurationLocalService.dynamicQuery(
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

		return _slotConfigurationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _slotConfigurationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.schedule.model.SlotConfiguration
		fetchSlotConfiguration(
			com.weprode.facile.schedule.service.persistence.SlotConfigurationPK
				slotConfigurationPK) {

		return _slotConfigurationLocalService.fetchSlotConfiguration(
			slotConfigurationPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _slotConfigurationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _slotConfigurationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _slotConfigurationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _slotConfigurationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.weprode.facile.schedule.model.SlotConfiguration>
		getSchoolSlots(long schoolId) {

		return _slotConfigurationLocalService.getSchoolSlots(schoolId);
	}

	@Override
	public org.json.JSONArray getSchoolSlotsAsJson(long schoolId) {
		return _slotConfigurationLocalService.getSchoolSlotsAsJson(schoolId);
	}

	/**
	 * Returns the slot configuration with the primary key.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration
	 * @throws PortalException if a slot configuration with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.schedule.model.SlotConfiguration
			getSlotConfiguration(
				com.weprode.facile.schedule.service.persistence.
					SlotConfigurationPK slotConfigurationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _slotConfigurationLocalService.getSlotConfiguration(
			slotConfigurationPK);
	}

	/**
	 * Returns a range of all the slot configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.schedule.model.impl.SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @return the range of slot configurations
	 */
	@Override
	public java.util.List<com.weprode.facile.schedule.model.SlotConfiguration>
		getSlotConfigurations(int start, int end) {

		return _slotConfigurationLocalService.getSlotConfigurations(start, end);
	}

	/**
	 * Returns the number of slot configurations.
	 *
	 * @return the number of slot configurations
	 */
	@Override
	public int getSlotConfigurationsCount() {
		return _slotConfigurationLocalService.getSlotConfigurationsCount();
	}

	@Override
	public void saveSchoolSlots(long schoolId, org.json.JSONArray jsonSlots) {
		_slotConfigurationLocalService.saveSchoolSlots(schoolId, jsonSlots);
	}

	/**
	 * Updates the slot configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SlotConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param slotConfiguration the slot configuration
	 * @return the slot configuration that was updated
	 */
	@Override
	public com.weprode.facile.schedule.model.SlotConfiguration
		updateSlotConfiguration(
			com.weprode.facile.schedule.model.SlotConfiguration
				slotConfiguration) {

		return _slotConfigurationLocalService.updateSlotConfiguration(
			slotConfiguration);
	}

	@Override
	public SlotConfigurationLocalService getWrappedService() {
		return _slotConfigurationLocalService;
	}

	@Override
	public void setWrappedService(
		SlotConfigurationLocalService slotConfigurationLocalService) {

		_slotConfigurationLocalService = slotConfigurationLocalService;
	}

	private SlotConfigurationLocalService _slotConfigurationLocalService;

}