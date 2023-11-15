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

package com.weprode.facile.maintenance;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.agenda.model.Event;
import com.weprode.facile.agenda.service.EventLocalServiceUtil;
import com.weprode.facile.agenda.service.EventPopulationLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.course.model.ContentBlock;
import com.weprode.facile.course.model.Homework;
import com.weprode.facile.course.model.SessionContent;
import com.weprode.facile.course.service.ContentBlockLocalServiceUtil;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.course.service.SessionContentLocalServiceUtil;
import com.weprode.facile.document.model.Activity;
import com.weprode.facile.document.service.ActivityLocalServiceUtil;
import com.weprode.facile.news.model.News;
import com.weprode.facile.news.service.NewsAttachedFileLocalServiceUtil;
import com.weprode.facile.news.service.NewsLocalServiceUtil;
import com.weprode.facile.news.service.NewsPopulationLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.model.SessionTeacher;
import com.weprode.facile.schedule.model.Subject;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.facile.schedule.service.SubjectLocalServiceUtil;
import com.weprode.facile.school.life.constants.SchoollifeConstants;
import com.weprode.facile.school.life.model.Renvoi;
import com.weprode.facile.school.life.model.SchoollifeSession;
import com.weprode.facile.school.life.model.SchoollifeSlot;
import com.weprode.facile.school.life.service.RenvoiLocalServiceUtil;
import com.weprode.facile.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.facile.school.life.service.SchoollifeSlotLocalServiceUtil;
import com.weprode.facile.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataFeedUtil {

    private static final Log logger = LogFactoryUtil.getLog(DataFeedUtil.class);
    private Date maxDate = null;
    private final Random rand = new Random();

    private final Map<Long, List<User>> schoolIdStudents = new HashMap<>();

    public DataFeedUtil() {
        Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(studentRole.getRoleId());

        for (Organization school : OrgUtilsLocalServiceUtil.getAllSchools()) {
            List<Long> orgIds = new ArrayList<>();
            orgIds.add(school.getOrganizationId());

            List<User> students = UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
            schoolIdStudents.put(school.getOrganizationId(), students);
        }
    }

    public void runDataFeed() {
        if (PropsUtil.get(NeroSystemProperties.DATA_FEED_ENABLED) == null || !PropsUtil.get(NeroSystemProperties.DATA_FEED_ENABLED).equals("true")) {
            logger.error("This tool is not executable on this platform");
            return;
        }
        try {
            maxDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-30");
            // maxDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();
        } catch (Exception e) {
            // Nothing
        }
        feedCourses();
        feedHomeworks();
        feedNews();
        feedEvents();
        feedDocumentActivities();
        feedRenvois();
        feedTravauxAFaire();
        feedRetenues();
        feedDepannages();
    }

    private void feedCourses() {

        logger.info("Run feedCourses");
        // Pick existing session contents
        List<SessionContent> sessionContents = SessionContentLocalServiceUtil.getSessionContents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (SessionContent sessionContent : sessionContents) {
            logger.info("Processing content for session " + sessionContent.getSessionId());
            try {
                CDTSession sourceSession = CDTSessionLocalServiceUtil.getCDTSession(sessionContent.getSessionId());

                // Get all sessions of this course
                List<CDTSession> courseSessions = CDTSessionLocalServiceUtil.getGroupSessions(sourceSession.getGroupId(), sourceSession.getStart(), maxDate, true);
                logger.info("There are " + courseSessions.size() + " course sessions");
                Date targetDate = sourceSession.getStart();

                // Go forward in time, every two weeks
                while (targetDate.before(maxDate)) {
                    targetDate = add14Days(targetDate);
                    logger.info(" > Trying date " + targetDate);
                    // Loop over them and find the good one
                    for (CDTSession courseSession : courseSessions) {
                        if (isSameDayAndHour(courseSession.getStart(), targetDate)) {
                            logger.info("   FOUND");
                            copyContent(sessionContent, courseSession);
                        }
                    }
                }

            } catch (Exception e) {
                logger.error("Error processing sessionContent for sessionId " + sessionContent.getSessionId(), e);
            }
        }
    }

    private void copyContent(SessionContent sourceContent, CDTSession targetSession) {

        List<SessionTeacher> sessionTeachers = SessionTeacherLocalServiceUtil.getSessionTeachers(targetSession.getSessionId());
        long teacherId = sessionTeachers.get(0).getTeacherId();
        SessionContent sessionContent = SessionContentLocalServiceUtil.addSessionContent(targetSession.getSessionId(), teacherId, sourceContent.getTitle(), targetSession.getStart(), false);
        sessionContent.setModificationDate(targetSession.getStart());
        SessionContentLocalServiceUtil.updateSessionContent(sessionContent);
        logger.info("     Copied content");

        // Add blocks
        List<ContentBlock> sourceBlocks = ContentBlockLocalServiceUtil.getContentsByItemId(sourceContent.getSessionId());
        for (ContentBlock sourceBlock : sourceBlocks) {
            try {
                ContentBlockLocalServiceUtil.addBlock(teacherId, targetSession.getSessionId(), sourceBlock.getBlockType(), sourceBlock.getBlockName(), sourceBlock.getBlockValue(), sourceBlock.getFileEntryId());
                logger.info("     Copied block");
            } catch (Exception e) {
                logger.error("Error copying content block", e);
            }
        }

    }

    private void feedHomeworks () {

        logger.info("Run feedHomeworks");
        // Get existing homeworks
        List<Homework> existingHomeworks = HomeworkLocalServiceUtil.getHomeworks(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (Homework existingHomework : existingHomeworks) {

            try {
                CDTSession originalSourceSession = CDTSessionLocalServiceUtil.getCDTSession(existingHomework.getSourceSessionId());
                CDTSession originalTargetSession = CDTSessionLocalServiceUtil.getCDTSession(existingHomework.getTargetSessionId());
                // Get all sessions of this course
                List<CDTSession> courseSessions = CDTSessionLocalServiceUtil.getGroupSessions(originalSourceSession.getGroupId(), originalSourceSession.getStart(), maxDate, true);
                logger.info("There are " + courseSessions.size() + " course sessions");

                Date sourceDate = originalSourceSession.getStart();
                Date targetDate = originalTargetSession.getStart();
                // Go forward in time, every two weeks
                while (targetDate.before(maxDate)) {
                    sourceDate = add14Days(sourceDate);
                    targetDate = add14Days(targetDate);
                    logger.info(" > Trying date " + targetDate);
                    // Loop over them and find the good ones
                    CDTSession sourceSession = null;
                    CDTSession targetSession = null;
                    for (CDTSession courseSession : courseSessions) {
                        if (isSameDayAndHour(courseSession.getStart(), sourceDate)) {
                            logger.info("   Source session is found");
                            sourceSession = courseSession;
                        }
                        if (isSameDayAndHour(courseSession.getStart(), targetDate)) {
                            logger.info("   Target session is found");
                            targetSession = courseSession;
                        }
                    }
                    if (sourceSession != null && targetSession != null) {
                        copyHomework(existingHomework, sourceSession, targetSession);
                    }
                }
            } catch (Exception e) {
                logger.error("Error processing homework " + existingHomework.getHomeworkId(), e);
            }
        }
    }

    private void copyHomework(Homework existingHomework, CDTSession sourceSession, CDTSession targetSession) {

        try {
            List<SessionTeacher> sessionTeachers = SessionTeacherLocalServiceUtil.getSessionTeachers(sourceSession.getSessionId());
            User teacher = UserLocalServiceUtil.getUser(sessionTeachers.get(0).getTeacherId());
            Homework createdHomework = HomeworkLocalServiceUtil.createHomework(teacher, existingHomework.getTitle(), sourceSession.getSessionId(), targetSession.getSessionId(),
                    existingHomework.getCourseId(), targetSession.getStart(), existingHomework.getHomeworkType(), existingHomework.getEstimatedTime(), new ArrayList<>(), sourceSession.getStart(), false);
            createdHomework.setModificationDate(sourceSession.getStart());
            HomeworkLocalServiceUtil.updateHomework(createdHomework);
            logger.info("   Created homework");

            // Add blocks
            List<ContentBlock> sourceBlocks = ContentBlockLocalServiceUtil.getContentsByItemId(existingHomework.getHomeworkId());
            for (ContentBlock sourceBlock : sourceBlocks) {
                try {
                    ContentBlockLocalServiceUtil.addBlock(teacher.getUserId(), createdHomework.getHomeworkId(), sourceBlock.getBlockType(), sourceBlock.getBlockName(), sourceBlock.getBlockValue(), sourceBlock.getFileEntryId());
                    logger.info("     Copied block");
                } catch (Exception e) {
                    logger.error("Error copying content block", e);
                }
            }
        } catch (Exception e) {
            logger.error("Error copying homework", e);
        }

    }

    private void feedNews() {

        logger.info("Run feedNews");
        List<News> allNews = NewsLocalServiceUtil.getNewses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (News news : allNews) {
            Date targetPublicationDate = news.getPublicationDate();
            Date targetModificationDate = news.getModificationDate();
            Date targetExpirationDate = news.getExpirationDate();
            while (targetPublicationDate.before(maxDate)) {

                targetPublicationDate = add14Days(targetPublicationDate);
                targetModificationDate = add14Days(targetModificationDate);
                targetExpirationDate = add14Days(targetExpirationDate);

                JSONArray jsonPopulations = NewsPopulationLocalServiceUtil.convertNewsPopulations(news.getNewsId(), news.getAuthorId());
                JSONArray jsonFiles = NewsAttachedFileLocalServiceUtil.convertNewsFiles(news.getNewsId(), news.getAuthorId());
                // Create long list from the Json array
                List<Long> attachFileIds = new ArrayList<>();
                for (int i = 0 ; i < jsonFiles.length() ; i++) {
                    attachFileIds.add(jsonFiles.getJSONObject(i).getLong(JSONConstants.ID));
                }
                News createdNews = NewsLocalServiceUtil.addNews(news.getAuthorId(), news.getTitle(), news.getContent(), news.getIsSchoolNews(), news.getIsImportant(), news.getImageId(),
                        targetPublicationDate, targetExpirationDate, jsonPopulations, attachFileIds);
                logger.info("Created news with " + jsonPopulations.length() + " populations and " + jsonFiles.length() + " attached files");
                createdNews.setModificationDate(targetModificationDate);
                NewsLocalServiceUtil.updateNews(createdNews);
            }
        }
    }

    private void feedEvents() {

        logger.info("Run feedEvents");
        List<Event> allEvents = EventLocalServiceUtil.getEvents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (Event event : allEvents) {
            Date targetStartDate = event.getStartDate();
            Date targetEndDate = event.getEndDate();

            while (targetEndDate.before(maxDate)) {

                targetStartDate = add14Days(targetStartDate);
                targetEndDate = add14Days(targetEndDate);

                JSONArray jsonPopulations = EventPopulationLocalServiceUtil.convertEventPopulations(event.getEventId(), event.getAuthorId());

                EventLocalServiceUtil.createEvent(event.getAuthorId(), event.getTitle(), event.getDescription(),
                        event.getLocation(), targetStartDate, targetEndDate, jsonPopulations);
                logger.info("Created event with " + jsonPopulations.length() + " populations");
            }
        }
    }

    public void feedDocumentActivities() {

        logger.info("Run feedDocumentActivities");
        List<Activity> activities = ActivityLocalServiceUtil.getActivities(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (Activity activity : activities) {
            Date targetDate = activity.getModificationDate();

            while (targetDate.before(maxDate)) {

                targetDate = add14Days(targetDate);
                Activity createdActivity = ActivityLocalServiceUtil.addActivity(activity.getFileEntryId(), activity.getFolderId(), activity.getUserId(), activity.getGroupId(), activity.getFileName(), activity.getFolderName(), activity.getType());
                createdActivity.setModificationDate(targetDate);
                ActivityLocalServiceUtil.updateActivity(createdActivity);
                logger.info("Created activity");
            }
        }
    }

    public void feedRenvois() {

        logger.info("Run feedRenvois");
        List<Renvoi> renvois = RenvoiLocalServiceUtil.getRenvois(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (Renvoi renvoi : renvois) {

            try {
                CDTSession session = CDTSessionLocalServiceUtil.getCDTSession(renvoi.getSourceSessionId());
                Date targetDate = session.getStart();
                List<CDTSession> courseSessions = CDTSessionLocalServiceUtil.getGroupSessions(session.getGroupId(), session.getStart(), maxDate, true);
                logger.info("There are " + courseSessions.size() + " course sessions");

                SchoollifeSession sourceSchoollifeSession = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(renvoi.getSchoollifeSessionId());
                Date targetHHCDate = sourceSchoollifeSession.getStartDate();

                List<SchoollifeSession> schoollifeSessions = SchoollifeSessionLocalServiceUtil.getSlotSessions(sourceSchoollifeSession.getSchoollifeSlotId());
                logger.info("There are " + schoollifeSessions.size() + " hhc sessions");

                while (targetDate.before(maxDate)) {

                    targetDate = add14Days(targetDate);
                    targetHHCDate = add14Days(targetHHCDate);

                    CDTSession targetSession = null;
                    for (CDTSession courseSession : courseSessions) {
                        if (isSameDayAndHour(courseSession.getStart(), targetDate)) {
                            logger.info("   Target session is found");
                            targetSession = courseSession;
                        }
                    }

                    SchoollifeSession targetHhcSession = null;
                    for (SchoollifeSession schoollifeSession : schoollifeSessions) {
                        if (isSameDayAndHour(schoollifeSession.getStartDate(), targetHHCDate)) {
                            logger.info("   Target HHC session is found");
                            targetHhcSession = schoollifeSession;
                        }
                    }
                    if (targetSession != null && targetHhcSession != null) {
                        RenvoiLocalServiceUtil.addRenvoi(targetHhcSession.getSchoollifeSessionId(), renvoi.getTeacherId(), renvoi.getSourceTeacherId(), renvoi.getStudentId(), targetSession.getSessionId(), 0, targetDate);
                        logger.info("Created renvoi");
                    }
                }
            } catch (Exception e) {
                logger.error("Error creating a renvoi", e);
            }
        }

    }

    public void feedTravauxAFaire() {

        logger.info("Run feedTravauxAFaire");

        // Get Random student number between 10 and 20
        List<Subject> subjects = SubjectLocalServiceUtil.getSubjects(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        feedSchoolLifeSessions(SchoollifeConstants.TYPE_TRAVAUX, 10, 20, subjects);
    }

    public void feedRetenues() {

        logger.info("Run feedRetenues");

        // Get Random student number between 5 and 15
        feedSchoolLifeSessions(SchoollifeConstants.TYPE_RETENUE, 5, 15, new ArrayList<>());

    }

    public void feedDepannages() {

        logger.info("Run feedDepannage");

        // Get Random student number between 0 and 10
        feedSchoolLifeSessions(SchoollifeConstants.TYPE_DEPANNAGE, 0, 10, new ArrayList<>());
    }

    private void feedSchoolLifeSessions(int slotType, int minStudents, int maxStudents, List<Subject> subjects) {
        List<SchoollifeSession> sessions = new ArrayList<>();

        for (SchoollifeSlot slot : SchoollifeSlotLocalServiceUtil.getSchoollifeSlots(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
            if (slot.getType() == slotType) {
                sessions.addAll(SchoollifeSessionLocalServiceUtil
                        .getSlotSessions(slot.getSchoollifeSlotId()));
            }
        }

        logger.info("Found " + sessions.size() + " sessions");
        for (SchoollifeSession session : sessions) {

            if (session.getStartDate().before(maxDate)) {

                try {
                    long teacherId = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId()).getTeacherId();

                    int nbStudents = getRandomIntInRange(minStudents, maxStudents);
                    populateSchoolLifeSession(nbStudents, schoolIdStudents.get(session.getSchoolId()),
                            teacherId, session.getSchoollifeSessionId(), subjects);
                } catch (PortalException e) {
                    logger.error("Could not get teacherId for schoolLifeSlotId " + session.getSchoollifeSlotId());
                }
            }
        }
    }

    private int getRandomIntInRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void populateSchoolLifeSession(int nbStudents, List<User> students, long teacherId ,
                                          long schoollifeSessionId, List<Subject> subjects) {
        boolean notifyParents = false;
        String comment = "";
        String replayTestSubject = "";
        List<Long> addedUserIds = new ArrayList<>();

        logger.info("Adding " + nbStudents + " students");
        for (int i = 0; i < nbStudents; ++i) {
            long studentId;
            do {
                studentId = students.get(rand.nextInt(students.size())).getUserId();
            } while (addedUserIds.contains(studentId));

            addedUserIds.add(studentId);

            if (!subjects.isEmpty()) {
                replayTestSubject = subjects.get(rand.nextInt(subjects.size())).getName();
            }

            boolean success = SessionStudentLocalServiceUtil.registerStudentToSession(
                    teacherId, studentId, schoollifeSessionId,
                    comment, replayTestSubject, notifyParents);

            if (!success) {
                logger.warn("Could not add student number " + i + " to sessionId");
            }
        }
    }

    private boolean isSameDayAndHour(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)
                && cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY)
                && cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE);
    }

    private Date add14Days(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 14);
        return cal.getTime();
    }
}
