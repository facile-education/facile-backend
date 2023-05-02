package com.weprode.nero.help.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONArray;
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
import com.weprode.nero.help.service.HelpCategoryLocalServiceUtil;
import com.weprode.nero.help.service.base.HelpCategoryServiceBaseImpl;
import com.weprode.nero.help.utils.HelpUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=help",
                "json.web.service.context.path=HelpCategory"
        },
        service = AopService.class
)
public class HelpCategoryServiceImpl extends HelpCategoryServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpCategoryServiceImpl.class);

    @JSONWebService(value = "get-help-menu", method = "GET")
    public JSONObject getHelpMenu(String search) {
        logger.info("Getting help menu with search = " + search);
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to fetch help menu", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONArray helpTree = HelpUtil.getHelpTree(user, search);
            result.put(JSONConstants.HELP_TREE, helpTree);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "save-category", method = "POST")
    public JSONObject saveHelpCategory(String categoryName, long serviceId) {
        logger.info("Updating category with name = " + categoryName);
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ||
                    !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to update category", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONObject jsonCategory = HelpUtil.saveHelpCategory(categoryName, serviceId);

            result.put(JSONConstants.CATEGORY, jsonCategory);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when updating help category", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-category", method = "GET")
    public JSONObject deleteCategory(long categoryId) {
        logger.info("Deleting category with id = " + categoryId);
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ||
                    !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to delete category", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            HelpCategoryLocalServiceUtil.removeCategory(categoryId);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when deleting help category", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
