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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.contact.service.ContactLocalServiceUtil;
import com.weprode.facile.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.facile.news.exception.NoSuchReadException;
import com.weprode.facile.news.model.NewsPopulation;
import com.weprode.facile.news.model.NewsRead;
import com.weprode.facile.news.service.NewsLocalServiceUtil;
import com.weprode.facile.news.service.NewsPopulationLocalServiceUtil;
import com.weprode.facile.news.service.NewsReadLocalServiceUtil;
import com.weprode.facile.news.service.base.NewsReadLocalServiceBaseImpl;
import com.weprode.facile.news.service.persistence.NewsReadPK;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.news.model.NewsRead",
        service = AopService.class
)
public class NewsReadLocalServiceImpl extends NewsReadLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(NewsReadLocalServiceImpl.class);

    public boolean setNewsRead(long userId, long newsId) {
        try {
            // Check news existence
            NewsLocalServiceUtil.getNews(newsId);
            // Mark it as read
            try {
                newsReadPersistence.findByPrimaryKey(new NewsReadPK(newsId, userId));
            } catch (NoSuchReadException e) {
                NewsRead nr = newsReadPersistence.create(new NewsReadPK(newsId, userId));
                nr.setReadDate(new Date());
                NewsReadLocalServiceUtil.updateNewsRead(nr);
                logger.info("Mark news " + newsId + " as read by userId " + userId);
            }

            return true;
        } catch (Exception e) {
            logger.error("Error marking user " + userId + " has read newsId " + newsId, e);
            return false;
        }
    }

    public boolean setNewsUnRead(long userId, long newsId) {
        try {
            NewsReadPK newsReadPK= new NewsReadPK(newsId, userId);
            NewsReadLocalServiceUtil.deleteNewsRead(newsReadPK);
            logger.info("Mark news " + newsId + " as unRead by userId " + userId);

            return true;
        } catch (Exception e) {
            logger.error("Error marking user " + userId + " has unRead newsId " + newsId, e);
        }

        return false;
    }

    public boolean setNewsAsUnReadForAll(long newsId) {
        try {
            List<NewsRead> newsReadsList = newsReadPersistence.findBynewsId(newsId);

            for (NewsRead nr : newsReadsList) {
                NewsReadLocalServiceUtil.deleteNewsRead(nr);
            }
            logger.info("Mark news " + newsId + " as unRead for all");

            return true;
        } catch (Exception e) {
            logger.error("Error marking user newsId " + newsId + " as unread for all", e);
        }

        return false;
    }


    public NewsRead getUserReadNews(long userId, long newsId) {
        try {
            return newsReadPersistence.findByPrimaryKey(new NewsReadPK(newsId, userId));
        } catch (Exception e) {
            logger.error("Error fetching if user " + userId + " has read newsId " + newsId, e);
        }

        return null;
    }

    public boolean hasUserReadNews(long userId, long newsId) {
        try {
            newsReadPersistence.findByPrimaryKey(new NewsReadPK(newsId, userId));
            return true;
        } catch (NoSuchReadException e) {
            return false;
        } catch (Exception e) {
            logger.error("Error fetching if user " + userId + " has read newsId " + newsId, e);
            return false;
        }
    }

    public List<Long> getUsersHavingRead(long newsId) {
        List<Long> userIds = new ArrayList<>();

        try {
            List<NewsRead> userNewsReads = newsReadPersistence.findBynewsId(newsId);
            if (userNewsReads != null) {
                for (NewsRead userNewsRead : userNewsReads) {
                    userIds.add(userNewsRead.getUserId());
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching user having read newsId " + newsId);
        }

        return userIds;
    }

    public JSONArray getNewsReadStatus(long newsId, long userId) throws SystemException, PortalException {
        JSONArray jsonReadStatus = new JSONArray();

        // Loop over populations
        List<NewsPopulation> populations = NewsPopulationLocalServiceUtil.getNewsPopulations(newsId);
        if (populations != null) {
            for (NewsPopulation population : populations) {
                JSONObject jsonPopulation = new JSONObject();

                // Loop over population members
                JSONArray jsonMembers = new JSONArray();
                Group group = GroupLocalServiceUtil.getGroup(population.getGroupId());
                List<User> groupMembers;
                if (group.isRegularSite()) {
                    groupMembers = UserLocalServiceUtil.getGroupUsers(group.getGroupId());
                    jsonPopulation.put(JSONConstants.POPULATION_NAME, GroupUtilsLocalServiceUtil.getGroupName(population.getGroupId()));
                } else {
                    List<Long> orgIds = new ArrayList<>();
                    orgIds.add(group.getClassPK());
                    List<Long> roleIds = new ArrayList<>();
                    // Role Id 0 means everyone
                    if (population.getRoleId() != 0) {
                        roleIds.add(population.getRoleId());
                    }
                    groupMembers = UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
                    jsonPopulation.put(JSONConstants.POPULATION_NAME, ContactLocalServiceUtil.getPopulationName(group.getClassPK(), population.getRoleId(), userId));
                }

                for (User member : groupMembers) {
                    JSONObject jsonMember = new JSONObject();
                    jsonMember.put(JSONConstants.USER_ID, member.getUserId());
                    jsonMember.put(JSONConstants.LAST_NAME, member.getLastName());
                    jsonMember.put(JSONConstants.FIRST_NAME, member.getFirstName());

                    try {
                        NewsRead newsRead = newsReadPersistence.fetchByPrimaryKey(new NewsReadPK(newsId, member.getUserId()));
                        jsonMember.put(JSONConstants.HAS_READ, true);
                        jsonMember.put(JSONConstants.READ_DATE, new SimpleDateFormat(JSONConstants.DATE_EXCHANGE_FORMAT).format(newsRead.getReadDate()));
                    } catch (Exception e) {
                        jsonMember.put(JSONConstants.HAS_READ, false);
                    }
                    jsonMembers.put(jsonMember);
                }
                jsonPopulation.put(JSONConstants.MEMBERS, jsonMembers);
                jsonReadStatus.put(jsonPopulation);
            }
        }

        return jsonReadStatus;
    }

    public void deleteByNewsId(long newsId) throws SystemException {
        newsReadPersistence.removeBynewsId(newsId);
    }
}
