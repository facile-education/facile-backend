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
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.application.model.BroadcastRule;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for BroadcastRule. This utility wraps
 * <code>com.weprode.nero.application.service.impl.BroadcastRuleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastRuleLocalService
 * @generated
 */
public class BroadcastRuleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.application.service.impl.BroadcastRuleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the broadcast rule to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastRuleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcastRule the broadcast rule
	 * @return the broadcast rule that was added
	 */
	public static BroadcastRule addBroadcastRule(BroadcastRule broadcastRule) {
		return getService().addBroadcastRule(broadcastRule);
	}

	public static BroadcastRule addRule(
		long applicationId, long schoolId, long roleId, long orgId,
		long groupId) {

		return getService().addRule(
			applicationId, schoolId, roleId, orgId, groupId);
	}

	public static com.liferay.portal.kernel.json.JSONObject convertRule(
		BroadcastRule rule) {

		return getService().convertRule(rule);
	}

	/**
	 * Creates a new broadcast rule with the primary key. Does not add the broadcast rule to the database.
	 *
	 * @param broadcastRuleId the primary key for the new broadcast rule
	 * @return the new broadcast rule
	 */
	public static BroadcastRule createBroadcastRule(long broadcastRuleId) {
		return getService().createBroadcastRule(broadcastRuleId);
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
	 * Deletes the broadcast rule from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastRuleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcastRule the broadcast rule
	 * @return the broadcast rule that was removed
	 */
	public static BroadcastRule deleteBroadcastRule(
		BroadcastRule broadcastRule) {

		return getService().deleteBroadcastRule(broadcastRule);
	}

	/**
	 * Deletes the broadcast rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastRuleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule that was removed
	 * @throws PortalException if a broadcast rule with the primary key could not be found
	 */
	public static BroadcastRule deleteBroadcastRule(long broadcastRuleId)
		throws PortalException {

		return getService().deleteBroadcastRule(broadcastRuleId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static boolean deleteRule(long applicationBroadcastRuleId) {
		return getService().deleteRule(applicationBroadcastRuleId);
	}

	public static boolean deleteSchoolRules(long applicationId, long schoolId) {
		return getService().deleteSchoolRules(applicationId, schoolId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.BroadcastRuleModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.BroadcastRuleModelImpl</code>.
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

	public static BroadcastRule fetchBroadcastRule(long broadcastRuleId) {
		return getService().fetchBroadcastRule(broadcastRuleId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the broadcast rule with the primary key.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule
	 * @throws PortalException if a broadcast rule with the primary key could not be found
	 */
	public static BroadcastRule getBroadcastRule(long broadcastRuleId)
		throws PortalException {

		return getService().getBroadcastRule(broadcastRuleId);
	}

	/**
	 * Returns a range of all the broadcast rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.weprode.nero.application.model.impl.BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @return the range of broadcast rules
	 */
	public static List<BroadcastRule> getBroadcastRules(int start, int end) {
		return getService().getBroadcastRules(start, end);
	}

	/**
	 * Returns the number of broadcast rules.
	 *
	 * @return the number of broadcast rules
	 */
	public static int getBroadcastRulesCount() {
		return getService().getBroadcastRulesCount();
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

	public static List<BroadcastRule> getSchoolRules(
		long applicationId, long schoolId) {

		return getService().getSchoolRules(applicationId, schoolId);
	}

	public static boolean removeRule(long applicationBroadcastRuleId) {
		return getService().removeRule(applicationBroadcastRuleId);
	}

	/**
	 * Updates the broadcast rule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BroadcastRuleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param broadcastRule the broadcast rule
	 * @return the broadcast rule that was updated
	 */
	public static BroadcastRule updateBroadcastRule(
		BroadcastRule broadcastRule) {

		return getService().updateBroadcastRule(broadcastRule);
	}

	public static BroadcastRuleLocalService getService() {
		return _service;
	}

	private static volatile BroadcastRuleLocalService _service;

}