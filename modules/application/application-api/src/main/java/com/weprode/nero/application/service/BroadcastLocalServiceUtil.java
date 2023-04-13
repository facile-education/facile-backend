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

package com.weprode.nero.application.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.application.model.Broadcast;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Broadcast. This utility wraps
 * <code>com.weprode.nero.application.service.impl.BroadcastLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastLocalService
 * @generated
 */
public class BroadcastLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.application.service.impl.BroadcastLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the broadcast to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcast the broadcast
	 * @return the broadcast that was added
	 */
	public static Broadcast addBroadcast(Broadcast broadcast) {
		return getService().addBroadcast(broadcast);
	}

	/**
	 * Creates a new broadcast with the primary key. Does not add the broadcast to the database.
	 *
	 * @param broadcastId the primary key for the new broadcast
	 * @return the new broadcast
	 */
	public static Broadcast createBroadcast(long broadcastId) {
		return getService().createBroadcast(broadcastId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static Broadcast createSchoolBroadcast(
			long applicationId, long schoolId)
		throws SystemException {

		return getService().createSchoolBroadcast(applicationId, schoolId);
	}

	/**
	 * Deletes the broadcast from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcast the broadcast
	 * @return the broadcast that was removed
	 */
	public static Broadcast deleteBroadcast(Broadcast broadcast) {
		return getService().deleteBroadcast(broadcast);
	}

	/**
	 * Deletes the broadcast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast that was removed
	 * @throws PortalException if a broadcast with the primary key could not be found
	 */
	public static Broadcast deleteBroadcast(long broadcastId)
		throws PortalException {

		return getService().deleteBroadcast(broadcastId);
	}

	public static boolean deleteBroadcastForSchool(
		long applicationId, long schoolId) {

		return getService().deleteBroadcastForSchool(applicationId, schoolId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.BroadcastModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.BroadcastModelImpl</code>.
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

	public static Broadcast fetchBroadcast(long broadcastId) {
		return getService().fetchBroadcast(broadcastId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the broadcast with the primary key.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast
	 * @throws PortalException if a broadcast with the primary key could not be found
	 */
	public static Broadcast getBroadcast(long broadcastId)
		throws PortalException {

		return getService().getBroadcast(broadcastId);
	}

	public static List<Long> getBroadcastedSchools(long applicationId) {
		return getService().getBroadcastedSchools(applicationId);
	}

	/**
	 * Returns a range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @return the range of broadcasts
	 */
	public static List<Broadcast> getBroadcasts(int start, int end) {
		return getService().getBroadcasts(start, end);
	}

	/**
	 * Returns the number of broadcasts.
	 *
	 * @return the number of broadcasts
	 */
	public static int getBroadcastsCount() {
		return getService().getBroadcastsCount();
	}

	public static Broadcast getByApplicationIdEtabId(
			long applicationId, long schoolId)
		throws SystemException {

		return getService().getByApplicationIdEtabId(applicationId, schoolId);
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

	public static boolean isApplicationBroadcastedToUser(
		long userId, String applicationKey) {

		return getService().isApplicationBroadcastedToUser(
			userId, applicationKey);
	}

	public static boolean removeBroadcast(Long applicationId)
		throws SystemException {

		return getService().removeBroadcast(applicationId);
	}

	/**
	 * Updates the broadcast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcast the broadcast
	 * @return the broadcast that was updated
	 */
	public static Broadcast updateBroadcast(Broadcast broadcast) {
		return getService().updateBroadcast(broadcast);
	}

	public static Broadcast updateBroadcast(
			long applicationId, long schoolId, boolean isBroadcasted,
			String applicationUrl)
		throws SystemException {

		return getService().updateBroadcast(
			applicationId, schoolId, isBroadcasted, applicationUrl);
	}

	public static BroadcastLocalService getService() {
		return _service;
	}

	private static volatile BroadcastLocalService _service;

}