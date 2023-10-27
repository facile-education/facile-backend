package com.weprode.nero.maintenance;

import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Component(
        immediate = true,
        property = {"cron.expression=0 0/1 * 1/1 * ? *, destination.name=liferay/scheduler_dispatch"},
        service = TmpFolderCleanupMessageListener.class
)
public class TmpFolderCleanupMessageListener extends BaseMessageListener {

    private static final Log logger = LogFactoryUtil.getLog(TmpFolderCleanupMessageListener.class);

    @Override
    public void doReceive(Message message) throws MessageListenerException {
        logger.info("doReceive");
        System.out.println("rtrtrt");
        new Thread(() -> {
            // Do as administrator, to get rid of permission issues
            List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(RoleUtilsLocalServiceUtil.getAdministratorRole().getRoleId());
            PrincipalThreadLocal.setName(adminUsers.get(0).getUserId());
            PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(adminUsers.get(0));
            PermissionThreadLocal.setPermissionChecker(permissionChecker);

            try {
                logger.info("Tmp folder cleanup starting ...");
                // Loop over all users
                List<User> allUsers = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
                for (User user : allUsers) {
                    try {
                        Folder tmpFolder = DLAppServiceUtil.getFolder(user.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.TMP_FILE_FOLDER_NAME);
                        logger.info("Processing user " + user.getFullName());
                        List<FileEntry> fileEntries = DLAppServiceUtil.getFileEntries(tmpFolder.getGroupId(), tmpFolder.getFolderId());
                        for (FileEntry fileEntry : fileEntries) {
                            DLAppServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
                            logger.info("Deleted tmp file " + fileEntry.getTitle());
                        }
                    } catch (NoSuchFolderException e) {
                        // Nothing
                    }

                }

            } catch (Exception e) {
                logger.error("Error running GVE synchronization", e);
            }

        }).start();
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException{
        logger.info("Activating");
        String cronExpression = GetterUtil.getString(properties.get("cron.expression"), "0 0/1 * 1/1 * ? *");
        logger.info("cronExpression: " + cronExpression);

//        if (initialized) {
//                // First deactivate the current job before we schedule.
//                deactivate();
//            }

        String className = getClass().getName();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.YEAR, 2100);
        Date endDate = cal.getTime();
        Trigger trigger = TriggerFactoryUtil.createTrigger(className, className, new Date(), endDate, "0 * * * * ?", TimeZone.getTimeZone("Europe/Paris"));

        schedulerEngineHelper.schedule(trigger, getStorageType(), "Cleanup tmp folders", DestinationNames.SCHEDULER_DISPATCH, null);
        //schedulerEngineHelper.run(PortalUtil.getDefaultCompanyId(), className, className, getStorageType());

        // Set the initialized flag.
        initialized = true;

    }

    protected StorageType getStorageType() {
        return StorageType.PERSISTED;
    }

    @Deactivate
    protected void deactivate() throws SchedulerException {
        if (initialized) {
            String className = getClass().getName();
            schedulerEngineHelper.delete(className, className, getStorageType());
        }
        // Clear the initialized flag
        initialized = false;
    }

    @Reference
    SchedulerEngineHelper schedulerEngineHelper;

    @Reference(unbind = "-")
    protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
        this.schedulerEngineHelper = schedulerEngineHelper;
    }

    private volatile boolean initialized;

}