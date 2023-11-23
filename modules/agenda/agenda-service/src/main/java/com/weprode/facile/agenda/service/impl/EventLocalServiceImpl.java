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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.agenda.exception.NoSuchEventException;
import com.weprode.facile.agenda.model.Event;
import com.weprode.facile.agenda.model.EventPopulation;
import com.weprode.facile.agenda.service.EventLocalServiceUtil;
import com.weprode.facile.agenda.service.EventPopulationLocalServiceUtil;
import com.weprode.facile.agenda.service.EventReadLocalServiceUtil;
import com.weprode.facile.agenda.service.base.EventLocalServiceBaseImpl;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.mobile.constants.MobileConstants;
import com.weprode.facile.mobile.service.MobileDeviceLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.NewsAdminLocalServiceUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.agenda.model.Event",
        service = AopService.class
)
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(EventLocalServiceImpl.class);

    @Indexable(type = IndexableType.REINDEX)
    public Event createEvent(long authorId, String title, String description, String location, Date startDate, Date endDate, JSONArray populations) throws SystemException {
        // If mandatory fields are not set, return null
        if (title.equals("") || startDate == null || endDate == null || populations.length() == 0) {
            logger.info("Agenda event could not be created because missing mandatory field");
            return null;
        }

        Event event = eventPersistence.create(counterLocalService.increment());
        event.setCompanyId(PortalUtil.getDefaultCompanyId());
        event.setAuthorId(authorId);
        event.setTitle(title);
        String sanitizedDescription = FileUtilsLocalServiceUtil.sanitizeHTMLContent(description);
        event.setDescription(sanitizedDescription);
        event.setLocation(location);

        // Update startDate with current second and milliseconds to ensure that 2 events will not have the same startDate
        Calendar nowCal = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.set(Calendar.SECOND, nowCal.get(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, nowCal.get(Calendar.MILLISECOND));
        event.setStartDate(cal.getTime());
        event.setEndDate(endDate);
        event = eventPersistence.updateImpl(event);

        // Create populations
        for (int idx = 0 ; idx < populations.length() ; idx++) {
            JSONObject population = populations.getJSONObject(idx);
            EventPopulationLocalServiceUtil.addPopulation(event.getEventId(), population.getLong(JSONConstants.GROUP_ID), population.getLong(JSONConstants.ROLE_ID));
        }

        // Mark the event as read for the author
        EventReadLocalServiceUtil.markEventAsRead(authorId, event.getEventId());

        // Mobile push
        manageMobilePush(title, location, description, populations, event.getEventId());

        return event;
    }

    @Indexable(type = IndexableType.REINDEX)
    public Event modifyEvent(long eventId, String title, String description, String location, Date startDate, Date endDate, JSONArray populations, boolean markAsUnread) throws SystemException {
        // If mandatory fields are not set, return null
        if (title.equals("") || startDate == null || endDate == null || populations.length() == 0) {
            logger.info("Agenda event could not be modified because missing mandatory field");
            return null;
        }

        Event event = null;
        try {
            event = eventPersistence.findByPrimaryKey(eventId);
            event.setTitle(title);
            String sanitizedDescription = FileUtilsLocalServiceUtil.sanitizeHTMLContent(description);
            event.setDescription(sanitizedDescription);
            event.setLocation(location);
            event.setStartDate(startDate);
            event.setEndDate(endDate);
            event = eventPersistence.updateImpl(event);
        } catch (Exception e) {
            logger.error("Error while updating event " + eventId, e);
        }

        // Delete and re-create populations
        eventPopulationPersistence.removeByeventId(eventId);
        for (int idx = 0 ; idx < populations.length() ; idx++) {
            JSONObject population = populations.getJSONObject(idx);
            EventPopulationLocalServiceUtil.addPopulation(eventId, population.getLong(JSONConstants.GROUP_ID), population.getLong(JSONConstants.ROLE_ID));
        }

        // Mobile push
        if (markAsUnread && event != null) {
            manageMobilePush(title, location, description, populations, event.getEventId());
        }

        return event;
    }

    @Indexable(type = IndexableType.DELETE)
    public Event deleteEventWithDependencies(long eventId) throws SystemException, NoSuchEventException {
        eventPersistence.remove(eventId);

        eventReadPersistence.removeByeventId(eventId);
        eventPopulationPersistence.removeByeventId(eventId);

        return null;
    }

    public List<Event> getUserEvents(User user, Date minDate, int nbEvents, boolean unreadOnly) throws SystemException {
        // Get user groups
        List<Long> groupIds = UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId());
        // Add root org
        groupIds.add(OrgUtilsLocalServiceUtil.getOrCreateRootOrg(PortalUtil.getDefaultCompanyId()).getGroupId());

        // Get user role ids
        List<Long> roleIds = new ArrayList<>();
        List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
        for (Role role : roles) {
            roleIds.add(role.getRoleId());
        }
        // For communities and schools
        roleIds.add((long) 0);

        return eventFinder.getUserEvents(user.getUserId(), minDate, nbEvents, groupIds, roleIds, unreadOnly);
    }

    // Used for directors + collectivity admins + directors that need to see all school's events
    public List<Event> getSchoolEvents(User user, Date minDate, int nbEvents, boolean unreadOnly) throws SystemException {

        List<Long> schoolIds = new ArrayList<>();
        List<Organization> schools = new ArrayList<>();
        if (RoleUtilsLocalServiceUtil.isDirectionMember(user) || NewsAdminLocalServiceUtil.isUserDelegate(user)) {
            schools = UserOrgsLocalServiceUtil.getUserSchools(user);
        } else if (RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            schools = OrgUtilsLocalServiceUtil.getAllSchools();
        }
        for (Organization school : schools) {
            schoolIds.add(school.getOrganizationId());
        }
        return eventFinder.getSchoolEvents(user.getUserId(), minDate, nbEvents, schoolIds, unreadOnly);
    }

    public int countEvents(User user, Date minDate, boolean unreadOnly) throws SystemException {
        // Get user groups
        List<Long> groupIds = UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId());
        // Add root org
        groupIds.add(OrgUtilsLocalServiceUtil.getOrCreateRootOrg(PortalUtil.getDefaultCompanyId()).getGroupId());

        // Get user role ids
        List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
        List<Long> roleIds = new ArrayList<>();
        for (Role role : roles) {
            roleIds.add(role.getRoleId());
        }
        // For communities and one role school level orgs (Enseignants, Personnels...)
        roleIds.add((long) 0);

        return eventFinder.countEvents(user.getUserId(), minDate, groupIds, roleIds, unreadOnly);
    }

    // Used for directors + collectivity admins + directors that need to see all school's events
    public int countSchoolEvents (User user, Date minDate, boolean unreadOnly) throws SystemException {

        List<Long> schoolIds = new ArrayList<>();
        List<Organization> schools = new ArrayList<>();
        if (RoleUtilsLocalServiceUtil.isDirectionMember(user) || NewsAdminLocalServiceUtil.isUserDelegate(user)) {
            schools = UserOrgsLocalServiceUtil.getUserSchools(user);
        } else if (RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            schools = OrgUtilsLocalServiceUtil.getAllSchools();
        }
        for (Organization school : schools) {
            schoolIds.add(school.getOrganizationId());
        }

        return eventFinder.countSchoolEvents(user.getUserId(), minDate, schoolIds, unreadOnly);
    }

    public boolean hasUserEvent(long userId, long eventId) {
        try {
            // Return true if the user is the author
            Event event = EventLocalServiceUtil.getEvent(eventId);
            if (event.getAuthorId() == userId) {
                return true;
            }

            // Admins can read all events
            User user = UserLocalServiceUtil.getUser(userId);
            if (RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) || RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isAdministrator(user) || NewsAdminLocalServiceUtil.isUserDelegate(user)) {
                return true;
            }

            // Get user group ids
            List<Long> userGroupIds = UserUtilsLocalServiceUtil.getUserGroupIds(userId);

            // Get user role ids
            List<Role> roles = RoleLocalServiceUtil.getUserRoles(userId);
            List<Long> roleIds = new ArrayList<>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
            }

            // Loop over populations and match both groupId and roleId
            List<EventPopulation> eventPopulations = eventPopulationPersistence.findByeventId(eventId);
            if (eventPopulations != null) {
                for (EventPopulation eventPopulation : eventPopulations) {
                    if ((eventPopulation.getRoleId() == 0 || roleIds.contains(eventPopulation.getRoleId())) && userGroupIds.contains(eventPopulation.getGroupId())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error checking if user " + userId + " has the view on eventId " + eventId, e);
        }

        return false;
    }

    public JSONObject convertEventToJson (long userId, long eventId, boolean withDetails) {
        JSONObject jsonEvent = new JSONObject();

        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            Event event = EventLocalServiceUtil.getEvent(eventId);
            jsonEvent.put(JSONConstants.EVENT_ID, event.getEventId());
            jsonEvent.put(JSONConstants.TITLE, event.getTitle());
            jsonEvent.put(JSONConstants.LOCATION, event.getLocation());
            jsonEvent.put(JSONConstants.AUTHOR_ID, event.getAuthorId());
            try {
                User author = UserLocalServiceUtil.getUser(event.getAuthorId());
                jsonEvent.put(JSONConstants.AUTHOR_NAME, author.getFullName());
            } catch (Exception e) {
                jsonEvent.put(JSONConstants.AUTHOR_NAME, "Utilisateur supprimé");
            }
            jsonEvent.put(JSONConstants.START_DATE, sdf.format(event.getStartDate()));
            jsonEvent.put(JSONConstants.END_DATE, sdf.format(event.getEndDate()));
            jsonEvent.put(JSONConstants.HAS_READ, EventReadLocalServiceUtil.hasUserReadEvent(userId, eventId));

            // The directors, delegates and author can edit the events
            // Only the author can edit an event
            User user = UserLocalServiceUtil.getUser(userId);
            boolean isEditable = event.getAuthorId() == userId ||
                    NewsAdminLocalServiceUtil.isUserDelegate(user) ||
                    RoleUtilsLocalServiceUtil.isDirectionMember(user);
            jsonEvent.put(JSONConstants.IS_EDITABLE, isEditable);

            // Only the author can delete the news
            jsonEvent.put(JSONConstants.IS_DELETABLE, event.getAuthorId() == userId);

            if (withDetails) {
                jsonEvent.put(JSONConstants.DESCRIPTION, event.getDescription());
                jsonEvent.put(JSONConstants.POPULATIONS, EventPopulationLocalServiceUtil.convertEventPopulations(eventId, userId));
                if (event.getAuthorId() == userId || RoleUtilsLocalServiceUtil.isDirectionMember(user) || NewsAdminLocalServiceUtil.isUserDelegate(user)) {
                    jsonEvent.put(JSONConstants.READ_INFOS, EventReadLocalServiceUtil.getEventReadStatus(eventId, userId));
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching agenda event " + eventId, e);
        }

        return jsonEvent;
    }

    private void manageMobilePush(String title, String location, String content, JSONArray populations, long eventId) {

        try {
            for (int idx = 0; idx < populations.length(); idx++) {
                JSONObject population = populations.getJSONObject(idx);
                // Get all population members
                Group group = GroupLocalServiceUtil.getGroup(population.getLong(JSONConstants.GROUP_ID));
                List<User> groupMembers;
                if (group.isRegularSite()) {
                    groupMembers = UserLocalServiceUtil.getGroupUsers(group.getGroupId());
                } else {
                    List<Long> orgIds = new ArrayList<>();
                    orgIds.add(group.getClassPK());
                    List<Long> roleIds = new ArrayList<>();
                    if (population.getLong(JSONConstants.ROLE_ID) != 0) {
                        roleIds.add(population.getLong(JSONConstants.ROLE_ID));
                    }
                    groupMembers = UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
                }

                for (User member : groupMembers) {

                    MobileDeviceLocalServiceUtil.pushNotificationToUser(member.getUserId(), title, location, content,
                            MobileConstants.EVENT_TYPE, eventId);
                }
            }
        } catch (Exception e) {
            logger.info("Error pushing mobile notification for created announcement", e);
        }
    }

}
