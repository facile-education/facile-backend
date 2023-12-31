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

package com.weprode.facile.organization.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.model.OrgDetails;
import com.weprode.facile.organization.service.base.OrgDetailsLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the organization details local service.
 *
 * @author Cedric Lecarpentier
 */
@Component(
        property = "model.class.name=com.weprode.facile.organization.model.OrgDetails",
        service = AopService.class
)
public class OrgDetailsLocalServiceImpl extends OrgDetailsLocalServiceBaseImpl {
    private static final Log logger = LogFactoryUtil.getLog(OrgDetailsLocalServiceImpl.class);

    public OrgDetails createOrgDetails(long orgId, long schoolId, String orgName, int type) {
        OrgDetails orgDetails = null;

        try {
            orgDetails = orgDetailsPersistence.fetchByPrimaryKey(orgId);
            if (orgDetails == null) {
                orgDetails = orgDetailsPersistence.create(orgId);
                orgDetails.setSchoolId(schoolId);
                orgDetails.setOrgName(orgName);
                orgDetails.setType(type);
                orgDetails.setIsArchive(false);
                orgDetails = orgDetailsPersistence.update(orgDetails);
            }
        } catch (Exception e) {
            logger.error("Error when creating organization detail");
        }

        return orgDetails;
    }

    public boolean isArchived(long orgId) {
        try {
            OrgDetails orgDetails = orgDetailsPersistence.fetchByPrimaryKey(orgId);
            if (orgDetails == null) {
                return true;
            } else {
                return orgDetails.isIsArchive();
            }
        } catch (Exception e) {
            logger.error("Error when getting if organization "+orgId+" is archived");
        }
        return true;
    }

    public boolean hasType(long orgId, int type) {
        try {
            OrgDetails orgDetails = orgDetailsPersistence.fetchByPrimaryKey(orgId);
            if (orgDetails != null) {
                return orgDetails.getType() == type;
            }
        } catch (Exception e) {
            logger.error("Error when getting if organization "+orgId+" has type "+type + e.getMessage());
        }
        return false;
    }

    public boolean isSchool(long orgId) {
        try {
            OrgDetails orgDetails = orgDetailsPersistence.fetchByPrimaryKey(orgId);
            if (orgDetails != null) {
                return orgDetails.getType() == OrgConstants.SCHOOL_TYPE;
            }
        } catch (Exception e) {
            logger.error("Error when getting if organization "+orgId+" is a school");
        }

        return false;
    }

    public boolean isVolee(long orgId) {
        try {
            OrgDetails orgDetails = orgDetailsPersistence.fetchByPrimaryKey(orgId);
            if (orgDetails != null) {
                return orgDetails.getType() == OrgConstants.VOLEE_TYPE;
            }
        } catch (Exception e) {
            logger.error("Error when getting if organization "+orgId+" is a volee");
        }

        return false;
    }

    public boolean isCours(long orgId) {
        try {
            OrgDetails orgDetails = orgDetailsPersistence.fetchByPrimaryKey(orgId);
            if (orgDetails != null) {
                return orgDetails.getType() == OrgConstants.COURS_TYPE;
            }
        } catch (Exception e) {
            logger.error("Error when getting if organization "+orgId+" is a cours");
        }

        return false;
    }

    public boolean isClass(long orgId) {
        try {
            OrgDetails orgDetails = orgDetailsPersistence.fetchByPrimaryKey(orgId);
            if (orgDetails != null) {
                return orgDetails.getType() == OrgConstants.CLASS_TYPE;
            }
        } catch (Exception e) {
            logger.error("Error when getting if organization "+orgId+" is a class");
        }

        return false;
    }

    public boolean isSubject(long orgId) {
        try {
            OrgDetails orgDetails = orgDetailsPersistence.fetchByPrimaryKey(orgId);
            if (orgDetails != null) {
                return orgDetails.getType() == OrgConstants.SUBJECT_TYPE;
            }
        } catch (Exception e) {
            logger.error("Error when getting if organization "+orgId+" is a subject");
        }

        return false;
    }
}
