package com.weprode.nero.agenda.service.impl;

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
import com.weprode.nero.agenda.exception.NoSuchEventReadException;
import com.weprode.nero.agenda.model.Event;
import com.weprode.nero.agenda.model.EventPopulation;
import com.weprode.nero.agenda.model.EventRead;
import com.weprode.nero.agenda.service.EventLocalServiceUtil;
import com.weprode.nero.agenda.service.EventPopulationLocalServiceUtil;
import com.weprode.nero.agenda.service.EventReadLocalServiceUtil;
import com.weprode.nero.agenda.service.base.EventReadLocalServiceBaseImpl;
import com.weprode.nero.agenda.service.persistence.EventReadPK;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.contact.service.ContactLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.agenda.model.EventRead",
        service = AopService.class
)
public class EventReadLocalServiceImpl extends EventReadLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(EventReadLocalServiceImpl.class);

    public boolean markEventAsRead(long userId, long eventId) {
        try {
            // Check event existence
            EventLocalServiceUtil.getEvent(eventId);
            
            // Mark it as read
            try {
                eventReadPersistence.findByPrimaryKey(new EventReadPK(eventId, userId));
            } catch (NoSuchEventReadException e) {
                EventRead er = eventReadPersistence.create(new EventReadPK(eventId, userId));
                er.setReadDate(new Date());
                EventReadLocalServiceUtil.updateEventRead(er);
                logger.info("Mark event " + eventId + " as read by userId " + userId);
            }
            
            return true;
        } catch (Exception e) {
            logger.error("Error marking user " + userId + " has read eventId " + eventId, e);
            return false;
        }
    }

    public boolean markEventAsUnRead(long userId, long eventId) {
        try {
            EventReadPK eventReadPK = new EventReadPK(eventId, userId);
            EventReadLocalServiceUtil.deleteEventRead(eventReadPK);
            
            logger.info("Mark event " + eventId + " as unRead by userId " + userId);
            return true;
        } catch (Exception e) {
            logger.error("Error marking user " + userId + " has unRead eventId " + eventId, e);
        }
        
        return false;
    }

    public boolean markEventAsUnReadForAll(long eventId) {
        try {
            Event event = EventLocalServiceUtil.getEvent(eventId);
            List<EventRead> eventReadList = eventReadPersistence.findByeventId(eventId);
            for (EventRead er : eventReadList) {
                if (er.getUserId() != event.getAuthorId()) {
                    EventReadLocalServiceUtil.deleteEventRead(er);
                }
            }
            
            logger.info("Mark event " + eventId + " as unRead for all");
            return true;
        } catch (Exception e) {
            logger.error("Error marking user eventId " + eventId + " as unread for all", e);
        }
        
        return false;
    }

    public boolean hasUserReadEvent(long userId, long eventId) {
        try {
            eventReadPersistence.findByPrimaryKey(new EventReadPK(eventId, userId));
            
            return true;
        } catch (NoSuchEventReadException e) {
            return false;
        } catch (Exception e) {
            logger.error("Error fetching if user " + userId + " has read eventId " + eventId, e);
            return false;
        }
    }

    public EventRead getUserReadEvent(long userId, long eventId) {
        try {
            return eventReadPersistence.findByPrimaryKey(new EventReadPK(eventId, userId));
        } catch (Exception e) {
            logger.error("Error fetching if user " + userId + " has read eventId " + eventId, e);
        }
        
        return null;
    }
    
    public JSONArray getEventReadStatus(long eventId, long userId) throws SystemException, PortalException {
        JSONArray jsonReadStatus = new JSONArray();
        
        // Loop over populations
        List<EventPopulation> populations = EventPopulationLocalServiceUtil.getEventPopulations(eventId);
        if (populations != null) {
            for (EventPopulation population : populations) {
                JSONObject jsonPopulation = new JSONObject();
                Group group = GroupLocalServiceUtil.getGroup(population.getGroupId());
                jsonPopulation.put(JSONConstants.POPULATION_NAME, ContactLocalServiceUtil.getPopulationName(group.getClassPK(), population.getRoleId(), userId));

                // Loop over population members
                JSONArray jsonMembers = new JSONArray();
                List<User> groupMembers;
                if (group.isRegularSite()) {
                    groupMembers = UserLocalServiceUtil.getGroupUsers(group.getGroupId());
                } else {
                    List<Long> orgIds = new ArrayList<>();
                    orgIds.add(group.getClassPK());
                    List<Long> roleIds = new ArrayList<>();
                    roleIds.add(population.getRoleId());
                    groupMembers = UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
                }

                for (User member : groupMembers) {
                    JSONObject jsonMember = new JSONObject();
                    jsonMember.put(JSONConstants.USER_ID, member.getUserId());
                    jsonMember.put(JSONConstants.LAST_NAME, member.getLastName());
                    jsonMember.put(JSONConstants.FIRST_NAME, member.getFirstName());

                    try {
                        EventRead eventRead = eventReadPersistence.fetchByPrimaryKey(new EventReadPK(eventId, member.getUserId()));
                        jsonMember.put(JSONConstants.HAS_READ, true);
                        jsonMember.put(JSONConstants.READ_DATE, eventRead.getReadDate());
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
}
