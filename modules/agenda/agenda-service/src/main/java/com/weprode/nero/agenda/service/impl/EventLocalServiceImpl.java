package com.weprode.nero.agenda.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.agenda.exception.NoSuchEventException;
import com.weprode.nero.agenda.model.Event;
import com.weprode.nero.agenda.service.EventLocalServiceUtil;
import com.weprode.nero.agenda.service.EventPopulationLocalServiceUtil;
import com.weprode.nero.agenda.service.EventReadLocalServiceUtil;
import com.weprode.nero.agenda.service.base.EventLocalServiceBaseImpl;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
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
        property = "model.class.name=com.weprode.nero.agenda.model.Event",
        service = AopService.class
)
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(EventLocalServiceImpl.class);

    public Event createEvent(long authorId, String title, String description, String location, Date startDate, Date endDate, JSONArray populations) throws SystemException {
        // If mandatory fields are not set, return null
        if (title.equals("") || startDate == null || endDate == null || populations.length() == 0) {
            logger.info("Agenda event could not be created because missing mandatory field");
            return null;
        }

        Event event = eventPersistence.create(counterLocalService.increment());
        event.setAuthorId(authorId);
        event.setTitle(title);
        event.setDescription(description);
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

        return event;
    }

    public Event modifyEvent(long eventId, String title, String description, String location, Date startDate, Date endDate, JSONArray populations) throws SystemException {
        // If mandatory fields are not set, return null
        if (title.equals("") || startDate == null || endDate == null || populations.length() == 0) {
            logger.info("Agenda event could not be modified because missing mandatory field");
            return null;
        }

        Event event = null;
        try {
            event = eventPersistence.findByPrimaryKey(eventId);
            event.setTitle(title);
            event.setDescription(description);
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

        return event;
    }

    public Event deleteEventWithDependencies(long eventId) throws SystemException, NoSuchEventException {
        eventPersistence.remove(eventId);

        eventReadPersistence.removeByeventId(eventId);
        eventPopulationPersistence.removeByeventId(eventId);

        return null;
    }

    public List<Event> getUserEvents(User user, int startIndex, int nbEvents, boolean unreadOnly) throws SystemException {
        // Get user groups
        List<Long> groupIds = CommunityInfosLocalServiceUtil.getUserGroupIds(user.getUserId());

        // Get user role ids
        List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
        List<Long> roleIds = new ArrayList<>();
        for (Role role : roles) {
            roleIds.add(role.getRoleId());
        }
        // For communities and one role school level orgs (Enseignants, Personnels...)
        roleIds.add((long) 0);

        return eventFinder.getUserEvents(user.getUserId(), startIndex, nbEvents, groupIds, roleIds, unreadOnly);
    }

    public int countEvents(User user, Date minDate, boolean unreadOnly) throws SystemException {
        // Get user groups
        List<Long> groupIds = CommunityInfosLocalServiceUtil.getUserGroupIds(user.getUserId());

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

    public JSONObject convertEventToJson (long userId, long eventId, boolean withDetails) {
        JSONObject jsonEvent = new JSONObject();

        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");

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
            // Author and direction can edit/delete the event
            User user = UserLocalServiceUtil.getUser(userId);
            jsonEvent.put(JSONConstants.IS_EDITABLE, event.getAuthorId() == userId || RoleUtilsLocalServiceUtil.isDirectionMember(user));
            if (withDetails) {
                jsonEvent.put(JSONConstants.DESCRIPTION, event.getDescription());
                jsonEvent.put(JSONConstants.POPULATIONS, EventPopulationLocalServiceUtil.convertEventPopulations(eventId, userId));
                if (event.getAuthorId() == userId) {
                    jsonEvent.put(JSONConstants.READ_INFOS, EventReadLocalServiceUtil.getEventReadStatus(eventId, userId));
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching agenda event " + eventId, e);
        }

        return jsonEvent;
    }
}