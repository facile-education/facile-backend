package com.weprode.nero.school.life.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.SchoollifeSlotLocalServiceUtil;
import com.weprode.nero.school.life.service.base.SchoollifeSlotServiceBaseImpl;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=schoollife",
                "json.web.service.context.path=SchoollifeSlot"
        },
        service = AopService.class
)
public class SchoollifeSlotServiceImpl extends SchoollifeSlotServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(SchoollifeSlotServiceImpl.class);

    @JSONWebService(value = "create-slot", method = "GET")
    public JSONObject createSlot(long schoolId, String startDateStr, String endDateStr, int day, String startHour, String endHour, long teacherId, int type, String room, int capacity) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            Date startDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDateStr);
            Date endDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(endDateStr);
            SchoollifeSlotLocalServiceUtil.addSlot(schoolId, startDate, endDate, day, startHour, endHour, teacherId, type, room, capacity);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error creating schoollife slot for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "edit-slot", method = "GET")
    public JSONObject editSlot(long schoollifeSessionId, String startDateStr, String endDateStr, int newDay, String newStartHour, String newEndHour, long newTeacherId, String newRoom, int newCapacity) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            Date startDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDateStr);
            Date endDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(endDateStr);
            long schoollifeSlotId = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId).getSchoollifeSlotId();
            SchoollifeSlotLocalServiceUtil.editSlot(schoollifeSlotId, startDate, endDate, newDay, newStartHour, newEndHour, newTeacherId, newRoom, newCapacity);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error editing schoollife slot linked to session " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-slot", method = "GET")
    public JSONObject deleteSlot(long schoollifeSessionId, String startDateStr, String endDateStr) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            Date startDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDateStr);
            Date endDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(endDateStr);
            long schoollifeSlotId = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId).getSchoollifeSlotId();

            boolean success = SchoollifeSlotLocalServiceUtil.deleteSlot(schoollifeSlotId, startDate, endDate);
            result.put(JSONConstants.SUCCESS, success);
        } catch (Exception e) {
            logger.error("Error deleting schoollife slot linked to session " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-session-limit-slot-date", method = "GET")
    public JSONObject getSessionLimitSlotDate(long schoollifeSessionId) {
        JSONObject result = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

        try {
            long schoollifeSlotId = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId).getSchoollifeSlotId();
            SchoollifeSession lastSlotSession = SchoollifeSessionLocalServiceUtil.getLastSession(schoollifeSlotId);
            result.put(JSONConstants.LAST_SESSION_DATE, df.format(lastSlotSession.getStartDate()));
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error deleting schoollife slot linked to session " + schoollifeSessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
