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

package com.weprode.facile.messaging.search;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.weprode.facile.messaging.model.Message;
import com.weprode.facile.messaging.model.MessageContent;
import com.weprode.facile.messaging.service.MessageAttachFileLocalServiceUtil;
import com.weprode.facile.messaging.service.MessageContentLocalServiceUtil;
import com.weprode.facile.messaging.service.MessageFolderLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.facile.messaging.model.Message",
        service = ModelDocumentContributor.class
)
public class MessageModelDocumentContributor
        implements ModelDocumentContributor<Message> {

    @Override
    public void contribute(Document document, Message message) {
        try {
            MessageContent content = MessageContentLocalServiceUtil.getMessageContent(message.getMessageId());

            document.addDate(Field.MODIFIED_DATE, message.getSendDate());
            document.addText(Field.TITLE, message.getMessageSubject());
            document.addNumber(Field.COMPANY_ID, message.getCompanyId());
            User owner = MessageFolderLocalServiceUtil.getFolderUser(message.getFolderId());
            if (owner != null) {
                document.addNumber(Field.GROUP_ID, owner.getGroupId());
                document.addNumber(Field.SCOPE_GROUP_ID, owner.getGroupId());
            }

            Role userRole = RoleLocalServiceUtil.getRole(message.getCompanyId(), RoleConstants.USER);
            document.addText(Field.ROLE_ID, new String[]{String.valueOf(userRole.getRoleId())});

            document.addText(Field.CONTENT, HtmlParserUtil.extractText(content.getMessageContent()));
            document.addDate(Field.DISPLAY_DATE, message.getSendDate());
            document.addNumber(Field.FOLDER_ID, message.getFolderId());
            document.addNumber(Field.USER_ID, message.getSenderId());
            document.addText(Field.USER_NAME, message.getSenderName());
            document.addNumber(Field.TYPE, message.getType());

            List<String> fileNames = new ArrayList<>();

            for (Long attachmentId : MessageAttachFileLocalServiceUtil.getMessageAttachFileIds(message.getMessageId())) {
                FileEntry attachment = DLAppServiceUtil.getFileEntry(attachmentId);
                fileNames.add(attachment.getTitle());
            }

            String[] attachmentNames = fileNames.toArray(String[]::new);
            document.addText(Field.ASSET_TAG_NAMES, attachmentNames);

            logger.debug("Added message fields to document " + document);
        } catch (PortalException e) {
            logger.warn("Unable to index message " + message.getMessageId(), e);
        }
    }

    private static final Log logger = LogFactoryUtil.getLog(
            MessageModelDocumentContributor.class);
}
