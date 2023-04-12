package com.weprode.nero.document.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.document.constants.PermissionConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.nero.group.model.CommunityInfos;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.List;

public class DLAppJsonFactory {

    private DLAppJsonFactory() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(DLAppJsonFactory.class);

    public static JSONObject format(long userId, Folder folder) throws PortalException, SystemException {
        int space = DocumentUtil.getSpace(folder, folder.getUserId());
        return format(userId, folder, space);
    }

    public static JSONObject format(long userId, Folder folder, int space) throws PortalException, SystemException {
        return format(userId, folder, space, false);
    }

    public static JSONObject format(long userId, Folder folder, int space, boolean withDetails) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUser(userId);
        return format(user, folder, space, withDetails);
    }

    public static JSONObject format(User user, Folder folder, int space, boolean withDetails) {

        JSONObject formattedFolder = JSONFactoryUtil.createJSONObject();
        addCommonsFields(formattedFolder, folder, user, withDetails);

        switch (space) {
            case DocumentConstants.SENDING_BOX:
                addSendingBoxFields(formattedFolder, folder, user);
                break;
            case DocumentConstants.TRASH:
                addTrashFields(formattedFolder, folder, user);
                break;
            case DocumentConstants.COLLABORATIVE:
                addGroupFields(formattedFolder, folder, user);
                break;
            default:
                break;
        }

        return formattedFolder;
    }

    public static JSONObject format(long userId, FileEntry fileEntry) throws PortalException, SystemException {
        int space = DocumentUtil.getSpace(fileEntry, userId);
        return format(userId, fileEntry, space);
    }

    public static JSONObject format(long userId, FileEntry fileEntry, int space) throws PortalException, SystemException {
        return format(userId, fileEntry, space, false);
    }

    public static JSONObject format(long userId, FileEntry fileEntry, int space, boolean withDetails) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUser(userId);
        return format(user, fileEntry, space,withDetails);
    }

    /**
     * Formats a given FileEntry into JSONObject
     */
    public static JSONObject format(User user, FileEntry fileEntry, int space, boolean withDetails) {
        JSONObject formattedFile = JSONFactoryUtil.createJSONObject();

        addCommonsFields(formattedFile, fileEntry, user, withDetails);

        switch (space) {
            case DocumentConstants.SENDING_BOX:
                addSendingBoxFields(formattedFile, fileEntry, user);
                break;
            case DocumentConstants.TRASH:
                addTrashFields(formattedFile, fileEntry, user);
                break;
            case DocumentConstants.COLLABORATIVE:
                addGroupFields(formattedFile, fileEntry, user);
                break;
            default:
                break;
        }

        return formattedFile;
    }

    /**
     * Add commons' folders fields to JSONObject, wherever it is (not depending on folder's space)
     */
    private static void addCommonsFields(JSONObject formattedFolder, Folder folder, User user, boolean withDetails) {
        formattedFolder.put(JSONConstants.ID, String.valueOf(folder.getFolderId()));
        formattedFolder.put(JSONConstants.NAME, folder.getName());
        formattedFolder.put(JSONConstants.TYPE, "Folder");
        formattedFolder.put(JSONConstants.LAST_MODIFIED_DATE, new SimpleDateFormat(DocumentConstants.DATE_FORMAT)
                .format(folder.getModifiedDate()));
        
        try {
            if (UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId()).getWebdavActivated()) {
                formattedFolder.put(JSONConstants.URL_WEBDAV, ENTWebDAVUtil.getWebDavUrl(folder.getGroupId(), folder));
            }
        } catch (Exception e) {
            logger.error(e);
        }

        // Permissions
        // Directors and school admins have all rights on institutional groups
        boolean hasFullPermissions = false;
        try {
            Group group = GroupLocalServiceUtil.getGroup(folder.getGroupId());
            hasFullPermissions = group.isOrganization() && RoleUtilsLocalServiceUtil.isSchoolAdmin(user);
        } catch (Exception e) {
            logger.debug(e);
        }
        
        final JSONObject permissions = JSONFactoryUtil.createJSONObject();
        permissions.put(PermissionConstants.ADD_OBJECT, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, PermissionConstants.ADD_OBJECT));
        permissions.put(ActionKeys.DELETE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.DELETE));
        permissions.put(ActionKeys.PERMISSIONS, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.PERMISSIONS));
        formattedFolder.put(JSONConstants.PERMISSIONS, permissions);

        if (withDetails) {
            try {
                formattedFolder.put(JSONConstants.SIZE, FolderUtilsLocalServiceUtil.getFolderSize(folder));
            } catch (Exception e) {
                logger.error(e.getMessage());
                formattedFolder.put(JSONConstants.SIZE, "error when computing size");
            }
            formattedFolder.put(JSONConstants.CREATION_DATE, new SimpleDateFormat(DocumentConstants.DATE_FORMAT).format(folder.getCreateDate()));
            // formattedFolder.put("starred", FavoriteLocalServiceUtil.isInUserFavorites(user, folder.getFolderId())); // TODO: Handle favorite
        }
    }

    /**
     * Add commons' files fields to JSONObject, wherever it is (not depending on file's space)
     */
    private static void addCommonsFields(JSONObject formattedFile, FileEntry fileEntry, User user, boolean withDetails) {
        formattedFile.put(JSONConstants.ID, String.valueOf(fileEntry.getFileEntryId()));
        formattedFile.put(JSONConstants.NAME, fileEntry.getTitle());
        formattedFile.put(JSONConstants.TYPE, "File");
        formattedFile.put(JSONConstants.EXTENSION, fileEntry.getExtension().toLowerCase());
        formattedFile.put(JSONConstants.LAST_MODIFIED_DATE,
                new SimpleDateFormat(DocumentConstants.DATE_FORMAT).format(fileEntry.getModifiedDate()));

        // Permissions
        final JSONObject permissions = JSONFactoryUtil.createJSONObject();
        permissions.put(ActionKeys.UPDATE, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE));
        permissions.put(ActionKeys.DELETE, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.DELETE));
        permissions.put(ActionKeys.PERMISSIONS, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS));
        formattedFile.put(JSONConstants.PERMISSIONS, permissions);

        if (withDetails) {
            formattedFile.put(JSONConstants.SIZE, (int) fileEntry.getSize());
            formattedFile.put(JSONConstants.CREATION_DATE,
                    new SimpleDateFormat(DocumentConstants.DATE_FORMAT).format(fileEntry.getCreateDate()));
            formattedFile.put(JSONConstants.CREATOR, fileEntry.getUserName());
            // formattedFile.put("starred", FavoriteLocalServiceUtil.isInUserFavorites(user, fileEntry.getFileEntryId()));   // TODO: Handle favorite
            formattedFile.put(JSONConstants.VERSION, fileEntry.getVersion());
            formattedFile.put(JSONConstants.URL, FileUtilsLocalServiceUtil.getDownloadUrl(fileEntry));
            try {
                if (UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId()).getWebdavActivated()) {
                    formattedFile.put(JSONConstants.URL_WEBDAV, ENTWebDAVUtil.getWebDavUrl(fileEntry.getGroupId(), fileEntry));
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    private static void addSendingBoxFields(JSONObject formattedFolder, Folder folder, User user) {
        try {
            if (DocumentUtil.belongToASendingBoxSubFolder(folder, user.getUserId())) {
                formattedFolder.put(JSONConstants.IS_SUB_ENTITY, true);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void addSendingBoxFields(JSONObject formattedFile, FileEntry fileEntry, User user) {
        try {
            if (DocumentUtil.belongToASendingBoxSubFolder(fileEntry, user.getUserId())) {
                formattedFile.put(JSONConstants.IS_SUB_ENTITY, true);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void addTrashFields(JSONObject formattedFolder, Folder folder, User user) {
//        try {
//            if (!NewDocumentUtil.belongToATrashSubFolder(folder, user.getUserId())) {    // no trash entry for trash subFolders
//                TrashEntry trashEntry = TrashEntryLocalServiceUtil.findByEntryId(folder.getFolderId());
//                formattedFolder.put(JSONConstants.NAME, trashEntry.getSourceName()); // Change name with the original name
//                formattedFolder.put("sourcePath", trashEntry.getSourcePath());
//                formattedFolder.put("deleteDate", new SimpleDateFormat(DocumentConstants.DATE_FORMAT).format(trashEntry.getDeleteDate()));
//            } else {
//                formattedFolder.put(JSONConstants.IS_SUB_ENTITY, true);
//            }
//        } catch (Exception e) {
//            logger.error(e);
//        }
    }

    private static void addGroupFields(JSONObject formattedFolder, Folder folder, User user) {
        try {
            Group folderGroup = GroupLocalServiceUtil.getGroup(folder.getGroupId());
            formattedFolder.put(JSONConstants.GROUP_ID, folderGroup.getGroupId());
            // TODO: make the groupRootFolder directly have the good name
            if (folder.getParentFolderId() == 0) { // The group root folder have a specific name that is the name of the group and not the folder.name field.
                if (folderGroup.isOrganization()) {	// Org groups
                    Organization org = OrganizationLocalServiceUtil.getOrganization(folderGroup.getOrganizationId());
                    boolean withSchoolName = OrgDetailsLocalServiceUtil.hasType(folderGroup.getOrganizationId(), OrgConstants.SCHOOL_TYPE) || OrgDetailsLocalServiceUtil.hasType(folderGroup.getOrganizationId(), OrgConstants.SCHOOL_LEVEL_TYPE);
                    formattedFolder.put(JSONConstants.NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), withSchoolName));
                    formattedFolder.put(JSONConstants.NB_MEMBERS, UserOrgsLocalServiceUtil.countOrgMembers(org.getOrganizationId()));
                    formattedFolder.put(JSONConstants.COLOR, OrgUtilsLocalServiceUtil.getOrgColor(user, org));
                } else {	// Personal groups
                    formattedFolder.put(JSONConstants.NAME, folderGroup.getName(user.getLocale()));
                    formattedFolder.put(JSONConstants.NB_MEMBERS, UserLocalServiceUtil.getGroupUsersCount(folderGroup.getGroupId(), WorkflowConstants.STATUS_APPROVED));
                    CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(folderGroup.getGroupId());
                    String color = communityInfos.getColor() != null && !communityInfos.getColor().isEmpty() ? communityInfos.getColor() : "#4353B3"; // (CDTColorUtil.getNewColor(2));
                    formattedFolder.put(JSONConstants.COLOR, color);
                }
                formattedFolder.put(JSONConstants.IS_GROUP_ROOT_FOLDER, true); // those folder is considered as Group on front side (group root folder)
                final JSONObject permissions = JSONFactoryUtil.createJSONObject();

                // Directors and school admins have all rights on institutional groups
                boolean hasFullPermissions = false;
                try {
                    Group group = GroupLocalServiceUtil.getGroup(folder.getGroupId());
                    hasFullPermissions = group.isOrganization() && RoleUtilsLocalServiceUtil.isSchoolAdmin(user);
                } catch (Exception e) {
                    logger.debug(e);
                }

                permissions.put(PermissionConstants.ADD_OBJECT, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, PermissionConstants.ADD_OBJECT));
                permissions.put(ActionKeys.UPDATE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.UPDATE));
                permissions.put(ActionKeys.DELETE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.DELETE));
                permissions.put(ActionKeys.PERMISSIONS, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.PERMISSIONS));
                formattedFolder.put(JSONConstants.PERMISSIONS, permissions);
            }

        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void addTrashFields(JSONObject formattedFile, FileEntry fileEntry, User user) {
//        try {
//            if (!NewDocumentUtil.belongToATrashSubFolder(fileEntry, user.getUserId())) {    // no trash entry for trash subFolders
//                TrashEntry trashEntry = TrashEntryLocalServiceUtil.findByEntryId(fileEntry.getFileEntryId());
//                formattedFile.put(JSONConstants.NAME, trashEntry.getSourceName()); // Change name with the original name
//                formattedFile.put("sourcePath", trashEntry.getSourcePath());
//                formattedFile.put("deleteDate", new SimpleDateFormat(DocumentConstants.DATE_FORMAT).format(trashEntry.getDeleteDate()));
//            }
//        } catch (Exception e) {
//            logger.error(e);
//        }
    }

    private static void addGroupFields(JSONObject formattedFile, FileEntry fileEntry, User user) {
        formattedFile.put(JSONConstants.IS_GROUP_FILE, true);
        final JSONObject permissions = JSONFactoryUtil.createJSONObject();
        // Directors and school admins have all rights on institutional groups
        boolean hasFullPermissions = false;
        try {
            Group group = GroupLocalServiceUtil.getGroup(fileEntry.getGroupId());
            hasFullPermissions = group.isOrganization() && RoleUtilsLocalServiceUtil.isSchoolAdmin(user);
        } catch (Exception e) {
            logger.debug(e);
        }
        
        permissions.put(ActionKeys.UPDATE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE));
        permissions.put(ActionKeys.DELETE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.DELETE));
        permissions.put(ActionKeys.PERMISSIONS, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS));
        formattedFile.put(JSONConstants.PERMISSIONS, permissions);
    }
    
    public static JSONArray formatUsers(List<Long> userIds) {
        final JSONArray jsonUsers = JSONFactoryUtil.createJSONArray();

        for (long userId : userIds) {
            jsonUsers.put(formatUser(userId));
        }

        return jsonUsers;
    }

    public static JSONObject formatUser(long userId) {
        try {
            User user = UserLocalServiceUtil.getUser(userId);

            // Retrieve user picture url
            String urlImg = UserConstants.getPortraitURL(PortalUtil.getPathImage(), user.isMale(),
                    user.getPortraitId(), user.getUserUuid());

            // Put fields
            JSONObject curr = JSONFactoryUtil.createJSONObject();
            curr.put(JSONConstants.ID, userId);
            curr.put(JSONConstants.NAME, user.getFullName());
            curr.put(JSONConstants.PICTURE, urlImg);
            // TODO other fields? (address, mail, etc...)

            return curr;
        } catch (Exception e) {
            logger.error("Cant retrieve user with userId " + userId, e);
            return JSONFactoryUtil.createJSONObject();
        }
    }

    public static JSONObject quickFormatUser(long userId) {
        try {
            User user = UserLocalServiceUtil.getUser(userId);

            JSONObject curr = JSONFactoryUtil.createJSONObject();
            curr.put(JSONConstants.ID, userId);
            curr.put(JSONConstants.NAME, user.getFullName());

            return curr;
        } catch (Exception e) {
            logger.error("Cant retrieve user with userId " + userId, e);
            return JSONFactoryUtil.createJSONObject();
        }
    }

    public static JSONObject formatError(String type) throws SystemException {
        return formatError(type, StringPool.BLANK);
    }

    public static JSONObject formatError(String type, String message) throws SystemException {
        JSONObject error = JSONFactoryUtil.createJSONObject();

        error.put(JSONConstants.TYPE, type);
        error.put(JSONConstants.MESSAGE, message);

        return error;
    }
    
}
