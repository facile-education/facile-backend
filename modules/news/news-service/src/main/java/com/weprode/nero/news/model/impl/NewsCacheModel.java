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

import com.weprode.nero.news.model.News;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing News in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NewsCacheModel implements CacheModel<News>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NewsCacheModel)) {
			return false;
		}

		NewsCacheModel newsCacheModel = (NewsCacheModel)object;

		if (newsId == newsCacheModel.newsId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, newsId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{newsId=");
		sb.append(newsId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", content=");
		sb.append(content);
		sb.append(", authorId=");
		sb.append(authorId);
		sb.append(", isSchoolNews=");
		sb.append(isSchoolNews);
		sb.append(", isImportant=");
		sb.append(isImportant);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append(", modificationDate=");
		sb.append(modificationDate);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public News toEntityModel() {
		NewsImpl newsImpl = new NewsImpl();

		newsImpl.setNewsId(newsId);
		newsImpl.setCompanyId(companyId);

		if (title == null) {
			newsImpl.setTitle("");
		}
		else {
			newsImpl.setTitle(title);
		}

		if (content == null) {
			newsImpl.setContent("");
		}
		else {
			newsImpl.setContent(content);
		}

		newsImpl.setAuthorId(authorId);
		newsImpl.setIsSchoolNews(isSchoolNews);
		newsImpl.setIsImportant(isImportant);

		if (expirationDate == Long.MIN_VALUE) {
			newsImpl.setExpirationDate(null);
		}
		else {
			newsImpl.setExpirationDate(new Date(expirationDate));
		}

		if (publicationDate == Long.MIN_VALUE) {
			newsImpl.setPublicationDate(null);
		}
		else {
			newsImpl.setPublicationDate(new Date(publicationDate));
		}

		if (modificationDate == Long.MIN_VALUE) {
			newsImpl.setModificationDate(null);
		}
		else {
			newsImpl.setModificationDate(new Date(modificationDate));
		}

		newsImpl.setImageId(imageId);

		newsImpl.resetOriginalValues();

		return newsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		newsId = objectInput.readLong();

		companyId = objectInput.readLong();
		title = objectInput.readUTF();
		content = objectInput.readUTF();

		authorId = objectInput.readLong();

		isSchoolNews = objectInput.readBoolean();

		isImportant = objectInput.readBoolean();
		expirationDate = objectInput.readLong();
		publicationDate = objectInput.readLong();
		modificationDate = objectInput.readLong();

		imageId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(newsId);

		objectOutput.writeLong(companyId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeLong(authorId);

		objectOutput.writeBoolean(isSchoolNews);

		objectOutput.writeBoolean(isImportant);
		objectOutput.writeLong(expirationDate);
		objectOutput.writeLong(publicationDate);
		objectOutput.writeLong(modificationDate);

		objectOutput.writeLong(imageId);
	}

	public long newsId;
	public long companyId;
	public String title;
	public String content;
	public long authorId;
	public boolean isSchoolNews;
	public boolean isImportant;
	public long expirationDate;
	public long publicationDate;
	public long modificationDate;
	public long imageId;

}