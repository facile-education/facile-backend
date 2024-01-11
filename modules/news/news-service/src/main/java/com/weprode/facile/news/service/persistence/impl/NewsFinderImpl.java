package com.weprode.facile.news.service.persistence.impl;

import com.liferay.petra.string.StringUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.news.model.News;
import com.weprode.facile.news.model.impl.NewsImpl;
import com.weprode.facile.news.service.persistence.NewsFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component(service = NewsFinder.class)
public class NewsFinderImpl extends NewsFinderBaseImpl
        implements NewsFinder {

    private static final Log logger = LogFactoryUtil.getLog(NewsFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    public static final String GET_NEWS_ACTIVITIES = NewsFinder.class.getName() + ".getNewsActivities";
    public static final String GET_GROUP_ACTIVITIES = NewsFinder.class.getName() + ".getGroupActivities";
    public static final String GET_NEWS = NewsFinder.class.getName() + ".getNews";
    public static final String GET_ALL_SCHOOL_NEWS = NewsFinder.class.getName() + ".getAllSchoolNews";
    public static final String COUNT_NEWS = NewsFinder.class.getName() + ".countNews";
    public static final String COUNT_ALL_SCHOOL_NEWS = NewsFinder.class.getName() + ".countAllSchoolNews";
    public static final String DATE_SEARCH_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public List<News> getNews(long userId, List<Long> groupIds, List<Long> roleIds, Date currentDate, int startIndex, int nbNews, boolean groupNews, boolean importantOnly, boolean unreadOnly) {
        Session session = null;

        try {
            session = openSession();

            String other = " AND news.isSchoolNews = " + (groupNews ? "0" : "1");
            if (importantOnly) {
                other += " AND news.isImportant = 1";
            }
            if (unreadOnly) {
                other += " AND newsread.userId IS NULL";
            }

            String sql = customSQL.get(getClass(), GET_NEWS);
            sql = StringUtil.replace(sql, "[$GROUP_IDS$]", buildIdList(groupIds));
            sql = StringUtil.replace(sql, "[$ROLE_IDS$]", buildIdList(roleIds));
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
            logger.debug("News sql = " + sql);

            SQLQuery q = session.createSQLQuery(sql);
            //q.setCacheable(false);
            q.addEntity("News_News", NewsImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_SEARCH_FORMAT);
            qPos.add(userId);
            qPos.add(sdf.format(currentDate)); // publication Date >= currentDate
            qPos.add(userId);
            qPos.add(sdf.format(currentDate)); // expiration Date < currentDate
            qPos.add(userId);
            qPos.add(startIndex);
            qPos.add(nbNews);

            return (List<News>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while fetching news", e);
        } finally {
            closeSession(session);
        }

        return Collections.emptyList();
    }

    public List<News> getAllSchoolNews(long userId, List<Long> schoolIds, Date currentDate, int startIndex, int nbNews, boolean importantOnly, boolean unreadOnly) {
        Session session = null;

        try {
            session = openSession();

            String other = "";
            if (importantOnly) {
                other += " AND news.isImportant = 1";
            }
            if (unreadOnly) {
                other += " AND newsread.userId IS NULL";
            }

            String sql = customSQL.get(getClass(), GET_ALL_SCHOOL_NEWS);
            sql = StringUtil.replace(sql, "[$SCHOOL_IDS$]", buildIdList(schoolIds));
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
            logger.debug("News sql = " + sql);

            SQLQuery q = session.createSQLQuery(sql);
            //q.setCacheable(false);
            q.addEntity("News_News", NewsImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_SEARCH_FORMAT);
            qPos.add(userId);
            qPos.add(sdf.format(currentDate));
            qPos.add(userId);
            qPos.add(sdf.format(currentDate));
            qPos.add(userId);
            qPos.add(startIndex);
            qPos.add(nbNews);

            return (List<News>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while fetching news", e);
        } finally {
            closeSession(session);
        }

        return Collections.emptyList();
    }

    public int countNews(long userId, List<Long> groupIds, List<Long> roleIds, boolean groupNews, boolean importantOnly, boolean unreadOnly) {
        Session session = null;

        try {
            session = openSession();

            String other = " AND news.isSchoolNews = " + (groupNews ? "0" : "1");
            if (importantOnly) {
                other += " AND news.isImportant = 1";
            }
            if (unreadOnly) {
                other += " AND newsread.userId IS NULL";
            }

            String sql = customSQL.get(getClass(), COUNT_NEWS);
            sql = StringUtil.replace(sql, "[$GROUP_IDS$]", buildIdList(groupIds));
            sql = StringUtil.replace(sql, "[$ROLE_IDS$]", buildIdList(roleIds));
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
            logger.debug("News count sql = " + sql);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addScalar("totalCount", Type.INTEGER);

            QueryPos qPos = QueryPos.getInstance(q);
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_SEARCH_FORMAT);
            qPos.add(userId);
            qPos.add(sdf.format(new Date()));
            qPos.add(sdf.format(new Date()));
            qPos.add(userId);

            return (Integer) q.uniqueResult();

        } catch (Exception e) {
            logger.error("Error while counting news", e);
        } finally {
            closeSession(session);
        }

        return 0;
    }

    public int countAllSchoolNews(long userId, List<Long> schoolIds, boolean importantOnly, boolean unreadOnly) {
        Session session = null;

        try {
            session = openSession();

            String other = "";
            if (importantOnly) {
                other += " AND news.isImportant = 1";
            }
            if (unreadOnly) {
                other += " AND newsread.userId IS NULL";
            }

            String sql = customSQL.get(getClass(), COUNT_ALL_SCHOOL_NEWS);
            sql = StringUtil.replace(sql, "[$SCHOOL_IDS$]", buildIdList(schoolIds));
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
            logger.debug("News count sql = " + sql);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addScalar("totalCount", Type.INTEGER);

            QueryPos qPos = QueryPos.getInstance(q);
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_SEARCH_FORMAT);
            qPos.add(userId);
            qPos.add(sdf.format(new Date()));
            qPos.add(sdf.format(new Date()));
            qPos.add(userId);

            return (Integer) q.uniqueResult();

        } catch (Exception e) {
            logger.error("Error while counting news", e);
        } finally {
            closeSession(session);
        }

        return 0;
    }

    public List<News> getNewsActivities(long userId, List<Long> groupIds, List<Long> roleIds, Date minDate, Date maxDate, int nbNews, boolean groupNewsOnly) {
        Session session = null;

        try {
            session = openSession();

            String other = "";
            if (groupNewsOnly) {
                other += " AND news.isSchoolNews = 0";
            }

            // If maxDate is today, we set AuthorMaxDate to the maximum, to be able to edit news published later in the future
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_SEARCH_FORMAT);
            Date authorMinDate = minDate;
            Date authorMaxDate = maxDate;
            if (isNow(maxDate)) {
                authorMaxDate = sdf.parse("2030-01-01 00:00:00.000"); // TODO: make something about that
            }

            String sql = customSQL.get(getClass(), GET_NEWS_ACTIVITIES);
            sql = StringUtil.replace(sql, "[$GROUP_IDS$]", buildIdList(groupIds));
            sql = StringUtil.replace(sql, "[$ROLE_IDS$]", buildIdList(roleIds));
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
            logger.debug("getNewsActivities = " + sql);

            SQLQuery q = session.createSQLQuery(sql);
            q.addEntity("News_News", NewsImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            qPos.add(sdf.format(minDate));
            qPos.add(sdf.format(maxDate));
            qPos.add(userId);
            qPos.add(sdf.format(authorMinDate));
            qPos.add(sdf.format(authorMaxDate));
            qPos.add(userId);
            qPos.add(sdf.format(new Date()));
            qPos.add(nbNews);

            return (List<News>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        } catch (Exception e) {
            logger.error("Error while fetching news", e);
        } finally {
            closeSession(session);
        }

        return Collections.emptyList();
    }

    public List<News> getGroupActivities(long userId, long groupId, List<Long> roleIds, Date minDate, Date maxDate, int nbNews) {
        Session session = null;

        try {
            session = openSession();

            // If maxDate is today, we set AuthorMaxDate to the maximum, to be able to edit news published later in the future
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_SEARCH_FORMAT);
            Date authorMinDate = minDate;
            Date authorMaxDate = maxDate;
            if (isNow(maxDate)) {
                authorMaxDate = sdf.parse("2030-01-01 00:00:00.000");  // TODO: make something about that
            }

            String sql = customSQL.get(getClass(), GET_GROUP_ACTIVITIES);
            sql = StringUtil.replace(sql, "[$ROLE_IDS$]", buildIdList(roleIds));
            logger.debug("getGroupActivities = " + sql);

            SQLQuery q = session.createSQLQuery(sql);
            q.addEntity("News_News", NewsImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            qPos.add(sdf.format(minDate));
            qPos.add(sdf.format(maxDate));
            qPos.add(userId);
            qPos.add(sdf.format(authorMinDate));
            qPos.add(sdf.format(authorMaxDate));
            qPos.add(groupId);
            qPos.add(userId);
            qPos.add(nbNews);

            return (List<News>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        } catch (Exception e) {
            logger.error("Error while fetching news", e);
        } finally {
            closeSession(session);
        }

        return Collections.emptyList();
    }

    private static boolean isNow(Date date) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);

        Calendar cal2 = Calendar.getInstance();

        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE));
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
