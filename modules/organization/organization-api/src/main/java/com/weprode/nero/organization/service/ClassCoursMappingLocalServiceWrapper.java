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

package com.weprode.nero.organization.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ClassCoursMappingLocalService}.
 *
 * @author Marc Salvat
 * @see ClassCoursMappingLocalService
 * @generated
 */
public class ClassCoursMappingLocalServiceWrapper
	implements ClassCoursMappingLocalService,
			   ServiceWrapper<ClassCoursMappingLocalService> {

	public ClassCoursMappingLocalServiceWrapper(
		ClassCoursMappingLocalService classCoursMappingLocalService) {

		_classCoursMappingLocalService = classCoursMappingLocalService;
	}

	/**
	 * Adds the class cours mapping to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ClassCoursMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param classCoursMapping the class cours mapping
	 * @return the class cours mapping that was added
	 */
	@Override
	public com.weprode.nero.organization.model.ClassCoursMapping
		addClassCoursMapping(
			com.weprode.nero.organization.model.ClassCoursMapping
				classCoursMapping) {

		return _classCoursMappingLocalService.addClassCoursMapping(
			classCoursMapping);
	}

	@Override
	public com.weprode.nero.organization.model.ClassCoursMapping
		addClassCoursMapping(long classOrgId, long coursOrgId) {

		return _classCoursMappingLocalService.addClassCoursMapping(
			classOrgId, coursOrgId);
	}

	/**
	 * Creates a new class cours mapping with the primary key. Does not add the class cours mapping to the database.
	 *
	 * @param mappingId the primary key for the new class cours mapping
	 * @return the new class cours mapping
	 */
	@Override
	public com.weprode.nero.organization.model.ClassCoursMapping
		createClassCoursMapping(long mappingId) {

		return _classCoursMappingLocalService.createClassCoursMapping(
			mappingId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _classCoursMappingLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public void deleteByClassOrgId(long classOrgId) {
		_classCoursMappingLocalService.deleteByClassOrgId(classOrgId);
	}

	@Override
	public void deleteByCoursOrgId(long coursOrgId) {
		_classCoursMappingLocalService.deleteByCoursOrgId(coursOrgId);
	}

	/**
	 * Deletes the class cours mapping from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ClassCoursMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param classCoursMapping the class cours mapping
	 * @return the class cours mapping that was removed
	 */
	@Override
	public com.weprode.nero.organization.model.ClassCoursMapping
		deleteClassCoursMapping(
			com.weprode.nero.organization.model.ClassCoursMapping
				classCoursMapping) {

		return _classCoursMappingLocalService.deleteClassCoursMapping(
			classCoursMapping);
	}

	/**
	 * Deletes the class cours mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ClassCoursMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping that was removed
	 * @throws PortalException if a class cours mapping with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.organization.model.ClassCoursMapping
			deleteClassCoursMapping(long mappingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _classCoursMappingLocalService.deleteClassCoursMapping(
			mappingId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _classCoursMappingLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _classCoursMappingLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _classCoursMappingLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _classCoursMappingLocalService.dynamicQuery();
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

		return _classCoursMappingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.organization.model.impl.ClassCoursMappingModelImpl</code>.
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

		return _classCoursMappingLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.organization.model.impl.ClassCoursMappingModelImpl</code>.
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

		return _classCoursMappingLocalService.dynamicQuery(
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

		return _classCoursMappingLocalService.dynamicQueryCount(dynamicQuery);
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

		return _classCoursMappingLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.organization.model.ClassCoursMapping
		fetchClassCoursMapping(long mappingId) {

		return _classCoursMappingLocalService.fetchClassCoursMapping(mappingId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _classCoursMappingLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<Long> getClassCours(long classOrgId) {
		return _classCoursMappingLocalService.getClassCours(classOrgId);
	}

	/**
	 * Returns the class cours mapping with the primary key.
	 *
	 * @param mappingId the primary key of the class cours mapping
	 * @return the class cours mapping
	 * @throws PortalException if a class cours mapping with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.organization.model.ClassCoursMapping
			getClassCoursMapping(long mappingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _classCoursMappingLocalService.getClassCoursMapping(mappingId);
	}

	/**
	 * Returns a range of all the class cours mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.organization.model.impl.ClassCoursMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of class cours mappings
	 * @param end the upper bound of the range of class cours mappings (not inclusive)
	 * @return the range of class cours mappings
	 */
	@Override
	public java.util.List<com.weprode.nero.organization.model.ClassCoursMapping>
		getClassCoursMappings(int start, int end) {

		return _classCoursMappingLocalService.getClassCoursMappings(start, end);
	}

	/**
	 * Returns the number of class cours mappings.
	 *
	 * @return the number of class cours mappings
	 */
	@Override
	public int getClassCoursMappingsCount() {
		return _classCoursMappingLocalService.getClassCoursMappingsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _classCoursMappingLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _classCoursMappingLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _classCoursMappingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the class cours mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ClassCoursMappingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param classCoursMapping the class cours mapping
	 * @return the class cours mapping that was updated
	 */
	@Override
	public com.weprode.nero.organization.model.ClassCoursMapping
		updateClassCoursMapping(
			com.weprode.nero.organization.model.ClassCoursMapping
				classCoursMapping) {

		return _classCoursMappingLocalService.updateClassCoursMapping(
			classCoursMapping);
	}

	@Override
	public void updateClassCoursMapping(
		long coursOrgId, java.util.List<Long> classOrgIds) {

		_classCoursMappingLocalService.updateClassCoursMapping(
			coursOrgId, classOrgIds);
	}

	@Override
	public ClassCoursMappingLocalService getWrappedService() {
		return _classCoursMappingLocalService;
	}

	@Override
	public void setWrappedService(
		ClassCoursMappingLocalService classCoursMappingLocalService) {

		_classCoursMappingLocalService = classCoursMappingLocalService;
	}

	private ClassCoursMappingLocalService _classCoursMappingLocalService;

}