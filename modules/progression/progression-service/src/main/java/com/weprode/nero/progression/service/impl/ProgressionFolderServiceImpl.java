package com.weprode.nero.progression.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.progression.model.ProgressionFolder;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.service.ProgressionFolderLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.progression.service.base.ProgressionFolderServiceBaseImpl;
import com.weprode.nero.progression.utils.ProgressionUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=progression",
                "json.web.service.context.path=ProgressionFolder"
        },
        service = AopService.class
)
public class ProgressionFolderServiceImpl extends ProgressionFolderServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ProgressionFolderServiceImpl.class);

    @JSONWebService(value = "get-folder-content", method = "GET")
    public JSONObject getFolderContent(long folderId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsFolder(user.getUserId(), folderId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            List<ProgressionFolder> subFolders = ProgressionFolderLocalServiceUtil.getSubFolders(folderId);
            JSONArray folderArray = new JSONArray();
            for (ProgressionFolder folder : subFolders) {
                folderArray.put(folder.convertToJSON());
            }
            result.put(JSONConstants.FOLDERS, folderArray);

            List<ProgressionItem> items = ProgressionItemLocalServiceUtil.getFolderItems(folderId);
            JSONArray itemArray = new JSONArray();
            for (ProgressionItem item: items) {
                itemArray.put(item.convertToJSON(user.getUserId(), true));
            }
            result.put(JSONConstants.ITEMS, itemArray);

        } catch (SystemException e) {
            logger.error("Could not get folder (id="+folderId+") content for "+user.getFullName()+" (id="+user.getUserId()+")", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "add-folder", method = "POST")
    public JSONObject addFolder(long progressionId, long parentFolderId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsProgression(user.getUserId(), progressionId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ProgressionFolder folder = ProgressionFolderLocalServiceUtil.addFolder(progressionId, parentFolderId);
            result.put(JSONConstants.FOLDER, folder.convertToJSON());

        } catch (SystemException e) {
            logger.error("Could not add folder in parentFolderId = " + parentFolderId + " for "+user.getFullName()+" (id="+user.getUserId()+")", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "update-folder", method = "POST")
    public JSONObject updateFolder(long folderId, long parentFolderId, String name, int order) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsFolder(user.getUserId(), folderId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ProgressionFolderLocalServiceUtil.updateFolder(folderId, parentFolderId, name, order);
        } catch (SystemException e) {
            logger.error("Could not update folderId="+folderId+" for "+user.getFullName()+" (id="+user.getUserId()+")", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-folder", method = "DELETE")
    public JSONObject deleteFolder(long folderId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        result.put(JSONConstants.SUCCESS, true);
        try {
            // Check ownership
            if (!ProgressionUtils.ownsFolder(user.getUserId(), folderId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ProgressionFolderLocalServiceUtil.deleteFolder(user.getUserId(), folderId);
            logger.info("User "+user.getFullName()+" (id="+user.getUserId()+") has deleted progression folderId "+folderId);

        } catch (Exception e) {
            logger.error("Could not delete folderId "+folderId+" for "+user.getFullName()+" (id="+user.getUserId()+")");
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
