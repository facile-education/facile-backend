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

import com.weprode.nero.organization.model.OrgMapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing OrgMapping in entity cache.
 *
 * @author Marc Salvat
 * @generated
 */
public class OrgMappingCacheModel
	implements CacheModel<OrgMapping>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof OrgMappingCacheModel)) {
			return false;
		}

		OrgMappingCacheModel orgMappingCacheModel =
			(OrgMappingCacheModel)object;

		if (entStructureUAI.equals(orgMappingCacheModel.entStructureUAI)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entStructureUAI);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{organizationId=");
		sb.append(organizationId);
		sb.append(", entStructureUAI=");
		sb.append(entStructureUAI);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OrgMapping toEntityModel() {
		OrgMappingImpl orgMappingImpl = new OrgMappingImpl();

		orgMappingImpl.setOrganizationId(organizationId);

		if (entStructureUAI == null) {
			orgMappingImpl.setEntStructureUAI("");
		}
		else {
			orgMappingImpl.setEntStructureUAI(entStructureUAI);
		}

		orgMappingImpl.resetOriginalValues();

		return orgMappingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		organizationId = objectInput.readLong();
		entStructureUAI = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(organizationId);

		if (entStructureUAI == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(entStructureUAI);
		}
	}

	public long organizationId;
	public String entStructureUAI;

}