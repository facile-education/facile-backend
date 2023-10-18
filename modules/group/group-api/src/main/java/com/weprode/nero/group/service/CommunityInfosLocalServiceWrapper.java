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

package com.weprode.nero.group.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommunityInfosLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CommunityInfosLocalService
 * @generated
 */
public class CommunityInfosLocalServiceWrapper
	implements CommunityInfosLocalService,
			   ServiceWrapper<CommunityInfosLocalService> {

	public CommunityInfosLocalServiceWrapper() {
		this(null);
	}

	public CommunityInfosLocalServiceWrapper(
		CommunityInfosLocalService communityInfosLocalService) {

		_communityInfosLocalService = communityInfosLocalService;
	}

	/**
	 * Adds the community infos to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommunityInfosLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param communityInfos the community infos
	 * @return the community infos that was added
	 */
	@Override
	public com.weprode.nero.group.model.CommunityInfos addCommunityInfos(
		com.weprode.nero.group.model.CommunityInfos communityInfos) {

		return _communityInfosLocalService.addCommunityInfos(communityInfos);
	}

	@Override
	public com.weprode.nero.group.model.CommunityInfos createCommunity(
			long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _communityInfosLocalService.createCommunity(groupId, userId);
	}

	/**
	 * Creates a new community infos with the primary key. Does not add the community infos to the database.
	 *
	 * @param communityInfosId the primary key for the new community infos
	 * @return the new community infos
	 */
	@Override
	public com.weprode.nero.group.model.CommunityInfos createCommunityInfos(
		long communityInfosId) {

		return _communityInfosLocalService.createCommunityInfos(
			communityInfosId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _communityInfosLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the community infos from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommunityInfosLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param communityInfos the community infos
	 * @return the community infos that was removed
	 */
	@Override
	public com.weprode.nero.group.model.CommunityInfos deleteCommunityInfos(
		com.weprode.nero.group.model.CommunityInfos communityInfos) {

		return _communityInfosLocalService.deleteCommunityInfos(communityInfos);
	}

	/**
	 * Deletes the community infos with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommunityInfosLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos that was removed
	 * @throws PortalException if a community infos with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.group.model.CommunityInfos deleteCommunityInfos(
			long communityInfosId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _communityInfosLocalService.deleteCommunityInfos(
			communityInfosId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _communityInfosLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _communityInfosLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _communityInfosLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _communityInfosLocalService.dynamicQuery();
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

		return _communityInfosLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.group.model.impl.CommunityInfosModelImpl</code>.
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

		return _communityInfosLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.group.model.impl.CommunityInfosModelImpl</code>.
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

		return _communityInfosLocalService.dynamicQuery(
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

		return _communityInfosLocalService.dynamicQueryCount(dynamicQuery);
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

		return _communityInfosLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.group.model.CommunityInfos fetchCommunityInfos(
		long communityInfosId) {

		return _communityInfosLocalService.fetchCommunityInfos(
			communityInfosId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _communityInfosLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the community infos with the primary key.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos
	 * @throws PortalException if a community infos with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.group.model.CommunityInfos getCommunityInfos(
			long communityInfosId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _communityInfosLocalService.getCommunityInfos(communityInfosId);
	}

	@Override
	public com.weprode.nero.group.model.CommunityInfos
			getCommunityInfosByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			   com.weprode.nero.group.exception.NoSuchCommunityInfosException {

		return _communityInfosLocalService.getCommunityInfosByGroupId(groupId);
	}

	/**
	 * Returns a range of all the community infoses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.group.model.impl.CommunityInfosModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of community infoses
	 * @param end the upper bound of the range of community infoses (not inclusive)
	 * @return the range of community infoses
	 */
	@Override
	public java.util.List<com.weprode.nero.group.model.CommunityInfos>
		getCommunityInfoses(int start, int end) {

		return _communityInfosLocalService.getCommunityInfoses(start, end);
	}

	/**
	 * Returns the number of community infoses.
	 *
	 * @return the number of community infoses
	 */
	@Override
	public int getCommunityInfosesCount() {
		return _communityInfosLocalService.getCommunityInfosesCount();
	}

	/**
	 * Get the communities which have expired from more than 3 months
	 */
	@Override
	public java.util.List<com.weprode.nero.group.model.CommunityInfos>
		getExpireCommunityToRemove() {

		return _communityInfosLocalService.getExpireCommunityToRemove();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _communityInfosLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _communityInfosLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _communityInfosLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group>
		getSchoolCommunities(
			long schoolId, boolean pedagogicalOnly, boolean activeOnly) {

		return _communityInfosLocalService.getSchoolCommunities(
			schoolId, pedagogicalOnly, activeOnly);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group>
		getUserCommunities(
			long userId, boolean pedagogicalOnly, boolean activeOnly) {

		return _communityInfosLocalService.getUserCommunities(
			userId, pedagogicalOnly, activeOnly);
	}

	@Override
	public java.util.List<Long> getUserCommunitiesIds(
		long userId, boolean pedagogicalOnly, boolean activeOnly) {

		return _communityInfosLocalService.getUserCommunitiesIds(
			userId, pedagogicalOnly, activeOnly);
	}

	/**
	 * Updates the community infos in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommunityInfosLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param communityInfos the community infos
	 * @return the community infos that was updated
	 */
	@Override
	public com.weprode.nero.group.model.CommunityInfos updateCommunityInfos(
		com.weprode.nero.group.model.CommunityInfos communityInfos) {

		return _communityInfosLocalService.updateCommunityInfos(communityInfos);
	}

	@Override
	public CommunityInfosLocalService getWrappedService() {
		return _communityInfosLocalService;
	}

	@Override
	public void setWrappedService(
		CommunityInfosLocalService communityInfosLocalService) {

		_communityInfosLocalService = communityInfosLocalService;
	}

	private CommunityInfosLocalService _communityInfosLocalService;

}