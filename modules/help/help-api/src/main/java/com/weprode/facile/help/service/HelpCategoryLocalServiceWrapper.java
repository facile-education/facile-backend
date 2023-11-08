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

package com.weprode.facile.help.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HelpCategoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpCategoryLocalService
 * @generated
 */
public class HelpCategoryLocalServiceWrapper
	implements HelpCategoryLocalService,
			   ServiceWrapper<HelpCategoryLocalService> {

	public HelpCategoryLocalServiceWrapper() {
		this(null);
	}

	public HelpCategoryLocalServiceWrapper(
		HelpCategoryLocalService helpCategoryLocalService) {

		_helpCategoryLocalService = helpCategoryLocalService;
	}

	/**
	 * Adds the help category to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpCategory the help category
	 * @return the help category that was added
	 */
	@Override
	public com.weprode.facile.help.model.HelpCategory addHelpCategory(
		com.weprode.facile.help.model.HelpCategory helpCategory) {

		return _helpCategoryLocalService.addHelpCategory(helpCategory);
	}

	@Override
	public com.weprode.facile.help.model.HelpCategory createCategory(
		String categoryName, long serviceId) {

		return _helpCategoryLocalService.createCategory(
			categoryName, serviceId);
	}

	/**
	 * Creates a new help category with the primary key. Does not add the help category to the database.
	 *
	 * @param categoryId the primary key for the new help category
	 * @return the new help category
	 */
	@Override
	public com.weprode.facile.help.model.HelpCategory createHelpCategory(
		long categoryId) {

		return _helpCategoryLocalService.createHelpCategory(categoryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpCategoryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the help category from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpCategory the help category
	 * @return the help category that was removed
	 */
	@Override
	public com.weprode.facile.help.model.HelpCategory deleteHelpCategory(
		com.weprode.facile.help.model.HelpCategory helpCategory) {

		return _helpCategoryLocalService.deleteHelpCategory(helpCategory);
	}

	/**
	 * Deletes the help category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category that was removed
	 * @throws PortalException if a help category with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.help.model.HelpCategory deleteHelpCategory(
			long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpCategoryLocalService.deleteHelpCategory(categoryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpCategoryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _helpCategoryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _helpCategoryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helpCategoryLocalService.dynamicQuery();
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

		return _helpCategoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.help.model.impl.HelpCategoryModelImpl</code>.
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

		return _helpCategoryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.help.model.impl.HelpCategoryModelImpl</code>.
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

		return _helpCategoryLocalService.dynamicQuery(
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

		return _helpCategoryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _helpCategoryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.help.model.HelpCategory fetchHelpCategory(
		long categoryId) {

		return _helpCategoryLocalService.fetchHelpCategory(categoryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _helpCategoryLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.weprode.facile.help.model.HelpCategory>
		getAllCategories() {

		return _helpCategoryLocalService.getAllCategories();
	}

	/**
	 * Returns a range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.help.model.impl.HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @return the range of help categories
	 */
	@Override
	public java.util.List<com.weprode.facile.help.model.HelpCategory>
		getHelpCategories(int start, int end) {

		return _helpCategoryLocalService.getHelpCategories(start, end);
	}

	/**
	 * Returns the number of help categories.
	 *
	 * @return the number of help categories
	 */
	@Override
	public int getHelpCategoriesCount() {
		return _helpCategoryLocalService.getHelpCategoriesCount();
	}

	/**
	 * Returns the help category with the primary key.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category
	 * @throws PortalException if a help category with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.help.model.HelpCategory getHelpCategory(
			long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpCategoryLocalService.getHelpCategory(categoryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _helpCategoryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpCategoryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpCategoryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void removeCategory(long categoryId) throws Exception {
		_helpCategoryLocalService.removeCategory(categoryId);
	}

	/**
	 * Updates the help category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpCategory the help category
	 * @return the help category that was updated
	 */
	@Override
	public com.weprode.facile.help.model.HelpCategory updateHelpCategory(
		com.weprode.facile.help.model.HelpCategory helpCategory) {

		return _helpCategoryLocalService.updateHelpCategory(helpCategory);
	}

	@Override
	public HelpCategoryLocalService getWrappedService() {
		return _helpCategoryLocalService;
	}

	@Override
	public void setWrappedService(
		HelpCategoryLocalService helpCategoryLocalService) {

		_helpCategoryLocalService = helpCategoryLocalService;
	}

	private HelpCategoryLocalService _helpCategoryLocalService;

}