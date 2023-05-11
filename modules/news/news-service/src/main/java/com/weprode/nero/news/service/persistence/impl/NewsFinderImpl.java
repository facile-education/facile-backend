package com.weprode.nero.news.service.persistence.impl;

import com.liferay.petra.string.StringUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.news.model.News;
import com.weprode.nero.news.model.impl.NewsImpl;
import com.weprode.nero.news.service.persistence.NewsFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component(service = NewsFinder.class)
public class NewsFinderImpl extends NewsFinderBaseImpl
        implements NewsFinder {

    private static final Log logger = LogFactoryUtil.getLog(NewsFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    public static final String GET_NEWS =
            NewsFinder.class.getName() + ".getNews";
    public static final String GET_NEWS_COUNT =
            NewsFinder.class.getName() + ".getNewsCount";

    public List<News> getNews(long userId, List<Long> groupIds, List<Long> roleIds, Date maxDate, int nbNews, boolean groupNews, boolean importantOnly, boolean unreadOnly) {
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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
            qPos.add(sdf.format(maxDate));
            qPos.add(sdf.format(new Date()));
            qPos.add(userId);
            qPos.add(nbNews);

//            EntityCacheUtil.clearCache(NewsImpl.class.getName());
//            FinderCacheUtil.clearCache(NewsImpl.class.getName());
//            clearCache();
            return (List<News>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (Exception e) {
            logger.error("Error while fetching news", e);
        } finally {
            closeSession(session);
        }

        return Collections.emptyList();
    }

    public int getNewsCount(long userId, List<Long> groupIds, List<Long> roleIds, boolean groupNews, boolean importantOnly, boolean unreadOnly) {
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

            String sql = customSQL.get(getClass(), GET_NEWS_COUNT);
            sql = StringUtil.replace(sql, "[$GROUP_IDS$]", buildIdList(groupIds));
            sql = StringUtil.replace(sql, "[$ROLE_IDS$]", buildIdList(roleIds));
            sql = StringUtil.replace(sql, "[$OTHER$]", other);
            logger.debug("News count sql = " + sql);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addScalar("totalCount", Type.INTEGER);

            QueryPos qPos = QueryPos.getInstance(q);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
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
