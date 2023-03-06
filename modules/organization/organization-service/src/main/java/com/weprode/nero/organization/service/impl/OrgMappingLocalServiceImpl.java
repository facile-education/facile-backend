package com.weprode.nero.organization.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.weprode.nero.organization.exception.NoSuchOrgMappingException;
import com.weprode.nero.organization.model.OrgMapping;
import com.weprode.nero.organization.service.base.OrgMappingLocalServiceBaseImpl;
import com.weprode.nero.organization.service.persistence.OrgMappingUtil;

public class OrgMappingLocalServiceImpl extends OrgMappingLocalServiceBaseImpl {
    
    private static final Log logger = LogFactoryUtil.getLog(OrgMappingLocalServiceImpl.class);

    public OrgMapping addOrgMapping(Organization organization, String uai) throws SystemException {
        if (uai.equals("")) {
            return OrgMappingUtil.fetchByorganisationId(organization.getOrganizationId());
        }

        long orgID = organization.getOrganizationId();

        OrgMapping orgMapping = orgMappingLocalService.fetchOrgMapping(uai);
        if (orgMapping == null) {
            orgMapping = orgMappingLocalService.createOrgMapping(uai);
            orgMapping.setOrganizationId(orgID);
            orgMappingPersistence.update(orgMapping);
        }

        return orgMapping;
    }

    /**
     * Returns the structure UAI from the organization if it exist.
     */
    public String getOrganizationStrutUAI(Organization organization){
        try {
            OrgMapping orgMapping = OrgMappingUtil.fetchByorganisationId(organization.getOrganizationId());
            if (orgMapping == null) {
                return null;
            }
            return orgMapping.getEntStructureUAI();

        } catch (Exception e) {
            logger.error("Error getting UAI for organization", e);
        }

        return null;
    }

    public Organization getSchoolFromUai(String uai) {
        try {
            OrgMapping orgMapping = OrgMappingUtil.fetchByPrimaryKey(uai);
            if (orgMapping == null) {
                return null;
            }
            return OrganizationLocalServiceUtil.getOrganization(orgMapping.getOrganizationId());

        } catch (Exception e) {
            logger.error("Error getting school for UAI " + uai, e);
        }

        return null;
    }

    public void deleteOrgMapping(long organizationId) throws NoSuchOrgMappingException {
        orgMappingPersistence.removeByorganisationId(organizationId);
    }
}
