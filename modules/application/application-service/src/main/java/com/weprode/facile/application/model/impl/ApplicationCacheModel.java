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

package com.weprode.facile.application.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.application.model.Application;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Application in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationCacheModel
	implements CacheModel<Application>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ApplicationCacheModel)) {
			return false;
		}

		ApplicationCacheModel applicationCacheModel =
			(ApplicationCacheModel)object;

		if (applicationId == applicationCacheModel.applicationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, applicationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{applicationId=");
		sb.append(applicationId);
		sb.append(", applicationName=");
		sb.append(applicationName);
		sb.append(", applicationKey=");
		sb.append(applicationKey);
		sb.append(", categoryName=");
		sb.append(categoryName);
		sb.append(", image=");
		sb.append(image);
		sb.append(", hasCustomUrl=");
		sb.append(hasCustomUrl);
		sb.append(", hasGlobalUrl=");
		sb.append(hasGlobalUrl);
		sb.append(", globalUrl=");
		sb.append(globalUrl);
		sb.append(", exportUser=");
		sb.append(exportUser);
		sb.append(", exportParent=");
		sb.append(exportParent);
		sb.append(", exportStudent=");
		sb.append(exportStudent);
		sb.append(", exportTeacher=");
		sb.append(exportTeacher);
		sb.append(", exportOther=");
		sb.append(exportOther);
		sb.append(", menuEntryId=");
		sb.append(menuEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Application toEntityModel() {
		ApplicationImpl applicationImpl = new ApplicationImpl();

		applicationImpl.setApplicationId(applicationId);

		if (applicationName == null) {
			applicationImpl.setApplicationName("");
		}
		else {
			applicationImpl.setApplicationName(applicationName);
		}

		if (applicationKey == null) {
			applicationImpl.setApplicationKey("");
		}
		else {
			applicationImpl.setApplicationKey(applicationKey);
		}

		if (categoryName == null) {
			applicationImpl.setCategoryName("");
		}
		else {
			applicationImpl.setCategoryName(categoryName);
		}

		if (image == null) {
			applicationImpl.setImage("");
		}
		else {
			applicationImpl.setImage(image);
		}

		applicationImpl.setHasCustomUrl(hasCustomUrl);
		applicationImpl.setHasGlobalUrl(hasGlobalUrl);

		if (globalUrl == null) {
			applicationImpl.setGlobalUrl("");
		}
		else {
			applicationImpl.setGlobalUrl(globalUrl);
		}

		applicationImpl.setExportUser(exportUser);
		applicationImpl.setExportParent(exportParent);
		applicationImpl.setExportStudent(exportStudent);
		applicationImpl.setExportTeacher(exportTeacher);
		applicationImpl.setExportOther(exportOther);
		applicationImpl.setMenuEntryId(menuEntryId);

		applicationImpl.resetOriginalValues();

		return applicationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		applicationId = objectInput.readLong();
		applicationName = objectInput.readUTF();
		applicationKey = objectInput.readUTF();
		categoryName = objectInput.readUTF();
		image = objectInput.readUTF();

		hasCustomUrl = objectInput.readBoolean();

		hasGlobalUrl = objectInput.readBoolean();
		globalUrl = objectInput.readUTF();

		exportUser = objectInput.readBoolean();

		exportParent = objectInput.readBoolean();

		exportStudent = objectInput.readBoolean();

		exportTeacher = objectInput.readBoolean();

		exportOther = objectInput.readBoolean();

		menuEntryId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(applicationId);

		if (applicationName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicationName);
		}

		if (applicationKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicationKey);
		}

		if (categoryName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(categoryName);
		}

		if (image == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(image);
		}

		objectOutput.writeBoolean(hasCustomUrl);

		objectOutput.writeBoolean(hasGlobalUrl);

		if (globalUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(globalUrl);
		}

		objectOutput.writeBoolean(exportUser);

		objectOutput.writeBoolean(exportParent);

		objectOutput.writeBoolean(exportStudent);

		objectOutput.writeBoolean(exportTeacher);

		objectOutput.writeBoolean(exportOther);

		objectOutput.writeLong(menuEntryId);
	}

	public long applicationId;
	public String applicationName;
	public String applicationKey;
	public String categoryName;
	public String image;
	public boolean hasCustomUrl;
	public boolean hasGlobalUrl;
	public String globalUrl;
	public boolean exportUser;
	public boolean exportParent;
	public boolean exportStudent;
	public boolean exportTeacher;
	public boolean exportOther;
	public long menuEntryId;

}