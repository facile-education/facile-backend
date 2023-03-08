package com.weprode.nero.user.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.user.service.persistence.LDAPMappingFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.math.BigInteger;

@Component(service = LDAPMappingFinder.class)
public class LDAPMappingFinderImpl extends LDAPMappingFinderBaseImpl
        implements LDAPMappingFinder {

    private static final Log logger = LogFactoryUtil.getLog(LDAPMappingFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    public static final String FIND_MAX_UID =
            LDAPMappingFinder.class.getName() +
                    "findMaxUid";

    public Long findMaxUid() {
        Session session = null;

        long res = 0L;
        try {
            session = openSession();

            String sqlMaxUid = customSQL.get(getClass(), FIND_MAX_UID);

            SQLQuery query = session.createSQLQuery(sqlMaxUid);
            Object queryResult = query.uniqueResult();

            // Get result and if it's is not empty convert it as long
            if (queryResult != null) {
                res = ((BigInteger) queryResult).longValue();
            }

        } catch (Exception e) {
            logger.error(e);
        } finally {
            closeSession(session);
        }

        return res;
    }
}
