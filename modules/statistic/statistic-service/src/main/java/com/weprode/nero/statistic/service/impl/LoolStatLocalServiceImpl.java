package com.weprode.nero.statistic.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.statistic.model.LoolStat;
import com.weprode.nero.statistic.service.base.LoolStatLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Date;

@Component(
        property = "model.class.name=com.weprode.nero.statistic.model.LoolStat",
        service = AopService.class
)
public class LoolStatLocalServiceImpl extends LoolStatLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(LoolStatLocalServiceImpl.class);

    public LoolStat addLoolStat(long objectId, long userId, boolean saveAction, int type) {
        try {
            long statId = counterLocalService.increment();

            LoolStat loolStat = createLoolStat(statId);
            loolStat.setObjectId(objectId);
            loolStat.setUserId(userId);
            loolStat.setType(type);
            loolStat.setActionDate(new Date());
            loolStat.setSaveAction(saveAction);
            loolStat = loolStatPersistence.update(loolStat);

            return loolStat;
        } catch (Exception e) {
            logger.debug(e);
        }

        return null;
    }
}
