package com.weprode.nero.eel.synchronization.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.nero.eel.synchronization.model.ParentSynchro;
import com.weprode.nero.eel.synchronization.service.base.ParentSynchroLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Date;

@Component(
        property = "model.class.name=com.weprode.nero.user.model.ParentSynchro",
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
