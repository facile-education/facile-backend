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

package com.weprode.nero.schedule.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ScheduleConfiguration}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfiguration
 * @generated
 */
public class ScheduleConfigurationWrapper
	extends BaseModelWrapper<ScheduleConfiguration>
	implements ModelWrapper<ScheduleConfiguration>, ScheduleConfiguration {

	public ScheduleConfigurationWrapper(
		ScheduleConfiguration scheduleConfiguration) {

		super(scheduleConfiguration);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("configId", getConfigId());
		attributes.put("projectStartDate", getProjectStartDate());
		attributes.put("schoolYearStartDate", getSchoolYearStartDate());
		attributes.put("schoolYearSemesterDate", getSchoolYearSemesterDate());
		attributes.put("schoolYearEndDate", getSchoolYearEndDate());
		attributes.put("h1Weeks", getH1Weeks());
		attributes.put("h2Weeks", getH2Weeks());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long configId = (Long)attributes.get("configId");

		if (configId != null) {
			setConfigId(configId);
		}

		Date projectStartDate = (Date)attributes.get("projectStartDate");

		if (projectStartDate != null) {
			setProjectStartDate(projectStartDate);
		}

		Date schoolYearStartDate = (Date)attributes.get("schoolYearStartDate");

		if (schoolYearStartDate != null) {
			setSchoolYearStartDate(schoolYearStartDate);
		}

		Date schoolYearSemesterDate = (Date)attributes.get(
			"schoolYearSemesterDate");

		if (schoolYearSemesterDate != null) {
			setSchoolYearSemesterDate(schoolYearSemesterDate);
		}

		Date schoolYearEndDate = (Date)attributes.get("schoolYearEndDate");

		if (schoolYearEndDate != null) {
			setSchoolYearEndDate(schoolYearEndDate);
		}

		String h1Weeks = (String)attributes.get("h1Weeks");

		if (h1Weeks != null) {
			setH1Weeks(h1Weeks);
		}

		String h2Weeks = (String)attributes.get("h2Weeks");

		if (h2Weeks != null) {
			setH2Weeks(h2Weeks);
		}
	}

	@Override
	public ScheduleConfiguration cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the config ID of this schedule configuration.
	 *
	 * @return the config ID of this schedule configuration
	 */
	@Override
	public long getConfigId() {
		return model.getConfigId();
	}

	/**
	 * Returns the h1 weeks of this schedule configuration.
	 *
	 * @return the h1 weeks of this schedule configuration
	 */
	@Override
	public String getH1Weeks() {
		return model.getH1Weeks();
	}

	/**
	 * Returns the h2 weeks of this schedule configuration.
	 *
	 * @return the h2 weeks of this schedule configuration
	 */
	@Override
	public String getH2Weeks() {
		return model.getH2Weeks();
	}

	/**
	 * Returns the primary key of this schedule configuration.
	 *
	 * @return the primary key of this schedule configuration
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the project start date of this schedule configuration.
	 *
	 * @return the project start date of this schedule configuration
	 */
	@Override
	public Date getProjectStartDate() {
		return model.getProjectStartDate();
	}

	/**
	 * Returns the school year end date of this schedule configuration.
	 *
	 * @return the school year end date of this schedule configuration
	 */
	@Override
	public Date getSchoolYearEndDate() {
		return model.getSchoolYearEndDate();
	}

	/**
	 * Returns the school year semester date of this schedule configuration.
	 *
	 * @return the school year semester date of this schedule configuration
	 */
	@Override
	public Date getSchoolYearSemesterDate() {
		return model.getSchoolYearSemesterDate();
	}

	/**
	 * Returns the school year start date of this schedule configuration.
	 *
	 * @return the school year start date of this schedule configuration
	 */
	@Override
	public Date getSchoolYearStartDate() {
		return model.getSchoolYearStartDate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the config ID of this schedule configuration.
	 *
	 * @param configId the config ID of this schedule configuration
	 */
	@Override
	public void setConfigId(long configId) {
		model.setConfigId(configId);
	}

	/**
	 * Sets the h1 weeks of this schedule configuration.
	 *
	 * @param h1Weeks the h1 weeks of this schedule configuration
	 */
	@Override
	public void setH1Weeks(String h1Weeks) {
		model.setH1Weeks(h1Weeks);
	}

	/**
	 * Sets the h2 weeks of this schedule configuration.
	 *
	 * @param h2Weeks the h2 weeks of this schedule configuration
	 */
	@Override
	public void setH2Weeks(String h2Weeks) {
		model.setH2Weeks(h2Weeks);
	}

	/**
	 * Sets the primary key of this schedule configuration.
	 *
	 * @param primaryKey the primary key of this schedule configuration
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the project start date of this schedule configuration.
	 *
	 * @param projectStartDate the project start date of this schedule configuration
	 */
	@Override
	public void setProjectStartDate(Date projectStartDate) {
		model.setProjectStartDate(projectStartDate);
	}

	/**
	 * Sets the school year end date of this schedule configuration.
	 *
	 * @param schoolYearEndDate the school year end date of this schedule configuration
	 */
	@Override
	public void setSchoolYearEndDate(Date schoolYearEndDate) {
		model.setSchoolYearEndDate(schoolYearEndDate);
	}

	/**
	 * Sets the school year semester date of this schedule configuration.
	 *
	 * @param schoolYearSemesterDate the school year semester date of this schedule configuration
	 */
	@Override
	public void setSchoolYearSemesterDate(Date schoolYearSemesterDate) {
		model.setSchoolYearSemesterDate(schoolYearSemesterDate);
	}

	/**
	 * Sets the school year start date of this schedule configuration.
	 *
	 * @param schoolYearStartDate the school year start date of this schedule configuration
	 */
	@Override
	public void setSchoolYearStartDate(Date schoolYearStartDate) {
		model.setSchoolYearStartDate(schoolYearStartDate);
	}

	@Override
	protected ScheduleConfigurationWrapper wrap(
		ScheduleConfiguration scheduleConfiguration) {

		return new ScheduleConfigurationWrapper(scheduleConfiguration);
	}

}