package com.weprode.nero.messaging.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessageContent;
import com.weprode.nero.messaging.service.MessageLocalServiceUtil;
import com.weprode.nero.messaging.service.base.MessageContentLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.weprode.nero.messaging.model.MessageContent",
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
