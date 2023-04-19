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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.schedule.service.http.CDTSessionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class CDTSessionSoap implements Serializable {

	public static CDTSessionSoap toSoapModel(CDTSession model) {
		CDTSessionSoap soapModel = new CDTSessionSoap();

		soapModel.setSessionId(model.getSessionId());
		soapModel.setSessionStart(model.getSessionStart());
		soapModel.setSessionEnd(model.getSessionEnd());
		soapModel.setWeekId(model.getWeekId());
		soapModel.setPublished(model.isPublished());
		soapModel.setTitle(model.getTitle());
		soapModel.setFullCoursName(model.getFullCoursName());
		soapModel.setDescription(model.getDescription());
		soapModel.setRoom(model.getRoom());
		soapModel.setSubject(model.getSubject());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setIsManual(model.isIsManual());

		return soapModel;
	}

	public static CDTSessionSoap[] toSoapModels(CDTSession[] models) {
		CDTSessionSoap[] soapModels = new CDTSessionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CDTSessionSoap[][] toSoapModels(CDTSession[][] models) {
		CDTSessionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CDTSessionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CDTSessionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CDTSessionSoap[] toSoapModels(List<CDTSession> models) {
		List<CDTSessionSoap> soapModels = new ArrayList<CDTSessionSoap>(
			models.size());

		for (CDTSession model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CDTSessionSoap[soapModels.size()]);
	}

	public CDTSessionSoap() {
	}

	public long getPrimaryKey() {
		return _sessionId;
	}

	public void setPrimaryKey(long pk) {
		setSessionId(pk);
	}

	public long getSessionId() {
		return _sessionId;
	}

	public void setSessionId(long sessionId) {
		_sessionId = sessionId;
	}

	public Date getSessionStart() {
		return _sessionStart;
	}

	public void setSessionStart(Date sessionStart) {
		_sessionStart = sessionStart;
	}

	public Date getSessionEnd() {
		return _sessionEnd;
	}

	public void setSessionEnd(Date sessionEnd) {
		_sessionEnd = sessionEnd;
	}

	public long getWeekId() {
		return _weekId;
	}

	public void setWeekId(long weekId) {
		_weekId = weekId;
	}

	public boolean getPublished() {
		return _published;
	}

	public boolean isPublished() {
		return _published;
	}

	public void setPublished(boolean published) {
		_published = published;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getFullCoursName() {
		return _fullCoursName;
	}

	public void setFullCoursName(String fullCoursName) {
		_fullCoursName = fullCoursName;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getRoom() {
		return _room;
	}

	public void setRoom(String room) {
		_room = room;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public boolean getIsManual() {
		return _isManual;
	}

	public boolean isIsManual() {
		return _isManual;
	}

	public void setIsManual(boolean isManual) {
		_isManual = isManual;
	}

	private long _sessionId;
	private Date _sessionStart;
	private Date _sessionEnd;
	private long _weekId;
	private boolean _published;
	private String _title;
	private String _fullCoursName;
	private String _description;
	private String _room;
	private String _subject;
	private long _schoolId;
	private long _groupId;
	private boolean _isManual;

}