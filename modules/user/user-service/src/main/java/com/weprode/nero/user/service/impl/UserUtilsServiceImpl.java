package com.weprode.nero.user.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.DocumentUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.preference.model.UserProperties;
import com.weprode.nero.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.model.UserContact;
import com.weprode.nero.user.service.UserContactLocalServiceUtil;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsService;
import com.weprode.nero.user.service.base.UserUtilsServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=user",
                "json.web.service.context.path=UserUtils"
        },
        service = AopService.class
)
public class UserUtilsServiceImpl extends UserUtilsServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserUtilsServiceImpl.class);

    @JSONWebService(value = "get-user-infos", method = "GET")
    public JSONObject getUserInfos() {
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

        UserProperties userProperties = null;
        String portraitUrl = "";

        try {
            userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
            portraitUrl = UserConstants.getPortraitURL(PortalUtil.getPathImage(), user.isMale(),
                    user.getPortraitId(), user.getUserUuid());
        } catch (Exception e) {
            logger.error("Cannot get user ("+user.getUserId()+") informations", e);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        // Needed infos
        result.put(JSONConstants.USER_ID, user.getUserId());
        result.put(JSONConstants.LAST_NAME, user.getLastName());
        result.put(JSONConstants.FIRST_NAME, user.getFirstName());
        result.put(JSONConstants.PICTURE, portraitUrl);
        result.put(JSONConstants.THEME_COLOR, userProperties.getThemeColor());
        result.put(JSONConstants.HAS_WEBDAV_ENABLED, userProperties.getWebdavActivated());

        // Roles
        result.put(JSONConstants.IS_ADMINISTRATOR, RoleUtilsLocalServiceUtil.isAdministrator(user));
        result.put(JSONConstants.IS_ENT_ADMIN, RoleUtilsLocalServiceUtil.isENTAdmin(user));
        result.put(JSONConstants.IS_LOCAL_ADMIN, RoleUtilsLocalServiceUtil.isSchoolAdmin(user));
        result.put(JSONConstants.IS_STUDENT, RoleUtilsLocalServiceUtil.isStudent(user));
        result.put(JSONConstants.IS_PARENT, RoleUtilsLocalServiceUtil.isParent(user));
        result.put(JSONConstants.IS_TEACHER, RoleUtilsLocalServiceUtil.isTeacher(user));
        result.put(JSONConstants.IS_PERSONAL, RoleUtilsLocalServiceUtil.isPersonal(user));
        result.put(JSONConstants.IS_DOYEN, RoleUtilsLocalServiceUtil.isDoyen(user));
        result.put(JSONConstants.IS_DIRECTION_MEMBER, RoleUtilsLocalServiceUtil.isDirectionMember(user));
        result.put(JSONConstants.IS_SECRETARIAT, RoleUtilsLocalServiceUtil.isSecretariat(user));

        // Children
        List<User> children = UserRelationshipLocalServiceUtil.getChildren(user.getUserId());
        JSONArray userChildrenJson = JSONFactoryUtil.createJSONArray();
        if (children != null) {
            for (User child : children) {
                JSONObject userChildJson = JSONFactoryUtil.createJSONObject();
                userChildJson.put(JSONConstants.FIRST_NAME, child.getFirstName());
                userChildJson.put(JSONConstants.LAST_NAME, child.getLastName());
                userChildJson.put(JSONConstants.USER_ID, child.getUserId());
                userChildrenJson.put(userChildJson);
            }
        }
        result.put(JSONConstants.CHILDREN, userChildrenJson);

        // Schools
        JSONArray userSchoolsJson = JSONFactoryUtil.createJSONArray();
        List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserVisibilitySchools(user);
        if (userSchools != null) {
            for (Organization userSchool : userSchools) {
                JSONObject userSchoolJson = JSONFactoryUtil.createJSONObject();
                userSchoolJson.put(JSONConstants.SCHOOL_ID, userSchool.getOrganizationId());
                userSchoolJson.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userSchool.getName(), true));
                userSchoolJson.put(JSONConstants.IS_PREFERED, (userProperties.getPreferedSchoolId() == userSchool.getOrganizationId()));
                if (RoleUtilsLocalServiceUtil.isSchoolAdmin(user, userSchool.getOrganizationId()) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                    userSchoolJson.put(JSONConstants.IS_ADMIN, true);
                }
                userSchoolsJson.put(userSchoolJson);
            }
        }
        result.put(JSONConstants.USER_SCHOOLS, userSchoolsJson);
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    @JSONWebService(value = "get-personnal-details", method = "GET")
    public JSONObject getPersonnalDetails() {
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

        try {
            UserContact userContact = UserContactLocalServiceUtil.getUserContactByUserId(user.getUserId());

            result.put(JSONConstants.MAIL, userContact.getMail());
            result.put(JSONConstants.MOBILE_PHONE, userContact.getMobilePhone());
            result.put(JSONConstants.SMS_PHONE, userContact.getMobilePhoneSMS());
            result.put(JSONConstants.HOME_PHONE, userContact.getHomePhone());
            result.put(JSONConstants.PRO_PHONE, userContact.getProPhone());
            result.put(JSONConstants.ADDRESS, userContact.getAddress());

            // TODO Preferences
            /* NotifyConfig userNotificationConfig = NotifyConfigLocalServiceUtil.getOrCreateNotifyConfig(user.getUserId());
            //result.put("", userNotificationConfig.setActivate(frequency != NONE);
            result.put(JSONConstants.REPORT_FREQUENCY, userNotificationConfig.getDigestPeriod());*/

            UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
            result.put(JSONConstants.IS_LOCAL_USER, userProperties.isManualAccount());
            result.put(JSONConstants.IS_WEBDAV_ENABLED, userProperties.isWebdavActivated());
            result.put(JSONConstants.WEBDAV_URL, DocumentUtilsLocalServiceUtil.getWebDavUrl(user));

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Cannot get user ("+user.getUserId()+") details", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

}
