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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.messaging.constants.MessagingConstants;
import com.weprode.facile.messaging.model.Message;
import com.weprode.facile.messaging.model.MessageFolder;
import com.weprode.facile.messaging.service.MessageFolderLocalServiceUtil;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.messaging.service.base.MessageFolderServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=messaging",
                "json.web.service.context.path=MessageFolder"
        },
        service = AopService.class
)
public class MessageFolderServiceImpl extends MessageFolderServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MessageFolderServiceImpl.class);

    /**
     * Add personal folder
     */
    @JSONWebService(method = "POST")
    public JSONObject addFolder(long parentFolderId, String folderName) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check ownership
            if (parentFolderId != 0) {
                MessageFolder parentFolder = MessageFolderLocalServiceUtil.getMessageFolder(parentFolderId);
                if (parentFolder.getUserId() != user.getUserId()) {
                    logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " adds folder into folder " + parentFolderId);
                    return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
                }
            }

            MessageFolder folder = MessageFolderLocalServiceUtil.addFolderMessage(user.getUserId(), folderName, MessagingConstants.PERSONAL_FOLDER_TYPE, parentFolderId);

            JSONObject jsonFolder = convertFolder(folder);
            jsonFolder.put(JSONConstants.SUB_FOLDERS, new JSONArray());

            result.put(JSONConstants.FOLDER, jsonFolder);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when adding a sub-folder to folder " + parentFolderId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    /**
     * Get all user message boxes and root folders
     */
    @JSONWebService(method = "GET")
    public JSONObject getAllUserFolders() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            JSONArray jsonBoxes = new JSONArray();
            List<MessageFolder> rootFolders = MessageFolderLocalServiceUtil.getUserSubFolders(user.getUserId(), 0);

            for (MessageFolder rootFolder : rootFolders) {
                JSONObject jsonFolder = convertFolder(rootFolder);

                // Number of new messages for the 4 main folders
                if (rootFolder.getType() != MessagingConstants.PERSONAL_FOLDER_TYPE) {
                    int nbUnread = MessageLocalServiceUtil.countUnreadMessages(rootFolder.getFolderId());
                    jsonFolder.put(JSONConstants.NB_UNREAD, nbUnread);
                } else {
                    jsonFolder.put(JSONConstants.NB_UNREAD, 0);
                }

                // Personal folders -> loop recursively
                if (rootFolder.getType() == MessagingConstants.PERSONAL_FOLDER_TYPE) {
                    JSONArray jsonSubFolders = recursiveFolder(user.getUserId(), rootFolder.getFolderId());
                    jsonFolder.put(JSONConstants.SUB_FOLDERS, jsonSubFolders);
                }

                jsonBoxes.put(jsonFolder);
            }

            result.put(JSONConstants.FOLDERS, jsonBoxes);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when fetching messaging boxes for userId " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    private JSONArray recursiveFolder(long userId, long folderId) {
        JSONArray jsonFolders =  new JSONArray();

        List<MessageFolder> subFolders = MessageFolderLocalServiceUtil.getUserSubFolders(userId, folderId);
        for (MessageFolder subFolder : subFolders) {
            JSONObject jsonFolder = convertFolder(subFolder);
            jsonFolder.put(JSONConstants.SUB_FOLDERS, recursiveFolder(userId, subFolder.getFolderId()));
            int nbUnread = MessageLocalServiceUtil.countUnreadMessages(subFolder.getFolderId());
            jsonFolder.put(JSONConstants.NB_UNREAD, nbUnread);
            jsonFolders.put(jsonFolder);
        }

        return jsonFolders;
    }

    private JSONObject convertFolder(MessageFolder folder) {
        JSONObject jsonFolder = new JSONObject();

        jsonFolder.put(JSONConstants.FOLDER_NAME, folder.getFolderName());
        jsonFolder.put(JSONConstants.FOLDER_ID, folder.getFolderId());
        jsonFolder.put(JSONConstants.TYPE, folder.getType());
        jsonFolder.put(JSONConstants.SUB_FOLDERS, new JSONArray());

        return jsonFolder;
    }

    /**
     * Add a folder
     */
    @JSONWebService(method = "POST")
    public JSONObject renameFolder(long folderId, String newLabel) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check ownership
            MessageFolder folder = MessageFolderLocalServiceUtil.getMessageFolder(folderId);
            if (user.getUserId() != folder.getUserId()) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " renames folder" + folderId);
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }
            folder.setFolderName(newLabel);
            folder = MessageFolderLocalServiceUtil.updateMessageFolder(folder);
            result.put(JSONConstants.RENAMED_FOLDER, convertFolder(folder));
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error when user " + user.getUserId() + " renames folder " + folderId + " with new name " + newLabel, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    /**
     * Remove a folder
     */
    @JSONWebService(method = "DELETE")
    public JSONObject deleteFolder(long folderId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check ownership
            MessageFolder folderToDelete = MessageFolderLocalServiceUtil.getMessageFolder(folderId);
            if (folderToDelete.getUserId() != user.getUserId()) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " deletes folder" + folderId);
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
            }

            if (folderToDelete.getType() != MessagingConstants.PERSONAL_FOLDER_TYPE) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " deletes folder" + folderId);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            MessageFolder trashFolder = MessageFolderLocalServiceUtil.getUserTrashFolder(user.getUserId());
            recursiveDelete(user.getUserId(), folderToDelete, trashFolder.getFolderId());
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error when removing message folder for userId " + user.getUserId() + " and folderId " + folderId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }


    /**
     * Recursive removal for folder
     */
    private static void recursiveDelete(long userId, MessageFolder folder, long trashFolderId) {
        List<MessageFolder> subFolders = MessageFolderLocalServiceUtil.getUserSubFolders(userId, folder.getFolderId());

        for (MessageFolder subFolder : subFolders) {
            recursiveDelete(userId, subFolder, trashFolderId);
        }

        // Move messages to trash
        for (Message message : MessageLocalServiceUtil.getMessagesByFolder(folder.getFolderId(), userId)) {
            message.setFolderId(trashFolderId);
            MessageLocalServiceUtil.updateMessage(message);
        }

        MessageFolderLocalServiceUtil.deleteMessageFolder(folder);
    }
}
