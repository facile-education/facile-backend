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

package com.weprode.facile.agenda.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.agenda.model.EventPopulation;
import com.weprode.facile.agenda.service.base.EventPopulationLocalServiceBaseImpl;
import com.weprode.facile.agenda.service.persistence.EventPopulationPK;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.contact.service.ContactLocalServiceUtil;
import com.weprode.facile.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.agenda.model.EventPopulation",
        service = AopService.class
)
public class EventPopulationLocalServiceImpl extends EventPopulationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(EventPopulationLocalServiceImpl.class);

    public EventPopulation addPopulation(long eventId, long groupId, long roleId) throws SystemException {
        EventPopulationPK eventPopulationPK = new EventPopulationPK(eventId, groupId, roleId);
        
        EventPopulation population = eventPopulationPersistence.create(eventPopulationPK);
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
        return eventPopulationPersistence.update(population);
    }

    public List<EventPopulation> getEventPopulations(long eventId) throws SystemException {
        return eventPopulationPersistence.findByeventId(eventId);
    }

    public JSONArray convertEventPopulations(long eventId, long userId) throws SystemException {
        JSONArray populations = new JSONArray();

        List<EventPopulation> eventPopulations = eventPopulationPersistence.findByeventId(eventId);
        if (eventPopulations != null) {

            for (EventPopulation eventPopulation : eventPopulations) {
                try {
                    JSONObject jsonEvent = new JSONObject();
                    long roleId = eventPopulation.getRoleId();
                    jsonEvent.put(JSONConstants.GROUP_ID, eventPopulation.getGroupId());
                    jsonEvent.put(JSONConstants.ROLE_ID, roleId);
                    Group group = GroupLocalServiceUtil.getGroup(eventPopulation.getGroupId());
                    String populationName = ContactLocalServiceUtil.getPopulationName(group.getClassPK(), roleId, userId);
                    if (!populationName.equals("")) {
                        jsonEvent.put(JSONConstants.POPULATION_NAME, populationName);
                        populations.put(jsonEvent);
                    }
                } catch (Exception e) {
                    logger.error("Error converting population for eventId " + eventId, e);
                }
            }
        }

        return populations;
    }
}
