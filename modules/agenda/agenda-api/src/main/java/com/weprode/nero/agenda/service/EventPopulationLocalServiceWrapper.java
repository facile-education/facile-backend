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

package com.weprode.nero.agenda.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EventPopulationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EventPopulationLocalService
 * @generated
 */
public class EventPopulationLocalServiceWrapper
	implements EventPopulationLocalService,
			   ServiceWrapper<EventPopulationLocalService> {

	public EventPopulationLocalServiceWrapper() {
		this(null);
	}

	public EventPopulationLocalServiceWrapper(
		EventPopulationLocalService eventPopulationLocalService) {

		_eventPopulationLocalService = eventPopulationLocalService;
	}

	/**
	 * Adds the event population to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventPopulationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventPopulation the event population
	 * @return the event population that was added
	 */
	@Override
	public com.weprode.nero.agenda.model.EventPopulation addEventPopulation(
		com.weprode.nero.agenda.model.EventPopulation eventPopulation) {

		return _eventPopulationLocalService.addEventPopulation(eventPopulation);
	}

	@Override
	public com.weprode.nero.agenda.model.EventPopulation addPopulation(
			long eventId, long groupId, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _eventPopulationLocalService.addPopulation(
			eventId, groupId, roleId);
	}

	@Override
	public org.json.JSONArray convertEventPopulations(long eventId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _eventPopulationLocalService.convertEventPopulations(
			eventId, userId);
	}

	/**
	 * Creates a new event population with the primary key. Does not add the event population to the database.
	 *
	 * @param eventPopulationPK the primary key for the new event population
	 * @return the new event population
	 */
	@Override
	public com.weprode.nero.agenda.model.EventPopulation createEventPopulation(
		com.weprode.nero.agenda.service.persistence.EventPopulationPK
			eventPopulationPK) {

		return _eventPopulationLocalService.createEventPopulation(
			eventPopulationPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventPopulationLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the event population from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventPopulationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventPopulation the event population
	 * @return the event population that was removed
	 */
	@Override
	public com.weprode.nero.agenda.model.EventPopulation deleteEventPopulation(
		com.weprode.nero.agenda.model.EventPopulation eventPopulation) {

		return _eventPopulationLocalService.deleteEventPopulation(
			eventPopulation);
	}

	/**
	 * Deletes the event population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventPopulationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population that was removed
	 * @throws PortalException if a event population with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.agenda.model.EventPopulation deleteEventPopulation(
			com.weprode.nero.agenda.service.persistence.EventPopulationPK
				eventPopulationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventPopulationLocalService.deleteEventPopulation(
			eventPopulationPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventPopulationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _eventPopulationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _eventPopulationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eventPopulationLocalService.dynamicQuery();
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

		return _eventPopulationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.agenda.model.impl.EventPopulationModelImpl</code>.
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

		return _eventPopulationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.agenda.model.impl.EventPopulationModelImpl</code>.
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

		return _eventPopulationLocalService.dynamicQuery(
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

		return _eventPopulationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _eventPopulationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.agenda.model.EventPopulation fetchEventPopulation(
		com.weprode.nero.agenda.service.persistence.EventPopulationPK
			eventPopulationPK) {

		return _eventPopulationLocalService.fetchEventPopulation(
			eventPopulationPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _eventPopulationLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the event population with the primary key.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population
	 * @throws PortalException if a event population with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.agenda.model.EventPopulation getEventPopulation(
			com.weprode.nero.agenda.service.persistence.EventPopulationPK
				eventPopulationPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventPopulationLocalService.getEventPopulation(
			eventPopulationPK);
	}

	/**
	 * Returns a range of all the event populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.agenda.model.impl.EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @return the range of event populations
	 */
	@Override
	public java.util.List<com.weprode.nero.agenda.model.EventPopulation>
		getEventPopulations(int start, int end) {

		return _eventPopulationLocalService.getEventPopulations(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.agenda.model.EventPopulation>
			getEventPopulations(long eventId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _eventPopulationLocalService.getEventPopulations(eventId);
	}

	/**
	 * Returns the number of event populations.
	 *
	 * @return the number of event populations
	 */
	@Override
	public int getEventPopulationsCount() {
		return _eventPopulationLocalService.getEventPopulationsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _eventPopulationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _eventPopulationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventPopulationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the event population in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventPopulationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventPopulation the event population
	 * @return the event population that was updated
	 */
	@Override
	public com.weprode.nero.agenda.model.EventPopulation updateEventPopulation(
		com.weprode.nero.agenda.model.EventPopulation eventPopulation) {

		return _eventPopulationLocalService.updateEventPopulation(
			eventPopulation);
	}

	@Override
	public EventPopulationLocalService getWrappedService() {
		return _eventPopulationLocalService;
	}

	@Override
	public void setWrappedService(
		EventPopulationLocalService eventPopulationLocalService) {

		_eventPopulationLocalService = eventPopulationLocalService;
	}

	private EventPopulationLocalService _eventPopulationLocalService;

}