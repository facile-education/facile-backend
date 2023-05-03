package com.weprode.nero.schedule.utils;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.Homework;
import com.weprode.nero.schedule.service.HomeworkLocalServiceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeworkUtil {

    private HomeworkUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This method copy all homework in JSON to targetSession
     */
    public static void copyHomeworksFromSessionToAnother(JSONArray homeworksAsJSON, CDTSession targetSession) throws PortalException, SystemException, ParseException {

        for (int i = 0; i < homeworksAsJSON.length() ; i++) {

            JSONObject homeworkAsJSON = homeworksAsJSON.getJSONObject(i);
            long homeworkSourceId = homeworkAsJSON.getLong(JSONConstants.HOMEWORK_ID);
            Homework homeworkToCopy = HomeworkLocalServiceUtil.getHomework(homeworkSourceId);
            Date copiedHomeworkToDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(homeworkAsJSON.getString("toDate"));
            // get target week
            Calendar cal = Calendar.getInstance();
            cal.setTime(copiedHomeworkToDate);
            int weekId = cal.get(Calendar.WEEK_OF_YEAR);

            long homeworkTargetId = CounterLocalServiceUtil.increment();
            Homework copiedHomework = HomeworkLocalServiceUtil.createHomework(homeworkTargetId);
            copiedHomework.setDescription(homeworkToCopy.getDescription());
            copiedHomework.setType(homeworkToCopy.getType());
            copiedHomework.setEstimatedTime(homeworkToCopy.getEstimatedTime());
            copiedHomework.setFromDate(targetSession.getSessionStart());
            copiedHomework.setSourceSessionId(targetSession.getSessionId());
            copiedHomework.setTargetDate(copiedHomeworkToDate);
            copiedHomework.setTargetWeekId(weekId);
            copiedHomework.setTargetSessionId(JSONConstants.getLongValue(homeworkAsJSON, JSONConstants.TARGET_SESSION_ID, 0));
            copiedHomework.setGroupId(targetSession.getGroupId());
            HomeworkLocalServiceUtil.updateHomework(copiedHomework);

            // Copy attach files and audio instructions
            // TODO Attachments
            // AttachFileLocalServiceUtil.copyHomework(homeworkSourceId, homeworkTargetId);
        }
    }
}
