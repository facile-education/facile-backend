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

package com.weprode.facile.application.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.application.exception.NoSuchBroadcastRuleException;
import com.weprode.facile.application.model.BroadcastRule;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the broadcast rule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastRuleUtil
 * @generated
 */
@ProviderType
public interface BroadcastRulePersistence
	extends BasePersistence<BroadcastRule> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BroadcastRuleUtil} to access the broadcast rule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the broadcast rules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching broadcast rules
	 */
	public java.util.List<BroadcastRule> findByschoolId(long schoolId);

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
	public java.util.List<BroadcastRule> findByschoolId(
		long schoolId, int start, int end);

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
	public java.util.List<BroadcastRule> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator);

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
	public java.util.List<BroadcastRule> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	public BroadcastRule findByschoolId_First(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
				orderByComparator)
		throws NoSuchBroadcastRuleException;

	/**
	 * Returns the first broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	public BroadcastRule fetchByschoolId_First(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator);

	/**
	 * Returns the last broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	public BroadcastRule findByschoolId_Last(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
				orderByComparator)
		throws NoSuchBroadcastRuleException;

	/**
	 * Returns the last broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	public BroadcastRule fetchByschoolId_Last(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator);

	/**
	 * Returns the broadcast rules before and after the current broadcast rule in the ordered set where schoolId = &#63;.
	 *
	 * @param broadcastRuleId the primary key of the current broadcast rule
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next broadcast rule
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	public BroadcastRule[] findByschoolId_PrevAndNext(
			long broadcastRuleId, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
				orderByComparator)
		throws NoSuchBroadcastRuleException;

	/**
	 * Removes all the broadcast rules where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public void removeByschoolId(long schoolId);

	/**
	 * Returns the number of broadcast rules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching broadcast rules
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Returns all the broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching broadcast rules
	 */
	public java.util.List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId);

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
	public java.util.List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId, int start, int end);

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
	public java.util.List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator);

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
	public java.util.List<BroadcastRule> findByapplicationId_schoolId(
		long applicationId, long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	public BroadcastRule findByapplicationId_schoolId_First(
			long applicationId, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
				orderByComparator)
		throws NoSuchBroadcastRuleException;

	/**
	 * Returns the first broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	public BroadcastRule fetchByapplicationId_schoolId_First(
		long applicationId, long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator);

	/**
	 * Returns the last broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule
	 * @throws NoSuchBroadcastRuleException if a matching broadcast rule could not be found
	 */
	public BroadcastRule findByapplicationId_schoolId_Last(
			long applicationId, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
				orderByComparator)
		throws NoSuchBroadcastRuleException;

	/**
	 * Returns the last broadcast rule in the ordered set where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast rule, or <code>null</code> if a matching broadcast rule could not be found
	 */
	public BroadcastRule fetchByapplicationId_schoolId_Last(
		long applicationId, long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator);

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
	public BroadcastRule[] findByapplicationId_schoolId_PrevAndNext(
			long broadcastRuleId, long applicationId, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
				orderByComparator)
		throws NoSuchBroadcastRuleException;

	/**
	 * Removes all the broadcast rules where applicationId = &#63; and schoolId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 */
	public void removeByapplicationId_schoolId(
		long applicationId, long schoolId);

	/**
	 * Returns the number of broadcast rules where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the number of matching broadcast rules
	 */
	public int countByapplicationId_schoolId(long applicationId, long schoolId);

	/**
	 * Caches the broadcast rule in the entity cache if it is enabled.
	 *
	 * @param broadcastRule the broadcast rule
	 */
	public void cacheResult(BroadcastRule broadcastRule);

	/**
	 * Caches the broadcast rules in the entity cache if it is enabled.
	 *
	 * @param broadcastRules the broadcast rules
	 */
	public void cacheResult(java.util.List<BroadcastRule> broadcastRules);

	/**
	 * Creates a new broadcast rule with the primary key. Does not add the broadcast rule to the database.
	 *
	 * @param broadcastRuleId the primary key for the new broadcast rule
	 * @return the new broadcast rule
	 */
	public BroadcastRule create(long broadcastRuleId);

	/**
	 * Removes the broadcast rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule that was removed
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	public BroadcastRule remove(long broadcastRuleId)
		throws NoSuchBroadcastRuleException;

	public BroadcastRule updateImpl(BroadcastRule broadcastRule);

	/**
	 * Returns the broadcast rule with the primary key or throws a <code>NoSuchBroadcastRuleException</code> if it could not be found.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule
	 * @throws NoSuchBroadcastRuleException if a broadcast rule with the primary key could not be found
	 */
	public BroadcastRule findByPrimaryKey(long broadcastRuleId)
		throws NoSuchBroadcastRuleException;

	/**
	 * Returns the broadcast rule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param broadcastRuleId the primary key of the broadcast rule
	 * @return the broadcast rule, or <code>null</code> if a broadcast rule with the primary key could not be found
	 */
	public BroadcastRule fetchByPrimaryKey(long broadcastRuleId);

	/**
	 * Returns all the broadcast rules.
	 *
	 * @return the broadcast rules
	 */
	public java.util.List<BroadcastRule> findAll();

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
	public java.util.List<BroadcastRule> findAll(int start, int end);

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
	public java.util.List<BroadcastRule> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator);

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
	public java.util.List<BroadcastRule> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BroadcastRule>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the broadcast rules from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of broadcast rules.
	 *
	 * @return the number of broadcast rules
	 */
	public int countAll();

}