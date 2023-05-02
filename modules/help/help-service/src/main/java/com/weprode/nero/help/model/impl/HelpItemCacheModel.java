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

package com.weprode.nero.help.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.help.model.HelpItem;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing HelpItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HelpItemCacheModel
	implements CacheModel<HelpItem>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HelpItemCacheModel)) {
			return false;
		}

		HelpItemCacheModel helpItemCacheModel = (HelpItemCacheModel)object;

		if (itemId == helpItemCacheModel.itemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{itemId=");
		sb.append(itemId);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", videoURL=");
		sb.append(videoURL);
		sb.append(", videoDescription=");
		sb.append(videoDescription);
		sb.append(", manual=");
		sb.append(manual);
		sb.append(", position=");
		sb.append(position);
		sb.append(", language=");
		sb.append(language);
		sb.append(", isManagement=");
		sb.append(isManagement);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HelpItem toEntityModel() {
		HelpItemImpl helpItemImpl = new HelpItemImpl();

		helpItemImpl.setItemId(itemId);
		helpItemImpl.setCategoryId(categoryId);

		if (itemName == null) {
			helpItemImpl.setItemName("");
		}
		else {
			helpItemImpl.setItemName(itemName);
		}

		if (videoURL == null) {
			helpItemImpl.setVideoURL("");
		}
		else {
			helpItemImpl.setVideoURL(videoURL);
		}

		if (videoDescription == null) {
			helpItemImpl.setVideoDescription("");
		}
		else {
			helpItemImpl.setVideoDescription(videoDescription);
		}

		if (manual == null) {
			helpItemImpl.setManual("");
		}
		else {
			helpItemImpl.setManual(manual);
		}

		helpItemImpl.setPosition(position);

		if (language == null) {
			helpItemImpl.setLanguage("");
		}
		else {
			helpItemImpl.setLanguage(language);
		}

		helpItemImpl.setIsManagement(isManagement);

		helpItemImpl.resetOriginalValues();

		return helpItemImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemId = objectInput.readLong();

		categoryId = objectInput.readLong();
		itemName = objectInput.readUTF();
		videoURL = objectInput.readUTF();
		videoDescription = objectInput.readUTF();
		manual = objectInput.readUTF();

		position = objectInput.readInt();
		language = objectInput.readUTF();

		isManagement = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(itemId);

		objectOutput.writeLong(categoryId);

		if (itemName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (videoURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(videoURL);
		}

		if (videoDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(videoDescription);
		}

		if (manual == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(manual);
		}

		objectOutput.writeInt(position);

		if (language == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(language);
		}

		objectOutput.writeBoolean(isManagement);
	}

	public long itemId;
	public long categoryId;
	public String itemName;
	public String videoURL;
	public String videoDescription;
	public String manual;
	public int position;
	public String language;
	public boolean isManagement;

}