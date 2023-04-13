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

package com.weprode.nero.application.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.application.model.BroadcastRule;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the broadcast rule service. This utility wraps <code>com.weprode.nero.application.service.persistence.impl.BroadcastRulePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastRulePersistence
 * @generated
 */
public class BroadcastRuleUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(BroadcastRule broadcastRule) {
		getPersistence().clearCache(broadcastRule);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, BroadcastRule> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BroadcastRule> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BroadcastRule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BroadcastRule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BroadcastRule update(BroadcastRule broadcastRule) {
		return getPersistence().update(broadcastRule);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BroadcastRule update(
		BroadcastRule broadcastRule, ServiceContext serviceContext) {

		return getPersistence().update(broadcastRule, serviceContext);
	}

	/**
	 * Returns all the broadcast rules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching broadcast rules
	 */
	public static List<BroadcastRule> findByschoolId(long schoolId) {
		return getPersistence().findByschoolId(schoolId);
	}

	/**
	 * Returns a range of all the broadcast rules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @return the range of matching broadcast rules
	 */
	public static List<BroadcastRule> findByschoolId(
		long schoolId, int start, int end) {

		return getPersistence().findByschoolId(schoolId, start, end);
	}

	/**
	 * Returns an ordered range of all the broadcast rules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching broadcast rules
	 */
	public static List<BroadcastRule> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the broadcast rules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching broadcast rules
	 */
	public static List<BroadcastRule> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	public static BroadcastRule findByschoolId_First(
			long schoolId, OrderByComparator<BroadcastRule> orderByComparator)
		throws com.weprode.nero.application.exception.
			NoSuchBroadcastRuleException {

		return getPersistence().findByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the first broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	public static BroadcastRule fetchByschoolId_First(
		long schoolId, OrderByComparator<BroadcastRule> orderByComparator) {

		return getPersistence().fetchByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	public static BroadcastRule findByschoolId_Last(
			long schoolId, OrderByComparator<BroadcastRule> orderByComparator)
		throws com.weprode.nero.application.exception.
			NoSuchBroadcastRuleException {

		return getPersistence().findByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	public static BroadcastRule fetchByschoolId_Last(
		long schoolId, OrderByComparator<BroadcastRule> orderByComparator) {

		return getPersistence().fetchByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the broadcast rules before and after the current broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param broadcastRuleId the primary key of the current broadcast rule
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next broadcast rule
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	public static BroadcastRule[] findByschoolId_PrevAndNext(
			long broadcastRuleId, long schoolId,
			OrderByComparator<BroadcastRule> orderByComparator)
		throws com.weprode.nero.application.exception.
			NoSuchBroadcastRuleException {

		return getPersistence().findByschoolId_PrevAndNext(
			broadcastRuleId, schoolId, orderByComparator);
	}

	/**
	 * Removes all the broadcast rules where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public static void removeByschoolId(long schoolId) {
		getPersistence().removeByschoolId(schoolId);
	}

	/**
	 * Returns the number of broadcast rules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching broadcast rules
	 */
	public static int countByschoolId(long schoolId) {
		return getPersistence().countByschoolId(schoolId);
	}

	/**
	 * Returns all the broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching broadcast rules
	 */
	public static List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId) {

		return getPersistence().findByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Returns a range of all the broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @return the range of matching broadcast rules
	 */
	public static List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId, int start, int end) {

		return getPersistence().findByapplicationId_schoolId(
			applicationId, schoolId, start, end);
	}

	/**
	 * Returns an ordered range of all the broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching broadcast rules
	 */
	public static List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId, int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator) {

		return getPersistence().findByapplicationId_schoolId(
			applicationId, schoolId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching broadcast rules
	 */
	public static List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId, int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByapplicationId_schoolId(
			applicationId, schoolId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	public static BroadcastRule findByapplicationId_schoolId_First(
			long applicationId, long schoolId,
			OrderByComparator<BroadcastRule> orderByComparator)
		throws com.weprode.nero.application.exception.
			NoSuchBroadcastRuleException {

		return getPersistence().findByapplicationId_schoolId_First(
			applicationId, schoolId, orderByComparator);
	}

	/**
	 * Returns the first broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	public static BroadcastRule fetchByapplicationId_schoolId_First(
		long applicationId, long schoolId,
		OrderByComparator<BroadcastRule> orderByComparator) {

		return getPersistence().fetchByapplicationId_schoolId_First(
			applicationId, schoolId, orderByComparator);
	}

	/**
	 * Returns the last broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	public static BroadcastRule findByapplicationId_schoolId_Last(
			long applicationId, long schoolId,
			OrderByComparator<BroadcastRule> orderByComparator)
		throws com.weprode.nero.application.exception.
			NoSuchBroadcastRuleException {

		return getPersistence().findByapplicationId_schoolId_Last(
			applicationId, schoolId, orderByComparator);
	}

	/**
	 * Returns the last broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	public static BroadcastRule fetchByapplicationId_schoolId_Last(
		long applicationId, long schoolId,
		OrderByComparator<BroadcastRule> orderByComparator) {

		return getPersistence().fetchByapplicationId_schoolId_Last(
			applicationId, schoolId, orderByComparator);
	}

	/**
	 * Returns the broadcast rules before and after the current broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param broadcastRuleId the primary key of the current broadcast rule
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next broadcast rule
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	public static BroadcastRule[] findByapplicationId_schoolId_PrevAndNext(
			long broadcastRuleId, long applicationId, long schoolId,
			OrderByComparator<BroadcastRule> orderByComparator)
		throws com.weprode.nero.application.exception.
			NoSuchBroadcastRuleException {

		return getPersistence().findByapplicationId_schoolId_PrevAndNext(
			broadcastRuleId, applicationId, schoolId, orderByComparator);
	}

	/**
	 * Removes all the broadcast rules where applicationId = &#63; and schoolId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 */
	public static void removeByapplicationId_schoolId(
		long applicationId, long schoolId) {

		getPersistence().removeByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Returns the number of broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the number of matching broadcast rules
	 */
	public static int countByapplicationId_schoolId(
		long applicationId, long schoolId) {

		return getPersistence().countByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Caches the broadcast rule in the entity cache if it is enabled.
	 *
	 * @param broadcastRule the broadcast rule
	 */
	public static void cacheResult(BroadcastRule broadcastRule) {
		getPersistence().cacheResult(broadcastRule);
	}

	/**
	 * Caches the broadcast rules in the entity cache if it is enabled.
	 *
	 * @param broadcastRules the broadcast rules
	 */
	public static void cacheResult(List<BroadcastRule> broadcastRules) {
		getPersistence().cacheResult(broadcastRules);
	}

	/**
	 * Creates a new broadcast rule with the primary key. Does not add the broadcast rule to the database.
	 *
	 * @param broadcastRuleId the primary key for the new broadcast rule
	 * @return the new broadcast rule
	 */
	public static BroadcastRule create(long broadcastRuleId) {
		return getPersistence().create(broadcastRuleId);
	}

	/**
	 * Removes the broadcast rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule that was removed
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	public static BroadcastRule remove(long broadcastRuleId)
		throws com.weprode.nero.application.exception.
			NoSuchBroadcastRuleException {

		return getPersistence().remove(broadcastRuleId);
	}

	public static BroadcastRule updateImpl(BroadcastRule broadcastRule) {
		return getPersistence().updateImpl(broadcastRule);
	}

	/**
	 * Returns the broadcast rule with the primary key or throws a <code>NoSuchBroadcastRuleException</code> if it could not be found.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	public static BroadcastRule findByPrimaryKey(long broadcastRuleId)
		throws com.weprode.nero.application.exception.
			NoSuchBroadcastRuleException {

		return getPersistence().findByPrimaryKey(broadcastRuleId);
	}

	/**
	 * Returns the broadcast rule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule, or <code>null</code> if a broadcast rule with the primary key could not be found
	 */
	public static BroadcastRule fetchByPrimaryKey(long broadcastRuleId) {
		return getPersistence().fetchByPrimaryKey(broadcastRuleId);
	}

	/**
	 * Returns all the broadcast rules.
	 *
	 * @return the broadcast rules
	 */
	public static List<BroadcastRule> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the broadcast rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @return the range of broadcast rules
	 */
	public static List<BroadcastRule> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the broadcast rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of broadcast rules
	 */
	public static List<BroadcastRule> findAll(
		int start, int end,
		OrderByComparator<BroadcastRule> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the broadcast rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastRuleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcast rules
	 * @param end the upper bound of the range of broadcast rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of broadcast rules
	 */
	public static List<BroadcastRule> findAll(
		int start, int end, OrderByComparator<BroadcastRule> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the broadcast rules from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of broadcast rules.
	 *
	 * @return the number of broadcast rules
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BroadcastRulePersistence getPersistence() {
		return _persistence;
	}

	private static volatile BroadcastRulePersistence _persistence;

}