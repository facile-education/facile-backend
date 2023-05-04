package com.weprode.nero.preference.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.weprode.nero.preference.model.UserMobileToken;
import com.weprode.nero.preference.service.base.UserMobileTokenLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.preference.model.UserMobileToken",
        service = AopService.class
)
public class UserMobileTokenLocalServiceImpl extends UserMobileTokenLocalServiceBaseImpl {

    /**
     * Add new userMobileToken
     */
    public UserMobileToken addUserMobileToken(long userId, String mobileToken) throws SystemException {
        final long userMobileTokenId = counterLocalService.increment();
        UserMobileToken userMobileToken = userMobileTokenPersistence.create(userMobileTokenId);

        userMobileToken.setUserId(userId);
        userMobileToken.setMobileToken(mobileToken);
        return userMobileTokenPersistence.update(userMobileToken);
    }

    /**
     * Fetch userMobileToken by userId and mobile token
     */
    public UserMobileToken getUserMobileToken(long userId, String mobileToken) throws SystemException {
        return userMobileTokenPersistence.fetchByuserId_mobileToken(userId, mobileToken);
    }

    public List<UserMobileToken> getAllUserMobileTokens(long userId) throws SystemException {
        return userMobileTokenPersistence.findByuserId(userId);
    }
}
