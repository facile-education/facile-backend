package com.weprode.facile.group.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.group.model.CommunityInfos;
import com.weprode.facile.group.service.CommunityInfosLocalService;
import com.weprode.facile.group.service.persistence.CommunityInfosFinder;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component(service = CommunityInfosFinder.class)
public class CommunityInfosFinderImpl extends CommunityInfosFinderBaseImpl
        implements CommunityInfosFinder {

    private static final Log logger = LogFactoryUtil.getLog(CommunityInfosFinderImpl.class);

    @BeanReference(type = CommunityInfosLocalService.class)
    private CommunityInfosLocalService communityInfosLocalService;

    /**
     * Get the communities which have expired from more than 3 months
     * @return list of community info or null in case of exception
     */
    public List<CommunityInfos> findExpiredCommunityToRemove() {
        List<CommunityInfos> listCommunityToDelete = new ArrayList<>();

        Session session = null;
        try {
            session = openSession();

            ClassLoader classLoader = getClass().getClassLoader();

            // We create the dynamic query and build the criterion to select all the schools childs but not the schools (which have an orgroot for parent)
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommunityInfos.class, classLoader);

            // build date Today - 2 months
            GregorianCalendar expirationDate = new GregorianCalendar();
            expirationDate.add(Calendar.MONTH, -3);

            // format and add criterion
            Criterion criterion = RestrictionsFactoryUtil.le("expirationDate", expirationDate.getTime());
            dynamicQuery.add(criterion);

            // CAre the dynamic query returns unmodified list
            List<CommunityInfos> communities = communityInfosLocalService.dynamicQuery(dynamicQuery);
            listCommunityToDelete.addAll(communities);
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                logger.error("Error when retrieving expired private groups to remove ", e);
            }
        }
        finally {
            closeSession(session);
        }

        return listCommunityToDelete;
    }
}
