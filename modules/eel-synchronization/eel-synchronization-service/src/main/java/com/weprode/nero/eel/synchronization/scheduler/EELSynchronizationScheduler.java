package com.weprode.nero.eel.synchronization.scheduler;

import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
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
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.eel.synchronization.service.SynchronizationLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Map;

@Component(
        configurationPid = "com.weprode.nero.eel.synchronization.scheduler.EELSynchronizationSchedulerConfiguration",
        service = SchedulerJobConfiguration.class
)
public class EELSynchronizationScheduler implements SchedulerJobConfiguration {

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
                    SynchronizationLocalServiceUtil.runGVEParentSynchronization(true);
                    logger.info("END GVE parent synchronization.");
                }
            } catch (Exception e) {
                logger.error("Error running GVE parent synchronization", e);
            }

            logger.info("Scheduled user purge starting ...");
            try {
                UserUtilsLocalServiceUtil.purgeExpiredUsers();
            } catch (Exception e) {
                logger.error("Error running user purge", e);
            }
        };
    }

    @Override
    public TriggerConfiguration getTriggerConfiguration() {
        // Cron expression is picked in OSGI configuration file
        return TriggerConfiguration.createTriggerConfiguration(schedulerConfiguration.eelSynchronizationCronExpression());
    }

    @Activate
    protected void activate(Map<String, Object> properties) {
        schedulerConfiguration = ConfigurableUtil.createConfigurable(EELSynchronizationSchedulerConfiguration.class, properties);
        logger.info("Activating EEL synchronization scheduler");
    }

    private EELSynchronizationSchedulerConfiguration schedulerConfiguration;
    private static final Log logger = LogFactoryUtil.getLog(EELSynchronizationScheduler.class);

}