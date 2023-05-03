package com.weprode.nero.schedule.utils;

import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.Homework;

import java.util.List;

public class JSONProxy {

    private JSONProxy() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(JSONProxy.class);

    /**
     * Convert sessions in a JSONArray
     */
    public static JSONArray convertSessionsToJson(List<CDTSession> sessions, User user, long colorsTeacherId) {

        JSONArray sessionsArray = new JSONArray();
        for (CDTSession session : sessions) {
            try {
                sessionsArray.put(session.convertToJSON(colorsTeacherId, user));
            } catch (Exception e) {
                logger.error("Error when fetching CDT session for user "+user.getFullName(), e);
            }
        }

        return sessionsArray;
    }

    public static JSONArray convertUsersToJson(List<User> userList) {

        JSONArray userListAsJSON = new JSONArray();
        for (User aUser : userList) {
            JSONObject aUserAsJson = convertUserToJson(aUser);
            userListAsJSON.put(aUserAsJson);
        }

        return userListAsJSON;
    }

    public static JSONObject convertUserToJson(User user) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put(JSONConstants.USER_ID, user.getUserId());
        jsonUser.put(JSONConstants.FIRST_NAME, user.getFirstName());
        jsonUser.put(JSONConstants.LAST_NAME, user.getLastName());
        jsonUser.put(JSONConstants.FULL_NAME, user.getLastName() + " " + user.getFirstName());
        return jsonUser;
    }

    public static JSONArray convertSessionsToSimpleJson(List<CDTSession> sessions) {

        JSONArray sessionsArray = new JSONArray();
        for (CDTSession session : sessions) {
            sessionsArray.put(session.convertToJSON());
        }
        return sessionsArray;
    }

    /**
     * Build JSON for ajax call error case
     */
    public static JSONObject getJSONReturnInErrorCase(String errorMessage){
        JSONObject errorReturn = new JSONObject();
        errorReturn.put(JSONConstants.ERROR,errorMessage);
        errorReturn.put(JSONConstants.SUCCESS,false);
        return errorReturn;
    }


    /**
     * Generates a Json Array that describes a list of attach file
     */
    // TODO Attachments
    /*public static JSONArray convertAttachFiles (User studentUser, List<AttachFile> attachFileList) {
        return convertAttachFiles(studentUser, attachFileList, false);
    }

    public static JSONArray convertAttachFiles (User studentUser, List<AttachFile> attachFileList, boolean isExercice) {
        JSONArray fileDescriptor = new JSONArray();
        for (AttachFile attachFile : attachFileList) {
            if (attachFile.getIsEditable() == isExercice) {
                fileDescriptor.put(attachFile.convertToJSON(studentUser));
            }
        }

        return fileDescriptor;
    }*/

    /**
     * Generates a Json array that describes a list of homework for a same session
     */
    public static JSONArray convertHomeworksAsJson (User user, List<Homework> homeworkList) {

        JSONArray homeworkArray = new JSONArray();

        for (Homework homework : homeworkList) {
            homeworkArray.put(homework.convertToJSON(user));
        }
        return homeworkArray;
    }

    /**
     * Convernt the list of attach file Id  in long array
     * @param attachFiles the list aof file id
     * @return fild ids as long array
     */
    public static Long[] decodeFileIdFromJSONArray(JSONArray attachFiles) {
        Long[] fileIds = new Long[attachFiles.length()];
        for (int i=0; i<attachFiles.length(); i++) {
            if (attachFiles.getLong(i) > 0) {
                fileIds[i] = attachFiles.getLong(i);
            } else {
                fileIds[i] = JSONConstants.getLongValue(attachFiles.getJSONObject(i), JSONConstants.ID, 0);
            }
        }
        return fileIds;
    }
    
}
