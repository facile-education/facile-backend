/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.user.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.about.service.UserReadVersionNoteLocalServiceUtil;
import com.weprode.facile.commons.FacileLogger;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.DocumentUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgMappingLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.preference.model.NotifyConfig;
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.NotifyConfigLocalServiceUtil;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.statistic.service.MatomoLocalServiceUtil;
import com.weprode.facile.user.model.UserContact;
import com.weprode.facile.user.service.UserContactLocalServiceUtil;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import com.weprode.facile.user.service.base.UserUtilsServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
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
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        UserProperties userProperties;
        String portraitUrl = "";

        try {
            userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
            portraitUrl = UserUtilsLocalServiceUtil.getUserPicture(user);
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
        result.put(JSONConstants.HAS_READ_LAST_VERSION_NOTE, UserReadVersionNoteLocalServiceUtil.hasReadLastVersionNote(user.getUserId()));

        // Roles
        result.put(JSONConstants.IS_ADMINISTRATOR, RoleUtilsLocalServiceUtil.isAdministrator(user));
        result.put(JSONConstants.IS_ENT_ADMIN, RoleUtilsLocalServiceUtil.isCollectivityAdmin(user));
        result.put(JSONConstants.IS_LOCAL_ADMIN, RoleUtilsLocalServiceUtil.isSchoolAdmin(user));
        result.put(JSONConstants.IS_STUDENT, RoleUtilsLocalServiceUtil.isStudent(user));
        result.put(JSONConstants.IS_PARENT, RoleUtilsLocalServiceUtil.isParent(user));
        result.put(JSONConstants.IS_TEACHER, RoleUtilsLocalServiceUtil.isTeacher(user));
        result.put(JSONConstants.IS_PERSONAL, RoleUtilsLocalServiceUtil.isPersonal(user));
        result.put(JSONConstants.IS_DOYEN, RoleUtilsLocalServiceUtil.isDoyen(user));
        result.put(JSONConstants.IS_DIRECTION_MEMBER, RoleUtilsLocalServiceUtil.isDirectionMember(user));
        result.put(JSONConstants.IS_SECRETARIAT, RoleUtilsLocalServiceUtil.isSecretariat(user));
        result.put(JSONConstants.PROFILE_ID, MatomoLocalServiceUtil.getUserProfileId(user));

        // Children
        List<User> children = UserRelationshipLocalServiceUtil.getChildren(user.getUserId());
        JSONArray userChildrenJson = new JSONArray();
        if (children != null) {
            for (User child : children) {
                JSONObject userChildJson = new JSONObject();
                userChildJson.put(JSONConstants.FIRST_NAME, child.getFirstName());
                userChildJson.put(JSONConstants.LAST_NAME, child.getLastName());
                userChildJson.put(JSONConstants.USER_ID, child.getUserId());
                userChildrenJson.put(userChildJson);
            }
        }
        result.put(JSONConstants.CHILDREN, userChildrenJson);

        // Schools
        JSONArray userSchoolsJson = new JSONArray();
        List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserVisibilitySchools(user);
        if (userSchools != null) {
            for (Organization userSchool : userSchools) {
                JSONObject userSchoolJson = new JSONObject();
                userSchoolJson.put(JSONConstants.SCHOOL_ID, userSchool.getOrganizationId());
                userSchoolJson.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userSchool.getName(), true));
                if (RoleUtilsLocalServiceUtil.isSchoolAdmin(user, userSchool.getOrganizationId()) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                    userSchoolJson.put(JSONConstants.IS_ADMIN, true);
                }
                userSchoolsJson.put(userSchoolJson);
            }
        }
        result.put(JSONConstants.USER_SCHOOLS, userSchoolsJson);

        // Terms of use
        result.put(JSONConstants.AGREED_TERMS_OF_USE, user.getAgreedToTermsOfUse());

        // Password change
        result.put(JSONConstants.PASSWORD_CHANGE, user.getPasswordReset());
        if (user.getPasswordReset()) {
            // Get a ticket if any, needed for security
            List<Ticket> tickets = TicketLocalServiceUtil.getTickets(User.class.getName(), user.getUserId(), TicketConstants.TYPE_PASSWORD);
            if (tickets != null && !tickets.isEmpty()) {
                result.put(JSONConstants.TICKET_KEY, tickets.get(0).getKey());
            }
        }

        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    @JSONWebService(value = "get-personnal-details", method = "GET")
    public JSONObject getPersonnalDetails() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            UserContact userContact = UserContactLocalServiceUtil.getUserContactByUserId(user.getUserId());

            result.put(JSONConstants.MAIL, userContact.getMail());
            result.put(JSONConstants.MOBILE_PHONE, userContact.getMobilePhone());
            result.put(JSONConstants.SMS_PHONE, userContact.getMobilePhoneSMS());
            result.put(JSONConstants.HOME_PHONE, userContact.getHomePhone());
            result.put(JSONConstants.PRO_PHONE, userContact.getProPhone());
            result.put(JSONConstants.ADDRESS, userContact.getAddress());

            NotifyConfig userNotificationConfig = NotifyConfigLocalServiceUtil.getOrCreateNotifyConfig(user.getUserId());
            //result.put("", userNotificationConfig.setActivate(frequency != NONE);
            result.put(JSONConstants.REPORT_FREQUENCY, userNotificationConfig.getDigestPeriod());

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

    @JSONWebService(value = "accept-terms-of-use", method = "GET")
    public JSONObject acceptTermsOfUse() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            user.setAgreedToTermsOfUse(true);
            UserLocalServiceUtil.updateUser(user);

            UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
            userProperties.setTermsOfUseAgreedDate(new Date());
            UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error accepting terms of use for user " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    @JSONWebService(value = "get-parent-infos", method = "GET")
    public JSONObject getParentInfos(long parentUserId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) &&
                !RoleUtilsLocalServiceUtil.isSchoolAdmin(user) &&
                !RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets parent infos");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            StringBuilder strBuilder = new StringBuilder();
            List<User> children = UserRelationshipLocalServiceUtil.getChildren(parentUserId);
            if (children != null) {
                strBuilder.append("Responsable légal de ");
                for (User child : children) {
                    strBuilder.append(child.getFullName()).append(" (").append(UserOrgsLocalServiceUtil.getStudentClassName(child)).append("), ");
                }
            }

            result.put(JSONConstants.INFOS, strBuilder.toString());
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error fetching children for user " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    @JSONWebService(value = "get-cas-attributes", method = "GET")
    public JSONObject getCasAttributes() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
            FacileLogger.registerUser(user);
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            result.put(JSONConstants.SCREEN_NAME, user.getScreenName());
            result.put(JSONConstants.USER_ID, user.getUserId());
            result.put(JSONConstants.LAST_NAME, user.getLastName());
            result.put(JSONConstants.FIRST_NAME, user.getFirstName());
            result.put(JSONConstants.EMAIL, user.getEmailAddress());
            result.put(JSONConstants.ROLE_ID, RoleUtilsLocalServiceUtil.displayUserRoles(user));

            Organization school = UserOrgsLocalServiceUtil.getEtabRatachement(user);
            String uai = OrgMappingLocalServiceUtil.getOrganizationStrutUAI(school);
            result.put(JSONConstants.SCHOOL_UAI, uai);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error fetching cas attributes for user " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }


}
