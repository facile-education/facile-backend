package com.weprode.nero.eel.synchronization.scheduler;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(category = "weprode")
@Meta.OCD(
        id = "com.weprode.nero.eel.synchronization.scheduler.EELSynchronizationSchedulerConfiguration",
        localization = "content/Language",
        name = "configuration-name"
)
public interface EELSynchronizationSchedulerConfiguration {

    @Meta.AD(deflt = "0 0 5 * * ? *", name = "eel-synchronization-cron-expression", required = false)
    String eelSynchronizationCronExpression();

}
