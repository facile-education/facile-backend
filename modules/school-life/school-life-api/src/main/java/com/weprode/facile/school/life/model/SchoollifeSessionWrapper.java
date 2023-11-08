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

package com.weprode.facile.school.life.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SchoollifeSession}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSession
 * @generated
 */
public class SchoollifeSessionWrapper
	extends BaseModelWrapper<SchoollifeSession>
	implements ModelWrapper<SchoollifeSession>, SchoollifeSession {

	public SchoollifeSessionWrapper(SchoollifeSession schoollifeSession) {
		super(schoollifeSession);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoollifeSessionId", getSchoollifeSessionId());
		attributes.put("schoollifeSlotId", getSchoollifeSlotId());
		attributes.put("schoolId", getSchoolId());
		attributes.put("type", getType());
		attributes.put("weekNb", getWeekNb());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("rollCalled", isRollCalled());
		attributes.put("absenceNotificationSent", isAbsenceNotificationSent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schoollifeSessionId = (Long)attributes.get("schoollifeSessionId");

		if (schoollifeSessionId != null) {
			setSchoollifeSessionId(schoollifeSessionId);
		}

		Long schoollifeSlotId = (Long)attributes.get("schoollifeSlotId");

		if (schoollifeSlotId != null) {
			setSchoollifeSlotId(schoollifeSlotId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer weekNb = (Integer)attributes.get("weekNb");

		if (weekNb != null) {
			setWeekNb(weekNb);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Boolean rollCalled = (Boolean)attributes.get("rollCalled");

		if (rollCalled != null) {
			setRollCalled(rollCalled);
		}

		Boolean absenceNotificationSent = (Boolean)attributes.get(
			"absenceNotificationSent");

		if (absenceNotificationSent != null) {
			setAbsenceNotificationSent(absenceNotificationSent);
		}
	}

	@Override
	public SchoollifeSession cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the absence notification sent of this schoollife session.
	 *
	 * @return the absence notification sent of this schoollife session
	 */
	@Override
	public boolean getAbsenceNotificationSent() {
		return model.getAbsenceNotificationSent();
	}

	/**
	 * Returns the end date of this schoollife session.
	 *
	 * @return the end date of this schoollife session
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the primary key of this schoollife session.
	 *
	 * @return the primary key of this schoollife session
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the roll called of this schoollife session.
	 *
	 * @return the roll called of this schoollife session
	 */
	@Override
	public boolean getRollCalled() {
		return model.getRollCalled();
	}

	/**
	 * Returns the school ID of this schoollife session.
	 *
	 * @return the school ID of this schoollife session
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the schoollife session ID of this schoollife session.
	 *
	 * @return the schoollife session ID of this schoollife session
	 */
	@Override
	public long getSchoollifeSessionId() {
		return model.getSchoollifeSessionId();
	}

	/**
	 * Returns the schoollife slot ID of this schoollife session.
	 *
	 * @return the schoollife slot ID of this schoollife session
	 */
	@Override
	public long getSchoollifeSlotId() {
		return model.getSchoollifeSlotId();
	}

	/**
	 * Returns the start date of this schoollife session.
	 *
	 * @return the start date of this schoollife session
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the type of this schoollife session.
	 *
	 * @return the type of this schoollife session
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns the week nb of this schoollife session.
	 *
	 * @return the week nb of this schoollife session
	 */
	@Override
	public int getWeekNb() {
		return model.getWeekNb();
	}

	/**
	 * Returns <code>true</code> if this schoollife session is absence notification sent.
	 *
	 * @return <code>true</code> if this schoollife session is absence notification sent; <code>false</code> otherwise
	 */
	@Override
	public boolean isAbsenceNotificationSent() {
		return model.isAbsenceNotificationSent();
	}

	/**
	 * Returns <code>true</code> if this schoollife session is roll called.
	 *
	 * @return <code>true</code> if this schoollife session is roll called; <code>false</code> otherwise
	 */
	@Override
	public boolean isRollCalled() {
		return model.isRollCalled();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this schoollife session is absence notification sent.
	 *
	 * @param absenceNotificationSent the absence notification sent of this schoollife session
	 */
	@Override
	public void setAbsenceNotificationSent(boolean absenceNotificationSent) {
		model.setAbsenceNotificationSent(absenceNotificationSent);
	}

	/**
	 * Sets the end date of this schoollife session.
	 *
	 * @param endDate the end date of this schoollife session
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the primary key of this schoollife session.
	 *
	 * @param primaryKey the primary key of this schoollife session
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this schoollife session is roll called.
	 *
	 * @param rollCalled the roll called of this schoollife session
	 */
	@Override
	public void setRollCalled(boolean rollCalled) {
		model.setRollCalled(rollCalled);
	}

	/**
	 * Sets the school ID of this schoollife session.
	 *
	 * @param schoolId the school ID of this schoollife session
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the schoollife session ID of this schoollife session.
	 *
	 * @param schoollifeSessionId the schoollife session ID of this schoollife session
	 */
	@Override
	public void setSchoollifeSessionId(long schoollifeSessionId) {
		model.setSchoollifeSessionId(schoollifeSessionId);
	}

	/**
	 * Sets the schoollife slot ID of this schoollife session.
	 *
	 * @param schoollifeSlotId the schoollife slot ID of this schoollife session
	 */
	@Override
	public void setSchoollifeSlotId(long schoollifeSlotId) {
		model.setSchoollifeSlotId(schoollifeSlotId);
	}

	/**
	 * Sets the start date of this schoollife session.
	 *
	 * @param startDate the start date of this schoollife session
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the type of this schoollife session.
	 *
	 * @param type the type of this schoollife session
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	/**
	 * Sets the week nb of this schoollife session.
	 *
	 * @param weekNb the week nb of this schoollife session
	 */
	@Override
	public void setWeekNb(int weekNb) {
		model.setWeekNb(weekNb);
	}

	@Override
	protected SchoollifeSessionWrapper wrap(
		SchoollifeSession schoollifeSession) {

		return new SchoollifeSessionWrapper(schoollifeSession);
	}

}