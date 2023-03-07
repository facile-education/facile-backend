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

package com.weprode.nero.group.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class GroupMembershipSoap implements Serializable {

	public static GroupMembershipSoap toSoapModel(GroupMembership model) {
		GroupMembershipSoap soapModel = new GroupMembershipSoap();

		soapModel.setMembershipId(model.getMembershipId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setFullYear(model.isFullYear());

		return soapModel;
	}

	public static GroupMembershipSoap[] toSoapModels(GroupMembership[] models) {
		GroupMembershipSoap[] soapModels =
			new GroupMembershipSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GroupMembershipSoap[][] toSoapModels(
		GroupMembership[][] models) {

		GroupMembershipSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new GroupMembershipSoap[models.length][models[0].length];
		}
		else {
			soapModels = new GroupMembershipSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GroupMembershipSoap[] toSoapModels(
		List<GroupMembership> models) {

		List<GroupMembershipSoap> soapModels =
			new ArrayList<GroupMembershipSoap>(models.size());

		for (GroupMembership model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GroupMembershipSoap[soapModels.size()]);
	}

	public GroupMembershipSoap() {
	}

	public long getPrimaryKey() {
		return _membershipId;
	}

	public void setPrimaryKey(long pk) {
		setMembershipId(pk);
	}

	public long getMembershipId() {
		return _membershipId;
	}

	public void setMembershipId(long membershipId) {
		_membershipId = membershipId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public boolean getFullYear() {
		return _fullYear;
	}

	public boolean isFullYear() {
		return _fullYear;
	}

	public void setFullYear(boolean fullYear) {
		_fullYear = fullYear;
	}

	private long _membershipId;
	private long _groupId;
	private long _userId;
	private Date _startDate;
	private Date _endDate;
	private boolean _fullYear;

}