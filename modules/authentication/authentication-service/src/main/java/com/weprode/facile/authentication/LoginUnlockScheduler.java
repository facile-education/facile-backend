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

package com.weprode.facile.authentication;

import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;
import com.weprode.facile.authentication.model.LoginLock;
import com.weprode.facile.authentication.service.LoginLockLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(
        configurationPid = "com.weprode.facile.authentication.LoginUnlockSchedulerConfiguration",
        service = SchedulerJobConfiguration.class
)
public class LoginUnlockScheduler implements SchedulerJobConfiguration {

    @Override
    public String getDestinationName() {
        return DestinationNames.SCHEDULER_DISPATCH;
    }

    @Override
    public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
        return () -> {
            try {
                logger.debug("Login unlock starting ...");
                // Loop over all users
                List<LoginLock> allLocks = LoginLockLocalServiceUtil.getLoginLocks(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
                for (LoginLock loginLock : allLocks) {
                    try {
                        if (loginLock.getLockEndDate() != null && loginLock.getLockEndDate().before(new Date())) {
                            logger.info("Unlocking login " + loginLock.getLogin());
                            loginLock.setFailedLoginAttempts(0);
                            loginLock.setIsLocked(false);
                            loginLock.setLockEndDate(null);
                            LoginLockLocalServiceUtil.updateLoginLock(loginLock);
                        }
                    } catch (Exception e) {
                        logger.error("Error unlocking login " + loginLock.getLogin(), e);
                    }
                }

            } catch (Exception e) {
                logger.error("Error running login unlock task", e);
            }
        };
    }

    @Override
    public TriggerConfiguration getTriggerConfiguration() {
        // Cron expression is picked in OSGI configuration file
        return TriggerConfiguration.createTriggerConfiguration(schedulerConfiguration.loginUnlockCronExpression());
    }

    @Activate
    protected void activate(Map<String, Object> properties) {
        schedulerConfiguration = ConfigurableUtil.createConfigurable(LoginUnlockSchedulerConfiguration.class, properties);
        logger.info("Activating login unlock scheduler");
    }

    private LoginUnlockSchedulerConfiguration schedulerConfiguration;
    private static final Log logger = LogFactoryUtil.getLog(LoginUnlockScheduler.class);

}