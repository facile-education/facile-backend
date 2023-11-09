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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.course.model.ContentBlock;
import com.weprode.facile.course.model.SessionContent;
import com.weprode.facile.course.service.ContentBlockLocalServiceUtil;
import com.weprode.facile.course.service.SessionContentLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.model.SessionTeacher;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.facile.schedule.service.SessionTeacherLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataFeedUtil {

    private static final Log logger = LogFactoryUtil.getLog(DataFeedUtil.class);

    public DataFeedUtil() {
        // Nothing
    }

    public void runDataFeed() {
        feedCourses();
    }

    private void feedCourses() {

        // Pick existing session contents
        List<SessionContent> sessionContents = SessionContentLocalServiceUtil.getSessionContents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (SessionContent sessionContent : sessionContents) {
            logger.info("Processing content for session " + sessionContent.getSessionId());
            try {
                CDTSession sourceSession = CDTSessionLocalServiceUtil.getCDTSession(sessionContent.getSessionId());

                // Get all sessions of this course
                List<CDTSession> courseSessions = CDTSessionLocalServiceUtil.getGroupSessions(sourceSession.getGroupId(), sourceSession.getStart(), ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate(), true);
                logger.info("There are " + courseSessions.size() + " course sessions");
                Date targetDate = sourceSession.getStart();
                // Go forward in time, every two weeks
                while (targetDate.before(ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate())) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(targetDate);
                    cal.add(Calendar.DATE, 14);
                    targetDate = cal.getTime();
                    logger.info(" > Trying date " + targetDate);
                    // Loop over them and find the good one
                    for (CDTSession courseSession : courseSessions) {
                        if (isSameDay(courseSession.getStart(), targetDate)) {
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
        SessionContentLocalServiceUtil.addSessionContent(targetSession.getSessionId(), teacherId, sourceContent.getTitle(), targetSession.getStart(), false);
        logger.info("     Copied content");

        // Add blocks
        List<ContentBlock> sourceBlocks = ContentBlockLocalServiceUtil.getContentsByItemId(targetSession.getSessionId());
        for (ContentBlock sourceBlock : sourceBlocks) {
            try {
                ContentBlockLocalServiceUtil.addBlock(teacherId, targetSession.getSessionId(), sourceBlock.getBlockType(), sourceBlock.getBlockName(), sourceBlock.getBlockValue(), sourceBlock.getFileEntryId());
                logger.info("     Copied block");
            } catch (Exception e) {
                logger.error("Error copying content block", e);
            }
        }

    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE);
    }

}
