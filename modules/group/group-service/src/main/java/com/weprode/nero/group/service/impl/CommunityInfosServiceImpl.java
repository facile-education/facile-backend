package com.weprode.nero.group.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.group.model.CommunityInfos;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.group.service.MembershipActivityLocalServiceUtil;
import com.weprode.nero.group.service.base.CommunityInfosServiceBaseImpl;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommunityInfosServiceImpl extends CommunityInfosServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(CommunityInfosServiceImpl.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    @JSONWebService(value = "create-community", method = "POST")
    public JSONObject createCommunity(String groupName, String description, boolean isPedagogical, String members, String color) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS,false);
                return result;
            }

            logger.info("User " + user.getFullName() + " creates community " + groupName);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS,false);
            return result;
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
            // TODO cdt
            // Date expireDate = ConfigurationLocalServiceUtil.getConfiguration(
            //        UserOrgsLocalServiceUtil.getEtabRatachement(user).getOrganizationId()).getEndSessionsDate();

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

            CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(createdGroup.getGroupId());

            communityInfos.setCreationDate(new Date());
            communityInfos.setCreatorId(user.getUserId());
            // TODO cdt
            // communityInfos.setExpirationDate(expireDate);
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

            JSONArray jsonMembers = JSONFactoryUtil.createJSONArray(members);

            Role ownerRole = RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.SITE_OWNER);
            Role managerRole = RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.SITE_ADMINISTRATOR);
            ResourceBundle messages = ResourceBundle.getBundle("content.Language", user.getLocale());

            List<Long> notifiedMemberIds = new ArrayList<>();

            for (int i=0; i < jsonMembers.length(); i++) {

                JSONObject jsonMember = jsonMembers.getJSONObject(i);

                boolean isAdmin = jsonMember.getBoolean(JSONConstants.IS_ADMIN);
                String type = jsonMember.getString(JSONConstants.TYPE);
                Long memberId = jsonMember.getLong(JSONConstants.USER_ID);
                if (!notifiedMemberIds.contains(memberId)) {
                    notifiedMemberIds.add(memberId);
                }

                if (type.equals("organization")) {
                    OrganizationLocalServiceUtil.addGroupOrganizations(createdGroup.getGroupId(), new long[]{memberId});
                } else {

                    // Add members to the group and empty their group cache so that they see the new group immediately
                    UserLocalServiceUtil.addGroupUsers(createdGroup.getGroupId(), new long[]{memberId});

                    if (isAdmin) {
                        // Give admin users the group admin role
                        UserGroupRoleLocalServiceUtil.addUserGroupRoles(new long[]{memberId}, createdGroup.getGroupId(), managerRole.getRoleId());
                    }
                }
            }

            // Notify members that they have been added to a new group
            Long noReplySenderId = Long.valueOf(PropsUtil.get(NeroSystemProperties.MAIL_NO_REPLY_USER_ID));
            User noReplyUser = UserLocalServiceUtil.getUser(noReplySenderId);
            String subject = messages.getString("creation-du-groupe") + " " + createdGroup.getName();
            String content = "Bonjour,</br>" + user.getFullName() + " " + messages.getString("added-to-group") + " " + createdGroup.getName()+".</br></br> L'&eacute;quipe technique";
            // TODO Messaging
            // InternalMessageLocalServiceUtil.sendInternalMessage(noReplyUser, notifiedMemberIds, subject, content, JSONFactoryUtil.createJSONArray(), 0, 0);

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

        JSONObject result = JSONFactoryUtil.createJSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            logger.info("User " + user.getFullName() + " is about to create community " + communityName);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);

            return result;
        }

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
                DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
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

        JSONObject result = JSONFactoryUtil.createJSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS,false);
                return result;
            }
            if (!RoleUtilsLocalServiceUtil.isCommunityAdmin(user.getUserId(), groupId)) {
                throw new Exception("Only group admin can update group infos");
            }
            logger.info("User " + user.getFullName() + " updates community " + groupId + " with name " + groupName);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS,false);
            return result;
        }

        // Check group name unicity
        try {
            GroupLocalServiceUtil.getGroup(user.getCompanyId(), groupName);
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS,false);
        }

        try {
            // TODO cdt
            Date expireDate = new Date();
            // Date expireDate = ConfigurationLocalServiceUtil.getConfiguration(UserOrgsLocalServiceUtil.getEtabRatachement(user).getOrganizationId()).getEndSessionsDate();

            Group group = GroupLocalServiceUtil.getGroup(groupId);
            group.setName(groupName);
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
            JSONArray jsonMembers = JSONFactoryUtil.createJSONArray(members);

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
                    if (membersIdAdmin.get(memberId) != isAdmin && memberId != user.getUserId()) {
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

                // Add members to the group and empty their group cache so that they see the new group immediately
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
            Long noReplySenderId = Long.valueOf(PropsUtil.get(NeroSystemProperties.MAIL_NO_REPLY_USER_ID));
            User noReplyUser = UserLocalServiceUtil.getUser(noReplySenderId);
            String subject = messages.getString("ajouter-au-groupe") + " " + group.getName();
            String content = "Bonjour,</br>" + user.getFullName() + " " + messages.getString("added-to-group") + " " + group.getName()+".</br></br> L'&eacute;quipe technique";
            // TODO Messaging
            // InternalMessageLocalServiceUtil.sendInternalMessage(noReplyUser, notifiedMemberIds, subject, content, JSONFactoryUtil.createJSONArray(), 0, 0);

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

    @JSONWebService(value = "remove-community", method = "GET")
    public JSONObject removeCommunity(long groupId) {

        JSONObject result = JSONFactoryUtil.createJSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
            }
            if (!RoleUtilsLocalServiceUtil.isCommunityAdmin(user.getUserId(), groupId)) {
                throw new Exception("Only group admin can remove the community");
            }
            logger.info("User " + user.getFullName() + " removes community " + groupId);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
        }

        try {
            // Rename group
            Group group = GroupLocalServiceUtil.getGroup(groupId);
            int nbTry = 1;
            boolean success = false;
            while (!success && nbTry < 500) {
                try {
                    group.setName(group.getName() + " (archived)" + (nbTry > 1 ? " (" + nbTry + ")" : ""));
                    GroupLocalServiceUtil.updateGroup(group);
                    success = true;
                } catch (SystemException e) {
                    nbTry++;
                }
            }
            if (!success) {
                throw new Exception("exceed nb renamed try");
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
        JSONObject result = JSONFactoryUtil.createJSONObject();
        
        User user = null;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS,false);
            }
            logger.info("User " + user.getFullName() + " extends community " + groupId + " to school year end date");
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS,false);
        }

        try {
            // Get school year end date
            // TODO cdt
            Date schoolYearEndDate = new Date();
            // Date schoolYearEndDate = ConfigurationLocalServiceUtil.getConfiguration(UserOrgsLocalServiceUtil.getEtabRatachement(user).getOrganizationId()).getEndSessionsDate();

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
