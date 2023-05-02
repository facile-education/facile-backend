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

import com.weprode.nero.help.model.HelpRelation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing HelpRelation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HelpRelationCacheModel
	implements CacheModel<HelpRelation>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HelpRelationCacheModel)) {
			return false;
		}

		HelpRelationCacheModel helpRelationCacheModel =
			(HelpRelationCacheModel)object;

		if (relationId == helpRelationCacheModel.relationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, relationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{relationId=");
		sb.append(relationId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", relatedItemId=");
		sb.append(relatedItemId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HelpRelation toEntityModel() {
		HelpRelationImpl helpRelationImpl = new HelpRelationImpl();

		helpRelationImpl.setRelationId(relationId);
		helpRelationImpl.setItemId(itemId);
		helpRelationImpl.setRelatedItemId(relatedItemId);

		helpRelationImpl.resetOriginalValues();

		return helpRelationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		relationId = objectInput.readLong();

		itemId = objectInput.readLong();

		relatedItemId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(relationId);

		objectOutput.writeLong(itemId);

		objectOutput.writeLong(relatedItemId);
	}

	public long relationId;
	public long itemId;
	public long relatedItemId;

}