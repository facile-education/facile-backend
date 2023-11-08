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

package com.weprode.facile.messaging.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.messaging.model.MessagingConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MessagingConfig in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessagingConfigCacheModel
	implements CacheModel<MessagingConfig>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessagingConfigCacheModel)) {
			return false;
		}

		MessagingConfigCacheModel messagingConfigCacheModel =
			(MessagingConfigCacheModel)object;

		if (userId == messagingConfigCacheModel.userId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", isForwardActive=");
		sb.append(isForwardActive);
		sb.append(", forwardMail=");
		sb.append(forwardMail);
		sb.append(", isSignatureActive=");
		sb.append(isSignatureActive);
		sb.append(", signature=");
		sb.append(signature);
		sb.append(", isAutoReplyActive=");
		sb.append(isAutoReplyActive);
		sb.append(", autoReplyContent=");
		sb.append(autoReplyContent);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MessagingConfig toEntityModel() {
		MessagingConfigImpl messagingConfigImpl = new MessagingConfigImpl();

		messagingConfigImpl.setUserId(userId);
		messagingConfigImpl.setIsForwardActive(isForwardActive);

		if (forwardMail == null) {
			messagingConfigImpl.setForwardMail("");
		}
		else {
			messagingConfigImpl.setForwardMail(forwardMail);
		}

		messagingConfigImpl.setIsSignatureActive(isSignatureActive);

		if (signature == null) {
			messagingConfigImpl.setSignature("");
		}
		else {
			messagingConfigImpl.setSignature(signature);
		}

		messagingConfigImpl.setIsAutoReplyActive(isAutoReplyActive);

		if (autoReplyContent == null) {
			messagingConfigImpl.setAutoReplyContent("");
		}
		else {
			messagingConfigImpl.setAutoReplyContent(autoReplyContent);
		}

		messagingConfigImpl.resetOriginalValues();

		return messagingConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();

		isForwardActive = objectInput.readBoolean();
		forwardMail = objectInput.readUTF();

		isSignatureActive = objectInput.readBoolean();
		signature = objectInput.readUTF();

		isAutoReplyActive = objectInput.readBoolean();
		autoReplyContent = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(userId);

		objectOutput.writeBoolean(isForwardActive);

		if (forwardMail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(forwardMail);
		}

		objectOutput.writeBoolean(isSignatureActive);

		if (signature == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(signature);
		}

		objectOutput.writeBoolean(isAutoReplyActive);

		if (autoReplyContent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(autoReplyContent);
		}
	}

	public long userId;
	public boolean isForwardActive;
	public String forwardMail;
	public boolean isSignatureActive;
	public String signature;
	public boolean isAutoReplyActive;
	public String autoReplyContent;

}