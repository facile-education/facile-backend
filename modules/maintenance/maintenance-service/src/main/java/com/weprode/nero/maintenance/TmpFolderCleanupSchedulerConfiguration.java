package com.weprode.nero.maintenance;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(category = "weprode")
@Meta.OCD(
        id = "com.weprode.nero.maintenance.TmpFolderCleanupSchedulerConfiguration",
        localization = "content/Language",
        name = "configuration-name"
)
public interface TmpFolderCleanupSchedulerConfiguration {

    @Meta.AD(deflt = "0 0 4 * * ? *", name = "tmp-cleanup-cron-expression", required = false)
    String tmpCleanupCronExpression();
}
