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

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.StringQuery;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import com.weprode.facile.agenda.model.Event;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.messaging.model.Message;
import com.weprode.facile.news.model.News;
import com.weprode.facile.search.model.SearchResults;
import com.weprode.facile.search.service.base.SearchEngineLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;

@Component(
        service = AopService.class
)
public class SearchEngineLocalServiceImpl extends SearchEngineLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SearchEngineLocalServiceImpl.class);

    @Reference
    protected Queries queries;

    @Reference
    protected Searcher searcher;

    @Reference
    protected SearchRequestBuilderFactory searchRequestBuilderFactory;

    public SearchResults search(User user, String keywords, int page, int pageSize) {
        logger.info("Searching for query " + keywords);

        try {
            long companyId = PortalUtil.getDefaultCompanyId();

            ArrayList<Long> groupIds = new ArrayList<>();
            // User groupId
            groupIds.add(user.getGroupId());

            // Institutionnal groups
            for (Organization org : user.getOrganizations()) {
                groupIds.add(org.getGroupId());
            }

            // Pers. groups
            for (long groupId : user.getGroupIds()) {
                groupIds.add(groupId);
            }

            BooleanQuery globalQuery = queries.booleanQuery();

            // Do not search content over some folders
            for (long folderId : getFolderIdsToNotIndex(user)) {
                StringQuery docLibraryFilter = queries.string("(NOT folderId:" + folderId + ")");
                globalQuery.addFilterQueryClauses(docLibraryFilter);
            }

            BooleanQuery boolQuery = queries.booleanQuery();
            for (Role role : RoleLocalServiceUtil.getUserRoles(user.getUserId())) {
                boolQuery.addShouldQueryClauses(queries.term(Field.ROLE_ID, String.valueOf(role.getRoleId())));
            }
            globalQuery.addFilterQueryClauses(boolQuery);

            SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory.builder();
            searchRequestBuilder.withSearchContext(searchContext -> {
                // CompanyId (mandatory)
                searchContext.setCompanyId(companyId);

                // GroupIds (for filtering user content)
                searchContext.setGroupIds(groupIds.stream().mapToLong(l -> l).toArray());

                // Locale
                searchContext.setLocale(LocaleUtil.getDefault());

                // Class names
                String[] classNames = {Message.class.getName(), News.class.getName(),
                        Event.class.getName(),
                        DLFileEntry.class.getName(), DLFolder.class.getName()};
                searchContext.setEntryClassNames(classNames);

                // Search input
                searchContext.setKeywords(keywords);
            });

            // Enable content highlighting
            searchRequestBuilder.highlightEnabled(true);

            // Handle pagination
            searchRequestBuilder.from((page - 1) * pageSize);
            searchRequestBuilder.size(pageSize);

            logger.debug("Global query is " + globalQuery);
            SearchRequest searchRequest = searchRequestBuilder.query(globalQuery).build();
            SearchResponse searchResponse = searcher.search(searchRequest);

            logger.info("Found " + searchResponse.getCount() + " results. Return " + searchResponse.getSearchHits().getSearchHits().size() + " items for page " + page);
            return new SearchResults(searchResponse, page, pageSize);
        } catch (Exception e) {
            logger.error("Error when fetching search result", e);
        }

        return null;
    }

    private long[] getFolderIdsToNotIndex(User user) throws PortalException {
        return new long[]{
                FolderUtilsLocalServiceUtil.getTmpFolder(user.getUserId()).getFolderId(),
                FolderUtilsLocalServiceUtil.getThumbnailFolder(user.getUserId()).getFolderId(),
                FolderUtilsLocalServiceUtil.getProgressionFolder(user.getUserId()).getFolderId()
        };
    }
}
