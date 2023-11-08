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
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.facile.eel.synchronization.model.ParentSynchro;
import com.weprode.facile.eel.synchronization.service.base.ParentSynchroLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Date;

@Component(
        property = "model.class.name=com.weprode.facile.user.model.ParentSynchro",
        service = AopService.class
)
public class ParentSynchroLocalServiceImpl extends ParentSynchroLocalServiceBaseImpl {

    public ParentSynchro addParentSynchro(long schoolId, Date startDate, Date endDate, String fileName,
                                          long lineCount, long errorCount) throws SystemException {
        ParentSynchro synchro = parentSynchroPersistence.create(schoolId);

        synchro.setStartDate(startDate);
        synchro.setEndDate(endDate);
        synchro.setFileName(fileName);
        synchro.setLineCount(lineCount);
        synchro.setErrorCount(errorCount);

        parentSynchroPersistence.update(synchro);

        return synchro;
    }

}
