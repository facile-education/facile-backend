package com.weprode.nero.schedule.utils;

import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;

import java.util.ArrayList;
import java.util.List;

public class FilterUtil {

    private FilterUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(FilterUtil.class);

    public static JSONArray getGroupsForFilter(User teacher) {
        JSONArray schoolsArray = new JSONArray();

        List<Organization> userOrganizations = UserOrgsLocalServiceUtil.getUserClassesAndCours(teacher, false);
        List<Organization> userSchools = UserOrgsLocalServiceUtil.getUserSchools(teacher);
        List<Group> personalGroups = CommunityInfosLocalServiceUtil.getUserCommunities(teacher.getUserId(), true, true);

        // Loop over schools
        for (Organization school: userSchools){

            JSONObject schoolObject = new JSONObject();
            schoolObject.put(JSONConstants.SCHOOL_ID, school.getGroupId());
            schoolObject.put(JSONConstants.SCHOOL_NAME, OrgUtilsLocalServiceUtil.formatOrgName(school.getName(), true));

            // Loop over classes
            JSONArray groupsArray = new JSONArray();
            for (Organization org : userOrganizations){
                if (org.getParentOrganizationId() == school.getOrganizationId()) {
                    JSONObject classObject = new JSONObject();
                    classObject.put(JSONConstants.GROUP_ID, org.getGroupId());
                    classObject.put(JSONConstants.GROUP_NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false));
                    groupsArray.put(classObject);
                }
            }
            schoolObject.put(JSONConstants.GROUPS, groupsArray);
            schoolsArray.put(schoolObject);
        }

        // Loop over personal groups
        if (!personalGroups.isEmpty()) {
            JSONObject schoolObject = new JSONObject();
            schoolObject.put(JSONConstants.SCHOOL_NAME, "Personnels");
            JSONArray groupsArray = new JSONArray();
            for (Group group : personalGroups) {
                JSONObject groupObject = new JSONObject();
                groupObject.put(JSONConstants.GROUP_ID, group.getGroupId());
                groupObject.put(JSONConstants.GROUP_NAME, group.getName());
                groupObject.put(JSONConstants.IS_PERSONAL_GROUP, true);
                groupsArray.put(groupObject);
            }
            schoolObject.put(JSONConstants.GROUPS, groupsArray);
            schoolsArray.put(schoolObject);
        }
        
        return schoolsArray;
    }

    public static List<CDTSession> filterSessionsOnVolee(List<CDTSession> sessionList, String volee) {
        List<CDTSession> filteredSessionList = new ArrayList<>();
        
        if (sessionList != null) {
            for (CDTSession session : sessionList) {

                try {
                    // Keep sessions on personal groups
                    Group sessionGroup = GroupLocalServiceUtil.getGroup(session.getGroupId());
                    Organization sessionOrg = OrganizationLocalServiceUtil.fetchOrganization(sessionGroup.getClassPK());
                    if (sessionOrg == null) {
                        filteredSessionList.add(session);
                        continue;
                    }

                    // Keep manually created sessions because volee does not exist
                    if (session.getIsManual()) {
                        filteredSessionList.add(session);
                        continue;
                    }

                    // Session's title is formatted like MA1034S or MA.1034
                    // Volee is the 3rd and 4th characters or the 4th and 5th
                    if (session.getTitle().length() >= 4) {
                        String sessionVolee = session.getTitle().substring(2, 4);
                        String sessionVolee2 = session.getTitle().substring(3, 5);
                        if (sessionVolee.equals(volee) || sessionVolee2.equals(volee)) {
                            filteredSessionList.add(session);
                        }
                    }

                } catch (Exception e) {
                    logger.error("Error while filtering sessions on volee " + volee, e);
                }
            }
        }
        
        return filteredSessionList;
    }
    
}
