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

package com.weprode.facile.access.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccessCategoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccessCategoryLocalService
 * @generated
 */
public class AccessCategoryLocalServiceWrapper
	implements AccessCategoryLocalService,
			   ServiceWrapper<AccessCategoryLocalService> {

	public AccessCategoryLocalServiceWrapper() {
		this(null);
	}

	public AccessCategoryLocalServiceWrapper(
		AccessCategoryLocalService accessCategoryLocalService) {

		_accessCategoryLocalService = accessCategoryLocalService;
	}

	/**
	 * Adds the access category to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessCategory the access category
	 * @return the access category that was added
	 */
	@Override
	public com.weprode.facile.access.model.AccessCategory addAccessCategory(
		com.weprode.facile.access.model.AccessCategory accessCategory) {

		return _accessCategoryLocalService.addAccessCategory(accessCategory);
	}

	@Override
	public com.weprode.facile.access.model.AccessCategory addCategory(
		long schoolId, String categoryName, int position) {

		return _accessCategoryLocalService.addCategory(
			schoolId, categoryName, position);
	}

	@Override
	public org.json.JSONObject convertToJson(
		com.weprode.facile.access.model.AccessCategory category) {

		return _accessCategoryLocalService.convertToJson(category);
	}

	/**
	 * Creates a new access category with the primary key. Does not add the access category to the database.
	 *
	 * @param categoryId the primary key for the new access category
	 * @return the new access category
	 */
	@Override
	public com.weprode.facile.access.model.AccessCategory createAccessCategory(
		long categoryId) {

		return _accessCategoryLocalService.createAccessCategory(categoryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessCategoryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the access category from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessCategory the access category
	 * @return the access category that was removed
	 */
	@Override
	public com.weprode.facile.access.model.AccessCategory deleteAccessCategory(
		com.weprode.facile.access.model.AccessCategory accessCategory) {

		return _accessCategoryLocalService.deleteAccessCategory(accessCategory);
	}

	/**
	 * Deletes the access category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category that was removed
	 * @throws PortalException if a access category with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.access.model.AccessCategory deleteAccessCategory(
			long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessCategoryLocalService.deleteAccessCategory(categoryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessCategoryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _accessCategoryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _accessCategoryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accessCategoryLocalService.dynamicQuery();
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

		return _accessCategoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.access.model.impl.AccessCategoryModelImpl</code>.
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

		return _accessCategoryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.access.model.impl.AccessCategoryModelImpl</code>.
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

		return _accessCategoryLocalService.dynamicQuery(
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

		return _accessCategoryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _accessCategoryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.facile.access.model.AccessCategory fetchAccessCategory(
		long categoryId) {

		return _accessCategoryLocalService.fetchAccessCategory(categoryId);
	}

	/**
	 * Returns a range of all the access categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.facile.access.model.impl.AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @return the range of access categories
	 */
	@Override
	public java.util.List<com.weprode.facile.access.model.AccessCategory>
		getAccessCategories(int start, int end) {

		return _accessCategoryLocalService.getAccessCategories(start, end);
	}

	/**
	 * Returns the number of access categories.
	 *
	 * @return the number of access categories
	 */
	@Override
	public int getAccessCategoriesCount() {
		return _accessCategoryLocalService.getAccessCategoriesCount();
	}

	/**
	 * Returns the access category with the primary key.
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category
	 * @throws PortalException if a access category with the primary key could not be found
	 */
	@Override
	public com.weprode.facile.access.model.AccessCategory getAccessCategory(
			long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessCategoryLocalService.getAccessCategory(categoryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _accessCategoryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _accessCategoryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accessCategoryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessCategoryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.weprode.facile.access.model.AccessCategory>
		getSchoolCategories(long schoolId) {

		return _accessCategoryLocalService.getSchoolCategories(schoolId);
	}

	@Override
	public void removeBySchoolId(long schoolId) {
		_accessCategoryLocalService.removeBySchoolId(schoolId);
	}

	@Override
	public void removeCategory(long categoryId, long schoolId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_accessCategoryLocalService.removeCategory(categoryId, schoolId);
	}

	/**
	 * Updates the access category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccessCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accessCategory the access category
	 * @return the access category that was updated
	 */
	@Override
	public com.weprode.facile.access.model.AccessCategory updateAccessCategory(
		com.weprode.facile.access.model.AccessCategory accessCategory) {

		return _accessCategoryLocalService.updateAccessCategory(accessCategory);
	}

	@Override
	public com.weprode.facile.access.model.AccessCategory updateCategory(
			long categoryId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accessCategoryLocalService.updateCategory(categoryId, name);
	}

	@Override
	public AccessCategoryLocalService getWrappedService() {
		return _accessCategoryLocalService;
	}

	@Override
	public void setWrappedService(
		AccessCategoryLocalService accessCategoryLocalService) {

		_accessCategoryLocalService = accessCategoryLocalService;
	}

	private AccessCategoryLocalService _accessCategoryLocalService;

}