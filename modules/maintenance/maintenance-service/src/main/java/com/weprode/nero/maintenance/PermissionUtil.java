package com.weprode.nero.maintenance;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;

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
        List<Organization> schools = OrgUtilsLocalServiceUtil.getAllSchools();
        for (Organization school : schools) {
            List<Integer> types = new ArrayList<>();
            types.add(OrgConstants.SUBJECT_TYPE);

            List<Organization> orgs = OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, false);
            for (Organization org : orgs) {
                logger.info("Processing org " + org.getName());
                try {
                    Folder rootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(org.getGroupId());
                    recursiveFolder(rootFolder);
                } catch (Exception e) {
                    logger.error("Error processing permissions for org " + org.getOrganizationId(), e);
                }
            }

        }
        logger.info("END Permissions");
    }

    private static void recursiveFolder(Folder folder) throws PortalException {
        logger.info(" > folder " + folder.getName());
        // Loop over permission roles
        for (String roleName : PermissionUtilsLocalServiceUtil.getPermissionRoles(folder.getGroupId())) {
            Role role = RoleUtilsLocalServiceUtil.getRole(roleName);
            if (PermissionUtilsLocalServiceUtil.hasRoleFolderPermission(role, folder, "ADD_OBJECT")) {
                PermissionUtilsLocalServiceUtil.addDefaultPermissionsFolder(folder);
            }
        }
        List<Folder> subFolders = DLAppServiceUtil.getFolders(folder.getGroupId(), folder.getFolderId());
        for (Folder subFolder: subFolders) {
            recursiveFolder(subFolder);
        }

    }

}
