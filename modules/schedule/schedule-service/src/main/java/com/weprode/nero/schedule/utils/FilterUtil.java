package com.weprode.nero.schedule.utils;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class FilterUtil {

    private FilterUtil() {
        throw new IllegalStateException("Utility class");
    }

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

}
