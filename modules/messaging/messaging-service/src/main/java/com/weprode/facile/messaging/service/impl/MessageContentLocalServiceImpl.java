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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.messaging.model.Message;
import com.weprode.facile.messaging.model.MessageContent;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.messaging.service.base.MessageContentLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.weprode.facile.messaging.model.MessageContent",
        service = AopService.class
)
public class MessageContentLocalServiceImpl extends MessageContentLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MessageContentLocalServiceImpl.class);

    public MessageContent addContent(long messageId, String messageContent) {
        try {
            MessageContent content = messageContentPersistence.create(messageId);
            content.setMessageContent(messageContent);
            content = messageContentPersistence.update(content);

            return content;
        } catch (Exception e) {
            logger.error("Error when creating a message content", e);
        }

        return null;
    }

    public String getContent(long messageId) {
        try {
            MessageContent messageContent = messageContentPersistence.fetchBymessageId(messageId);

            return messageContent.getMessageContent();
        } catch (Exception e) {
            try {
                logger.error("This should not happen when fetching content for message " + messageId);
                Message message = MessageLocalServiceUtil.getMessage(messageId);
                MessageContent messageContent = messageContentPersistence.fetchBymessageId(message.getSendMessageId());

                return messageContent.getMessageContent();
            } catch (Exception e2) {
                logger.error("Error when fetching a message content with messageId "+messageId, e2);
            }
        }

        return StringPool.BLANK;
    }
}
