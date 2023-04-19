package com.weprode.nero.group.service.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.group.model.CommunityInfos;
import com.weprode.nero.group.model.GroupActivity;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.group.service.GroupActivityLocalServiceUtil;
import com.weprode.nero.group.service.GroupUtilsService;
import com.weprode.nero.group.service.base.GroupUtilsServiceBaseImpl;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.model.OrgDetails;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.preference.model.UserProperties;
import com.weprode.nero.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.comparator.UserLastNameCaseInsensitiveComparator;
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
        logger.info("Get user groups for schoolId " + schoolId + ", includeInstitutional=" + includeInstitutional + ", includeCommunities=" + includeCommunities + ", pedagogicalOnly=" + pedagogicalOnly);

        // SchoolId = 0 means all user's schools
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();

            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            if (!RoleUtilsLocalServiceUtil.isPersonal(user) && !RoleUtilsLocalServiceUtil.isTeacher(user)) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONArray groupsArray = JSONFactoryUtil.createJSONArray();

            if (includeInstitutional) {
                List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserVisibilitySchools(user);

                // Loop over schools
                for (Organization school : userSchools){

                    if (schoolId == 0 || school.getOrganizationId() == schoolId) {

                        List<Organization> schoolOrganizations = OrgUtilsLocalServiceUtil.getSchoolClasses(school.getOrganizationId(), false);
                        // Loop over classes
                        for (Organization org : schoolOrganizations){
                            if (org.getParentOrganizationId() == school.getOrganizationId()) {
                                JSONObject classObject = JSONFactoryUtil.createJSONObject();
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
                        JSONObject groupObject = JSONFactoryUtil.createJSONObject();
                        groupObject.put(JSONConstants.GROUP_ID, group.getGroupId());
                        groupObject.put(JSONConstants.GROUP_NAME, group.getName());
                        groupObject.put(JSONConstants.IS_PERSONAL_GROUP, true);
                        groupsArray.put(groupObject);
                    }
                }
            }

            if (groupsArray == null) {
                result.put(JSONConstants.SUCCESS, false);
            } else{
                result.put(JSONConstants.SUCCESS, true);
                result.put(JSONConstants.GROUPS, groupsArray);
            }
        } catch (Exception e) {
            logger.error("Error fetching user groups", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    // Used in Collaborative spaces service
    @JSONWebService(value = "get-user-collaborative-groups", method = "GET")
    public JSONObject getUserCollaborativeGroups(String filter, boolean allCommunities, boolean allClasses, boolean allCours) {

        logger.info("Get user collaborative groups allCommunities=" + allCommunities + ", allClasses=" + allClasses + ", allCours=" + allCours);

        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
                result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONArray groupsArray = JSONFactoryUtil.createJSONArray();
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
                if (filter.equals("") || containsIgnoreCase(group.getName(), filter)) {
                    JSONObject jsonGroup = JSONFactoryUtil.createJSONObject();
                    CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(group.getGroupId());
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
            if (allClasses && (RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isPat(user) || RoleUtilsLocalServiceUtil.isEmps(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user))) {
                types.add(OrgConstants.CLASS_TYPE);
                types.add(OrgConstants.SCHOOL_LEVEL_TYPE);
                types.add(OrgConstants.SUBJECT_TYPE);
                organizations = OrgUtilsLocalServiceUtil.getSchoolOrganizations(schoolId, types, null, false);
                // Adding school org
                organizations.add(OrganizationLocalServiceUtil.getOrganization(schoolId));
            } else if (allCours && (RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isPat(user) || RoleUtilsLocalServiceUtil.isEmps(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user))) {
                types.add(OrgConstants.COURS_TYPE);
                organizations = OrgUtilsLocalServiceUtil.getSchoolOrganizations(schoolId, types, null, false);
            } else if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) || (!allCommunities)) {
                // My institutional
                types.add(OrgConstants.COURS_TYPE);
                types.add(OrgConstants.SCHOOL_TYPE);
                types.add(OrgConstants.SCHOOL_LEVEL_TYPE);
                if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
                    types.add(OrgConstants.SUBJECT_TYPE);
                }
                if (!RoleUtilsLocalServiceUtil.isParent(user)) {
                    types.add(OrgConstants.CLASS_TYPE);
                }
                organizations = UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, null, false, OrgConstants.ALL_SCHOOLS_ID);
            }

            // Doyens, psys and conseillers sociaux see their classes
            organizations.addAll(UserOrgsLocalServiceUtil.getRoleAffectedClasses(user));

            for (Organization org : organizations) {
                if (filter.equals("") || containsIgnoreCase(org.getName(),filter)) {
                    OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(org.getOrganizationId());
                    boolean withSchoolName = orgDetails.getType() == OrgConstants.SCHOOL_TYPE || orgDetails.getType() == OrgConstants.SCHOOL_LEVEL_TYPE;
                    String formattedName = OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), withSchoolName);

                    JSONObject jsonGroup = JSONFactoryUtil.createJSONObject();
                    jsonGroup.put(JSONConstants.GROUP_ID, org.getGroupId());
                    jsonGroup.put(JSONConstants.GROUP_NAME, formattedName);
                    jsonGroup.put(JSONConstants.DESCRIPTION, "Espace collaboratif de " + formattedName);
                    jsonGroup.put(JSONConstants.STATUS, 0);
                    jsonGroup.put(JSONConstants.COLOR, OrgUtilsLocalServiceUtil.getOrgColor(user, org));
                    // No expiration date for school, subject or volee
                    if (orgDetails.getType() != OrgConstants.SCHOOL_TYPE && orgDetails.getType() != OrgConstants.SUBJECT_TYPE) {
                        // TODO get date from config
                        jsonGroup.put(JSONConstants.EXPIRATION_DATE, "2023-08-07");
                    }
                    jsonGroup.put(JSONConstants.IS_EXPIRED, false);
                    jsonGroup.put(JSONConstants.IS_INSTITUTIONAL, true);
                    jsonGroup.put(JSONConstants.IS_PEDAGOGICAL, orgDetails.getType() == OrgConstants.COURS_TYPE);
                    jsonGroup.put(JSONConstants.IS_CLASS, orgDetails.getType() == OrgConstants.CLASS_TYPE);
                    jsonGroup.put(JSONConstants.IS_SCHOOL, orgDetails.getType() == OrgConstants.SCHOOL_TYPE);
                    jsonGroup.put(JSONConstants.IS_SUBJECT, orgDetails.getType() == OrgConstants.SUBJECT_TYPE);
                    jsonGroup.put(JSONConstants.IS_ADMIN, RoleUtilsLocalServiceUtil.isTeacher(user));
                    // Members : do not count parents for classes and cours
                    jsonGroup.put(JSONConstants.NB_MEMBERS, UserOrgsLocalServiceUtil.countOrgMembers(org.getOrganizationId()));
                    // Return the group documentId
                    Folder groupRootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(org.getGroupId());
                    jsonGroup.put(JSONConstants.ROOT_FOLDER_ID, groupRootFolder.getFolderId());

                    groupsArray.put(jsonGroup);
                }
            }

            if (groupsArray == null) {
                result.put(JSONConstants.SUCCESS, false);
            } else{
                result.put(JSONConstants.SUCCESS, true);
                result.put(JSONConstants.GROUPS, groupsArray);
            }
        } catch (Exception e) {
            logger.error("Error fetching user groups", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    @JSONWebService(value = "get-users-completion", method="GET")
    public JSONObject getUsersCompletion (String query, long schoolId, long roleId) {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                return result;
            }
            if (!RoleUtilsLocalServiceUtil.isTeacher(user) && !RoleUtilsLocalServiceUtil.isPersonal(user)) {
                throw new Exception();
            }
            logger.info("User " + user.getFullName() + " search users with query " + query);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        try {
            List<Long> schoolIds = new ArrayList<>();
            if (schoolId > 0) {
                schoolIds.add(schoolId);
            }

            List<Long> roleIds = new ArrayList<>();
            if (roleId > 0) {
                roleIds.add(roleId);
            }

            OrderByComparator obc = new UserLastNameCaseInsensitiveComparator(true);

            // TODO Contacts
            List<User> results = new ArrayList<>();
            // List<User> results = ContactLocalServiceUtil.directorySearch(user, query, new ArrayList<Long>(), schoolIds, roleIds,
            //        null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);
            // TODO Convert to JSON with user profil and school ? -> UserUtils ?
            JSONArray contacts = JSONFactoryUtil.createJSONArray();

            for (User contact : results) {
                JSONObject contactJSON = JSONFactoryUtil.createJSONObject();
                contactJSON.put(JSONConstants.USER_ID, contact.getUserId());
                contactJSON.put(JSONConstants.FIRST_NAME, contact.getFirstName());
                contactJSON.put(JSONConstants.LAST_NAME, contact.getLastName());
                contactJSON.put(JSONConstants.SCREEN_NAME, contact.getScreenName());
                Organization contactSchool = UserOrgsLocalServiceUtil.getEtabRatachement(contact);
                contactJSON.put(JSONConstants.SCHOOL_ID, contactSchool.getOrganizationId());
                contactJSON.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(contactSchool.getName(), true));
                // List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserSchools(contact);
                // contactJSON.put("schoolId", userSchools.get(0).getOrganizationId());
                // contactJSON.put("schoolName", userSchools.get(0).getName());
                contactJSON.put(JSONConstants.ROLES, RoleUtilsLocalServiceUtil.displayUserRoles(contact));
                contacts.put(contactJSON);
            }
            result.put(JSONConstants.RESULTS, contacts);
            result.put(JSONConstants.SUCCESS, true);

            return result;
        } catch (Exception e) {
            logger.error("Error in auto-completion", e);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }
    }

    @JSONWebService(value = "get-group-members", method = "GET")
    public JSONObject getGroupMembers(long groupId) {

        JSONObject result = JSONFactoryUtil.createJSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
            logger.info("User " + user.getFullName() + " fetches members of group " + groupId);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            JSONArray jsonMembers = JSONFactoryUtil.createJSONArray();
            Group group = GroupLocalServiceUtil.getGroup(groupId);
            List<User> groupMembers;
            if (group.isRegularSite()) {
                groupMembers = UserLocalServiceUtil.getGroupUsers(groupId);
                if (groupMembers != null) {
                    for (User member : groupMembers) {
                        if (member.isActive()) {
                            JSONObject jsonMember = JSONFactoryUtil.createJSONObject();
                            jsonMember.put(JSONConstants.USER_ID, member.getUserId());
                            jsonMember.put(JSONConstants.USER_NAME, member.getLastName() + " " + member.getFirstName());
                            jsonMember.put(JSONConstants.FIRST_NAME, member.getFirstName());
                            jsonMember.put(JSONConstants.LAST_NAME, member.getLastName());
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
                            JSONObject jsonMember = JSONFactoryUtil.createJSONObject();
                            jsonMember.put(JSONConstants.USER_ID, member.getUserId());
                            jsonMember.put(JSONConstants.USER_NAME, member.getLastName() + " " + member.getFirstName());
                            jsonMember.put(JSONConstants.IS_TEACHER, RoleUtilsLocalServiceUtil.isTeacher(member));
                            jsonMember.put(JSONConstants.IS_PARENT, RoleUtilsLocalServiceUtil.isParent(member));
                            jsonMember.put(JSONConstants.IS_STUDENT, RoleUtilsLocalServiceUtil.isStudent(member));
                            jsonMember.put(JSONConstants.IS_ADMIN, RoleUtilsLocalServiceUtil.isTeacher(user));
                            jsonMembers.put(jsonMember);
                        }
                    }
                }
            }
            result.put("members", jsonMembers);

        } catch (Exception e) {
            logger.error("Error while fetching details for groupId " + groupId, e);
        }

        result.put(JSONConstants.SUCCESS, true);
        return result;
    }

    @JSONWebService(value = "get-group-activity", method = "GET")
    public JSONObject getGroupActivity(long groupId, String maxDate, int nbResults) {

        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put(JSONConstants.SUCCESS, false);

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                return result;
            }
            logger.info("User " + user.getFullName() + " fetches activity for groupId " + groupId);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        try {
            JSONArray jsonActivities = JSONFactoryUtil.createJSONArray();
            List<Long> groupIds = new ArrayList<>();
            groupIds.add(groupId);
            Date maximumDate = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).parse(maxDate);
            List<GroupActivity> groupActivities = GroupActivityLocalServiceUtil.getGroupsActivities(user.getUserId(), groupIds, maximumDate, nbResults);
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

    @JSONWebService(value = "get-specific-group-activities", method = "GET")
    public JSONObject getSpecificGroupActivities(long groupId, String maxDate, int nbResults, boolean allHistory, boolean containNews, boolean containDocs, boolean containMembership, boolean containPendingFirings, boolean containFirings, boolean containHomework, boolean containSessions) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        result.put(JSONConstants.SUCCESS, false);

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                return result;
            }

            logger.info("User " + user.getFullName() + " fetches activity for groupId " + groupId);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        try {
            JSONArray jsonActivities = JSONFactoryUtil.createJSONArray();
            List<Long> groupIds = new ArrayList<>();
            groupIds.add(groupId);
            Date maximumDate = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).parse(maxDate);
            List<GroupActivity> groupActivities = GroupActivityLocalServiceUtil.getGroupsActivities(user.getUserId(), groupIds, maximumDate, nbResults, allHistory, containNews, containDocs, containMembership, containPendingFirings, containFirings, containHomework, containSessions);
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

    @JSONWebService(value = "get-group-history", method = "GET")
    public JSONObject getGroupHistory(long groupId, String maxDate, int nbResults) {

        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put(JSONConstants.SUCCESS, false);

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
                return result;
            }
            logger.info("User " + user.getFullName() + " fetches activity of group " + groupId);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        try {
            JSONArray jsonActivities = JSONFactoryUtil.createJSONArray();
            List<Long> groupIds = new ArrayList<>();
            groupIds.add(groupId);
            Date maximumDate = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).parse(maxDate);
            List<GroupActivity> groupActivities = GroupActivityLocalServiceUtil.getGroupsHistory(user.getUserId(), groupIds, maximumDate, nbResults);
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
