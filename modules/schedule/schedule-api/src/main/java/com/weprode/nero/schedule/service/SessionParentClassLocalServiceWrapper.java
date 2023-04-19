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

package com.weprode.nero.schedule.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SessionParentClassLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SessionParentClassLocalService
 * @generated
 */
public class SessionParentClassLocalServiceWrapper
	implements ServiceWrapper<SessionParentClassLocalService>,
			   SessionParentClassLocalService {

	public SessionParentClassLocalServiceWrapper(
		SessionParentClassLocalService sessionParentClassLocalService) {

		_sessionParentClassLocalService = sessionParentClassLocalService;
	}

	@Override
	public com.weprode.nero.schedule.model.SessionParentClass
		addSessionParentClass(long sessionId, long groupId) {

		return _sessionParentClassLocalService.addSessionParentClass(
			sessionId, groupId);
	}

	/**
	 * Adds the session parent class to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClass the session parent class
	 * @return the session parent class that was added
	 */
	@Override
	public com.weprode.nero.schedule.model.SessionParentClass
		addSessionParentClass(
			com.weprode.nero.schedule.model.SessionParentClass
				sessionParentClass) {

		return _sessionParentClassLocalService.addSessionParentClass(
			sessionParentClass);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionParentClassLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new session parent class with the primary key. Does not add the session parent class to the database.
	 *
	 * @param sessionParentClassId the primary key for the new session parent class
	 * @return the new session parent class
	 */
	@Override
	public com.weprode.nero.schedule.model.SessionParentClass
		createSessionParentClass(long sessionParentClassId) {

		return _sessionParentClassLocalService.createSessionParentClass(
			sessionParentClassId);
	}

	@Override
	public boolean deleteByGroupId(long groupId) {
		return _sessionParentClassLocalService.deleteByGroupId(groupId);
	}

	@Override
	public boolean deleteBySessionId(long sessionId) {
		return _sessionParentClassLocalService.deleteBySessionId(sessionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionParentClassLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the session parent class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class that was removed
	 * @throws PortalException if a session parent class with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.SessionParentClass
			deleteSessionParentClass(long sessionParentClassId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionParentClassLocalService.deleteSessionParentClass(
			sessionParentClassId);
	}

	/**
	 * Deletes the session parent class from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClass the session parent class
	 * @return the session parent class that was removed
	 */
	@Override
	public com.weprode.nero.schedule.model.SessionParentClass
		deleteSessionParentClass(
			com.weprode.nero.schedule.model.SessionParentClass
				sessionParentClass) {

		return _sessionParentClassLocalService.deleteSessionParentClass(
			sessionParentClass);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _sessionParentClassLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _sessionParentClassLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sessionParentClassLocalService.dynamicQuery();
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

		return _sessionParentClassLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl</code>.
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

		return _sessionParentClassLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl</code>.
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

		return _sessionParentClassLocalService.dynamicQuery(
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

		return _sessionParentClassLocalService.dynamicQueryCount(dynamicQuery);
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

		return _sessionParentClassLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.schedule.model.SessionParentClass
		fetchSessionParentClass(long sessionParentClassId) {

		return _sessionParentClassLocalService.fetchSessionParentClass(
			sessionParentClassId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _sessionParentClassLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<Long> getGroupSessions(long groupId) {
		return _sessionParentClassLocalService.getGroupSessions(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _sessionParentClassLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sessionParentClassLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public String getParentClassName(long sessionId) {
		return _sessionParentClassLocalService.getParentClassName(sessionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionParentClassLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the session parent class with the primary key.
	 *
	 * @param sessionParentClassId the primary key of the session parent class
	 * @return the session parent class
	 * @throws PortalException if a session parent class with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.schedule.model.SessionParentClass
			getSessionParentClass(long sessionParentClassId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionParentClassLocalService.getSessionParentClass(
			sessionParentClassId);
	}

	/**
	 * Returns a range of all the session parent classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.schedule.model.impl.SessionParentClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of session parent classes
	 * @param end the upper bound of the range of session parent classes (not inclusive)
	 * @return the range of session parent classes
	 */
	@Override
	public java.util.List<com.weprode.nero.schedule.model.SessionParentClass>
		getSessionParentClasses(int start, int end) {

		return _sessionParentClassLocalService.getSessionParentClasses(
			start, end);
	}

	/**
	 * Returns the number of session parent classes.
	 *
	 * @return the number of session parent classes
	 */
	@Override
	public int getSessionParentClassesCount() {
		return _sessionParentClassLocalService.getSessionParentClassesCount();
	}

	@Override
	public java.util.List<Long> getSessionParentGroupIds(long sessionId) {
		return _sessionParentClassLocalService.getSessionParentGroupIds(
			sessionId);
	}

	/**
	 * Updates the session parent class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SessionParentClassLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sessionParentClass the session parent class
	 * @return the session parent class that was updated
	 */
	@Override
	public com.weprode.nero.schedule.model.SessionParentClass
		updateSessionParentClass(
			com.weprode.nero.schedule.model.SessionParentClass
				sessionParentClass) {

		return _sessionParentClassLocalService.updateSessionParentClass(
			sessionParentClass);
	}

	@Override
	public SessionParentClassLocalService getWrappedService() {
		return _sessionParentClassLocalService;
	}

	@Override
	public void setWrappedService(
		SessionParentClassLocalService sessionParentClassLocalService) {

		_sessionParentClassLocalService = sessionParentClassLocalService;
	}

	private SessionParentClassLocalService _sessionParentClassLocalService;

}