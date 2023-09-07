package com.weprode.nero.school.life.utils;

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
import com.weprode.nero.school.life.constants.SchoollifeConstants;
import com.weprode.nero.school.life.model.SchoollifeSession;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.SessionStudentLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(
        immediate = true, property = {"cron.expression=0 0 17 * * ?"},
        service = SchoollifeAbsenceMessageListener.class
)
public class SchoollifeAbsenceMessageListener extends BaseMessageListener {

    private static final Log logger = LogFactoryUtil.getLog(SchoollifeAbsenceMessageListener.class);

    private static final String DEFAULT_CRON_EXPRESSION = "0 0 0 * * ?";
    private volatile boolean initialized;
    private TriggerFactory triggerFactory;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;


    @Override
    protected void doReceive(Message message) {
        long timer = System.currentTimeMillis();
        logger.warn("Start schoollife absence scheduler");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // Fetch all passed schoollife sessions of the current day for which the 'call' has been done
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.set(Calendar.HOUR_OF_DAY, 7);
            Date startDate = cal.getTime();
            logger.info("Running absence scheduler from " + sdf.format(startDate) + " to " + sdf.format(now));

            // Retenue
            List<SchoollifeSession> retenueSessions = SchoollifeSessionLocalServiceUtil.getUnnotifiedSessions(SchoollifeConstants.TYPE_RETENUE, startDate, now);
            for (SchoollifeSession retenueSession : retenueSessions) {

                List<Long> studentIds = SessionStudentLocalServiceUtil.getAbsentStudents(retenueSession.getSchoollifeSessionId());
                logger.info(studentIds.size() + " students absent in retenue session " + retenueSession.getSchoollifeSessionId());
                for (Long studentId : studentIds) {
                    NotificationUtil.notifyRetenueAbsence(studentId, retenueSession.getSchoollifeSessionId());
                }

                // Set notification sent
                retenueSession.setAbsenceNotificationSent(true);
                SchoollifeSessionLocalServiceUtil.updateSchoollifeSession(retenueSession);

            }

            // Travaux à refaire
            List<SchoollifeSession> travauxSessions = SchoollifeSessionLocalServiceUtil.getUnnotifiedSessions(SchoollifeConstants.TYPE_TRAVAUX, startDate, now);
            for (SchoollifeSession travauxSession : travauxSessions) {

                List<Long> studentIds = SessionStudentLocalServiceUtil.getAbsentStudents(travauxSession.getSchoollifeSessionId());
                logger.info(studentIds.size() + " students absent in travaux session " + travauxSession.getSchoollifeSessionId());
                for (Long studentId : studentIds) {
                    NotificationUtil.notifyTravauxAbsence(studentId, travauxSession.getSchoollifeSessionId());
                }

                // Set notification sent
                travauxSession.setAbsenceNotificationSent(true);
                SchoollifeSessionLocalServiceUtil.updateSchoollifeSession(travauxSession);
            }

            // Etude
            List<SchoollifeSession> etudeSessions = SchoollifeSessionLocalServiceUtil.getUnnotifiedSessions(SchoollifeConstants.TYPE_ETUDE, startDate, now);
            for (SchoollifeSession etudeSession : etudeSessions) {

                List<Long> studentIds = SessionStudentLocalServiceUtil.getAbsentStudents(etudeSession.getSchoollifeSessionId());
                logger.info(studentIds.size() + " students absent in etude session " + etudeSession.getSchoollifeSessionId());
                for (Long studentId : studentIds) {
                    NotificationUtil.notifyEtudeAbsence(studentId, etudeSession.getSchoollifeSessionId());
                }

                // Set notification sent
                etudeSession.setAbsenceNotificationSent(true);
                SchoollifeSessionLocalServiceUtil.updateSchoollifeSession(etudeSession);
            }

        } catch (Exception e) {
            logger.debug(e);
        }

        logger.warn("END schoollife absence scheduler. Total time " + (System.currentTimeMillis() - timer) + "ms.");
    }


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
        logger.info("Notification cronExpression: " + cronExpression);

        // Create a new trigger definition for the job.
        String listenerClass = getClass().getName();
        Trigger jobTrigger = triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, cronExpression);

        // Wrap the current scheduler entry in our new wrapper.
        // Use the persisted storage type and set the wrapper back to the class field.
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
