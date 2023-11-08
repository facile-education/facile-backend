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

package com.weprode.facile.user.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.user.model.LDAPMapping;
import com.weprode.facile.user.service.LDAPMappingLocalServiceUtil;
import com.weprode.facile.user.service.base.LDAPMappingLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.user.model.LDAPMapping",
        service = AopService.class
)
public class LDAPMappingLocalServiceImpl extends LDAPMappingLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(LDAPMappingLocalServiceImpl.class);

    public User getUserFromUID(String uid) {
        if (uid != null && !uid.isEmpty()) {
            try {
                return UserLocalServiceUtil.getUser(ldapMappingPersistence.fetchByUID(uid).getUserId());
            } catch (Exception e) {
                logger.debug(e);
            }
        }

        return null;
    }

    public Long getMaxUid() {
        return ldapMappingFinder.findMaxUid();
    }

    public String getUserUid(long userId) {

        try {
            LDAPMapping ldapMapping = ldapMappingPersistence.findByPrimaryKey(userId);
            return ldapMapping.getUID();
        } catch (Exception e) {
            logger.debug(e);
        }

        return "";
    }

    public String getUserJointure(long userId) {
        try {
            LDAPMapping ldapMapping = ldapMappingPersistence.findByPrimaryKey(userId);
            return ldapMapping.getEntPersonJointure();
        } catch (Exception e) {
            logger.debug(e);
        }

        return "";

    }

    public String getUserStructRattachId(long userId) {

        try {
            LDAPMapping ldapMapping = ldapMappingPersistence.findByPrimaryKey(userId);
            return ldapMapping.getEntEleveStructRattachId();
        } catch (Exception e) {
            logger.debug(e);
        }

        return "";
    }

    public User getUserByEntEleveStructRattachId(String entEleveStructRattachId) {
        try {
            User matchingUser = null;
            List<LDAPMapping> ldapMappings = ldapMappingPersistence.findByEntEleveStructRattachId(entEleveStructRattachId);

            if (ldapMappings != null) {
                int size = ldapMappings.size();

                for (LDAPMapping ldapMapping : ldapMappings) {
                    User user = UserLocalServiceUtil.getUser(ldapMapping.getUserId());
                    if (!user.isActive()) {
                        // Delete this mapping if more than one, because of INE-bug that recreates AAF accounts
                        if (size > 1) {
                            LDAPMappingLocalServiceUtil.deleteLDAPMapping(ldapMapping);
                        }
                    } else {
                        matchingUser = user;
                    }
                }
            }

            return matchingUser;
        } catch (Exception e) {
            logger.debug(e);
        }

        return null;
    }

    public User getUserByEntPersonJointure(String entPersonJointure) {
        try {
            LDAPMapping ldapMapping = ldapMappingPersistence.findByEntPersonJointure(entPersonJointure);

            return UserLocalServiceUtil.getUser(ldapMapping.getUserId());
        } catch (Exception e) {
            logger.debug(e);
        }

        return null;
    }
}
