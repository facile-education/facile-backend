package com.weprode.nero.preference.scheduler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.weprode.nero.preference.model.NotifyConfig;
import com.weprode.nero.preference.service.NotifyConfigLocalServiceUtil;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true, property = {"cron.expression=0 11 13 * * ?"},
        service = NotificationMessageListener.class
)
public class NotificationMessageListener extends BaseMessageListener {

    private static final Log logger = LogFactoryUtil.getLog(NotificationMessageListener.class);

    private static final String DEFAULT_CRON_EXPRESSION = "0 0 0 * * ?";
    private volatile boolean initialized;
    private TriggerFactory triggerFactory;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

//    private static final String FOLDER_PORTLET_ID = "foldersearchengine";
//    private static final String AGENDA_PORTLET_ID = "eventagendasearchengine";
//
//    public static final int SERVICE_DROPBOX = 0;
//    public static final int SERVICE_NEWS = 1;
//    public static final int SERVICE_DOC = 4;
//    public static final int SERVICE_AGENDA = 5;

    private static int DIGEST_PERIODE_DAILY = 1;
    private static int DIGEST_PERIODE_WEEKLY = 2;

//    private static long ROOT_GROUP_ID = -1;

    @Override
    protected void doReceive(Message message) throws Exception {
        long timer = System.currentTimeMillis();
        logger.warn("Start user notifications.");
        try {
            SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");

            // Get daily notifications users
            List<NotifyConfig> dailyNotificationConfigs = NotifyConfigLocalServiceUtil.getNotifyConfigActivateDigestPeriod(true, DIGEST_PERIODE_DAILY);

            if (dailyNotificationConfigs != null) {

                Calendar cal = new GregorianCalendar();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_WEEK, -1);
                String startDate = sdt.format(cal.getTime());
                String endDate = sdt.format(new Date());

                // Search from yesterday 17:30 to today 17:30
                // 173000 is an hard code to start search at 17:30:00
                String intervalSearchDate = "[" + startDate + "173000 TO " + endDate + "173000]";

                for (NotifyConfig dailyNotificationConfig : dailyNotificationConfigs) {
                    try {
                        User user = UserLocalServiceUtil.getUser(dailyNotificationConfig.getUserId());

                        String loginIP = user.getLoginIP();
                        if (loginIP != null && loginIP.length() > 0 && !user.isDefaultUser() && user.isActive()) {
                            long time = System.currentTimeMillis();
                            notifyUser(user, dailyNotificationConfig, intervalSearchDate, cal.getTime(), true);
                            logger.warn("Total time " + (System.currentTimeMillis() - time) + "ms for user " + user.getFirstName() + " " + user.getLastName());
                        }
                    } catch (Exception exc) {
                        logger.error(exc);
                    }
                }
            }

            // On fridays only
            if (isFriday()) {
                // Weekly notification users
                List<NotifyConfig> weeklyNotificationConfigs = NotifyConfigLocalServiceUtil.getNotifyConfigActivateDigestPeriod(true, DIGEST_PERIODE_WEEKLY);

                if (weeklyNotificationConfigs != null) {

                    Calendar cal = new GregorianCalendar();
                    cal.setTime(new Date());
                    cal.add(Calendar.DAY_OF_WEEK, -7);
                    String fromDate = sdt.format(cal.getTime());
                    String currentDate = sdt.format(new Date());

                    //the 000000 is an hard code to start search at 00:00:00
                    String intervalSearchDate = "[" + fromDate + "000000 TO " + currentDate + "000000]";

                    for (NotifyConfig weeklyNotificationConfig : weeklyNotificationConfigs) {

                        try {
                            User user = UserLocalServiceUtil.getUser(weeklyNotificationConfig.getUserId());

                            String loginIP = user.getLoginIP();
                            if (loginIP != null && loginIP.length() > 0 && !user.isDefaultUser() && user.isActive()) {
                                notifyUser(user, weeklyNotificationConfig, intervalSearchDate, cal.getTime(), false);
                            }
                        } catch (Exception exc) {
                            logger.error(exc);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        logger.warn("END user notifications task. Total time " + (System.currentTimeMillis() - timer) + "ms for all users.");
    }

    /**
     * Run notification for a given user
     * @param date: string for solr search
     * @param limitDate: anterior date until which generate Pronote reports
     */
    private void notifyUser(User user, NotifyConfig userNotifyConfig, String date, Date limitDate, boolean isDailyNotification) throws PortalException, SystemException {

        logger.warn("Run notification for user " + user.getFullName());

        // TODO
//        String portalUrl = PortalUtil.getPortalProperties().getProperty("absolute.url") + "/user/" +
//                user.getScreenName() + ENTMainUtilsLocalServiceUtil.getLoginDefaultPageLanding(user.getCompanyId());
//
//        boolean notifyDropBox = userNotifyConfig.getNotifyCasier();
//        // Parents should not be notified for group documents
//        boolean notifyGrpDoc = userNotifyConfig.getNotifyGrpDoc() && !RoleUtilsLocalServiceUtil.isParent(user);
//
//        List<ENTNotification> dropboxNotifications = new ArrayList<ENTNotification>();
//        List<ENTNotification> agendaNotifications = new ArrayList<ENTNotification>();
//
//        Map<Long, List<ENTNotification>> notificationMap = new HashMap<Long, List<ENTNotification>>();
//
//        // Get all modified hits during targeted period
//        Hits userHits = hitsFoundForNotification(user, userNotifyConfig, date);
//        if (userHits != null && userHits.getLength() > 0) {
//            List<Document> listUserHits = userHits.toList();
//
//            List<Long> uniqueEntryIds = new ArrayList<>();
//
//            // filter and sort the hits
//            for (Document anHit : listUserHits) {
//                try {
//                    long entryId = GetterUtil.getLong(anHit.get("entryClassPK"));
//                    String entryClassName = anHit.get("entryClassName");
//
//                    // Manage uniqueness
//                    if (uniqueEntryIds.contains(entryId)) {
//                        continue;
//                    }
//                    uniqueEntryIds.add(entryId);
//
//                    if (entryClassName.equals(EventAgenda.class.getName())) {
//                        EventAgenda event = EventAgendaLocalServiceUtil.getEventAgenda(entryId);
//
//                        if (user.getUserId() != event.getCreatorId()) {
//                            ENTNotification agendaNotification = getAgendaEventNotification(portalUrl, user, event);
//
//                            if (agendaNotification != null) {
//                                if (agendaNotification.getGroupId() > 0) {
//                                    addNotificationToMap(notificationMap, agendaNotification);
//                                } else {
//                                    agendaNotifications.add(agendaNotification);
//                                }
//                            }
//                        }
//                    } else if (entryClassName.equals(DLFileEntry.class.getName())) {
//                        try {
//                            FileEntry fileEntry = DLAppServiceUtil.getFileEntry(entryId);
//
//                            ENTNotification fileNotification = getFileNotification(portalUrl, user, fileEntry, notifyDropBox, notifyGrpDoc);
//
//                            if (fileNotification != null) {
//                                if (fileNotification.getService() == SERVICE_DROPBOX) {
//                                    dropboxNotifications.add(fileNotification);
//                                } else if (user.getUserId() != fileEntry.getUserId()) {
//                                    addNotificationToMap(notificationMap, fileNotification);
//                                }
//                            }
//                        } catch (Exception e) {
//                            logger.error("No fileEntry found for id : " + entryId);
//                        }
//                    } else if (entryClassName.equals(DLFolder.class.getName())) {
//                        try {
//                            Folder folder = DLAppLocalServiceUtil.getFolder(entryId);
//
//                            ENTNotification folderNotification = getFolderNotification(portalUrl, user, folder, notifyDropBox, notifyGrpDoc);
//
//                            if (folderNotification != null) {
//                                if (folderNotification.getService() == SERVICE_DROPBOX) {
//                                    dropboxNotifications.add(folderNotification);
//                                } else if (user.getUserId() != folder.getUserId()) {
//                                    addNotificationToMap(notificationMap, folderNotification);
//                                }
//                            }
//                        } catch (Exception e) {
//                            logger.error("No folder found for id : " + entryId);
//                        }
//                    } else if (entryClassName.equals(BlogsEntry.class.getName())) {
//                        BlogsEntry blogEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(entryId);
//
//                        if (user.getUserId() != blogEntry.getUserId()) {
//                            addNewsNotificationMap(user, notificationMap, portalUrl, blogEntry);
//                        }
//                    } else {
//                        logger.warn("Object " + entryClassName + " found during notification process for user "
//                                + user.getUserId() + ". It can't be classify");
//                        continue;
//                    }
//                } catch (Exception e) {
//                    logger.error(e);
//                }
//            }
//
//            int digestPeriod = userNotifyConfig.getDigestPeriod();
//            int totalNotificationNumber = dropboxNotifications.size() + agendaNotifications.size() + notificationMap.keySet().size();
//            logger.info(user.getFullName() + " has " + totalNotificationNumber + " item(s) in notification repport.");
//
//            // If there is a dropbox notif OR 1 other notif AND if the group map has
//            // at least one key, then build the message and send it
//            if (totalNotificationNumber > 0) {
//                String period = "";
//                if (digestPeriod == DIGEST_PERIODE_DAILY) {
//                    period = "quotidien";
//                } else if (digestPeriod == DIGEST_PERIODE_WEEKLY) {
//                    period = "hebdomadaire";
//                }
//                String subject = "Rapport" + " " + period + " " + "de l'activit&eacute; de votre ENT";
//
//                // Destinataires
//                List<Long> destFinal = new ArrayList<>();
//                destFinal.add(user.getUserId());
//
//                String content = writeNotificationContent(user, notificationMap, dropboxNotifications, agendaNotifications, period);
//
//                // Send report message
//                Long noReplySenderId = ENTMainUtilsLocalServiceUtil.getMailNoReplyUserId(user.getCompanyId());
//
//                MessageLocalServiceUtil.sendMessage(noReplySenderId, destFinal, subject, content, MessagingConstants.TYPE_REPORT);
//            }
//        }
    }

    // Return true if current day is sunday
    private static boolean isFriday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
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
        logger.info("Notification cronExpression: " + cronExpression);

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
