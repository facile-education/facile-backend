package com.weprode.nero.organization.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.model.OrgDetails;
import com.weprode.nero.organization.service.base.OrgDetailsLocalServiceBaseImpl;

/**
 * The implementation of the organization details local service.
 *
 * @author Cedric Lecarpentier
 */
public class OrgDetailsLocalServiceImpl extends OrgDetailsLocalServiceBaseImpl {
    private static final Log logger = LogFactoryUtil.getLog(OrgDetailsLocalServiceImpl.class);

    public OrgDetails createOrgDetails(long orgId, long schoolId, String orgName, String eduLevel, int role,
                                       int type, boolean isArchive) {
        OrgDetails orgDetails = null;

        try {
            orgDetails = orgDetailsPersistence.fetchByPrimaryKey(orgId);
            if (orgDetails == null) {
                orgDetails = orgDetailsPersistence.create(orgId);
                orgDetails.setSchoolId(schoolId);
                orgDetails.setOrgName(orgName);
                orgDetails.setEduLevel(eduLevel);
                orgDetails.setRole(role);
                orgDetails.setType(type);
                orgDetails.setIsArchive(isArchive);
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
