package com.weprode.nero.user.service.impl;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.NewsAdminLocalServiceUtil;
import com.weprode.nero.user.service.NewsAdminService;
import com.weprode.nero.user.service.base.NewsAdminServiceBaseImpl;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "json.web.service.context.name=user",
                "json.web.service.context.path=NewsAdmin"
        },
        service = NewsAdminService.class
)
public class NewsAdminServiceImpl extends NewsAdminServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(NewsAdminServiceImpl.class);

    @JSONWebService(value = "add-news-delegate", method = "GET")
    public JSONObject addNewsDelegate(long userId, long schoolId) {
        JSONObject result = new JSONObject();

        try {
            User user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            logger.info("User " + user.getUserId() + " adds user " + userId + " as news delegate for school " + schoolId);

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isSchoolAdmin(user)
                    || RoleUtilsLocalServiceUtil.isAdministrator(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user))) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            result.put(JSONConstants.SUCCESS, NewsAdminLocalServiceUtil.addSchoolDelegate(userId, schoolId));
        } catch (Exception e) {
            logger.error("Error while saving school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "remove-news-delegate", method = "GET")
    public JSONObject removeNewsDelegate(long userId, long schoolId) {

        JSONObject result = new JSONObject();

        try {
            User user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            logger.info("User " + user.getUserId() + " removes user " + userId + " as news delegate for school " + schoolId);

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isSchoolAdmin(user)
                    || RoleUtilsLocalServiceUtil.isAdministrator(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user))) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            result.put(JSONConstants.SUCCESS, NewsAdminLocalServiceUtil.removeSchoolDelegate(userId, schoolId));
        } catch (Exception e) {
            logger.error("Error while saving school admins for schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
