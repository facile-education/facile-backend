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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.facile.school.life.constants.SchoollifeConstants;
import com.weprode.facile.school.life.model.SchoollifeSession;
import com.weprode.facile.school.life.model.SchoollifeSlot;
import com.weprode.facile.school.life.model.SessionStudent;
import com.weprode.facile.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.facile.school.life.service.SchoollifeSlotLocalServiceUtil;
import com.weprode.facile.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.facile.school.life.service.base.SessionStudentLocalServiceBaseImpl;
import com.weprode.facile.school.life.service.persistence.SessionStudentPK;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.school.life.model.SessionStudent",
        service = AopService.class
)
public class SessionStudentLocalServiceImpl extends SessionStudentLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(SessionStudentLocalServiceImpl.class);

    public boolean registerStudentToSession(long teacherId, long studentId, long schoollifeSessionId, String comment, String replayTestSubject, boolean notifyParents) {
        logger.info("Registering student " + studentId + " to session " + schoollifeSessionId);
        
        try {
            SchoollifeSession startSession = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            if (startSession.getType() == SchoollifeConstants.TYPE_ETUDE) {
                // Etudes : the student is registered in future sessions and until schoolyear's end date
                List<SchoollifeSession> sessions = SchoollifeSessionLocalServiceUtil.getSlotSessions(startSession.getSchoollifeSlotId());

                for (SchoollifeSession session : sessions) {
                    if (session.getEndDate().after(startSession.getStartDate())) {
                        addStudentToSession(teacherId, studentId, session.getSchoollifeSessionId(), comment, replayTestSubject, notifyParents);
                    }
                }
            } else {

                // The student is registered once
                addStudentToSession(teacherId, studentId, schoollifeSessionId, comment, replayTestSubject, notifyParents);
            }
        } catch (Exception e) {
            logger.error("Error registering student " + studentId + " to schoollife session " + schoollifeSessionId, e);
            return false;
        }
        
        return true;
    }
    
    public boolean unregisterStudentToSession(long studentId, long schoollifeSessionId, boolean allSessions) {
        logger.info("Unregistering student " + studentId + " from session " + schoollifeSessionId);
        
        try {
            SchoollifeSession startSession = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);

            if (startSession.getType() == SchoollifeConstants.TYPE_ETUDE && allSessions) {
                // Etudes : the student is unregistered in future sessions and until schoolyear's end date
                List<SchoollifeSession> sessions = SchoollifeSessionLocalServiceUtil.getSlotSessions(startSession.getSchoollifeSlotId());

                for (SchoollifeSession session : sessions) {
                    if (session.getEndDate().after(startSession.getStartDate())) {
                        removeStudentFromSession(studentId, session.getSchoollifeSessionId());
                    }
                }
            } else {

                // The student is registered once
                removeStudentFromSession(studentId, schoollifeSessionId);
            }
        } catch (Exception e) {
            logger.error("Error registering student " + studentId + " to schoollife session " + schoollifeSessionId, e);
            return false;
        }
        
        return true;
    }


    public SessionStudent addStudentToSession(long teacherId, long studentId, long schoollifeSessionId, String comment, String subject, boolean notifyParents) {
        try {
            // Check that the capacity is not already reached
            SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoollifeSessionId);
            SchoollifeSlot slot = SchoollifeSlotLocalServiceUtil.getSchoollifeSlot(session.getSchoollifeSlotId());
            int nbRegisteredStudents = SessionStudentLocalServiceUtil.getNbRegisteredStudents(session.getSchoollifeSessionId());

            if (nbRegisteredStudents >= slot.getCapacity()) {
                return null;
            } else {
                SessionStudentPK pk = new SessionStudentPK(schoollifeSessionId, studentId);
                SessionStudent sessionStudent = sessionStudentPersistence.create(pk);
                sessionStudent.setIsPresent(false);
                sessionStudent.setComment(comment);
                sessionStudent.setSourceTeacherId(teacherId);
                sessionStudent.setSubject(subject);
                sessionStudent.setNotifyParents(notifyParents);
                sessionStudent = sessionStudentPersistence.update(sessionStudent);
                logger.info("Added student " + studentId + " to schoollife Session " + schoollifeSessionId);

                return sessionStudent;
            }

        } catch (Exception e) {
            logger.error("Error adding student " + studentId + " to schoollife session " + schoollifeSessionId, e);
            return null;
        }
    }

    public List<SessionStudent> getSessionMembers(long schoollifeSessionId) {
        try {
            return sessionStudentPersistence.findByschoollifeSessionId(schoollifeSessionId);
        } catch (Exception e) {
            logger.error("Error fetching student members for schoollife session " + schoollifeSessionId, e);
            return new ArrayList<>();
        }
    }


    public int getNbRegisteredStudents(long schoollifeSessionId) {
        try {
            return sessionStudentPersistence.countByschoollifeSessionId(schoollifeSessionId);
        } catch (Exception e) {
            logger.error("Error counting student members for schoollife session " + schoollifeSessionId, e);
            return 0;
        }
    }

    public boolean markStudentPresent(long schoollifeSessionId, long studentId, boolean isPresent) {
        SessionStudentPK pk = new SessionStudentPK(schoollifeSessionId, studentId);
        
        try {
            SessionStudent sessionStudent = sessionStudentPersistence.findByPrimaryKey(pk);
            if (sessionStudent != null) {
                sessionStudent.setIsPresent(isPresent);
                sessionStudentPersistence.update(sessionStudent);
                logger.info("Student " + studentId + " is marked " + (isPresent ? "present" : "absent") + " for schoollifeSessionId " + schoollifeSessionId);
            }
        } catch (Exception e) {
            logger.error("Error when marking student " + studentId + " present or absent for schoollifeSessionId " + schoollifeSessionId, e);
            return false;
        }
        
        return true;
    }

    public boolean deleteSession(long schoollifeSessionId) {
        try {
            sessionStudentPersistence.removeByschoollifeSessionId(schoollifeSessionId);
            logger.info("Deleted session " + schoollifeSessionId);
        } catch (Exception e) {
            logger.error("Error deleting students when deleting schoollife session " + schoollifeSessionId, e);
            return false;
        }
        
        return true;
    }

    public boolean removeStudentFromSession(long studentId, long schoollifeSessionId) {
        try {
            SessionStudentPK pk = new SessionStudentPK(schoollifeSessionId, studentId);
            sessionStudentPersistence.remove(pk);
            
            logger.info("Removed student " + studentId + " from session " + schoollifeSessionId);
        } catch (Exception e) {
            logger.error("Error removing student " + studentId + " from schoollife session " + schoollifeSessionId, e);
            return false;
        }
        
        return true;
    }

    public JSONArray getStudentSessions(User user, long studentId, Date minDate, Date maxDate) {
        return getStudentSessions(user, studentId, minDate, maxDate, true);
    }

    public JSONArray getStudentSessions(User user, long studentId, Date minDate, Date maxDate, boolean withFired) {
        JSONArray jsonSessions = new JSONArray();
        
        try {
            List<SessionStudent> studentSessions = sessionStudentPersistence.findBystudentId(studentId);
            if (studentSessions != null) {
                for (SessionStudent studentSession : studentSessions) {
                    try {
                        SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(studentSession.getSchoollifeSessionId());
                        if (withFired || session.getType() != SchoollifeConstants.TYPE_RENVOI) {
                            if (!session.getStartDate().before(minDate) && session.getStartDate().before(maxDate)) {
                                jsonSessions.put(SchoollifeSessionLocalServiceUtil.formatSchoollifeSession(session, user));
                            }
                        }
                    } catch (Exception e) {
                        logger.debug(e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching schoollife sessions for student " + studentId, e);
        }
        
        return jsonSessions;
    }

    public List<Long> getAbsentStudents(long schoollifeSessionId) {
        List<Long> absentStudentIds = new ArrayList<>();
        
        try {
            List<SessionStudent> sessionStudents = sessionStudentPersistence.findByschoollifeSessionId(schoollifeSessionId);
            if (sessionStudents != null) {
                for (SessionStudent sessionStudent : sessionStudents) {
                    if (!sessionStudent.isIsPresent()) {
                        absentStudentIds.add(sessionStudent.getStudentId());
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Error fetching absent students from schoollife session " + schoollifeSessionId, e);
        }
        
        return absentStudentIds;
    }

    // Returns a JSONObject with the count for a given student and for the 5 schoollife types
    // Count since current schoolyear's start date
    public JSONObject countStudentSessions(long studentId) {
        JSONObject json = new JSONObject();
        Date minDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate();
        int nbRenvois = 0;
        int nbRetenues = 0;
        int nbTravaux = 0;
        int nbDepannages = 0;
        int nbEtudes = 0;

        List<SessionStudent> sessionStudentList = sessionStudentPersistence.findBystudentId(studentId);
        if (sessionStudentList != null) {
            for (SessionStudent sessionStudent : sessionStudentList) {
                try {
                    SchoollifeSession session = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(sessionStudent.getSchoollifeSessionId());
                    if (session.getStartDate().after(minDate)) {
                        switch (session.getType()) {
                            case SchoollifeConstants.TYPE_RENVOI:
                                nbRenvois++;
                                break;
                            case SchoollifeConstants.TYPE_RETENUE:
                                nbRetenues++;
                                break;
                            case SchoollifeConstants.TYPE_TRAVAUX:
                                nbTravaux++;
                                break;
                            case SchoollifeConstants.TYPE_DEPANNAGE:
                                nbDepannages++;
                                break;
                            case SchoollifeConstants.TYPE_ETUDE:
                                nbEtudes++;
                                break;
                            default:
                                break;
                        }
                    }
                } catch (Exception e) {
                    logger.error("Error processing HHC session for student " + studentId, e);
                }
            }
        }
        json.put(JSONConstants.NB_RENVOIS, nbRenvois);
        json.put(JSONConstants.NB_RETENUES, nbRetenues);
        json.put(JSONConstants.NB_TRAVAUX, nbTravaux);
        json.put(JSONConstants.NB_DEPANNAGES, nbDepannages);
        json.put(JSONConstants.NB_ETUDES, nbEtudes);
        return json;
    }
}
