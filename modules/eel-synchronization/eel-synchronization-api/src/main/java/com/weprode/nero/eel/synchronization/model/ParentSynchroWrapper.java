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

package com.weprode.nero.eel.synchronization.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ParentSynchro}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ParentSynchro
 * @generated
 */
public class ParentSynchroWrapper
	extends BaseModelWrapper<ParentSynchro>
	implements ModelWrapper<ParentSynchro>, ParentSynchro {

	public ParentSynchroWrapper(ParentSynchro parentSynchro) {
		super(parentSynchro);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoolId", getSchoolId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("fileName", getFileName());
		attributes.put("lineCount", getLineCount());
		attributes.put("errorCount", getErrorCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Long lineCount = (Long)attributes.get("lineCount");

		if (lineCount != null) {
			setLineCount(lineCount);
		}

		Long errorCount = (Long)attributes.get("errorCount");

		if (errorCount != null) {
			setErrorCount(errorCount);
		}
	}

	@Override
	public ParentSynchro cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the end date of this parent synchro.
	 *
	 * @return the end date of this parent synchro
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the error count of this parent synchro.
	 *
	 * @return the error count of this parent synchro
	 */
	@Override
	public long getErrorCount() {
		return model.getErrorCount();
	}

	/**
	 * Returns the file name of this parent synchro.
	 *
	 * @return the file name of this parent synchro
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the line count of this parent synchro.
	 *
	 * @return the line count of this parent synchro
	 */
	@Override
	public long getLineCount() {
		return model.getLineCount();
	}

	/**
	 * Returns the primary key of this parent synchro.
	 *
	 * @return the primary key of this parent synchro
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this parent synchro.
	 *
	 * @return the school ID of this parent synchro
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the start date of this parent synchro.
	 *
	 * @return the start date of this parent synchro
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the end date of this parent synchro.
	 *
	 * @param endDate the end date of this parent synchro
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the error count of this parent synchro.
	 *
	 * @param errorCount the error count of this parent synchro
	 */
	@Override
	public void setErrorCount(long errorCount) {
		model.setErrorCount(errorCount);
	}

	/**
	 * Sets the file name of this parent synchro.
	 *
	 * @param fileName the file name of this parent synchro
	 */
	@Override
	public void setFileName(String fileName) {
		model.setFileName(fileName);
	}

	/**
	 * Sets the line count of this parent synchro.
	 *
	 * @param lineCount the line count of this parent synchro
	 */
	@Override
	public void setLineCount(long lineCount) {
		model.setLineCount(lineCount);
	}

	/**
	 * Sets the primary key of this parent synchro.
	 *
	 * @param primaryKey the primary key of this parent synchro
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this parent synchro.
	 *
	 * @param schoolId the school ID of this parent synchro
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the start date of this parent synchro.
	 *
	 * @param startDate the start date of this parent synchro
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	@Override
	protected ParentSynchroWrapper wrap(ParentSynchro parentSynchro) {
		return new ParentSynchroWrapper(parentSynchro);
	}

}