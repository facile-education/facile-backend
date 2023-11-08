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
import com.weprode.facile.user.model.UserRelationship;
import com.weprode.facile.user.service.base.UserRelationshipLocalServiceBaseImpl;
import com.weprode.facile.user.service.persistence.UserRelationshipPK;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.user.model.UserRelationship",
        service = AopService.class
)
public class UserRelationshipLocalServiceImpl extends UserRelationshipLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(UserRelationshipLocalServiceImpl.class);

    public UserRelationship createUserRelationship(long childUserId, long parentUserId) {
        UserRelationship uRelation = null;

        UserRelationshipPK pk = new UserRelationshipPK(childUserId, parentUserId);
        try {
            uRelation = userRelationshipPersistence.fetchByPrimaryKey(pk);
        } catch (Exception e) {
            logger.debug(e);
        }

        if (uRelation != null) {
            logger.info("Found existing relationship between " + childUserId + " and " + parentUserId);
            return uRelation;
        } else {
            try {
                uRelation = userRelationshipPersistence.create(pk);
                uRelation = userRelationshipPersistence.update(uRelation);
                logger.info("Created new relationship between " + childUserId + " and " + parentUserId);
                return uRelation;
            } catch (Exception e) {
                logger.error("Error creating child/parent relationship between " + childUserId +" and " + parentUserId, e);
            }
        }

        return null;
    }

    public List<User> getChildren(long parentId) {
        List<User> childrenList = new ArrayList<>();

        try {
            List<UserRelationship> relations = userRelationshipPersistence.findByparentUserId(parentId);
            for (UserRelationship relation : relations) {
                if (relation.getChildUserId() != 0) {
                    User child = UserLocalServiceUtil.getUser(relation.getChildUserId());
                    if (child.isActive()) {
                        childrenList.add(child);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error getting children for user " + parentId, e);
        }

        return childrenList;
    }

    public List<User> getParents(long childId) {
        List<User> parents = new ArrayList<>();

        try {
            List<UserRelationship> relationships =  userRelationshipPersistence.findBychildUserId(childId);
            if (relationships != null) {
                for (UserRelationship relationship : relationships){
                    User parent = UserLocalServiceUtil.getUser(relationship.getParentUserId());
                    if (parent.isActive()) {
                        parents.add(parent);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error getting parents for user " + childId, e);
        }

        return parents;
    }

    // Return all relatives of the user's children
    public List<User> getAllRelatives(long parentId) {

        List<User> relatives = new ArrayList<>();
        try {
            List<User> userChildren = getChildren(parentId);
            for (User userChild : userChildren) {
                if (userChild.isActive()) {
                    List<User> childParents = getParents(userChild.getUserId());
                    for (User childParent : childParents) {
                        if (childParent.isActive() && !relatives.contains(childParent)) {
                            relatives.add(childParent);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error getting all relatives for parent " + parentId, e);
        }
        return relatives;
    }

    public boolean isChild(long parentId, long childId) {
        List<User> userChildren = getChildren(parentId);
        for (User userChild : userChildren) {
            if (userChild.getUserId() == childId) {
                return true;
            }
        }
        return false;
    }

    public void deleteChild(long childId) {
        try {
            userRelationshipPersistence.removeBychildUserId(childId);
        } catch (Exception e) {
            logger.error("Error deleting child " + childId, e);
        }
    }

    public void deleteParent(long parentId) {
        try {
            userRelationshipPersistence.removeByparentUserId(parentId);
        } catch (Exception e) {
            logger.error("Error deleting parent " + parentId, e);
        }
    }
    
}
