package com.weprode.nero.about.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.nero.about.model.UpdateInformation;
import com.weprode.nero.about.service.base.UpdateInformationLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.about.model.UpdateInformation",
        service = AopService.class
)
public class UpdateInformationLocalServiceImpl extends UpdateInformationLocalServiceBaseImpl {

    // Ajout d'une stat
    public UpdateInformation addUpdateInformation() throws SystemException {
        final long uiId = counterLocalService.increment();

        return this.createUpdateInformation(uiId);
    }

    // obtenir le ui
    public UpdateInformation getUpdateInformation() throws SystemException {
        List<UpdateInformation> uiList = updateInformationPersistence.findAll();

        if (uiList.isEmpty()) {
            return addUpdateInformation();
        }

        return uiList.get(0);
    }

    public UpdateInformation UpdateUpdateInformation(String text, Date modifyDate) throws SystemException {
        UpdateInformation ui;

        List<UpdateInformation> uiList = updateInformationPersistence.findAll();
        if (uiList.isEmpty()) {
            ui = addUpdateInformation();
        }
        else {
            ui = uiList.get(0);
        }
        ui.setDescription(text);
        ui.setModifyDate(modifyDate);

        updateInformationPersistence.update(ui);

        return ui;
    }
}
