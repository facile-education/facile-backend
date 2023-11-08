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

package com.weprode.facile.organization.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.organization.model.ClassCoursMapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ClassCoursMapping in entity cache.
 *
 * @author Marc Salvat
 * @generated
 */
public class ClassCoursMappingCacheModel
	implements CacheModel<ClassCoursMapping>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ClassCoursMappingCacheModel)) {
			return false;
		}

		ClassCoursMappingCacheModel classCoursMappingCacheModel =
			(ClassCoursMappingCacheModel)object;

		if (mappingId == classCoursMappingCacheModel.mappingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mappingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{mappingId=");
		sb.append(mappingId);
		sb.append(", classOrgId=");
		sb.append(classOrgId);
		sb.append(", coursOrgId=");
		sb.append(coursOrgId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ClassCoursMapping toEntityModel() {
		ClassCoursMappingImpl classCoursMappingImpl =
			new ClassCoursMappingImpl();

		classCoursMappingImpl.setMappingId(mappingId);
		classCoursMappingImpl.setClassOrgId(classOrgId);
		classCoursMappingImpl.setCoursOrgId(coursOrgId);

		classCoursMappingImpl.resetOriginalValues();

		return classCoursMappingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mappingId = objectInput.readLong();

		classOrgId = objectInput.readLong();

		coursOrgId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mappingId);

		objectOutput.writeLong(classOrgId);

		objectOutput.writeLong(coursOrgId);
	}

	public long mappingId;
	public long classOrgId;
	public long coursOrgId;

}