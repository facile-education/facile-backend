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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SessionContent service. Represents a row in the &quot;Course_SessionContent&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SessionContentModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.nero.course.model.impl.SessionContentImpl"
)
@ProviderType
public interface SessionContent extends PersistedModel, SessionContentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.nero.course.model.impl.SessionContentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SessionContent, Long> SESSION_ID_ACCESSOR =
		new Accessor<SessionContent, Long>() {

			@Override
			public Long get(SessionContent sessionContent) {
				return sessionContent.getSessionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SessionContent> getTypeClass() {
				return SessionContent.class;
			}

		};

	public org.json.JSONObject convertToJSON(
		com.liferay.portal.kernel.model.User user, boolean isContentIncluded);

}