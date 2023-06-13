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
		soapModel.setStart(model.getStart());
		soapModel.setEnd(model.getEnd());
		soapModel.setWeekId(model.getWeekId());
		soapModel.setFullCoursName(model.getFullCoursName());
		soapModel.setRoom(model.getRoom());
		soapModel.setSubject(model.getSubject());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCourseItemId(model.getCourseItemId());
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

	public Date getStart() {
		return _start;
	}

	public void setStart(Date start) {
		_start = start;
	}

	public Date getEnd() {
		return _end;
	}

	public void setEnd(Date end) {
		_end = end;
	}

	public long getWeekId() {
		return _weekId;
	}

	public void setWeekId(long weekId) {
		_weekId = weekId;
	}

	public String getFullCoursName() {
		return _fullCoursName;
	}

	public void setFullCoursName(String fullCoursName) {
		_fullCoursName = fullCoursName;
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

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCourseItemId() {
		return _courseItemId;
	}

	public void setCourseItemId(long courseItemId) {
		_courseItemId = courseItemId;
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
	private Date _start;
	private Date _end;
	private long _weekId;
	private String _fullCoursName;
	private String _room;
	private String _subject;
	private long _groupId;
	private long _courseItemId;
	private boolean _isManual;

}