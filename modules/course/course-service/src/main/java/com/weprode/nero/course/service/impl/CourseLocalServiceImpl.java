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

package com.weprode.nero.course.service.impl;

import com.liferay.portal.aop.AopService;
import com.weprode.nero.course.service.base.CourseLocalServiceBaseImpl;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.nero.course.model.Course",
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