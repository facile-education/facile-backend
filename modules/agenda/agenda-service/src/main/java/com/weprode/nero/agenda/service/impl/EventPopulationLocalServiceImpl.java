package com.weprode.nero.agenda.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.weprode.nero.agenda.model.EventPopulation;
import com.weprode.nero.agenda.service.base.EventPopulationLocalServiceBaseImpl;
import com.weprode.nero.agenda.service.persistence.EventPopulationPK;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.contact.service.ContactLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.agenda.model.EventPopulation",
        service = AopService.class
)
public class EventPopulationLocalServiceImpl extends EventPopulationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(EventPopulationLocalServiceImpl.class);

    public EventPopulation addPopulation(long eventId, long groupId, long roleId) throws SystemException {
        EventPopulationPK eventPopulationPK = new EventPopulationPK(eventId, groupId, roleId);
        
        EventPopulation population = eventPopulationPersistence.create(eventPopulationPK);
        
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
