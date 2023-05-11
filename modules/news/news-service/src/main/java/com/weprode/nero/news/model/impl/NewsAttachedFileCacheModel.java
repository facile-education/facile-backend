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

package com.weprode.nero.news.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.news.model.NewsAttachedFile;
import com.weprode.nero.news.service.persistence.NewsAttachedFilePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NewsAttachedFile in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NewsAttachedFileCacheModel
	implements CacheModel<NewsAttachedFile>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NewsAttachedFileCacheModel)) {
			return false;
		}

		NewsAttachedFileCacheModel newsAttachedFileCacheModel =
			(NewsAttachedFileCacheModel)object;

		if (newsAttachedFilePK.equals(
				newsAttachedFileCacheModel.newsAttachedFilePK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, newsAttachedFilePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{newsId=");
		sb.append(newsId);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NewsAttachedFile toEntityModel() {
		NewsAttachedFileImpl newsAttachedFileImpl = new NewsAttachedFileImpl();

		newsAttachedFileImpl.setNewsId(newsId);
		newsAttachedFileImpl.setFileId(fileId);

		if (fileName == null) {
			newsAttachedFileImpl.setFileName("");
		}
		else {
			newsAttachedFileImpl.setFileName(fileName);
		}

		newsAttachedFileImpl.resetOriginalValues();

		return newsAttachedFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		newsId = objectInput.readLong();

		fileId = objectInput.readLong();
		fileName = objectInput.readUTF();

		newsAttachedFilePK = new NewsAttachedFilePK(newsId, fileId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(newsId);

		objectOutput.writeLong(fileId);

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}
	}

	public long newsId;
	public long fileId;
	public String fileName;
	public transient NewsAttachedFilePK newsAttachedFilePK;

}