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

package com.weprode.facile.agenda.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.agenda.model.EventPopulation;
import com.weprode.facile.agenda.service.persistence.EventPopulationPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing EventPopulation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EventPopulationCacheModel
	implements CacheModel<EventPopulation>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EventPopulationCacheModel)) {
			return false;
		}

		EventPopulationCacheModel eventPopulationCacheModel =
			(EventPopulationCacheModel)object;

		if (eventPopulationPK.equals(
				eventPopulationCacheModel.eventPopulationPK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eventPopulationPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{eventId=");
		sb.append(eventId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EventPopulation toEntityModel() {
		EventPopulationImpl eventPopulationImpl = new EventPopulationImpl();

		eventPopulationImpl.setEventId(eventId);
		eventPopulationImpl.setGroupId(groupId);
		eventPopulationImpl.setRoleId(roleId);

		eventPopulationImpl.resetOriginalValues();

		return eventPopulationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		eventId = objectInput.readLong();

		groupId = objectInput.readLong();

		roleId = objectInput.readLong();

		eventPopulationPK = new EventPopulationPK(eventId, groupId, roleId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(eventId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(roleId);
	}

	public long eventId;
	public long groupId;
	public long roleId;
	public transient EventPopulationPK eventPopulationPK;

}