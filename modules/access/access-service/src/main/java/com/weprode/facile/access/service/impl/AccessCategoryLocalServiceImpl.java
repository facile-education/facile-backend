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

package com.weprode.facile.access.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.access.AccessConstants;
import com.weprode.facile.access.model.AccessCategory;
import com.weprode.facile.access.service.AccessCategoryLocalServiceUtil;
import com.weprode.facile.access.service.AccessLocalServiceUtil;
import com.weprode.facile.access.service.base.AccessCategoryLocalServiceBaseImpl;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.facile.access.model.AccessCategory",
	service = AopService.class
)
public class AccessCategoryLocalServiceImpl extends AccessCategoryLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(AccessCategoryLocalServiceImpl.class);

	public AccessCategory addCategory(long schoolId, String categoryName, int position) {
		AccessCategory category = accessCategoryPersistence.create(counterLocalService.increment());
		category.setCategoryName(categoryName);
		category.setSchoolId(schoolId);
		category.setPosition(position);
		logger.info("Added category " + categoryName + " for schoolId " + schoolId + " in position " + position);
		return accessCategoryPersistence.update(category);
	}

	public AccessCategory updateCategory(long categoryId, String name) throws PortalException {
		AccessCategory category = AccessCategoryLocalServiceUtil.getAccessCategory(categoryId);
		category.setCategoryName(name);

		return AccessCategoryLocalServiceUtil.updateAccessCategory(category);
	}

	public List<AccessCategory> getSchoolCategories(long schoolId) {
		return accessCategoryPersistence.findByschoolId(schoolId);
	}

	public JSONObject convertToJson(AccessCategory category) {
		JSONObject jsonCategory = new JSONObject();
		jsonCategory.put(AccessConstants.CATEGORY_ID, category.getCategoryId());
		jsonCategory.put(AccessConstants.CATEGORY_NAME, category.getCategoryName());
		jsonCategory.put(AccessConstants.POSITION, category.getPosition());
		return jsonCategory;
	}

	public void removeCategory(long categoryId, long schoolId) throws PortalException {
		AccessCategory accessCategory = AccessCategoryLocalServiceUtil.getAccessCategory(categoryId);
		int oldPosition = accessCategory.getPosition();
		AccessLocalServiceUtil.removeByCategoryId(categoryId);
		AccessCategoryLocalServiceUtil.deleteAccessCategory(categoryId);

		for (AccessCategory category : getSchoolCategories(schoolId)) {
			if (category.getPosition() > oldPosition) {
				// Access has been move to another folder -> so move up all the following folders
				category.setPosition(category.getPosition() - 1);
				AccessCategoryLocalServiceUtil.updateAccessCategory(accessCategory);
			}
		}
	}

	public void removeBySchoolId(long schoolId) {
		// First remove all accesses inside this category
		List<AccessCategory> categories = accessCategoryPersistence.findByschoolId(schoolId);
		if (categories != null) {
			for (AccessCategory category : categories) {
				AccessLocalServiceUtil.removeByCategoryId(category.getCategoryId());
			}
		}
		accessCategoryPersistence.removeByschoolId(schoolId);
	}

}