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

package com.weprode.facile.user.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.facile.user.exception.NoSuchContactException;
import com.weprode.facile.user.model.UserContact;
import com.weprode.facile.user.service.base.UserContactLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.weprode.facile.user.model.UserContact",
        service = AopService.class
)
public class UserContactLocalServiceImpl extends UserContactLocalServiceBaseImpl {

    public UserContact addUserContact(long userId) throws SystemException {

        UserContact userContact = userContactPersistence.create(userId);
        userContact.setAddress("");
        userContact.setHomePhone("");
        userContact.setProPhone("");
        userContact.setMobilePhone("");
        userContact = userContactPersistence.update(userContact);

        return userContact;
    }


    /**
     * Return the user contact, given the userId
     * If not existing, create it
     */
    public UserContact getUserContactByUserId(long userId) throws SystemException {
        try {
            return userContactPersistence.findByPrimaryKey(userId);
        }
        catch (NoSuchContactException e) {
            return addUserContact(userId);
        }
    }

}
