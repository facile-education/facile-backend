package com.weprode.nero.course.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.model.impl.HomeworkImpl;
import com.weprode.nero.course.service.HomeworkLocalServiceUtil;
import com.weprode.nero.course.service.persistence.HomeworkFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component(service = HomeworkFinder.class)
public class HomeworkFinderImpl extends HomeworkFinderBaseImpl
        implements HomeworkFinder {

    private static final Log logger = LogFactoryUtil.getLog(HomeworkFinderImpl.class);

    public static final String FIND_TEACHER_HOMEWORKS = "getTeacherHomeworksToCorrect";

    @Reference
    private CustomSQL customSQL;

    public List<Homework> getTeacherHomeworksToCorrect(long teacherId) throws SystemException {

        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), FIND_TEACHER_HOMEWORKS);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Course_Homework", HomeworkImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(teacherId);

            return (List<Homework>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while finding homeworks to correct for teacher " + teacherId + " in date range", e);
        } finally {
            closeSession(session);
        }

        return null;
    }

    public List<Homework> getStudentHomeworksFromGroupIds (List<Long> studentGroupIdList, Date minDate, Date maxDate) {
        List<Homework> homeworkList = new ArrayList<>();

        // Global criterion is
        // WHERE groupId in <groupIdList>
        // AND targetDate >= minDate
        // AND target Date <= maxDate
        ClassLoader classLoader = getClass().getClassLoader();

        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Homework.class, "homework", classLoader);
        Criterion criterion;

        // GroupId list criterion
        Criterion groupIdCriterion = null;
        for (Long groupId : studentGroupIdList){
            if(groupIdCriterion != null){
                groupIdCriterion = RestrictionsFactoryUtil.or(groupIdCriterion , RestrictionsFactoryUtil.eq("homework.groupId",groupId));
            }
            else{
                groupIdCriterion = RestrictionsFactoryUtil.eq("homework.groupId",groupId);
            }
        }

        Criterion betweenDatesCriterion = RestrictionsFactoryUtil.between("homework.targetDate", minDate, maxDate);

        criterion = RestrictionsFactoryUtil.and(groupIdCriterion, betweenDatesCriterion);

        if (criterion != null) {
            dynamicQuery.add(criterion);

            // Run the dynamic query
            homeworkList.addAll(HomeworkLocalServiceUtil.dynamicQuery(dynamicQuery));
        }

        return homeworkList;
    }
}
