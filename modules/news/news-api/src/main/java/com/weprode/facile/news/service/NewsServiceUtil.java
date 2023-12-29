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

package com.weprode.facile.news.service;

/**
 * Provides the remote service utility for News. This utility wraps
 * <code>com.weprode.facile.news.service.impl.NewsServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see NewsService
 * @generated
 */
public class NewsServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.news.service.impl.NewsServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject addNews(
		java.lang.String title, java.lang.String content, boolean isSchoolNews,
		boolean isImportant, long imageId, java.lang.String publicationDate,
		java.lang.String population, java.lang.String attachFiles) {

		return getService().addNews(
			title, content, isSchoolNews, isImportant, imageId, publicationDate,
			population, attachFiles);
	}

	public static org.json.JSONObject deleteNews(long newsId) {
		return getService().deleteNews(newsId);
	}

	public static org.json.JSONObject editNews(
		long newsId, java.lang.String title, java.lang.String content,
		boolean isImportant, long imageId, java.lang.String publicationDate,
		java.lang.String population, java.lang.String attachFiles,
		boolean markAsUnreadForAll) {

		return getService().editNews(
			newsId, title, content, isImportant, imageId, publicationDate,
			population, attachFiles, markAsUnreadForAll);
	}

	public static org.json.JSONObject getGroupNewsBroadcastGroups() {
		return getService().getGroupNewsBroadcastGroups();
	}

	public static org.json.JSONObject getNewsDetails(long newsId) {
		return getService().getNewsDetails(newsId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getSchoolNews(
		java.lang.String currentDateString, int startIndex, int nbNews,
		boolean importantOnly, boolean unreadOnly) {

		return getService().getSchoolNews(
			currentDateString, startIndex, nbNews, importantOnly, unreadOnly);
	}

	public static org.json.JSONObject getSchoolNewsBroadcastGroups() {
		return getService().getSchoolNewsBroadcastGroups();
	}

	public static org.json.JSONObject setNewsRead(long newsId) {
		return getService().setNewsRead(newsId);
	}

	public static NewsService getService() {
		return _service;
	}

	private static volatile NewsService _service;

}