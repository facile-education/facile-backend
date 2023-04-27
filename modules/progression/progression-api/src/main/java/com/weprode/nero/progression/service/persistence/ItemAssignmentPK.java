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

package com.weprode.nero.progression.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ItemAssignmentPK
	implements Comparable<ItemAssignmentPK>, Serializable {

	public long progressionItemId;
	public long sessionId;

	public ItemAssignmentPK() {
	}

	public ItemAssignmentPK(long progressionItemId, long sessionId) {
		this.progressionItemId = progressionItemId;
		this.sessionId = sessionId;
	}

	public long getProgressionItemId() {
		return progressionItemId;
	}

	public void setProgressionItemId(long progressionItemId) {
		this.progressionItemId = progressionItemId;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public int compareTo(ItemAssignmentPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (progressionItemId < pk.progressionItemId) {
			value = -1;
		}
		else if (progressionItemId > pk.progressionItemId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (sessionId < pk.sessionId) {
			value = -1;
		}
		else if (sessionId > pk.sessionId) {
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

		if (!(object instanceof ItemAssignmentPK)) {
			return false;
		}

		ItemAssignmentPK pk = (ItemAssignmentPK)object;

		if ((progressionItemId == pk.progressionItemId) &&
			(sessionId == pk.sessionId)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, progressionItemId);
		hashCode = HashUtil.hash(hashCode, sessionId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("progressionItemId=");

		sb.append(progressionItemId);
		sb.append(", sessionId=");

		sb.append(sessionId);

		sb.append("}");

		return sb.toString();
	}

}