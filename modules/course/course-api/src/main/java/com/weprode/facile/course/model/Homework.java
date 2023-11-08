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

package com.weprode.facile.course.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Homework service. Represents a row in the &quot;Course_Homework&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see HomeworkModel
 * @generated
 */
@ImplementationClassName("com.weprode.facile.course.model.impl.HomeworkImpl")
@ProviderType
public interface Homework extends HomeworkModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.facile.course.model.impl.HomeworkImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Homework, Long> HOMEWORK_ID_ACCESSOR =
		new Accessor<Homework, Long>() {

			@Override
			public Long get(Homework homework) {
				return homework.getHomeworkId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Homework> getTypeClass() {
				return Homework.class;
			}

		};

	public org.json.JSONObject convertToJSON(
		com.liferay.portal.kernel.model.User user, boolean includeBlocks);

}