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

package com.weprode.facile.application.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.application.model.BroadcastRule;
import com.weprode.facile.application.service.BroadcastRuleLocalServiceUtil;
import com.weprode.facile.application.service.base.BroadcastRuleServiceBaseImpl;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(
        property = {
                "json.web.service.context.name=application",
                "json.web.service.context.path=BroadcastRule"
        },
        service = AopService.class
)
public class BroadcastRuleServiceImpl extends BroadcastRuleServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(BroadcastRuleServiceImpl.class);

    @JSONWebService(value = "get-application-rules", method = "GET")
    public JSONObject getApplicationRules(long applicationId, long schoolId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)
                && !RoleUtilsLocalServiceUtil.isDirectionMember(user)
                && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            logger.info("User " + user.getUserId() + " fetches broadcast rules for applicationId " + applicationId + ", schoolId " + schoolId);
            JSONArray jsonRules = new JSONArray();

            List<BroadcastRule> rules = BroadcastRuleLocalServiceUtil.getSchoolRules(applicationId, schoolId);
            Map<Long, List<Long>> map = new HashMap<>();
            // Key is the orgId, value is a list of roleIds
            for (BroadcastRule rule : rules) {
                if (!map.containsKey(rule.getOrgId())) {
                    map.put(rule.getOrgId(), new ArrayList<>());
                }
                map.get(rule.getOrgId()).add(rule.getRoleId());
            }

            // Parse the map
            for (Map.Entry<Long, List<Long>> entry : map.entrySet()) {
                JSONObject jsonRule = new JSONObject();

                // Add roles
                JSONArray jsonRoles = new JSONArray();
                for (Long roleId : entry.getValue()) {
                    JSONObject jsonRole = new JSONObject();
                    jsonRole.put(JSONConstants.ROLE_ID, roleId);
                    if (roleId == 0) {
                        jsonRole.put(JSONConstants.DISPLAY_TEXT, "Tous les profils");
                    } else {
                        Role role = RoleLocalServiceUtil.getRole(roleId);
                        jsonRole.put(JSONConstants.DISPLAY_TEXT, role.getTitle(user.getLocale()));
                    }
                    jsonRoles.put(jsonRole);
                }
                jsonRule.put(JSONConstants.ROLES, jsonRoles);

                // Add orgs
                JSONArray jsonOrgs = new JSONArray();
                JSONObject jsonOrg = new JSONObject();
                jsonOrg.put(JSONConstants.ORG_ID, entry.getKey());
                if (entry.getKey() == 0) {
                    jsonOrg.put(JSONConstants.ORG_NAME, "Tout l'\u00e9tablissement");
                } else {
                    Organization org = OrganizationLocalServiceUtil.getOrganization(entry.getKey());
                    jsonOrg.put(JSONConstants.ORG_NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false));
                }
                jsonOrgs.put(jsonOrg);
                jsonRule.put(JSONConstants.ORGS, jsonOrgs);
                jsonRules.put(jsonRule);
            }

            result.put(JSONConstants.SUCCESS, true);
            result.put(JSONConstants.RULES, jsonRules);

        } catch (Exception e) {
            logger.error("Error fetching broadcast rules for application " + applicationId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }

    @JSONWebService(value = "update-broadcast-rules", method = "POST")
    public JSONObject updateBroadcastRules(long applicationId, long schoolId, String rules) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user)
                && !RoleUtilsLocalServiceUtil.isDirectionMember(user)
                && !RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            logger.info("User " + user.getUserId() + " updates broadcast rules for applicationId " + applicationId + ", schoolId " + schoolId + " : rules = " + rules);
            JSONArray jsonRules = new JSONArray(rules);

            // First delete all application's rules
            BroadcastRuleLocalServiceUtil.deleteSchoolRules(applicationId, schoolId);

            // Then recreate them all
            for (int i = 0 ; i < jsonRules.length() ; i++) {
                JSONObject jsonRule = jsonRules.getJSONObject(i);
                
                // Loop over roleIds
                JSONArray jsonRoleIds = jsonRule.getJSONArray(JSONConstants.ROLE_IDS);
                JSONArray jsonOrgIds = null;
                if (jsonRule.has(JSONConstants.ORG_IDS)) {
                    jsonOrgIds = jsonRule.getJSONArray(JSONConstants.ORG_IDS);
                }
                JSONArray jsonGroupIds = null;
                if (jsonRule.has(JSONConstants.GROUP_IDS)) {
                    jsonGroupIds = jsonRule.getJSONArray(JSONConstants.GROUP_IDS);
                }
                for (int roleIdx = 0 ; roleIdx < jsonRoleIds.length() ; roleIdx++) {
                    if (jsonOrgIds != null) {
                        for (int orgIdx = 0 ; orgIdx < jsonOrgIds.length() ; orgIdx++) {
                            BroadcastRuleLocalServiceUtil.addRule(applicationId, schoolId, jsonRoleIds.getLong(roleIdx), jsonOrgIds.getLong(orgIdx), 0);
                        }
                    }
                    if (jsonGroupIds != null) {
                        for (int groupIdx = 0; groupIdx < jsonGroupIds.length(); groupIdx++) {
                            BroadcastRuleLocalServiceUtil.addRule(applicationId, schoolId, jsonRoleIds.getLong(roleIdx), 0, jsonGroupIds.getLong(groupIdx));
                        }
                    }
                }
            }

            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error creating application broadcast rule for applicationId " + applicationId + " and schoolId " + schoolId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        
        return result;
    }
    
}
