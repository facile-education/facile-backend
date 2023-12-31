/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.search.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.hits.SearchHit;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.messaging.model.Message;
import com.weprode.facile.messaging.model.MessageFolder;
import com.weprode.facile.messaging.service.MessageAttachFileLocalServiceUtil;
import com.weprode.facile.messaging.service.MessageFolderLocalServiceUtil;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.news.model.NewsAttachedFile;
import com.weprode.facile.news.service.NewsAttachedFileLocalServiceUtil;
import com.weprode.facile.search.constants.SearchConstants;
import com.weprode.facile.search.model.SearchHistory;
import com.weprode.facile.search.model.SearchResults;
import com.weprode.facile.search.service.SearchEngineLocalServiceUtil;
import com.weprode.facile.search.service.SearchHistoryLocalServiceUtil;
import com.weprode.facile.search.service.base.SearchEngineServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=search",
                "json.web.service.context.path=SearchEngine"
        },
        service = AopService.class
)
public class SearchEngineServiceImpl extends SearchEngineServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SearchEngineServiceImpl.class);

    @JSONWebService(value = "quick-search", method = "GET")
    public JSONObject quickSearch(String query, int start, int end) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        logger.info("User " + user.getFullName() + " searches query " + query);
        JSONArray jsonResults = new JSONArray();
        int page = start / (end - start) + 1;
        SearchResults results = SearchEngineLocalServiceUtil.search(user, query, page, (end - start));
        for (SearchHit searchResult : results.getHits()) {
            jsonResults.put(SearchResults.convertHitToJson(searchResult, user.getUserId()));
        }

        result.put(JSONConstants.RESULTS, jsonResults);
        result.put(JSONConstants.TOTAL_COUNT, results.getCount());
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    @JSONWebService(value = "add-query-history", method = "GET")
    public JSONObject addQueryHistory(String query) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            SearchHistoryLocalServiceUtil.addQuery(user.getUserId(), query);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while adding query '" + query + "' to history", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "advanced-search", method = "GET")
    public JSONObject advancedSearch(String query, int service, boolean filesOnly, int start, int end) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            JSONArray jsonResults = new JSONArray();
            int page = start / (end - start) + 1;
            SearchResults results = SearchEngineLocalServiceUtil.search(user, query, page, (end - start));
            for (SearchHit searchResult : results.getHits()) {
                jsonResults.put(SearchResults.convertHitToJson(searchResult, user.getUserId()));
            }

            result.put(JSONConstants.RESULTS, jsonResults);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while searching query '" + query + "'", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-last-search-queries", method = "GET")
    public JSONObject getLastSearchQueries() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            JSONArray jsonResults = new JSONArray();
            List<SearchHistory> searchQueries = SearchHistoryLocalServiceUtil.getLastSearchQueries(user.getUserId());
            for (SearchHistory searchQuery : searchQueries) {
                JSONObject jsonResult = new JSONObject();
                jsonResult.put(JSONConstants.HISTORY_ID, searchQuery.getSearchHistoryId());
                jsonResult.put(JSONConstants.QUERY, searchQuery.getQuery());
                jsonResult.put(JSONConstants.DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(searchQuery.getQueryDate()));
                jsonResults.put(jsonResult);
            }

            result.put(JSONConstants.RESULTS, jsonResults);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while fetching last search queries", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-search-result-details", method = "GET")
    public JSONObject getSearchResultDetails(long entityId, int service) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        JSONObject jsonResult = new JSONObject();
        try {
            switch (service) {
                case SearchConstants.TYPE_NEWS:
                    JSONArray newsAttachments = new JSONArray();
                    for (NewsAttachedFile attachment : NewsAttachedFileLocalServiceUtil.getNewsAttachedFiles(entityId)) {
                        String title = DLAppServiceUtil.getFileEntry(attachment.getFileId()).getTitle();
                        newsAttachments.put(title);
                    }
                    jsonResult.put(JSONConstants.ATTACHED_FILES, newsAttachments);

                    break;
                case SearchConstants.TYPE_MESSAGE:
                    jsonResult.put(JSONConstants.ENTITY_ID, entityId);
                    Message message = MessageLocalServiceUtil.getMessage(entityId);

                    MessageFolder messageFolder = MessageFolderLocalServiceUtil.getMessageFolder(message.getFolderId());
                    JSONObject messageFolderJson = new JSONObject();
                    messageFolderJson.put(JSONConstants.ID, messageFolder.getFolderId());
                    messageFolderJson.put(JSONConstants.FOLDER_NAME, messageFolder.getFolderName());
                    jsonResult.put(JSONConstants.MESSAGING_FOLDER, messageFolderJson);

                    JSONArray attachments = new JSONArray();
                    for (long attachmentId : MessageAttachFileLocalServiceUtil.getMessageAttachFileIds(message.getMessageId())) {
                        String title = DLAppServiceUtil.getFileEntry(attachmentId).getTitle();
                        attachments.put(title);
                    }
                    jsonResult.put(JSONConstants.ATTACHED_FILES, attachments);
                    break;
                default:
                    logger.warn("Unknown service type = " + service);
            }

            result.put(JSONConstants.RESULT, jsonResult);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while fetching last search queries", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
