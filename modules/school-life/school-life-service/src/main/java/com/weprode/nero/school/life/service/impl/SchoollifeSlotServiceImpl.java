package com.weprode.nero.school.life.service.impl;

import com.liferay.portal.aop.AopService;

import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.SchoollifeSlotLocalServiceUtil;
import com.weprode.nero.school.life.service.base.SchoollifeSlotServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component(
        property = {
                "json.web.service.context.name=schoollife",
                "json.web.service.context.path=SchoollifeSlot"
        },
        service = AopService.class
)
public class SchoollifeSlotServiceImpl extends SchoollifeSlotServiceBaseImpl {
    // TODO: Role verification for restricted operations

    private final Log logger = LogFactoryUtil.getLog(SchoollifeSlotServiceImpl.class);

    @JSONWebService(value = "create-slot", method = "GET")
    public JSONObject createSlot(long schoolId, String startDateStr, int day, String startHour, String endHour, long teacherId, int type, String room, int capacity) {
        JSONObject result = new JSONObject();

        try {
            Date startDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDateStr);
            SchoollifeSlotLocalServiceUtil.addSlot(schoolId, startDate, day, startHour, endHour, teacherId, type, room, capacity);
            // TODO: return created slot?

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error creating schoollife slot for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "edit-slot", method = "GET")
    public JSONObject editSlot(long schoollifeSessionId, String startDateStr, int newDay, String newStartHour, String newEndHour, long newTeacherId, String newRoom, int newCapacity) {
        JSONObject result = new JSONObject();

        try {
            Date startDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDateStr);
            long schoollifeSlotId = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId).getSchoollifeSlotId();
            SchoollifeSlotLocalServiceUtil.editSlot(schoollifeSlotId, startDate, newDay, newStartHour, newEndHour, newTeacherId, newRoom, newCapacity);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error editing schoollife slot linked to session " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-slot", method = "GET")
    public JSONObject deleteSlot(long schoollifeSessionId, String startDateStr) {
        JSONObject result = new JSONObject();

        try {
            Date startDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDateStr);
            long schoollifeSlotId = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId).getSchoollifeSlotId();

            boolean success = SchoollifeSlotLocalServiceUtil.deleteSlot(schoollifeSlotId, startDate);
            result.put(JSONConstants.SUCCESS, success);
        } catch (Exception e) {
            logger.error("Error deleting schoollife slot linked to session " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
