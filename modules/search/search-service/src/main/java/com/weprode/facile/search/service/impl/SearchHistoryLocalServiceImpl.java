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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.search.constants.SearchConstants;
import com.weprode.facile.search.model.SearchHistory;
import com.weprode.facile.search.service.base.SearchHistoryLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.search.model.SearchHistory",
        service = AopService.class
)
public class SearchHistoryLocalServiceImpl extends SearchHistoryLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SearchHistoryLocalServiceImpl.class);

    public boolean addQuery(long userId, String query) {
        // First get existing queries for user
        try {
            List<SearchHistory> userQueries = searchHistoryPersistence.findByuserId(userId);
            if (userQueries != null) {
                // Loop over them to check if current query has been run or not
                // This to avoid having the same query in history
                boolean isFound = false;
                Date oldestQueryDate = new Date();
                long oldestSearchHistoryId = 0;

                for (SearchHistory userQuery : userQueries) {
                    if (userQuery.getQuery().equalsIgnoreCase(query)) {
                        // Same query was run -> update the date
                        userQuery.setQueryDate(new Date());
                        logger.info("Updating query date for query " + query);
                        searchHistoryPersistence.updateImpl(userQuery);
                        isFound = true;
                    }
                    // For further deletion
                    if (userQuery.getQueryDate().before(oldestQueryDate)) {
                        oldestSearchHistoryId = userQuery.getSearchHistoryId();
                        oldestQueryDate = userQuery.getQueryDate();
                    }
                }

                // If not found, adding a new one
                if (!isFound) {
                    SearchHistory searchHistory = searchHistoryPersistence.create(counterLocalService.increment());
                    searchHistory.setUserId(userId);
                    searchHistory.setQuery(query);
                    searchHistory.setQueryDate(new Date());
                    searchHistoryPersistence.updateImpl(searchHistory);
                    logger.info("Adding new query in history for user " + userId);

                    // Delete the oldest search item if there are more than NB_QUERIES
                    if (searchHistoryPersistence.countByuserId(userId) > SearchConstants.NB_QUERIES) {
                        searchHistoryPersistence.remove(oldestSearchHistoryId);
                        logger.info("Deleted oldest query for user " + userId);
                    }
                }
            }

            return true;
        } catch (Exception e) {
            logger.error("Error while adding query for user " + userId + " in history", e);
        }

        return false;
    }

    // Returns the sorted list
    public List<SearchHistory> getLastSearchQueries(long userId) {
        List<SearchHistory> lastQueries = new ArrayList<>();

        try {
            List<SearchHistory> historyList = new ArrayList<>(searchHistoryPersistence.findByuserId(userId));
            // Sort by query date
            historyList.sort((searchHistory1, searchHistory2) -> searchHistory2.getQueryDate().compareTo(searchHistory1.getQueryDate()));

            // Limit to NB_QUERIES if needed
            if (historyList.size() > SearchConstants.NB_QUERIES) {
                historyList = historyList.subList(0, SearchConstants.NB_QUERIES);
            }

            return historyList;
        } catch (Exception e) {
            logger.error("Error while fetching last queries for user " + userId + " in history", e);
        }

        return lastQueries;
    }
}
