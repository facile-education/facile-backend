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

package com.weprode.facile.statistic.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.statistic.model.LoolStat;
import com.weprode.facile.statistic.service.base.LoolStatLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Date;

@Component(
        property = "model.class.name=com.weprode.facile.statistic.model.LoolStat",
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
