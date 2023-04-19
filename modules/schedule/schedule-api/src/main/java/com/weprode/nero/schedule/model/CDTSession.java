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

package com.weprode.nero.schedule.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the CDTSession service. Represents a row in the &quot;Schedule_CDTSession&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CDTSessionModel
 * @generated
 */
@ImplementationClassName("com.weprode.nero.schedule.model.impl.CDTSessionImpl")
@ProviderType
public interface CDTSession extends CDTSessionModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.nero.schedule.model.impl.CDTSessionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CDTSession, Long> SESSION_ID_ACCESSOR =
		new Accessor<CDTSession, Long>() {

			@Override
			public Long get(CDTSession cdtSession) {
				return cdtSession.getSessionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CDTSession> getTypeClass() {
				return CDTSession.class;
			}

		};

	public com.liferay.portal.kernel.json.JSONObject convertToJSON();

	public com.liferay.portal.kernel.json.JSONObject convertToJSON(
		boolean includeDetails, com.liferay.portal.kernel.model.User user);

	public com.liferay.portal.kernel.json.JSONObject convertToJSON(
		long colorsTeacherId, com.liferay.portal.kernel.model.User user);

	public java.util.List<CDTSession> getPreviousSessions(
		com.liferay.portal.kernel.model.User user);

	public java.util.List<CDTSession> getNextSessions(
		com.liferay.portal.kernel.model.User user);

	public String getTeacherList();

	public String getSessionGroupName(boolean withSchoolName);

}