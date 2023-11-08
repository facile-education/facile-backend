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

package com.weprode.facile.user.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.user.model.LDAPMapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LDAPMapping in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LDAPMappingCacheModel
	implements CacheModel<LDAPMapping>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LDAPMappingCacheModel)) {
			return false;
		}

		LDAPMappingCacheModel ldapMappingCacheModel =
			(LDAPMappingCacheModel)object;

		if (UserId == ldapMappingCacheModel.UserId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, UserId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{UserId=");
		sb.append(UserId);
		sb.append(", EntPersonJointure=");
		sb.append(EntPersonJointure);
		sb.append(", UID=");
		sb.append(UID);
		sb.append(", INE=");
		sb.append(INE);
		sb.append(", EntEleveStructRattachId=");
		sb.append(EntEleveStructRattachId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LDAPMapping toEntityModel() {
		LDAPMappingImpl ldapMappingImpl = new LDAPMappingImpl();

		ldapMappingImpl.setUserId(UserId);

		if (EntPersonJointure == null) {
			ldapMappingImpl.setEntPersonJointure("");
		}
		else {
			ldapMappingImpl.setEntPersonJointure(EntPersonJointure);
		}

		if (UID == null) {
			ldapMappingImpl.setUID("");
		}
		else {
			ldapMappingImpl.setUID(UID);
		}

		if (INE == null) {
			ldapMappingImpl.setINE("");
		}
		else {
			ldapMappingImpl.setINE(INE);
		}

		if (EntEleveStructRattachId == null) {
			ldapMappingImpl.setEntEleveStructRattachId("");
		}
		else {
			ldapMappingImpl.setEntEleveStructRattachId(EntEleveStructRattachId);
		}

		ldapMappingImpl.resetOriginalValues();

		return ldapMappingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		UserId = objectInput.readLong();
		EntPersonJointure = objectInput.readUTF();
		UID = objectInput.readUTF();
		INE = objectInput.readUTF();
		EntEleveStructRattachId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(UserId);

		if (EntPersonJointure == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EntPersonJointure);
		}

		if (UID == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(UID);
		}

		if (INE == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(INE);
		}

		if (EntEleveStructRattachId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EntEleveStructRattachId);
		}
	}

	public long UserId;
	public String EntPersonJointure;

	public String UID;

	public String INE;

	public String EntEleveStructRattachId;

}