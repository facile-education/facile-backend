package com.weprode.nero.eel.synchronization.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.eel.synchronization.model.Subject;
import com.weprode.nero.eel.synchronization.service.base.SubjectLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.user.model.Subject",
        service = AopService.class
)
public class SubjectLocalServiceImpl extends SubjectLocalServiceBaseImpl {

    private static Log logger = LogFactoryUtil.getLog(SubjectLocalServiceImpl.class);

    public Subject getOrCreateSubject(String name) {
        Subject subject = null;

        try {
            subject = getSubjectByName(name);
            if (subject != null) {
                return subject;
            } else {
                // Create subject
                subject = createSubject(name);
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return subject;
    }

    public Subject createSubject(String name) {
        try {
            long subjectId = counterLocalService.increment();

            Subject subject = subjectPersistence.create(subjectId);
            subject.setName(name);
            subject = subjectPersistence.update(subject);

            return subject;
        } catch (Exception e) {
            logger.error("Error creating subject with name=" + name, e);
        }

        return null;
    }

    public Subject getSubjectByName(String name) {
        try {
            List<Subject> subjectList = subjectPersistence.findByname(name);
            if (subjectList != null && subjectList.size() > 0) {
                return subjectList.get(0);
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return null;
    }

    public JSONObject getSubjectsJSON() {
        JSONObject ret = new JSONObject();

        JSONArray jsonSubjects = new JSONArray();

        List<Subject> subjectList = new ArrayList<>();
        try {
            subjectList = subjectPersistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        }  catch (Exception e) {
            logger.error("Error while fetching the list of subjects", e);
        }

        for (Subject subject : subjectList) {
            JSONObject jsonSubject = new JSONObject();
            jsonSubject.put(JSONConstants.SUBJECT_ID, subject.getSubjectId());
            jsonSubject.put(JSONConstants.NAME, subject.getName());
            jsonSubjects.put(jsonSubject);
        }

        ret.put(JSONConstants.SUBJECTS, jsonSubjects);
        ret.put(JSONConstants.SUCCESS, true);

        return ret;
    }

}
