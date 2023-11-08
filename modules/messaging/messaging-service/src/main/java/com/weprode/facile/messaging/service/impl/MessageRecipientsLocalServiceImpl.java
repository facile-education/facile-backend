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
import com.weprode.facile.messaging.exception.NoSuchMessageRecipientsException;
import com.weprode.facile.messaging.model.MessageRecipients;
import com.weprode.facile.messaging.service.base.MessageRecipientsLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.messaging.model.MessageRecipients",
        service = AopService.class
)
public class MessageRecipientsLocalServiceImpl extends MessageRecipientsLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MessageRecipientsLocalServiceImpl.class);

    public MessageRecipients addMessageRecipients(long messageId, List<Long> recipientList) throws SystemException {
        StringBuilder recipients = new StringBuilder();

        for (long recipient: recipientList) {
            recipients.append(recipient).append(",");
        }
        MessageRecipients messageRecipients = messageRecipientsPersistence.create(messageId);
        messageRecipients.setRecipients(recipients.toString());

        return messageRecipientsPersistence.update(messageRecipients);
    }

    public String getMessageRecipientsAsString(long messageId) throws SystemException {
        try {
            return messageRecipientsPersistence.findBymessageId(messageId).getRecipients();
        } catch (NoSuchMessageRecipientsException e) {
            // No recipient
            logger.debug(e);
        }

        return StringPool.BLANK;
    }

    public List<User> getRecipients(long messageId) {
        List<User> recipients = new ArrayList<>();

        try {
            String recipientIdListStr = messageRecipientsPersistence.findBymessageId(messageId).getRecipients();
            String[] recipientsArray = recipientIdListStr.split(",");

            for (String recipientIdStr : recipientsArray) {
                if (!recipientIdStr.isEmpty()) {
                    try {
                        User recipient = UserLocalServiceUtil.getUser(Long.parseLong(recipientIdStr));
                        recipients.add(recipient);
                    } catch (Exception e) {
                        // User does not exist anymore
                        logger.debug(e);
                    }
                }
            }
        } catch (Exception e) {
            // There might be no recipient (eg. draft)
            logger.debug(e);
        }

        return recipients;
    }

    public boolean deleteMessageRecipientsByMessageId(long messageId) throws SystemException{
        try {
            messageRecipientsPersistence.removeBymessageId(messageId);
        } catch (NoSuchMessageRecipientsException e) {
            // No message recipient
            logger.debug(e);
        }

        return true;
    }
}
