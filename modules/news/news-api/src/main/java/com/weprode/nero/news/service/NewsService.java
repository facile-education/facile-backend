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

package com.weprode.nero.news.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for News. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see NewsServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface NewsService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.news.service.impl.NewsServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the news remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link NewsServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(method = "POST", value = "add-news")
	public JSONObject addNews(
		String title, String content, boolean isSchoolNews, boolean isImportant,
		long imageId, String publicationDate, String population,
		String attachFiles);

	@JSONWebService(method = "GET", value = "delete-news")
	public JSONObject deleteNews(long newsId);

	@JSONWebService(method = "POST", value = "edit-news")
	public JSONObject editNews(
		long newsId, String title, String content, boolean isImportant,
		long imageId, String publicationDate, String population,
		String attachFiles, boolean markAsUnreadForAll);

	@JSONWebService(method = "GET", value = "get-group-news-broadcast-groups")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getGroupNewsBroadcastGroups();

	@JSONWebService(method = "GET", value = "get-news-details")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getNewsDetails(long newsId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "GET", value = "get-school-news")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getSchoolNews(
		String maxDateString, int nbNews, boolean importantOnly,
		boolean unreadOnly);

	@JSONWebService(method = "GET", value = "get-school-news-broadcast-groups")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getSchoolNewsBroadcastGroups();

	@JSONWebService(method = "GET", value = "set-news-read")
	public JSONObject setNewsRead(long newsId);

}