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

package com.liferay.notifications.uad.exporter;

import com.liferay.notifications.uad.constants.NotificationsUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the user notification event UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link UserNotificationEventUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseUserNotificationEventUADExporter
	extends DynamicQueryUADExporter<UserNotificationEvent> {

	@Override
	public Class<UserNotificationEvent> getTypeClass() {
		return UserNotificationEvent.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return userNotificationEventLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return NotificationsUADConstants.
			USER_ID_FIELD_NAMES_USER_NOTIFICATION_EVENT;
	}

	@Override
	protected String toXmlString(UserNotificationEvent userNotificationEvent) {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.kernel.model.UserNotificationEvent");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userNotificationEventId</column-name><column-value><![CDATA[");
		sb.append(userNotificationEvent.getUserNotificationEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(userNotificationEvent.getUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected UserNotificationEventLocalService
		userNotificationEventLocalService;

}