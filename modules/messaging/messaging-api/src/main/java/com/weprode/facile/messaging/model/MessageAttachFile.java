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

package com.weprode.facile.messaging.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the MessageAttachFile service. Represents a row in the &quot;Messaging_MessageAttachFile&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MessageAttachFileModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.facile.messaging.model.impl.MessageAttachFileImpl"
)
@ProviderType
public interface MessageAttachFile
	extends MessageAttachFileModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.facile.messaging.model.impl.MessageAttachFileImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MessageAttachFile, Long> MESSAGE_ID_ACCESSOR =
		new Accessor<MessageAttachFile, Long>() {

			@Override
			public Long get(MessageAttachFile messageAttachFile) {
				return messageAttachFile.getMessageId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MessageAttachFile> getTypeClass() {
				return MessageAttachFile.class;
			}

		};
	public static final Accessor<MessageAttachFile, Long> FILE_ID_ACCESSOR =
		new Accessor<MessageAttachFile, Long>() {

			@Override
			public Long get(MessageAttachFile messageAttachFile) {
				return messageAttachFile.getFileId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MessageAttachFile> getTypeClass() {
				return MessageAttachFile.class;
			}

		};

}