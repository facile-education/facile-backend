package com.weprode.facile.agenda.service.persistence.impl;

import com.liferay.petra.string.StringUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.agenda.model.Event;
import com.weprode.facile.agenda.model.impl.EventImpl;
import com.weprode.facile.agenda.service.persistence.EventFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component(service = EventFinder.class)
public class EventFinderImpl extends EventFinderBaseImpl
        implements EventFinder {
    private static final Log logger = LogFactoryUtil.getLog(EventFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    public static final String GET_USER_EVENTS = EventFinder.class.getName() + ".getUserEvents";
    public static final String GET_SCHOOL_EVENTS = EventFinder.class.getName() + ".getSchoolEvents";
    public static final String COUNT_USER_EVENTS = EventFinder.class.getName() + ".countUserEvents";
    public static final String COUNT_SCHOOL_EVENTS = EventFinder.class.getName() + ".countSchoolEvents";

    public List<Event> getUserEvents(long userId, int startIndex, int nbEvents, List<Long> groupIds, List<Long> roleIds, boolean unreadOnly) {
        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), GET_USER_EVENTS);
            sql = StringUtil.replace(sql, "[$GROUP_IDS$]", buildIdList(groupIds));
            sql = StringUtil.replace(sql, "[$ROLE_IDS$]", buildIdList(roleIds));

            String other = "";
            if (unreadOnly) {
                other = " AND event.eventId NOT IN (SELECT read_.eventId FROM Agenda_EventRead read_ WHERE userId = " + userId + ")";
            }
            sql = StringUtil.replace(sql, "[$OTHER$]", other);

            logger.debug("Agenda events sql = " + sql);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Agenda_Event", EventImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));
            qPos.add(userId);
            qPos.add(startIndex);
            qPos.add(nbEvents);

            return (List<Event>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while fetching agenda events for user " + userId + " from index " + startIndex, e);
        } finally {
            closeSession(session);
        }

        return Collections.emptyList();
    }

    public List<Event> getSchoolEvents(long userId, int startIndex, int nbEvents, List<Long> schoolIds, boolean unreadOnly) {
        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), GET_SCHOOL_EVENTS);
            sql = StringUtil.replace(sql, "[$SCHOOL_IDS$]", buildIdList(schoolIds));

            String other = "";
            if (unreadOnly) {
                other = " AND event.eventId NOT IN (SELECT read_.eventId FROM Agenda_EventRead read_ WHERE userId = " + userId + ")";
            }
            sql = StringUtil.replace(sql, "[$OTHER$]", other);

            logger.debug("Agenda events sql = " + sql);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Agenda_Event", EventImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));
            qPos.add(userId);
            qPos.add(startIndex);
            qPos.add(nbEvents);

            return (List<Event>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while fetching agenda events for user " + userId + " from index " + startIndex, e);
        } finally {
            closeSession(session);
        }

        return Collections.emptyList();
    }

    public int countEvents(long userId, Date minDate, List<Long> groupIds, List<Long> roleIds, boolean unreadOnly) {
        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), COUNT_USER_EVENTS);
            sql = StringUtil.replace(sql, "[$GROUP_IDS$]", buildIdList(groupIds));
            sql = StringUtil.replace(sql, "[$ROLE_IDS$]", buildIdList(roleIds));

            String other = "";
            if (unreadOnly) {
                other = " AND event.eventId NOT IN (SELECT read_.eventId FROM Agenda_EventRead read_ WHERE userId = " + userId + ")";
            }
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(minDate));
            qPos.add(userId);

            return ((BigInteger) q.uniqueResult()).intValue();
        } catch (Exception e) {
            logger.error("Error while fetching agenda events for user " + userId + " from " + minDate, e);
        } finally {
            closeSession(session);
        }

        return 0;
    }

    public int countSchoolEvents(long userId, Date minDate, List<Long> schoolIds, boolean unreadOnly) {
        Session session = null;

        try {
            session = openSession();

            String sql = customSQL.get(getClass(), COUNT_SCHOOL_EVENTS);
            sql = StringUtil.replace(sql, "[$SCHOOL_IDS$]", buildIdList(schoolIds));

            String other = "";
            if (unreadOnly) {
                other = " AND event.eventId NOT IN (SELECT read_.eventId FROM Agenda_EventRead read_ WHERE userId = " + userId + ")";
            }
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(minDate));
            qPos.add(userId);

            return ((BigInteger) q.uniqueResult()).intValue();
        } catch (Exception e) {
            logger.error("Error while fetching agenda events for user " + userId + " from " + minDate, e);
        } finally {
            closeSession(session);
        }

        return 0;
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
