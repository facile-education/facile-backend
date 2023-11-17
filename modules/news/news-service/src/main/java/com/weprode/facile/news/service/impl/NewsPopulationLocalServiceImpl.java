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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.contact.service.ContactLocalServiceUtil;
import com.weprode.facile.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.facile.news.model.NewsPopulation;
import com.weprode.facile.news.service.base.NewsPopulationLocalServiceBaseImpl;
import com.weprode.facile.news.service.persistence.NewsPopulationPK;
import com.weprode.facile.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.news.model.NewsPopulation",
        service = AopService.class
)
public class NewsPopulationLocalServiceImpl extends NewsPopulationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(NewsPopulationLocalServiceImpl.class);

    public NewsPopulation addPopulation(long newsId, long groupId, long roleId) throws SystemException {
        NewsPopulationPK newsPopulationPK = new NewsPopulationPK(newsId, groupId, roleId);

        NewsPopulation population = newsPopulationPersistence.create(newsPopulationPK);
        long schoolId = 0;
        try {
            Group group = GroupLocalServiceUtil.getGroup(groupId);
            if (group.isOrganization()) {
                Organization org = OrganizationLocalServiceUtil.getOrganization(group.getClassPK());
                if (OrgDetailsLocalServiceUtil.isSchool(org.getOrganizationId()) || org.getOrganizationId() == OrgUtilsLocalServiceUtil.getOrCreateRootOrg(PortalUtil.getDefaultCompanyId()).getOrganizationId()) {
                    schoolId = org.getOrganizationId();
                } else {
                    schoolId = org.getParentOrganizationId();
                }
            }
        } catch (Exception e) {
            // Nothing
        }
        population.setSchoolId(schoolId);

        return newsPopulationPersistence.update(population);
    }

    public List<NewsPopulation> getNewsPopulations(long newsId) throws SystemException {
        return newsPopulationPersistence.findBynewsId(newsId);
    }

    public JSONArray convertNewsPopulations(long newsId, long userId) throws SystemException {
        JSONArray jsonPopulations = new JSONArray();

        List<NewsPopulation> populations = newsPopulationPersistence.findBynewsId(newsId);
        if (populations != null) {
            for (NewsPopulation population : populations) {
                try {
                    JSONObject jsonPopulation = new JSONObject();
                    jsonPopulation.put(JSONConstants.GROUP_ID, population.getGroupId());
                    jsonPopulation.put(JSONConstants.ROLE_ID, population.getRoleId());
                    Group group = GroupLocalServiceUtil.getGroup(population.getGroupId());
                    if (group.isRegularSite()) {
                        jsonPopulation.put(JSONConstants.POPULATION_NAME, GroupUtilsLocalServiceUtil.getGroupName(population.getGroupId()));
                    } else {
                        jsonPopulation.put(JSONConstants.POPULATION_NAME, ContactLocalServiceUtil.getPopulationName(group.getClassPK(), population.getRoleId(), userId));
                    }
                    jsonPopulations.put(jsonPopulation);
                } catch (Exception e) {
                    logger.info("Error converting population for newsId " + newsId);
                }
            }
        }

        return jsonPopulations;
    }

    public void deleteByNewsId(long newsId) throws SystemException {
        logger.info("Deleting populations for news " + newsId);

        newsPopulationPersistence.removeBynewsId(newsId);
    }

    public void deleteByGroupId(long groupId) {
        newsPopulationPersistence.removeBygroupId(groupId);
    }

    public List<NewsPopulation> getByGroupId(long groupId) {
        return newsPopulationPersistence.findBygroupId(groupId);
    }
}
