package com.weprode.nero.progression.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.service.HomeworkLocalServiceUtil;
import com.weprode.nero.course.service.SessionContentLocalServiceUtil;
import com.weprode.nero.progression.model.ItemAssignment;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.service.ItemAssignmentLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.progression.service.base.ItemAssignmentServiceBaseImpl;
import com.weprode.nero.progression.service.persistence.ItemAssignmentPK;
import com.weprode.nero.progression.utils.ProgressionUtils;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=progression",
                "json.web.service.context.path=ItemAssignment"
        },
        service = AopService.class
)
public class ItemAssignmentServiceImpl extends ItemAssignmentServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ItemAssignmentServiceImpl.class);
    
    @JSONWebService(value = "add-session-assignment", method = "POST")
    public JSONObject addSessionAssignment(long itemId, long sessionId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId)) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            // Check that the user is a session's teacher
            if (!SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }

            logger.info("Teacher " + user.getFullName() + " adds assignment of item " + itemId + " to session " + sessionId);
            ItemAssignment assignment = ItemAssignmentLocalServiceUtil.assignSession(itemId, sessionId);

            // Push item content to session
            // TODO
            //result.put(JSONConstants.ASSIGNMENT, assignment.convertToJSON(user.getUserId()));
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Could not add assignment with itemId="+itemId+" and sessionId="+sessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "add-homework-assignment", method = "POST")
    public JSONObject addHomeworkAssignment(long itemId, String homeworks) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId)) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }

            JSONArray jsonHomeworks;
            try {
                jsonHomeworks = new JSONArray(homeworks);
            } catch (JSONException e) {
                logger.error("Error adding homework assignments : passed json is not well-formed:" + homeworks, e);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            JSONArray jsonAssignments = new JSONArray();

            // Parse the homeworks to create
            for (int i = 0 ; i < jsonHomeworks.length() ; i++) {
                JSONObject homeworkToCreate = jsonHomeworks.getJSONObject(i);
                logger.info("Parsing homework " + homeworkToCreate.toString());

                long homeworkId = JSONConstants.getLongValue(homeworkToCreate, JSONConstants.HOMEWORK_ID, 0);
                long sourceSessionId = homeworkToCreate.getLong(JSONConstants.SOURCE_SESSION_ID);

                // Check that the user is a source session's teacher
                if (!SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sourceSessionId)) {
                    return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
                }

                long targetSessionId = JSONConstants.getLongValue(homeworkToCreate, JSONConstants.TARGET_SESSION_ID, 0);

                // Target date is the targetSession date if any, else the toDate
                Date toDate = null;
                if (targetSessionId != 0) {
                    CDTSession targetSession = CDTSessionLocalServiceUtil.getCDTSession(targetSessionId);
                    toDate = targetSession.getStart();
                } else {
                    try {
                        toDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(homeworkToCreate.getString(JSONConstants.TO_DATE));
                    } catch (Exception e) {
                        logger.error("Error parsing toDate " + homeworkToCreate.getString(JSONConstants.TO_DATE) + " while creating homework", e);
                    }
                }
                int type = homeworkToCreate.getInt(JSONConstants.TYPE);

                // GroupId is the one from sourceSession
                long groupId = CDTSessionLocalServiceUtil.getCDTSession(sourceSessionId).getGroupId();

                // Students
                List<Long> studentIds = new ArrayList<>();
                JSONArray jsonStudents = homeworkToCreate.getJSONArray("selectedStudents");
                for (int j = 0 ; j < jsonStudents.length() ; j++) {
                    studentIds.add(jsonStudents.getJSONObject(j).getLong(JSONConstants.USER_ID));
                }

                if (homeworkId == 0) {
                    // This is creation
                    Homework homework = HomeworkLocalServiceUtil.createHomework(user, sourceSessionId, targetSessionId, groupId, toDate, type, 30, studentIds, new Date(), false);

                    logger.info("Teacher " + user.getFullName() + " assigns homework item " + itemId + " to session " + sourceSessionId + ". Created homeworkId is " + homework.getHomeworkId());
                    ItemAssignment assignment = ItemAssignmentLocalServiceUtil.assignHomework(itemId, sourceSessionId, homework.getHomeworkId());
                    jsonAssignments.put(assignment.convertToJSON(user.getUserId()));

                    // Propagate description + files
                    // TODO

                } else {
                    // This is update
                    logger.info("Teacher " + user.getFullName() + " updates assignment for homework item " + itemId + " to session " + sourceSessionId);
                    HomeworkLocalServiceUtil.updateHomework(homeworkId, targetSessionId, toDate, 30, studentIds, new Date(), false);
                    ItemAssignmentPK pk = new ItemAssignmentPK(itemId, sourceSessionId);
                    ItemAssignment assignment = ItemAssignmentLocalServiceUtil.getItemAssignment(pk);
                    jsonAssignments.put(assignment.convertToJSON(user.getUserId()));
                }
            }
            //result.put(JSONConstants.ASSIGNMENTS, jsonAssignments);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Could not add homeworks assignment with itemId = " + itemId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-assignment", method = "DELETE")
    public JSONObject deleteAssignment(long itemId, long sessionId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId)) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }

            // Check that the user is a session's teacher
            if (!SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }

            logger.info("Teacher " + user.getFullName() + " deletes assignment of item " + itemId + " to session " + sessionId);
            ItemAssignmentPK pk = new ItemAssignmentPK(itemId, sessionId);
            ItemAssignment itemAssignment = ItemAssignmentLocalServiceUtil.fetchItemAssignment(pk);
            ItemAssignmentLocalServiceUtil.deleteItemAssigment(itemAssignment);

            if (itemAssignment.getHomeworkId() == 0) {
                // This is session
                SessionContentLocalServiceUtil.deleteSessionContent(sessionId);

                // Also delete specific item and contents if any
                ProgressionItem sessionSpecificItem = ProgressionItemLocalServiceUtil.getSpecificSessionItem(sessionId);
                if (sessionSpecificItem != null) {
                    ProgressionItemLocalServiceUtil.deleteProgressionItem(sessionSpecificItem.getProgressionItemId());
                }
            } else {
                // This is homework
                // Check that the current user is the homework's teacher
                Homework homework = HomeworkLocalServiceUtil.getHomework(itemAssignment.getHomeworkId());
                if (homework.getTeacherId() != user.getUserId()) {
                    return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
                }
                HomeworkLocalServiceUtil.deleteHomeworkAndDependencies(homework.getHomeworkId());

                // Also delete specific item and contents if any
                ProgressionItem homeworkSpecificItem = ProgressionItemLocalServiceUtil.getSpecificHomeworkItem(itemAssignment.getHomeworkId());
                if (homeworkSpecificItem != null) {
                    ProgressionItemLocalServiceUtil.deleteProgressionItem(homeworkSpecificItem.getProgressionItemId());
                }
            }
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Could not delete assignment with itemId="+itemId+" and sessionId="+sessionId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
