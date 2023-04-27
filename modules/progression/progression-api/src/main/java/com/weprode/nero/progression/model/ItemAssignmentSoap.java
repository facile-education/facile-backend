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

package com.weprode.nero.progression.model;

import com.weprode.nero.progression.service.persistence.ItemAssignmentPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.progression.service.http.ItemAssignmentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ItemAssignmentSoap implements Serializable {

	public static ItemAssignmentSoap toSoapModel(ItemAssignment model) {
		ItemAssignmentSoap soapModel = new ItemAssignmentSoap();

		soapModel.setProgressionItemId(model.getProgressionItemId());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setHomeworkId(model.getHomeworkId());
		soapModel.setAssignedDate(model.getAssignedDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static ItemAssignmentSoap[] toSoapModels(ItemAssignment[] models) {
		ItemAssignmentSoap[] soapModels = new ItemAssignmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ItemAssignmentSoap[][] toSoapModels(
		ItemAssignment[][] models) {

		ItemAssignmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ItemAssignmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ItemAssignmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ItemAssignmentSoap[] toSoapModels(
		List<ItemAssignment> models) {

		List<ItemAssignmentSoap> soapModels = new ArrayList<ItemAssignmentSoap>(
			models.size());

		for (ItemAssignment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ItemAssignmentSoap[soapModels.size()]);
	}

	public ItemAssignmentSoap() {
	}

	public ItemAssignmentPK getPrimaryKey() {
		return new ItemAssignmentPK(_progressionItemId, _sessionId);
	}

	public void setPrimaryKey(ItemAssignmentPK pk) {
		setProgressionItemId(pk.progressionItemId);
		setSessionId(pk.sessionId);
	}

	public long getProgressionItemId() {
		return _progressionItemId;
	}

	public void setProgressionItemId(long progressionItemId) {
		_progressionItemId = progressionItemId;
	}

	public long getSessionId() {
		return _sessionId;
	}

	public void setSessionId(long sessionId) {
		_sessionId = sessionId;
	}

	public long getHomeworkId() {
		return _homeworkId;
	}

	public void setHomeworkId(long homeworkId) {
		_homeworkId = homeworkId;
	}

	public Date getAssignedDate() {
		return _assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		_assignedDate = assignedDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private long _progressionItemId;
	private long _sessionId;
	private long _homeworkId;
	private Date _assignedDate;
	private Date _modifiedDate;

}