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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import com.weprode.nero.group.model.GroupActivity;

import java.util.*;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for GroupActivity. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see GroupActivityLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface GroupActivityLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.group.service.impl.GroupActivityLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the group activity local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link GroupActivityLocalServiceUtil} if injection and service tracking are not available.
	 */
	public JSONObject convertGroupActivity(
		long userId, GroupActivity groupActivity);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<GroupActivity> getGroupsActivities(
		long userId, java.util.List<Long> groupIds, java.util.Date maxDate,
		int nbResults);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<GroupActivity> getGroupsActivities(
		long userId, java.util.List<Long> groupIds, java.util.Date maxDate,
		int nbResults, boolean allHistory, boolean containNews,
		boolean containDocs, boolean containMembership,
		boolean containPendingFirings, boolean containFirings,
		boolean containHomework, boolean containsSessions);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<GroupActivity> getGroupsHistory(
		long userId, java.util.List<Long> groupIds, java.util.Date maxDate,
		int nbResults);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

}