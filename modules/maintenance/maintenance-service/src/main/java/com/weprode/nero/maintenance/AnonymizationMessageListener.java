package com.weprode.nero.maintenance;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.util.Date;
import java.util.Map;

@Component(
        immediate = true, property = {"cron.expression=0 34 8 * * ?"},
        service = AnonymizationMessageListener.class
)
public class AnonymizationMessageListener extends BaseMessageListener {

    private static final Log logger = LogFactoryUtil.getLog(AnonymizationMessageListener.class);

    private static final String DEFAULT_CRON_EXPRESSION = "0 0 0 * * ?";
    private volatile boolean initialized;
    private TriggerFactory triggerFactory;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) {
        new Thread(() -> {
            try {
                // Uncomment to use
                // logger.info("Scheduled anonymization starting ...");
                // AnonymizationUtil.anonymize();
                //logger.info("END anonymization.");
            } catch (Exception e) {
                logger.error("Error running anonymization", e);
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
        logger.info("Anonymization cronExpression: " + cronExpression);

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
