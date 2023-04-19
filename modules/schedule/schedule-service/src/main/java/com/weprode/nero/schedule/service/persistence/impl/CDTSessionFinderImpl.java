package com.weprode.nero.schedule.service.persistence.impl;

import com.liferay.petra.string.StringUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.impl.CDTSessionImpl;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.persistence.CDTSessionFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(service = CDTSessionFinder.class)
public class CDTSessionFinderImpl extends CDTSessionFinderBaseImpl
        implements CDTSessionFinder {

    private static final Log logger = LogFactoryUtil.getLog(CDTSessionFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    public static final String FIND_BY_TEACHERID_RANGEDATE =
            CDTSessionFinder.class.getName() +
                    ".findByTeacherId_RangeDate";

    public static final String FIND_BY_STUDENTID_RANGEDATE =
            CDTSessionFinder.class.getName() +
                    ".findByStudentId_RangeDate";

    public static final String GET_SESSION_ACTIVITY =
            CDTSessionFinder.class.getName() +
                    ".getSessionActivity";

    public List<CDTSession> getTeacherSessions(long teacherId, Date minDate, Date maxDate) {
        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), FIND_BY_TEACHERID_RANGEDATE);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Schedule_CDTSession", CDTSessionImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            qPos.add(sdf.format(minDate));
            qPos.add(sdf.format(maxDate));
            qPos.add(teacherId);

            return (List<CDTSession>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while finding sessions attached to teacher "+teacherId+" in date range", e);
        } finally {
            closeSession(session);
        }

        return null;
    }

    public List<CDTSession> getStudentSpecificSessions(long studentId, Date minDate, Date maxDate) {
        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), FIND_BY_STUDENTID_RANGEDATE);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Schedule_CDTSession", CDTSessionImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            qPos.add(sdf.format(minDate));
            qPos.add(sdf.format(maxDate));
            qPos.add(studentId);

            return (List<CDTSession>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while finding sessions attached to student "+studentId+" in date range", e);
        } finally {
            closeSession(session);
        }

        return null;
    }

    public List<CDTSession> getSessionActivity(long userId, List<Long> groupIds, Date minDate, Date maxDate) {
        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), GET_SESSION_ACTIVITY);

            // Build group id list
            StringBuilder groupIdsStr = new StringBuilder();
            for (int i = 0 ; i < groupIds.size() ; i++) {
                groupIdsStr.append(groupIds.get(i));
                if (i != (groupIds.size() - 1)) {
                    groupIdsStr.append(",");
                }
            }
            sql = StringUtil.replace(sql, "[$GROUP_IDS$]", groupIdsStr.toString());

            // Add teacher filter if not full history
            if (userId != 0) {
                sql += " AND sessionTeacher.teacherId != " + userId;
            }

            logger.debug("CDTSessionFinderImpl : sql="+sql);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Schedule_CDTSession", CDTSessionImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            qPos.add(sdf.format(minDate));
            qPos.add(sdf.format(maxDate));

            return (List<CDTSession>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while finding session activity for user "+userId, e);
        } finally {
            closeSession(session);
        }

        return new ArrayList<>();
    }

    public List<CDTSession> getSchoolSessions(Long schoolId, Date startDate, Date endDate) throws SystemException {
        List<CDTSession> sessionsForSchool = new ArrayList<>();

        Session session = null;
        try {
            session = openSession();
            ClassLoader classLoader = getClass().getClassLoader();

            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CDTSession.class, classLoader);
            Property schoolIdProperty = PropertyFactoryUtil.forName("schoolId");
            Property sessionStartProperty = PropertyFactoryUtil.forName("sessionStart");

            if (schoolId > 0) {
                dynamicQuery.add(schoolIdProperty.eq(schoolId));
                dynamicQuery.add(sessionStartProperty.between(startDate, endDate));

                sessionsForSchool.addAll(CDTSessionLocalServiceUtil.dynamicQuery(dynamicQuery));
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            closeSession(session);
        }

        return sessionsForSchool;
    }

    /**
     * Get the session for a list of group id that begin between 2 dates
     */
    public List<CDTSession> getGroupsSessions(List<Long> groupIds, Date startDate, Date endDate) throws SystemException {
        List<CDTSession> sessionsForGroups = new ArrayList<>();

        Session session = null;
        try {
            session = openSession();
            ClassLoader classLoader = getClass().getClassLoader();

            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CDTSession.class, classLoader);
            Disjunction disjunctionOrgsId = RestrictionsFactoryUtil.disjunction();
            Property groupIdProperty = PropertyFactoryUtil.forName("groupId");
            Property sessionStartProperty = PropertyFactoryUtil.forName("sessionStart");

            if (groupIds != null && !groupIds.isEmpty()) {

                for (Long groupId : groupIds) {
                    disjunctionOrgsId.add(groupIdProperty.eq(groupId));
                }
                dynamicQuery.add(disjunctionOrgsId);
                dynamicQuery.add(sessionStartProperty.between(startDate, endDate));

                sessionsForGroups.addAll(CDTSessionLocalServiceUtil.dynamicQuery(dynamicQuery));
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            closeSession(session);
        }

        return sessionsForGroups;
    }
}
