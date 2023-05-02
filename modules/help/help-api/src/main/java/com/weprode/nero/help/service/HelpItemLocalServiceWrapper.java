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
 * Provides a wrapper for {@link HelpItemLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpItemLocalService
 * @generated
 */
public class HelpItemLocalServiceWrapper
	implements HelpItemLocalService, ServiceWrapper<HelpItemLocalService> {

	public HelpItemLocalServiceWrapper(
		HelpItemLocalService helpItemLocalService) {

		_helpItemLocalService = helpItemLocalService;
	}

	/**
	 * Adds the help item to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItem the help item
	 * @return the help item that was added
	 */
	@Override
	public com.weprode.nero.help.model.HelpItem addHelpItem(
		com.weprode.nero.help.model.HelpItem helpItem) {

		return _helpItemLocalService.addHelpItem(helpItem);
	}

	/**
	 * Creates a new help item with the primary key. Does not add the help item to the database.
	 *
	 * @param itemId the primary key for the new help item
	 * @return the new help item
	 */
	@Override
	public com.weprode.nero.help.model.HelpItem createHelpItem(long itemId) {
		return _helpItemLocalService.createHelpItem(itemId);
	}

	@Override
	public com.weprode.nero.help.model.HelpItem createItem(
		long categoryId, String itemName, String videoUrl,
		String videoDescription, String manual, String language,
		boolean isManagement) {

		return _helpItemLocalService.createItem(
			categoryId, itemName, videoUrl, videoDescription, manual, language,
			isManagement);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the help item from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItem the help item
	 * @return the help item that was removed
	 */
	@Override
	public com.weprode.nero.help.model.HelpItem deleteHelpItem(
		com.weprode.nero.help.model.HelpItem helpItem) {

		return _helpItemLocalService.deleteHelpItem(helpItem);
	}

	/**
	 * Deletes the help item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item that was removed
	 * @throws PortalException if a help item with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.help.model.HelpItem deleteHelpItem(long itemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemLocalService.deleteHelpItem(itemId);
	}

	@Override
	public void deleteItem(long itemId) throws Exception {
		_helpItemLocalService.deleteItem(itemId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _helpItemLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _helpItemLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helpItemLocalService.dynamicQuery();
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

		return _helpItemLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpItemModelImpl</code>.
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

		return _helpItemLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpItemModelImpl</code>.
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

		return _helpItemLocalService.dynamicQuery(
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

		return _helpItemLocalService.dynamicQueryCount(dynamicQuery);
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

		return _helpItemLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.help.model.HelpItem fetchHelpItem(long itemId) {
		return _helpItemLocalService.fetchHelpItem(itemId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _helpItemLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the help item with the primary key.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item
	 * @throws PortalException if a help item with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.help.model.HelpItem getHelpItem(long itemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemLocalService.getHelpItem(itemId);
	}

	/**
	 * Returns a range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.help.model.impl.HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @return the range of help items
	 */
	@Override
	public java.util.List<com.weprode.nero.help.model.HelpItem> getHelpItems(
		int start, int end) {

		return _helpItemLocalService.getHelpItems(start, end);
	}

	@Override
	public java.util.List<com.weprode.nero.help.model.HelpItem> getHelpItems(
		long categoryId, String searchTerms) {

		return _helpItemLocalService.getHelpItems(categoryId, searchTerms);
	}

	/**
	 * Returns the number of help items.
	 *
	 * @return the number of help items
	 */
	@Override
	public int getHelpItemsCount() {
		return _helpItemLocalService.getHelpItemsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _helpItemLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpItemLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpItemLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean removeItem(long helpItemId) {
		return _helpItemLocalService.removeItem(helpItemId);
	}

	/**
	 * Updates the help item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpItem the help item
	 * @return the help item that was updated
	 */
	@Override
	public com.weprode.nero.help.model.HelpItem updateHelpItem(
		com.weprode.nero.help.model.HelpItem helpItem) {

		return _helpItemLocalService.updateHelpItem(helpItem);
	}

	@Override
	public HelpItemLocalService getWrappedService() {
		return _helpItemLocalService;
	}

	@Override
	public void setWrappedService(HelpItemLocalService helpItemLocalService) {
		_helpItemLocalService = helpItemLocalService;
	}

	private HelpItemLocalService _helpItemLocalService;

}