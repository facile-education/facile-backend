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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BroadcastRuleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastRuleLocalService
 * @generated
 */
public class BroadcastRuleLocalServiceWrapper
	implements BroadcastRuleLocalService,
			   ServiceWrapper<BroadcastRuleLocalService> {

	public BroadcastRuleLocalServiceWrapper() {
		this(null);
	}

	public BroadcastRuleLocalServiceWrapper(
		BroadcastRuleLocalService broadcastRuleLocalService) {

		_broadcastRuleLocalService = broadcastRuleLocalService;
	}

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
	@Override
	public com.weprode.nero.application.model.BroadcastRule addBroadcastRule(
		com.weprode.nero.application.model.BroadcastRule broadcastRule) {

		return _broadcastRuleLocalService.addBroadcastRule(broadcastRule);
	}

	@Override
	public com.weprode.nero.application.model.BroadcastRule addRule(
		long applicationId, long schoolId, long roleId, long orgId,
		long groupId) {

		return _broadcastRuleLocalService.addRule(
			applicationId, schoolId, roleId, orgId, groupId);
	}

	@Override
	public org.json.JSONObject convertRule(
		com.weprode.nero.application.model.BroadcastRule rule) {

		return _broadcastRuleLocalService.convertRule(rule);
	}

	/**
	 * Creates a new broadcast rule with the primary key. Does not add the broadcast rule to the database.
	 *
	 * @param broadcastRuleId the primary key for the new broadcast rule
	 * @return the new broadcast rule
	 */
	@Override
	public com.weprode.nero.application.model.BroadcastRule createBroadcastRule(
		long broadcastRuleId) {

		return _broadcastRuleLocalService.createBroadcastRule(broadcastRuleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastRuleLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public com.weprode.nero.application.model.BroadcastRule deleteBroadcastRule(
		com.weprode.nero.application.model.BroadcastRule broadcastRule) {

		return _broadcastRuleLocalService.deleteBroadcastRule(broadcastRule);
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
	@Override
	public com.weprode.nero.application.model.BroadcastRule deleteBroadcastRule(
			long broadcastRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastRuleLocalService.deleteBroadcastRule(broadcastRuleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastRuleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public boolean deleteRule(long applicationBroadcastRuleId) {
		return _broadcastRuleLocalService.deleteRule(
			applicationBroadcastRuleId);
	}

	@Override
	public boolean deleteSchoolRules(long applicationId, long schoolId) {
		return _broadcastRuleLocalService.deleteSchoolRules(
			applicationId, schoolId);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _broadcastRuleLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _broadcastRuleLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _broadcastRuleLocalService.dynamicQuery();
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

		return _broadcastRuleLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _broadcastRuleLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _broadcastRuleLocalService.dynamicQuery(
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

		return _broadcastRuleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _broadcastRuleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.weprode.nero.application.model.BroadcastRule fetchBroadcastRule(
		long broadcastRuleId) {

		return _broadcastRuleLocalService.fetchBroadcastRule(broadcastRuleId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _broadcastRuleLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the broadcast rule with the primary key.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule
	 * @throws PortalException if a broadcast rule with the primary key could not be found
	 */
	@Override
	public com.weprode.nero.application.model.BroadcastRule getBroadcastRule(
			long broadcastRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastRuleLocalService.getBroadcastRule(broadcastRuleId);
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
	@Override
	public java.util.List<com.weprode.nero.application.model.BroadcastRule>
		getBroadcastRules(int start, int end) {

		return _broadcastRuleLocalService.getBroadcastRules(start, end);
	}

	/**
	 * Returns the number of broadcast rules.
	 *
	 * @return the number of broadcast rules
	 */
	@Override
	public int getBroadcastRulesCount() {
		return _broadcastRuleLocalService.getBroadcastRulesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _broadcastRuleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _broadcastRuleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _broadcastRuleLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.weprode.nero.application.model.BroadcastRule>
		getSchoolRules(long applicationId, long schoolId) {

		return _broadcastRuleLocalService.getSchoolRules(
			applicationId, schoolId);
	}

	@Override
	public boolean removeRule(long applicationBroadcastRuleId) {
		return _broadcastRuleLocalService.removeRule(
			applicationBroadcastRuleId);
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
	@Override
	public com.weprode.nero.application.model.BroadcastRule updateBroadcastRule(
		com.weprode.nero.application.model.BroadcastRule broadcastRule) {

		return _broadcastRuleLocalService.updateBroadcastRule(broadcastRule);
	}

	@Override
	public BroadcastRuleLocalService getWrappedService() {
		return _broadcastRuleLocalService;
	}

	@Override
	public void setWrappedService(
		BroadcastRuleLocalService broadcastRuleLocalService) {

		_broadcastRuleLocalService = broadcastRuleLocalService;
	}

	private BroadcastRuleLocalService _broadcastRuleLocalService;

}