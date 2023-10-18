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
 * Provides a wrapper for {@link EventReadLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EventReadLocalService
 * @generated
 */
public class EventReadLocalServiceWrapper
	implements EventReadLocalService, ServiceWrapper<EventReadLocalService> {

	public EventReadLocalServiceWrapper() {
		this(null);
	}

	public EventReadLocalServiceWrapper(
		EventReadLocalService eventReadLocalService) {

		_eventReadLocalService = eventReadLocalService;
	}

	/**
	 * Adds the event read to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventReadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventRead the event read
	 * @return the event read that was added
	 */
	@Override
	public com.weprode.nero.agenda.model.EventRead addEventRead(
		com.weprode.nero.agenda.model.EventRead eventRead) {

		return _eventReadLocalService.addEventRead(eventRead);
	}

	/**
	 * Creates a new event read with the primary key. Does not add the event read to the database.
	 *
	 * @param eventReadPK the primary key for the new event read
	 * @return the new event read
	 */
	@Override
	public com.weprode.nero.agenda.model.EventRead createEventRead(
		com.weprode.nero.agenda.service.persistence.EventReadPK eventReadPK) {

		return _eventReadLocalService.createEventRead(eventReadPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventReadLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the event read from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventReadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventRead the event read
	 * @return the event read that was removed
	 */
	@Override
	public com.weprode.nero.agenda.model.EventRead deleteEventRead(
		com.weprode.nero.agenda.model.EventRead eventRead) {

		return _eventReadLocalService.deleteEventRead(eventRead);
	}

	/**
	 * Deletes the event read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventReadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read that was removed
	 * @throws PortalException if a event read with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.agenda.model.EventRead deleteEventRead(
			com.weprode.nero.agenda.service.persistence.EventReadPK eventReadPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventReadLocalService.deleteEventRead(eventReadPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventReadLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _eventReadLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _eventReadLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eventReadLocalService.dynamicQuery();
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

		return _eventReadLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.agenda.model.impl.EventReadModelImpl</code>.
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

		return _eventReadLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.agenda.model.impl.EventReadModelImpl</code>.
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

		return _eventReadLocalService.dynamicQuery(
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

		return _eventReadLocalService.dynamicQueryCount(dynamicQuery);
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

		return _eventReadLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.agenda.model.EventRead fetchEventRead(
		com.weprode.nero.agenda.service.persistence.EventReadPK eventReadPK) {

		return _eventReadLocalService.fetchEventRead(eventReadPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _eventReadLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the event read with the primary key.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read
	 * @throws PortalException if a event read with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.agenda.model.EventRead getEventRead(
			com.weprode.nero.agenda.service.persistence.EventReadPK eventReadPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventReadLocalService.getEventRead(eventReadPK);
	}

	/**
	 * Returns a range of all the event reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.agenda.model.impl.EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @return the range of event reads
	 */
	@Override
	public java.util.List<com.weprode.nero.agenda.model.EventRead>
		getEventReads(int start, int end) {

		return _eventReadLocalService.getEventReads(start, end);
	}

	/**
	 * Returns the number of event reads.
	 *
	 * @return the number of event reads
	 */
	@Override
	public int getEventReadsCount() {
		return _eventReadLocalService.getEventReadsCount();
	}

	@Override
	public org.json.JSONArray getEventReadStatus(long eventId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _eventReadLocalService.getEventReadStatus(eventId, userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _eventReadLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _eventReadLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventReadLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.weprode.nero.agenda.model.EventRead getUserReadEvent(
		long userId, long eventId) {

		return _eventReadLocalService.getUserReadEvent(userId, eventId);
	}

	@Override
	public boolean hasUserReadEvent(long userId, long eventId) {
		return _eventReadLocalService.hasUserReadEvent(userId, eventId);
	}

	@Override
	public boolean markEventAsRead(long userId, long eventId) {
		return _eventReadLocalService.markEventAsRead(userId, eventId);
	}

	@Override
	public boolean markEventAsUnRead(long userId, long eventId) {
		return _eventReadLocalService.markEventAsUnRead(userId, eventId);
	}

	@Override
	public boolean markEventAsUnReadForAll(long eventId) {
		return _eventReadLocalService.markEventAsUnReadForAll(eventId);
	}

	/**
	 * Updates the event read in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventReadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventRead the event read
	 * @return the event read that was updated
	 */
	@Override
	public com.weprode.nero.agenda.model.EventRead updateEventRead(
		com.weprode.nero.agenda.model.EventRead eventRead) {

		return _eventReadLocalService.updateEventRead(eventRead);
	}

	@Override
	public EventReadLocalService getWrappedService() {
		return _eventReadLocalService;
	}

	@Override
	public void setWrappedService(EventReadLocalService eventReadLocalService) {
		_eventReadLocalService = eventReadLocalService;
	}

	private EventReadLocalService _eventReadLocalService;

}