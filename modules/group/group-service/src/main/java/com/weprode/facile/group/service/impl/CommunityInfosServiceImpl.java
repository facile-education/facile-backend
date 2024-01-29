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

package com.weprode.facile.group.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.group.model.CommunityInfos;
import com.weprode.facile.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.facile.group.service.CommunityInfosService;
import com.weprode.facile.group.service.MembershipActivityLocalServiceUtil;
import com.weprode.facile.group.service.base.CommunityInfosServiceBaseImpl;
import com.weprode.facile.messaging.service.MessageLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(
        property = {
                "json.web.service.context.name=group",
                "json.web.service.context.path=CommunityInfos"
        },
        service = CommunityInfosService.class
)
public class CommunityInfosServiceImpl extends CommunityInfosServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(CommunityInfosServiceImpl.class);

    @JSONWebService(value = "create-community", method = "POST")
    public JSONObject createCommunity(String groupName, String description, boolean isPedagogical, String members, String color) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates community");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        // Check group name unicity
        Group existingGroup = null;
        try {
            existingGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), groupName);
        } catch (Exception e) {
            logger.debug(e);
        }

        if (existingGroup != null) {
            result.put(JSONConstants.ERROR, JSONConstants.GROUP_NAME);
            result.put(JSONConstants.SUCCESS,false);
            return result;
        }

        try {
            Date expireDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();

            // Create the group
            String friendlyUrl = StringPool.SLASH + groupName;
            Map<Locale, String> groupNameMap = new HashMap<>();
            groupNameMap.put(LocaleUtil.getDefault(), groupName);

            Map<Locale, String> descriptionMap = new HashMap<>();
            descriptionMap.put(LocaleUtil.getDefault(), description);

            Group createdGroup = GroupLocalServiceUtil.addGroup(user.getUserId(), GroupConstants.DEFAULT_PARENT_GROUP_ID,
                    "com.liferay.portal.kernel.model.Group", 20030, GroupConstants.DEFAULT_LIVE_GROUP_ID,
                    groupNameMap, descriptionMap, GroupConstants.TYPE_SITE_PRIVATE, true,
                    GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, friendlyUrl, true, true, new ServiceContext());

            CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.createCommunity(createdGroup.getGroupId(), user.getUserId());

            communityInfos.setCreationDate(new Date());
            communityInfos.setCreatorId(user.getUserId());
            communityInfos.setExpirationDate(expireDate);
            communityInfos.setGroupId(createdGroup.getGroupId());
            communityInfos.setIsPedagogical(isPedagogical);
            communityInfos.setIsContactList(true);

            // Check color format
            Pattern pattern = Pattern.compile("#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})");
            Matcher matcher = pattern.matcher(color);
            if (matcher.matches()) {
                communityInfos.setColor(color);
            }
            CommunityInfosLocalServiceUtil.updateCommunityInfos(communityInfos);

            JSONArray jsonMembers = new JSONArray(members);

            Role ownerRole = RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.SITE_OWNER);
            Role managerRole = RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.SITE_ADMINISTRATOR);
            ResourceBundle messages = ResourceBundle.getBundle("content.Language", user.getLocale());

            List<Long> notifiedMemberIds = new ArrayList<>();

            for (int i=0; i < jsonMembers.length(); i++) {

                JSONObject jsonMember = jsonMembers.getJSONObject(i);

                boolean isAdmin = jsonMember.getBoolean(JSONConstants.IS_ADMIN);
                String type = JSONConstants.getStringValue(jsonMember, JSONConstants.TYPE, StringPool.BLANK);
                Long memberId = jsonMember.getLong(JSONConstants.USER_ID);
                if (!notifiedMemberIds.contains(memberId)) {
                    notifiedMemberIds.add(memberId);
                }

                if (type.equals("organization")) {
                    OrganizationLocalServiceUtil.addGroupOrganizations(createdGroup.getGroupId(), new long[]{memberId});
                } else {

                    // Add member to the group
                    UserLocalServiceUtil.addGroupUsers(createdGroup.getGroupId(), new long[]{memberId});

                    if (isAdmin) {
                        // Give admin users the group admin role
                        UserGroupRoleLocalServiceUtil.addUserGroupRoles(new long[]{memberId}, createdGroup.getGroupId(), managerRole.getRoleId());
                    }
                    MembershipActivityLocalServiceUtil.addMembershipActivity(createdGroup.getGroupId(), user.getUserId(), Collections.singletonList(memberId), true);
                }
            }

            // Notify members that they have been added to a new group
            long noReplySenderId = Long.parseLong(PropsUtil.get(NeroSystemProperties.MESSAGING_NOREPLY_USER_ID));
            String subject = messages.getString("creation-du-groupe") + " " + createdGroup.getName(LocaleUtil.getDefault());
            String content = "Bonjour,</br></br>" + user.getFullName() + " " + messages.getString("added-to-group") + " " + createdGroup.getName(LocaleUtil.getDefault())+".</br></br>Meilleurs messages,</br>L'&eacute;quipe technique";
            MessageLocalServiceUtil.sendMessage(noReplySenderId, notifiedMemberIds, subject, content);

            // The creator has both owner and admin roles
            UserGroupRoleLocalServiceUtil.addUserGroupRoles(user.getUserId(), createdGroup.getGroupId(), new long[] {ownerRole.getRoleId(), managerRole.getRoleId()});
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error creating community", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "check-community-name", method = "GET")
    public JSONObject checkCommunityName(String communityName) {

        JSONObject result = new JSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " checks community name");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }
        logger.info("User " + user.getFullName() + " is about to create community " + communityName);
        Group existingGroup = null;
        try {
            existingGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), communityName);
        } catch (Exception e) {
            logger.debug(e);
        }

        if (existingGroup != null) {
            result.put(JSONConstants.SUCCESS, false);
            // If the group is expired and the user is the owner, we suggest him to reactivate the group
            CommunityInfos communityInfos = null;
            try {
                communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(existingGroup.getGroupId());
            } catch (Exception e) {
                logger.debug(e);
            }
            if (communityInfos != null && communityInfos.getStatus() == 3 && communityInfos.getCreatorId() == user.getUserId()) {
                DateFormat dateFormat = new SimpleDateFormat(JSONConstants.DATE_EXCHANGE_FORMAT);
                result.put(JSONConstants.ERROR_CODE, 1);
                result.put(JSONConstants.CREATION_DATE, dateFormat.format(communityInfos.getCreationDate()));
                result.put(JSONConstants.EXPIRATION_DATE, dateFormat.format(communityInfos.getExpirationDate()));
                result.put(JSONConstants.GROUP_ID, communityInfos.getGroupId());
            } else {
                result.put(JSONConstants.ERROR_CODE, 2);
            }
        } else {
            result.put(JSONConstants.SUCCESS, true);
        }

        return result;
    }

    @JSONWebService(value = "edit-community", method = "POST")
    public JSONObject editCommunity(long groupId, String groupName, String description, boolean isPedagogical, String members, String color) {

        JSONObject result = new JSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isCommunityAdmin(user.getUserId(), groupId)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " edits community " + groupId);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        // Check group name unicity
        try {
            GroupLocalServiceUtil.getGroup(user.getCompanyId(), groupName);
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS,false);
        }

        try {
            Date expireDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();

            Group group = GroupLocalServiceUtil.getGroup(groupId);
            group.setName(groupName, user.getLocale());
            group.setDescription(description);
            GroupLocalServiceUtil.updateGroup(group);

            CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(groupId);
            communityInfos.setIsPedagogical(isPedagogical);
            communityInfos.setIsContactList(true);
            communityInfos.setExpirationDate(expireDate);

            // Check color format
            Pattern pattern = Pattern.compile("#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})");
            Matcher matcher = pattern.matcher(color);
            if (matcher.matches()) {
                communityInfos.setColor(color);
            }
            CommunityInfosLocalServiceUtil.updateCommunityInfos(communityInfos);

            // Handle members
            JSONArray jsonMembers = new JSONArray(members);

            Role managerRole = RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.SITE_ADMINISTRATOR);
            ResourceBundle messages = ResourceBundle.getBundle("content.Language", user.getLocale());

            List<Long> notifiedMemberIds = new ArrayList<>();

            // Build current member map with their admin status
            Map<Long, Boolean> membersIdAdmin = new HashMap<>();
            for (User member : UserLocalServiceUtil.getGroupUsers(groupId)) {
                membersIdAdmin.put(member.getUserId(), RoleUtilsLocalServiceUtil.isCommunityAdmin(member.getUserId(), groupId));
            }

            for (int i=0; i < jsonMembers.length(); i++) {
                JSONObject jsonMember = jsonMembers.getJSONObject(i);

                boolean isAdmin = jsonMember.getBoolean(JSONConstants.IS_ADMIN);
                Long memberId = jsonMember.getLong(JSONConstants.USER_ID);

                // Handle existing member
                if (membersIdAdmin.containsKey(memberId)) {
                    // Check admin rights - User cannot remove himself from group admins
                    if (!membersIdAdmin.get(memberId).equals(isAdmin) && memberId != user.getUserId()) {
                        if (isAdmin) {
                            logger.info("User " + user.getFullName() + " adds user " + memberId + " as community admin for groupId " + group.getGroupId());
                            // Give admin users the group admin role
                            UserGroupRoleLocalServiceUtil.addUserGroupRoles(new long[]{memberId}, group.getGroupId(), managerRole.getRoleId());
                        } else {
                            logger.info("User " + user.getFullName() + " removes user " + memberId + " as community admin for groupId " + groupId);
                            UserGroupRoleLocalServiceUtil.deleteUserGroupRoles(memberId, group.getGroupId(), new long[]{managerRole.getRoleId()});
                        }
                    }
                    membersIdAdmin.remove(memberId);
                    continue;
                }

                // Handle new member
                if (!notifiedMemberIds.contains(memberId)) {
                    notifiedMemberIds.add(memberId);
                }

                // Add members to the group
                UserLocalServiceUtil.addGroupUsers(group.getGroupId(), new long[]{memberId});

                if (isAdmin) {
                    logger.info("User " + user.getFullName() + " adds user " + memberId + " as community admin for groupId " + group.getGroupId());
                    // Give admin users the group admin role
                    UserGroupRoleLocalServiceUtil.addUserGroupRoles(new long[]{memberId}, group.getGroupId(), managerRole.getRoleId());
                }

                logger.info("Admin " + user.getFullName() + " adds user " + jsonMember.getLong("userId") + " to group " + group.getGroupId());
                MembershipActivityLocalServiceUtil.addMembershipActivity(group.getGroupId(), user.getUserId(), Collections.singletonList(memberId), true);
            }

            // Notify members that they have been added to a new group
            long noReplySenderId = Long.parseLong(PropsUtil.get(NeroSystemProperties.MESSAGING_NOREPLY_USER_ID));
            String subject = messages.getString("ajouter-au-groupe") + " " + group.getName(LocaleUtil.getDefault());
            String content = "Bonjour,</br></br>" + user.getFullName() + " " + messages.getString("added-to-group") + " " + group.getName(LocaleUtil.getDefault())+".</br></br>Meilleurs messages,</br> L'&eacute;quipe technique";
            MessageLocalServiceUtil.sendMessage(noReplySenderId, notifiedMemberIds, subject, content);

            // Remove obsolete members
            for (Map.Entry<Long, Boolean> oldMember : membersIdAdmin.entrySet()) {
                // They are not allowed to remove themself, to ensure there remains always 1 admin
                if (user.getUserId() != oldMember.getKey()) {
                    logger.info("Admin " + user.getFullName() + " removes user " + oldMember.getKey() + " from group " + group.getGroupId());
                    GroupLocalServiceUtil.unsetUserGroups(oldMember.getKey(), new long[] {group.getGroupId()});
                    MembershipActivityLocalServiceUtil.addMembershipActivity(group.getGroupId(), user.getUserId(), Collections.singletonList(oldMember.getKey()), false);
                }
            }

        } catch (Exception e) {
            logger.error("Error updating community", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        result.put(JSONConstants.SUCCESS, true);
        return result;
    }

    @JSONWebService(value = "remove-community", method = "DELETE")
    public JSONObject removeCommunity(long groupId) {

        JSONObject result = new JSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isCommunityAdmin(user.getUserId(), groupId)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " removes community " + groupId);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            // Rename group
            Group group = GroupLocalServiceUtil.getGroup(groupId);
            int nbTry = 1;
            boolean success = false;
            while (!success && nbTry < 500) {
                try {
                    group.setName(group.getName(LocaleUtil.getDefault()) + " (archived)" + (nbTry > 1 ? " (" + nbTry + ")" : ""), LocaleUtil.getDefault());
                    GroupLocalServiceUtil.updateGroup(group);
                    success = true;
                } catch (SystemException e) {
                    nbTry++;
                }
            }
            if (!success) {
                logger.error("Error renaming archive the community");
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

            CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(groupId);
            communityInfos.setStatus(3);
            CommunityInfosLocalServiceUtil.updateCommunityInfos(communityInfos);
        } catch (Exception e) {
            logger.error("Error deleting community", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        result.put(JSONConstants.SUCCESS, true);
        return result;
    }

    @JSONWebService(value = "extend-community", method = "POST")
    public JSONObject extendCommunity(long groupId) {
        JSONObject result = new JSONObject();
        
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        if (!RoleUtilsLocalServiceUtil.isCommunityAdmin(user.getUserId(), groupId)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " extends community " + groupId);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            // Get school year end date
            Date schoolYearEndDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();

            CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(groupId);
            communityInfos.setExpirationDate(schoolYearEndDate);
            communityInfos.setStatus(0);
            CommunityInfosLocalServiceUtil.updateCommunityInfos(communityInfos);

        } catch (Exception e) {
            logger.error("Error extending community expiration date for groupId " + groupId, e);
            result.put(JSONConstants.SUCCESS, false);
        }
        result.put(JSONConstants.SUCCESS, true);
        
        return result;
    }
}
