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
public class MembershipActivitySoap implements Serializable {

	public static MembershipActivitySoap toSoapModel(MembershipActivity model) {
		MembershipActivitySoap soapModel = new MembershipActivitySoap();

		soapModel.setMembershipActivityId(model.getMembershipActivityId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setActionUserId(model.getActionUserId());
		soapModel.setTargetUserIds(model.getTargetUserIds());
		soapModel.setIncoming(model.isIncoming());
		soapModel.setMovementDate(model.getMovementDate());

		return soapModel;
	}

	public static MembershipActivitySoap[] toSoapModels(
		MembershipActivity[] models) {

		MembershipActivitySoap[] soapModels =
			new MembershipActivitySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MembershipActivitySoap[][] toSoapModels(
		MembershipActivity[][] models) {

		MembershipActivitySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new MembershipActivitySoap[models.length][models[0].length];
		}
		else {
			soapModels = new MembershipActivitySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MembershipActivitySoap[] toSoapModels(
		List<MembershipActivity> models) {

		List<MembershipActivitySoap> soapModels =
			new ArrayList<MembershipActivitySoap>(models.size());

		for (MembershipActivity model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new MembershipActivitySoap[soapModels.size()]);
	}

	public MembershipActivitySoap() {
	}

	public long getPrimaryKey() {
		return _membershipActivityId;
	}

	public void setPrimaryKey(long pk) {
		setMembershipActivityId(pk);
	}

	public long getMembershipActivityId() {
		return _membershipActivityId;
	}

	public void setMembershipActivityId(long membershipActivityId) {
		_membershipActivityId = membershipActivityId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getActionUserId() {
		return _actionUserId;
	}

	public void setActionUserId(long actionUserId) {
		_actionUserId = actionUserId;
	}

	public String getTargetUserIds() {
		return _targetUserIds;
	}

	public void setTargetUserIds(String targetUserIds) {
		_targetUserIds = targetUserIds;
	}

	public boolean getIncoming() {
		return _incoming;
	}

	public boolean isIncoming() {
		return _incoming;
	}

	public void setIncoming(boolean incoming) {
		_incoming = incoming;
	}

	public Date getMovementDate() {
		return _movementDate;
	}

	public void setMovementDate(Date movementDate) {
		_movementDate = movementDate;
	}

	private long _membershipActivityId;
	private long _groupId;
	private long _actionUserId;
	private String _targetUserIds;
	private boolean _incoming;
	private Date _movementDate;

}