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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.group.model.CommunityInfos;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CommunityInfos. This utility wraps
 * <code>com.weprode.nero.group.service.impl.CommunityInfosLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CommunityInfosLocalService
 * @generated
 */
public class CommunityInfosLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.group.service.impl.CommunityInfosLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static CommunityInfos addCommunityInfos(
		CommunityInfos communityInfos) {

		return getService().addCommunityInfos(communityInfos);
	}

	public static CommunityInfos createCommunity(long groupId, long userId)
		throws SystemException {

		return getService().createCommunity(groupId, userId);
	}

	/**
	 * Creates a new community infos with the primary key. Does not add the community infos to the database.
	 *
	 * @param communityInfosId the primary key for the new community infos
	 * @return the new community infos
	 */
	public static CommunityInfos createCommunityInfos(long communityInfosId) {
		return getService().createCommunityInfos(communityInfosId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static CommunityInfos deleteCommunityInfos(
		CommunityInfos communityInfos) {

		return getService().deleteCommunityInfos(communityInfos);
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
	public static CommunityInfos deleteCommunityInfos(long communityInfosId)
		throws PortalException {

		return getService().deleteCommunityInfos(communityInfosId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static CommunityInfos fetchCommunityInfos(long communityInfosId) {
		return getService().fetchCommunityInfos(communityInfosId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the community infos with the primary key.
	 *
	 * @param communityInfosId the primary key of the community infos
	 * @return the community infos
	 * @throws PortalException if a community infos with the primary key could not be found
	 */
	public static CommunityInfos getCommunityInfos(long communityInfosId)
		throws PortalException {

		return getService().getCommunityInfos(communityInfosId);
	}

	public static CommunityInfos getCommunityInfosByGroupId(long groupId)
		throws com.weprode.nero.group.exception.NoSuchCommunityInfosException,
			   SystemException {

		return getService().getCommunityInfosByGroupId(groupId);
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
	public static List<CommunityInfos> getCommunityInfoses(int start, int end) {
		return getService().getCommunityInfoses(start, end);
	}

	/**
	 * Returns the number of community infoses.
	 *
	 * @return the number of community infoses
	 */
	public static int getCommunityInfosesCount() {
		return getService().getCommunityInfosesCount();
	}

	/**
	 * Get the communities which have expired from more than 3 months
	 */
	public static List<CommunityInfos> getExpireCommunityToRemove() {
		return getService().getExpireCommunityToRemove();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static List<com.liferay.portal.kernel.model.Group>
		getSchoolCommunities(
			long schoolId, boolean pedagogicalOnly, boolean activeOnly) {

		return getService().getSchoolCommunities(
			schoolId, pedagogicalOnly, activeOnly);
	}

	public static List<com.liferay.portal.kernel.model.Group>
		getUserCommunities(
			long userId, boolean pedagogicalOnly, boolean activeOnly) {

		return getService().getUserCommunities(
			userId, pedagogicalOnly, activeOnly);
	}

	public static List<Long> getUserCommunitiesIds(
		long userId, boolean pedagogicalOnly, boolean activeOnly) {

		return getService().getUserCommunitiesIds(
			userId, pedagogicalOnly, activeOnly);
	}

	public static List<Long> getUserGroupIds(long userId) {
		return getService().getUserGroupIds(userId);
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
	public static CommunityInfos updateCommunityInfos(
		CommunityInfos communityInfos) {

		return getService().updateCommunityInfos(communityInfos);
	}

	public static CommunityInfosLocalService getService() {
		return _service;
	}

	private static volatile CommunityInfosLocalService _service;

}