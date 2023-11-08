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

package com.weprode.facile.preference.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.preference.model.NotifyConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NotifyConfig in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NotifyConfigCacheModel
	implements CacheModel<NotifyConfig>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NotifyConfigCacheModel)) {
			return false;
		}

		NotifyConfigCacheModel notifyConfigCacheModel =
			(NotifyConfigCacheModel)object;

		if (notifyConfigId == notifyConfigCacheModel.notifyConfigId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, notifyConfigId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{notifyConfigId=");
		sb.append(notifyConfigId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", activate=");
		sb.append(activate);
		sb.append(", notifyCasier=");
		sb.append(notifyCasier);
		sb.append(", notifyActu=");
		sb.append(notifyActu);
		sb.append(", notifyGrpDoc=");
		sb.append(notifyGrpDoc);
		sb.append(", notifyAgenda=");
		sb.append(notifyAgenda);
		sb.append(", notifySync=");
		sb.append(notifySync);
		sb.append(", digestPeriod=");
		sb.append(digestPeriod);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NotifyConfig toEntityModel() {
		NotifyConfigImpl notifyConfigImpl = new NotifyConfigImpl();

		notifyConfigImpl.setNotifyConfigId(notifyConfigId);
		notifyConfigImpl.setUserId(userId);
		notifyConfigImpl.setActivate(activate);
		notifyConfigImpl.setNotifyCasier(notifyCasier);
		notifyConfigImpl.setNotifyActu(notifyActu);
		notifyConfigImpl.setNotifyGrpDoc(notifyGrpDoc);
		notifyConfigImpl.setNotifyAgenda(notifyAgenda);
		notifyConfigImpl.setNotifySync(notifySync);
		notifyConfigImpl.setDigestPeriod(digestPeriod);

		notifyConfigImpl.resetOriginalValues();

		return notifyConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		notifyConfigId = objectInput.readLong();

		userId = objectInput.readLong();

		activate = objectInput.readBoolean();

		notifyCasier = objectInput.readBoolean();

		notifyActu = objectInput.readBoolean();

		notifyGrpDoc = objectInput.readBoolean();

		notifyAgenda = objectInput.readBoolean();

		notifySync = objectInput.readBoolean();

		digestPeriod = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(notifyConfigId);

		objectOutput.writeLong(userId);

		objectOutput.writeBoolean(activate);

		objectOutput.writeBoolean(notifyCasier);

		objectOutput.writeBoolean(notifyActu);

		objectOutput.writeBoolean(notifyGrpDoc);

		objectOutput.writeBoolean(notifyAgenda);

		objectOutput.writeBoolean(notifySync);

		objectOutput.writeInt(digestPeriod);
	}

	public long notifyConfigId;
	public long userId;
	public boolean activate;
	public boolean notifyCasier;
	public boolean notifyActu;
	public boolean notifyGrpDoc;
	public boolean notifyAgenda;
	public boolean notifySync;
	public int digestPeriod;

}