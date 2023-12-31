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

package com.weprode.facile.school.life.utils;

import com.liferay.petra.string.StringPool;
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

    private static final String HTML_LINE_BREAK = "</br>";
    private static final String GREETING = "Meilleurs messages," + HTML_LINE_BREAK;

    public static void notifyRenvoiRegistration(long teacherId, long sourceTeacherId, long sourceSessionId, long sourceSchoollifeSessionId, long studentId, long schoollifeSessionId) {

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            User sourceTeacher = UserLocalServiceUtil.getUser(sourceTeacherId);

            // Recipients:
            // - source teachers
            // - his doyens if not the source teacher nor the teacher
            // - main teacher if not the source teacher nor the teacher
            // - co-teachers
            List<Long> recipientList = new ArrayList<>();

            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            // Build notification subject and content
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            DateFormat df = getDateFormat();
            String subject = "Avis de renvoi : " + student.getFullName() + " le " + df.format(session.getStartDate());
            String content = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Je vous informe que l'\u00e9l\u00e8ve " + student.getFullName() + " s'est pr\u00e9sent\u00e9.e en salle de renvoi le " + df.format(new Date()) + "."+ HTML_LINE_BREAK;
            content += "L'\u00e9l\u00e8ve m'a indiqu\u00e9 \u00eatre renvoy\u00e9.e ";
            if (sourceSessionId != 0) {
                CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(sourceSessionId);
                // If co-teaching, add other co-teachers
                List<Long> sessionTeacherIds = SessionTeacherLocalServiceUtil.getTeacherIds(sourceSessionId);
                for (Long sessionTeacherId : sessionTeacherIds) {
                    if (!recipientList.contains(sessionTeacherId) && sessionTeacherId != teacherId) {
                        recipientList.add(sessionTeacherId);
                    }
                }
                String coursName = GroupUtilsLocalServiceUtil.getGroupName(cdtSession.getGroupId());
                content += "du cours " + coursName + " dispens\u00e9 par " + sourceTeacher.getFullName() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
            } else {
                content += "du cr\u00e9neau " + SchoollifeSessionLocalServiceUtil.getSessionName(sourceSchoollifeSessionId) + " encadr\u00e9 par " + sourceTeacher.getFullName() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
            }
            content += GREETING + teacher.getFullName();

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for renvoi", e);
        }
    }

    public static void notifyRenvoiUnregistration(long teacherId, long sourceTeacherId, long sourceSessionId, long sourceSchoollifeSessionId, long studentId, long schoollifeSessionId) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            User sourceTeacher = UserLocalServiceUtil.getUser(sourceTeacherId);

            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Annulation de renvoi : " + student.getFullName() + " le " + slotName;

            // Send notification to the source's teacher, the student's doyens and the student's main teacher if not the teacher + co-teachers
            List<Long> recipientList = new ArrayList<>();

            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            if (!recipientList.isEmpty()) {
                String content = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Je vous informe de l'annuation du renvoi de l'\u00e9l\u00e8ve " + student.getFullName();

                if (sourceSessionId != 0) {
                    CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(sourceSessionId);
                    // If co-teaching, add other co-teachers
                    List<Long> sessionTeacherIds = SessionTeacherLocalServiceUtil.getTeacherIds(sourceSessionId);
                    for (Long sessionTeacherId : sessionTeacherIds) {
                        if (!recipientList.contains(sessionTeacherId) && sessionTeacherId != teacherId) {
                            recipientList.add(sessionTeacherId);
                        }
                    }

                    String coursName = GroupUtilsLocalServiceUtil.getGroupName(cdtSession.getGroupId());
                    content += " du cours " + coursName + " dispens\u00e9 par " + sourceTeacher.getFullName() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
                } else {
                    content += " du cr\u00e9neau " + SchoollifeSessionLocalServiceUtil.getSessionName(sourceSchoollifeSessionId) + " encadr\u00e9 par " + sourceTeacher.getFullName() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
                }

                content += GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);
            }
        } catch (Exception e) {
            logger.error("Error sending notification for renvoi", e);
        }
    }

    public static void notifyRenvoiReason(long teacherId, long studentId, long schoollifeSessionId, String reason) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);

            // Send notification to the parents
            List<Long> recipientList = new ArrayList<>();

            List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
            for (User parent : parents) {
                recipientList.add(parent.getUserId());
            }

            RenvoiPK pk = new RenvoiPK(schoollifeSessionId, studentId);
            Renvoi renvoi = RenvoiLocalServiceUtil.getRenvoi(pk);

            String sourceSession = "";
            Date sourceDate;
            if (renvoi.getSourceSessionId() != 0) {
                CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(renvoi.getSourceSessionId());
                sourceDate = cdtSession.getStart();
                String coursName = GroupUtilsLocalServiceUtil.getGroupName(cdtSession.getGroupId());
                sourceSession = " du cours de " + coursName;
            } else {
                sourceDate = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(renvoi.getSourceSchoollifeSessionId()).getStartDate();
                sourceSession = " du cr\u00e9neau de " + SchoollifeSessionLocalServiceUtil.getSessionName(renvoi.getSourceSchoollifeSessionId());
            }

            String subject = "Avis de renvoi";

            DateFormat df = getDateFormat();
            String parentContent = "Ch\u00e8re Madame, Cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Je suis au regret de vous informer que j'ai renvoy\u00e9 " + student.getFullName()
                    + sourceSession
                    + " le " + df.format(sourceDate)
                    + " pour le motif suivant:" + HTML_LINE_BREAK + reason + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + "Je me tiens, bien entendu, \u00e0 votre disposition pour discuter de la situation de votre enfant." + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);

            // Send notification to the student's doyens and the student's main teacher (and psychologist and social counselor) if not the source teacher + co-teachers
            recipientList = new ArrayList<>();
            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            List<User> conseillerSociaux = UserUtilsLocalServiceUtil.getUserConseillersSociaux(student);
            for (User conseillerSocial : conseillerSociaux) {
                if (!recipientList.contains(conseillerSocial.getUserId()) && conseillerSocial.getUserId() != teacherId) {
                    recipientList.add(conseillerSocial.getUserId());
                }
            }

            List<User> psychologists = UserUtilsLocalServiceUtil.getUserPsychologues(student);
            for (User psychologist : psychologists) {
                if (!recipientList.contains(psychologist.getUserId()) && psychologist.getUserId() != teacherId) {
                    recipientList.add(psychologist.getUserId());
                }
            }

            if (renvoi.getSourceSessionId() != 0) {
                // If co-teaching, add other co-teachers
                List<Long> sessionTeacherIds = SessionTeacherLocalServiceUtil.getTeacherIds(renvoi.getSourceSessionId());
                for (Long sessionTeacherId : sessionTeacherIds) {
                    if (!recipientList.contains(sessionTeacherId) && sessionTeacherId != teacherId) {
                        recipientList.add(sessionTeacherId);
                    }
                }
            }

            if (!recipientList.isEmpty()) {

                String teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "J'ai renvoy\u00e9 l'\u00e9l\u00e8ve " + student.getFullName()
                        + sourceSession
                        + " le " + df.format(renvoi.getRenvoiDate())
                        + " pour le motif suivant:</br>" + reason + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }
        } catch (Exception e) {
            logger.error("Error sending notification for renvoi", e);
        }
    }

    public static void notifyRetenueRegistration(long teacherId, long studentId, long schoollifeSessionId, String comment, boolean notifyParents) {
        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

            // Send notification to the student
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(studentId);

            DateFormat df = getDateFormat();
            String subject = "Retenue le " + df.format(session.getStartDate());

            String studentContent = student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous serez en retenue le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK;
            if (!comment.isEmpty()) {
                studentContent += "Motif : " + comment + HTML_LINE_BREAK;
            }
            studentContent += HTML_LINE_BREAK + GREETING + teacher.getFullName();

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, studentContent, MessagingConstants.TYPE_HHC);

            // Send notification to his parents if selected
            if (notifyParents) {
                recipientList = new ArrayList<>();
                List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
                for (User parent : parents) {
                    recipientList.add(parent.getUserId());
                }
                String parentContent = "Ch\u00e8re Madame, cher Monsieur, " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Votre enfant " + student.getFullName() + " sera en retenue le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + ".</br>";
                if (!comment.isEmpty()) {
                    parentContent += "Motif : " + comment + HTML_LINE_BREAK;
                }
                parentContent += HTML_LINE_BREAK + GREETING + teacher.getFullName();
                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }

            // Send notification to his doyens and main teacher if not the teacher
            recipientList = new ArrayList<>();
            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            if (!recipientList.isEmpty()) {
                String teacherContent = "Cher.e.s coll\u00e8gue.s, " + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve " + student.getFullName() + " sera en retenue le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + ".</br>";
                if (!comment.isEmpty()) {
                    teacherContent += "Motif : " + comment + HTML_LINE_BREAK;
                }
                teacherContent += HTML_LINE_BREAK + GREETING + teacher.getFullName();
                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }


        } catch (Exception e) {
            logger.error("Error sending notification for retenue", e);
        }
    }

    public static void notifyRetenueUnregistration(long teacherId, long studentId, long schoollifeSessionId, boolean notifyParents) {

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);

            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

            // Send notification to the student
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(studentId);

            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Annulation retenue du " + slotName;

            DateFormat df = getDateFormat();
            String content = "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "La retenue du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9e." + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);

            // Send notification to student's parents if selected
            if (notifyParents) {
                recipientList = new ArrayList<>();
                List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
                for (User parent : parents) {
                    recipientList.add(parent.getUserId());
                }
                String parentContent = "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "La retenue de votre enfant " + student.getFullName() + " du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9e." + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }
            
            // Send notification to the student's doyen and the student's main teacher if not the teacher
            recipientList = new ArrayList<>();
            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            if (!recipientList.isEmpty()) {

                String teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "La retenue de l'\u00e9l\u00e8ve " + student.getFullName() + " du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9e." + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }
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
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(sessionStudent.getSourceTeacherId());

            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId())) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId())) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            String subject = "Retenue - Absence de " + student.getFullName();

            DateFormat df = getDateFormat();
            String content = "Cher.e.s coll\u00e8gue.s, " + HTML_LINE_BREAK + HTML_LINE_BREAK + student.getFullName() + " ne s'est pas pr\u00e9sent\u00e9.e en retenue le " + df.format(session.getStartDate()) + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
            content += GREETING + watchTeacher.getFullName();

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
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

            // Recipients:
            // - student
            // his parents if selected
            // - his doyens if not the teacher
            // - main teacher if not the source teacher
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(studentId);

            DateFormat df = getDateFormat();
            String subject = "Travail \u00e0 refaire le " + df.format(session.getStartDate());

            String studentContent = "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous \u00eates convoqu\u00e9.e pour un travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, studentContent, MessagingConstants.TYPE_HHC);

            if (notifyParents) {
                recipientList = new ArrayList<>();
                List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
                for (User parent : parents) {
                    recipientList.add(parent.getUserId());
                }
                String parentContent = "Ch\u00e8re Madame, cher Monsieur,"
                        + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Votre enfant " + student.getFullName() + " est convoqu\u00e9.e pour un travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }

            recipientList = new ArrayList<>();
            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            if (!recipientList.isEmpty()) {

                String teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + student.getFullName() + " est convoqu\u00e9.e pour un travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }
        } catch (Exception e) {
            logger.error("Error sending notification for travaux", e);
        }
    }

    public static void notifyTravauxUnregistration(long teacherId, long studentId, long schoollifeSessionId, boolean notifyParents, SessionStudent studentSession) {

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            User teacher = UserLocalServiceUtil.getUser(teacherId);

            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());

            // Send notification to the student
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(studentId);

            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Annulation travail \u00e0 refaire en " + studentSession.getSubject() + " le " + slotName;

            DateFormat df = getDateFormat();
            String content = "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);

            // Send notification to student's parents if selected
            if (notifyParents) {
                recipientList = new ArrayList<>();
                List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
                for (User parent : parents) {
                    recipientList.add(parent.getUserId());
                }
                String parentContent = "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " par votre enfant " + student.getFullName() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }


            // Send notification to the student's doyens and the student's main teacher if not the teacher
            recipientList = new ArrayList<>();
            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            if (!recipientList.isEmpty()) {

                String teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le travail \u00e0 refaire en " + studentSession.getSubject() + " le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " par l'\u00e9l\u00e8ve " + student.getFullName() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }
        } catch (Exception e) {
            logger.error("Error sending notification for travail a refaire", e);
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
            // - source teacher if not the watcher teacher
            // - doyens if not the source teacher and not the watcher teacher
            // - main teacher if not the source teacher and not the watcher teacher
            List<Long> recipientList = new ArrayList<>();
            if (watchTeacher.getUserId() != sessionStudent.getSourceTeacherId()) {
                recipientList.add(sessionStudent.getSourceTeacherId());
            }

            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != watchTeacher.getUserId()) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != watchTeacher.getUserId()) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            String subject = "Travail \u00e0 refaire - Absence de " + student.getFullName();

            DateFormat df = getDateFormat();
            String content = "Cher.e.s coll\u00e8gue.s, " + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve " + student.getFullName() + " ne s'est pas pr\u00e9sent\u00e9.e au travail \u00e0 refaire de " + sessionStudent.getSubject() + " le " + df.format(session.getStartDate()) + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
            content += GREETING + watchTeacher.getFullName();

            MessageLocalServiceUtil.sendMessage(watchTeacher.getUserId(), recipientList, subject, content, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for travaux absence for studentId="+studentId+", schoollifeSessionId=" + schoollifeSessionId, e);
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

            if (notifyParents) {
                recipientList = new ArrayList<>();
                List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
                for (User parent : parents) {
                    recipientList.add(parent.getUserId());
                }
                String parentContent = "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + "Votre enfant " + student.getFullName() + " s'est pr\u00e9sent\u00e9.e au d\u00e9pannage le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }

            // Send notification to the student's doyens and the student's main teacher if not the teacher
            recipientList = new ArrayList<>();
            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            if (!recipientList.isEmpty()) {

                String teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + student.getFullName() + " s'est pr\u00e9sent\u00e9.e au d\u00e9pannage le " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }
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

            DateFormat df = getDateFormat();
            // Send notification to student's parents if selected
            if (notifyParents) {
                recipientList = new ArrayList<>();
                List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
                for (User parent : parents) {
                    recipientList.add(parent.getUserId());
                }

                String parentContent = "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le d\u00e9pannage de l'\u00e9l\u00e8ve " + student.getFullName() + " du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }

            // Send notification to the student's doyen and the student's main teacher if not the teacher
            recipientList = new ArrayList<>();
            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            if (!recipientList.isEmpty()) {

                String teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Le d\u00e9pannage de l'\u00e9l\u00e8ve " + student.getFullName() + " du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + " est annul\u00e9." + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }
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

            // Send notification to the student
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(studentId);

            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Cercle d'\u00e9tude le " + slotName;

            String content = student.getFullName() + ", "+ HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous \u00eates inscrit.e au cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                    + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                    + GREETING + teacher.getFullName();

            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);

            // Send notification to student's parents if selected
            if (notifyParents) {
                recipientList = new ArrayList<>();
                List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
                for (User parent : parents) {
                    recipientList.add(parent.getUserId());
                }
                String parentContent = "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + "Votre enfant " + student.getFullName() + " est inscrit.e au cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                        + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }

            // Send notification to the student's doyens and the student's main teacher if not the teacher
            recipientList = new ArrayList<>();
            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            if (!recipientList.isEmpty()) {

                String teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve " + student.getFullName() + " est inscrit.e au cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                        + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }
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

            // Send notification to the student
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(studentId);

            Calendar cal = Calendar.getInstance();
            cal.setTime(session.getStartDate());
            String slotName = getFrenchDay(cal) + " \u00e0 " + cal.get(Calendar.HOUR_OF_DAY) + "h" + String.format("%02d", cal.get(Calendar.MINUTE));
            String subject = "Fin du cercle d'\u00e9tude le " + slotName;

            DateFormat df = getDateFormat();
            String content;
            if (allSessions) {
                content = "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous \u00eates d\u00e9sinscrit.e du cercle d'\u00e9tude du " + slotName + " en salle " + slot.getRoom()
                        + " du " + df.format(session.getStartDate()) + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK + ""
                        + GREETING + teacher.getFullName();
            } else {
                content = "Cher.e " + student.getFullName() + ", " + HTML_LINE_BREAK + HTML_LINE_BREAK + "Vous \u00eates d\u00e9sinscrit.e du cercle d'\u00e9tude du " + df.format(session.getStartDate()) + " en salle " + slot.getRoom() + "" + HTML_LINE_BREAK + HTML_LINE_BREAK
                        + GREETING + teacher.getFullName();
            }
            MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, content, MessagingConstants.TYPE_HHC);

            // Send notification to student's parents if selected
            if (notifyParents) {
                recipientList = new ArrayList<>();
                List<User> parents = UserRelationshipLocalServiceUtil.getParents(studentId);
                for (User parent : parents) {
                    recipientList.add(parent.getUserId());
                }
                String parentContent = "";
                if (allSessions) {
                    parentContent = "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Votre enfant " + student.getFullName() + " est d\u00e9sinscrit.e du cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                            + "du " + df.format(session.getStartDate()) + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                            + GREETING + teacher.getFullName();
                } else {
                    parentContent = "Ch\u00e8re Madame, cher Monsieur," + HTML_LINE_BREAK + HTML_LINE_BREAK + "Votre enfant " + student.getFullName() + " est d\u00e9sinscrit.e du cercle d'\u00e9tude du " + df.format(session.getStartDate()) + ", en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                            + GREETING + teacher.getFullName();
                }

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, parentContent, MessagingConstants.TYPE_HHC);
            }

            // Send notification to the student's doyens and the student's main teacher if not the teacher
            recipientList = new ArrayList<>();
            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId()) && doyen.getUserId() != teacherId) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId()) && mainTeacher.getUserId() != teacherId) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            if (!recipientList.isEmpty()) {

                String teacherContent = "";
                if (allSessions) {
                    teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve" + student.getFullName() + " est d\u00e9sinscrit.e du cercle d'\u00e9tude du " + slotName + ", en salle " + slot.getRoom() + ", "
                            + "depuis le " + df.format(session.getStartDate()) + " jusqu'\u00e0 la fin de l'ann\u00e9e scolaire." + HTML_LINE_BREAK + HTML_LINE_BREAK
                            + GREETING + teacher.getFullName();
                } else {
                    teacherContent = "Cher.e.s coll\u00e8gue.s," + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve" + student.getFullName() + " est d\u00e9sinscrit.e du cercle d'\u00e9tude du " + df.format(session.getStartDate()) + ", en salle " + slot.getRoom() + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK
                            + GREETING + teacher.getFullName();
                }

                MessageLocalServiceUtil.sendMessage(teacherId, recipientList, subject, teacherContent, MessagingConstants.TYPE_HHC);
            }
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
            // - doyens if not the source teacher
            // - main teacher if not the source teacher
            List<Long> recipientList = new ArrayList<>();
            recipientList.add(sessionStudent.getSourceTeacherId());

            List<User> doyens = UserUtilsLocalServiceUtil.getUserDoyens(student);
            for (User doyen : doyens) {
                if (!recipientList.contains(doyen.getUserId())) {
                    recipientList.add(doyen.getUserId());
                }
            }

            List<User> mainTeachers = UserUtilsLocalServiceUtil.getStudentMainTeachers(student);
            for (User mainTeacher : mainTeachers) {
                if (!recipientList.contains(mainTeacher.getUserId())) {
                    recipientList.add(mainTeacher.getUserId());
                }
            }

            String subject = "Cercle d'\u00e9tude - Absence de " + student.getFullName();

            DateFormat df = getDateFormat();
            String content = "Cher.e.s coll\u00e8gue.s, " + HTML_LINE_BREAK + HTML_LINE_BREAK + "L'\u00e9l\u00e8ve " + student.getFullName() + " ne s'est pas pr\u00e9sent\u00e9.e au cercle d'\u00e9tude le " + df.format(session.getStartDate()) + StringPool.PERIOD + HTML_LINE_BREAK + HTML_LINE_BREAK;
            content += GREETING + watchTeacher.getFullName();

            MessageLocalServiceUtil.sendMessage(watchTeacher.getUserId(), recipientList, subject, content, MessagingConstants.TYPE_HHC);
        } catch (Exception e) {
            logger.error("Error sending notification for travaux absence for studentId="+studentId+", schoollifeSessionId=" + schoollifeSessionId, e);
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
