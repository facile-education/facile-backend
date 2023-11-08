/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.maintenance;

import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Map;

@Component(
        configurationPid = "com.weprode.facile.maintenance.TmpFolderCleanupSchedulerConfiguration",
        service = SchedulerJobConfiguration.class
)
public class TmpFolderCleanupScheduler implements SchedulerJobConfiguration {

    @Override
    public String getDestinationName() {
        return DestinationNames.SCHEDULER_DISPATCH;
    }

    @Override
    public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
        return () -> {
            logger.info("RUN");
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
                        if (user.getGroup() != null) {
                            logger.info("Processing user " + user.getFullName() + " with groupId " + user.getGroupId());
                            Folder tmpFolder = DLAppServiceUtil.getFolder(user.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.TMP_FILE_FOLDER_NAME);
                            logger.info(">>> folder Id = " + tmpFolder.getFolderId());
                            List<FileEntry> fileEntries = DLAppServiceUtil.getFileEntries(tmpFolder.getGroupId(), tmpFolder.getFolderId());
                            for (FileEntry fileEntry : fileEntries) {
                                DLAppServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
                                logger.info("Deleted tmp file " + fileEntry.getTitle());
                            }
                        }
                    } catch (NoSuchFolderException e) {
                        // Nothing
                    }

                }

            } catch (Exception e) {
                logger.error("Error running tmp folder cleanup", e);
            }
        };
    }

    @Override
    public TriggerConfiguration getTriggerConfiguration() {
        // Cron expression is picked in OSGI configuration file
        return TriggerConfiguration.createTriggerConfiguration(schedulerConfiguration.tmpCleanupCronExpression());
    }

    @Activate
    protected void activate(Map<String, Object> properties) {
        schedulerConfiguration = ConfigurableUtil.createConfigurable(TmpFolderCleanupSchedulerConfiguration.class, properties);
        logger.info("Activating tmp folder cleanup scheduler");
    }

    private TmpFolderCleanupSchedulerConfiguration schedulerConfiguration;
    private static final Log logger = LogFactoryUtil.getLog(TmpFolderCleanupScheduler.class);

}