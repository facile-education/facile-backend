package com.weprode.nero.help.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.help.service.HelpLinkLocalServiceUtil;
import com.weprode.nero.help.service.base.HelpLinkServiceBaseImpl;
import com.weprode.nero.help.utils.HelpUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=help",
                "json.web.service.context.path=HelpLink"
        },
        service = AopService.class
)
public class HelpLinkServiceImpl extends HelpLinkServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpLinkServiceImpl.class);

    @JSONWebService(value = "save-link", method = "POST")
    public JSONObject saveLink(String link) {
        logger.info("Saving link with content = " + link);
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONObject savedLink = HelpUtil.saveLink(new JSONObject(link));

            result.put(JSONConstants.LINK, savedLink);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-link", method = "GET")
    public JSONObject deleteLink(long linkId) {
        logger.info("Deleting link with id = " + linkId);
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            HelpLinkLocalServiceUtil.deleteHelpLink(linkId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when deleting help link", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
