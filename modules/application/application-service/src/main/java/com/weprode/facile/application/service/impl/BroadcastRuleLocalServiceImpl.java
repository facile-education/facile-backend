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
import org.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.application.model.BroadcastRule;
import com.weprode.facile.application.service.base.BroadcastRuleLocalServiceBaseImpl;
import com.weprode.facile.commons.constants.JSONConstants;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.application.model.BroadcastRule",
        service = AopService.class
)
public class BroadcastRuleLocalServiceImpl extends BroadcastRuleLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(BroadcastRuleLocalServiceImpl.class);

    public BroadcastRule addRule(long applicationId, long schoolId, long roleId, long orgId, long groupId) {
        try {
            BroadcastRule rule = broadcastRulePersistence.create(counterLocalService.increment());

            rule.setApplicationId(applicationId);
            rule.setSchoolId(schoolId);
            rule.setRoleId(roleId);
            rule.setOrgId(orgId);
            rule.setGroupId(groupId);
            logger.info("Created application broadcast rule for applicationId " + applicationId + ", schoolId " + schoolId + ", roleId " + roleId + ", orgId " + orgId + ", groupId " + groupId);

            return broadcastRulePersistence.updateImpl(rule);
        } catch (Exception e) {
            logger.error("Error creating new application broadcast rule for applicationId " + applicationId + ", schoolId " + schoolId + ", roleId " + roleId + ", groupId " + groupId + " and orgId " + orgId);
        }

        return null;
    }

    public boolean deleteRule(long applicationBroadcastRuleId) {
        try {
            broadcastRulePersistence.remove(applicationBroadcastRuleId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting application broadcast rule " + applicationBroadcastRuleId, e);
        }

        return false;
    }

    public boolean deleteSchoolRules(long applicationId, long schoolId) {
        try {
            logger.info("Deleting broadcast rules for applicationId " + applicationId + " and schoolId " + schoolId);
            broadcastRulePersistence.removeByapplicationId_schoolId(applicationId, schoolId);

            return true;
        } catch (Exception e) {
            logger.error("Error deleting application broadcast rules", e);
        }

        return false;
    }

    public List<BroadcastRule> getSchoolRules(long applicationId, long schoolId) {
        List<BroadcastRule> rules = new ArrayList<>();

        try {
            rules = broadcastRulePersistence.findByapplicationId_schoolId(applicationId, schoolId);
        } catch (Exception e) {
            logger.error("Error fetching broadcast rules for applicationId " + applicationId + " and schoolId " + schoolId, e);
        }

        return rules;
    }

    public boolean removeRule(long applicationBroadcastRuleId) {
        try {
            broadcastRulePersistence.remove(applicationBroadcastRuleId);
            return true;
        } catch (Exception e) {
            logger.error("Error removing application broadcast rule " + applicationBroadcastRuleId, e);
        }

        return false;
    }

    public JSONObject convertRule(BroadcastRule rule) {
        JSONObject jsonRule = new JSONObject();

        jsonRule.put(JSONConstants.APPLICATION_ID, rule.getApplicationId());
        jsonRule.put(JSONConstants.SCHOOL_ID, rule.getSchoolId());
        jsonRule.put(JSONConstants.ROLE_ID, rule.getRoleId());
        jsonRule.put(JSONConstants.ORG_ID, rule.getOrgId());
        jsonRule.put(JSONConstants.GROUP_ID, rule.getGroupId());

        return jsonRule;
    }

}
