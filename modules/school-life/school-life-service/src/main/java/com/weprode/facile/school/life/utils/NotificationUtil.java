/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.school.life.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.facile.messaging.constants.MessagingConstants;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.facile.school.life.model.Renvoi;
import com.weprode.facile.school.life.model.SchoollifeSession;
import com.weprode.facile.school.life.model.SchoollifeSlot;
import com.weprode.facile.school.life.model.SessionStudent;
import com.weprode.facile.school.life.service.RenvoiLocalServiceUtil;
import com.weprode.facile.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.facile.school.life.service.SchoollifeSlotLocalServiceUtil;
import com.weprode.facile.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.facile.school.life.service.persistence.RenvoiPK;
import com.weprode.facile.school.life.service.persistence.SessionStudentPK;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class NotificationUtil {

    private NotificationUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(NotificationUtil.class);
    private static final String DOYENS = "DOYENS";
    private static final String MAIN_TEACHERS = "MAIN_TEACHERS";
    private static final String COURSE_TEACHERS = "CO_TEACHERS";
    private static final String PARENTS = "PARENTS";
    private static final String PSYCHOLOGISTS = "PSYCHOLOGISTS";
    private static final String SOCIAL_COUNSELORS = "SOCIAL_COUNSELORS";

    public static void notifyRenvoiRegistration(long firingTeacherId, long sourceTeacherId, long sourceSessionId, long sourceSchoollifeSessionId, long studentId, long schoollifeSessionId) {

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(firingTeacherId);
            User sourceTeacher = UserLocalServiceUtil.getUser(sourceTeacherId);

            DateFormat df = getDateFormat();
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            String subject = "Avis de renvoi : " + student.getFullName() + " le " + df.format(session.getStartDate());

            // Recipients:
            // - source teacher and co-teachers
            // - student's doyens if it's not the firing teacher
            // - student's main teacher if it's not the firing teacher
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(firingTeacherId));
            List<String> profilesToNotify = Arrays.asList(COURSE_TEACHERS, DOYENS, MAIN_TEACHERS);
            List<Long> recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, sourceSessionId);

            String content = createColleagesRenvoiRegistrationContent(student, teacher, sourceTeacher, sourceSessionId, sourceSchoollifeSessionId);

            MessageLocalServiceUtil.sendMessage(firingTeacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for renvoi", e);
        }
    }

    public static void notifyRenvoiUnregistration(long firingTeacherId, long sourceTeacherId, long sourceSessionId, long sourceSchoollifeSessionId, long studentId, long schoollifeSessionId) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User firingTeacher = UserLocalServiceUtil.getUser(firingTeacherId);
            User sourceTeacher = UserLocalServiceUtil.getUser(sourceTeacherId);

            // Subject
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Annulation de renvoi : " + student.getFullName() + " le " + slotName;

            // Send notification to the source's teacher, the student's doyens and the student's main teacher if not the teacher + co-teachers
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(firingTeacherId));
            List<String> profilesToNotify = Arrays.asList(COURSE_TEACHERS, DOYENS, MAIN_TEACHERS);
            List<Long> recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, sourceSessionId);

            String content = createColleagesRenvoiRegistrationContent(student, firingTeacher, sourceTeacher, sourceSessionId, sourceSchoollifeSessionId);

            MessageLocalServiceUtil.sendMessage(firingTeacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for renvoi", e);
        }
    }

    public static void notifyRenvoiReason(long teacherId, long studentId, long schoollifeSessionId, String reason) {
        try {
            RenvoiPK pk = new RenvoiPK(schoollifeSessionId, studentId);
            Renvoi renvoi = RenvoiLocalServiceUtil.getRenvoi(pk);

            String sourceSessionLabel = "";
            Date sourceDate;
            if (renvoi.getSourceSessionId() != 0) {
                CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(renvoi.getSourceSessionId());
                sourceDate = cdtSession.getStart();
                String coursName = GroupUtilsLocalServiceUtil.getGroupName(cdtSession.getGroupId());
                sourceSessionLabel = " du cours de " + coursName;
            } else {
                sourceDate = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(renvoi.getSourceSchoollifeSessionId()).getStartDate();
                sourceSessionLabel = " du cr\u00e9neau de " + SchoollifeSessionLocalServiceUtil.getSessionName(renvoi.getSourceSchoollifeSessionId());
            }

            notifyFiringReasonForParents(teacherId, studentId, reason, sourceSessionLabel, sourceDate);
            notifyFiringReasonForSchoolColleagues(teacherId, studentId, renvoi, reason, sourceSessionLabel, sourceDate);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public static void notifyRetenueRegistration(long teacherId, long studentId, long schoollifeSessionId, String comment, boolean notifyParents) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            String subject = "Retenue le " + getDateFormat().format(session.getStartDate());

            // Send notification to the student
            List<Long> recipientList = new ArrayList<>(Collections.singletonList(studentId));
            String studentContent = createStudentRetenueRegistrationContent(student, teacher, session, comment);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, studentContent, MessagingConstants.TYPE_HHC);


            // Send notification to his parents if selected
            if (notifyParents) {
                List<String> profilesToNotify = new ArrayList<>(Collections.singletonList(PARENTS));
                List<Long> usersToExclude = new ArrayList<>();
                recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

                String parentContent = createParentRetenueRegistrationContent(student, teacher, session, comment);

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }


            // Send notification to his doyens and main teacher if not the teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(teacherId));
            recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

            String teacherContent = createColleagesRetenueRegistrationContent(student, teacher, session, comment);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);

        } catch (Exception e) {
            logger.error("Error sending notification for retenue", e);
        }
    }

    public static void notifyRetenueUnregistration(long teacherId, long studentId, long schoollifeSessionId, boolean notifyParents) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            // Subject
            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Annulation retenue du " + slotName;

            // Send notification to the student
            List<Long> recipientList = new ArrayList<>(Collections.singletonList(studentId));
            String studentContent = createStudentRetenueUnRegistrationContent(student, teacher, session);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, studentContent, MessagingConstants.TYPE_HHC);


            // Send notification to his parents if selected
            if (notifyParents) {
                List<String> profilesToNotify = new ArrayList<>(Collections.singletonList(PARENTS));
                List<Long> usersToExclude = new ArrayList<>();
                recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

                String parentContent = createParentRetenueUnRegistrationContent(student, teacher, session);

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }


            // Send notification to his doyens and main teacher if not the teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(teacherId));
            recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

            String teacherContent = createColleagesRetenueUnRegistrationContent(student, teacher, session);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);

        } catch (Exception e) {
            logger.error("Error sending notification for retenue", e);
        }
    }

    public static void notifyRetenueAbsence(long studentId, long schoollifeSessionId) {

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            SessionStudentPK pk = new SessionStudentPK(schoollifeSessionId, studentId);
            SessionStudent sessionStudent = SessionStudentLocalServiceUtil.getSessionStudent(pk);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());
            User watchTeacher = UserLocalServiceUtil.getUser(slot.getTeacherId());

            // Recipients are:
            // - source teacher
            // - doyen if not the source teacher
            // - main teacher if not the source teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(watchTeacher.getUserId()));
            List<Long> recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);
            recipientList.add(sessionStudent.getSourceTeacherId());


            String subject = "Retenue - Absence de " + student.getFullName();

            String content = createRetenueAbsenceContent(student, watchTeacher, session);

            MessageLocalServiceUtil.sendMessage(watchTeacher.getUserId(), recipientList, subject, content, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for retenue", e);
        }
    }

    public static void notifyTravauxRegistration(long teacherId, long studentId, long schoollifeSessionId, boolean notifyParents) {

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SessionStudentPK pk = new SessionStudentPK(schoollifeSessionId, studentId);
            SessionStudent studentSession = SessionStudentLocalServiceUtil.fetchSessionStudent(pk);

            // Recipients:
            // - student
            // - his parents if selected
            // - his doyens if not the teacher
            // - main teacher if not the source teacher

            String subject = "Travail \u00e0 refaire le " + getDateFormat().format(session.getStartDate());


            // Send notification to the student
            List<Long> recipientList = new ArrayList<>(Collections.singletonList(studentId));
            String studentContent = createStudentTravauxRegistrationContent(student, teacher, session, studentSession);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, studentContent, MessagingConstants.TYPE_HHC);


            // Send notification to his parents if selected
            if (notifyParents) {
                List<String> profilesToNotify = new ArrayList<>(Collections.singletonList(PARENTS));
                List<Long> usersToExclude = new ArrayList<>();
                recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

                String parentContent = createParentTravauxRegistrationContent(student, teacher, session, studentSession);

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }


            // Send notification to his doyens and main teacher if not the teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(teacherId));
            recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

            String teacherContent = createColleagesTravauxRegistrationContent(student, teacher, session, studentSession);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for travaux", e);
        }
    }

    public static void notifyTravauxUnregistration(long teacherId, long studentId, long schoollifeSessionId, boolean notifyParents, SessionStudent studentSession) {

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            // Recipients:
            // - student
            // - his parents if selected
            // - his doyens if not the teacher
            // - main teacher if not the source teacher

            // Subject
            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Annulation travail \u00e0 refaire en " + studentSession.getSubject() + " le " + slotName;


            // Send notification to the student
            List<Long> recipientList = new ArrayList<>(Collections.singletonList(studentId));
            String studentContent = createStudentTravauxUnRegistrationContent(student, teacher, session, studentSession);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, studentContent, MessagingConstants.TYPE_HHC);


            // Send notification to his parents if selected
            if (notifyParents) {
                List<String> profilesToNotify = new ArrayList<>(Collections.singletonList(PARENTS));
                List<Long> usersToExclude = new ArrayList<>();
                recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

                String parentContent = createParentTravauxUnRegistrationContent(student, teacher, session, studentSession);

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }


            // Send notification to his doyens and main teacher if not the teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(teacherId));
            recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

            String teacherContent = createColleagesTravauxUnRegistrationContent(student, teacher, session, studentSession);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for travaux", e);
        }
    }

    public static void notifyTravauxAbsence(long studentId, long schoollifeSessionId) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            SessionStudentPK pk = new SessionStudentPK(schoollifeSessionId, studentId);
            SessionStudent sessionStudent = SessionStudentLocalServiceUtil.getSessionStudent(pk);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());
            User watchTeacher = UserLocalServiceUtil.getUser(slot.getTeacherId());

            // Recipients are:
            // - source teacher
            // - doyen if not the source teacher
            // - main teacher if not the source teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(watchTeacher.getUserId()));
            List<Long> recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);
            recipientList.add(sessionStudent.getSourceTeacherId());


            String subject = "Retenue - Absence de " + student.getFullName();

            String content = createTravauxAbsence(student, watchTeacher, session, sessionStudent);

            MessageLocalServiceUtil.sendMessage(watchTeacher.getUserId(), recipientList, subject, content, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for travaux absence for studentId=" + studentId + ", schoollifeSessionId=" + schoollifeSessionId, e);
        }
    }

    public static void notifyDepannageRegistration(long teacherId, long studentId, long schoollifeSessionId, boolean notifyParents) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

            // Send notification to student's parents if selected
            List<Long> recipientList;

            DateFormat df = getDateFormat();
            String subject = "D\u00e9pannage le " + df.format(session.getStartDate());

            // Parents
            if (notifyParents) {
                List<String> profilesToNotify = new ArrayList<>(Collections.singletonList(PARENTS));
                List<Long> usersToExclude = new ArrayList<>();
                recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

                String parentContent = createParentDepannageRegistrationContent(student, teacher, session);

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }


            // Send notification to his doyens and main teacher if not the teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(teacherId));
            recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

            String teacherContent = createColleagesDepannageRegistrationContent(student, teacher, session);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for dépannage", e);
        }
    }

    public static void notifyDepannageUnregistration(long teacherId, long studentId, long schoollifeSessionId, boolean notifyParents) {

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);

            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

            // Send notification to the student
            List<Long> recipientList;

            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Annulation d\u00e9pannage le " + slotName;

            // Parents
            if (notifyParents) {
                List<String> profilesToNotify = new ArrayList<>(Collections.singletonList(PARENTS));
                List<Long> usersToExclude = new ArrayList<>();
                recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

                String parentContent = createParentDepannageUnRegistrationContent(student, teacher, session);

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }


            // Send notification to his doyens and main teacher if not the teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(teacherId));
            recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

            String teacherContent = createColleagesDepannageUnRegistrationContent(student, teacher, session);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);

        } catch (Exception e) {
            logger.error("Error sending notification for dépannage", e);
        }
    }

    public static void notifyEtudeRegistration(long teacherId, long studentId, long schoollifeSessionId, boolean notifyParents) {

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

            // Subject
            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Cercle d'\u00e9tude le " + slotName;

            // Send notification to the student
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(studentId);

            String content = createStudentEtudeRegistrationContent(student, teacher, session, slotName);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);

            // Send notification to student's parents if selected
            if (notifyParents) {
                List<String> profilesToNotify = new ArrayList<>(Collections.singletonList(PARENTS));
                List<Long> usersToExclude = new ArrayList<>();
                recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

                String parentContent = createParentEtudeRegistrationContent(student, teacher, session, slotName);

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }

            // Send notification to the student's doyens and the student's main teacher if not the teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(teacherId));
            recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

            String teacherContent = createColleageEtudeRegistrationContent(student, teacher, session, slotName);


            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for etude", e);
        }
    }

    public static void notifyEtudeUnregistration(long teacherId, long studentId, long schoollifeSessionId, boolean notifyParents, boolean allSessions) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);

            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

            // Subject
            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Fin du cercle d'\u00e9tude le " + slotName;

            // Send notification to the student
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(studentId);

            String content = createStudentEtudeUnRegistrationContent(student, teacher, session, slotName, allSessions);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);

            // Send notification to student's parents if selected
            if (notifyParents) {
                List<String> profilesToNotify = new ArrayList<>(Collections.singletonList(PARENTS));
                List<Long> usersToExclude = new ArrayList<>();
                recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

                String parentContent = createParentEtudeUnRegistrationContent(student, teacher, session, slotName, allSessions);

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }

            // Send notification to the student's doyens and the student's main teacher if not the teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(teacherId));
            recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

            String teacherContent = createColleageEtudeUnRegistrationContent(student, teacher, session, slotName, allSessions);

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for etude deregistration", e);
        }
    }

    public static void notifyEtudeAbsence(long studentId, long schoollifeSessionId) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            SessionStudentPK pk = new SessionStudentPK(schoollifeSessionId, studentId);
            SessionStudent sessionStudent = SessionStudentLocalServiceUtil.getSessionStudent(pk);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());
            User watchTeacher = UserLocalServiceUtil.getUser(slot.getTeacherId());

            // Recipients are:
            // - source teacher
            // - doyen if not the source teacher
            // - main teacher if not the source teacher
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS));
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(watchTeacher.getUserId()));
            List<Long> recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);
            recipientList.add(sessionStudent.getSourceTeacherId());


            String subject = "Retenue - Absence de " + student.getFullName();

            String content = createEtudeAbsenceContent(student, watchTeacher, session);

            MessageLocalServiceUtil.sendMessage(watchTeacher.getUserId(), recipientList, subject, content, MessagingConstants.TYPE_HHC);
      } catch (Exception e) {
            logger.error("Error sending notification for travaux absence for studentId=" + studentId + ", schoollifeSessionId=" + schoollifeSessionId, e);
        }
    }

    /* ===================== Messages ==================== */
    private static final String HTML_LINE_BREAK = "</br>";
    private static final String GREETING = "Meilleurs messages," + HTML_LINE_BREAK;

    private static String createStudentRetenueRegistrationContent(User student, User teacher, SchoollifeSession session, String comment) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        String studentContent = student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous serez en retenue le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK;
        if (!comment.isEmpty()) {
            studentContent += "Motif : " + comment + HTML_LINE_BREAK;
        }
        studentContent += HTML_LINE_BREAK + GREETING + teacher.getFullName();

        return studentContent;
    }

    private static String createParentRetenueRegistrationContent(User student, User teacher, SchoollifeSession session, String comment) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        String parentContent = "Ch\u00e8re Madame, cher Monsieur, " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Votre enfant " + student.getFullName() + " sera en retenue le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + ".</br>";
        if (!comment.isEmpty()) {
            parentContent += "Motif : " + comment + HTML_LINE_BREAK;
        }
        parentContent += HTML_LINE_BREAK + GREETING + teacher.getFullName();

        return parentContent;
    }

    private static String createColleagesRetenueRegistrationContent(User student, User teacher, SchoollifeSession session, String comment) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        String teacherContent = "Cher.e.s coll\u00e8gue.s, " + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve " + student.getFullName() + " sera en retenue le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + ".</br>";
        if (!comment.isEmpty()) {
            teacherContent += "Motif : " + comment + HTML_LINE_BREAK;
        }
        teacherContent += HTML_LINE_BREAK + GREETING + teacher.getFullName();

        return teacherContent;
    }

    private static String createStudentRetenueUnRegistrationContent(User student, User teacher, SchoollifeSession session) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "La retenue du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9e." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createParentRetenueUnRegistrationContent(User student, User teacher, SchoollifeSession session) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "La retenue de votre enfant " + student.getFullName() + " du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9e." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createColleagesRetenueUnRegistrationContent(User student, User teacher, SchoollifeSession session) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "La retenue de l'\u00e9l\u00e8ve " + student.getFullName() + " du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9e." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createRetenueAbsenceContent(User student, User watchTeacher, SchoollifeSession session) throws PortalException {
        DateFormat df = getDateFormat();

        return "Cher.e.s coll\u00e8gue.s, " + HTML_LINE_BREAK + HTML_LINE_BREAK + student.getFullName() + " ne s'est pas pr\u00e9sent\u00e9.e en retenue le " + df.format(session.getStartDate()) + StringPool.PERIOD
                + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + watchTeacher.getFullName();
    }

    private static String createStudentTravauxRegistrationContent(User student, User teacher, SchoollifeSession session, SessionStudent studentSession) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous \u00eates convoqu\u00e9.e pour un travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createParentTravauxRegistrationContent(User student, User teacher, SchoollifeSession session, SessionStudent studentSession) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Ch\u00e8re Madame, cher Monsieur,"
                + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Votre enfant " + student.getFullName() + " est convoqu\u00e9.e pour un travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createColleagesTravauxRegistrationContent(User student, User teacher, SchoollifeSession session, SessionStudent studentSession) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + student.getFullName() + " est convoqu\u00e9.e pour un travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createStudentTravauxUnRegistrationContent(User student, User teacher, SchoollifeSession session, SessionStudent studentSession) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createParentTravauxUnRegistrationContent(User student, User teacher, SchoollifeSession session, SessionStudent studentSession) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " par votre enfant " + student.getFullName() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();

    }

    private static String createColleagesTravauxUnRegistrationContent(User student, User teacher, SchoollifeSession session, SessionStudent studentSession) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " par l'\u00e9l\u00e8ve " + student.getFullName() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createTravauxAbsence(User student, User watchTeacher, SchoollifeSession session, SessionStudent sessionStudent) {
        DateFormat df = getDateFormat();

        return  "Cher.e.s coll\u00e8gue.s, " + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve " + student.getFullName() + " ne s'est pas pr\u00e9sent\u00e9.e au travail \u00e0 refaire de " + sessionStudent.getSubject() + " le " + df.format(session.getStartDate()) + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + watchTeacher.getFullName();
    }

    private static String createParentDepannageRegistrationContent(User student, User teacher, SchoollifeSession session) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK
                + "Votre enfant " + student.getFullName() + " s'est pr\u00e9sent\u00e9.e au d\u00e9pannage le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createColleagesDepannageRegistrationContent(User student, User teacher, SchoollifeSession session) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + student.getFullName() + " s'est pr\u00e9sent\u00e9.e au d\u00e9pannage le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createParentDepannageUnRegistrationContent(User student, User teacher, SchoollifeSession session) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le d\u00e9pannage de l'\u00e9l\u00e8ve " + student.getFullName() + " du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createColleagesDepannageUnRegistrationContent(User student, User teacher, SchoollifeSession session) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le d\u00e9pannage de l'\u00e9l\u00e8ve " + student.getFullName() + " du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createStudentEtudeRegistrationContent(User student, User teacher, SchoollifeSession session, String slotName) throws PortalException {
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous \u00eates inscrit.e au cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createParentEtudeRegistrationContent(User student, User teacher, SchoollifeSession session, String slotName) throws PortalException {
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK
                + "Votre enfant " + student.getFullName() + " est inscrit.e au cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createColleageEtudeRegistrationContent(User student, User teacher, SchoollifeSession session, String slotName) throws PortalException {
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

        return "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve " + student.getFullName() + " est inscrit.e au cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + teacher.getFullName();
    }

    private static String createEtudeAbsenceContent(User student, User watchTeacher, SchoollifeSession session) {
        DateFormat df = getDateFormat();

        return  "Cher.e.s coll\u00e8gue.s, " + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve " + student.getFullName() + " ne s'est pas pr\u00e9sent\u00e9.e au cercle d'\u00e9tude le " + df.format(session.getStartDate()) + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                + GREETING + watchTeacher.getFullName();
    }

    private static String createStudentEtudeUnRegistrationContent(User student, User teacher, SchoollifeSession session, String slotName, boolean allSessions) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());
        String content;

        if (allSessions) {
            content = "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous \u00eates d\u00e9sinscrit.e du cercle d'\u00e9tude du " + slotName + " en salle " + slot.getRoom()
                    + " du " + df.format(session.getStartDate()) + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK + ""
                    + GREETING + teacher.getFullName();
        } else {
            content = "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous \u00eates d\u00e9sinscrit.e du cercle d'\u00e9tude du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + "" + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();
        }
        return content;
    }

    private static String createParentEtudeUnRegistrationContent(User student, User teacher, SchoollifeSession session, String slotName, boolean allSessions) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());
        String content;

        if (allSessions) {
            content = "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Votre enfant " + student.getFullName() + " est d\u00e9sinscrit.e du cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                    + "du " + df.format(session.getStartDate()) + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();
        } else {
            content = "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Votre enfant " + student.getFullName() + " est d\u00e9sinscrit.e du cercle d'\u00e9tude du " + df.format(session.getStartDate()) + ", en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();
        }
        return content;
    }

    private static String createColleageEtudeUnRegistrationContent(User student, User teacher, SchoollifeSession session, String slotName, boolean allSessions) throws PortalException {
        DateFormat df = getDateFormat();
        SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());
        String content;

        if (allSessions) {
            content = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve" + student.getFullName() + " est d\u00e9sinscrit.e du cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                    + "depuis le " + df.format(session.getStartDate()) + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();
        } else {
            content = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve" + student.getFullName() + " est d\u00e9sinscrit.e du cercle d'\u00e9tude du " + df.format(session.getStartDate()) + ", en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();
        }
        return content;
    }

    private static String createColleagesRenvoiRegistrationContent(User student, User teacher, User sourceTeacher, long sourceSessionId, long sourceSchoollifeSessionId) throws PortalException {
        DateFormat df = getDateFormat();

        String content = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Je vous informe que l'\u00e9l\u00e8ve " + student.getFullName() + " s'est pr\u00e9sent\u00e9.e en salle de renvoi le " + df.format(new Date()) + "." + HTML_LINE_BREAK;
        content += "L'\u00e9l\u00e8ve m'a indiqu\u00e9 \u00eatre renvoy\u00e9.e ";
        if (sourceSessionId != 0) {
            CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(sourceSessionId);
            String coursName = GroupUtilsLocalServiceUtil.getGroupName(cdtSession.getGroupId());
            content += "du cours " + coursName + " dispens\u00e9 par " + sourceTeacher.getFullName() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
        } else {
            content += "du cr\u00e9neau " + SchoollifeSessionLocalServiceUtil.getSessionName(sourceSchoollifeSessionId) + " encadr\u00e9 par " + sourceTeacher.getFullName() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
        }
        content += GREETING + teacher.getFullName();

        return content;
    }

    private static String createColleagesRenvoiUnRegistrationContent(User student, User firingTeacher, User sourceTeacher, long sourceSessionId, long sourceSchoollifeSessionId) throws PortalException {
        String content = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Je vous informe de l'annuation du renvoi de l'\u00e9l\u00e8ve " + student.getFullName();

        if (sourceSessionId != 0) {
            CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(sourceSessionId);
            String coursName = GroupUtilsLocalServiceUtil.getGroupName(cdtSession.getGroupId());
            content += " du cours " + coursName + " dispens\u00e9 par " + sourceTeacher.getFullName() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
        } else {
            content += " du cr\u00e9neau " + SchoollifeSessionLocalServiceUtil.getSessionName(sourceSchoollifeSessionId) + " encadr\u00e9 par " + sourceTeacher.getFullName() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
        }

        content += GREETING + firingTeacher.getFullName();

        return content;
    }
    private static void notifyFiringReasonForParents(long teacherId, long studentId, String reason, String sourceSessionLabel, Date sourceDate) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);

            List<Long> usersToExclude = new ArrayList<>();
            List<String> profilesToNotify = new ArrayList<>(Collections.singletonList(PARENTS));

            List<Long> recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, 0);

            String subject = "Avis de renvoi";

            DateFormat df = getDateFormat();
            String parentContent = "Ch\u00e8re Madame, Cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Je suis au regret de vous informer que j'ai renvoy\u00e9 " + student.getFullName()
                    + sourceSessionLabel
                    + " le " + df.format(sourceDate)
                    + " pour le motif suivant:" + HTML_LINE_BREAK + reason + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + "Je me tiens, bien entendu, \u00e0 votre disposition pour discuter de la situation de votre enfant." + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for renvoi", e);
        }
    }

    private static void notifyFiringReasonForSchoolColleagues(long sourceTeacherId, long studentId, Renvoi renvoi, String reason, String sourceSessionLabel, Date sourceDate) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(sourceTeacherId);

            // Send notification to the student's doyens and the student's main teacher (and psychologist and social counselor) if not the source teacher + co-teachers
            List<Long> usersToExclude = new ArrayList<>(Collections.singletonList(sourceTeacherId));
            List<String> profilesToNotify = new ArrayList<>(Arrays.asList(DOYENS, MAIN_TEACHERS, PSYCHOLOGISTS, SOCIAL_COUNSELORS));
            if (renvoi.getSourceSessionId() != 0) {
                profilesToNotify.add(COURSE_TEACHERS);
            }

            List<Long> recipientList = buildRecipientList(profilesToNotify, usersToExclude, student, renvoi.getSourceSessionId());

            String subject = "Avis de renvoi";

            if (!recipientList.isEmpty()) {
                DateFormat df = getDateFormat();
                String teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "J'ai renvoy\u00e9 l'\u00e9l\u00e8ve " + student.getFullName()
                        + sourceSessionLabel
                        + " le " + df.format(renvoi.getRenvoiDate())
                        + " pour le motif suivant:</br>" + reason + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(sourceTeacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }
        } catch (Exception e) {
            logger.error("Error sending notification for renvoi", e);
        }
    }

    private static List<Long> buildRecipientList(List<String> profilesToNotify, List<Long> usersToExclude, User student, long sessionId) {
        List<Long> recipientList = new ArrayList<>();

        if (profilesToNotify.contains(DOYENS)) {
            addDoyens(recipientList, student, usersToExclude);
        }
        if (profilesToNotify.contains(MAIN_TEACHERS)) {
            addMainTeachers(recipientList, student, usersToExclude);
        }
        if (profilesToNotify.contains(COURSE_TEACHERS)) {
            addCourseTeachers(recipientList, sessionId, usersToExclude);
        }
        if (profilesToNotify.contains(PARENTS)) {
            addParents(recipientList, student, usersToExclude);
        }
        if (profilesToNotify.contains(PSYCHOLOGISTS)) {
            addPsychologists(recipientList, student, usersToExclude);
        }
        if (profilesToNotify.contains(SOCIAL_COUNSELORS)) {
            addSocialCounselors(recipientList, student, usersToExclude);
        }

        return recipientList;
    }

    private static void addDoyens(List<Long> recipientList, User student, List<Long> usersToExclude) {
        List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
        for (User doyen : doyens) {
            if (!recipientList.contains(doyen.getUserId()) && !usersToExclude.contains(doyen.getUserId())) { // Do not add user already in list nor in the list of people to not be notified
                recipientList.add(doyen.getUserId());
            }
        }
    }

    private static void addMainTeachers(List<Long> recipientList, User student, List<Long> usersToExclude) {
        List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
        for (User mainTeacher : mainTeachers) {
            if (!recipientList.contains(mainTeacher.getUserId()) && !usersToExclude.contains(mainTeacher.getUserId())) {
                recipientList.add(mainTeacher.getUserId());
            }
        }
    }

    private static void addCourseTeachers(List<Long> recipientList, long sessionId, List<Long> usersToExclude) {
        if (sessionId != 0) {
            List<Long> sessionTeacherIds = SessionTeacherLocalServiceUtil.getTeacherIds(sessionId);
            for (Long sessionTeacherId : sessionTeacherIds) {
                if (!recipientList.contains(sessionTeacherId) && !usersToExclude.contains(sessionTeacherId)) {
                    recipientList.add(sessionTeacherId);
                }
            }
        }
    }

    private static void addParents(List<Long> recipientList, User student, List<Long> usersToExclude) {
        List<User> parents = UserRelationshipLocalServiceUtil.getParents(student.getUserId());
        for (User parent : parents) {
            if (!recipientList.contains(parent.getUserId()) && !usersToExclude.contains(parent.getUserId())) {
                recipientList.add(parent.getUserId());
            }
        }
    }

    private static void addPsychologists(List<Long> recipientList, User student, List<Long> usersToExclude) {
        List<User> psychologists = UserUtilsLocalServiceUtil.getUserPsychologues(student);
        for (User psychologist : psychologists) {
            if (!recipientList.contains(psychologist.getUserId()) && !usersToExclude.contains(psychologist.getUserId())) {
                recipientList.add(psychologist.getUserId());
            }
        }
    }

    private static void addSocialCounselors(List<Long> recipientList, User student, List<Long> usersToExclude) {
        List<User> socialCounselors = UserUtilsLocalServiceUtil.getUserConseillersSociaux(student);
        for (User socialCounselor : socialCounselors) {
            if (!recipientList.contains(socialCounselor.getUserId()) && !usersToExclude.contains(socialCounselor.getUserId())) {
                recipientList.add(socialCounselor.getUserId());
            }
        }
    }

    private static String getFrenchDay(Calendar cal) {

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.MONDAY) {
            return "lundi";
        } else if (dayOfWeek == Calendar.TUESDAY) {
            return "mardi";
        } else if (dayOfWeek == Calendar.WEDNESDAY) {
            return "mercredi";
        } else if (dayOfWeek == Calendar.THURSDAY) {
            return "jeudi";
        } else if (dayOfWeek == Calendar.FRIDAY) {
            return "vendredi";
        } else if (dayOfWeek == Calendar.SATURDAY) {
            return "samedi";
        } else {
            return "";
        }
    }

    private static DateFormat getDateFormat() {
        final String messageDateFormat = "dd MMMM yyyy '\u00e0' HH'h'mm";

        return new SimpleDateFormat(messageDateFormat, new Locale("fr", "FR"));
    }
}
