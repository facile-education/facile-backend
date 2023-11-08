package com.weprode.facile.group.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.weprode.facile.group.model.GroupUtils;
import com.weprode.facile.group.service.persistence.GroupUtilsFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component(service = GroupUtilsFinder.class)
public class GroupUtilsFinderImpl extends GroupUtilsFinderBaseImpl
        implements GroupUtilsFinder {

    private static final Log logger = LogFactoryUtil.getLog(GroupUtilsFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    @Reference
    private BasePersistence<GroupUtils> basePersistence;

    @BeanReference(type = GroupLocalService.class)
    private GroupLocalService groupLocalService;

    private static final String GROUP_CLASS_NAME = "com.liferay.portal.model.impl.GroupImpl";

    private static final String GROUP_ENTITY = "Group_";

    public static final String FIND_USER_GROUPS =
            GroupUtilsFinder.class.getName() +
                    ".findUserGroups";

    public static final String FIND_SCHOOL_GROUPS =
            GroupUtilsFinder.class.getName() +
                    ".findSchoolGroups";

    public List<Group> findUserGroups(long userId, boolean pedagogicalOnly, boolean activeOnly, int begin, int end) {
        Session session = null;

        try {
            session = basePersistence.openSession();

            StringBuilder sqlBuilder = new StringBuilder();

            if (pedagogicalOnly) {
                sqlBuilder.append(" AND ci.isPedagogical = 1");
            }
            if (activeOnly) {
                sqlBuilder.append(" AND ci.status = 0");
            }

            String sql = customSQL.get(getClass(), FIND_USER_GROUPS) + sqlBuilder;

            SQLQuery q = session.createSQLQuery(sql);

            // use portal class loader, since this is portal entity
            q.addEntity(GROUP_ENTITY,
                    PortalClassLoaderUtil.getClassLoader().loadClass(GROUP_CLASS_NAME));

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(userId);

            return (List<Group>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            logger.error("Custom query failed", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return Collections.emptyList();
    }

    public List<Group> findSchoolGroups(long userId, boolean pedagogicalOnly, boolean activeOnly, int begin, int end) {
        Session session = null;

        try {
            session = basePersistence.openSession();

            StringBuilder sqlBuilder = new StringBuilder();

            if (pedagogicalOnly) {
                sqlBuilder.append(" AND ci.isPedagogical = 1");
            }
            if (activeOnly) {
                sqlBuilder.append(" AND ci.status = 0");
            }

            String sql = customSQL.get(getClass(), FIND_SCHOOL_GROUPS) + sqlBuilder;

            SQLQuery q = session.createSQLQuery(sql);

            // use portal class loader, since this is portal entity
            q.addEntity(GROUP_ENTITY,
                    PortalClassLoaderUtil.getClassLoader().loadClass(GROUP_CLASS_NAME));

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(userId);

            return (List<Group>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            logger.error("Custom query failed", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return Collections.emptyList();
    }

    /**
     * Returns a list of groups from a list of organizations
     */
    public List<Group> findGroupsByOrganizationId(List<Organization> organizations) {
        List<Group> groupToReturn = new ArrayList<>();

        Session session = null;
        try {
            session = basePersistence.openSession();

            if (organizations != null && !organizations.isEmpty()) {

                ClassLoader classLoader = getClass().getClassLoader();
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class, classLoader);

                Long[] orgIdTab = new Long[organizations.size()];
                int idx = 0;
                for (Organization org : organizations) {
                    orgIdTab[idx++] = org.getOrganizationId();
                }
                dynamicQuery.add(PropertyFactoryUtil.forName("classPK").in(orgIdTab));

                groupToReturn.addAll(groupLocalService.dynamicQuery(dynamicQuery));
            }
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                logger.error("Error while getting the group list for given list of organizations", e);
            }
        } finally {
            basePersistence.closeSession(session);
        }

        return groupToReturn;
    }

}
