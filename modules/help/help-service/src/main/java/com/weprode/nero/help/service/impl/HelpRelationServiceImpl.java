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
import com.weprode.nero.help.service.HelpRelationLocalServiceUtil;
import com.weprode.nero.help.service.base.HelpRelationServiceBaseImpl;
import com.weprode.nero.help.utils.HelpUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=help",
                "json.web.service.context.path=HelpRelation"
        },
        service = AopService.class
)
public class HelpRelationServiceImpl extends HelpRelationServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpRelationServiceImpl.class);

    @JSONWebService(value = "save-relation", method = "POST")
    public JSONObject saveRelation(String relation) {
        logger.info("Saving relation with content = " + relation);
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ||
                    !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to update relation", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONObject savedRelation = HelpUtil.saveRelation(new JSONObject(relation));

            result.put(JSONConstants.RELATION, savedRelation);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "delete-relation", method = "GET")
    public JSONObject deleteRelation(long relationId) {
        logger.info("Deleting relation with id = " + relationId);
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ||
                    !RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                throw new AuthException();
            }
        } catch (Exception e) {
            logger.error("User is not allowed to update link", e);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            HelpRelationLocalServiceUtil.deleteHelpRelation(relationId);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when deleting help relation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
