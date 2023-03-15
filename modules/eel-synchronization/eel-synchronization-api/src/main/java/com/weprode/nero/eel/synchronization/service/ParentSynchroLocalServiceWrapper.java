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

package com.weprode.nero.eel.synchronization.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ParentSynchroLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ParentSynchroLocalService
 * @generated
 */
public class ParentSynchroLocalServiceWrapper
	implements ParentSynchroLocalService,
			   ServiceWrapper<ParentSynchroLocalService> {

	public ParentSynchroLocalServiceWrapper(
		ParentSynchroLocalService parentSynchroLocalService) {

		_parentSynchroLocalService = parentSynchroLocalService;
	}

	@Override
	public com.weprode.nero.eel.synchronization.model.ParentSynchro
			addParentSynchro(
				long schoolId, java.util.Date startDate, java.util.Date endDate,
				String fileName, long lineCount, long errorCount)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _parentSynchroLocalService.addParentSynchro(
			schoolId, startDate, endDate, fileName, lineCount, errorCount);
	}

	/**
	 * Adds the parent synchro to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParentSynchroLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param parentSynchro the parent synchro
	 * @return the parent synchro that was added
	 */
	@Override
	public com.weprode.nero.eel.synchronization.model.ParentSynchro
		addParentSynchro(
			com.weprode.nero.eel.synchronization.model.ParentSynchro
				parentSynchro) {

		return _parentSynchroLocalService.addParentSynchro(parentSynchro);
	}

	/**
	 * Creates a new parent synchro with the primary key. Does not add the parent synchro to the database.
	 *
	 * @param schoolId the primary key for the new parent synchro
	 * @return the new parent synchro
	 */
	@Override
	public com.weprode.nero.eel.synchronization.model.ParentSynchro
		createParentSynchro(long schoolId) {

		return _parentSynchroLocalService.createParentSynchro(schoolId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _parentSynchroLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the parent synchro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParentSynchroLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro that was removed
	 * @throws PortalException if a parent synchro with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.eel.synchronization.model.ParentSynchro
			deleteParentSynchro(long schoolId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _parentSynchroLocalService.deleteParentSynchro(schoolId);
	}

	/**
	 * Deletes the parent synchro from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParentSynchroLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param parentSynchro the parent synchro
	 * @return the parent synchro that was removed
	 */
	@Override
	public com.weprode.nero.eel.synchronization.model.ParentSynchro
		deleteParentSynchro(
			com.weprode.nero.eel.synchronization.model.ParentSynchro
				parentSynchro) {

		return _parentSynchroLocalService.deleteParentSynchro(parentSynchro);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _parentSynchroLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _parentSynchroLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _parentSynchroLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _parentSynchroLocalService.dynamicQuery();
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

		return _parentSynchroLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.eel.synchronization.model.impl.ParentSynchroModelImpl</code>.
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

		return _parentSynchroLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.eel.synchronization.model.impl.ParentSynchroModelImpl</code>.
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

		return _parentSynchroLocalService.dynamicQuery(
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

		return _parentSynchroLocalService.dynamicQueryCount(dynamicQuery);
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

		return _parentSynchroLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.eel.synchronization.model.ParentSynchro
		fetchParentSynchro(long schoolId) {

		return _parentSynchroLocalService.fetchParentSynchro(schoolId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _parentSynchroLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _parentSynchroLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _parentSynchroLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the parent synchro with the primary key.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro
	 * @throws PortalException if a parent synchro with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.eel.synchronization.model.ParentSynchro
			getParentSynchro(long schoolId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _parentSynchroLocalService.getParentSynchro(schoolId);
	}

	/**
	 * Returns a range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.eel.synchronization.model.impl.ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @return the range of parent synchros
	 */
	@Override
	public java.util.List
		<com.weprode.nero.eel.synchronization.model.ParentSynchro>
			getParentSynchros(int start, int end) {

		return _parentSynchroLocalService.getParentSynchros(start, end);
	}

	/**
	 * Returns the number of parent synchros.
	 *
	 * @return the number of parent synchros
	 */
	@Override
	public int getParentSynchrosCount() {
		return _parentSynchroLocalService.getParentSynchrosCount();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _parentSynchroLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the parent synchro in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParentSynchroLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param parentSynchro the parent synchro
	 * @return the parent synchro that was updated
	 */
	@Override
	public com.weprode.nero.eel.synchronization.model.ParentSynchro
		updateParentSynchro(
			com.weprode.nero.eel.synchronization.model.ParentSynchro
				parentSynchro) {

		return _parentSynchroLocalService.updateParentSynchro(parentSynchro);
	}

	@Override
	public ParentSynchroLocalService getWrappedService() {
		return _parentSynchroLocalService;
	}

	@Override
	public void setWrappedService(
		ParentSynchroLocalService parentSynchroLocalService) {

		_parentSynchroLocalService = parentSynchroLocalService;
	}

	private ParentSynchroLocalService _parentSynchroLocalService;

}