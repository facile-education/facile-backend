package com.weprode.facile.course.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.course.model.SessionContent;
import com.weprode.facile.course.model.impl.SessionContentImpl;
import com.weprode.facile.course.service.persistence.SessionContentFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component(service = SessionContentFinder.class)
public class SessionContentFinderImpl extends SessionContentFinderBaseImpl
        implements SessionContentFinder {

    private static final Log logger = LogFactoryUtil.getLog(SessionContentFinderImpl.class);
    @Reference
    private CustomSQL customSQL;

    public static final String GET_COURSE_CONTENTS = "getCourseContents";

    public List<SessionContent> getCourseContents(long courseId, Date minDate, Date maxDate) throws SystemException {

        Session session = null;
        try {
            session = openSession();

            String sql = customSQL.get(getClass(), GET_COURSE_CONTENTS);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Course_SessionContent", SessionContentImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            qPos.add(courseId);
            qPos.add(sdf.format(minDate));
            qPos.add(sdf.format(maxDate));

            return (List<SessionContent>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while fetching session contents for course " + courseId, e);
        } finally {
            closeSession(session);
        }
        return null;
    }

}
