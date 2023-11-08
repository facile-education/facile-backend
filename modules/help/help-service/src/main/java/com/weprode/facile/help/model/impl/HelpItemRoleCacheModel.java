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

package com.weprode.facile.help.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.help.model.HelpItemRole;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing HelpItemRole in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HelpItemRoleCacheModel
	implements CacheModel<HelpItemRole>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HelpItemRoleCacheModel)) {
			return false;
		}

		HelpItemRoleCacheModel helpItemRoleCacheModel =
			(HelpItemRoleCacheModel)object;

		if (helpItemRoleId == helpItemRoleCacheModel.helpItemRoleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, helpItemRoleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{helpItemRoleId=");
		sb.append(helpItemRoleId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HelpItemRole toEntityModel() {
		HelpItemRoleImpl helpItemRoleImpl = new HelpItemRoleImpl();

		helpItemRoleImpl.setHelpItemRoleId(helpItemRoleId);
		helpItemRoleImpl.setItemId(itemId);
		helpItemRoleImpl.setRoleId(roleId);

		helpItemRoleImpl.resetOriginalValues();

		return helpItemRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		helpItemRoleId = objectInput.readLong();

		itemId = objectInput.readLong();

		roleId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(helpItemRoleId);

		objectOutput.writeLong(itemId);

		objectOutput.writeLong(roleId);
	}

	public long helpItemRoleId;
	public long itemId;
	public long roleId;

}