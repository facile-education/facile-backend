package com.weprode.nero.maintenance;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {

    private static final Log logger = LogFactoryUtil.getLog(PermissionUtil.class);

    public static void addPermissions() {

        logger.info("START Permissions");

        // Do as administrator, to get rid of permission issues
        List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(RoleUtilsLocalServiceUtil.getAdministratorRole().getRoleId());
        PrincipalThreadLocal.setName(adminUsers.get(0).getUserId());
        PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(adminUsers.get(0));
        PermissionThreadLocal.setPermissionChecker(permissionChecker);

        // Loop over impacted orgs : school and subject
        // This was run after migration (August 7th 2023)
//        List<Organization> schools = OrgUtilsLocalServiceUtil.getAllSchools();
//        for (Organization school : schools) {
//            List<Integer> types = new ArrayList<>();
//            types.add(OrgConstants.SUBJECT_TYPE);
//
//            List<Organization> orgs = OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, false);
//            for (Organization org : orgs) {
//                logger.info("Processing org " + org.getName());
//                try {
//                    Folder rootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(org.getGroupId());
//                    recursiveFolder(rootFolder);
//                } catch (Exception e) {
//                    logger.error("Error processing permissions for org " + org.getOrganizationId(), e);
//                }
//            }
//
//        }

        // Loop over communities
        List<Organization> schools = OrgUtilsLocalServiceUtil.getAllSchools();
        for (Organization school : schools) {
            // Get school director
            List<Long> orgIds = new ArrayList<>();
            orgIds.add(school.getOrganizationId());
            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());
            List<User> directors = UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
            User director = directors.get(0);

            List<Group> communities = CommunityInfosLocalServiceUtil.getSchoolCommunities(school.getOrganizationId(), false, true);
            for (Group community : communities) {
                logger.info("Processing community " + community.getName());
                try {
                    Folder rootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(community.getGroupId());
                    recursiveFolder(director, rootFolder);
                } catch (Exception e) {
                    logger.error("Error processing permissions for community " + community.getGroupId(), e);
                }
            }
        }
        logger.info("END Permissions");
    }

    private static void recursiveFolder(User director, Folder folder) throws PortalException {
        logger.info(" > folder " + folder.getName());
        JSONArray permissionMatrix = new JSONArray();

        // Build the role list
        List<String> roleNames = PermissionUtilsLocalServiceUtil.getPermissionRoles(folder.getGroupId());
        // Available actions for DLFileEntry
        List<ResourceAction> dLFolderResourceActions = ResourceActionLocalServiceUtil.getResourceActions(DLFolder.class.getName());

        for (String roleName : roleNames) {
            Role role = RoleLocalServiceUtil.getRole(folder.getCompanyId(), roleName);

            boolean areRoleActionsEditable = !(role.getRoleId() == RoleUtilsLocalServiceUtil.getCommunityAdministratorRole().getRoleId() ||
                    role.getRoleId() == RoleUtilsLocalServiceUtil.getCommunityOwnerRole().getRoleId() ||
                    role.getRoleId() == RoleUtilsLocalServiceUtil.getSchoolAdminRole().getRoleId() ||
                    role.getRoleId() == RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());

            JSONObject roleActions = new JSONObject();
            roleActions.put(JSONConstants.ROLE_ID, role.getRoleId());
            roleActions.put(JSONConstants.ROLE_NAME, role.getName());
            roleActions.put(JSONConstants.EDITABLE, areRoleActionsEditable);

            for (ResourceAction resourceAction : dLFolderResourceActions) {
                roleActions.put(resourceAction.getActionId(), PermissionUtilsLocalServiceUtil.hasRoleFolderPermission(role, folder, resourceAction.getActionId()));
            }

            permissionMatrix.put(roleActions);
        }

        PermissionUtilsLocalServiceUtil.validateFullPermission(director, folder.getFolderId(), "folder", permissionMatrix, false, folder.getGroupId());

    }

}
