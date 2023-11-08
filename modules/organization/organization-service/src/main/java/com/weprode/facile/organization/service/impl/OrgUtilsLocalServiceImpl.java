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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.RegionConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.exception.NoSuchOrgDetailsException;
import com.weprode.facile.organization.model.OrgDetails;
import com.weprode.facile.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgMappingLocalServiceUtil;
import com.weprode.facile.organization.service.base.OrgUtilsLocalServiceBaseImpl;
import com.weprode.facile.schedule.service.CourseDetailsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component(
        property = "model.class.name=com.weprode.facile.organization.model.OrgUtils",
        service = AopService.class
)
public class OrgUtilsLocalServiceImpl extends OrgUtilsLocalServiceBaseImpl {
    private static final Log logger = LogFactoryUtil.getLog(OrgUtilsLocalServiceImpl.class);

    public List<Organization> getAllSchools() {
        return orgUtilsFinder.findOrganizationByType(OrgConstants.SCHOOL_TYPE, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    public List<Organization> getSchoolClasses(long schoolId, Boolean withArchives) {

        List<Integer> types = new ArrayList<>();
        types.add(OrgConstants.CLASS_TYPE);

        return orgUtilsFinder.findSchoolOrganizations(schoolId, types, withArchives,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    public List<Organization> getSchoolOrganizations(long schoolId, List<Integer> types, Boolean withArchives) {
        return orgUtilsFinder.findSchoolOrganizations(schoolId, types, withArchives,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    public Organization getOrCreateRootOrg(long companyId) throws PortalException, SystemException {
        String orgRootName = PropsUtil.get(NeroSystemProperties.ROOT_ORGANIZATION_NAME);

        try {
            return OrganizationLocalServiceUtil.getOrganization(companyId, orgRootName);
        } catch (NoSuchOrganizationException e) {
            // Do not create org root ?
            long defUserId = UserLocalServiceUtil.getGuestUserId(companyId);
            return OrganizationLocalServiceUtil.addOrganization(UUID.randomUUID().toString(),
                    defUserId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
                    orgRootName, OrganizationConstants.TYPE_ORGANIZATION,
                    RegionConstants.DEFAULT_REGION_ID, 0, ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
                    StringPool.BLANK, true, new ServiceContext());
        }
    }

    public Organization getOrCreateSchool(long companyId, String name) throws PortalException, SystemException {
        return getOrCreateSchool(companyId, name, StringPool.BLANK);
    }

    public Organization getOrCreateSchool(long companyId, String schoolName, String entStructureUAI)
            throws PortalException, SystemException {

        // Replace all ' - ' occurrences in school name by ' -' because ' - ' is the delimiter in the org name
        String formattedSchoolName = schoolName.replace(" - ", " -");

        Organization schoolOrg;
        try {
            schoolOrg = OrganizationLocalServiceUtil.getOrganization(companyId, formattedSchoolName);
        } catch (NoSuchOrganizationException e) {
            long defaultUserId = UserLocalServiceUtil.getGuestUserId(companyId);
            schoolOrg = OrganizationLocalServiceUtil.addOrganization(UUID.randomUUID().toString(),
                    defaultUserId, getOrCreateRootOrg(companyId).getOrganizationId(),
                    formattedSchoolName, OrganizationConstants.TYPE_ORGANIZATION,
                    RegionConstants.DEFAULT_REGION_ID, 0, ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
                    StringPool.BLANK, true, new ServiceContext());
            logger.info("Created school " + formattedSchoolName);
        }

        if (!entStructureUAI.equals("0")){
            OrgMappingLocalServiceUtil.addOrgMapping(schoolOrg, entStructureUAI);
        }

        // Now create OrganizationDetails
        OrgDetailsLocalServiceUtil.createOrgDetails(schoolOrg.getOrganizationId(), schoolOrg.getOrganizationId(),
                schoolOrg.getName(), OrgConstants.SCHOOL_TYPE);

        return schoolOrg;
    }

    public Organization getOrCreateOrganization(long companyId, String orgName, long schoolId, int type) throws PortalException, SystemException {
        Organization org;

        try {
            org = OrganizationLocalServiceUtil.getOrganization(companyId, orgName);
        } catch (NoSuchOrganizationException e) {
            long defaultUserId = UserLocalServiceUtil.getGuestUserId(companyId);
            org = OrganizationLocalServiceUtil.addOrganization(UUID.randomUUID().toString(),
                    defaultUserId, schoolId, orgName, OrganizationConstants.TYPE_ORGANIZATION,
                    RegionConstants.DEFAULT_REGION_ID, 0, ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
                    StringPool.BLANK, true, new ServiceContext());
            logger.info("Created organization " + orgName + " (id " + org.getOrganizationId() + ")");
        }

        // If type is -1, no need to update the OrgDetails
        if (type == -1) {
            return org;
        }

        // Create or update OrgDetails
        try {
            OrgDetails orgDetails = OrgDetailsLocalServiceUtil.getOrgDetails(org.getOrganizationId());
            // Update type if necessary
            if (orgDetails.getType() != type) {
                orgDetails.setType(type);
                OrgDetailsLocalServiceUtil.updateOrgDetails(orgDetails);
            }
        } catch (NoSuchOrgDetailsException e) {
            OrgDetailsLocalServiceUtil.createOrgDetails(org.getOrganizationId(), schoolId, orgName, type);
        }

        return org;
    }

    public String formatOrgName(String name, boolean withSchoolName) {
        if (!name.startsWith("Coll")
                && !name.startsWith("CO")) {
            return name;
        }

        if (name.contains("LFR_ORGANIZATION")) {
            name = name.replace("LFR_ORGANIZATION", "");
            name = name.trim();
        }

        String[] nameSplit = StringUtil.split(name, " - ");

        if (nameSplit.length == 0) {

            return name;
        } else if (nameSplit.length == 1) {

            // This is a school
            return nameSplit[0];
        } else {

            // This is a class or a group
            StringBuilder res = new StringBuilder();

            if (withSchoolName) {
                res = new StringBuilder(nameSplit[0]);
            }

            for (int i=1 ; i<nameSplit.length; i++) {
                res.append(" - ").append(nameSplit[i]);
            }

            if (res.toString().startsWith(" - ")){
                res = new StringBuilder(res.substring(3));
            }

            return res.toString();
        }
    }

    public boolean isVoleeAuthorized(String volee) {

        String authorizedVoleesStr = PropsUtil.get(NeroSystemProperties.SYNCHRO_VOLEES);
        String[] authorizedVoleesTab = authorizedVoleesStr.split(",");
        for (String authorizedVolee : authorizedVoleesTab) {
            if (volee.equals(authorizedVolee)) {
                return true;
            }
        }
        return false;
    }

    public String getOrgColor(User user, Organization org) {

        String color = "";
        try {
            if (OrgDetailsLocalServiceUtil.isClass(org.getOrganizationId())) {
                color = "#D20113";
            } else if (OrgDetailsLocalServiceUtil.isSchool(org.getOrganizationId())) {
                color = "#3CB57D";
            }  else if (OrgDetailsLocalServiceUtil.isSubject(org.getOrganizationId())) {
                color = "#7F00FF";
            } else {
                // Org is a course
                color = CourseDetailsLocalServiceUtil.getCourseColor(org.getGroupId());
            }
        } catch (Exception e) {
            logger.error("Error fetching color for org " + org.getOrganizationId(), e);
        }
        return color;
    }

}
