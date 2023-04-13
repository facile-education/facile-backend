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

package com.weprode.nero.application.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.application.model.DefaultRole;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DefaultRole in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DefaultRoleCacheModel
	implements CacheModel<DefaultRole>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DefaultRoleCacheModel)) {
			return false;
		}

		DefaultRoleCacheModel defaultRoleCacheModel =
			(DefaultRoleCacheModel)object;

		if (defaultRoleId == defaultRoleCacheModel.defaultRoleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, defaultRoleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{defaultRoleId=");
		sb.append(defaultRoleId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", applicationId=");
		sb.append(applicationId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DefaultRole toEntityModel() {
		DefaultRoleImpl defaultRoleImpl = new DefaultRoleImpl();

		defaultRoleImpl.setDefaultRoleId(defaultRoleId);
		defaultRoleImpl.setRoleId(roleId);
		defaultRoleImpl.setApplicationId(applicationId);

		defaultRoleImpl.resetOriginalValues();

		return defaultRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		defaultRoleId = objectInput.readLong();

		roleId = objectInput.readLong();

		applicationId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(defaultRoleId);

		objectOutput.writeLong(roleId);

		objectOutput.writeLong(applicationId);
	}

	public long defaultRoleId;
	public long roleId;
	public long applicationId;

}