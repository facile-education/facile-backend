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

package com.weprode.nero.course.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.course.service.http.SessionContentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SessionContentSoap implements Serializable {

	public static SessionContentSoap toSoapModel(SessionContent model) {
		SessionContentSoap soapModel = new SessionContentSoap();

		soapModel.setSessionId(model.getSessionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setTeacherId(model.getTeacherId());
		soapModel.setTitle(model.getTitle());
		soapModel.setModificationDate(model.getModificationDate());
		soapModel.setPublicationDate(model.getPublicationDate());
		soapModel.setIsDraft(model.isIsDraft());

		return soapModel;
	}

	public static SessionContentSoap[] toSoapModels(SessionContent[] models) {
		SessionContentSoap[] soapModels = new SessionContentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SessionContentSoap[][] toSoapModels(
		SessionContent[][] models) {

		SessionContentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SessionContentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SessionContentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SessionContentSoap[] toSoapModels(
		List<SessionContent> models) {

		List<SessionContentSoap> soapModels = new ArrayList<SessionContentSoap>(
			models.size());

		for (SessionContent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SessionContentSoap[soapModels.size()]);
	}

	public SessionContentSoap() {
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

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getTeacherId() {
		return _teacherId;
	}

	public void setTeacherId(long teacherId) {
		_teacherId = teacherId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public Date getModificationDate() {
		return _modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		_modificationDate = modificationDate;
	}

	public Date getPublicationDate() {
		return _publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		_publicationDate = publicationDate;
	}

	public boolean getIsDraft() {
		return _isDraft;
	}

	public boolean isIsDraft() {
		return _isDraft;
	}

	public void setIsDraft(boolean isDraft) {
		_isDraft = isDraft;
	}

	private long _sessionId;
	private long _companyId;
	private long _teacherId;
	private String _title;
	private Date _modificationDate;
	private Date _publicationDate;
	private boolean _isDraft;

}