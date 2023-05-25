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

package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.schedule.model.GroupColor;
import com.weprode.nero.schedule.service.base.GroupColorLocalServiceBaseImpl;
import com.weprode.nero.schedule.utils.CDTColorUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.GroupColor",
	service = AopService.class
)
public class GroupColorLocalServiceImpl extends GroupColorLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(GroupColorLocalServiceImpl.class);

	public String getColor(long groupId) {

		GroupColor groupColor;
		try {
			groupColor = groupColorPersistence.findByPrimaryKey(groupId);
			if (groupColor != null) {
				return groupColor.getColor();
			}
		} catch (Exception e) {
			// Nothing
		}

		groupColor = groupColorPersistence.create(groupId);

		// Get existing used colors count to get a fresh new one from the color pool
		int nbExistingGroupColors = groupColorPersistence.countAll();
		String color = CDTColorUtil.getRandomColor();
		groupColor.setColor(color);
		logger.info("nbTotal = " + nbExistingGroupColors + " : picked color " + color + " for groupId " + groupId);
		groupColorPersistence.update(groupColor);

		return color;
	}
}