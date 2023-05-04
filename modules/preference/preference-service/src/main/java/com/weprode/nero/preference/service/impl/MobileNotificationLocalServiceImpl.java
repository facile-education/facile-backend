package com.weprode.nero.preference.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.nero.preference.model.MobileNotification;
import com.weprode.nero.preference.service.base.MobileNotificationLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.preference.model.MobileNotification",
        service = AopService.class
)
public class MobileNotificationLocalServiceImpl extends MobileNotificationLocalServiceBaseImpl {
    
    public MobileNotification addMobileNotification() throws SystemException {
        final long mobileNotificationId = counterLocalService.increment();

        return mobileNotificationPersistence.create(mobileNotificationId);
    }

    public MobileNotification addMobileNotification(long userId, long etabId, String token, String device) throws SystemException, PortalException {
        MobileNotification mobileNotification = this.addMobileNotification();

        mobileNotification.setUserId(userId);
        mobileNotification.setEtabId(etabId);
        mobileNotification.setToken(token);

        mobileNotification.setDevice(device);

        mobileNotification.setEnable(true);

        mobileNotificationPersistence.update(mobileNotification);

        return mobileNotification;
    }

    public void removeMobileNotification(long mobileNotificationId) throws SystemException, PortalException {
        mobileNotificationPersistence.remove(mobileNotificationId);
    }

    public List<MobileNotification> getMobileNotificationByUserId(long userId) throws SystemException {
        return mobileNotificationPersistence.findByuserId(userId);
    }

    public List<MobileNotification> getMobileNotificationByUserIdEnable(long userId, Boolean enable) throws SystemException {
        return mobileNotificationPersistence.findByuserId_enable(userId, enable);
    }

    public List<MobileNotification> getMobileNotificationByEtabId(long etabId) throws SystemException {
        return mobileNotificationPersistence.findByetabId(etabId);
    }

    public List<MobileNotification> getMobileNotificationByEtabIdEnable(long etabId, Boolean enable) throws SystemException {
        return mobileNotificationPersistence.findByetabId_enable(etabId, enable);
    }
}
