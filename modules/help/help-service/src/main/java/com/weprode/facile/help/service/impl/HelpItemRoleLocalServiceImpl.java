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

package com.weprode.facile.help.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.weprode.facile.help.model.HelpItemRole;
import com.weprode.facile.help.service.base.HelpItemRoleLocalServiceBaseImpl;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.help.model.HelpItemRole",
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
