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

package com.weprode.facile.document;

import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.document.model.LoolToken;
import com.weprode.facile.document.service.LoolTokenLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(
        configurationPid = "com.weprode.facile.document.LoolTokenCleanupSchedulerConfiguration",
        service = SchedulerJobConfiguration.class
)
public class LoolTokenCleanupScheduler implements SchedulerJobConfiguration {

    @Override
    public String getDestinationName() {
        return DestinationNames.SCHEDULER_DISPATCH;
    }

    @Override
    public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
        return () -> {
            // Do as administrator, to get rid of permission issues
            List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(RoleUtilsLocalServiceUtil.getAdministratorRole().getRoleId());
            PrincipalThreadLocal.setName(adminUsers.get(0).getUserId());
            PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(adminUsers.get(0));
            PermissionThreadLocal.setPermissionChecker(permissionChecker);

            try {
                logger.info("Lool token cleanup scheduled task starting ...");
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.HOUR, -1);
                Date oneHour = cal.getTime();
                // Loop over all tokens
                List<LoolToken> allTokens = LoolTokenLocalServiceUtil.getLoolTokens(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
                for (LoolToken loolToken : allTokens) {
                    if (loolToken.getEditionDate() == null || loolToken.getEditionDate().before(oneHour)) {
                        LoolTokenLocalServiceUtil.deleteLoolToken(loolToken);
                    }
                }

                logger.info("Lool token cleanup scheduled task ended");
            } catch (Exception e) {
                logger.error("Error running lool token cleanup scheduled task", e);
            }
        };
    }

    @Override
    public TriggerConfiguration getTriggerConfiguration() {
        // Cron expression is picked in OSGI configuration file
        return TriggerConfiguration.createTriggerConfiguration(schedulerConfiguration.loolTokenCleanupCronExpression());
    }

    @Activate
    protected void activate(Map<String, Object> properties) {
        schedulerConfiguration = ConfigurableUtil.createConfigurable(LoolTokenCleanupSchedulerConfiguration.class, properties);
        logger.info("Activating Lool token cleanup scheduler");
    }

    private LoolTokenCleanupSchedulerConfiguration schedulerConfiguration;
    private static final Log logger = LogFactoryUtil.getLog(LoolTokenCleanupScheduler.class);

}