package com.weprode.nero.user.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.weprode.nero.user.model.UserUtils;
import com.weprode.nero.user.service.persistence.UserUtilsFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = UserUtilsFinder.class)
public class UserUtilsFinderImpl extends UserUtilsFinderBaseImpl
        implements UserUtilsFinder {

    private static final Log logger = LogFactoryUtil.getLog(UserUtilsFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    @Reference
    private BasePersistence<UserUtils> basePersistence;

    public static final String USER_CLASS_NAME = "com.liferay.portal.model.impl.UserImpl";

    public static final String COUNT_USERS =
            UserUtilsFinder.class.getName() +
                    ".countUsers";
    public static final String FIND_USERS =
            UserUtilsFinder.class.getName() +
                    ".findUsers";

    public static final String JOIN_STRING = "[$JOIN$]";
    public static final String WHERE_STRING = "[$WHERE$]";

    public List<User> findUsersFromIdList(List<Long> userIds) {
        List<User> usersFromUserIdsList = new ArrayList<>();

        if(userIds == null || userIds.isEmpty()){
            return usersFromUserIdsList;
        }

        Session session = null;
        try {
            session = basePersistence.openSession();

            ClassLoader classLoader = getClass().getClassLoader();

            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class, classLoader);
            Disjunction disjunctionUserId = RestrictionsFactoryUtil.disjunction();
            Property userIdProperty = PropertyFactoryUtil.forName("userId");

            for(Long aUserid : userIds){
                disjunctionUserId.add(userIdProperty.eq(aUserid));
            }
            dynamicQuery.add(disjunctionUserId);

            usersFromUserIdsList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                logger.error(e);
            }
            usersFromUserIdsList = new ArrayList<>();
        }
        finally {
            basePersistence.closeSession(session);
        }

        return usersFromUserIdsList;
    }

    public Long getUserCountFromContactSearch(String searchQuery, List<Long> organizationIds, List<Long> groupIds, List<Long> roleIds,
                                              List<Long> subjectIds, boolean localUsersOnly) throws SystemException {
        // Remove extra spaces (begininng / end / muliple spaces)
        searchQuery = searchQuery.trim().replaceAll(" +", " ").toLowerCase();

        Session session = null;

        BigInteger countBI = null;
        long count = 0L;

        try {
            // open session using liferay's session factory
            session = basePersistence.openSession();

            String customSql = customSQL.get(getClass(), COUNT_USERS);
            StringBundler sql = new StringBundler(buildContactSearchJoinAndWhere(customSql, searchQuery, organizationIds, groupIds,
                    roleIds, subjectIds, localUsersOnly));

            if (logger.isDebugEnabled()) {
                logger.debug("SQL Query :\n" + sql);
            }

            SQLQuery q = session.createSQLQuery(sql.toString());
            q.setCacheable(false);

            countBI = (BigInteger) q.uniqueResult();
        } catch (Exception e) {
            logger.error("countUserByNameAndOrg : Error when executing custom SQL query.", e);
        } finally {
            if (countBI != null) {
                count = countBI.longValue();
            }

            basePersistence.closeSession(session);
        }

        return count;
    }

    public List<User> getUserFromContactSearch(String searchQuery, List<Long> organizationIds, List<Long> groupIds, List<Long> roleIds,
                                               List<Long> subjectIds, boolean localUsersOnly, int start, int stop, OrderByComparator obc) throws SystemException {
        List<User> results = new ArrayList<>();

        // Remove extra spaces (begininng / end / muliple spaces)
        searchQuery = searchQuery.trim().replaceAll(" +", " ").toLowerCase();

        Session session = null;

        try {
            session = basePersistence.openSession();

            String customSql = customSQL.get(getClass(), FIND_USERS);
            StringBundler sql = new StringBundler(buildContactSearchJoinAndWhere(customSql, searchQuery, organizationIds, groupIds,
                    roleIds, subjectIds, localUsersOnly));

            if (obc != null) {
                sql.append(" ORDER BY ");
                sql.append(obc.toString());
            }

            if (logger.isDebugEnabled()) {
                logger.debug("SQL Query :\n" + sql);
            }

            SQLQuery q = session.createSQLQuery(sql.toString());
            q.setCacheable(false);

            q.addEntity("u", PortalClassLoaderUtil.getClassLoader().loadClass(USER_CLASS_NAME));

            return (List<User>) QueryUtil.list(q, getDialect(), start, stop, false);
        } catch (Exception e) {
            logger.error("Error when executing custom SQL query.", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return results;
    }

    // Build JOIN and WHERE clauses for contact search
    private String buildContactSearchJoinAndWhere(String sqlQuery, String searchQuery, List<Long> organizationIds, List<Long> groupIds, List<Long> roleIds,
                                                  List<Long> subjectIds, boolean localUsersOnly) {
        StringBundler join = new StringBundler();
        StringBundler where = new StringBundler();

        if (!searchQuery.isEmpty()) {
            where.append("( (lower(u.firstName) LIKE \"%" + searchQuery + "%\") ");
            where.append("OR (lower(u.lastName) LIKE \"%" + searchQuery + "%\") ");
            if (searchQuery.contains(" ")) {
                // searchQuery parameter is the aggregation of multiple search items separated by space
                String[] searchQueryTab = searchQuery.split(" ");
                StringBuilder searchQueryLike = new StringBuilder("%");
                for (String searchItem : searchQueryTab) {
                    searchQueryLike.append(searchItem).append("%");
                }
                where.append("OR (lower(concat(u.firstName, ' ', u.lastName)) LIKE \"" + searchQueryLike + "\") ");
                where.append("OR (lower(concat(u.lastName, ' ', u.firstName)) LIKE \"" + searchQueryLike + "\") ");
            }
            where.append(") ");
        }

        if (organizationIds != null && !organizationIds.isEmpty()) {
            join.append("JOIN Users_Orgs uo ON u.userId = uo.userId ");
            appendOperator(where, "AND");
            where.append("(uo.organizationId IN (" + join(organizationIds, ",") + ")) ");
        }

        if (groupIds != null && !groupIds.isEmpty()) {
            join.append("JOIN Users_Groups ug ON u.userId = ug.userId ");
            appendOperator(where, "AND");
            where.append("(ug.groupId IN (" + join(groupIds, ",") + ")) ");
        }

        if (roleIds != null && !roleIds.isEmpty()) {
            join.append("JOIN Users_Roles ur ON u.userId = ur.userId ");
            appendOperator(where, "AND");
            where.append("(ur.roleId IN (" + join(roleIds, ",") + ")) ");
        }

        if (subjectIds != null && !subjectIds.isEmpty()) {
            join.append("JOIN User_TeacherSubject ts ON u.userId = ts.teacherId ");
            appendOperator(where, "AND");
            where.append("(ts.subjectId IN (" + join(subjectIds, ",") + ")) ");
        }

        if (localUsersOnly) {
            join.append("JOIN Preference_UserProperties up ON u.userId = up.userId ");
            appendOperator(where, "AND");
            where.append("(up.manualAccount = 1) ");
        }

        // Get active users only
        appendOperator(where, "AND");
        where.append("(u.status = 0)");

        return replaceJoinAndWhereInSQLQuery(sqlQuery, join.toString(), where.toString());
    }

    // Append operator to where clause
    private StringBundler appendOperator(StringBundler where, String operator) {
        if (where.length() > 0) {
            where.append(operator);
            where.append(" ");
        }

        return where;
    }

    // Replace join and where query indicator by string value
    private String replaceJoinAndWhereInSQLQuery(String sql, String join, String where) {
        String finalQuery = sql.replace(JOIN_STRING, join);

        finalQuery = finalQuery.replace(WHERE_STRING, where);
        if (where.isEmpty()) {
            finalQuery = finalQuery.replace("WHERE", "");
        }

        return finalQuery;
    }

    // Convert an array into string with each value separated by delim
    private static String join(Iterable<?> it, String delim) {
        StringBuilder sb = new StringBuilder();

        Iterator<?> iter = it.iterator();
        if (iter.hasNext()) {
            sb.append(iter.next().toString());
        }

        while (iter.hasNext()) {
            sb.append(delim);
            sb.append(iter.next().toString());
        }

        return sb.toString();
    }
}
