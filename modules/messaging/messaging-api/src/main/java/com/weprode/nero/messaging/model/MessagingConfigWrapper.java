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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessagingConfig}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingConfig
 * @generated
 */
public class MessagingConfigWrapper
	extends BaseModelWrapper<MessagingConfig>
	implements MessagingConfig, ModelWrapper<MessagingConfig> {

	public MessagingConfigWrapper(MessagingConfig messagingConfig) {
		super(messagingConfig);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("isForwardActive", isIsForwardActive());
		attributes.put("forwardMail", getForwardMail());
		attributes.put("isSignatureActive", isIsSignatureActive());
		attributes.put("signature", getSignature());
		attributes.put("isAutoReplyActive", isIsAutoReplyActive());
		attributes.put("autoReplyContent", getAutoReplyContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Boolean isForwardActive = (Boolean)attributes.get("isForwardActive");

		if (isForwardActive != null) {
			setIsForwardActive(isForwardActive);
		}

		String forwardMail = (String)attributes.get("forwardMail");

		if (forwardMail != null) {
			setForwardMail(forwardMail);
		}

		Boolean isSignatureActive = (Boolean)attributes.get(
			"isSignatureActive");

		if (isSignatureActive != null) {
			setIsSignatureActive(isSignatureActive);
		}

		String signature = (String)attributes.get("signature");

		if (signature != null) {
			setSignature(signature);
		}

		Boolean isAutoReplyActive = (Boolean)attributes.get(
			"isAutoReplyActive");

		if (isAutoReplyActive != null) {
			setIsAutoReplyActive(isAutoReplyActive);
		}

		String autoReplyContent = (String)attributes.get("autoReplyContent");

		if (autoReplyContent != null) {
			setAutoReplyContent(autoReplyContent);
		}
	}

	@Override
	public MessagingConfig cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the auto reply content of this messaging config.
	 *
	 * @return the auto reply content of this messaging config
	 */
	@Override
	public String getAutoReplyContent() {
		return model.getAutoReplyContent();
	}

	/**
	 * Returns the forward mail of this messaging config.
	 *
	 * @return the forward mail of this messaging config
	 */
	@Override
	public String getForwardMail() {
		return model.getForwardMail();
	}

	/**
	 * Returns the is auto reply active of this messaging config.
	 *
	 * @return the is auto reply active of this messaging config
	 */
	@Override
	public boolean getIsAutoReplyActive() {
		return model.getIsAutoReplyActive();
	}

	/**
	 * Returns the is forward active of this messaging config.
	 *
	 * @return the is forward active of this messaging config
	 */
	@Override
	public boolean getIsForwardActive() {
		return model.getIsForwardActive();
	}

	/**
	 * Returns the is signature active of this messaging config.
	 *
	 * @return the is signature active of this messaging config
	 */
	@Override
	public boolean getIsSignatureActive() {
		return model.getIsSignatureActive();
	}

	/**
	 * Returns the primary key of this messaging config.
	 *
	 * @return the primary key of this messaging config
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the signature of this messaging config.
	 *
	 * @return the signature of this messaging config
	 */
	@Override
	public String getSignature() {
		return model.getSignature();
	}

	/**
	 * Returns the user ID of this messaging config.
	 *
	 * @return the user ID of this messaging config
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this messaging config.
	 *
	 * @return the user uuid of this messaging config
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this messaging config is is auto reply active.
	 *
	 * @return <code>true</code> if this messaging config is is auto reply active; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsAutoReplyActive() {
		return model.isIsAutoReplyActive();
	}

	/**
	 * Returns <code>true</code> if this messaging config is is forward active.
	 *
	 * @return <code>true</code> if this messaging config is is forward active; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsForwardActive() {
		return model.isIsForwardActive();
	}

	/**
	 * Returns <code>true</code> if this messaging config is is signature active.
	 *
	 * @return <code>true</code> if this messaging config is is signature active; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsSignatureActive() {
		return model.isIsSignatureActive();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the auto reply content of this messaging config.
	 *
	 * @param autoReplyContent the auto reply content of this messaging config
	 */
	@Override
	public void setAutoReplyContent(String autoReplyContent) {
		model.setAutoReplyContent(autoReplyContent);
	}

	/**
	 * Sets the forward mail of this messaging config.
	 *
	 * @param forwardMail the forward mail of this messaging config
	 */
	@Override
	public void setForwardMail(String forwardMail) {
		model.setForwardMail(forwardMail);
	}

	/**
	 * Sets whether this messaging config is is auto reply active.
	 *
	 * @param isAutoReplyActive the is auto reply active of this messaging config
	 */
	@Override
	public void setIsAutoReplyActive(boolean isAutoReplyActive) {
		model.setIsAutoReplyActive(isAutoReplyActive);
	}

	/**
	 * Sets whether this messaging config is is forward active.
	 *
	 * @param isForwardActive the is forward active of this messaging config
	 */
	@Override
	public void setIsForwardActive(boolean isForwardActive) {
		model.setIsForwardActive(isForwardActive);
	}

	/**
	 * Sets whether this messaging config is is signature active.
	 *
	 * @param isSignatureActive the is signature active of this messaging config
	 */
	@Override
	public void setIsSignatureActive(boolean isSignatureActive) {
		model.setIsSignatureActive(isSignatureActive);
	}

	/**
	 * Sets the primary key of this messaging config.
	 *
	 * @param primaryKey the primary key of this messaging config
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the signature of this messaging config.
	 *
	 * @param signature the signature of this messaging config
	 */
	@Override
	public void setSignature(String signature) {
		model.setSignature(signature);
	}

	/**
	 * Sets the user ID of this messaging config.
	 *
	 * @param userId the user ID of this messaging config
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this messaging config.
	 *
	 * @param userUuid the user uuid of this messaging config
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected MessagingConfigWrapper wrap(MessagingConfig messagingConfig) {
		return new MessagingConfigWrapper(messagingConfig);
	}

}