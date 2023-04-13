package com.weprode.nero.application.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.application.exception.NoSuchDefaultRoleException;
import com.weprode.nero.application.model.DefaultRole;
import com.weprode.nero.application.service.base.DefaultRoleLocalServiceBaseImpl;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.constants.NeroRoleConstants;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.application.model.DefaultRole",
        service = AopService.class
)
public class DefaultRoleLocalServiceImpl extends DefaultRoleLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(DefaultRoleLocalServiceImpl.class);

    public boolean addDefaultRole(long roleId, long applicationId) throws SystemException {
        final long defaultRoleId = counterLocalService.increment();

        DefaultRole defaultRole = defaultRolePersistence.create(defaultRoleId);
        defaultRole.setRoleId(roleId);
        defaultRole.setApplicationId(applicationId);
        defaultRolePersistence.update(defaultRole);

        return true;
    }

    public JSONArray getDefaultRoleJson(long applicationId) {
        try {
            long companyId = PortalUtil.getDefaultCompanyId();

            List<DefaultRole> defaultRoles = defaultRolePersistence.findByapplicationId(applicationId);
            JSONArray rolesTab = JSONFactoryUtil.createJSONArray();
            for (DefaultRole defaultRole : defaultRoles) {
                JSONObject roleJson = JSONFactoryUtil.createJSONObject();
                if (defaultRole.getRoleId() == 0) {
                    roleJson.put(JSONConstants.DISPLAY_TEXT, "Tous les profils");
                    roleJson.put(JSONConstants.ROLE_ID, 0);
                    roleJson.put(JSONConstants.IS_FOR_CLASS, false);
                    rolesTab.put(roleJson);
                } else {
                    Role role = RoleLocalServiceUtil.getRole(defaultRole.getRoleId());
                    String displayText = LanguageUtil.get(UserLocalServiceUtil.getDefaultUser(companyId).getLocale(), role.getName());
                    roleJson.put(JSONConstants.ROLE_ID, defaultRole.getRoleId());
                    roleJson.put(JSONConstants.DISPLAY_TEXT, displayText);
                    roleJson.put(JSONConstants.IS_FOR_CLASS, isForClass(role.getName()));
                    rolesTab.put(roleJson);
                }
            }

            return rolesTab;
        } catch (Exception e) {
            logger.error("Error fetching default roles for applicationId " + applicationId, e);
        }

        return JSONFactoryUtil.createJSONArray();
    }

    public boolean hasUserRole(long applicationId, long userId) {
        try {
            List<DefaultRole> defaultRoles = defaultRolePersistence.findByapplicationId(applicationId);

            if (defaultRoles.isEmpty()) {
                return true;
            } else {
                for (DefaultRole defaultRole : defaultRoles) {
                    if (RoleLocalServiceUtil.hasUserRole(userId, defaultRole.getRoleId())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching is user " + userId + " has default roles for applicationId " + applicationId, e);
        }

        return false;
    }

    public boolean deleteDefaultRole(long roleId, long applicationId) throws NoSuchDefaultRoleException, SystemException {
        defaultRolePersistence.removeByapplicationId_roleId(applicationId, roleId);

        return true;
    }

    public boolean deleteDefaultRoleByApplicationId(long applicationId) throws SystemException {
        defaultRolePersistence.removeByapplicationId(applicationId);

        return true;
    }

    private boolean isForClass(String roleName) {
        return roleName.equals(NeroRoleConstants.NATIONAL_1) ||
                roleName.equals(NeroRoleConstants.NATIONAL_2) ||
                roleName.equals(NeroRoleConstants.NATIONAL_3) ||
                roleName.equals(NeroRoleConstants.MAIN_TEACHER);
    }

}
