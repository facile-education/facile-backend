package com.weprode.nero.preference.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.nero.preference.exception.NoSuchUserPropertiesException;
import com.weprode.nero.preference.model.UserProperties;
import com.weprode.nero.preference.service.base.UserPropertiesLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.weprode.nero.preference.model.UserProperties",
        service = AopService.class
)
public class UserPropertiesLocalServiceImpl extends UserPropertiesLocalServiceBaseImpl {

    public UserProperties addUserProperties(long userId) throws SystemException {
        UserProperties userProperties = userPropertiesPersistence.create(userId);

        userProperties.setManualAccount(false);
        userProperties.setWebdavActivated(false);
        userProperties.setPreferedSchoolId(0);
        userProperties.setThemeColor("2982B9");
        userProperties = userPropertiesPersistence.update(userProperties);

        return userProperties;
    }

}
