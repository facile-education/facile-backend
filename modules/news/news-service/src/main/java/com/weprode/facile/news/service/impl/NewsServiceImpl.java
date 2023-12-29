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

package com.weprode.facile.news.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.news.model.News;
import com.weprode.facile.news.service.NewsLocalServiceUtil;
import com.weprode.facile.news.service.NewsReadLocalServiceUtil;
import com.weprode.facile.news.service.base.NewsServiceBaseImpl;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.facile.user.service.NewsAdminLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=news",
                "json.web.service.context.path=News"
        },
        service = AopService.class
)
public class NewsServiceImpl extends NewsServiceBaseImpl {

   private static final Log logger = LogFactoryUtil.getLog(NewsServiceImpl.class);

    @JSONWebService(value = "add-news", method = "POST")
    public JSONObject addNews(String title, String content, boolean isSchoolNews, boolean isImportant, long imageId, String publicationDate, String population, String attachFiles) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (isSchoolNews && !RoleUtilsLocalServiceUtil.isDirectionMember(user) && !NewsAdminLocalServiceUtil.isUserDelegate(user) && !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds school news");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }
        if (!isSchoolNews && !RoleUtilsLocalServiceUtil.isPersonal(user) && !RoleUtilsLocalServiceUtil.isTeacher(user) && !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " adds group news");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }


        try {
            Date publication = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(publicationDate);
            Date expiration = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();
            JSONArray populationJSONArray = new JSONArray(population);
            JSONArray attachFilesArray = new JSONArray(attachFiles);

            News createdNews = NewsLocalServiceUtil.addNews(user.getUserId(), title, content, isSchoolNews, isImportant, imageId, publication, expiration,
                    populationJSONArray, splitLongs(attachFilesArray));

            JSONObject jsonNews = NewsLocalServiceUtil.convertNewsToJson(createdNews.getNewsId(), user.getUserId(), false);
            result.put(JSONConstants.CREATED_NEWS, jsonNews);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Could not create news for " + user.getFullName() + "(id=" + user.getUserId() + "). News title = " + title, e);
        }
        return result;
    }

    @JSONWebService(value = "edit-news", method = "POST")
    public JSONObject editNews(long newsId, String title, String content, boolean isImportant, long imageId, String publicationDate, String population, String attachFiles, boolean markAsUnreadForAll) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Who can edit a news ?
            // School news : direction, secretary, delegate, author, collectivity admin
            // Group news : only the author
            News news = NewsLocalServiceUtil.getNews(newsId);
            if (news.getIsSchoolNews()
                    && news.getAuthorId() != user.getUserId()
                    && !RoleUtilsLocalServiceUtil.isDirectionMember(user)
                    && !NewsAdminLocalServiceUtil.isUserDelegate(user)
                    && !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " edits school news");
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            if (!news.getIsSchoolNews() && news.getAuthorId() != user.getUserId()) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " edits group news");
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            Date publication = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(publicationDate);
            Date expiration = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();

            JSONArray populationJSONArray = new JSONArray(population);
            JSONArray attachFilesArray = new JSONArray(attachFiles);

            News editedNews = NewsLocalServiceUtil.editNews(newsId, title, content, isImportant, imageId, publication, expiration,
                    populationJSONArray, splitLongs(attachFilesArray), markAsUnreadForAll);

            if (markAsUnreadForAll) {
                NewsReadLocalServiceUtil.setNewsAsUnReadForAll(newsId);
            }

            JSONObject jsonNews = NewsLocalServiceUtil.convertNewsToJson(editedNews.getNewsId(), user.getUserId(), false);
            result.put(JSONConstants.EDITED_NEWS, jsonNews);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error editing news for " + user.getFullName() + "(id=" + user.getUserId() + "). News title = " + title, e);
        }

        return result;
    }

    @JSONWebService(value = "get-school-news", method = "GET")
    public JSONObject getSchoolNews(String currentDateString, int startIndex, int nbNews, boolean importantOnly, boolean unreadOnly) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        try {
            Date currentDate = new SimpleDateFormat(NewsLocalServiceImpl.DATE_FORMAT).parse(currentDateString);
            logger.debug("User " + user.getFullName() + " fetches " + (importantOnly ? "important " : "") + (unreadOnly ? "unread " : "") + "school news before " + currentDate);
            List<News> newsList;
            if (RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) || NewsAdminLocalServiceUtil.isUserDelegate(user) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                newsList = NewsLocalServiceUtil.getAllSchoolNews(user, currentDate, startIndex, nbNews, unreadOnly);
            } else {
                newsList = NewsLocalServiceUtil.getNews(user,0, currentDate, startIndex, nbNews,false, importantOnly, unreadOnly);
            }
            JSONArray jsonNewsArray = new JSONArray();
            for (News news : newsList) {
                JSONObject jsonNews = NewsLocalServiceUtil.convertNewsToJson(news.getNewsId(), user.getUserId(), false);
                jsonNewsArray.put(jsonNews);
            }
            result.put(JSONConstants.NEWS, jsonNewsArray);

            if (RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) || NewsAdminLocalServiceUtil.isUserDelegate(user) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                result.put(JSONConstants.NB_UNREAD_NEWS, NewsLocalServiceUtil.countAllSchoolNews(user, importantOnly, true));
            } else {
                result.put(JSONConstants.NB_UNREAD_NEWS, NewsLocalServiceUtil.countNews(user, 0, false, importantOnly, true));
            }
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error getting all news", e);
        }

        return result;
    }

    @JSONWebService(value = "set-news-read", method = "GET")
    public JSONObject setNewsRead(long newsId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check if the user can read news
            if (!NewsLocalServiceUtil.hasUserNews(user.getUserId(), newsId)) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " marks news as read");
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            result.put(JSONConstants.SUCCESS, NewsReadLocalServiceUtil.setNewsRead(user.getUserId(), newsId));
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error saving that news " + newsId + " is read", e);
        }

        return result;
    }

    @JSONWebService(value = "get-group-news-broadcast-groups", method = "GET")
    public JSONObject getGroupNewsBroadcastGroups() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets group news broadcast groups");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }
        try {
            logger.debug("User " + user.getFullName() + " fetches group news broadcast groups");
            // Students and parents cannot write group news
            result = NewsLocalServiceUtil.getGroupNewsBroadcastGroups(user);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error getting broadcast groups", e);
        }

        return result;
    }

    @JSONWebService(value = "get-school-news-broadcast-groups", method = "GET")
    public JSONObject getSchoolNewsBroadcastGroups() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user)
                && !RoleUtilsLocalServiceUtil.isSecretariat(user)
                && !NewsAdminLocalServiceUtil.isUserDelegate(user)
                && !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets school news broadcast groups");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            logger.debug("User " + user.getFullName() + " fetches school news broadcast groups");
            if (RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
                result = NewsLocalServiceUtil.getSchoolNewsBroadcastGroupsForCollectivityAdmins(user);
            } else {
                result = NewsLocalServiceUtil.getSchoolNewsBroadcastGroups(user);
            }
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error getting school news broadcast groups", e);
        }

        return result;
    }

    @JSONWebService(value = "get-news-details", method = "GET")
    public JSONObject getNewsDetails(long newsId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        try {
            // Check if the user can read news
            if (!NewsLocalServiceUtil.hasUserNews(user.getUserId(), newsId)) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets news details");
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            JSONObject jsonNews = NewsLocalServiceUtil.convertNewsToJson(newsId, user.getUserId(), true);
            result.put(JSONConstants.NEWS, jsonNews);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error getting news details for news " + newsId, e);
        }

        return result;
    }

    @JSONWebService(value = "delete-news", method = "GET")
    public JSONObject deleteNews(long newsId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        try {
            // Only the author of a news
            News news = NewsLocalServiceUtil.getNews(newsId);
            if (user.getUserId() != news.getAuthorId()) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes news");
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            logger.info("User " + user.getFullName() + " deletes news " + newsId);
            NewsLocalServiceUtil.deleteNewsAndDependencies(news);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error deleting news", e);
        }

        return result;
    }

    private static List<Long> splitLongs(JSONArray source) {
        List<Long> list = new ArrayList<>();
        
        for (int i = 0; i < source.length(); i++) {
            list.add(source.getLong(i));
        }
        
        return list;
    }
}
