package com.weprode.facile.course.service.persistence.impl;

import com.liferay.petra.string.StringUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.course.model.Homework;
import com.weprode.facile.course.model.impl.HomeworkImpl;
import com.weprode.facile.course.service.persistence.HomeworkFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component(service = HomeworkFinder.class)
public class HomeworkFinderImpl extends HomeworkFinderBaseImpl
        implements HomeworkFinder {

    private static final Log logger = LogFactoryUtil.getLog(HomeworkFinderImpl.class);

    public static final String GET_TEACHER_HOMEWORKS = "getTeacherHomeworksToCorrect";
    public static final String GET_STUDENT_HOMEWORKS = "getStudentHomeworks";
    public static final String GET_STUDENTS_HOMEWORKS = "getStudentsHomeworks";
    public static final String COUNT_UNDONE_HOMEWORKS = "countUndoneHomeworks";
    public static final String GET_STUDENT_HOMEWORK_ACTIVITY = "getStudentHomeworkActivity";


    @Reference
    private CustomSQL customSQL;

    public List<Homework> getTeacherHomeworksToCorrect(long teacherId, long courseId) throws SystemException {

        Session session = null;

        try {
            session = openSession();

            String other = "";
            if (courseId != 0) {
                other += " AND homework.courseId = " + courseId;
            }
            String sql = customSQL.get(getClass(), GET_TEACHER_HOMEWORKS);
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
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

    public List<Homework> getStudentHomeworks (long studentId, Date minDate, Date maxDate, boolean undoneOnly) {

        Session session = null;

        try {
            session = openSession();

            String other = "";
            if (undoneOnly) {
                other += " AND studentHomework.isDone = 0";
            }

            String sql = customSQL.get(getClass(), GET_STUDENT_HOMEWORKS);
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Course_Homework", HomeworkImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(minDate);
            qPos.add(maxDate);
            qPos.add(studentId);

            return (List<Homework>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while finding homeworks for student " + studentId + " in date range", e);
        } finally {
            closeSession(session);
        }
        return new ArrayList<>();
    }

    public List<Homework> getStudentsHomeworks (List<Long> studentIds, Date minDate, Date maxDate) {

        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), GET_STUDENTS_HOMEWORKS);
            sql = StringUtil.replace(sql, "[$STUDENT_IDS$]", buildIdList(studentIds));

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Course_Homework", HomeworkImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(minDate);
            qPos.add(maxDate);

            return (List<Homework>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while finding homeworks for n students in date range", e);
        } finally {
            closeSession(session);
        }
        return new ArrayList<>();
    }

    public int countUndoneHomeworks (long studentId, Date minDate, Date maxDate) {
        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), COUNT_UNDONE_HOMEWORKS);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addScalar("count", Type.INTEGER);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(studentId);
            qPos.add(minDate);
            qPos.add(maxDate);

            return (Integer) q.uniqueResult();
        } catch (Exception e) {
            logger.error("Error while counting undone homeworks for student " + studentId, e);
        } finally {
            closeSession(session);
        }

        return 0;
    }

    public List<Homework> getStudentHomeworkActivity (long studentId, List<Long> groupIds, Date minDate, Date maxDate) {

        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), GET_STUDENT_HOMEWORK_ACTIVITY);
            sql = StringUtil.replace(sql, "[$GROUP_IDS$]", buildIdList(groupIds));
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Course_Homework", HomeworkImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            qPos.add(sdf.format(minDate));
            qPos.add(sdf.format(maxDate));
            qPos.add(studentId);

            return (List<Homework>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while finding homeworks for student " + studentId + " in date range", e);
        } finally {
            closeSession(session);
        }
        return new ArrayList<>();

    }

    private String buildIdList(List<Long> groupIds) {
        StringBuilder groupIdsStr = new StringBuilder();

        for (int i = 0 ; i < groupIds.size() ; i++) {
            groupIdsStr.append(groupIds.get(i));
            if (i != (groupIds.size() - 1)) {
                groupIdsStr.append(",");
            }
        }

        return groupIdsStr.toString();
    }

}
