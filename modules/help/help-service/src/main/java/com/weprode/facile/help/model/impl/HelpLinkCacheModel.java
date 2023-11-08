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

import com.weprode.facile.help.model.HelpLink;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing HelpLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HelpLinkCacheModel
	implements CacheModel<HelpLink>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HelpLinkCacheModel)) {
			return false;
		}

		HelpLinkCacheModel helpLinkCacheModel = (HelpLinkCacheModel)object;

		if (linkId == helpLinkCacheModel.linkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, linkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{linkId=");
		sb.append(linkId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", linkName=");
		sb.append(linkName);
		sb.append(", linkUrl=");
		sb.append(linkUrl);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HelpLink toEntityModel() {
		HelpLinkImpl helpLinkImpl = new HelpLinkImpl();

		helpLinkImpl.setLinkId(linkId);
		helpLinkImpl.setItemId(itemId);

		if (linkName == null) {
			helpLinkImpl.setLinkName("");
		}
		else {
			helpLinkImpl.setLinkName(linkName);
		}

		if (linkUrl == null) {
			helpLinkImpl.setLinkUrl("");
		}
		else {
			helpLinkImpl.setLinkUrl(linkUrl);
		}

		helpLinkImpl.resetOriginalValues();

		return helpLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		linkId = objectInput.readLong();

		itemId = objectInput.readLong();
		linkName = objectInput.readUTF();
		linkUrl = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(linkId);

		objectOutput.writeLong(itemId);

		if (linkName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(linkName);
		}

		if (linkUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(linkUrl);
		}
	}

	public long linkId;
	public long itemId;
	public String linkName;
	public String linkUrl;

}