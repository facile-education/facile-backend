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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NewsService}.
 *
 * @author Brian Wing Shun Chan
 * @see NewsService
 * @generated
 */
public class NewsServiceWrapper
	implements NewsService, ServiceWrapper<NewsService> {

	public NewsServiceWrapper() {
		this(null);
	}

	public NewsServiceWrapper(NewsService newsService) {
		_newsService = newsService;
	}

	@Override
	public org.json.JSONObject addNews(
		String title, String content, boolean isSchoolNews, boolean isImportant,
		long imageId, String publicationDate, String population,
		String attachFiles) {

		return _newsService.addNews(
			title, content, isSchoolNews, isImportant, imageId, publicationDate,
			population, attachFiles);
	}

	@Override
	public org.json.JSONObject deleteNews(long newsId) {
		return _newsService.deleteNews(newsId);
	}

	@Override
	public org.json.JSONObject editNews(
		long newsId, String title, String content, boolean isImportant,
		long imageId, String publicationDate, String population,
		String attachFiles, boolean markAsUnreadForAll) {

		return _newsService.editNews(
			newsId, title, content, isImportant, imageId, publicationDate,
			population, attachFiles, markAsUnreadForAll);
	}

	@Override
	public org.json.JSONObject getGroupNewsBroadcastGroups() {
		return _newsService.getGroupNewsBroadcastGroups();
	}

	@Override
	public org.json.JSONObject getNewsDetails(long newsId) {
		return _newsService.getNewsDetails(newsId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _newsService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSchoolNews(
		String maxDateString, int nbNews, boolean importantOnly,
		boolean unreadOnly) {

		return _newsService.getSchoolNews(
			maxDateString, nbNews, importantOnly, unreadOnly);
	}

	@Override
	public org.json.JSONObject getSchoolNewsBroadcastGroups() {
		return _newsService.getSchoolNewsBroadcastGroups();
	}

	@Override
	public org.json.JSONObject setNewsRead(long newsId) {
		return _newsService.setNewsRead(newsId);
	}

	@Override
	public NewsService getWrappedService() {
		return _newsService;
	}

	@Override
	public void setWrappedService(NewsService newsService) {
		_newsService = newsService;
	}

	private NewsService _newsService;

}