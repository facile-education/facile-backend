package com.weprode.nero.preference.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.nero.preference.model.MobileDevice;
import com.weprode.nero.preference.service.base.MobileDeviceLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.preference.model.MobileDevice",
        service = AopService.class
)
public class MobileDeviceLocalServiceImpl extends MobileDeviceLocalServiceBaseImpl {

    /**
     * Add new mobile device for user
     */
    public MobileDevice addMobileDevice(String manufacturerDeviceId, long userId, String model, String manufacturer, String os, String osVersion, String browserUA) throws PortalException, SystemException {
        final long mobileDeviceId = counterLocalService.increment();

        final MobileDevice mobileDevice = mobileDevicePersistence.create(mobileDeviceId);

        mobileDevice.setManufaturerDeviceId(manufacturerDeviceId);
        mobileDevice.setUserId(userId);
        mobileDevice.setDeviceModel(model);
        mobileDevice.setManufacturer(manufacturer);
        mobileDevice.setOperatingSystem(os);
        mobileDevice.setOperatingSystemVersion(osVersion);
        mobileDevice.setBrowserUA(browserUA);

        return mobileDevicePersistence.update(mobileDevice);
    }

    /**
     * Return the mobile device matching the id
     */
    public MobileDevice getMobileDevice(long mobileDeviceId) throws SystemException {
        return mobileDevicePersistence.fetchBymobileDeviceId(mobileDeviceId);
    }

    /**
     * Return the mobile device matching the manufacturer id
     */
    public MobileDevice getMobileDeviceByManufacturerDeviceId(String manufacturerDeviceId) throws SystemException {
        return mobileDevicePersistence.fetchBymanufaturerDeviceId(manufacturerDeviceId);
    }

    /**
     * Get user devices
     */
    public List<MobileDevice> getUserMobileDevices(long userId) throws SystemException {
        return mobileDevicePersistence.findByuserId(userId);
    }
}
