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
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.agenda.model.Event;
import com.weprode.facile.agenda.service.EventLocalServiceUtil;
import com.weprode.facile.agenda.service.EventReadLocalServiceUtil;
import com.weprode.facile.agenda.service.base.AgendaServiceBaseImpl;
import com.weprode.facile.commons.FacileLogger;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.NewsAdminLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=agenda",
                "json.web.service.context.path=Agenda"
        },
        service = AopService.class
)
public class AgendaServiceImpl extends AgendaServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(AgendaServiceImpl.class);

    @JSONWebService(value = "get-events", method = "GET")
    public JSONObject getEvents(String minDateStr, int startIndex, int nbEvents, boolean unreadOnly) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            Date minDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(minDateStr);
            List<Event> events;
            if (RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) || NewsAdminLocalServiceUtil.isUserDelegate(user) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                events = EventLocalServiceUtil.getSchoolEvents(user, minDate, startIndex, nbEvents, unreadOnly);
            } else {
                events = EventLocalServiceUtil.getUserEvents(user, minDate, startIndex, nbEvents, unreadOnly);
            }

            JSONArray jsonEvents = new JSONArray();
            for (Event event : events) {
                JSONObject jsonEvent = EventLocalServiceUtil.convertEventToJson(user.getUserId(), event.getEventId(), false);
                jsonEvents.put(jsonEvent);
            }
            result.put(JSONConstants.EVENTS, jsonEvents);
            if (RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) || NewsAdminLocalServiceUtil.isUserDelegate(user) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                result.put(JSONConstants.NB_UNREAD_EVENTS, EventLocalServiceUtil.countSchoolEvents(user, new Date(), true));
            } else {
                result.put(JSONConstants.NB_UNREAD_EVENTS, EventLocalServiceUtil.countEvents(user, new Date(), true));
            }
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while fetching groups activity for user " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-event-details", method = "GET")
    public JSONObject getEventDetails(long eventId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check if the user can read the event
            if (!EventLocalServiceUtil.hasUserEvent(user.getUserId(), eventId)) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " read eventId " + eventId);
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            result = EventLocalServiceUtil.convertEventToJson(user.getUserId(), eventId, true);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while fetching groups activity for user " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "create-event", method = "POST")
    public JSONObject createEvent(String title, String description, String location, String startDate, String endDate, String populations) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) && !NewsAdminLocalServiceUtil.isUserDelegate(user) && !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " creates an event");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            DateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            Date start = df.parse(startDate);
            Date end = df.parse(endDate);
            JSONArray jsonPopulations = new JSONArray(populations);
            Event createdEvent = EventLocalServiceUtil.createEvent(user.getUserId(), title, description, location, start, end, jsonPopulations);

            if (createdEvent != null) {
                JSONObject jsonEvent = EventLocalServiceUtil.convertEventToJson(user.getUserId(), createdEvent.getEventId(), false);
                result.put(JSONConstants.CREATED_EVENT, jsonEvent);
                result.put(JSONConstants.SUCCESS, true);
            } else {
                result.put(JSONConstants.SUCCESS, false);
            }
        } catch (Exception e) {
            logger.error("Error while creating event with title " + title, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "modify-event", method = "POST")
    public JSONObject modifyEvent(long eventId, String title, String description, String location, String startDate, String endDate, String populations, boolean markAsUnreadForAll) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Only the event's creator or a direction member or a delegate is allowed to modify it
            Event event = EventLocalServiceUtil.getEvent(eventId);
            if (event.getAuthorId() != user.getUserId() && !RoleUtilsLocalServiceUtil.isDirectionMember(user) && !NewsAdminLocalServiceUtil.isUserDelegate(user) && !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " modifies an event");
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }

            DateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            Date start = df.parse(startDate);
            Date end = df.parse(endDate);
            JSONArray jsonPopulations = new JSONArray(populations);
            Event modifiedEvent = EventLocalServiceUtil.modifyEvent(eventId, title, description, location, start, end, jsonPopulations, markAsUnreadForAll);
            if (modifiedEvent != null) {
                if (markAsUnreadForAll) {
                    EventReadLocalServiceUtil.markEventAsUnReadForAll(eventId);
                }
                JSONObject jsonEvent = EventLocalServiceUtil.convertEventToJson(user.getUserId(), modifiedEvent.getEventId(), false);
                result.put(JSONConstants.CREATED_EVENT, jsonEvent);
                result.put(JSONConstants.SUCCESS, true);
            } else {
                result.put(JSONConstants.SUCCESS, false);
            }

        } catch (Exception e) {
            logger.error("Error while updating event " + eventId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-event", method = "GET")
    public JSONObject deleteEvent(long eventId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Only the event's creator is allowed to delete it
            Event event = EventLocalServiceUtil.getEvent(eventId);
            if (event.getAuthorId() != user.getUserId()) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes an event");
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }

            EventLocalServiceUtil.deleteEventWithDependencies(eventId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error while deleting event " + eventId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "set-event-read", method = "POST")
    public JSONObject setEventRead (long eventId, boolean read) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check if the user can read the event
            if (!EventLocalServiceUtil.hasUserEvent(user.getUserId(), eventId)) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " marks an event as read");
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            if (read) {
                result.put(JSONConstants.SUCCESS, EventReadLocalServiceUtil.markEventAsRead(user.getUserId(), eventId));
            } else  {
                result.put(JSONConstants.SUCCESS, EventReadLocalServiceUtil.markEventAsUnRead(user.getUserId(), eventId));
            }
        } catch (Exception e) {
            logger.error("Error while setting event " + eventId + " as read/unread", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
