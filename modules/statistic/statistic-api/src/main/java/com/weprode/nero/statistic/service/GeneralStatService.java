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

package com.weprode.nero.statistic.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.*;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for GeneralStat. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see GeneralStatServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface GeneralStatService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.statistic.service.impl.GeneralStatServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the general stat remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link GeneralStatServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(method = "GET", value = "get-active-users-count")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getActiveUsersCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId);

	@JSONWebService(method = "GET", value = "get-dashboard-statistics-count")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getDashboardStatistics();

	@JSONWebService(method = "GET", value = "get-files-count")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getFilesCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId);

	@JSONWebService(method = "GET", value = "get-homeworks-count")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getHomeworksCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId);

	@JSONWebService(method = "GET", value = "get-messages-count")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getMessagesCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId);

	@JSONWebService(method = "GET", value = "get-news-count")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getNewsCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "GET", value = "get-sessions-count")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getSessionsCount(
		java.util.Date startDate, java.util.Date endDate, long schoolId,
		long serviceId, String comparator);

}