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

package com.weprode.facile.news.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NewsAttachedFilePK
	implements Comparable<NewsAttachedFilePK>, Serializable {

	public long newsId;
	public long fileId;

	public NewsAttachedFilePK() {
	}

	public NewsAttachedFilePK(long newsId, long fileId) {
		this.newsId = newsId;
		this.fileId = fileId;
	}

	public long getNewsId() {
		return newsId;
	}

	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	@Override
	public int compareTo(NewsAttachedFilePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (newsId < pk.newsId) {
			value = -1;
		}
		else if (newsId > pk.newsId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (fileId < pk.fileId) {
			value = -1;
		}
		else if (fileId > pk.fileId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NewsAttachedFilePK)) {
			return false;
		}

		NewsAttachedFilePK pk = (NewsAttachedFilePK)object;

		if ((newsId == pk.newsId) && (fileId == pk.fileId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, newsId);
		hashCode = HashUtil.hash(hashCode, fileId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("newsId=");

		sb.append(newsId);
		sb.append(", fileId=");

		sb.append(fileId);

		sb.append("}");

		return sb.toString();
	}

}