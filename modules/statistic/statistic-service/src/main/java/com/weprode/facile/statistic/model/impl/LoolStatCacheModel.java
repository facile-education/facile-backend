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

package com.weprode.facile.statistic.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.statistic.model.LoolStat;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoolStat in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LoolStatCacheModel
	implements CacheModel<LoolStat>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LoolStatCacheModel)) {
			return false;
		}

		LoolStatCacheModel loolStatCacheModel = (LoolStatCacheModel)object;

		if (statId == loolStatCacheModel.statId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, statId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{statId=");
		sb.append(statId);
		sb.append(", objectId=");
		sb.append(objectId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", saveAction=");
		sb.append(saveAction);
		sb.append(", actionDate=");
		sb.append(actionDate);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoolStat toEntityModel() {
		LoolStatImpl loolStatImpl = new LoolStatImpl();

		loolStatImpl.setStatId(statId);
		loolStatImpl.setObjectId(objectId);
		loolStatImpl.setUserId(userId);
		loolStatImpl.setSaveAction(saveAction);

		if (actionDate == Long.MIN_VALUE) {
			loolStatImpl.setActionDate(null);
		}
		else {
			loolStatImpl.setActionDate(new Date(actionDate));
		}

		loolStatImpl.setType(type);

		loolStatImpl.resetOriginalValues();

		return loolStatImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		statId = objectInput.readLong();

		objectId = objectInput.readLong();

		userId = objectInput.readLong();

		saveAction = objectInput.readBoolean();
		actionDate = objectInput.readLong();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(statId);

		objectOutput.writeLong(objectId);

		objectOutput.writeLong(userId);

		objectOutput.writeBoolean(saveAction);
		objectOutput.writeLong(actionDate);

		objectOutput.writeInt(type);
	}

	public long statId;
	public long objectId;
	public long userId;
	public boolean saveAction;
	public long actionDate;
	public int type;

}