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

import com.weprode.facile.application.model.AuthorizedSchool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AuthorizedSchool in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuthorizedSchoolCacheModel
	implements CacheModel<AuthorizedSchool>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AuthorizedSchoolCacheModel)) {
			return false;
		}

		AuthorizedSchoolCacheModel authorizedSchoolCacheModel =
			(AuthorizedSchoolCacheModel)object;

		if (authorizedSchoolId ==
				authorizedSchoolCacheModel.authorizedSchoolId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, authorizedSchoolId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{authorizedSchoolId=");
		sb.append(authorizedSchoolId);
		sb.append(", applicationId=");
		sb.append(applicationId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AuthorizedSchool toEntityModel() {
		AuthorizedSchoolImpl authorizedSchoolImpl = new AuthorizedSchoolImpl();

		authorizedSchoolImpl.setAuthorizedSchoolId(authorizedSchoolId);
		authorizedSchoolImpl.setApplicationId(applicationId);
		authorizedSchoolImpl.setSchoolId(schoolId);

		authorizedSchoolImpl.resetOriginalValues();

		return authorizedSchoolImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		authorizedSchoolId = objectInput.readLong();

		applicationId = objectInput.readLong();

		schoolId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(authorizedSchoolId);

		objectOutput.writeLong(applicationId);

		objectOutput.writeLong(schoolId);
	}

	public long authorizedSchoolId;
	public long applicationId;
	public long schoolId;

}