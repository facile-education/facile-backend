package com.weprode.nero.school.life.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.group.constants.ActivityConstants;
import com.weprode.nero.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.school.life.constants.SchoollifeConstants;
import com.weprode.nero.school.life.model.Renvoi;
import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.service.RenvoiLocalServiceUtil;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.base.RenvoiLocalServiceBaseImpl;
import com.weprode.nero.school.life.service.persistence.RenvoiPK;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.school.life.model.Renvoi",
        service = AopService.class
)
public class RenvoiLocalServiceImpl extends RenvoiLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(RenvoiLocalServiceImpl.class);

    public Renvoi addRenvoi(long schoollifeSessionId, long teacherId, long sourceTeacherId, long studentId, long sourceSessionId, long sourceSchoollifeSessionId, Date registrationDate) {
        logger.info("Adding a renvoi in schoollifeSessionId=" + schoollifeSessionId + ", teacherId=" + teacherId + ", studentId=" + studentId + "sourceTeacherId=" + sourceTeacherId + ", sourceSessionId=" + sourceSessionId + ", sourceSchoollifeSessionId=" + sourceSchoollifeSessionId);

        try {
            User student = UserLocalServiceUtil.getUser(studentId);
            long schoolId = UserOrgsLocalServiceUtil.getEtabRatachement(student).getOrganizationId();
            RenvoiPK pk = new RenvoiPK(schoollifeSessionId, studentId);

            Renvoi renvoi = renvoiPersistence.create(pk);
            renvoi.setTeacherId(teacherId);
            renvoi.setSourceSessionId(sourceSessionId);
            renvoi.setSourceSchoollifeSessionId(sourceSchoollifeSessionId);
            renvoi.setRenvoiDate(registrationDate);
            renvoi.setReason("");
            renvoi.setSourceTeacherId(sourceTeacherId);
            renvoi.setSchoolId(schoolId);
            // Class Org Id
            List<Organization> studentOrgs = UserOrgsLocalServiceUtil.getUserClasses(student, false);
            if (studentOrgs != null && !studentOrgs.isEmpty()) {
                renvoi.setOrgId(studentOrgs.get(0).getOrganizationId());
            } else {
                renvoi.setOrgId(0);
            }
            renvoi.setStatus(SchoollifeConstants.RENVOI_STATUS_CREATED);
            renvoi = renvoiPersistence.update(renvoi);

            return renvoi;
        } catch (Exception e) {
            logger.error("Error adding a renvoi in schoollifeSessionId=" + schoollifeSessionId + ", teacherId=" + teacherId + ", studentId=" + studentId+", sourceSessionId=" + sourceSessionId + ", sourceSchoollifeSessionId=" + sourceSchoollifeSessionId, e);
        }

        return null;
    }

    public boolean setReason(long schoollifeSessionId, long studentId, String reason) {
        try {
            logger.info("Reason set for renvoi studentId " + studentId + " and schoollifeSessionId "+ schoollifeSessionId);
            RenvoiPK pk = new RenvoiPK(schoollifeSessionId, studentId);

            Renvoi renvoi = renvoiPersistence.findByPrimaryKey(pk);
            renvoi.setReason(reason);
            renvoi.setStatus(SchoollifeConstants.RENVOI_STATUS_CLOSED);
            renvoiPersistence.update(renvoi);

            return true;
        } catch (Exception e) {
            logger.error("Error setting a renvoi reason in schoollifeSessionId=" + schoollifeSessionId + ", studentId=" + studentId, e);
        }

        return false;
    }

    public boolean removeRenvoi (long schoollifeSessionId, long studentId) {
        try {
            RenvoiPK pk = new RenvoiPK(schoollifeSessionId, studentId);
            renvoiPersistence.remove(pk);
            logger.info("Removed renvoi for studentId " + studentId + " and schoollifeSessionId "+ schoollifeSessionId);

            return true;
        } catch (Exception e) {
            logger.error("Error removing a renvoi schoollifeSessionId=" + schoollifeSessionId + ", studentId=" + studentId, e);
        }

        return false;
    }

    public List<Renvoi> getSchoolRenvois(long schoolId, Date minDate, Date maxDate) {

        List<Renvoi> schoolRenvois = new ArrayList<>();
        try {
            List<Renvoi> renvois = renvoiPersistence.findByschoolId(schoolId);
            if (renvois != null) {
                for (Renvoi renvoi : renvois) {
                    if (renvoi.getRenvoiDate().after(minDate) && renvoi.getRenvoiDate().before(maxDate)) {
                        schoolRenvois.add(renvoi);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching pending renvois", e);
        }

        return schoolRenvois;
    }

    public List<Renvoi> getTeacherPendingRenvois(long teacherId) {
        List<Renvoi> renvois = new ArrayList<>();

        try {
            User teacher = UserLocalServiceUtil.getUser(teacherId);
            if (RoleUtilsLocalServiceUtil.isTeacher(teacher)) {
                renvois = renvoiPersistence.findBysourceTeacherId_status(teacherId, SchoollifeConstants.RENVOI_STATUS_CREATED);
            }
        } catch (Exception e) {
            logger.error("Error fetching pending renvois", e);
        }

        return renvois;
    }

    public JSONObject convertRenvoiToJson(Renvoi renvoi) {
        JSONObject jsonPendingRenvoi = new JSONObject();

        try {
            SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            User student = UserLocalServiceUtil.getUser(renvoi.getStudentId());

            jsonPendingRenvoi.put(JSONConstants.TYPE, ActivityConstants.TYPE_PENDING_RENVOI);
            jsonPendingRenvoi.put(JSONConstants.MODIFICATION_DATE, df.format(renvoi.getRenvoiDate()));
            jsonPendingRenvoi.put(JSONConstants.STUDENT_NAME, student.getFullName());
            jsonPendingRenvoi.put(JSONConstants.TARGET, student.getFullName());
            jsonPendingRenvoi.put(JSONConstants.STUDENT_ID, renvoi.getStudentId());

            if (renvoi.getSourceSessionId() != 0) {
                CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(renvoi.getSourceSessionId());
                jsonPendingRenvoi.put(JSONConstants.SESSION_SUBJECT, cdtSession.getSubject());
                jsonPendingRenvoi.put(JSONConstants.SESSION_DATE, df.format(cdtSession.getSessionStart()));
                jsonPendingRenvoi.put(JSONConstants.SESSION_NAME, GroupLocalServiceUtil.getGroup(cdtSession.getGroupId()).getName());
            } else {
                SchoollifeSession schoollifeSession = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(renvoi.getSourceSchoollifeSessionId());
                jsonPendingRenvoi.put(JSONConstants.SESSION_NAME, SchoollifeSessionLocalServiceUtil.getSessionName(renvoi.getSourceSchoollifeSessionId()));
                jsonPendingRenvoi.put(JSONConstants.SESSION_DATE, df.format(schoollifeSession.getStartDate()));
            }
        } catch (Exception e) {
            logger.error("Error when converting renvoi " + renvoi.getSchoollifeSessionId(), e);
        }

        return jsonPendingRenvoi;
    }

    public List<Renvoi> getDoyenSchoolRenvois(User user, Date minDate, Date maxDate) {
        List<Renvoi> userSchoolRenvois = new ArrayList<>();

        // Limit analysis to main teachers and doyens and co-teachers
        // Skip own renvois
        if (RoleUtilsLocalServiceUtil.isMainTeacher(user) || RoleUtilsLocalServiceUtil.isDoyen(user)) {

            long schoolId = UserOrgsLocalServiceUtil.getEtabRatachement(user).getOrganizationId();
            List<Renvoi> schoolRenvois = RenvoiLocalServiceUtil.getSchoolRenvois(schoolId, minDate, maxDate);
            for (Renvoi schoolRenvoi : schoolRenvois) {

                try {
                    // Get student class
                    long studentClassOrgId = schoolRenvoi.getOrgId();
                    if (studentClassOrgId == 0) {
                        // Student's orgId was added since RS23 only
                        User student = UserLocalServiceUtil.getUser(schoolRenvoi.getStudentId());
                        List<Organization> studentClasses = UserOrgsLocalServiceUtil.getUserClasses(student, false);
                        if (!studentClasses.isEmpty()) {
                            studentClassOrgId = studentClasses.get(0).getOrganizationId();
                        }
                    }
                    if ((RoleUtilsLocalServiceUtil.isMainTeacher(user, studentClassOrgId) ||
                                RoleUtilsLocalServiceUtil.isDoyen(user, studentClassOrgId) ||
                                SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), schoolRenvoi.getSourceSessionId()))
                                && user.getUserId() != schoolRenvoi.getSourceTeacherId()) {
                            userSchoolRenvois.add(schoolRenvoi);
                        }

                } catch (Exception e) {
                    logger.error("Error while fetching school renvois for user " + user.getUserId(), e);
                }
            }
        }

        return userSchoolRenvois;
    }

    public List<Renvoi> getGroupRenvois(User user, List<Long> groupIds, Date minDate, Date maxDate) {
        List<Renvoi> groupRenvois = new ArrayList<>();

        // Limit analysis to teachers and doyens
        if (RoleUtilsLocalServiceUtil.isTeacher(user) || RoleUtilsLocalServiceUtil.isDoyen(user)) {

            try {
                // Execute the query
                List<Renvoi> renvois = renvoiFinder.getGroupRenvois(groupIds, minDate, maxDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

                for (Renvoi renvoi : renvois) {
                    if (user.getUserId() == renvoi.getTeacherId()) {
                        groupRenvois.add(renvoi);
                        continue;
                    }

                    User student = UserLocalServiceUtil.getUser(renvoi.getStudentId());
                    // Get student class
                    List<Organization> studentClasses = UserOrgsLocalServiceUtil.getUserClasses(student, false);
                    if (!studentClasses.isEmpty()) {

                        Organization studentClass = studentClasses.get(0);
                        if (RoleUtilsLocalServiceUtil.isMainTeacher(user, studentClass.getOrganizationId()) || RoleUtilsLocalServiceUtil.isDoyen(user, studentClass.getOrganizationId())) {
                            groupRenvois.add(renvoi);
                        }
                    }
                }

            } catch (Exception e) {
                logger.error("Error while fetching group renvois for user " + user.getUserId(), e);
            }
        }

        return groupRenvois;
    }

    public JSONObject convertSchoolRenvoi(Renvoi schoolRenvoi) {
        JSONObject jsonSchoolRenvoi = new JSONObject();

        try {
            SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

            jsonSchoolRenvoi.put(JSONConstants.TYPE, ActivityConstants.TYPE_SCHOOL_RENVOI);
            jsonSchoolRenvoi.put(JSONConstants.MODIFICATION_DATE, df.format(schoolRenvoi.getRenvoiDate()));

            User student = UserLocalServiceUtil.getUser(schoolRenvoi.getStudentId());
            jsonSchoolRenvoi.put(JSONConstants.STUDENT_NAME, student.getFullName());
            jsonSchoolRenvoi.put(JSONConstants.TARGET, student.getFullName());
            jsonSchoolRenvoi.put(JSONConstants.STUDENT_ID, schoolRenvoi.getStudentId());

            if (schoolRenvoi.getSourceSessionId() != 0) {
                CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(schoolRenvoi.getSourceSessionId());
                jsonSchoolRenvoi.put(JSONConstants.SESSION_SUBJECT, cdtSession.getSubject());
                jsonSchoolRenvoi.put(JSONConstants.SESSION_DATE, df.format(cdtSession.getSessionStart()));
                jsonSchoolRenvoi.put(JSONConstants.GROUP_NAME, GroupUtilsLocalServiceUtil.getGroupName(cdtSession.getGroupId()));
            } else {
                SchoollifeSession schoollifeSession = SchoollifeSessionLocalServiceUtil.getSchoollifeSession(schoolRenvoi.getSourceSchoollifeSessionId());
                jsonSchoolRenvoi.put(JSONConstants.SESSION_SUBJECT, SchoollifeSessionLocalServiceUtil.getSessionName(schoolRenvoi.getSourceSchoollifeSessionId()));
                jsonSchoolRenvoi.put(JSONConstants.SESSION_DATE, df.format(schoollifeSession.getStartDate()));
                jsonSchoolRenvoi.put(JSONConstants.GROUP_NAME, SchoollifeSessionLocalServiceUtil.getSessionName(schoolRenvoi.getSourceSchoollifeSessionId()));
            }

            User sourceTeacher = UserLocalServiceUtil.getUser(schoolRenvoi.getSourceTeacherId());
            jsonSchoolRenvoi.put(JSONConstants.TEACHER_NAME, sourceTeacher.getFullName());
            jsonSchoolRenvoi.put(JSONConstants.AUTHOR, sourceTeacher.getFullName());
            jsonSchoolRenvoi.put(JSONConstants.TEACHER_ID, schoolRenvoi.getSourceTeacherId());
        } catch (Exception e) {
            logger.error("Error while converting school renvoi " + schoolRenvoi.getSchoollifeSessionId(), e);
        }

        return jsonSchoolRenvoi;
    }
}
