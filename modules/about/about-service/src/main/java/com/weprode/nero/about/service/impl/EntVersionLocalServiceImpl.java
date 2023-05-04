package com.weprode.nero.about.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.about.model.EntVersion;
import com.weprode.nero.about.service.EntVersionLocalServiceUtil;
import com.weprode.nero.about.service.base.EntVersionLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.about.model.EntVersion",
        service = AopService.class
)
public class EntVersionLocalServiceImpl extends EntVersionLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(EntVersionLocalServiceImpl.class);

    /**
     * Add new ENT version with today date
     */
    public EntVersion addVersion(String version, String details) throws SystemException {
        return addVersion(version, details, new Date());
    }

    /**
     * Add new ENT version
     */
    public EntVersion addVersion(String version, String details, Date versionDate) throws SystemException {
        updateLastVersion();

        long entVersionId = counterLocalService.increment();
        EntVersion entVersion = entVersionPersistence.create(entVersionId);

        entVersion.setVersion(version);
        entVersion.setDetails(details);
        entVersion.setIsLast(true);
        entVersion.setVersionDate(versionDate);

        return entVersionPersistence.update(entVersion);
    }

    /**
     * Get all ENT versions sorted by date
     */
    public List<EntVersion> getAllEntVersion() throws SystemException {
        List<EntVersion> entVersionList = new ArrayList<>(EntVersionLocalServiceUtil.getEntVersions(QueryUtil.ALL_POS, QueryUtil.ALL_POS));

        entVersionList.sort((version1, version2) -> Long.compare(version2.getVersionDate().getTime(), version1.getVersionDate().getTime()));

        return entVersionList;
    }

    /**
     * Get last ENT version
     */
    public EntVersion getLastEntVersion() {
        try {
            return entVersionPersistence.fetchByisLast(true);
        } catch (Exception e) {
            logger.debug(e);
        }

        return null;
    }

    /**
     * Update last version to set boolean to false
     */
    private void updateLastVersion() throws SystemException {
        EntVersion previousVersion = getLastEntVersion();

        if (previousVersion != null) {
            previousVersion.setIsLast(false);
            entVersionPersistence.update(previousVersion);
        }
    }
}
