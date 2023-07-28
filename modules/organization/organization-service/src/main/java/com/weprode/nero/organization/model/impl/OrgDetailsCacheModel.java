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

package com.weprode.nero.organization.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.organization.model.OrgDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing OrgDetails in entity cache.
 *
 * @author Marc Salvat
 * @generated
 */
public class OrgDetailsCacheModel
	implements CacheModel<OrgDetails>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof OrgDetailsCacheModel)) {
			return false;
		}

		OrgDetailsCacheModel orgDetailsCacheModel =
			(OrgDetailsCacheModel)object;

		if (orgId == orgDetailsCacheModel.orgId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, orgId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{orgId=");
		sb.append(orgId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append(", orgName=");
		sb.append(orgName);
		sb.append(", type=");
		sb.append(type);
		sb.append(", isArchive=");
		sb.append(isArchive);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OrgDetails toEntityModel() {
		OrgDetailsImpl orgDetailsImpl = new OrgDetailsImpl();

		orgDetailsImpl.setOrgId(orgId);
		orgDetailsImpl.setSchoolId(schoolId);

		if (orgName == null) {
			orgDetailsImpl.setOrgName("");
		}
		else {
			orgDetailsImpl.setOrgName(orgName);
		}

		orgDetailsImpl.setType(type);
		orgDetailsImpl.setIsArchive(isArchive);

		orgDetailsImpl.resetOriginalValues();

		return orgDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		orgId = objectInput.readLong();

		schoolId = objectInput.readLong();
		orgName = objectInput.readUTF();

		type = objectInput.readInt();

		isArchive = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(orgId);

		objectOutput.writeLong(schoolId);

		if (orgName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(orgName);
		}

		objectOutput.writeInt(type);

		objectOutput.writeBoolean(isArchive);
	}

	public long orgId;
	public long schoolId;
	public String orgName;
	public int type;
	public boolean isArchive;

}