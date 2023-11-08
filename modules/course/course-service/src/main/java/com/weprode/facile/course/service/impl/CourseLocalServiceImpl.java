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

package com.weprode.facile.course.service.impl;

import com.liferay.portal.aop.AopService;
import com.weprode.facile.course.service.base.CourseLocalServiceBaseImpl;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.facile.course.model.Course",
	service = AopService.class
)
public class CourseLocalServiceImpl extends CourseLocalServiceBaseImpl {

	public boolean isSessionItem(long itemId) {
		try {
			CDTSessionLocalServiceUtil.getCDTSession(itemId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}