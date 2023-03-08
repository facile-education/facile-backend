package com.weprode.nero.user.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.user.model.UserRelationship;
import com.weprode.nero.user.service.base.UserRelationshipLocalServiceBaseImpl;
import com.weprode.nero.user.service.persistence.UserRelationshipPK;

import java.util.ArrayList;
import java.util.List;

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
