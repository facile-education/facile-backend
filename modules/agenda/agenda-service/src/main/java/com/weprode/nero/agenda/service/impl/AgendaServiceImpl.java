package com.weprode.nero.agenda.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.agenda.model.Event;
import com.weprode.nero.agenda.service.EventLocalServiceUtil;
import com.weprode.nero.agenda.service.EventReadLocalServiceUtil;
import com.weprode.nero.agenda.service.base.AgendaServiceBaseImpl;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.NewsAdminLocalServiceUtil;
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
    public JSONObject getEvents(int startIndex, int nbEvents, boolean unreadOnly) {
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

        try {
            List<Event> events = EventLocalServiceUtil.getUserEvents(user, startIndex, nbEvents, unreadOnly);

            JSONArray jsonEvents = new JSONArray();
            for (Event event : events) {
                JSONObject jsonEvent = EventLocalServiceUtil.convertEventToJson(user.getUserId(), event.getEventId(), false);
                jsonEvents.put(jsonEvent);
            }
            result.put(JSONConstants.EVENTS, jsonEvents);
            result.put(JSONConstants.NB_UNREAD_EVENTS, EventLocalServiceUtil.countEvents(user, new Date(), true));
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
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
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
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) && !NewsAdminLocalServiceUtil.isUserDelegate(user)) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
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
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            // Only the event's creator is allowed to modify it
            Event event = EventLocalServiceUtil.getEvent(eventId);
            if (event.getAuthorId() != user.getUserId()) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            DateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            Date start = df.parse(startDate);
            Date end = df.parse(endDate);
            JSONArray jsonPopulations = new JSONArray(populations);
            Event modifiedEvent = EventLocalServiceUtil.modifyEvent(eventId, title, description, location, start, end, jsonPopulations);
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
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            // Only the event's creator is allowed to delete it
            Event event = EventLocalServiceUtil.getEvent(eventId);
            if (event.getAuthorId() != user.getUserId()) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
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
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            if (read) {
                result.put(JSONConstants.SUCCESS, EventReadLocalServiceUtil.markEventAsRead(user.getUserId(), eventId));
            } else  {
                result.put(JSONConstants.SUCCESS, EventReadLocalServiceUtil.markEventAsUnRead(user.getUserId(), eventId));
            }
        } catch (Exception e) {
            logger.error("Error while deleting event " + eventId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}