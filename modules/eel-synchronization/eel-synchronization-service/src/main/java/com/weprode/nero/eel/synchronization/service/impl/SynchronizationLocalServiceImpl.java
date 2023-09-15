package com.weprode.nero.eel.synchronization.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.eel.synchronization.GVEParentSynchronizationManager;
import com.weprode.nero.eel.synchronization.GVESynchronizationManager;
import com.weprode.nero.eel.synchronization.service.base.SynchronizationLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.weprode.nero.user.model.Synchronization",
        service = AopService.class
)
public class SynchronizationLocalServiceImpl extends SynchronizationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SynchronizationLocalServiceImpl.class);

    public void runGVESynchronization (boolean sendReport) {
        GVESynchronizationManager syncManager = new GVESynchronizationManager();
        try {
            syncManager.runSynchronization(sendReport);
        } catch (Exception e) {
            logger.error("Error while running GVE synchronization", e);
        }
    }

    public void runGVEParentSynchronization (boolean sendReport) {
        GVEParentSynchronizationManager syncManager = new GVEParentSynchronizationManager();
        try {
            syncManager.runParentSynchronization(sendReport);
        } catch (Exception e) {
            logger.error("Error while running GVE parent synchronization", e);
        }
    }
}
