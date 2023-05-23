/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.weprode.nero.organization.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.organization.model.ClassCoursMapping;
import com.weprode.nero.organization.service.base.ClassCoursMappingLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cedric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.nero.organization.model.ClassCoursMapping",
	service = AopService.class
)
public class ClassCoursMappingLocalServiceImpl extends ClassCoursMappingLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ClassCoursMappingLocalServiceImpl.class);

	public ClassCoursMapping addClassCoursMapping(long classOrgId, long coursOrgId) {

		// Check if mapping already exists
		ClassCoursMapping classCoursMapping = null;
		try {
			List<ClassCoursMapping> mappings = classCoursMappingPersistence.findByclassOrgId_CoursOrgId(classOrgId, coursOrgId);
			if (mappings != null && mappings.size() > 0) {
				classCoursMapping = mappings.get(0);
			}
		} catch (Exception e) {
			// Nothing
		}

		if (classCoursMapping == null) {
			classCoursMapping = classCoursMappingPersistence.create(counterLocalService.increment());
			classCoursMapping.setClassOrgId(classOrgId);
			classCoursMapping.setCoursOrgId(coursOrgId);
			return classCoursMappingPersistence.update(classCoursMapping);
		} else {
			return classCoursMapping;
		}
	}

	public void updateClassCoursMapping(long coursOrgId, List<Long> classOrgIds) {

		// Get existing mappings
		List<ClassCoursMapping> existingMappings = classCoursMappingPersistence.findBycoursOrgId(coursOrgId);
		List<Long> existingClassOrgIds = new ArrayList<>();
		for (ClassCoursMapping existingMapping : existingMappings) {
			existingClassOrgIds.add(existingMapping.getClassOrgId());
		}

		boolean deleteAndCreate = false;

		// If number of existing mapping should not exist anymore -> delete all and recreate
		if (existingMappings != null) {
			for (ClassCoursMapping existingMapping : existingMappings) {
				if (!classOrgIds.contains(existingMapping.getClassOrgId())) {
					deleteAndCreate = true;
					break;
				}
			}
		}

		// If new mapping does not exist -> delete all and recreate
		for (Long classOrgId : classOrgIds) {
			if (!existingClassOrgIds.contains(classOrgId)) {
				deleteAndCreate = true;
				break;
			}
		}

		if (deleteAndCreate) {
			classCoursMappingPersistence.removeBycoursOrgId(coursOrgId);
			for (Long classOrgId : classOrgIds) {
				addClassCoursMapping(classOrgId, coursOrgId);
			}
			logger.info("Added " + classOrgIds.size() + " new mappings for coursOrgId " + coursOrgId);
		}
	}

	public List<Long> getClassCours(long classOrgId) {
		List<Long> coursOrgIds = new ArrayList<>();
		try {
			List<ClassCoursMapping> mappings =  classCoursMappingPersistence.findByclassOrgId(classOrgId);
			if (mappings != null) {
				for (ClassCoursMapping mapping : mappings) {
					coursOrgIds.add(mapping.getCoursOrgId());
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching cours groupIds from classOrgId " + classOrgId);
		}
		return coursOrgIds;
	}

}