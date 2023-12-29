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

package com.weprode.facile.document.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.document.model.LoolToken;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoolToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LoolTokenCacheModel
	implements CacheModel<LoolToken>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LoolTokenCacheModel)) {
			return false;
		}

		LoolTokenCacheModel loolTokenCacheModel = (LoolTokenCacheModel)object;

		if (loolTokenId == loolTokenCacheModel.loolTokenId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loolTokenId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{loolTokenId=");
		sb.append(loolTokenId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", token=");
		sb.append(token);
		sb.append(", editionDate=");
		sb.append(editionDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoolToken toEntityModel() {
		LoolTokenImpl loolTokenImpl = new LoolTokenImpl();

		loolTokenImpl.setLoolTokenId(loolTokenId);
		loolTokenImpl.setUserId(userId);

		if (token == null) {
			loolTokenImpl.setToken("");
		}
		else {
			loolTokenImpl.setToken(token);
		}

		if (editionDate == Long.MIN_VALUE) {
			loolTokenImpl.setEditionDate(null);
		}
		else {
			loolTokenImpl.setEditionDate(new Date(editionDate));
		}

		loolTokenImpl.resetOriginalValues();

		return loolTokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loolTokenId = objectInput.readLong();

		userId = objectInput.readLong();
		token = objectInput.readUTF();
		editionDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loolTokenId);

		objectOutput.writeLong(userId);

		if (token == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(token);
		}

		objectOutput.writeLong(editionDate);
	}

	public long loolTokenId;
	public long userId;
	public String token;
	public long editionDate;

}