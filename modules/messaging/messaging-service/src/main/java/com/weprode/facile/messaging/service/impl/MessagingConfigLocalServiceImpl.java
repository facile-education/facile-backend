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

package com.weprode.facile.messaging.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.messaging.model.MessagingConfig;
import com.weprode.facile.messaging.service.base.MessagingConfigLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.messaging.model.MessagingConfig",
        service = AopService.class
)
public class MessagingConfigLocalServiceImpl extends MessagingConfigLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MessagingConfigLocalServiceImpl.class);

    public MessagingConfig addDefaultMessagingConfig(long userId) {
        try {
            User user = UserLocalServiceUtil.getUser(userId);

            final MessagingConfig messagingConfig = messagingConfigPersistence.create(userId);
            messagingConfig.setSignature(StringPool.BLANK);
            messagingConfig.setIsSignatureActive(false);

            messagingConfig.setIsForwardActive(true);
            messagingConfig.setForwardMail(user.getEmailAddress());

            messagingConfig.setIsAutoReplyActive(false);
            messagingConfig.setAutoReplyContent(StringPool.BLANK);

            return messagingConfigPersistence.update(messagingConfig);
        } catch (Exception e) {
            return null;
        }
    }

    public MessagingConfig getOrCreateMessagingConfig(long userId) throws SystemException {
        MessagingConfig messagingConfig = fetchMessagingConfig(userId);

        if (messagingConfig != null) {
            return messagingConfig;
        }

        return addDefaultMessagingConfig(userId);
    }

    /**
     * Returns user messaging signature if it is active
     */
    public String getSignature(long userId) {
        try {
            MessagingConfig messagingConfig = getOrCreateMessagingConfig(userId);

            if (messagingConfig.getIsSignatureActive()) {
                return messagingConfig.getSignature();
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return StringPool.BLANK;
    }

    public List<String> getForwardAddresses(long userId) throws SystemException {
        List<String> result = new ArrayList<>();

        MessagingConfig messagingConfig = getOrCreateMessagingConfig(userId);

        if (messagingConfig != null && messagingConfig.getIsForwardActive() && !messagingConfig.getForwardMail().isEmpty()) {

            // May be multiple addresses here, comma is the separator character
            String[] forwardMailTab = messagingConfig.getForwardMail().split(",");
            for (String forwardMail : forwardMailTab) {
                if (!forwardMail.equals("")) {
                    result.add(forwardMail);
                }
            }
        }

        return result;
    }

    /**
     * Returns true if user messaging autoReply is active
     */
    public boolean hasAutoReplyActive(long userId) {
        try {
            MessagingConfig messagingConfig = getOrCreateMessagingConfig(userId);
            return messagingConfig.getIsAutoReplyActive();
        } catch (Exception e) {
            logger.debug(e);
        }

        return false;
    }

    /**
     * Returns user messaging autoReply content if it is active
     */
    public String getAutoReply(long userId) {
        try {
            MessagingConfig messagingConfig = getOrCreateMessagingConfig(userId);
            if (messagingConfig.getIsAutoReplyActive()) {
                return messagingConfig.getAutoReplyContent();
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return StringPool.BLANK;
    }
}
