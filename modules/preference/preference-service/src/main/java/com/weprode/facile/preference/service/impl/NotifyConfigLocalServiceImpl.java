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

package com.weprode.facile.preference.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.facile.preference.exception.NoSuchNotifyConfigException;
import com.weprode.facile.preference.model.NotifyConfig;
import com.weprode.facile.preference.service.base.NotifyConfigLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.preference.model.NotifyConfig",
        service = AopService.class
)
public class NotifyConfigLocalServiceImpl extends NotifyConfigLocalServiceBaseImpl {

    public NotifyConfig addNotifyConfig() throws SystemException {
        final long notifyConfigId = counterLocalService.increment();

        return this.createNotifyConfig(notifyConfigId);
    }

    public void deleteNotifyConfigByUser(long userId) throws NoSuchNotifyConfigException, SystemException{
        notifyConfigPersistence.removeByuserId(userId);
    }

    public NotifyConfig getOrCreateNotifyConfig(long userId) throws SystemException {
        try {
            return notifyConfigPersistence.findByuserId(userId);
        } catch (Exception e) {

            NotifyConfig newNotify = addNotifyConfig();
            newNotify.setUserId(userId);
            newNotify.setActivate(true);
            // 1 = daily - 2 = weekly
            newNotify.setDigestPeriod(2);
            newNotify.setNotifyActu(true);
            newNotify.setNotifyCasier(true);
            newNotify.setNotifyGrpDoc(true);
            newNotify.setNotifyAgenda(true);
            newNotify.setNotifySync(true);
            notifyConfigPersistence.update(newNotify);

            return newNotify;
        }
    }

    public List<NotifyConfig> getNotifyConfigActivate(boolean activate) throws SystemException {
        return notifyConfigPersistence.findByactivate(activate);
    }

    public List<NotifyConfig> getNotifyConfigActivateDigestPeriod(boolean activate, int digestPeriod) throws SystemException {
        return notifyConfigPersistence.findByactivate_digestPeriod(activate, digestPeriod);
    }
}
