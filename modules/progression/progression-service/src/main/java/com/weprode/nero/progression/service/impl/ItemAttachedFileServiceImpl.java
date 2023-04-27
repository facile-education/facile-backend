package com.weprode.nero.progression.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.progression.model.ItemAttachedFile;
import com.weprode.nero.progression.service.ItemAttachedFileLocalServiceUtil;
import com.weprode.nero.progression.service.base.ItemAttachedFileServiceBaseImpl;
import com.weprode.nero.progression.utils.ProgressionUtils;
import org.osgi.service.component.annotations.Component;

import java.io.File;

@Component(
        property = {
                "json.web.service.context.name=progression",
                "json.web.service.context.path=ItemAttachedFile"
        },
        service = AopService.class
)
public class ItemAttachedFileServiceImpl extends ItemAttachedFileServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ItemAttachedFileServiceImpl.class);
    
    @JSONWebService(value = "add-attachment", method = "POST")
    public JSONObject addAttachment(long itemId, String fileName, File file, boolean isToBeCompleted) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

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
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), itemId)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ItemAttachedFile attachedFile = ItemAttachedFileLocalServiceUtil.addAttachedFile(itemId, file, isToBeCompleted, false);

            result.put(JSONConstants.ATTACHED_FILE_ID, attachedFile.getItemAttachedFileId());
        } catch (Exception e) {
            logger.error("Could not add file in itemId="+itemId+" for "+user.getFullName()+" (id="+user.getUserId()+")", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-attachment", method = "DELETE")
    public JSONObject deleteAttachment(long attachmentId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

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
            ItemAttachedFile attachFile = ItemAttachedFileLocalServiceUtil.getItemAttachedFile(attachmentId);
            if (!ProgressionUtils.ownsProgressionItem(user.getUserId(), attachFile.getProgressionItemId())) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            ItemAttachedFileLocalServiceUtil.deleteAttachedFile(attachmentId);
            logger.info("User "+user.getFullName()+" (id="+user.getUserId()+") has deleted item attachedFileId "+attachmentId);

        } catch (Exception e) {
            logger.error("Could not delete attachmentId="+attachmentId+" for "+user.getFullName()+" (id="+user.getUserId()+")", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
