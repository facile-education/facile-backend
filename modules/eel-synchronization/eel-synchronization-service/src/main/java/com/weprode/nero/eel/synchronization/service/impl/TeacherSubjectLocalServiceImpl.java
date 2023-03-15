package com.weprode.nero.eel.synchronization.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.eel.synchronization.model.Subject;
import com.weprode.nero.eel.synchronization.model.TeacherSubject;
import com.weprode.nero.eel.synchronization.service.SubjectLocalServiceUtil;
import com.weprode.nero.eel.synchronization.service.TeacherSubjectLocalServiceUtil;
import com.weprode.nero.eel.synchronization.service.base.TeacherSubjectLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.user.model.TeacherSubject",
        service = AopService.class
)
public class TeacherSubjectLocalServiceImpl extends TeacherSubjectLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(TeacherSubjectLocalServiceImpl.class);

    /**
     * Returns true if the mapping has been created, false if it already exists
     */
    public TeacherSubject addTeacherSubjectInSchool(long teacherId, long subjectId, long schoolId) {

        try {
            List<TeacherSubject> teacherSubjectList = teacherSubjectPersistence.findByteacherId(teacherId);
            for (TeacherSubject teacherSubject : teacherSubjectList) {
                if (teacherSubject.getSchoolId() == schoolId && teacherSubject.getSubjectId() == subjectId) {
                    return teacherSubject;
                }
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        // Create if needed
        try {
            long teacherSubjectId = counterLocalService.increment();
            TeacherSubject teacherSubject = teacherSubjectPersistence.create(teacherSubjectId);
            teacherSubject.setTeacherId(teacherId);
            teacherSubject.setSubjectId(subjectId);
            teacherSubject.setSchoolId(schoolId);
            teacherSubject = teacherSubjectPersistence.update(teacherSubject);

            return teacherSubject;
        } catch (Exception e) {
            logger.debug(e);
        }

        return null;
    }

    public List<String> getTeacherSubjects(long teacherId) {
        List<String> teacherSubjects = new ArrayList<>();

        try {
            List<TeacherSubject> teacherSubjectList = teacherSubjectPersistence.findByteacherId(teacherId);
            if (teacherSubjectList != null) {
                for (TeacherSubject teacherSubject : teacherSubjectList) {
                    Subject subject = SubjectLocalServiceUtil.getSubject(teacherSubject.getSubjectId());
                    teacherSubjects.add(subject.getName());
                }
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return teacherSubjects;
    }

    public String getTeacherSubjectList(User teacher) {
        List<String> subjectList = TeacherSubjectLocalServiceUtil.getTeacherSubjects(teacher.getUserId());

        // Generate the string
        StringBuilder subjectsAsString = new StringBuilder();
        for (String subjectStr : subjectList) {
            subjectsAsString.append(subjectStr).append(", ");
        }
        if (subjectsAsString.length() > 1) {
            subjectsAsString = new StringBuilder(subjectsAsString.substring(0, subjectsAsString.length() - 2));
        }

        return subjectsAsString.toString();
    }

}
