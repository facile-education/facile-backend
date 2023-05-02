package com.weprode.nero.help.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.weprode.nero.help.model.HelpItemRole;
import com.weprode.nero.help.service.base.HelpItemRoleLocalServiceBaseImpl;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.help.model.HelpItemRole",
        service = AopService.class
)
public class HelpItemRoleLocalServiceImpl extends HelpItemRoleLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(HelpItemRoleLocalServiceImpl.class);

    public boolean addItemRole(long itemId, long roleId) {
        try {
            HelpItemRole helpItemRole = helpItemRolePersistence.create(counterLocalService.increment());
            helpItemRole.setItemId(itemId);
            helpItemRole.setRoleId(roleId);
            helpItemRolePersistence.updateImpl(helpItemRole);

            return true;
        } catch (Exception e) {
            logger.error("Error adding role " + roleId + " to item " + itemId, e);
        }

        return false;
    }

    public boolean isUserAllowedToSeeItem(User user, long itemId) {
        try {
            if (RoleUtilsLocalServiceUtil.isAdministrator(user))  {
                return true;
            }

            List<HelpItemRole> helpItemRoles = helpItemRolePersistence.findByitemId(itemId);
            if (helpItemRoles == null || helpItemRoles.isEmpty()) {
                // No role means allowed
                return true;
            } else {
                for (HelpItemRole helpItemRole : helpItemRoles) {
                    // First role matching -> is allowed
                    if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), helpItemRole.getRoleId())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error checking if user " + user.getUserId() + " is allowed to see help item " + itemId, e);
        }

        return false;
    }
}
