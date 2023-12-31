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

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.contact.service.ContactLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.group.model.CommunityInfos;
import com.weprode.facile.group.model.GroupActivity;
import com.weprode.facile.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.facile.group.service.GroupActivityLocalServiceUtil;
import com.weprode.facile.group.service.GroupUtilsService;
import com.weprode.facile.group.service.base.GroupUtilsServiceBaseImpl;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.model.OrgDetails;
import com.weprode.facile.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=group",
                "json.web.service.context.path=GroupUtils"
        },
        service = GroupUtilsService.class
)
public class GroupUtilsServiceImpl extends GroupUtilsServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(GroupUtilsServiceImpl.class);

    // Used in Horaires
    @JSONWebService(value = "get-user-groups", method = "GET")
    public JSONObject getUserGroups(long schoolId, boolean includeInstitutional, boolean includeCommunities, boolean pedagogicalOnly) {
        JSONObject result = new JSONObject();

        logger.debug("Get user groups for schoolId " + schoolId + ", includeInstitutional=" + includeInstitutional + ", includeCommunities=" + includeCommunities + ", pedagogicalOnly=" + pedagogicalOnly);

        // SchoolId = 0 means all user's schools
        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!RoleUtilsLocalServiceUtil.isAdministrator(user) &&
                !RoleUtilsLocalServiceUtil.isPersonal(user) &&
                !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets groups");
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }
        try {
            JSONArray groupsArray = new JSONArray();

            if (includeInstitutional) {
                List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserVisibilitySchools(user);

                // Loop over schools
                for (Organization school : userSchools){

                    if (schoolId == 0 || school.getOrganizationId() == schoolId) {

                        List<Organization> schoolOrganizations = OrgUtilsLocalServiceUtil.getSchoolClasses(school.getOrganizationId(), false);
                        // Loop over classes
                        for (Organization org : schoolOrganizations){
                            if (org.getParentOrganizationId() == school.getOrganizationId()) {
                                JSONObject classObject = new JSONObject();
                                classObject.put(JSONConstants.GROUP_ID, org.getGroupId());
                                classObject.put(JSONConstants.GROUP_NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false));
                                groupsArray.put(classObject);
                            }
                        }
                    }
                }
            }

            if (includeCommunities) {
                // Loop over personal groups
                List<Group> personalGroups = CommunityInfosLocalServiceUtil.getUserCommunities(user.getUserId(), pedagogicalOnly, true);
                if (!personalGroups.isEmpty()) {
                    for (Group group : personalGroups) {
                        JSONObject groupObject = new JSONObject();
                        groupObject.put(JSONConstants.GROUP_ID, group.getGroupId());
                        groupObject.put(JSONConstants.GROUP_NAME, group.getName(LocaleUtil.getDefault()));
                        groupObject.put(JSONConstants.IS_PERSONAL_GROUP, true);
                        groupsArray.put(groupObject);
                    }
                }
            }

            result.put(JSONConstants.SUCCESS, true);
            result.put(JSONConstants.GROUPS, groupsArray);
        } catch (Exception e) {
            logger.error("Error fetching user groups", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    @JSONWebService(value = "get-user-collaborative-groups", method = "GET")
    public JSONObject getUserCollaborativeGroups(String filter, boolean allCommunities, boolean allClasses, boolean allCours) {
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

        try {
            logger.debug("Get user collaborative groups allCommunities=" + allCommunities + ", allClasses=" + allClasses + ", allCours=" + allCours);

            JSONArray groupsArray = new JSONArray();
            DateFormat dateFormat = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);

            UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
            long schoolId = userProperties.getEtabId();

            List<Group> communities = new ArrayList<>();
            if (RoleUtilsLocalServiceUtil.isDirectionMember(user) && allCommunities) {
                communities = CommunityInfosLocalServiceUtil.getSchoolCommunities(schoolId, false, true);
            } else if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) || (!allClasses && !allCours)) {
                // My communities
                communities = CommunityInfosLocalServiceUtil.getUserCommunities(user.getUserId(), false,false);
            }

            for (Group group : communities) {
                if (filter.equals("") || containsIgnoreCase(group.getName(LocaleUtil.getDefault()), filter)) {
                    JSONObject jsonGroup = new JSONObject();
                    CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(group.getGroupId());
                    if (communityInfos.getStatus() == WorkflowConstants.STATUS_EXPIRED) {
                        continue;
                    }
                    jsonGroup.put(JSONConstants.GROUP_ID, group.getGroupId());
                    jsonGroup.put(JSONConstants.GROUP_NAME, group.getName(user.getLocale()));
                    jsonGroup.put(JSONConstants.DESCRIPTION, group.getDescription(user.getLocale()));
                    jsonGroup.put(JSONConstants.STATUS, communityInfos.getStatus());
                    String color = communityInfos.getColor() != null && !communityInfos.getColor().isEmpty() ? communityInfos.getColor() : "#4353B3";
                    jsonGroup.put(JSONConstants.COLOR, color);
                    jsonGroup.put(JSONConstants.IS_PEDAGOGICAL, communityInfos.getIsPedagogical());

                    if (communityInfos.getExpirationDate() != null) {
                        jsonGroup.put(JSONConstants.EXPIRATION_DATE, dateFormat.format(communityInfos.getExpirationDate()));
                        jsonGroup.put(JSONConstants.IS_EXPIRED, communityInfos.getExpirationDate().before(new Date()));
                    }
                    jsonGroup.put(JSONConstants.IS_INSTITUTIONAL, false);
                    jsonGroup.put(JSONConstants.IS_ADMIN, RoleUtilsLocalServiceUtil.isCommunityAdmin(user.getUserId(), group.getGroupId()));
                    jsonGroup.put(JSONConstants.NB_MEMBERS, UserLocalServiceUtil.getGroupUsersCount(group.getGroupId(), WorkflowConstants.STATUS_APPROVED));
                    // Return the group documentId
                    Folder groupRootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(group.getGroupId());
                    jsonGroup.put(JSONConstants.ROOT_FOLDER_ID, groupRootFolder.getFolderId());
                    groupsArray.put(jsonGroup);
                }
            }

            // Organizations
            List<Integer> types = new ArrayList<>();
            List<Organization> organizations = new ArrayList<>();
            if (allClasses && RoleUtilsLocalServiceUtil.isPersonal(user)) {
                types.add(OrgConstants.CLASS_TYPE);
                types.add(OrgConstants.SUBJECT_TYPE);
                organizations = OrgUtilsLocalServiceUtil.getSchoolOrganizations(schoolId, types, false);
                // Adding school org
                organizations.add(OrganizationLocalServiceUtil.getOrganization(schoolId));
            } else if (allCours && RoleUtilsLocalServiceUtil.isPersonal(user)) {
                types.add(OrgConstants.COURS_TYPE);
                organizations = OrgUtilsLocalServiceUtil.getSchoolOrganizations(schoolId, types, false);
            } else if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) || (!allCommunities)) {
                // My institutional
                types.add(OrgConstants.COURS_TYPE);
                types.add(OrgConstants.SCHOOL_TYPE);
                if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
                    types.add(OrgConstants.SUBJECT_TYPE);
                }
                if (!RoleUtilsLocalServiceUtil.isParent(user)) {
                    types.add(OrgConstants.CLASS_TYPE);
                }
                organizations = UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, false, OrgConstants.ALL_SCHOOLS_ID);
            }

            if (!allClasses && !allCours && !allCommunities) {
                // Doyens, psy and conseillers sociaux see their classes
                organizations.addAll(UserOrgsLocalServiceUtil.getRoleAffectedClasses(user));
            }

            List<Long> orgIds = new ArrayList<>(); // For duplicates
            for (Organization org : organizations) {
                if ((filter.equals("") || containsIgnoreCase(org.getName(),filter)) && !orgIds.contains(org.getOrganizationId())) {
                    OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(org.getOrganizationId());
                    boolean withSchoolName = orgDetails.getType() == OrgConstants.SCHOOL_TYPE;
                    String formattedName = OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), withSchoolName);

                    JSONObject jsonGroup = new JSONObject();
                    jsonGroup.put(JSONConstants.GROUP_ID, org.getGroupId());
                    jsonGroup.put(JSONConstants.GROUP_NAME, formattedName);
                    jsonGroup.put(JSONConstants.DESCRIPTION, "Espace collaboratif de " + formattedName);
                    jsonGroup.put(JSONConstants.STATUS, 0);
                    jsonGroup.put(JSONConstants.COLOR, OrgUtilsLocalServiceUtil.getOrgColor(user, org));
                    // No expiration date for school, subject or volee
                    if (orgDetails.getType() != OrgConstants.SCHOOL_TYPE && orgDetails.getType() != OrgConstants.SUBJECT_TYPE) {
                        jsonGroup.put(JSONConstants.EXPIRATION_DATE, dateFormat.format(ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate()));
                    }
                    jsonGroup.put(JSONConstants.IS_EXPIRED, false);
                    jsonGroup.put(JSONConstants.IS_INSTITUTIONAL, true);
                    jsonGroup.put(JSONConstants.IS_PEDAGOGICAL, orgDetails.getType() == OrgConstants.COURS_TYPE);
                    jsonGroup.put(JSONConstants.IS_CLASS, orgDetails.getType() == OrgConstants.CLASS_TYPE);
                    jsonGroup.put(JSONConstants.IS_SCHOOL, orgDetails.getType() == OrgConstants.SCHOOL_TYPE);
                    jsonGroup.put(JSONConstants.IS_SUBJECT, orgDetails.getType() == OrgConstants.SUBJECT_TYPE);
                    jsonGroup.put(JSONConstants.IS_ADMIN, false);
                    // Members : do not count parents for classes and cours
                    jsonGroup.put(JSONConstants.NB_MEMBERS, UserOrgsLocalServiceUtil.countOrgMembers(org.getOrganizationId()));
                    // Return the group documentId
                    Folder groupRootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(org.getGroupId());
                    jsonGroup.put(JSONConstants.ROOT_FOLDER_ID, groupRootFolder.getFolderId());

                    orgIds.add(org.getOrganizationId());
                    groupsArray.put(jsonGroup);
                }
            }

            result.put(JSONConstants.SUCCESS, true);
            result.put(JSONConstants.GROUPS, groupsArray);

        } catch (Exception e) {
            logger.error("Error fetching user groups", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    @JSONWebService(value = "get-group-members", method = "GET")
    public JSONObject getGroupMembers(long groupId) {
        JSONObject result = new JSONObject();
        
        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        // Limited to user's groups and all groups for direction
        if (!UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId()).contains(groupId) && !RoleUtilsLocalServiceUtil.isDirectionMember(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets members of group " + groupId);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }
        logger.debug("User " + user.getFullName() + " fetches members of group " + groupId);

        try {
            JSONArray jsonMembers = new JSONArray();
            Group group = GroupLocalServiceUtil.getGroup(groupId);
            List<User> groupMembers;
            if (group.isRegularSite()) {
                groupMembers = UserLocalServiceUtil.getGroupUsers(groupId);
                if (groupMembers != null) {
                    for (User member : groupMembers) {
                        if (member.isActive()) {
                            JSONObject jsonMember = ContactLocalServiceUtil.convertUserToJson(member);
                            jsonMember.put(JSONConstants.IS_TEACHER, RoleUtilsLocalServiceUtil.isTeacher(member));
                            jsonMember.put(JSONConstants.IS_PARENT, RoleUtilsLocalServiceUtil.isParent(member));
                            jsonMember.put(JSONConstants.IS_STUDENT, RoleUtilsLocalServiceUtil.isStudent(member));
                            jsonMember.put(JSONConstants.IS_ADMIN, RoleUtilsLocalServiceUtil.isCommunityAdmin(member.getUserId(), groupId));
                            jsonMembers.put(jsonMember);
                        }
                    }
                }
            } else {
                groupMembers = UserLocalServiceUtil.getOrganizationUsers(group.getClassPK());
                OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(group.getClassPK());
                boolean isClassOrCours = orgDetails.getType() == OrgConstants.CLASS_TYPE || orgDetails.getType() == OrgConstants.COURS_TYPE;
                if (groupMembers != null) {
                    for (User member : groupMembers) {
                        if (member.isActive() && (!isClassOrCours || !RoleUtilsLocalServiceUtil.isParent(member))) {
                            JSONObject jsonMember = ContactLocalServiceUtil.convertUserToJson(member);
                            jsonMember.put(JSONConstants.IS_TEACHER, RoleUtilsLocalServiceUtil.isTeacher(member));
                            jsonMember.put(JSONConstants.IS_PARENT, RoleUtilsLocalServiceUtil.isParent(member));
                            jsonMember.put(JSONConstants.IS_STUDENT, RoleUtilsLocalServiceUtil.isStudent(member));
                            jsonMember.put(JSONConstants.IS_ADMIN, false);
                            jsonMembers.put(jsonMember);
                        }
                    }
                }
            }
            result.put(JSONConstants.MEMBERS, jsonMembers);

        } catch (Exception e) {
            logger.error("Error while fetching details for groupId " + groupId, e);
        }

        result.put(JSONConstants.SUCCESS, true);
        return result;
    }

    @JSONWebService(value = "get-group-activity", method = "GET")
    public JSONObject getGroupActivity(long groupId, String maxDate, int nbResults) {

        JSONObject result = new JSONObject();
        result.put(JSONConstants.SUCCESS, false);

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        // Limited to user's groups for students and parents, else authorized
        if (RoleUtilsLocalServiceUtil.isStudentOrParent(user) && !UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId()).contains(groupId)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets activity of group " + groupId);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONArray jsonActivities = new JSONArray();
            Date maximumDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(maxDate);
            List<GroupActivity> groupActivities = GroupActivityLocalServiceUtil.getFullGroupActivities(user.getUserId(), groupId, maximumDate, nbResults);
            for (GroupActivity groupActivity : groupActivities) {
                JSONObject jsonActivity = GroupActivityLocalServiceUtil.convertGroupActivity(user.getUserId(), groupActivity);
                if (jsonActivity != null) {
                    jsonActivities.put(jsonActivity);
                }
            }
            result.put(JSONConstants.ACTIVITIES, jsonActivities);
            result.put(JSONConstants.SUCCESS, true);


        } catch (Exception e) {
            logger.error("Error while fetching activity for groupId " + groupId, e);
        }

        return result;
    }

    private boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        
        return false;
    }

}
