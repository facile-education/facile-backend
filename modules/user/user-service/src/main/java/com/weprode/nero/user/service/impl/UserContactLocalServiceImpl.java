package com.weprode.nero.user.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.nero.user.exception.NoSuchContactException;
import com.weprode.nero.user.model.UserContact;
import com.weprode.nero.user.service.base.UserContactLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.weprode.nero.user.model.UserContact",
        service = AopService.class
)
public class UserContactLocalServiceImpl extends UserContactLocalServiceBaseImpl {

    public UserContact addUserContact() throws SystemException {
        final long userContactId = counterLocalService.increment();

        return userContactPersistence.create(userContactId);
    }

    /**
     * Create a UserContact, with default values
     */
    public UserContact addUserContact(long userId) throws SystemException {
        final UserContact userContact = addUserContact();

        userContact.setUserId(userId);
        userContact.setAddress("");
        userContact.setIsAddressAuthorized(false);
        userContact.setMail("");
        userContact.setIsMailAuthorized(false);
        userContact.setHomePhone("");
        userContact.setProPhone("");
        userContact.setMobilePhone("");
        userContact.setMobilePhoneSMS("");

        return userContact;
    }


    /**
     * Return the user contact, given the userId
     * If not existing, create it
     */
    public UserContact getUserContactByUserId(long userId) throws SystemException {
        try {
            return userContactPersistence.findByuserId(userId);
        }
        catch (NoSuchContactException e) {
            return addUserContact(userId);
        }
    }

}
