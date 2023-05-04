package com.weprode.nero.messaging.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.messaging.constants.MessagingConstants;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessageFolder;
import com.weprode.nero.messaging.service.MessageFolderLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageLocalServiceUtil;
import com.weprode.nero.messaging.service.base.MessageFolderServiceBaseImpl;
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
    @JSONWebService(method = "GET")
    public JSONObject addFolder(long parentFolderId, String folderName) throws PrincipalException {
        JSONObject result = new JSONObject();

        long userId = getGuestOrUserId();
        MessageFolder folder = MessageFolderLocalServiceUtil.addFolderMessage(userId, folderName, MessagingConstants.PERSONAL_FOLDER_TYPE, parentFolderId);

        JSONObject jsonFolder = convertFolder(folder);
        jsonFolder.put(JSONConstants.SUB_FOLDERS, new JSONArray());

        result.put(JSONConstants.FOLDER, jsonFolder);
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    /**
     * Get all user message boxes and root folders
     */
    @JSONWebService(method = "GET")
    public JSONObject getAllUserFolders() throws PrincipalException {
        JSONObject result = new JSONObject();

        long userId = getGuestOrUserId();
        try {
            JSONArray jsonBoxes = new JSONArray();
            List<MessageFolder> rootFolders = MessageFolderLocalServiceUtil.getUserSubFolders(userId, 0);

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
                    JSONArray jsonSubFolders = recursiveFolder(userId, rootFolder.getFolderId());
                    jsonFolder.put(JSONConstants.SUB_FOLDERS, jsonSubFolders);
                }

                jsonBoxes.put(jsonFolder);
            }

            result.put(JSONConstants.FOLDERS, jsonBoxes);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when fetching messaging boxes for userId " + userId, e);
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
    @JSONWebService(method = "GET")
    public JSONObject renameFolder(long folderId, String newLabel) throws PrincipalException {
        JSONObject result = new JSONObject();

        long userId = getGuestOrUserId();
        result.put(JSONConstants.SUCCESS, false);
        try {
            MessageFolder folder = MessageFolderLocalServiceUtil.getMessageFolder(folderId);
            folder.setFolderName(newLabel);
            folder = MessageFolderLocalServiceUtil.updateMessageFolder(folder);
            result.put(JSONConstants.RENAMED_FOLDER, convertFolder(folder));
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error when user " + userId + " renames folder " + folderId + " with new name " + newLabel, e);
        }

        return result;
    }

    /**
     * Remove a folder
     */
    @JSONWebService(method = "GET")
    public JSONObject deleteFolder(long folderId) throws PrincipalException {
        JSONObject result = new JSONObject();

        long userId = getGuestOrUserId();
        result.put(JSONConstants.SUCCESS, false);
        try {
            MessageFolder folderToDelete = MessageFolderLocalServiceUtil.getMessageFolder(folderId);
            if (folderToDelete.getType() != MessagingConstants.PERSONAL_FOLDER_TYPE) {
                return result;
            }

            MessageFolder trashFolder = MessageFolderLocalServiceUtil.getUserTrashFolder(userId);

            recursiveDelete(userId, folderToDelete, trashFolder.getFolderId());

            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error when removing message folder for userId " + userId + " and folderId " + folderId, e);
        }

        return result;
    }


    /**
     * Recursive removal for folder
     */
    private static void recursiveDelete(long userId, MessageFolder folder, long trashFolderId) throws Exception {
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
