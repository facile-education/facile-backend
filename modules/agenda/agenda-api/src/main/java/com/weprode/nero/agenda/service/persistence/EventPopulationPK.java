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

package com.weprode.nero.agenda.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EventPopulationPK
	implements Comparable<EventPopulationPK>, Serializable {

	public long eventId;
	public long groupId;
	public long roleId;

	public EventPopulationPK() {
	}

	public EventPopulationPK(long eventId, long groupId, long roleId) {
		this.eventId = eventId;
		this.groupId = groupId;
		this.roleId = roleId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public int compareTo(EventPopulationPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (eventId < pk.eventId) {
			value = -1;
		}
		else if (eventId > pk.eventId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (groupId < pk.groupId) {
			value = -1;
		}
		else if (groupId > pk.groupId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (roleId < pk.roleId) {
			value = -1;
		}
		else if (roleId > pk.roleId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EventPopulationPK)) {
			return false;
		}

		EventPopulationPK pk = (EventPopulationPK)object;

		if ((eventId == pk.eventId) && (groupId == pk.groupId) &&
			(roleId == pk.roleId)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, eventId);
		hashCode = HashUtil.hash(hashCode, groupId);
		hashCode = HashUtil.hash(hashCode, roleId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(8);

		sb.append("{");

		sb.append("eventId=");

		sb.append(eventId);
		sb.append(", groupId=");

		sb.append(groupId);
		sb.append(", roleId=");

		sb.append(roleId);

		sb.append("}");

		return sb.toString();
	}

}