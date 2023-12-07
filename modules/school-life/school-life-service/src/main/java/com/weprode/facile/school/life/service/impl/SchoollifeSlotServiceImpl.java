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

package com.weprode.facile.school.life.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.FacileLogger;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.school.life.model.SchoollifeSession;
import com.weprode.facile.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.facile.school.life.service.SchoollifeSlotLocalServiceUtil;
import com.weprode.facile.school.life.service.base.SchoollifeSlotServiceBaseImpl;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " creates a slot");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            Date startDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDateStr);
            Date endDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(endDateStr);
            // Set 23:00 to endDate so that endDate is included
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            endDate = cal.getTime();
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
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " edits slot " + schoollifeSessionId);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            Date startDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDateStr);
            Date endDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(endDateStr);
            // Set 23:00 to endDate so that endDate is included
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            endDate = cal.getTime();
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
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isDoyen(user) &&
                !RoleUtilsLocalServiceUtil.isSecretariat(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes slot " + schoollifeSessionId);
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
            User user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
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
