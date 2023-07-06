package com.weprode.nero.document.utils;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;

public class ENTWebDAVUtil {

    private ENTWebDAVUtil() {
        throw new IllegalStateException("Utility class");
    }
    private static final Log logger = LogFactoryUtil.getLog(ENTWebDAVUtil.class);

    /**
     * Get user WebDav url
     */
    public static String getWebDavUrl(User user) {
        try {
            long userGroupId = user.getGroup().getGroupId();
            Folder userFolder = FolderUtilsLocalServiceUtil.getUserRootFolder(user.getUserId());

            return getWebDavUrl(userGroupId, userFolder);
        } catch (Exception e) {
            logger.error(e);
        }

        return StringPool.BLANK;
    }

    /**
     * Get file webdav url
     */
    public static String getWebDavUrl(long groupId, FileEntry file) {
        try {
            return getWebDavUrl(GroupLocalServiceUtil.getGroup(groupId), file);
        } catch (Exception e) {
            logger.error(e);
        }

        return StringPool.BLANK;
    }

    public static String getWebDavUrl(Group group, FileEntry file) {
        try {
            Folder curFolder = DLAppServiceUtil.getFolder(file.getFolderId());
            String folderWebdavUrl = getWebDavUrl(group, curFolder);
            return folderWebdavUrl + StringPool.SLASH + encodeURL(file.getTitle());
        } catch (Exception e) {
            logger.error(e);
        }

        return StringPool.BLANK;
    }

    /**
     * Get folder webdav url
     */
    public static String getWebDavUrl(long groupId, Folder folder) {
        try {
            return getWebDavUrl(GroupLocalServiceUtil.getGroup(groupId), folder);
        } catch (Exception e) {
            logger.error(e);
        }

        return StringPool.BLANK;
    }

    public static String getWebDavUrl(Group group, Folder folder) {
        try {
            StringBuilder sb = new StringBuilder();

            if (folder != null) {
                Folder curFolder = folder;
                while (true) {

                    sb.insert(0, encodeURL(curFolder.getName()));
                    sb.insert(0, StringPool.SLASH);

                    if (curFolder.getParentFolderId() == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
                        break;
                    }
                    else {
                        curFolder = DLAppServiceUtil.getFolder(curFolder.getParentFolderId());
                    }
                }
            }

            return PropsUtil.get(NeroSystemProperties.PORTAL_URL)
                    + PortalUtil.getPathContext()
                    + "/api/secure/webdav" + group.getFriendlyURL() + "/document_library" + sb;
        } catch (Exception e) {
            logger.error(e);
        }

        return StringPool.BLANK;
    }

    private static String encodeURL(String url) {
        url = URLCodec.encodeURL(url);
        url = StringUtil.replace(url, StringPool.PLUS, StringPool.SPACE);

        return url;
    }
}
