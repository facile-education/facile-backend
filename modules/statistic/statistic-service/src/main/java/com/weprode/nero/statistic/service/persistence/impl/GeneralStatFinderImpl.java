package com.weprode.nero.statistic.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.weprode.nero.organization.model.OrgUtils;
import com.weprode.nero.statistic.service.persistence.GeneralStatFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.math.BigInteger;
import java.util.*;

@Component(service = GeneralStatFinder.class)
public class GeneralStatFinderImpl extends GeneralStatFinderBaseImpl
        implements GeneralStatFinder {

    private static final Log logger = LogFactoryUtil.getLog(GeneralStatFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    @Reference
    private BasePersistence<OrgUtils> basePersistence;

    private static final String QUERY_COUNT_ALL_ACTIVE_USERS =
            GeneralStatFinder.class.getName() +
                    ".countAllActiveUsers";
    private static final String QUERY_COUNT_SCHOOL_ACTIVE_USERS =
            GeneralStatFinder.class.getName() +
                    ".countSchoolActiveUsers";
    private static final String QUERY_COUNT_ALL_FILES =
            GeneralStatFinder.class.getName() +
                    ".countAllFiles";
    private static final String QUERY_COUNT_SCHOOL_FILES =
            GeneralStatFinder.class.getName() +
                    ".countSchoolFiles";
    private static final String QUERY_COUNT_ALL_HOMEWORKS =
            GeneralStatFinder.class.getName() +
                    ".countAllHomeworks";
    private static final String QUERY_COUNT_SCHOOL_HOMEWORKS =
            GeneralStatFinder.class.getName() +
                    ".countSchoolHomeworks";
    private static final String QUERY_COUNT_ALL_MESSAGES =
            GeneralStatFinder.class.getName() +
                    ".countAllMessages";
    private static final String QUERY_COUNT_SCHOOL_MESSAGES =
            GeneralStatFinder.class.getName() +
                    ".countSchoolMessages";
    private static final String QUERY_COUNT_ALL_NEWS =
            GeneralStatFinder.class.getName() +
                    ".countAllNews";
    private static final String QUERY_COUNT_SCHOOL_NEWS =
            GeneralStatFinder.class.getName() +
                    ".countSchoolNews";

    // private static final String QUERY_COUNT_SCHOOL_CDT_SESSION_CONTENT =
    //      GeneralStatFinder.class.getName() +
    //              ".countSchoolCDTSessionContent";

    public int countActiveUsers(Date startDate, Date endDate, long schoolId) {
        Session session = null;

        try {
            session = basePersistence.openSession();

            String queryTitle = (schoolId == 0) ? QUERY_COUNT_ALL_ACTIVE_USERS : QUERY_COUNT_SCHOOL_ACTIVE_USERS;
            String sql = customSQL.get(getClass(), queryTitle);
            SQLQuery query = session.createSQLQuery(sql);
            QueryPos qPos = QueryPos.getInstance(query);

            qPos.add(CalendarUtil.getTimestamp(startDate));
            qPos.add(CalendarUtil.getTimestamp(endDate));
            if (schoolId != 0) {
                qPos.add(schoolId);
            }

            return ((BigInteger) query.uniqueResult()).intValue();

        } catch (Exception e) {
            logger.error("Custom query countActiveUsers failed", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return 0;
    }

    public Map<String, Integer> countFiles(Date startDate, Date endDate, long schoolId) {
        Session session = null;

        Map<String, Integer> resultMap = new HashMap<>();
        try {
            session = basePersistence.openSession();

            String queryTitle = (schoolId == 0) ? QUERY_COUNT_ALL_FILES : QUERY_COUNT_SCHOOL_FILES;
            String sql = customSQL.get(getClass(), queryTitle);
            SQLQuery query = session.createSQLQuery(sql);
            QueryPos qPos = QueryPos.getInstance(query);

            qPos.add(CalendarUtil.getTimestamp(startDate));
            qPos.add(CalendarUtil.getTimestamp(endDate));
            if (schoolId != 0) {
                qPos.add(schoolId);
            }

            List<Object[]> results = query.list();

            if (results != null && !results.isEmpty()) {
                for (Object[] result : results) {
                    String extension = (String) result[0];
                    int count = ((BigInteger) result[1]).intValue();

                    List<String> textExtensions = Arrays.asList("doc", "docx", "odt");
                    List<String> tabExtensions = Arrays.asList("xls", "xlsx", "ods");
                    List<String> presExtensions = Arrays.asList("ppt", "pptx", "odp");
                    List<String> imageExtensions = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp");
                    List<String> htmlExtensions = Arrays.asList("html", "xml", "htm");
                    List<String> videoExtensions = Arrays.asList("mp4", "m4a", "avi", "mov", "mpeg", "mpg");
                    List<String> audioExtensions = Arrays.asList("mp3", "wav");

                    if (extension.equals("pdf")) {
                        resultMap.put("pdf", count);
                    } else if (textExtensions.contains(extension)) {
                        if (resultMap.containsKey("text")) {
                            resultMap.put("text", resultMap.get("text") + count);
                        } else {
                            resultMap.put("text", count);
                        }
                    } else if (tabExtensions.contains(extension)) {
                        if (resultMap.containsKey("tab")) {
                            resultMap.put("tab", resultMap.get("tab") + count);
                        } else {
                            resultMap.put("tab", count);
                        }
                    } else if (presExtensions.contains(extension)) {
                        if (resultMap.containsKey("pres")) {
                            resultMap.put("pres", resultMap.get("pres") + count);
                        } else {
                            resultMap.put("pres", count);
                        }
                    } else if (imageExtensions.contains(extension)) {
                        if (resultMap.containsKey("image")) {
                            resultMap.put("image", resultMap.get("image") + count);
                        } else {
                            resultMap.put("image", count);
                        }
                    } else if (htmlExtensions.contains(extension)) {
                        if (resultMap.containsKey("html")) {
                            resultMap.put("html", resultMap.get("html") + count);
                        } else {
                            resultMap.put("html", count);
                        }
                    } else if (videoExtensions.contains(extension)) {
                        if (resultMap.containsKey("video")) {
                            resultMap.put("video", resultMap.get("video") + count);
                        } else {
                            resultMap.put("video", count);
                        }
                    } else if (audioExtensions.contains(extension)) {
                        if (resultMap.containsKey("audio")) {
                            resultMap.put("audio", resultMap.get("audio") + count);
                        } else {
                            resultMap.put("audio", count);
                        }
                    } else if (extension.equals("sldprt")) {
                        resultMap.put("solidworks", count);
                    } else if (extension.equals("ggb")) {
                        resultMap.put("geogebra", count);
                    } else if (extension.equals("ipynb")) {
                        resultMap.put("notebook", count);
                    } else if (extension.equals("mind")) {
                        resultMap.put("mindmap", count);
                    } else if (extension.equals("sb3")) {
                        resultMap.put("scratch", count);
                    } else {
                        resultMap.put("other", count);
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Custom query countFiles failed", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return resultMap;
    }

    public Map<Integer, Integer> countHomeworks(Date startDate, Date endDate, long schoolId) {
        Session session = null;
        Map<Integer, Integer> resultMap = new HashMap<>();

        try {
            session = basePersistence.openSession();

            String queryTitle = (schoolId == 0) ? QUERY_COUNT_ALL_HOMEWORKS : QUERY_COUNT_SCHOOL_HOMEWORKS;
            String sql = customSQL.get(getClass(), queryTitle);
            SQLQuery query = session.createSQLQuery(sql);
            QueryPos qPos = QueryPos.getInstance(query);

            qPos.add(CalendarUtil.getTimestamp(startDate));
            qPos.add(CalendarUtil.getTimestamp(endDate));
            if (schoolId != 0) {
                qPos.add(schoolId);
            }

            List<Object[]> results = query.list();

            if (results != null && !results.isEmpty()) {
                for (Object[] result : results) {
                    int type = (int) result[0];
                    int count = ((BigInteger) result[1]).intValue();

                    resultMap.put(type, count);
                }
            }

        } catch (Exception e) {
            logger.error("Custom query countHomeworks failed", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return resultMap;
    }

    public int countNews(Date startDate, Date endDate, long schoolId, boolean isSchoolNewsType) {
        Session session = null;

        try {
            session = basePersistence.openSession();

            String queryTitle = (schoolId == 0) ? QUERY_COUNT_ALL_NEWS : QUERY_COUNT_SCHOOL_NEWS;
            String sql = customSQL.get(getClass(), queryTitle);
            SQLQuery query = session.createSQLQuery(sql);
            QueryPos qPos = QueryPos.getInstance(query);

            qPos.add(CalendarUtil.getTimestamp(startDate));
            qPos.add(CalendarUtil.getTimestamp(endDate));
            if (schoolId != 0) {
                qPos.add(schoolId);
            }
            qPos.add(isSchoolNewsType);

            return ((BigInteger) query.uniqueResult()).intValue();

        } catch (Exception e) {
            logger.error("Custom query countNews failed", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return 0;
    }

    public int countMessages(Date startDate, Date endDate, long schoolId) {
        Session session = null;

        try {
            session = basePersistence.openSession();

            String queryTitle = (schoolId == 0) ? QUERY_COUNT_ALL_MESSAGES : QUERY_COUNT_SCHOOL_MESSAGES;
            String sql = customSQL.get(getClass(), queryTitle);
            SQLQuery query = session.createSQLQuery(sql);
            QueryPos qPos = QueryPos.getInstance(query);

            qPos = QueryPos.getInstance(query);
            qPos.add(CalendarUtil.getTimestamp(startDate));
            qPos.add(CalendarUtil.getTimestamp(endDate));
            if (schoolId != 0) {
                qPos.add(schoolId);
            }

            return ((BigInteger) query.uniqueResult()).intValue();

        } catch (Exception e) {
            logger.error("Custom query countMessages failed", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return 0;
    }
}
