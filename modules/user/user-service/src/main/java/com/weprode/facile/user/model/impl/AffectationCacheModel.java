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

import com.weprode.facile.user.model.Affectation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Affectation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AffectationCacheModel
	implements CacheModel<Affectation>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AffectationCacheModel)) {
			return false;
		}

		AffectationCacheModel affectationCacheModel =
			(AffectationCacheModel)object;

		if (affectationId == affectationCacheModel.affectationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, affectationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{affectationId=");
		sb.append(affectationId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", orgId=");
		sb.append(orgId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", adminUserId=");
		sb.append(adminUserId);
		sb.append(", affectationDate=");
		sb.append(affectationDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Affectation toEntityModel() {
		AffectationImpl affectationImpl = new AffectationImpl();

		affectationImpl.setAffectationId(affectationId);
		affectationImpl.setUserId(userId);
		affectationImpl.setOrgId(orgId);
		affectationImpl.setSchoolId(schoolId);
		affectationImpl.setType(type);
		affectationImpl.setAdminUserId(adminUserId);

		if (affectationDate == Long.MIN_VALUE) {
			affectationImpl.setAffectationDate(null);
		}
		else {
			affectationImpl.setAffectationDate(new Date(affectationDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			affectationImpl.setExpirationDate(null);
		}
		else {
			affectationImpl.setExpirationDate(new Date(expirationDate));
		}

		affectationImpl.resetOriginalValues();

		return affectationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		affectationId = objectInput.readLong();

		userId = objectInput.readLong();

		orgId = objectInput.readLong();

		schoolId = objectInput.readLong();

		type = objectInput.readInt();

		adminUserId = objectInput.readLong();
		affectationDate = objectInput.readLong();
		expirationDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(affectationId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(orgId);

		objectOutput.writeLong(schoolId);

		objectOutput.writeInt(type);

		objectOutput.writeLong(adminUserId);
		objectOutput.writeLong(affectationDate);
		objectOutput.writeLong(expirationDate);
	}

	public long affectationId;
	public long userId;
	public long orgId;
	public long schoolId;
	public int type;
	public long adminUserId;
	public long affectationDate;
	public long expirationDate;

}