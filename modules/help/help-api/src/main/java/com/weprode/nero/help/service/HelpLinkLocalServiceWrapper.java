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
 * Provides a wrapper for {@link HelpLinkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpLinkLocalService
 * @generated
 */
public class HelpLinkLocalServiceWrapper
	implements HelpLinkLocalService, ServiceWrapper<HelpLinkLocalService> {

	public HelpLinkLocalServiceWrapper(
		HelpLinkLocalService helpLinkLocalService) {

		_helpLinkLocalService = helpLinkLocalService;
	}

	/**
	 * Adds the help link to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpLink the help link
	 * @return the help link that was added
	 */
	@Override
	public com.weprode.nero.help.model.HelpLink addHelpLink(
		com.weprode.nero.help.model.HelpLink helpLink) {

		return _helpLinkLocalService.addHelpLink(helpLink);
	}

	@Override
	public com.weprode.nero.help.model.HelpLink addHelpLink(
		long itemId, String linkUrl, String linkName) {

		return _helpLinkLocalService.addHelpLink(itemId, linkUrl, linkName);
	}

	/**
	 * Creates a new help link with the primary key. Does not add the help link to the database.
	 *
	 * @param linkId the primary key for the new help link
	 * @return the new help link
	 */
	@Override
	public com.weprode.nero.help.model.HelpLink createHelpLink(long linkId) {
		return _helpLinkLocalService.createHelpLink(linkId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpLinkLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the help link from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpLink the help link
	 * @return the help link that was removed
	 */
	@Override
	public com.weprode.nero.help.model.HelpLink deleteHelpLink(
		com.weprode.nero.help.model.HelpLink helpLink) {

		return _helpLinkLocalService.deleteHelpLink(helpLink);
	}

	/**
	 * Deletes the help link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link that was removed
	 * @throws PortalException if a help link with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.help.model.HelpLink deleteHelpLink(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpLinkLocalService.deleteHelpLink(linkId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpLinkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _helpLinkLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _helpLinkLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helpLinkLocalService.dynamicQuery();
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

		return _helpLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpLinkModelImpl</code>.
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

		return _helpLinkLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpLinkModelImpl</code>.
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

		return _helpLinkLocalService.dynamicQuery(
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

		return _helpLinkLocalService.dynamicQueryCount(dynamicQuery);
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

		return _helpLinkLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.help.model.HelpLink fetchHelpLink(long linkId) {
		return _helpLinkLocalService.fetchHelpLink(linkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _helpLinkLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the help link with the primary key.
	 *
	 * @param linkId the primary key of the help link
	 * @return the help link
	 * @throws PortalException if a help link with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.help.model.HelpLink getHelpLink(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpLinkLocalService.getHelpLink(linkId);
	}

	/**
	 * Returns a range of all the help links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help links
	 * @param end the upper bound of the range of help links (not inclusive)
	 * @return the range of help links
	 */
	@Override
	public java.util.List<com.weprode.nero.help.model.HelpLink> getHelpLinks(
		int start, int end) {

		return _helpLinkLocalService.getHelpLinks(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.help.model.HelpLink> getHelpLinks(
		long itemId) {

		return _helpLinkLocalService.getHelpLinks(itemId);
	}

	/**
	 * Returns the number of help links.
	 *
	 * @return the number of help links
	 */
	@Override
	public int getHelpLinksCount() {
		return _helpLinkLocalService.getHelpLinksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _helpLinkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpLinkLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean removeLinksForHelpItem(long itemId) {
		return _helpLinkLocalService.removeLinksForHelpItem(itemId);
	}

	/**
	 * Updates the help link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpLink the help link
	 * @return the help link that was updated
	 */
	@Override
	public com.weprode.nero.help.model.HelpLink updateHelpLink(
		com.weprode.nero.help.model.HelpLink helpLink) {

		return _helpLinkLocalService.updateHelpLink(helpLink);
	}

	@Override
	public HelpLinkLocalService getWrappedService() {
		return _helpLinkLocalService;
	}

	@Override
	public void setWrappedService(HelpLinkLocalService helpLinkLocalService) {
		_helpLinkLocalService = helpLinkLocalService;
	}

	private HelpLinkLocalService _helpLinkLocalService;

}