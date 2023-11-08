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

package com.weprode.facile.eel.synchronization.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.eel.synchronization.GVEParentSynchronizationManager;
import com.weprode.facile.eel.synchronization.GVESynchronizationManager;
import com.weprode.facile.eel.synchronization.service.base.SynchronizationLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.weprode.facile.user.model.Synchronization",
        service = AopService.class
)
public class SynchronizationLocalServiceImpl extends SynchronizationLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(SynchronizationLocalServiceImpl.class);

    public void runGVESynchronization (boolean sendReport) {
        GVESynchronizationManager syncManager = new GVESynchronizationManager();
        try {
            syncManager.runSynchronization(sendReport);
        } catch (Exception e) {
            logger.error("Error while running GVE synchronization", e);
        }
    }

    public void runGVEParentSynchronization (boolean sendReport) {
        GVEParentSynchronizationManager syncManager = new GVEParentSynchronizationManager();
        try {
            syncManager.runParentSynchronization(sendReport);
        } catch (Exception e) {
            logger.error("Error while running GVE parent synchronization", e);
        }
    }
}
