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

package com.weprode.facile.organization.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ClassCoursMapping}.
 * </p>
 *
 * @author Marc Salvat
 * @see ClassCoursMapping
 * @generated
 */
public class ClassCoursMappingWrapper
	extends BaseModelWrapper<ClassCoursMapping>
	implements ClassCoursMapping, ModelWrapper<ClassCoursMapping> {

	public ClassCoursMappingWrapper(ClassCoursMapping classCoursMapping) {
		super(classCoursMapping);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mappingId", getMappingId());
		attributes.put("classOrgId", getClassOrgId());
		attributes.put("coursOrgId", getCoursOrgId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mappingId = (Long)attributes.get("mappingId");

		if (mappingId != null) {
			setMappingId(mappingId);
		}

		Long classOrgId = (Long)attributes.get("classOrgId");

		if (classOrgId != null) {
			setClassOrgId(classOrgId);
		}

		Long coursOrgId = (Long)attributes.get("coursOrgId");

		if (coursOrgId != null) {
			setCoursOrgId(coursOrgId);
		}
	}

	@Override
	public ClassCoursMapping cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the class org ID of this class cours mapping.
	 *
	 * @return the class org ID of this class cours mapping
	 */
	@Override
	public long getClassOrgId() {
		return model.getClassOrgId();
	}

	/**
	 * Returns the cours org ID of this class cours mapping.
	 *
	 * @return the cours org ID of this class cours mapping
	 */
	@Override
	public long getCoursOrgId() {
		return model.getCoursOrgId();
	}

	/**
	 * Returns the mapping ID of this class cours mapping.
	 *
	 * @return the mapping ID of this class cours mapping
	 */
	@Override
	public long getMappingId() {
		return model.getMappingId();
	}

	/**
	 * Returns the primary key of this class cours mapping.
	 *
	 * @return the primary key of this class cours mapping
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the class org ID of this class cours mapping.
	 *
	 * @param classOrgId the class org ID of this class cours mapping
	 */
	@Override
	public void setClassOrgId(long classOrgId) {
		model.setClassOrgId(classOrgId);
	}

	/**
	 * Sets the cours org ID of this class cours mapping.
	 *
	 * @param coursOrgId the cours org ID of this class cours mapping
	 */
	@Override
	public void setCoursOrgId(long coursOrgId) {
		model.setCoursOrgId(coursOrgId);
	}

	/**
	 * Sets the mapping ID of this class cours mapping.
	 *
	 * @param mappingId the mapping ID of this class cours mapping
	 */
	@Override
	public void setMappingId(long mappingId) {
		model.setMappingId(mappingId);
	}

	/**
	 * Sets the primary key of this class cours mapping.
	 *
	 * @param primaryKey the primary key of this class cours mapping
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ClassCoursMappingWrapper wrap(
		ClassCoursMapping classCoursMapping) {

		return new ClassCoursMappingWrapper(classCoursMapping);
	}

}