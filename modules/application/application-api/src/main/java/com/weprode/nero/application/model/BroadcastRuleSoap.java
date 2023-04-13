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

package com.weprode.nero.application.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.application.service.http.BroadcastRuleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class BroadcastRuleSoap implements Serializable {

	public static BroadcastRuleSoap toSoapModel(BroadcastRule model) {
		BroadcastRuleSoap soapModel = new BroadcastRuleSoap();

		soapModel.setBroadcastRuleId(model.getBroadcastRuleId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setOrgId(model.getOrgId());
		soapModel.setGroupId(model.getGroupId());

		return soapModel;
	}

	public static BroadcastRuleSoap[] toSoapModels(BroadcastRule[] models) {
		BroadcastRuleSoap[] soapModels = new BroadcastRuleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BroadcastRuleSoap[][] toSoapModels(BroadcastRule[][] models) {
		BroadcastRuleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BroadcastRuleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BroadcastRuleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BroadcastRuleSoap[] toSoapModels(List<BroadcastRule> models) {
		List<BroadcastRuleSoap> soapModels = new ArrayList<BroadcastRuleSoap>(
			models.size());

		for (BroadcastRule model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BroadcastRuleSoap[soapModels.size()]);
	}

	public BroadcastRuleSoap() {
	}

	public long getPrimaryKey() {
		return _broadcastRuleId;
	}

	public void setPrimaryKey(long pk) {
		setBroadcastRuleId(pk);
	}

	public long getBroadcastRuleId() {
		return _broadcastRuleId;
	}

	public void setBroadcastRuleId(long broadcastRuleId) {
		_broadcastRuleId = broadcastRuleId;
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	public long getOrgId() {
		return _orgId;
	}

	public void setOrgId(long orgId) {
		_orgId = orgId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	private long _broadcastRuleId;
	private long _applicationId;
	private long _schoolId;
	private long _roleId;
	private long _orgId;
	private long _groupId;

}