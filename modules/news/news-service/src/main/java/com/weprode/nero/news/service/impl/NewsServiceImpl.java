package com.weprode.nero.news.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.news.model.News;
import com.weprode.nero.news.service.NewsLocalServiceUtil;
import com.weprode.nero.news.service.NewsReadLocalServiceUtil;
import com.weprode.nero.news.service.base.NewsServiceBaseImpl;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.user.service.NewsAdminLocalServiceUtil;
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
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        if (isSchoolNews && !RoleUtilsLocalServiceUtil.isDirectionMember(user) && !NewsAdminLocalServiceUtil.isUserDelegate(user) && !RoleUtilsLocalServiceUtil.isSecretariat(user)) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        } else if (!isSchoolNews && !RoleUtilsLocalServiceUtil.isPersonal(user) && !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }


        try {
            Date publication = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(publicationDate);
            Date expiration = ScheduleConfigurationLocalServiceUtil.getDefaultSchoolYearEndDate();
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
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            // Who can edit a news ?
            // School news : the author, a direction member or a secretary
            // Group news : only the author
            News news = NewsLocalServiceUtil.getNews(newsId);
            if (news.getIsSchoolNews() && news.getAuthorId() != user.getUserId() && !RoleUtilsLocalServiceUtil.isDirectionMember(user) && !NewsAdminLocalServiceUtil.isUserDelegate(user) && !RoleUtilsLocalServiceUtil.isSecretariat(user)) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            if (!news.getIsSchoolNews() && news.getAuthorId() != user.getUserId()) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            Date publication = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(publicationDate);
            Date expiration = ScheduleConfigurationLocalServiceUtil.getDefaultSchoolYearEndDate();

            JSONArray populationJSONArray = new JSONArray(population);
            JSONArray attachFilesArray = new JSONArray(attachFiles);

            News editedNews = NewsLocalServiceUtil.editNews(newsId, title, content, isImportant, imageId, publication, expiration,
                    populationJSONArray, splitLongs(attachFilesArray));

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
    public JSONObject getSchoolNews(String maxDateString, int nbNews, boolean importantOnly, boolean unreadOnly) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        try {
            Date maxDate = new SimpleDateFormat(NewsLocalServiceImpl.DATE_FORMAT).parse(maxDateString);
            logger.info("User " + user.getFullName() + " fetches " + (importantOnly ? "important " : "") + (unreadOnly ? "unread " : "") + " school news before " + maxDate);
            List<News> newsList = NewsLocalServiceUtil.getNews(user,0, maxDate, nbNews,false, importantOnly, unreadOnly);

            JSONArray jsonNewsArray = new JSONArray();
            for (News news : newsList) {
                JSONObject jsonNews = NewsLocalServiceUtil.convertNewsToJson(news.getNewsId(), user.getUserId(), false);
                jsonNewsArray.put(jsonNews);
            }
            result.put(JSONConstants.NEWS, jsonNewsArray);

            result.put(JSONConstants.NB_UNREAD_NEWS, NewsLocalServiceUtil.getNewsCount(user, 0, false, false, true));

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
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
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
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        try {
            logger.info("User " + user.getFullName() + " fetches group news broadcast groups");
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
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        try {
            if (RoleUtilsLocalServiceUtil.isDirectionMember(user)
                    || RoleUtilsLocalServiceUtil.isSchoolAdmin(user)
                    || NewsAdminLocalServiceUtil.isUserDelegate(user)) {
                logger.info("User " + user.getFullName() + " fetches school news broadcast groups");
                result = NewsLocalServiceUtil.getSchoolNewsBroadcastGroups(user);
            }
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error getting broadcast groups", e);
        }

        return result;
    }

    @JSONWebService(value = "get-news-details", method = "GET")
    public JSONObject getNewsDetails(long newsId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        try {
            // Check if the user can read news
            if (NewsLocalServiceUtil.hasUserNews(user.getUserId(), newsId)) {
                logger.info("User " + user.getFullName() + " fetches details for news " + newsId);
                JSONObject jsonNews = NewsLocalServiceUtil.convertNewsToJson(newsId, user.getUserId(), true);
                result.put(JSONConstants.NEWS, jsonNews);
            }

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error getting broadcasted groups", e);
        }

        return result;
    }

    @JSONWebService(value = "delete-news", method = "GET")
    public JSONObject deleteNews(long newsId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
        try {
            // Only the author of a news can delete it
            News news = NewsLocalServiceUtil.getNews(newsId);
            if (user.getUserId() == news.getAuthorId()) {
                logger.info("User " + user.getFullName() + " deletes news " + newsId);
                NewsLocalServiceUtil.deleteNewsAndDependencies(newsId);
                result.put(JSONConstants.SUCCESS, true);
            }

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
