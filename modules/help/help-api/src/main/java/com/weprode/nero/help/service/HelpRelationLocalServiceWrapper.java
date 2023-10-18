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

package com.weprode.nero.help.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HelpRelationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpRelationLocalService
 * @generated
 */
public class HelpRelationLocalServiceWrapper
	implements HelpRelationLocalService,
			   ServiceWrapper<HelpRelationLocalService> {

	public HelpRelationLocalServiceWrapper() {
		this(null);
	}

	public HelpRelationLocalServiceWrapper(
		HelpRelationLocalService helpRelationLocalService) {

		_helpRelationLocalService = helpRelationLocalService;
	}

	/**
	 * Adds the help relation to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpRelationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpRelation the help relation
	 * @return the help relation that was added
	 */
	@Override
	public com.weprode.nero.help.model.HelpRelation addHelpRelation(
		com.weprode.nero.help.model.HelpRelation helpRelation) {

		return _helpRelationLocalService.addHelpRelation(helpRelation);
	}

	@Override
	public com.weprode.nero.help.model.HelpRelation addHelpRelation(
		long itemId, long relatedItemId) {

		return _helpRelationLocalService.addHelpRelation(itemId, relatedItemId);
	}

	/**
	 * Creates a new help relation with the primary key. Does not add the help relation to the database.
	 *
	 * @param relationId the primary key for the new help relation
	 * @return the new help relation
	 */
	@Override
	public com.weprode.nero.help.model.HelpRelation createHelpRelation(
		long relationId) {

		return _helpRelationLocalService.createHelpRelation(relationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRelationLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the help relation from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpRelationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpRelation the help relation
	 * @return the help relation that was removed
	 */
	@Override
	public com.weprode.nero.help.model.HelpRelation deleteHelpRelation(
		com.weprode.nero.help.model.HelpRelation helpRelation) {

		return _helpRelationLocalService.deleteHelpRelation(helpRelation);
	}

	/**
	 * Deletes the help relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpRelationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation that was removed
	 * @throws PortalException if a help relation with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.help.model.HelpRelation deleteHelpRelation(
			long relationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRelationLocalService.deleteHelpRelation(relationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRelationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _helpRelationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _helpRelationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helpRelationLocalService.dynamicQuery();
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

		return _helpRelationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpRelationModelImpl</code>.
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

		return _helpRelationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpRelationModelImpl</code>.
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

		return _helpRelationLocalService.dynamicQuery(
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

		return _helpRelationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _helpRelationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.help.model.HelpRelation fetchHelpRelation(
		long relationId) {

		return _helpRelationLocalService.fetchHelpRelation(relationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _helpRelationLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the help relation with the primary key.
	 *
	 * @param relationId the primary key of the help relation
	 * @return the help relation
	 * @throws PortalException if a help relation with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.help.model.HelpRelation getHelpRelation(
			long relationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRelationLocalService.getHelpRelation(relationId);
	}

	/**
	 * Returns a range of all the help relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpRelationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help relations
	 * @param end the upper bound of the range of help relations (not inclusive)
	 * @return the range of help relations
	 */
	@Override
	public java.util.List<com.weprode.nero.help.model.HelpRelation>
		getHelpRelations(int start, int end) {

		return _helpRelationLocalService.getHelpRelations(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.help.model.HelpRelation>
		getHelpRelations(long itemId) {

		return _helpRelationLocalService.getHelpRelations(itemId);
	}

	/**
	 * Returns the number of help relations.
	 *
	 * @return the number of help relations
	 */
	@Override
	public int getHelpRelationsCount() {
		return _helpRelationLocalService.getHelpRelationsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _helpRelationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpRelationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRelationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean removeRelationsForHelpItem(long itemId) {
		return _helpRelationLocalService.removeRelationsForHelpItem(itemId);
	}

	/**
	 * Updates the help relation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpRelationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpRelation the help relation
	 * @return the help relation that was updated
	 */
	@Override
	public com.weprode.nero.help.model.HelpRelation updateHelpRelation(
		com.weprode.nero.help.model.HelpRelation helpRelation) {

		return _helpRelationLocalService.updateHelpRelation(helpRelation);
	}

	@Override
	public HelpRelationLocalService getWrappedService() {
		return _helpRelationLocalService;
	}

	@Override
	public void setWrappedService(
		HelpRelationLocalService helpRelationLocalService) {

		_helpRelationLocalService = helpRelationLocalService;
	}

	private HelpRelationLocalService _helpRelationLocalService;

}