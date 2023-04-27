package com.weprode.nero.school.life.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.model.SchoollifeSlot;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.SchoollifeSlotLocalServiceUtil;
import com.weprode.nero.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.nero.school.life.service.base.SchoollifeSessionServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=schoollife",
                "json.web.service.context.path=SchoollifeSession"
        },
        service = AopService.class
)
public class SchoollifeSessionServiceImpl extends SchoollifeSessionServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(SchoollifeSessionServiceImpl.class);

    @JSONWebService(value = "get-week-sessions", method = "GET")
    public JSONObject getWeekSessions(long schoolId, int type, String currentDateStr) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        logger.info("Get schoollife week sessions for schoolId = " + schoolId  + ", type = " + type + " and date " + currentDateStr);
        JSONArray jsonSessions = JSONFactoryUtil.createJSONArray();

        try {
            SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            Date currentDate = df.parse(currentDateStr);
            List<SchoollifeSession> sessions = SchoollifeSessionLocalServiceUtil.getWeekSessions(schoolId, type, currentDate);

            for (SchoollifeSession session : sessions) {
                SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

                JSONObject jsonSession = JSONFactoryUtil.createJSONObject();
                jsonSession.put(JSONConstants.SCHOOLLIFE_SESSION_ID, session.getSchoollifeSessionId());
                jsonSession.put(JSONConstants.START_DATE, df.format(session.getStartDate()));
                jsonSession.put(JSONConstants.END_DATE, df.format(session.getEndDate()));
                jsonSession.put(JSONConstants.ROOM, slot.getRoom());
                jsonSession.put(JSONConstants.TYPE, session.getType());

                User teacher = UserLocalServiceUtil.getUser(slot.getTeacherId());
                JSONObject jsonTeacher = JSONFactoryUtil.createJSONObject();
                jsonTeacher.put(JSONConstants.TEACHER_ID, teacher.getUserId());
                jsonTeacher.put(JSONConstants.FIRST_NAME, teacher.getFirstName());
                jsonTeacher.put(JSONConstants.LAST_NAME, teacher.getLastName());
                jsonSession.put(JSONConstants.TEACHER, jsonTeacher);

                int nbRegisteredStudents = SessionStudentLocalServiceUtil.getNbRegisteredStudents(session.getSchoollifeSessionId());
                jsonSession.put(JSONConstants.CAPACITY, slot.getCapacity());
                jsonSession.put(JSONConstants.NB_REGISTERED_STUDENTS, nbRegisteredStudents);
                jsonSessions.put(jsonSession);
            }

            result.put(JSONConstants.SESSIONS, jsonSessions);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error fetching week sessions of type " + type + " and currentDate " + currentDateStr, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }
}
