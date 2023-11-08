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

package com.weprode.facile.application.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.application.model.Broadcast;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Broadcast in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BroadcastCacheModel
	implements CacheModel<Broadcast>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BroadcastCacheModel)) {
			return false;
		}

		BroadcastCacheModel broadcastCacheModel = (BroadcastCacheModel)object;

		if (broadcastId == broadcastCacheModel.broadcastId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, broadcastId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{broadcastId=");
		sb.append(broadcastId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append(", applicationId=");
		sb.append(applicationId);
		sb.append(", isBroadcasted=");
		sb.append(isBroadcasted);
		sb.append(", applicationUrl=");
		sb.append(applicationUrl);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Broadcast toEntityModel() {
		BroadcastImpl broadcastImpl = new BroadcastImpl();

		broadcastImpl.setBroadcastId(broadcastId);
		broadcastImpl.setSchoolId(schoolId);
		broadcastImpl.setApplicationId(applicationId);
		broadcastImpl.setIsBroadcasted(isBroadcasted);

		if (applicationUrl == null) {
			broadcastImpl.setApplicationUrl("");
		}
		else {
			broadcastImpl.setApplicationUrl(applicationUrl);
		}

		broadcastImpl.resetOriginalValues();

		return broadcastImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		broadcastId = objectInput.readLong();

		schoolId = objectInput.readLong();

		applicationId = objectInput.readLong();

		isBroadcasted = objectInput.readBoolean();
		applicationUrl = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(broadcastId);

		objectOutput.writeLong(schoolId);

		objectOutput.writeLong(applicationId);

		objectOutput.writeBoolean(isBroadcasted);

		if (applicationUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicationUrl);
		}
	}

	public long broadcastId;
	public long schoolId;
	public long applicationId;
	public boolean isBroadcasted;
	public String applicationUrl;

}