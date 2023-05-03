package com.weprode.nero.organization.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.model.OrgUtils;
import com.weprode.nero.organization.service.persistence.OrgUtilsFinder;
import java.util.Collections;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

@Component(service = OrgUtilsFinder.class)
public class OrgUtilsFinderImpl extends OrgUtilsFinderBaseImpl
        implements OrgUtilsFinder {

    private static final Log logger = LogFactoryUtil.getLog(OrgUtilsFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    @Reference
    private BasePersistence<OrgUtils> basePersistence;

    public static final String ORGANIZATION_CLASS_NAME = "com.liferay.portal.model.impl.OrganizationImpl";

    public static final String FIND_USER_ORGANIZATIONS =
            OrgUtilsFinder.class.getName() +
                    ".findUserOrganizations";

    public static final String FIND_ORGANIZATIONS_BY_PARENT_ID =
            OrgUtilsFinder.class.getName() +
                    ".findOrganizationsByParentId";

    public static final String FIND_ORGANIZATIONS_BY_TYPE =
            OrgUtilsFinder.class.getName() +
                    ".findOrganizationsByType";

    /**
     * Return the list of user's organizations
     */
    public List<Organization> findUserOrganizations(long userId, List<Integer> types, List<Integer> roles,
                                                    Boolean withArchives, long schoolId, int begin, int end) {
        Session session = null;

        try {
            session = basePersistence.openSession();

            StringBuilder sqlBuilder = new StringBuilder();

            sqlBuilder.append(formatIntegerListToSQL(roles, "od.role_"));

            sqlBuilder.append(formatIntegerListToSQL(types, "od.type_"));

            // Archives : null means all
            if (withArchives != null) {
                sqlBuilder.append(" AND od.isArchive = ").append(withArchives ? "1" : "0");
            }

            if (schoolId != OrgConstants.ALL_SCHOOLS_ID) {
                sqlBuilder.append(" AND od.schoolId = ").append(schoolId);
            }

            String sql = customSQL.get(getClass(), FIND_USER_ORGANIZATIONS) + sqlBuilder;

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);

            // use portal class loader, since this is portal entity
            q.addEntity("organization_",
                    PortalClassLoaderUtil.getClassLoader().loadClass(ORGANIZATION_CLASS_NAME));

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(userId);

            return (List<Organization>) QueryUtil.list(q, getDialect(), begin, end, false);

        } catch (Exception e) {
            logger.error("Error when getting user's organizations : userId=" + userId +
                    ", types=" + types.toString() + ", roles=" + roles.toString(), e);
        } finally {
            basePersistence.closeSession(session);
        }

        return Collections.emptyList();
    }

    /**
     * Return the list of user's organizations
     */
    public List<Organization> findSchoolOrganizations(long schoolId, List<Integer> types, List<Integer> roles,
                                                     Boolean withArchives, int begin, int end) {
        Session session = null;

        try {
            session = basePersistence.openSession();

            StringBuilder sqlBuilder = new StringBuilder();

            sqlBuilder.append(formatIntegerListToSQL(roles, "od.role_"));

            sqlBuilder.append(formatIntegerListToSQL(types, "od.type_"));

            // Archives : null means all
            if (withArchives != null) {
                sqlBuilder.append(" AND od.isArchive = ").append(withArchives);
            }

            String sql = customSQL.get(getClass(), FIND_ORGANIZATIONS_BY_PARENT_ID) + sqlBuilder;

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);

            // use portal class loader, since this is portal entity
            q.addEntity("organization_",
                    PortalClassLoaderUtil.getClassLoader().loadClass(ORGANIZATION_CLASS_NAME));

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(schoolId);

            return (List<Organization>) QueryUtil.list(q, getDialect(), begin, end, false);
        } catch (Exception e) {
            logger.error("Error when getting school's organizations : schoolId="+schoolId+", types="+types.toString()+", roles="+roles.toString(), e);
        } finally {
            basePersistence.closeSession(session);
        }

        return Collections.emptyList();
    }

    /**
     * Returns all ENT's schools
     */
    public List<Organization> findOrganizationByType(int type, int begin, int end) {
        Session session = null;

        try {
            session = basePersistence.openSession();

            String sql = customSQL.get(getClass(), FIND_ORGANIZATIONS_BY_TYPE);

            // Now that we have built the query, we can do all the usual stuff.
            SQLQuery q = session.createSQLQuery(sql);

            // use portal class loader, since this is portal entity
            q.addEntity("organization_",
                    PortalClassLoaderUtil.getClassLoader().loadClass(ORGANIZATION_CLASS_NAME));

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(type);

            return (List<Organization>) QueryUtil.list(q, getDialect(), begin, end, false);
        } catch (Exception e) {
            logger.error("Custom query failed", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return Collections.emptyList();
    }

    // TODO replace by IN (..., ..., ...) ? + complete sql file to use CDATA ?
    private String formatIntegerListToSQL (List<Integer> integers, String sqlAttribute) {

        // Empty roles list means all roles
        if (integers == null || integers.isEmpty()) {
            return "";
        }

        StringBuilder sqlBuilder = new StringBuilder(" AND (");
        for (int idx = 0 ; idx < integers.size() ; idx ++) {
            sqlBuilder.append(sqlAttribute).append(" = ").append(integers.get(idx));
            if (idx != integers.size() - 1) {
                sqlBuilder.append(" OR ");
            }
        }
        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }
}
