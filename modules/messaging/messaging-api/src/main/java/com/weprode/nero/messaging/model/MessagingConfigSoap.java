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

package com.weprode.nero.messaging.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.messaging.service.http.MessagingConfigServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MessagingConfigSoap implements Serializable {

	public static MessagingConfigSoap toSoapModel(MessagingConfig model) {
		MessagingConfigSoap soapModel = new MessagingConfigSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setIsForwardActive(model.isIsForwardActive());
		soapModel.setForwardMail(model.getForwardMail());
		soapModel.setIsSignatureActive(model.isIsSignatureActive());
		soapModel.setSignature(model.getSignature());
		soapModel.setIsAutoReplyActive(model.isIsAutoReplyActive());
		soapModel.setAutoReplyContent(model.getAutoReplyContent());

		return soapModel;
	}

	public static MessagingConfigSoap[] toSoapModels(MessagingConfig[] models) {
		MessagingConfigSoap[] soapModels =
			new MessagingConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MessagingConfigSoap[][] toSoapModels(
		MessagingConfig[][] models) {

		MessagingConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new MessagingConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MessagingConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MessagingConfigSoap[] toSoapModels(
		List<MessagingConfig> models) {

		List<MessagingConfigSoap> soapModels =
			new ArrayList<MessagingConfigSoap>(models.size());

		for (MessagingConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MessagingConfigSoap[soapModels.size()]);
	}

	public MessagingConfigSoap() {
	}

	public long getPrimaryKey() {
		return _userId;
	}

	public void setPrimaryKey(long pk) {
		setUserId(pk);
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public boolean getIsForwardActive() {
		return _isForwardActive;
	}

	public boolean isIsForwardActive() {
		return _isForwardActive;
	}

	public void setIsForwardActive(boolean isForwardActive) {
		_isForwardActive = isForwardActive;
	}

	public String getForwardMail() {
		return _forwardMail;
	}

	public void setForwardMail(String forwardMail) {
		_forwardMail = forwardMail;
	}

	public boolean getIsSignatureActive() {
		return _isSignatureActive;
	}

	public boolean isIsSignatureActive() {
		return _isSignatureActive;
	}

	public void setIsSignatureActive(boolean isSignatureActive) {
		_isSignatureActive = isSignatureActive;
	}

	public String getSignature() {
		return _signature;
	}

	public void setSignature(String signature) {
		_signature = signature;
	}

	public boolean getIsAutoReplyActive() {
		return _isAutoReplyActive;
	}

	public boolean isIsAutoReplyActive() {
		return _isAutoReplyActive;
	}

	public void setIsAutoReplyActive(boolean isAutoReplyActive) {
		_isAutoReplyActive = isAutoReplyActive;
	}

	public String getAutoReplyContent() {
		return _autoReplyContent;
	}

	public void setAutoReplyContent(String autoReplyContent) {
		_autoReplyContent = autoReplyContent;
	}

	private long _userId;
	private boolean _isForwardActive;
	private String _forwardMail;
	private boolean _isSignatureActive;
	private String _signature;
	private boolean _isAutoReplyActive;
	private String _autoReplyContent;

}