package com.weprode.facile.school.life.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.weprode.facile.school.life.model.Renvoi;
import com.weprode.facile.school.life.model.impl.RenvoiImpl;
import com.weprode.facile.school.life.service.persistence.RenvoiFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(service = RenvoiFinder.class)
public class RenvoiFinderImpl extends RenvoiFinderBaseImpl
        implements RenvoiFinder {

    private static final Log logger = LogFactoryUtil.getLog(RenvoiFinderImpl.class);

    @Reference
    private CustomSQL customSQL;

    public static final String GET_GROUPS_RENVOIS = "getGroupsRenvois";

    public List<Renvoi> getGroupRenvois(List<Long> groupIds, Date minDate, Date maxDate, int start, int end) {
        Session session = null;

        try {
            session = openSession();

            StringBuilder groupIdsParam = new StringBuilder();
            for (Long id: groupIds) {
                groupIdsParam.append(id);
                if (groupIds.indexOf(id) != (groupIds.size() - 1)) {
                    groupIdsParam.append(",");
                }
            }
            String sql = customSQL.get(getClass(), GET_GROUPS_RENVOIS).replace("[$groupIds$]", groupIdsParam);
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity("Schoollife_Renvoi", RenvoiImpl.class);

            QueryPos qPos = QueryPos.getInstance(query);
            qPos.add(CalendarUtil.getTimestamp(minDate));
            qPos.add(CalendarUtil.getTimestamp(maxDate));

            return (List<Renvoi>) QueryUtil.list(query, getDialect(), start, end);
        } catch (Exception e) {
            logger.error("Custom query getGroupRenvois failed", e);
        } finally {
            closeSession(session);
        }

        return new ArrayList<>();
    }
}
