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

package com.weprode.nero.progression.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ProgressionFolder service. Represents a row in the &quot;Progression_ProgressionFolder&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionFolderModel
 * @generated
 */
@ImplementationClassName(
	"com.weprode.nero.progression.model.impl.ProgressionFolderImpl"
)
@ProviderType
public interface ProgressionFolder
	extends PersistedModel, ProgressionFolderModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.weprode.nero.progression.model.impl.ProgressionFolderImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ProgressionFolder, Long>
		PROGRESSION_FOLDER_ID_ACCESSOR =
			new Accessor<ProgressionFolder, Long>() {

				@Override
				public Long get(ProgressionFolder progressionFolder) {
					return progressionFolder.getProgressionFolderId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<ProgressionFolder> getTypeClass() {
					return ProgressionFolder.class;
				}

			};

	public com.liferay.portal.kernel.json.JSONObject convertToJSON();

}