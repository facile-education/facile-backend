package com.weprode.nero.user.service.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.model.OrgDetails;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.model.Affectation;
import com.weprode.nero.user.service.AffectationLocalServiceUtil;
import com.weprode.nero.user.service.base.AffectationServiceBaseImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AffectationServiceImpl extends AffectationServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(AffectationServiceImpl.class);

    @JSONWebService(value = "get-affected-users", method = "GET")
    public JSONObject getAffectedUsers(long schoolId, String filter) {

        JSONObject result = JSONFactoryUtil.createJSONObject();
        User adminUser;
        try {
            adminUser = getGuestOrUser();
            if (adminUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
            logger.info("Admin " + adminUser.getUserId() + " fetches all affected users for school " + schoolId + " and filter " + filter);

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(adminUser) || RoleUtilsLocalServiceUtil.isSchoolAdmin(adminUser)
                    || RoleUtilsLocalServiceUtil.isAdministrator(adminUser) || RoleUtilsLocalServiceUtil.isENTAdmin(adminUser))) {
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
            JSONArray jsonUsers = JSONFactoryUtil.createJSONArray();
            List<User> users = AffectationLocalServiceUtil.getAffectedUsers(schoolId, filter);
            for (User user : users) {
                JSONObject jsonUser = JSONFactoryUtil.createJSONObject();
                jsonUser.put(JSONConstants.USER_ID, user.getUserId());
                jsonUser.put(JSONConstants.LAST_NAME, user.getLastName());
                jsonUser.put(JSONConstants.FIRST_NAME, user.getFirstName());
                jsonUser.put(JSONConstants.ROLES, RoleUtilsLocalServiceUtil.displayUserRoles(user));

                // Get affectations
                List<Affectation> userAffectations = AffectationLocalServiceUtil.getUserAffectations(user.getUserId(), schoolId);
                JSONArray jsonAffectations = JSONFactoryUtil.createJSONArray();
                for (Affectation userAffectation : userAffectations) {
                    try {
                        Organization org = OrganizationLocalServiceUtil.getOrganization(userAffectation.getOrgId());
                        OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(userAffectation.getOrgId());

                        // Authorized types are : school, class and cours
                        if (orgDetails.getType() == OrgConstants.SCHOOL_TYPE || orgDetails.getType() == OrgConstants.CLASS_TYPE
                                || orgDetails.getType() == OrgConstants.COURS_TYPE) {

                            JSONObject jsonAffectation = JSONFactoryUtil.createJSONObject();
                            jsonAffectation.put(JSONConstants.ORG_ID, userAffectation.getOrgId());
                            jsonAffectation.put(JSONConstants.ORG_NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false));
                            jsonAffectation.put(JSONConstants.TYPE, orgDetails.getType());
                            jsonAffectation.put(JSONConstants.ADMIN_NAME, UserLocalServiceUtil.getUser(userAffectation.getAdminUserId()).getFullName());
                            jsonAffectation.put(JSONConstants.AFFECTATION_DATE,
                                    new SimpleDateFormat("yyyy-MM-dd").format(userAffectation.getAffectationDate()));
                            jsonAffectations.put(jsonAffectation);
                        }
                    } catch (Exception e) {
                        logger.error("Error processing affectation for user " + user.getUserId(), e);
                    }
                }
                jsonUser.put(JSONConstants.AFFECTATIONS, jsonAffectations);

                jsonUsers.put(jsonUser);
            }

            result.put(JSONConstants.USERS, jsonUsers);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "add-user-affectation", method = "GET")
    public JSONObject addUserAffectation(long userId, long orgId, String expirationDate) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User adminUser;
        try {
            adminUser = getGuestOrUser();
            if (adminUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
            logger.info("Admin " + adminUser.getUserId() + " adds affectation for user " + userId + " to orgId " + orgId);

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(adminUser) || RoleUtilsLocalServiceUtil.isSchoolAdmin(adminUser)
                    || RoleUtilsLocalServiceUtil.isAdministrator(adminUser) || RoleUtilsLocalServiceUtil.isENTAdmin(adminUser))) {
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
            Date expireDate = null;
            if (!expirationDate.equals("")) {
                expireDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate);
            }
            boolean success = AffectationLocalServiceUtil.addUserAffectation(userId, orgId, adminUser.getUserId(), expireDate);
            result.put(JSONConstants.SUCCESS, success);

        } catch (Exception e) {
            logger.error("Error adding affectation for user " + userId + " to orgId " + orgId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }


    @JSONWebService(value = "remove-user-affectation", method = "GET")
    public JSONObject removeUserAffectation(long userId, long orgId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User adminUser;
        try {
            adminUser = getGuestOrUser();
            if (adminUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                throw new AuthException();
            }
            logger.info("Admin " + adminUser.getUserId() + " removes affectation for user " + userId + " to orgId " + orgId);

            if (!(RoleUtilsLocalServiceUtil.isDirectionMember(adminUser) || RoleUtilsLocalServiceUtil.isSchoolAdmin(adminUser)
                    || RoleUtilsLocalServiceUtil.isAdministrator(adminUser) || RoleUtilsLocalServiceUtil.isENTAdmin(adminUser))) {
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
            boolean success = AffectationLocalServiceUtil.removeUserAffectation(userId, orgId);
            result.put(JSONConstants.SUCCESS, success);
        } catch (Exception e) {
            logger.error("Error while fetching schools for manual user creation", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
    
}
