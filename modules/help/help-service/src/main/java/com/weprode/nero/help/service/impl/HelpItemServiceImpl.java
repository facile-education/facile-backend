package com.weprode.nero.help.service.impl;

import com.liferay.portal.aop.AopService;

import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.help.service.HelpItemLocalServiceUtil;
import com.weprode.nero.help.service.base.HelpItemServiceBaseImpl;
import com.weprode.nero.help.utils.HelpUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=help",
                "json.web.service.context.path=HelpItem"
        },
        service = AopService.class
)
public class HelpItemServiceImpl extends HelpItemServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpItemServiceImpl.class);

    @JSONWebService(value = "get-help-item", method = "GET")
    public JSONObject getHelpItemDetails(long itemId) {
        logger.info("Getting item content for id = " + itemId);
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to view item with id = " + itemId, e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONObject jsonHelpItem = HelpUtil.getHelpItemDetails(itemId);

            if (jsonHelpItem != null) {
                result.put(JSONConstants.HELP_ITEM, jsonHelpItem);
                result.put(JSONConstants.SUCCESS, true);
            }
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "save-help-item-position", method = "POST")
    public JSONObject saveHelpItemPosition(long categoryId, String item) {
        logger.info("Moving item with content = " + item);
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ||
                    !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to move item", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONObject jsonHelpItem = new JSONObject(item);
            HelpUtil.saveHelpItemPosition(categoryId, jsonHelpItem);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "save-help-item", method = "POST")
    public JSONObject saveHelpItem (long categoryId, String item) {
        logger.info("Saving item with content = " + item);
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ||
                    !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to update item", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONObject jsonHelpItem = new JSONObject(item);
            JSONObject savedHelpItem = HelpUtil.saveHelpItem(categoryId, jsonHelpItem);

            result.put(JSONConstants.HELP_ITEM, savedHelpItem);
            result.put(JSONConstants.CATEGORY_ID, categoryId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when saving help item", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-item", method = "POST")
    public JSONObject deleteItem(long itemId) {
        logger.info("Deleting item with id = " + itemId);
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ||
                    !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to delete item", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            HelpItemLocalServiceUtil.deleteItem(itemId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when deleting help item", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
