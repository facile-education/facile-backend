package com.weprode.nero.eel.synchronization.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.eel.synchronization.service.SynchronizationLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(
        immediate = true, property = {"cron.expression=0 0 5 * * ?"},
        service = EELSynchronizationMessageListener.class
)
public class EELSynchronizationMessageListener extends BaseMessageListener {

    private static final Log logger = LogFactoryUtil.getLog(EELSynchronizationMessageListener.class);

    private static final String DEFAULT_CRON_EXPRESSION = "0 0 0 * * ?";
    private volatile boolean initialized;
    private TriggerFactory triggerFactory;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) {
        new Thread(() -> {
            // Do as administrator, to get rid of permission issues
            List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(RoleUtilsLocalServiceUtil.getAdministratorRole().getRoleId());
            PrincipalThreadLocal.setName(adminUsers.get(0).getUserId());
            PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(adminUsers.get(0));
            PermissionThreadLocal.setPermissionChecker(permissionChecker);

            try {
                if (PropsUtil.get(NeroSystemProperties.SYNCHRO_ENABLED).equals("true")) {
                    logger.info("Scheduled GVE synchronization starting ...");
                    SynchronizationLocalServiceUtil.runGVESynchronization(true);
                    logger.info("END GVE synchronization.");
                }
            } catch (Exception e) {
                logger.error("Error running GVE synchronization", e);
            }

            try {
                if (PropsUtil.get(NeroSystemProperties.SYNCHRO_PARENT_ENABLED).equals("true")) {
                    logger.info("Scheduled GVE parent synchronization starting ...");
                    SynchronizationLocalServiceUtil.runGVEParentSynchronization(false);
                    logger.info("END GVE parent synchronization.");
                }
            } catch (Exception e) {
                logger.error("Error running GVE parent synchronization", e);
            }

            logger.info("Scheduled user purge starting ...");
            try {
                //UserUtilsLocalServiceUtil.purgeExpiredUsers();
            } catch (Exception e) {
                logger.error("Error running user purge", e);
            }
        }).start();
    }

    /**
     * setModuleServiceLifecycle: So this requires some explanation...
     *
     * OSGi will start a component once all of it's dependencies are satisfied.  However, there
     * are times where you want to hold off until the portal is completely ready to go.
     *
     * This reference declaration is waiting for the ModuleServiceLifecycle's PORTAL_INITIALIZED
     * component which will not be available until, surprise surprise, the portal has finished
     * initializing.
     *
     * With this reference, this component activation waits until portal initialization has completed.
     */
    @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
    protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
        // Copied from an example
    }

    @Reference(unbind = "-")
    protected void setTriggerFactory(TriggerFactory triggerFactory) {
        this.triggerFactory = triggerFactory;
    }

    @Reference(unbind = "-")
    protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
        this.schedulerEngineHelper = schedulerEngineHelper;
    }

    /**
     * activate: Called whenever the properties for the component change (ala Config Admin)
     * or OSGi is activating the component.
     * @param properties The properties map from Config Admin.
     */
    @Activate
    @Modified
    protected void activate(Map<String,Object> properties) {
        // Extract the cron expression from the properties
        String cronExpression = GetterUtil.getString(properties.get("cron.expression"), DEFAULT_CRON_EXPRESSION);
        logger.info("EEL cronExpression: " + cronExpression);

        // Create a new trigger definition for the job.
        String listenerClass = getClass().getName();
        Trigger jobTrigger = triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, cronExpression);

        // Wrap the current scheduler entry in our new wrapper.
        // Use the persisted storaget type and set the wrapper back to the class field.
        schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);

        // If we were initialized (i.e. if this is called due to CA modification)
        if (initialized) {
            // First deactivate the current job before we schedule.
            deactivate();
        }

        // Register the scheduled task
        schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);

        // Set the initialized flag.
        initialized = true;
    }

    /**
     * deactivate: Called when OSGi is deactivating the component.
     */
    @Deactivate
    protected void deactivate() {
        // If we previously were initialized
        if (initialized) {
            // Unschedule the job so it is cleaned up
            try {
                schedulerEngineHelper.unschedule(schedulerEntryImpl, getStorageType());
            } catch (SchedulerException se) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to unschedule trigger", se);
                }
            }

            // Unregister this listener
            schedulerEngineHelper.unregister(this);
        }

        // Clear the initialized flag
        initialized = false;
    }

    /**
     * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
     * @return StorageType The storage type to use.
     */
    protected StorageType getStorageType() {
        if (schedulerEntryImpl instanceof StorageTypeAware) {
            return ((StorageTypeAware) schedulerEntryImpl).getStorageType();
        }

        return StorageType.MEMORY_CLUSTERED;
    }

}
