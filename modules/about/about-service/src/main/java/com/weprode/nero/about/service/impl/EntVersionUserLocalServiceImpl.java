package com.weprode.nero.about.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.nero.about.model.EntVersion;
import com.weprode.nero.about.model.EntVersionUser;
import com.weprode.nero.about.service.EntVersionLocalServiceUtil;
import com.weprode.nero.about.service.EntVersionUserLocalServiceUtil;
import com.weprode.nero.about.service.base.EntVersionUserLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.weprode.nero.about.model.EntVersionUser",
        service = AopService.class
)
public class EntVersionUserLocalServiceImpl extends EntVersionUserLocalServiceBaseImpl {

    /**
     * Add new entVersionUser
     */
    public EntVersionUser addEntVersionUser(long entVersionId, long userId) throws SystemException {
        long entVersionUserId = counterLocalService.increment();

        EntVersionUser entVersionUser = entVersionUserPersistence.create(entVersionUserId);
        entVersionUser.setEntVersionId(entVersionId);
        entVersionUser.setUserId(userId);

        return entVersionUserPersistence.update(entVersionUser);
    }

    /**
     * Return true if user has already read the last version
     */
    public boolean hasUserDispelledLastVersion(long userId) throws SystemException {
        EntVersion lastEntVersion = EntVersionLocalServiceUtil.getLastEntVersion();

        if (lastEntVersion == null) {
            // No last version
            return true;
        }

        EntVersionUser entVersionUser = entVersionUserPersistence.fetchByentVersionId_userId(lastEntVersion.getEntVersionId(), userId);

        return entVersionUser != null;
    }

    public boolean markLastVersionAsRead(long userId) {
        EntVersion latestEntVersion = EntVersionLocalServiceUtil.getLastEntVersion();

        if (latestEntVersion != null) {
            try {
                EntVersionUserLocalServiceUtil.addEntVersionUser(latestEntVersion.getEntVersionId(), userId);
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }
}
