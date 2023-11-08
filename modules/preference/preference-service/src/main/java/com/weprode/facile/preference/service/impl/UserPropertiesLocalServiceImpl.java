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
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.base.UserPropertiesLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.weprode.facile.preference.model.UserProperties",
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
