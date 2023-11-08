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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.weprode.facile.organization.model.OrgCiteScolaire;
import com.weprode.facile.organization.service.OrgCiteScolaireLocalServiceUtil;
import com.weprode.facile.organization.service.OrgMappingLocalServiceUtil;
import com.weprode.facile.organization.service.base.OrgCiteScolaireLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.organization.model.OrgCiteScolaire",
        service = AopService.class
)
public class OrgCiteScolaireLocalServiceImpl extends OrgCiteScolaireLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(OrgCiteScolaireLocalServiceImpl.class);

    public OrgCiteScolaire addOrgCiteScolaire(String parentEntStructureUAI, String childEntStructureUAI) throws SystemException {

        OrgCiteScolaire orgaCiteScolaire = OrgCiteScolaireLocalServiceUtil.fetchOrgCiteScolaire(childEntStructureUAI);

        if (orgaCiteScolaire == null) {
            orgaCiteScolaire = OrgCiteScolaireLocalServiceUtil.createOrgCiteScolaire(childEntStructureUAI);
            orgaCiteScolaire.setParentENTStructureUAI(parentEntStructureUAI);
            OrgCiteScolaireLocalServiceUtil.addOrgCiteScolaire(orgaCiteScolaire);
        }

        return orgaCiteScolaire;
    }

    public List<Organization> getSchoolComplexSchools(Organization school) {

        List<Organization> schoolList = new ArrayList<>();

        String schoolUai = OrgMappingLocalServiceUtil.getOrganizationStrutUAI(school);
        List<String> linkedSchoolUAIList = new ArrayList<>();
        linkedSchoolUAIList.add(schoolUai);

        // Find with the structure as parent
        linkedSchoolUAIList.addAll(getChildUais(schoolUai));

        // Find with the structure as child
        OrgCiteScolaire schoolComplex;
        try {
            schoolComplex =  orgCiteScolairePersistence.fetchByPrimaryKey(schoolUai);
            // If the school is a child, then get the parent and the children of this parent
            if (schoolComplex != null) {
                // Add the parent if not already set
                String parentSchoolUai = schoolComplex.getParentENTStructureUAI();
                if (!linkedSchoolUAIList.contains(parentSchoolUai)){
                    linkedSchoolUAIList.add(parentSchoolUai);
                }

                // Get all children of the parent
                linkedSchoolUAIList.addAll(getChildUais(parentSchoolUai));
            }
        } catch (Exception e) {
            logger.error("Error fetching school complex from school as a child", e);
        }

        // Return the school organizations
        for (String linkedSchoolUAI : linkedSchoolUAIList) {
            Organization linkedSchool = OrgMappingLocalServiceUtil.getSchoolFromUai(linkedSchoolUAI);
            if (linkedSchool != null) {
                schoolList.add(linkedSchool);
            }
        }

        return schoolList;
    }

    private List<String> getChildUais(String parentUai) {
        List<String> childUAIList = new ArrayList<>();

        try {
            List<OrgCiteScolaire> schoolComplexList = orgCiteScolairePersistence.findByparentENTStructureUAI(parentUai);
            if (schoolComplexList != null) {
                for (OrgCiteScolaire schoolComplex : schoolComplexList) {
                    childUAIList.add(schoolComplex.getChildENTStructureUAI());
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching school complex from school as a parent", e);
        }

        return childUAIList;
    }
}
